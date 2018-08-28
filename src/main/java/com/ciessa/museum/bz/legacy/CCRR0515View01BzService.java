package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
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
import com.ciessa.museum.model.legacy.Ccrpcre;
import com.ciessa.museum.dao.legacy.CcrpcreDAO;
import com.ciessa.museum.model.legacy.Grmida;
import com.ciessa.museum.tools.Range;
import com.ciessa.museum.dao.legacy.GrmidaDAO;
import com.ciessa.museum.model.legacy.Grmcda;
import com.ciessa.museum.dao.legacy.GrmcdaDAO;

import com.ciessa.museum.model.legacy.Ccrpcpv;
import com.ciessa.museum.dao.legacy.CcrpcpvDAO;


public class CCRR0515View01BzService extends RestBaseServerResource {
	
public static final Logger log = Logger.getLogger(CACR205View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CcrpcreDAO myDaoCcrpcre;
	
	@Autowired
	GrmidaDAO myDaoGrmida;
	
	@Autowired
	GrmcdaDAO myDaoGrmcda;
	
	@Autowired
	CcrpcpvDAO myDaoCcrpcpv;
	
	//Entity
	Ccrpcre ObjCcrpcre	= new Ccrpcre();
	Grmida ObjGrmida	= new Grmida();
	Grmcda ObjGrmcda	= new Grmcda();
	Ccrpcpv ObjCcrpcpv  = new Ccrpcpv();
	
	//Variables_globales
	String npres = null;
	String ncuot = null;
	String codre = null;
	Integer crnucl = null;
	
	String WDNUC1 = null;
	String WDCLIE = null;
	String WDCUCO = null;
	
	private BigDecimal WDIMPP = new BigDecimal(0);
	private BigDecimal WDIMPC = new BigDecimal(0);
	private BigDecimal WDIMFE = new BigDecimal(0);
	private BigDecimal WDIMDE = new BigDecimal(0);
	private BigDecimal WDIIVA = new BigDecimal(0);
	private BigDecimal WDITOT = new BigDecimal(0);
	private BigDecimal WTIPUN = new BigDecimal(0);
	private BigDecimal WTICOM = new BigDecimal(0);
	private BigDecimal WTFEEC = new BigDecimal(0);
	private BigDecimal WTIEND = new BigDecimal(0);
	private BigDecimal WTIIVA = new BigDecimal(0);
	String WTSBTP = "";
	Integer WDCUOT = 0;
	String WDCFEZ = "";
	Long WDDIAT = new Long(0);
	private BigDecimal WDTACR = new BigDecimal(0);
	private BigDecimal WDIMP1 = new BigDecimal(0);
	private BigDecimal WDIMC1 = new BigDecimal(0);
	private BigDecimal WDIMF1 = new BigDecimal(0);
	private BigDecimal WDIMD1 = new BigDecimal(0);
	private BigDecimal WDIIV1 = new BigDecimal(0);
	private BigDecimal WDITO1 = new BigDecimal(0);
	
	List<Ccrpcpv> listCcrpcpv  = null;

	List<CCRR0515Adapter> list = new ArrayList<CCRR0515Adapter>();
	CCRR0515Adapter adapted = null;
	
	
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
			npres = obtainStringValue("npres", null);
			ncuot = obtainStringValue("ncuot", null);
			codre = obtainStringValue("codre", null);
			
			// get range, if not defined use default value
			// Range range = this.obtainRange();
			Range range = null;
			this.WDNUC1 = npres;
			this.WDCUCO = ncuot;
			String rpta = SubRutProini(ds, npres, range);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			
			long diff = new Date().getTime() - millisPre;
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
					"WDCUOT",
					"WDCFEZ",
					"WDDIAT",
					"WDTACR",
					"WDIMP1",
					"WDIMC1",
					"WDIMF1",
					"WDIMD1",
					"WDIIV1",
					"WDITO1"
			};
			
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
			returnValue.put("WDNUC1", WDNUC1);
			returnValue.put("WDCLIE", WDCLIE);
			returnValue.put("WDCUCO", WDCUCO);
			returnValue.put("WTIEND", WTIEND);
			returnValue.put("WTIPUN", WTIPUN);
			returnValue.put("WTFEEC", WTFEEC);
			returnValue.put("WTICOM", WTICOM);
			returnValue.put("WTIIVA", WTIIVA);
			returnValue.put("WTSBTP", WTSBTP);
			
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
		
	} //Fin @Get

	private String SubRutProini(DataSet ds, String npres, Range range) {
		try {
			ObjCcrpcre = myDaoCcrpcre.getUsingNpres(ds, npres);
			if (ObjCcrpcre == null) {
				crnucl = 0;
				}
			else {
				ObjGrmida = myDaoGrmida.getUsingCrnucl(ds, ObjCcrpcre.getCrnucl().toString());
				if (ObjGrmida != null) {
					this.WDCLIE = ObjGrmida.getRilsnm() +" "+ ObjGrmida.getRifsnm();
				}
			else {
				ObjGrmcda = myDaoGrmcda.getUsingCrnucl(ds, ObjCcrpcre.getCrnucl().toString());
				if(ObjGrmcda != null) {
					this.WDCLIE = ObjGrmcda.getRycpcn();
					}
				}
			SubRutCarsfl(ds, npres, ncuot, range);
			}
		}// Fin Try
		
		catch (ASException e) {
			return e.getMessage();
			
		} // Fin Catch
		
		return "";
	}
	
	private String SubRutCarsfl(DataSet ds, String npres, String ncuot, Range range) {
		try {
			listCcrpcpv = myDaoCcrpcpv.getUsingnNpresAndNcuotToList(ds, npres, ncuot, range);
			for( Ccrpcpv o : listCcrpcpv ) {
				this.WDIMPP = o.getCppuni();
				this.WDIMPC = o.getCpcomp();
				this.WDIMFE = o.getCpfeco();
				this.WDIMDE = o.getCpimen();				
				this.WDIIVA = o.getCpiva1().add(o.getCpiva2());
				this.WDITOT = o.getCppuni().add(o.getCpcomp().add(o.getCpfeco().add(o.getCpimen().add(this.WDIIVA))));
				this.WTIPUN = this.WTIPUN.add(this.WDIMPP);
				this.WTICOM = this.WTICOM.add(this.WDIMPC);
				this.WTFEEC = this.WTFEEC.add(this.WDIMFE);
				this.WTIEND = this.WTIEND.add(this.WDIMDE);
				this.WTIIVA = this.WTIIVA.add(this.WDIIVA);
				this.WTSBTP = this.WTSBTP + o.getCpsbtp();
				this.WDCUOT = this.WDCUOT + o.getCpcuat();
				this.WDCFEZ = this.WDCFEZ + "" + o.getCpcfez();
				this.WDDIAT = this.WDDIAT + o.getCpdiat();
				this.WDTACR = this.WDTACR.add(o.getCptacr());
				this.WDIMP1 = this.WDIMP1.add(o.getCppuni());
				this.WDIMC1 = this.WDIMC1.add(o.getCpcomp());
				this.WDIMF1 = this.WDIMF1.add(o.getCpfeco());
				this.WDIMD1 = this.WDIMD1.add(o.getCpimen());
				this.WDIIV1 = this.WDIIV1.add(this.WDIIVA);
				this.WDITO1 = this.WDITO1.add(this.WDITOT);
				
				adapted = new CCRR0515Adapter();
				adapted.setWDIMPP(this.WDIMPP.toString());
				adapted.setWDIMPC(this.WDIMPC.toString());
				adapted.setWDIMFE(this.WDIMFE.toString());
				adapted.setWDIMDE(this.WDIMDE.toString());
				adapted.setWDIIVA(this.WDIIVA.toString());
				adapted.setWDITOT(this.WDITOT.toString());
				adapted.setWDCUOT(this.WDCUOT.toString());
				adapted.setWDCFEZ(this.WDCFEZ.toString());
				adapted.setWDDIAT(this.WDDIAT.toString());
				adapted.setWDTACR(this.WDTACR.toString());
				adapted.setWDIMP1(this.WDIMP1.toString());
				adapted.setWDIMC1(this.WDIMC1.toString());
				adapted.setWDIMF1(this.WDIMF1.toString());
				adapted.setWDIMD1(this.WDIMD1.toString());
				adapted.setWDIIV1(this.WDIIV1.toString());
				adapted.setWDITO1(this.WDITO1.toString());
				list.add(adapted);
			}// Fin for
		}// Fin Try
		catch (ASException e) {
			return e.getMessage();
			
		} // Fin Catch
		
		return "";
	}
	
	public class CCRR0515Adapter {
		String WDCUOT = null;
		String WDCFEZ = null;
		String WDDIAT = null;
		String WDTACR = null;
		String WDIMP1 = null;
		String WDIMC1 = null;
		String WDIMF1 = null;
		String WDIMD1 = null;
		String WDIIV1 = null;
		String WDITO1 = null;
		
		String WDIMPP = null;
		String WDIMPC = null;
		String WDIMFE = null;
		String WDIMDE = null;
		String WDIIVA = null;
		String WDITOT = null;
		
		
		public CCRR0515Adapter() {
			
		}


		public String getWDCUOT() {
			return WDCUOT;
		}


		public void setWDCUOT(String wDCUOT) {
			WDCUOT = wDCUOT;
		}


		public String getWDCFEZ() {
			return WDCFEZ;
		}


		public void setWDCFEZ(String wDCFEZ) {
			WDCFEZ = wDCFEZ;
		}


		public String getWDDIAT() {
			return WDDIAT;
		}


		public void setWDDIAT(String wDDIAT) {
			WDDIAT = wDDIAT;
		}


		public String getWDTACR() {
			return WDTACR;
		}


		public void setWDTACR(String wDTACR) {
			WDTACR = wDTACR;
		}


		public String getWDIMP1() {
			return WDIMP1;
		}


		public void setWDIMP1(String wDIMP1) {
			WDIMP1 = wDIMP1;
		}


		public String getWDIMC1() {
			return WDIMC1;
		}


		public void setWDIMC1(String wDIMC1) {
			WDIMC1 = wDIMC1;
		}


		public String getWDIMF1() {
			return WDIMF1;
		}


		public void setWDIMF1(String wDIMF1) {
			WDIMF1 = wDIMF1;
		}


		public String getWDIMD1() {
			return WDIMD1;
		}


		public void setWDIMD1(String wDIMD1) {
			WDIMD1 = wDIMD1;
		}


		public String getWDIIV1() {
			return WDIIV1;
		}


		public void setWDIIV1(String wDIIV1) {
			WDIIV1 = wDIIV1;
		}


		public String getWDITO1() {
			return WDITO1;
		}


		public void setWDITO1(String wDITO1) {
			WDITO1 = wDITO1;
		}


		public String getWDIMPP() {
			return WDIMPP;
		}


		public void setWDIMPP(String wDIMPP) {
			WDIMPP = wDIMPP;
		}


		public String getWDIMPC() {
			return WDIMPC;
		}


		public void setWDIMPC(String wDIMPC) {
			WDIMPC = wDIMPC;
		}


		public String getWDIMFE() {
			return WDIMFE;
		}


		public void setWDIMFE(String wDIMFE) {
			WDIMFE = wDIMFE;
		}


		public String getWDIMDE() {
			return WDIMDE;
		}


		public void setWDIMDE(String wDIMDE) {
			WDIMDE = wDIMDE;
		}


		public String getWDIIVA() {
			return WDIIVA;
		}


		public void setWDIIVA(String wDIIVA) {
			WDIIVA = wDIIVA;
		}


		public String getWDITOT() {
			return WDITOT;
		}


		public void setWDITOT(String wDITOT) {
			WDITOT = wDITOT;
		}
		
		
	} //Fin CCRR0515Adapter
	

} // Fin CCRR0515View01BzService
