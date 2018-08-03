package com.ciessa.museum.bz.legacy;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.ciessa.museum.dao.legacy.SaldomDAO;
import com.ciessa.museum.model.legacy.Saldom;
import com.ciessa.museum.dao.legacy.Tap002DAO;
import com.ciessa.museum.dao.legacy.Tap014DAO;
import com.ciessa.museum.dao.legacy.Tap902DAO;
import com.ciessa.museum.model.legacy.Tap002;
import com.ciessa.museum.model.legacy.Tap014;
import com.ciessa.museum.model.legacy.Tap902;
import com.ciessa.museum.dao.legacy.TablamDAO;
import com.ciessa.museum.model.legacy.Tablam;
import com.ciessa.museum.dao.legacy.AltnamDAO;
import com.ciessa.museum.dao.legacy.Cfp001210DAO;
import com.ciessa.museum.dao.legacy.CumastDAO;
import com.ciessa.museum.dao.legacy.Cuxrf1DAO;
import com.ciessa.museum.dao.legacy.RsctamDAO;
import com.ciessa.museum.model.legacy.Altnam;
import com.ciessa.museum.model.legacy.Cfp001210;
import com.ciessa.museum.model.legacy.Cumast;
import com.ciessa.museum.model.legacy.Cuxrf1;
import com.ciessa.museum.model.legacy.Rsctam;

public class TAR0030View01BzService extends RestBaseServerResource{
	
	public static final Logger log = Logger.getLogger(TAR0030View01BzService.class.getName());

	@Autowired
	DataSetDAO dsDao;

	@Autowired
	Tap002DAO myDaoTap002;
	
	@Autowired
	SaldomDAO myDaoSaldom;
	
	@Autowired
	TablamDAO myDaoTablam;
	
	@Autowired
	Cfp001210DAO myDaoCfp001210;
	
	@Autowired
	AltnamDAO myDaoAltnam;
	
	@Autowired
	RsctamDAO myDaoRsctam;
	
	@Autowired
	Tap902DAO myDaoTap902;
	
	@Autowired
	Tap014DAO myDaoTap014;
	
	@Autowired
	Cuxrf1DAO myDaoCuxrf1;
	
	@Autowired
	CumastDAO myDaoCumast;

	//Entity
	Tap002 objTap002 = new Tap002();
	Saldom objSaldom = new Saldom();
    Tablam objTablam = new Tablam();
    Cfp001210 objCfp001210 = new Cfp001210();
    Altnam objAltnam = new Altnam();
    Rsctam objRsctam = new Rsctam();
    Tap902 objTap902 = new Tap902();
    Tap014 objTap014 = new Tap014();
    List<Cuxrf1> lstCuxrf1 = null;
    Cumast objCumast = new Cumast();
    
    //Variables
    String cuenta = null;
    String tipbco = null;
    String dmtype = null;
    Integer branch = null;
	String oficta = null;
	Integer divisi = null;
	String sitfis = null;
	Integer planca = null;
	String impdeb = null;
    String tipo = null;
    String typdes = null;
    Long ctacac = null;
    String estad = null;
    String estado = null;
    String tipest = null;
    String envpos = null;
    Date fecape = null;
    Date fecult = null;
    String exsmov = null;
    Integer cicdes = null;
    String frecue = null;
    String nomct1 = null;
    String nomct2 = null;
    String nomct3 = null;
    String domici = null;
    String localo = null;
    String provin = null;
    Integer codpos = null;
    String telefo = null;
    Integer segmen = null;
    Integer bcracd = null;
	Integer canext = null;
	Long citima = null;
	Integer codtas = null;
	String acuerd = null;
	
	Date fecpna = null;
	Date fecna = null;
	Date fecwo = null;
	Date fecua = null;
	Integer moneda = null;
	String cativa = null;
	BigDecimal nrruc = null;
	String altaex = null;
	Integer planin = null;
	Long motbaj = null;
	String cvalor = null;
	String desbaj = null;
	
	String tipcta = null;
	String name1 = null;
	String name2 = null;
	String name3 = null;
	String name4 = null;
	String name5 = null;
	String name6 = null;
    
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		String rpta = null;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();

			//Parametros
			String ctacta = obtainStringValue("ctacta", null);
			
