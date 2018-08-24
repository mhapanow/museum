package com.ciessa.museum.bz.legacy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;
import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;

import com.ciessa.museum.model.legacy.Cacphst;
import com.ciessa.museum.tools.CollectionFactory;
import com.ciessa.museum.tools.Range;
import com.ciessa.museum.dao.legacy.CacphstDAO;


public class CACR216View01BzService extends RestBaseServerResource {

	public static final Logger log = Logger.getLogger(CACR216View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CacphstDAO myDaoCacphst;
	
	Cacphst ObjCacphst =  new Cacphst();

	//Entity
	List<Cacphst> listCacphst = null;
	
	//VariablesGlobales
	CARC216Adapter adapted = new CARC216Adapter();
	List<CARC216Adapter> list = null;
	Date sfeamd = null;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;		
		try
		{
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			
			Map<String,String> attributes = CollectionFactory.createMap();
			long millisPre = new Date().getTime();
			
			// get range, if not defined use default value
			// Range range = this.obtainRange();
			Range range = null;

			list = new ArrayList<CARC216Adapter>();
			
			listCacphst = myDaoCacphst.getUsingOrderHifsel(ds, range);
			
			for( Cacphst o : listCacphst ) {
				sfeamd = new SimpleDateFormat("yyyyMMdd").parse(o.getHifsel().toString());

				adapted.setWSFSEL(sfeamd);
				if (o.getHifunc().equals("P")) {
					adapted.setWSFUNC("Personal");
				}
				else {
					adapted.setWSFUNC("Back End");
				}
				if (o.getHitreg().equals("1")) {
					adapted.setWSTREG("ACT");
				}
				else {
					adapted.setWSTREG("REN");
				}
				list.add(adapted);
			}
			
			long diff = new Date().getTime() - millisPre;
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
					"pkid",
					"member",
					"WSFSEL",
					"WSTREG",
					"WSFUNC"
			};
			
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
			
			if( attributes.containsKey("recordCount"))
				returnValue.put("recordCount", Long.valueOf(attributes.get("recordCount")));
			else 
				returnValue.put("recordCount", list.size());
		} 
		catch (ASException e) {
			if (e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENEXPIRED_CODE
					|| e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENMISSING_CODE) {
				log.log(Level.INFO, e.getMessage());
			} else {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
			returnValue = getJSONRepresentationFromException(e);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			returnValue = getJSONRepresentationFromException(ASExceptionHelper.defaultException(e.getMessage(), e));
		} finally {
			markEnd(start);
		}
		return returnValue.toString();	
	}
	
	public class CARC216Adapter {
		private String pkid;
		private String member;
		Date WSFSEL  = null;
		String WSTREG  = null;
		String WSFUNC   = null;
		
		public CARC216Adapter() {
		}
				
		public String getPkid() {
			return pkid;
		}

		public void setPkid(String pkid) {
			this.pkid = pkid;
		}

		public String getMember() {
			return member;
		}

		public void setMember(String member) {
			this.member = member;
		}

		public Date getWSFSEL() {
			return WSFSEL;
		}
		public void setWSFSEL(Date wSFSEL) {
			WSFSEL = wSFSEL;
		}
		public String getWSTREG() {
			return WSTREG;
		}
		public void setWSTREG(String wSTREG) {
			WSTREG = wSTREG;
		}
		public String getWSFUNC() {
			return WSFUNC;
		}
		public void setWSFUNC(String wSFUNC) {
			WSFUNC = wSFUNC;
		}; 
	}
	
}