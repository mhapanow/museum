package com.ciessa.museum.bz.legacy;

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
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;

public class ZRSTDSPSView07BzService extends RestBaseServerResource {
	
	public static final Logger log = Logger.getLogger(ZRSTDSPSView07BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;

	//
	Integer custompantalla = null;
	String w1desc = null;
	String w1fmov = null;
	String w2desc = null;
	String w2fmov = null;
	
	//
	String wfecnum8 = null;
	
	String w1amnt = null;
	String w1tefm = null;
	String w1tnoa = null;
	String w1iorg = null;
	
	String w2amnt = null;
	String w2tefm = null;
	String w2tnoa = null;
	String w2iorg = null;
	//
	String wsdescd = null;
	String wfecchar8 = null;
	String wsfmovd = null;
	String wsamntd = null;
	String wstefmd = null;
	String wstnoad = null;
	String wsiorgd = null;
	
	//
	List<ZRSTDSPSAdapter> list = new ArrayList<ZRSTDSPSAdapter>();
	ZRSTDSPSAdapter adapted = null;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			//Parametros Get
			custompantalla = obtainIntegerValue("custompantalla", 0); //view1 o view2
			this.w1desc = obtainStringValue("w1desc", null);
			this.w1fmov = obtainStringValue("w1fmov", null);
			this.w1amnt = obtainStringValue("w1amnt", null);
			this.w1tefm = obtainStringValue("w1tefm", null);
			this.w1tnoa = obtainStringValue("w1tnoa", null);
			this.w1iorg = obtainStringValue("w1iorg", null);			
			
			this.w2desc = obtainStringValue("w2desc", null);
			this.w2fmov = obtainStringValue("w2fmov", null);
			this.w2amnt = obtainStringValue("w2amnt", null);
			this.w2tefm = obtainStringValue("w2tefm", null);
			this.w2tnoa = obtainStringValue("w2tnoa", null);
			this.w2iorg = obtainStringValue("w2iorg", null);
			
			/*wfecnum8 = obtainStringValue("wfecnum8", null);
			w1amnt = obtainStringValue("w1amnt", null);
			w1tefm = obtainStringValue("w1tefm", null);
			w1tnoa = obtainStringValue("w1tnoa", null);
			w1iorg = obtainStringValue("w1iorg", null);*/
			
			String rpta = SubRutDspnov(ds);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
					"WSDESCD",
					"WSFMOVD",
					"WSAMNTD",
					"WSTEFMD",
					"WSTNOAD",
					"WSIORGD",
			};

			// Obtains the user JSON representation
			ZRSTDSPSAdapter adapted = new ZRSTDSPSAdapter();
			adapted.setWSDESCD(this.wsdescd);
			adapted.setWSFMOVD(this.wsfmovd);
			adapted.setWSAMNTD(this.wsamntd);
			adapted.setWSTEFMD(this.wstefmd);
			adapted.setWSTNOAD(this.wstnoad);
			adapted.setWSIORGD(this.wsiorgd);
			returnValue = getJSONRepresentationFromObject(adapted, fields);
			
			
		}
		catch (ASException e) {
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
	
	private String SubRutDspnov (DataSet ds) {
		try {
			if (this.custompantalla == 1) {
				this.wsdescd = this.w1desc;
				if ( validarFecha(this.w1fmov) ) {
					this.wfecchar8 = new SimpleDateFormat("ddMMyyyy").parse(this.w1fmov.toString()).toString();
					this.wsfmovd = this.wfecnum8;
				}else {
					this.wsfmovd = "";
					//--return "Fotmato Fecha Inválida";
				}
				this.wsamntd = this.w1amnt;
				this.wstefmd = this.w1tefm;
				this.wstnoad = this.w1tnoa;
				this.wsiorgd = this.w1iorg;
			}
			
			if (this.custompantalla == 2) {
				this.wsdescd = this.w2desc;
				if ( validarFecha(this.w2fmov) ) {
					this.wfecchar8 = new SimpleDateFormat("ddMMyyyy").parse(this.w2fmov.toString()).toString();
					this.wsfmovd = this.wfecnum8;
				}else {
					this.wsfmovd = "";
					//return "Fotmato Fecha Inválida";
				}
				this.wsamntd = this.w2amnt;
				this.wstefmd = this.w2tefm;
				this.wstnoad = this.w2tnoa;
				this.wsiorgd = this.w2iorg;
			}
			return "";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	//
	public class ZRSTDSPSAdapter {
		String WSDESCD = null;
		String WSFMOVD = null;
		String WSAMNTD = null;
		String WSTEFMD = null;
		String WSTNOAD = null;
		String WSIORGD = null;
		
		public ZRSTDSPSAdapter() {
			
		}

		public String getWSDESCD() {
			return WSDESCD;
		}

		public void setWSDESCD(String wSDESCD) {
			WSDESCD = wSDESCD;
		}

		public String getWSFMOVD() {
			return WSFMOVD;
		}

		public void setWSFMOVD(String wSFMOVD) {
			WSFMOVD = wSFMOVD;
		}

		public String getWSAMNTD() {
			return WSAMNTD;
		}

		public void setWSAMNTD(String wSAMNTD) {
			WSAMNTD = wSAMNTD;
		}

		public String getWSTEFMD() {
			return WSTEFMD;
		}

		public void setWSTEFMD(String wSTEFMD) {
			WSTEFMD = wSTEFMD;
		}

		public String getWSTNOAD() {
			return WSTNOAD;
		}

		public void setWSTNOAD(String wSTNOAD) {
			WSTNOAD = wSTNOAD;
		}

		public String getWSIORGD() {
			return WSIORGD;
		}

		public void setWSIORGD(String wSIORGD) {
			WSIORGD = wSIORGD;
		}
		
		
		
	}
	
	
	
	//Validar Fecha
	public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