			//Proceso
			if (ctacta.equals(""))
			{
				log.log(Level.SEVERE, ctacta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException("Parámetros incorrectos", new Exception())).toString();
			}
			if (!ctacta.equals(""))
			{
				cuenta = ctacta;
			}
			tipbco = "Cod.BCU :";
			rpta = SubRutCuen(ds, cuenta);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			branch = objTap002.getDmbrch();
			oficta = objTap002.getDmoff();
			divisi = objTap002.getDmsf4();
			sitfis = objTap002.getDmwhfg();
			planca = objTap002.getDmsccd();
			impdeb = objTap002.getDmadex();
			if (cuenta.charAt(0) == '5') {
				tipo = "1";
				} 
			else {	
				tipo = "6";
				}
			dmtype = objTap002.getDmtype().toString();
			objCfp001210 = myDaoCfp001210.getUsingKey(ds, tipo, dmtype);
			typdes = objCfp001210.getCftnme();
			ctacac = objTap002.getDmtfac();
			estad = objTap002.getDmstat();
			if (estad.equals("1") || estad.equals("6")) {
				estado = "0";
				tipest = "ACTIVA";
			}
			if (estad.equals("5")) {
				estado = "1";
				tipest = "INACTIVA";
			}
			if (estad.equals("A")) {
				estado = "2";
				tipest = "DOM.DESCONOCIDO";
			}
			if (estad.equals("B")) {
				estado = "3";
				tipest = "EMBARGADA";
			}
			if (estad.equals("C")) {
				estado = "4";
				tipest = "BLOQUEADA";
			}
			if (estad.equals("D")) {
				estado = "5";
				tipest = "CERRADA C/SALDO";
			}
			if (estad.equals("E")) {
				estado = "8";
				tipest = "TRAMITE CIERRE";
			}
			if (estad.equals("4")) {
				estado = "9";
				tipest = "CERRADA";
			}
			if (estad.equals("N")) {
				estado = "N";
				tipest = "NON ACCRUAL";
			}
			if (estad.equals("O")) {
				estado = "O";
				tipest = "WRITE OFF";
			}
			if (estad.equals("P")) {
				estado = "P";
				tipest = "PEND. N/A";
			}
			if (objTap002.getDmmail().equals("N")) {
				if (objTap002.getDmntrq().equals("2")) {
					envpos = "5";
				}
				else {
					envpos = "1";
				}
			}
			if (objTap002.getDmmail().equals("Y")) {
				envpos = "2";
			}
			if (objTap002.getDmmail().equals("X")) {
				envpos = "8";
			}
			if (objTap002.getDmmail().equals("D")) {
				envpos = "3";
			}
			if (objTap002.getDmmail().equals("E")) {
				envpos = "4";
			}
			if (objTap002.getDmmail().equals("M")) {
				envpos = "6";
			}
			if (objTap002.getDmmail().equals("H")) {
				envpos = "7";
			}
			if (objTap002.getDmmail().equals("C")) {
				envpos = "9";
			}
			fecape = new SimpleDateFormat("yyyyMMdd").parse(objTap002.getDmdopn().toString());
			fecult = new SimpleDateFormat("yyyyMMdd").parse(objTap002.getDmntdt().toString());
			if (objTap002.getDmnoac().equals("D")) {
				exsmov = "N";
			}
			else {
				exsmov = "S";
			}
			if (objTap002.getDmstsp() != 0) {
				cicdes = objTap002.getDmstsp();
				if (objTap002.getDmstfr() == 1) {
					frecue = "M";
				}
				else {
					//TODO: Recuperar DataArea CtaAtri
					//TODO: FRECUE = TIPTRI
				}
			}
			else {
				if (objTap002.getDmstfr() == 1) {
					cicdes = 40;
					frecue = "D";
				}
				else {
					cicdes = 50;
					frecue = "S";
				}
			}
			objAltnam = myDaoAltnam.getUsingCuenta(ds, cuenta);
			if (objAltnam !=null ) {
				nomct1 = objAltnam.getNamel1();
				nomct2 = objAltnam.getNamel2();
				nomct3 = objAltnam.getNamel3();
				domici = objAltnam.getAdres1();
				localo = objAltnam.getAdres2();
				provin = objAltnam.getAdres3();
				codpos = objAltnam.getCposta();
				telefo = objAltnam.getCtelef();
			}
			objRsctam = myDaoRsctam.getUsingCuenta(ds, cuenta);
			if (objRsctam != null) {
				segmen = objRsctam.getCsegme();
				bcracd = objRsctam.getCactiv();
				canext = objRsctam.getQresum();
			}
			if (tipo.equals("6")) {
				objTap902 = myDaoTap902.getUsingCuentaAndCaplp(ds, cuenta, 20);
				if (objTap902 != null) {
					citima = objTap902.getNctav();
				}
			}
			else {
				objTap902 = myDaoTap902.getUsingCuentaAndCaplp(ds, cuenta, 10);
				if (objTap902 != null) {
					citima = objTap902.getNctap();
				}
			}
			objTap014 = myDaoTap014.getUsingDmtypDmacctAndDmtodf(ds, tipo, cuenta, 9);
			if (objTap014 != null) {
				if (tipo.equals("6")) {
					codtas = objTap014.getDoindx();
				}
				objTap014 = myDaoTap014.getUsingDmtypDmacctAndDmtodf(ds, tipo, cuenta, 1);
				if (objTap014 != null) {
					acuerd = "A";
				}
				objTap014 = myDaoTap014.getUsingDmtypDmacctAndDmtodf(ds, tipo, cuenta, 2);
				if (objTap014 != null) {
					if (acuerd.equals("A")) {
						acuerd = "M";
					}
					else {
						acuerd = "D";
					}
				}
				
				rpta = SubRutShort(ds);
				if (!rpta.equals(""))
				{
					log.log(Level.SEVERE, rpta, new Exception());
					return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
				}
				
			}

