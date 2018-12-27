package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.bz.legacy.CgrrcompBzService.CgrrcompAdapter;
import com.ciessa.museum.bz.legacy.Cus060BzService.CUS060Adapter;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.CbipticDAO;
import com.ciessa.museum.dao.legacy.Cfp001005DAO;
import com.ciessa.museum.dao.legacy.Cfp001205DAO;
import com.ciessa.museum.dao.legacy.Cfp001210DAO;
import com.ciessa.museum.dao.legacy.CompcmoDAO;
import com.ciessa.museum.dao.legacy.GrmriaDAO;
import com.ciessa.museum.dao.legacy.SaldomDAO;
import com.ciessa.museum.dao.legacy.Tap002wDAO;
import com.ciessa.museum.dao.legacy.Tap003DAO;
import com.ciessa.museum.dao.legacy.Tap005bDAO;
import com.ciessa.museum.dao.legacy.Tap901DAO;
import com.ciessa.museum.dao.legacy.Tap902DAO;
import com.ciessa.museum.dao.legacy.TransmDAO;
import com.ciessa.museum.dao.legacy.ZbhpvrzDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cbiptic;
import com.ciessa.museum.model.legacy.Cfp001005;
import com.ciessa.museum.model.legacy.Cfp001205;
import com.ciessa.museum.model.legacy.Cfp001210;
import com.ciessa.museum.model.legacy.Compcmo;
import com.ciessa.museum.model.legacy.Grmria;
import com.ciessa.museum.model.legacy.Saldom;
import com.ciessa.museum.model.legacy.Tap002w;
import com.ciessa.museum.model.legacy.Tap003;
import com.ciessa.museum.model.legacy.Tap005b;
import com.ciessa.museum.model.legacy.Tap901;
import com.ciessa.museum.model.legacy.Tap902;
import com.ciessa.museum.model.legacy.Transm;
import com.ciessa.museum.model.legacy.Zbhpvrz;

