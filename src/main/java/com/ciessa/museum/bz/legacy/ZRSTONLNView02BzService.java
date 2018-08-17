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
	long w1acns  = 0;
	int w1cans  = 0;

	String aux = null;
	String w1ebah  = null;
	String w1ebad  = null;	
	String w1prcd  = null;
	String w1bbah  = null;
	String w1bbad  = null;
	String w1cydu  = null;
	String w1obol  = null;
	String w1estc  = null;
	String w1cacl  = null;
	String w1cpos  = null;
	String w1retr  = null;
	String w1func  = null;
	String w1crba  = null;
	String w1badd  = null;
	
	List<Zrsprer> listZrsprer  = null;

	ZRSTONLNAdapter adapted = new ZRSTONLNAdapter();
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
			
			aux    = obtainStringValue("w1afac", null);
			w1afac = Integer.parseInt(aux);
			
			aux    = obtainStringValue("w1cifa", null);
			w1cifa = Integer.parseInt(aux);
			
			aux    = obtainStringValue("w1agig", null);
			w1agig = Integer.parseInt(aux);
			
			aux    = obtainStringValue("w1orgn", null);
			w1orgn = Integer.parseInt(aux);
				
			aux    = obtainStringValue("w1acns", null);
			w1acns = Integer.parseInt(aux);
					
			aux = obtainStringValue("w1cans", null);
			w1cans = Integer.parseInt(aux);
				
			aux    = obtainStringValue("w1logo", null);
			w1logo = Integer.parseInt(aux);
			
			w1ebah = obtainStringValue("w1ebah", null);
			w1ebad = obtainStringValue("w1ebad", null);			
			w1prcd = obtainStringValue("w1prcd", null);
			w1bbad = obtainStringValue("w1bbad", null);
			w1bbah = obtainStringValue("w1bbah", null);
			w1cydu = obtainStringValue("w1cydu", null);
			w1obol = obtainStringValue("w1obol", null);
			w1estc = obtainStringValue("w1estc", null);
			w1cacl = obtainStringValue("w1cacl", null);
			w1cpos = obtainStringValue("w1cpos", null);
			w1retr = obtainStringValue("w1retr", null);
			w1func = obtainStringValue("w1func", null);
			w1crba = obtainStringValue("w1crba", null);
			w1badd = obtainStringValue("w1badd", null);

			String rpta = SubRutSR_SELECC(ds);
			
			if (rpta.equals("")){
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString(); }
			
			
			rpta = SubRutSR_ARMASQL(ds);
			if (rpta.equals("")){
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString(); }
			
		
			String[] fields = new String[] {
					 "TXTAVISO",
					 "WSOP",
					 "WSAFAC",
					 "SCIFA",
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
			
			if(w1afac > 0 && w1afac <1980) {
				return "Error";
				
			}
			if(w1cifa > 12) {
				return "Error";
				
			}
			if(w1logo > 12 && w1prcd.equals("")) {
				return "Error";
				
			}
			if(!w1prcd.equals("") && !w1prcd.equals("VS") && !w1prcd.equals("MC") && !w1prcd.equals("DN")) {
				return "Error";
				
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
			if(w1acns != 0) {
				
				ctaselecc = ctaselecc + 1;
			}
			if(w1cans != 0) {
				
				ctaselecc = ctaselecc + 1;
			}
			if(!w1bbad.equals("0")) {
				
				ctaselecc = ctaselecc + 1;
			}
			if(!w1bbah.equals("0")) {
				
				ctaselecc = ctaselecc + 1;
			}
			if(!w1bbah.equals("0") && w1bbad.equals("0") ) {
				
				w1bbad = "LOVAL";
			}
			if(!w1bbad.equals("0") && w1bbah.equals("0") ) {
				
				w1bbah = "HIVAL";
			}
			if(!w1ebad.equals("0")) {
				
				ctaselecc = ctaselecc + 1;
			}
			if(!w1ebah.equals("0")) {
				
				ctaselecc = ctaselecc + 1;
			}
			if(!w1ebah.equals("0") && w1ebad.equals("0")) {
				
				w1ebad = "LOVAL";
			}
			if(!w1ebad.equals("0")&& w1ebah.equals("0")) {
				
				w1ebah = "HIVAL";
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
			
			ctaselecc = 0;
			return "Eror";
	}
	
	
	private String SubRutSR_ARMASQL(DataSet ds ) {
		try {
			if(w1cans !=0 ) {
				ObjGrmcrc= myDaoGrmcrc.getUsingW1cans(ds, w1cans);
				if(ObjGrmcrc != null) {
					w1acns = ObjGrmcrc.getRgactn();
					
				}
				
			}
			listZrsprer = myDaoZrsprer.getUsingW1afacW1cifaW1agigW1orgnW1logoW1acnsW1cansW1baddW1bbahW1ebadW1ebahW1cyduW1obolW1estcW1caclW1cposW1retrW1funcANDw1crbaToList(ds,  w1afac, w1cifa, w1agig, w1orgn, w1logo, w1acns, w1cans, w1badd, w1bbah, w1ebad, w1ebah, w1cydu, w1obol, w1estc, w1cacl, w1cpos, w1retr, w1func, w1crba);
			
			for (Zrsprer o : listZrsprer) {
				adapted.setTXTAVISO("Aviso");
				adapted.setWSOP("valor no referenciado");
				adapted.setWSAFAC(o.getMeafac());
				adapted.setSCIFA(o.getMeacif());
				adapted.setWSAGIG(o.getMeagig());
				adapted.setWSORGN(o.getMeorg());
				adapted.setWSLOGO(o.getMelogo());
				adapted.setWSACNB("valor no referenciado");
				adapted.setWSNAME("valor no referenciado");
				list.add(adapted);				
			}
			
			
		} catch (ASException e) {
			return e.getMessage();
			
		}
		return "";
	} //Fin public
			

	public class ZRSTONLNAdapter {
	
		int WSAFAC  = 0;
		int SCIFA   = 0;
		int WSORGN  = 0;
		int WSLOGO  = 0;
		
		String TXTAVISO= null;
		String WSOP    = null;
		String WSAGIG  = null;
		String WSACNB  = null;
		String WSNAME  = null;

		public ZRSTONLNAdapter() {
			
		}

		public int getWSAFAC() {
			return WSAFAC;
		}

		public void setWSAFAC(int wSAFAC) {
			WSAFAC = wSAFAC;
		}

		public int getSCIFA() {
			return SCIFA;
		}

		public void setSCIFA(int sCIFA) {
			SCIFA = sCIFA;
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

		public String getTXTAVISO() {
			return TXTAVISO;
		}

		public void setTXTAVISO(String tXTAVISO) {
			TXTAVISO = tXTAVISO;
		}

		public String getWSOP() {
			return WSOP;
		}

		public void setWSOP(String wSOP) {
			WSOP = wSOP;
		}

		public String getWSAGIG() {
			return WSAGIG;
		}

		public void setWSAGIG(String wSAGIG) {
			WSAGIG = wSAGIG;
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
	
	
	
	
	
	
	
} // Fin public class ZRSTONLNView02BzService