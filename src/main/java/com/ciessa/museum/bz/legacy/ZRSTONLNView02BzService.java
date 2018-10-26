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
import com.ciessa.museum.model.legacy.Grmcrc;
import com.ciessa.museum.dao.legacy.GrmcrcDAO;
import com.ciessa.museum.model.legacy.Zrsprer;
import com.ciessa.museum.dao.legacy.ZrsprerDAO;



public class ZRSTONLNView02BzService extends RestBaseServerResource {

	public static final Logger log = Logger.getLogger(ZRSTONLNView02BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	GrmcrcDAO myDaoGrmcrc;
	
	@Autowired
	ZrsprerDAO myDaoZrsprer;
	
	Grmcrc ObjGrmcrc   = new Grmcrc();
	Zrsprer ObjZrsprer = new Zrsprer();
	
	//Variables_globales
	int w1afac  = 0;
	int w1cifa  = 0;
	int w1logo  = 0;
	int w1agig  = 0;
	int w1orgn  = 0;
	BigDecimal w1bbad  = new BigDecimal(0);
	BigDecimal w1bbah  = new BigDecimal(0);
	BigDecimal w1ebad  = new BigDecimal(0);
	BigDecimal w1ebah  = new BigDecimal(0);
	
	String w1acns  = "";
	String w1cans  = "";

	String aux = null;
	String w1prcd  = null;
	
	String w1cydu  = null;
	String w1obol  = null;
	String w1estc  = null;
	String w1cacl  = null;
	String w1cpos  = null;
	String w1retr  = null;
	String w1func  = null;
	String w1crba  = null;
	
	List<Zrsprer> listZrsprer  = null;

	ZRSTONLNAdapter adapted = null;
	List<ZRSTONLNAdapter> list = new ArrayList<ZRSTONLNAdapter>();


	
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
			
			w1afac = obtainIntegerValue("w1afac", 0);			
			w1cifa = obtainIntegerValue("w1cifa", 0);
			w1agig = obtainIntegerValue("w1agig", 0);
			w1orgn = obtainIntegerValue("w1orgn", 0);
			w1prcd = obtainStringValue("w1prcd", "");
			w1acns = obtainStringValue("w1acns", "");
			w1cans = obtainStringValue("w1cans", "");
			w1bbad = new BigDecimal(obtainDoubleValue("w1bbad", 0.0));
			w1bbah = new BigDecimal(obtainDoubleValue("w1bbah", 0.0));
			w1ebad = new BigDecimal(obtainDoubleValue("w1bbad", 0.0));
			w1ebah = new BigDecimal(obtainDoubleValue("w1bbah", 0.0));
			w1cydu = obtainStringValue("w1cydu", "");
			w1obol = obtainStringValue("w1obol", "");
			w1estc = obtainStringValue("w1estc", "");
			w1cacl = obtainStringValue("w1cacl", "");
			w1cpos = obtainStringValue("w1cpos", "");
			w1retr = obtainStringValue("w1retr", "");
			w1func = obtainStringValue("w1func", "");
			w1crba = obtainStringValue("w1crba", "");
			w1logo = obtainIntegerValue("w1logo", 0);

			String rpta = SubRutSR_SELECC(ds);
			
			if (!rpta.equals("")){
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString(); }
			
			
			rpta = SubRutSR_ARMASQL(ds);
			if (!rpta.equals("")){
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString(); }
			
		
			String[] fields = new String[] {
					 "WSAFAC",
					 "WSCIFA",
					 "WSAGIG",
					 "WSORGN",
					 "WSLOGO",
					 "WSACNB",
					 "WSNAME",
					 
			};
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
			
			
			long diff = new Date().getTime() - millisPre;
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
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
	
	
	
	private String SubRutSR_SELECC(DataSet ds) {
		
		int ctaselecc = 0;
		String error = "";
			
			if(w1afac > 0 && w1afac <1980) {
				error = "Error";
				return error;
			}
			if(w1cifa > 12) {
				error = "Error";
				return error;
			}
			if(w1logo == 0 && w1prcd.equals("")) {
				error = "Error";
				return error;
			}
			if(!w1prcd.equals("") && !w1prcd.equals("VS") && !w1prcd.equals("MC") && !w1prcd.equals("DN")) {
				error = "Error";
				return error;
			}
			if(w1afac != 0 || w1cifa != 0) {
				ctaselecc = ctaselecc + 1;
			}
			if(w1agig != 0) {
				ctaselecc = ctaselecc + 1;
			}
			if(w1orgn != 0) {
				ctaselecc = ctaselecc + 1;
			}
			if(w1logo != 0) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1prcd.equals("")) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1acns.equals("0")) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1cans.equals("0") ) {
				ctaselecc = ctaselecc + 1;
			}
			if(w1bbad.compareTo(BigDecimal.ZERO) == 0) {
				ctaselecc = ctaselecc + 1;
			}
			if(w1bbah.compareTo(BigDecimal.ZERO) == 0) {
				ctaselecc = ctaselecc + 1;
			}
			if(w1bbah.compareTo(BigDecimal.ZERO) == 0 && w1bbad.compareTo(BigDecimal.ZERO) > 0) {
				w1bbad = new BigDecimal(-99999999999.99);
			}
			if(w1bbad.compareTo(BigDecimal.ZERO) == 0 && w1bbah.compareTo(BigDecimal.ZERO) > 0) {				
				w1bbah = new BigDecimal(99999999999.99);
			}
			if(!w1ebad.equals("0")) {
				
				ctaselecc = ctaselecc + 1;
			}
			if(!w1ebah.equals("0")) {
				
				ctaselecc = ctaselecc + 1;
			}
			if(w1ebah.compareTo(BigDecimal.ZERO) == 0 && w1ebad.compareTo(BigDecimal.ZERO) > 0) {
				w1ebad = new BigDecimal(-99999999999.99);;
			}
			if(w1ebad.compareTo(BigDecimal.ZERO) == 0 && w1ebah.compareTo(BigDecimal.ZERO) > 0) {
				w1ebah = new BigDecimal(99999999999.99);
			}
			if(!w1cydu.equals("0")) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1obol.equals("0")) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1estc.equals("0")) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1cacl.equals("0")) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1cpos.equals("0")) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1retr.equals("0")) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1func.equals("0")) {
				ctaselecc = ctaselecc + 1;
			}
			if(!w1crba.equals("0")) {
				ctaselecc = ctaselecc + 1;
			}
			if (error.equals("")) {
				if (ctaselecc == 0 ) {
					return "Error";
				}
			}
			return error;
	}
	
	
	private String SubRutSR_ARMASQL(DataSet ds ) {
		try {
			if( !w1cans.equals("0") ) {
				ObjGrmcrc= myDaoGrmcrc.getUsingW1cans(ds, w1cans);
				if(ObjGrmcrc != null) {
					w1acns = ObjGrmcrc.getRgactn().toString();
				}
			}
			listZrsprer = myDaoZrsprer.getUsingW1afacW1cifaW1agigW1orgnW1logoW1acnsW1cansW1baddW1bbahW1ebadW1ebahW1cyduW1obolW1estcW1caclW1cposW1retrW1funcANDw1crbaToList(ds,  w1afac, w1cifa, w1agig, w1orgn, w1logo, w1acns, w1cans, w1bbad, w1bbah, w1ebad, w1ebah, w1cydu, w1obol, w1estc, w1cacl, w1cpos, w1retr, w1func, w1crba);
			
			for (Zrsprer o : listZrsprer) {
				adapted = new ZRSTONLNAdapter();
				adapted.setWSAFAC(Integer.parseInt(String.format("%02d", o.getMeyfac()) + String.format("%02d", o.getMeaafc())));
				adapted.setWSCIFA(o.getMecifa());
				adapted.setWSAGIG(o.getMeagig());
				adapted.setWSORGN(o.getMeorg());
				adapted.setWSLOGO(o.getMelogo());
				adapted.setWSACNB(o.getMencct());
				adapted.setWSNAME(o.getMename());
				list.add(adapted);				
			}
			
			
		} catch (ASException e) {
			return e.getMessage();
			
		}
		return "";
	} //Fin public
			

	public class ZRSTONLNAdapter {
	
		int WSAFAC  = 0;
		String WSCIFA = "";
		String WSAGIG  = "";
		int WSORGN = 0;
		int WSLOGO = 0;
		String WSACNB = "";
		String WSNAME = "";

		public ZRSTONLNAdapter() {
			
		}

		public int getWSAFAC() {
			return WSAFAC;
		}

		public void setWSAFAC(int wSAFAC) {
			WSAFAC = wSAFAC;
		}

		public String getWSCIFA() {
			return WSCIFA;
		}

		public void setWSCIFA(String wSCIFA) {
			WSCIFA = wSCIFA;
		}

		public String getWSAGIG() {
			return WSAGIG;
		}

		public void setWSAGIG(String wSAGIG) {
			WSAGIG = wSAGIG;
		}

		public int getWSORGN() {
			return WSORGN;
		}

		public void setWSORGN(int wSORGN) {
			WSORGN = wSORGN;
		}

		public int getWSLOGO() {
			return WSLOGO;
		}

		public void setWSLOGO(int wSLOGO) {
			WSLOGO = wSLOGO;
		}

		public String getWSACNB() {
			return WSACNB;
		}

		public void setWSACNB(String wSACNB) {
			WSACNB = wSACNB;
		}

		public String getWSNAME() {
			return WSNAME;
		}

		public void setWSNAME(String wSNAME) {
			WSNAME = wSNAME;
		}

	}// fin Adapter
	
}