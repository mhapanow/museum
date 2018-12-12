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
import com.ciessa.museum.dao.legacy.ZrsplemDAO;
import com.ciessa.museum.dao.legacy.ZrsppmaDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Zrsplem;
import com.ciessa.museum.model.legacy.Zrsppma;
import com.ciessa.museum.tools.CollectionFactory;

public class ZRSTRECMView02BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(ZRSTRECMView02BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	ZrsppmaDAO myDAOZrsppma;
	
	@Autowired
	ZrsplemDAO myDAOZrsplem;
	
	Zrsppma ObjZrsppma = new Zrsppma();
	List<Zrsplem> ListZrsplem = null;
	
	//
	String parammeorg = null;
	String parammelogo = null;
	String parammencct = null;
	Integer parammeyfac = null;
	Integer parammeaafc = null;
	String parammecifa = null;
	String parammeagig  = null;
	String parammeapen  = null;
	
	String imorg = null;
	String imlogo = null;
	String imncct = null;
	Integer imaaf4 = null;
	String imcifa = null;
	String imagig = null;
	
	//String sfley1 = null;
	List<String> sfley1 = new ArrayList<String>();
	String c1fec = null;
	String c1orgn = null;
	String c1logo = null;
	String c1acnb = null;
	String c1apen = null;
	String c1bicy = null;
	
	String c1titu = null;
	
	String amtmai = null;
	Integer amnmai = null;
	String ss = null;
	
	Integer quie1 = null;
	
	Integer i1 = null;
	String sust = null;
	
	Integer s1 = null;
	Integer s2 = null;
	Integer s3 = null;
	Integer s4 = null;
	Integer s5 = null;
	
	String wley = null;
	
	String sm = "";
	
	//
	FUNCIONESBzService func = new FUNCIONESBzService();
	
	//
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
			
			// inicializar array de string
			this.sm = String.format("%1$-4140s",this.sm);
			this.ss = String.format("%1$-80s",this.ss);
			
			String rpta = this.SubRutSinzsr(ds);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			ObjZrsppma = myDAOZrsppma.getUsingImorgAndImlogoAndImncctAndImaaf4AndImcifaAndImagig(ds, imorg, imlogo, imncct, imaaf4.toString(), imcifa, imagig);
			
			rpta = this.SubRutProces(ds);
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
				"c1orgn",
				"c1logo",
				"c1acnb",
				"c1apen",
				"c1fec",
				"c1bicy",
				"c1titu",
				"sfley1",
			};
			adapted = new ZRSTRECMAdapter();
			adapted.setC1orgn(this.c1orgn);
			adapted.setC1logo(this.c1logo);
			adapted.setC1acnb(this.c1acnb);
			adapted.setC1apen(this.c1apen);
			adapted.setC1fec(this.c1fec.toString());
			adapted.setC1bicy(this.c1bicy);
			adapted.setC1titu(this.c1titu);
			adapted.setSfley1(this.sfley1);
			
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
	
	private String SubRutProces(DataSet ds) {
		try {
			this.imorg = this.parammeorg; 
			this.imlogo = this.parammelogo; 
			this.imncct = this.parammencct; 
			this.imaaf4 = (this.parammeyfac * 100 ) + this.parammeaafc; 
			this.imcifa = this.parammecifa; 
			this.imagig = this.parammeagig;
			String resp = SubRutLoad1(ds);
			if (!resp.equals(""))
			{
				return resp;
			}
			
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	private String SubRutLoad1(DataSet ds) {
		try {
			//this.sfley1 = "";
			//this.c1fec = (Integer.parseInt(ObjZrsppma.getImcifa()) * 10000 ) + "/" + ObjZrsppma.getImaaf4();
			this.c1fec = ObjZrsppma.getImcifa() + "/" + String.format("%04d", ObjZrsppma.getImaaf4());
			this.c1orgn = this.parammeorg;
			this.c1logo = this.parammelogo;
			this.c1acnb = this.parammencct; 
			
			this.c1apen = this.parammeapen;
			this.c1bicy = this.parammeagig;
			
			if (ObjZrsppma.getImtenv().equals("M")) {
				this.c1titu = "MAILING DE MARKETING";
			}else {
				this.c1titu = "MAILING DE SERVICIOS";
			}
			
			if (ObjZrsppma.getImtenv().equals("M")) {
				this.amtmai = ObjZrsppma.getImtmam();
				this.amnmai = ObjZrsppma.getImnmam();
				this.ss = func.StringToArrayString(this.ss,1, ObjZrsppma.getImvsum());
			}else {
				this.amtmai = ObjZrsppma.getImtmas();
				this.amnmai = ObjZrsppma.getImnmas();
				this.ss = func.StringToArrayString(this.ss,1, ObjZrsppma.getImvsuv());
			}
			
			String rpts = SubRutCarga(ds);
			if (!rpts.equals(""))
			{
				return rpts;
			}
			
			this.quie1 = 1;
			
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
			
	}
	
	
	
	private String SubRutCarga(DataSet ds) {
		try {
			this.i1 = 1;
			this.sust = "N";
			
			ListZrsplem = myDAOZrsplem.getUsinAmtmaiAndAmnmai(ds, amtmai, amnmai);
			
			for( Zrsplem o : ListZrsplem) {
				if (o.getAmlcs1() == 0) {
					//this.sfley1 = "";
					this.sfley1.add(o.getAmltxc());
					//TODO: sfley1 debe de ser un array donde cada item es la linea de la carta.
				}else {
					this.sust = "S";
					this.s1 = o.getAmlcs1();
					this.s2 = o.getAmlcs2();
					this.s3 = o.getAmlcs3();
					this.s4 = o.getAmlcs4();
					this.s5 = o.getAmlcs5();
					this.sm = func.StringToArrayString(this.sm, i1, o.getAmltxc());
					i1 = i1 +90;
				}
			}
			
			if (this.sust == "S") {
				String rpts = SubRutRsust(ds);
				if (!rpts.equals(""))
				{
					return rpts;
				}
				i1 = 1;
				
				for (int i = 1; i < 46; i++) {
					this.wley = this.sm.substring(i1 -1 , i1) ;//
					if (this.wley != "") {
						this.sfley1.add(this.wley);
					}
					i1 = i1 + 90;
				}
			}
			
			
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	private String SubRutRsust(DataSet ds) {
		try {
			Integer w = 0;
			Integer i = 1;
			if (this.sm.indexOf("#") >= 0) {
				for (int j = 1; j <= this.s1; j++) {
					w = w + 1;
					this.sm = func.StringToStringPosition(this.sm, i, this.ss, w); //SM(I) = SS(W);
					i = i +1;
				}
			}
			
			if (this.s2 != 0) {
				i = 1;
				if (this.sm.indexOf("#") >= 0) {
					for (int j = 1; j <= this.s2; j++) {
						w = w + 1;
						this.sm = func.StringToStringPosition(this.sm, i, this.ss, w);
						i = i +1;
					}
				}
			}
			if (this.s3 != 0) {
				i = 1;
				if (this.sm.indexOf("#") >= 0) {
					for (int j = 1; j <= this.s3; j++) {
						w = w + 1;
						this.sm = func.StringToStringPosition(this.sm, i, this.ss, w);
						i = i +1;
					}
				}
			}
			if (this.s4 != 0) {
				i = 1;
				if (this.sm.indexOf("#") >= 0) {
					for (int j = 1; j <= this.s4; j++) {
						w = w + 1;
						this.sm = func.StringToStringPosition(this.sm, i, this.ss, w);
						i = i +1;
					}
				}
			}
			if (this.s5 != 0) {
				i = 1;
				if (this.sm.indexOf("#") >= 0) {
					for (int j = 1; j <= this.s5; j++) {
						w = w + 1;
						this.sm = func.StringToStringPosition(this.sm, i, this.ss, w);
						i = i +1;
					}
				}
			}
			
			
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	
	
	public class ZRSTRECMAdapter {
		String c1orgn = null;
		String c1logo = null;
		String c1acnb = null;
		String c1apen = null;
		String c1fec = null;
		String c1bicy = null;
		String c1titu = null;
		List<String> sfley1 = null;
		
		public ZRSTRECMAdapter() {
			
		}

		public String getC1orgn() {
			return c1orgn;
		}

		public void setC1orgn(String c1orgn) {
			this.c1orgn = c1orgn;
		}

		public String getC1logo() {
			return c1logo;
		}

		public void setC1logo(String c1logo) {
			this.c1logo = c1logo;
		}

		public String getC1acnb() {
			return c1acnb;
		}

		public void setC1acnb(String c1acnb) {
			this.c1acnb = c1acnb;
		}

		public String getC1apen() {
			return c1apen;
		}

		public void setC1apen(String c1apen) {
			this.c1apen = c1apen;
		}

		public String getC1fec() {
			return c1fec;
		}

		public void setC1fec(String c1fec) {
			this.c1fec = c1fec;
		}

		public String getC1bicy() {
			return c1bicy;
		}

		public void setC1bicy(String c1bicy) {
			this.c1bicy = c1bicy;
		}

		public String getC1titu() {
			return c1titu;
		}

		public void setC1titu(String c1titu) {
			this.c1titu = c1titu;
		}

		public List<String> getSfley1() {
			return sfley1;
		}

		public void setSfley1(List<String> sfley1) {
			this.sfley1 = sfley1;
		}

		
		
		
		
	}

}
