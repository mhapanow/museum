package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.ZrsppmaDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Zrsppma;
import com.ciessa.museum.tools.CollectionFactory;

public class ZRSTRECMView01BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(ZRSTRECMView01BzService.class.getName());
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	ZrsppmaDAO myDAOZrsppma;
	
	Zrsppma ObjZrsppma = new Zrsppma();
	
	//
	String parammeorg = null;
	String parammelogo = null;
	String parammencct = null;
	Integer parammeyfac = null;
	Integer parammeaafc = null;
	String parammecifa = null;
	String parammeagig  = null;
	String parammeapen  = null;
	
	String sm = null;//4140 elements
	String ss = null;//80 elements
	
	String imorg = null;
	String imlogo = null;
	String imncct = null;
	Integer imaaf4 = null;
	String imcifa = null;
	String imagig = null;
	
	String menu = null;
	
	String p1fec = null;
	String p1apen = null;
	String p1bicy = null;
	
	ZRSTRECMAdapter adapted = null;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			this.parammeorg = obtainStringValue("meorg", null);
			this.parammelogo = obtainStringValue("melogo", null);
			this.parammencct = obtainStringValue("mencct", null);
			this.parammeyfac = obtainIntegerValue("meyfac", null);
			this.parammeaafc = obtainIntegerValue("meaafc", null);
			this.parammecifa = String.format("%02d", Integer.parseInt(obtainStringValue("mecifa", null)));
			this.parammeagig = obtainStringValue("meagig", null);
			this.parammeapen = obtainStringValue("meapen", null);
			
			String rpta = SubProcDspmailing(ds);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			// retrieve all elements
			Map<String,String> attributes = CollectionFactory.createMap();
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found in " + diff + " millis");
			
			String[] fields = new String[] {
					"p1orgn",
					"p1logo",
					"p1acnb",
					"p1apen",
					"p1fec",
					"p1bicy",
					"p1m",
					"p1s",
			};

			adapted = new ZRSTRECMAdapter(); 
			adapted.setP1orgn(this.imorg);
			adapted.setP1logo(this.imlogo);
			adapted.setP1acnb(this.parammencct);
			adapted.setP1apen(this.p1apen);
			adapted.setP1fec(this.p1fec);
			adapted.setP1bicy(this.p1bicy);
			adapted.setP1m(this.sm);
			adapted.setP1s(this.ss);
			
			// Obtains the user JSON representation
			returnValue = getJSONRepresentationFromObject(adapted, fields);
			
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
	
	private String SubProcDspmailing(DataSet ds) {
		try {
			
			String rpta = this.SubRutSinzsr(ds);
			if (!rpta.equals(""))
			{
				return rpta;
			}
			
			this.sm = null;
			this.ss = null;
			this.menu = null;
			ObjZrsppma = myDAOZrsppma.getUsingImorgAndImlogoAndImncctAndImaaf4AndImcifaAndImagig(ds, imorg, imlogo, imncct, imaaf4.toString(), imcifa, imagig);
			
			if (ObjZrsppma != null) {
				if (ObjZrsppma.getImnmam() != 0) {
					this.sm= "M";
				}
				if (ObjZrsppma.getImnmas() != 0) {
					this.ss = "S";
				}
			}
			else {
				return "No existen datos";
			}
				
			if (this.sm != "" && this.ss != "") {
				String rut = this.SubRutRmenu(ds);
				if (!rut.equals(""))
				{
					return rut;
				}
			}
			return "";
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		
	}
	
	private String SubRutSinzsr(DataSet ds) {
		try {
			this.imorg = this.parammeorg; 
			this.imlogo = this.parammelogo; 
			this.imncct = this.parammencct; 
			this.imaaf4 = (this.parammeyfac * 100 ) + this.parammeaafc; 
			this.imcifa = this.parammecifa; 
			this.imagig = this.parammeagig;			
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}

	private String SubRutRmenu(DataSet ds) {
		try {
			this.menu = "S";
			this.p1fec = ObjZrsppma.getImcifa() + "/" + ObjZrsppma.getImaaf4().toString();
			this.p1apen = this.parammeapen;
			this.p1bicy = this.parammeagig;
			
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	public class ZRSTRECMAdapter {
		
		String p1orgn = null;
		String p1logo = null;
		String p1acnb = null;
		String p1apen = null;
		String p1fec = null;
		String p1bicy = null;
		String p1m = null;
		String p1s = null;
		
		public ZRSTRECMAdapter() {
			
		}

		public String getP1orgn() {
			return p1orgn;
		}

		public void setP1orgn(String p1orgn) {
			this.p1orgn = p1orgn;
		}

		public String getP1logo() {
			return p1logo;
		}

		public void setP1logo(String p1logo) {
			this.p1logo = p1logo;
		}

		public String getP1acnb() {
			return p1acnb;
		}

		public void setP1acnb(String p1acnb) {
			this.p1acnb = p1acnb;
		}

		public String getP1apen() {
			return p1apen;
		}

		public void setP1apen(String p1apen) {
			this.p1apen = p1apen;
		}

		public String getP1fec() {
			return p1fec;
		}

		public void setP1fec(String p1fec) {
			this.p1fec = p1fec;
		}

		public String getP1bicy() {
			return p1bicy;
		}

		public void setP1bicy(String p1bicy) {
			this.p1bicy = p1bicy;
		}

		public String getP1m() {
			return p1m;
		}

		public void setP1m(String p1m) {
			this.p1m = p1m;
		}

		public String getP1s() {
			return p1s;
		}

		public void setP1s(String p1s) {
			this.p1s = p1s;
		}
		
		
	}
}
