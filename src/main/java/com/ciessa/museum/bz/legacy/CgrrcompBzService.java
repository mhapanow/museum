package com.ciessa.museum.bz.legacy;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.bz.legacy.Cus060BzService.CUS060Adapter;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.CgrprecDAO;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Cgrprec;

public class CgrrcompBzService extends RestBaseServerResource {
public static final Logger log = Logger.getLogger(CgrrcompBzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	CgrprecDAO myDAOCgrprec;
	
	List<Cgrprec> listCgrprec = null;
	List<Cgrprec> listCgrprecRevis = null;
	
	String paramwkbank = "";
	String paramnumcue = "";
	String paramnumche = "";
	String paramtipope = "";
	String paramfecrec = "";
	String paramfecpag = "";
	String paramfecmul = "";
	String parammotrec = "";
	String paramtipreg = "";
	String paramestado = "";
	String paramcomput = "";
	String paramcompur = "";
	String paramrevisi = "";
	String parampcantm = "";
	String paramerror = "";
		
	String asfech = "";
	//String addmm = "";
	String diapro = "";
	String mespro = "";
	String anopro = "";
	String acompu = "";
	String dsfech = "";
	String zsruti = "";
	Integer zsfin1 = 0;
	//String zsrin1 = "";
	//String zsrout = "";
	//String zsoper = "";
	//String zscret = "";
	Integer ftope = 0;
	Integer zsfout = 0;
	Integer fecdia = 0;
	//Integer zsqdia = 0;
	//Integer dias = 0;
	
	Integer frech = 0;
	Integer ssmult = 0;
	Integer scanch = 0;
	Integer scanti = 0;
	Integer scant2 = 0;
	Integer scanc3 = 0;
	Integer cinco = 0;
	Integer cuatro = 0;
	Integer tres = 0;
	Integer dos = 0;
	String scheq = "";
	Integer difer = 0;
	Integer ssrevi = 0;
	
	Integer fecpag = 0;
	//String fecpa1 = "";
	Integer fecmul = 0;
	//String fecmu1 = "";
	Integer fecrec = 0;
	//String fecre1 = "";
	String estado = "";
	String tipreg = "";
	String motrec = "";
	String aare = "";
	String cgnche = "";
	Integer v = 0;
	Integer x = 0;
	String[] che = new String[9999];
	String[] rev = new String[9999];
	Integer zsdife = 0;
	Integer afecha = 0;
	
	//
	FUNCIONESBzService func = new FUNCIONESBzService();
	CgrrcompAdapter adapter = null;
	
	public CgrrcompAdapter objetoCgrrcomp(DataSet ds, String numcue, String numche, String tipope, String fecrec, String fecpag, String fecmul, String motrec, String tipreg, String estado, String comput, String compur, String revisi, String pcantm, String error) {
		long start = markStart();
		try {
			this.asfech = func.FormatoFechaHora("ddMMyyyy");
			this.diapro = func.FormatoFechaHora("dd");
			this.mespro = func.FormatoFechaHora("MM");
			this.anopro = func.FormatoFechaHora("yyyy");
			this.acompu = "";
			this.dsfech = "";
			this.zsruti = "SADDSUB";
			this.fecdia = Integer.parseInt(func.FormatoFechaHora("yyyyMMdd"));
			this.zsfin1 = this.fecdia;
			this.zsfout = this.fecdia -365;
			this.ftope = this.zsfout;
			
			this.dsfech = "";
			this.zsruti = "SADDSUB";
			this.zsfin1 = this.fecdia;
			this.zsfout = this.fecdia - Integer.parseInt(func.FormatoFechaHora("dd"));//DIAS
			
			this.frech = this.zsfout;
			
			this.ssmult = 0;
			this.acompu = "";    
			this.difer = 0;
			this.ssmult = 0;
			if (tipope.equals("CHEQ")) {
				this.scanch = 0;
				this.scanc3 = 0;
				this.scanti = 0;
				this.scant2 = 0;
				this.cinco = 0;
				this.cuatro = 0;
				this.tres = 0;
				this.dos = 0;
				this.scheq = numche;
				error = "";
				if (numche.equals("")) {
					error = "ERROR";
				}
				if (numcue.equals("")) {
					error = "ERROR";
				}
				
				if (error.equals("")) {
					listCgrprec = myDAOCgrprec.getUsingListNumcue(ds, numcue);
					for(Cgrprec o:listCgrprec) {
						this.cgnche = o.getCgnche();
						if (o.getCgtipr().equals("A") || o.getCgtipr().equals("M")) {
							this.fecpag = Integer.parseInt(o.getCgapag() + String.format("%02d", o.getCgmpag()) + String.format("%02d", o.getCgdpag()));
							this.fecmul = Integer.parseInt(o.getCgasam() + String.format("%02d", o.getCgmsam()) + String.format("%02d", o.getCgdsam()));
							this.fecrec = Integer.parseInt(o.getCgarec() + String.format("%02d", o.getCgmrec()) + String.format("%02d", o.getCgdrec()));
							this.estado = o.getCgstat();
							this.tipreg = o.getCgtipr();
							this.motrec = o.getCgrech();
							SubRutMulta(ds, o.getCgmult().toString(), o.getCgstat(), this.fecmul.toString(), this.fecrec);
							SubRutRevis(ds, numcue, this.cgnche);
							if (!this.aare.equals("X")) {
								SubRutCompu(ds, motrec, estado, tipreg, this.fecrec);
							}else {
								SubRutCompu(ds, motrec, estado, tipreg, this.fecrec);
								if (this.acompu.equals("1")) {
									this.acompu = "0";
								}
							}
							if ( o.getCgnche().equals(this.scheq)) {
								continue;
							}
						}
					}//fin for
					if (this.acompu.equals(" ") ) {
						comput = "";
					}
					if (this.acompu.equals("0") ) {
						comput = "0";
					}
					if (this.acompu.equals("1")) {
						comput = "1";
					}
				}
				if (!this.aare.equals(" ")) {
					revisi = "1";
				}else {
					revisi = "0";
				}
				pcantm = this.ssmult.toString();
			}else {
				if (tipope.equals("CANT")) {
					this.scanch = 0;
					this.scanti = 0;
					this.scant2 = 0;
					this.scanc3 = 0;
					this.cinco = 0;
					this.cuatro = 0;
					this.tres = 0;
					this.dos = 0;
					this.ssrevi = 0;
					error = "";
					if (!numche.equals("")) {
						error = "ERROR";
					}
					if (numcue.equals("" )) {
						error = "ERROR";
					}
					if (error.equals("")) {
						this.v = 0;
						Arrays.fill(this.che, "");
						Arrays.fill(this.rev, "");
						listCgrprec = myDAOCgrprec.getUsingListNumcue(ds, numcue);
						for(Cgrprec o:listCgrprec) {
							this.cgnche = o.getCgnche();
							if (o.getCgtipr().equals("A") || o.getCgtipr().equals("M")) {
								this.fecpag = Integer.parseInt(o.getCgapag() + String.format("%02d", o.getCgmpag()) + String.format("%02d", o.getCgdpag()));
								this.fecmul = Integer.parseInt(o.getCgasam() + String.format("%02d", o.getCgmsam()) + String.format("%02d", o.getCgdsam()));
								this.fecrec = Integer.parseInt(o.getCgarec() + String.format("%02d", o.getCgmrec()) + String.format("%02d", o.getCgdrec()));
								this.estado = o.getCgstat();
								this.tipreg = o.getCgtipr();
								this.motrec = o.getCgrech();
								SubRutMulta(ds, o.getCgmult().toString(), o.getCgstat(), this.fecmul.toString(), this.fecrec);
								this.x = 1;
								int position = Arrays.asList(this.che).indexOf(this.cgnche); 
								if (position == -1) {
									this.v = this.v + 1;
									this.che[this.v] = this.cgnche;
									this.rev[this.v] = "X";
									SubRutRevis(ds, numcue, this.cgnche);
									if (!this.aare.equals("")) {
										this.ssrevi = this.ssrevi + 1;
									}
								}else {
									this.aare = this.rev[this.x];
								}
								if (!this.aare.equals("X")) {
									SubRutCompu(ds, motrec, estado, tipreg, this.fecrec);
								}
							}//fin if equals
						}//fin for list
						comput = this.scanti.toString();
						compur = this.scant2.toString();
						revisi = this.ssrevi.toString();
						pcantm = this.ssmult.toString();
					}//fin if error
				}
			}
			
			//--String paramwkbank = paramwkbank;
			String paramnumcue = numcue;
			String paramnumche = numche;
			String paramtipope = tipope;
			String paramfecrec = fecrec;
			String paramfecpag = fecpag;
			String paramfecmul = fecmul;
			String parammotrec = motrec;
			String paramtipreg = tipreg;
			String paramestado = estado;
			String paramcomput = comput;
			String paramcompur = compur;
			String paramrevisi = revisi;
			String parampcantm = pcantm;
			String paramerror = error;
			
			adapter = new CgrrcompAdapter();
			adapter.setNUMCUE(paramnumcue);
			adapter.setNUMCHE(paramnumche);
			adapter.setTIPOPE(paramtipope);
			adapter.setFECREC(paramfecrec);
			adapter.setFECPAG(paramfecpag);
			adapter.setFECMUL(paramfecmul);
			adapter.setMOTREC(parammotrec);
			adapter.setTIPREG(paramtipreg);
			adapter.setESTADO(paramestado);
			adapter.setCOMPUT(paramcomput);
			adapter.setCOMPUR(paramcompur);
			adapter.setREVISI(paramrevisi);
			adapter.setPCANTM(parampcantm);
			adapter.setERROR(paramerror);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			markEnd(start);
		}
		return adapter;
		
	}
	
	private String SubRutMulta(DataSet ds, String Cgmult, String Cgstat, String fecmu1, Integer fecre1) {
		try {
			if (fecmu1.equals("00000000") && fecre1 <= this.frech && fecre1 >= 20040108 && Cgmult.equals("0") && Cgstat.equals("2")) {
				this.ssmult = this.ssmult + 1;
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutRevis(DataSet ds, String numcue, String cgnche) {
		try {
			this.aare = " ";
			listCgrprecRevis = myDAOCgrprec.getUsingListNumcueAndCgnche(ds, numcue, cgnche);
			for(Cgrprec o:listCgrprecRevis) {
				if (o.getCgtipr().equals("E") && o.getCgadbt().equals("0000")) {
					if (o.getCgcmcn().equals("21")) {
						this.aare = "X";
					}else {
						this.aare = "*";
					}
				}
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutCompu(DataSet ds, String motrec, String estado, String tipreg, Integer fecre1) {
		try {
			this.acompu = "0";
			if (motrec.equals("2")) {
				this.acompu = "2";
			}else {
				if (this.fecrec >= this.ftope) {
					if (this.fecrec >= 20011014 && this.fecpag <= 20011014 && (this.fecpag > 0 || this.fecpag != null) ) {
						this.acompu = "0";
					}
					if (this.fecrec < 20000301) {
						this.acompu = "0";
					}
					if (estado.equals("3") || estado.equals("4") || estado.equals("5")) {
						this.acompu = "0";
					}
					if (tipreg.equals("B")) {
						this.acompu = "0";
					}
					if (tipreg.equals("A") || tipreg.equals("M")) {
						if (motrec.equals("1") || motrec.equals("3")) {
							if (this.fecpag > 0 || this.fecpag != null) {
								this.dsfech = "";
								this.zsfout = this.fecrec +20;
								this.afecha = this.zsfout;
								if (fecre1 >= 20011203 && fecre1 <= 20020215 && motrec.equals("1")) {
									if (this.fecpag <= 20020308) {
										if (this.cinco < 5) {
											this.cinco = this.cinco + 1;
										}else {
											if (this.tres < 3) {
												this.tres = this.tres + 1;
											}else {
												this.acompu = "1";
												this.scanti = this.scanti + 1;
											}
										}
										if (this.cuatro < 4) {
											this.cuatro = this.cuatro + 1;
										}else {
											if (this.dos < 2) {
												this.dos = this.dos + 1;
											}else {
												this.scant2 = this.scant2 + 1;
											}
										}
									}else {
										this.acompu = "1";
										this.scant2 = this.scant2 + 1;
										this.scanti = this.scanti + 1;	
									}
									if (fecre1 < 20011203 && motrec.equals("1") || fecre1 > 20020215 && motrec.equals("1")) {
										if (this.fecpag <= this.afecha) {
											if (this.tres < 3) {
												this.tres = this.tres + 1;
											}else {
												this.acompu = "1";
												this.scanti = this.scanti + 1;
											}
											if (this.dos < 2) {
												this.dos = this.dos + 1;
											}else {
												this.scant2 = this.scant2 + 1;
											}
										}else {
											this.acompu = "1";
											this.scant2 = this.scant2 + 1;
											this.scanti = this.scanti + 1;
										}
									}
								}
								if (this.fecpag == 0 || this.fecpag == null) {
									if (fecre1 >= 20011203 && fecre1 <= 20020215 && this.fecdia <= 20020308) {
										this.acompu = "";
									}else {
										this.dsfech="";
										SimpleDateFormat dateFormats = new SimpleDateFormat("yyyyMMdd");
										Date fechaInicial = dateFormats.parse(this.fecrec.toString());
										Date fechaFinal = dateFormats.parse(this.fecdia.toString());
										int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
										this.zsdife = dias;
										this.difer = this.zsdife;
										if (this.difer > 25) {
											this.scanti = this.scanti +1;
											this.scant2 = this.scant2 +1;
											this.acompu = "1";
										}else {
											this.acompu = "";
										}
									}
								}
							}
						}
					}
				}else {
					this.acompu="0";
				}
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	
	
	public class CgrrcompAdapter {
		String NUMCUE = "";
		String NUMCHE = "";
		String TIPOPE = "";
		String FECREC = "";
		String FECPAG = "";
		String FECMUL = "";
		String MOTREC = "";
		String TIPREG = "";
		String ESTADO = "";
		String COMPUT = "";
		String COMPUR = "";
		String REVISI = "";
		String PCANTM = "";
		String ERROR = "";
		
		public CgrrcompAdapter(){
			
		}

		public String getNUMCUE() {
			return NUMCUE;
		}

		public void setNUMCUE(String nUMCUE) {
			NUMCUE = nUMCUE;
		}

		public String getNUMCHE() {
			return NUMCHE;
		}

		public void setNUMCHE(String nUMCHE) {
			NUMCHE = nUMCHE;
		}

		public String getTIPOPE() {
			return TIPOPE;
		}

		public void setTIPOPE(String tIPOPE) {
			TIPOPE = tIPOPE;
		}

		public String getFECREC() {
			return FECREC;
		}

		public void setFECREC(String fECREC) {
			FECREC = fECREC;
		}

		public String getFECPAG() {
			return FECPAG;
		}

		public void setFECPAG(String fECPAG) {
			FECPAG = fECPAG;
		}

		public String getFECMUL() {
			return FECMUL;
		}

		public void setFECMUL(String fECMUL) {
			FECMUL = fECMUL;
		}

		public String getMOTREC() {
			return MOTREC;
		}

		public void setMOTREC(String mOTREC) {
			MOTREC = mOTREC;
		}

		public String getTIPREG() {
			return TIPREG;
		}

		public void setTIPREG(String tIPREG) {
			TIPREG = tIPREG;
		}

		public String getESTADO() {
			return ESTADO;
		}

		public void setESTADO(String eSTADO) {
			ESTADO = eSTADO;
		}

		public String getCOMPUT() {
			return COMPUT;
		}

		public void setCOMPUT(String cOMPUT) {
			COMPUT = cOMPUT;
		}

		public String getCOMPUR() {
			return COMPUR;
		}

		public void setCOMPUR(String cOMPUR) {
			COMPUR = cOMPUR;
		}

		public String getREVISI() {
			return REVISI;
		}

		public void setREVISI(String rEVISI) {
			REVISI = rEVISI;
		}

		public String getPCANTM() {
			return PCANTM;
		}

		public void setPCANTM(String pCANTM) {
			PCANTM = pCANTM;
		}

		public String getERROR() {
			return ERROR;
		}

		public void setERROR(String eRROR) {
			ERROR = eRROR;
		}
		
	}

}
