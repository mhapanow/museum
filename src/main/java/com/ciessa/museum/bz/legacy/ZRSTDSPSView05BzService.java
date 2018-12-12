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
	String meorg = "";
	String melogo = "";
	String mencct = "";
	String meyac = "";
	String meaafc = "";
	String mecifa = "";
	String meagig = "";
	
	String out6 = "";
	String break6 = "";
	String sprcd = "";
	String sanyocic = "";
	String subic = "";
	String[] slin = new String[400];
	Integer si1 = 0;
	String[] slin1 = new String[400];
	Integer si2 = 0;
	String[] slin2 = new String[400];
	Integer si = 0;
	String[] slinGetstmmsg = new String[400];
	String fechfac = null;
	String mebicyd = null;
	Boolean flagsfl = false;
	List<String> w6linems = new ArrayList<String>();
	
	String amprcd = "";
	String amiddp = "";
	String amubdg = "";
	Integer sq = 0;
	String santnum = "";
	
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
					"w6linems",
			};

			// Obtains the user JSON representation
			ZRSTDSPSAdapter adapted = new ZRSTDSPSAdapter();
			adapted.setMEORGND(this.meorg);
			adapted.setMELOGOD(this.melogo);
			String meacnbd = null;
			String menamed = null;
			adapted.setMEACNBD(this.Objshdr2000.getMencct());
			adapted.setMENAMED(this.Objshdr2000.getMename());
			adapted.setFECHFAC(this.fechfac);
			adapted.setMEBICYD(this.mebicyd);
			//String[] stringArray = this.w6linems.toArray(new String[this.w6linems.size()]);
			//adapted.setW6linems( Arrays.toString(stringArray));
			adapted.setW6linems( String.join(",", this.w6linems));
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
			
			this.sanyocic = String.format("%02d", Objshdr2000.getMeyfac()) + String.format("%02d",Objshdr2000.getMeaafc()) + Objshdr2000.getMecifa();
			this.subic = "Z1";
			
			this.si1 = 0;
			//limpiar slin1
			for (int i=0; i< this.slin1.length; i++) this.slin1[i] = "";
			this.si1 = SubProcGetstmmsg(ds, this.sprcd, this.sanyocic, this.subic);
			for (int i=0; i< this.slin1.length; i++)
				this.slin1[i] = this.slinGetstmmsg[i];
			
			this.subic = "Z2";
			this.si2 = 0;
			for (int i=0; i< this.slin2.length; i++) this.slin2[i] = "";
			this.si2 = SubProcGetstmmsg(ds, this.sprcd, this.sanyocic, this.subic);
			for (int i=0; i< this.slin2.length; i++)
				this.slin2[i] = this.slinGetstmmsg[i];
			
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
				for (int j = 1; j <= this.si; j++) {
					if ( !slin[j].equals("") ) {
						if (j  == 1) {
							this.fechfac = this.Objshdr2000.getMecifa() + "/" + String.format("%02d", this.Objshdr2000.getMeyfac()) + String.format("%02d",this.Objshdr2000.getMeaafc());
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
	
	private Integer SubProcGetstmmsg(DataSet ds, String _sprcd, String _sanyocic, String _subic) {
		try {
			for (int i = 0; i< this.slinGetstmmsg.length; i++) this.slinGetstmmsg[i] = "";
			if (_sprcd.equals("") || _sprcd.equals("0") || _subic.equals("") ) {
				return 0;
			}
			
			this.amprcd = _sprcd;
			this.amiddp = _sanyocic;
			this.amubdg = _subic;
			this.sq = 0;
			
			ListZrsplem = myDAOZrsplem.getUsinAmprcdAndAmiddpAndAmubdg(ds, _sprcd, _sanyocic, _subic);
			
			for(Zrsplem o : ListZrsplem) {
				if (this.sq < this.slinGetstmmsg.length) {
					if (o.getAmemai().equals("A")) {
						this.santnum = o.getAmnmai().toString();
						if (o.getAmnuli()>0 && o.getAmemai().equals("A")) {
							this.sq += 1;
							this.slinGetstmmsg[this.sq] = o.getAmltxc(); 
						}
					}					
				}
				if (this.sq < this.slinGetstmmsg.length && !this.santnum.equals(o.getAmnmai().toString()) && !this.santnum.equals("0") && o.getAmemai().equals("A") ) {
					this.sq += 1;
					this.slinGetstmmsg[this.sq] = "";
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
		String w6linems = null;
		
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

		public String getW6linems() {
			return w6linems;
		}

		public void setW6linems(String w6linems) {
			this.w6linems = w6linems;
		}




	}
	
	

}
