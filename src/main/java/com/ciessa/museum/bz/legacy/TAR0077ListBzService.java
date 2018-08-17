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
import com.ciessa.museum.dao.legacy.Cfp001220DAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cfp001220;
import com.ciessa.museum.tools.CollectionFactory;
import com.ciessa.museum.tools.Range;

public class TAR0077ListBzService extends RestBaseServerResource {

	public static final Logger log = Logger.getLogger(TAR0077ListBzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	Cfp001220DAO myDao;
	
	@Get
	public String list() {
		
    	long start = markStart();
		JSONObject returnValue = null;
		try {
			List<Cfp001220> list = new ArrayList<Cfp001220>();
			
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet()); 
			
			// get range, if not defined use default value
			// Range range = this.obtainRange();
			Range range = null;
				
			// Get order
			String order = this.obtainStringValue(ORDER, null);

			// retrieve all elements
			Map<String,String> attributes = CollectionFactory.createMap();
			long millisPre = new Date().getTime();
			list = myDao.getUsingKeyAndRange(ds, range, order, attributes);
			
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found [" + list.size() + "] in " + diff + " millis");
			List<TAR0077Adapter> adapted = new ArrayList<TAR0077Adapter>();
			for( Cfp001220 obj : list ) {
				adapted.add(new TAR0077Adapter(obj));
			}

			String[] fields = new String[] {
					"pkid",
					"member",
					"WSCODI",
					"WSDESC",
					"WSDESL"
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
	
	public class TAR0077Adapter {
		
		private String Pkid;
		private String member;
		private String WSCODI;
		private String WSDESC;
		private String WSDESL;
		
		public TAR0077Adapter(Cfp001220 src) {
			this.Pkid = src.getPkid();
			this.member = src.getMember();
			this.WSCODI = src.getCfctr();
			this.WSDESC = src.getCfdco();
			this.WSDESL = src.getCfdla();
		}

		/**
		 * @return the wSCODI
		 */
		public String getWSCODI() {
			return WSCODI;
		}

		/**
		 * @param wSCODI the wSCODI to set
		 */
		public void setWSCODI(String wSCODI) {
			WSCODI = wSCODI;
		}

		/**
		 * @return the wSDESC
		 */
		public String getWSDESC() {
			return WSDESC;
		}

		/**
		 * @param wSDESC the wSDESC to set
		 */
		public void setWSDESC(String wSDESC) {
			WSDESC = wSDESC;
		}

		/**
		 * @return the wSDESL
		 */
		public String getWSDESL() {
			return WSDESL;
		}

		/**
		 * @param wSDESL the wSDESL to set
		 */
		public void setWSDESL(String wSDESL) {
			WSDESL = wSDESL;
		}

		/**
		 * @return the pkid
		 */
		public String getPkid() {
			return Pkid;
		}

		/**
		 * @param pkid the pkid to set
		 */
		public void setPkid(String pkid) {
			Pkid = pkid;
		}

		/**
		 * @return the member
		 */
		public String getMember() {
			return member;
		}

		/**
		 * @param member the member to set
		 */
		public void setMember(String member) {
			this.member = member;
		}
		
	}
	
	
}
