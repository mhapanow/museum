package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.DtgpdesDAO;
import com.ciessa.museum.dao.legacy.Tgpp632DAO;
import com.ciessa.museum.dao.legacy.ZpcpclrDAO;
import com.ciessa.museum.dao.legacy.ZrspdmrDAO;
import com.ciessa.museum.dao.legacy.ZrspiirDAO;
import com.ciessa.museum.dao.legacy.ZrspilrDAO;
import com.ciessa.museum.dao.legacy.ZrspmirDAO;
import com.ciessa.museum.dao.legacy.ZrspmlrDAO;
import com.ciessa.museum.dao.legacy.ZrsppirDAO;
import com.ciessa.museum.dao.legacy.ZrspplrDAO;
import com.ciessa.museum.dao.legacy.ZvrpfrqDAO;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Dtgpdes;
import com.ciessa.museum.model.legacy.Tgpp632;
import com.ciessa.museum.model.legacy.Zpcpclr;
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
	
	@Autowired
	ZpcpclrDAO myDAOZpcpclr;
	
	List<Dtgpdes> ListDtgpdes = new ArrayList<Dtgpdes>();
	List<Tgpp632> ListTgpp632 = new ArrayList<Tgpp632>();
	List<Zrspdmr> ListZrspdmr = new ArrayList<Zrspdmr>();
	List<Zrspplr> ListZrspplr = new ArrayList<Zrspplr>();
	List<Zrspilr> ListZrspilr = new ArrayList<Zrspilr>();
	List<Zrsppir> ListZrsppir = new ArrayList<Zrsppir>();
	List<Zrspmlr> ListZrspmlr = new ArrayList<Zrspmlr>();
	List<Zrspiir> ListZrspiir = new ArrayList<Zrspiir>();
	List<Zrspmir> ListZrspmir = new ArrayList<Zrspmir>();
	
	Zrspmlr ObjZrspmlr = new Zrspmlr();
	Zrspdmr ObjZrspdmr = new Zrspdmr();
	Zvrpfrq ObjZvrpfrq = new Zvrpfrq();
	Zrspmir ObjZrspmir = new Zrspmir();
	Zpcpclr ObjZpcpclr = new Zpcpclr();
	
	Zrsprer sstmhdr = null; //3000
	String spos = ""; //50
	Integer smaxelem = 0;
	Integer sind = 0;
	
	
	
	String syapaso  = null;
	String scabecera = null;
	
	String tl0127 = "PM N0000000000 C000 INPAGA ";
	String ti0127 = "PM N0000000000 C000 INPAGA";
	
	String mlcobo = "";
	String bocap = "166";
	String micobo = "";
	String bocad = "166";

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
	String[] mon = new String[50]; //NO SE SABE INDICE
	String[] moe = new String[50]; //NO SE SABE INDICE
	String[] mta = new String[50]; //50 de 33 posiciones
	String[] daj = new String[50]; // 50 de 28 posiciones
	String[] civ = new String[50]; //NO SE SABE INDICE
	String[] liv = new String[50]; //NO SE SABE INDICE
	String[] mos = new String[50]; //NO SE SABE INDICE
	String[] mod = new String[100];//100 elemntos - 10posiciones
	String[] mov = new String[100];//100 elemntos - 10posiciones
	
	String[] pai = new String[90]; //90-3
	String[] pad = new String[90]; //90-10
	 
	
	
	String wconfi = null;
	String mone = null;
	String sposic = null;
	BigDecimal sslmcm = new BigDecimal(0);
	Integer aaorgn = 0;
	
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
	
	String sw0106 = "";
	String auxd03 = "";
	String auxm03 = "";
	String sw0102 = "";
	String sw0304 = "";
	String sw0506 = "";
	String aux17 = "CITIPHONE BANKING";
	
	String wreflo = "";
	String wrefe = "";
	String wwdrfn = "";
	String agrefe = "Ref:";
	
	String micfar = "";
	
	Integer sqmov = 0;
	String sretpos = "";
	
	String sacttl = "";
	String sactti = "";
	String sactil = "";
	String sactii = "";
	String sactml = "";
	String sactmi = "";
	Integer resp05 = 0;
	
	Boolean ain69 = false;
	Boolean ain71 = false;
	Boolean ain73 = false;
	Boolean ain74 = false;
	Boolean ain75 = false;
	Boolean ain79 = false;
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
	Integer ttfecu = 0;
	BigDecimal alimpo = new BigDecimal(0);
	BigDecimal wtimp1 = new BigDecimal(0);
	BigDecimal wtotar = new BigDecimal(0);
	Boolean ain15 = false;
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
	
	String wreflz = "";
	String wlncab = "";
	String agpoli = "";
	String agrama = "";
	String agendo = "";
	String fecpin = "";
	
	//Funtioness	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	//Estrucutras
	ZRSTEMMVSTRX strx = null;
	ArrayList<ZRSTEMMVSTRX> sfmt = new ArrayList<ZRSTEMMVSTRX>();
	ZRSTEMMVLXREFP lxrefp = new ZRSTEMMVLXREFP();
	ZRSTEMMVW1MRCH w1mrch = new ZRSTEMMVW1MRCH();
	ZRSTEMMVWWMRCH wwmrch = new ZRSTEMMVWWMRCH();
	
	public ArrayList<ZRSTEMMVSTRX> getSfmt() {
		return this.sfmt;
	}
	
	
	public void SubProcGetstmdet (DataSet ds, Zrsprer SstmHdr, String Spos, Integer SmaxElem, ArrayList<ZRSTEMMVSTRX> Sfmt, Integer Sind  ) {
		long start = markStart();
		try {
			
			//Valores Iniciales
			
			this.sstmhdr = SstmHdr;
			this.spos = Spos;
			this.smaxelem = SmaxElem;
			//--this.sfmt = Sfmt;  //TODO: revisar esta linea
			this.sfmt.clear();
			for(int i = 0; i<256; i++) {
				this.sfmt.add(new ZRSTEMMVSTRX());
				//Sfmt.add(new ZRSTEMMVSTRX());
			}
		
			this.sind = Sind;
			
			this.SubRutAinit(ds);
			
			this.syapaso = "1";
			//this.scabecera = SstmHdr;
			this.sind = 0;
			this.ain69=false;
			this.SubRutApprde(ds, Spos);
		
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			markEnd(start);
		}
	}
	
	private String SubRutApprde (DataSet ds, String Spos) {
		try {
			this.wconfi = this.sstmhdr.getMecacl();
			this.mone = "pesos ";
			this.sposic = Spos;
			if (this.sstmhdr.getMecacl() == "c") {
				this.sslmcm = this.sstmhdr.getMemlco();
			}
			if (this.sstmhdr.getMetcon().equals("1") || this.sstmhdr.getMetcon().equals("3")) {
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
							this.wa6101 = "";
							this.ws6115 = this.ws6115.add(o.getTlpmna());							
						} else {
							this.fc6001 = 0;
							this.wa6001 = "";
							this.ws6116 = this.ws6116.add(o.getTlpmna());
						}
					} else {
						this.aadrfn = o.getTlxtrf();

						lxrefp = null;
						lxrefp.setTLNPTR("000");
						lxrefp.setTLNCAX(o.getTlncax());
						lxrefp.setTLCRDD(o.getTlcrdd());
						lxrefp.setTLCRMM(o.getTlcrmm());
						lxrefp.setTLCRAA(o.getTlcraa());
						
						this.SubRutArefpg(ds);
						this.SubRutSlineplr(ds, o.getTlpmna(), o.getTlcori(), o.getTlcmov(), o.getTlcsmv(), o.getTlncrd());
					}					
				} // fin for

				buscar$FMT01(7, 70, 1, 18, 69, 0);
				
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
			
			if (this.sstmhdr.getMetcon().equals("2") || this.sstmhdr.getMetcon().equals("3")) {
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
						
						this.aadrfn = o.getTixtrf();
						
						this.lxrefp = null;
						lxrefp.setTINPTR("000");
						lxrefp.setTINCAX(o.getTincax());
						lxrefp.setTICRDD(o.getTicrdd());
						lxrefp.setTICRMM(o.getTicrmm());
						lxrefp.setTICRAA(o.getTicraa());
						
						SubRutArefpg(ds);
						SubRutSlinepir(ds, o.getTiimpo(), o.getTicori(), o.getTicmov(), o.getTicsmv(), o.getTincrd());
						
					}//fin if
					
				}//fin for
				
				buscar$FMT01(7, 70, 1, 18, 69, 0);
				
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
			this.wtctal = new BigDecimal(0);
			this.wtctai = new BigDecimal(0);
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
		int a = Arrays.asList(this.mta).indexOf(this.aamoaj);
		if (a>0) {
			this.y = a;
			this.wdesl1 = this.daj[this.y];
		}else {
			this.SubRutAdesmo(ds);
			this.adpg = "NO";
		}
		return "";
	}
	
	private String SubRutAdesmo(DataSet ds) {
		
		try {
			this.y = 1;
			int a = Arrays.asList(this.mov).indexOf(this.wclmov);
			if (a>0) {
				this.y = a;
				this.wdeslo = this.mod[this.y];
			}else {
				ObjZrspdmr = myDAOZrspdmr.getUsigDrcmonAndDrcoriAndDrcmovAndDrcsmv(ds, drcmon, drcori.toString(), drcmov.toString(), drcsmv.toString());
				if (ObjZrspdmr != null) {
					this.y = 1;
					int b = Arrays.asList(this.mov).indexOf("");
					if (b>0) {
						this.mov[this.y] = this.wclmov;
						this.mod[this.y] = ObjZrspdmr.getDrdimp();
						this.wdeslo = this.mod[this.y];
					}
				}
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
				this.sw0106 = this.ssapdt.toString();//string integer
				this.sw0102 = this.sw0106.substring(1, 2);
				this.sw0304 = this.sw0106.substring(3, 4);
				this.sw0506 = this.sw0106.substring(5, 6);
				
				this.auxd03 = this.sw0506 + "/";
				this.auxm03 = this.sw0304 + "/";
				this.auxa08 = this.auxd03 + this.auxm03;
				this.auxa08 = this.sw0102;
				this.sw0106 = this.ssatdm.toString();
				this.auxh05 = this.sw0102 + ":";
				this.auxh05 = this.sw0304;
				this.sw0106 = this.ssnrtr.toString();
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
					this.sw0106 = this.ssnrtr.toString();
					this.auxa30 = this.aux17 + " " + this.sw0106;
					this.auxb30 = "";
					this.auxb30 = this.wdeslo + " " + this.auxa30;
					this.wdesl1 = this.auxb30;
				}else {
					if (this.ssclco != 0) {
						ObjZpcpclr = myDAOZpcpclr.getUsingSsclo(ds, ssclco);
						if (ObjZpcpclr != null) {
							this.auxa30 = "";
							this.auxb30 = "";
							this.auxa30 = wdeslo + " CHEQUE";
							this.auxb30 = auxa30 + " " + ObjZpcpclr.getClabrv();
							this.wdesl1 = "";
							this.wdesl1 = auxb30;
						}
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
			this.wwmrch = new ZRSTEMMVWWMRCH();
			this.ain69 = false;
			if (this.drcori == 2 && this.drcmov == 70) {
				this.wwdrfn = this.aadrfn;
			}else {
				this.lxrefp.ObjectToString();
				this.wreflo = this.lxrefp.getResultado(); 
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXMINI("1");
			strx.setTXFILE("P");
			strx.setTXLOIN("L");
			strx.setTXDESC(this.wdesl1);
			strx.setTXIMPO(TLPMNA);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
		this.sqmov = 0;
		return "";
	}
	
	private String SubRutAnewentry(DataSet ds) {
		try {
			if ( (this.sind + this.sqmov ) < this.sfmt.size() && (this.sind + this.sqmov ) < this.smaxelem ) {
				this.sind = this.sind +1;
				this.sfmt.set(this.sind, strx);
				if (this.sind == this.sfmt.size() || this.sind == this.smaxelem) {
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
			strx.setTXFMOVC(0);
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
			
			if (ListZrspmlr != null && ListZrspmlr.size() > 0) {
				this.ain73 = false;
				this.ain74 = false;
				this.ain75 = false;
				this.whcrcp = new BigDecimal(0);
				this.whcrc2 = new BigDecimal(0);
				this.whcrc3 = new BigDecimal(0);
				
				Integer _mlncuo = 0;
				Integer _mlcacu = 0;
				String _mlxtrf = "";
				Integer _mlcori = 0;
				Integer _mlcmov = 0;
				Integer _mlcsmv = 0;
				String _mlncrd = "";
				BigDecimal _mlcrcp = new BigDecimal(0);
				
				int indice = 0;
				int indiceNext = 0;			
				while (indice <= ListZrspmlr.size()) {
					indiceNext = indice;
					this.wsttem = new BigDecimal(0);
					this.wlimpo = new BigDecimal(0);
					this.wtmoz1 = new BigDecimal(0);
					this.wtmoz2 = new BigDecimal(0);
					this.wtmoz3 = new BigDecimal(0);
					this.wt1[0] = "0";
					this.wh1[0] = "0";
					this.wc1[0] = "0";
					String mldita = ListZrspmlr.get(indice).getMlncrd().substring(16, 18);
					this.digant = mldita; //Mldita(); 
					this.mxmota = ListZrspmlr.get(indice).getMlncrd().substring(19, 19); //Mlmota(); 
					this.ordant = ListZrspmlr.get(indice).getMlubir();
					this.aacanb = ListZrspmlr.get(indice).getMlncrd();
					
					while (indice <= ListZrspmlr.size() && mldita.equals(this.digant) && ListZrspmlr.get(indice).getMlubir().equals(this.ordant)  ) {
						this.soldmlr = this.sactml;
						this.wreflo = "";
						this.wrefe = "";
						this.wwdrfn = "";
						this.wwmrch = new ZRSTEMMVWWMRCH();
						this.ain69 = false;
						this.wdeslo = "";
						this.drcmon = "001";
						this.drcori = ListZrspmlr.get(indice).getMlcori();
						this.drcmov = ListZrspmlr.get(indice).getMlcmov();
						this.drcsmv = ListZrspmlr.get(indice).getMlcsmv();
						
						if (Arrays.asList(mos).contains(this.wclmov)) {
							if ( ListZrspmlr.get(indice).getMlcsmv() >= 90) {
								this.wtmoz2 = this.wtmoz2.add(ListZrspmlr.get(indice).getMlimpo());
								this.whcrc2 = this.whcrc2.add(ListZrspmlr.get(indice).getMlcrcp());
								this.wclmo2 = this.wclmov;
							}else {
								if (ListZrspmlr.get(indice).getMlcori() == 11 && ListZrspmlr.get(indice).getMlcmov() == 31 && ListZrspmlr.get(indice).getMlcsmv() == 1) {
									this.wlimpo = this.wlimpo.add(ListZrspmlr.get(indice).getMlimpo());
									this.wsinfi = this.mlde40;
									if ( fc.BigDecimalComparar(ListZrspmlr.get(indice).getMltefm().toString(), "0", "!=") ) {
										this.wsttem = ListZrspmlr.get(indice).getMltefm();
									}
								}else {
									if (ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 5 && ListZrspmlr.get(indice).getMlcsmv() == 1) {
										this.wtmoz3 = this.wtmoz3.add(ListZrspmlr.get(indice).getMlimpo());
										this.whcrc3 = this.whcrc3.add(ListZrspmlr.get(indice).getMlcrcp());
										this.wclmo3 = this.wclmov;
									}else {
										if (ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 5) {
											if (ListZrspmlr.get(indice).getMlcsmv() == 10)
												this.z1 = 1;
											else if (ListZrspmlr.get(indice).getMlcsmv() == 11) 
												this.z1 = 2;
											else if (ListZrspmlr.get(indice).getMlcsmv() == 12) 
												this.z1 = 3;
											else if (ListZrspmlr.get(indice).getMlcsmv() == 13) 
												this.z1 = 4;
											else if (ListZrspmlr.get(indice).getMlcsmv() == 14) 
												this.z1 = 5;
											else if (ListZrspmlr.get(indice).getMlcsmv() == 15) 
												this.z1 = 7;
											else
												this.z1 = 8;
											
											this.wt1[this.z1] = (new BigDecimal(wt1[this.z1]).add(ListZrspmlr.get(indice).getMlimpo())).toString();
											this.wh1[this.z1] = (new BigDecimal(wh1[this.z1]).add(ListZrspmlr.get(indice).getMlcrcp())).toString();
											this.wc1[this.z1] = this.wclmov;
										}
									}
								}
							}
							this.aama12 = "";
							if (ListZrspmlr.get(indice).getMlncuo() != 0) {
								this.aama12 = " CUOTA";
								this.aancuo = ListZrspmlr.get(indice).getMlncuo();
								this.aacacu = ListZrspmlr.get(indice).getMlcacu();
								this.aama12 = this.aama12 + " " + this.aancuo + "/" + this.aacacu;
								this.mlde40 = this.aama12;
							}
							if (ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 5) {
								if (ListZrspmlr.get(indice).getMlcsmv() == 11 || ListZrspmlr.get(indice).getMlcsmv() == 12 || ListZrspmlr.get(indice).getMlcsmv() == 13 || ListZrspmlr.get(indice).getMlcsmv() == 14 || ListZrspmlr.get(indice).getMlcsmv() == 91 || ListZrspmlr.get(indice).getMlcsmv() == 92 || ListZrspmlr.get(indice).getMlcsmv() == 93 || ListZrspmlr.get(indice).getMlcsmv() == 94) {
									this.ain75 = true;
								}
							}
						}else {
							this.wimplo = ListZrspmlr.get(indice).getMlimpo();
							this.wtctal = this.wtctal.add(ListZrspmlr.get(indice).getMlimpo());
							if (ListZrspmlr.get(indice).getMlcmov() == 60 && ListZrspmlr.get(indice).getMlcsmv() != 0) {
								this.wdeslo = "";
								SubRutAdesmo(ds);
								this.mlfecu = "";
							}else {
								this.wdeslo = this.mlde40;
								this.mlfecu = "";
							}
							if (ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 5) {
								if (ListZrspmlr.get(indice).getMlcsmv() == 11 || ListZrspmlr.get(indice).getMlcsmv() == 12 || ListZrspmlr.get(indice).getMlcsmv() == 13 || ListZrspmlr.get(indice).getMlcsmv() == 14 || ListZrspmlr.get(indice).getMlcsmv() == 91 || ListZrspmlr.get(indice).getMlcsmv() == 92 || ListZrspmlr.get(indice).getMlcsmv() == 93 || ListZrspmlr.get(indice).getMlcsmv() == 94) {
									this.ain75 = true;
								}
							}
							if (ListZrspmlr.get(indice).getMlcori() == 8) {
								this.mlfecu = "";
							}
							this.fecaux = 0;
							if (ListZrspmlr.get(indice).getMlcori() == 3 && ListZrspmlr.get(indice).getMlcmov() == 60 && ListZrspmlr.get(indice).getMlcsmv() == 15 || ListZrspmlr.get(indice).getMlcori() == 3 && ListZrspmlr.get(indice).getMlcmov() == 60 && ListZrspmlr.get(indice).getMlcsmv() == 16) {
								if (ListZrspmlr.get(indice).getMlcsmv() == 15) {
									this.ws6d15 = this.ws6d15.add(ListZrspmlr.get(indice).getMlimpo());
								}else {
									this.ws6d16 = this.ws6d16.add(ListZrspmlr.get(indice).getMlimpo());
								}
							}else {
								if (this.wdeslo == "") {
									this.wdeslo = this.mlde40;
								}
								this.mamini = "1";
								SubRutSlinel04(ds, ListZrspmlr.get(indice).getMlncuo(), ListZrspmlr.get(indice).getMlcacu(), ListZrspmlr.get(indice).getMlxtrf(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
								this.ain75 = true;
								this.whcrcp = new BigDecimal(0);
							}
						}
						_mlncuo = ListZrspmlr.get(indice).getMlncuo();
						_mlcacu = ListZrspmlr.get(indice).getMlcacu();
						_mlxtrf = ListZrspmlr.get(indice).getMlxtrf();
						_mlcori = ListZrspmlr.get(indice).getMlcori();
						_mlcmov = ListZrspmlr.get(indice).getMlcmov();
						_mlcsmv = ListZrspmlr.get(indice).getMlcsmv();
						_mlncrd = ListZrspmlr.get(indice).getMlncrd();
						_mlcrcp = ListZrspmlr.get(indice).getMlcrcp();
						indice +=1;
					}//FIN 1er while
					for (int z1 = 1; z1 < 10; z1++) {
						this.wtmoz1 = new BigDecimal(this.wt1[z1]);
						this.whcrcp = new BigDecimal(this.wh1[z1]);
						this.wclmo1 = this.wc1[z1];
						if ( fc.BigDecimalComparar(this.wtmoz1.toString(), "0", "!=")) {
							this.wreflo = "";
							this.wrefe = "";
							this.wwdrfn = "";
							this.wwmrch = new ZRSTEMMVWWMRCH();
							this.ain69 = false;
							this.wimplo = this.wtmoz1;
							this.wtctal = this.wtctal.add(this.wtmoz1);
							this.wclmov = wclmo1;
							this.wdeslo = "";
							SubRutAdesmo(ds);
							this.fecaux = 0;
							this.mamini = "1";
							SubRutSlinel04s(ds,_mlncuo, _mlcacu, _mlxtrf, _mlcori, _mlcmov, _mlcsmv, _mlncrd);
							if ( fc.BigDecimalComparar(this.wtmoz2.toString(), "0", "=")) {
								this.ain75 = false;
							}
							this.whcrcp = BigDecimal.ZERO;
							this.wtmoz1 = BigDecimal.ZERO;
						}
					}//FIN FOR
					if (fc.BigDecimalComparar(this.wtmoz2.toString(), "0", "!=")) {
						this.wreflo = "";
						this.wrefe = "";
						this.wwdrfn = "";
						this.wwmrch = new ZRSTEMMVWWMRCH();
						this.ain69 = false;
						this.wimplo = this.wtmoz2;
						this.wtctal = this.wtctal.add(this.wtmoz2);
						this.whcrcp = this.whcrc2;
						this.wclmov = this.wclmo2;
						this.wdeslo = "";
						SubRutAdesmo(ds);
						this.fecaux = 0;
						this.mamini = "1";
						SubRutSlinel04s(ds, _mlncuo, _mlcacu, _mlxtrf, _mlcori, _mlcmov, _mlcsmv, _mlncrd);
						this.ain75 = false;
						this.whcrcp = BigDecimal.ZERO;
						this.whcrc2 = BigDecimal.ZERO;
						this.wtmoz2 = BigDecimal.ZERO;
					}//FIN IF
					if (fc.BigDecimalComparar(this.wtmoz3.toString(), "0", "!=")) {
						this.wreflo = "";
						this.wrefe = "";
						this.wwdrfn = "";
						this.wwmrch = new ZRSTEMMVWWMRCH();
						this.ain69 = false;
						this.wimplo = this.wtmoz3;
						this.wtctal = this.wtctal.add(this.wtmoz3);
						this.whcrcp = this.whcrc3;
						this.wdeslo = "";
						this.wclmov = this.wclmo3;						
						SubRutAdesmo(ds);
						this.fecaux = 0;
						this.mamini = "1";
						SubRutSlinel04s(ds, _mlncuo, _mlcacu, _mlxtrf, _mlcori, _mlcmov, _mlcsmv, _mlncrd);
						this.wtmoz3 = BigDecimal.ZERO;
					}//FIN IF
					if (fc.BigDecimalComparar(this.wlimpo.toString(), "0", "!=")) {
						this.ain79 = false;
						this.wtctal = this.wtctal.add(this.wlimpo);
						this.mamini = "1";
						SubRutSlinel41(ds, _mlcrcp, _mlxtrf, _mlcori, _mlcmov, _mlncrd);
						this.wlimpo = BigDecimal.ZERO;
					}//FIN IF
					if (indice == indiceNext) {
						indice += 1;
					}
				}//FIN WHILE X/REGISTRO
			}//FIN IF EXISTE REGISTRO
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
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
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
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
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			String _mlxtrf = "";
			Integer _mlcori = 0;
			Integer _mlcmov = 0;
			Integer _mlcsmv = 0;
			String _mlncrd = "";
			Integer _mlncuo = 0;
			Integer _mlcacu = 0;
			
			ListZrspmlr = myDAOZrspmlr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedict(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), this.sstmhdr.getMencct().substring(6, 15), this.sstmhdr.getMencct().substring(16, 19), "2");
			int indice = 0;
			int indiceNext = 0;
			while (indice <= ListZrspmlr.size()) {
				indiceNext = indice;
				this.ad81 = "";
				this.ad82 = "";
				this.totdes = BigDecimal.ZERO;
				this.desdes = "";
				this.ttfecu = 0;
				this.alimpo = BigDecimal.ZERO;
				this.wtotar = BigDecimal.ZERO;
				String mldita = ListZrspmlr.get(indice).getMlncrd().substring(16, 18);
				this.digant = mldita; //Mldita();
				this.mxmota = ListZrspmlr.get(indice).getMlncrd().substring(19, 19);
				this.aacanb = ListZrspmlr.get(indice).getMlncrd();
				while (indice <= ListZrspmlr.size() && mldita.equals(this.digant) && ListZrspmlr.get(indice).getMlncrd().equals(this.aacanb)  ) {
					this.ain73 = false;
					this.ain74 = false;
					this.ain75 = false;
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 37 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 37 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 39 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 23 || ListZrspmlr.get(indice).getMlcori() == 24 && ListZrspmlr.get(indice).getMlcmov() == 37) {
						buscar$FMT02(1, 36, 9, 36, 9, 22, 9, 38, 24, 36);
					}
					if (ListZrspmlr.get(indice).getMlncuo() != 0) {
						this.aama12 = "";
						this.aama12 = " CUOTA";
						this.aancuo = ListZrspmlr.get(indice).getMlncuo();
						this.aacacu = ListZrspmlr.get(indice).getMlcacu();
						this.aama12 = this.aama12 + " " + this.aancuo + "/" + this.aacacu;
						this.mlde40 = this.aama12;
					}
					this.ain15 = false;
					this.wreflo = "";
					this.wrefe = "";
					this.wwdrfn = "";
					this.wwmrch = new ZRSTEMMVWWMRCH();
					this.ain69 = false;
					this.wwdrfn = ListZrspmlr.get(indice).getMlxtrf();

					w1mrch = null;
					w1mrch.setMLZOES(ListZrspmlr.get(indice).getMlzoes());
					w1mrch.setMLRUES(ListZrspmlr.get(indice).getMlrues());
					w1mrch.setMLCOES(ListZrspmlr.get(indice).getMlcoes());
					w1mrch.setMLSUES(ListZrspmlr.get(indice).getMlsues());
					w1mrch.setMLMOES(ListZrspmlr.get(indice).getMlmoes());
					
					if (w1mrch.getMLZOES() != 0 && w1mrch.getMLRUES() != 0 && w1mrch.getMLCOES() != 0 && w1mrch.getMLSUES() != 0 && w1mrch.getMLMOES() != 0 ) {
						wwmrch.setMLZOES(w1mrch.getMLZOES());
						wwmrch.setMLRUES(w1mrch.getMLRUES());
						wwmrch.setMLCOES(w1mrch.getMLCOES());
						wwmrch.setMLSUES(w1mrch.getMLSUES());
						wwmrch.setMLMOES(w1mrch.getMLMOES());
					}
					this.wdeslo = "";
					if (ListZrspmlr.get(indice).getMlncup() != 0) {
						this.wreflo = ListZrspmlr.get(indice).getMlncup().toString();
					}
					if (ListZrspmlr.get(indice).getMlcori() == 3) {
						this.wreflx = ListZrspmlr.get(indice).getMlncab() + ListZrspmlr.get(indice).getMldmov();
						this.wrefly = ListZrspmlr.get(indice).getMlmmov() + ListZrspmlr.get(indice).getMlamov();
					}
					if (ListZrspmlr.get(indice).getMlcori() == 23 && ListZrspmlr.get(indice).getMlcmov() == 35) {
						this.wreflo = "";
						this.wreflo = ListZrspmlr.get(indice).getMlncab();
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 || ListZrspmlr.get(indice).getMlcori() == 4 || ListZrspmlr.get(indice).getMlcori() == 5 || ListZrspmlr.get(indice).getMlcori() == 9 || ListZrspmlr.get(indice).getMlcori() == 24) {
						this.wreflo = ListZrspmlr.get(indice).getMlzoes().toString();
						this.wreflq = ListZrspmlr.get(indice).getMlrues() + ListZrspmlr.get(indice).getMlcoes();
						if ( !ListZrspmlr.get(indice).getMlncab().equals("")) {
							this.mlcobo = ListZrspmlr.get(indice).getMlncab().substring(0, 3);
							if (this.mlcobo.equals(this.bocap)) { 
								this.wreflw = ListZrspmlr.get(indice).getMlsues() + ListZrspmlr.get(indice).getMlmoes();
								this.mlfecu = ListZrspmlr.get(indice).getMldmov().toString().substring(0, 2) + ListZrspmlr.get(indice).getMlmmov().toString().substring(0, 2) + ListZrspmlr.get(indice).getMlamov().toString().substring(0, 2);
								this.wreflv = Integer.parseInt(this.mlfecu);

							}else {
								this.wlncab = ListZrspmlr.get(indice).getMlncab().substring(12, 16);
								this.wreflz = this.wlncab;
								if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 20 && ListZrspmlr.get(indice).getMlzoes() == 1 && ListZrspmlr.get(indice).getMlrues() == 18) {
									if (ListZrspmlr.get(indice).getMlrefc().equals("") || ListZrspmlr.get(indice).getMlrefc().equals("0")) {
										this.wreflr = ListZrspmlr.get(indice).getMlncaf();
									} else {
										this.wreflo="";
										if (ListZrspmlr.get(indice).getMlncuo() != 0) {
											this.ain73 = true;
										}
										
										this.agpoli = ListZrspmlr.get(indice).getMlrefc().substring(1, 8);
										this.agrama = ListZrspmlr.get(indice).getMlrefc().substring(12, 14);
										this.agendo = ListZrspmlr.get(indice).getMlrefc().substring(17, 19);
										
										this.wreflo = this.wreflo + " " + this.agpoli + " " + this.agrama + " " + this.agendo;
									}
								}else {
									this.wreflr = ListZrspmlr.get(indice).getMlncaf();
								}
							}
						}
						if (ListZrspmlr.get(indice).getMlcmov() == 22 && ListZrspmlr.get(indice).getMlcori() == 9 || ListZrspmlr.get(indice).getMlcmov() == 22 && ListZrspmlr.get(indice).getMlcori() == 5 || ListZrspmlr.get(indice).getMlcmov() == 22 && ListZrspmlr.get(indice).getMlcori() == 8 || ListZrspmlr.get(indice).getMlcmov() == 22 && ListZrspmlr.get(indice).getMlcori() == 1) {
							this.ain73 = true;
						}
						if (ListZrspmlr.get(indice).getMlcori() == 5 && ListZrspmlr.get(indice).getMlcmov() == 22 && ListZrspmlr.get(indice).getMlcsmv() == 9) {
							this.ain73 = false;
							this.ain74 = true;
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 8 && ListZrspmlr.get(indice).getMlcmov() == 20 || ListZrspmlr.get(indice).getMlcori() == 8 && ListZrspmlr.get(indice).getMlcmov() == 22 || ListZrspmlr.get(indice).getMlcori() == 8 && ListZrspmlr.get(indice).getMlcmov() == 25) {
						this.wreflo = ListZrspmlr.get(indice).getMlzoes().toString();
						this.wreflq = ListZrspmlr.get(indice).getMlrues();
						this.wreflq = ListZrspmlr.get(indice).getMlcoes();
						if (!ListZrspmlr.get(indice).getMlncab().equals("")) {
							this.mlcobo = ListZrspmlr.get(indice).getMlncab().substring(0, 3);
							if (this.mlcobo.equals(this.bocap)) { 
								this.wreflw = ListZrspmlr.get(indice).getMlsues();
								this.wreflw = ListZrspmlr.get(indice).getMlmoes();
								this.mlfecu = ListZrspmlr.get(indice).getMldmov().toString().substring(0, 2) + ListZrspmlr.get(indice).getMlmmov().toString().substring(0, 2) + ListZrspmlr.get(indice).getMlamov().toString().substring(0, 2);
								this.wreflv = Integer.parseInt(this.mlfecu);
							}else {
								this.wlncab = ListZrspmlr.get(indice).getMlncab().substring(12, 16);
								this.wreflz = this.wlncab;
								this.wreflr = ListZrspmlr.get(indice).getMlncaf();
							}
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 12 && ListZrspmlr.get(indice).getMlcmov() == 20) {
						this.wreflo = "";
					}
					if (!this.wwdrfn.equals("")) {
						if (ListZrspmlr.get(indice).getMlcori() == 1 || ListZrspmlr.get(indice).getMlcori() == 24 || ListZrspmlr.get(indice).getMlcori() == 5 || ListZrspmlr.get(indice).getMlcori() == 6 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 38 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 39 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 36 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 37 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 58 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 59 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 56 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 57 || ListZrspmlr.get(indice).getMlcori() == 12) {
							this.ain69 = true;
							this.wrefe = this.agrefe;
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 25) {
						this.wtimp1 = ListZrspmlr.get(indice).getMlcrcp().add(ListZrspmlr.get(indice).getMlimpo());
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							this.totcuo = ListZrspmlr.get(indice).getMlimpo();
							this.w1desc = this.mlde40;
							this.w1ncuo = ListZrspmlr.get(indice).getMlncuo();
							this.w1cacu = ListZrspmlr.get(indice).getMlcacu();
							if (!this.mlfecu.equals("") && !this.mlfecu.equals("0")) {
								this.fecadd = ListZrspmlr.get(indice).getMldmov();
								this.fecamm = ListZrspmlr.get(indice).getMlmmov();
								this.fecaa1 = ListZrspmlr.get(indice).getMlymov();
								this.fecaa2 = ListZrspmlr.get(indice).getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel27(ds, ListZrspmlr.get(indice).getMlxtrf(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
							SubRutSlinel25(ds, ListZrspmlr.get(indice).getMlcrcp(), ListZrspmlr.get(indice).getMlxtrf(),ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
							
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 26) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							SubRutSlinel26(ds, ListZrspmlr.get(indice).getMlimpo(), ListZrspmlr.get(indice).getMlxtrf(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 27 || ListZrspmlr.get(indice).getMlcori() == 4 && ListZrspmlr.get(indice).getMlcmov() == 27) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.wlcrcp = ListZrspmlr.get(indice).getMlcrcp().add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						if (!sstmhdr.getMecacl().equals("B")) {
							this.totcuo = ListZrspmlr.get(indice).getMlimpo();
							this.mlcap = ListZrspmlr.get(indice).getMlimpo();
							this.w1desc = this.mlde40;
							this.w1ncuo = ListZrspmlr.get(indice).getMlncuo();
							this.w1cacu = ListZrspmlr.get(indice).getMlcacu();
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 28 || ListZrspmlr.get(indice).getMlcori() == 4 && ListZrspmlr.get(indice).getMlcmov() == 28) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.totcuo = this.totcuo.add(ListZrspmlr.get(indice).getMlimpo());
						this.mlinte = ListZrspmlr.get(indice).getMlimpo();
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							if (!this.mlfecu.equals("") && !this.mlfecu.equals("0")) {
								this.fecadd = ListZrspmlr.get(indice).getMldmov();
								this.fecamm = ListZrspmlr.get(indice).getMlmmov();
								this.fecaa1 = ListZrspmlr.get(indice).getMlymov();
								this.fecaa2 = ListZrspmlr.get(indice).getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel27(ds, ListZrspmlr.get(indice).getMlxtrf(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
							SubRutSlinel28(ds, this.mlcap, this.mlinte, ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd(), this.mlde40);
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 4 && ListZrspmlr.get(indice).getMlcmov() == 29) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							SubRutSlinel29(ds, ListZrspmlr.get(indice).getMlimpo(), ListZrspmlr.get(indice).getMlmini(), ListZrspmlr.get(indice).getMlxtrf(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 23) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							if (!this.mlfecu.equals("") && !this.mlfecu.equals("0")) {
								this.fecadd = ListZrspmlr.get(indice).getMldmov();
								this.fecamm = ListZrspmlr.get(indice).getMlmmov();
								this.fecaa1 = ListZrspmlr.get(indice).getMlymov();
								this.fecaa2 = ListZrspmlr.get(indice).getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel23(ds, ListZrspmlr.get(indice).getMlde40(), ListZrspmlr.get(indice).getMlimpo(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 24) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						if (!sstmhdr.getMecacl().equals("B")) {
							if (!this.mlfecu.equals("") && !this.mlfecu.equals("0")) {
								this.fecadd = ListZrspmlr.get(indice).getMldmov();
								this.fecamm = ListZrspmlr.get(indice).getMlmmov();
								this.fecaa1 = ListZrspmlr.get(indice).getMlymov();
								this.fecaa2 = ListZrspmlr.get(indice).getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel23(ds, ListZrspmlr.get(indice).getMlde40(), ListZrspmlr.get(indice).getMlimpo(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 36 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 36 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 22 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 38 || ListZrspmlr.get(indice).getMlcori() == 24 && ListZrspmlr.get(indice).getMlcmov() == 36) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						if (!sstmhdr.getMecacl().equals("B")) {
							this.totcu2 = ListZrspmlr.get(indice).getMlimpo();
							this.w1desc = ListZrspmlr.get(indice).getMlde40();
							this.w1ncuo = ListZrspmlr.get(indice).getMlncuo();
							this.w1cacu = ListZrspmlr.get(indice).getMlcacu();
							this.ain15 = true;
							if (ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 36 && ListZrspmlr.get(indice).getMlcsmv() == 0 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 38 && ListZrspmlr.get(indice).getMlcsmv() == 0 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 22 && ListZrspmlr.get(indice).getMlcsmv() == 0 ) {
								this.ain73 = false;
								this.ain74 = true;
							}
							if (ListZrspmlr.get(indice).getMlcori() == 24 && ListZrspmlr.get(indice).getMlcmov() == 36 && ListZrspmlr.get(indice).getMlcsmv() == 9) {
								this.ain73 = false;
								this.ain74 = true;
							}else {
								this.ain73 = true;
							}
							if (!this.wconfi.equals("B")) {
								if (!this.mlfecu.equals("") && !this.mlfecu.equals("0")) {
									this.fecadd = ListZrspmlr.get(indice).getMldmov();
									this.fecamm = ListZrspmlr.get(indice).getMlmmov();
									this.fecaa1 = ListZrspmlr.get(indice).getMlymov();
									this.fecaa2 = ListZrspmlr.get(indice).getMlamov();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinel36(ds, ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
								this.totcu2 = BigDecimal.ZERO;
							}
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 37 || ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 37 || ListZrspmlr.get(indice).getMlcori() == 24 && ListZrspmlr.get(indice).getMlcmov() == 37) {
						if (this.w1desc.equals("")) {
							this.w1desc = ListZrspmlr.get(indice).getMlde40();
						}
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.totcu2 = this.totcu2.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						if (ListZrspmlr.get(indice).getMlcori() == 9 && ListZrspmlr.get(indice).getMlcmov() == 37 && ListZrspmlr.get(indice).getMlcsmv() == 0) {
							this.ain73 = false;
							this.ain74 = true;
						}
						if (ListZrspmlr.get(indice).getMlcori() == 24 && ListZrspmlr.get(indice).getMlcmov() == 37 && ListZrspmlr.get(indice).getMlcsmv() == 9) {
							this.ain73 = false;
							this.ain74 = true;
						}else {
							this.ain73 = true;
						}
						if (!this.wconfi.equals("B")) {
							if (!this.mlfecu.equals("") && !this.mlfecu.equals("0")) {
								this.fecadd = ListZrspmlr.get(indice).getMldmov();
								this.fecamm = ListZrspmlr.get(indice).getMlmmov();
								this.fecaa1 = ListZrspmlr.get(indice).getMlymov();
								this.fecaa2 = ListZrspmlr.get(indice).getMlamov();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinel36(ds, ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
							this.totcu2 = BigDecimal.ZERO;
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 17 && ListZrspmlr.get(indice).getMlcmov() == 26) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						this.wsadef = ListZrspmlr.get(indice).getMlde40();
						if (!this.wconfi.equals("B")) {
							if (ListZrspmlr.get(indice).getMldmov() != 0) {
								this.agamov = ListZrspmlr.get(indice).getMlymov() * 100;
								this.agamov = this.agamov + ListZrspmlr.get(indice).getMlamov();
							}
							SubRutSlinel37(ds, ListZrspmlr.get(indice).getMlimpo(), ListZrspmlr.get(indice).getMlxtrf(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 17 && ListZrspmlr.get(indice).getMlcmov() == 27) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						this.wsinef = ListZrspmlr.get(indice).getMlde40();
						if (!this.wconfi.equals("B")) {
							SubRutSlinel38(ds, ListZrspmlr.get(indice).getMlimpo(), ListZrspmlr.get(indice).getMltefm(), ListZrspmlr.get(indice).getMltnoa(), ListZrspmlr.get(indice).getMlxtrf(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 17 && ListZrspmlr.get(indice).getMlcmov() == 28) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						if (!this.wconfi.equals("B")) {
							SubRutSlinel29(ds, ListZrspmlr.get(indice).getMlimpo(), ListZrspmlr.get(indice).getMlmini(), ListZrspmlr.get(indice).getMlxtrf(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
						}
					}
					if (ListZrspmlr.get(indice).getMlcori() == 17 && ListZrspmlr.get(indice).getMlcmov() == 29) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						this.alimpo = this.alimpo.add(ListZrspmlr.get(indice).getMlimpo());
					}
					if (ListZrspmlr.get(indice).getMlcori() == 12 && ListZrspmlr.get(indice).getMlcmov() == 50 && ListZrspmlr.get(indice).getMlcsmv() == 0) {
						this.ain73 = true;
						this.ain74 = false;
					}
					if (ListZrspmlr.get(indice).getMlcori() == 1 && ListZrspmlr.get(indice).getMlcmov() == 20 && ListZrspmlr.get(indice).getMlcsmv() == 66) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.ain15 = true;
						this.totdes = this.totdes.add(ListZrspmlr.get(indice).getMlimpo());
						this.desdes = ListZrspmlr.get(indice).getMlde40();
						this.mlfecu = ListZrspmlr.get(indice).getMldmov().toString().substring(0, 2) + ListZrspmlr.get(indice).getMlmmov().toString().substring(0, 2) + ListZrspmlr.get(indice).getMlamov().toString().substring(0, 2);
						this.ttfecu = Integer.parseInt(this.mlfecu);
					}
					if (!this.ain15) {
						this.wtotar = this.wtotar.add(ListZrspmlr.get(indice).getMlimpo());
						this.wimplo = ListZrspmlr.get(indice).getMlimpo();
						this.wdeslo = ListZrspmlr.get(indice).getMlde40();
						if (!this.wconfi.equals("B")) {
							this.ain75 = false;
							if (!this.mlfecu.equals("") && !this.mlfecu.equals("0")) {
								this.fecadd = ListZrspmlr.get(indice).getMldmov();
								this.fecamm = ListZrspmlr.get(indice).getMlmmov();
								this.fecaa1 = ListZrspmlr.get(indice).getMlymov();
								this.fecaa2 = ListZrspmlr.get(indice).getMlamov();
							}else {
								this.fecaux = 0;
							}
							this.ain69 = false;
							this.mamini = "2";
							SubRutSlinel04(ds, ListZrspmlr.get(indice).getMlncuo(), ListZrspmlr.get(indice).getMlcacu(), ListZrspmlr.get(indice).getMlxtrf(), ListZrspmlr.get(indice).getMlcori(), ListZrspmlr.get(indice).getMlcmov(), ListZrspmlr.get(indice).getMlcsmv(), ListZrspmlr.get(indice).getMlncrd());
						}
					}
					_mlxtrf = ListZrspmlr.get(indice).getMlxtrf();
					_mlcori = ListZrspmlr.get(indice).getMlcori();
					_mlcmov = ListZrspmlr.get(indice).getMlcmov();
					_mlcsmv = ListZrspmlr.get(indice).getMlcsmv();
					_mlncrd = ListZrspmlr.get(indice).getMlncrd();
					_mlncuo = ListZrspmlr.get(indice).getMlncuo();
					_mlcacu = ListZrspmlr.get(indice).getMlcacu();
					indice +=1;
				} // FIN IF
				if (indice == indiceNext) {
					indice += 1;
				}
			}//FIN WHILE
			if (fc.BigDecimalComparar(this.alimpo.toString(), "0", "!=")) {
				if (!this.wconfi.equals("B")) {
					SubRutSlinel30(ds, _mlxtrf, _mlcori, _mlcmov, _mlcsmv, _mlncrd);
				}
			}
			if (fc.BigDecimalComparar(this.totdes.toString(), "0", "!=")) {
				this.wimplo = this.totdes;
				this.wdeslo = this.desdes;
				this.ain75 = false;
				this.fecaux = 0;
				this.mamini = "2";
				SubRutSlinel04(ds, _mlncuo, _mlcacu, _mlxtrf, _mlcori, _mlcmov, _mlcsmv, _mlncrd);
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
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
			strx.setTXTEFM(BigDecimal.ZERO); //MLPIFG
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
			String MLNCRD,
			String MLDE40) {
		try {
			strx = new ZRSTEMMVSTRX();
			strx.setTXFMT("DELO28");
			strx.setTXMINI("2");
			strx.setTXFILE("M");
			strx.setTXLOIN("L");
			strx.setTXDESC(" (*)CITIBANK CAPITAL ");
			strx.setTXIMPO(MLCAP);
			strx.setTXIORG(MLINTE);
			strx.setTXREFC(MLDE40.substring(19, 40));//DESCPC
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(MLDE40);
			strx.setTXIMPO(MLIMPO);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(this.w1desc);
			
			if (this.ain74 == true) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.totcu2);
			strx.setTXNCUO(this.w1ncuo);
			strx.setTXCACU(this.w1cacu);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
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
			ListZrspmlr = myDAOZrspmlr.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedict(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), this.sstmhdr.getMencct().substring(6, 15), this.sstmhdr.getMencct().substring(16, 19), "3");
			for (Zrspmlr o : ListZrspmlr) {
				if (o.getMlcori() == 24 && o.getMlcmov() == 39 || o.getMlcori() == 9 && o.getMlcmov() == 39 ) {
					buscar$FMT03(24, 38, 9, 38);
				}
				if (o.getMlncuo() != 0) {
					this.aama12 = "";
					this.aama12 = "CUOTA";
					this.aancuo = o.getMlncuo();
					this.aacacu = o.getMlcacu();
					this.aama12 = this.aama12 +" " + this.aancuo.toString() +"/" + this.aacacu.toString();
					this.mlde40 = this.aama12;
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
				if (!this.mlfecu.equals("") && !this.mlfecu.equals("0")) {
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
				this.wwmrch = new ZRSTEMMVWWMRCH();
				this.wmone = "";
				this.wreflo = o.getMlncab() + o.getMlnlcc().toString();
				this.wrefe = this.agrefe;
				this.wwdrfn = o.getMlxtrf();

				w1mrch = null;
				w1mrch.setMLZOES(o.getMlzoes());
				w1mrch.setMLRUES(o.getMlrues());
				w1mrch.setMLCOES(o.getMlcoes());
				w1mrch.setMLSUES(o.getMlsues());
				w1mrch.setMLMOES(o.getMlmoes());
				if (w1mrch.getMLZOES() != 0 && w1mrch.getMLRUES() != 0 && w1mrch.getMLCOES() != 0 && w1mrch.getMLSUES() != 0 && w1mrch.getMLMOES() != 0) {
					wwmrch.setMLZOES(w1mrch.getMLZOES());
					wwmrch.setMLRUES(w1mrch.getMLRUES());
					wwmrch.setMLCOES(w1mrch.getMLCOES());
					wwmrch.setMLSUES(w1mrch.getMLSUES());
					wwmrch.setMLMOES(w1mrch.getMLMOES());
				}
				if (o.getMlcori() == 24 && o.getMlcmov() == 38 || o.getMlcori() == 9 && o.getMlcmov() == 38) {
					this.wtotar = this.wtotar.add(o.getMlimpo());
					this.wtctal = this.wtctal.add(o.getMlimpo());
					if (o.getMlncuo() == 1 && o.getMlcori() == 24) {
						this.ascuol = o.getMlcacu();
						this.ascaol = o.getMlimpo().add(o.getMlcrcp());
					}
					this.agsini = "";
					if (this.sstmhdr.getMecacl().compareTo("B") == 1 ) {
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
					if (this.wconfi.compareTo("B") == 1) {
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(this.w1desc);
			
			if (this.ain74) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.totcu2);
			strx.setTXNCUO(this.w1ncuo);
			strx.setTXCACU(this.w1cacu);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(this.wdesli);
			strx.setTXIMPO(TIIMPO);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			ListZrspmir = myDAOZrspmir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedictAndMimini(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), this.sstmhdr.getMencct().substring(6-1, 15), this.sstmhdr.getMencct().substring(16-1, 19), "1");
			int indice = 0;
			int indiceNext = 0;
			String _mimini = "";
			Integer _micori = 0;
			Integer _micmov = 0;
			Integer _micsmv = 0;
			String _mincrd = "";
			BigDecimal _micrcp = new BigDecimal(0);
			String _mixtrf = "";
			while (indice < ListZrspmir.size()) {
				indiceNext = indice;
				this.wtmoz1 = BigDecimal.ZERO;
				this.wtmoz3 = BigDecimal.ZERO;
				this.whcrc3 = BigDecimal.ZERO;
				this.wiimpo = BigDecimal.ZERO;
				String midita = ListZrspmir.get(indice).getMincrd().substring(16, 18);
				this.digant = midita; //Mldita();
				this.mxmota = ListZrspmir.get(indice).getMincrd().substring(19, 19);
				this.ordant = ListZrspmir.get(indice).getMiubir();
				this.aacanb = ListZrspmir.get(indice).getMincrd();
				this.wsttem = BigDecimal.ZERO;
				while (indice <= ListZrspmir.size() && midita.equals(this.digant) && ListZrspmir.get(indice).getMiubir().equals(this.ordant) && ListZrspmir.get(indice).getMincrd().equals(this.aacanb)  ) {  
					this.soldmir = this.sactmi;
					this.wreflo = "";
					this.wrefe = "";
					this.wwdrfn = "";
					this.ain69 = false;
					this.wdeslo = "";
					this.drcmon = "002";
					this.drcori = ListZrspmir.get(indice).getMicori();
					this.drcmov = ListZrspmir.get(indice).getMicmov();
					this.drcsmv = ListZrspmir.get(indice).getMicsmv();
					if (Arrays.asList(mos).contains(this.wclmov)) {
						if (ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 5 && ListZrspmir.get(indice).getMicsmv() == 1) {
							this.wtmoz3 = this.wtmoz3.add(ListZrspmir.get(indice).getMiimpo());
							this.whcrc3 = this.whcrc3.add(ListZrspmir.get(indice).getMiiorg());
							this.wclmo3 = this.wclmov;
						}else {
							if (ListZrspmir.get(indice).getMicori() == 11 && ListZrspmir.get(indice).getMicmov() == 31 && ListZrspmir.get(indice).getMicsmv() == 1) {
								this.wiimpo = this.wiimpo.add(ListZrspmir.get(indice).getMiimpo());
								this.wsinfi = ListZrspmir.get(indice).getMide40();
								if ( fc.BigDecimalComparar(ListZrspmir.get(indice).getMitefm().toString(), "0", "!=") ) {
									this.wsttem = ListZrspmir.get(indice).getMitefm();
								}
							}else {
								if (ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 5) {
									if (ListZrspmir.get(indice).getMicsmv() == 10)
										this.z1 = 1;
									else if (ListZrspmir.get(indice).getMicsmv() == 11) 
										this.z1 = 2;
									else if (ListZrspmir.get(indice).getMicsmv() == 12) 
										this.z1 = 3;
									else if (ListZrspmir.get(indice).getMicsmv() == 13) 
										this.z1 = 4;
									else if (ListZrspmir.get(indice).getMicsmv() == 14) 
										this.z1 = 5;
									else if (ListZrspmir.get(indice).getMicsmv() == 15) 
										this.z1 = 7;
									else
										this.z1 = 8;
									
									this.wt1[this.z1] = (new BigDecimal(wt1[this.z1]).add(ListZrspmir.get(indice).getMiimpo())).toString();
									this.wh1[this.z1] = (new BigDecimal(wh1[this.z1]).add(ListZrspmir.get(indice).getMicrcp())).toString();
									this.wc1[this.z1] = this.wclmov;
								}
							}
						}
						
						if (ListZrspmir.get(indice).getMincuo() != 0) {
							this.aama12 = "";
							this.aama12 = "CUOTA";
							this.aancuo = ListZrspmir.get(indice).getMincuo();
							this.aacacu = ListZrspmir.get(indice).getMicacu();
							this.aama12 = this.aama12 + " " + this.aancuo + "/" + this.aacacu;
							this.mide40 = this.aama12;
						}
						if (ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 5) {
							if (ListZrspmir.get(indice).getMicsmv() == 11 || ListZrspmir.get(indice).getMicsmv() == 12 || ListZrspmir.get(indice).getMicsmv() == 13 || ListZrspmir.get(indice).getMicsmv() == 14 || ListZrspmir.get(indice).getMicsmv() == 91 || ListZrspmir.get(indice).getMicsmv() == 92 || ListZrspmir.get(indice).getMicsmv() == 93 || ListZrspmir.get(indice).getMicsmv() == 94) {
								this.ain75 = true;
							}
						}
					}else {
						
						if (ListZrspmir.get(indice).getMicmov() == 60 && ListZrspmir.get(indice).getMicsmv() != 0) {
							this.wdeslo = "";
							SubRutAdesmo(ds);
							this.mifecu = "";
						}else {
							this.wdeslo = ListZrspmir.get(indice).getMide40();
							this.mifecu = "";
						}
						this.wimplo = ListZrspmir.get(indice).getMiimpo();
						this.wtctai = this.wtctai.add(ListZrspmir.get(indice).getMiimpo());
						if (ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 5) {
							if (ListZrspmir.get(indice).getMicsmv() == 11 || ListZrspmir.get(indice).getMicsmv() == 12 || ListZrspmir.get(indice).getMicsmv() == 13 || ListZrspmir.get(indice).getMicsmv() == 14 || ListZrspmir.get(indice).getMicsmv() == 91 || ListZrspmir.get(indice).getMicsmv() == 92 || ListZrspmir.get(indice).getMicsmv() == 93 || ListZrspmir.get(indice).getMicsmv() == 94) {
								this.ain75 = true;
							}
						}
						this.fecaux = 0;
						SubRutSlinei04(ds, ListZrspmir.get(indice).getMimini(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd());
						this.ain75 = false;
						this.wicrcp = BigDecimal.ZERO;
					}
					_mimini = ListZrspmir.get(indice).getMimini();
					_micori = ListZrspmir.get(indice).getMicori();
					_micmov = ListZrspmir.get(indice).getMicmov();
					_micsmv = ListZrspmir.get(indice).getMicsmv();
					_mincrd = ListZrspmir.get(indice).getMincrd();
					_micrcp = ListZrspmir.get(indice).getMicrcp();
					_mixtrf = ListZrspmir.get(indice).getMixtrf();
					indice +=1;
				} //FIN 1er WHILE
				for (int z1 = 1; z1 < 10; z1++) {
					this.wtmoz1 = new BigDecimal(this.wt1[z1]);
					this.wicrcp = new BigDecimal(this.wh1[z1]);
					this.wclmo1 = this.wc1[z1];
					if ( fc.BigDecimalComparar(this.wtmoz1.toString(), "0", "!=")) {
						this.wreflo = "";
						this.wrefe = "";
						this.wwdrfn = "";
						this.wwmrch = new ZRSTEMMVWWMRCH();
						this.ain69 = false;
						this.wimplo = this.wtmoz1;
						this.wtctai = this.wtctai.add(this.wtmoz1);
						this.wdeslo = "";
						this.wclmov = wclmo1;
						SubRutAdesmo(ds);
						this.fecaux = 0;
						SubRutSlinei04s(ds, _mimini, _micori, _micmov, _micsmv, _mincrd);
						this.ain75 = false;
						this.wicrcp = BigDecimal.ZERO;
						this.wtmoz1 = BigDecimal.ZERO;
					}
				}
				if (fc.BigDecimalComparar(this.wtmoz3.toString(), "0", "!=")) {
					this.wreflo = "";
					this.wrefe = "";
					this.wwdrfn = "";
					this.wwmrch = new ZRSTEMMVWWMRCH();
					this.ain69 = false;
					this.wimplo = this.wtmoz3;
					this.whcrcp = this.whcrc3;
					this.wtctai = this.wtctai.add(this.wtmoz3);
					this.wdeslo = "";
					this.wclmov = this.wclmo3;
					SubRutAdesmo(ds);
					this.fecaux = 0;
					this.mamini = "1";
					SubRutSlinei04s(ds, _mimini, _micori, _micmov, _micsmv, _mincrd);
					this.ain75 = false;
					this.whcrc3 = BigDecimal.ZERO;
					this.wicrcp = BigDecimal.ZERO;
					this.wtmoz3 = BigDecimal.ZERO;
				}
				if (fc.BigDecimalComparar(this.wiimpo.toString(), "0", "==")) {
					this.ain79 = false;
					this.wtctai = this.wtctai.add(this.wiimpo);
					SubRutSlinei41(ds, _micrcp, _mixtrf, _micori, _micmov, _mincrd);
					this.wiimpo = BigDecimal.ZERO;
				}
				if (indice == indiceNext) {
					indice += 1;
				}
			}//FIN WHILE
			
			
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(this.wdeslo);
			strx.setTXIMPO(this.wimplo);
			strx.setTXIORG(this.wicrcp);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(this.wdeslo);
			strx.setTXIMPO(this.wimplo);
			strx.setTXIORG(this.wicrcp);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			strx.setTXFMOV(0);
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
			Integer _mincuo = 0;
			Integer _micacu = 0;
			Integer _micori = 0;
			Integer _micmov = 0;
			Integer _micsmv = 0;
			String _mincrd = "";
			Integer _mimhab = 0;
			String _micfar = "";
			
			ListZrspmir = myDAOZrspmir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedictAndMimini(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), this.sstmhdr.getMencct().substring(6-1, 15), this.sstmhdr.getMencct().substring(16-1, 19), "2");
			int indice = 0;
			int indiceNext = 0;					
			while (indice < ListZrspmir.size()) {
				indiceNext = indice;
				this.wtotar = BigDecimal.ZERO;
				this.micant = "";
				this.digant = ListZrspmir.get(indice).getMincrd().substring(16, 18);
				this.mxmota = ListZrspmir.get(indice).getMincrd().substring(19, 19);
				this.aacanb = ListZrspmir.get(indice).getMincrd();
				this.totdes = BigDecimal.ZERO;        
				this.desdes = "";        
				this.ttfecu = 0;
				while (indice < ListZrspmir.size() && ListZrspmir.get(indice).getMincrd().equals(this.aacanb)) {
					this.ain75 = false;
					this.ain74 = false;
					this.ain73 = false;
					this.ain71= false;
					this.mide40 = ListZrspmir.get(indice).getMide40();
					if (ListZrspmir.get(indice).getMicori() == 0 && ListZrspmir.get(indice).getMicmov() == 57 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 57 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 59 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 53 || ListZrspmir.get(indice).getMicori() == 24 && ListZrspmir.get(indice).getMicmov() == 57) {
						buscar$FMT02(1, 56, 9, 56, 9, 58, 9, 52, 24, 56);
					}
					if (ListZrspmir.get(indice).getMincuo() != 0) {
						this.aama12 = "";        
						this.aama12 = " CUOTA";
						this.aancuo = ListZrspmir.get(indice).getMincuo();
						this.aacacu = ListZrspmir.get(indice).getMicacu();
						this.aama12 = this.aama12 + "  " + this.aancuo + "/" + this.aacacu;
						this.mide40 = this.aama12;
					}
					this.ain15 = false;
					this.wreflo = "";
					this.wrefe = "";
					this.wwdrfn = "";
					this.wwmrch = new ZRSTEMMVWWMRCH();
					this.ain69 = false;
					this.wdeslo = "";
					this.kyfr00 = "";
					this.wreflo = this.winca1.toString() + " ";
					this.wwdrfn = ListZrspmir.get(indice).getMixtrf();
					w1mrch = new ZRSTEMMVW1MRCH();
					w1mrch.setMIZOES(ListZrspmir.get(indice).getMizoes());
					w1mrch.setMIRUES(ListZrspmir.get(indice).getMirues());
					w1mrch.setMICOES(ListZrspmir.get(indice).getMicoes());
					w1mrch.setMISUES(ListZrspmir.get(indice).getMisues());
					w1mrch.setMIMOES(ListZrspmir.get(indice).getMimoes());
					if (w1mrch.getMIZOES() != 0 && w1mrch.getMIRUES() != 0 && w1mrch.getMICOES() != 0 && w1mrch.getMISUES() != 0 && w1mrch.getMIMOES() != 0 && ListZrspmir.get(indice).getMicori() != 6) {
						wwmrch.setMIZOES(w1mrch.getMIZOES());
						wwmrch.setMIRUES(w1mrch.getMIRUES());
						wwmrch.setMICOES(w1mrch.getMICOES());
						wwmrch.setMISUES(w1mrch.getMISUES());
						wwmrch.setMIMOES(w1mrch.getMIMOES());
					}
					if (!this.wwdrfn.equals("")) {
						if (ListZrspmir.get(indice).getMicori() == 1 || ListZrspmir.get(indice).getMicori() == 24 || ListZrspmir.get(indice).getMicori() == 5 || ListZrspmir.get(indice).getMicori() == 6 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 38 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 39 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 36 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 37 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 58 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 59 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 56 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 57 || ListZrspmir.get(indice).getMicori() == 12) {
							this.ain69 = true;
							this.wrefe = this.agrefe;
						}
					}
					this.wtotar = this.wtotar.add(ListZrspmir.get(indice).getMiimpo());
					this.wimplo = ListZrspmir.get(indice).getMiimpo();
					this.wdeslo = this.mide40;
					if (ListZrspmir.get(indice).getMicmov() != 57) {
						this.wdespa = "";
					}
					this.wimcbo = ListZrspmir.get(indice).getMiiorg();
					this.y = 1;
					int a = Arrays.asList(this.mon).indexOf(ListZrspmir.get(indice).getMimhab().toString());
					if (a>0) {
						this.y = a;
						this.wmone = this.moe[y];
					}
					this.wdespa = "";
					this.aadosf = ListZrspmir.get(indice).getMixtrf();
					this.micfar = "";
					this.micfar = this.aadosf;
					this.kyfr00 = this.micfar;
					
					if (!this.micfar.equals(this.micant)) {
						this.micant = this.micfar;
						this.y = 1;
						int b = Arrays.asList(this.pai).indexOf(this.micfar);
						
						if (b > 0) {
							this.y = b;
							this.wdespa = this.pad[y];
						}else {
							ObjZvrpfrq = myDAOZvrpfrq.getUsingTxcfar(ds, this.kyfr00);
							if (ObjZvrpfrq != null) {
								this.y = 1;
								int c = Arrays.asList(this.pai).indexOf("");
								if (c > 0) {
									this.y = c;
									this.pai[y] = this.micfar; 
									this.pad[y] = ObjZvrpfrq.getFr9969();
									this.wdespa = pad[y];
								}
							}
						}
					}
					if (!this.wconfi.equals("B")) {
						this.ain73 = false;
						this.ain74 = false;
						this.ain75 = false;
						this.wwdrfn = ListZrspmir.get(indice).getMixtrf();
						this.ain69 = false;
						if (w1mrch.getMIZOES() != 0 && w1mrch.getMIRUES() != 0 && w1mrch.getMICOES() != 0 && w1mrch.getMISUES() != 0 && w1mrch.getMIMOES() != 0 && ListZrspmir.get(indice).getMicori() != 6) {
							wwmrch.setMIZOES(w1mrch.getMIZOES());
							wwmrch.setMIRUES(w1mrch.getMIRUES());
							wwmrch.setMICOES(w1mrch.getMICOES());
							wwmrch.setMISUES(w1mrch.getMISUES());
							wwmrch.setMIMOES(w1mrch.getMIMOES());
						}
						if (ListZrspmir.get(indice).getMicori() == 23 && ListZrspmir.get(indice).getMicmov() == 35) {
							this.wreflo = "";
							this.wrefe = "";
							this.wwdrfn = "";
							this.wwmrch = new ZRSTEMMVWWMRCH();
							this.wwdrfn = ListZrspmir.get(indice).getMixtrf();
							if (w1mrch.getMIZOES() != 0 && w1mrch.getMIRUES() != 0 && w1mrch.getMICOES() != 0 && w1mrch.getMISUES() != 0 && w1mrch.getMIMOES() != 0 && ListZrspmir.get(indice).getMicori() != 6) {
								wwmrch.setMIZOES(w1mrch.getMIZOES());
								wwmrch.setMIRUES(w1mrch.getMIRUES());
								wwmrch.setMICOES(w1mrch.getMICOES());
								wwmrch.setMISUES(w1mrch.getMISUES());
								wwmrch.setMIMOES(w1mrch.getMIMOES());
							}
							this.wreflo = ListZrspmir.get(indice).getMincab();							
						}
						if (ListZrspmir.get(indice).getMicmov() == 40 && ListZrspmir.get(indice).getMicori() == 1) {
							if (ListZrspmir.get(indice).getMicmov() == 0) {
								this.ain69 = true;
							}
							this.wreflo = ListZrspmir.get(indice).getMincup().toString() + ListZrspmir.get(indice).getMizoes();
							this.wreflq = ListZrspmir.get(indice).getMirues() + ListZrspmir.get(indice).getMicoes();
							if (!ListZrspmir.get(indice).getMincab().equals("")) {
								this.micobo = ListZrspmir.get(indice).getMincab().substring(0, 3); 
								if (this.micobo.equals(this.bocad)) {
									this.wreflw = ListZrspmir.get(indice).getMisues() + ListZrspmir.get(indice).getMimoes();
									this.mifecu = ListZrspmir.get(indice).getMidcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMimcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMiacup().toString().substring(0, 2);
									this.wreflv = Integer.parseInt(this.mifecu);
								}else {
									this.wreflz = ListZrspmir.get(indice).getMincab().substring(12, 16); //WINCAB
									this.wreflr = ListZrspmir.get(indice).getMincaf();
								}
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 1 || ListZrspmir.get(indice).getMicori() == 5 || ListZrspmir.get(indice).getMicori() == 8 || ListZrspmir.get(indice).getMicori() == 9 || ListZrspmir.get(indice).getMicori() == 24) {
							this.wreflo = ListZrspmir.get(indice).getMincup().toString() + ListZrspmir.get(indice).getMizoes();
							this.wreflq = ListZrspmir.get(indice).getMirues() + ListZrspmir.get(indice).getMicoes();
							if (!ListZrspmir.get(indice).getMincab().equals("")) {
								this.micobo = ListZrspmir.get(indice).getMincab().substring(0, 3); 
								if (this.micobo.equals(this.bocad)) {
									this.wreflw = ListZrspmir.get(indice).getMisues() + ListZrspmir.get(indice).getMimoes();
									this.mifecu = ListZrspmir.get(indice).getMidcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMimcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMiacup().toString().substring(0, 2);
									this.wreflv = Integer.parseInt(this.mifecu);
								}else {
									this.wreflz = ListZrspmir.get(indice).getMincab().substring(12, 16);
									if (ListZrspmir.get(indice).getMicori() == 1 && ListZrspmir.get(indice).getMicmov() == 40 && ListZrspmir.get(indice).getMizoes() == 1 && ListZrspmir.get(indice).getMirues() == 18) {
										if (ListZrspmir.get(indice).getMirefc().equals("") || ListZrspmir.get(indice).getMirefc() == "0") {
											this.wreflr = ListZrspmir.get(indice).getMincaf();
										}else {
											this.wreflo = " ";
											this.wreflr = 0; 
											if (ListZrspmir.get(indice).getMincuo() != 0) {
												this.ain73 = true;
												if (ListZrspmir.get(indice).getMicsmv() == 66) {
													this.ain71 = true;
												}
											}
											this.agpoli = ListZrspmir.get(indice).getMirefc().substring(1, 8);
											this.agrama = ListZrspmir.get(indice).getMirefc().substring(12, 14);
											this.agendo = ListZrspmir.get(indice).getMirefc().substring(17, 19);
											this.wreflo = this.wreflo + " " + this.agpoli + " " + this.agrama + " " + this.agendo;
										}
									}else {
										this.wreflr = ListZrspmir.get(indice).getMincaf();
									}
								}
							}
						}
						if (ListZrspmir.get(indice).getMicmov() == 52) {
							if (ListZrspmir.get(indice).getMicori() == 1 || ListZrspmir.get(indice).getMicori() == 5 || ListZrspmir.get(indice).getMicori() == 8 || ListZrspmir.get(indice).getMicori() == 9) {
								this.wreflo = ListZrspmir.get(indice).getMincup().toString() + ListZrspmir.get(indice).getMizoes();
								this.wreflq = ListZrspmir.get(indice).getMirues() + ListZrspmir.get(indice).getMicoes();
								if (!ListZrspmir.get(indice).getMincab().equals("")) {
									this.micobo = ListZrspmir.get(indice).getMincab().substring(0, 3); 
									if (this.micobo.equals(this.bocad)) {
										this.wreflw = ListZrspmir.get(indice).getMisues() + ListZrspmir.get(indice).getMimoes();
										this.mifecu = ListZrspmir.get(indice).getMidcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMimcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMiacup().toString().substring(0, 2);
										this.wreflv = Integer.parseInt(this.mifecu);
									}else {
										this.wreflz = ListZrspmir.get(indice).getMincab().substring(12, 16);
										this.wreflr = ListZrspmir.get(indice).getMincaf();
									}
								}
								this.ain73 = true;
							}
							if (ListZrspmir.get(indice).getMicori() == 5 && ListZrspmir.get(indice).getMicsmv() == 9) {
								this.ain73 = false;
								this.ain74 = true;
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 12 && ListZrspmir.get(indice).getMicmov() == 20) {
							this.wreflo = "";
						}
						if (!this.wwdrfn.equals("")) {
							if (ListZrspmir.get(indice).getMicori() == 1 || ListZrspmir.get(indice).getMicori() == 24 || ListZrspmir.get(indice).getMicori() == 5 || ListZrspmir.get(indice).getMicori() == 6 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 38 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 39 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 36 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 37 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 58 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 59 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 56 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 57 || ListZrspmir.get(indice).getMicori() == 12) {
								this.ain69 = true;
								this.wrefe = this.agrefe;
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 1 && ListZrspmir.get(indice).getMicmov() == 25) {
							this.wreflo = ListZrspmir.get(indice).getMincup().toString() + ListZrspmir.get(indice).getMizoes();
							this.wreflq = ListZrspmir.get(indice).getMirues() + ListZrspmir.get(indice).getMicoes();
							if (!ListZrspmir.get(indice).getMincab().equals("")) {
								this.micobo = ListZrspmir.get(indice).getMincab().substring(0, 3); 
								if (this.micobo.equals(this.bocad)) {
									this.wreflw = ListZrspmir.get(indice).getMisues() + ListZrspmir.get(indice).getMimoes();
									this.mifecu = ListZrspmir.get(indice).getMidcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMimcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMiacup().toString().substring(0, 2);
									this.wreflv = Integer.parseInt(this.mifecu);
								}else {
									this.wreflz = ListZrspmir.get(indice).getMincab().substring(12, 16);
									this.wreflr = ListZrspmir.get(indice).getMincaf();
								}
							}
							this.wwdrfn = ListZrspmir.get(indice).getMixtrf();
							if (w1mrch.getMIZOES() != 0 && w1mrch.getMIRUES() != 0 && w1mrch.getMICOES() != 0 && w1mrch.getMISUES() != 0 && w1mrch.getMIMOES() != 0 && ListZrspmir.get(indice).getMicori() != 6) {
								wwmrch.setMIZOES(w1mrch.getMIZOES());
								wwmrch.setMIRUES(w1mrch.getMIRUES());
								wwmrch.setMICOES(w1mrch.getMICOES());
								wwmrch.setMISUES(w1mrch.getMISUES());
								wwmrch.setMIMOES(w1mrch.getMIMOES());
							}
							if (!this.wwdrfn.equals("")) {
								if (ListZrspmir.get(indice).getMicori() == 1 || ListZrspmir.get(indice).getMicori() == 24 || ListZrspmir.get(indice).getMicori() == 5 || ListZrspmir.get(indice).getMicori() == 6 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 38 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 39 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 36 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 37 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 58 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 59 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 56 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 57 || ListZrspmir.get(indice).getMicori() == 12) {
									this.ain69 = true;
									this.wrefe = this.agrefe;
								}
							}
							this.wtimp1 = ListZrspmir.get(indice).getMicrcp().add(ListZrspmir.get(indice).getMiimpo());
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								if (!this.mifecu.equals("") && !this.mifecu.equals("0")) {
									this.fecadd = ListZrspmir.get(indice).getMidcup();
									this.fecamm = ListZrspmir.get(indice).getMimcup();
									this.fecaa1 = ListZrspmir.get(indice).getMiccup();
									this.fecaa2 = ListZrspmir.get(indice).getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei27(ds, this.mide40,ListZrspmir.get(indice).getMiimpo(), ListZrspmir.get(indice).getMincuo(), ListZrspmir.get(indice).getMicacu(), ListZrspmir.get(indice).getMixtrf(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd());
								SubRutSlinei25(ds, ListZrspmir.get(indice).getMicrcp(), ListZrspmir.get(indice).getMixtrf(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd());
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 1 && ListZrspmir.get(indice).getMicmov() == 26) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								SubRutSlinei26(ds, ListZrspmir.get(indice).getMiimpo(), ListZrspmir.get(indice).getMixtrf(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd());
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 4 && ListZrspmir.get(indice).getMicmov() == 27) {
							this.wreflo = ListZrspmir.get(indice).getMizoes().toString();
							this.wreflq = ListZrspmir.get(indice).getMirues() + ListZrspmir.get(indice).getMicoes();
							this.wwdrfn = ListZrspmir.get(indice).getMixtrf();
							if (w1mrch.getMIZOES() != 0 && w1mrch.getMIRUES() != 0 && w1mrch.getMICOES() != 0 && w1mrch.getMISUES() != 0 && w1mrch.getMIMOES() != 0 && ListZrspmir.get(indice).getMicori() != 6) {
								wwmrch.setMIZOES(w1mrch.getMIZOES());
								wwmrch.setMIRUES(w1mrch.getMIRUES());
								wwmrch.setMICOES(w1mrch.getMICOES());
								wwmrch.setMISUES(w1mrch.getMISUES());
								wwmrch.setMIMOES(w1mrch.getMIMOES());
							}
							if (!ListZrspmir.get(indice).getMincab().equals("")) {
								this.micobo = ListZrspmir.get(indice).getMincab().substring(0, 3); 
								if (this.micobo.equals(this.bocad)) {
									this.wreflw = ListZrspmir.get(indice).getMisues() + ListZrspmir.get(indice).getMimoes();
									this.mifecu = ListZrspmir.get(indice).getMidcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMimcup().toString().substring(0, 2) + ListZrspmir.get(indice).getMiacup().toString().substring(0, 2);
									this.wreflv = Integer.parseInt(this.mifecu);
								}else {
									
									this.wreflz = ListZrspmir.get(indice).getMincab().substring(12, 16);
									this.wreflr = ListZrspmir.get(indice).getMincaf();
								}
							}
							if (ListZrspmir.get(indice).getMicori() == 12 && ListZrspmir.get(indice).getMicmov() == 20) {
								this.wreflo = "";
							}
							this.wwdrfn = ListZrspmir.get(indice).getMixtrf();
							if (w1mrch.getMIZOES() != 0 && w1mrch.getMIRUES() != 0 && w1mrch.getMICOES() != 0 && w1mrch.getMISUES() != 0 && w1mrch.getMIMOES() != 0 && ListZrspmir.get(indice).getMicori() != 6) {
								wwmrch.setMIZOES(w1mrch.getMIZOES());
								wwmrch.setMIRUES(w1mrch.getMIRUES());
								wwmrch.setMICOES(w1mrch.getMICOES());
								wwmrch.setMISUES(w1mrch.getMISUES());
								wwmrch.setMIMOES(w1mrch.getMIMOES());
							}
							if (!this.wwdrfn.equals("")) {
								if (ListZrspmir.get(indice).getMicori() == 1 || ListZrspmir.get(indice).getMicori() == 24 || ListZrspmir.get(indice).getMicori() == 5 || ListZrspmir.get(indice).getMicori() == 6 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 38 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 39 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 36 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 37 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 58 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 59 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 56 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 57 || ListZrspmir.get(indice).getMicori() == 12) {
									this.ain69 = true;
									this.wrefe = this.agrefe;	
								}
							}
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								if (!this.mifecu.equals("") && !this.mifecu.equals("0")) {
									this.fecadd = ListZrspmir.get(indice).getMidcup();
									this.fecamm = ListZrspmir.get(indice).getMimcup();
									this.fecaa1 = ListZrspmir.get(indice).getMiccup();
									this.fecaa2 = ListZrspmir.get(indice).getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei27(ds, this.mide40,ListZrspmir.get(indice).getMiimpo(), ListZrspmir.get(indice).getMincuo(), ListZrspmir.get(indice).getMicacu(), ListZrspmir.get(indice).getMixtrf(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd());
								this.wlcrcp = ListZrspmir.get(indice).getMicrcp().add(ListZrspmir.get(indice).getMiimpo());
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 4 && ListZrspmir.get(indice).getMicmov() == 28) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								this.fecpin = ListZrspmir.get(indice).getMidpri().toString() + ListZrspmir.get(indice).getMimpri().toString() + ListZrspmir.get(indice).getMiapri().toString();
								SubRutSlinei28(ds, ListZrspmir.get(indice).getMiimpo(), ListZrspmir.get(indice).getMitnoa(), ListZrspmir.get(indice).getMixtrf(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd());
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 4 && ListZrspmir.get(indice).getMicmov() == 29) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								SubRutSlinei29(ds, ListZrspmir.get(indice).getMiimpo(), ListZrspmir.get(indice).getMimini(), ListZrspmir.get(indice).getMixtrf(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd());
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 1 && ListZrspmir.get(indice).getMicmov() == 53) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								if (!this.mifecu.equals("") && !this.mifecu.equals("0")) {
									this.fecadd = ListZrspmir.get(indice).getMidcup();
									this.fecamm = ListZrspmir.get(indice).getMimcup();
									this.fecaa1 = ListZrspmir.get(indice).getMiccup();
									this.fecaa2 = ListZrspmir.get(indice).getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei53(ds, this.mide40, ListZrspmir.get(indice).getMiimpo(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd(), ListZrspmir.get(indice).getMimhab(), ListZrspmir.get(indice).getMicfar());
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 1 && ListZrspmir.get(indice).getMicmov() == 54) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								if (!this.mifecu.equals("") && !this.mifecu.equals("0")) {
									this.fecadd = ListZrspmir.get(indice).getMidcup();
									this.fecamm = ListZrspmir.get(indice).getMimcup();
									this.fecaa1 = ListZrspmir.get(indice).getMiccup();
									this.fecaa2 = ListZrspmir.get(indice).getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei53(ds, this.mide40, ListZrspmir.get(indice).getMiimpo(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd(), ListZrspmir.get(indice).getMimhab(), ListZrspmir.get(indice).getMicfar());
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 1 && ListZrspmir.get(indice).getMicmov() == 56 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 56 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 58 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 52 || ListZrspmir.get(indice).getMicori() == 24 && ListZrspmir.get(indice).getMicmov() == 56) {
							this.ain15 = true;
							if (!this.wconfi.equals("B")) {
								this.totcu2 = ListZrspmir.get(indice).getMiimpo();
								this.totcui = ListZrspmir.get(indice).getMiiorg();
								this.w1desc = this.mide40;
								this.w1ncuo = ListZrspmir.get(indice).getMincuo();
								this.w1cacu = ListZrspmir.get(indice).getMicacu();
								this.ain15 = true;
								if (ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 56 && ListZrspmir.get(indice).getMicsmv() == 0 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 58 && ListZrspmir.get(indice).getMicsmv() == 0 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 52 && ListZrspmir.get(indice).getMicsmv() == 0) {
									this.ain73 = false;
									this.ain74 = true;
								}
								if (ListZrspmir.get(indice).getMicori() == 24 && ListZrspmir.get(indice).getMicmov() == 56 && ListZrspmir.get(indice).getMicsmv() == 9) {
									this.ain73 = false;
									this.ain74 = true;
								}else {
									this.ain73 = false;
								}
								if (!this.wconfi.equals("B")) {
									if (!this.mifecu.equals("") && !this.mifecu.equals("0")) {
										this.fecadd = ListZrspmir.get(indice).getMidcup();
										this.fecamm = ListZrspmir.get(indice).getMimcup();
										this.fecaa1 = ListZrspmir.get(indice).getMiccup();
										this.fecaa2 = ListZrspmir.get(indice).getMiacup();
									}else {
										this.fecaux = 0;
									}
									SubRutSlinei56(ds, this.mide40, ListZrspmir.get(indice).getMincuo(), ListZrspmir.get(indice).getMicacu(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd(), ListZrspmir.get(indice).getMimhab(), ListZrspmir.get(indice).getMicfar());
									this.totcu2 = BigDecimal.ZERO;
								}
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 1 && ListZrspmir.get(indice).getMicmov() == 57 || ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 57 || ListZrspmir.get(indice).getMicori() == 24 && ListZrspmir.get(indice).getMicmov() == 57) {
							this.totcu2 = this.totcu2.add(ListZrspmir.get(indice).getMiimpo());
							this.totcui = this.totcui.add(ListZrspmir.get(indice).getMiiorg());
							this.wimcbo = this.totcui;
							this.ain15 = true;
							if (ListZrspmir.get(indice).getMicori() == 9 && ListZrspmir.get(indice).getMicmov() == 57 && ListZrspmir.get(indice).getMicsmv() == 0) {
								this.ain73 = false;
								this.ain74 = true;
							}
							if (ListZrspmir.get(indice).getMicori() == 24 && ListZrspmir.get(indice).getMicmov() == 57 && ListZrspmir.get(indice).getMicsmv() == 9) {
								this.ain73 = false;
								this.ain74 = true;
							}else {
								this.ain73 = false;
							}
							if (!this.wconfi.equals("B")) {
								if (!this.mifecu.equals("") && !this.mifecu.equals("0")) {
									this.fecadd = ListZrspmir.get(indice).getMidcup();
									this.fecamm = ListZrspmir.get(indice).getMimcup();
									this.fecaa1 = ListZrspmir.get(indice).getMiccup();
									this.fecaa2 = ListZrspmir.get(indice).getMiacup();
								}else {
									this.fecaux = 0;
								}
								SubRutSlinei56(ds, this.mide40, ListZrspmir.get(indice).getMincuo(), ListZrspmir.get(indice).getMicacu(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd(), ListZrspmir.get(indice).getMimhab(), ListZrspmir.get(indice).getMicfar());
								this.totcu2 = BigDecimal.ZERO;
							}
						}
						if (ListZrspmir.get(indice).getMicori() == 1 && ListZrspmir.get(indice).getMicmov() == 40 && ListZrspmir.get(indice).getMicsmv() == 66) {
							this.ain15 = true;
							this.totdes = this.totdes.add(ListZrspmir.get(indice).getMiimpo());
							this.desdes = this.mide40;
							this.ttfecu = Integer.parseInt(this.mifecu);
						}
						if (this.ain15.equals(false)) {
							if (!this.mifecu.equals("") && !this.mifecu.equals("0")) {
								this.fecadd = ListZrspmir.get(indice).getMidcup();
								this.fecamm = ListZrspmir.get(indice).getMimcup();
								this.fecaa1 = ListZrspmir.get(indice).getMiccup();
								this.fecaa2 = ListZrspmir.get(indice).getMiacup();
							}else {
								this.fecaux = 0;
							}
							SubRutSlinei05(ds, ListZrspmir.get(indice).getMincuo(), ListZrspmir.get(indice).getMicacu(), ListZrspmir.get(indice).getMicori(), ListZrspmir.get(indice).getMicmov(), ListZrspmir.get(indice).getMicsmv(), ListZrspmir.get(indice).getMincrd(), ListZrspmir.get(indice).getMimhab(), ListZrspmir.get(indice).getMicfar());
						}
					}
					_mincuo = ListZrspmir.get(indice).getMincuo();
					_micacu = ListZrspmir.get(indice).getMicacu();
					_micori = ListZrspmir.get(indice).getMicori();
					_micmov = ListZrspmir.get(indice).getMicmov();
					_micsmv = ListZrspmir.get(indice).getMicsmv();
					_mincrd = ListZrspmir.get(indice).getMincrd();
					_mimhab = ListZrspmir.get(indice).getMimhab();
					_micfar = ListZrspmir.get(indice).getMicfar();
					indice +=1;
				}//FIN IF
				
				if (indice == indiceNext) {
					indice += 1;
				}
			}//fin WHILE
			if (fc.BigDecimalComparar(this.totdes.toString(), "0", "!=")) {
				this.wimplo = this.totdes;
				this.wdeslo = this.desdes;
				this.fecaux = 0;
				this.ain69 = false;
				SubRutSlinei05(ds, _mincuo, _micacu, _micori, _micmov, _micsmv, _mincrd, _mimhab, _micfar);	
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(MIDE40);
			strx.setTXIMPO(MIIMPO);
			strx.setTXNCUO(MINCUO);
			strx.setTXCACU(MICACU);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			strx.setTXTEFM(BigDecimal.ZERO); //MIPIFG
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
			
			if ( fc.ValidarDdmmAAAA(this.fecpin.toString()) ) {
				strx.setTXFMOVC(Integer.parseInt((new SimpleDateFormat("yyyyMMdd").parse(fecpin.toString())).toString()));
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(MIDE40);
			strx.setTXIMPO(MIIMPO);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(MIDE40);
			
			if (this.ain74 == true) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.totcu2);
			strx.setTXNCUO(MINCUO);
			strx.setTXCACU(MICACU);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			if (this.ain69) {
				strx.setTXFMT(strx.getTXFMT().trim() + "R");
			}
			strx.setTXDESC(this.wdeslo);
			
			if (this.ain74 == true) {
				strx.setTXDESC(strx.getTXDESC().trim() + " CANCELACIÓN ANTICIPADA");
			}
			strx.setTXIMPO(this.wimplo);
			strx.setTXNCUO(MINCUO);
			strx.setTXCACU(MICACU);
			strx.setTXREFC(this.wwdrfn);
			this.wwmrch.ObjectToString();
			strx.setTXMRCH(this.wwmrch.getResultado());
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
			ListZrspmir = myDAOZrspmir.getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedictAndMimini(ds, this.sstmhdr.getMeyfac().toString(), this.sstmhdr.getMeaafc().toString(), this.sstmhdr.getMecifa(), this.sstmhdr.getMeagig(), this.aaorgn.toString(), this.sstmhdr.getMelogo().toString(), this.sstmhdr.getMencct(), this.sstmhdr.getMencct().substring(6-1, 15), this.sstmhdr.getMencct().substring(16-1, 19), "3");
			
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
				if (!this.mifecu.equals("") && !this.mifecu.equals("0")) {
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
				this.wwmrch = new ZRSTEMMVWWMRCH();
				this.wmone = "";
				this.wreflo = o.getMincab();
				this.wreflo = o.getMinlcc().toString();
				this.wrefe = this.agrefe;
				this.wwdrfn = o.getMixtrf();
				
				w1mrch = null;
				w1mrch.setMIZOES(o.getMizoes());
				w1mrch.setMIRUES(o.getMirues());
				w1mrch.setMICOES(o.getMicoes());
				w1mrch.setMISUES(o.getMisues());
				w1mrch.setMIMOES(o.getMimoes());
				if (w1mrch.getMIZOES() != 0 && w1mrch.getMIRUES() != 0 && w1mrch.getMICOES() != 0 && w1mrch.getMISUES() != 0 && w1mrch.getMIMOES() != 0 && o.getMicori() != 6) {
					wwmrch.setMIZOES(w1mrch.getMIZOES());
					wwmrch.setMIRUES(w1mrch.getMIRUES());
					wwmrch.setMICOES(w1mrch.getMICOES());
					wwmrch.setMISUES(w1mrch.getMISUES());
					wwmrch.setMIMOES(w1mrch.getMIMOES());
				}
				this.wmone = "USD";
				if (o.getMicori() == 24 && o.getMicmov() == 58 || o.getMicori() == 9 && o.getMicmov() == 58) {
					this.wtotar = this.wtotar.add(o.getMiimpo());
					this.wtctai = this.wtctai.add(o.getMiimpo());
					if (o.getMincuo() == 1 && o.getMicori() == 24) {
						this.aucuol = o.getMicacu();
						this.aucaol = o.getMiimpo().add(o.getMicrcp());
					}
					if (this.sstmhdr.getMecacl().compareTo("B") == 1 ) {
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
			BigDecimal MIIORG,
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
			if (fc.BigDecimalComparar(MIIMPO.toString(), MIIORG.toString(), "==")) {
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
	
	
	//Funcion $FMT
	public void buscar$FMT01(Integer TXCORI1, Integer TXCMOV1, Integer TXCSMV1, Integer TXCORI2, Integer TXCMOV2, Integer TXCSMV2){
	    int bound = this.sfmt.size();
	    int next = 0;
		for (int Ind = 0; Ind < bound; Ind++) {
	        if (this.sfmt.get(Ind).TXCORI == TXCORI1 && this.sfmt.get(Ind).TXCMOV == TXCMOV1 && this.sfmt.get(Ind).TXCSMV == TXCSMV1) {
	        	next = Ind + 1;
	        	if (this.sfmt.get(next).TXCORI == TXCORI2 && this.sfmt.get(next).TXCMOV == TXCMOV2 && this.sfmt.get(next).TXCSMV == TXCSMV2 && this.sfmt.get(Ind).TXIMPO == this.sfmt.get(next).TXIMPO.negate()) {
	        		this.sfmt.remove(this.sfmt.get(next));
	        		this.sfmt.remove(this.sfmt.get(Ind));
	        		this.sind = this.sind - 2; 
				}
	        }
		 }
	}
	
	public void buscar$FMT02(Integer TXCORI1, Integer TXCMOV1, Integer TXCORI2, Integer TXCMOV2, Integer TXCORI3, Integer TXCMOV3, Integer TXCORI4, Integer TXCMOV4, Integer TXCORI5, Integer TXCMOV5){
	    int bound = this.sfmt.size() -1;
	    if (this.sfmt.get(bound).TXCORI == TXCORI1 && this.sfmt.get(bound).TXCMOV == TXCMOV1 ||
	    		this.sfmt.get(bound).TXCORI == TXCORI2 && this.sfmt.get(bound).TXCMOV == TXCMOV2 ||
	    		this.sfmt.get(bound).TXCORI == TXCORI3 && this.sfmt.get(bound).TXCMOV == TXCMOV3 ||
	    		this.sfmt.get(bound).TXCORI == TXCORI4 && this.sfmt.get(bound).TXCMOV == TXCMOV4 ||
	    		this.sfmt.get(bound).TXCORI == TXCORI5 && this.sfmt.get(bound).TXCMOV == TXCMOV5) {
	    	this.sfmt.remove(this.sfmt.get(bound));
    		this.sind = this.sind - 1;
	    }
	}
	
	public void buscar$FMT03(Integer TXCORI1, Integer TXCMOV1, Integer TXCORI2, Integer TXCMOV2){
	    int bound = this.sfmt.size() -1;
	    if (this.sfmt.get(bound).TXCORI == TXCORI1 && this.sfmt.get(bound).TXCMOV == TXCMOV1 ||
	    		this.sfmt.get(bound).TXCORI == TXCORI2 && this.sfmt.get(bound).TXCMOV == TXCMOV2 ) {
	    	this.sfmt.remove(this.sfmt.get(bound));
    		this.sind = this.sind - 1;
	    }
	}
	
	
	public class ZRSTEMMVSTRX {
		String TXFMT = "";
		String TXMINI = "";
		String TXFILE = "";
		String TXLOIN = "";
		//--String TXDESC = "";
		String TXDESC = "";
		BigDecimal TXIMPO = new BigDecimal(0);
		String TXREFC = "";
		String TXMRCH = "";
		//--Integer TXCORI = 0;
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
	
	private class ZRSTEMMVLXREFP {
		
		//para registro ZRSPPIR
		String TINPTR = "";
		Integer TINCAX = 0;
		Integer TICRDD = 0;
		Integer TICRMM = 0;
		Integer TICRAA = 0;
		
		//para registro ZRSPPLR
		String TLNPTR = "";
		Integer TLNCAX = 0;
		Integer TLCRDD = 0;
		Integer TLCRMM = 0;
		Integer TLCRAA = 0;
		String Resultado = "";
		
		public void ObjectToString() {
			if (!this.TINPTR.equals("") && this.TINPTR != null) {
				this.Resultado += TINPTR.toString();
			}
			if (this.TINCAX != 0 && this.TINCAX != null) {
				this.Resultado += TINCAX.toString();
			}
			if (this.TICRDD != 0 && this.TICRDD != null) {
				this.Resultado += TICRDD.toString();
			}
			if (this.TICRMM != 0 && this.TICRMM != null) {
				this.Resultado += TICRMM.toString();
			}
			if (this.TICRAA != 0 && this.TICRAA != null) {
				this.Resultado += TICRAA.toString();
			}
			if (!this.TLNPTR.equals("") && this.TLNPTR != null) {
				this.Resultado += TLNPTR.toString();
			}
			if (this.TLNCAX != 0 && this.TLNCAX != null) {
				this.Resultado += TLNCAX.toString();
			}
			if (this.TLCRDD != 0 && this.TLCRDD != null) {
				this.Resultado += TLCRDD.toString();
			}
			if (this.TLCRMM != 0 && this.TLCRMM != null) {
				this.Resultado += TLCRMM.toString();
			}
			if (this.TLCRAA != 0 && this.TLCRAA != null) {
				this.Resultado += TLCRAA.toString();
			}
		}
		

		public String getTINPTR() {
			return TINPTR;
		}
		public void setTINPTR(String tINPTR) {
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
		public String getTLNPTR() {
			return TLNPTR;
		}
		public void setTLNPTR(String tLNPTR) {
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


		public String getResultado() {
			return Resultado;
		}


		public void setResultado(String resultado) {
			Resultado = resultado;
		}
		
		


		
	}
	
	private class ZRSTEMMVWWMRCH{
		Integer MIZOES = 0;
		Integer MIRUES = 0;
		Integer MICOES = 0;
		Integer MISUES = 0;
		Integer MIMOES = 0;
		
		Integer MLZOES = 0;
		Integer MLRUES = 0;
		Integer MLCOES = 0;
		Integer MLSUES = 0;
		Integer MLMOES = 0;
		
		String Resultado = "";
		
		public void ObjectToString() {
			if (this.MIZOES != 0 && this.MIZOES != null) {
				this.Resultado += MIZOES.toString();
			}
			if (this.MIRUES != 0 && this.MIRUES != null) {
				this.Resultado += MIRUES.toString();
			}
			if (this.MICOES != 0 && this.MICOES != null) {
				this.Resultado += MICOES.toString();
			}
			if (this.MISUES != 0 && this.MISUES != null) {
				this.Resultado += MISUES.toString();
			}
			if (this.MIMOES != 0 && this.MIMOES != null) {
				this.Resultado += MIMOES.toString();
			}
			
			if (this.MLZOES != 0 && this.MLZOES != null) {
				this.Resultado += MLZOES.toString();
			}
			if (this.MLRUES != 0 && this.MLRUES != null) {
				this.Resultado += MLRUES.toString();
			}
			if (this.MLCOES != 0 && this.MLCOES != null) {
				this.Resultado += MLCOES.toString();
			}
			if (this.MLSUES != 0 && this.MLSUES != null) {
				this.Resultado += MLSUES.toString();
			}
			if (this.MLMOES != 0 && this.MLMOES != null) {
				this.Resultado += MLMOES.toString();
			}
		}
		
		public Integer getMIZOES() {
			return MIZOES;
		}
		public void setMIZOES(Integer mIZOES) {
			MIZOES = mIZOES;
		}
		public Integer getMIRUES() {
			return MIRUES;
		}
		public void setMIRUES(Integer mIRUES) {
			MIRUES = mIRUES;
		}
		public Integer getMICOES() {
			return MICOES;
		}
		public void setMICOES(Integer mICOES) {
			MICOES = mICOES;
		}
		public Integer getMISUES() {
			return MISUES;
		}
		public void setMISUES(Integer mISUES) {
			MISUES = mISUES;
		}
		public Integer getMIMOES() {
			return MIMOES;
		}
		public void setMIMOES(Integer mIMOES) {
			MIMOES = mIMOES;
		}
		public Integer getMLZOES() {
			return MLZOES;
		}
		public void setMLZOES(Integer mLZOES) {
			MLZOES = mLZOES;
		}
		public Integer getMLRUES() {
			return MLRUES;
		}
		public void setMLRUES(Integer mLRUES) {
			MLRUES = mLRUES;
		}
		public Integer getMLCOES() {
			return MLCOES;
		}
		public void setMLCOES(Integer mLCOES) {
			MLCOES = mLCOES;
		}
		public Integer getMLSUES() {
			return MLSUES;
		}
		public void setMLSUES(Integer mLSUES) {
			MLSUES = mLSUES;
		}
		public Integer getMLMOES() {
			return MLMOES;
		}
		public void setMLMOES(Integer mLMOES) {
			MLMOES = mLMOES;
		}

		public String getResultado() {
			return Resultado;
		}

		public void setResultado(String resultado) {
			Resultado = resultado;
		}
		
		
		
	}
	
	private class ZRSTEMMVW1MRCH {
		Integer MIZOES = 0;
		Integer MIRUES = 0;
		Integer MICOES = 0;
		Integer MISUES = 0;
		Integer MIMOES = 0;
		
		Integer MLZOES = 0;
		Integer MLRUES = 0;
		Integer MLCOES = 0;
		Integer MLSUES = 0;
		Integer MLMOES = 0;
		
		//**constructor para cada tabla MI ML BUSCAR EN FER1020 RETORNA FICHA CHEQUEAR ADAPTER TAR
		
		public Integer getMIZOES() {
			return MIZOES;
		}
		public void setMIZOES(Integer mIZOES) {
			MIZOES = mIZOES;
		}
		public Integer getMIRUES() {
			return MIRUES;
		}
		public void setMIRUES(Integer mIRUES) {
			MIRUES = mIRUES;
		}
		public Integer getMICOES() {
			return MICOES;
		}
		public void setMICOES(Integer mICOES) {
			MICOES = mICOES;
		}
		public Integer getMISUES() {
			return MISUES;
		}
		public void setMISUES(Integer mISUES) {
			MISUES = mISUES;
		}
		public Integer getMIMOES() {
			return MIMOES;
		}
		public void setMIMOES(Integer mIMOES) {
			MIMOES = mIMOES;
		}
		public Integer getMLZOES() {
			return MLZOES;
		}
		public void setMLZOES(Integer mLZOES) {
			MLZOES = mLZOES;
		}
		public Integer getMLRUES() {
			return MLRUES;
		}
		public void setMLRUES(Integer mLRUES) {
			MLRUES = mLRUES;
		}
		public Integer getMLCOES() {
			return MLCOES;
		}
		public void setMLCOES(Integer mLCOES) {
			MLCOES = mLCOES;
		}
		public Integer getMLSUES() {
			return MLSUES;
		}
		public void setMLSUES(Integer mLSUES) {
			MLSUES = mLSUES;
		}
		public Integer getMLMOES() {
			return MLMOES;
		}
		public void setMLMOES(Integer mLMOES) {
			MLMOES = mLMOES;
		}
		
		
		
	}

}
//////////////////////////////////////////////1084-1273-1465 - 1626 - 1869 - 2377 - 2509 2657 3516 3679 3743 4158 4303 4903