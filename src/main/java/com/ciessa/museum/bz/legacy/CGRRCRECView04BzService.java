package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
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
	String afeaut = "";
	String userau = "";
	String det = "";
	
	Integer fecreg = 0;
	Integer afech = 0;
	String pend = "REG. PENDIENTE   ";  
	String auto = "REG. AUTORIZADO  ";  
	String baja = "REG. DADO DE BAJA";
	String bcra = "ELIMINADO DE BCRA";
	Integer fecpch = 0;
	Integer fecpm = 0;
	Integer fecrec = 0;
	Integer fecdbt = 0;
	Integer fecult = 0;
	Integer feccie = 0;
	Integer fecenv = 0;
	BigDecimal impmul = new BigDecimal(0);
	Integer fcdevo = 0;
	BigDecimal devmul = new BigDecimal(0);
	String dvacct = "";
	String dvnche = "";
	Integer ssfdev = 0;
	
	Integer fecaut = 0;
	
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
					"CTACTE",
					"NUMCHQ",
					"NSEC",
					"NUMSUC",
					"CAUSA",
					"TIPOR",
					"MENSA",
					"IMPORT",
					"FECREC",
					"FECREG",
					"PLADIF",
					"FECPCH",
					"FECDBT",
					"FECPM",
					"IMPMUL",
					"FCDEVO",
					"DEVMUL",
					"NUMREF",
					"FECCIE",
					"CUITL1",
					"CUITL2",
					"CUITL3",
					"CUITL4",
					"CUITL5",
					"CUITL6",
					"CUITL7",
					"CUITL8",
					"CUITL9",
					"CUITL0",
					"FECENV",
					"USERI",
					"FECULT",
					"USERAU",
					"FECAUT",
			};
			this.adapter = new CGRRCRECV04Adapter();
			adapter.setCTACTE(this.ctacte);
			adapter.setNUMCHQ(this.numchq);
			adapter.setNSEC(this.nsec);
			adapter.setNUMSUC(this.numsuc);
			adapter.setCAUSA(this.causa);
			adapter.setTIPOR(this.tipor);
			adapter.setMENSA(this.mensa);
			adapter.setIMPORT(this.imp0rt);
			adapter.setFECREC(this.fecrec);
			adapter.setFECREG(this.fecreg);
			adapter.setPLADIF(this.pladif);
			adapter.setFECPCH(this.fecpch);
			adapter.setFECDBT(this.fecdbt);
			adapter.setFECPM(this.fecpm);
			adapter.setIMPMUL(this.impmul);
			adapter.setFCDEVO(this.fcdevo);
			adapter.setDEVMUL(this.devmul);
			adapter.setNUMREF(this.numref);
			adapter.setFECCIE(this.feccie);
			adapter.setCUITL1(this.cuitl1);
			adapter.setCUITL2(this.cuitl2);
			adapter.setCUITL3(this.cuitl3);
			adapter.setCUITL4(this.cuitl4);
			adapter.setCUITL5(this.cuitl5);
			adapter.setCUITL6(this.cuitl6);
			adapter.setCUITL7(this.cuitl7);
			adapter.setCUITL8(this.cuitl8);
			adapter.setCUITL9(this.cuitl9);
			adapter.setCUITL0(this.cuitl0);
			adapter.setFECENV(this.fecenv);
			adapter.setUSERI(this.useri);
			adapter.setFECULT(this.fecult);
			adapter.setUSERAU(this.userau);
			adapter.setFECAUT(this.fecaut);
			
			
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
			this.acuent = "0" + this.cuenta;
			
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
				this.afech = Integer.parseInt(this.adia)*10000 + Integer.parseInt(this.ames) * 100 + Integer.parseInt(this.aano);
				this.fecreg = this.afech;
				if (objCgrprec.getCgstat().equals("1")) {
					this.mensa = this.pend;
				}else {
					if (objCgrprec.getCgstat().equals("2")) {
						this.mensa = this.auto;
					}else {
						if (objCgrprec.getCgstat().equals("3") || objCgrprec.getCgstat().equals("4") ) {
							this.mensa = this.baja;
						}else {
							this.mensa = "";
						}
					}
				}
				this.aano = objCgrprec.getCgapag().substring(3-1,4);
				this.ames = objCgrprec.getCgmpag();
				this.adia = objCgrprec.getCgdpag();
				this.afech = Integer.parseInt(this.adia)*10000 + Integer.parseInt(this.ames) * 100 + Integer.parseInt(this.aano);
				this.fecpch = this.afech;
				this.aano = objCgrprec.getCgasam().substring(3-1,4);
				this.ames = objCgrprec.getCgmsam();
				this.adia = objCgrprec.getCgdsam();
				this.afech = Integer.parseInt(this.adia)*10000 + Integer.parseInt(this.ames) * 100 + Integer.parseInt(this.aano);
				this.fecpm = this.afech;
				this.aano = objCgrprec.getCgarec().substring(3-1,4);
				this.ames = objCgrprec.getCgmrec();
				this.adia = objCgrprec.getCgdrec();
				this.afech = Integer.parseInt(this.adia)*10000 + Integer.parseInt(this.ames) * 100 + Integer.parseInt(this.aano);
				this.fecrec = this.afech;
				this.aano = objCgrprec.getCgadbt().substring(3-1,4);
				this.ames = objCgrprec.getCgmdbt();
				this.adia = objCgrprec.getCgddbt();
				this.afech = Integer.parseInt(this.adia)*10000 + Integer.parseInt(this.ames) * 100 + Integer.parseInt(this.aano);
				this.fecdbt = this.afech;
				this.aano = objCgrprec.getCgfeuc().toString().substring(3-1, 4);
				this.ames = objCgrprec.getCgfeuc().toString().substring(5-1, 6);
				this.adia = objCgrprec.getCgfeuc().toString().substring(7-1, 8);
				this.afech = Integer.parseInt(this.adia)*10000 + Integer.parseInt(this.ames) * 100 + Integer.parseInt(this.aano);
				this.fecult = this.afech;
				this.useri = "";
				this.useri = objCgrprec.getCgusri();
				this.pladif = objCgrprec.getCgpdif();
				this.aano = objCgrprec.getCgacie().substring(3-1,4);
				this.ames = objCgrprec.getCgmcie();
				this.adia = objCgrprec.getCgdcie();
				this.afech = Integer.parseInt(this.adia)*10000 + Integer.parseInt(this.ames) * 100 + Integer.parseInt(this.aano);
				this.feccie = this.afech;
				this.numref = objCgrprec.getCgnref();
				this.aano = objCgrprec.getCgfenr().toString().substring(3-1, 4);
				this.ames = objCgrprec.getCgfenr().toString().substring(5-1, 6);
				this.adia = objCgrprec.getCgfenr().toString().substring(7-1, 8);
				this.afech = Integer.parseInt(this.adia)*10000 + Integer.parseInt(this.ames) * 100 + Integer.parseInt(this.aano);
				this.fecenv = this.afech;
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
				
				this.afeaut = objCgrprec.getCgfaut().toString();
				this.fecaut = Integer.parseInt(this.afeaut.substring(7-1, 8) + this.afeaut.substring(5-1, 6) + this.afeaut.substring(3-1, 4));
				/*this.fecaut = this.adiaau;
				this.aauxau = this.amesau;
				this.aauxau = this.aanoau;
				this.fecaut = this.aauxau;*/
				this.userau = "";
				this.userau = objCgrprec.getCgusra();
				SubRutDevolu(ds);
				objCgrprec = myDAOCgrprec.getUsingCgacctAndCgnche(ds, objCgrprec.getCgacct(), objCgrprec.getCgnche());
				if (objCgrprec != null) {
					if (objCgrprec.getCgtipr().equals("B"))
						this.mensa = this.bcra;
				}//fin if null
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
			this.impmul = objCgrprec.getCgmult();
			this.fcdevo = 0;
			this.devmul = BigDecimal.ZERO;
			this.dvacct = this.ctacte;
			this.dvnche = this.numchq;
			if (objCgrprec.getCgfdev() != 0) {
				this.ssfdev = objCgrprec.getCgfdev();
				/*this.diadev = this.ssddev;
				this.mesdev = this.ssmdev;
				this.anodev = this.ssadev;*/
				this.impmul = objCgrprec.getCgmult();
				this.devmul = objCgrprec.getCgmult();
			}
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
}
	
	
	
	
	public class CGRRCRECV04Adapter {
		
		String CTACTE = "";
		String NUMCHQ = "";
		String NSEC = "";
		String NUMSUC = "";
		String CAUSA = "";
		String TIPOR = "";
		String MENSA = "";
		String IMPORT = "";
		Integer FECREC = 0;
		Integer FECREG = 0;
		String PLADIF = "";
		Integer FECPCH = 0;
		Integer FECDBT = 0;
		Integer FECPM = 0;
		BigDecimal IMPMUL = new BigDecimal(0);
		Integer FCDEVO = 0;
		BigDecimal DEVMUL = new BigDecimal(0);
		Long NUMREF = new Long("0");
		Integer FECCIE = 0;
		String CUITL1 = "";
		String CUITL2 = "";
		String CUITL3 = "";
		String CUITL4 = "";
		String CUITL5 = "";
		String CUITL6 = "";
		String CUITL7 = "";
		String CUITL8 = "";
		String CUITL9 = "";
		String CUITL0 = "";
		Integer FECENV = 0;
		String USERI = "";
		Integer FECULT = 0;
		String USERAU = "";
		Integer FECAUT = 0;
		
		public CGRRCRECV04Adapter () {
			
		}

		public String getCTACTE() {
			return CTACTE;
		}

		public void setCTACTE(String cTACTE) {
			CTACTE = cTACTE;
		}

		public String getNUMCHQ() {
			return NUMCHQ;
		}

		public void setNUMCHQ(String nUMCHQ) {
			NUMCHQ = nUMCHQ;
		}

		public String getNSEC() {
			return NSEC;
		}

		public void setNSEC(String nSEC) {
			NSEC = nSEC;
		}

		public String getNUMSUC() {
			return NUMSUC;
		}

		public void setNUMSUC(String nUMSUC) {
			NUMSUC = nUMSUC;
		}

		public String getCAUSA() {
			return CAUSA;
		}

		public void setCAUSA(String cAUSA) {
			CAUSA = cAUSA;
		}

		public String getTIPOR() {
			return TIPOR;
		}

		public void setTIPOR(String tIPOR) {
			TIPOR = tIPOR;
		}

		public String getMENSA() {
			return MENSA;
		}

		public void setMENSA(String mENSA) {
			MENSA = mENSA;
		}

		public String getIMPORT() {
			return IMPORT;
		}

		public void setIMPORT(String iMPORT) {
			IMPORT = iMPORT;
		}

		public Integer getFECREC() {
			return FECREC;
		}

		public void setFECREC(Integer fECREC) {
			FECREC = fECREC;
		}

		public Integer getFECREG() {
			return FECREG;
		}

		public void setFECREG(Integer fECREG) {
			FECREG = fECREG;
		}

		public String getPLADIF() {
			return PLADIF;
		}

		public void setPLADIF(String pLADIF) {
			PLADIF = pLADIF;
		}

		public Integer getFECPCH() {
			return FECPCH;
		}

		public void setFECPCH(Integer fECPCH) {
			FECPCH = fECPCH;
		}

		public Integer getFECDBT() {
			return FECDBT;
		}

		public void setFECDBT(Integer fECDBT) {
			FECDBT = fECDBT;
		}

		public Integer getFECPM() {
			return FECPM;
		}

		public void setFECPM(Integer fECPM) {
			FECPM = fECPM;
		}

		public BigDecimal getIMPMUL() {
			return IMPMUL;
		}

		public void setIMPMUL(BigDecimal iMPMUL) {
			IMPMUL = iMPMUL;
		}

		public Integer getFCDEVO() {
			return FCDEVO;
		}

		public void setFCDEVO(Integer fCDEVO) {
			FCDEVO = fCDEVO;
		}

		public BigDecimal getDEVMUL() {
			return DEVMUL;
		}

		public void setDEVMUL(BigDecimal dEVMUL) {
			DEVMUL = dEVMUL;
		}

		public Long getNUMREF() {
			return NUMREF;
		}

		public void setNUMREF(Long nUMREF) {
			NUMREF = nUMREF;
		}

		public Integer getFECCIE() {
			return FECCIE;
		}

		public void setFECCIE(Integer fECCIE) {
			FECCIE = fECCIE;
		}

		public String getCUITL1() {
			return CUITL1;
		}

		public void setCUITL1(String cUITL1) {
			CUITL1 = cUITL1;
		}

		public String getCUITL2() {
			return CUITL2;
		}

		public void setCUITL2(String cUITL2) {
			CUITL2 = cUITL2;
		}

		public String getCUITL3() {
			return CUITL3;
		}

		public void setCUITL3(String cUITL3) {
			CUITL3 = cUITL3;
		}

		public String getCUITL4() {
			return CUITL4;
		}

		public void setCUITL4(String cUITL4) {
			CUITL4 = cUITL4;
		}

		public String getCUITL5() {
			return CUITL5;
		}

		public void setCUITL5(String cUITL5) {
			CUITL5 = cUITL5;
		}

		public String getCUITL6() {
			return CUITL6;
		}

		public void setCUITL6(String cUITL6) {
			CUITL6 = cUITL6;
		}

		public String getCUITL7() {
			return CUITL7;
		}

		public void setCUITL7(String cUITL7) {
			CUITL7 = cUITL7;
		}

		public String getCUITL8() {
			return CUITL8;
		}

		public void setCUITL8(String cUITL8) {
			CUITL8 = cUITL8;
		}

		public String getCUITL9() {
			return CUITL9;
		}

		public void setCUITL9(String cUITL9) {
			CUITL9 = cUITL9;
		}

		public String getCUITL0() {
			return CUITL0;
		}

		public void setCUITL0(String cUITL0) {
			CUITL0 = cUITL0;
		}

		public Integer getFECENV() {
			return FECENV;
		}

		public void setFECENV(Integer fECENV) {
			FECENV = fECENV;
		}

		public String getUSERI() {
			return USERI;
		}

		public void setUSERI(String uSERI) {
			USERI = uSERI;
		}

		public Integer getFECULT() {
			return FECULT;
		}

		public void setFECULT(Integer fECULT) {
			FECULT = fECULT;
		}

		public String getUSERAU() {
			return USERAU;
		}

		public void setUSERAU(String uSERAU) {
			USERAU = uSERAU;
		}

		public Integer getFECAUT() {
			return FECAUT;
		}

		public void setFECAUT(Integer fECAUT) {
			FECAUT = fECAUT;
		}

		
		
	}

}
