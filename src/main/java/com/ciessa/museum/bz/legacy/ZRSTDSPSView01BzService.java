package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.bz.legacy.ZRSTEMMVView01BzService.ZRSTEMMVSTRX;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.ZrsprerDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Zrsprer;

public class ZRSTDSPSView01BzService extends RestBaseServerResource {
public static final Logger log = Logger.getLogger(ZRSTDSPSView01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	ZrsprerDAO myDAOZrsprer;
	
	Zrsprer objZrsprer = new Zrsprer();
	   
	Boolean out = false;
	String afrqloc = "";
	Integer meorg  = 0;
	Integer melogo = 0;
	String mencct = "";
	Integer meyfac = 0;
	Integer meaafc = 0;
	String mecifa = "";
	String meagig = "";
	
	String sthis = "";
	Integer smax = 0;
	Integer si = 0;
	
	Integer meorgnd = 0;
	Integer melogod = 0;
	String meacnbd = "";
	String menamed = "";
	Integer mebicyd = 0;
	BigDecimal mesunsd = new BigDecimal(0);
	BigDecimal mesunud = new BigDecimal(0);
	BigDecimal mestlcd = new BigDecimal(0);
	BigDecimal mesticd = new BigDecimal(0);
	BigDecimal mepmind = new BigDecimal(0);
	BigDecimal mepmidd = new BigDecimal(0);
	BigDecimal memlicd = new BigDecimal(0);
	BigDecimal memlcod = new BigDecimal(0);
	BigDecimal mecocod = new BigDecimal(0);
	BigDecimal mecoved = new BigDecimal(0);
	Integer medcifw = 0;
	Integer memcifw = 0;
	Integer meacifw = 0;
	Integer medvtow = 0;
	Integer memvtow = 0;
	Integer meavtow = 0;
	Integer medpciw = 0;
	Integer mempciw = 0;
	Integer meapciw = 0;
	Integer medprvw = 0;
	Integer memprvw = 0;
	Integer meaprvw = 0;
	Date mefecied = new Date();
	Date mefevtod = new Date();
	Date mefepcied = new Date();
	Date mefepvtod = new Date();
	String txtaviso = "";
	Boolean smonl = false;
	Boolean smoni = false;
	Integer sqtarjl = 0;
	
	Zrsprer shdr3000 = new Zrsprer(); //3000
	
	BigDecimal stottarjl = new BigDecimal(0);
	String scanbl = "";
	
	List<ZRSTDSPSAdapter> list = new ArrayList<ZRSTDSPSAdapter>();
	ZRSTDSPSAdapter adapter = null;
	
	ZRSTDSPSSSTMHDR sstmhdr = new ZRSTDSPSSSTMHDR();
	ZRSTDSPSSSTMHDR strhdr = new ZRSTDSPSSSTMHDR();
	
	@Autowired
	ZRSTEMMVView01BzService ZRSTEMMV;
	
	ZRSTEMMVSTRX smov = null;
	
	ArrayList<ZRSTEMMVSTRX> strx = null;
	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			//variables		
			sstmhdr.setDSCENT(obtainIntegerValue("dscent", 0));
			sstmhdr.setDSANO(obtainIntegerValue("dsano", 0));
			sstmhdr.setDSCIC(obtainStringValue("dscic", ""));
			sstmhdr.setDSAG(obtainStringValue("dsag", ""));
			sstmhdr.setDSORG(obtainIntegerValue("dsorg", 0));
			sstmhdr.setDSLOGO(obtainIntegerValue("dslogo", 0));
			sstmhdr.setDSCUENTA(obtainStringValue("dscuenta", ""));
			sstmhdr.setDSTITULAR(obtainStringValue("dstitular", ""));
			
			String rpta = SubProcDspstm(ds);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
						
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
					"MEORGND",
					"MELOGOD",
					"MEACNBD",
					"MENAMED",
					"MESUNSD",
					"MESUNUD",
					"MESTLCD",
					"MESTICD",
					"MEPMIND",
					"MEPMIDD",
					"MEMLICD",
					"MEMLCOD",
					"MEBICYD",
					"MECOCOD",
					"MECOVED",
					"MEFECIED",
					"MEFEVTOD",
					"MEFEPCIED",
					"MEFEPVTOD",
					"TXTAVISO"
			};
			
