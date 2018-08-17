package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ciessa.museum.model.legacy.Ccrpcre;
import com.ciessa.museum.dao.legacy.CcrpcreDAO;
import com.ciessa.museum.model.legacy.Ccrppro;
import com.ciessa.museum.dao.legacy.CcrpproDAO;
import com.ciessa.museum.model.legacy.Ccrpind;
import com.ciessa.museum.dao.legacy.CcrpindDAO;
import com.ciessa.museum.model.legacy.Ccrpscb;
import com.ciessa.museum.dao.legacy.CcrpscbDAO;
import com.ciessa.museum.model.legacy.Ccrpsch;
import com.ciessa.museum.dao.legacy.CcrpschDAO;
import com.ciessa.museum.model.legacy.Ccrpcap;
import com.ciessa.museum.dao.legacy.CcrpcapDAO;
import com.ciessa.museum.model.legacy.Ccrpmov;
import com.ciessa.museum.dao.legacy.CcrpmovDAO;
import com.ciessa.museum.model.legacy.Ccrpcar;
import com.ciessa.museum.dao.legacy.CcrpcarDAO;


public class CCRR0580View01BzService extends RestBaseServerResource {

	public static final Logger log = Logger.getLogger(CCRR0580View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CcrpcreDAO myDaoCcrpcre;
	
	@Autowired
	CcrpproDAO myDaoCcrppro;
	
	@Autowired
	CcrpindDAO myDaoCcrpind;
	
	@Autowired
	CcrpscbDAO myDaoCcrpscb;
	
	@Autowired
	CcrpschDAO myDaoCcrpsch;
	
	@Autowired
	CcrpcapDAO myDaoCcrpcap;
	
	@Autowired
	CcrpmovDAO myDaoCcrpmov;
	
	@Autowired
	CcrpcarDAO myDaoCcrpcar;
	
	//Objetos
	Ccrpcre ObjCcrpcre = new Ccrpcre();
	Ccrppro ObjCcrppro = new Ccrppro();
	Ccrpind ObjCcrpind = new Ccrpind();
	Ccrpscb ObjCcrpscb = new Ccrpscb();
	Ccrpsch ObjCcrpsch = new Ccrpsch();
	Ccrpcap ObjCcrpcap = new Ccrpcap();
	Ccrpmov ObjCcrpmov = new Ccrpmov();
	Ccrpcar ObjCcrpcar = new Ccrpcar();
	
	//Variables_globales
	int idia   = 0;
	int scncuo = 0;
	
	String numcre = null;
	String tplan  = null;
	String Scstcu = null;
	String Sci8in = null;
	String Sci9in = null;
	String archiv = null;
	
	String NUMCRE = null;
	String CRCSUC = null;
	String CRCDIV = null;
	String ORCAPI = null;
	String CLAVE  = null;
	String NOMPRO = null;
	String NOMCLI = null;
	String MONEDA = null;
	String CRTACR = null;
	String INVAIN = null;
	String INCDIN = null;
	String SELECC = null;
	
	List<Ccrpcap> ListCcrpcap  = null;
	List<Ccrpsch> ListCcrpsch  = null;
	List<CCRR0580Adapter> list = new ArrayList<CCRR0580Adapter>();
	CCRR0580Adapter adapted = null;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		Date d = new Date(); Calendar calendar = Calendar.getInstance(); calendar.setTime(d);
		
		try
		{
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			//Parametros_Get
			numcre = obtainStringValue("numcre", null);
			tplan  = obtainStringValue("tplan", null);		
			
			ObjCcrpcre = myDaoCcrpcre.getUsingNumcre(ds, numcre);
			if (ObjCcrpcre.equals(null)) {
				log.log(Level.SEVERE, "Hubo Error", new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException("Hubo Error", new Exception())).toString();
			}
			ObjCcrppro = myDaoCcrppro.getUsingCrcapiAndCrlineAndCrtrtaAndCrtrinAndCrcoajAndCrctaiAndCrcina(ds, ObjCcrpcre.getCrcapi(), ObjCcrpcre.getCrline().toString(), ObjCcrpcre.getCrtrta().toString(), ObjCcrpcre.getCrtrin().toString(), ObjCcrpcre.getCrcoaj().toString(), ObjCcrpcre.getCrctai().toString(), ObjCcrpcre.getCrcina().toString());   
			
			if(ObjCcrppro!= null) {
				int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
				idia = weekOfMonth - ObjCcrppro.getOrqdan();	
			}
			
			
			if(ObjCcrpcre.getCrcina()>0) {
				int anno = calendar.get(Calendar.YEAR);
				int mes = calendar.get(Calendar.MONTH);

				ObjCcrpind = myDaoCcrpind.getUsingCrcinaAndAñoAndMesAndDia(ds, ObjCcrpcre.getCrcina(), anno, mes,  idia);
			}

			if (this.tplan.equals("1") && ObjCcrpcre.getCrccup()>0)	{
				scncuo = ObjCcrpcre.getCrccup(); 
			}
			else{ 
				ObjCcrpcre.setCrccup(1);
			}
			if(!ObjCcrpcre.getCrmawk().equals("0")) {
				if(this.tplan.equals("0")) {
					ObjCcrpscb = myDaoCcrpscb.getUsingCrnucr(ds, ObjCcrpcre.getCrnucr().toString());
				}else {
					ObjCcrpscb = myDaoCcrpscb.getUsingCrnucrAndScncuo(ds, ObjCcrpcre.getCrnucr().toString(), scncuo );
				}
			}
			if(this.tplan.equals("1")) {
				if( ObjCcrpcre.getCrccup() > 0) {
					scncuo =  ObjCcrpcre.getCrccup();
				}
				else {
					scncuo = 1;
				}
			}
			if(!ObjCcrpcre.getCrmawk().equals("0")) {
				if(this.tplan.equals("0")) {
					ObjCcrpsch = myDaoCcrpsch.getUsingCrnucr(ds, ObjCcrpcre.getCrnucr().toString());
				}else {
					ObjCcrpsch = myDaoCcrpsch.getUsingCrnucrAndScncuo(ds, ObjCcrpcre.getCrnucr().toString(), scncuo );
				}
				archiv = "H";
				String rpta = SubRutPPAR01(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
					if (rpta.equals("")) {
						log.log(Level.SEVERE, rpta, new Exception());
						return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
					}	
				rpta = SubRutEXTR01(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
					if (rpta.equals("")) {
						log.log(Level.SEVERE, rpta, new Exception());
						return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
					}
			}
			if(!ObjCcrpcre.getCrmawk().equals("0") && scncuo < ObjCcrpcre.getCrcvwk()) {
				if(this.tplan.equals("0")) {
					ObjCcrpsch = myDaoCcrpsch.getUsingCrnucr(ds, ObjCcrpcre.getCrnucr().toString());
				}else {
					ObjCcrpsch = myDaoCcrpsch.getUsingCrnucrAndScncuo(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
				}
				String rpta = SubRutPPAR01(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
					if (rpta.equals("")) {
						log.log(Level.SEVERE, rpta, new Exception());
						return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
					}			
				rpta = SubRutEXTR01(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
					if (rpta.equals("")) {
						log.log(Level.SEVERE, rpta, new Exception());
						return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
					}
				if ( ObjCcrpsch == null)
				{	    scncuo=999;	  }
				archiv = "H";
			}
			if(!ObjCcrpcre.getCrmawk().equals("0") && scncuo > ObjCcrpcre.getCrcvwk()) {
				if(this.tplan.equals("0")) {
					ObjCcrpscb = myDaoCcrpscb.getUsingCrnucr(ds, ObjCcrpcre.getCrnucr().toString());
				}else {
					ObjCcrpscb = myDaoCcrpscb.getUsingCrnucrAndScncuo(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
				}
			}
			adapted = new CCRR0580Adapter();
			adapted.ARCHIV = "8";
			Scstcu = ObjCcrpscb.getSbstcu();
			Sci8in = ObjCcrpscb.getSbi8in() == null ? "" : ObjCcrpscb.getSbi8in().toString();
			Sci9in = ObjCcrpscb.getSbi9in() == null ? "": ObjCcrpscb.getSbi9in().toString();
			
			do {
				Ccrpsch o = (Ccrpsch) ListCcrpsch;
				adapted.AMORT = o.getScimba().toString() + o.getScimam();
				adapted.SCICIN = adapted.getSCICIN() + Sci9in + Sci8in;
				adapted.INTERE = adapted.getSCICIN();
				adapted.AJUSTE = o.getScicaj().toString();
				adapted.TASA = o.getSctacr().toString();
				if(ObjCcrpcre.getCrmawk().equals("0")) {
					ObjCcrpsch = myDaoCcrpsch.getUsingCrnucr(ds, ObjCcrpcre.getCrnucr().toString());
					adapted.ARCHIV = "H";
					SubRutPPAR01(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
					SubRutEXTR01(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
				}
				if(!ObjCcrpcre.getCrmawk().equals("0") && scncuo < ObjCcrpcre.getCrcvwk()) {
					ObjCcrpsch = myDaoCcrpsch.getUsingCrnucr(ds, ObjCcrpcre.getCrnucr().toString());
					adapted.ARCHIV = "H";
					SubRutPPAR01(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
					SubRutEXTR01(ds, ObjCcrpcre.getCrnucr().toString(), scncuo);
				}					
				if(!ObjCcrpcre.getCrmawk().equals("0") && scncuo >= ObjCcrpcre.getCrcvwk()) {
					ObjCcrpscb = myDaoCcrpscb.getUsingCrnucr(ds, ObjCcrpcre.getCrnucr().toString());
					adapted.ARCHIV = "B";
					Scstcu = ObjCcrpscb.getSbstcu();
					Sci8in = ObjCcrpscb.getSbi8in().toString();
					Sci9in = ObjCcrpscb.getSbi9in().toString();
				}
				list.add(adapted);
			} while (ObjCcrpsch != null);
			
			long diff = new Date().getTime() - millisPre;
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
					"FECVTO",
					"AMORT",
					"INTERE",
					"AJUSTE",
					"TASA",
					"PPAR",
					"EXTOR",
					"FECPAG",
					"SCNCUO",
					"SCSTCU",
					"ARCHIV"
			};
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
		} //fin try
		
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

	} //Fn @Get
	
	private String SubRutPPAR01 (DataSet ds, String crnucr, int scncuo) {	
		try {
			String ppar = "";
			ListCcrpcap = myDaoCcrpcap.getUsingCrnucrAndScncuoToList(ds, crnucr, scncuo );
			for( Ccrpcap o :ListCcrpcap ) {
				ObjCcrpmov = myDaoCcrpmov.getUsingCanmovAndCrnucr(ds, o.getCanmov() , crnucr);
				if(ObjCcrpmov != null && ObjCcrpmov.getMvcmmo().equals("P") && !ObjCcrpmov.getMvstat().equals("X") ) {
					ppar= "X";
					break;
				}
			}
		} 
		catch (Exception e) {
			return e.getMessage();
		}
		return "";
	} //Fin SubRutPPAR01
	
	private String SubRutEXTR01 (DataSet ds, String crnucr, int scncuo){
		try {
			String extor = "";
			ObjCcrpcar = myDaoCcrpcar.getUsingCrnucrAndScncuo(ds, crnucr, scncuo);
			if (ObjCcrpcar != null) {
				extor = "X";
			}else {
				extor = "";
			}
		} 
		catch (Exception e) {
			return e.getMessage();
		}
		return "";
	}//Fin SubRutEXTR01
	
	
	public class CCRR0580Adapter {
		String FECVTO = null;
		String AMORT  = null;
		String INTERE = null;
		String AJUSTE = null;
		String TASA   = null;
		String PPAR   = null;
		String EXTOR  = null;
		String FECPAG = null;
		String SCNCUO = null;
		String SCSTCU = null;
		String ARCHIV = null;
		String SCICIN = null;
		
		public CCRR0580Adapter() {
			
		}

		public String getSCICIN() {
			return SCICIN;
		}

		public void setSCICIN(String sCICIN) {
			SCICIN = sCICIN;
		}

		public String getNUMCRE() {
			return NUMCRE;
		}

		public void setNUMCRE(String nUMCRE) {
			NUMCRE = nUMCRE;
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

		public String getORCAPI() {
			return ORCAPI;
		}

		public void setORCAPI(String oRCAPI) {
			ORCAPI = oRCAPI;
		}

		public String getCLAVE() {
			return CLAVE;
		}

		public void setCLAVE(String cLAVE) {
			CLAVE = cLAVE;
		}

		public String getNOMPRO() {
			return NOMPRO;
		}

		public void setNOMPRO(String nOMPRO) {
			NOMPRO = nOMPRO;
		}

		public String getNOMCLI() {
			return NOMCLI;
		}

		public void setNOMCLI(String nOMCLI) {
			NOMCLI = nOMCLI;
		}

		public String getMONEDA() {
			return MONEDA;
		}

		public void setMONEDA(String mONEDA) {
			MONEDA = mONEDA;
		}

		public String getCRTACR() {
			return CRTACR;
		}

		public void setCRTACR(String cRTACR) {
			CRTACR = cRTACR;
		}

		public String getINVAIN() {
			return INVAIN;
		}

		public void setINVAIN(String iNVAIN) {
			INVAIN = iNVAIN;
		}

		public String getINCDIN() {
			return INCDIN;
		}

		public void setINCDIN(String iNCDIN) {
			INCDIN = iNCDIN;
		}

		public String getSELECC() {
			return SELECC;
		}

		public void setSELECC(String sELECC) {
			SELECC = sELECC;
		}

		public String getFECVTO() {
			return FECVTO;
		}

		public void setFECVTO(String fECVTO) {
			FECVTO = fECVTO;
		}

		public String getAMORT() {
			return AMORT;
		}

		public void setAMORT(String aMORT) {
			AMORT = aMORT;
		}

		public String getINTERE() {
			return INTERE;
		}

		public void setINTERE(String iNTERE) {
			INTERE = iNTERE;
		}

		public String getAJUSTE() {
			return AJUSTE;
		}

		public void setAJUSTE(String aJUSTE) {
			AJUSTE = aJUSTE;
		}

		public String getTASA() {
			return TASA;
		}

		public void setTASA(String tASA) {
			TASA = tASA;
		}

		public String getPPAR() {
			return PPAR;
		}

		public void setPPAR(String pPAR) {
			PPAR = pPAR;
		}

		public String getEXTOR() {
			return EXTOR;
		}

		public void setEXTOR(String eXTOR) {
			EXTOR = eXTOR;
		}

		public String getFECPAG() {
			return FECPAG;
		}

		public void setFECPAG(String fECPAG) {
			FECPAG = fECPAG;
		}

		public String getSCNCUO() {
			return SCNCUO;
		}

		public void setSCNCUO(String sCNCUO) {
			SCNCUO = sCNCUO;
		}

		public String getSCSTCU() {
			return SCSTCU;
		}

		public void setSCSTCU(String sCSTCU) {
			SCSTCU = sCSTCU;
		}

		public String getARCHIV() {
			return ARCHIV;
		}

		public void setARCHIV(String aRCHIV) {
			ARCHIV = aRCHIV;
		}
		
	} // Fin CCRR0580Adapter
	
} // Fin public class CCRR0580View01BzService