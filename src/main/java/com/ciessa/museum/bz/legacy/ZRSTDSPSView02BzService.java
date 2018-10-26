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
	Boolean smoni = false;
	Integer sqtarji = 0;
	
	BigDecimal stottarji = new BigDecimal(0);
	String scanbi = "";
	Zrsprer shdr3000 = null; //3000
	
	ZRSTDSPSSSTMHDR sstmhdr = new ZRSTDSPSSSTMHDR();
	ZRSTDSPSSSTMHDR strhdr = new ZRSTDSPSSSTMHDR();
	
	@Autowired
	ZRSTEMMVView01BzService ZRSTEMMV;
	
	ZRSTEMMVSTRX smov = null;
	
	ArrayList<ZRSTEMMVSTRX> strx = null;
	
	
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
					"w2desci",
					"w2pais",
					"w2mone",
					"w2iorg",
					"w2amnt",
					"w2refc",
					"w2mrch",
					"w2desc",
					"w2tarj",
					"w2fmov",
					"w2tefm",
					"w2tnoa",
					"w2fmt",
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
			/*this.shdr3000.setMeorg (strhdr.getDSORG());
			this.shdr3000.setMelogo(strhdr.getDSLOGO());
			this.shdr3000.setMencct(strhdr.getDSCUENTA());
			this.shdr3000.setMeyfac(strhdr.getDSCENT());
			this.shdr3000.setMeaafc(strhdr.getDSANO());
			this.shdr3000.setMecifa(strhdr.getDSCIC());
			this.shdr3000.setMeagig(strhdr.getDSAG());*/
			
			this.shdr3000 = objZrsprer;
			
			this.sthis = "";
			this.smax = 256;
			this.si = 0;
			
			
			this.strx = new ArrayList<ZRSTEMMVSTRX>();
			ZRSTEMMV.SubProcGetstmdet( ds, this.shdr3000, this.sthis, this.smax, this.strx, this.si);
			this.strx = ZRSTEMMV.getSfmt();
			
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
						
			this.smoni =  false;
			this.stottarji = BigDecimal.ZERO;
			this.sqtarji = 0;
			
			for (int i = 0; i < this.strx.size(); i++) {
				
				this.smov = this.strx.get(i);
				if (this.smov.getTXLOIN().equals("I")) {
					if (!this.smoni) {
						this.smoni = true;
						adapter = new ZRSTDSPSAdapter();
						adapter.setW2desci("SALDO ANTERIOR EN DOLARES");
						adapter.setW2desc( "SALDO ANTERIOR EN DOLARES");
						adapter.setW2pais("");
						adapter.setW2mone("");
						adapter.setW2iorg(BigDecimal.ZERO);
						adapter.setW2amnt(objZrsprer.getMesafi());
						adapter.setW2refc("");
						adapter.setW2mrch("");
						list.add(adapter);
					}//fin if
					if (this.sqtarji != 0 && (!this.scanbi.equals(this.smov.getTXCANB()) || !this.smov.getTXMINI().equals("2"))) {
						adapter = new ZRSTDSPSAdapter();
						adapter.setW2desci("TOTAL CARGOS TARJETA " + this.scanbi.substring(16,20));
						adapter.setW2desc("TOTAL CARGOS TARJETA " + this.scanbi.substring(16,20));
						adapter.setW2pais("");
						adapter.setW2mone("");
						adapter.setW2iorg(BigDecimal.ZERO);
						adapter.setW2amnt(this.stottarji);
						adapter.setW2refc("");
						adapter.setW2mrch("");
						this.scanbi = this.smov.getTXCANB();
						list.add(adapter);
					}
					adapter = new ZRSTDSPSAdapter();
					if (this.smov.getTXMHAB() != 0 && this.smov.getTXCFAR() != this.afrqloc.toString()) {
						if (this.smov.getTXMHAB() != 0) {
							objDtgpdes = myDAODtgpdes.getUsingTxmhab(ds, this.smov.getTXMHAB().toString());
							if (objDtgpdes != null) {
								adapter.setW2mone(objDtgpdes.getDsds02());
							}
						}
						if ( !this.smov.getTXCFAR().equals("0") ) {
							ObjZvrpfrq = myDAOZvrpfrq.getUsingTxcfar(ds, this.smov.getTXCFAR());
							if (ObjZvrpfrq != null) {
								adapter.setW2pais(ObjZvrpfrq.getFr9969());
							}
						}
					}
					adapter.setW2desci(this.smov.getTXDESC()); 
					adapter.setW2desc(this.smov.getTXDESC()); 
					adapter.setW2iorg(this.smov.getTXIORG());
					adapter.setW2amnt(this.smov.getTXIMPO());
					adapter.setW2refc(this.smov.getTXREFC());
					adapter.setW2tarj(this.smov.getTXCANB().substring(16-1, 20-1));
					adapter.setW2fmov(this.smov.getTXFMOV());
					adapter.setW2tefm(this.smov.getTXTEFM());
					adapter.setW2tnoa(this.smov.getTXTNOA());
					adapter.setW2fmt(this.smov.getTXFMT());
					adapter.setW2mrch(this.smov.getTXMRCH());
					list.add(adapter);
					
					if (this.smov.getTXFILE().equals("M") && this.smov.getTXMINI().equals("2")) {
						this.stottarji = this.smov.getTXIMPO().add(this.stottarji);
						this.sqtarji = 1 + this.sqtarji;
						this.scanbi = this.smov.getTXCANB();
					}
				}//fin if
			}//fin for
			if (this.smoni) {
				if (this.sqtarji != 0) {
					adapter = new ZRSTDSPSAdapter();
					adapter.setW2desci(" TOTAL CARGOS TARJETA " + this.scanbi.substring(16-1, 20-1));
					adapter.setW2desc(" TOTAL CARGOS TARJETA " + this.scanbi.substring(16-1, 20-1));
					adapter.setW2pais("");
					adapter.setW2mone("");
					adapter.setW2iorg(BigDecimal.ZERO);
					adapter.setW2amnt(this.stottarji);
					adapter.setW2refc("");
					adapter.setW2mrch("");
					list.add(adapter);
					this.stottarji = BigDecimal.ZERO;
					this.sqtarji = 0;
				}
				adapter = new ZRSTDSPSAdapter();
				adapter.setW2desci(" SALDOS TOTALES DOLARES");
				adapter.setW2desc(" SALDOS TOTALES DOLARES");
				adapter.setW2pais("");
				adapter.setW2mone("");
				adapter.setW2iorg(BigDecimal.ZERO);
				adapter.setW2amnt(objZrsprer.getMestic());
				adapter.setW2refc("");
				adapter.setW2mrch("");
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
		String w2desci;
		String w2pais;
		String w2mone;
		BigDecimal w2iorg;
		BigDecimal w2amnt;
		String w2refc;
		String w2mrch;
		String w2desc;
		String w2tarj;
		Integer w2fmov;
		BigDecimal w2tefm;
		BigDecimal w2tnoa;
		String w2fmt;
		
		
		public String getW2desci() {
			return w2desci;
		}
		public void setW2desci(String w2desci) {
			this.w2desci = w2desci;
		}
		public String getW2pais() {
			return w2pais;
		}
		public void setW2pais(String w2pais) {
			this.w2pais = w2pais;
		}
		public String getW2mone() {
			return w2mone;
		}
		public void setW2mone(String w2mone) {
			this.w2mone = w2mone;
		}
		public BigDecimal getW2iorg() {
			return w2iorg;
		}
		public void setW2iorg(BigDecimal w2iorg) {
			this.w2iorg = w2iorg;
		}
		public BigDecimal getW2amnt() {
			return w2amnt;
		}
		public void setW2amnt(BigDecimal w2amnt) {
			this.w2amnt = w2amnt;
		}
		public String getW2refc() {
			return w2refc;
		}
		public void setW2refc(String w2refc) {
			this.w2refc = w2refc;
		}
		public String getW2mrch() {
			return w2mrch;
		}
		public void setW2mrch(String w2mrch) {
			this.w2mrch = w2mrch;
		}
		public String getW2desc() {
			return w2desc;
		}
		public void setW2desc(String w2desc) {
			this.w2desc = w2desc;
		}
		public String getW2tarj() {
			return w2tarj;
		}
		public void setW2tarj(String w2tarj) {
			this.w2tarj = w2tarj;
		}
		public Integer getW2fmov() {
			return w2fmov;
		}
		public void setW2fmov(Integer w2fmov) {
			this.w2fmov = w2fmov;
		}
		public BigDecimal getW2tefm() {
			return w2tefm;
		}
		public void setW2tefm(BigDecimal w2tefm) {
			this.w2tefm = w2tefm;
		}
		public BigDecimal getW2tnoa() {
			return w2tnoa;
		}
		public void setW2tnoa(BigDecimal w2tnoa) {
			this.w2tnoa = w2tnoa;
		}
		public String getW2fmt() {
			return w2fmt;
		}
		public void setW2fmt(String w2fmt) {
			this.w2fmt = w2fmt;
		}
		
		
		
		
	}
	
	
	}
