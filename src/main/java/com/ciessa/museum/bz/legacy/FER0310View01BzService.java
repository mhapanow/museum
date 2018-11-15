package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
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
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.CcupginDAO;
import com.ciessa.museum.dao.legacy.CcupvinDAO;
import com.ciessa.museum.dao.legacy.CompcmoDAO;
import com.ciessa.museum.dao.legacy.GrmactDAO;
import com.ciessa.museum.dao.legacy.GrmcusDAO;
import com.ciessa.museum.dao.legacy.GrmriaDAO;
import com.ciessa.museum.dao.legacy.InsprefDAO;
import com.ciessa.museum.dao.legacy.Tap002DAO;
import com.ciessa.museum.dao.legacy.Tap003DAO;
import com.ciessa.museum.dao.legacy.ZbhpvrzDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Ccupgin;
import com.ciessa.museum.model.legacy.Ccupvin;
import com.ciessa.museum.model.legacy.Compcmo;
import com.ciessa.museum.model.legacy.Grmact;
import com.ciessa.museum.model.legacy.Grmcus;
import com.ciessa.museum.model.legacy.Grmria;
import com.ciessa.museum.model.legacy.Inspref;
import com.ciessa.museum.model.legacy.Tap002;
import com.ciessa.museum.model.legacy.Tap003;
import com.ciessa.museum.model.legacy.Zbhpvrz;

