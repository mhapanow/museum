package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

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
import com.ciessa.museum.tools.Range;

public class FER1020View3BzService extends RestBaseServerResource{
	
	public static final Logger log = Logger.getLogger(FER1020View3BzService.class.getName());
	
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
	
	Date fecjud = null;
	Date fecjuh = null;
	
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
			"WCTA1",
			"DESTIP",
			"DOLIMA",
			"DMCMCN",
			"WSDATC",
			"WSAMND",
			"WSINTE"
	};
		
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	String wbas;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			
			wbas = obtainStringValue("wbas", null);
			String wcta = obtainStringValue("wcta", null);
			String waca = obtainStringValue("waca", null);
			String wpto = obtainStringValue("wpto", null);
			String wtod = obtainStringValue("wtod", null);
			String wvigd = obtainStringValue("wvigd", null);
			String wvigh = obtainStringValue("wvigh", null);
			double wimp = obtainDoubleValue("wimp",0d);
			
			// get range, if not defined use default value
			// Range range = this.obtainRange();
			Range range = null;
			
			
			
			String rpta = SubRut001(ds, wcta, wbas, wvigd, wvigh, wtod);
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			else
			{
				ArrayList<Tap014> lstTap014 = SubRut006(ds, wcta, wbas, waca, wpto, wtod, fecjud, fecjuh, wimp, range);

				// Obtains the user JSON representation
				Fer1020Adapter adapter = new Fer1020Adapter(wbas);
				ArrayList<Fer1020SFLAdapter> sflAdapter = new ArrayList<FER1020View3BzService.Fer1020SFLAdapter>();
				if (lstTap014 != null) {
					for( Tap014 obj : lstTap014) {
						sflAdapter.add(new Fer1020SFLAdapter(obj));
					}
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
	
	private String SubRut001(DataSet ds, String wcta, String wbas, String wvigd, String wvigh, String wtod) throws ParseException {
		try {
			if (wcta != null && wbas != null) {				
				if ((Integer.parseInt(wbas) == 0 && Integer.parseInt(wcta) == 0) || (Integer.parseInt(wbas) > 0 && Integer.parseInt(wcta) > 0)){
					return "Parametros Incorrectos";
				}
			}
			if (wcta != null)
			{
				if (Integer.parseInt(wcta) > 0) {
					Tap002 obj = myDaoTap002.getUsingWcta(ds, wcta);
					if (obj == null)
						return "Cuenta Inexistente";
				}
			}
			if (wbas != null) {
				if (Integer.parseInt(wbas) > 0) {
					Tap002 obj = myDaoTap002.getUsingWbas(ds, wbas);
					if (obj == null)
						return "Numero de Base Inexistente";
				}	
			}
			if (Integer.parseInt(wvigd) > 0) {
				if (wtod == "X")
					return "Parametros Incorrectos";
				else {
					//fecjud = format.parse(wvigd);
					fecjud = new SimpleDateFormat("ddMMyyyy").parse(wvigd.toString());
				}
			}
			if (Integer.parseInt(wvigh) > 0) {
				if (wtod == "X")
					return "Parametros Incorrectos";
				else
				{
					// fecjuh = format.parse(wvigh);
					fecjuh = new SimpleDateFormat("ddMMyyyy").parse(wvigh.toString());
				}
			}
		
		} catch (ASException e) {
			return e.getMessage();
		}
		return "";
	}
	
	private ArrayList<Tap014> SubRut006(DataSet ds, String wcta, String wbas, String waca, String wpto, String wtod, Date fecjud, Date fecjuh, double wimp, Range range) {
		ArrayList<Tap014> lstTap014 = null;
		try {
			if (Integer.parseInt(wcta) > 0)
				lstTap014 = myDaoTap014.getUsingWcta(ds, wcta, waca, wpto, wtod, fecjud, fecjuh, wimp, range);
			if (Integer.parseInt(wbas) > 0)
				lstTap014 = myDaoTap014.getUsingWbas(ds, wbas, waca, wpto, wtod, fecjud, fecjuh, wimp, range);
			return lstTap014;
		}catch (ASException e) {
			return null;
		}
	}
	
	public class Fer1020Adapter {
		
		private String WBAS;
		
		public Fer1020Adapter(String wbas) {
			this.WBAS = wbas;
		}

		public String getWBAS() {
			return WBAS;
		}

		public void setWBAS(String wBAS) {
			WBAS = wBAS;
		}

	}

	public class Fer1020SFLAdapter {
		private Long WCTA1;
		private String DESTIP;
		private Long DOLIMA;
		private String DMCMCN;
		private Date WSDATC;
		private Date WSAMND;
		private BigDecimal WSINTE;
		
		public Fer1020SFLAdapter(Tap014 src) {
			this.DOLIMA = src.getDolima();
			this.WCTA1 = src.getDmacct();
			this.WSINTE = src.getDotacc();
			if (Integer.parseInt(wbas) == 0) {
				try {
					this.WSDATC = new SimpleDateFormat("yyyyMMdd").parse(src.getDodatc().toString());
					this.WSAMND = new SimpleDateFormat("yyyyMMdd").parse(src.getDoamnd().toString());
				} catch( Exception e ) {}
			}
			if( src.getDmtodf().equals(1)) {
				this.DESTIP = "ACA";
			} else if( src.getDmtodf().equals(2)) {
				this.DESTIP = "PTO";
			} else if( src.getDmtodf().equals(9)) {
				this.DESTIP = "TOD";
		}

	}

		public Long getWCTA1() {
			return WCTA1;
		}

		public void setWCTA1(Long wCTA1) {
			WCTA1 = wCTA1;
		}

		public String getDESTIP() {
			return DESTIP;
		}

		public void setDESTIP(String dESTIP) {
			DESTIP = dESTIP;
		}

		public Long getDOLIMA() {
			return DOLIMA;
		}

		public void setDOLIMA(Long dOLIMA) {
			DOLIMA = dOLIMA;
		}

		public String getDMCMCN() {
			return DMCMCN;
		}

		public void setDMCMCN(String dMCMCN) {
			DMCMCN = dMCMCN;
		}

		public Date getWSDATC() {
			return WSDATC;
		}

		public void setWSDATC(Date wSDATC) {
			WSDATC = wSDATC;
		}

		public Date getWSAMND() {
			return WSAMND;
		}

		public void setWSAMND(Date wSAMND) {
			WSAMND = wSAMND;
		}

		public BigDecimal getWSINTE() {
			return WSINTE;
		}

		public void setWSINTE(BigDecimal wSINTE) {
			WSINTE = wSINTE;
		}
	
	}

}
