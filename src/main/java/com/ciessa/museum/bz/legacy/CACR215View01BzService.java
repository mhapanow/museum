package com.ciessa.museum.bz.legacy;

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
import com.ciessa.museum.dao.legacy.CacpmreDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cacpmre;
import com.ciessa.museum.tools.CollectionFactory;

public class CACR215View01BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(CACR215View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CacpmreDAO myDao;
	
	@Get
	public String list() {
    	long start = markStart();
		JSONObject returnValue = null;
		try {
			List<Cacpmre> list = new ArrayList<Cacpmre>();
			
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet()); 
							
			// Get order
			String order = this.obtainStringValue("ORDER", null);

			// retrieve all elements
			Map<String,String> attributes = CollectionFactory.createMap();
			long millisPre = new Date().getTime();
			list = myDao.getUsing(ds, order, attributes);
			
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found [" + list.size() + "] in " + diff + " millis");
			List<CACR215Adapter> adapted = new ArrayList<CACR215Adapter>();
			for( Cacpmre obj : list ) {
				adapted.add(new CACR215Adapter(obj));
			}

			String[] fields = new String[] {
					"pkid",
					"member",
					"WSMREC",
					"WSCORT",
					"WSDESC"
			};

			returnValue = this.getJSONRepresentationFromArrayOfObjects(adapted, fields);
			
			if( attributes.containsKey("recordCount"))
				returnValue.put("recordCount", Long.valueOf(attributes.get("recordCount")));
			else 
				returnValue.put("recordCount", list.size());
				
    	} catch (ASException e) {
    		if( e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENEXPIRED_CODE || 
    				e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENMISSING_CODE) {
    			log.log(Level.INFO, e.getMessage());
    		} else {
    			log.log(Level.SEVERE, e.getMessage(), e);
    		}
    		returnValue = getJSONRepresentationFromException(e);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			returnValue = this.getJSONRepresentationFromException(e);
		} finally {
			markEnd(start);
		}
		return returnValue.toString();
	}
	
	public class CACR215Adapter {
		private String pkid;
		private String member;
		private Integer WSMREC;
		private String WSCORT;
		private String WSDESC;
		
		public CACR215Adapter(Cacpmre src) {
			this.pkid = src.getPkid();
			this.member = src.getMember();
			this.WSMREC = src.getMrmrec();
			this.WSCORT = src.getMrcort();
			this.WSDESC = src.getMrdesc();
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

		public Integer getWSMREC() {
			return WSMREC;
		}

		public void setWSMREC(Integer wSMREC) {
			WSMREC = wSMREC;
		}

		public String getWSCORT() {
			return WSCORT;
		}

		public void setWSCORT(String wSCORT) {
			WSCORT = wSCORT;
		}

		public String getWSDESC() {
			return WSDESC;
		}

		public void setWSDESC(String wSDESC) {
			WSDESC = wSDESC;
		}

	}
	

}
