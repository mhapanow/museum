package com.ciessa.museum.bz.legacy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.ZrspmssDAO;
import com.ciessa.museum.dao.legacy.ZrsprerDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Zrspmss;
import com.ciessa.museum.model.legacy.Zrsprer;

public class ZRSTDSPSView06BzService extends RestBaseServerResource {
	
	public static final Logger log = Logger.getLogger(ZRSTDSPSView06BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	ZrsprerDAO myDaoZrsprer;
	
	@Autowired
	ZrspmssDAO myDaoZrspmss;
	
	//Entity
	//Zrsprer ObjZrsprer = new Zrsprer();
	Zrspmss ObjZrspmss = new Zrspmss();
	
	// Variables
	String meorg = null;
	String melogo = null;
	String mencct = null;
	String meyac = null;
	String meaafc = null;
	String mecifa = null;
	String meagig = null;
	//
	//Zrsprer stmheader  = new Zrsprer();
	Zrsprer Objshdr2000 = new Zrsprer();
	
	String out5 = null;
	String break5 = null;
	String sfecres7 = null;
	String sfecres6 = null;
	Integer si1 = 0;
	String scodmsg120 = null;
	Integer sorgd = 0;
	Integer si2 = 0;
	
	//
	String sanyocic6 = null;
	String msyfac = null;
	String msaafc = null;
	String mscifa = null;
	String msccyc = null;
	Integer msorg = null;
	Integer mslogo = null;
	// TODO
	String scent = null;
	String sanyo2 = null;
	String scic = null;
	// **
	String msmgcd = null;
	String smsgvrs = null;
	String sauxchar = null;
	
	String msvrsn = null;
	String auxdec = null;
	
	Integer Si = 0;
	Integer J = 0;
	Integer K = 0;
	Integer Q = 0;
	Integer R = 0;
	
	String[] slin = new String[400];
	String[] slins = new String[400];
	String[] slinuss = new String[400];
	
	Boolean flagsfl = false;
	String fechfac = null;
	String smesres = null;
	String sanyores = null;
	String mebicyd = null;
	String meccyc = null;
	
	String w5linems = null;
	
	String nove5sav = null; //TODO :
	String nove5 = null; //TODO:
	
	String meacnbd = null; //TODO:
	String menamed = null; // TODO:
	
	List<ZRSTDSPSAdapter> list = new ArrayList<ZRSTDSPSAdapter>();
	ZRSTDSPSAdapter adapted = null;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			//Parametros Get
			meorg = obtainStringValue("meorg", null);
			melogo = obtainStringValue("melogo", null);
			mencct = obtainStringValue("mencct", null);
			meyac = obtainStringValue("meyac", null);
			meaafc = obtainStringValue("meaafc", null);
			mecifa = obtainStringValue("mecifa", null);
			meagig = obtainStringValue("meagig", null);
			
			// TODO .:. No se encuentra.
			Objshdr2000  = myDaoZrsprer.getUsingMeorgAndMelogoAndMencctAndMeyfacAndMeaafcAndMecifaAndMeagig(ds,Integer.parseInt(meorg) , Integer.parseInt(melogo), mencct, Integer.parseInt(meyac), Integer.parseInt(meaafc), mecifa, meagig);
			
			String rpta = SubProcDspecsmsgs(ds);
			
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
					"MEORGND",
					"MELOGOD",
					"MEACNBD",
					"MENAMED",
					"FECHFAC",
					"MEBICYD",
					"W5LINEMS",
			};

			// Obtains the user JSON representation
			ZRSTDSPSAdapter adapted = new ZRSTDSPSAdapter();
			adapted.setMEORGND(this.meorg);
			adapted.setMELOGOD(this.melogo);
			adapted.setMEACNBD(this.meacnbd); // TODO :
			adapted.setMENAMED(this.menamed); // TODO :
			adapted.setFECHFAC(this.fechfac);
			adapted.setMEBICYD(this.mebicyd);
			adapted.setW5LINEMS(this.w5linems);
			returnValue = getJSONRepresentationFromObject(adapted, fields);
			
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
	
