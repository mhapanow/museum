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
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cfp001005;

public class FER0330View01BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(FER0330View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	Cfp001005DAO myDAOCfp001005;
	
	
	Cfp001005 objCfp001005 = new Cfp001005();
	
	
	String wcta = "";
	String amar = "";
	String wdevpr = "";
	
	String adevpr = "";
	String fecha = "";
	Integer wfech = 0;
	Integer wfec2 = 0;
	String qname = "";
	String qlib = "";
	Integer qlen = 0;
	String abanco = "";
	String codeco = "";
	String march = "";
	Integer wbatch = 0;
	Integer wfec1 = 0;
	Integer wpsav = 0;
	Integer wpdda = 0;
	String banco = "";
	Integer nroreg = 0;
	String cfalfa = "";
	String wncta = "";
	
	
	
	
	FER0330V01Adapter adapter = null;
	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
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
			this.wdevpr = obtainStringValue("wdevpr", null);
			
			this.adevpr = this.wdevpr;
			this.fecha = fc.FormatoFechaHora("ddMMyyyy");
			this.wfech = 0;
			this.wfec2 = 0;
			this.qname = "FEU0003";
			this.qlib = "*LIBL";
			this.qlen = 29;
			this.abanco = this.codeco;
			this.march = "";
			this.wbatch = 0;
			this.wfec1 = 0;
			
			
			SubRutUnica(ds);
			if (this.wcta.equals("")) {
				//atit = tit[1]; 
			}
			this.amar = "0";

				
			
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {

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
	
	private String SubRutUnica(DataSet ds) {
		try {
			this.banco = this.codeco;
			this.nroreg = 5;
			this.cfalfa = "";
			
			objCfp001005 = myDAOCfp001005.getUsingKey(ds);
			if (objCfp001005 != null) {
				this.wpsav = objCfp001005.getCfpsav();
				this.wpdda = objCfp001005.getCfpdda();
			}
			if (this.wcta.equals("")) {
				this.wncta = this.wcta;
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	
	public class FER0330V01Adapter{
		
	}
	

}