			// Logs the result
			long diff = new Date().getTime() - millisPre;
			log.info("Element found in " + diff + " millis");

			// Get the output fields
			String[] fields = new String[] {
					"CUENTA",
					"BRANCH",
					"FECAPE",
					"TIPEST",
					"ESTADO",
					"MOTBAJ",
					"DESBAJ",
					"FECULT",
					"DMTYPE",
					"TYPDES",
					"MONEDA",
					"ACUERD",
					"DIVISI",
					"OFICTA",
					"ALTAEX",
					"PLANIN",
					"TIPBCO",
					"BCRACD",
					"SITFIS",
					"IMPDEB",
					"ENVPOS",
					"CICDES",
					"FRECUE",
					"EXSMOV",
					"PLANCA",
					"CODTAS",
					"TIPCTA",
					"CTACAC",
					"CITIMA",
					"CATIVA",
					"NRRUC",
					"NOMCT1",
					"NOMCT2",
					"NOMCT3",
					"DOMICI",
					"TELEFO",
					"CODPOS",
					"LOCALI",
					"PROVIN",
					"DMSTCP",
					"FECPNA",
					"NAME1",
					"NAME2",
					"FECNA",
					"NAME3",
					"NAME4",
					"FECWO",
					"NAME5",
					"NAME6",
					"FECUA"
			};

			// Obtains the user JSON representation
			TAR0030Adapter adapted = new TAR0030Adapter();
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

