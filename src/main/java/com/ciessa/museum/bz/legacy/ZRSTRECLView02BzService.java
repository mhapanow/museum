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
import com.ciessa.museum.dao.legacy.ZrsplenDAO;
import com.ciessa.museum.dao.legacy.ZrsppleDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Zrsplen;
import com.ciessa.museum.model.legacy.Zrspple;
import com.ciessa.museum.tools.CollectionFactory;

public class ZRSTRECLView02BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(ZRSTRECLView02BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	ZrsppleDAO myDAOZrspple;
	
	@Autowired
	ZrsplenDAO myDAOZrsplen;
	
	Zrspple ObjZrspple = new Zrspple();
	List<Zrsplen> ListZrsplen = null;

	String parammeorg = null;
	String parammelogo = null;
	String parammencct = null;
	Integer parammeyfac = null;
	Integer parammeaafc = null;
	String parammecifa = null;
	String parammeagig = null;
	String paramcuerpo = null;
	
	String sm = null;//6300 elements
	String ss = null;//80 elements
	
	String ract = null;
	String dict = null;
	String rlorg = null;
	String rllogo = null;
	String rlncct = null;
	Integer rlaaf4 = null;
	String rlcifa = null;
	String rlagig = null;
	String rltenv = null;
	
	Integer quie1 = null;
	Integer quie2 = null;
	String c1fec = null;
	String c1orgn = null;
	String c1logo = null;
	String c1acnb = null;
	String c1apen = null;
	String c1bicy = null;
	String c2fec = null;
	String c2orgn = null;
	String c2logo = null;
	String c2acnb = null;
	String c2apen = null;
	String c2bicy = null;
	Integer c1 = null;
	Integer c2 = null;
	String sleye = null;
	String lrtile = null;
	Integer lridln = null;
	
	List<String> sfley1 = new ArrayList<String>();
	List<String> sfley2 = new ArrayList<String>();
	Integer i1 = null;
	Integer i2 = null;
	String sust = null;
	
	Integer s1 = null;
	Integer s2 = null;
	Integer s3 = null;
	Integer s4 = null;
	Integer s5 = null;
	
	String wley = null;
	
	//
	FUNCIONESBzService func = new FUNCIONESBzService();
	
	//
	ZRSTRECLAdapter adapted = null;
	
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
			this.parammecifa = obtainStringValue("mecifa", null);
			this.parammeagig = obtainStringValue("meagig", null);
			this.paramcuerpo = obtainStringValue("cuerpo", null);
			
			// inicializar array de string
			this.sm = String.format("%1$-6300s",this.sm);
			this.ss = String.format("%1$-80s",this.ss);
			
			String rpta = SubProcDspleyenda(ds);
			
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
					"c2orgn",
					"c2logo",
					"c2acnb",
					"c2apen",
					"c2fec",
					"c2bicy",
					"sfley2",
			};
			adapted = new ZRSTRECLAdapter();
			adapted.setC2orgn(this.c2orgn);
			adapted.setC2logo(this.c2logo);
			adapted.setC2acnb(this.c2acnb);
			adapted.setC2apen(this.c2apen);
			adapted.setC2fec(this.c2fec.toString());
			adapted.setC2bicy(this.c2bicy);
			adapted.setSfley2(this.sfley2);
			
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
	
	private String SubProcDspleyenda(DataSet ds) {
		try {
			this.sm = String.format("%1$-6300s",this.sm);
			this.ss = String.format("%1$-80s",this.ss);
			
			String rpta = this.SubRutSinzsr(ds);
			if (!rpta.equals(""))
			{
				return rpta;
			}
			
			ObjZrspple = myDAOZrspple.getUsigRlorgAndRllogoAndRlncctAndRlaaf4AndRlcifaAndRlagigAndRltenv(ds, this.rlorg, this.rllogo, this.rlncct, this.rlaaf4.toString(), this.rlcifa, this.rlagig, this.rltenv);
			
			if (ObjZrspple != null) {
				String resp = this.SubRutProces(ds);
				if (!resp.equals(""))
				{
					return resp;
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
			ract = this.parammencct;
			dict = this.parammencct;
			rlorg = this.parammeorg;
			rllogo = this.parammelogo;
			rlncct = this.parammencct;
			rlaaf4 = (this.parammeyfac * 100 ) + this.parammeaafc;
			rlcifa = this.parammecifa;
			rlagig = this.parammeagig;
			rltenv = "L";
			
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	private String SubRutProces(DataSet ds) {
		try {
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
			
			c1fec = ObjZrspple.getRlcifa() + "/" + ObjZrspple.getRlaaf4().toString();
			c1orgn = this.parammeorg; 
			c1logo = this.parammelogo;
			c1acnb = this.parammencct;
			String mename = null;
			c1apen = mename;
			c1bicy = this.parammeagig;
			c2fec = this.c1fec;
			c2orgn = this.parammeorg;
			c2logo = this.parammelogo;
			c2acnb = this.parammencct; 
			c2apen = mename; 
			c2bicy = this.parammeagig; 
			c1 = 0; 
			c2 = 0;
			String resp = null;
			if (!ObjZrspple.getRltif1().equals("")) {
				sleye = "FRENTE 1"; 
				lrtile = ObjZrspple.getRltif1();
				lridln = ObjZrspple.getRlidf1();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsf1());
			
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltif2().equals("")) {
				sleye = "FRENTE 2"; 
				lrtile = ObjZrspple.getRltif2();
				lridln = ObjZrspple.getRlidf2();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsf2());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltif3().equals("")) {
				sleye = "FRENTE 3"; 
				lrtile = ObjZrspple.getRltif3();
				lridln = ObjZrspple.getRlidf3();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsf3());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltif4().equals("")) {
				sleye = "FRENTE 4"; 
				lrtile = ObjZrspple.getRltif4();
				lridln = ObjZrspple.getRlidf4();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsf4());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltif5().equals("")) {
				sleye = "FRENTE 5"; 
				lrtile = ObjZrspple.getRltif5();
				lridln = ObjZrspple.getRlidf5();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsf5());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltif6().equals("")) {
				sleye = "FRENTE 6"; 
				lrtile = ObjZrspple.getRltif6();
				lridln = ObjZrspple.getRlidf6();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsf6());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			
			if (!ObjZrspple.getRltid1().equals("")) {
				this.sleye = "DORSO 1";
				this.lrtile = ObjZrspple.getRltid1();
				this.lridln = ObjZrspple.getRlidd1();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsd1());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltid2().equals("")) {
				this.sleye = "DORSO 2";
				this.lrtile = ObjZrspple.getRltid2();
				this.lridln = ObjZrspple.getRlidd2();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsd2());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltid3().equals("")) {
				this.sleye = "DORSO 3";
				this.lrtile = ObjZrspple.getRltid3();
				this.lridln = ObjZrspple.getRlidd3();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsd3());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltid4().equals("")) {
				this.sleye = "DORSO 4";
				this.lrtile = ObjZrspple.getRltid4();
				this.lridln = ObjZrspple.getRlidd4();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsd4());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltid5().equals("")) {
				this.sleye = "DORSO 5";
				this.lrtile = ObjZrspple.getRltid5();
				this.lridln = ObjZrspple.getRlidd5();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsd5());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			if (!ObjZrspple.getRltid6().equals("")) {
				this.sleye = "DORSO 6";
				this.lrtile = ObjZrspple.getRltid6();
				this.lridln = ObjZrspple.getRlidd6();
				this.ss = func.StringToArrayString(this.ss, 1, ObjZrspple.getRlvsd6());
				
				resp = SubRutCarga(ds);
				if (!resp.equals(""))
				{
					return resp;
				}
			}
			
			this.quie1 = 1;
			this.quie2 = 1;
			
			
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	
	private String SubRutCarga(DataSet ds) {
		try {
			this.c1 = this.c1 + 1;
			//this.sfley1 = "";
			this.sfley1.add(this.sleye);
			
			this.c2 = this.c2 + 1;
			//this.sfley2 = "";
			this.sfley2.add(this.sleye);
			
			i1 = 1;
			i2 = 3151;
			this.sust = "N";
			
			ListZrsplen = myDAOZrsplen.getUsinLrtileAndLridln(ds, lrtile, lridln.toString());
			
			for(Zrsplen o : ListZrsplen) {
				if (o.getLrlcs1() == 0) {
					//this.sfley1= "";
					this.sfley1.add(o.getLrlipc());
					this.c1 = this.c1 +1;
					
					//this.sfley2 = "";
					this.sfley2.add(o.getLrlisc());
					this.c2 = this.c2 +1;
				}else {
					this.sust = "S";
					this.s1 = o.getLrlcs1();
					this.s2 = o.getLrlcs2();
					this.s3 = o.getLrlcs3();
					this.s4 = o.getLrlcs4();
					this.s5 = o.getLrlcs5();
					//this.sm(i1) = o.getLrlipc();
					this.sm = func.StringToArrayString(this.sm, i1, o.getLrlipc());
					//this.sm(i2) = o.getLrlisc();
					this.sm = func.StringToArrayString(this.sm, i2, o.getLrlisc());
					this.i1 = this.i1 + 150;
					this.i2 = this.i2 + 150;
				}	
			}
			
			if (this.sust == "S") {
				String rpts = SubRutRsust(ds);
				if (!rpts.equals(""))
				{
					return rpts;
				}
				this.i1 = 1;
				this.i2 = 3151;
				for (int i = 1; i <= 21; i++) {
					this.wley = this.sm.substring(i1 -1 , i1) ;//this.wley = this.sm(i1);
					if (this.wley != "") {
						this.sfley1.add(this.wley);
						this.c1 = this.c1 +1;
					}
					this.wley = this.sm.substring(i2 -1 , i2) ;////this.wley = this.sm(i2);
					if (this.wley != "") {
						this.sfley2.add(this.wley);
						this.c2 = this.c2 +1;
					}
					this.i1 = this.i1 + 150;
					this.i2 = this.i2 + 150;
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
					this.sm = func.StringToStringPosition(this.sm, i, this.ss, w);//SM(I) = SS(W);
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
	
	public class ZRSTRECLAdapter {
		
		String c2orgn = null;
		String c2logo = null;
		String c2acnb = null;
		String c2apen = null;
		String c2fec = null;
		String c2bicy = null;
		List<String> sfley2 = null;
		
		public ZRSTRECLAdapter() {
			
		}

		public String getC2orgn() {
			return c2orgn;
		}

		public void setC2orgn(String c2orgn) {
			this.c2orgn = c2orgn;
		}

		public String getC2logo() {
			return c2logo;
		}

		public void setC2logo(String c2logo) {
			this.c2logo = c2logo;
		}

		public String getC2acnb() {
			return c2acnb;
		}

		public void setC2acnb(String c2acnb) {
			this.c2acnb = c2acnb;
		}

		public String getC2apen() {
			return c2apen;
		}

		public void setC2apen(String c2apen) {
			this.c2apen = c2apen;
		}

		public String getC2fec() {
			return c2fec;
		}

		public void setC2fec(String c2fec) {
			this.c2fec = c2fec;
		}

		public String getC2bicy() {
			return c2bicy;
		}

		public void setC2bicy(String c2bicy) {
			this.c2bicy = c2bicy;
		}

		public List<String> getSfley2() {
			return sfley2;
		}

		public void setSfley2(List<String> sfley2) {
			this.sfley2 = sfley2;
		}
		
		


		
	}
	
	

}
