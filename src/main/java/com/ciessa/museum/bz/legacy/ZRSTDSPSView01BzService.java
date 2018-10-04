package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
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
	String strhdr = "";
	Integer afrqloc = 0;
	Integer meorg  = 0;
	Integer melogo = 0;
	String mencct = "";
	Integer meyfac = 0;
	Integer meaafc = 0;
	String mecifa = "";
	String meagig = "";
	
	String sthis = "";
	Integer smax = 0;
	String strx = "";
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
	Integer mefecied = 0;
	Integer mefevtod = 0;
	Integer mefepcied = 0;
	Integer mefepvtod = 0;
	String txtaviso = "";
	Boolean smonl = false;
	Boolean smoni = false;
	
	BigDecimal stottarjl = new BigDecimal(0);
	String scanbl = "";
	
	List<ZRSTDSPSAdapter> list = new ArrayList<ZRSTDSPSAdapter>();
	ZRSTDSPSAdapter adapter = null;
	
	ZRSTDSPSSSTMHDR sstmhdr = new ZRSTDSPSSSTMHDR();
	//ZRSTDSPSSMOV smov = null;
	
	ZRSTEMMVView01BzService ZRSTEMMV = new ZRSTEMMVView01BzService();
	ZRSTEMMVView01BzService.ZRSTEMMVSTRX smov = null;
	
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
					"W1DESC",
					"W1AMNT",
					"W1REFC",
					"W1MRCH"
			};
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
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
			returnValue.put("MEFECIED",this.mefecied);
			returnValue.put("MEFEVTOD",this.mefevtod);
			returnValue.put("MEFEPCIED",this.mefepcied);
			returnValue.put("MEFEPVTOD",this.mefepvtod);
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
			this.out = false;
			//this.strhdr = sstmhdr;
			this.afrqloc = 346;
			this.meorg  = sstmhdr.getDSORG();
			this.melogo = sstmhdr.getDSLOGO();
			this.mencct = sstmhdr.getDSCUENTA();
			this.meyfac = sstmhdr.getDSCENT();
			this.meaafc = sstmhdr.getDSANO();
			this.mecifa = sstmhdr.getDSCIC();
			this.meagig = sstmhdr.getDSAG();
			
			objZrsprer = myDAOZrsprer.getUsingMeorgAndMelogoAndMencctAndMeyfacAndMeaafcAndMecifaAndMeagig(ds, meorg, melogo, mencct, meyfac, meaafc, mecifa, meagig);
			
			this.sthis = "";
			this.smax = 256;
			this.strx = "";
			this.si = 0;
			
			//$NEXT (50) = Ejecutar procedimiento GETSTMDET   con parámetros ($HDR3000, $THIS, $MAX, $TRX , $I)
			
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
			/*this.mefecied =  objZrsprer.getMefecie();
			this.mefevtod =  objZrsprer.getMefevto();
			this.mefepcied =  objZrsprer.getMefepcie();
			this.mefepvtod =  objZrsprer.getMefepvto();*/
			this.smonl =  false;
			this.smoni =  false;
			for (int i = 0; i < EMPTY_STRING_ARRAY.length; i++) {
				this.smov = null;
				this.smov = ZRSTEMMV.strx;
				if (this.smov.getTXLOIN().equals("L")) {
					if (!this.smonl) {
						this.smonl = true;
						adapter = new ZRSTDSPSAdapter();
						adapter.setW1DESC("SALDO ANTERIOR PESOS");
						adapter.setW1AMNT(objZrsprer.getMesafl());
						adapter.setW1REFC("");
						adapter.setW1MRCH("");
						list.add(adapter);
					}
					if ( (!this.scanbl.equals(this.smov.getTXCANB()) || !this.smov.getTXMINI().equals("2"))) {//this.sqtarjl  != 0 &&
						adapter = new ZRSTDSPSAdapter();
						adapter.setW1DESC("TOTAL CARGOS TARJETA " + this.scanbl.substring(16, 20)); 
						adapter.setW1AMNT(this.stottarjl);
						adapter.setW1REFC("");
						adapter.setW1MRCH("");
						this.scanbl = this.smov.getTXCANB();
						list.add(adapter);
					}
					adapter = new ZRSTDSPSAdapter();
					adapter.setW1DESC(this.smov.getTXDESC());
					adapter.setW1AMNT(this.smov.getTXIMPO());
					adapter.setW1REFC(this.smov.getTXREFC());
					/*W1TARJ  = this.smov.getTXCANB().substring(16,20);
					W1FMOV  = this.smov.getTXFMOV();
					W1TEFM  = this.smov.getTXTEFM();
					W1TNOA  = this.smov.getTXTNOA();
					W1IORG  = this.smov.getTXIORG();
					W1FMT  = this.smov.getTXFMT();*/
					adapter.setW1MRCH(this.smov.getTXMRCH());
					list.add(adapter);
					
					if (this.smov.getTXFILE().equals("M") && this.smov.getTXMINI().equals("2")) {
						this.stottarjl = this.smov.getTXIMPO().add(this.stottarjl);
						//SQTARJL = 1 + SQTARJL;
						this.scanbl = this.smov.getTXCANB();
					}
				}//FIN IF
			}//FIN FOR
			if (this.smonl) {
				if (true) {//this.sqtarjl != 0
					adapter = new ZRSTDSPSAdapter();
					adapter.setW1DESC(" TOTAL CARGOS TARJETA " + this.scanbl.substring(16, 20));
					adapter.setW1AMNT(this.stottarjl);
					adapter.setW1REFC("");
					adapter.setW1MRCH("");
					list.add(adapter);
				}//fin if
				
				adapter = new ZRSTDSPSAdapter();
				adapter.setW1DESC(" SALDOS TOTALES PESOS");
				adapter.setW1AMNT(objZrsprer.getMestlc());
				adapter.setW1REFC("");
				adapter.setW1MRCH("");
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
		String W1DESC = "";
		BigDecimal W1AMNT = new BigDecimal(0);
		String W1REFC = "";
		String W1MRCH = "";
		
		public String getW1DESC() {
			return W1DESC;
		}
		public void setW1DESC(String w1desc) {
			W1DESC = w1desc;
		}
		public BigDecimal getW1AMNT() {
			return W1AMNT;
		}
		public void setW1AMNT(BigDecimal w1amnt) {
			W1AMNT = w1amnt;
		}
		public String getW1REFC() {
			return W1REFC;
		}
		public void setW1REFC(String w1refc) {
			W1REFC = w1refc;
		}
		public String getW1MRCH() {
			return W1MRCH;
		}
		public void setW1MRCH(String w1mrch) {
			W1MRCH = w1mrch;
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

}
