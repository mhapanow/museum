package com.ciessa.museum.bz.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.bz.legacy.Cus060BzService.CUS060Adapter;
import com.ciessa.museum.dao.DataSetDAO;
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
	
	Grmria objGrmria = new Grmria();
	//Tap002 objTap002 = new Tap002();
	Tap003 objTap003 = new Tap003();
	Compcmo objCompcmo = new Compcmo();
	Zbhpvrz objZbhpvrz = new Zbhpvrz();
	Cbiptic objCbiptic = new Cbiptic();
	Tap902 objTap902 = new Tap902();
	Saldom objSaldom = new Saldom();
	Tap03w objTap03w = new Tap03w();
	Transm objTransm = new Transm();
	Tap901 objTap901 = new Tap901();
	
	List<Tap002w> listTap002w = null;
	
	Integer acctno = 0;
	Integer a1 = 0;
	String wkbank = "";
	String wkacct = "";
	String dsmkey = "";
	String bknum = "";
	String dmwhfg = "";
	Integer fecvto = 0;
	
	
	
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
			
			
			
			
			SubRutLeemae(ds);
			listTap002w = myDAOTap002w.getUsing(ds);
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
			//SubRutMfproc(ds);
			
			
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
