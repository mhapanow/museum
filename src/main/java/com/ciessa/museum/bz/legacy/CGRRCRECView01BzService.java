package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;

public class CGRRCRECView01BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(CGRRCRECView01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	
	
	
	String aamcue = "";
	Integer impgan = 0;
	Integer impg02 = 0;
	Integer impu03 = 0;
	Integer banco = 0;
	String work30 = "";
	String tipreg = "";
	Integer tipoc = 0;
	Integer cux1ac = 0;
	String pgmq = "";
	Integer doc102 = 0;
	Integer doc202 = 0;
	String doc302 = "";
	String tipo = "";
	String shortn = "";
	String ind13 = "";
	String numcta = "";
	
	
	CGRRCRECV1Adapter adapter = null;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			this.aamcue = obtainStringValue("aamcue", null);
			
			this.impgan = 0;
			this.impg02 = 0;
			this.impu03 = 0;
			this.banco = 1;
			this.work30 = " ";
			this.tipreg = "1";
			this.tipoc = 20;
			this.cux1ac = 0;
			this.pgmq = "CGRRCREC";
			SubRutAcbaco(ds);


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
	
	private String SubRutAcbaco(DataSet ds) {
		try {
			this.doc102 = 0;
			this.doc202 = 0;
			this.doc302 = "";
			this.impg02 = 0;
			this.tipo = "";
			this.shortn = "";
			this.ind13 = "0";
			if (true) {//error
				if (true) {//0
					//mostrar pantalla01
				}else {
					this.numcta = this.aamcue;
				}
			}
			
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	


	
	
	public class CGRRCRECV1Adapter {
		
		


		
	}
	
	
	

}
