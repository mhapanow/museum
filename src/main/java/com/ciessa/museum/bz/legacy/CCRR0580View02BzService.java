package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
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
import com.ciessa.museum.model.legacy.Ccrpsch;
import com.ciessa.museum.dao.legacy.CcrpschDAO;
import com.ciessa.museum.model.legacy.Ccrpcar;
import com.ciessa.museum.dao.legacy.CcrpcarDAO;
import com.ciessa.museum.model.legacy.Ccrpcap;
import com.ciessa.museum.dao.legacy.CcrpcapDAO;
import com.ciessa.museum.model.legacy.Ccrpmov;
import com.ciessa.museum.dao.legacy.CcrpmovDAO;
import com.ciessa.museum.model.legacy.Ccrpscb;
import com.ciessa.museum.dao.legacy.CcrpscbDAO;

import com.ciessa.museum.model.legacy.Ccrpcci;
import com.ciessa.museum.dao.legacy.CcrpcciDAO;


public class CCRR0580View02BzService extends RestBaseServerResource {

	public static final Logger log = Logger.getLogger(CCRR0580View02BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CcrpschDAO myDaoCcrpsch;
	
	@Autowired
	CcrpcarDAO myDaoCcrpcar;
	
	@Autowired
	CcrpcapDAO myDaoCcrpcap;
	
	@Autowired
	CcrpmovDAO myDaoCcrpmov;
	
	@Autowired
	CcrpscbDAO myDaoCcrpscb;
	
	@Autowired
	CcrpcciDAO myDaoCcrpcci;

	//Entidades
	Ccrpsch ObjCcrpsch = new Ccrpsch();
	Ccrpcar ObjCcrpcar = new Ccrpcar();
	Ccrpcap ObjCcrpcap = new Ccrpcap();
	Ccrpmov ObjCcrpmov = new Ccrpmov();
	Ccrpscb ObjCcrpscb = new Ccrpscb();
	Ccrpcci ObjCcrpcci = new Ccrpcci();
	
		
	//Variables_Globales
	String archiv = null;
	String crnucr = null;
	String scncuo = null;
	
	
	String extor = "";
	String ppar = "";

	String SCACON = "";
	String AVIVTO = "";
	
	private BigDecimal SBIEN ;
	private BigDecimal SCICCA; 
	private BigDecimal SCICIN;
	private BigDecimal SCIICC; 
	private BigDecimal SCIICD;
	private BigDecimal SCIIPC; 
	private BigDecimal SCIIPD; 
	private BigDecimal SCISAU; 
	private BigDecimal SCISVI; 
	private BigDecimal TOPTUN;
	private BigDecimal WSIB;
	private BigDecimal WSIVA1;
	private BigDecimal WSIVA2;
	private BigDecimal WSIVA3;
	private BigDecimal WSIVA4;
	private BigDecimal WSIVA5;
	private BigDecimal WSIVA6;
	private BigDecimal WSIVA7;
	private BigDecimal WSICAC;
	private BigDecimal TOTAL;
	private Long FACCOR; 
	
	List<Ccrpcap> ListCcrpcap  = null;
	List<Ccrpsch> ListCcrpsch  = null;
	List<Ccrpcci> ListCcrpcci  = null;
	
	JSONObject returnJson;
	CCRR0580Adapter adapted = null;

	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
	
		
		try
		{
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			//Parametros_Get
			this.archiv = obtainStringValue("archiv", null);
			this.crnucr  = obtainStringValue("numcre", null);
			this.scncuo = obtainStringValue("numcuo", null);
			
			String rpta = SubRutRtn100(ds, archiv, crnucr, scncuo);
			
			if (rpta.equals("")) {
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}	
			
			// Get the output fields
			String[] fields = new String[] {
					"AVIVTO",
					"WCAMP",
					"WDEMP",
					"NUMCRE",
					"SCTICU",
					"SCSTCU",
					"SCNCUO",
					"WSIB",
					"MONEDA",
					"SCTACR",
					"SCIMAM",
					"SCIBAS",
					"SCIMBA",
					"SCIACT",
					"SCICIN",
					"FACCOR",
					"SCICAJ",
					"WSIVA1",
					"SCISVI",
					"WSIVA2",
					"SCIISE",
					"WSIVA3",
					"SCICCA",
					"WSIVA4",
					"TOTPUN",
					"WSIVA5",
					"SBIEN",
					"WSIVA6",
					"FECVTO",
					"WSIVA7",
					"FECPAG",
					"TOTAL",
					"SCCAR1",
					"SCFPCU",
					"SCIIPD",
					"SCCSUP",
					"SCIICD",
					"SCIIPC",
					"SCI8C1",
					"SCIICC",
					"FECREV",
					"WSICAC",
					"SCACON"

			};

			// Obtains the user JSON representation
			returnJson = getJSONRepresentationFromObject(adapted, fields);
				
			long diff = new Date().getTime() - millisPre;
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			return returnJson.toString();
			
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

	
	
	private String SubRutRtn100 (DataSet ds, String archiv, String crnucr, String scncuo) {
		try {
			if (archiv.equals("H")) {
				ObjCcrpsch = myDaoCcrpsch.getUsingCrnucrAndScncuo(ds, crnucr, Integer.parseInt(scncuo)).get(0);
				SubRutPPAR01(ds, crnucr, Integer.parseInt(scncuo));
				SubRutEXTR01(ds, crnucr, Integer.parseInt(scncuo));
			}else {
				ObjCcrpscb = myDaoCcrpscb.getUsingCrnucrAndScncuo(ds, crnucr, Integer.parseInt(scncuo));
				adapted.setSCIBAS(ObjCcrpscb.getSbibas().toString());
				adapted.setSCTICU(ObjCcrpscb.getSbticu().toString());
				adapted.setSCIACT(ObjCcrpscb.getSbiact().toString());
				adapted.setSCFPCU(ObjCcrpscb.getSbfpcu());
				adapted.setSCCSUP(ObjCcrpscb.getSbcsup().toString());
				adapted.setSCIICD(ObjCcrpscb.getSbiicd().toString());
				adapted.setSCIIPD(ObjCcrpscb.getSbiipd().toString());
				adapted.setSCIIPC(ObjCcrpscb.getSbiipc().toString());
				adapted.setSCIICC(ObjCcrpscb.getSbiicc().toString());
				adapted.setSCACON(ObjCcrpscb.getSbacon());
				adapted.setSCSTCU(ObjCcrpscb.getSbstcu());
				adapted.setSCISAU(ObjCcrpscb.getSbisau().toString());
				adapted.setSCI8IN(ObjCcrpscb.getSbi8in().toString());
				adapted.setSCI8CD(ObjCcrpscb.getSbi8cd().toString());
				adapted.setSCI8PD(ObjCcrpscb.getSbi8pd().toString());
				adapted.setSCI8PC(ObjCcrpscb.getSbi8pc().toString());
				adapted.setSCI8CC(ObjCcrpscb.getSbi8cc().toString());
				adapted.setSCI8CS(ObjCcrpscb.getSbi8cs().toString());
				adapted.setSCI8CA(ObjCcrpscb.getSbi8ca().toString());
				adapted.setSCI9IN(ObjCcrpscb.getSbi9in().toString());
				adapted.setSCI9CD(ObjCcrpscb.getSbi9cd().toString());
				adapted.setSCI9PD(ObjCcrpscb.getSbi9pd().toString());
				adapted.setSCI9PC(ObjCcrpscb.getSbi9pc().toString());
				adapted.setSCI9CC(ObjCcrpscb.getSbi9cc().toString());
				adapted.setSCI9CS(ObjCcrpscb.getSbi9cs().toString());
				adapted.setSCI9CA(ObjCcrpscb.getSbi9ca().toString());
				adapted.setSCCAR1(ObjCcrpscb.getSbcar1().toString());
				adapted.setSCI8C1(ObjCcrpscb.getSbi8c1().toString());
			}
			
			if(ObjCcrpsch.getScfpcu().equals("0")) { adapted.setDSFPCU("EFECTIVO");  		  }
			if(ObjCcrpsch.getScfpcu().equals("1")) { adapted.setDSFPCU("CHEQUE 24 HORAS");    }
			if(ObjCcrpsch.getScfpcu().equals("2")) { adapted.setDSFPCU("CHEQUE 48 HORAS");    }
			if(ObjCcrpsch.getScfpcu().equals("3")) { adapted.setDSFPCU("CHEQUE 72 HORAS");    }
			if(ObjCcrpsch.getScfpcu().equals("4")) { adapted.setDSFPCU("DEBITO AUTOMATICO");  }
			if(ObjCcrpsch.getScfpcu().equals("5")) { adapted.setDSFPCU("DEBITO DINERS");	  }
			if(ObjCcrpsch.getScfpcu().equals("6")) { adapted.setDSFPCU("BANELCO");   		  }
			if(ObjCcrpsch.getScfpcu().equals("7")) { adapted.setDSFPCU("RAPIPAGO");   		  }
			if(ObjCcrpsch.getScfpcu().equals("8")) { adapted.setDSFPCU("BANCO NACION");   	  }
			if(ObjCcrpsch.getScfpcu().equals("9")) { adapted.setDSFPCU("DEBITO CRUZ/REINT'"); }
			if(ObjCcrpsch.getScfpcu().equals("W")) { adapted.setDSFPCU("UNION'");   		  }
			
			if(adapted.getSCTICU().equals("A"))    {adapted.setDSTICU("CUOTA AJUSTE + INTERES");	}
			if(adapted.getSCTICU().equals("C"))    {adapted.setDSTICU("TCUOTA TOTAL AMORT+AJ+INT.");}
			if(adapted.getSCTICU().equals("C"))    {adapted.setDSTICU("PCANCELACION PARCIAL");		}
			if(adapted.getSCTICU().equals("C"))    {adapted.setDSTICU("CCANCELACION TOTAL");		}
			if(adapted.getSCTICU().equals("C"))    {adapted.setDSTICU("SCANC. PARC.- SALTEA VTO");	}
			if(adapted.getSCTICU().equals("P"))    {adapted.setDSTICU("CCUOTA ADIC PUNIT/COMP");	}
			
			this.SBIEN  = ObjCcrpsch.getScisau();
			this.SCICCA = this.SCICCA.add(ObjCcrpsch.getSci8ca().add(ObjCcrpsch.getSci9ca()));
			this.SCICIN = this.SCICIN.add(ObjCcrpsch.getSci9in().add(ObjCcrpsch.getSci8in()));
			this.SCIICC = this.SCIICC.add(ObjCcrpsch.getSci8cc().add(ObjCcrpsch.getSci9cc()));	
			this.SCIICD = this.SCIICD.add(ObjCcrpsch.getSci8cc().add(ObjCcrpsch.getSci9cd()));
			this.SCIIPC = this.SCIIPC.add(ObjCcrpsch.getSci8pc().add(ObjCcrpsch.getSci9pc()));
			this.SCIIPD = this.SCIIPD.add(ObjCcrpsch.getSci8pd().add(ObjCcrpsch.getSci9pd()));
			this.SCISAU = this.SCISAU.add(ObjCcrpsch.getSci8cs().add(ObjCcrpsch.getSci9cs()));
			this.SCISVI = this.SCISVI.add(ObjCcrpsch.getSci9sv().add(ObjCcrpsch.getSci8sv()));
			
			this.TOPTUN = ObjCcrpsch.getSciipd().add(ObjCcrpsch.getSciicd().add(ObjCcrpsch.getSciipc().add(ObjCcrpsch.getSciicc())));
			WSIB = new BigDecimal(0); 
			this.WSIVA1 = ObjCcrpsch.getSci9in().add(ObjCcrpsch.getSci8in());
			this.WSIVA2 = ObjCcrpsch.getSci9cs().add(ObjCcrpsch.getSci8cs());
			this.WSIVA3 = ObjCcrpsch.getSci9pd().add(ObjCcrpsch.getSci8pd());
			this.WSIVA4 = ObjCcrpsch.getSci9pc().add(ObjCcrpsch.getSci8pc());
			this.WSIVA5 = ObjCcrpsch.getSci9cd().add(ObjCcrpsch.getSci8cd());
			this.WSIVA6 = ObjCcrpsch.getSci9cc().add(ObjCcrpsch.getSci8cc());
			this.WSIVA7 = ObjCcrpsch.getSci9ca().add(ObjCcrpsch.getSci8ca());
			
			if (ObjCcrpsch.getSciipc().compareTo(BigDecimal.ZERO) > 0) {
				this.SCIIPC = new BigDecimal(0);
			}
			
			if (ObjCcrpsch.getSciicc().compareTo(BigDecimal.ZERO) > 0) {
				this.SCIICC = new BigDecimal(0);
			}
			
			SubRutConaju(ds, ObjCcrpsch.getScbanc().toString(), ObjCcrpsch.getScnucr().toString(), scncuo, ObjCcrpsch.getScticu());
			
			this.TOTAL = ObjCcrpsch.getScisvi().add(ObjCcrpsch.getSciise().add(ObjCcrpsch.getScimam().add(ObjCcrpsch.getScimba().add(ObjCcrpsch.getScicin().add(ObjCcrpsch.getScicaj().add(ObjCcrpsch.getScicca().add(this.TOPTUN.add(this.SBIEN.add(ObjCcrpsch.getSccar1().add(ObjCcrpsch.getSci8c1().subtract(this.WSICAC.add(this.WSIB))))))))))));
			
			if(ObjCcrpsch.getScibas() > 0) {
				this.FACCOR = ObjCcrpsch.getSciact() / ObjCcrpsch.getScibas();
			}
			
			if (ObjCcrpsch.getScmaav().equals("0")) {
				AVIVTO = "EMITIDO";
			}else {
				AVIVTO = "NO EMITIDO";
			}
			
		} catch (Exception e) {
			return e.getMessage();
			
		}
		return "";
		
	}// Fin SubRutRtn100

	
	private String SubRutPPAR01 (DataSet ds, String crnucr, int scncuo) {
		try {
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
			ObjCcrpcar = myDaoCcrpcar.getUsingCrnucrAndScncuo(ds, crnucr, scncuo);
			if (ObjCcrpcar != null){
				extor = "X";
			}
			else {
				extor = "";
			}
		} 
		catch (Exception e) {
			return e.getMessage();
		}

		return "";
	}//Fin SubRutEXTR01
	
	
	private String SubRutConaju(DataSet ds, String scbanc, String scnucr, String scncuo, String scticu) {
		try {
			ListCcrpcci = myDaoCcrpcci.getUsingnScbancAndScnucrAndScncuoAndScticuToList(ds, scbanc, scnucr, scncuo, scticu);
			if(ListCcrpcci != null) {
				for(Ccrpcci o : ListCcrpcci) {
					WSICAC = o.getCiicac();
					SCACON = o.getCiusau();
				}
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "";
	}	
	
	
	public class CCRR0580Adapter{
		
		String SCIBAS = null; 
		String SCTICU = null;
		String SCIACT = null;
		String SCFPCU = null;
		String SCCSUP = null;
		String SCIICD = null;
		String SCIIPD = null;
		String SCIIPC = null;
		String SCIICC = null;
		String SCACON = null;
		String SCSTCU = null;
		String SCISAU = null;
		String SCI8IN = null;
		String SCI8CD = null;
		String SCI8PD = null;
		String SCI8PC = null;
		String SCI8CC = null;
		String SCI8CS = null;
		String SCI8CA = null;
		String SCI9IN = null;
		String SCI9CD = null;
		String SCI9PD = null;
		String SCI9PC = null;
		String SCI9CC = null;
		String SCI9CS = null;
		String SCI9CA = null;
		String SCCAR1 = null;
		String SCI8C1 = null;
		String DSFPCU = null;
		String DSTICU = null;
		String WSICAC = null;
		
			
		public CCRR0580Adapter (){ }
		
		
		public String getSCTICU() {
			return SCTICU;
		}

		public void setSCTICU(String sCTICU) {
			SCTICU = sCTICU;
		}

		public String getSCIBAS() {
			return SCIBAS;
		}

		public void setSCIBAS(String sCIBAS) {
			SCIBAS = sCIBAS;
		}
		
		public String getSCIACT() {
			return SCIACT;
		}

		public void setSCIACT(String sCIACT) {
			SCIACT = sCIACT;
		}

		public String getSCFPCU() {
			return SCFPCU;
		}

		public void setSCFPCU(String sCFPCU) {
			SCFPCU = sCFPCU;
		}

		public String getSCCSUP() {
			return SCCSUP;
		}

		public void setSCCSUP(String sCCSUP) {
			SCCSUP = sCCSUP;
		}

		public String getSCIICD() {
			return SCIICD;
		}

		public void setSCIICD(String sCIICD) {
			SCIICD = sCIICD;
		}

		public String getSCIIPD() {
			return SCIIPD;
		}

		public void setSCIIPD(String sCIIPD) {
			SCIIPD = sCIIPD;
		}

		public String getSCIIPC() {
			return SCIIPC;
		}

		public void setSCIIPC(String sCIIPC) {
			SCIIPC = sCIIPC;
		}

		public String getSCIICC() {
			return SCIICC;
		}

		public void setSCIICC(String sCIICC) {
			SCIICC = sCIICC;
		}

		public String getSCACON() {
			return SCACON;
		}

		public void setSCACON(String sCACON) {
			SCACON = sCACON;
		}

		public String getSCSTCU() {
			return SCSTCU;
		}

		public void setSCSTCU(String sCSTCU) {
			SCSTCU = sCSTCU;
		}

		public String getSCISAU() {
			return SCISAU;
		}

		public void setSCISAU(String sCISAU) {
			SCISAU = sCISAU;
		}

		public String getSCI8IN() {
			return SCI8IN;
		}

		public void setSCI8IN(String sCI8IN) {
			SCI8IN = sCI8IN;
		}

		public String getSCI8CD() {
			return SCI8CD;
		}

		public void setSCI8CD(String sCI8CD) {
			SCI8CD = sCI8CD;
		}

		public String getSCI8PD() {
			return SCI8PD;
		}

		public void setSCI8PD(String sCI8PD) {
			SCI8PD = sCI8PD;
		}

		public String getSCI8PC() {
			return SCI8PC;
		}

		public void setSCI8PC(String sCI8PC) {
			SCI8PC = sCI8PC;
		}

		public String getSCI8CC() {
			return SCI8CC;
		}

		public void setSCI8CC(String sCI8CC) {
			SCI8CC = sCI8CC;
		}

		public String getSCI8CS() {
			return SCI8CS;
		}

		public void setSCI8CS(String sCI8CS) {
			SCI8CS = sCI8CS;
		}

		public String getSCI8CA() {
			return SCI8CA;
		}

		public void setSCI8CA(String sCI8CA) {
			SCI8CA = sCI8CA;
		}

		public String getSCI9IN() {
			return SCI9IN;
		}

		public void setSCI9IN(String sCI9IN) {
			SCI9IN = sCI9IN;
		}

		public String getSCI9CD() {
			return SCI9CD;
		}

		public void setSCI9CD(String sCI9CD) {
			SCI9CD = sCI9CD;
		}

		public String getSCI9PD() {
			return SCI9PD;
		}

		public void setSCI9PD(String sCI9PD) {
			SCI9PD = sCI9PD;
		}

		public String getSCI9PC() {
			return SCI9PC;
		}

		public void setSCI9PC(String sCI9PC) {
			SCI9PC = sCI9PC;
		}

		public String getSCI9CC() {
			return SCI9CC;
		}

		public void setSCI9CC(String sCI9CC) {
			SCI9CC = sCI9CC;
		}

		public String getSCI9CS() {
			return SCI9CS;
		}

		public void setSCI9CS(String sCI9CS) {
			SCI9CS = sCI9CS;
		}

		public String getSCI9CA() {
			return SCI9CA;
		}

		public void setSCI9CA(String sCI9CA) {
			SCI9CA = sCI9CA;
		}

		public String getSCCAR1() {
			return SCCAR1;
		}

		public void setSCCAR1(String sCCAR1) {
			SCCAR1 = sCCAR1;
		}

		public String getSCI8C1() {
			return SCI8C1;
		}

		public void setSCI8C1(String sCI8C1) {
			SCI8C1 = sCI8C1;
		}


		public String getDSFPCU() {
			return DSFPCU;
		}


		public void setDSFPCU(String dSFPCU) {
			DSFPCU = dSFPCU;
		}


		public String getDSTICU() {
			return DSTICU;
		}


		public void setDSTICU(String dSTICU) {
			DSTICU = dSTICU;
		}


		public String getWSICAC() {
			return WSICAC;
		}


		public void setWSICAC(String wSICAC) {
			WSICAC = wSICAC;
		}
		
		
		
		
		
		
		
	
	} //Fin CCRR0580Adapter
	
	
	
} // Fin public class CCRR0580View02BzService