			String[] arrayFields = new String[] {
					"w1desc",
					"w1amnt",
					"w1refc",
					"w1mrch"

			};
			
			returnValue = getJSONRepresentationFromObject(adapter, fields);
			returnValue.put("data", getJSONRepresentationFromArrayOfObjects(list, arrayFields).get("data"));
			returnValue.put("MEORGND",this.meorgnd);
			returnValue.put("MELOGOD",this.melogod);
			returnValue.put("MEACNBD",this.meacnbd);
			returnValue.put("MENAMED",this.menamed);
			returnValue.put("MESUNSD",this.mesunsd);
			returnValue.put("MESUNUD",this.mesunud);
			returnValue.put("MESTLCD",this.mestlcd);
			returnValue.put("MESTICD",this.mesticd);
			returnValue.put("MEPMIND",this.mepmind);
			returnValue.put("MEPMIDD",this.mepmidd);
			returnValue.put("MEMLICD",this.memlicd);
			returnValue.put("MEMLCOD",this.memlcod);
			returnValue.put("MEBICYD",this.mebicyd);
			returnValue.put("MECOCOD",this.mecocod);
			returnValue.put("MECOVED",this.mecoved);
			returnValue.put("MEFECIED",new SimpleDateFormat("dd/MM/yy").format(this.mefecied));
			returnValue.put("MEFEVTOD",new SimpleDateFormat("dd/MM/yy").format(this.mefevtod));
			returnValue.put("MEFEPCIED",new SimpleDateFormat("dd/MM/yy").format(this.mefepcied));
			returnValue.put("MEFEPVTOD",new SimpleDateFormat("dd/MM/yy").format(this.mefepvtod));
			returnValue.put("TXTAVISO",this.txtaviso);
			
			
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

