package com.ciessa.museum.bz.legacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.ZrsplemDAO;
import com.ciessa.museum.dao.legacy.ZrsprerDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Zrsplem;
import com.ciessa.museum.model.legacy.Zrsprer;

public class ZRSTDSPSView05BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(ZRSTDSPSView05BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	ZrsprerDAO myDaoZrsprer;
	
	@Autowired
	ZrsplemDAO myDAOZrsplem;
	
	Zrsprer Objshdr2000 = new Zrsprer();
	
	List<Zrsplem> ListZrsplem = null;
	
	// Variables
	String meorg = null;
	String melogo = null;
	String mencct = null;
	String meyac = null;
	String meaafc = null;
	String mecifa = null;
	String meagig = null;
	
	String out6 = null;
	String break6 = null;
	String sprcd = null;
	String sanyocic = null;
	String subic = null;
	String[] slin = new String[400];
	Integer si1 = 0;
	String[] slin1 = new String[400];
	Integer si2 = 0;
	String[] slin2 = new String[400];
	Integer si = 0;
	String fechfac = null;
	String mebicyd = null;
	Boolean flagsfl = false;
	List<String> w6linems = new ArrayList<String>();
	
	String amprcd = null;
	String amiddp = null;
	String amubdg = null;
	Integer sq = 0;
	String santnum = null;
	
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
			
			Objshdr2000  = myDaoZrsprer.getUsingMeorgAndMelogoAndMencctAndMeyfacAndMeaafcAndMecifaAndMeagig(ds,Integer.parseInt(meorg) , Integer.parseInt(melogo), mencct, Integer.parseInt(meyac), Integer.parseInt(meaafc), mecifa, meagig);
			String rpta = SubProcDsplocmsgs(ds);
			
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
					"W6LINEMS",
			};

			// Obtains the user JSON representation
			ZRSTDSPSAdapter adapted = new ZRSTDSPSAdapter();
			adapted.setMEORGND(this.meorg);
			adapted.setMELOGOD(this.melogo);
			String meacnbd = null;
			String menamed = null;
			adapted.setMEACNBD(meacnbd); // TODO :
			adapted.setMENAMED(menamed); // TODO :
			adapted.setFECHFAC(this.fechfac);
			adapted.setMEBICYD(this.mebicyd);
			String[] stringArray = this.w6linems.toArray(new String[this.w6linems.size()]);
			adapted.setW6LINEMS( Arrays.toString(stringArray));
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
	
	private String SubProcDsplocmsgs(DataSet ds) {
		try {
			this.out6 = "";
			this.break6 = "";
			if (Objshdr2000.getMelogo() >= 600) {
				this.sprcd = "DN";
			}else {
				if (Objshdr2000.getMelogo()>= 300) {
					this.sprcd = "MC";
				} else {
					this.sprcd = "VS";
				}
			}
			String sfecres6 = ""; //TODO.: NO EXISTE VARIABLE
			this.sanyocic = sfecres6;
			this.subic = "Z1";
			
			this.si1 = 0;
			//limpiar slin1
			this.si1 = SubProcGetstmmsg(ds, this.sprcd, this.sanyocic, this.subic, this.slin1);
			
			this.subic = "Z2";
			this.si2 = 0;
			//limpiar slin2
			this.si2 = SubProcGetstmmsg(ds, this.sprcd, this.sanyocic, this.subic, this.slin2);
			
			if (this.si1 > 0) {
				this.slin = this.slin1;
				if (this.si2 > 0) {
					for (int sj = si1 + 1 ; sj < si1 + si2; sj++) {
						slin[sj] = slin2[sj - si1];
					}
				}
			} else {
				this.slin = this.slin2;
			}
			this.si = this.si1 + this.si2;
			if (this.si > 0) {
				for (int j = 1; j < this.si; j++) {
					if ( !slin[j].equals("") ) {
						if (j  == 1) {
							String smesres = "";
							String sanyores = "";
							this.fechfac = smesres + "/" + sanyores; //TODO :
							this.mebicyd = Objshdr2000.getMeccyc().toString();
							this.flagsfl = true; // TODO :
						}
						this.w6linems.add(this.slin[j]);
					}
				}
			}
			
			return "";
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	private Integer SubProcGetstmmsg(DataSet ds, String _sprcd, String _sanyocic,
			String _subic, String[] _slin) {
		try {
			if (_sprcd.equals("") || _sprcd.equals("0") || _subic.equals("") ) {
				return 0;
			}
			
			this.amprcd = _sprcd;
			this.amiddp = _sanyocic;
			this.amubdg = _subic;
			this.sq = 0;
			
			ListZrsplem = myDAOZrsplem.getUsinAmprcdAndAmiddpAndAmubdg(ds, _sprcd, _sanyocic, _subic);
			
			for(Zrsplem o : ListZrsplem) {
				if (this.sq < this.slin.length) {
					if (o.getAmemai().equals("A")) {
						this.santnum = o.getAmnmai().toString();
						if (o.getAmnuli()>0 && o.getAmemai().equals("A")) {
							this.sq += 1;
							this.slin[this.sq] = o.getAmltxc(); 
						}
					}					
				}
				if (this.sq < this.slin.length && !this.santnum.equals(o.getAmnmai().toString()) && !this.santnum.equals("0") && o.getAmemai().equals("A") ) {
					this.sq += 1;
					this.slin[this.sq] = "";
				}
			}
			
			return sq;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return 0;
		}
	}
	
	public class ZRSTDSPSAdapter {
		String MEORGND = null;
		String MELOGOD = null;
		String MEACNBD = null;
		String MENAMED = null;
		String FECHFAC = null;
		String MEBICYD = null;
		String W6LINEMS = null;
		
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

		public String getW6LINEMS() {
			return W6LINEMS;
		}

		public void setW6LINEMS(String w6linems) {
			W6LINEMS = w6linems;
		}
		
		

	}
	
	

}
