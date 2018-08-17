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
import com.ciessa.museum.dao.legacy.Cfp001002DAO;
import com.ciessa.museum.dao.legacy.DtgpdesDAO;
import com.ciessa.museum.dao.legacy.GrmcdaDAO;
import com.ciessa.museum.dao.legacy.GrmidaDAO;
import com.ciessa.museum.dao.legacy.TablamDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Ccrpcre;
import com.ciessa.museum.model.legacy.Cfp001002;
import com.ciessa.museum.model.legacy.Dtgpdes;
import com.ciessa.museum.model.legacy.Grmcda;
import com.ciessa.museum.model.legacy.Grmida;
import com.ciessa.museum.model.legacy.Tablam;

public class CCRR0500View02BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(CCRR0500View02BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	Cfp001002DAO myDaoCfp001002;
	
	@Autowired
	TablamDAO myDaoTablam;
	
	@Autowired
	DtgpdesDAO myDaoDtgpdes;
	
	@Autowired
	CcrpcreDAO myDaoCcrpcre;
	
	@Autowired
	GrmidaDAO myDaoGrmida;
	
	@Autowired
	GrmcdaDAO myDaoGrmcda;
	
	//Entity
	Tablam ObjTablam = new Tablam();
	Dtgpdes ObjDtgpdes = new Dtgpdes();
	Cfp001002 ObjCfp001002 = new Cfp001002();
	Ccrpcre ObjCcrpcre = new Ccrpcre();
	Grmida ObjGrmida = new Grmida();
	Grmcda ObjGrmcda = new Grmcda();
	
	//Variables_globales INPUT
	private String crntar = null;
	private String crcsuc = null;
	private String crcdiv = null;
	private String crstco = null;
	
	//variables _globales 
	private String ctstco = null;
	
	private String dsstco = null;
	private String cuna1 = null;
		
	List<Ccrpcre> listCcrpcre  = null;
	
	CCRR0500Adapter adapted = new CCRR0500Adapter();
	List<CCRR0500Adapter> list = new ArrayList<CCRR0500Adapter>();
	
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
			crntar = obtainStringValue("crntar", null);
			crcsuc = obtainStringValue("crcsuc", null);
			crcdiv = obtainStringValue("crcdiv", null);
			crstco = obtainStringValue("crstco", null);
			
			if (this.crntar.equals("")) {
				rpta = SubRutRtn100(ds);
			} else {
				if (Integer.parseInt(this.crcsuc) > 0 |	Integer.parseInt(this.crcdiv) > 0 |	Integer.parseInt(this.crstco) > 0) {
					rpta = "ERROR FALTAN PARAMETROS";
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
			returnValue.put("CRCSUC", this.crcsuc);
			returnValue.put("CRCDIV", this.crcdiv);
			returnValue.put("CRSTCO", this.crstco);
			returnValue.put("DSSTCO", this.dsstco);
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
	
	private String SubRutRtn100(DataSet ds) {
		try {
			ObjCfp001002 = myDaoCfp001002.getUsingCfnsuc(ds, this.crcsuc);
			if (ObjCfp001002 == null) {
				return "Error. Ejecutar Rutina RTN100";
			}
			ObjTablam = myDaoTablam.getUsingCrcdiv(ds, this.crcdiv);
			if (ObjTablam == null) {
				return "Error. Ejecutar Rutina RTN100";
			}
			if (Integer.parseInt(this.crstco) < 2 | Integer.parseInt(this.crstco) > 4) {
				return "Error. Ejecutar Rutina RTN100 - Opciones fuera de Rango";
			}
			ObjDtgpdes = myDaoDtgpdes.getUsingCtstco(ds, this.ctstco);//TODO: no encuentro esta variable
			if (ObjDtgpdes != null) {
				this.dsstco = ObjDtgpdes.getDsds01().toString();
			}
			String crbanc = null ; //TODO No encuentro esta variable
			listCcrpcre = myDaoCcrpcre.getUsingCrbancCrcsucCrcdivAndCrstco(ds, crbanc, crcsuc, crcdiv, crstco);			
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
				adapted.setNOMCLI(this.cuna1);
				adapted.setFECALT(o.getCrddoc().toString() + o.getCrmmoc().toString() + o.getCraaoc().toString());
				adapted.setFECVTO(o.getCrddvc().toString() + o.getCrmmvc().toString() + o.getCraavc().toString());
				list.add(adapted);
			}
			
			
		} catch (ASException e) {
			return e.getMessage();
			
		}
		
		return "";
	}
	
	public class CCRR0500Adapter {
		private String CRCSUC;
		private String CRCDIV;
		private String CRSTCO;
		private String NOMCLI;
		private String CRNUCR;
		private String CRSTCR; //TODO: variable no retornable
		private String FECALT;
		private String FECVTO;
		private String SALDO;
		
		public CCRR0500Adapter() {			
		}

		public String getCRCSUC() {
			return CRCSUC;
		}

		public void setCRCSUC(String cRCSUC) {
			CRCSUC = cRCSUC;
		}

		public String getCRCDIV() {
			return CRCDIV;
		}

		public void setCRCDIV(String cRCDIV) {
			CRCDIV = cRCDIV;
		}

		public String getCRSTCO() {
			return CRSTCO;
		}

		public void setCRSTCO(String cRSTCO) {
			CRSTCO = cRSTCO;
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
