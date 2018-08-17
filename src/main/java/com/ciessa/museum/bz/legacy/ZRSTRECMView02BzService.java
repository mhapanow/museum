package com.ciessa.museum.bz.legacy;

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
	
	String imorg = null;
	String imlogo = null;
	String imncct = null;
	Integer imaaf4 = null;
	String imcifa = null;
	String imagig = null;
	
	String sfley1 = null;
	Integer c1fec = null;
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
	
	String sm = null;
	
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
			
			this.parammeorg = obtainStringValue("parammeorg", null);
			this.parammelogo = obtainStringValue("parammelogo", null);
			this.parammencct = obtainStringValue("parammencct", null);
			this.parammeyfac = obtainIntegerValue("parammeyfac", null);
			this.parammeaafc = obtainIntegerValue("parammeaafc", null);
			this.parammecifa = obtainStringValue("parammecifa", null);
			this.parammeagig = obtainStringValue("parammeagig", null);
			
			// inicializar array de string
			String.format("%1$-4140s",this.sm);
			String.format("%1$-80s",this.ss);
			
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
					"C1ORGN",
					"C1LOGO",
					"C1ACNB",
					"C1APEN",
					"C1FEC",
					"C1BICY",
					"C1TITU",
					"SFLEY1",
			};
			
			adapted.setC1ORGN(this.c1orgn);
			adapted.setC1LOGO(this.c1logo);
			adapted.setC1ACNB(this.c1acnb);
			adapted.setC1APEN(this.c1apen);
			adapted.setC1FEC(this.c1fec.toString());
			adapted.setC1BICY(this.c1bicy);
			adapted.setC1TITU(this.c1titu);
			adapted.setSFLEY1(this.sfley1);
			
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
			this.sfley1 = "";
			this.c1fec = (Integer.parseInt(this.imcifa) * 100 ) + this.imaaf4;
			this.c1orgn = this.parammeorg;
			this.c1logo = this.parammelogo;
			this.c1acnb = this.parammencct; 
			
			String meapen = null; //TODO no existe
			this.c1apen = meapen; //TODO no existe
			this.c1bicy = this.parammeagig;
			
			if (ObjZrsppma.getImtenv() == "M") {
				this.c1titu = "MAILING DE MARKETING";
			}else {
				this.c1titu = "MAILING DE SERVICIOS";
			}
			
			if (ObjZrsppma.getImtenv() == "M") {
				this.amtmai = ObjZrsppma.getImtmam();
				this.amnmai = ObjZrsppma.getImnmam();
				this.ss = ObjZrsppma.getImvsum();
			}else {
				this.amtmai = ObjZrsppma.getImtmas();
				this.amnmai = ObjZrsppma.getImnmas();
				this.ss = ObjZrsppma.getImvsuv();
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
					this.sfley1 = "";
					this.sfley1 = o.getAmltxc();
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
						this.sfley1 = this.wley;
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
		String C1ORGN = null;
		String C1LOGO = null;
		String C1ACNB = null;
		String C1APEN = null;
		String C1FEC = null;
		String C1BICY = null;
		String C1TITU = null;
		String SFLEY1 = null;
		
		public ZRSTRECMAdapter() {
			
		}

		public String getC1ORGN() {
			return C1ORGN;
		}

		public void setC1ORGN(String c1orgn) {
			C1ORGN = c1orgn;
		}

		public String getC1LOGO() {
			return C1LOGO;
		}

		public void setC1LOGO(String c1logo) {
			C1LOGO = c1logo;
		}

		public String getC1ACNB() {
			return C1ACNB;
		}

		public void setC1ACNB(String c1acnb) {
			C1ACNB = c1acnb;
		}

		public String getC1APEN() {
			return C1APEN;
		}

		public void setC1APEN(String c1apen) {
			C1APEN = c1apen;
		}

		public String getC1FEC() {
			return C1FEC;
		}

		public void setC1FEC(String c1fec) {
			C1FEC = c1fec;
		}

		public String getC1BICY() {
			return C1BICY;
		}

		public void setC1BICY(String c1bicy) {
			C1BICY = c1bicy;
		}

		public String getC1TITU() {
			return C1TITU;
		}

		public void setC1TITU(String c1titu) {
			C1TITU = c1titu;
		}

		public String getSFLEY1() {
			return SFLEY1;
		}

		public void setSFLEY1(String sFLEY1) {
			SFLEY1 = sFLEY1;
		}
		
		
		
	}

}
