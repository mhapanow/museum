package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.CcrpcreDAO;
import com.ciessa.museum.dao.legacy.CcrpproDAO;
import com.ciessa.museum.dao.legacy.Glc001DAO;
import com.ciessa.museum.dao.legacy.GrmcdaDAO;
import com.ciessa.museum.dao.legacy.GrmidaDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Ccrpcre;
import com.ciessa.museum.model.legacy.Ccrppro;
import com.ciessa.museum.model.legacy.Glc001;
import com.ciessa.museum.model.legacy.Grmcda;
import com.ciessa.museum.model.legacy.Grmida;

public class CCRR0500View01BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(CCRR0500View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CcrpcreDAO myDaoCcrpcre;
	
	@Autowired
	CcrpproDAO myDaoCcrppro;
	
	@Autowired
	GrmidaDAO myDaoGrmida;
	
	@Autowired
	GrmcdaDAO myDaoGrmcda;
	
	@Autowired
	Glc001DAO myDaoGlc001;
	
	//Entity
	Ccrpcre ObjCcrpcre	= new Ccrpcre();
	Ccrppro ObjCcrppro = new Ccrppro ();
	Grmida ObjGrmida = new Grmida();
	Grmcda ObjGrmcda = new Grmcda();
	Glc001 ObjGlc001 = new Glc001();
	
	//Variables_globales
	String crnucr = null;
	String cuna1 = null;
	// Variables 
	String ordepr = null;
	String anomcl = null;
	String moneda = null;
	
	List<Ccrpcre> listCcrpcre  = null;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		String rpta = "";

		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();

			//Parametros_Get
			crnucr = obtainStringValue("crnucr", null);
		
			ObjCcrpcre = myDaoCcrpcre.getUsingNpres(ds, crnucr);
			if (ObjCcrpcre == null) {
				log.log(Level.SEVERE, "Ccrpcre - No existe el registro", new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException("No existe el registro", new Exception())).toString();
			}else {
				//ejecuta rutina
				rpta = SubRutObt580(ds);
			}
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			long diff = new Date().getTime() - millisPre;
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
					"CRBANC",
					"CRNUCR",
					"CRCAPI",
					"CRLINE",
					"CRTRTA",
					"CRTRIN",
					"CRCOAJ",
					"CRCTAI",
					"CRCINA"
			};
			// Obtains the user JSON representation
			CCRR0500Adapter adapted = new CCRR0500Adapter(ObjCcrpcre);
			returnValue = getJSONRepresentationFromObject(adapted, fields);

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

	private String SubRutObt580(DataSet ds) {
		
		try {
			ObjCcrppro = myDaoCcrppro.getUsingCrcapiAndCrlineAndCrtrtaAndCrtrinAndCrcoajAndCrctaiAndCrcina(ds, ObjCcrpcre.getCrcapi(), ObjCcrpcre.getCrline().toString(), ObjCcrpcre.getCrtrta().toString(), ObjCcrpcre.getCrtrin().toString(), ObjCcrpcre.getCrcoaj().toString(), ObjCcrpcre.getCrctai().toString(), ObjCcrpcre.getCrcina().toString());
			if (ObjCcrppro == null) {
				this.ordepr = "??????????";
			}
			
			ObjGrmida = myDaoGrmida.getUsingRirmcn(ds, ObjCcrpcre.getCrnucl().toString());
			if (ObjGrmida != null) {
				this.cuna1 =ObjGrmida.getRifsnm() + " " +ObjGrmida.getRilsnm();
			}else {
				ObjGrmcda = myDaoGrmcda.getUsingRyrmcn(ds, ObjCcrpcre.getCrnucl().toString());
				if (ObjGrmcda != null) {
					this.cuna1 =ObjGrmcda.getRycpcn();
				}
			}
			
			if (this.cuna1 == "") {
				this.cuna1 ="??????????";
			}
			this.anomcl = this.cuna1;
			
			ObjGlc001 = myDaoGlc001.getUsingCrcomo(ds, ObjCcrpcre.getCrcomo().toString());
			
			if (ObjGlc001 == null) {
				this.moneda = "??????????";
			}else {
				this.moneda = ObjGlc001.getGcdesc();
			}
			
		} catch (ASException e) {
			return e.getMessage();
			
		}
		
		return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	public class CCRR0500Adapter {
		String CRBANC = null;
		String CRNUCR = null;
		String CRCAPI = null;
		String CRLINE = null;
		String CRTRTA = null;
		String CRTRIN = null;
		String CRCOAJ = null;
		String CRCTAI = null;
		String CRCINA = null;
		
		public CCRR0500Adapter(Ccrpcre src) {
			this.CRBANC = src.getCrbanc().toString(); 
			this.CRNUCR = src.getCrnucr().toString(); 
			this.CRCAPI = src.getCrcapi();
			this.CRLINE = src.getCrline().toString();
			this.CRTRTA = src.getCrtrta().toString();
			this.CRTRIN = src.getCrtrin().toString();
			this.CRCOAJ = src.getCrcoaj().toString();
			this.CRCTAI = src.getCrctai().toString(); 
			this.CRCINA = src.getCrcina().toString();

		}

		public String getCRBANC() {
			return CRBANC;
		}
		public void setCRBANC(String cRBANC) {
			CRBANC = cRBANC;
		}
		public String getCRNUCR() {
			return CRNUCR;
		}
		public void setCRNUCR(String cRNUCR) {
			CRNUCR = cRNUCR;
		}
		public String getCRCAPI() {
			return CRCAPI;
		}
		public void setCRCAPI(String cRCAPI) {
			CRCAPI = cRCAPI;
		}
		public String getCRLINE() {
			return CRLINE;
		}
		public void setCRLINE(String cRLINE) {
			CRLINE = cRLINE;
		}
		public String getCRTRTA() {
			return CRTRTA;
		}
		public void setCRTRTA(String cRTRTA) {
			CRTRTA = cRTRTA;
		}
		public String getCRTRIN() {
			return CRTRIN;
		}
		public void setCRTRIN(String cRTRIN) {
			CRTRIN = cRTRIN;
		}
		public String getCRCOAJ() {
			return CRCOAJ;
		}
		public void setCRCOAJ(String cRCOAJ) {
			CRCOAJ = cRCOAJ;
		}
		public String getCRCTAI() {
			return CRCTAI;
		}
		public void setCRCTAI(String cRCTAI) {
			CRCTAI = cRCTAI;
		}
		public String getCRCINA() {
			return CRCINA;
		}
		public void setCRCINA(String cRCINA) {
			CRCINA = cRCINA;
		}
		
		
		
	}
	
}
