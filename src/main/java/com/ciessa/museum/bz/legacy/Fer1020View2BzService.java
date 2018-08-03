package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.AltnamDAO;
import com.ciessa.museum.dao.legacy.SaldomDAO;
import com.ciessa.museum.dao.legacy.Tap002DAO;
import com.ciessa.museum.dao.legacy.Tap014DAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Altnam;
import com.ciessa.museum.model.legacy.Saldom;
import com.ciessa.museum.model.legacy.Tap002;
import com.ciessa.museum.model.legacy.Tap014;

public class FER1020View2BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(FER1020View2BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;

	@Autowired
	Tap002DAO myDaoTap002;
	
	@Autowired
	AltnamDAO myDaoAltnam;
	
	@Autowired
	SaldomDAO myDaoSaldom;
	
	@Autowired
	Tap014DAO myDaoTap014;

	Tap002 tap002 = new Tap002();
	Altnam altnam = new Altnam();
	Saldom saldom = new Saldom();
	Long wdias = new Long(0);

	// Get the output fields
	String[] fields = new String[] {
			"WCTA1",
			"NOMBRE",
			"DOMIC",
			"TELEF",
			"WDIAS",
			"DMCBAL",
			"DMCMCN"
	};

	// Get the output fields
	String[] arrayFields = new String[] {
			"DESTIP",
			"DOLIMA",
			"WSDATC",
			"WSAMND",
			"DOAGGI",
			"WSINTE"
	};
		
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			
			String wbas = obtainStringValue("wbas", null);
			String wcta = obtainStringValue("wcta", null);
			String waca = obtainStringValue("waca", null);
			String wpto = obtainStringValue("wpto", null);
			String wtod = obtainStringValue("wtod", null);
			String wvigd = obtainStringValue("wvigd", null);
			String wvigh = obtainStringValue("wvigh", null);
			double wimp = obtainDoubleValue("wimp",0d);
			
			Date fecjud = null;
			Date fecjuh = null;
			
			String rpta = SubRut001(ds, wcta, wbas, wvigd, wvigh, wtod, fecjud, fecjuh);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			else
			{
				ArrayList<Tap014> lstTap014 = SubRut007(ds, wcta, wbas, waca, wpto, wtod, fecjud, fecjuh, wimp);

				// Obtains the user JSON representation
				Fer1020Adapter adapter = new Fer1020Adapter(tap002, altnam, saldom, wdias);
				ArrayList<Fer1020SFLAdapter> sflAdapter = new ArrayList<FER1020View2BzService.Fer1020SFLAdapter>();
				for( Tap014 obj : lstTap014) {
					sflAdapter.add(new Fer1020SFLAdapter(obj));
				}
				
				returnValue = getJSONRepresentationFromObject(adapter, fields);
				returnValue.put("data", getJSONRepresentationFromArrayOfObjects(sflAdapter, arrayFields).get("data"));
			}
			
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
	
	private String SubRut001(DataSet ds, String wcta, String wbas, String wvigd, String wvigh, String wtod, Date fecjud, Date fecjuh) throws ParseException {
		try {
			if (wcta != null && wbas != null) {				
				if ((Integer.parseInt(wbas) == 0 && Integer.parseInt(wcta) == 0) || (Integer.parseInt(wbas) > 0 && Integer.parseInt(wcta) > 0)){
					return "Parametros Incorrectos";
				}
			}
			if (Integer.parseInt(wcta) > 0) {
				Tap002 obj = myDaoTap002.getUsingWcta(ds, wcta);
				if (obj == null)
					return "Cuenta Inexistente";
				else
					return "";
			}
			if (Integer.parseInt(wbas) > 0) {
				Tap002 obj = myDaoTap002.getUsingWbas(ds, wbas);
				if (obj == null)
					return "Numero de Base Inexistente";
				else
					return "";
			}
			if (Integer.parseInt(wvigd) > 0) {
				if (wtod == "X")
					return "Parametros Incorrectos";
				else {
					fecjud = format.parse(wvigd);
				}
			}
			if (Integer.parseInt(wvigh) > 0) {
				if (wtod == "X")
					return "Parametros Incorrectos";
				else
				{
					fecjuh = format.parse(wvigh);
				}
			}
		
		} catch (ASException e) {
			return e.getMessage();
		}
		return "";
	}
	
	private ArrayList<Tap014> SubRut007(DataSet ds, String wcta, String wbas, String waca, String wpto, String wtod, Date fecjud, Date fecjuh, double wimp) throws ParseException, ASException {
		Date dexaca;
		tap002 = myDaoTap002.getUsingWcta(ds, wcta);
		altnam = myDaoAltnam.getUsingWcta(ds, wcta);
		if (tap002.getDmcbal().intValue() < 0) {
			saldom = myDaoSaldom.getUsingWcta(ds, wcta);
			if (saldom != null) {
				if (saldom.getDexaca() > 0) {
					//TODO: Resolver
					dexaca = format.parse(saldom.getDexaca().toString());
					wdias = Calendar.getInstance().getTime().getTime() - dexaca.getTime();
				}
				else
				{
					wdias = (long)0;
				}
			}
			else
				wdias = (long)0;
		}
		else
			wdias = (long)0;
		//
		ArrayList<Tap014> lstTap014 = SubRut006(ds, wcta, wbas, waca, wpto, wtod, fecjud, fecjuh, wimp);
		return lstTap014;
	}
	
	private ArrayList<Tap014> SubRut006(DataSet ds, String wcta, String wbas, String waca, String wpto, String wtod, Date fecjud, Date fecjuh, double wimp) {
		ArrayList<Tap014> lstTap014 = null;
		try {
			if (StringUtils.hasText(wcta))
				lstTap014 = myDaoTap014.getUsingWcta(ds, wcta, waca, wpto, wtod, fecjud, fecjuh, wimp);
			if (StringUtils.hasText(wbas))
				lstTap014 = myDaoTap014.getUsingWbas(ds, wbas, waca, wpto, wtod, fecjud, fecjuh, wimp);
			return lstTap014;
		}catch (ASException e) {
			return null;
		}
	}
	
	public class Fer1020Adapter {
		
		private long WCTA1;
		private String NOMBRE;
		private String DOMIC;
		private String TELEF;
		private Long WDIAS;
		private BigDecimal DMCBAL;
		private int DMCMCN;
		
		public Fer1020Adapter(Tap002 src1, Altnam src2, Saldom src3, Long wdias) {
			this.WCTA1 = src1.getDmacct();
			this.NOMBRE = src2.getNamel1();
			this.DOMIC = src2.getAdres1();
			this.TELEF = src2.getCtelef();
			this.WDIAS = wdias;
			this.DMCBAL = src1.getDmcbal();
			this.DMCMCN = src1.getDmcmcn();
		}

		/**
		 * @return the wCTA1
		 */
		public long getWCTA1() {
			return WCTA1;
		}

		/**
		 * @param wCTA1 the wCTA1 to set
		 */
		public void setWCTA1(long wCTA1) {
			WCTA1 = wCTA1;
		}

		/**
		 * @return the nOMBRE
		 */
		public String getNOMBRE() {
			return NOMBRE;
		}

		/**
		 * @param nOMBRE the nOMBRE to set
		 */
		public void setNOMBRE(String nOMBRE) {
			NOMBRE = nOMBRE;
		}

		/**
		 * @return the dOMIC
		 */
		public String getDOMIC() {
			return DOMIC;
		}

		/**
		 * @param dOMIC the dOMIC to set
		 */
		public void setDOMIC(String dOMIC) {
			DOMIC = dOMIC;
		}

		/**
		 * @return the tELEF
		 */
		public String getTELEF() {
			return TELEF;
		}

		/**
		 * @param tELEF the tELEF to set
		 */
		public void setTELEF(String tELEF) {
			TELEF = tELEF;
		}

		/**
		 * @return the wDIAS
		 */
		public Long getWDIAS() {
			return WDIAS;
		}

		/**
		 * @param wDIAS the wDIAS to set
		 */
		public void setWDIAS(Long wDIAS) {
			WDIAS = wDIAS;
		}

		/**
		 * @return the dMCBAL
		 */
		public BigDecimal getDMCBAL() {
			return DMCBAL;
		}

		/**
		 * @param dMCBAL the dMCBAL to set
		 */
		public void setDMCBAL(BigDecimal dMCBAL) {
			DMCBAL = dMCBAL;
		}

		/**
		 * @return the dMCMCN
		 */
		public int getDMCMCN() {
			return DMCMCN;
		}

		/**
		 * @param dMCMCN the dMCMCN to set
		 */
		public void setDMCMCN(int dMCMCN) {
			DMCMCN = dMCMCN;
		}

	}

	public class Fer1020SFLAdapter {
		private String DESTIP;
		private Long DOLIMA;
		private Date WSDATC;
		private Date WSAMND;
		private BigDecimal DOAGGI;
		private BigDecimal WSINTE;
		
		public Fer1020SFLAdapter(Tap014 src/*, Saldom src2*/) {

			// TODO: Consultar si necesitamos el SALDOM en este registro
			try {
				this.WSDATC = new SimpleDateFormat("yyyyMMdd").parse(src.getDodatc().toString());
			} catch( Exception e ) {}
			if( src.getDmtodf().equals(1)) {
				this.DESTIP = "ACA";
			} else if( src.getDmtodf().equals(2)) {
				this.DESTIP = "PTO";
			} else if( src.getDmtodf().equals(9)) {
				this.DESTIP = "TOD";
//				try {
//					this.WSDATC = new SimpleDateFormat("yyyyMMDD").parse(src2.getDexaca().toString());
//				} catch( Exception e ) {}
			}
			this.DOLIMA = src.getDolima();
			this.DOAGGI = src.getDoaggi();

			try {
				this.WSAMND = new SimpleDateFormat("yyyyMMdd").parse(src.getDoamnd().toString());
			} catch( Exception e ) {}
			this.WSINTE = src.getDotacc();
		}

		/**
		 * @return the dESTIP
		 */
		public String getDESTIP() {
			return DESTIP;
		}

		/**
		 * @param dESTIP the dESTIP to set
		 */
		public void setDESTIP(String dESTIP) {
			DESTIP = dESTIP;
		}

		/**
		 * @return the dOLIDO
		 */
		public Long getDOLIMA() {
			return DOLIMA;
		}

		/**
		 * @param dOLIDO the dOLIDO to set
		 */
		public void setDOLIMA(Long dOLIMA) {
			DOLIMA = dOLIMA;
		}

		/**
		 * @return the wSDATC
		 */
		public Date getWSDATC() {
			return WSDATC;
		}

		/**
		 * @param wSDATC the wSDATC to set
		 */
		public void setWSDATC(Date wSDATC) {
			WSDATC = wSDATC;
		}

		/**
		 * @return the wSAMND
		 */
		public Date getWSAMND() {
			return WSAMND;
		}

		/**
		 * @param wSAMND the wSAMND to set
		 */
		public void setWSAMND(Date wSAMND) {
			WSAMND = wSAMND;
		}

		/**
		 * @return the dOAGGI
		 */
		public BigDecimal getDOAGGI() {
			return DOAGGI;
		}

		/**
		 * @param dOAGGI the dOAGGI to set
		 */
		public void setDOAGGI(BigDecimal dOAGGI) {
			DOAGGI = dOAGGI;
		}

		/**
		 * @return the wSINTE
		 */
		public BigDecimal getWSINTE() {
			return WSINTE;
		}

		/**
		 * @param wSINTE the wSINTE to set
		 */
		public void setWSINTE(BigDecimal wSINTE) {
			WSINTE = wSINTE;
		}

	}
}
