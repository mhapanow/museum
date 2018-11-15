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
	
	ZRSTDSPSAdapter adapted = null;
	List<ZRSTDSPSAdapter> list = new ArrayList<ZRSTDSPSAdapter>();
	
	
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
					"w4rrred",
					"w4rrre",
					"w4orgn",
					"w4rear",
					"w4rpre",
					"w4rpgi",
					"w4rbpi",
					"w4rbeg",
					"w4rava",
					"w4rpad",
					"w4rred",
					"w4rrmp",
			};
			
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
			returnValue.put("meyfac", this.meyfac);
			returnValue.put("meaafc", this.meaafc);
			returnValue.put("mecifa", this.mecifa);
			returnValue.put("meagig", this.meagig);
			returnValue.put("melogo", this.melogo);
			returnValue.put("mencct", this.mencct);
			
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
				
				adapted.setW4rrred(o.getRwrrre());
				adapted.setW4rrre(o.getRwrrre());
				adapted.setW4orgn(o.getRworg().toString());
				adapted.setW4rear(o.getRwrear().toString());
				adapted.setW4rpre(o.getRwrpre().toString());
				adapted.setW4rpgi(o.getRwrpgi().toString());
				adapted.setW4rbpi(o.getRwrbpi().toString());
				adapted.setW4rbeg(o.getRwrbeg().toString());
				adapted.setW4rava(o.getRwrava().toString());
				adapted.setW4rpad(o.getRwrpad().toString());
				adapted.setW4rred(o.getRwrred().toString());
				adapted.setW4rrmp(o.getRwrrmp().toString());
				
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
		String w4rrred = null;
		String w4rrre = null;
		String w4orgn = null;
		String w4rear = null;
		String w4rpre = null;
		String w4rpgi = null;
		String w4rbpi = null;
		String w4rbeg = null;
		String w4rava = null;
		String w4rpad = null;
		String w4rred = null;
		String w4rrmp = null;
		
		public ZRSTDSPSAdapter() {
			
		}

		public String getW4rrred() {
			return w4rrred;
		}

		public void setW4rrred(String w4rrred) {
			this.w4rrred = w4rrred;
		}

		public String getW4rrre() {
			return w4rrre;
		}

		public void setW4rrre(String w4rrre) {
			this.w4rrre = w4rrre;
		}

		public String getW4orgn() {
			return w4orgn;
		}

		public void setW4orgn(String w4orgn) {
			this.w4orgn = w4orgn;
		}

		public String getW4rear() {
			return w4rear;
		}

		public void setW4rear(String w4rear) {
			this.w4rear = w4rear;
		}

		public String getW4rpre() {
			return w4rpre;
		}

		public void setW4rpre(String w4rpre) {
			this.w4rpre = w4rpre;
		}

		public String getW4rpgi() {
			return w4rpgi;
		}

		public void setW4rpgi(String w4rpgi) {
			this.w4rpgi = w4rpgi;
		}

		public String getW4rbpi() {
			return w4rbpi;
		}

		public void setW4rbpi(String w4rbpi) {
			this.w4rbpi = w4rbpi;
		}

		public String getW4rbeg() {
			return w4rbeg;
		}

		public void setW4rbeg(String w4rbeg) {
			this.w4rbeg = w4rbeg;
		}

		public String getW4rava() {
			return w4rava;
		}

		public void setW4rava(String w4rava) {
			this.w4rava = w4rava;
		}

		public String getW4rpad() {
			return w4rpad;
		}

		public void setW4rpad(String w4rpad) {
			this.w4rpad = w4rpad;
		}

		public String getW4rred() {
			return w4rred;
		}

		public void setW4rred(String w4rred) {
			this.w4rred = w4rred;
		}

		public String getW4rrmp() {
			return w4rrmp;
		}

		public void setW4rrmp(String w4rrmp) {
			this.w4rrmp = w4rrmp;
		}
		
		

		
	}
	
}
