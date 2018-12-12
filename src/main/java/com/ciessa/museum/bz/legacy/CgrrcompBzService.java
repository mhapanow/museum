package com.ciessa.museum.bz.legacy;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
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
						if (o.getCgtipr().equals("A") || o.getCgtipr().equals("M")) {
							this.fecpag = Integer.parseInt(o.getCgapag() + String.format("%02d", o.getCgmpag()) + String.format("%02d", o.getCgdpag()));
							this.fecmul = Integer.parseInt(o.getCgasam() + String.format("%02d", o.getCgmsam()) + String.format("%02d", o.getCgdsam()));
							this.fecrec = Integer.parseInt(o.getCgarec() + String.format("%02d", o.getCgmrec()) + String.format("%02d", o.getCgdrec()));
							this.estado = o.getCgstat();
							this.tipreg = o.getCgtipr();
							this.motrec = o.getCgrech();
							SubRutMulta(ds, o.getCgmult().toString(), o.getCgstat());
							SubRutRevis(ds, numcue);
							if (!this.aare.equals("X")) {
								SubRutCompu(ds);
							}else {
								SubRutCompu(ds);
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
								SubRutMulta(ds, o.getCgmult().toString(), o.getCgstat());
								this.x = 1;
								int position = Arrays.asList(this.che).indexOf(this.cgnche); 
								if (position == -1) {
									this.v = this.v + 1;
									this.che[this.v] = this.cgnche;
									this.rev[this.v] = "X";
									SubRutRevis(ds, numcue);
									if (!this.aare.equals("")) {
										this.ssrevi = this.ssrevi + 1;
									}
								}else {
									this.aare = this.rev[this.x];
								}
								if (!this.aare.equals("X")) {
									SubRutCompu(ds);
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
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			markEnd(start);
		}
		return adapter;
		
	}
	
	private String SubRutMulta(DataSet ds, String Cgmult, String Cgstat) {
		try {
			if (this.fecmu1.equals("00000000") && Integer.parseInt(this.fecre1) <= Integer.parseInt(this.frech) && Integer.parseInt(this.fecre1) >= 20040108 && Cgmult.equals("0") && Cgstat.equals("2")) {
				this.ssmult = this.ssmult + 1;
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutRevis(DataSet ds, String numcue) {
		try {
			this.aare = " ";
			listCgrprec = myDAOCgrprec.getUsingListNumcueAnd(ds, numcue);
			for(Cgrprec o:listCgrprec) {
				/*if (o.getC5tipr().equals("E") && o.getC5adbt().equals("0000")) {
					if (o.getC5cmcn().equals("21")) {
						this.aare = "X";
					}else {
						this.aare = "*";
					}
				}*/
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutCompu(DataSet ds, String motrec, String estado, String tipreg) {
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
								this.afecha = this.zsfout.toString();
								if (this.fecre1 >= 20011203 && this.fecre1 <= 20020215 && motrec.equals("1")) {
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
									if (this.fecre1 < 20011203 && motrec.equals("1") || this.fecre1 > 20020215 && motrec.equals("1")) {
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
									if (this.fecre1 >= 20011203 && this.fecre1 <= 20020215 && this.fecdia <= 20020308) {
										this.acompu = "";
									}else {
										this.dsfech="";
										this.zsdife = 
									}
								}
							}
						}
					}
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
		
	}

}