	private String SubProcDspecsmsgs(DataSet ds) {
		try {
			out5 = null;
			break5 = null;
			// stmheader = Objshdr2000;
			sfecres7 = null;
			sfecres7 = sfecres6; //TODO, No existe sfecres6
			si1 = 0;
			scodmsg120 = Objshdr2000.getMemsgp();
			si1 = SubProcGetecsmsg(ds, sfecres7, "sbllgcyc", Objshdr2000.getMeorg(), Objshdr2000.getMelogo(), "scodmsg", this.slins);
			//
			sorgd = Objshdr2000.getMeorg() - 1;
			si2 = 0;
			scodmsg120 = Objshdr2000.getMemsgu();
			si2 = SubProcGetecsmsg(ds, sfecres7, "sbllgcyc", sorgd, Objshdr2000.getMelogo(), "scodmsg", this.slinuss);
			
			if (si1 > 0) {
				this.slin  = this.slins;
				if (si2 > 0) {
					for (int sj = si1 + 1 ; sj < si1 + si2; sj++) {
						slin[sj] = slinuss[sj - si1];
					}
				}
			}else {
				this.slin = this.slinuss;
			}
			Si = si1 + si2;
			
			if (Si > 0) {
				for (int j = 1; j < Si; j++) {
					if (slin[j] != "") {
						if (flagsfl  == false) {
							this.fechfac = this.smesres + "/" + this.sanyores; //TODO :
							this.mebicyd = Objshdr2000.getMeccyc().toString();
							this.flagsfl = true; // TODO :
						}
					}
					w5linems = this.slin[j];
				}
			}
			this.nove5sav = this.nove5;
			
			return "";
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	private Integer SubProcGetecsmsg(DataSet ds,
			String sanyocic,
			String sbllgcyc,
			Integer sorg,
			Integer slogo,
			String smsgvrs,
			String[] slin) {
		try {
			if (sanyocic == "0") {
				return 0;
			}
			
			sanyocic6 = sanyocic;
			msyfac = scent; // TODO
			msaafc = sanyo2; // TODO
			mscifa = scic; // TODO
			msccyc = sbllgcyc;
			msorg = sorg;
			mslogo = slogo;
			
			for (int i = 0; i < 20; i++) {
				msmgcd = smsgvrs.substring(1-1, 2-1); // (i)
				sauxchar = smsgvrs.substring(1-3, 4-1); // (i)
				if ( msmgcd != "" && StringUtils.isNumeric(sauxchar) ) {
					msvrsn = auxdec; // TODO auxdec
					String rpta = SubRutSscicloppal(ds);
				}
			}
			return Q;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return 0;
		}
	}
	
	private String SubRutSscicloppal(DataSet ds) {
		try {
			ObjZrspmss = myDaoZrspmss.getUsingMsyfacAndMsaafcAndMscifaAndMsccycAndMsorgAndMslogoAndMsmgcdAndMsvrsn(ds, msyfac, msaafc, mscifa, msccyc, msorg.toString(), mslogo.toString(), msmgcd, msvrsn);
			
			if (ObjZrspmss != null && !ObjZrspmss.getMsmgds().equals("")) {
				J = 1;
				while (J <= 70 && J > 0) {
					K = J;
					//J = getPosition(" ", ObjZrspmss.getMsmgds().substring( K + 1 ) );
					J = ObjZrspmss.getMsmgds().indexOf(" ", K +1 );
					
				}
				if (K > 1) {
					Q = Q + 1;
					slin[Q] = ObjZrspmss.getMsmgds().substring( K - 1 );
					 R = ObjZrspmss.getMsmgds().length() - K;
					if (R > 70) {
						Q = Q + 1;
						slin[Q] = ObjZrspmss.getMsmgds().substring( K + 1 , K + 1 + 60 );
						Q = Q + 1;
						slin[Q] = ObjZrspmss.getMsmgds().substring( 60 + K + 1 );
					}else {
						Q = Q + 1;
						slin[Q] = ObjZrspmss.getMsmgds().substring( K + 1 );
					}
				}else {
					Q = Q + 1;
					slin[Q] = ObjZrspmss.getMsmgds().substring( 1 - 1 , 60 - 1 );
					Q = Q + 1;
					slin[Q] = ObjZrspmss.getMsmgds().substring( 61 - 1 , 61 + 60 );
				}
				Q = Q + 1;
				slin[Q] = "";
			}
			
			return "";
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	public class ZRSTDSPSAdapter {
		String MEORGND = null;
		String MELOGOD = null;
		String MEACNBD = null;
		String MENAMED = null;
		String FECHFAC = null;
		String MEBICYD = null;
		String W5LINEMS = null;
		
		public ZRSTDSPSAdapter() {
			
		}

		public String getMEORGND() {
			return MEORGND;
		}

		public void setMEORGND(String mEORGND) {
			MEORGND = mEORGND;
		}

		public String getMELOGOD() {
			return MELOGOD;
		}

		public void setMELOGOD(String mELOGOD) {
			MELOGOD = mELOGOD;
		}

		public String getMEACNBD() {
			return MEACNBD;
		}

		public void setMEACNBD(String mEACNBD) {
			MEACNBD = mEACNBD;
		}

		public String getMENAMED() {
			return MENAMED;
		}

		public void setMENAMED(String mENAMED) {
			MENAMED = mENAMED;
		}

		public String getFECHFAC() {
			return FECHFAC;
		}

		public void setFECHFAC(String fECHFAC) {
			FECHFAC = fECHFAC;
		}

		public String getMEBICYD() {
			return MEBICYD;
		}

		public void setMEBICYD(String mEBICYD) {
			MEBICYD = mEBICYD;
		}

		public String getW5LINEMS() {
			return W5LINEMS;
		}

		public void setW5LINEMS(String w5linems) {
			W5LINEMS = w5linems;
		}
		
		
		
	}

}
