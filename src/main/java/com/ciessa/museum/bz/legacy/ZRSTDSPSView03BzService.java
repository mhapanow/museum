package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.ZrsprerDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Zrsprer;

public class ZRSTDSPSView03BzService extends RestBaseServerResource {
public static final Logger log = Logger.getLogger(ZRSTDSPSView03BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	ZrsprerDAO myDAOZrsprer;

	
	Zrsprer objZrsprer = new Zrsprer();
	
	
	String dsorg = null;
	String dslogo = null;
	String dscuenta = null;
	String dscent = null;
	String dsano = null;
	String dscic = null;
	String dsag = null;
	
	String meorg = null;
	String melogo = null;
	String mencct = null;
	String meyfac = null;
	String meaafc = null;
	String mecifa = null;
	String meagig = null;
	
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			this.dsorg = obtainStringValue("dsorg", null);
			this.dslogo = obtainStringValue("dslogo", null);
			this.dscuenta = obtainStringValue("dscuenta", null);
			this.dscent = obtainStringValue("dscent", null);
			this.dsano = obtainStringValue("dsano", null);
			this.dscic = obtainStringValue("dscic", null);
			this.dsag = obtainStringValue("dsag", null);
			
			this.meorg  = this.dsorg;
			this.melogo = this.dslogo;
			this.mencct = this.dscuenta;
			this.meyfac = this.dscent;
			this.meaafc = this.dsano;
			this.mecifa = this.dscic;
			this.meagig = this.dsag;
			
			objZrsprer = myDAOZrsprer.getUsingMeorgAndMelogoAndMencctAndMeyfacAndMeaafcAndMecifaAndMeagig(ds, Integer.parseInt(meorg), Integer.parseInt(melogo), mencct, Integer.parseInt(meyfac), Integer.parseInt(meaafc), mecifa, meagig);
			
			
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
					"MECACLB",
					"MECBCDB",
					"MEHOFLB",
					"MEENBAB",
					"MEENBUB",
					"MESTLCB",
					"MESTICB",
					"MEESTCB",
					"MEESTAB",
					"MEACOFB",
					"MEFUNCB",
					"MEOBOLB",
			};
			
			ZRSTDSPSAdapter adapted = new ZRSTDSPSAdapter();
			adapted.setMECACLB(objZrsprer.getMecacl());
			adapted.setMECBCDB(objZrsprer.getMecbcd());
			adapted.setMEHOFLB(objZrsprer.getMehofl());
			adapted.setMEENBAB(objZrsprer.getMeenba().toString());
			adapted.setMEENBUB(objZrsprer.getMeenbu().toString());
			adapted.setMESTLCB(objZrsprer.getMestlc().toString());
			adapted.setMESTICB(objZrsprer.getMestic().toString());
			adapted.setMEESTCB(objZrsprer.getMeestc());
			adapted.setMEESTAB(objZrsprer.getMeesta());
			adapted.setMEACOFB(objZrsprer.getMeacof());
			adapted.setMEFUNCB(objZrsprer.getMefunc());
			adapted.setMEOBOLB(objZrsprer.getMeobol().toString());
			
			returnValue = this.getJSONRepresentationFromObject(adapted, fields);
			
			
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
	
	public class ZRSTDSPSAdapter {
		
		String MECACLB = null;
		String MECBCDB = null;
		String MEHOFLB = null;
		String MEENBAB = null;
		String MEENBUB = null;
		String MESTLCB = null;
		String MESTICB = null;
		String MEESTCB = null;
		String MEESTAB = null;
		String MEACOFB = null;
		String MEFUNCB = null;
		String MEOBOLB = null;
		
		public ZRSTDSPSAdapter() {
			
		}

		public String getMECACLB() {
			return MECACLB;
		}

		public void setMECACLB(String mECACLB) {
			MECACLB = mECACLB;
		}

		public String getMECBCDB() {
			return MECBCDB;
		}

		public void setMECBCDB(String mECBCDB) {
			MECBCDB = mECBCDB;
		}

		public String getMEHOFLB() {
			return MEHOFLB;
		}

		public void setMEHOFLB(String mEHOFLB) {
			MEHOFLB = mEHOFLB;
		}

		public String getMEENBAB() {
			return MEENBAB;
		}

		public void setMEENBAB(String mEENBAB) {
			MEENBAB = mEENBAB;
		}

		public String getMEENBUB() {
			return MEENBUB;
		}

		public void setMEENBUB(String mEENBUB) {
			MEENBUB = mEENBUB;
		}

		public String getMESTLCB() {
			return MESTLCB;
		}

		public void setMESTLCB(String mESTLCB) {
			MESTLCB = mESTLCB;
		}

		public String getMESTICB() {
			return MESTICB;
		}

		public void setMESTICB(String mESTICB) {
			MESTICB = mESTICB;
		}

		public String getMEESTCB() {
			return MEESTCB;
		}

		public void setMEESTCB(String mEESTCB) {
			MEESTCB = mEESTCB;
		}

		public String getMEESTAB() {
			return MEESTAB;
		}

		public void setMEESTAB(String mEESTAB) {
			MEESTAB = mEESTAB;
		}

		public String getMEACOFB() {
			return MEACOFB;
		}

		public void setMEACOFB(String mEACOFB) {
			MEACOFB = mEACOFB;
		}

		public String getMEFUNCB() {
			return MEFUNCB;
		}

		public void setMEFUNCB(String mEFUNCB) {
			MEFUNCB = mEFUNCB;
		}

		public String getMEOBOLB() {
			return MEOBOLB;
		}

		public void setMEOBOLB(String mEOBOLB) {
			MEOBOLB = mEOBOLB;
		}
		
	}
}
