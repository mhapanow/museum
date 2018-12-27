package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.Cfp001005DAO;
import com.ciessa.museum.dao.legacy.Cfp001207DAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cfp001005;
import com.ciessa.museum.model.legacy.Cfp001207;

public class FER0320View01BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(FER0320View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	Cfp001005DAO myDAOCfp001005;
	
	@Autowired
	Cfp001207DAO myDAOCfp001207;
	
	
	Cfp001005 objCfp001005 = new Cfp001005();
	Cfp001207 objCfp001207 = new Cfp001207();
	
	
	String wcta = "";
	String amar = "";
	
	String arec = "";
	String fecha = "";
	String udate = "";
	Integer wpsav = 0;
	Integer wpdda = 0;
	Integer acodr = 0;
	String clavex = "";
	String f8 = "";
	String fecdia = "";
	String codeco = "1";
	
	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
	FER0320V1Adapter adapter = null;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			this.wcta = obtainStringValue("wcta", null);
			this.amar = obtainStringValue("amar", null);
			
			this.arec = "1";
			this.fecha = this.udate;
			SubRutUnica(ds);
			if (this.wcta.equals("")) {
				//Mostrar Pantalla1 
			}
			this.amar = "0";

			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {

			};
			returnValue = getJSONRepresentationFromObject(adapter, fields);;
			
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
	
	private String SubRutUnica(DataSet ds) {
		try {
			objCfp001005 = myDAOCfp001005.getUsingKey(ds);
			if (objCfp001005 != null) {
				this.wpsav = objCfp001005.getCfpsav();
				this.wpdda = objCfp001005.getCfpdda();
			}
			objCfp001207 = myDAOCfp001207.getUsingKey(ds);
			this.acodr = 1;
			this.clavex = this.codeco;
			this.f8 = this.fc.FormatoFechaHora("ddMMyyyy");
			this.fecdia = this.f8;

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	
	
	
	public class FER0320V1Adapter {
		String WNCTA = "";
		String CODECO = "";
		String DMBRCH = "";
		String DMCMCN = "";
		String WNOMB1 = "";
		String WNOMB2 = "";
		String WNOMB3 = "";
		String DMIODL = "";
		String WSDISP = "";
		String ATIT = "";
		
		public FER0320V1Adapter() {
			
		}
		
	}
	
	
	
	
	

}
