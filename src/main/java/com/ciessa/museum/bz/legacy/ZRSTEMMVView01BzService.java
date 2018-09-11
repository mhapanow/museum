package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.DtgpdesDAO;
import com.ciessa.museum.dao.legacy.Tgpp632DAO;
import com.ciessa.museum.dao.legacy.ZrspdmrDAO;
import com.ciessa.museum.dao.legacy.ZrspilrDAO;
import com.ciessa.museum.dao.legacy.ZrspmlrDAO;
import com.ciessa.museum.dao.legacy.ZrsppirDAO;
import com.ciessa.museum.dao.legacy.ZrspplrDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Dtgpdes;
import com.ciessa.museum.model.legacy.Tgpp632;
import com.ciessa.museum.model.legacy.Zrspdmr;
import com.ciessa.museum.model.legacy.Zrspilr;
import com.ciessa.museum.model.legacy.Zrspmlr;
import com.ciessa.museum.model.legacy.Zrsppir;
import com.ciessa.museum.model.legacy.Zrspplr;

public class ZRSTEMMVView01BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(ZRSTEMMVView01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	DtgpdesDAO myDAODtgpdes;
	
	@Autowired
	ZrspplrDAO myDAOZrspplr;
	
	@Autowired
	ZrspilrDAO myDAOZrspilr;
	
	@Autowired
	ZrspmlrDAO myDAOZrspmlr;
	
	@Autowired
	ZrsppirDAO myDAOZrsppir;
	
	@Autowired
	Tgpp632DAO myDAOTgpp632;
	
	@Autowired
	ZrspdmrDAO myDAOZrspdmr;
	
	List<Dtgpdes> ListDtgpdes = null;
	List<Tgpp632> ListTgpp632 = null;
	List<Zrspdmr> ListZrspdmr = null;
	List<Zrspplr> ListZrspplr = null;
	List<Zrspilr> ListZrspilr = null;
	List<Zrsppir> ListZrsppir = null;
	
	Zrspmlr ObjZrspmlr = new Zrspmlr();
	Zrspdmr ObjZrspdmr = new Zrspdmr();
	
	 
	
	String syapaso  = null;
	String scabecera = null;
	Integer Sind = null;
	
	BigDecimal wtctal = new BigDecimal(0);
	BigDecimal wtctai = new BigDecimal(0);
	String sadhoc = null;
	String wapr16 = null;
	String fqdivr = null;
	String wapr15 = null;
	String fqdivg = null;
	String wapd16 = null;
	String wapd15 = null;
	String dsfeho = null;
	String dscoca = null;
	String dscoac = null;
	Integer y = null;
	
	String dwcoac = null;
	String wwcoac = null;
	String[] mon = null;
	String[] moe = null;
	String[] mta = null;
	String[] daj = null;
	String[] civ = null;
	String[] liv = null;
	String[] mos = null;
	String[] mod = null;
	String[] mov = null;
	
	String wconfi = null;
	String mecacl = null; // TODO.: NO EXISTE
	String mone = null;
	String sposic = null;
	String sslmcm = null; // TODO.: NO EXISTE
	String memlco = null; // TODO.: NO EXISTE
	String metcon = null; // TODO.: NO EXISTE
	Integer aaorgn = 0; // TODO.: NO EXISTE
	Integer meorg = 0; // TODO.: NO EXISTE
	
	String meyfac = null; // TODO.: NO EXISTE
	String meaafc = null; // TODO.: NO EXISTE
	String mecifa = null; // TODO.: NO EXISTE
	String meagig = null; // TODO.: NO EXISTE
	String melogo = null; // TODO.: NO EXISTE
	String mencct = null; // TODO.: NO EXISTE
	
	BigDecimal adolar = new BigDecimal(0);
	String fecaux = null;
	String wdeslo = null;
	String drcmon = null;
	Integer drcori = 0;
	Integer drcmov = 0;
	Integer drcsmv = 0;
	
	String wdesl1 = null;
	String aamoaj = null;
	String sstetr = null;
	Integer ssapdt = 0;
	Integer ssatdm = 0;
	Integer ssnrtr = 0;
	Integer ssclco = 0;
	String adpg = null;
	
	Integer fecadd = 0;
	Integer fecamm = 0;
	Integer fecaa1 = 0;
	Integer fecaa2 = 0;
	Integer sscrdd = 0;
	Integer sscrmm = 0;
	Integer sscraa = 0;
	Integer sspntr = 0;
	Integer ssncax = 0;
	Integer ssbonr = 0;
	
	Integer fc6101 = 0;
	Integer ws6101 = 0;
	String wa6101 = null;
	BigDecimal ws6115 = new BigDecimal(0);
	
	Integer fc6001 = 0;
	Integer ws6001 = 0;
	String wa6001 = null;
	BigDecimal ws6116 = new BigDecimal(0);
	String aadrfn = null;
	Integer wstpin = 0;
	Integer wstpct = 0;
	Integer watpin = 0;
	Integer wtotiu = 0;
	Integer wstpcu = 0;
	Integer watpiu = 0;
	BigDecimal wtotin = new BigDecimal(0);
	BigDecimal totses = new BigDecimal(0);
	BigDecimal wimplo = new BigDecimal(0);
	BigDecimal mesafi = new BigDecimal(0);
	String wdesli = "";
	String fcu101 = "";
	String fcu001 = "";
	BigDecimal wsu101 = new BigDecimal(0);
	BigDecimal wsu001 = new BigDecimal(0);
	String wau101 = "";
	String wau001 = "";
	BigDecimal totseu = new BigDecimal(0);
	
	String wclmov = "";
	String auxh05 = "";
	String auxa08 = "";
	String auxa09 = "";
	String auxa30 = "";
	String auxb30 = "";
	
	Integer sw0106 = 0;
	String auxd03 = "";
	String auxm03 = "";
	String sw0102 = "";
	String sw0304 = "";
	String aux17 = "";
	
	String wreflo = "";
	String wrefe = "";
	String wwdrfn = "";
	String wwmrch = "";
	String lxrefp = "";
	String agrefe = "";
	
	String strx = "";
	String txfmt = "";
	String txmini = "";
	String txfile = "";
	String txloin = "";
	String txdesc = "";
	BigDecimal tximpo = new BigDecimal(0);
	String txrefc = "";
	String txmrch = "";
	Integer txcori = 0;
	Integer txcmov = 0;
	Integer txcsmv = 0;
	String txcanb = "";
	
	//Funtions
	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
	public String SubProcGetstmdet (String SstmHdr, String Spos, String SmaxElem, String Sfmt, Integer Sind  ) {
		long start = markStart();
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
						
			this.SubRutAinit(ds);
			this.syapaso = "1";
			this.scabecera = SstmHdr;
			this.Sind = 0;
			this.SubRutApprde(ds, Spos);
			
			
		} catch (ASException e) {
			if (e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENEXPIRED_CODE
					|| e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENMISSING_CODE) {
				log.log(Level.INFO, e.getMessage());
			} else {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
			//returnValue = getJSONRepresentationFromException(e);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			//returnValue = getJSONRepresentationFromException(ASExceptionHelper.defaultException(e.getMessage(), e));
		} finally {
			markEnd(start);
		}
		
		return "";
	}
	
	private String SubRutAinit(DataSet ds) {
		try {
			this.wtctal = null;
			this.wtctai = null;
			this.sadhoc = null;
			this.wapr16 = this.fqdivr; // TODO.:NO EXISTE
			this.wapr15 = this.fqdivg; // TODO.:NO EXISTE
			this.wapd16 = this.fqdivr; // TODO.:NO EXISTE
			this.wapd15 = this.fqdivg; // TODO.:NO EXISTE
			this.dsfeho = this.fc.FormatoFechaHora("HH:mm:ss yy.MM.dd");
			this.dscoca = "810";
			this.dscoac = "";
			this.y = 0;
			
			ListDtgpdes = myDAODtgpdes.getUsingDscoca(ds, this.dscoca);
			
			for(Dtgpdes o : ListDtgpdes) {
				this.y = this.y +1;
				this.dwcoac = o.getDscoac();
				this.wwcoac = this.dwcoac;
				if (o.getDsvl01() == 0) {
					mon[y] = this.wwcoac;
				}else {
					mon[y] = o.getDsvl01().toString();
				}
				moe[y] = o.getDsds02();
			}
			this.dscoca = "3";
			this.dscoac = "400";
			this.y = 0;
			ListDtgpdes = myDAODtgpdes.getUsingDscoca(ds, this.dscoca);
			for(Dtgpdes o : ListDtgpdes) {
				if (o.getDstreg() == "6") {
					if (o.getDscoac() == "413" || o.getDscoac() == "414") {
						y = y + 1;
						this.mta[y] = o.getDscoac();
						this.daj[y] = o.getDsds02();
					}
				}
			}
			y = 1;
			
			ListTgpp632 = myDAOTgpp632.getUsig(ds);
			for(Tgpp632 o :ListTgpp632) {
				this.y = this.y +1;
				this.civ[y] = o.getCey2ev();
				this.liv[y] = o.getCey2tx();
			}
			this.y = 0;
			ListZrspdmr = myDAOZrspdmr.getUsig(ds);
			for (Zrspdmr o :ListZrspdmr) {
				this.y = this.y +1;
				this.mos[y] = this.wclmov;
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		
		
		return "";
	}
		
	private String SubRutApprde (DataSet ds, String Spos) {
		try {
			this.wconfi = this.mecacl; // TODO.: No existe mecacl
			this.mone = "pesos ";
			this.mone = "";
			this.sposic = Spos;
			if (this.mecacl == "c") {
				this.sslmcm = this.memlco; // TODO.: No existe sslmcm y memlco
			}
			if (this.metcon == "1" || this.metcon == "3") { //TODO.: no existe metcon
				this.aaorgn = this.meorg; // TODO.: no existe aargn y meorg
				
				this.ListZrspplr = myDAOZrspplr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(ds, meyfac, meaafc, mecifa, meagig, aaorgn.toString(), melogo, mencct);
				for( Zrspplr o :ListZrspplr ) {
					if (o.getTlcori() == 7 && o.getTlcmov() == 70 && o.getTlcsmv() == 3) {
						if (o.getTlpmna().compareTo(new BigDecimal("0")) == -1) { // Se interpreta Tlpmna(1erValor) es menor a cero(2doValor)
							this.adolar = o.getTlpmna().negate();
						}else {
							this.adolar = o.getTlpmna();
						}
					}
					wtctal = wtctal.add(o.getTlpmna());
					this.fecaux = "";
					this.wdeslo = "";
					this.drcmon = "001";
					this.drcori = o.getTlcori();
					this.drcmov = o.getTlcmov();
					this.drcsmv = o.getTlcsmv();
					if (o.getTlcmov() == 61) {
						if (o.getTlcsmv() == 6) {
							this.wdeslo = "";
							this.wdeslo = "TL0127"; // TODO.: NO SE ENCUENTRA
							this.wdesl1 = this.wdeslo; 
						}else {
							this.aamoaj = "000";
							this.SubRutAdeajc(ds);
						}
					}else {
						if (o.getTlcori()==2 && o.getTlcmov()== 70) {
							this.sstetr = o.getTltetr();
							this.ssapdt = o.getTlapdt();
							this.ssatdm = o.getTladtm();
							this.ssnrtr = o.getTlnrtr();
							this.ssclco = o.getTlclco();
							this.adpg = "SI";
						}
						this.SubRutAdesmo(ds);
						this.adpg = "NO";
					}
					
					if (this.wdesl1.equals("") || ( o.getTlcori() == 2 && o.getTlcmov() == 70) ) {
						this.wdesl1 = "";
						this.wdesl1 = "TLDE40"; // TODO.: NO SE ENCUENTRA
					}
					if ((o.getTlcori() == 2 && o.getTlcmov() == 70 && o.getTlcsmv() != 40) || (o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 15) || (o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 16) ) {
						this.fecadd = o.getTlcrdd();
						this.fecamm = o.getTlcrmm();
						this.fecaa1 = o.getTlcrcc();
						this.fecaa2 = o.getTlcraa();
					}
					this.sscrdd = o.getTlcrdd();
					this.sscrmm = o.getTlcrmm();
					this.sscraa = o.getTlcraa();
					this.sspntr = o.getTlptnr();
					this.ssncax = o.getTlncax();
					this.ssbonr = o.getTlbonr();
					
					if ((o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 15) || (o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 16)) {
						if (o.getTlcsmv() == 15) {
							this.fc6101 = 0;
							this.ws6101 = this.ws6101 +0;
							this.wa6101 = "";
							this.ws6115 = this.ws6115.add(o.getTlpmna());							
						} else {
							this.fc6001 = 0;
							this.ws6001 = this.ws6001 + 0;
							this.wa6001 = "";
							this.ws6116 = this.ws6116.add(o.getTlpmna());
						}
					} else {
						this.aadrfn = o.getTlxtrf();
						this.SubRutArefpg(ds);
						this.SubRutSlineplr(ds, o.getTlpmna(), o.getTlcori(), o.getTlcmov(), o.getTlcsmv(), o.getTlncrd());
					}					
				} // fin for
				
				//verificar (SFMT)
				// 
				
				this.wtotin = new BigDecimal(0);
				this.wstpin = 0;
				this.wstpct = 0;
				this.watpin = 0;
				this.wtotiu = 0;
				this.wstpcu = 0;
				this.watpiu = 0;
				this.aaorgn = this.meorg;
				
				this.ListZrspilr = myDAOZrspilr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAorgnAndMelogoAndMencct(ds, this.meyfac, this.meaafc, this.mecifa, this.meagig, this.aaorgn.toString(), this.melogo, this.mencct);
				for (Zrspilr o :ListZrspilr ) {
					if (o.getIlcsmv()< 50) {
						this.wtotin = this.wtotin.add(o.getIlicpm());
					}
				}
				if (fc.BigDecimalComparar(this.wtotin.toString(), "0", "!=")) {
					this.wtctal = this.wtctal.add(this.wtotin);
					if (this.wstpct > 0) {
						this.wstpin = this.watpin / this.wstpct;
					}
					this.SubRutSlineilr(ds);
				}
				
				this.aaorgn = this.meorg;
				
				ObjZrspmlr = myDAOZrspmlr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(ds, this.meyfac, this.meaafc, this.mecifa, this.meagig, this.aaorgn.toString(), this.melogo, this.mencct);
				
				if (ObjZrspmlr != null) {					
					SubRutAlzon1(ds);
					SubRutAprtpesil(ds);
					SubRutAprtdeviva(ds);
					SubRutAlzon2(ds);
					SubRutAlzon3(ds);
				}else {
					SubRutAprtpesil(ds);
					SubRutAprtdeviva(ds);					
				}
				if ( fc.BigDecimalComparar(this.totses.toString(), "0", "!=")) {
					SubRutSlinel42(ds);
					this.wtctal = this.wtctal.add(this.totses);
				}
				this.wimplo = this.wtctal;
				
			}//fin if
			
			if (this.metcon == "2" || this.metcon == "3") {
				this.mone = "DOLARES";
				this.mone = "";
				this.wtctai = this.mesafi; //NO EXISTE MESAFI
				this.aaorgn = this.meorg -1;
				
				ListZrsppir = myDAOZrsppir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(ds, this.meyfac, this.meaafc, this.mecifa, this.meagig, this.aaorgn.toString(), this.melogo, this.mencct);
				for (Zrsppir o :ListZrsppir) {
					this.fecaux = "";
					this.wdeslo = "";
					this.drcmon = "002";
					this.drcori = o.getTicori();
					this.drcmov = o.getTicmov();
					this.drcsmv = o.getTicsmv();
					if (o.getTicmov() == 61) {
						if (o.getTicsmv() == 6) {
							this.wdeslo = "";
							this.wdeslo = "TI0127"; //NO SE ENCUENTRA
						}else {
							this.aamoaj = "000";
							SubRutAdeajc(ds);        
							this.wdesli = this.wdesl1;
						} //fin if
					} else {
						if (o.getTicori() == 2 && o.getTicmov() == 70) {
							this.sstetr = o.getTitetr();
							this.ssapdt = o.getTiapdt();
							this.ssatdm = o.getTiadtm();
							this.ssnrtr = o.getTinrtr();
							this.ssclco = o.getTiclco();
							this.adpg = "SI";
						}
						SubRutAdesmo(ds);
						this.adpg = "NO";
						this.wdesli = this.wdesl1;
					}//fin if
					
					if (this.wdesl1.equals("") || ( o.getTicori() == 2 && o.getTicmov() == 70) ) {
						this.wdesl1 = "";
						this.wdesl1 = "TIDE40";
						this.wdesli = this.wdesl1;
					}//fin if
					
					this.wtctai = this.wtctai.add(o.getTiimpo());
					if ((o.getTicori() == 2 && o.getTicmov() == 70 && o.getTicsmv() != 40) || (o.getTicori() == 3 && o.getTicmov() == 60 && o.getTicsmv() == 1) || (o.getTicori() == 3 && o.getTicmov() == 61 && o.getTicsmv() == 1) || (o.getTicori() == 3 && o.getTicmov() == 61 && o.getTicsmv() == 28)) {
						this.fecadd = o.getTicrdd();
						this.fecamm = o.getTicrmm();
						this.fecaa1 = o.getTicrcc();
						this.fecaa2 = o.getTicraa();
						this.sscrdd = o.getTicrdd();
					}
					this.sscrmm = o.getTicrmm();
					this.sscraa = o.getTicraa();
					this.sspntr = o.getTiptnr();
					this.ssncax = o.getTincax();
					this.ssbonr = o.getTibonr();
					
					if ((o.getTicori() == 3 && o.getTicmov() == 61 && o.getTicsmv() == 1) || (o.getTicori() == 3 && o.getTicmov() == 60 && o.getTicsmv() == 1)) {
						if (o.getTicmov() == 61) {
							this.fcu101 = this.fecaux;
							this.wsu101 = this.wsu101.add(o.getTiimpo());
							this.wau101 = "TIDE40";
						} else {
							this.fcu001 = this.fecaux;
							this.wsu001 = this.wsu001.add(o.getTiimpo());
							this.wau001 = "TIDE40";	
						}
					}else {
						/*if (o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 28) {
							this.wdesl1 = "TIDE40";
						} else {
							this.aadrfn = "TIXTRF";
						}*/
						
						SubRutArefpg(ds);
						SubRutSlinepir(ds);
						
					}//fin if
					
				}//fin for
				
				// ($FMT),
				//
				
				this.wtotin = new BigDecimal(0);
				this.wstpin = 0;
				this.wstpct = 0;
				this.watpin = 0;
				this.wtotiu = 0;
				this.wstpcu = 0;
				this.watpiu = 0;
				this.aaorgn = this.meorg - 1;
				
				
				// archivo Zrspiir 
				
				if ( fc.BigDecimalComparar(this.wtotin.toString(), "0", ">")) {
					this.wtctai = this.wtctai.add(this.wtotin);
					if (this.wstpct > 0) {
						this.wstpin = this.watpin / this.wstpct;
					}
					SubRutSlineiir(ds);
				}
				this.aaorgn = this.meorg -1;
				
				// archivo Zrspmir 
				//
				SubRutAizon1(ds);
				SubRutAprtpesii(ds);
				SubRutAizon2(ds);
				SubRutAizon3(ds);
				SubRutAprtpesii(ds);
				//
				
				if ( fc.BigDecimalComparar(this.totseu.toString(), "0", "!=") ) {
					SubRutSlinei42(ds);
					this.wtctai = this.wtctai.add(this.totseu);
				}
				this.wimplo = this.wtctai;
				
				
			}//fin if
			
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}

	private String SubRutAdeajc(DataSet ds) {
		this.y = 1;
		//la serie MTA
		//si existe
		this.wdesl1 = this.daj[this.y];
		//sino
		this.SubRutAdesmo(ds);
		this.adpg = "NO";
		
		return "";
	}
	
	private String SubRutAdesmo(DataSet ds) {
		
		try {
			this.y = 1;
			//la serie WCLMOV
			//si existe
			this.wdeslo = this.mod[this.y]; //TODO: NO EXISTE
			//sino		
			ObjZrspdmr = myDAOZrspdmr.getUsigDrcmonAndDrcoriAndDrcmovAndDrcsmv(ds, drcmon, drcori.toString(), drcmov.toString(), drcsmv.toString());
			if (ObjZrspdmr != null) {
				this.y = 1;
				//MOV el valor blanco 
				//si existe
				this.mov[this.y] = this.wclmov;
				this.mod[this.y] = ObjZrspdmr.getDrdimp();
				this.wdeslo = this.mod[this.y];
			}
			
			if (this.adpg.equals("SI")) {
				SubRutAdespg(ds);
			}else {
				this.wdesl1 = this.wdeslo;
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";		
	}
	
	private String SubRutAdespg(DataSet ds) {
		try {
			this.auxh05 = "";
			this.auxa08 = "";
			this.auxa09 = "";
			this.auxa30 = "";
			this.auxb30 = "";
			this.wdesl1 = "";
			if ( !this.sstetr.equals("") ) {
				this.sw0106 = this.ssapdt;
				//this.auxd03 (3 posiciones) = this.sw0506 + "/";
				//this.auxm03 (3 posiciones) = this.sw0304 + "/";
				this.auxa08 = this.auxd03 + this.auxm03;
				this.auxa08 = this.sw0102; // NO EXISTE sw0102
				this.sw0106 = this.ssatdm;
				this.auxh05 = this.sw0102 + ":";
				this.auxh05 = this.sw0304; //NO EXISTE sw0304
				this.sw0106 = this.ssnrtr;
				this.auxa30 = this.sstetr + " " + this.auxa08;
				this.auxb30 = this.auxa30 + " " + this.auxh05;
				this.auxa30 = "";
				this.auxa30 = this.auxb30 + " " + this.sw0106;
				this.auxb30 = "";
				this.auxb30 = this.wdeslo + " " + this.auxa30;
				this.wdesl1 = this.auxb30;
			}else {
				if (this.ssnrtr != 0) {
					this.auxa30 = "";
					this.sw0106 = this.ssnrtr;
					this.auxa30 = this.aux17 + " " + this.sw0106; //NO EXISTE aux17
					this.auxb30 = "";
					this.auxb30 = this.wdeslo + " " + this.auxa30;
					this.wdesl1 = this.auxb30;
				}else {
					if (this.ssclco != 0) {
						//Zpcpclr 
						//si existe
						this.auxa30 = "";
						this.auxb30 = "";
						this.auxa30 = wdeslo + " CHEQUE";
						//this.auxb30 = auxa30 + " " + obj.Clabrv();
						this.wdesl1 = "";
						this.wdesl1 = auxb30;
					}else {
						this.wdesl1 = this.wdeslo;
					}
				}
			}
			
		}  catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutArefpg(DataSet ds) {
		try {
			this.wreflo = "";
			this.wrefe = "";
			this.wwdrfn = "";
			this.wwmrch = "";
			if (ObjZrspdmr.getDrcori() == 2 && ObjZrspdmr.getDrcmov() == 70) {
				this.wwdrfn = this.aadrfn;
			}else {
				this.wreflo = this.lxrefp; //NO EXISTE lxrefp 
			}
			
			if (ObjZrspdmr.getDrcori() == 12 && ObjZrspdmr.getDrcmov() == 20) {
				this.wreflo= "";
			}
			if ( !this.wwdrfn.equals("")) {
				if (ObjZrspdmr.getDrcori() == 12 && ObjZrspdmr.getDrcmov() == 20) {
					this.wrefe = this.agrefe; //NO EXISTE agrefe
				}
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlineplr(DataSet ds, BigDecimal TLPMNA, Integer TLCORI, Integer TLCMOV, Integer TLCSMV, String TLNCRD) {
		try {
			this.strx =  "";
			this.txfmt = "DELO02";
			this.txfmt = this.txfmt.trim() + "R";
			this.txmini = "1";
			this.txfile = "P";
			this.txloin = "L";
			this.txdesc = this.wdesl1;
			this.tximpo = TLPMNA;
			this.txrefc = this.wwdrfn;
			this.txmrch = this.wwmrch;
			this.txcori = TLCORI;
			this.txcmov = TLCMOV;
			this.txcsmv = TLCSMV;
			this.txcanb = TLNCRD;
			
			if (this.sw0106 ) {
				
			} else {

			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
}
