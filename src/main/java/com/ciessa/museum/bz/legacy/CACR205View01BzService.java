package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
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
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;

import com.ciessa.museum.model.legacy.Cacphst;
import com.ciessa.museum.model.legacy.Grmria;
import com.ciessa.museum.tools.CollectionFactory;
import com.ciessa.museum.dao.legacy.CacphstDAO;
import com.ciessa.museum.dao.legacy.GrmriaDAO;
import com.ciessa.museum.model.legacy.Grmida;
import com.ciessa.museum.dao.legacy.GrmidaDAO;
import com.ciessa.museum.model.legacy.Grmcda;
import com.ciessa.museum.dao.legacy.GrmcdaDAO;
import com.ciessa.museum.model.legacy.Cacpmre;
import com.ciessa.museum.dao.legacy.CacpmreDAO;


public class CACR205View01BzService extends RestBaseServerResource {

	public static final Logger log = Logger.getLogger(CACR205View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CacphstDAO myDaoCacphst;
	
	@Autowired
	GrmriaDAO myDaoGrmria;
	
	@Autowired
	GrmidaDAO myDaoGrmida; 
	
	@Autowired
	GrmcdaDAO myDaoGrmcda;
	
	@Autowired
	CacpmreDAO myDaoCacpmre;
	
	//Entity
	Cacphst ObjCacphst	= new Cacphst();
	Grmria ObjGrmria	= new Grmria();
	Grmida ObjGrmida	= new Grmida();
	Grmcda ObjGrmcda 	= new Grmcda();
	Cacpmre ObjCacpmre 	= new Cacpmre();
	
	List<Cacphst> listCacphst = null;

	
	//Variables globales
	String wsacct = null;
	String clave = null;

	List<CARC205Adapter> list = new ArrayList<CARC205Adapter>();
	CARC205Adapter adapted = null;
	
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
			
			//Parametros Get
			wsacct = obtainStringValue("wsacct", null);
			
			String rpta = SubRutVal(ds);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			if (rpta.equals(""))
			{
				rpta = SubRutCarsf1(ds);
				if (!rpta.equals(""))
				{
					log.log(Level.SEVERE, rpta, new Exception());
					return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
				}	
			}
			
			// retrieve all elements
			Map<String,String> attributes = CollectionFactory.createMap();
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found [" + list.size() + "] in " + diff + " millis");
			
			String[] fields = new String[] {
					"clave",
					"member",
					"WSDTRE",
					"WSDATE",
					"WSAMND",
					"WSAAMT",
					"WSNLIM",
					"WSNVTO",
					"WSDRES"
			};
			
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
			returnValue.put("WSACCT", wsacct);
			returnValue.put("WSTITU", list.get(0).getWSTITU());
			
			if( attributes.containsKey("recordCount"))
				returnValue.put("recordCount", Long.valueOf(attributes.get("recordCount")));
			else 
				returnValue.put("recordCount", list.size());
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
	
	
	private String SubRutVal(DataSet ds) {
		//TODO: Se agrega esta validacion para el parametro de ingreso
		if (wsacct == null) {
			return "Parametros Incorrectos";
		}
		if (wsacct.equals("0")) {
			return "Parámetros incorrectos";
		}
		return "";
	}
	
	private String SubRutCarsf1(DataSet ds) {
		try {
			listCacphst = myDaoCacphst.getUsingHiacct(ds, wsacct);
			for( Cacphst o : listCacphst ) {
				adapted = new CARC205Adapter();
				if ( o.getHitreg().equals("1")) {
					adapted.setWSDTRE("ACT");
				}
				else
				{
					adapted.setWSDTRE("REN");
				}
				ObjGrmria = myDaoGrmria.getUsingRqactn(ds, wsacct);
				if (ObjGrmria != null) {
					ObjGrmida = myDaoGrmida.getUsingRirmcn(ds, ObjGrmria.getRqrmcn().toString());
					if (ObjGrmida != null) {
						adapted.setWSTITU(ObjGrmida.getRilsnm() + ObjGrmida.getRifsnm());
					}
					else {
						ObjGrmcda = myDaoGrmcda.getUsingRyrmcn(ds, ObjGrmria.getRqrmcn().toString());
						if (ObjGrmcda != null) {
							adapted.setWSTITU(ObjGrmcda.getRycpcn());
						}
					}
				}
				adapted.setClave(o.getHiacct().toString() + o.getHitodf().toString() + o.getHidate().toString() + o.getHitie().toString() + o.getHifsel());
				adapted.setWSBRCH(o.getHibrch()); //TODO: ASGINACIÓN DUPLICADA EN LA LINEA 199
				adapted.setWSSF4(o.getHisf4()); //TODO: ASGINACIÓN DUPLICADA EN LA LINEA 200
				adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(o.getHidate().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSDATE(adapted.getSFEAMD());
				adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(o.getHifsel().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSFSEL(adapted.getSFEAMD());
				adapted.setWSSTAT(o.getHistat());
				adapted.setWSAAMT(o.getHiaamt());
				adapted.setWSCBAL(o.getHicbal().doubleValue());
				adapted.setWSIODL(o.getHiiodl());
				adapted.setWSBRCH(o.getHibrch());  //TODO: ASGINACIÓN DUPLICADA EN LA LINEA 187
				adapted.setWSSF4(o.getHisf4()); //TODO: ASGINACIÓN DUPLICADA EN LA LINEA 188
				adapted.setWSCMCN(o.getHicmcn());
				adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(o.getHiamnd().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSAMND(adapted.getSFEAMD());
				adapted.setWSTYPE(o.getHitype());
				adapted.setWSRESU(o.getHiresu());
				if(o.getHiresu().equals("")) 
					adapted.setWSDRES("EN PROCESO");
				if(o.getHiresu().equals("C"))
					adapted.setWSDRES("CANCELADO");
				if(o.getHiresu().equals("R")) 
					adapted.setWSDRES("RECHAZADO");
				if(o.getHiresu().equals("A"))
					adapted.setWSDRES("APLICADO");
				adapted.setWSMREC(o.getHimrec());
				if (!o.getHifcan().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(o.getHifcan().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSFCAN(adapted.getSFEAMD());
				if (!o.getHifact().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(o.getHifact().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSFACT(adapted.getSFEAMD());
				adapted.setWSSUML(o.getHisuml());
				adapted.setWSDIEX(o.getHidiex());
				adapted.setWSIMEX(o.getHiimex());
				adapted.setWSMEXH(o.getHimexh());
				if (!o.getHinvt2().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(o.getHinvt2().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSNVT2(adapted.getSFEAMD());
				adapted.setWSSTAN(o.getHistan());
				adapted.setWSTYPN(o.getHitypn());
				adapted.setWSNLIM(o.getHinlim());
				if (!o.getHinvto().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(o.getHinvto().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSNVTO(adapted.getSFEAMD());
				if (!o.getHisent().equals(0)) adapted.setSFEAMD(new SimpleDateFormat("yyyyMMdd").parse(o.getHisent().toString()));
				//TODO: Ejecutar rutina FECHA
				adapted.setWSSENT(adapted.getSFEAMD());
				adapted.setWSETAP(o.getHietap());
				if (!o.getHiobse().equals("")) {
					adapted.setWSOBSE(o.getHiobse());
					adapted.setaaOBSE(o.getHiobse());
				}
				adapted.setWSBHDN(o.getHibhdn());
				adapted.setWSBHMC(o.getHibhmc());
				adapted.setWSBHVS(o.getHibhvs());				
				SubRutCarga(ds);
				list.add(adapted);
			}
			return "";
		}
		catch (ASException e) {
			return e.getMessage();
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}

	private String SubRutCarga(DataSet ds) {
		try {
			if( adapted.getWSSTAT().equals("1")) adapted.setWSDSTA("ACTIVA");
			if( adapted.getWSSTAT().equals("4")) adapted.setWSDSTA("CERRADA");
			if( adapted.getWSSTAT().equals("5")) adapted.setWSDSTA("DORMAN");
			if( adapted.getWSSTAT().equals("6")) adapted.setWSDSTA("NUEVA CUENTA");
			if( adapted.getWSSTAT().equals("A")) adapted.setWSDSTA("DOMICILIO DESC.");
			if( adapted.getWSSTAT().equals("B")) adapted.setWSDSTA("EMBARGADA");
			if( adapted.getWSSTAT().equals("C")) adapted.setWSDSTA("BLOQUEADA");
			if( adapted.getWSSTAT().equals("D")) adapted.setWSDSTA("CERRADA C/SDO.");
			if( adapted.getWSSTAT().equals("E")) adapted.setWSDSTA("EN CIERRE");
			if( adapted.getWSSTAT().equals("N")) adapted.setWSDSTA("INACT NON ACCRU");
			if( adapted.getWSSTAT().equals("O")) adapted.setWSDSTA("INACT WRITE OFF");
			if( adapted.getWSSTAT().equals("P")) adapted.setWSDSTA("INACT PAST DUE");
			ObjCacpmre = myDaoCacpmre.getUsingMrmrec(ds, adapted.getWSMREC().toString());
			if(ObjCacpmre != null) {
				adapted.setWSDMRE(ObjCacpmre.getMrdesc()); 
			}
			if(adapted.getWSSTAN().equals("1"))	adapted.setWSDSTN("ACTIVA");
			if(adapted.getWSSTAN().equals("4"))	adapted.setWSDSTN("CERRADA");
			if(adapted.getWSSTAN().equals("5"))	adapted.setWSDSTN("DORMAN");
			if(adapted.getWSSTAN().equals("6"))	adapted.setWSDSTN("NUEVA CUENTA");
			if(adapted.getWSSTAN().equals("A"))	adapted.setWSDSTN("DOMICILIO DESC.");
			if(adapted.getWSSTAN().equals("B"))	adapted.setWSDSTN("EMBARGADA");
			if(adapted.getWSSTAN().equals("C")) adapted.setWSDSTN("BLOQUEADA");
			if(adapted.getWSSTAN().equals("D"))	adapted.setWSDSTN("CERRADA C/SDO.");
			if(adapted.getWSSTAN().equals("E"))	adapted.setWSDSTN("EN CIERRE");
			if(adapted.getWSSTAN().equals("N"))	adapted.setWSDSTN("INACT NON ACCRU");
			if(adapted.getWSSTAN().equals("O"))	adapted.setWSDSTN("INACT WRITE OFF");
			if(adapted.getWSSTAN().equals("P"))	adapted.setWSDSTN("INACT PAST DUE");
			if(adapted.getWSDTRE().equals("ACT")) {				
				adapted.setWSTIPO("ACTUALIZACIÓN");
			}else {
				adapted.setWSTIPO("RENOVACIÓN");
			}
			if(adapted.getWSETAP().equals(10)) adapted.setWSETAD("SELECCIÓN");
			if(adapted.getWSETAP().equals(15)) adapted.setWSETAD("SCORING");
			if(adapted.getWSETAP().equals(20)) adapted.setWSETAD("REN/ACT");
			if(adapted.getWSETAP().equals(30)) adapted.setWSETAD("APLICACION");
		}
		catch (ASException e) {
			return e.getMessage();
		}
		return"";
	}
	
	
	public class CARC205Adapter {
		String clave = null;
		String WSACCT = null;
		String WSTITU = null;
		String WOPC  = null;; 
		String WSDTRE = null;
		Date WSDATE = null;
		Date WSAMND = null; 
		Long  WSAAMT = null; 
		String WNLIM = null; 
		String WNVTO = null;  
		String WSDRES = null;
		Integer WSMREC = null;
		Date WSFCAN =  null;
		Date WSFACT = null;
		Long WSSUML = null;
		Integer WSDIEX = null;
		BigDecimal WSIMEX = null;
		Integer WSMEXH = null;
		Date WSNVT2 = null;
		Integer WSTYPN = null;
		BigDecimal WSNLIM = null;
		Date WSNVTO = null;
		Date WSSENT = null;
		Integer WSBRCH = null;										
		Integer WSSF4 = null;
		Date SFEAMD= null;
		Date WSFSEL = null; 
		String WSSTAT = null;
		String WSSTAN = null;
		Double WSCBAL = null;  
		Long WSIODL = null;
		Integer WSCMCN = null;  
		Integer WSTYPE = null;		
		String WSRESU = null;
		String WSOBSE = null;
		String aaOBSE = null;
		BigDecimal WSBHDN = null;
		BigDecimal WSBHMC = null;
		BigDecimal WSBHVS = null;
		String WSDSTA = null;
		String WSDMRE = null;
		String WSDSTN = null;	
		String WSTIPO = null;
		String WSETAD = null;
		Integer WSETAP = null;
		
		public CARC205Adapter() {
			
		}
		
		public String getClave() {
			return clave;
		}

		public void setClave(String clave) {
			this.clave = clave;
		}

		public Date getWSSENT() {
			return WSSENT;
		}

		public void setWSSENT(Date wSSENT) {
			WSSENT = wSSENT;
		}

		public Date getWSNVTO() {
			return WSNVTO;
		}

		public void setWSNVTO(Date wSNVTO) {
			WSNVTO = wSNVTO;
		}

		public BigDecimal getWSNLIM() {
			return WSNLIM;
		}

		public void setWSNLIM(BigDecimal wSNLIM) {
			WSNLIM = wSNLIM;
		}

		public Integer getWSTYPN() {
			return WSTYPN;
		}

		public void setWSTYPN(Integer wSTYPN) {
			WSTYPN = wSTYPN;
		}

		public Date getWSNVT2() {
			return WSNVT2;
		}

		public void setWSNVT2(Date wSNVT2) {
			WSNVT2 = wSNVT2;
		}

		public Integer getWSMEXH() {
			return WSMEXH;
		}

		public void setWSMEXH(Integer wSMEXH) {
			WSMEXH = wSMEXH;
		}

		public BigDecimal getWSIMEX() {
			return WSIMEX;
		}

		public void setWSIMEX(BigDecimal wSIMEX) {
			WSIMEX = wSIMEX;
		}

		public Integer getWSDIEX() {
			return WSDIEX;
		}

		public void setWSDIEX(Integer wSDIEX) {
			WSDIEX = wSDIEX;
		}

		public Long getWSSUML() {
			return WSSUML;
		}

		public void setWSSUML(Long wSSUML) {
			WSSUML = wSSUML;
		}

		public Date getWSFACT() {
			return WSFACT;
		}

		public void setWSFACT(Date wSFACT) {
			WSFACT = wSFACT;
		}

		public Date getWSFCAN() {
			return WSFCAN;
		}

		public void setWSFCAN(Date wSFCAN) {
			WSFCAN = wSFCAN;
		}

		public Integer getWSMREC() {
			return WSMREC;
		}

		public void setWSMREC(Integer wSMREC) {
			WSMREC = wSMREC;
		}

		public Date getWSDATE() {
			return WSDATE;
		}

		public void setWSDATE(Date wSDATE) {
			WSDATE = wSDATE;
		}

		public String getAaOBSE() {
			return aaOBSE;
		}

		public void setAaOBSE(String aaOBSE) {
			this.aaOBSE = aaOBSE;
		}

		public Integer getWSETAP() {
			return WSETAP;
		}

		public void setWSETAP(Integer wSETAP) {
			WSETAP = wSETAP;
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

		public String getWSSTAN() {
			return WSSTAN;
		}

		public void setWSSTAN(String wSSTAN) {
			WSSTAN = wSSTAN;
		}

		public String getWSDSTN() {
			return WSDSTN;
		}

		public void setWSDSTN(String wSDSTN) {
			WSDSTN = wSDSTN;
		}

		public String getWSDMRE() {
			return WSDMRE;
		}

		public void setWSDMRE(String wSDMRE) {
			WSDMRE = wSDMRE;
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

		public String getaaOBSE() {
			return aaOBSE;
		}

		public void setaaOBSE(String aaOBSE) {
			this.aaOBSE = aaOBSE;
		}

		public String getWSOBSE() {
			return WSOBSE;
		}

		public void setWSOBSE(String wSOBSE) {
			WSOBSE = wSOBSE;
		}
		
		public String getWSDRES() {
			return WSDRES;
		}

		public void setWSDRES(String wSDRES) {
			WSDRES = wSDRES;
		}

		public String getWSACCT() {
			return WSACCT;
		}

		public void setWSACCT(String wSACCT) {
			WSACCT = wSACCT;
		}

		public String getWSTITU() {
			return WSTITU;
		}

		public void setWSTITU(String wSTITU) {
			WSTITU = wSTITU;
		}

		public String getWOPC() {
			return WOPC;
		}

		public void setWOPC(String wOPC) {
			WOPC = wOPC;
		}

		public String getWSDTRE() {
			return WSDTRE;
		}

		public void setWSDTRE(String wSDTRE) {
			WSDTRE = wSDTRE;
		}

		public Date getWSAMND() {
			return WSAMND;
		}

		public void setWSAMND(Date wSAMND) {
			WSAMND = wSAMND;
		}

		public Long getWSAAMT() {
			return WSAAMT;
		}

		public void setWSAAMT(Long wSAAMT) {
			WSAAMT = wSAAMT;
		}

		public String getWNLIM() {
			return WNLIM;
		}

		public void setWNLIM(String wNLIM) {
			WNLIM = wNLIM;
		}

		public String getWNVTO() {
			return WNVTO;
		}

		public void setWNVTO(String wNVTO) {
			WNVTO = wNVTO;
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

		public Date getSFEAMD() {
			return SFEAMD;
		}

		public void setSFEAMD(Date sFEAMD) {
			SFEAMD = sFEAMD;
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

		public Double getWSCBAL() {
			return WSCBAL;
		}

		public void setWSCBAL(Double wSCBAL) {
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

		public String getWSDSTA() {
			return WSDSTA;
		}

		public void setWSDSTA(String wSDSTA) {
			WSDSTA = wSDSTA;
		}
		
	}

}