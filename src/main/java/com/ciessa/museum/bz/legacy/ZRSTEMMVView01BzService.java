package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.DtgpdesDAO;
import com.ciessa.museum.dao.legacy.Tgpp632DAO;
import com.ciessa.museum.dao.legacy.ZrspdmrDAO;
import com.ciessa.museum.dao.legacy.ZrspiirDAO;
import com.ciessa.museum.dao.legacy.ZrspilrDAO;
import com.ciessa.museum.dao.legacy.ZrspmirDAO;
import com.ciessa.museum.dao.legacy.ZrspmlrDAO;
import com.ciessa.museum.dao.legacy.ZrsppirDAO;
import com.ciessa.museum.dao.legacy.ZrspplrDAO;
import com.ciessa.museum.dao.legacy.ZvrpfrqDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Dtgpdes;
import com.ciessa.museum.model.legacy.Tgpp632;
import com.ciessa.museum.model.legacy.Zrspdmr;
import com.ciessa.museum.model.legacy.Zrspiir;
import com.ciessa.museum.model.legacy.Zrspilr;
import com.ciessa.museum.model.legacy.Zrspmir;
import com.ciessa.museum.model.legacy.Zrspmlr;
import com.ciessa.museum.model.legacy.Zrsppir;
import com.ciessa.museum.model.legacy.Zrspplr;
import com.ciessa.museum.model.legacy.Zrsprer;
import com.ciessa.museum.model.legacy.Zvrpfrq;

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
	
	@Autowired
	ZrspiirDAO myDAOZrspiir;
	
	@Autowired
	ZrspmirDAO myDAOZrspmir;
	
	@Autowired
	ZvrpfrqDAO myDAOZvrpfrq;
	
	List<Dtgpdes> ListDtgpdes = null;
	List<Tgpp632> ListTgpp632 = null;
	List<Zrspdmr> ListZrspdmr = null;
	List<Zrspplr> ListZrspplr = null;
	List<Zrspilr> ListZrspilr = null;
	List<Zrsppir> ListZrsppir = null;
	List<Zrspmlr> ListZrspmlr = null;
	List<Zrspiir> ListZrspiir = null;
	List<Zrspmir> ListZrspmir = null;
	
	Zrspmlr ObjZrspmlr = new Zrspmlr();
	Zrspdmr ObjZrspdmr = new Zrspdmr();
	Zvrpfrq ObjZvrpfrq = new Zvrpfrq();
	Zrspmir ObjZrspmir = new Zrspmir();
	
	Zrsprer sstmhdr = null; //3000
	String spos = ""; //50
	Integer smaxelem = 0;
	String[] sfmt = new String[256];  // 256 con 200
	Integer sind = 0;
	
	
	
	String syapaso  = null;
	String scabecera = null;
	
	String tl0127 = "PM N0000000000 C000 INPAGA ";
	String ti0127 = "PM N0000000000 C000 INPAGA";

	BigDecimal wtctal = new BigDecimal(0);
	BigDecimal wtctai = new BigDecimal(0);
	String sadhoc = null;
	//String wapr16 = null;
	//String fqdivr = null;
	//String wapr15 = null;
	//String fqdivg = null;
	//String wapd16 = null;
	//String wapd15 = null;
	String dsfeho = null;
	Integer dscoca = 0;
	String dscoac = null;
	Integer y = 0;
	
	String dwcoac = null;
	String wwcoac = null;
	String[] mon = null;
	String[] moe = null;
	String[] mta = new String[50]; //50 de 33 posiciones
	String[] daj = new String[50]; // 50 de 28 posiciones
	String[] civ = null;
	String[] liv = null;
	String[] mos = null;
	String[] mod = new String[100];//100 elemntos - 10posiciones
	String[] mov = new String[100];//100 elemntos - 10posiciones
	
	String wconfi = null;
	//String mecacl = null; // TODO.: NO EXISTE
	String mone = null;
	String sposic = null;
	BigDecimal sslmcm = new BigDecimal(0); // TODO.: NO EXISTE
	//String memlco = null; // TODO.: NO EXISTE
	//String metcon = null; // TODO.: NO EXISTE
	Integer aaorgn = 0; // TODO.: NO EXISTE
	//Integer meorg = 0; // TODO.: NO EXISTE
	
	//String meyfac = null; // TODO.: NO EXISTE
	//String meaafc = null; // TODO.: NO EXISTE
	//String mecifa = null; // TODO.: NO EXISTE
	//String meagig = null; // TODO.: NO EXISTE
	//String melogo = null; // TODO.: NO EXISTE
	//String mencct = null; // TODO.: NO EXISTE
	
	BigDecimal adolar = new BigDecimal(0);
	Integer fecaux = 0;
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
	BigDecimal ws6101 = new BigDecimal(0);
	String wa6101 = null;
	BigDecimal ws6115 = new BigDecimal(0);
	
	Integer fc6001 = 0;
	BigDecimal ws6001 = new BigDecimal(0);
	String wa6001 = null;
	BigDecimal ws6116 = new BigDecimal(0);
	String aadrfn = null;
	BigDecimal wstpin = new BigDecimal(0);
	BigDecimal wstpct = new BigDecimal(0);
	BigDecimal watpin = new BigDecimal(0);
	Integer wtotiu = 0;
	Integer wstpcu = 0;
	Integer watpiu = 0;
	BigDecimal wtotin = new BigDecimal(0);
	BigDecimal totses = new BigDecimal(0);
	BigDecimal wimplo = new BigDecimal(0);
	//BigDecimal mesafi = new BigDecimal(0);
	String wdesli = "";
	Integer fcu101 = 0;
	Integer fcu001 = 0;
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
	String aux17 = "CITIPHONE BANKING";
	
	String wreflo = "";
	String wrefe = "";
	String wwdrfn = "";
	Integer wwmrch = 0;
	//Integer lxrefp = 0;
	String agrefe = "Ref:";
	
	/*String strx = "";
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
	Integer txfmovc = 0;*/
	Integer sqmov = 0;
	String sretpos = "";
	
	//String txfile2 = "";
	String sacttl = "";
	String sactti = "";
	String sactil = "";
	String sactii = "";
	String sactml = "";
	String sactmi = "";
	//String txmin = "";
	//BigDecimal txtefm = new BigDecimal(0);
	Integer resp05 = 0;
	
	Boolean ain69 = false;
	Boolean ain71 = false;
	Boolean ain73 = false;
	Boolean ain74 = false;
	Boolean ain75 = false;
	Boolean ain79 = false;
	//Integer whcrcp = 0;
	//Integer whcrc2 = 0;
	//Integer whcrc3 = 0;
	//Integer wsttem = 0;
	//Integer wlimpo = 0;
	//Integer wtmoz1 = 0;
	//Integer wtmoz2 = 0;
	//Integer wtmoz3 = 0;
	String[] wt1 = null; // 10 elementos
	String[] wh1 = null; // 10 elementos entero
	String[] wc1 = null; // 10 elemento string
	String digant = "";
	String mxmota = "";
	String ordant = "";
	String aacanb = "";
	String soldmlr = "";
	BigDecimal wtmoz2 = new BigDecimal(0);
	BigDecimal whcrc2 = new BigDecimal(0);
	BigDecimal wlimpo = new BigDecimal(0);
	BigDecimal wtmoz3 = new BigDecimal(0);
	BigDecimal whcrc3 = new BigDecimal(0);
	String wclmo2 = "";
	String wsinfi = "";
	BigDecimal wsttem = new BigDecimal(0);
	String wclmo3 = "";
	Integer z1 = 0;
	String aama12 = "";
	Integer aancuo = 0;
	Integer aacacu = 0;
	String mlfecu = "";
	BigDecimal ws6d15 = new BigDecimal(0);
	BigDecimal ws6d16 = new BigDecimal(0);
	String mamini = "";
	
	BigDecimal wtmoz1 = new BigDecimal(0);
	BigDecimal whcrcp = new BigDecimal(0);
	String wclmo1 = "";
	//Integer txncuo = 0;
	//Integer txcacu = 0;
	//BigDecimal txiorg = new BigDecimal(0);
	Boolean[] sindl = new Boolean[5];
	Integer sssnbr = 0;
	BigDecimal ws6000 = new BigDecimal(0);
	BigDecimal wsto61 = new BigDecimal(0);
	BigDecimal wapr16 = new BigDecimal(0);
	BigDecimal wapr15 = new BigDecimal(0);
	BigDecimal wapd16 = new BigDecimal(0);
	BigDecimal wapd15 = new BigDecimal(0);
	BigDecimal wa6115 = new BigDecimal(0);
	BigDecimal wx6115 = new BigDecimal(0);
	BigDecimal wa6116 = new BigDecimal(0);
	String ad81 = "";
	String ad82 = "";
	BigDecimal totdes = new BigDecimal(0);
	String desdes = "";
	String ttfecu = "";
	BigDecimal alimpo = new BigDecimal(0);
	BigDecimal wtimp1 = new BigDecimal(0);
	BigDecimal wtotar = new BigDecimal(0);
	Boolean ain15 = false;
	Integer w1mrch = 0;
	String wreflx = "";
	Integer wrefly = 0;
	Integer wreflq = 0;
	Integer wreflw = 0;
	Integer wreflv = 0;
	Integer wreflr = 0;
	BigDecimal totcuo = new BigDecimal(0);
	String w1desc = "";
	Integer w1ncuo = 1;
	Integer w1cacu = 0;
	BigDecimal wlcrcp = new BigDecimal(0);
	BigDecimal mlcap = new BigDecimal(0);
	BigDecimal mlinte = new BigDecimal(0);
	BigDecimal totcu2 = new BigDecimal(0);
	String wsadef = "";
	Integer agamov = 0;
	String wsinef = "";
	String wmone = "";
	String wacinb = "";
	BigDecimal wlimp1 = new BigDecimal(0);
	BigDecimal wlimpa = new BigDecimal(0);
	Integer ascuol = 0;
	BigDecimal ascaol = new BigDecimal(0);
	String agsini = "";
	BigDecimal wiimpo = new BigDecimal(0);
	String soldmir = "";
	String mifecu = "";
	BigDecimal wicrcp = new BigDecimal(0);
	BigDecimal wimcbo = new BigDecimal(0);
	BigDecimal wiimp1 = new BigDecimal(0);
	BigDecimal wiimpa = new BigDecimal(0);
	String micant = "";
	String mide40 = "";
	String kyfr00 = "";
	Integer winca1 = 0;
	String wdespa = "";
	String aadosf = "";
	BigDecimal totcui = new BigDecimal(0);
	Integer aucuol = 0;
	BigDecimal aucaol = new BigDecimal(0);
	String mlde40 = "";
	
	
	
	
	//Funtioness	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	//Estrucutras
	ZRSTEMMVSTRX strx = null;
	//ZRSTEMMVSFMT sfmt = null;
	ZRSTEMMVLXREFP lxrefp = null;
	
	public String SubProcGetstmdet (Zrsprer SstmHdr, String Spos, Integer SmaxElem, String[] Sfmt, Integer Sind  ) {
		long start = markStart();
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			
			//Valores Iniciales
			
			this.sstmhdr = SstmHdr;
			this.spos = Spos;
			this.smaxelem = SmaxElem;
			this.sfmt = Sfmt;
			this.sind = Sind;
			
			this.SubRutAinit(ds);
			
			this.syapaso = "1";
			//this.scabecera = SstmHdr;
			this.sind = 0;
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
	
	private String SubRutApprde (DataSet ds, String Spos) {
		try {
			this.wconfi = this.sstmhdr.getMecacl();
			this.mone = "pesos ";
			this.sposic = Spos;
			if (this.sstmhdr.getMecacl() == "c") {
				this.sslmcm = this.sstmhdr.getMemlco();
			}
			if (this.sstmhdr.getMetcon() == "1" || this.sstmhdr.getMetcon() == "3") {
				this.aaorgn = this.sstmhdr.getMeorg();
				
				this.ListZrspplr = myDAOZrspplr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct());
				for( Zrspplr o :ListZrspplr ) {
					if (o.getTlcori() == 7 && o.getTlcmov() == 70 && o.getTlcsmv() == 3) {
						if ( fc.BigDecimalComparar(o.getTlpmna().toString(), "0", "<") ) {
							this.adolar = o.getTlpmna().negate();
						}else {
							this.adolar = o.getTlpmna();
						}
					}
					wtctal = wtctal.add(o.getTlpmna());
					this.fecaux = 0;
					this.wdeslo = "";
					this.drcmon = "001";
					this.drcori = o.getTlcori();
					this.drcmov = o.getTlcmov();
					this.drcsmv = o.getTlcsmv();
					if (o.getTlcmov() == 61) {
						if (o.getTlcsmv() == 6) {
							this.wdeslo = "";
							this.wdeslo = this.tl0127;
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
					
					if (this.wdesl1.equals("") ||  o.getTlcori() == 2 && o.getTlcmov() == 70 ) {
						this.wdesl1 = "";
						this.wdesl1 = o.getTlde40(); 
					}
					if (o.getTlcori() == 2 && o.getTlcmov() == 70 && o.getTlcsmv() != 40 || o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 15 || o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 16 ) {
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
					
					if (o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 15 || o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 16 ) {
						if (o.getTlcsmv() == 15) {
							this.fc6101 = 0;
							//this.ws6101 = new BigDecimal(0);
							this.wa6101 = "";
							this.ws6115 = this.ws6115.add(o.getTlpmna());							
						} else {
							this.fc6001 = 0;
							//this.ws6001 = new BigDecimal(0);
							this.wa6001 = "";
							this.ws6116 = this.ws6116.add(o.getTlpmna());
						}
					} else {
						this.aadrfn = o.getTlxtrf();
						// LXREFP = Zrspplr (al registro de esta tabla)
						lxrefp = new ZRSTEMMVLXREFP();
						//lxrefp.setTLNPTR(o.ptr);
						lxrefp.setTLNCAX(o.getTlncax());
						lxrefp.setTLCRDD(o.getTlcrdd());
						lxrefp.setTLCRMM(o.getTlcrmm());
						lxrefp.setTLCRAA(o.getTlcraa());
						
						this.SubRutArefpg(ds);
						this.SubRutSlineplr(ds, o.getTlpmna(), o.getTlcori(), o.getTlcmov(), o.getTlcsmv(), o.getTlncrd());
					}					
				} // fin for
				
				//verificar (SFMT)
				// 
				
				this.wtotin = new BigDecimal(0);
				this.wstpin = new BigDecimal(0);
				this.wstpct = new BigDecimal(0);
				this.watpin = new BigDecimal(0);
				this.wtotiu = 0;
				this.wstpcu = 0;
				this.watpiu = 0;
				this.aaorgn = this.sstmhdr.getMeorg();
				//variable para rutina
				String _ilxtrf = "";
				Integer _ilcori = 0;
				Integer _ilcmov = 0;
				Integer _ilcsmv = 0;
				String _ilncrd = "";

				this.ListZrspilr = myDAOZrspilr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAorgnAndMelogoAndMencct(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct());
				for (Zrspilr o :ListZrspilr ) {
					_ilxtrf = o.getIlxtrf();
					_ilcori = o.getIlcori();
					_ilcmov = o.getIlcmov();
					_ilcsmv = o.getIlcsmv();
					_ilncrd = o.getIlncrd();
					
					if (o.getIlcsmv()< 50) {
						this.wtotin = this.wtotin.add(o.getIlicpm());
						
					}
				}
				if (fc.BigDecimalComparar(this.wtotin.toString(), "0", "!=")) {
					this.wtctal = this.wtctal.add(this.wtotin);
					if ( fc.BigDecimalComparar(this.wstpct.toString(), "0", ">") ) {
						this.wstpin = this.watpin.divide(this.wstpct);
					}
					this.SubRutSlineilr(ds, _ilxtrf, _ilcori, _ilcmov, _ilcsmv, _ilncrd);
				}
				
				this.aaorgn = this.sstmhdr.getMeorg();
				
				ObjZrspmlr = myDAOZrspmlr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct());
				
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
					SubRutSlinel42(ds, ObjZrspmlr.getMlxtrf(), ObjZrspmlr.getMlcori(), ObjZrspmlr.getMlcmov(), ObjZrspmlr.getMlcsmv(), ObjZrspmlr.getMlncrd());
					this.wtctal = this.wtctal.add(this.totses);
				}
				this.wimplo = this.wtctal;
				
			}//fin if
			
			if (this.sstmhdr.getMetcon() == "2" || this.sstmhdr.getMetcon() == "3") {
				this.mone = "DOLARES";
				this.wtctai = this.sstmhdr.getMesafi();
				this.aaorgn = this.sstmhdr.getMeorg() -1;
				
				ListZrsppir = myDAOZrsppir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct() );
				for (Zrsppir o :ListZrsppir) {
					this.fecaux = 0;
					this.wdeslo = "";
					this.drcmon = "002";
					this.drcori = o.getTicori();
					this.drcmov = o.getTicmov();
					this.drcsmv = o.getTicsmv();
					if (o.getTicmov() == 61) {
						if (o.getTicsmv() == 6) {
							this.wdeslo = "";
							this.wdeslo = this.ti0127;
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
					
					if (this.wdesl1.equals("") ||  o.getTicori() == 2 && o.getTicmov() == 70 ) {
						this.wdesl1 = "";
						this.wdesl1 = o.getTide40();
						this.wdesli = this.wdesl1;
					}//fin if
					
					this.wtctai = this.wtctai.add(o.getTiimpo());
					if (o.getTicori() == 2 && o.getTicmov() == 70 && o.getTicsmv() != 40 || o.getTicori() == 3 && o.getTicmov() == 60 && o.getTicsmv() == 1 || o.getTicori() == 3 && o.getTicmov() == 61 && o.getTicsmv() == 1 || o.getTicori() == 3 && o.getTicmov() == 61 && o.getTicsmv() == 28) {
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
					
					if (o.getTicori() == 3 && o.getTicmov() == 61 && o.getTicsmv() == 1 || o.getTicori() == 3 && o.getTicmov() == 60 && o.getTicsmv() == 1) {
						if (o.getTicmov() == 61) {
							this.fcu101 = this.fecaux;
							this.wsu101 = this.wsu101.add(o.getTiimpo());
							this.wau101 = o.getTide40();
						} else {
							this.fcu001 = this.fecaux;
							this.wsu001 = this.wsu001.add(o.getTiimpo());
							this.wau001 = o.getTide40();	
						}
					}else {
						//if (o.getTlcori() == 3 && o.getTlcmov() == 61 && o.getTlcsmv() == 28) {
						//	this.wdesl1 = "TIDE40";
						//} else {
						
						this.aadrfn = o.getTixtrf();
						//}
						//this.lxrefp = o.get
						this.lxrefp = new ZRSTEMMVLXREFP();
						lxrefp.setTINCAX(o.getTincax());
						lxrefp.setTICRDD(o.getTicrdd());
						lxrefp.setTICRMM(o.getTicrmm());
						lxrefp.setTICRAA(o.getTicraa());
						
						SubRutArefpg(ds);
						SubRutSlinepir(ds, o.getTiimpo(), o.getTicori(), o.getTicmov(), o.getTicsmv(), o.getTincrd());
						
					}//fin if
					
				}//fin for
				
				// ($FMT),
				//
				
				this.wtotin = new BigDecimal(0);
				this.wstpin = new BigDecimal(0);
				this.wstpct = new BigDecimal(0);
				this.watpin = new BigDecimal(0);
				this.wtotiu = 0;
				this.wstpcu = 0;
				this.watpiu = 0;
				this.aaorgn = this.sstmhdr.getMeorg() - 1;
				//variables
				String _iixtrf = "";
				Integer _iicori = 0;
				Integer _iicmov = 0;
				Integer _iicsmv = 0;
				String _iincrd = "";
				
				ListZrspiir = myDAOZrspiir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct());
				for(Zrspiir o : ListZrspiir) {
					this.wtotin = this.wtotin.add(o.getIiintx());
					_iixtrf = o.getIixtrf();
					_iicori = o.getIicori();
					_iicmov = o.getIicmov();
					_iicsmv = o.getIicsmv();
					_iincrd = o.getIincrd();
				}

 
				
				if ( fc.BigDecimalComparar(this.wtotin.toString(), "0", ">")) {
					this.wtctai = this.wtctai.add(this.wtotin);
					if ( fc.BigDecimalComparar(this.wstpct.toString(), "", ">") ) {
						this.wstpin = this.watpin.divide(this.wstpct);
					}
					SubRutSlineiir(ds, _iixtrf, _iicori, _iicmov, _iicsmv, _iincrd); 
				}
				this.aaorgn = this.sstmhdr.getMeorg() -1;

				ObjZrspmir = myDAOZrspmir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct());
				
				if (ObjZrspmir != null) {
					SubRutAizon1(ds);
					SubRutAprtpesii(ds, ObjZrspmir.getMixtrf(), ObjZrspmir.getMicori(), ObjZrspmir.getMicmov(), ObjZrspmir.getMicsmv());
					SubRutAizon2(ds);
					SubRutAizon3(ds);
				}else {
					//SubRutAprtpesii(ds);
				}
				
				if ( fc.BigDecimalComparar(this.totseu.toString(), "0", "!=") ) {
					SubRutSlinei42(ds, ObjZrspmir.getMixtrf(), ObjZrspmir.getMicori(), ObjZrspmir.getMicmov(), ObjZrspmir.getMicsmv(), ObjZrspmir.getMincrd());
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

	private String SubRutAinit(DataSet ds) {
		try {
			this.wtctal = null;
			this.wtctai = null;
			this.sadhoc = null;
			/*
			this.wapr16 = this.fqdivr; // TODO.:NO EXISTE
			this.wapr15 = this.fqdivg; // TODO.:NO EXISTE
			this.wapd16 = this.fqdivr; // TODO.:NO EXISTE
			this.wapd15 = this.fqdivg; // TODO.:NO EXISTE
			*/
			
			this.dsfeho = this.fc.FormatoFechaHora("HH:mm:ss yy.MM.dd");
			this.dscoca = 810;
			this.dscoac = "";
			this.y = 0;
			
			ListDtgpdes = myDAODtgpdes.getUsingDscoca(ds, this.dscoca.toString());
			
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
			this.dscoca = 3;
			this.dscoac = "400";
			this.y = 0;
			ListDtgpdes = myDAODtgpdes.getUsingDscoca(ds, this.dscoca.toString());
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
			this.wwmrch = 0;
			this.ain69 = false;
			if (this.drcori == 2 && this.drcmov == 70) {
				this.wwdrfn = this.aadrfn;
			}else {
				this.wreflo = this.lxrefp.toString(); //NO EXISTE lxrefp 
			}
			
			if (drcori == 12 && drcmov == 20) {
				this.wreflo= "";
			}
			if ( !this.wwdrfn.equals("")) {
				if (drcori == 2 && drcmov == 70) {
					this.ain69 = true;
					this.wrefe = this.agrefe;
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
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO02");
			//strx.setTXFMT(strx.getTXFMT().trim() + "R");
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXMINI("1");
			strx.setTXFILE("P");
			strx.setTXLOIN("L");
			strx.setTXDESC(this.wdesl1);
			strx.setTXIMPO(TLPMNA);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(TLCORI);
			strx.setTXCMOV(TLCMOV);
			strx.setTXCSMV(TLCSMV);
			strx.setTXCANB(TLNCRD);
			
			if ( fc.ValidarAammdd(this.sw0106.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt(fc.ConvertAmdToDma(this.sw0106.toString())));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAnewentry(DataSet ds) {
		try {
			if ( (this.sind + this.sqmov ) < this.sfmt.length && (this.sind + this.sqmov ) < this.smaxelem ) {
				this.sind = this.sind +1;
				this.sfmt[this.sind] = this.strx;
				if (this.sind == this.sfmt.length || this.sind == this.smaxelem) {
					SubRutAexit(ds);
				}
			} else {
				SubRutAexit(ds);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAexit(DataSet ds) {
		try {
			strx.TXFILE2 = strx.getTXFILE() + strx.getTXLOIN();
			if (strx.getTXFILE2().equals("PL")) {
				this.sretpos = strx.getTXFILE2() + this.sacttl;				
			}else if (strx.getTXFILE2().equals("PI")) {
				this.sretpos = strx.getTXFILE2() + this.sactti;	
			}else if (strx.getTXFILE2().equals("IL")) {
				this.sretpos = strx.getTXFILE2() + this.sactil;	
			}else if (strx.getTXFILE2().equals("II")) {
				this.sretpos = strx.getTXFILE2() + this.sactii;	
			}else if (strx.getTXFILE2().equals("ML")) {
				this.sretpos = strx.getTXFILE2() + this.sactml;	
			}else if (strx.getTXFILE2().equals("MI")) {
				this.sretpos = strx.getTXFILE2() + this.sactmi;	
			}else {
				this.sretpos = strx.getTXFILE2() + strx.getTXMINI();	
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlineilr(DataSet ds, String ILXTRF, Integer ILCORI, Integer ILCMOV, Integer ILCSMV, String ILNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO03");
			strx.setTXMINI("1");
			strx.setTXFILE("I");
			strx.setTXLOIN("L");
			strx.setTXDESC("INTERESES POR ATRASOS");
			strx.setTXIMPO(this.wtotin);
			strx.setTXREFC(ILXTRF);
			strx.setTXTEFM(this.wstpin);
			strx.setTXCORI(ILCORI);
			strx.setTXCMOV(ILCMOV);
			strx.setTXCSMV(ILCSMV);
			strx.setTXCANB(ILNCRD);
			
			/*if ( fc.ValidarAammdd(this.resp05.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt(fc.ConvertAmdToDma(this.resp05.toString())));
			} else {*/
				strx.setTXFMOVC(0);
			//}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAlzon1(DataSet ds) {
		try {
			ListZrspmlr = myDAOZrspmlr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedict(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), this.sstmhdr.getMencct().substring(6, 15), this.sstmhdr.getMencct().substring(16, 19), "1"); 
			
			if (ListZrspmlr != null) {
				this.ain73 = false;
				this.ain74 = false;
				this.ain75 = false;
				this.whcrcp = new BigDecimal(0);
				this.whcrc2 = new BigDecimal(0);
				this.whcrc3 = new BigDecimal(0);
				for (Zrspmlr o : ListZrspmlr) {//Por cada registro seleccionado
					this.wsttem = new BigDecimal(0);
					this.wlimpo = new BigDecimal(0);
					this.wtmoz1 = new BigDecimal(0);
					this.wtmoz2 = new BigDecimal(0);
					this.wtmoz3 = new BigDecimal(0);
					this.wt1[0] = "0";
					this.wh1[0] = "0";
					this.wc1[0] = "0";
					this.digant = o.getMlncrd().substring(16, 18); //Mldita(); 
					this.mxmota = o.getMlncrd().substring(19, 19); //Mlmota(); 
					this.ordant = o.getMlubir();
					this.aacanb = o.getMlncrd();
					for (int j = 0; j < EMPTY_STRING_ARRAY.length; j++) {//Por cada registro seleccionado y MLDITA = DIGANT y MLUBIR = ORDANT
						this.soldmlr = this.sactml;
						this.wreflo = "";
						this.wrefe = "";
						this.wwdrfn = "";
						this.wwmrch = 0;
						this.ain69 = false;
						this.wdeslo = "";
						this.drcmon = "001";
						this.drcori = o.getMlcori();
						this.drcmov = o.getMlcmov();
						this.drcsmv = o.getMlcsmv();
						
						if (Arrays.asList(mos).contains(this.wclmov)) {
							if ( o.getMlcsmv() >= 90) {
								this.wtmoz2 = this.wtmoz2.add(o.getMlimpo());
								this.whcrc2 = this.whcrc2.add(o.getMlcrcp());
								this.wclmo2 = this.wclmov;
							}else {
								if (o.getMlcori() == 11 && o.getMlcmov() == 31 && o.getMlcsmv() == 1) {
									this.wlimpo = this.wlimpo.add(o.getMlimpo());
									this.wsinfi = this.mlde40;
									if ( fc.BigDecimalComparar(o.getMltefm().toString(), "0", "!=") ) {
										this.wsttem = o.getMltefm();
									}
								}else {
									if (o.getMlcori() == 9 && o.getMlcmov() == 5 && o.getMlcsmv() == 1) {
										this.wtmoz3 = this.wtmoz3.add(o.getMlimpo());
										this.whcrc3 = this.whcrc3.add(o.getMlcrcp());
										this.wclmo3 = this.wclmov;
									}else {
										if (o.getMlcori() == 9 && o.getMlcmov() == 5) {
											if (o.getMlcsmv() == 10)
												this.z1 = 1;
											else if (o.getMlcsmv() == 11) 
												this.z1 = 2;
											else if (o.getMlcsmv() == 12) 
												this.z1 = 3;
											else if (o.getMlcsmv() == 13) 
												this.z1 = 4;
											else if (o.getMlcsmv() == 14) 
												this.z1 = 5;
											else if (o.getMlcsmv() == 15) 
												this.z1 = 7;
											else
												this.z1 = 8;
											
											this.wt1[this.z1] = (new BigDecimal(wt1[this.z1]).add(o.getMlimpo())).toString();
											this.wh1[this.z1] = (new BigDecimal(wh1[this.z1]).add(o.getMlcrcp())).toString();
											this.wc1[this.z1] = this.wclmov;
										}
									}
								}
							}
							this.aama12 = "";
							if (o.getMlncuo() != 0) {
								this.aama12 = " CUOTA";
								this.aancuo = o.getMlncuo();
								this.aacacu = o.getMlcacu();
								this.aama12 = this.aama12 + " " + this.aancuo + "/" + this.aacacu;
								this.mlde40 = this.aama12;
							}
							if (o.getMlcori() == 9 && o.getMlcmov() == 5) {
								if (o.getMlcsmv() == 11 || o.getMlcsmv() == 12 || o.getMlcsmv() == 13 || o.getMlcsmv() == 14 || o.getMlcsmv() == 91 || o.getMlcsmv() == 92 || o.getMlcsmv() == 93 || o.getMlcsmv() == 94) {
									this.ain75 = true;
								}
							}
						}else {
							this.wimplo = o.getMlimpo();
							this.wtctal = this.wtctal.add(o.getMlimpo());
							if (o.getMlcmov() == 60 && o.getMlcsmv() != 0) {
								this.wdeslo = "";
								SubRutAdesmo(ds);
								this.mlfecu = "";
							}else {
								this.wdeslo = this.mlde40;
								this.mlfecu = "";
							}
							if (o.getMlcori() == 9 && o.getMlcmov() == 5) {
								if (o.getMlcsmv() == 11 || o.getMlcsmv() == 12 || o.getMlcsmv() == 13 || o.getMlcsmv() == 14 || o.getMlcsmv() == 91 || o.getMlcsmv() == 92 || o.getMlcsmv() == 93 || o.getMlcsmv() == 94) {
									this.ain75 = true;
								}
							}
							if (o.getMlcori() == 8) {
								this.mlfecu = "";
							}
							this.fecaux = 0;
							if (o.getMlcori() == 3 && o.getMlcmov() == 60 && o.getMlcsmv() == 15 || o.getMlcori() == 3 && o.getMlcmov() == 60 && o.getMlcsmv() == 16) {
								if (o.getMlcsmv() == 15) {
									this.ws6d15 = this.ws6d15.add(o.getMlimpo());
								}else {
									this.ws6d16 = this.ws6d16.add(o.getMlimpo());
								}
							}else {
								if (this.wdeslo == "") {
									this.wdeslo = this.mlde40;
								}
								this.mamini = "1";
								SubRutSlinel04(ds, o.getMlncuo(), o.getMlcacu(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
								this.ain75 = true;
								this.whcrcp = new BigDecimal(0);
							}
						}
					}
					for (int z1 = 1; z1 < 10; z1++) {
						this.wtmoz1 = new BigDecimal(this.wt1[z1]);
						this.whcrcp = new BigDecimal(this.wh1[z1]);
						this.wclmo1 = this.wc1[z1];
						if ( fc.BigDecimalComparar(this.wtmoz1.toString(), "0", "!=")) {
							this.wreflo = "";
							this.wrefe = "";
							this.wwdrfn = "";
							this.wwmrch = 0;
							this.ain69 = false;
							this.wimplo = this.wtmoz1;
							this.wtctal = this.wtctal.add(this.wtmoz1);
							this.wclmov = wclmo1;
							this.wdeslo = "";
							SubRutAdesmo(ds);
							this.fecaux = 0;
							this.mamini = "1";
							SubRutSlinel04s(ds,o.getMlncuo(), o.getMlcacu(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
							if ( fc.BigDecimalComparar(this.wtmoz2.toString(), "0", "=")) {
								this.ain75 = false;
							}
							this.whcrcp = BigDecimal.ZERO;
							this.wtmoz1 = BigDecimal.ZERO;
						}
					}
					if (fc.BigDecimalComparar(this.wtmoz2.toString(), "0", "!=")) {
						this.wreflo = "";
						this.wrefe = "";
						this.wwdrfn = "";
						this.wwmrch = 0;
						this.ain69 = false;
						this.wimplo = this.wtmoz2;
						this.wtctal = this.wtctal.add(this.wtmoz2);
						this.whcrcp = this.whcrc2;
						this.wclmov = this.wclmo2;
						this.wdeslo = "";
						SubRutAdesmo(ds);
						this.fecaux = 0;
						this.mamini = "1";
						SubRutSlinel04s(ds, o.getMlncuo(), o.getMlcacu(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						this.ain75 = false;
						this.whcrcp = BigDecimal.ZERO;
						this.whcrc2 = BigDecimal.ZERO;
						this.wtmoz2 = BigDecimal.ZERO;
					}
					if (fc.BigDecimalComparar(this.wtmoz3.toString(), "0", "!=")) {
						this.wreflo = "";
						this.wrefe = "";
						this.wwdrfn = "";
						this.wwmrch = 0;
						this.ain69 = false;
						this.wimplo = this.wtmoz3;
						this.wtctal = this.wtctal.add(this.wtmoz3);
						this.whcrcp = this.whcrc3;
						this.wdeslo = "";
						this.wclmov = this.wclmo3;						
						SubRutAdesmo(ds);
						this.fecaux = 0;
						this.mamini = "1";
						SubRutSlinel04s(ds, o.getMlncuo(), o.getMlcacu(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						this.wtmoz3 = BigDecimal.ZERO;
					}
					if (fc.BigDecimalComparar(this.wlimpo.toString(), "0", "!=")) {
						this.ain79 = false;
						this.wtctal = this.wtctal.add(this.wlimpo);
						this.mamini = "1";
						SubRutSlinel41(ds, o.getMlcrcp(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlncrd());
						this.wlimpo = BigDecimal.ZERO;
					}
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel04(DataSet ds, Integer MLNCUO,
			Integer MLCACU,
			String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO04");
			this.sindl = new Boolean[5];
			sindl[2] = this.ain73;
			sindl[3] = this.ain74;
			sindl[4] = this.ain75;
			SubRutSgetfmtnbr(ds);
			strx.setTXFMT(strx.getTXFMT().trim() + "#" + this.sssnbr);
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXMINI(this.mamini);
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC(this.wdeslo);
			
			if (this.ain74 == true) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.wimplo);
			strx.setTXNCUO(MLNCUO);
			strx.setTXCACU(MLCACU);
			strx.setTXIORG(this.whcrcp);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel04s(DataSet ds, Integer MLNCUO,
			Integer MLCACU,
			String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO04");
			this.sindl = new Boolean[5];
			sindl[2] = this.ain73;
			sindl[3] = this.ain74;
			sindl[4] = this.ain75;
			SubRutSgetfmtnbr(ds);
			strx.setTXFMT(strx.getTXFMT().trim() + "#" + this.sssnbr);
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXMINI(this.mamini);
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC(this.wdeslo);
			
			if (this.ain74 == true) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.wimplo);
			strx.setTXNCUO(MLNCUO);
			strx.setTXCACU(MLCACU);
			strx.setTXIORG(this.whcrcp);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			this.sactml = this.soldmlr;
			
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSgetfmtnbr(DataSet ds) {
		try {
			this.sssnbr=0;
			Integer sj = 0;
			for (int si = 0; si < 3; si++) {
				sj = 2*si;
				if (this.sindl[si+1]) {
					this.sssnbr = this.sssnbr + sj; 
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel41(DataSet ds, BigDecimal MLCRCP,
			String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO41SM0");
			strx.setTXMINI("3");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			if (!this.wsinfi.equals("")) {
				strx.setTXDESC(this.wsinfi);
			}else {
				strx.setTXDESC("INTERESES SOBRE CAPITAL ");
			}

			strx.setTXIMPO(this.wimplo);
			strx.setTXIORG(MLCRCP);
			strx.setTXTEFM(this.wsttem);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(0);
			strx.setTXCANB(MLNCRD);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			
			SubRutAnewentry(ds);
			
			if (this.ain79) {
				strx = new ZRSTEMMVSTRX();
				strx.setTXFMT("DELO41SM1");
				strx.setTXMINI("3");
				strx.setTXFILE("M");
				strx.setTXLOIN("L");
				strx.setTXDESC("- COSTO DE FINANCIAMIENTO  ");
				strx.setTXIMPO(this.wimplo);
				strx.setTXREFC(MLXTRF);
				strx.setTXCORI(MLCORI);
				strx.setTXCMOV(MLCMOV);
				strx.setTXCSMV(1);
				strx.setTXCANB(MLNCRD);
				
				if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
					strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
				} else {
					strx.setTXFMOVC(0);
				}
				
				SubRutAnewentry(ds);
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAprtpesil(DataSet ds) {
		try {
			if (fc.BigDecimalComparar(this.ws6101.toString(), "0", "!=")) {
				this.fecaux = this.fc6101;
				this.wdesl1 = this.wa6101;
				this.ws6000 = this.ws6101;
				SubRutSlinel6a(ds);
			}
			this.ws6101 = BigDecimal.ZERO;
			if (fc.BigDecimalComparar(this.ws6001.toString(), "0", "!=")) {
				this.fecaux = this.fc6001;
				this.wdesl1 = this.wa6001;
				this.ws6000 = this.ws6001;
				SubRutSlinel6a(ds);
			}
			this.ws6001 = BigDecimal.ZERO;
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel6a(DataSet ds) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO6A");
			strx.setTXMINI("1");
			strx.setTXFILE("S");
			strx.setTXLOIN("L");
			strx.setTXDESC(this.wdesl1);
			strx.setTXIMPO(this.ws6000);
			strx.setTXREFC(ObjZrspmlr.getMlxtrf());
			strx.setTXCORI(ObjZrspmlr.getMlcori());
			strx.setTXCMOV(ObjZrspmlr.getMlcmov());
			strx.setTXCSMV(ObjZrspmlr.getMlcsmv());
			strx.setTXFMOVC(0);
			this.sqmov = 2;
			SubRutAnewentry(ds);

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAprtdeviva(DataSet ds) {
		try {
			this.wsto61 = this.ws6115.add(this.ws6116);
			this.wsto61 = this.wsto61.add(this.ws6d15);
			this.wsto61 = this.wsto61.add(this.ws6d16);
			this.wapr16 = new BigDecimal(1.27);
			this.wapr15 = new BigDecimal(2.48);
			this.wapd16 = new BigDecimal(1.27);
			this.wapd15 = new BigDecimal(2.48);
			
			if (fc.BigDecimalComparar(this.wsto61.toString(), "0", "!=")) {
				if (fc.BigDecimalComparar(this.ws6115.toString(), "0", "=")) {
					this.wa6115 = BigDecimal.ZERO;
				}else {
					this.wx6115 = this.ws6115.divide(new BigDecimal(0.0248));
					if (fc.BigDecimalComparar(this.wx6115.toString(), "0", "<")) {
						this.wa6115 = this.wx6115.negate();
					}else {
						this.wa6115 = this.wx6115;
					}
				}
				SubRutSlinel61(ds);
				this.ws6101 = BigDecimal.ZERO;
				this.ws6001 = BigDecimal.ZERO;
				this.ws6115 = BigDecimal.ZERO;
				this.ws6116 = BigDecimal.ZERO;
				this.wa6115 = BigDecimal.ZERO;
				this.wa6116 = BigDecimal.ZERO;
				this.ws6d15 = BigDecimal.ZERO;
				this.ws6d16 = BigDecimal.ZERO;
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel61(DataSet ds) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO61");
			strx.setTXMINI("3");
			strx.setTXFILE("S");
			strx.setTXLOIN("L");
			strx.setTXDESC("CR‚D.IVA COMBUSTIBLES  ");
			strx.setTXIMPO(this.ws6116);
			strx.setTXREFC(ObjZrspmlr.getMlxtrf());
			strx.setTXTEFM(this.wapr16);
			strx.setTXCORI(ObjZrspmlr.getMlcori());
			strx.setTXCMOV(ObjZrspmlr.getMlcmov());
			strx.setTXCSMV(ObjZrspmlr.getMlcsmv());
			strx.setTXCANB(ObjZrspmlr.getMlncrd());
			strx.setTXFMOVC(Integer.parseInt(fc.FormatoFechaHora("yyyyMMdd")));
			strx.setTXIORG(this.wa6116);
			this.sqmov = 2;
			SubRutAnewentry(ds);
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO61");
			strx.setTXMINI("3");
			strx.setTXFILE("S");
			strx.setTXLOIN("L");
			strx.setTXDESC("CR‚D.IVA OTROS CONSUMOS ");
			strx.setTXIMPO(this.ws6115);
			strx.setTXREFC(ObjZrspmlr.getMlxtrf());
			strx.setTXTEFM(this.wapr15);
			strx.setTXCORI(ObjZrspmlr.getMlcori());
			strx.setTXCMOV(ObjZrspmlr.getMlcmov());
			strx.setTXCSMV(ObjZrspmlr.getMlcsmv());
			strx.setTXCANB(ObjZrspmlr.getMlncrd());
			strx.setTXFMOVC(Integer.parseInt(fc.FormatoFechaHora("yyyyMMdd")));
			strx.setTXIORG(this.wa6115);
			SubRutAnewentry(ds);
			if (fc.BigDecimalComparar(this.ws6d16.toString(), "0", "!=") || fc.BigDecimalComparar(this.ws6d15.toString(), "0", "!=")) {
				strx = new ZRSTEMMVSTRX();
				strx.setTXFMT("DELO61");
				strx.setTXMINI("3");
				strx.setTXFILE("S");
				strx.setTXLOIN("L");
				strx.setTXDESC("D‚B. IVA COMBUSTIBLES  ");
				strx.setTXIMPO(this.ws6d16);
				strx.setTXREFC(ObjZrspmlr.getMlxtrf());
				strx.setTXTEFM(this.wapd16);
				strx.setTXCORI(ObjZrspmlr.getMlcori());
				strx.setTXCMOV(ObjZrspmlr.getMlcmov());
				strx.setTXCSMV(ObjZrspmlr.getMlcsmv());
				strx.setTXCANB(ObjZrspmlr.getMlncrd());
				strx.setTXFMOVC(Integer.parseInt(fc.FormatoFechaHora("yyyyMMdd")));
				strx.setTXIORG(BigDecimal.ZERO);
				this.sqmov = 2;
				SubRutAnewentry(ds);
				strx = new ZRSTEMMVSTRX();
				strx.setTXFMT("DELO61");
				strx.setTXMINI("3");
				strx.setTXFILE("S");
				strx.setTXLOIN("L");
				strx.setTXDESC("D‚B. IVA OTROS CONSUMOS ");
				strx.setTXIMPO(this.ws6d15);
				strx.setTXREFC(ObjZrspmlr.getMlxtrf());
				strx.setTXTEFM(this.wapd15);
				strx.setTXCORI(ObjZrspmlr.getMlcori());
				strx.setTXCMOV(ObjZrspmlr.getMlcmov());
				strx.setTXCSMV(ObjZrspmlr.getMlcsmv());
				strx.setTXCANB(ObjZrspmlr.getMlncrd());
				strx.setTXFMOVC(Integer.parseInt(fc.FormatoFechaHora("yyyyMMdd")));
				strx.setTXIORG(BigDecimal.ZERO);
				SubRutAnewentry(ds);
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAlzon2(DataSet ds) {
		try {
			ListZrspmlr = myDAOZrspmlr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedict(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), this.sstmhdr.getMencct().substring(6, 15), this.sstmhdr.getMencct().substring(16, 19), "2");
			for (Zrspmlr o : ListZrspmlr) {//Por cada registro seleccionado
				this.ad81 = "";
				this.ad82 = "";
				this.totdes = BigDecimal.ZERO;
				this.desdes = "";
				this.ttfecu = "";
				this.alimpo = BigDecimal.ZERO;
				this.wtotar = BigDecimal.ZERO;
				this.digant = o.getMlncrd().substring(16, 18);
				this.mxmota = o.getMlncrd().substring(19, 19);
				this.aacanb = o.getMlncrd();
				for (int i = 0; i < EMPTY_STRING_ARRAY.length; i++) {
					this.ain73 = false;
					this.ain74 = false;
					this.ain75 = false;
					if (o.getMlcori() == 1 && o.getMlcmov() == 37 || o.getMlcori() == 9 && o.getMlcmov() == 37 || o.getMlcori() == 9 && o.getMlcmov() == 39 || o.getMlcori() == 9 && o.getMlcmov() == 23 || o.getMlcori() == 24 && o.getMlcmov() == 37) {
						//($FMT) 
						
					}
					if (o.getMlncuo() != 0) {
						this.aama12 = "";
						this.aama12 = " CUOTA";
						this.aancuo = o.getMlncuo();
						this.aacacu = o.getMlcacu();
						this.aama12 = this.aama12 + " " + this.aancuo + "/" + this.aacacu;
						this.mlde40 = this.aama12;
					}
					this.ain15 = false;
					this.wreflo = "";
					this.wrefe = "";
					this.wwdrfn = "";
					this.wwmrch = 0;
					this.ain69 = false;
					this.wwdrfn = o.getMlxtrf();
					this.w1mrch = o.getmlru
					if (this.w1mrch != 0 && this.w1mrch != null) {
						this.wwmrch = this.w1mrch;
					}
					this.wdeslo = "";
					if (o.getMlncup() != 0) {
						this.wreflo = o.getMlncup().toString();
					}
					if (o.getMlcori() == 3) {
						this.wreflx = o.getMlncab() + o.getMldmov();
						this.wrefly = o.getMlmmov() + o.getMlamov();
					}
					if (o.getMlcori() == 23 && o.getMlcmov() == 35) {
						this.wreflo = "";
						this.wreflo = o.getMlncab();
					}
					if (o.getMlcori() == 1 || o.getMlcori() == 4 || o.getMlcori() == 5 || o.getMlcori() == 9 || o.getMlcori() == 24) {
						this.wreflo = o.getMlzoes().toString();
						this.wreflq = o.getMlrues() + o.getMlcoes();
						if ( !o.getMlncab().equals("")) {//MLCOBO es un substring (primeras dos posiciones) del MLNCAB
							if (true) {//MLCOBO  = BOCAP 
								this.wreflw = o.getMlsues() + o.getMlmoes();
								//this.wreflv = o.getMlfecu();//NO EXISTE
								//MLFECU = MLDMOV(2) + MLMMOV(2) + MLAMOV(2) Estos campos son del Zrspmlr

							}else {
								//this.wreflz = this.wlncab; //NO EXISTE Es un substring (posicion 12 a la 16) del campo MLNCAB
								if (o.getMlcori() == 1 && o.getMlcmov() == 20 && o.getMlzoes() == 1 && o.getMlrues() == 18) {
									if (o.getMlrefc().equals("") || o.getMlrefc().equals("0")) {
										this.wreflr = o.getMlncaf();
									} else {
										this.wreflo="";
										if (o.getMlncuo() != 0) {
											this.ain73 = true;
										}
										//this.wreflo = this.wreflo + " " + this.agpoli + " " + this.agrama + " " + this.agendo;
										//poli Es un substring (de 1 a 8) de MLREFC
										//grma Es un substring (de 12 a 14) de MLREFC
										//gedo Es un substring (de 17 a 19) de MLREFC
									}
								}else {
									this.wreflr = o.getMlncaf();
								}
							}
						}
						if (o.getMlcmov() == 22 && o.getMlcori() == 9 || o.getMlcmov() == 22 && o.getMlcori() == 5 || o.getMlcmov() == 22 && o.getMlcori() == 8 || o.getMlcmov() == 22 && o.getMlcori() == 1) {
							this.ain73 = true;
						}
						if (o.getMlcori() == 5 && o.getMlcmov() == 22 && o.getMlcsmv() == 9) {
							this.ain73 = false;
							this.ain74 = true;
						}
					}
					if (o.getMlcori() == 8 && o.getMlcmov() == 20 || o.getMlcori() == 8 && o.getMlcmov() == 22 || o.getMlcori() == 8 && o.getMlcmov() == 25) {
						this.wreflo = o.getMlzoes().toString();
						this.wreflq = o.getMlrues();
						this.wreflq = o.getMlcoes();
						if (!o.getMlncab().equals("")) {
							if (true) { // o.mlcobo == bocap
								this.wreflw = o.getMlsues();
								this.wreflw = o.getMlmoes();
								//this.wreflv = o.getMlfecu();//NO EXISTE
							}else {
								//this.wreflz = this.wlncab;
								this.wreflr = o.getMlncaf();
							}
						}
					}
					if (o.getMlcori() == 12 && o.getMlcmov() == 20) {
						this.wreflo = ""; //BLANCOS
					}
					if (!this.wwdrfn.equals("")) {
						if (o.getMlcori() == 1 || o.getMlcori() == 24 || o.getMlcori() == 5 || o.getMlcori() == 6 || o.getMlcori() == 9 && o.getMlcmov() == 38 || o.getMlcori() == 9 && o.getMlcmov() == 39 || o.getMlcori() == 9 && o.getMlcmov() == 36 || o.getMlcori() == 9 && o.getMlcmov() == 37 || o.getMlcori() == 9 && o.getMlcmov() == 58 || o.getMlcori() == 9 && o.getMlcmov() == 59 || o.getMlcori() == 9 && o.getMlcmov() == 56 || o.getMlcori() == 9 && o.getMlcmov() == 57 || o.getMlcori() == 12) {
							this.wrefe = this.agrefe;
						}
					}
					if (o.getMlcori() == 1 && o.getMlcmov() == 25) {
						this.wtimp1 = o.getMlcrcp().add(o.getMlimpo());
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							this.totcuo = o.getMlimpo();
							this.w1desc = this.mlde40;
							this.w1ncuo = o.getMlncuo();
							this.w1cacu = o.getMlcacu();
							if (true) { //o.getMlfecu()!=""
								this.fecadd = o.getMldmov();
								this.fecamm = o.getMlmmov();
								this.fecaa1 = o.getMlymov();
								this.fecaa2 = o.getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel27(ds, o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
							SubRutSlinel25(ds, o.getMlcrcp(), o.getMlxtrf(),o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
							
						}
					}
					if (o.getMlcori() == 1 && o.getMlcmov() == 26) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							SubRutSlinel26(ds, o.getMlimpo(), "o.getMlpifg()", o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
					if (o.getMlcori() == 1 && o.getMlcmov() == 27 || o.getMlcori() == 4 && o.getMlcmov() == 27) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.wlcrcp = o.getMlcrcp().add(o.getMlimpo());
						this.ain15 = true;
						if (!sstmhdr.getMecacl().equals("B")) {
							this.totcuo = o.getMlimpo();
							this.mlcap = o.getMlimpo();
							this.w1desc = this.mlde40;
							this.w1ncuo = o.getMlncuo();
							this.w1cacu = o.getMlcacu();
						}
					}
					if (o.getMlcori() == 1 && o.getMlcmov() == 28 || o.getMlcori() == 4 && o.getMlcmov() == 28) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.totcuo = this.totcuo.add(o.getMlimpo());
						this.mlinte = o.getMlimpo();
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							if (true) { //o.getMlfecu()!=""
								this.fecadd = o.getMldmov();
								this.fecamm = o.getMlmmov();
								this.fecaa1 = o.getMlymov();
								this.fecaa2 = o.getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel27(ds, o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
							SubRutSlinel28(ds, this.mlcap, this.mlinte, o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
					if (o.getMlcori() == 4 && o.getMlcmov() == 29) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							SubRutSlinel29(ds, o.getMlimpo(), o.getMlmini(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
					if (o.getMlcori() == 1 && o.getMlcmov() == 23) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							if (true) { //o.getMlfecu()!=""
								this.fecadd = o.getMldmov();
								this.fecamm = o.getMlmmov();
								this.fecaa1 = o.getMlymov();
								this.fecaa2 = o.getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel23(ds, o.getMlde40(), o.getMlimpo(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
					if (o.getMlcori() == 1 && o.getMlcmov() == 24) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						if (!sstmhdr.getMecacl().equals("B")) {
							if (true) { //o.getMlfecu()!=""
								this.fecadd = o.getMldmov();
								this.fecamm = o.getMlmmov();
								this.fecaa1 = o.getMlymov();
								this.fecaa2 = o.getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel23(ds, o.getMlde40(), o.getMlimpo(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
					if (o.getMlcori() == 1 && o.getMlcmov() == 36 || o.getMlcori() == 9 && o.getMlcmov() == 36 || o.getMlcori() == 9 && o.getMlcmov() == 22 || o.getMlcori() == 9 && o.getMlcmov() == 38 || o.getMlcori() == 24 && o.getMlcmov() == 36) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						if (!sstmhdr.getMecacl().equals("B")) {
							this.totcu2 = o.getMlimpo();
							this.w1desc = o.getMlde40();
							this.w1ncuo = o.getMlncuo();
							this.w1cacu = o.getMlcacu();
							this.ain15 = true;
							if (o.getMlcori() == 9 && o.getMlcmov() == 36 && o.getMlcsmv() == 0 || o.getMlcori() == 9 && o.getMlcmov() == 38 && o.getMlcsmv() == 0 || o.getMlcori() == 9 && o.getMlcmov() == 22 && o.getMlcsmv() == 0 ) {
								this.ain73 = false;
								this.ain74 = true;
							}
							if (o.getMlcori() == 24 && o.getMlcmov() == 36 && o.getMlcsmv() == 9) {
								this.ain73 = false;
								this.ain74 = true;
							}else {
								this.ain73 = true;
							}
							if (!this.wconfi.equals("B")) {
								if (true) { //o.getMlfecu()!=""
									this.fecadd = o.getMldmov();
									this.fecamm = o.getMlmmov();
									this.fecaa1 = o.getMlymov();
									this.fecaa2 = o.getMlamov();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinel36(ds, o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
								this.totcu2 = BigDecimal.ZERO;
							}
						}
					}
					if (o.getMlcori() == 1 && o.getMlcmov() == 37 || o.getMlcori() == 9 && o.getMlcmov() == 37 || o.getMlcori() == 24 && o.getMlcmov() == 37) {
						if (this.w1desc.equals("")) {
							this.w1desc = o.getMlde40();
						}
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.totcu2 = this.totcu2.add(o.getMlimpo());
						this.ain15 = true;
						if (o.getMlcori() == 9 && o.getMlcmov() == 37 && o.getMlcsmv() == 0) {
							this.ain73 = false;
							this.ain74 = true;
						}
						if (o.getMlcori() == 24 && o.getMlcmov() == 37 && o.getMlcsmv() == 9) {
							this.ain73 = false;
							this.ain74 = true;
						}else {
							this.ain73 = true;
						}
						if (!this.wconfi.equals("B")) {
							if (true) { //o.getMlfecu()!=""
								this.fecadd = o.getMldmov();
								this.fecamm = o.getMlmmov();
								this.fecaa1 = o.getMlymov();
								this.fecaa2 = o.getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel36(ds, o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
							this.totcu2 = BigDecimal.ZERO;
						}
					}
					if (o.getMlcori() == 17 && o.getMlcmov() == 26) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						this.wsadef = o.getMlde40();
						if (!this.wconfi.equals("B")) {
							if (o.getMldmov() != 0) {
								this.agamov = o.getMlymov() * 100;
								this.agamov = this.agamov + o.getMlamov();
							}
							SubRutSlinel37(ds, o.getMlimpo(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
					if (o.getMlcori() == 17 && o.getMlcmov() == 27) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						this.wsinef = o.getMlde40();
						if (!this.wconfi.equals("B")) {
							SubRutSlinel38(ds, o.getMlimpo(), o.getMltefm(), o.getMltnoa(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
					if (o.getMlcori() == 17 && o.getMlcmov() == 28) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							SubRutSlinel29(ds, o.getMlimpo(), o.getMlmini(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
					if (o.getMlcori() == 17 && o.getMlcmov() == 29) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						this.alimpo = this.alimpo.add(o.getMlimpo());
					}
					if (o.getMlcori() == 12 && o.getMlcmov() == 50 && o.getMlcsmv() == 0) {
						this.ain73 = true;
						this.ain74 = false;
					}
					if (o.getMlcori() == 1 && o.getMlcmov() == 20 && o.getMlcsmv() == 66) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.ain15 = true;
						this.totdes = this.totdes.add(o.getMlimpo());
						this.desdes = o.getMlde40();
						//this.ttfecu = o.getmlfecu(); //NO EXISTE
					}
					if (!this.ain15) {
						this.wtotar = this.wtotar.add(o.getMlimpo());
						this.wimplo = o.getMlimpo();
						this.wdeslo = o.getMlde40();
						if (!this.wconfi.equals("B")) {
							this.ain75 = false;
							if (true) { //o.getMlfecu()!=""
								this.fecadd = o.getMldmov();
								this.fecamm = o.getMlmmov();
								this.fecaa1 = o.getMlymov();
								this.fecaa2 = o.getMlamov();
							}else {
								this.fecaux = 0;
							}
							this.mamini = "2";
							SubRutSlinel04(ds, o.getMlncuo(), o.getMlcacu(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
				}
			}
			if (fc.BigDecimalComparar(this.alimpo.toString(), "0", "!=")) {
				if (!this.wconfi.equals("B")) {
					//SubRutSlinel30(ds, o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd()); // faltan parametros de Zrspmlr
				}
			}
			if (fc.BigDecimalComparar(this.totdes.toString(), "0", "!=")) {
				this.wimplo = this.totdes;
				this.wdeslo = this.desdes;
				this.ain75 = false;
				this.fecaux = 0;
				this.mamini = "2";
				//SubRutSlinel04(ds); faltan parametros de Zrspmlr
			}
			this.wtctal = this.wtctal.add(this.wtotar);
			this.wmone = "PES";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel27(DataSet ds, String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO27");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(this.w1desc);
			strx.setTXIMPO(this.totcuo);
			strx.setTXNCUO(this.w1ncuo);
			strx.setTXCACU(this.w1cacu);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel25(DataSet ds, BigDecimal MLCRCP,
			String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO25");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC(" VALOR TOTAL DE LA COMPRA:");
			strx.setTXIMPO(this.wtimp1);
			strx.setTXIORG(MLCRCP);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel26(DataSet ds, BigDecimal MLIMPO,
			String MLPIFG,
			String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO26");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC(" GS. OTORGAMIENTO (");
			strx.setTXIMPO(MLIMPO);
			//strx.setTXTEFM(MLPIFG); //NO EXISTE EN TABLA
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel28(DataSet ds, BigDecimal MLCAP,
			BigDecimal MLINTE,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO28");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC(" (*)CITIBANK CAPITAL ");
			strx.setTXIMPO(MLCAP);
			strx.setTXIORG(MLINTE);
			//strx.setTXREFC(DESCPC);//NO EXISTE CAMPO
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel29(DataSet ds, BigDecimal MLIMPO,
			String MLMINI,
			String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO29");
			strx.setTXMINI(MLMINI);
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC("IMPUESTO DE SELLOS");
			strx.setTXIMPO(MLIMPO);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel23(DataSet ds, String MLDE40,
			BigDecimal MLIMPO,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO23");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(MLDE40);
			strx.setTXIMPO(MLIMPO);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel36(DataSet ds,Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO36");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			this.sindl = new Boolean[5];
			sindl[2] = this.ain73;
			sindl[3] = this.ain74;
			SubRutSgetfmtnbr(ds);
			strx.setTXFMT(strx.getTXFMT().trim() + "#" + this.sssnbr);
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(this.w1desc);
			
			if (this.ain74 == true) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.totcu2);
			strx.setTXNCUO(this.w1ncuo);
			strx.setTXCACU(this.w1cacu);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel37(DataSet ds, BigDecimal MLIMPO,
			String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO37");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			if (!this.wsadef.equals("")) {
				strx.setTXDESC(this.wsadef);
			}else {
				strx.setTXDESC("-CAPITAL");
			}
			strx.setTXIMPO(MLIMPO);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel38(DataSet ds, BigDecimal MLIMPO,
			BigDecimal MLTEFM,
			BigDecimal MLTNOA,
			String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO38");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			if (!this.wsinef.equals("")) {
				strx.setTXDESC(this.wsinef);
			}else {
				strx.setTXDESC("-INTERES");
			}
			strx.setTXIMPO(MLIMPO);
			strx.setTXTEFM(MLTEFM);
			strx.setTXTNOA(MLTNOA);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel30(DataSet ds, String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO30");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC("- ARANCEL POR USO DE BANELCO");
			strx.setTXIMPO(this.alimpo);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
		
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAlzon3(DataSet ds) {
		try {
			this.wlimpo = BigDecimal.ZERO;
			ListZrspmlr = myDAOZrspmlr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedict(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), "MERACT", "MEDICT", "3");
			for (Zrspmlr o : ListZrspmlr) {//Por cada registro seleccionado
				if (o.getMlcori() == 24 && o.getMlcmov() == 39 || o.getMlcori() == 9 && o.getMlcmov() == 39 ) {
					//SFMT
					//ELIMINAR
				}
				if (o.getMlncuo() != 0) {
					this.aama12 = "";
					this.aama12 = "CUOTA";
					this.aancuo = o.getMlncuo();
					this.aacacu = o.getMlcacu();
					this.aama12 = this.aama12 +" " + this.aancuo.toString() +"/" + this.aacacu.toString();
					// this.mlde40 = this.aama12;
				}
				if (o.getMlcori() == 11 && o.getMlcmov() == 30) {
					this.wtctal = this.wtctal.add(o.getMlimpo());
					this.ain15 = true;
					if (fc.BigDecimalComparar(o.getMlimpo().toString(), "0", "!=")) {
						this.wsttem = BigDecimal.ZERO;
						SubRutSlinel40(ds, o.getMlcrcp(), o.getMlnlcc(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
					}
				}
				if (o.getMlcori() == 11 && o.getMlcmov() == 31) {
					this.wsinfi = o.getMlde40();
					if (o.getMlcsmv() != 0) {
						if (!this.wacinb.equals("")) {
							this.wlimp1 = this.wlimp1.add(o.getMlimpo());
						}else {
							this.wlimpa = this.wlimpa.add(o.getMlimpo());
						}
						this.wtctal = this.wtctal.add(o.getMlimpo());
					}else {
						this.wlimpo = this.wlimpo.add(o.getMlimpo());
						this.wtctal = this.wtctal.add(o.getMlimpo());
						this.ain15 = true;
						if (fc.BigDecimalComparar(this.wlimpo.toString(), "=", "!=") || fc.BigDecimalComparar(this.wlimp1.toString(), "0", "!=")) {
							if (fc.BigDecimalComparar(this.wlimp1.toString(), "0", "!=")) {
								this.ain79 = true;
							}
							SubRutSlinel41(ds, o.getMlcrcp(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlncrd());
							this.ain79 = false;
							this.wlimpo = BigDecimal.ZERO;
							this.wlimp1 = BigDecimal.ZERO;
						}
					}
					if (fc.BigDecimalComparar(this.wlimpa.toString(), "0", "!=")) {
						SubRutSlinel43(ds, o.getMlnlcc(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						this.wlimpa = BigDecimal.ZERO;
					}
				}
				if (o.getMlcori() == 9 && o.getMlcmov() == 31 && o.getMlcsmv() == 0) {
					this.wlimpa = this.wlimpa.add(o.getMlimpo());
					this.wtctal = this.wtctal.add(o.getMlimpo());
					SubRutSlinel44(ds, o.getMlnlcc(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());     
					this.wlimpa = BigDecimal.ZERO;
				}
				if (o.getMlcori() == 11 && o.getMlcmov() == 32) {
					this.totses = this.totses.add(o.getMlimpo());
				}
				if (o.getMlcori() == 11 && o.getMlcmov() == 33) {
					this.wtctal = this.wtctal.add(o.getMlimpo());
					this.ain15 = true;
					SubRutSlinel29(ds, o.getMlimpo(), o.getMlmini(), o.getMlxtrf(), o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
				}
				if (true) { //o.getMlfecu() != "" && o.getMlfecu() != ""
					this.fecadd = o.getMldmov();
					this.fecamm = o.getMlmmov();
					this.fecaa1 = o.getMlymov();
					this.fecaa2 = o.getMlamov();
				}else {
					this.fecaux = 0;
				}
				this.ain73 = false;
				this.ain74 = false;
				this.wreflo = "";
				this.wrefe = "";
				this.wwdrfn = "";
				this.wwmrch = 0;
				this.wmone = "";
				this.wreflo = o.getMlncab() + o.getMlnlcc().toString();
				this.wrefe = this.agrefe;
				this.wwdrfn = o.getMlxtrf();
				//this.w1mrch = registro ZRSPMLR
				if (this.w1mrch != 0 && this.w1mrch != null) {
					this.wwmrch = this.w1mrch;
				}
				if (o.getMlcori() == 24 && o.getMlcmov() == 38 || o.getMlcori() == 9 && o.getMlcmov() == 38) {
					this.wtotar = this.wtotar.add(o.getMlimpo());
					this.wtctal = this.wtctal.add(o.getMlimpo());
					if (o.getMlncuo() == 1 && o.getMlcori() == 24) {
						this.ascuol = o.getMlcacu();
						this.ascaol = o.getMlimpo().add(o.getMlcrcp());
					}
					this.agsini = "";
					if (this.sstmhdr.getMecacl() == "B" ) {//////////////////////////////////////// > B
						this.totcu2 = o.getMlimpo();
						this.w1desc = o.getMlde40();
						this.w1ncuo = o.getMlncuo();
						this.w1cacu = o.getMlcacu();
						if (o.getMlcsmv() == 3) {
							this.ain73 = true;
							SubRutSlinel39(ds, o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
						}
					}
				}
				if (o.getMlcori() == 24 && o.getMlcmov() == 39 || o.getMlcori() == 9 && o.getMlcmov() == 39) {
					if (this.w1desc.equals("")) {
						this.w1desc = o.getMlde40();
					}
					this.wtotar = this.wtotar.add(o.getMlimpo());
					this.wtctal = this.wtctal.add(o.getMlimpo());
					this.totcu2 = this.totcu2.add(o.getMlimpo());
					if (o.getMlcsmv() == 9) {
						this.ain73 = false;
						this.ain74 = true;
					}else {
						this.ain73 = true;
					}
					if (this.wconfi =="B") { // this.wconfi > 'B'
						SubRutSlinel39(ds, o.getMlcori(), o.getMlcmov(), o.getMlcsmv(), o.getMlncrd());
					}
				}
				if (o.getMlcori() == 24 && o.getMlcmov() == 40) {
					this.totses = this.totses.add(o.getMlimpo());
				}
				if (o.getMlcori() == 24 && o.getMlcmov() == 41 || o.getMlcori() == 24 && o.getMlcmov() == 42 && o.getMlcsmv() == 9) {
					this.totses = this.totses.add(o.getMlimpo());
				}
				if (o.getMlcori() == 1 && o.getMlcmov() == 43 || o.getMlcori() == 24 && o.getMlcmov() == 43) {
					this.totses = this.totses.add(o.getMlimpo());
				}
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel40(DataSet ds, BigDecimal MLCRCP, 
			Integer MLNLCC, 
			String MLXTRF, 
			Integer MLCORI, 
			Integer MLCMOV, 
			Integer MLCSMV, 
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO40");
			strx.setTXMINI("3");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC("- CAPITAL FINANCIADO AL CIERRE");
			strx.setTXIORG(MLCRCP);
			strx.setTXTEFM(this.wsttem);
			strx.setTXNLCC(MLNLCC);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel43(DataSet ds, Integer MLNLCC, 
			String MLXTRF, 
			Integer MLCORI, 
			Integer MLCMOV, 
			Integer MLCSMV, 
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO43");
			strx.setTXMINI("3");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC("- INT. BONIFICABLES (PERCIBIDOS)");
			strx.setTXIMPO(this.wlimpa);
			strx.setTXNLCC(MLNLCC);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel44(DataSet ds, Integer MLNLCC, 
			String MLXTRF, 
			Integer MLCORI, 
			Integer MLCMOV, 
			Integer MLCSMV, 
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO44");
			strx.setTXMINI("3");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC("INT. BONIFICABLES (PERCIBIDOS)");
			strx.setTXIMPO(this.wlimpa);
			strx.setTXNLCC(MLNLCC);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}
	
	private String SubRutSlinel39(DataSet ds,Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("CLIN39");
			strx.setTXMINI("3");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			this.sindl = new Boolean[5];
			sindl[2] = this.ain73;
			sindl[3] = this.ain74;
			SubRutSgetfmtnbr(ds);
			strx.setTXFMT(strx.getTXFMT().trim() + "#" + this.sssnbr);
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(this.w1desc);
			
			if (this.ain74) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.totcu2);
			strx.setTXNCUO(this.w1ncuo);
			strx.setTXCACU(this.w1cacu);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinel42(DataSet ds, String MLXTRF,
			Integer MLCORI,
			Integer MLCMOV,
			Integer MLCSMV,
			String MLNCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO42");
			strx.setTXMINI("3");
			strx.setTXFILE("S");
			strx.setTXLOIN("L");
			strx.setTXDESC("GTS. ADM. Y SEGURO DE VIDA TOTAL");
			strx.setTXIMPO(this.totses);
			strx.setTXREFC(MLXTRF);
			strx.setTXCORI(MLCORI);
			strx.setTXCMOV(MLCMOV);
			strx.setTXCSMV(MLCSMV);
			strx.setTXCANB(MLNCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			this.totses = BigDecimal.ZERO;
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	public String SubRutSlinepir(DataSet ds, BigDecimal TIIMPO,
			Integer TICORI,
			Integer TICMOV,
			Integer TICSMV,
			String TINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN02");
			strx.setTXMINI("1");
			strx.setTXFILE("P");
			strx.setTXLOIN("I");
			//strx.setTXFMT(strx.getTXFMT().trim() + "R");
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(this.wdesli);
			strx.setTXIMPO(TIIMPO);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(TICORI);
			strx.setTXCMOV(TICMOV);
			strx.setTXCSMV(TICSMV);
			strx.setTXCANB(TINCRD);
			if ( fc.ValidarDdmmAAAA(this.sw0106.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(sw0106.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	public String SubRutSlineiir(DataSet ds, String IIXTRF, 
			Integer IICORI, 
			Integer IICMOV, 
			Integer IICSMV, 
			String IINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN03");
			strx.setTXMINI("1");
			strx.setTXFILE("I");
			strx.setTXLOIN("I");
			strx.setTXDESC("INTERESES POR ATRASOS");
			strx.setTXIMPO(this.wtotin);
			strx.setTXREFC(IIXTRF);
			strx.setTXTEFM(this.wstpin);
			strx.setTXCORI(IICORI);
			strx.setTXCMOV(IICMOV);
			strx.setTXCSMV(IICSMV);
			strx.setTXCANB(IINCRD);
			if ( fc.ValidarDdmmAAAA(this.resp05.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(resp05.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	public String SubRutAizon1(DataSet ds) {
		try {
			this.ain75 = false;
			ListZrspmir = myDAOZrspmir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedictAndMimini(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), "MERACT", "MEDICT", "1");
			for (Zrspmir o : ListZrspmir) {
				this.wtmoz1 = BigDecimal.ZERO;
				this.wtmoz3 = BigDecimal.ZERO;
				this.whcrc3 = BigDecimal.ZERO;
				this.wiimpo = BigDecimal.ZERO;
				//this.digant = o.getMidita();
				//this.mxmota = o.getMimota();
				//this.ordant = o.getMiubir();
				//this.aacanb = o.getMincrd();
				this.wsttem = BigDecimal.ZERO;
				for (int j = 0; j < EMPTY_STRING_ARRAY.length; j++) {
					this.soldmir = this.sactmi;
					this.wreflo = "";
					this.wrefe = "";
					this.wwdrfn = "";
					this.wdeslo = "";
					this.drcmon = "002";
					//this.drcori = o.getMicori();
					//this.drcmov = o.getMicmov();
					//this.drcsmv = o.getMicsmv();
					if (Arrays.asList(mos).contains(this.wclmov)) {
						if (o.getMicori() == 9 && o.getMicmov() == 5 && o.getMicsmv() == 1) {
							this.wtmoz3 = this.wtmoz3.add(o.getMiimpo());
							this.whcrc3 = this.whcrc3.add(o.getMiiorg());
							this.wclmo3 = this.wclmov;
						}else {
							if (o.getMicori() == 11 && o.getMicmov() == 31 && o.getMicsmv() == 1) {
								this.wiimpo = this.wiimpo.add(o.getMiimpo());
								this.wsinfi = o.getMide40();
								if ( fc.BigDecimalComparar(o.getMitefm().toString(), "0", "!=") ) {
									this.wsttem = o.getMitefm();
								}
							}else {
								if (o.getMicori() == 9 && o.getMicmov() == 5) {
									if (o.getMicsmv() == 10)
										this.z1 = 1;
									else if (o.getMicsmv() == 11) 
										this.z1 = 2;
									else if (o.getMicsmv() == 12) 
										this.z1 = 3;
									else if (o.getMicsmv() == 13) 
										this.z1 = 4;
									else if (o.getMicsmv() == 14) 
										this.z1 = 5;
									else if (o.getMicsmv() == 15) 
										this.z1 = 7;
									else
										this.z1 = 8;
									
									this.wt1[this.z1] = (new BigDecimal(wt1[this.z1]).add(o.getMiimpo())).toString();
									this.wh1[this.z1] = (new BigDecimal(wh1[this.z1]).add(o.getMicrcp())).toString();
									this.wc1[this.z1] = this.wclmov;
								}
							}
						}
						
						if (o.getMincuo() != 0) {
							this.aama12 = "";
							this.aama12 = "CUOTA";
							this.aancuo = o.getMincuo();
							this.aacacu = o.getMicacu();
							this.aama12 = this.aama12 + " " + this.aancuo + "/" + this.aacacu;
							this.mide40 = this.aama12;
						}
						if (o.getMicori() == 9 && o.getMicmov() == 5) {
							if (o.getMicsmv() == 11 || o.getMicsmv() == 12 || o.getMicsmv() == 13 || o.getMicsmv() == 14 || o.getMicsmv() == 91 || o.getMicsmv() == 92 || o.getMicsmv() == 93 || o.getMicsmv() == 94) {
								this.ain75 = true;
							}
						}
					}else {
						
						if (o.getMicmov() == 60 && o.getMicsmv() != 0) {
							this.wdeslo = "";
							SubRutAdesmo(ds);
							this.mifecu = "";
						}else {
							this.wdeslo = o.getMide40();
							this.mifecu = "";
						}
						this.wimplo = o.getMiimpo();
						this.wtctai = this.wtctai.add(o.getMiimpo());
						if (o.getMicori() == 9 && o.getMicmov() == 5) {
							if (o.getMicsmv() == 11 || o.getMicsmv() == 12 || o.getMicsmv() == 13 || o.getMicsmv() == 14 || o.getMicsmv() == 91 || o.getMicsmv() == 92 || o.getMicsmv() == 93 || o.getMicsmv() == 94) {
								this.ain75 = true;
							}
						}
						this.fecaux = 0;
						SubRutSlinei04(ds, o.getMimini(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
						this.ain75 = false;
						this.wicrcp = BigDecimal.ZERO;
					}
				}
				for (int z1 = 1; z1 < 10; z1++) {
					this.wtmoz1 = new BigDecimal(this.wt1[z1]);
					this.wicrcp = new BigDecimal(this.wh1[z1]);
					this.wclmo1 = this.wc1[z1];
					if ( fc.BigDecimalComparar(this.wtmoz1.toString(), "0", "!=")) {
						this.wreflo = "";
						this.wrefe = "";
						this.wwdrfn = "";
						this.wwmrch = 0;
						this.wimplo = this.wtmoz1;
						this.wtctai = this.wtctai.add(this.wtmoz1);
						this.wdeslo = "";
						this.wclmov = wclmo1;
						SubRutAdesmo(ds);
						this.fecaux = 0;
						SubRutSlinei04s(ds, o.getMimini(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
						this.ain75 = false;
						this.wicrcp = BigDecimal.ZERO;
						this.wtmoz1 = BigDecimal.ZERO;
					}
				}
				if (fc.BigDecimalComparar(this.wtmoz3.toString(), "0", "!=")) {
					this.wreflo = "";
					this.wrefe = "";
					this.wwdrfn = "";
					this.wwmrch = 0;
					this.wimplo = this.wtmoz3;
					this.whcrcp = this.whcrc3;
					this.wtctai = this.wtctai.add(this.wtmoz3);
					this.wdeslo = "";
					this.wclmov = this.wclmo3;
					SubRutAdesmo(ds);
					this.fecaux = 0;
					this.mamini = "1";
					SubRutSlinei04s(ds, o.getMimini(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
					this.ain75 = false;
					this.whcrc3 = BigDecimal.ZERO;
					this.wicrcp = BigDecimal.ZERO;
					this.wtmoz3 = BigDecimal.ZERO;
				}
				if (fc.BigDecimalComparar(this.wiimpo.toString(), "0", "==")) {
					this.ain79 = false;
					this.wtctai = this.wtctai.add(this.wiimpo);
					SubRutSlinei41(ds, o.getMicrcp(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMincrd());
					this.wiimpo = BigDecimal.ZERO;
				}
			}
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinei04(DataSet ds, String MIMINI, Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN04");
			strx.setTXMINI(MIMINI);
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			this.sindl = new Boolean[5];
			sindl[4] = this.ain75;
			SubRutSgetfmtnbr(ds);
			strx.setTXFMT(strx.getTXFMT().trim() + "#" + this.sssnbr);
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(this.wdeslo);
			strx.setTXIMPO(this.wimplo);
			strx.setTXIORG(this.wicrcp);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinei04s(DataSet ds, String MIMINI,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN04");
			strx.setTXMINI(MIMINI);
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			this.sindl = new Boolean[5];
			sindl[4] = this.ain75;
			SubRutSgetfmtnbr(ds);
			strx.setTXFMT(strx.getTXFMT().trim() + "#" + this.sssnbr);
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(this.wdeslo);
			strx.setTXIMPO(this.wimplo);
			strx.setTXIORG(this.wicrcp);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			
			SubRutAnewentry(ds);
			this.sactmi = this.soldmir;
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAprtpesii(DataSet ds, String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV) {
		try {
			if (fc.BigDecimalComparar(this.wsu101.toString(), "0", "!=")) {
				this.fecaux = this.fcu101;
				this.wdesl1 = this.wau101;
				this.ws6000 = this.wsu101;
				this.wsu101 = BigDecimal.ZERO;
			}
			if (fc.BigDecimalComparar(this.wsu001.toString(), "0", "!=")) {
				this.fecaux = this.fcu001;
				this.wdesl1 = this.wau001;
				this.ws6000 = this.wsu001;
				SubRutSlinei6a(ds, MIXTRF, MICORI, MICMOV, MICSMV);
				this.wsu001 = BigDecimal.ZERO;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinei6a (DataSet ds, String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO6A");
			strx.setTXMINI("1");
			strx.setTXFILE("S");
			strx.setTXLOIN("I");
			strx.setTXDESC(this.wdesl1);
			strx.setTXIMPO(this.ws6000);
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			//strx.setTXFMOV(0);
			this.sqmov = 2;
			SubRutAnewentry(ds);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}

	private String SubRutAizon2(DataSet ds) {
		try {
			ListZrspmir = myDAOZrspmir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedictAndMimini(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), "MERACT", "MEDICT", "2");
			for (Zrspmir o : ListZrspmir) {
				this.wtotar = BigDecimal.ZERO;
				this.micant = "";
				//this.digant = o.getMidita(); //no existe
				//this.mxmota = o.getMimota(); //no existe
				this.aacanb = o.getMincrd();
				this.totdes = BigDecimal.ZERO;        
				this.desdes = "";        
				this.ttfecu = "";
				for (int i = 0; i < EMPTY_STRING_ARRAY.length; i++) {//Por cada registro seleccionado y MINCRD = @@CANB
					this.ain75 = false;
					this.ain74 = false;
					this.ain73 = false;
					this.ain71= false;
					if (o.getMicori() == 0 && o.getMicmov() == 57 || o.getMicori() == 9 && o.getMicmov() == 57 || o.getMicori() == 9 && o.getMicmov() == 59 || o.getMicori() == 9 && o.getMicmov() == 53 || o.getMicori() == 24 && o.getMicmov() == 57) {
						//FMT
						//FIN FMT
					}
					if (o.getMincuo() != 0) {
						this.aama12 = "";        
						this.aama12 = " CUOTA";
						this.aancuo = o.getMincuo();
						this.aacacu = o.getMicacu();
						this.aama12 = this.aama12 + "  " + this.aancuo + "/" + this.aacacu;
						this.mide40 = this.aama12;
					}
					this.ain15 = false;
					this.wreflo = "";
					this.wrefe = "";
					this.wwdrfn = "";
					this.wwmrch = 0;
					this.wdeslo = "";
					this.kyfr00 = "";
					this.wreflo = this.winca1.toString() + " ";
					this.wwdrfn = o.getMixtrf();
					//W1MRCH = registro actual de ZRSPMIR
					if (true && o.getMicori() != 6) {//this.w1mrch  != 0 && this.w1mrch != Blancos && o.getMicori != 6
						this.wwmrch = this.w1mrch;
					}
					if (!this.wwdrfn.equals("")) {
						if (o.getMicori() == 1 || o.getMicori() == 24 || o.getMicori() == 5 || o.getMicori() == 6 || o.getMicori() == 9 && o.getMicmov() == 38 || o.getMicori() == 9 && o.getMicmov() == 39 || o.getMicori() == 9 && o.getMicmov() == 36 || o.getMicori() == 9 && o.getMicmov() == 37 || o.getMicori() == 9 && o.getMicmov() == 58 || o.getMicori() == 9 && o.getMicmov() == 59 || o.getMicori() == 9 && o.getMicmov() == 56 || o.getMicori() == 9 && o.getMicmov() == 57 || o.getMicori() == 12) {
							this.wrefe = this.agrefe;
						}
					}
					this.wtotar = this.wtotar.add(o.getMiimpo());
					this.wimplo = o.getMiimpo();
					this.wdeslo = this.mide40;
					if (o.getMicmov() != 57) {
						this.wdespa = "";
					}
					this.wimcbo = o.getMiiorg();
					this.y = 1;
					//Buscar el valor de MIMHAB en la serie MON, y mover el índice de dicho valor a Y
					if (true) {//existe la serie
						this.wmone = this.moe[y];
					}
					this.wdespa = "";
					this.aadosf = o.getMixtrf();
					//Limpiar la variable MICFAR //NO EXISTE VERIFICAR
					//MICFAR = AADOSF
					//KYFR00 = MICFAR
					if (true) {//Si MICFAR != MICANT
						//MICANT = MICFAR
						this.y = 1;
						//Buscar el valor de MICFAR en la serie PAI, y mover el índice a la variable Y
						if (true) { //si existe
							//this.wdespa = this.pad[y];
						}else {
							ObjZvrpfrq = myDAOZvrpfrq.getUsingTxcfar(ds, this.kyfr00);
							if (ObjZvrpfrq != null) {
								this.y = 1;
								//Buscar en valor Blanco en la serie PAI y mover índice de dicho valor a Y
								if (true) {//si existe
									//this.pai[y] = MICFAR;
									//this.pad[y] = FR9969;
									//this.wdespa = pad[y];
								}
							}
						}
					}
					if (!this.wconfi.equals("B")) {
						this.ain73 = false;
						this.ain74 = false;
						this.ain75 = false;
						this.wwdrfn = o.getMixtrf();
						if (true && o.getMicori() != 6) {//this.w1mrch != 0 && this.w1mrch != Blancos && o.getMicori() != 6
							this.wwmrch = this.w1mrch;
						}
						if (o.getMicori() == 23 && o.getMicmov() == 35) {
							this.wreflo = "";
							this.wrefe = "";
							this.wwdrfn = "";
							this.wwmrch = 0;
							this.wwdrfn = o.getMixtrf();
							if (true && o.getMicori() != 6) {//this.w1mrch != 0 && this.w1mrch != Blancos && o.getMicori() != 6
								this.wwmrch = this.w1mrch;
							}
							this.wreflo = o.getMincab();							
						}
						if (o.getMicmov() == 40 && o.getMicori() == 1) {
							this.wreflo = o.getMincup().toString() + o.getMizoes();
							this.wreflq = o.getMirues() + o.getMicoes();
							if (!o.getMincab().equals("")) {
								if (true) { //MICOBO = BOCAD
									this.wreflw = o.getMisues() + o.getMimoes();
									//this.wreflv = o.getMifecu();
								}else {
									//this.wreflz = this.wincab;
									this.wreflr = o.getMincaf();
								}
							}
						}
						if (o.getMicori() == 1 || o.getMicori() == 5 || o.getMicori() == 8 || o.getMicori() == 9 || o.getMicori() == 24) {
							this.wreflo = o.getMincup().toString() + o.getMizoes();
							this.wreflq = o.getMirues() + o.getMicoes();
							if (!o.getMincab().equals("")) {
								if (true) { //MICOBO = BOCAD
									this.wreflw = o.getMisues() + o.getMimoes();
									//this.wreflv = o.getMifecu();
								}else {
									//this.wreflz = this.wincab;
									if (o.getMicori() == 1 && o.getMicmov() == 40 && o.getMizoes() == 1 && o.getMirues() == 18) {
										if (o.getMirefc().equals("") || o.getMirefc() == "0") {
											this.wreflr = o.getMincaf();
										}else {
											this.wreflo = " ";
											this.wreflr = 0; 
											if (o.getMincuo() != 0) {
												this.ain73 = true;
												if (o.getMicsmv() == 66) {
													this.ain71 = true;
												}
											}
											//this.wreflo = this.wreflo + " " + this.agpoli + " " + this.agrama + " " + this.agendo;
										}
									}else {
										this.wreflr = o.getMincaf();
									}
								}
							}
						}
						if (o.getMicmov() == 52) {
							if (o.getMicori() == 1 || o.getMicori() == 5 || o.getMicori() == 8 || o.getMicori() == 9) {
								this.wreflo = o.getMincup().toString() + o.getMizoes();
								this.wreflq = o.getMirues() + o.getMicoes();
								if (!o.getMincab().equals("")) {
									if (true) { //MICOBO = BOCAD
										this.wreflw = o.getMisues() + o.getMimoes();
										//this.wreflv = o.getMifecu();
									}else {
										//this.wreflz = this.wincab;
										this.wreflr = o.getMincaf();
									}
								}
								this.ain73 = true;
							}
							if (o.getMicori() == 5 && o.getMicsmv() == 9) {
								this.ain73 = false;
								this.ain74 = true;
							}
						}
						if (o.getMicori() == 12 && o.getMicmov() == 20) {
							this.wreflo = "";
						}
						if (!this.wwdrfn.equals("")) {
							if (o.getMicori() == 1 || o.getMicori() == 24 || o.getMicori() == 5 || o.getMicori() == 6 || o.getMicori() == 9 && o.getMicmov() == 38 || o.getMicori() == 9 && o.getMicmov() == 39 || o.getMicori() == 9 && o.getMicmov() == 36 || o.getMicori() == 9 && o.getMicmov() == 37 || o.getMicori() == 9 && o.getMicmov() == 58 || o.getMicori() == 9 && o.getMicmov() == 59 || o.getMicori() == 9 && o.getMicmov() == 56 || o.getMicori() == 9 && o.getMicmov() == 57 || o.getMicori() == 12) {
								this.wrefe = this.agrefe;
							}
						}
						if (o.getMicori() == 1 && o.getMicmov() == 25) {
							this.wreflo = o.getMincup().toString() + o.getMizoes();
							this.wreflq = o.getMirues() + o.getMicoes();
							if (!o.getMincab().equals("")) {
								if (true) { //MICOBO = BOCAD
									this.wreflw = o.getMisues() + o.getMimoes();
									//this.wreflv = o.getMifecu();
								}else {
									//this.wreflz = this.wincab;
									this.wreflr = o.getMincaf();
								}
							}
							this.wwdrfn = o.getMixtrf();
							if (true && o.getMicori() != 6) {//this.w1mrch != 0 && this.w1mrch != Blancos && o.getMicori() != 6
								this.wwmrch = this.w1mrch;
							}
							if (!this.wwdrfn.equals("")) {
								if (o.getMicori() == 1 || o.getMicori() == 24 || o.getMicori() == 5 || o.getMicori() == 6 || o.getMicori() == 9 && o.getMicmov() == 38 || o.getMicori() == 9 && o.getMicmov() == 39 || o.getMicori() == 9 && o.getMicmov() == 36 || o.getMicori() == 9 && o.getMicmov() == 37 || o.getMicori() == 9 && o.getMicmov() == 58 || o.getMicori() == 9 && o.getMicmov() == 59 || o.getMicori() == 9 && o.getMicmov() == 56 || o.getMicori() == 9 && o.getMicmov() == 57 || o.getMicori() == 12) {
									this.wrefe = this.agrefe;
								}
							}
							this.wtimp1 = o.getMicrcp().add(o.getMiimpo());
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								if (true) { // o.getMifecu() != Blancos y o.getMifecu() != Blancos
									this.fecadd = o.getMidcup();
									this.fecamm = o.getMimcup();
									this.fecaa1 = o.getMiccup();
									this.fecaa2 = o.getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei27(ds, this.mide40,o.getMiimpo(), o.getMincuo(), o.getMicacu(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
								SubRutSlinei25(ds, o.getMicrcp(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
							}
						}
						if (o.getMicori() == 1 && o.getMicmov() == 26) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								SubRutSlinei26(ds, o.getMiimpo(), "o.getMipifg()", o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
							}
						}
						if (o.getMicori() == 4 && o.getMicmov() == 27) {
							this.wreflo = o.getMizoes().toString();
							this.wreflq = o.getMirues() + o.getMicoes();
							this.wwdrfn = o.getMixtrf();
							if (true && o.getMicori() != 6) {//this.w1mrch != 0 && this.w1mrch != Blancos && o.getMicori() != 6
								this.wwmrch = this.w1mrch;
							}
							if (!o.getMincab().equals("")) {
								if (true) { //MICOBO = BOCAD
									this.wreflw = o.getMisues() + o.getMimoes();
									//this.wreflv = o.getMifecu();
								}else {
									//this.wreflz = this.wincab;
									this.wreflr = o.getMincaf();
								}
							}
							if (o.getMicori() == 12 && o.getMicmov() == 20) {
								this.wreflo = "";
							}
							this.wwdrfn = o.getMixtrf();
							if (true && o.getMicori() != 6) {//this.w1mrch != 0 && this.w1mrch != Blancos && o.getMicori() != 6
								this.wwmrch = this.w1mrch;
							}
							if (!this.wwdrfn.equals("")) {
								if (o.getMicori() == 1 || o.getMicori() == 24 || o.getMicori() == 5 || o.getMicori() == 6 || o.getMicori() == 9 && o.getMicmov() == 38 || o.getMicori() == 9 && o.getMicmov() == 39 || o.getMicori() == 9 && o.getMicmov() == 36 || o.getMicori() == 9 && o.getMicmov() == 37 || o.getMicori() == 9 && o.getMicmov() == 58 || o.getMicori() == 9 && o.getMicmov() == 59 || o.getMicori() == 9 && o.getMicmov() == 56 || o.getMicori() == 9 && o.getMicmov() == 57 || o.getMicori() == 12) {
									this.wrefe = this.agrefe;	
								}
							}
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								if (true) { // o.getMifecu() != Blancos y o.getMifecu() != Blancos
									this.fecadd = o.getMidcup();
									this.fecamm = o.getMimcup();
									this.fecaa1 = o.getMiccup();
									this.fecaa2 = o.getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei27(ds, this.mide40,o.getMiimpo(), o.getMincuo(), o.getMicacu(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
								this.wlcrcp = o.getMicrcp().add(o.getMiimpo());
							}
						}
						if (o.getMicori() == 4 && o.getMicmov() == 28) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								SubRutSlinei28(ds, o.getMiimpo(), o.getMitnoa(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
							}
						}
						if (o.getMicori() == 4 && o.getMicmov() == 29) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								SubRutSlinei29(ds, o.getMiimpo(), o.getMimini(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
							}
						}
						if (o.getMicori() == 1 && o.getMicmov() == 53) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								if (true) { // o.getMifecu() != Blancos y o.getMifecu() != Blancos
									this.fecadd = o.getMidcup();
									this.fecamm = o.getMimcup();
									this.fecaa1 = o.getMiccup();
									this.fecaa2 = o.getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei53(ds, this.mide40, o.getMiimpo(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd(), o.getMimhab(), o.getMicfar());
							}
						}
						if (o.getMicori() == 1 && o.getMicmov() == 54) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								if (true) { // o.getMifecu() != Blancos y o.getMifecu() != Blancos
									this.fecadd = o.getMidcup();
									this.fecamm = o.getMimcup();
									this.fecaa1 = o.getMiccup();
									this.fecaa2 = o.getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei53(ds, this.mide40, o.getMiimpo(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd(), o.getMimhab(), o.getMicfar());
							}
						}
						if (o.getMicori() == 1 && o.getMicmov() == 56 || o.getMicori() == 9 && o.getMicmov() == 56 || o.getMicori() == 9 && o.getMicmov() == 58 || o.getMicori() == 9 && o.getMicmov() == 52 || o.getMicori() == 24 && o.getMicmov() == 56) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								this.totcu2 = o.getMiimpo();
								this.totcui = o.getMiiorg();
								this.w1desc = this.mide40;
								this.w1ncuo = o.getMincuo();
								this.w1cacu = o.getMicacu();
								this.ain15 = true;
								if (o.getMicori() == 9 && o.getMicmov() == 56 && o.getMicsmv() == 0 || o.getMicori() == 9 && o.getMicmov() == 58 && o.getMicsmv() == 0 || o.getMicori() == 9 && o.getMicmov() == 52 && o.getMicsmv() == 0) {
									this.ain73 = false;
									this.ain74 = true;
								}
								if (o.getMicori() == 24 && o.getMicmov() == 56 && o.getMicsmv() == 9) {
									this.ain73 = false;
									this.ain74 = true;
								}else {
									this.ain73 = false;
								}
								if (!this.wconfi.equals("B")) {
									if (true) { // o.getMifecu() != Blancos y o.getMifecu() != Blancos
										this.fecadd = o.getMidcup();
										this.fecamm = o.getMimcup();
										this.fecaa1 = o.getMiccup();
										this.fecaa2 = o.getMiacup();
									}else {
										this.fecaux = 0;
									}
									SubRutSlinei56(ds, this.mide40, o.getMincuo(), o.getMicacu(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd(), o.getMimhab(), o.getMicfar());
									this.totcu2 = BigDecimal.ZERO;
								}
							}
						}
						if (o.getMicori() == 1 && o.getMicmov() == 57 || o.getMicori() == 9 && o.getMicmov() == 57 || o.getMicori() == 24 && o.getMicmov() == 57) {
							this.totcu2 = this.totcu2.add(o.getMiimpo());
							this.totcui = this.totcui.add(o.getMiiorg());
							this.wimcbo = this.totcui;
							this.ain15 = true;
							if (o.getMicori() == 9 && o.getMicmov() == 57 && o.getMicsmv() == 0) {
								this.ain73 = false;
								this.ain74 = true;
							}
							if (o.getMicori() == 24 && o.getMicmov() == 57 && o.getMicsmv() == 9) {
								this.ain73 = false;
								this.ain74 = true;
							}else {
								this.ain73 = false;
							}
							if (!this.wconfi.equals("B")) {
								if (true) { // o.getMifecu() != Blancos y o.getMifecu() != Blancos
									this.fecadd = o.getMidcup();
									this.fecamm = o.getMimcup();
									this.fecaa1 = o.getMiccup();
									this.fecaa2 = o.getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei56(ds, this.mide40, o.getMincuo(), o.getMicacu(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd(), o.getMimhab(), o.getMicfar());
								this.totcu2 = BigDecimal.ZERO;
							}
						}
						if (o.getMicori() == 1 && o.getMicmov() == 40 && o.getMicsmv() == 66) {
							this.ain15 = true;
							this.totdes = this.totdes.add(o.getMiimpo());
							this.desdes = this.mide40;
							//this.ttfecu = o.getMifecu();//no
						}
						if (this.ain15 = false) {
							if (true) { // o.getMifecu() != Blancos y o.getMifecu() != Blancos
								this.fecadd = o.getMidcup();
								this.fecamm = o.getMimcup();
								this.fecaa1 = o.getMiccup();
								this.fecaa2 = o.getMiacup();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinei05(ds, o.getMincuo(), o.getMicacu(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd(), o.getMimhab(), o.getMicfar());
						}
					}
				}
			}
			if (fc.BigDecimalComparar(this.totdes.toString(), "0", "!=")) {
				this.wimplo = this.totdes;
				this.wdeslo = this.desdes;
				this.fecaux = 0;
				//SubRutSlinei05(ds, o.getMincuo(), o.getMicacu(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd(), o.getMimhab(), o.getMicfar());	
			}
			this.wtctai = this.wtctai.add(this.wtotar);
			this.wmone = "USD";
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
		
	private String SubRutSlinei27(DataSet ds, String MIDE40,
			BigDecimal MIIMPO,
			Integer MINCUO,
			Integer MICACU,
			String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN27");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(MIDE40);
			strx.setTXIMPO(MIIMPO);
			strx.setTXNCUO(MINCUO);
			strx.setTXCACU(MICACU);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			
			SubRutAnewentry(ds);

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinei25(DataSet ds, BigDecimal MICRCP,
			String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN25");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			strx.setTXDESC(" VALOR TOTAL DE LA COMPRA:");
			strx.setTXIMPO(this.wtimp1);
			strx.setTXIORG(MICRCP);
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
		
	private String SubRutSlinei29(DataSet ds, BigDecimal MIIMPO,
			String MIMINI,
			String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN29");
			strx.setTXMINI(MIMINI);
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			strx.setTXDESC("IMPUESTO DE SELLOS");
			strx.setTXIMPO(MIIMPO);
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
		
	private String SubRutSlinei26(DataSet ds, BigDecimal MIIMPO,
			String MIPIFG,
			String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN26");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			strx.setTXDESC(" GS. OTORGAMIENTO (");
			strx.setTXIMPO(MIIMPO);
			//strx.setTXTEFM(MIPIFG); //NO EXISTE EN TABLA
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return ""; 
	}
			
	private String SubRutSlinei28(DataSet ds, BigDecimal MIIMPO,
			BigDecimal MITNOA,
			String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN28");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			strx.setTXDESC("(*)CITIBANK INT.(TNA");
			strx.setTXIMPO(MIIMPO);
			strx.setTXIORG(this.wlcrcp);
			strx.setTXTNOA(MITNOA);
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) { // fecpin
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinei53(DataSet ds, String MIDE40,
			BigDecimal MIIMPO,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD,
			Integer MIMHAB,
			String MICFAR) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN53");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(MIDE40);
			strx.setTXIMPO(MIIMPO);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			strx.setTXIORG(this.wimcbo);
			strx.setTXMHAB(MIMHAB);
			strx.setTXCFAR(MICFAR);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
		
	private String SubRutSlinei56(DataSet ds, String MIDE40,
			Integer MINCUO,
			Integer MICACU,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD,
			Integer MIMHAB,
			String MICFAR) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN56");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			this.sindl = new Boolean[5];
			sindl[2] = this.ain73;
			sindl[3] = this.ain74;
			SubRutSgetfmtnbr(ds);
			strx.setTXFMT(strx.getTXFMT().trim() + "#" + this.sssnbr);
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(MIDE40);
			
			if (this.ain74 == true) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.totcu2);
			strx.setTXNCUO(MINCUO);
			strx.setTXCACU(MICACU);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			strx.setTXIORG(this.wimcbo);
			strx.setTXMHAB(MIMHAB);
			strx.setTXCFAR(MICFAR);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
		
	private String SubRutSlinei05(DataSet ds, Integer MINCUO,
			Integer MICACU,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD,
			Integer MIMHAB,
			String MICFAR) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN05");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			this.sindl = new Boolean[5];
			sindl[1] = this.ain71;
			sindl[2] = this.ain73;
			sindl[3] = this.ain74;
			sindl[4] = this.ain75;
			SubRutSgetfmtnbr(ds);
			strx.setTXFMT(strx.getTXFMT().trim() + "#" + this.sssnbr);
			strx.setTXFMT(strx.getTXFMT().trim() + "R");
			strx.setTXDESC(this.wdeslo);
			
			if (this.ain74 == true) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.wimplo);
			strx.setTXNCUO(MINCUO);
			strx.setTXCACU(MICACU);
			strx.setTXREFC(this.wwdrfn);
			strx.setTXMRCH(this.wwmrch);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			strx.setTXIORG(this.wimcbo);
			strx.setTXMHAB(MIMHAB);
			strx.setTXCFAR(MICFAR);
			
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAizon3(DataSet ds) {
		try {
			this.wiimpo = BigDecimal.ZERO;
			ListZrspmir = myDAOZrspmir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedictAndMimini(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), "MERACT", "MEDICT", "3");
			
			for ( Zrspmir o : ListZrspmir) {
				if (o.getMincuo() != 0) {
					this.aama12 = "";
					this.aama12 = " CUOTA";
					this.aancuo = o.getMincuo();
					this.aacacu = o.getMicacu();
					this.aama12 = this.aama12 + " " + this.aancuo + "/" + this.aacacu;
					this.mide40 = this.aama12;
				}
				if (o.getMicori() == 11 && o.getMicmov() == 30) {
					this.wtctai = this.wtctai.add(o.getMiimpo());
					this.ain15 = true;
					if (fc.BigDecimalComparar(o.getMiimpo().toString(), "0", "!=")) {
						this.wsttem = BigDecimal.ZERO;
						SubRutSlinei40(ds, o.getMiimpo(), o.getMiiorg(), o.getMinlcc(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd()); 
					}
				}
				if (o.getMicori() == 11 && o.getMicmov() == 31) {
					this.wsinfi = this.mide40;
					if (o.getMicsmv() == 0) {
						if (this.wacinb.equals("")) {
							this.wiimp1 = this.wiimp1.add(o.getMiimpo());
						}else {
							this.wiimpa = this.wiimpa.add(o.getMiimpo());
						}
						this.wtctai = this.wtctai.add(o.getMiimpo());
					}else {
						this.wiimpo = this.wiimpo.add(o.getMiimpo());
						this.wtctai = this.wtctai.add(o.getMiimpo());
						this.ain15 = true;
						if (fc.BigDecimalComparar(this.wiimpo.toString(), "0", "!=") || fc.BigDecimalComparar(this.wiimp1.toString(), "0", "!=") ) {
							if (fc.BigDecimalComparar(this.wiimp1.toString(), "0", "!=")) {
								this.ain79 = true;
							}
							SubRutSlinei41(ds, o.getMicrcp(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMincrd());
							this.ain79 = false;
							this.wiimpo = BigDecimal.ZERO;
							this.wiimp1 = BigDecimal.ZERO;
						}
					}
					if (fc.BigDecimalComparar(this.wiimpa.toString(), "0", "!=")) {
						SubRutSlinei43(ds, o.getMinlcc(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
						this.wiimpa = BigDecimal.ZERO;
					}
				}
				if (o.getMicori() == 9 && o.getMicmov() == 31 && o.getMicsmv() == 0) {
					this.wiimpa = this.wiimpa.add(o.getMiimpo());
					this.wtctai = this.wtctai.add(o.getMiimpo());
					SubRutSlinei44(ds, o.getMinlcc(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());      
					this.wiimpa = BigDecimal.ZERO;
				}
				if (o.getMicori() == 11 && o.getMicmov() == 32) {
					this.totseu = this.totseu.add(o.getMiimpo());
				}
				if (o.getMicori() == 11 && o.getMicmov() == 33) {
					this.wtctai = this.wtctai.add(o.getMiimpo());
					this.ain15 = true;
					SubRutSlinei29(ds, o.getMiimpo(), o.getMimini(), o.getMixtrf(), o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
				}
				if (true) { // o.getMifecu() != Blancos y o.getMifecu() != Blancos
					this.fecadd = o.getMidcup();
					this.fecamm = o.getMimcup();
					this.fecaa1 = o.getMiccup();
					this.fecaa2 = o.getMiacup();
				}else {
					this.fecaux = 0;
				}
				this.ain73 = false;
				this.ain74 = false;
				this.wreflo = "";
				this.wrefe = "";
				this.wwdrfn = "";
				this.wwmrch = 0;
				this.wmone = "";
				this.wreflo = o.getMincab();
				this.wreflo = o.getMinlcc().toString();
				this.wrefe = this.agrefe;
				this.wwdrfn = o.getMixtrf();
				//W1MRCH = registro actual de ZRSPMIR
				if (true && o.getMicori() != 6) {//this.w1mrch != 0 && this.w1mrch != Blancos && o.getMicori() != 6
					this.wwmrch = this.w1mrch;
				}
				this.wmone = "USD";
				if (o.getMicori() == 24 && o.getMicmov() == 58 || o.getMicori() == 9 && o.getMicmov() == 58) {
					this.wtotar = this.wtotar.add(o.getMiimpo());
					this.wtctai = this.wtctai.add(o.getMiimpo());
					if (o.getMincuo() == 1 && o.getMicori() == 24) {
						this.aucuol = o.getMicacu();
						this.aucaol = o.getMiimpo().add(o.getMicrcp());
					}
					if (this.sstmhdr.getMecacl() == "B" ) {////////////////////////////////////////
						this.totcu2 = o.getMiimpo();
						this.w1desc = this.mide40;
						this.w1ncuo = o.getMincuo();
						this.w1cacu = o.getMicacu();
						if (o.getMicsmv() == 3) {
							this.ain73 = true;
							SubRutSlinel39(ds, o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
						}
					}
				}
				if (o.getMicori() == 24 && o.getMicmov() == 59 || o.getMicori() == 9 && o.getMicmov() == 59) {
					if (this.w1desc.equals("")) {
						this.w1desc = this.mide40;
					}
					this.wtotar = this.wtotar.add(o.getMiimpo());
					this.totcu2 = this.totcu2.add(o.getMiimpo());
					this.wtctai = this.wtctai.add(o.getMiimpo());
					if (o.getMicsmv() == 9) {
						this.ain73 = false;
						this.ain74 = true;
					}else {
						this.ain73 = true;
					}
					if (!this.wconfi.equals("B")) {
						SubRutSlinel39(ds, o.getMicori(), o.getMicmov(), o.getMicsmv(), o.getMincrd());
					}
				}
				if (o.getMicori() == 24 && o.getMicmov() == 60) {
					this.totseu = this.totseu.add(o.getMiimpo());
				}
				if (o.getMicori() == 24 && o.getMicmov() == 61 || o.getMicori() == 24 && o.getMicmov() == 62 && o.getMicsmv() == 9) {
					this.totseu = this.totseu.add(o.getMiimpo());
				}
				if (o.getMicori() == 1 && o.getMicmov() == 63 || o.getMicori() == 24 && o.getMicmov() == 63) {
					this.totseu = this.totseu.add(o.getMiimpo());
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	
	private String SubRutSlinei40(DataSet ds, BigDecimal MIIMPO,
			BigDecimal MIIOR,
			Integer MINLCC,
			String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN40");
			strx.setTXMINI("3");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			strx.setTXDESC("- CAPITAL FINANCIADO AL CIERRE");
			if (fc.BigDecimalComparar(MIIMPO.toString(), MIIOR.toString(), "==")) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CONVERSIÓN PESOS *");
			}
			strx.setTXIMPO(MIIMPO);
			strx.setTXTEFM(this.wsttem);
			strx.setTXNLCC(MINLCC);
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
		
	private String SubRutSlinei41(DataSet ds, BigDecimal MICRCP,
			String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN41SM0");
			strx.setTXMINI("3");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			if (!this.wsinfi.equals("")) {
				strx.setTXDESC("INTERESES SOBRE CAPITAL  ");
			}
			strx.setTXIMPO(this.wiimpo);
			strx.setTXIORG(MICRCP);
			strx.setTXTEFM(this.wsttem);
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(0);
			strx.setTXCANB(MINCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
			strx = new ZRSTEMMVSTRX();
			if (this.ain79) {
				strx.setTXFMT("DEIN41SM1");
				strx.setTXMINI("3");
				strx.setTXFILE("M");
				strx.setTXLOIN("I");
				strx.setTXDESC("- COSTO DE FINANCIAMIENTO    ");
				strx.setTXIMPO(this.wiimp1);
				strx.setTXREFC(MIXTRF);
				strx.setTXCORI(MICORI);
				strx.setTXCMOV(MICMOV);
				strx.setTXCSMV(1);
				strx.setTXCANB(MINCRD);
				if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
					strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
				} else {
					strx.setTXFMOVC(0);
				}
				SubRutAnewentry(ds);
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinei42(DataSet ds, String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN42");
			strx.setTXMINI("1");
			strx.setTXFILE("S");
			strx.setTXLOIN("I");
			strx.setTXDESC("GTS. ADM. Y SEGURO DE VIDA TOTAL");
			strx.setTXIMPO(this.totseu);
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			if ( fc.ValidarDdmmAAAA(this.mifecu.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(mifecu.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			this.totseu = BigDecimal.ZERO;
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinei43(DataSet ds, Integer MINLCC,
			String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN43");
			strx.setTXMINI("3");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			strx.setTXDESC("- INT. BONIFICABLES (PERCIBIDOS)");
			strx.setTXIMPO(this.wiimpa);
			strx.setTXNLCC(MINLCC);
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSlinei44(DataSet ds, Integer MINLCC,
			String MIXTRF,
			Integer MICORI,
			Integer MICMOV,
			Integer MICSMV,
			String MINCRD) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DEIN44");
			strx.setTXMINI("3");
			strx.setTXFILE("M");
			strx.setTXLOIN("I");
			strx.setTXDESC("INT. BONIFICABLES (PERCIBIDOS)");
			strx.setTXIMPO(this.wiimpa);
			strx.setTXNLCC(MINLCC);
			strx.setTXREFC(MIXTRF);
			strx.setTXCORI(MICORI);
			strx.setTXCMOV(MICMOV);
			strx.setTXCSMV(MICSMV);
			strx.setTXCANB(MINCRD);
			if ( fc.ValidarDdmmAAAA(this.fecaux.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecaux.toString())).toString()));
			} else {
				strx.setTXFMOVC(0);
			}
			SubRutAnewentry(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	
	
	
	
	private class ZRSTEMMVSTRX {
		String TXFMT = "";
		String TXMINI = "";
		String TXFILE = "";
		String TXLOIN = "";
		String TXDESC = "";
		BigDecimal TXIMPO = new BigDecimal(0);
		String TXREFC = "";
		Integer TXMRCH = 0;
		Integer TXCORI = 0;
		Integer TXCMOV = 0;
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
		public Integer getTXMRCH() {
			return TXMRCH;
		}
		public void setTXMRCH(Integer tXMRCH) {
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
	
	private class ZRSTEMMVLXREFP {
		
		//para registro ZRSPPIR
		Integer TINPTR = 0;
		Integer TINCAX = 0;
		Integer TICRDD = 0;
		Integer TICRMM = 0;
		Integer TICRAA = 0;
		
		//para registro ZRSPPLR
		Integer TLNPTR = 0;
		Integer TLNCAX = 0;
		Integer TLCRDD = 0;
		Integer TLCRMM = 0;
		Integer TLCRAA = 0;
		

		public Integer getTINPTR() {
			return TINPTR;
		}
		public void setTINPTR(Integer tINPTR) {
			TINPTR = tINPTR;
		}
		public Integer getTINCAX() {
			return TINCAX;
		}
		public void setTINCAX(Integer tINCAX) {
			TINCAX = tINCAX;
		}
		public Integer getTICRDD() {
			return TICRDD;
		}
		public void setTICRDD(Integer tICRDD) {
			TICRDD = tICRDD;
		}
		public Integer getTICRMM() {
			return TICRMM;
		}
		public void setTICRMM(Integer tICRMM) {
			TICRMM = tICRMM;
		}
		public Integer getTICRAA() {
			return TICRAA;
		}
		public void setTICRAA(Integer tICRAA) {
			TICRAA = tICRAA;
		}
		public Integer getTLNPTR() {
			return TLNPTR;
		}
		public void setTLNPTR(Integer tLNPTR) {
			TLNPTR = tLNPTR;
		}
		public Integer getTLNCAX() {
			return TLNCAX;
		}
		public void setTLNCAX(Integer tLNCAX) {
			TLNCAX = tLNCAX;
		}
		public Integer getTLCRDD() {
			return TLCRDD;
		}
		public void setTLCRDD(Integer tLCRDD) {
			TLCRDD = tLCRDD;
		}
		public Integer getTLCRMM() {
			return TLCRMM;
		}
		public void setTLCRMM(Integer tLCRMM) {
			TLCRMM = tLCRMM;
		}
		public Integer getTLCRAA() {
			return TLCRAA;
		}
		public void setTLCRAA(Integer tLCRAA) {
			TLCRAA = tLCRAA;
		}


		
	}
	
	private class ZRSTEMMVSFMT {
		String TXMINI = "";
		String TXCANB = "";
		String TXFILE = "";
		String TXLOIN = "";
		String TXFMT = "";
		Integer TXFMOV = 0;
		Integer TXCORI = 0;
		Integer TXCMOV = 0;
		Integer TXCSMV = 0;
		String TXDESC = "";
		BigDecimal TXIMPO = new BigDecimal(0);
		String TXREFC = "";
		BigDecimal TXTEFM = new BigDecimal(0);
		BigDecimal TXTNOA = new BigDecimal(0);
		Integer TXCACU = 0;
		Integer TXNCUO = 0;
		Integer TXNLCC = 0;
		BigDecimal TXIORG = new BigDecimal(0);
		String TXCFAR = "";
		Integer TXMHAB = 0;
		Integer TXMRCH = 0;
		
		
		public String getTXMINI() {
			return TXMINI;
		}
		public void setTXMINI(String tXMINI) {
			TXMINI = tXMINI;
		}
		public String getTXCANB() {
			return TXCANB;
		}
		public void setTXCANB(String tXCANB) {
			TXCANB = tXCANB;
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
		public String getTXFMT() {
			return TXFMT;
		}
		public void setTXFMT(String tXFMT) {
			TXFMT = tXFMT;
		}
		public Integer getTXFMOV() {
			return TXFMOV;
		}
		public void setTXFMOV(Integer tXFMOV) {
			TXFMOV = tXFMOV;
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
		public Integer getTXCSMV() {
			return TXCSMV;
		}
		public void setTXCSMV(Integer tXCSMV) {
			TXCSMV = tXCSMV;
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
		public BigDecimal getTXTEFM() {
			return TXTEFM;
		}
		public void setTXTEFM(BigDecimal tXTEFM) {
			TXTEFM = tXTEFM;
		}
		public BigDecimal getTXTNOA() {
			return TXTNOA;
		}
		public void setTXTNOA(BigDecimal tXTNOA) {
			TXTNOA = tXTNOA;
		}
		public Integer getTXCACU() {
			return TXCACU;
		}
		public void setTXCACU(Integer tXCACU) {
			TXCACU = tXCACU;
		}
		public Integer getTXNCUO() {
			return TXNCUO;
		}
		public void setTXNCUO(Integer tXNCUO) {
			TXNCUO = tXNCUO;
		}
		public Integer getTXNLCC() {
			return TXNLCC;
		}
		public void setTXNLCC(Integer tXNLCC) {
			TXNLCC = tXNLCC;
		}
		public BigDecimal getTXIORG() {
			return TXIORG;
		}
		public void setTXIORG(BigDecimal tXIORG) {
			TXIORG = tXIORG;
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
		public Integer getTXMRCH() {
			return TXMRCH;
		}
		public void setTXMRCH(Integer tXMRCH) {
			TXMRCH = tXMRCH;
		}
		
		
		
	}
}
//////////////////////////////////////////////1084-1273-1465 - 1626 - 1869 - 2377 - 2509 2657 3516 3679 3743 4158 4303