	private String SubProcDspstm(DataSet ds) {
		try {
			DecimalFormat df = new DecimalFormat("00");
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			this.out = false;
			this.strhdr = sstmhdr;
			this.afrqloc = "346";
			this.meorg  = strhdr.getDSORG();
			this.melogo = strhdr.getDSLOGO();
			this.mencct = strhdr.getDSCUENTA();
			this.meyfac = strhdr.getDSCENT();
			this.meaafc = strhdr.getDSANO();
			this.mecifa = strhdr.getDSCIC();
			this.meagig = strhdr.getDSAG();
			
						
			objZrsprer = myDAOZrsprer.getUsingMeorgAndMelogoAndMencctAndMeyfacAndMeaafcAndMecifaAndMeagig(ds, meorg, melogo, mencct, meyfac, meaafc, mecifa, meagig);
			/*
			this.shdr3000.setMeorg (objZrsprer.getMeorg());
			this.shdr3000.setMelogo(objZrsprer.getMelogo());
			this.shdr3000.setMencct(objZrsprer.getMencct());
			this.shdr3000.setMeyfac(objZrsprer.getDSCENT());
			this.shdr3000.setMeaafc(objZrsprer.getDSANO());
			this.shdr3000.setMecifa(objZrsprer.getDSCIC());
			this.shdr3000.setMeagig(objZrsprer.getDSAG());
			this.shdr3000.setMetcon();
			*/
			this.shdr3000 = objZrsprer;
			
			this.sthis = "";
			this.smax = 256;
			this.si = 0;
			
			this.strx = new ArrayList<ZRSTEMMVSTRX>();
			ZRSTEMMV.SubProcGetstmdet( ds, this.shdr3000, this.sthis, this.smax, this.strx, this.si);
			this.strx = ZRSTEMMV.getSfmt();
			//TODO: Lo mimso hacer con el resto de campos por referencia
						
			this.meorgnd =  objZrsprer.getMeorg();
			this.melogod =  objZrsprer.getMelogo();
			this.meacnbd =  objZrsprer.getMencct();
			this.menamed =  objZrsprer.getMename();
			this.mebicyd =  objZrsprer.getMeccyc();
			this.mesunsd =  objZrsprer.getMesuns();
			this.mesunud =  objZrsprer.getMesunu();
			this.mestlcd =  objZrsprer.getMestlc();
			this.mesticd =  objZrsprer.getMestic();
			this.mepmind =  objZrsprer.getMepmin();
			this.mepmidd =  objZrsprer.getMepmid();
			this.memlicd =  objZrsprer.getMemlic();
			this.memlcod =  objZrsprer.getMemlco();
			this.mecocod =  objZrsprer.getMecoco();
			this.mecoved =  objZrsprer.getMecove();
			this.medcifw =  objZrsprer.getMedcif();
			this.memcifw =  objZrsprer.getMemcif();
			this.meacifw =  objZrsprer.getMeacif();
			this.medvtow =  objZrsprer.getMedvto();
			this.memvtow =  objZrsprer.getMemvto();
			this.meavtow =  objZrsprer.getMeavto();
			this.medpciw =  objZrsprer.getMedpci();
			this.mempciw =  objZrsprer.getMempci();
			this.meapciw =  objZrsprer.getMeapci();
			this.medprvw =  objZrsprer.getMedprv();
			this.memprvw =  objZrsprer.getMemprv();
			this.meaprvw =  objZrsprer.getMeaprv();
			try {
				this.mefecied = sdf.parse(df.format(objZrsprer.getMedcif()) + df.format(objZrsprer.getMemcif()) + df.format(objZrsprer.getMeacif()));	
			}catch(Exception e) {
			}
			try {
				this.mefevtod =  sdf.parse(df.format(objZrsprer.getMedvto()) + df.format(objZrsprer.getMemvto()) + df.format(objZrsprer.getMeavto()));		
			}catch(Exception e) {
			}
			try {
				this.mefepcied = sdf.parse(df.format(objZrsprer.getMedpci()) + df.format(objZrsprer.getMempci()) + df.format(objZrsprer.getMeapci()));	
			}catch(Exception e) {
			}
			try {
				this.mefepvtod = sdf.parse(df.format(objZrsprer.getMedprv()) + df.format(objZrsprer.getMemprv()) + df.format(objZrsprer.getMeaprv()));	
			}catch(Exception e) {
			}
			this.smonl =  false;
			this.smoni =  false;
			this.stottarjl = BigDecimal.ZERO;
			this.sqtarjl = 0;
			for (int i = 1; i < this.strx.size(); i++) {
				
				this.smov = this.strx.get(i);
				
				if (this.smov.getTXLOIN().equals("L")) {
					if (!this.smonl) {
						this.smonl = true;
						adapter = new ZRSTDSPSAdapter();
						adapter.setW1desc("SALDO ANTERIOR PESOS");
						adapter.setW1amnt(objZrsprer.getMesafl());
						adapter.setW1refc("");
						adapter.setW1mrch("");
						list.add(adapter);
					}
					if (this.sqtarjl != 0 && (!this.scanbl.equals(this.smov.getTXCANB()) || !this.smov.getTXMINI().equals("2"))) {//this.sqtarjl  != 0 &&
						adapter = new ZRSTDSPSAdapter();
						adapter.setW1desc("TOTAL CARGOS TARJETA " + this.scanbl.substring(16, 20)); 
						adapter.setW1amnt(this.stottarjl);
						adapter.setW1refc("");
						adapter.setW1mrch("");
						this.scanbl = this.smov.getTXCANB();
						list.add(adapter);
					}
					adapter = new ZRSTDSPSAdapter();
					adapter.setW1desc(this.smov.getTXDESC());
					adapter.setW1amnt(this.smov.getTXIMPO());
					adapter.setW1refc(this.smov.getTXREFC());
					adapter.setW1tarj(this.smov.getTXCANB().substring(16-1,20-1));
					adapter.setW1fmov(this.smov.getTXFMOV());
					adapter.setW1tefm(this.smov.getTXTEFM());
					adapter.setW1tnoa(this.smov.getTXTNOA());
					adapter.setW1iorg(this.smov.getTXIORG());
					adapter.setW1fmt(this.smov.getTXFMT());
					adapter.setW1mrch(this.smov.getTXMRCH());
					list.add(adapter);
					
					if (this.smov.getTXFILE().equals("M") && this.smov.getTXMINI().equals("2")) {
						this.stottarjl = this.smov.getTXIMPO().add(this.stottarjl);
						this.sqtarjl = 1 + this.sqtarjl;
						this.scanbl = this.smov.getTXCANB();
					}
				}//FIN IF
			}//FIN FOR
			if (this.smonl) {
				if (this.sqtarjl != 0) {
					adapter = new ZRSTDSPSAdapter();
					adapter.setW1desc(" TOTAL CARGOS TARJETA " + this.scanbl.substring(16, 20));
					adapter.setW1amnt(this.stottarjl);
					adapter.setW1refc("");
					adapter.setW1mrch("");
					list.add(adapter);
				}//fin if
				
				adapter = new ZRSTDSPSAdapter();
				adapter.setW1desc(" SALDOS TOTALES PESOS");
				adapter.setW1amnt(objZrsprer.getMestlc());
				adapter.setW1refc("");
				adapter.setW1mrch("");
				list.add(adapter);
			}//fin if
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	//adapter
	public class ZRSTDSPSAdapter {
		private String w1desc;
		private BigDecimal w1amnt;
		String w1refc;
		String w1mrch;
		String w1tarj;
		Integer w1fmov;
		BigDecimal w1tefm;
		BigDecimal w1tnoa;
		BigDecimal w1iorg;
		String w1fmt;
		public String getW1desc() {
			return w1desc;
		}
		public void setW1desc(String w1desc) {
			this.w1desc = w1desc;
		}
		public BigDecimal getW1amnt() {
			return w1amnt;
		}
		public void setW1amnt(BigDecimal w1amnt) {
			this.w1amnt = w1amnt;
		}
		public String getW1refc() {
			return w1refc;
		}
		public void setW1refc(String w1refc) {
			this.w1refc = w1refc;
		}
		public String getW1mrch() {
			return w1mrch;
		}
		public void setW1mrch(String w1mrch) {
			this.w1mrch = w1mrch;
		}
		public String getW1tarj() {
			return w1tarj;
		}
		public void setW1tarj(String w1tarj) {
			this.w1tarj = w1tarj;
		}
		public Integer getW1fmov() {
			return w1fmov;
		}
		public void setW1fmov(Integer w1fmov) {
			this.w1fmov = w1fmov;
		}
		public BigDecimal getW1tefm() {
			return w1tefm;
		}
		public void setW1tefm(BigDecimal w1tefm) {
			this.w1tefm = w1tefm;
		}
		public BigDecimal getW1tnoa() {
			return w1tnoa;
		}
		public void setW1tnoa(BigDecimal w1tnoa) {
			this.w1tnoa = w1tnoa;
		}
		public BigDecimal getW1iorg() {
			return w1iorg;
		}
		public void setW1iorg(BigDecimal w1iorg) {
			this.w1iorg = w1iorg;
		}
		public String getW1fmt() {
			return w1fmt;
		}
		public void setW1fmt(String w1fmt) {
			this.w1fmt = w1fmt;
		}
		
		
		
		
	}
	
	private class ZRSTDSPSSSTMHDR {
		Integer DSCENT = 0;
		Integer DSANO = 0;
		String DSCIC = "";
		String DSAG = "";
		Integer DSORG = 0;
		Integer DSLOGO = 0;
		String DSCUENTA = "";
		String DSTITULAR = "";
		
		public Integer getDSCENT() {
			return DSCENT;
		}
		public void setDSCENT(Integer dSCENT) {
			DSCENT = dSCENT;
		}
		public Integer getDSANO() {
			return DSANO;
		}
		public void setDSANO(Integer dSANO) {
			DSANO = dSANO;
		}
		public String getDSCIC() {
			return DSCIC;
		}
		public void setDSCIC(String dSCIC) {
			DSCIC = dSCIC;
		}
		public String getDSAG() {
			return DSAG;
		}
		public void setDSAG(String dSAG) {
			DSAG = dSAG;
		}
		public Integer getDSORG() {
			return DSORG;
		}
		public void setDSORG(Integer dSORG) {
			DSORG = dSORG;
		}
		public Integer getDSLOGO() {
			return DSLOGO;
		}
		public void setDSLOGO(Integer dSLOGO) {
			DSLOGO = dSLOGO;
		}
		public String getDSCUENTA() {
			return DSCUENTA;
		}
		public void setDSCUENTA(String dSCUENTA) {
			DSCUENTA = dSCUENTA;
		}
		public String getDSTITULAR() {
			return DSTITULAR;
		}
		public void setDSTITULAR(String dSTITULAR) {
			DSTITULAR = dSTITULAR;
		}
		
		
	}
	
	private class ZRSTDSPSSMOV{
		
		String TXFMT = "";
		String TXMINI = "";
		String TXFILE = "";
		String TXLOIN = "";
		String TXDESC = "";
		BigDecimal TXIMPO = new BigDecimal(0);
		String TXREFC = "";
		String TXMRCH = "";
		Integer TXCORI = 0;
		Integer TXCMOV = 0;
		Integer TXFMOV = 0;
		Integer TXCSMV = 0;
		String TXCANB = "";
		Integer TXFMOVC = 0;
		String TXFILE2 = "";
		String TXMIN = "";
		BigDecimal TXTEFM = new BigDecimal(0);
		Integer TXNCUO = 0;
		Integer TXCACU = 0;
		BigDecimal TXIORG = new BigDecimal(0);
		BigDecimal TXTNOA = new BigDecimal(0);
		Integer TXNLCC = 0;
		String TXCFAR = "";
		Integer TXMHAB = 0;
		
		public String getTXFMT() {
			return TXFMT;
		}
		public void setTXFMT(String tXFMT) {
			TXFMT = tXFMT;
		}
		public String getTXMINI() {
			return TXMINI;
		}
		public void setTXMINI(String tXMINI) {
			TXMINI = tXMINI;
		}
		public String getTXFILE() {
			return TXFILE;
		}
		public void setTXFILE(String tXFILE) {
			TXFILE = tXFILE;
		}
		public String getTXLOIN() {
			return TXLOIN;
		}
		public void setTXLOIN(String tXLOIN) {
			TXLOIN = tXLOIN;
		}
		public String getTXDESC() {
			return TXDESC;
		}
		public void setTXDESC(String tXDESC) {
			TXDESC = tXDESC;
		}
		public BigDecimal getTXIMPO() {
			return TXIMPO;
		}
		public void setTXIMPO(BigDecimal tXIMPO) {
			TXIMPO = tXIMPO;
		}
		public String getTXREFC() {
			return TXREFC;
		}
		public void setTXREFC(String tXREFC) {
			TXREFC = tXREFC;
		}
		public String getTXMRCH() {
			return TXMRCH;
		}
		public void setTXMRCH(String tXMRCH) {
			TXMRCH = tXMRCH;
		}
		public Integer getTXCORI() {
			return TXCORI;
		}
		public void setTXCORI(Integer tXCORI) {
			TXCORI = tXCORI;
		}
		public Integer getTXCMOV() {
			return TXCMOV;
		}
		public void setTXCMOV(Integer tXCMOV) {
			TXCMOV = tXCMOV;
		}
		public Integer getTXFMOV() {
			return TXFMOV;
		}
		public void setTXFMOV(Integer tXFMOV) {
			TXFMOV = tXFMOV;
		}
		public Integer getTXCSMV() {
			return TXCSMV;
		}
		public void setTXCSMV(Integer tXCSMV) {
			TXCSMV = tXCSMV;
		}
		public String getTXCANB() {
			return TXCANB;
		}
		public void setTXCANB(String tXCANB) {
			TXCANB = tXCANB;
		}
		public Integer getTXFMOVC() {
			return TXFMOVC;
		}
		public void setTXFMOVC(Integer tXFMOVC) {
			TXFMOVC = tXFMOVC;
		}
		public String getTXFILE2() {
			return TXFILE2;
		}
		public void setTXFILE2(String tXFILE2) {
			TXFILE2 = tXFILE2;
		}
		public String getTXMIN() {
			return TXMIN;
		}
		public void setTXMIN(String tXMIN) {
			TXMIN = tXMIN;
		}
		public BigDecimal getTXTEFM() {
			return TXTEFM;
		}
		public void setTXTEFM(BigDecimal tXTEFM) {
			TXTEFM = tXTEFM;
		}
		public Integer getTXNCUO() {
			return TXNCUO;
		}
		public void setTXNCUO(Integer tXNCUO) {
			TXNCUO = tXNCUO;
		}
		public Integer getTXCACU() {
			return TXCACU;
		}
		public void setTXCACU(Integer tXCACU) {
			TXCACU = tXCACU;
		}
		public BigDecimal getTXIORG() {
			return TXIORG;
		}
		public void setTXIORG(BigDecimal tXIORG) {
			TXIORG = tXIORG;
		}
		public BigDecimal getTXTNOA() {
			return TXTNOA;
		}
		public void setTXTNOA(BigDecimal tXTNOA) {
			TXTNOA = tXTNOA;
		}
		public Integer getTXNLCC() {
			return TXNLCC;
		}
		public void setTXNLCC(Integer tXNLCC) {
			TXNLCC = tXNLCC;
		}
		public String getTXCFAR() {
			return TXCFAR;
		}
		public void setTXCFAR(String tXCFAR) {
			TXCFAR = tXCFAR;
		}
		public Integer getTXMHAB() {
			return TXMHAB;
		}
		public void setTXMHAB(Integer tXMHAB) {
			TXMHAB = tXMHAB;
		}
		
		
	}
	
	/*
	public class ZRSTEMMVSTRX {
		String TXFMT = "";
		String TXMINI = "";
		String TXFILE = "";
		String TXLOIN = "";
		String TXDESC = "";
		BigDecimal TXIMPO = new BigDecimal(0);
		String TXREFC = "";
		String TXMRCH = "";
		Integer TXCORI = 0;
		Integer TXCMOV = 0;
		Integer TXFMOV = 0;
		Integer TXCSMV = 0;
		String TXCANB = "";
		Integer TXFMOVC = 0;
		String TXFILE2 = "";
		String TXMIN = "";
		BigDecimal TXTEFM = new BigDecimal(0);
		Integer TXNCUO = 0;
		Integer TXCACU = 0;
		BigDecimal TXIORG = new BigDecimal(0);
		BigDecimal TXTNOA = new BigDecimal(0);
		Integer TXNLCC = 0;
		String TXCFAR = "";
		Integer TXMHAB = 0;
		
		
		public String getTXFMT() {
			return TXFMT;
		}
		public void setTXFMT(String tXFMT) {
			TXFMT = tXFMT;
		}
		public String getTXMINI() {
			return TXMINI;
		}
		public void setTXMINI(String tXMINI) {
			TXMINI = tXMINI;
		}
		public String getTXFILE() {
			return TXFILE;
		}
		public void setTXFILE(String tXFILE) {
			TXFILE = tXFILE;
		}
		public String getTXLOIN() {
			return TXLOIN;
		}
		public void setTXLOIN(String tXLOIN) {
			TXLOIN = tXLOIN;
		}
		public String getTXDESC() {
			return TXDESC;
		}
		public void setTXDESC(String tXDESC) {
			TXDESC = tXDESC;
		}
		public BigDecimal getTXIMPO() {
			return TXIMPO;
		}
		public void setTXIMPO(BigDecimal tXIMPO) {
			TXIMPO = tXIMPO;
		}
		public String getTXREFC() {
			return TXREFC;
		}
		public void setTXREFC(String tXREFC) {
			TXREFC = tXREFC;
		}
		public String getTXMRCH() {
			return TXMRCH;
		}
		public void setTXMRCH(String tXMRCH) {
			TXMRCH = tXMRCH;
		}
		public Integer getTXCORI() {
			return TXCORI;
		}
		public void setTXCORI(Integer tXCORI) {
			TXCORI = tXCORI;
		}
		public Integer getTXCMOV() {
			return TXCMOV;
		}
		public void setTXCMOV(Integer tXCMOV) {
			TXCMOV = tXCMOV;
		}
		public Integer getTXFMOV() {
			return TXFMOV;
		}
		public void setTXFMOV(Integer tXFMOV) {
			TXFMOV = tXFMOV;
		}
		public Integer getTXCSMV() {
			return TXCSMV;
		}
		public void setTXCSMV(Integer tXCSMV) {
			TXCSMV = tXCSMV;
		}
		public String getTXCANB() {
			return TXCANB;
		}
		public void setTXCANB(String tXCANB) {
			TXCANB = tXCANB;
		}
		public Integer getTXFMOVC() {
			return TXFMOVC;
		}
		public void setTXFMOVC(Integer tXFMOVC) {
			TXFMOVC = tXFMOVC;
		}
		public String getTXFILE2() {
			return TXFILE2;
		}
		public void setTXFILE2(String tXFILE2) {
			TXFILE2 = tXFILE2;
		}
		public String getTXMIN() {
			return TXMIN;
		}
		public void setTXMIN(String tXMIN) {
			TXMIN = tXMIN;
		}
		public BigDecimal getTXTEFM() {
			return TXTEFM;
		}
		public void setTXTEFM(BigDecimal tXTEFM) {
			TXTEFM = tXTEFM;
		}
		public Integer getTXNCUO() {
			return TXNCUO;
		}
		public void setTXNCUO(Integer tXNCUO) {
			TXNCUO = tXNCUO;
		}
		public Integer getTXCACU() {
			return TXCACU;
		}
		public void setTXCACU(Integer tXCACU) {
			TXCACU = tXCACU;
		}
		public BigDecimal getTXIORG() {
			return TXIORG;
		}
		public void setTXIORG(BigDecimal tXIORG) {
			TXIORG = tXIORG;
		}
		public BigDecimal getTXTNOA() {
			return TXTNOA;
		}
		public void setTXTNOA(BigDecimal tXTNOA) {
			TXTNOA = tXTNOA;
		}
		public Integer getTXNLCC() {
			return TXNLCC;
		}
		public void setTXNLCC(Integer tXNLCC) {
			TXNLCC = tXNLCC;
		}
		public String getTXCFAR() {
			return TXCFAR;
		}
		public void setTXCFAR(String tXCFAR) {
			TXCFAR = tXCFAR;
		}
		public Integer getTXMHAB() {
			return TXMHAB;
		}
		public void setTXMHAB(Integer tXMHAB) {
			TXMHAB = tXMHAB;
		}
		
		
	}
	*/

}
