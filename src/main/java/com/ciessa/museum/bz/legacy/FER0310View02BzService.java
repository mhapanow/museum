package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.bz.legacy.Cus060BzService.CUS060Adapter;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.Cfp001205DAO;
import com.ciessa.museum.dao.legacy.CompcmoDAO;
import com.ciessa.museum.dao.legacy.GrmriaDAO;
import com.ciessa.museum.dao.legacy.SaldomDAO;
import com.ciessa.museum.dao.legacy.Tap002wDAO;
import com.ciessa.museum.dao.legacy.Tap003DAO;
import com.ciessa.museum.dao.legacy.Tap902DAO;
import com.ciessa.museum.dao.legacy.ZbhpvrzDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cbiptic;
import com.ciessa.museum.model.legacy.Cfp001205;
import com.ciessa.museum.model.legacy.Compcmo;
import com.ciessa.museum.model.legacy.Grmria;
import com.ciessa.museum.model.legacy.Saldom;
import com.ciessa.museum.model.legacy.Tap002w;
import com.ciessa.museum.model.legacy.Tap003;
import com.ciessa.museum.model.legacy.Tap03w;
import com.ciessa.museum.model.legacy.Tap901;
import com.ciessa.museum.model.legacy.Tap902;
import com.ciessa.museum.model.legacy.Transm;
import com.ciessa.museum.model.legacy.Zbhpvrz;

public class FER0310View02BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(FER0310View02BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	GrmriaDAO myDAOGrmria;
	
	@Autowired
	Tap002wDAO myDAOTap002w;
	
	@Autowired
	Tap003DAO myDAOTap003;
	
	@Autowired
	CompcmoDAO myDAOCompcmo;
	
	@Autowired
	ZbhpvrzDAO myDAOZbhpvrz;
	
	/*@Autowired
	CbipticDAO myDAOCbiptic;*/

	@Autowired
	Tap902DAO myDAOTap902;

	@Autowired
	SaldomDAO myDAOSaldom;

	/*@Autowired
	Tap03wDAO myDAOTap03w;

	@Autowired
	TransmDAO myDAOTransm;

	@Autowired
	Tap901DAO myDAOTap901;*/
	
	@Autowired
	Cfp001205DAO myDAOCfp001205;
	
	//Grmria objGrmria = new Grmria();
	Tap002w objTap002w = new Tap002w();
	Tap003 objTap003 = new Tap003();
	Compcmo objCompcmo = new Compcmo();
	Zbhpvrz objZbhpvrz = new Zbhpvrz();
	Cbiptic objCbiptic = new Cbiptic();
	Tap902 objTap902 = new Tap902();
	Saldom objSaldom = new Saldom();
	Tap03w objTap03w = new Tap03w();
	Transm objTransm = new Transm();
	Tap901 objTap901 = new Tap901();
	Cfp001205 objCfp001205 = new Cfp001205();
	
	List<Tap002w> listTap002w = null;
	List<Grmria> listGrmria = null;
	
	String acctno = "";
	String acctan = "";
	Integer aaamm = 0;
	Integer ames = 0;
	String bknum = "001";
	String wkbank = "";
	String wkacct = "";
	String dsmkey = "";
	String dmwhfg = "";
	String swtaky = "";
	String swtaac = "";
	Integer wshmes = 0;
	Integer wswavs = 0;
	String rqprcd = "";
	Long dmacct = new Long("0");
	String azbh = "";
	Long rqactn = new Long("0");
	Integer dmdopn = 0;
	Integer dmdla = 0;
	Integer dmdlst = 0;
	
	Integer m = 0;
	BigDecimal[] rate = new BigDecimal[4];
	Integer hssec = 0;
	Integer wkssno = 0;
	Integer wkhmph = 0;
	Integer wkbuph = 0;
	Integer dmdint = 0;
	Integer dmdld1 = 0;
	Integer codmon = 0;
	BigDecimal prtacc = new BigDecimal(0);
	String wkalt = "";
	Integer dmssno = 0;
	Integer dmhpnr = 0;
	Integer dmbunr = 0;
	String bcrkey = "";
	Integer fld5 = 0;
	BigDecimal[] dsrt = new BigDecimal[10];
	Integer[] cfpi = { 0, 0, 0, 0};
	Integer toaccr = 0;
	String accctl = "";
	Integer scal6 = 0;
	Integer dmpthr = 0;
	Integer frjul = 0;
	
	
	

	String rpta = "";
	
	//Funtioness	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
	@Autowired
	Cus060BzService Cus060;
	
	CUS060Adapter cu060adapter = null;
	
	
	
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
			
			rpta += SubRutLeemae(ds);
			//listTap002w = myDAOTap002w.getUsing(ds);
			if (listTap002w != null) {
				SubRutDispon(ds);
				if (this.acctno.toString().substring(0,1).equals("0")) {
					SubRutComput(ds); 
				}
				if (true) {//presiona F3
					SubRutStproc(ds); 
					SubRutStprow(ds);
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
	
	private String SubRutLeemae(DataSet ds) {
		try {
			if ( new Long(this.acctno) < new Long("5000000000")) {
				//deshanilitar DMWHFG y FECVTO;
			}
			this.wkbank = this.bknum;
			if ( new Long(this.acctno) < new Long("5000000000")) {
				this.wkacct = "06" + this.acctno;
			}
			else {
				this.wkacct = "01" + this.acctno;
			}
			this.dsmkey = this.wkacct;
			this.dsmkey = this.bknum;
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
			this.swtaac = this.swtaky.substring(5, 14); 
			objTap002w = myDAOTap002w.getUsingTipoAndCuenta(ds, this.swtaky, this.swtaac);
			if (objTap002w != null) {
				this.wshmes = 0;
				this.dmacct = objTap002w.getDmacct();
				if (objTap002w.getDmtyp() == 6) {
					objCompcmo = myDAOCompcmo.getUsingDmacctAndAaamm(ds, objTap002w.getDmacct().toString(), aaamm.toString());
					if (objCompcmo != null) {
						//this.WshmesWswavs = true;
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
				if ( new Long(this.acctno) < new Long("5000000000")) {
					this.wkacct = "06" + this.acctno;
				}
				else {
					this.wkacct = "01" + this.acctno;
				}

				this.dsmkey = this.bknum.concat( this.wkacct);
				//SubRutGtcust(ds);
				this.hssec = this.wkssno;
				this.dmssno = this.hssec;
				this.dmhpnr = this.wkhmph;
				this.dmbunr = this.wkbuph;
				if (objTap002w.getDmdint() != 0) {
					objCfp001205 = myDAOCfp001205.getUsingKeyDmdint(ds, objTap002w.getDmdint());
					if (objCfp001205 != null) {
						this.dmdint = 0;
					}else {
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
				
				//SubRutAccr01(ds, objTap002w);
				this.frjul = objTap002w.getDmacc5();
				//SubRutSrp003(ds);    
				this.dmpthr = this.scal6;
				this.wkalt = objTap002w.getDmstct();
				this.wkbank = this.bknum;
				
				
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutDispon(DataSet ds) {
		try {
		
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}
	
	private String SubRutComput(DataSet ds) {
		try {
		
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}
	
	private String SubRutStproc(DataSet ds) {
		try {
		
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}
	
	private String SubRutStprow(DataSet ds) {
		try {
		
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
		
	}
	
	

}
