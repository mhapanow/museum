package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.Cfp001220DAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cfp001220;

public class TAR0077ViewBzService extends RestBaseServerResource {

	public static final Logger log = Logger.getLogger(TAR0077ListBzService.class.getName());

	@Autowired
	DataSetDAO dsDao;

	@Autowired
	Cfp001220DAO myDao;

	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {

			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());

			long millisPre = new Date().getTime();
			String wscodi = obtainStringValue("wscodi", null);
			Cfp001220 obj = myDao.getUsingWscodi(ds, wscodi);
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");

			// Get the output fields
			String[] fields = new String[] {
					"pkid",
					"member",
					"WSCODI",
					"WSDESC",
					"WSDESL",
					"WSCOTE",
					"WSCOMN",
					"WSCOGL",
					"WSCITI",
					"WSNREO",
					"WSCAOP",
					"WSGENU"
			};

			// Obtains the user JSON representation
			TAR0077Adapter adapted = new TAR0077Adapter(obj);
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

	public class TAR0077Adapter {

		private String Pkid;
		private String member;
		private String WSCODI;
		private String WSDESC;
		private String WSDESL;
		private String WSCOTE;
		private String WSCOMN;
		private String WSCOGL;
		private String WSCITI;
		private String WSNREO;
		private String WSCAOP;
		private String WSGENU;

		public TAR0077Adapter(Cfp001220 src) {
			this.Pkid = src.getPkid();
			this.member = src.getMember();
			this.WSCODI = src.getCfctr();
			this.WSDESC = src.getCfdco();
			this.WSDESL = src.getCfdla();
			this.WSCOTE = src.getCfcte();
			this.WSCOMN = src.getCfcmn();
			this.WSCOGL = src.getCfcgl();
			this.WSCITI = src.getCitit();
			this.WSNREO = src.getNrefo();
			this.WSCAOP = src.getCanop();
			this.WSGENU = src.getDepost().equals("Y") || src.getWithdw().equals("Y") ? "Y" : "N";
		}

		/**
		 * @return the pkid
		 */
		public String getPkid() {
			return Pkid;
		}

		/**
		 * @param pkid
		 *            the pkid to set
		 */
		public void setPkid(String pkid) {
			Pkid = pkid;
		}

		/**
		 * @return the member
		 */
		public String getMember() {
			return member;
		}

		/**
		 * @param member
		 *            the member to set
		 */
		public void setMember(String member) {
			this.member = member;
		}

		/**
		 * @return the wSCODI
		 */
		public String getWSCODI() {
			return WSCODI;
		}

		/**
		 * @param wSCODI
		 *            the wSCODI to set
		 */
		public void setWSCODI(String wSCODI) {
			WSCODI = wSCODI;
		}

		/**
		 * @return the wSDESC
		 */
		public String getWSDESC() {
			return WSDESC;
		}

		/**
		 * @param wSDESC
		 *            the wSDESC to set
		 */
		public void setWSDESC(String wSDESC) {
			WSDESC = wSDESC;
		}

		/**
		 * @return the wSDESL
		 */
		public String getWSDESL() {
			return WSDESL;
		}

		/**
		 * @param wSDESL
		 *            the wSDESL to set
		 */
		public void setWSDESL(String wSDESL) {
			WSDESL = wSDESL;
		}

		/**
		 * @return the wSCOTE
		 */
		public String getWSCOTE() {
			return WSCOTE;
		}

		/**
		 * @param wSCOTE
		 *            the wSCOTE to set
		 */
		public void setWSCOTE(String wSCOTE) {
			WSCOTE = wSCOTE;
		}

		/**
		 * @return the wSCOMN
		 */
		public String getWSCOMN() {
			return WSCOMN;
		}

		/**
		 * @param wSCOMN
		 *            the wSCOMN to set
		 */
		public void setWSCOMN(String wSCOMN) {
			WSCOMN = wSCOMN;
		}

		/**
		 * @return the wSCOGL
		 */
		public String getWSCOGL() {
			return WSCOGL;
		}

		/**
		 * @param wSCOGL
		 *            the wSCOGL to set
		 */
		public void setWSCOGL(String wSCOGL) {
			WSCOGL = wSCOGL;
		}

		/**
		 * @return the wSCITI
		 */
		public String getWSCITI() {
			return WSCITI;
		}

		/**
		 * @param wSCITI
		 *            the wSCITI to set
		 */
		public void setWSCITI(String wSCITI) {
			WSCITI = wSCITI;
		}

		/**
		 * @return the wSNREO
		 */
		public String getWSNREO() {
			return WSNREO;
		}

		/**
		 * @param wSNREO
		 *            the wSNREO to set
		 */
		public void setWSNREO(String wSNREO) {
			WSNREO = wSNREO;
		}

		/**
		 * @return the wSCAOP
		 */
		public String getWSCAOP() {
			return WSCAOP;
		}

		/**
		 * @param wSCAOP
		 *            the wSCAOP to set
		 */
		public void setWSCAOP(String wSCAOP) {
			WSCAOP = wSCAOP;
		}

		/**
		 * @return the wSGENU
		 */
		public String getWSGENU() {
			return WSGENU;
		}

		/**
		 * @param wSGENU
		 *            the wSGENU to set
		 */
		public void setWSGENU(String wSGENU) {
			WSGENU = wSGENU;
		}

	}
}
