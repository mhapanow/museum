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
import com.ciessa.museum.dao.legacy.DtgpdesDAO;
import com.ciessa.museum.dao.legacy.ZrsprerDAO;
import com.ciessa.museum.dao.legacy.ZvrpfrqDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Dtgpdes;
import com.ciessa.museum.model.legacy.Zrsprer;
import com.ciessa.museum.model.legacy.Zvrpfrq;

public class ZRSTDSPSView02BzService extends RestBaseServerResource {
public static final Logger log = Logger.getLogger(ZRSTDSPSView02BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	ZrsprerDAO myDAOZrsprer;
	
	@Autowired
	DtgpdesDAO myDAODtgpdes;
	
	@Autowired
	ZvrpfrqDAO myDAOZvrpfrq;
	
	Dtgpdes objDtgpdes = new Dtgpdes();
	Zvrpfrq ObjZvrpfrq = new Zvrpfrq();
	
	
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
	Boolean smonl = false;
	Boolean smoni = false;
	
	BigDecimal stottarji = new BigDecimal(0);
	String scanbi = "";
	
	ZRSTDSPSSSTMHDR sstmhdr = new ZRSTDSPSSSTMHDR();
	
	ZRSTEMMVView01BzService ZRSTEMMV = new ZRSTEMMVView01BzService();
	ZRSTEMMVView01BzService.ZRSTEMMVSTRX smov = null;
	
	
	List<ZRSTDSPSAdapter> list = new ArrayList<ZRSTDSPSAdapter>();
	ZRSTDSPSAdapter adapter = null;
	
	
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
					"DSCENT",
					"DSANO",
					"DSCIC",
					"DSAG",
					"DSORG",
					"DSLOGO",
					"DSCUENTA",
					"DSTITULAR",
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
					if (!this.smoni) {
						this.smoni = true;
						adapter = new ZRSTDSPSAdapter();
						adapter.setW2DESCI("SALDO ANTERIOR EN DOLARES");
						//adapter.setW2DESC( "SALDO ANTERIOR EN DOLARES");
						adapter.setW2PAIS("");
						adapter.setW2MONE("");
						adapter.setW2IORG(BigDecimal.ZERO);
						adapter.setW2AMNT(objZrsprer.getMesafi());
						adapter.setW2REFC("");
						adapter.setW2MRCH("");
						list.add(adapter);
					}//fin if
					if ( (!this.scanbi.equals(this.smov.getTXCANB()) || !this.smov.getTXMINI().equals("2"))) {//this.sqtarjl  != 0 &&
						adapter = new ZRSTDSPSAdapter();
						adapter.setW2DESCI("TOTAL CARGOS TARJETA " + this.scanbi.substring(16,20));
						//adapter.setW2DESC("TOTAL CARGOS TARJETA " + this.scanbi.substring(16,20));
						adapter.setW2PAIS("");
						adapter.setW2MONE("");
						adapter.setW2IORG(BigDecimal.ZERO);
						adapter.setW2AMNT(this.stottarji);
						adapter.setW2REFC("");
						adapter.setW2MRCH("");
						this.scanbi = this.smov.getTXCANB();
						list.add(adapter);
					}
					adapter = new ZRSTDSPSAdapter();
					if (this.smov.getTXMHAB() != 0 && this.smov.getTXCFAR() != this.afrqloc.toString()) {
						if (this.smov.getTXMHAB() != 0) {
							objDtgpdes = myDAODtgpdes.getUsingTxmhab(ds, this.smov.getTXMHAB().toString());
							if (objDtgpdes != null) {
								adapter.setW2MONE(objDtgpdes.getDsds02());
							}
						}
						if ( !this.smov.getTXCFAR().equals("0") ) {
							ObjZvrpfrq = myDAOZvrpfrq.getUsingTxcfar(ds, this.smov.getTXCFAR());
							if (ObjZvrpfrq != null) {
								adapter.setW2PAIS(ObjZvrpfrq.getFr9969());
							}
						}
					}
					adapter.setW2DESCI(this.smov.getTXDESC()); 
					//adapter.setW2DESC(this.smov.getTXDESC()); 
					adapter.setW2IORG(this.smov.getTXIORG());
					adapter.setW2AMNT(this.smov.getTXIMPO());
					adapter.setW2REFC(this.smov.getTXREFC());
					/*adapter.setW2TARJ(this.smov.getTXCANB().substring(16, 20));
					adapter.setW2FMOV(this.smov.getTXFMOV());
					adapter.setW2TEFM(this.smov.getTXTEFM());
					adapter.setW2TNOA(this.smov.getTXTNOA());
					adapter.setW2FMT(this.smov.getTXFMT());*/
					adapter.setW2MRCH(this.smov.getTXMRCH());
					list.add(adapter);
					
					if (this.smov.getTXFILE().equals("M") && this.smov.getTXMINI().equals("2")) {
						this.stottarji = this.smov.getTXIMPO().add(this.stottarji);
						//SQTARJI = 1 + SQTARJI;
						this.scanbi = this.smov.getTXCANB();
					}
				}//fin if
			}//fin for
			if (this.smonl) {
				if (true) {//this.sqtarji != 0
					adapter = new ZRSTDSPSAdapter();
					adapter.setW2DESCI(" TOTAL CARGOS TARJETA " + this.scanbi.substring(16, 20));
					//adapter.setW2DESC(" TOTAL CARGOS TARJETA " + this.scanbi.substring(16, 20));
					adapter.setW2PAIS("");
					adapter.setW2MONE("");
					adapter.setW2IORG(BigDecimal.ZERO);
					adapter.setW2AMNT(this.stottarji);
					adapter.setW2REFC("");
					adapter.setW2MRCH("");
					list.add(adapter);
					this.stottarji = BigDecimal.ZERO;
					//this.sqtarji = "";
				}
				adapter = new ZRSTDSPSAdapter();
				adapter.setW2DESCI(" SALDOS TOTALES DOLARES");
				//adapter.setW2DESC(" SALDOS TOTALES DOLARES");
				adapter.setW2PAIS("");
				adapter.setW2MONE("");
				adapter.setW2IORG(BigDecimal.ZERO);
				adapter.setW2AMNT(objZrsprer.getMestic());
				adapter.setW2REFC("");
				adapter.setW2MRCH("");
				list.add(adapter);
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
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
	
	public class ZRSTDSPSAdapter{
		String W2DESCI = "";
		String W2PAIS = "";
		String W2MONE = "";
		BigDecimal W2IORG = new BigDecimal(0);
		BigDecimal W2AMNT = new BigDecimal(0);
		String W2REFC = "";
		String W2MRCH = "";
		
		
		public String getW2DESCI() {
			return W2DESCI;
		}
		public void setW2DESCI(String w2desci) {
			W2DESCI = w2desci;
		}
		public String getW2PAIS() {
			return W2PAIS;
		}
		public void setW2PAIS(String w2pais) {
			W2PAIS = w2pais;
		}
		public String getW2MONE() {
			return W2MONE;
		}
		public void setW2MONE(String w2mone) {
			W2MONE = w2mone;
		}
		public BigDecimal getW2IORG() {
			return W2IORG;
		}
		public void setW2IORG(BigDecimal w2iorg) {
			W2IORG = w2iorg;
		}
		public BigDecimal getW2AMNT() {
			return W2AMNT;
		}
		public void setW2AMNT(BigDecimal w2amnt) {
			W2AMNT = w2amnt;
		}
		public String getW2REFC() {
			return W2REFC;
		}
		public void setW2REFC(String w2refc) {
			W2REFC = w2refc;
		}
		public String getW2MRCH() {
			return W2MRCH;
		}
		public void setW2MRCH(String w2mrch) {
			W2MRCH = w2mrch;
		}
		
		
	}
	
	}
