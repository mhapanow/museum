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
import com.ciessa.museum.bz.legacy.CgrrcompBzService.CgrrcompAdapter;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.CgrprecDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Cgrprec;

public class CGRRCRECView03BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(CGRRCRECView03BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CgrprecDAO myDAOCgrprec;
	
	
	
	List<Cgrprec> listCgrprec = null;
	
	
	String aamcue = "";
	String cuenta = "";
	String error = "";
	String acuent = "";
	String det = "";
	String ccte = "";
	String numch = "";
	String acgimp = "";
	String tipr1 = "";
	String nrtr = "";
	BigDecimal imp = new BigDecimal("0"); 
	String aimpte = "";  
	String mot1 = "       SIN FONDOS";  
	String mot2 = "     FALLA FORMAL";  
	String mot3 = "  DE REGISTRACION";  
	Long cuit = new Long("0");
	String aanore = "";
	String amesre = "";
	String adiare = "";
	String aanopc = "";
	String amespc = "";
	String adiapc = "";
	String aanopm = "";
	String amespm = "";
	String adiapm = "";
	String aanodi = "";
	String numcue = "";
	String numche = "";
	String tipope = "";
	String motrec = "";
	String tipreg = "";
	String estado = "";
	String amesdi = "";
	String adiadi = "";
	String comput = "";
	String compur = "";
	String revisi = "";
	String pcantm = "";
	
	String compu = "";
	String revi = "";
	String rech = "";
	Integer rrn3 = 0;
	Integer limite = 0;
	
	
	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
	@Autowired
	CgrrcompBzService Cgrrcomp;
	
	CgrrcompAdapter cgrrcompadapter = null;
	
	CGRRCRECV03Adapter adapter = null;
	List<CGRRCRECV03Adapter> listAdapter = null;
	
	

	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			this.aamcue = obtainStringValue("aamcue", null);
			
			this.cuenta = this.aamcue;
			
			this.listAdapter = new ArrayList<CGRRCRECV03Adapter>();
			
			SubRutCherec(ds);    
			SubRutLeesfl(ds);    

			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			String[] fields = new String[] {
			};
			
			String[] arrayFields = new String[] {
					"CCTE",
					"NUMCH",
					"TIPR1",
					"IMP",
					"RECH",
					"COMPU",
					"REVI",
					"CUIT",
					"NRTR"
			};
			
			returnValue = getJSONRepresentationFromObject(adapter, fields);
			returnValue.put("data", getJSONRepresentationFromArrayOfObjects(listAdapter, arrayFields).get("data"));
			
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
	
	private String SubRutCherec(DataSet ds) {
		try {
			Long con = new Long(this.cuenta);
			this.acuent = String.format("%011d", con );
			listCgrprec = myDAOCgrprec.getUsingListNumcue(ds, this.acuent);
			for(Cgrprec o: listCgrprec) {
				this.det = " ";
				this.ccte = this.cuenta;
				this.numch = o.getCgnche();
				this.acgimp = o.getCgimpo();
				this.tipr1 = o.getCgtipr();
				this.nrtr = o.getCgnrtr();
				this.aimpte = this.acgimp.substring(5, 15);
				this.imp = new BigDecimal(this.aimpte).divide(new BigDecimal("100"),2);
				if (o.getCgrech().equals("1")) {
					this.rech = this.mot1;
				}else {
					if (o.getCgrech().equals("2")) {
						this.rech = this.mot2;
						}else {
							if (o.getCgrech().equals("3")) {
								this.rech = this.mot3;
							}	
						}
				}
				this.cuit = new Long(o.getCgcui1());
				this.aanore = o.getCgarec();
				this.amesre = o.getCgmrec();
				this.adiare = o.getCgdrec();
				this.aanopc = o.getCgapag();
				this.amespc = o.getCgmpag();
				this.adiapc = o.getCgdpag();
				this.aanopm = o.getCgasam();
				this.amespm = o.getCgmsam();
				this.adiapm = o.getCgdsam();
				this.aanodi = fc.FormatoFechaHora("yyyy");
				this.amesdi = fc.FormatoFechaHora("MM");
				this.adiadi = fc.FormatoFechaHora("dd");
				this.numcue = "0" + this.cuenta;
				this.numche = o.getCgnche();
				this.tipope = "CHEQ";
				//this.aecrec = this.afecre;
				//this.fecpag = this.afecpc;
				//this.fecmul = this.afecpm;
				this.motrec = o.getCgrech();
				this.tipreg = o.getCgtipr();
				this.estado = o.getCgstat();
				
				
				this.comput = "";    
				this.compur = "";    
				this.revisi = "";    
				this.pcantm = "";    
				this.error = "";  
				cgrrcompadapter = Cgrrcomp.objetoCgrrcomp(ds, numcue, numche, tipope, "aecrec", "fecpag", "fecmul", motrec, tipreg, estado, comput, compur, revisi, pcantm, error);
				this.compu = "";
				if (cgrrcompadapter.getCOMPUT().equals(" 0")) {
					this.compu = "N";
				}
				if (cgrrcompadapter.getCOMPUT().equals(" 1")) {
					this.compu = "S";
				}
				if (cgrrcompadapter.getCOMPUT().equals(" " )) {
					this.compu = " ";
				}
				if (!cgrrcompadapter.getERROR().equals(" ")) {
					this.compu = " ";
				}
				this.revi = ""; 
				if (cgrrcompadapter.getREVISI().equals(" 0")) {
					this.revi = " ";
				}
				if (cgrrcompadapter.getREVISI().equals(" 1")) {
					this.revi = "R";
				}
				if (cgrrcompadapter.getREVISI().equals(" ")) {
					this.revi = " ";
				}
				if (!cgrrcompadapter.getERROR().equals(" ")) {
					this.revi = " ";
				}
				
				
				//--if (o.getCgrech().equals("2") || this.compu.equals("S")) {
					this.rrn3 = this.rrn3 + 1;
					//Grabar registro en Pantalla3 
					adapter = new CGRRCRECV03Adapter();
					adapter.setCCTE(this.ccte);
					adapter.setNUMCH(this.numch);
					adapter.setTIPR1(this.tipr1);
					adapter.setIMP(this.imp);
					adapter.setRECH(this.rech);
					adapter.setCOMPU(this.compu);
					adapter.setREVI(this.revi);
					adapter.setCUIT(this.cuit);
					adapter.setNRTR(this.nrtr);
					listAdapter.add(adapter);	
				//--}
				
				
				this.ccte = "";
				this.numch = "";
				this.compu = "";
				this.revi = "";
				this.rech = "";

		
			}
			
			
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
}

	private String SubRutLeesfl(DataSet ds) {
		try {
			if (listCgrprec != null ) {//&& this.numcta.equals("")
				this.error = "error";
			}
			if (this.rrn3 > 0) {
				this.limite = this.rrn3;
				//Mostrar Pantalla3 
				
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public class CGRRCRECV03Adapter {
		
		String CCTE = "";
		String NUMCH = "";
		String TIPR1 = "";
		BigDecimal IMP = new BigDecimal(0);
		String RECH = "";
		String COMPU = "";
		String REVI = "";
		Long CUIT = new Long("0");
		String NRTR = "";
		
		public CGRRCRECV03Adapter() {
			
		}

		public String getNRTR() {
			return NRTR;
		}

		public void setNRTR(String nRTR) {
			NRTR = nRTR;
		}

		public String getCCTE() {
			return CCTE;
		}

		public void setCCTE(String cCTE) {
			CCTE = cCTE;
		}

		public String getNUMCH() {
			return NUMCH;
		}

		public void setNUMCH(String nUMCH) {
			NUMCH = nUMCH;
		}

		public String getTIPR1() {
			return TIPR1;
		}

		public void setTIPR1(String tIPR1) {
			TIPR1 = tIPR1;
		}

		public BigDecimal getIMP() {
			return IMP;
		}

		public void setIMP(BigDecimal iMP) {
			IMP = iMP;
		}

		public String getRECH() {
			return RECH;
		}

		public void setRECH(String rECH) {
			RECH = rECH;
		}

		public String getCOMPU() {
			return COMPU;
		}

		public void setCOMPU(String cOMPU) {
			COMPU = cOMPU;
		}

		public String getREVI() {
			return REVI;
		}

		public void setREVI(String rEVI) {
			REVI = rEVI;
		}

		public Long getCUIT() {
			return CUIT;
		}

		public void setCUIT(Long cUIT) {
			CUIT = cUIT;
		}
		
	}
	
	
	

}
