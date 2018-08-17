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
			
			this.parammeorg = obtainStringValue("parammeorg", null);
			this.parammelogo = obtainStringValue("parammelogo", null);
			this.parammencct = obtainStringValue("parammencct", null);
			this.parammeyfac = obtainIntegerValue("parammeyfac", null);
			this.parammeaafc = obtainIntegerValue("parammeaafc", null);
			this.parammecifa = obtainStringValue("parammecifa", null);
			this.parammeagig = obtainStringValue("parammeagig", null);
			
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
					"P1ORGN",
					"P1LOGO",
					"P1ACNB",
					"P1APEN",
					"P1FEC",
					"P1BICY",
					"P1M",
					"P1S",
			};

			adapted.setP1ORGN(this.imorg);
			adapted.setP1LOGO(this.imlogo);
			adapted.setP1ACNB(""); //TODO::
			adapted.setP1APEN(""); //TODO::
			adapted.setP1FEC(this.p1fec);
			adapted.setP1BICY(this.p1bicy);
			adapted.setP1M(this.sm);
			adapted.setP1S(this.ss);
			
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
			this.p1fec = ObjZrsppma.getImcifa();
			this.p1fec = ObjZrsppma.getImaaf4().toString();
			String meapen = null; //TODO::
			this.p1apen = meapen; //TODO
			this.p1bicy = this.parammeagig;
			
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	public class ZRSTRECMAdapter {
		
		String P1ORGN = null;
		String P1LOGO = null;
		String P1ACNB = null;
		String P1APEN = null;
		String P1FEC = null;
		String P1BICY = null;
		String P1M = null;
		String P1S = null;
		
		public ZRSTRECMAdapter() {
			
		}

		public String getP1ORGN() {
			return P1ORGN;
		}

		public void setP1ORGN(String p1orgn) {
			P1ORGN = p1orgn;
		}

		public String getP1LOGO() {
			return P1LOGO;
		}

		public void setP1LOGO(String p1logo) {
			P1LOGO = p1logo;
		}

		public String getP1ACNB() {
			return P1ACNB;
		}

		public void setP1ACNB(String p1acnb) {
			P1ACNB = p1acnb;
		}

		public String getP1APEN() {
			return P1APEN;
		}

		public void setP1APEN(String p1apen) {
			P1APEN = p1apen;
		}

		public String getP1FEC() {
			return P1FEC;
		}

		public void setP1FEC(String p1fec) {
			P1FEC = p1fec;
		}

		public String getP1BICY() {
			return P1BICY;
		}

		public void setP1BICY(String p1bicy) {
			P1BICY = p1bicy;
		}

		public String getP1M() {
			return P1M;
		}

		public void setP1M(String p1m) {
			P1M = p1m;
		}

		public String getP1S() {
			return P1S;
		}

		public void setP1S(String p1s) {
			P1S = p1s;
		}
		
		
	}
}