public class FER0310View01BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(FER0310View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	GrmriaDAO myDAOGrmria;
	
	@Autowired
	CcupvinDAO myDAOCcupvin;
	
	@Autowired
	CcupginDAO myDAOCcupgin;
	
	@Autowired
	GrmcusDAO myDAOGrmcus;
	
	@Autowired
	InsprefDAO myDAOInspref;
	
	@Autowired
	Tap002DAO myDAOTap002;
	
	@Autowired
	Tap003DAO myDAOTap003;
	
	@Autowired
	GrmactDAO myDAOGrmact;
	
	@Autowired
	CompcmoDAO myDAOCompcmo;
	
	@Autowired
	ZbhpvrzDAO myDAOZbhpvrz;
	
	
	Grmact objGrmact = new Grmact();
	Grmria objGrmria = new Grmria();
	Ccupvin objCcupvin = new Ccupvin();
	Ccupgin objCcupgin = new Ccupgin();
	Grmcus objGrmcus = new Grmcus();
	Inspref objInspref = new Inspref();
	Tap002 objTap002 = new Tap002();
	Tap003 objTap003 = new Tap003();
	
	Compcmo objCompcmo = new Compcmo();
	Zbhpvrz objZbhpvrz = new Zbhpvrz();
	
	List<Grmria> listGrmria = null;
	
	String modelb = "";
	String xxfp = "";
	String offint = "";
	Integer codmon = 0;
	Integer dmssno = 0;
	Integer dmhpnr = 0;
	Integer dmbunr = 0;
	String op = "";
	String march = "";
	String cfpbfg = "";
	String ddinq = "";
	String bcrkey = "";
	Integer frjul = 0;
	String sper = "";
	Integer sfrq = 0;
	Integer srday = 0;
	Integer yesday = 0;
	String chnkey = "";
	Integer aadia = 0;
	Integer aames = 0;
	Integer aaano = 0;
	Integer aadd = 0;
	Integer aamm = 0;
	Integer aaaa = 0;
	Integer aaamm = 0;
	Integer adia = 0;
	Integer ames = 0;
	Integer aano = 0;
	Integer a4 = 0;
	Integer rest2 = 0;
	Integer cx = 0;
	String cfmn = "";
	Integer acodr = 0;
	Integer wk5 = 0;
	Integer wk4 = 0;
	Integer cprmo = 0;
	String giro = "";
	Integer acctan = 0;
	Integer acctno = 0;
	String visual = "";
	String titu1 = "";
	BigDecimal narn = new BigDecimal(0);
	Integer tojul = 0;
	Integer sres3 = 0;
	Integer sfrqx = 0;
	String swork4 = ""; //subtring de 4 posiciones dia/mes
	Integer sy = 0;
	Integer sres5 = 0;
	Integer sres3x = 0;
	Integer sm = 0;
	Integer tocal = 0;
	Integer dtefmt = 0;
	Integer srrslt = 0;
	Integer frcal = 0;
	
	Integer dmbk = 0;
	Integer dmbrch = 0;
	Integer dmacct = 0;
	BigDecimal wsbal = new BigDecimal(0);
	Integer wmmacc = 0;
	Integer scrhoy = 0;
	Integer wdspin = 0;
	Integer sdbhoy = 0;
	Integer dmdopn = 0;
	Integer sdbpen = 0;
	Integer dmdla = 0;
	Integer wsdact = 0;
	Integer dmdlst = 0;
	Integer dmccaw = 0;
	Integer dmpbal = 0;
	Integer sreten = 0;
	Integer dmiodl = 0;
	Integer wsinmo = 0;
	Integer wqextr = 0;
	Integer wsdisp = 0;
	Integer scrpen = 0;
	Integer fecvto = 0;
	Integer fecame = 0;
	Integer amtamx = 0;
	String wrevi = "";
	String aabar = "";
	String na1 = "";
	String na2 = "";
	String tipcta = "" ;
	String na3 = "";
	String divisi = "";
	String wwidct = "";
	String wwnoma = "";
	String wwsrds = "";
	String dmoff = "";
	String na4 = "";
	String tpbloq = "";
	String na5 = "";
	String na6 = "";
	String dmwhfg = "";
	String sflag = "";
	Integer[] sjl5 = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
	String dscndt = "";
	String bknum = "001";
	String[] amn = new String[99];
	String[] tcpt = new String[19];
	String[] cky = new String[19];
	Integer[] sdim = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	Integer sd = 0;
	Integer raactn = 0;
	
	String rqacrt = "";
	String vistat = "";
	String gistat = "";
	Integer bkgnty = 0;
	Integer bkgncd = 0;
	Long virmcn = new Long("0");
	Integer a1 = 0;
	String wkbank = "";
	String wkacct = "";
	String dsmkey = "";
	Integer scal6 = 0;
	Double test = 0.0;
	String srcvt = "";
	Integer yyyy = 0;
	String swtaky = "";
	String swtaac = "";
	Integer wshmes = 0;
	Integer wswavs = 0;
	String rqprcd = "";
	String azbh = "";
	Long rqactn = new Long("0");
	Integer dmdld1 = 0;
	BigDecimal prtacc = new BigDecimal(0);
	String wkalt = "";
	String samd6 = "";
	String aaa6 = "";
	String saa6 = "";
	String amm6 = "";
	String smm6 = "";
	String add6 = "";
	String sdd6 = "";
	String adma6 = "";
	Integer m = 0;
	Integer rate = 0;
	Boolean WshmesWswavs = false;
	Integer hssec = 0;
	Integer wkssno = 0;
	Integer wkhmph = 0;
	Integer wkbuph = 0;
	Integer dmdint = 0;
	String fld5 = "";
	String accctl = "";
	Integer dmpthr = 0;
	String wktitl = "";
	Integer dmald = 0;
	
	Long float0 = new Long(0);
	Long float1 = new Long(0);
	BigDecimal dmcol0 = new BigDecimal(0);
	BigDecimal dmcol1 = new BigDecimal(0);
	
	Integer aacta1 = 0;
	Integer aacta2 = 0;
	Integer dshbk = 0;
	Integer dshdsv = 0;
	Long dsacct = new Long(0);
	Integer wkappl = 0;
	String cfpacb = "";
	Integer dmnsfa = 0;
	Integer dmnsff = 0;
	Integer srxctr = 0;
	BigDecimal[] dsrt = new BigDecimal[10];
	Integer dmacc5 = 0;
	long flt1 = new Long(0);
	long flt2 = new Long(0);
	long flt3 = new Long(0);
	long flt4 = new Long(0);
	long flt5 = new Long(0);
	Integer dmaccr = 0;
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
	Integer toaccr = 0;

	
	//Funtioness	
	FUNCIONESBzService fc = new FUNCIONESBzService();

	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			
			String acctno = obtainStringValue("acctno", "0");
			
			
			this.dscndt = this.fc.FormatoFechaHora("ddMMyyyy");
			this.modelb = "0";
			this.azbh = "Y";
			if (!this.xxfp.equals("1")) {
				//SubRutNewarn(ds);    
				this.offint = " ";
				this.xxfp = "1";
				this.codmon = 0;
			}
			this.dmssno = 0;
			this.dmhpnr = 0;
			this.dmbunr = 0;
			this.op = "I";
			this.march = "";
			this.cfpbfg = " ";
			this.ddinq = "X";
			this.op = "O";
			this.bcrkey = "";
			this.bcrkey = this.bknum;
			//this.frjul = Fecha actual en formato Juliano (AAAADDD)
			Date date = new Date();
			DateFormat d = new SimpleDateFormat("yyyyD"); 
			this.frjul = Integer.parseInt(d.format(date)); 
			
			this.sper = "D";
			this.sfrq = 1;
			this.srday = 00;
			SubRutSrp019(ds);
			this.adia = Integer.parseInt(this.fc.FormatoFechaHora("dd"));
			this.ames = Integer.parseInt(this.fc.FormatoFechaHora("MM"));
			this.aano = Integer.parseInt(this.fc.FormatoFechaHora("yyyy"));
			this.yesday = this.tojul;
			this.chnkey = "";
			this.aadia = this.adia;
			this.aames = this.ames;
			this.aaano = this.aano;
			this.aadd = this.adia;
			this.aamm = this.ames;
			this.aaaa = this.aano;
			this.aaamm = this.ames - 1;
			
			if (this.aaamm == 0) {
				this.aaamm = 12;
				this.a4 = this.aano - 1;
				this.aaamm = this.a4;
			}else {
				this.aaamm = this.aano;
			}
			this.rest2 = this.aano%4;
			//this.rest2 = Mover resto del cálculo anterior
			if (this.rest2 == 0) {
				for (int q = 3; q < 13; q++) {
					sjl5[q] = sjl5[q] + 1;
				}
			}
			
			this.chnkey = this.bknum;
			for (int c = 1; c < 99; c++) {
				this.cx = c;
				//archivo cfp001
				if (true) {
					this.cfmn = "00";
				}
				//this.amn[c] = obj.getCfmn();
				//this.tcpt[c] = obj.getCftcpt();
			}
			
			//*			
			//*CFP001
			this.cky[4] = "";
			
			this.acodr = 1;
			this.wk5 = Integer.parseInt(this.dscndt);
			this.wk4 = this.wk5;
			this.cprmo = this.wk4;
			SubRutBorro(ds);
			this.giro = "0";

			this.acctan = this.acctno;
			objTap002 = myDAOTap002.getUsingAcctan(ds, acctan.toString());
			if (objTap002 != null) {
				this.giro = "1";
				this.acctno = 0;
				SubRutBorro(ds);
				this.visual = "1";
				while (this.visual == "1") {
					SubRutSrcase(ds);
					if (this.acctno.toString().substring(0,2).equals("0")) {
						this.titu1 = "Cant.Ch.Comp./Revision :";
					}else {
						this.titu1 = "Extracciones efectuadas:";
					}
					if (this.giro == "1") {
						
					}
					this.giro = "1";
					this.visual = "0";
				}
				
			}
			List<String> a = new ArrayList();
			String[] b = new String[1];
			
			returnValue = getJSONRepresentationFromObject(a, b);
			
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
					this.swork4 = this.sd.toString();
				}else {
					this.swork4 = this.sd.toString();
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
	
	private String SubRutBorro(DataSet ds) {
		try {
			this.dmbk = 0;
			this.dmbrch = 0;
			this.codmon = 0;
			this.dmacct = 0;
			this.wsbal = BigDecimal.ZERO;
			this.wmmacc = 0;
			this.scrhoy = 0;
			this.wdspin = 0;
			this.sdbhoy = 0;
			this.dmdopn = 0;
			this.sdbpen = 0;
			this.dmdla = 0;
			this.wsdact = 0;
			this.dmdlst = 0;
			this.dmccaw = 0;
			this.dmpbal = 0;
			this.sreten = 0;
			this.dmiodl = 0;
			this.wsinmo = 0;
			this.wqextr = 0;
			this.wrevi = "";
			this.aabar = "";
			this.wsdisp = 0;
			this.scrpen = 0;
			this.fecvto = 0;
			this.na1 = "";
			this.na2 = "";
			this.tipcta = "" ;
			this.na3 = "";
			this.divisi = "";
			this.wwidct = "";
			this.wwnoma = "";
			this.wwsrds = "";
			this.dmoff = "";
			this.na4 = "";
			this.tpbloq = "";
			this.na5 = "";
			this.na6 = "";
			this.dmwhfg = "";
			this.fecame = 0;
			this.amtamx = 0;
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}

	private String SubRutSrcase(DataSet ds) {
		try {
			this.wwidct = "";
			this.wwnoma = "";
			this.wwsrds = "";
			//this.rqrmcn = "";
			this.sflag = "NO";
			this.raactn  = this.dmacct;
			objGrmact = myDAOGrmact.getUsingRaactn(ds, raactn.toString());
			
			if (objGrmact != null) {
				this.sflag = "SI";
			}

			this.wwidct = objGrmact.getRaidct();
			if (this.sflag.equals("SI") && !objGrmact.getRaidct().equals("")) {
				this.rqacrt = "T";
				objGrmria = myDAOGrmria.getUsingRaprcdAndRaactnAndRqacrt(ds, objGrmact.getRaprcd(), raactn.toString(), rqacrt);
				if (objGrmria != null) {
					if (objGrmact.getRaidct().equals("PAY")) {//AAPAY
						this.virmcn = objGrmria.getRqrmcn();
						this.vistat = "A";
						objCcupvin = myDAOCcupvin.getUsingVirmcnAndVistat(ds, virmcn.toString(), vistat.toString());
						if (objCcupvin != null) {
							if (!objCcupvin.getViempp().equals("000") && !objCcupvin.getViempp().equals(" ")) {
								this.gistat= "A";
								objCcupgin = myDAOCcupgin.getUsingViemppAndViidgiAndGistat(ds, objCcupvin.getViempp(), objCcupvin.getViidgi(), gistat);
								if (objCcupgin != null) {
									this.wwnoma = objCcupgin.getGidegi();
								}
							}
						}
					}
					objGrmcus = myDAOGrmcus.getUsingRqrmcn(ds, objGrmria.getRqrmcn().toString());
					if (objGrmcus != null) {
						this.bkgnty = 66;
						this.bkgncd = objGrmcus.getRbsgcd();
						objInspref = myDAOInspref.getUsingBkgntyAndBkgncd(ds, bkgnty.toString(), bkgncd.toString());
						this.wwsrds = objInspref.getBksrds();
					}
				}
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}

	private String SubRutLeemae(DataSet ds) {
		try {
			this.a1 = Integer.parseInt(this.acctno.toString().substring(0,1));
			
			if (this.a1 == 0) {
				this.dmwhfg = null;
				this.fecvto = null;
			}
			this.wkbank = this.bknum;
			if (this.acctno < 1000000000) {
				this.wkacct = "06" + this.acctno.toString();
			}
			else {
				this.wkacct = "01" + this.acctno.toString();
			}
			//this.wkacct = this.acctno.toString();
			this.dsmkey = this.wkacct;
			this.dsmkey = this.bknum;
			SubRutMfproc(ds);
			
			
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
			this.swork4 = this.frjul.toString();
			this.tocal = Integer.parseInt(this.swork4);
			this.sy = Integer.parseInt(this.swork4);
			this.sres3 = this.frjul;
			this.test = this.sy * 0.25;
			if (this.test == 0 && this.sres3 == 60) {
				this.sm = 2;
				this.sd = 29;
			}else {
				if (this.test == 0 && this.sres3 > 59) {
					this.sres3 = this.sres3 - 1;
				}
				/*this.sm = 1; //*mat
				if (this.sjl5[0] >= this.sres3) {
					this.sres3 = this.sres3 - 1;
				}
				this.sm = this.sm - 1;
				this.sd = this.sres3 - this.sjl5[this.sm];*/
			}
			if (this.dtefmt == 1) {
				this.swork4 = this.sd.toString() + this.sm.toString();
			}else {
				this.swork4 = this.sd.toString() + this.sm.toString();
			}
			this.tocal = Integer.parseInt( this.swork4);
			this.frcal = this.tocal;
			SubRutSrp012(ds);
			
			
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
					this.sm = Integer.parseInt(this.swork4.substring(3, 4));
					this.sd = Integer.parseInt(this.swork4.substring(1, 2));
				}else {
					this.sm = Integer.parseInt(this.swork4.substring(3, 4));
					this.sd = Integer.parseInt(this.swork4.substring(1, 2));
				}
				this.tojul = this.sjl5[this.sm];
				this.tojul = this.yyyy;
				this.tojul = this.tojul + this.sd;
				this.test = this.sy * 0.25;
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
	
	private String SubRutMfproc(DataSet ds) {
		try {
			this.swtaky = this.dsmkey;
			this.swtaac = this.swtaky.substring(5, 14); 
			objTap002 = myDAOTap002.getUsingTipoAndCuenta(ds, this.swtaky, this.swtaac);
			if (objTap002 != null) {
				this.wshmes = 0;
				if (objTap002.getDmtyp() == 6) {
					objCompcmo = myDAOCompcmo.getUsingDmacctAndAaamm(ds, objTap002.getDmacct().toString(), aaamm.toString());
					if (objCompcmo != null) {
						this.WshmesWswavs = true;
						this.wshmes = objCompcmo.getHusmes();
					}
				}
				
				this.wswavs = 0;
				if (objTap002.getDmtyp() == 1) {
					this.rqprcd = "IA";
				}else {
					this.rqprcd = "CA";
				}
				if (this.azbh.equals("Y")) {
					this.rqactn = objTap002.getDmacct();
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

				this.dmdopn = Integer.parseInt( new SimpleDateFormat("ddMMyy").format(new SimpleDateFormat("yyMMdd").parse(objTap002.getDmdopn().toString())) );
				this.dmdla = Integer.parseInt( new SimpleDateFormat("ddMMyy").format(new SimpleDateFormat("yyMMdd").parse(objTap002.getDmdla().toString())) );
				this.dmdlst = Integer.parseInt( new SimpleDateFormat("ddMMyy").format(new SimpleDateFormat("yyMMdd").parse(objTap002.getDmdlst().toString())) );
				this.dmdld1 = objTap002.getDmdld();
				this.codmon = objTap002.getDmcmcn();
				this.prtacc = objTap002.getDmtacc();
				this.wkalt = "";
				this.wkbank = this.bknum;
				
				if (this.acctno < 1000000000) {
					this.wkacct = "06" + this.acctno.toString();
				}
				else {
					this.wkacct = "01" + this.acctno.toString();
				}

				this.dsmkey = this.bknum + this.wkacct;
				SubRutGtcust(ds);    
				this.hssec = this.wkssno;
				this.dmssno = this.hssec;
				this.dmhpnr = this.wkhmph;
				this.dmbunr = this.wkbuph;
				if (objTap002.getDmdint() != 0) {
					//CFP001
					if (true) {
						this.dmdint = 0;
					}else {
						this.bcrkey = "";
						this.bcrkey = "1";
						this.m = 0;
						this.rate = this.rate - this.rate;
						while (this.m < 4) {
							this.m += 1;
							if (this.m <= 4) {
								//*CFPI
								this.fld5 = this.bknum; // + CFPI (M)
								this.bcrkey = this.fld5;
								//this.rate[this.m] = this.dsrt[1].divide(new BigDecimal(36));
								//this.rate[this.m] = this.dsrt[1].divide(new BigDecimal(36.5));
							}
						}
					}
				}
				this.bcrkey = " ";
				//TOACCR = Fecha del día con formato juliano AAAADDD
				Date date = new Date();
				DateFormat d = new SimpleDateFormat("yyyyD"); 
				this.toaccr = Integer.parseInt(d.format(date)); 

				this.accctl = "A";
				
				SubRutAccr01(ds, objTap002.getDmbit2(), objTap002.getDmacc5(), objTap002.getDmcbal(), objTap002.getDmcf3(), objTap002.getDmcf4(), objTap002.getDmcf5() );  
				this.frjul = this.dmacc5; //variable
				SubRutSrp003(ds);    
				this.dmpthr = this.scal6;
				this.wkalt = objTap002.getDmstct();
				this.wkbank = this.bknum;
				this.wkacct = objTap002.getDmacct().toString();
				this.wkacct = "06";
				this.wkacct = "01";
				this.wktitl = objTap002.getDmtitl();
				SubRutGtcust(ds);    
				this.hssec = this.wkssno;
				this.dmssno = this.hssec;
				this.dmhpnr = this.wkhmph;
				this.dmbunr = this.wkbuph;
				this.wsbal = objTap002.getDmcbal();
				this.float0 = objTap002.getDmcf1() + objTap002.getDmcf2() + objTap002.getDmcf3() + objTap002.getDmcf4() + objTap002.getDmcf5();
				this.flt1   = objTap002.getDmcf1();
				this.flt2 = objTap002.getDmcf2();
				this.flt3 = objTap002.getDmcf3();
				this.flt4 = objTap002.getDmcf4();
				this.flt5 = objTap002.getDmcf5();
				this.float1 = objTap002.getDmcf1() + objTap002.getDmcf2() + objTap002.getDmcf3() + objTap002.getDmcf4() + objTap002.getDmcf5();
				this.dmcol0 = objTap002.getDmcbal().subtract(new BigDecimal(this.float0).subtract(new BigDecimal(objTap002.getDmhold())));
				this.dmcol1 = objTap002.getDmcbal().subtract(new BigDecimal(this.float1).subtract(new BigDecimal(objTap002.getDmhold())));
				
				if (objTap002.getDmemp().equals("E") || objTap002.getDmemp().equals("O") || objTap002.getDmemp().equals("D")) {
					this.dmald = 0;
				}
				if (objTap002.getDmemp().equals("E") || objTap002.getDmemp().equals("O") || objTap002.getDmemp().equals("D")) {
					this.aacta1 = this.acctno;
					this.aacta2 = this.aacta1;
				}
				this.dshbk = objTap002.getDmbk();
				this.dshdsv = objTap002.getDmtyp();
				this.dsacct = objTap002.getDmacct();
				objTap003 = myDAOTap003.getUsingDmacct(ds, objTap002.getDmacct().toString());
			}
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutSrp012(DataSet ds) {
		try {
			this.scal6 = this.frcal;
			this.sy = this.frcal;
			this.scal6 = this.sy;
			
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
			//Ejecutar objeto "CUS060'  con los parámetros WKBANK, WKACCT, WKALT, WKTITL, WKAPPL, NA1, NA2, NA3, NA4, NA5, NA6, WKSSNO, WKBUPH y WKHMPH
			this.wkalt = " ";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutAccr01(DataSet ds, String Dmbit2, Integer Dmacc5, BigDecimal Dmcbal,
			Long Dmcf3, Long Dmcf4, Long Dmcf5) {
		try {
			if (!Dmbit2.substring(5).equals("1")) {
				if (Dmacc5 == 0) {
					this.frjul = this.toaccr;
					this.sper = "D";
					this.sfrq = 1;
					this.srday = 00;
					SubRutSrp019(ds);    
					this.dmacc5 = this.tojul;
					this.dmaccr = this.tojul;
				}
				if (this.cfpacb.equals("1")) {
					this.frjul = this.dmacc5;
					//SubRutSrp013(ds);    
					this.dmaccr = this.tojul;
				}
				this.dmnsfa = this.dmnsfa - this.dmnsfa;
				this.dmnsff = this.dmnsff - this.dmnsff;
				this.srxctr = this.srxctr - this.srxctr;
				//*348 35
				this.dmybal = Dmcbal;
				this.dmdrt = 0;
				this.dmcrt = 0;
				this.dmdrti = 0;
				this.dmcrti = 0;
				this.dmcktd = 0;
				this.dmcf2 = Dmcf3;
				this.dmcf3 = Dmcf4;
				this.dmcf4 = Dmcf5;
				this.dmcf5 = new Long(0);
				this.dmdtdy = 0;
				this.dmctdy = 0;
				this.dmsofd = 0;
				this.dmsofc = 0;
				this.dmiapa = 0;
				this.dmiada = 0;
				this.dmfrst = 0;
				/*if (this.dsdow == 2) {
					this.dmactv= "";
				}
				if (this.dsmofg =="F") {
					this.dmttrn = 0;
					this.dmmwd = 0;
				}*/
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

			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}


}
