package com.ciessa.museum.bz.legacy;

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
import com.ciessa.museum.dao.legacy.CcrpcreDAO;
import com.ciessa.museum.dao.legacy.GrmcdaDAO;
import com.ciessa.museum.dao.legacy.GrmidaDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Ccrpcre;
import com.ciessa.museum.model.legacy.Grmcda;
import com.ciessa.museum.model.legacy.Grmida;

public class CCRR0500View03BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(CCRR0500View03BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CcrpcreDAO myDaoCcrpcre;
	
	@Autowired
	GrmidaDAO myDaoGrmida;
	
	@Autowired
	GrmcdaDAO myDaoGrmcda;
	
	//Entity
	Ccrpcre ObjCcrpcre = new Ccrpcre();
	Grmida ObjGrmida = new Grmida();
	Grmcda ObjGrmcda = new Grmcda();
	
	//Variables_globales INPUT
	private String crntar = null;
	private String crcsuc = null;
	private String crcdiv = null;
	private String crstco = null;
	private String cuna1 = null;
	
	List<Ccrpcre> listCcrpcre  = null;
	
	CCRR0500Adapter adapted = new CCRR0500Adapter();
	List<CCRR0500Adapter> list = new ArrayList<CCRR0500Adapter>();
	
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;		
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();

			//Parametros_Get
			crntar = obtainStringValue("crntar", null);
			crcsuc = obtainStringValue("crcsuc", null);
			crcdiv = obtainStringValue("crcdiv", null);
			crstco = obtainStringValue("crstco", null);
			
			String rpta = "";
			
			if (this.crntar.equals("")) {
				rpta = "ERROR FALTAN PARAMETROS";
			}
			else {
				if (Integer.parseInt(this.crcsuc) > 0 |	Integer.parseInt(this.crcdiv) > 0 |	Integer.parseInt(this.crstco) > 0) {
					rpta = "ERROR FALTAN PARAMETROS";
				}
				else {
					rpta = SubRutRtn200(ds);		
				}
			}
				
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			
			// retrieve all elements
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found [" + list.size() + "] in " + diff + " millis");
			
			String[] fields = new String[] {
					"NOMCLI",
					"CRNUCR",
					"CRSTCR",
					"FECALT",
					"FECVTO",
					"SALDO",
			};
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
			returnValue.put("CRNTAR", this.crntar);
			
			
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
	
	private String SubRutRtn200(DataSet ds) {
		try {
			listCcrpcre = myDaoCcrpcre.getUsingCrntar(ds, this.crntar);
			for (Ccrpcre o : listCcrpcre) {
				adapted.setSALDO(o.getCrisde().toString());
				ObjGrmida = myDaoGrmida.getUsingRirmcn(ds, o.getCrnucl().toString());
				if (ObjGrmida != null) {
					this.cuna1 = ObjGrmida.getRifsnm() + " " + ObjGrmida.getRilsnm();
				} else {
					ObjGrmcda = myDaoGrmcda.getUsingRyrmcn(ds, o.getCrnucl().toString());
					if (ObjGrmcda != null) {
						this.cuna1 = ObjGrmcda.getRycpcn();
					}
				}
				if (this.cuna1 =="") {
					this.cuna1 = "??????????";
				}
				adapted.setCRSTCR(o.getCrstcr().toString());
				adapted.setCRNUCR(o.getCrnucr().toString());
				adapted.setNOMCLI(this.cuna1);
				adapted.setFECALT(String.format("%02d", o.getCrddoc()) + "/"+ String.format("%02d", o.getCrmmoc()) + "/" + o.getCraaoc().toString());
				adapted.setFECVTO(String.format("%02d", o.getCrddvc()) + "/"+ String.format("%02d", o.getCrmmvc()) + "/" + o.getCraavc().toString());
				list.add(adapted);
			}
			
		} catch (ASException e) {
			return e.getMessage();
			
		}
		return "";
	}
	

	public class CCRR0500Adapter {
		private String NOMCLI;
		private String CRNUCR;
		private String CRSTCR; //TODO: variable no retornable
		private String FECALT;
		private String FECVTO;
		private String SALDO;
		
		public CCRR0500Adapter() {			
		}

		public String getNOMCLI() {
			return NOMCLI;
		}

		public void setNOMCLI(String nOMCLI) {
			NOMCLI = nOMCLI;
		}

		public String getCRNUCR() {
			return CRNUCR;
		}

		public void setCRNUCR(String cRNUCR) {
			CRNUCR = cRNUCR;
		}

		public String getCRSTCR() {
			return CRSTCR;
		}

		public void setCRSTCR(String cRSTCR) {
			CRSTCR = cRSTCR;
		}

		public String getFECALT() {
			return FECALT;
		}

		public void setFECALT(String fECALT) {
			FECALT = fECALT;
		}

		public String getFECVTO() {
			return FECVTO;
		}

		public void setFECVTO(String fECVTO) {
			FECVTO = fECVTO;
		}

		public String getSALDO() {
			return SALDO;
		}

		public void setSALDO(String sALDO) {
			SALDO = sALDO;
		}
		
		
	}
}
