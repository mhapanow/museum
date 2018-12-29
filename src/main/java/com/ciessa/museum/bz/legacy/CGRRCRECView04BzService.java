package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.CgrprecDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cgrprec;

public class CGRRCRECView04BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(CGRRCRECView04BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CgrprecDAO myDAOCgrprec;
	
	
	
	Cgrprec objCgrprec = new Cgrprec();
	
	
	String ccte = "";
	String numch = "";
	String imp = "";
	String nrtr = "";
	String ctacte = "";
	String cuenta = "";
	String acta = "";
	String numchq = "";
	String cheque = "";
	String imp0rt = "";
	String acuent = "";
	String tipor = "";
	String nsec = "";
	String causa = "";
	String numsuc = "";
	String aano = "";
	String ames = "";
	String adia = "";
	String mensa = "";
	String useri = "";
	String pladif = "";
	Long numref = new Long("0");
	String cuitl1 = "";
	String cuitl2 = "";
	String cuitl3 = "";
	String cuitl4 = "";
	String cuitl5 = "";
	String cuitl6 = "";
	String cuitl7 = "";
	String cuitl8 = "";
	String cuitl9 = "";
	String cuitl0 = "";
	Integer afeaut = 0;
	String userau = "";
	String det = "";
	
	
	
	
	CGRRCRECV04Adapter adapter = null;

	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();


			this.ccte = obtainStringValue("ccte", null);
			this.numch = obtainStringValue("numch", null);
			this.imp = obtainStringValue("imp", null);
			this.nrtr = obtainStringValue("nrtr", null);
		
			SubRutLeesfl(ds);    

			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {

			};
			returnValue = getJSONRepresentationFromObject(adapter, fields);
			
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
	
	private String SubRutLeesfl(DataSet ds) {
		try {
			this.ctacte = this.ccte;
			this.cuenta = this.ccte;
			this.acta = "00";
			this.numchq = this.numch;
			this.cheque = this.numch;
			this.imp0rt = this.imp;
			this.acuent = this.cuenta;
			
			objCgrprec = myDAOCgrprec.getUsingAcuentAndChequeAndNrtr(ds, this.acuent, this.cheque, this.nrtr);
			if (objCgrprec != null) {
				if (objCgrprec.getCgtipr().equals("A")) {
					this.tipor = "ALTA ";
				}else {
					if (objCgrprec.getCgtipr().equals("M")) {
						this.tipor = "MODIF";
					}
				}
				this.nsec = objCgrprec.getCgnrtr();
				this.causa = objCgrprec.getCgrech();
				this.numsuc = objCgrprec.getCgbrch();
				this.aano = objCgrprec.getCgareg();
				this.ames = objCgrprec.getCgmreg();
				this.adia = objCgrprec.getCgdreg();
				//this.fecreg = this.afech;
				if (objCgrprec.getCgstat().equals("1")) {
					this.mensa = "PEND";
				}else {
					if (objCgrprec.getCgstat().equals("2")) {
						this.mensa = "AUTO";
					}else {
						if (objCgrprec.getCgstat().equals("3") || objCgrprec.getCgstat().equals("4") ) {
							this.mensa = "BAJA";
						}else {
							this.mensa = "";
						}
					}
				}
				
				objCgrprec = myDAOCgrprec.getUsingCgacctAndCgnche(ds, objCgrprec.getCgacct(), objCgrprec.getCgnche());
				if (objCgrprec != null) {
					this.mensa = "BCRA";
				}//fin if null
				this.aano = objCgrprec.getCgapag();
				this.ames = objCgrprec.getCgmpag();
				this.adia = objCgrprec.getCgdpag();
				//this.fecpch = this.afech;
				this.aano = objCgrprec.getCgasam();
				this.ames = objCgrprec.getCgmsam();
				this.adia = objCgrprec.getCgdsam();
				//this.fecpm = this.afech;
				this.aano = objCgrprec.getCgarec();
				this.ames = objCgrprec.getCgmrec();
				this.adia = objCgrprec.getCgdrec();
				//this.fecrec = this.afech;
				this.aano = objCgrprec.getCgadbt();
				this.ames = objCgrprec.getCgmdbt();
				this.adia = objCgrprec.getCgddbt();
				//this.fecdbt = this.afech;
				//this.aano = objCgrprec.getCgauc();
				//this.ames = objCgrprec.getCgmuc();
				//this.adia = objCgrprec.getCgduc();
				//this.fecult = this.afech;
				this.useri = "";
				this.useri = objCgrprec.getCgusri();
				this.pladif = objCgrprec.getCgpdif();
				this.aano = objCgrprec.getCgacie();
				this.ames = objCgrprec.getCgmcie();
				this.adia = objCgrprec.getCgdcie();
				//this.feccie = this.afech;
				this.numref = objCgrprec.getCgnref();
				//this.aano = objCgrprec.getCgaenr();
				//this.ames = objCgrprec.getCgmenr();
				//this.adia = objCgrprec.getCgdenr();
				//this.fecenv = this.afech;
				this.cuitl1 = "0";
				this.cuitl2 = "0";
				this.cuitl3 = "0";
				this.cuitl4 = "0";
				this.cuitl5 = "0";
				this.cuitl6 = "0";
				this.cuitl7 = "0";
				this.cuitl8 = "0";
				this.cuitl9 = "0";
				this.cuitl0 = "0";
				this.cuitl1 = objCgrprec.getCgcui1();
				if (!objCgrprec.getCgcui2().equals("0")) {
					this.cuitl2 = objCgrprec.getCgcui2();
					if (!objCgrprec.getCgcui3().equals("0")) {
						this.cuitl3 = objCgrprec.getCgcui3();
						if (!objCgrprec.getCgcui4().equals("0")) {
							this.cuitl4 = objCgrprec.getCgcui4();
							if (!objCgrprec.getCgcui5().equals("0")) {
								this.cuitl5 = objCgrprec.getCgcui5();
								if (!objCgrprec.getCgcui6().equals("0")) {
									this.cuitl6 = objCgrprec.getCgcui6();
									if (!objCgrprec.getCgcui7().equals("0")) {
										this.cuitl7 = objCgrprec.getCgcui7();
										if (!objCgrprec.getCgcui8().equals("0")) {
											this.cuitl8 = objCgrprec.getCgcui8();
											if (!objCgrprec.getCgcui9().equals("0")) {
												this.cuitl9 = objCgrprec.getCgcui9();
												if (!objCgrprec.getCgcui0().equals("0")) {
													this.cuitl0 = objCgrprec.getCgcui0();
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				this.afeaut = objCgrprec.getCgfaut();
				/*this.fecaut = this.adiaau;
				this.aauxau = this.amesau;
				this.aauxau = this.aanoau;
				this.fecaut = this.aauxau;*/
				this.userau = "";
				this.userau = objCgrprec.getCgusra();
				SubRutDevolu(ds);    
				//Mostrar Pantalla4
				this.det = " ";
				
			}
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutDevolu(DataSet ds) {
		try {
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
}
	
	
	
	
	public class CGRRCRECV04Adapter {
		
		
		
		
		public CGRRCRECV04Adapter () {
			
		}
		
		
		
		
		
	}

}
