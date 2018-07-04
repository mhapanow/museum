package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.bz.legacy.TAR0077ListBzService.TAR0077Adapter;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.CacphstDAO;
import com.ciessa.museum.dao.legacy.CacpmreDAO;
import com.ciessa.museum.dao.legacy.Cfp001002DAO;
import com.ciessa.museum.dao.legacy.GrmcdaDAO;
import com.ciessa.museum.dao.legacy.GrmidaDAO;
import com.ciessa.museum.dao.legacy.GrmriaDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cacphst;
import com.ciessa.museum.model.legacy.Cacpmre;
import com.ciessa.museum.model.legacy.Cfp001002;
import com.ciessa.museum.model.legacy.Cfp001220;
import com.ciessa.museum.model.legacy.Cuxrf1;
import com.ciessa.museum.model.legacy.Grmcda;
import com.ciessa.museum.model.legacy.Grmida;
import com.ciessa.museum.model.legacy.Grmria;
import com.ciessa.museum.model.legacy.Tap014;
import com.ciessa.museum.tools.CollectionFactory;

public class CACR210View01BzService extends RestBaseServerResource {
	
	public static final Logger log = Logger.getLogger(CACR210View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CacphstDAO myDaoCacphst;
	
	@Autowired
	Cfp001002DAO myDaoCfp001002;
	
	@Autowired
	CacpmreDAO myDaoCacpmre;
	
	@Autowired
	GrmriaDAO myDaoGrmria;
	
	@Autowired
	GrmidaDAO myDaoGrmida;
	
	@Autowired
	GrmcdaDAO myDaoGrmcda;
	
	//Entity
	Cacphst objCacphst = new Cacphst();
	Cfp001002 objCfp001002 = new Cfp001002();
	Cacpmre objCacpmre = new Cacpmre();
	Grmria objGrmria = new Grmria();
	Grmida objGrmida = new Grmida();
	Grmcda objGrmcda = new Grmcda();
	
	
	//Variables
    Date sfeamd  = null;
    Date sfedma = null;
    Date ssfsel = null;
    String ssbrch = null;
    String ssresu = null;
    String ssmrec = null;
    Date wfsel = null;
    String wsdbrc = null;
    String wsmrec = null;
    String ssdmre = null;
    List<CACR210Adapter> listAdapted = null;   
    CACR210Adapter adapted = null;
    
	@Get
	public String view() {
		
		long start = markStart();
		JSONObject returnValue = null;
		String rpta = null;

		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			
			//Obtener Parametros
			try {
				ssfsel = new SimpleDateFormat("ddMMyyyy").parse(obtainStringValue("ssfsel", null).toString());
			} catch (Exception e) {	}
			ssbrch = obtainStringValue("ssbrch", null);
			ssresu = obtainStringValue("ssresu", null);
			ssmrec = obtainStringValue("ssmrec", null);
			if (ssbrch == null || ssresu == null || ssmrec == null) {
				log.log(Level.SEVERE, "Parametros incorrectos", new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException("Parametros incorrectos", new Exception())).toString();
			}
			
			long millisPre = new Date().getTime();
			

			objCacphst = myDaoCacphst.getUsingMax(ds);
			if (objCacphst != null)
			{
				try {
					sfeamd = new SimpleDateFormat("yyyyMMdd").parse(objCacphst.getHifsel().toString());
				} catch (Exception e) {	}
				//TODO: Ejecutar rutina FECHA
				ssfsel = sfeamd;
			}
			//TODO: se va manejar directamente con la Base de Datos.
			rpta = SubRutVal(ds);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			rpta = SubRutCarsf1(ds);
			
			// retrieve all elements
			Map<String,String> attributes = CollectionFactory.createMap();
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found [" + listAdapted.size() + "] in " + diff + " millis");
			
			String[] fields = new String[] {
					"pkid",
					"member",
					"WSACCT",
					"WSDTRE",
					"WSDATE",
					"WSAMND",
					"WSAAMT",
					"WSNVTO",
					"WSDRES"
			};
			
			returnValue = this.getJSONRepresentationFromArrayOfObjects(listAdapted, fields);		
			returnValue.put("SSFSEL", ssfsel);
			returnValue.put("$$BRCH", ssbrch);
			returnValue.put("WSDBRC", wsdbrc);
			returnValue.put("SSRESU", ssresu);
			returnValue.put("SSMREC", ssmrec);
			returnValue.put("SSDMRE", ssdmre);
			returnValue.put("WSCUEN", listAdapted.size());
			
			if( attributes.containsKey("recordCount"))
				returnValue.put("recordCount", Long.valueOf(attributes.get("recordCount")));
			else 
				returnValue.put("recordCount", listAdapted.size());
			
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
	
	private String SubRutVal(DataSet ds) {
		try {
			if (ssfsel == null && ssbrch.equals(0) && ssresu.equals("") && ssmrec.equals("")) {
				return "Parámetros incorrectos";
			}
			wfsel = ssfsel;
			if (!ssbrch.equals("999")) {
				objCfp001002 = myDaoCfp001002.getUsingCfnsuc(ds, ssbrch);
				if (objCfp001002 == null) {
					return "No se ha encontrado el registro del motivo";
				}else {
					wsdbrc = objCfp001002.getCfbrnm();
				}
			}
			if (ssbrch.equals("999")) {
				wsdbrc = "TODAS";
			}
			if(!ssmrec.equals("0")) { //W$MREC
				objCacpmre = myDaoCacpmre.getUsingMrmrec(ds, ssmrec);
				if (objCacpmre == null) {
					return "No se ha encontrado el registro";
				}else {
					ssresu="R";
					ssdmre = objCacpmre.getMrdesc();
				}
			}
		} catch (ASException e) {
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutCarsf1(DataSet ds) {
		try {
			List<Cacphst> list = new ArrayList<Cacphst>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String fechaComoCadena = sdf.format(ssfsel);
			list = myDaoCacphst.getUsingKey(ds, Integer.parseInt(fechaComoCadena), Integer.parseInt(ssbrch), Integer.parseInt(ssmrec), ssresu);
			listAdapted = new ArrayList<CACR210Adapter>();
			for( Cacphst obj : list ) {
				adapted = new CACR210Adapter();
				if (obj.getHitreg().equals("1")) {
					adapted.setWSDTRE("ACT");
				}else {
					adapted.setWSDTRE("REN");
				}
				objGrmria = myDaoGrmria.getUsingRqactn(ds, obj.getHiacct().toString());
				if (objGrmria != null) {
					objGrmida = myDaoGrmida.getUsingRirmcn(ds, objGrmria.getRqrmcn().toString());
					if(objGrmida != null) {
						adapted.setWSTITU(objGrmida.getRilsnm() + objGrmida.getRifsnm());
					}else {
						objGrmcda = myDaoGrmcda.getUsingRyrmcn(ds, objGrmria.getRqrmcn().toString());
						if(objGrmcda != null) {
							adapted.setWSTITU(objGrmcda.getRycpcn());
						}
					}
				}
				adapted.setPkid(obj.getPkid());
				adapted.setMember(obj.getMember());
				adapted.setWSBRCH(obj.getHibrch());
				adapted.setWSSF4(obj.getHisf4());
				if (!obj.getHidate().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(obj.getHidate().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSDATE(adapted.getSFEAMD());
				if (!obj.getHifsel().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(obj.getHifsel().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSFSEL(adapted.getSFEAMD());
				adapted.setWSSTAT(obj.getHistat());
				adapted.setWSAAMT(obj.getHiaamt());
				adapted.setWSCBAL(obj.getHicbal());
				adapted.setWSIODL(obj.getHiiodl());
				adapted.setWSBRCH(obj.getHibrch());
				adapted.setWSSF4(obj.getHisf4());
				adapted.setWSCMCN(obj.getHicmcn());
				if (!obj.getHiamnd().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(obj.getHiamnd().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSAMND(adapted.getSFEAMD());
				adapted.setWSTYPE(obj.getHitype());
				adapted.setWSRESU(obj.getHiresu());
				if (obj.getHiresu().equals(""))
					adapted.setWSDRES("EN PROCESO");
				if (obj.getHiresu().equals("C"))
					adapted.setWSDRES("CANCELADO");
				if (obj.getHiresu().equals("R"))
					adapted.setWSDRES("RECHAZADO");
				if (obj.getHiresu().equals("A"))
					adapted.setWSDRES("APLICADO");
				adapted.setWSMREC(obj.getHimrec());
				if (!obj.getHifcan().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(obj.getHifcan().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSFCAN(adapted.getSFEAMD());
				if (!obj.getHifact().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(obj.getHifact().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSFACT(adapted.getSFEAMD());
				adapted.setWSSUML(obj.getHisuml());
				adapted.setWSDIEX(obj.getHidiex());
				adapted.setWSIMEX(obj.getHiimex());
				adapted.setWSMEXH(obj.getHimexh());
				if (!obj.getHinvt2().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(obj.getHinvt2().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSNVT2(adapted.getSFEAMD());
				adapted.setWSSTAN(obj.getHistan());
				adapted.setWSTYPN(obj.getHitypn());
				adapted.setWSNLIM(obj.getHinlim());
				if (!obj.getHinvto().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(obj.getHinvto().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSNVTO(adapted.getSFEAMD());
				if (!obj.getHisent().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(obj.getHisent().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSSENT(adapted.getSFEAMD());
				adapted.setWSETAP(obj.getHietap());
				if (!obj.getHiobse().equals("")) {
					adapted.setWSOBSE(obj.getHiobse());
					adapted.setAAOBSE(obj.getHiobse());
				}
				adapted.setWSBHDN(obj.getHibhdn());
				adapted.setWSBHMC(obj.getHibhmc());
				adapted.setWSBHVS(obj.getHibhvs());
				SubRutCarga(ds);
				listAdapted.add(adapted);		
			}
			
		} catch (ASException e) {
    		if( e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENEXPIRED_CODE || 
    				e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENMISSING_CODE) {
    			log.log(Level.INFO, e.getMessage());
    		} else {
    			log.log(Level.SEVERE, e.getMessage(), e);
    		}
    		return "ERROR";
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private void SubRutCarga(DataSet ds) {
		try {
			if (adapted.getWSSTAT().equals("1")) adapted.setWSDSTA("ACTIVA");
			if (adapted.getWSSTAT().equals("4")) adapted.setWSDSTA("CERRADA");
			if (adapted.getWSSTAT().equals("5")) adapted.setWSDSTA("DORMAN");
			if (adapted.getWSSTAT().equals("6")) adapted.setWSDSTA("NUEVA CUENTA");
			if (adapted.getWSSTAT().equals("A")) adapted.setWSDSTA("DOMICILIO DESC.");
			if (adapted.getWSSTAT().equals("B")) adapted.setWSDSTA("EMBARGADA");
			if (adapted.getWSSTAT().equals("C")) adapted.setWSDSTA("BLOQUEADA");
			if (adapted.getWSSTAT().equals("D")) adapted.setWSDSTA("CERRADA C/SDO");
			if (adapted.getWSSTAT().equals("E")) adapted.setWSDSTA("EN CIERRE");
			if (adapted.getWSSTAT().equals("N")) adapted.setWSDSTA("INACT NON ACCRU");
			if (adapted.getWSSTAT().equals("O")) adapted.setWSDSTA("INACT WRITE OFF");
			if (adapted.getWSSTAT().equals("P")) adapted.setWSDSTA("INACT PAST DUE");
			objCacpmre = myDaoCacpmre.getUsingMrmrec(ds, adapted.getWSMREC().toString());
			if (objCacpmre != null) {
				adapted.setWSDMRE(objCacpmre.getMrdesc());
			}
			if (adapted.getWSSTAN().equals("1")) adapted.setWSDSTN("ACTIVA");
			if (adapted.getWSSTAN().equals("4")) adapted.setWSDSTN("CERRADA");
			if (adapted.getWSSTAN().equals("5")) adapted.setWSDSTN("DORMAN");
			if (adapted.getWSSTAN().equals("6")) adapted.setWSDSTN("NUEVA CUENTA");
			if (adapted.getWSSTAN().equals("A")) adapted.setWSDSTN("DOMICILIO DESC.");
			if (adapted.getWSSTAN().equals("B")) adapted.setWSDSTN("EMBARGADA");
			if (adapted.getWSSTAN().equals("C")) adapted.setWSDSTN("BLOQUEADA");
			if (adapted.getWSSTAN().equals("D")) adapted.setWSDSTN("CERRADA C/SDO");
			if (adapted.getWSSTAN().equals("E")) adapted.setWSDSTN("EN CIERRE");
			if (adapted.getWSSTAN().equals("N")) adapted.setWSDSTN("INACT NON ACCRU");
			if (adapted.getWSSTAN().equals("O")) adapted.setWSDSTN("INACT WRITE OFF");
			if (adapted.getWSSTAN().equals("P")) adapted.setWSDSTN("INACT PAST DUE");
			if (adapted.getWSDTRE().equals("ACT")) {
				adapted.setWSTIPO("ACTUALIZACION");
			} else {
				adapted.setWSTIPO("renovacion");
			}
			if (adapted.getWSETAP().equals(10)) adapted.setWSETAD("SELECCION");
			if (adapted.getWSETAP().equals(15)) adapted.setWSETAD("SCORING");
			if (adapted.getWSETAP().equals(20)) adapted.setWSETAD("REN/ACT");
			if (adapted.getWSETAP().equals(30)) adapted.setWSETAD("APLICACION");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public class CACR210Adapter{
		private String pkid;
		private String member;
		private String WSDTRE;
		private String WSTITU;
		private Integer WSBRCH;
		private Integer WSSF4;
		private Date SFEAMD;
		private Date WSDATE;
		private Date WSFSEL;
		private String WSSTAT;
		private Long WSAAMT;
		private BigDecimal WSCBAL;
		private Long WSIODL;
		private Integer WSCMCN;
		private Date WSAMND;
		private Integer WSTYPE;
		private String WSRESU;
		private String WSDRES;
		private Integer WSMREC;
		private Date WSFCAN;
		private Date WSFACT;
		private Long WSSUML;
		private Integer WSDIEX;
		private BigDecimal WSIMEX;
		private Integer WSMEXH;
		private Date WSNVT2;
		private String WSSTAN;
		private Integer WSTYPN;
		private BigDecimal WSNLIM;
		private Date WSNVTO;
		private Date WSSENT;
		private Integer WSETAP;
		private String WSOBSE;
		private String AAOBSE;
		private BigDecimal WSBHDN;
		private BigDecimal WSBHMC;
		private BigDecimal WSBHVS;
		private String WSDSTA; 
		private String WSDMRE; 
		private String WSDSTN; 
		private String WSTIPO; 
		private String WSETAD; 	
		
		public CACR210Adapter() {}

		public String getPkid() {
			return pkid;
		}

		public void setPkid(String pkid) {
			this.pkid = pkid;
		}

		public String getMember() {
			return member;
		}

		public void setMember(String member) {
			this.member = member;
		}

		public String getWSDTRE() {
			return WSDTRE;
		}

		public void setWSDTRE(String wSDTRE) {
			WSDTRE = wSDTRE;
		}

		public String getWSTITU() {
			return WSTITU;
		}

		public void setWSTITU(String wSTITU) {
			WSTITU = wSTITU;
		}

		public Integer getWSBRCH() {
			return WSBRCH;
		}

		public void setWSBRCH(Integer wSBRCH) {
			WSBRCH = wSBRCH;
		}

		public Integer getWSSF4() {
			return WSSF4;
		}

		public void setWSSF4(Integer wSSF4) {
			WSSF4 = wSSF4;
		}

		public Date getWSDATE() {
			return WSDATE;
		}

		public void setWSDATE(Date wSFDATE) {
			WSDATE = wSFDATE;
		}

		public Date getWSFSEL() {
			return WSFSEL;
		}

		public void setWSFSEL(Date wSFSEL) {
			WSFSEL = wSFSEL;
		}

		public String getWSSTAT() {
			return WSSTAT;
		}

		public void setWSSTAT(String wSSTAT) {
			WSSTAT = wSSTAT;
		}

		public Long getWSAAMT() {
			return WSAAMT;
		}

		public void setWSAAMT(Long wSAAMT) {
			WSAAMT = wSAAMT;
		}

		public BigDecimal getWSCBAL() {
			return WSCBAL;
		}

		public void setWSCBAL(BigDecimal wSCBAL) {
			WSCBAL = wSCBAL;
		}

		public Long getWSIODL() {
			return WSIODL;
		}

		public void setWSIODL(Long wSIODL) {
			WSIODL = wSIODL;
		}

		public Integer getWSCMCN() {
			return WSCMCN;
		}

		public void setWSCMCN(Integer wSCMCN) {
			WSCMCN = wSCMCN;
		}

		public Date getWSAMND() {
			return WSAMND;
		}

		public void setWSAMND(Date wSAMND) {
			WSAMND = wSAMND;
		}

		public Integer getWSTYPE() {
			return WSTYPE;
		}

		public void setWSTYPE(Integer wSTYPE) {
			WSTYPE = wSTYPE;
		}

		public String getWSRESU() {
			return WSRESU;
		}

		public void setWSRESU(String wSRESU) {
			WSRESU = wSRESU;
		}

		public String getWSDRES() {
			return WSDRES;
		}

		public void setWSDRES(String wSDRES) {
			WSDRES = wSDRES;
		}

		public Integer getWSMREC() {
			return WSMREC;
		}

		public void setWSMREC(Integer wSMREC) {
			WSMREC = wSMREC;
		}

		public Date getWSFCAN() {
			return WSFCAN;
		}

		public void setWSFCAN(Date wSFCAN) {
			WSFCAN = wSFCAN;
		}

		public Date getWSFACT() {
			return WSFACT;
		}

		public void setWSFACT(Date wSFACT) {
			WSFACT = wSFACT;
		}

		public Long getWSSUML() {
			return WSSUML;
		}

		public void setWSSUML(Long wSSUML) {
			WSSUML = wSSUML;
		}

		public Integer getWSDIEX() {
			return WSDIEX;
		}

		public void setWSDIEX(Integer wSDIEX) {
			WSDIEX = wSDIEX;
		}

		public BigDecimal getWSIMEX() {
			return WSIMEX;
		}

		public void setWSIMEX(BigDecimal wSIMEX) {
			WSIMEX = wSIMEX;
		}

		public Integer getWSMEXH() {
			return WSMEXH;
		}

		public void setWSMEXH(Integer wSMEXH) {
			WSMEXH = wSMEXH;
		}

		public Date getWSNVT2() {
			return WSNVT2;
		}

		public void setWSNVT2(Date wSNVT2) {
			WSNVT2 = wSNVT2;
		}

		public String getWSSTAN() {
			return WSSTAN;
		}

		public void setWSSTAN(String wSSTAN) {
			WSSTAN = wSSTAN;
		}

		public Integer getWSTYPN() {
			return WSTYPN;
		}

		public void setWSTYPN(Integer wSTYPN) {
			WSTYPN = wSTYPN;
		}

		public BigDecimal getWSNLIM() {
			return WSNLIM;
		}

		public void setWSNLIM(BigDecimal wSNLIM) {
			WSNLIM = wSNLIM;
		}

		public Date getWSNVTO() {
			return WSNVTO;
		}

		public void setWSNVTO(Date wSNVTO) {
			WSNVTO = wSNVTO;
		}

		public Date getWSSENT() {
			return WSSENT;
		}

		public void setWSSENT(Date wSSENT) {
			WSSENT = wSSENT;
		}

		public Integer getWSETAP() {
			return WSETAP;
		}

		public void setWSETAP(Integer wSETAP) {
			WSETAP = wSETAP;
		}

		public String getWSOBSE() {
			return WSOBSE;
		}

		public void setWSOBSE(String wSOBSE) {
			WSOBSE = wSOBSE;
		}

		public BigDecimal getWSBHDN() {
			return WSBHDN;
		}

		public void setWSBHDN(BigDecimal wSBHDN) {
			WSBHDN = wSBHDN;
		}

		public BigDecimal getWSBHMC() {
			return WSBHMC;
		}

		public void setWSBHMC(BigDecimal wSBHMC) {
			WSBHMC = wSBHMC;
		}

		public BigDecimal getWSBHVS() {
			return WSBHVS;
		}

		public void setWSBHVS(BigDecimal wSBHVS) {
			WSBHVS = wSBHVS;
		}

		public String getWSDSTA() {
			return WSDSTA;
		}

		public void setWSDSTA(String wSDSTA) {
			WSDSTA = wSDSTA;
		}

		public String getWSDMRE() {
			return WSDMRE;
		}

		public void setWSDMRE(String wSDMRE) {
			WSDMRE = wSDMRE;
		}

		public String getWSDSTN() {
			return WSDSTN;
		}

		public void setWSDSTN(String wSDSTN) {
			WSDSTN = wSDSTN;
		}

		public String getWSTIPO() {
			return WSTIPO;
		}

		public void setWSTIPO(String wSTIPO) {
			WSTIPO = wSTIPO;
		}

		public String getWSETAD() {
			return WSETAD;
		}

		public void setWSETAD(String wSETAD) {
			WSETAD = wSETAD;
		}

		public Date getSFEAMD() {
			return SFEAMD;
		}

		public void setSFEAMD(Date sFEAMD) {
			SFEAMD = sFEAMD;
		}

		public String getAAOBSE() {
			return AAOBSE;
		}

		public void setAAOBSE(String aAOBSE) {
			AAOBSE = aAOBSE;
		}
		
	}
	
}