public class FER0310View03BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(FER0310View03BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	Tap002wDAO myDAOTap002w;
	
	@Autowired
	CompcmoDAO myDAOCompcmo;
	
	@Autowired
	GrmriaDAO myDAOGrmria;
	
	@Autowired
	ZbhpvrzDAO myDAOZbhpvrz;
	
	@Autowired
	Cfp001205DAO myDAOCfp001205;
	
	@Autowired
	Cfp001210DAO myDAOCfp001210;
	
	@Autowired
	Cfp001005DAO myDAOCfp001005;
	
	@Autowired
	CbipticDAO myDAOCbiptic;
	
	@Autowired
	Tap902DAO myDAOTap902;
	
	@Autowired
	SaldomDAO myDAOSaldom;
	
	@Autowired
	TransmDAO myDAOTransm;
	
	@Autowired
	Tap003DAO myDAOTap003;
	
	@Autowired
	Tap901DAO myDAOTap901;
	
	@Autowired
	Tap005bDAO myDAOTap005b;
	
	
	Tap002w objTap002w = new Tap002w();
	Compcmo objCompcmo = new Compcmo();
	Zbhpvrz objZbhpvrz = new Zbhpvrz();
	Cfp001205 objCfp001205 = new Cfp001205();
	Cfp001210 objCfp001210 = new Cfp001210();
	Cfp001005 objCfp001005 = new Cfp001005();
	Cbiptic objCbiptic = new Cbiptic();
	Tap902 objTap902 = new Tap902();
	Saldom objSaldom = new Saldom();
	
	
	
	
	List<Grmria> listGrmria = null;
	List<Tap902> listTap902 = null;
	List<Transm> listTransm = null;
	List<Tap003> listTap003 = null;
	List<Tap901> listTap901 = null;
	List<Tap005b> listTap005b = null;
	
	
	String rpta = "";
	String acctno = "";
	String acctan = "";
	Integer ames = 0;
	String bknum = "001";
	String wkbank = "";
	String wkacct = "";
	String dsmkey = "";
	String swtaky = "";
	String swtaac = "";
	String swtaty = "";
	String dmbt25 = "";
	String dmbt23 = "";
	BigDecimal dmpbal = new BigDecimal(0);
	Integer wshmes = 0;
	Integer aaamm = 0;
	Long dmacct = new Long("0");
	Integer wswavs = 0;
	String rqprcd = "";
	String azbh = "";
	Long rqactn = new Long("0");
	Integer dmdopn = 0;
	Integer dmdla = 0;
	Integer dmdlst = 0;
	Integer dmdld1 = 0;
	Integer codmon = 0;
	BigDecimal prtacc = new BigDecimal(0);
	String wkalt = "";
	Integer hssec = 0;
	Integer wkssno = 0;
	Integer wkhmph = 0;
	Integer wkbuph = 0;
	Integer dmdint = 0;
	Integer dmssno = 0;
	Integer dmhpnr = 0;
	Integer dmbunr = 0;
	Integer[] cfpi = { 0, 0, 0, 0};
	Long[] cfpb = new Long[5];
	String bcrkey = "";
	Integer m = 0;
	BigDecimal[] rate = new BigDecimal[4];
	Integer fld5 = 0;
	BigDecimal[] dsrt = new BigDecimal[10];
	Integer toaccr = 0;
	String accctl = "";
	Integer scal6 = 0;
	Integer dmpthr = 0;
	String wktitl = "";
	BigDecimal wsbal = new BigDecimal(0);
	Long float0 = new Long(0);
	Long float1 = new Long(0);
	long flt1 = new Long(0);
	long flt2 = new Long(0);
	long flt3 = new Long(0);
	long flt4 = new Long(0);
	long flt5 = new Long(0);
	BigDecimal dmcol0 = new BigDecimal(0);
	BigDecimal dmcol1 = new BigDecimal(0);
	Integer dmald = 0;
	String aacta1 = "";
	String aacta2 = "";
	Integer dshbk = 0;
	Integer dshdsv = 0;
	Long dsacct = new Long(0);
	Integer dmbk = 0;
	String na1 = "";
	String na2 = "";
	String na3 = "";
	String na4 = "";
	String na5 = "";
	String na6 = "";
	Integer wkappl = 0;
	Integer frjul = 0;
	String sper = "";
	Integer sfrq = 0;
	Integer srday = 0;
	Integer dmacc5 = 0;
	Integer tojul = 0;
	Integer dmaccr = 0;
	BigDecimal dmnsfa = new BigDecimal(0);
	Integer dmnsff = 0;
	Integer srxctr = 0;
	BigDecimal dmybal = new BigDecimal(0);
	Integer dmdrt = 0;
	Integer dmcrt = 0;
	Integer dmdrti = 0;
	Integer dmcrti = 0;
	Integer dmcktd = 0;
	Long dmcf2 = new Long(0);
	Long dmcf3 = new Long(0);
	Long dmcf4 = new Long(0);
	Long dmcf5 = new Long(0);
	Integer dmdtdy = 0;
	Integer dmctdy = 0;
	Integer dmsofd = 0;
	Integer dmsofc = 0;
	Integer dmiapa = 0;
	Integer dmiada = 0;
	Integer dmfrst = 0;
	Integer dmlnsf = 0;
	Integer dmlsc = 0;
	String dmactv = "";
	Integer dmttrn = 0; 
	Integer dmmwd = 0; 
	String dmeft = "";
	Integer dmmtda = 0;
	Integer dmmtdc = 0;
	Integer dmmtdd = 0;
	Integer dmmdri = 0;
	Integer dmmcri = 0;
	Integer dmmcrd = 0;
	Integer dmmnsf = 0;
	Integer dmmnsp = 0;
	Integer dmmodm = 0;
	Integer dmkmo = 0;
	Integer dmsctm = 0;
	Integer dmexsc = 0;
	Integer fld3m = 0;
	Integer dmlyav = 0;
	Integer dmytda = 0;
	Integer dmytdc = 0;
	Integer dmytdd = 0;
	Integer dmnsfl = 0;
	Integer dmnsfy = 0;
	Integer dmkite = 0;
	Integer dmnsfp = 0;
	Integer dmodly = 0;
	Integer dmoddy = 0;
	BigDecimal dmodiy = new BigDecimal(0);
	BigDecimal dmipdy = new BigDecimal(0);
	Integer dmyipd = 0;
	BigDecimal dmwhly = new BigDecimal(0);
	BigDecimal dmwhty = new BigDecimal(0);
	Integer dmyacc = 0;
	String cfpbfg = "";
	BigDecimal dmpsbl = new BigDecimal(0);
	BigDecimal dmpscr = new BigDecimal(0);
	BigDecimal dmpsdr = new BigDecimal(0);
	Integer dmpsci = 0;
	Integer dmpsdi = 0;
	BigDecimal dmpssc = new BigDecimal(0);
	BigDecimal dmpsin = new BigDecimal(0);
	BigDecimal dmpsip = new BigDecimal(0);
	BigDecimal dmpsiw = new BigDecimal(0);
	Integer dmpslt = 0;
	Integer dmcra = 0;
	Integer dmdra = 0;
	Integer dmcri = 0;
	Integer dmdri = 0;
	Integer dmscst = 0;
	Integer dmints = 0;
	Integer dmintp = 0;
	Integer dminws = 0;
	Integer dmencd = 0;
	Integer dmencc = 0;
	String dmefff = "";
	Integer dmlast = 0;
	Integer accr3 = 0;
	Integer accr4 = 0;
	Integer toacc = 0;
	Integer srrslt = 0;
	Integer wkcday = 0;
	Integer dyslst = 0;
	BigDecimal wkclbl = new BigDecimal(0);
	BigDecimal wkcbal = new BigDecimal(0);
	Long dmaggb = new Long(0);
	Long dmagcb = new Long(0);
	Integer dmaggd = 0;
	Integer xxodi = 0;
	Integer dmagid = 0;
	BigDecimal dmaggi = new BigDecimal(0);
	Integer wkcint = 0;
	BigDecimal wkaccr = new BigDecimal(0);
	BigDecimal wkacc2 = new BigDecimal(0);
	Integer i = 0;
	Integer inftr = 0;
	Long wkxxbl = new Long(0);
	BigDecimal wkrsv = new BigDecimal(0);
	BigDecimal dmtacc = new BigDecimal(0);
	BigDecimal wkjbl2 = new BigDecimal(0);
	Integer tocal = 0;
	String swork4 = "";
	Integer sy = 0;
	Integer sres3 = 0;
	Integer test = 0;
	Integer sm = 0;
	Integer sd = 0;
	Integer[] sjl5 = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
	Integer dtefmt = 1;
	Integer frcal = 0;
	Integer sfrqx = 0;
	Integer sres5 = 0;
	Integer sres3x = 0;
	Integer[] sdim = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	Integer swork7 = 0;
	Integer swrk7f = 0;
	Integer swrk71 = 0;
	Integer swrk7t = 0;
	Integer inftr1 = 0;
	BigDecimal wkaccx = new BigDecimal(0);
	Integer yyyy = 0;
	String srcvt = "";
	Integer wqextr = 0;
	String divisi = "";
	Integer fvtolm = 0;
	String[] cky = new String[19];
	String tipcta = "" ;
	BigDecimal sreten = new BigDecimal(0);
	BigDecimal ascrha = new BigDecimal(0);
	BigDecimal wsdisp = new BigDecimal(0);
	BigDecimal asdisp = new BigDecimal(0);
	BigDecimal wsinmo = new BigDecimal(0);
	Long ancta = new Long(0);
	Integer ancta1 = 0;
	Long dmccaw = new Long(0);
	BigDecimal scrpen = new BigDecimal(0);
	BigDecimal wsinte = new BigDecimal(0);
	BigDecimal disdbu = new BigDecimal(0);
	BigDecimal discbu = new BigDecimal(0);
	BigDecimal disda = new BigDecimal(0);
	BigDecimal disca = new BigDecimal(0);
	BigDecimal acrhoy = new BigDecimal(0);
	BigDecimal adbpen = new BigDecimal(0);
	BigDecimal scrhoy = new BigDecimal(0);
	BigDecimal sdbhoy = new BigDecimal(0);
	BigDecimal sdbpen = new BigDecimal(0);
	BigDecimal adbhoy = new BigDecimal(0);
	BigDecimal areten = new BigDecimal(0);
	Integer afecvt = 0;
	Integer fecvto = 0;
	Integer aaplic = 0;
	Integer t = 0;
	String segmen = "";
	BigDecimal tipcam = new BigDecimal(0);
	Integer acmcn = 0;
	Integer amtyp = 0;
	Integer ldbank = 1;
	Integer acodr = 1;
	BigDecimal dmcbal = new BigDecimal(0);
	BigDecimal stope = new BigDecimal(0);
	Long[] sr5 = new Long[9];
	BigDecimal wsdact = new BigDecimal(0);
	BigDecimal wmmacc = new BigDecimal(0);
	BigDecimal wdspin = new BigDecimal(0);
	String tpbloq = "";
	String fecpas = "";
	String[] tabest = { " ", "2", "3", "4", "5", "7", "A", "B", "C", "D", "E", "P", "N", "O" };
	String[] tabdes = {"NORMAL", "CUENTA PURGED", "NO CHECK STATUS", "CUENTA CERRADA", "DORMAN", "CLOSED TO ALL POSTING", "DOMICILIO DESCONOCIDO", "CUENTA EMBARGADA", "CUENTA BLOQUEADA", "CTA.CERRADA C/SALDO", "EN TRAMITE DE CIERRE", "CTA.PEND.NON ACCRUAL", "CUENTA NON ACCRUAL", "CUENTA WRITE OFF"};
	Integer fecamd = 0;
	String dfpas = "";
	String mfpas = "";
	String afpas = "";
	Integer[] e = { 31,28,31,30,31,30,31,31,30,31,30,31 };
	Integer finmes = 0;
	Integer qdias = 0;
	BigDecimal wwint = new BigDecimal(0);
	BigDecimal wpryin = new BigDecimal(0);
	Integer aano = 0;
	Integer resto = 0;
	Integer adia = 0;
	Integer fecval = 0;
	Integer fecdia = 0;
	Integer fecing = 0;
	BigDecimal aimpor = new BigDecimal(0);
	BigDecimal sdbctk = new BigDecimal(0);
	Integer abnk1 = 0;
	String atpcta = "";
	Long acuen1 = new Long(0);
	String afill1 = "";
	String afill2 = "";
	Integer fecimn = 0;
	Integer csamd8 = 0;
	String numcue = "";
	String tipope = "";
	String wrevi = "";
	String aabar = "";
	Integer rr1 = 0;
	Integer wsrnbr = 0;
	Integer wtbatc = 0;
	Integer prdate = 0;
	BigDecimal wkbal = new BigDecimal(0);
	Integer wsdate = 0;
	Integer wbatch = 0;
	Integer dhitc = 0;
	Long dhser = new Long("0");
	BigDecimal wkdr = new BigDecimal(0);
	BigDecimal wkcr = new BigDecimal(0);
	
	
	
	
	
	Integer fechai = 0;
	Integer anoi = 0;
	BigDecimal dhamt = new BigDecimal(0);
	Integer anod = 0;
	Integer mesd = 0;
	Integer mesi = 0;
	Integer diad = 0;
	Integer diai = 0;
	Integer fechad = 0;
	Integer wsuc1 = 0;
	Integer wnbat1 = 0;
	
	
	
	
	
	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
	@Autowired
	Cus060BzService Cus060;
	
	CUS060Adapter cu060adapter = null;
	
	@Autowired
	CgrrcompBzService Cgrrcomp;
	
	CgrrcompAdapter cgrrcompadapter = null;
	
	FER0310V03Adapter adapter = null;	
	
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			this.acctno = obtainStringValue("acctno", null);
			
			
			this.acctan = this.acctno;
			this.ames = Integer.parseInt(this.fc.FormatoFechaHora("MM"));
			this.aaamm = this.ames - 1;
			this.azbh = "Y";
			Arrays.fill(this.dsrt, BigDecimal.ZERO);
			rpta += SubRutLeemae(ds);
			if (objTap002w != null) {
				rpta += SubRutDispon(ds);
				if ( new Long(this.acctno) < new Long("5000000000")) {
					rpta += SubRutComput(ds);
				}
				rpta += SubRutInstmt(ds);
			}
			
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			adapter = new FER0310V03Adapter();
			adapter.setDMACCT(this.dmacct);
			adapter.setPRDATE(this.prdate);
			adapter.setNA1(this.na1);
			adapter.setDMDLST(this.dmdlst);
			adapter.setDMPBAL(this.dmpbal);
			adapter.setNA2(this.na2);
			adapter.setDMCRI(this.dmcri);
			adapter.setDMCRA(this.dmcra);
			adapter.setNA3(this.na3);
			adapter.setDMDRI(this.dmdri);
			adapter.setDMDRA(this.dmdra);
			adapter.setNA4(this.na4);
			adapter.setDMSCST(this.dmscst);
			adapter.setNA5(this.na5);
			adapter.setDMINTS(this.dmints);
			adapter.setNA6(this.na6);
			adapter.setDMCBAL(this.dmcbal);
			
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
					"DMACCT",
					"PRDATE",
					"NA1",
					"DMDLST",
					"DMPBAL",
					"NA2",
					"DMCRI",
					"DMCRA",
					"NA3",
					"DMDRI",
					"DMDRA",
					"NA4",
					"DMSCST",
					"NA5",
					"DMINTS",
					"NA6",
					"DMCBAL"
			};
			returnValue = getJSONRepresentationFromObject(adapter, fields);
			
			
			
			
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
	
	private String SubRutLeemae(DataSet ds) {
		try {
			
			this.wkbank = this.bknum;
			if ( new Long(this.acctno) < new Long("5000000000")) {
				this.wkacct = "06" + this.acctno;
			}
			else {
				this.wkacct = "01" + this.acctno;
			}
			this.dsmkey = this.bknum + this.wkacct.substring(2-1,12);
			SubRutMfproc(ds);
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}
	
	private String SubRutMfproc(DataSet ds) {
		try {
			this.swtaky = this.dsmkey;
			this.swtaac = this.swtaky.substring(5-1, 14);
			this.swtaty = this.swtaky.substring(4-1,5-1);
			objTap002w = myDAOTap002w.getUsingTipoAndCuenta(ds, this.swtaty, this.swtaac);
			this.dmbt25 = objTap002w.getDmbt25();
			this.dmbt23 = objTap002w.getDmbt23();
			this.dmpbal = objTap002w.getDmpbal();
			if (objTap002w != null) {
				this.wshmes = 0;
				this.dmacct = objTap002w.getDmacct();
				if (objTap002w.getDmtyp() == 6) {
					objCompcmo = myDAOCompcmo.getUsingDmacctAndAaamm(ds, objTap002w.getDmacct().toString(), aaamm.toString());
					if (objCompcmo != null) {
						this.wshmes = objCompcmo.getHusmes();
					}
				}
				
				this.wswavs = 0;
				if (objTap002w.getDmtyp() == 1) {
					this.rqprcd = "IA";
				}else {
					this.rqprcd = "CA";
				}
				if (this.azbh.equals("Y")) {
					this.rqactn = objTap002w.getDmacct();
					listGrmria = myDAOGrmria.getUsingRqprcdAndRqactn(ds, this.rqprcd, this.rqactn.toString());
					for (Grmria o: listGrmria) {
						objZbhpvrz = myDAOZbhpvrz.getUsingRqrmcn(ds, o.getRqrmcn().toString());
						if (objZbhpvrz != null) {
							if (this.wswavs == 0 || objZbhpvrz.getVzasco() > this.wswavs) {
								this.wswavs = objZbhpvrz.getVzasco();
							}
						}
					}
				}

				this.dmdopn = Integer.parseInt( new SimpleDateFormat("ddMMyy").format(new SimpleDateFormat("yyMMdd").parse(objTap002w.getDmdopn().toString())) );
				this.dmdla = Integer.parseInt( new SimpleDateFormat("ddMMyy").format(new SimpleDateFormat("yyMMdd").parse(objTap002w.getDmdla().toString())) );
				this.dmdlst = Integer.parseInt( new SimpleDateFormat("ddMMyy").format(new SimpleDateFormat("yyMMdd").parse(objTap002w.getDmdlst().toString())) );
				this.dmdld1 = objTap002w.getDmdld();
				this.codmon = objTap002w.getDmcmcn();
				this.prtacc = objTap002w.getDmtacc();
				this.wkalt = "";
				this.wkbank = this.bknum;
				
				if (new Long(this.acctno) < new Long("5000000000")) {
					this.wkacct = "06" + this.acctno;
				}
				else {
					this.wkacct = "01" + this.acctno;
				}

				this.dsmkey = this.bknum.concat( this.wkacct);
				SubRutGtcust(ds);
				this.hssec = this.wkssno;
				this.dmssno = this.hssec;
				this.dmhpnr = this.wkhmph;
				this.dmbunr = this.wkbuph;
				if (objTap002w.getDmdint() != 0) {
					objCfp001205 = myDAOCfp001205.getUsingKeyDmdint(ds, objTap002w.getDmdint());
					if (objCfp001205 != null) {
						this.dmdint = 0;
						this.cfpi[1] = Integer.parseInt(objCfp001205.getCfpi().substring(1-1,2));
						this.cfpi[2] = Integer.parseInt(objCfp001205.getCfpi().substring(3-1,4));
						this.cfpi[3] = Integer.parseInt(objCfp001205.getCfpi().substring(5-1,6));
						this.cfpi[4] = Integer.parseInt(objCfp001205.getCfpi().substring(7-1,8));

						this.cfpb[1] = (long) objCfp001205.getCfpb1();
						this.cfpb[2] = (long) objCfp001205.getCfpb2();
						this.cfpb[3] = (long) objCfp001205.getCfpb3();
						this.cfpb[4] = (long) objCfp001205.getCfpb4();
						
						this.bcrkey = "";
						this.bcrkey = "1";
						this.m = 0;
						 Arrays.fill(this.rate, BigDecimal.ZERO);
						while (this.m < 4) {
							this.m += 1;
							if (this.m <= 4) {
								//*CFPI
								this.fld5 = Integer.parseInt(this.bknum)*100+ cfpi[this.m]; 
								this.bcrkey = this.fld5.toString();
								if (objCfp001205.getCfpyrb().equals("1")) {
									this.rate[this.m] = this.dsrt[1].divide(new BigDecimal(36));
								}else {
									this.rate[this.m] = this.dsrt[1].divide(new BigDecimal(36.5));
								}
							}
						}
					}
				}
				this.bcrkey = " ";
				Date date = new Date();
				DateFormat d = new SimpleDateFormat("yyyyD"); 
				this.toaccr = Integer.parseInt(d.format(date)); 

				this.accctl = "A";
				
				SubRutAccr01(ds, objTap002w);
				SubRutSrp003(ds);    
				this.dmpthr = this.scal6;
				this.wkalt = objTap002w.getDmstct();
				this.wkbank = this.bknum;

				
				if (objTap002w.getDmacct() < new Long("5000000000")) {
					this.wkacct = "06" + String.format("%010d", objTap002w.getDmacct());
				}
				else {
					this.wkacct = "01" + String.format("%010d", objTap002w.getDmacct());
				}
				
				this.wktitl = objTap002w.getDmtitl();
				SubRutGtcust(ds);    
				this.hssec = this.wkssno;
				this.dmssno = this.hssec;
				this.dmhpnr = this.wkhmph;
				this.dmbunr = this.wkbuph;
				this.wsbal = objTap002w.getDmcbal();
				this.float0 = objTap002w.getDmcf1() + objTap002w.getDmcf2() + objTap002w.getDmcf3() + objTap002w.getDmcf4() + objTap002w.getDmcf5();
				this.flt1   = objTap002w.getDmcf1();
				this.flt2 = objTap002w.getDmcf2();
				this.flt3 = objTap002w.getDmcf3();
				this.flt4 = objTap002w.getDmcf4();
				this.flt5 = objTap002w.getDmcf5();
				this.float1 = objTap002w.getDmcf1() + objTap002w.getDmcf2() + objTap002w.getDmcf3() + objTap002w.getDmcf4() + objTap002w.getDmcf5();
				this.dmcol0 = objTap002w.getDmcbal().subtract(new BigDecimal(this.float0).subtract(new BigDecimal(objTap002w.getDmhold())));
				this.dmcol1 = objTap002w.getDmcbal().subtract(new BigDecimal(this.float1).subtract(new BigDecimal(objTap002w.getDmhold())));
				
				if (objTap002w.getDmemp().equals("E") || objTap002w.getDmemp().equals("O") || objTap002w.getDmemp().equals("D")) {
					this.dmald = 0;
				}
				if (objTap002w.getDmemp().equals("E") || objTap002w.getDmemp().equals("O") || objTap002w.getDmemp().equals("D")) {
					this.aacta1 = this.acctno;
					this.aacta2 = this.aacta1;
				}
				this.dshbk = objTap002w.getDmbk();
				this.dshdsv = objTap002w.getDmtyp();
				this.dsacct = objTap002w.getDmacct();
				
				this.dmbk = objTap002w.getDmbk();
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutGtcust(DataSet ds) {
		try {
			this.na1 = "";
			this.na2 = "";
			this.na3 = "";
			this.na4 = "";
			this.na5 = "";
			this.na6 = "";
			this.wkssno = 0;
			this.wkbuph = 0;
			this.wkhmph = 0;
			this.wkappl = 20;
			cu060adapter = Cus060.ObjetoCus060(ds, wkbank, wkacct, wkalt, wktitl, wkappl, na1, na2, na3, na4, na5, na6, wkssno, wkbuph, wkhmph);
			
			this.wkbank = cu060adapter.getWKBANK();
			this.wkacct = cu060adapter.getWKACCT();
			this.wkalt = cu060adapter.getWKALT();
			this.wktitl = cu060adapter.getWKTITL();
			this.wkappl = cu060adapter.getWKAPPL();
			this.na1 = cu060adapter.getNA1();
			this.na2 = cu060adapter.getNA2();
			this.na3 = cu060adapter.getNA3();
			this.na4 = cu060adapter.getNA4();
			this.na5 = cu060adapter.getNA5();
			this.na6 = cu060adapter.getNA6();
			this.wkssno = cu060adapter.getWKSSNO();
			this.wkbuph = cu060adapter.getWKBUPH();
			this.wkhmph = cu060adapter.getWKHMPH(); 
			
			this.wkalt = " ";
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAccr01(DataSet ds, Tap002w o) {
		try {
			if (!this.dmbt25.equals("y")) {
				if (o.getDmacc5() == 0) {
					this.frjul = this.toaccr;
					this.sper = "D";
					this.sfrq = 1;
					this.srday = 00;
					SubRutSrp019(ds);    
					this.dmacc5 = this.tojul;
					this.dmaccr = this.tojul;
				}
				if (objCfp001205.getCfpacb() != null) {
					if (objCfp001205.getCfpacb().equals("1")) {
						this.frjul = o.getDmacc5();
						SubRutSrp013(ds);
						this.dmaccr = this.tojul;
					}
				}
				this.dmnsfa = o.getDmnsfa().subtract(o.getDmnsfa());
				this.dmnsff = this.dmnsff - this.dmnsff;
				this.srxctr = this.srxctr - this.srxctr;
				this.dmbt25 = "N";
				this.dmbt23 = "N";
				
				this.dmybal = o.getDmcbal();
				this.dmdrt = 0;
				this.dmcrt = 0;
				this.dmdrti = 0;
				this.dmcrti = 0;
				this.dmcktd = 0;
				this.dmcf2 = o.getDmcf3();
				this.dmcf3 = o.getDmcf4();
				this.dmcf4 = o.getDmcf5();
				this.dmcf5 = new Long(0);
				this.dmdtdy = 0;
				this.dmctdy = 0;
				this.dmsofd = 0;
				this.dmsofc = 0;
				this.dmiapa = 0;
				this.dmiada = 0;
				this.dmfrst = 0;
				if (fc.BigDecimalComparar(o.getDmcbal().toString(), "0", ">=")) {
					this.dmlnsf = 0;
					this.dmlsc = 0;
				}
				Integer dsdow = -1;
				Calendar fecha = Calendar.getInstance();
				dsdow = fecha.get(Calendar.DAY_OF_WEEK);
				
				
				if (dsdow == 2) {//dia actual es lunes
					this.dmactv= "";
				}
				if (fc.PrimerDiaHabil()) {
					this.dmttrn = 0;
					this.dmmwd = 0;
					this.dmeft = " ";
					this.dmmtda = 0;
					this.dmmtdc = 0;
					this.dmmtdd = 0;
					this.dmmdri = 0;
					this.dmmcri = 0;
					this.dmmcrd = 0;
					this.dmmnsf = 0;
					this.dmmnsp = 0;
					this.dmmodm = 0;
					this.dmkmo = 0;
					this.dmsctm = 0;
					this.dmexsc = 0;
					
					Date date = new Date();
					DateFormat d = new SimpleDateFormat("yyyyD"); 
					Integer dsdt= Integer.parseInt(d.format(date));
					this.fld3m = Integer.parseInt(dsdt.toString().substring(0, 3));
					
					if (this.fld3m <= 31) {
						this.dmlyav = 0;
						this.dmlyav = (int)(long)o.getDmytda()/o.getDmytdd();
						this.dmytda = 0;
						this.dmytdc = 0;
						this.dmytdd = 0;
						this.dmnsfl = o.getDmnsfy();
						this.dmnsfy = 0;
						this.dmkite = 0;
						this.dmnsfp = 0;
						this.dmodly = o.getDmoddy();
						this.dmoddy =0;
						this.dmodiy = BigDecimal.ZERO;
						this.dmipdy = o.getDmyipd();
						this.dmyipd = 0;
						this.dmwhly = o.getDmwhty();
						this.dmwhty = o.getDmwhty().subtract(o.getDmwhty());
						this.dmyacc = 0;
					}
				}
				
				if (this.dmbt23.equals("y")) {
					if (!this.cfpbfg.equals("Y")) {
						this.dmpsbl = o.getDmpbal();
						this.dmpscr = o.getDmcra();
						this.dmpsdr = o.getDmdra();
						this.dmpsci = o.getDmcri();
						this.dmpsdi = o.getDmdri();
						this.dmpssc = o.getDmscst();
						this.dmpsin = o.getDmints();
						this.dmpsip = o.getDmintp();
						this.dmpsiw = o.getDminws();
						this.dmpslt = o.getDmlast();
					}
					this.dmpbal = new BigDecimal(o.getDmcbal().intValue());
					this.dmcra = 0;
					this.dmdra = 0;
					this.dmcri = 0;
					this.dmdri = 0;
					this.dmscst = 0;
					this.dmints = 0;
					this.dmintp = 0;
					this.dminws = 0;
					this.dmencd = 0;
					this.dmencc = 0;
					this.dmefff = " ";
					if (this.cfpbfg.equals("Y")) {
						this.dmpsdi = o.getDmlast();
					}else {
						this.dmlast = 0;
					}
					this.dmbt23 = "N"; 
				}
			}
				
			if (this.srxctr == 0) {
				this.float0 = o.getDmcf1() + o.getDmcf2() + o.getDmcf3() + o.getDmcf4() + o.getDmcf5();
			}
			this.srxctr = this.srxctr + 1;
			
			if (this.accr3 == this.toaccr && !this.accctl.equals("B")) {
				this.accr3 = this.toaccr;
				this.toaccr = this.accr4;
			}else {
				this.accr3 = this.toaccr;
				if (this.accctl.equals("B")) {
					this.frjul = this.toaccr;
					this.sper = "D";
					this.sfrq = 1;
					this.srday = 0;
					SubRutSrp019(ds);    
					this.toaccr = this.tojul;
					this.accr4 = this.tojul;
				}
			}
			
			this.toacc = this.toaccr;
			this.wkcday = 0;
			this.dyslst = 0;
			if (o.getDmacc5() != this.toaccr) {
				this.frjul = o.getDmacc5();
				this.tojul = this.toaccr;
				SubRutSrp005(ds);    
				this.wkcday = this.srrslt;
				this.dyslst = this.srrslt;
				this.wkclbl = o.getDmcbal().subtract(new BigDecimal(this.float0));
				this.wkcbal = o.getDmcbal();
				if (this.wkcday != 1) {
					this.wkcbal = this.wkcbal.multiply(new BigDecimal(this.wkcday));
					this.wkclbl = this.wkclbl.multiply(new BigDecimal(this.wkcday));
				}
				this.dmaggb = o.getDmaggb() + wkcbal.longValue();
				this.dmagcb = o.getDmagcb() + wkclbl.longValue();
				this.dmaggd = o.getDmaggd() + wkcday;
				this.dmmtdd = o.getDmmtdd() + wkcday;
				this.dmytdd = o.getDmytdd() + wkcday;
				this.dmmtda = (int)(long) (o.getDmmtda() + wkcbal.longValue());
				this.dmytda = (int)(long) (o.getDmytda() + wkcbal.longValue());
				this.dmytdc = (int)(long) (o.getDmytdc() + wkclbl.longValue());
				this.dmmtdc = (int)(long) (o.getDmmtdc() + wkclbl.longValue());
				if (this.dmbt25.equals("y") && fc.BigDecimalComparar(o.getDmybal().toString(), "0", ">=") ) {
					this.dmoddy = o.getDmoddy() + this.wkcday;
					this.xxodi = this.wkcday * (int)(long)(0.000494);
					this.dmodiy = o.getDmodiy().add(new BigDecimal(this.xxodi));
				}
				if (o.getDmdint() == 0) {
					this.dmacc5 = this.toaccr;
					this.dmaccr = this.toaccr;
				}else {
					if (objCfp001205.getCfpacb().equals("1")) {
						this.frjul = this.toaccr;
						SubRutSrp013(ds);
						this.toacc = this.tojul;
						this.frjul = o.getDmaccr();
						SubRutSrp006(ds);
						this.wkcday = this.srrslt;
					}
					this.wkclbl = o.getDmcbal().subtract(new BigDecimal(this.float0));
					this.wkcbal = o.getDmcbal();
					if (this.wkcday != 1) {
						this.wkcbal = this.wkcbal.multiply(new BigDecimal(this.wkcday));
						this.wkclbl = this.wkclbl.multiply(new BigDecimal(this.wkcday));
					}
					this.dmagid = o.getDmagid() + this.wkcday;
					if (!objCfp001205.getCfpinc().equals("B")) {
						this.dmaggi = o.getDmaggi().add(this.wkclbl);
					}else {
						this.dmaggi = o.getDmaggi().add(this.wkcbal);
					}
					this.wkcint = 0;
					this.wkcbal = o.getDmcbal();
					if (!objCfp001205.getCfpinc().equals("B")) {
						this.wkcbal = this.wkcbal.subtract(new BigDecimal(this.float0));
					}
					if (objCfp001205.getCfpnty().equals("B") || objCfp001205.getCfpnty().equals("D")) {
						this.wkcint = o.getDmtacc().intValue();
					}
					this.wkaccr = BigDecimal.ZERO;
					this.wkacc2 = BigDecimal.ZERO;
					if (fc.BigDecimalComparar(this.wkcbal.toString(), objCfp001205.getCfpmba().toString(), ">=")   ) {
						this.srrslt = this.wkcday;
						if (!objCfp001205.getCftrfg().equals("I")) {
							this.i = 1;
							while ( fc.BigDecimalComparar(this.wkcbal.toString(), this.wkxxbl.toString(), ">=")  && this.i <= 4) {
								this.wkxxbl = this.cfpb[this.i];
								if (this.wkxxbl.equals(9999999)) {
									this.wkxxbl = (long) 999999999;
								}
								if (fc.BigDecimalComparar(this.wkcbal.toString(), this.wkxxbl.toString(), ">") ) {
									this.i = this.i + 1;
								}
							}
							if (this.i > 4) {
								this.i = 1;
							}
							this.inftr = this.rate[this.i].intValue();
							this.srrslt = this.wkcday;
							this.wkrsv = new BigDecimal(1).subtract(objCfp001205.getCfprsv());
							this.wkcbal = this.wkrsv.multiply(this.wkcbal);
							SubRutAccrrt(ds);
							this.dmtacc = o.getDmtacc().add(this.wkaccr);
						}else {
							this.wkjbl2 = this.wkcbal;
							this.i = 1;
							while (fc.BigDecimalComparar(this.wkcbal.toString(), "0", "<=") || (this.i <= 4 && this.rate[this.i].intValue() == 0)) {
								if (this.rate[this.i].intValue() != 0) {
									this.wkxxbl = this.cfpb[this.i];
									if (this.wkxxbl == 9999999) {
										this.wkxxbl = (long) 999999999;
									}
									if (this.wkcbal.intValue() > this.wkxxbl) {
										this.wkjbl2 = this.wkjbl2.subtract(new BigDecimal(this.wkxxbl));
										this.wkcbal = new BigDecimal(this.wkxxbl);
									}else {
										this.wkjbl2 = BigDecimal.ZERO;
									}
									if (this.i != 1) {
										this.wkcint = 0;
									}
									this.inftr = this.rate[this.i].intValue();
									this.srrslt = this.wkcday;
									
									this.wkrsv = new BigDecimal(1).subtract(objCfp001205.getCfprsv());
									this.wkcbal = this.wkrsv.multiply(this.wkcbal);
									SubRutAccrrt(ds);
									this.dmtacc = o.getDmtacc().add(this.wkaccr);
									this.i = this.i + 1;
									this.wkcbal = this.wkjbl2;
								}
							}
						}
					}
					this.dmaccr = this.toacc;
					this.dmacc5 = this.toaccr;
				}
			}
			this.dmbt25.equals("Y");
				
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSrp003(DataSet ds) {
		try {
			this.tocal = 0;
			this.scal6 = 0;
			this.swork4 = this.frjul.toString().substring(1-1,4);
			this.tocal = Integer.parseInt(this.swork4);
			this.sy = Integer.parseInt(this.swork4.substring(3-1, 4));
			this.sres3 = Integer.parseInt(this.frjul.toString().substring(5-1, 7));
			this.test = this.sy % 4;
			if (this.test == 0 && this.sres3 == 60) {
				this.sm = 2;
				this.sd = 29;
			}else {
				if (this.test == 0 && this.sres3 > 59) {
					this.sres3 = this.sres3 - 1;
				}
				this.sm = 1;
				while (this.sjl5[this.sm] < this.sres3) {
					this.sm = this.sm+1;
				}
				this.sm = this.sm - 1;
				this.sd = this.sres3 - this.sjl5[this.sm];
			}
			if (this.dtefmt == 1) {
				this.swork4 = String.format("%02d",this.sd) + String.format("%02d",this.sm + 1);
			}else {
				this.swork4 = String.format("%02d",this.sd) + String.format("%02d",this.sm + 1);
			}
			this.tocal = Integer.parseInt(this.swork4 + this.tocal.toString());
			this.frcal = this.tocal;
			SubRutSrp012(ds);
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSrp019(DataSet ds) {
		try {
			if (!this.sper.equals("M")) {
				if (!this.sper.equals("Y")) {
					this.tojul = this.frjul;
					this.sres3 = this.tojul;
					this.sfrqx = this.sfrq;
					while (this.sfrq < this.sres3) {
						this.tojul = this.tojul - 1000;
						this.swork4 = this.tojul.toString();
						this.sy = Integer.parseInt(this.swork4);
						this.sres5 = 250 * this.sy;
						this.sres3x = this.sres5;
						if (this.sres3x == 0) {
							this.sfrq = this.sfrq - 366;
						}else {
							this.sfrq = this.sfrq - 365;
						}
					}
					this.tojul = this.tojul - this.sfrq;
					this.sfrq = this.sfrqx;
				}
			}else {
				if (!this.sper.equals("Y")) {
					SubRutSrp003(ds);
					this.sres5 = this.sm;
					this.sres5 = this.sres5 - this.sfrq;
					while (this.sres5 <= 0) {
						this.tocal = this.tocal - 1;
						this.sres5 = 12 + this.sres5;
					}
					this.sm = this.sres5;
					if (this.dtefmt == 2) {
						this.tocal = this.sm;
					}else {
						this.swork4 = this.tocal.toString();
						this.swork4 = this.sm.toString();
						this.tocal = Integer.parseInt(this.swork4);
					}
				}else {
					SubRutSrp003(ds);    
					this.tocal = this.tocal - this.sfrq;
				}
				this.swork4 = this.tocal.toString();
				this.srrslt = 250 * Integer.parseInt(this.swork4);
				this.sres3 = this.srrslt;
				if (this.sres3 == 0) {
					this.sdim[2] = 29;
				}else {
					this.sdim[2] = 28;
				}
				this.swork4 = this.tocal.toString();
				if (this.srday > 0) {
					this.sd = this.srday;
				}
				
				if (this.sd > this.sdim[this.sm]) {
					this.sd = this.sdim[this.sm];
				}

				if (this.dtefmt == 1) {
					this.swork4 = this.swork4.replaceAll(this.swork4.substring(0, 2), this.sd.toString()) ;
				}else {
					this.swork4 = this.swork4.replaceAll(this.swork4.substring(0, 2), this.sd.toString()) ;
				}
				this.tocal = Integer.parseInt(this.swork4);
				this.frcal = this.tocal;
				SubRutSrp001(ds);
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSrp013(DataSet ds) {
		try {
			SubRutSrp003(ds);
			this.swork4 = this.tocal.toString();
			if (this.sm == 2 && this.sd >= this.sdim[this.sm]) {
				this.sd = 30;
			}
			if (this.dtefmt == 2) {
				this.swork4 = this.swork4.replaceAll(this.swork4.substring(0, 2), this.sd.toString()) ;
			}else {
				this.swork4 = this.swork4.replaceAll(this.swork4.substring(0, 2), this.sd.toString()) ;
			}
			this.frcal = Integer.parseInt(this.swork4)*100 + this.tocal;
			SubRutSrp002(ds);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSrp005(DataSet ds) {
		try {
			this.srrslt = 0;
			if (this.frjul != 0 && this.tojul != 0) {
				this.frcal = this.frjul;
				this.tocal = this.tojul;
				if (this.frcal > this.tocal) {
					this.frjul = this.tocal;
					this.tojul = this.frcal;
				}
				this.swork7 = this.tojul;
				this.swork4 = this.frjul.toString();
				this.sres5 = Integer.parseInt(this.swork4);
				this.swork4 = this.tojul.toString();
				this.sres5 = Integer.parseInt(this.swork4) - this.sres5;
				this.tojul = this.frjul;
				while (this.tojul >= this.swork7) {
					if (this.sres5 <= 0) {
						this.tojul = 1000 + this.tojul;
						this.tojul = this.tojul + this.swork7;
					}
					this.swork4 = this.frjul.toString();
					this.swrk7f = 365 * Integer.parseInt(this.swork4);
					this.swrk71 = 250 * Integer.parseInt(this.swork4);
					this.swrk71 = 750 + this.swrk71;
					this.swork4 = this.swrk71.toString();
					this.swrk7f = Integer.parseInt(this.swork4) + this.swrk7f;
					this.sres3 = this.frjul;
					this.swrk7f = this.sres3 + this.swrk7f;
					this.swork4 = this.tojul.toString();
					this.swrk7t = 365 * Integer.parseInt(this.swork4);
					this.swrk71 = 250 * Integer.parseInt(this.swork4);
					this.swrk71 = 750 + this.swrk71;
					this.swork4 = this.swrk71.toString();
					this.swrk7t = Integer.parseInt(this.swork4) + this.swrk7t;
					this.sres3 = this.tojul;
					this.swrk7t = this.sres3 + this.swrk7t;
					this.swrk7t = this.swrk7t - this.swrk7f;
					this.srrslt = this.swrk7t + this.srrslt;
					if (this.tojul < this.swork7) {
						this.frjul = 1000 + this.frjul;
					}
				}
				this.tojul = this.tocal;
				this.frjul = this.frcal;
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}
	
	private String SubRutSrp006(DataSet ds) {
		try {
			this.srrslt = 0;
			this.swork4 = this.frjul.toString();
			this.srrslt = 360 * Integer.parseInt(this.swork4);
			this.sres3 = this.frjul;
			this.srrslt = this.sres3 + this.srrslt;
			this.swork4 = this.tojul.toString();
			this.swork7 = 360 * Integer.parseInt(this.swork4);
			this.sres3 = this.tojul;
			this.swork7 = this.sres3 + this.swork7;
			this.srrslt = this.swork7 - this.srrslt;
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAccrrt(DataSet ds) {
		try {
			this.wkaccr = BigDecimal.ZERO;
			this.wkacc2 = BigDecimal.ZERO;
			if (objCfp001205.getCfpnty().equals("S") || objCfp001205.getCfpnty().equals("A") || objCfp001205.getCfpnty().equals("C")) {
				this.inftr = this.srrslt * this.inftr;
			}else {
				this.inftr = (int)(long)0.10 * this.inftr;
				this.inftr = 1 + this.inftr;
				this.inftr1 = this.inftr;
				while (this.srrslt > 1) {
					if (this.srrslt <= 1) {
						this.inftr = this.inftr1 - 1;
					}
					this.inftr1 = this.inftr1 * this.inftr;
					this.srrslt = this.srrslt - 1;
				}
			}
			this.wkaccx = new BigDecimal(this.inftr).multiply(this.wkcbal);
			if (objCfp001205.getCfpnty().equals("S") || objCfp001205.getCfpnty().equals("C") || objCfp001205.getCfpnty().equals("A")) {
				this.wkaccx = new BigDecimal(0.1).multiply(this.wkaccx);
				this.wkaccr = this.wkaccx;
				if (fc.BigDecimalComparar(this.wkaccr.toString(), "0", "<")) {
					this.wkaccr = this.wkaccr.negate();
				}
			}
			if (objCfp001205.getCfpnty().equals("D") || objCfp001205.getCfpnty().equals("B")) {
				if (fc.BigDecimalComparar(this.wkacc2.toString(), "0", "<")) {
					this.inftr = this.inftr + 1;
					this.inftr = 1/this.inftr;
					this.inftr = 1 - this.inftr;
					this.wkcint = this.wkcint - this.wkaccr.intValue();
				}
				this.wkacc2 = new BigDecimal ( this.inftr * this.wkcint);
			
				if (fc.BigDecimalComparar(this.wkacc2.toString(), "0", "<")) {
					this.wkacc2 = this.wkacc2.negate();
				}
				this.wkaccr = this.wkaccr.subtract(this.wkacc2);
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}

	private String SubRutSrp012(DataSet ds) {
		try {
			this.scal6 = Integer.parseInt(this.frcal.toString().substring(1-1, 4) + this.frcal.toString().substring(7-1, 8)); 		
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSrp001(DataSet ds) {
		try {
			this.tojul = 0;
			
			if (this.frcal != 0) {
				this.yyyy = this.frcal;
				this.sy = this.yyyy;
				this.swork4 = this.frcal.toString();
				if ( this.dtefmt == 1) {
					this.sm = Integer.parseInt(this.swork4.substring(2, 4));
					this.sd = Integer.parseInt(this.swork4.substring(0, 2));
				}else {
					this.sm = Integer.parseInt(this.swork4.substring(2, 4));
					this.sd = Integer.parseInt(this.swork4.substring(0, 2));
				}
				this.tojul = this.sjl5[this.sm];
				this.tojul = this.yyyy;
				this.tojul = this.tojul + this.sd;
				this.test = this.sy % 4;
				if (this.test == 0 && this.sm > 2) {
					this.tojul = this.tojul + 1;
				}
			}
			this.srcvt = "";
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}

	private String SubRutSrp002(DataSet ds) {
		try {
			this.tojul = 0;
			if (this.srcvt == "6") {
				SubRutSrp011(ds);
			}
			if (this.frcal != 0) {
				this.yyyy = this.frcal;
				this.sy = this.yyyy;
				this.swork4 = this.frcal.toString();
				if (this.dtefmt == 1) {
					this.sm = Integer.parseInt(this.swork4);
					this.sd = Integer.parseInt(this.swork4);	
				}
				if (this.sm == 2) {
					this.test = this.sy % 4;
					if (this.test == 0 && this.sd == 29 || this.test != 0 && this.sd == 28) {
						this.sd = 30;
					}
				}
				this.sm = this.sm - 1;
				this.tojul = this.sm * 30;
				this.tojul = this.yyyy;
				this.tojul = this.tojul + this.sd;
			}
			this.srcvt = "";
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}

	private String SubRutSrp011(DataSet ds) {
		try {
			this.frcal = 0;
			this.tocal = 0;
			if (this.scal6 != 0) {
				this.frcal = this.scal6;
				this.sy = this.scal6;
				this.swork4 = this.sy.toString();
				if (this.scal6 != 0) {
					if (this.sy < 50) {
						this.swork4 = "20";
					}else {
						this.swork4 = "19";
					}
				}
				this.frcal = Integer.parseInt(this.swork4);
				this.tocal = this.frcal;
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}
	
	private String SubRutDispon(DataSet ds) {
		try {
			this.wqextr = this.objTap002w.getDmmwd();
			this.divisi = this.objTap002w.getDmsf4().toString();
			this.fvtolm = 0;
			if (this.acctno.substring(0,1).equals("0")) {
				this.cky[7] = "6";
			}else {
				this.cky[7] = "1";
			}
			objCfp001210 = myDAOCfp001210.getUsingKey(ds, this.cky[7], this.objTap002w.getDmtype().toString());
			if (objCfp001210 != null) {
				this.tipcta = objCfp001210.getCftnme();
			}else {
				this.tipcta = "******************************";
			}
			this.sreten = BigDecimal.ZERO;
			this.ascrha = BigDecimal.ZERO;
			this.wsdisp = BigDecimal.ZERO;
			this.asdisp = BigDecimal.ZERO;
			this.wsinmo = BigDecimal.ZERO;
			this.ancta = new Long(0);
			this.ancta1 = 0;
			this.dmccaw = new Long(0);
			this.scrpen = BigDecimal.ZERO;
			this.wsinte = BigDecimal.ZERO;
			this.ancta = this.objTap002w.getDmacct();
			rpta += SubRutBorra5(ds);    
			rpta += SubRutDispo1(ds);
			
			this.disdbu = this.disda;
			this.discbu = this.disca;
			this.scrhoy = this.acrhoy;
			this.sdbhoy = this.adbhoy;
			this.sdbpen = this.adbpen;
			this.sreten = this.areten;
			this.fecvto = this.afecvt;
			if (new Long(this.ancta) > new Long("5000000000")) {
				this.aaplic = objCfp001005.getCfpsav();
			}else {
				this.aaplic = objCfp001005.getCfpdda();
			}
			this.t = this.objTap002w.getDmodl();
			this.ancta1 = this.ancta.intValue();
			if (this.objTap002w.getDmgovt().equals("+")) {
				if (this.objTap002w.getDmcmcn() != 0) {
					this.segmen = "POOL";
					objCbiptic = myDAOCbiptic.getUsingDmcmcnAndDmbrchAndSegmen(ds, this.objTap002w.getDmcmcn().toString(), this.objTap002w.getDmbrch().toString(), this.segmen);
					if (objCbiptic == null) {
						this.tipcam = new BigDecimal(1);
					}else {
						this.tipcam = objCbiptic.getTccvta();
					}
				}
				this.acmcn = this.objTap002w.getDmcmcn();
				
				listTap902 = myDAOTap902.getUsingListLdbankANDAcodrANDaaplicANDancta1(ds, ldbank.toString(), this.acodr.toString(), this.aaplic.toString(), this.ancta1.toString());
				
				if (listTap902 != null || listTap902.size()>0) {
					this.aaplic = listTap902.get(0).getCaplp();
					this.ancta1 = listTap902.get(0).getNctap().intValue();
					
					listTap902 = myDAOTap902.getUsingListLdbankANDAcodrANDaaplicANDancta1(ds, ldbank.toString(), this.acodr.toString(), this.aaplic.toString(), this.ancta1.toString());
					for(Tap902 o:listTap902) {
						if (!o.getNctav().toString().equals(this.acctno)) {
							if (o.getCaplv() == objCfp001005.getCfpsav()) {
								this.amtyp = 1;
							}else {
								if (o.getCaplv() == objCfp001005.getCfpdda()) {
									this.amtyp = 6;
								}
							}
							this.ancta = o.getNctav();
							objTap002w = myDAOTap002w.getUsingLdbankAndAmtypAndAncta(ds, ldbank.toString(), this.amtyp.toString(), this.ancta.toString());
							this.dmbt25 = objTap002w.getDmbt25();
							this.dmbt23 = objTap002w.getDmbt23();
							this.dmpbal = objTap002w.getDmpbal();
							if (objTap002w != null) {
								this.dmdopn = Integer.parseInt((new SimpleDateFormat("ddMMyyyy").parse(objTap002w.getDmdopn().toString())).toString());
								this.dmdla = Integer.parseInt((new SimpleDateFormat("ddMMyyyy").parse(objTap002w.getDmdla().toString())).toString());
								this.dmdlst = Integer.parseInt((new SimpleDateFormat("ddMMyyyy").parse(objTap002w.getDmdlst().toString())).toString());
							}
							rpta += SubRutBorra5(ds);
							rpta += SubRutDispo1(ds);
							this.dmcbal = objTap002w.getDmcbal().subtract(this.disda.add(this.disca));
							this.asdisp = this.dmcbal.add(new BigDecimal(objTap002w.getDmiodl()));
							this.asdisp = this.asdisp.subtract(this.adbhoy);
							this.asdisp = this.asdisp.subtract(this.adbpen);
							this.asdisp = this.asdisp.subtract(this.areten);
							if (objTap002w.getDmcmcn() == this.acmcn) {
								if (this.acmcn == 0) {
									this.segmen = "POOL";
									objCbiptic = myDAOCbiptic.getUsingDmcmcnAndDmbrchAndSegmen(ds, this.objTap002w.getDmcmcn().toString(), this.objTap002w.getDmbrch().toString(), this.segmen);
									if (objCbiptic == null) {
										this.tipcam = new BigDecimal(1);
									}else {
										this.tipcam = objCbiptic.getTccomp();
									}
									this.asdisp = this.asdisp.multiply(this.tipcam);
									this.acrhoy = this.acrhoy.multiply(this.tipcam);
								}else {
									this.asdisp = this.asdisp.divide(this.tipcam);
									this.acrhoy = this.acrhoy.divide(this.tipcam);
								}
							}
							this.ascrha = this.ascrha.add(this.acrhoy);
							this.wsdisp = this.wsdisp.add(this.asdisp);
						}//fin if						
					}//fin for list					
				}//fin if primer register.
				
				this.swtaac = this.swtaky.substring(5, 14);
				objTap002w = myDAOTap002w.getUsingLdbankAndAmtypAndAncta(ds, this.swtaky.substring(0, 3), this.swtaky.substring(3), this.swtaac);
				this.dmbt25 = objTap002w.getDmbt25();
				this.dmbt23 = objTap002w.getDmbt23();
				this.dmpbal = objTap002w.getDmpbal();
				if (objTap002w != null) {
					this.dmdopn = Integer.parseInt((new SimpleDateFormat("ddMMyyyy").parse(objTap002w.getDmdopn().toString())).toString());
					this.dmdla = Integer.parseInt((new SimpleDateFormat("ddMMyyyy").parse(objTap002w.getDmdla().toString())).toString());
					this.dmdlst = Integer.parseInt((new SimpleDateFormat("ddMMyyyy").parse(objTap002w.getDmdlst().toString())).toString());
				}
				
				
			}//fin if equal +
			this.dmcbal = objTap002w.getDmcbal().subtract(this.disdbu.add(this.discbu));
			this.wsbal = objTap002w.getDmcbal();
			this.asdisp = objTap002w.getDmcbal().add(new BigDecimal(objTap002w.getDmiodl()).subtract(this.sdbpen.subtract(this.sreten.subtract(this.sdbhoy))));
			if (objTap002w.getDmnow().equals("9")) {
				if (!objTap002w.getDmusr1().equals("2")) {
					this.asdisp = this.asdisp.subtract(this.wsinmo);
					this.wsinmo = this.wsinmo.add(this.scrhoy);
				}else {
					this.asdisp = BigDecimal.ZERO;
				}
			}else {
				this.asdisp = this.asdisp.add(this.scrhoy);
			}
			if (fc.BigDecimalComparar(this.wsdisp.toString(), "0", "!=")) {
				if (objTap002w.getDmtyp() == 6) {
					this.wsdisp = this.wsdisp.add(this.ascrha);
					this.t = objTap002w.getDmodl();
					if ( fc.BigDecimalComparar(this.asdisp.toString(), "0", ">=") ) {
						this.stope = new BigDecimal( this.sr5[this.t]);
					}else {
						this.stope = new BigDecimal(this.sr5[this.t]).add(this.asdisp);
					}
				}else {
					if (fc.BigDecimalComparar(this.asdisp.toString(), "0", ">")) {
						this.stope = this.asdisp;
					}else {
						this.stope = BigDecimal.ZERO;
					}
				}
				if (fc.BigDecimalComparar(this.wsdisp.toString(), "0", "<")) {
					this.dmccaw = objTap002w.getDmiodl();
					this.wsdisp = this.wsdisp.negate();
					if (objTap002w.getDmnow().equals("9")) {
						this.wsdisp = this.wsdisp.subtract(this.wsinmo);
						if (fc.BigDecimalComparar(this.wsdisp.toString(), "0", "<")) {
							this.wsdisp = BigDecimal.ZERO;
						}
					}
					if (fc.BigDecimalComparar(this.wsdisp.toString(), this.stope.toString(), ">")) {
						this.sreten = this.sreten.add(this.stope);
						this.asdisp = this.asdisp.subtract(this.stope);
					}else {
						this.sreten = this.sreten.add(this.wsdisp);
						this.asdisp = this.asdisp.subtract(this.wsdisp);
					}
				}else {
					if (objTap002w.getDmtyp() == 6) {
						if (fc.BigDecimalComparar(this.wsdisp.toString(), this.stope.toString(), ">")) {
							this.dmccaw = objTap002w.getDmiodl() + new Long(this.stope.toString());
							this.asdisp = this.asdisp.add(this.stope);
						}else {
							this.dmccaw = objTap002w.getDmiodl() + new Long(this.wsdisp.toString());
							this.asdisp = this.asdisp.add(this.wsdisp);
						}
					}
				}
			}else {
				this.dmccaw = objTap002w.getDmiodl();
			}
			
			this.wsdisp = this.asdisp;
			this.wsdact = objTap002w.getDmcbal().subtract(this.sdbhoy);
			this.wsdact = this.wsdact.subtract(this.sdbpen);
			if (!objTap002w.getDmnow().equals("9")) {
				this.wsdact = this.wsdact.add(this.scrhoy);
			}
			this.wmmacc = objTap002w.getDmtacc();
			this.wmmacc = this.wmmacc.add(this.wsinte);
			this.wdspin = this.wsdisp.add(this.wmmacc);
			this.tpbloq = "";
			this.fecpas = "";
			int position = Arrays.asList(this.tabest).indexOf(objTap002w.getDmstat()); 
			if (position > -1) {
				this.tpbloq = this.tabdes[position];
				if (objTap002w.getDmstat().equals("P") || objTap002w.getDmstat().equals("N") || objTap002w.getDmstat().equals("O")) {
					
					objSaldom = myDAOSaldom.getUsingDmsf4AndDmbrchAndDmtypAndDmacct(ds, objTap002w.getDmsf4(), objTap002w.getDmbrch(), objTap002w.getDmtyp(), objTap002w.getDmacct());
					if (objSaldom != null) {
						if (objTap002w.getDmstat().equals("P")) {
							this.fecamd = objSaldom.getDsapen();
						}else {
							if (objTap002w.getDmstat().equals("N")) {
								this.fecamd = objSaldom.getDsana();
							}else {
								this.fecamd = objSaldom.getDsawo();
							}
						}
						this.dfpas = this.fecamd.toString().substring(4, 6);
						this.mfpas = this.fecamd.toString().substring(2, 4);
						this.afpas = this.fecamd.toString().substring(0, 2);
					}
				}
			}
			Calendar nows = Calendar.getInstance();
			Integer Mes = nows.get(Calendar.MONTH);
			this.finmes = e[Mes];
			if (Mes == 1) {//FEBRUARY 
				this.resto = this.aano%4;
				this.finmes = this.finmes + 1;
			}
			this.adia = Integer.parseInt(this.fc.FormatoFechaHora("dd"));
			this.qdias = this.finmes - this.adia;
			this.qdias = this.qdias + 1;
			this.wwint = (objTap002w.getDmcbal().multiply(objTap002w.getDmintr()).multiply(new BigDecimal(this.qdias))).divide(new BigDecimal(36500),2);
			this.wpryin = this.wwint.add(objTap002w.getDmmacc());
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutBorra5(DataSet ds) {
		try {
			this.adbpen = BigDecimal.ZERO;
			this.adbhoy = BigDecimal.ZERO;
			this.areten = BigDecimal.ZERO;
			this.acrhoy = BigDecimal.ZERO;
			this.disda = BigDecimal.ZERO;
			this.disca = BigDecimal.ZERO;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutDispo1(DataSet ds) {
		try {
			listTransm = myDAOTransm.getUsingKeyAncta(ds, this.ancta.intValue());
			for(Transm o:listTransm) {
				this.fecing = Integer.parseInt( o.getDaain().toString() + o.getDmmin().toString() + o.getDddin().toString());
				this.fecval = Integer.parseInt( o.getDaava().toString() + o.getDmmva().toString() + o.getDddva().toString());
				if (!o.getXerror().equals("E")) {
					if (o.getCmonex() != 0) {
						this.aimpor = o.getSimloc();
					}else {
						this.aimpor = o.getSimpex();
					}
					if (o.getCdbcr() <= 5) {
						if (this.fecval > this.fecdia) {
							this.scrpen = this.scrpen.add(this.aimpor);
						}else {
							if (this.fecval == this.fecdia) {
								if (o.getCbat().equals("4")) {
									this.disca = this.disca.add(this.aimpor);
								}else {
									this.acrhoy = this.acrhoy.add(this.aimpor);
								}
							}else {
								if (this.fecing < this.fecdia) {
									this.disca = this.disca.add(this.aimpor);
								}else {
									this.acrhoy = this.acrhoy.add(this.aimpor);
								}
							}
						}
					}else {
						if (this.fecval > this.fecdia) {
							this.adbpen = this.adbpen.add(this.aimpor);
						}else {
							if (this.fecval == this.fecdia) {
								if (o.getCbat().equals("4")) {
									this.adbpen = this.adbpen.add(this.aimpor);
								}else {
									this.adbhoy = this.adbhoy.add(this.aimpor);
									if (o.getCsucur() != this.objTap002w.getDmbrch() && o.getCbat().equals("1") ) {
										this.sdbctk = this.sdbctk.add(this.aimpor);
									}
								}
							}else {
								if (this.fecing < this.fecdia) {
									this.disda = this.disda.add(this.aimpor);
								}else {
									this.adbhoy = this.adbhoy.add(this.aimpor);
									if (o.getCsucur() != this.objTap002w.getDmbrch() && o.getCbat().equals("1")) {
										this.sdbctk = this.sdbctk.add(this.aimpor);
									}
								}								
							}
						}
					}
					if (o.getFcomtr() == 1) {
						this.wqextr = this.wqextr + 1;
					}
				}
			}
			this.abnk1 = this.ldbank;
			if (new Long(this.ancta) > new Long("5000000000")) {
				this.atpcta = "1";
			}else {
				this.atpcta = "6";
			}
			
			this.acuen1 = this.ancta;
			this.afill1 = "";
			this.afill2 = "";
			this.dshbk = this.abnk1;
			this.dshdsv = Integer.parseInt(this.atpcta);
			this.dsacct = this.acuen1;
			listTap003 = myDAOTap003.getUsingListAtpctaAndAcuen1(ds, this.atpcta.toString(), this.acuen1.toString());
			for(Tap003 o:listTap003) {
				this.areten = this.areten.add(o.getDsamt());
			}
			this.afecvt = 0;
			if ( Integer.parseInt(this.acctno.substring(0,1)) > 0 && this.objTap002w.getDmnow().equals("9") ) {
				listTap901 = myDAOTap901.getUsingListDmbkAndDmtypAndDmacct(ds, this.objTap002w.getDmbk().toString(), this.objTap002w.getDmtyp().toString(), this.objTap002w.getDmacct().toString());
				for (Tap901 o:listTap901) {
					this.fecimn = o.getSsedyy() + o.getSsedmm() + o.getSseddd();
					this.csamd8 = this.fecimn;
					if (this.fecimn != 0) {
						if (this.csamd8 < 800000) {
							this.csamd8 = this.csamd8 + 20000000;
						}else {
							this.csamd8 = this.csamd8 + 19000000;
						}
					}
					if (this.csamd8 > this.fecdia) {
						this.wsinmo = this.wsinmo.add(o.getSsamon());
						this.wsinte = this.wsinte.add(o.getSstacc());
						if (this.afecvt == 0) {
							this.afecvt = this.fecimn;
						}
					}
				}
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutComput(DataSet ds) {
		try {
			this.numcue = "0" + String.format("%010d", objTap002w.getDmacct());
			this.tipope = "CANT";
			cgrrcompadapter = Cgrrcomp.objetoCgrrcomp(ds, numcue, "", tipope, "", "", "", "", "", "", "", "", "", "", "");
			if (cgrrcompadapter.ERROR == "") {
				this.wqextr = Integer.parseInt(cgrrcompadapter.getCOMPUT());
				this.wrevi = cgrrcompadapter.getREVISI();
				this.aabar = "/";
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutInstmt(DataSet ds) {
		try {
			this.rr1 = 0;
			this.wtbatc = 0;
			//Grabar registro en SFCR12
			this.wsrnbr = 0;
			this.frjul = objTap002w.getDmacc5();
			SubRutSrp003(ds);    
			this.prdate = this.scal6;
			this.wkbal = objTap002w.getDmpbal();
			this.rr1 = this.rr1 + 1;
			this.wsdate = objTap002w.getDmdlst();
			this.wbatch = 0;
			this.dhitc = 0;
			this.dhser = new Long(0);
			this.wkdr = BigDecimal.ZERO;
			this.wkcr = BigDecimal.ZERO;
			//Grabar registro en PANTALLA3
						
			if (objTap002w.getDmlast() > 0) {
				listTap005b = myDAOTap005b.getUsingListDmbkAndDmtypAndDmacctAndDmfsttAndRegist(ds, objTap002w.getDmbk().toString(), objTap002w.getDmtyp().toString(), objTap002w.getDmacct().toString(), objTap002w.getDmfstt().toString(), "regist");
				for(Tap005b o:listTap005b) {
					this.fechai = o.getDheff();
					if (this.anoi <= 1991 && this.codmon == 0) {
						this.dhamt = o.getDhamt().subtract(new BigDecimal(0.01)).divide(new BigDecimal(10000));
					}
					this.anod = this.anoi;
					this.mesd = this.mesi;
					this.diad = this.diai;
					this.wsdate = this.fechad;
					if (o.getDhdrcr() >= 6) {
						this.wkdr = o.getDhamt();
						this.wkcr = BigDecimal.ZERO;
					}else {
						this.wkdr = BigDecimal.ZERO;
						this.wkcr = o.getDhamt();
					}
					this.wkbal = this.wkbal.add(this.wkcr).subtract(this.wkdr);
					this.wsuc1 = o.getDhbtbr();
					this.wtbatc = Integer.parseInt(o.getDhbtcd());
					this.wnbat1 = o.getDhbtnr();
					this.dhser = o.getDhref();
					this.rr1 = this.rr1 + 1;
					//Grabar registro en PANTALLA3
				}
				
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	public class FER0310V03Adapter {
		
		Long DMACCT = new Long("0");
		Integer PRDATE = 0;
		String NA1 = "";
		Integer DMDLST = 0;
		BigDecimal DMPBAL = new BigDecimal(0);
		String NA2 = "";
		Integer DMCRI = 0;
		Integer DMCRA = 0;
		String NA3 = "";
		Integer DMDRI = 0;
		Integer DMDRA = 0;
		String NA4 = "";
		Integer DMSCST = 0;
		String NA5 = "";
		Integer DMINTS = 0;
		String NA6 = "";
		BigDecimal DMCBAL = new BigDecimal(0);
		
		
		public FER0310V03Adapter() {
			
		}


		public Long getDMACCT() {
			return DMACCT;
		}


		public void setDMACCT(Long dMACCT) {
			DMACCT = dMACCT;
		}


		public Integer getPRDATE() {
			return PRDATE;
		}


		public void setPRDATE(Integer pRDATE) {
			PRDATE = pRDATE;
		}


		public String getNA1() {
			return NA1;
		}


		public void setNA1(String nA1) {
			NA1 = nA1;
		}


		public Integer getDMDLST() {
			return DMDLST;
		}


		public void setDMDLST(Integer dMDLST) {
			DMDLST = dMDLST;
		}


		public BigDecimal getDMPBAL() {
			return DMPBAL;
		}


		public void setDMPBAL(BigDecimal dMPBAL) {
			DMPBAL = dMPBAL;
		}


		public String getNA2() {
			return NA2;
		}


		public void setNA2(String nA2) {
			NA2 = nA2;
		}


		public Integer getDMCRI() {
			return DMCRI;
		}


		public void setDMCRI(Integer dMCRI) {
			DMCRI = dMCRI;
		}


		public Integer getDMCRA() {
			return DMCRA;
		}


		public void setDMCRA(Integer dMCRA) {
			DMCRA = dMCRA;
		}


		public String getNA3() {
			return NA3;
		}


		public void setNA3(String nA3) {
			NA3 = nA3;
		}


		public Integer getDMDRI() {
			return DMDRI;
		}


		public void setDMDRI(Integer dMDRI) {
			DMDRI = dMDRI;
		}


		public Integer getDMDRA() {
			return DMDRA;
		}


		public void setDMDRA(Integer dMDRA) {
			DMDRA = dMDRA;
		}


		public String getNA4() {
			return NA4;
		}


		public void setNA4(String nA4) {
			NA4 = nA4;
		}


		public Integer getDMSCST() {
			return DMSCST;
		}


		public void setDMSCST(Integer dMSCST) {
			DMSCST = dMSCST;
		}


		public String getNA5() {
			return NA5;
		}


		public void setNA5(String nA5) {
			NA5 = nA5;
		}


		public Integer getDMINTS() {
			return DMINTS;
		}


		public void setDMINTS(Integer dMINTS) {
			DMINTS = dMINTS;
		}


		public String getNA6() {
			return NA6;
		}


		public void setNA6(String nA6) {
			NA6 = nA6;
		}


		public BigDecimal getDMCBAL() {
			return DMCBAL;
		}


		public void setDMCBAL(BigDecimal dMCBAL) {
			DMCBAL = dMCBAL;
		}
		
		
		

		
		
		
	}
	
	
	
	
	
	
	
	
	

}