	private String SubRutCuen(DataSet ds, String cuenta) {
		String CVALOR=null;
		try {
			if (cuenta.equals("0")) {
				return "Parámetros incorrectos esta en cero";
			}
			else {
				if (cuenta.charAt(0) != '0' && cuenta.charAt(0) != '5') {
					return "Cuenta no valida.";
				} 
				else {
					if (cuenta.charAt(0) == '5')
					{
						tipo = "1";
					} 
					else
					{
						tipo = "6";
					}
				}
			}
			objTap002 = myDaoTap002.getUsingTipoAndCuenta(ds, tipo, cuenta);
			if (objTap002 == null)
			{
				log.log(Level.SEVERE, "Cuenta inexistente.", new Exception());
				return "Cuenta inexistente";
			}
			objSaldom = myDaoSaldom.getUsingTipoAndCuenta(ds, tipo, cuenta);
			if (objSaldom == null)
			{
				log.log(Level.SEVERE,  "Cuenta con Saldo inexistente.", new Exception());
				return "Cuenta con Saldo inexistente.";
			}
			else
			{
				try {
					fecpna = new SimpleDateFormat("yyyyMMdd").parse(objSaldom.getDsapen().toString());
					fecna = new SimpleDateFormat("yyyyMMdd").parse(objSaldom.getDsana().toString());
					fecwo = new SimpleDateFormat("yyyyMMdd").parse(objSaldom.getDsawo().toString());
					fecua = new SimpleDateFormat("yyyyMMdd").parse(objTap002.getDmfdi4().toString());
				} catch( Exception e ) {}
				moneda = objTap002.getDmcmcn();
				cativa = objTap002.getDmiova();
				nrruc = objTap002.getDmadde().multiply(new BigDecimal(10));
				altaex = objTap002.getDmrest();
				planin = objTap002.getDmdint();
				motbaj = objTap002.getDmcf1();
				if (objTap002.getDmtyp() == 1 && objTap002.getDmstat() == "4" && objTap002.getDmcf1() == 0) {
					cvalor = "99";
				}
				else{			
					cvalor = " " + new DecimalFormat("00").format(objTap002.getDmcf1());
				}
				objTablam = myDaoTablam.getUsingCvalor(ds, cvalor);
				if (objTablam != null)
				{
					desbaj = objTablam.getTdescr();					
				}
			}
		} catch (ASException e) {
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutShort(DataSet ds) {
		try {
			lstCuxrf1 = myDaoCuxrf1.getUsingCux1ac(ds, tipo, cuenta);
			for(Cuxrf1 o : lstCuxrf1) {
				if (o.getCuxrel().charAt(2-1) == 'O') {
					tipcta = "I";
				}
				else {
					tipcta = "C";
				}
				objCumast = myDaoCumast.getUsingCunbr(ds, o.getCux1cs());
				if (objCumast != null) {
					if (name1 == null) name1 = objCumast.getCushky();
					if (name2 == null) name2 = objCumast.getCushky();
					if (name3 == null) name3 = objCumast.getCushky();
					if (name4 == null) name4 = objCumast.getCushky();
					if (name5 == null) name5 = objCumast.getCushky();
					if (name6 == null) name6 = objCumast.getCushky();
				}
			}
		} catch (ASException e) {
			return e.getMessage();
		}
		return "";
	}
		
	public class TAR0030Adapter {
		private String CUENTA;
		private Integer BRANCH;
		private Date FECAPE;
		private String TIPEST;
		private String ESTADO;
		private Long MOTBAJ;
		private String DESBAJ;
		private Date FECULT;
		private String DMTYPE;
		private String TYPDES;
		private Integer MONEDA;
		private String ACUERD;
		private Integer DIVISI;
		private String OFICTA;
		private String ALTAEX;
		private Integer PLANIN;
		private String TIPBCO;
		private Integer BCRACD;
		private String SITFIS;
		private String IMPDEB;
		private String ENVPOS;
		private Integer CICDES;
		private String FRECUE;
		private String EXSMOV;
		private Integer PLANCA;
		private Integer CODTAS;
		private String TIPCTA;
		private Long CTACAC;
		private Long CITIMA;
		private String CATIVA;
		private BigDecimal NRRUC;
		private String NOMCT1;
		private String NOMCT2;
		private String NOMCT3;
		private String DOMICI;
		private String TELEFO;
		private Integer CODPOS;
		private String LOCALI;
		private String PROVIN;
		private String DMSTCP;
		private Date FECPNA;
		private String NAME1;
		private String NAME2;
		private Date FECNA;
		private String NAME3;
		private String NAME4;
		private Date FECWO;
		private String NAME5;
		private String NAME6;
		private Date FECUA;

		public TAR0030Adapter() {
			this.CUENTA = cuenta;
			this.BRANCH = branch;
			this.FECAPE = fecape;
			this.TIPEST = tipest;
			this.ESTADO = estado;
			this.MOTBAJ = motbaj;
			this.DESBAJ = desbaj;
			this.FECULT = fecult;
			this.DMTYPE = dmtype;
			this.TYPDES = typdes;
			this.MONEDA = moneda;
			this.ACUERD = acuerd;
			this.DIVISI = divisi;
			this.OFICTA = oficta;
			this.ALTAEX = altaex;
			this.PLANIN = planin;
			this.TIPBCO = tipbco;
			this.BCRACD = bcracd;
			this.SITFIS = sitfis;
			this.IMPDEB = impdeb;
			this.ENVPOS = envpos;
			this.CICDES = cicdes;
			this.FRECUE = frecue;
			this.EXSMOV = exsmov;
			this.PLANCA = planca;
			this.CODTAS = codtas;
			this.TIPCTA = tipcta;
			this.CTACAC = ctacac;
			this.CITIMA = citima;
			this.CATIVA = cativa;
			this.NRRUC = nrruc;
			this.NOMCT1 = nomct1;
			this.NOMCT2 = nomct2;
			this.NOMCT3 = nomct3;
			this.DOMICI = domici;
			this.TELEFO = telefo;
			this.CODPOS = codpos;
			this.LOCALI = ""; //TODO: este campo no tiene asignacion dentro del algoritmo
			this.PROVIN = provin;
			this.DMSTCP = ""; //TODO: este campo no tiene asignacion dentro del algoritmo
			this.FECPNA = fecpna;
			this.NAME1 = name1;
			this.NAME2 = name2;
			this.FECNA = fecna;
			this.NAME3 = name3;
			this.NAME4 = name4;
			this.FECWO = fecwo;
			this.NAME5 = name5;
			this.NAME6 = name6;
			this.FECUA = fecua;
		}

		public String getCUENTA() {
			return CUENTA;
		}

		public void setCUENTA(String cUENTA) {
			CUENTA = cUENTA;
		}

		public Integer getBRANCH() {
			return BRANCH;
		}

		public void setBRANCH(Integer bRANCH) {
			BRANCH = bRANCH;
		}

		public Date getFECAPE() {
			return FECAPE;
		}

		public void setFECAPE(Date fECAPE) {
			FECAPE = fECAPE;
		}

		public String getTIPEST() {
			return TIPEST;
		}

		public void setTIPEST(String tIPEST) {
			TIPEST = tIPEST;
		}

		public String getESTADO() {
			return ESTADO;
		}

		public void setESTADO(String eSTADO) {
			ESTADO = eSTADO;
		}

		public Long getMOTBAJ() {
			return MOTBAJ;
		}

		public void setMOTBAJ(Long mOTBAJ) {
			MOTBAJ = mOTBAJ;
		}

		public String getDESBAJ() {
			return DESBAJ;
		}

		public void setDESBAJ(String dESBAJ) {
			DESBAJ = dESBAJ;
		}

		public Date getFECULT() {
			return FECULT;
		}

		public void setFECULT(Date fECULT) {
			FECULT = fECULT;
		}

		public String getDMTYPE() {
			return DMTYPE;
		}

		public void setDMTYPE(String dMTYPE) {
			DMTYPE = dMTYPE;
		}

		public String getTYPDES() {
			return TYPDES;
		}

		public void setTYPDES(String tYPDES) {
			TYPDES = tYPDES;
		}

		public Integer getMONEDA() {
			return MONEDA;
		}

		public void setMONEDA(Integer mONEDA) {
			MONEDA = mONEDA;
		}

		public String getACUERD() {
			return ACUERD;
		}

		public void setACUERD(String aCUERD) {
			ACUERD = aCUERD;
		}

		public Integer getDIVISI() {
			return DIVISI;
		}

		public void setDIVISI(Integer dIVISI) {
			DIVISI = dIVISI;
		}

		public String getOFICTA() {
			return OFICTA;
		}

		public void setOFICTA(String oFICTA) {
			OFICTA = oFICTA;
		}

		public String getALTAEX() {
			return ALTAEX;
		}

		public void setALTAEX(String aLTAEX) {
			ALTAEX = aLTAEX;
		}

		public Integer getPLANIN() {
			return PLANIN;
		}

		public void setPLANIN(Integer pLANIN) {
			PLANIN = pLANIN;
		}

		public String getTIPBCO() {
			return TIPBCO;
		}

		public void setTIPBCO(String tIPBCO) {
			TIPBCO = tIPBCO;
		}

		public Integer getBCRACD() {
			return BCRACD;
		}

		public void setBCRACD(Integer bCRACD) {
			BCRACD = bCRACD;
		}

		public String getSITFIS() {
			return SITFIS;
		}

		public void setSITFIS(String sITFIS) {
			SITFIS = sITFIS;
		}

		public String getIMPDEB() {
			return IMPDEB;
		}

		public void setIMPDEB(String iMPDEB) {
			IMPDEB = iMPDEB;
		}

		public String getENVPOS() {
			return ENVPOS;
		}

		public void setENVPOS(String eNVPOS) {
			ENVPOS = eNVPOS;
		}

		public Integer getCICDES() {
			return CICDES;
		}

		public void setCICDES(Integer cICDES) {
			CICDES = cICDES;
		}

		public String getFRECUE() {
			return FRECUE;
		}

		public void setFRECUE(String fRECUE) {
			FRECUE = fRECUE;
		}

		public String getEXSMOV() {
			return EXSMOV;
		}

		public void setEXSMOV(String eXSMOV) {
			EXSMOV = eXSMOV;
		}

		public Integer getPLANCA() {
			return PLANCA;
		}

		public void setPLANCA(Integer pLANCA) {
			PLANCA = pLANCA;
		}

		public Integer getCODTAS() {
			return CODTAS;
		}

		public void setCODTAS(Integer cODTAS) {
			CODTAS = cODTAS;
		}

		public String getTIPCTA() {
			return TIPCTA;
		}

		public void setTIPCTA(String tIPCTA) {
			TIPCTA = tIPCTA;
		}

		public Long getCTACAC() {
			return CTACAC;
		}

		public void setCTACAC(Long cTACAC) {
			CTACAC = cTACAC;
		}

		public Long getCITIMA() {
			return CITIMA;
		}

		public void setCITIMA(Long cITIMA) {
			CITIMA = cITIMA;
		}

		public String getCATIVA() {
			return CATIVA;
		}

		public void setCATIVA(String cATIVA) {
			CATIVA = cATIVA;
		}

		public BigDecimal getNRRUC() {
			return NRRUC;
		}

		public void setNRRUC(BigDecimal nRRUC) {
			NRRUC = nRRUC;
		}

		public String getNOMCT1() {
			return NOMCT1;
		}

		public void setNOMCT1(String nOMCT1) {
			NOMCT1 = nOMCT1;
		}

		public String getNOMCT2() {
			return NOMCT2;
		}

		public void setNOMCT2(String nOMCT2) {
			NOMCT2 = nOMCT2;
		}

		public String getNOMCT3() {
			return NOMCT3;
		}

		public void setNOMCT3(String nOMCT3) {
			NOMCT3 = nOMCT3;
		}

		public String getDOMICI() {
			return DOMICI;
		}

		public void setDOMICI(String dOMICI) {
			DOMICI = dOMICI;
		}

		public String getTELEFO() {
			return TELEFO;
		}

		public void setTELEFO(String tELEFO) {
			TELEFO = tELEFO;
		}

		public Integer getCODPOS() {
			return CODPOS;
		}

		public void setCODPOS(Integer cODPOS) {
			CODPOS = cODPOS;
		}

		public String getLOCALI() {
			return LOCALI;
		}

		public void setLOCALI(String lOCALI) {
			LOCALI = lOCALI;
		}

		public String getPROVIN() {
			return PROVIN;
		}

		public void setPROVIN(String pROVIN) {
			PROVIN = pROVIN;
		}

		public String getDMSTCP() {
			return DMSTCP;
		}

		public void setDMSTCP(String dMSTCP) {
			DMSTCP = dMSTCP;
		}

		public Date getFECPNA() {
			return FECPNA;
		}

		public void setFECPNA(Date fECPNA) {
			FECPNA = fECPNA;
		}

		public String getNAME1() {
			return NAME1;
		}

		public void setNAME1(String nAME1) {
			NAME1 = nAME1;
		}

		public String getNAME2() {
			return NAME2;
		}

		public void setNAME2(String nAME2) {
			NAME2 = nAME2;
		}

		public Date getFECNA() {
			return FECNA;
		}

		public void setFECNA(Date fECNA) {
			FECNA = fECNA;
		}

		public String getNAME3() {
			return NAME3;
		}

		public void setNAME3(String nAME3) {
			NAME3 = nAME3;
		}

		public String getNAME4() {
			return NAME4;
		}

		public void setNAME4(String nAME4) {
			NAME4 = nAME4;
		}

		public Date getFECWO() {
			return FECWO;
		}

		public void setFECWO(Date fECWO) {
			FECWO = fECWO;
		}

		public String getNAME5() {
			return NAME5;
		}

		public void setNAME5(String nAME5) {
			NAME5 = nAME5;
		}

		public String getNAME6() {
			return NAME6;
		}

		public void setNAME6(String nAME6) {
			NAME6 = nAME6;
		}

		public Date getFECUA() {
			return FECUA;
		}

		public void setFECUA(Date fECUA) {
			FECUA = fECUA;
		}
		
	}
	
}
