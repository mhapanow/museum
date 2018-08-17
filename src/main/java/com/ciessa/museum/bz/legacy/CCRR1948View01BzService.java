package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.CcrpcapDAO;
import com.ciessa.museum.dao.legacy.CcrpcarDAO;
import com.ciessa.museum.dao.legacy.CcrpcpiDAO;
import com.ciessa.museum.dao.legacy.CcrpcriDAO;
import com.ciessa.museum.dao.legacy.CcrpsbeDAO;
import com.ciessa.museum.dao.legacy.CcrpsceDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Ccrpcap;
import com.ciessa.museum.model.legacy.Ccrpcar;
import com.ciessa.museum.model.legacy.Ccrpcpi;
import com.ciessa.museum.model.legacy.Ccrpcri;
import com.ciessa.museum.model.legacy.Ccrpsbe;
import com.ciessa.museum.model.legacy.Ccrpsce;


public class CCRR1948View01BzService extends RestBaseServerResource {
public static final Logger log = Logger.getLogger(CCRR1948View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CcrpcapDAO myDaoCcrpcap;
	
	@Autowired
	CcrpcarDAO myDaoCcrpcar;
	
	@Autowired
	CcrpsceDAO myDaoCcrpsce;
	
	@Autowired
	CcrpsbeDAO myDaoCcrpsbe;
	
	@Autowired
	CcrpcpiDAO myDaoCcrpcpi;
	
	@Autowired
	CcrpcriDAO myDaoCcrpcri;
	
	//Entity
	Ccrpcap ObjCcrpcap	= new Ccrpcap();
	Ccrpcar ObjCcrpcar = new Ccrpcar();
	Ccrpsce ObjCcrpsce = new Ccrpsce();
	Ccrpsbe ObjCcrpsbe = new Ccrpsbe();
	Ccrpcpi ObjCcrpcpi = new Ccrpcpi();
	Ccrpcri ObjCcrpcri = new Ccrpcri();
	
	//Variables_globales _Valores INPUT
	String dibanc = null;
	String dinucr = null;
	String dincuo = null;
	String dimodo = null;
	BigDecimal ditota = null;
	String didmon = null;
	
	//Variables _Globales OUTPUT
	String kynucr = null;
	String kyncuo = null;
	String p1nucr = null;
	String p1ncuo = null;
	String p1mone = null;
	
	// Variable _Globales _CCRPCAP
	private BigDecimal p1carp;
	private BigDecimal p1i8ca;
	private BigDecimal p1i8gp;
	private BigDecimal p1i8ip;
	private BigDecimal p1i8op;
	private BigDecimal p1i8pp;
	private BigDecimal p1i8sp;
	private BigDecimal p1i8sv;
	private BigDecimal p1i9ca;
	private BigDecimal p1i9gp;
	private BigDecimal p1i9ip;
	private BigDecimal p1i9op;
	private BigDecimal p1i9pp;
	private BigDecimal p1i9sp;
	private BigDecimal p1i9sv;
	private BigDecimal p1icap;
	private BigDecimal p1iccp;
	private BigDecimal p1icip;
	private BigDecimal p1iicp;
	private BigDecimal p1iipp;
	private BigDecimal p1iisp;
	private BigDecimal p1imap;
	private BigDecimal p1imbp;
	private BigDecimal p1isap;
	private BigDecimal p1isvp;
	private BigDecimal p1iva1;
	private BigDecimal p1iva2;
	private BigDecimal p1iva3;
	private BigDecimal p1iva5;
	private BigDecimal p1iva6;
	private BigDecimal p1iva7;
	private BigDecimal p1iva8;
	
	private BigDecimal p1ibp;
	private BigDecimal p1i8ib;
	private BigDecimal p1i9ib;
	private BigDecimal p1iva4;
	private BigDecimal p1tpag;
	private BigDecimal p1tdeu;
	private BigDecimal parout;
	
	//Variables otro cai8vp + cai9vp
	private BigDecimal _cai8vp;
	private BigDecimal _cai9vp;	

	// Variables _Globles _ObjetoCCRRCUOT
	private String abanc;
	private String anucr;
	private String ancuo;
	private Integer asbnc;
	private Integer accon;
	private String aschb;
	private BigDecimal aimpor;
	private BigDecimal aimpo8;
	private BigDecimal aimpo9;
	private BigDecimal aalicu;
	private String atipp;
	private Integer anmov;
	
	private Integer nnparm;
	
	private BigDecimal aaimpo;
	private BigDecimal aaimp8;
	private BigDecimal aaimp9;
	
	List<Ccrpcap> listCcrpcap = null;
	List<Ccrpcar> listCcrpcar = null; 
	List<Ccrpsce> listCcrpsce = null; 
	List<Ccrpsbe> listCcrpsbe = null;
	List<Ccrpcpi> listCcrpcpi = null;
	List<Ccrpcri> listCcrpcri = null;
	
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			//Parametros_Get		
			this.dibanc = obtainStringValue("dibanc", null);
			this.dinucr = obtainStringValue("dinucr", null);
			this.dincuo = obtainStringValue("dincuo", null);
			this.dimodo = obtainStringValue("dimodo", null);
			this.ditota = new BigDecimal(obtainStringValue("ditota", null));
			this.didmon = obtainStringValue("didmon", null);
			

			String rpta = SubRutAddout(ds);
			
			if (!rpta.equals(""))
			{
				log.log(Level.SEVERE, rpta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
			}
			
			if (this.dimodo.equals("*DSP")) 
			{
				log.log(Level.SEVERE, "DIMODO no inicializado", new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException("DIMODO no inicializado", new Exception())).toString();
			}
			
			long diff = new Date().getTime() - millisPre;
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			// Get the output fields
			String[] fields = new String[] {
					"P1NUCR",
					"P1NCUO",
					"P1MONE",
					"P1IMAP",
					"P1IBP",
					"P1IMBP",
					"P1IVA2",
					"P1ICIP",
					"P1IVA1",
					"P1ICAP",
					"P1IVA3",
					"P1ISVP",
					"P1IVA5",
					"P1IISP",
					"P1IVA7",
					"P1ICCP",
					"P1IVA4",
					"P1ISAP",
					"P1CARP",
					"P1IIPP",
					"P1IICP",
					"P1TPAG",
					"P1IVA6",
					"P1TDEU"
			};

			// Obtains the user JSON representation
			CCRR1948Adapter adapted = new CCRR1948Adapter();
			returnValue = getJSONRepresentationFromObject(adapted, fields);
							
		}catch (ASException e) {
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
		
	private String SubRutAddout(DataSet ds) {
		try {
			this.kynucr = this.dinucr;
			this.kyncuo = this.dincuo;
			this.p1nucr = this.dinucr;
			this.p1ncuo = this.dincuo;
			this.p1mone = this.didmon;

			listCcrpcap = myDaoCcrpcap.getUsingCrnucrAndScncuoToList(ds, this.kynucr, Integer.parseInt(this.kyncuo));

			for( Ccrpcap o : listCcrpcap ) {
				this.p1carp = this.p1carp.add(o.getCacarp());
				this.p1i8ca = this.p1i8ca.add(o.getCai8ca());
				this.p1i8gp = this.p1i8gp.add(o.getCai8gp());
				this.p1i8ip = this.p1i8ip.add(o.getCai8ip());
				this.p1i8op = this.p1i8op.add(o.getCai8op());
				this.p1i8pp = this.p1i8pp.add(o.getCai8pp());
				this.p1i8sp = this.p1i8sp.add(o.getCai8sp());
				this.p1i8sv = this.p1i8sv.add(o.getCai8vp());
				this.p1i9ca = this.p1i9ca.add(o.getCai9ca());
				this.p1i9gp = this.p1i9gp.add(o.getCai9gp());
				this.p1i9ip = this.p1i9ip.add(o.getCai9ip());
				this.p1i9op = this.p1i9op.add(o.getCai9op());
				this.p1i9pp = this.p1i9pp.add(o.getCai9pp());
				this.p1i9sp = this.p1i9sp.add(o.getCai9sp());
				this.p1i9sv = this.p1i9sv.add(o.getCai9vp());
				this.p1icap = this.p1icap.add(o.getCaicap());
				this.p1iccp = this.p1iccp.add(o.getCaiccp());
				this.p1icip = this.p1icip.add(o.getCaicip());
				this.p1iicp = this.p1iicp.add(o.getCaiicp());
				this.p1iipp = this.p1iipp.add(o.getCaiipp());
				this.p1iisp = this.p1iisp.add(o.getCaiisp());
				this.p1imap = this.p1imap.add(o.getCaimap());
				this.p1imbp = this.p1imbp.add(o.getCaimbp());
				this.p1isap = this.p1isap.add(o.getCaisap());
				this.p1isvp = this.p1isvp.add(o.getCaisvp());
				this.p1iva1 = this.p1iva1.add(o.getCai8ip().add(o.getCai9ip()));
				this.p1iva2 = this.p1iva2.add(o.getCai8sp().add(o.getCai9sp()));
				this.p1iva3 = this.p1iva3.add(o.getCai8pp().add(o.getCai9pp()));
				this.p1iva5 = this.p1iva5.add(o.getCai8op().add(o.getCai9op()));
				this.p1iva6 = this.p1iva6.add(o.getCai8gp().add(o.getCai9gp()));
				this.p1iva7 = this.p1iva7.add(o.getCai8ca().add(o.getCai9ca()));
				this.p1iva8 = this.p1iva8.add(o.getCai8vp().add(o.getCai9vp()));
				
				this._cai8vp = this._cai8vp.add(o.getCai8vp());
				this._cai9vp = this._cai9vp.add(o.getCai9vp());
			}
			
			listCcrpcar = myDaoCcrpcar.getUsingnDKynucrAndDkyncuoToList(ds, this.kynucr, this.kyncuo);
			
			for( Ccrpcar o : listCcrpcar ) {
				this.p1carp = this.p1carp.subtract(o.getRvcarp());
				this.p1i8ca = this.p1i8ca.subtract(o.getRvi8ca());
				this.p1i8gp = this.p1i8gp.subtract(o.getRvi8gp());
				this.p1i8ip = this.p1i8ip.subtract(o.getRvi8ip());
				this.p1i8op = this.p1i8op.subtract(o.getRvi8op());
				this.p1i8pp = this.p1i8pp.subtract(o.getRvi8pp());
				this.p1i8sp = this.p1i8sp.subtract(o.getRvi8sp());
				this.p1i8sv = this.p1i8sv.subtract(this._cai8vp);
				this.p1i9ca = this.p1i9ca.subtract(o.getRvi9ca());
				this.p1i9gp = this.p1i9gp.subtract(o.getRvi9gp());
				this.p1i9ip = this.p1i9ip.subtract(o.getRvi9ip());
				this.p1i9op = this.p1i9op.subtract(o.getRvi9op());
				this.p1i9pp = this.p1i9pp.subtract(o.getRvi9pp());
				this.p1i9sp = this.p1i9sp.subtract(o.getRvi9sp());
				this.p1i9sv = this.p1i9sv.subtract(this._cai9vp);
				this.p1icap = this.p1icap.subtract(o.getRvicap());
				this.p1iccp = this.p1iccp.subtract(o.getRviccp());
				this.p1icip = this.p1icip.subtract(o.getRvicip());
				this.p1iicp = this.p1iicp.subtract(o.getRviicp());
				this.p1iipp = this.p1iipp.subtract(o.getRviipp());
				this.p1iisp = this.p1iisp.subtract(o.getRviisp());
				this.p1imap = this.p1imap.subtract(o.getRvimap());
				this.p1imbp = this.p1imbp.subtract(o.getRvimbp());
				this.p1isap = this.p1isap.subtract(o.getRvisap());
				this.p1isvp = this.p1isvp.subtract(o.getRvisvp());
				this.p1iva1 = this.p1iva1.subtract(o.getRvi8ip().add(o.getRvi9ip()));
				this.p1iva2 = this.p1iva2.subtract(o.getRvi8sp().add(o.getRvi9sp()));
				this.p1iva3 = this.p1iva3.subtract(o.getRvi8pp().add(o.getRvi9pp()));
				this.p1iva5 = this.p1iva5.subtract(o.getRvi8op().add(o.getRvi9op()));
				this.p1iva6 = this.p1iva6.subtract(o.getRvi8gp().add(o.getRvi9gp()));
				this.p1iva7 = this.p1iva7.subtract(o.getRvi8ca().add(o.getRvi9ca()));
				this.p1iva8 = this.p1iva8.subtract(this._cai8vp.add(this._cai9vp));
			}
			
			String rpta = SubRutCalimp(ds);
			
			this.p1ibp = this.p1ibp.add(aaimpo);
			this.p1i8ib = this.p1i8ib.add(aaimp8);
			this.p1i9ib = this.p1i9ib.add(aaimp9);
			this.p1iva4 = this.p1iva4.add(aaimp8);
			this.p1iva4 = this.p1iva4.add(aaimp9);
			
			this.p1tpag = this.p1imap.add(this.p1imbp.add(this.p1icip.add(this.p1icap.add(this.p1isvp.add(this.p1iisp.add(this.p1iccp.add(this.p1isap.add(this.p1carp.add(this.p1iipp.add(this.p1iicp.add(this.p1ibp.add(this.p1iva1.add(this.p1iva2.add(this.p1iva3.add(this.p1iva4.add(this.p1iva5.add(this.p1iva6.add(this.p1iva7.add(this.p1iva8)))))))))))))))))));
			this.p1tdeu = this.ditota.divide(p1tpag);
			// this.parout = this.dsout; //no exist

			
		}catch (ASException e) {
			return e.getMessage();
		} // Fin Catch
			
		return "";
	}
	
	private String SubRutCalimp(DataSet ds) {
		
			//Asigna valores para ejecutar el Objeto
			this.abanc  = this.dibanc;
			this.anucr  = this.dinucr;
			this.ancuo  = this.dincuo;
			this.asbnc  = 0;
			this.accon  = 3;
			this.aschb = "";
			this.aimpor = new BigDecimal(0);
			this.aimpo8 = new BigDecimal(0);
			this.aimpo9 = new BigDecimal(0);
			this.aalicu = new BigDecimal(0);
			this.atipp = "s";
			this.anmov  = 0;
			
			String rpta = SubObjCcrrcuot(ds);
			
			this.aaimpo = this.aimpor;
			this.aaimp8 = this.aimpo8;
			this.aaimp9 = this.aimpo9;
		
		return "";
		
	}
	
	private String SubObjCcrrcuot(DataSet ds) {
		try {
			//Limpia Variables
			this.aimpor = new BigDecimal(0);
			this.aimpo8 = new BigDecimal(0);
			this.aimpo9 = new BigDecimal(0);
			
			if (this.nnparm == 10) {
				this.aalicu = new BigDecimal(0);
			}
			if (this.accon == 0 & this.aschb != "B" & this.nnparm <= 10) {
				listCcrpsce = myDaoCcrpsce.getUsingnAbancAnucrAncuoAndAsbncToList(ds, this.abanc, this.anucr, this.ancuo, this.asbnc.toString());
				for( Ccrpsce o : listCcrpsce ) {
					this.aimpor = this.aimpor.add(o.getSciimp());
					this.aimpor = this.aimpor.subtract(o.getSciimb());
					this.aimpo8 = this.aimpo8.add(o.getSci8im());
					this.aimpo9 = this.aimpo9.add(o.getSci9im());					
					if (this.nnparm >= 10) {
						this.aalicu = new BigDecimal(o.getScalic().toString());
					}
				}
			}
			if (this.accon != 0 & this.aschb != "B" & this.nnparm <= 10) {
				listCcrpsce = myDaoCcrpsce.getUsingnAbancAnucrAncuoAsbncAndAcconToList(ds, this.abanc, this.anucr, this.ancuo, this.asbnc.toString(),this.accon.toString());

				for( Ccrpsce o : listCcrpsce ) {
					this.aimpor = this.aimpor.add(o.getSciimp());
					this.aimpor = this.aimpor.subtract(o.getSciimb());
					this.aimpo8 = this.aimpo8.add(o.getSci8im());
					this.aimpo9 = this.aimpo9.add(o.getSci9im());					
					if (this.nnparm <= 10) {
						this.aalicu = new BigDecimal(o.getScalic().toString());
					}
				}				
			}
			
			if (this.accon == 0 & this.aschb == "B" & this.nnparm <= 10) {
				listCcrpsbe = myDaoCcrpsbe.getUsingnAbancAnucrAncuoAndAsbncToList(ds, this.abanc, this.anucr, this.ancuo, this.asbnc.toString());
				for( Ccrpsbe o : listCcrpsbe ) {
					this.aimpor = this.aimpor.add(o.getSbiimp());
					this.aimpor = this.aimpor.subtract(o.getSbiimb());
					this.aimpo8 = this.aimpo8.add(o.getSbi8im());
					this.aimpo9 = this.aimpo9.add(o.getSbi9im());					
					if (this.nnparm >= 10) {
						this.aalicu = new BigDecimal(o.getSbalic().toString());
					}
				}		
			}
			
			if (this.accon != 0 & this.aschb == "B" & this.nnparm <= 10) {
				listCcrpsbe = myDaoCcrpsbe.getUsingnAbancAnucrAncuoAsbnAndAcconcToList(ds, this.abanc, this.anucr, this.ancuo, this.asbnc.toString(), this.accon.toString());
				for( Ccrpsbe o : listCcrpsbe ) {
					this.aimpor = this.aimpor.add(o.getSbiimp());
					this.aimpor = this.aimpor.subtract(o.getSbiimb());
					this.aimpo8 = this.aimpo8.add(o.getSbi8im());
					this.aimpo9 = this.aimpo9.add(o.getSbi9im());					
					if (this.nnparm >= 10) {
						this.aalicu = new BigDecimal(o.getSbalic().toString());
					}
				}		
			}
			
			if ( (this.nnparm >= 11 & this.atipp == "P" ) | (this.nnparm >= 11 & this.atipp == "S") ) {
				
					if (this.atipp == "S" & this.accon == 0) {
						listCcrpcpi = myDaoCcrpcpi.getUsingnAbancAnucrAncuoAndAsbncToList(ds, this.abanc, this.anucr, this.ancuo, this.asbnc.toString());						
					}
					if (this.atipp == "S" & this.accon != 0) {
						listCcrpcpi = myDaoCcrpcpi.getUsingnAbancAnucrAncuoAsbnAndAcconcToList(ds, this.abanc, this.anucr, this.ancuo, this.asbnc.toString(), this.accon.toString());
					}
					if (this.atipp == "P" & this.accon == 0) {
						listCcrpcpi = myDaoCcrpcpi.getUsingnAbancAnmovAnucrAncuoAndAsbncToList(ds, this.abanc, this.anmov.toString(), this.anucr, this.ancuo, this.asbnc.toString());
					}
					if (this.atipp == "P" & this.accon != 0) {
						listCcrpcpi = myDaoCcrpcpi.getUsingnAbancAnmovAnucrAncuoAsbncAndAcconToList(ds, this.abanc, this.anmov.toString(), this.anucr, this.ancuo, this.asbnc.toString(), this.accon.toString());
					}
					for( Ccrpcpi o : listCcrpcpi ) {
						this.aimpor = this.aimpor.add(o.getCpimpp());
						this.aimpo8 = this.aimpo8.add(o.getCpi8ip());
						this.aimpo9 = this.aimpo9.add(o.getCpi9ip());
					}
			}
			if ( (this.nnparm >= 11 & this.atipp == "E" ) | (this.nnparm >= 11 & this.atipp == "S") ) {
				if (this.atipp == "S" & this.accon == 0) {
					listCcrpcri = myDaoCcrpcri.getUsingnAbancAnucrAncuoAndAsbncToList(ds, this.abanc, this.anucr, this.ancuo, this.asbnc.toString());						
				}
				if (this.atipp == "S" & this.accon != 0) {
					listCcrpcri = myDaoCcrpcri.getUsingnAbancAnucrAncuoAsbnAndAcconcToList(ds, this.abanc, this.anucr, this.ancuo, this.asbnc.toString(), this.accon.toString());
				}
				if (this.atipp == "E" & this.accon == 0) {
					listCcrpcri = myDaoCcrpcri.getUsingnAbancAnmovAnucrAncuoAndAsbncToList(ds, this.abanc, this.anmov.toString(), this.anucr, this.ancuo, this.asbnc.toString());
				}
				if (this.atipp == "E" & this.accon != 0) {
					listCcrpcri = myDaoCcrpcri.getUsingnAbancAnmovAnucrAncuoAsbncAndAcconToList(ds, this.abanc, this.anmov.toString(), this.anucr, this.ancuo, this.asbnc.toString(), this.accon.toString());
				}
				for( Ccrpcri o : listCcrpcri ) {
					if (this.atipp == "S") {
						this.aimpor = this.aimpor.subtract(o.getRpimpp());
						this.aimpo8 = this.aimpo8.subtract(o.getRpi8ip());
						this.aimpo9 = this.aimpo9.subtract(o.getRpi9ip());
					} else {
						this.aimpor = this.aimpor.add(o.getRpimpp());
						this.aimpo8 = this.aimpo8.add(o.getRpi8ip());
						this.aimpo9 = this.aimpo9.add(o.getRpi9ip());
					}
				}
			}
			
		} catch (ASException e) {
			return e.getMessage();
		} // Fin Catch
		
		
		return "";
	}

	public class CCRR1948Adapter {
		private String P1NUCR;
		private String P1NCUO;
		private String P1MONE;
		private String P1IMAP;
		private String P1IBP;
		private String P1IMBP;
		private String P1IVA2;
		private String P1ICIP;
		private String P1IVA1;
		private String P1ICAP;
		private String P1IVA3;
		private String P1ISVP;
		private String P1IVA5;
		private String P1IISP;
		private String P1IVA7;
		private String P1ICCP;
		private String P1IVA4;
		private String P1ISAP;
		private String P1CARP;
		private String P1IIPP;
		private String P1IICP;
		private String P1TPAG;
		private String P1IVA6;
		private String P1TDEU;
		
		public CCRR1948Adapter() {
			this.P1NUCR = p1nucr.toString();
			this.P1NCUO = p1ncuo.toString();
			this.P1MONE = p1mone.toString();
			this.P1IMAP = p1imap.toString();
			this.P1IBP = p1ibp.toString();
			this.P1IMBP = p1imbp.toString();
			this.P1IVA2 = p1iva2.toString();
			this.P1ICIP = p1icip.toString();
			this.P1IVA1 = p1iva1.toString();
			this.P1ICAP = p1icap.toString();
			this.P1IVA3 = p1iva3.toString();
			this.P1ISVP = p1isvp.toString();
			this.P1IVA5 = p1iva5.toString();
			this.P1IISP = p1iisp.toString();
			this.P1IVA7 = p1iva7.toString();
			this.P1ICCP = p1iccp.toString();
			this.P1IVA4 = p1iva4.toString();
			this.P1ISAP = p1isap.toString();
			this.P1CARP = p1carp.toString();
			this.P1IIPP = p1iipp.toString();
			this.P1IICP = p1iicp.toString();
			this.P1TPAG = p1tpag.toString();
			this.P1IVA6 = p1iva6.toString();
			this.P1TDEU = p1tdeu.toString();
		}
		
		
		
		public String getP1NUCR() {
			return P1NUCR;
		}
		public void setP1NUCR(String p1nucr) {
			P1NUCR = p1nucr;
		}
		public String getP1NCUO() {
			return P1NCUO;
		}
		public void setP1NCUO(String p1ncuo) {
			P1NCUO = p1ncuo;
		}
		public String getP1MONE() {
			return P1MONE;
		}
		public void setP1MONE(String p1mone) {
			P1MONE = p1mone;
		}
		public String getP1IMAP() {
			return P1IMAP;
		}
		public void setP1IMAP(String p1imap) {
			P1IMAP = p1imap;
		}
		public String getP1IBP() {
			return P1IBP;
		}
		public void setP1IBP(String p1ibp) {
			P1IBP = p1ibp;
		}
		public String getP1IMBP() {
			return P1IMBP;
		}
		public void setP1IMBP(String p1imbp) {
			P1IMBP = p1imbp;
		}
		public String getP1IVA2() {
			return P1IVA2;
		}
		public void setP1IVA2(String p1iva2) {
			P1IVA2 = p1iva2;
		}
		public String getP1ICIP() {
			return P1ICIP;
		}
		public void setP1ICIP(String p1icip) {
			P1ICIP = p1icip;
		}
		public String getP1IVA1() {
			return P1IVA1;
		}
		public void setP1IVA1(String p1iva1) {
			P1IVA1 = p1iva1;
		}
		public String getP1ICAP() {
			return P1ICAP;
		}
		public void setP1ICAP(String p1icap) {
			P1ICAP = p1icap;
		}
		public String getP1IVA3() {
			return P1IVA3;
		}
		public void setP1IVA3(String p1iva3) {
			P1IVA3 = p1iva3;
		}
		public String getP1ISVP() {
			return P1ISVP;
		}
		public void setP1ISVP(String p1isvp) {
			P1ISVP = p1isvp;
		}
		public String getP1IVA5() {
			return P1IVA5;
		}
		public void setP1IVA5(String p1iva5) {
			P1IVA5 = p1iva5;
		}
		public String getP1IISP() {
			return P1IISP;
		}
		public void setP1IISP(String p1iisp) {
			P1IISP = p1iisp;
		}
		public String getP1IVA7() {
			return P1IVA7;
		}
		public void setP1IVA7(String p1iva7) {
			P1IVA7 = p1iva7;
		}
		public String getP1ICCP() {
			return P1ICCP;
		}
		public void setP1ICCP(String p1iccp) {
			P1ICCP = p1iccp;
		}
		public String getP1IVA4() {
			return P1IVA4;
		}
		public void setP1IVA4(String p1iva4) {
			P1IVA4 = p1iva4;
		}
		public String getP1ISAP() {
			return P1ISAP;
		}
		public void setP1ISAP(String p1isap) {
			P1ISAP = p1isap;
		}
		public String getP1CARP() {
			return P1CARP;
		}
		public void setP1CARP(String p1carp) {
			P1CARP = p1carp;
		}
		public String getP1IIPP() {
			return P1IIPP;
		}
		public void setP1IIPP(String p1iipp) {
			P1IIPP = p1iipp;
		}
		public String getP1IICP() {
			return P1IICP;
		}
		public void setP1IICP(String p1iicp) {
			P1IICP = p1iicp;
		}
		public String getP1TPAG() {
			return P1TPAG;
		}
		public void setP1TPAG(String p1tpag) {
			P1TPAG = p1tpag;
		}
		public String getP1IVA6() {
			return P1IVA6;
		}
		public void setP1IVA6(String p1iva6) {
			P1IVA6 = p1iva6;
		}
		public String getP1TDEU() {
			return P1TDEU;
		}
		public void setP1TDEU(String p1tdeu) {
			P1TDEU = p1tdeu;
		}
		
		
		
		
	}
}
