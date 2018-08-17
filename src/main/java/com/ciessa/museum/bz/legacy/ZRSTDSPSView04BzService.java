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
import com.ciessa.museum.dao.legacy.ZrsprwdDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Zrsprwd;
import com.ciessa.museum.tools.CollectionFactory;

public class ZRSTDSPSView04BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(ZRSTDSPSView04BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	ZrsprwdDAO myDaoZrsprwd;	
	
	Integer meyfac= null;
	Integer meaafc= null;
	String mecifa= null;
	String meagig= null;
	Integer melogo= null;
	String mencct= null;
	
	Integer rwaafc = null;
	String rwcifa = null;
	String rwagig = null;
	Integer rwlogo = null;
	String rwncct = null;
	
	Integer meaafcd = null;
	
	List<Zrsprwd> listZrsprwd = null;
	List<ZRSTDSPSAdapter> list = new ArrayList<ZRSTDSPSAdapter>();
	ZRSTDSPSAdapter adapted = null;
	
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			meyfac = obtainIntegerValue("meyfac", null);
			meaafc = obtainIntegerValue("meaafc", null);
			mecifa = obtainStringValue("mecifa", null);
			meagig = obtainStringValue("meagig", null);
			melogo = obtainIntegerValue("melogo", null);
			mencct = obtainStringValue("mencct", null);
			
			String rpta = SubProcDspbonus(ds);
			
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			// retrieve all elements
			Map<String,String> attributes = CollectionFactory.createMap();
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found [" + list.size() + "] in " + diff + " millis");
			
			String[] fields = new String[] {
					"W4SEOP",
					"W4RRRED",
					"W4ORGN",
					"W4REAR",
					"W4RPRE"
			};
			
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
			
			if( attributes.containsKey("recordCount"))
				returnValue.put("recordCount", Long.valueOf(attributes.get("recordCount")));
			else 
				returnValue.put("recordCount", list.size());
			
		} catch (ASException e) {
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
	
	private String SubProcDspbonus(DataSet ds) {
		try {
			this.rwaafc = this.meyfac;
			this.rwaafc = this.meaafc; // TODO.: ERROR DOBLE VARIABLE
			this.rwcifa = this.mecifa;
			this.rwagig = this.meagig;
			this.rwlogo = this.melogo;
			this.rwncct = this.mencct;
			if (this.meaafc <= 60) {
				this.meaafcd = 2000 + this.meaafc;
			}else {
				this.meaafcd = 1900 + this.meaafc;
			}
			listZrsprwd = myDaoZrsprwd.getUsingListRwaafcAndRwcifaAndRwagigAndRwlogoAndRwncct(ds, rwaafc, rwcifa, rwagig, rwlogo, rwncct);
			for(Zrsprwd o : listZrsprwd ) {
				
				adapted = new ZRSTDSPSAdapter();
				
				adapted.setW4RRRED(o.getRwrrre());
				adapted.setW4RRRE(o.getRwrrre());
				adapted.setW4ORGN(o.getRworg().toString());
				adapted.setW4REAR(o.getRwrear().toString());
				adapted.setW4RPRE(o.getRwrpre().toString());
				adapted.setW4RPGI(o.getRwrpgi().toString());
				adapted.setW4RBPI(o.getRwrbpi().toString());
				adapted.setW4RBEG(o.getRwrbeg().toString());
				adapted.setW4RAVA(o.getRwrava().toString());
				adapted.setW4RPAD(o.getRwrpad().toString());
				adapted.setW4RRED(o.getRwrred().toString());
				adapted.setW4RRMP(o.getRwrrmp().toString());
				
				list.add(adapted);
			}
			
			return "";
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	public class ZRSTDSPSAdapter {
		String W4RRRED = null;
		String W4RRRE = null;
		String W4ORGN = null;
		String W4REAR = null;
		String W4RPRE = null;
		String W4RPGI = null;
		String W4RBPI = null;
		String W4RBEG = null;
		String W4RAVA = null;
		String W4RPAD = null;
		String W4RRED = null;
		String W4RRMP = null;
		
		public ZRSTDSPSAdapter() {
			
		}

		public String getW4RRRED() {
			return W4RRRED;
		}

		public void setW4RRRED(String w4rrred) {
			W4RRRED = w4rrred;
		}

		public String getW4RRRE() {
			return W4RRRE;
		}

		public void setW4RRRE(String w4rrre) {
			W4RRRE = w4rrre;
		}

		public String getW4ORGN() {
			return W4ORGN;
		}

		public void setW4ORGN(String w4orgn) {
			W4ORGN = w4orgn;
		}

		public String getW4REAR() {
			return W4REAR;
		}

		public void setW4REAR(String w4rear) {
			W4REAR = w4rear;
		}

		public String getW4RPRE() {
			return W4RPRE;
		}

		public void setW4RPRE(String w4rpre) {
			W4RPRE = w4rpre;
		}

		public String getW4RPGI() {
			return W4RPGI;
		}

		public void setW4RPGI(String w4rpgi) {
			W4RPGI = w4rpgi;
		}

		public String getW4RBPI() {
			return W4RBPI;
		}

		public void setW4RBPI(String w4rbpi) {
			W4RBPI = w4rbpi;
		}

		public String getW4RBEG() {
			return W4RBEG;
		}

		public void setW4RBEG(String w4rbeg) {
			W4RBEG = w4rbeg;
		}

		public String getW4RAVA() {
			return W4RAVA;
		}

		public void setW4RAVA(String w4rava) {
			W4RAVA = w4rava;
		}

		public String getW4RPAD() {
			return W4RPAD;
		}

		public void setW4RPAD(String w4rpad) {
			W4RPAD = w4rpad;
		}

		public String getW4RRED() {
			return W4RRED;
		}

		public void setW4RRED(String w4rred) {
			W4RRED = w4rred;
		}

		public String getW4RRMP() {
			return W4RRMP;
		}

		public void setW4RRMP(String w4rrmp) {
			W4RRMP = w4rrmp;
		}
		
	}
	
}
