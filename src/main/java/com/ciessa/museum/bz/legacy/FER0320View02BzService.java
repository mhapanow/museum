package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.AltnamDAO;
import com.ciessa.museum.dao.legacy.CbipticDAO;
import com.ciessa.museum.dao.legacy.Cfp001005DAO;
import com.ciessa.museum.dao.legacy.Cfp001207DAO;
import com.ciessa.museum.dao.legacy.CumastDAO;
import com.ciessa.museum.dao.legacy.Cuxrf1DAO;
import com.ciessa.museum.dao.legacy.Tap002wDAO;
import com.ciessa.museum.dao.legacy.Tap003DAO;
import com.ciessa.museum.dao.legacy.Tap901DAO;
import com.ciessa.museum.dao.legacy.Tap902DAO;
import com.ciessa.museum.dao.legacy.TranemDAO;
import com.ciessa.museum.dao.legacy.TransmDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Altnam;
import com.ciessa.museum.model.legacy.Cbiptic;
import com.ciessa.museum.model.legacy.Cfp001005;
import com.ciessa.museum.model.legacy.Cfp001207;
import com.ciessa.museum.model.legacy.Cumast;
import com.ciessa.museum.model.legacy.Cuxrf1;
import com.ciessa.museum.model.legacy.Tap002w;
import com.ciessa.museum.model.legacy.Tap003;
import com.ciessa.museum.model.legacy.Tap901;
import com.ciessa.museum.model.legacy.Tap902;
import com.ciessa.museum.model.legacy.Tranem;
import com.ciessa.museum.model.legacy.Transm;
import com.ciessa.museum.tools.CollectionFactory;

public class FER0320View02BzService extends RestBaseServerResource{
	public static final Logger log = Logger.getLogger(FER0320View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	Cfp001005DAO myDAOCfp001005;
	
	@Autowired
	Cfp001207DAO myDAOCfp001207;
	
	@Autowired
	Tap002wDAO myDAOTap002w;
	
	@Autowired
	CbipticDAO myDAOCbiptic;
	
	@Autowired
	Tap902DAO myDAOTap902;
	
	@Autowired
	AltnamDAO myDAOAltnam;
	
	@Autowired
	Cuxrf1DAO myDAOCuxrf1;
	
	@Autowired
	TransmDAO myDAOTransm;
	
	@Autowired
	TranemDAO myDAOTranem;
	
	@Autowired
	CumastDAO myDAOCumast;
	
	@Autowired
	Tap003DAO myDAOTap003;
	
	@Autowired
	Tap901DAO myDAOTap901;
	
	
	Cfp001005 objCfp001005 = new Cfp001005();
	Cfp001207 objCfp001207 = new Cfp001207();
	Tap002w objTap002w = new Tap002w();
	Cbiptic objCbiptic = new Cbiptic();
	Tap902 objTap902 = new Tap902();
	Altnam objAltnam = new Altnam();
	Cumast objCumast = new Cumast();
	
	List<Tap902> listTap902 = null;
	List<Cuxrf1> listCuxrf1 = null;
	List<Transm> listTransm = null;
	List<Tranem> listTranem = null;
	List<Tap003> listTap003 = null;
	List<Tap901> listTap901 = null;
	
	String wcta = "";
	String amar = "";
	String wncta = "";
	
	String arec = "";
	Date fecha = null;
	Date udate = null;
	Integer wpsav = 0;
	Integer wpdda = 0;
	Integer acodr = 0;
	String clavex = "";
	Integer f8 = 0;
	Integer fecdia = 0;
	String codeco = "1";
	Boolean error = false;
	Integer a4 = 0;
	Integer a1 = 0;
	String dmtyp1 = "";
	
	String wbatch = "";
	Integer afecva = 0;
	
	String[] ind67 = new String[1];
	String[] ind68 = new String[1];
	Integer nrr = 0;
	BigDecimal wssald = new BigDecimal(0);
	BigDecimal ascrha = new BigDecimal(0);
	Long ancta = new Long("0");
	BigDecimal disdbu = new BigDecimal(0);
	BigDecimal discbu = new BigDecimal(0);
	BigDecimal disda = new BigDecimal(0);
	BigDecimal disca = new BigDecimal(0);
	BigDecimal acrhoy = new BigDecimal(0);
	BigDecimal adbpen = new BigDecimal(0);
	BigDecimal scrhoy = new BigDecimal(0);
	BigDecimal sdbhoy = new BigDecimal(0);
	BigDecimal sdbpen = new BigDecimal(0);
	BigDecimal adbhoy = new BigDecimal(0);
	BigDecimal sreten = new BigDecimal(0);
	BigDecimal areten = new BigDecimal(0);
	Integer aaplic = 0;
	Integer t = 0;
	Long ancta1 = new Long("0");
	String segmen = "";
	BigDecimal tipcam = new BigDecimal(0);
	Integer acmcn = 0;
	Integer amtyp = 0;
	BigDecimal dmcbal = new BigDecimal(0);
	BigDecimal asdisp = new BigDecimal(0);
	BigDecimal wsdisp = new BigDecimal(0);
	BigDecimal wsinmo = new BigDecimal(0);
	BigDecimal stope = new BigDecimal(0);
	Long[] sr5 = new Long[9];
	Long dmiodl = new Long("0");
	String abnk = "";
	Integer aapdes = 0;
	Long acuent = new Long("0");
	Integer arcd = 0;
	String acta = "";
	BigDecimal scrpen = new BigDecimal(0);
	String ncbat = "";
	BigDecimal wsdebi = new BigDecimal(0);
	BigDecimal wscred = new BigDecimal(0);
	String cref1 = "";
	Integer fecval = 0;
	Integer fecing = 0;
	BigDecimal aimpor = new BigDecimal(0);
	Integer adaava = 0;
	Integer admmva = 0;
	Integer adddva = 0;
	String dshbk = "";
	String atpcta = "";
	String dshdsv = "";
	Long acuen1 = new Long("0");
	Long dsacct = new Long("0");
	Integer dsser = 0;
	String dstype = "";
	Integer ssssyy = 0;
	Integer fecimn = 0;
	
	Integer dmbrch = 0;
	Integer dmcmcn = 0;
	String wnomb1 = "";
	String wnomb2 = "";
	String wnomb3 = "";
	
	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
	FER0320V2Adapter adapter = null;
	List<FER0320V2Adapter> listAdapter = null;
	
	
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			this.wcta = obtainStringValue("wcta", null);
			this.amar = obtainStringValue("amar", null);
			
			
			this.arec = "1";
			this.udate = fc.FechaActual("ddMMyyyy");
			this.fecha = this.udate;
			
			this.ind68[0] = "0";
			this.ind67[0] = "0";
			
			listAdapter = new ArrayList<FER0320V2Adapter>();
			
			SubRutUnica(ds);
			SubRutConsi(ds);
			if (!this.error) {
				SubRutCarga(ds);
				this.dmbrch = objTap002w.getDmbrch();
				this.dmcmcn = objTap002w.getDmcmcn();
				//Mostrar Pantalla2 
			}
			this.amar = "0";

			// retrieve all elements
			Map<String,String> attributes = CollectionFactory.createMap();
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found [" + listAdapter.size() + "] in " + diff + " millis");
			
			String[] fields = new String[] {
					"WNCTA",
					"CODECO",
					"DMBRCH",
					"DMCMCN",
					"WNOMB1",
					"WNOMB2",
					"WNOMB3",
					"DMIODL",
					"WSDISP",
			};
			
			String[] arrayFields = new String[] {
					"AFECVA",
					"CTRAEX",
					"WBATCH",
					"CREF1",
					"WSDEBI",
					"WSCRED",
					"WSSALD",
			};
			
			returnValue = getJSONRepresentationFromObject(adapter, fields);
			returnValue.put("data", getJSONRepresentationFromArrayOfObjects(listAdapter, arrayFields).get("data"));
			returnValue.put("WNCTA", this.wncta);
			returnValue.put("CODECO", this.codeco);
			returnValue.put("DMBRCH", this.dmbrch);
			returnValue.put("DMCMCN", this.dmcmcn);
			returnValue.put("WNOMB1", this.wnomb1);
			returnValue.put("WNOMB2", this.wnomb2);
			returnValue.put("WNOMB3", this.wnomb3);
			returnValue.put("DMIODL", this.dmiodl);
			returnValue.put("WSDISP", this.wsdisp);
			
			
			
			if( attributes.containsKey("recordCount"))
				returnValue.put("recordCount", Long.valueOf(attributes.get("recordCount")));
			else 
				returnValue.put("recordCount", listAdapter.size());
			
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
	
	private String SubRutUnica(DataSet ds) {
		try {
			objCfp001005 = myDAOCfp001005.getUsingKey(ds);
			if (objCfp001005 != null) {
				this.wpsav = objCfp001005.getCfpsav();
				this.wpdda = objCfp001005.getCfpdda();
			}
			objCfp001207 = myDAOCfp001207.getUsingKey(ds);
			this.sr5[0] = objCfp001207.getCflim1();
			this.sr5[1] = objCfp001207.getCflim2();
			this.sr5[2] = objCfp001207.getCflim3();
			this.sr5[3] = objCfp001207.getCflim4();
			this.sr5[4] = objCfp001207.getCflim5();
			this.sr5[5] = objCfp001207.getCflim6();
			this.sr5[6] = objCfp001207.getCflim7();
			this.sr5[7] = objCfp001207.getCflim8();
			this.sr5[8] = objCfp001207.getCflim9();
			
			this.acodr = 1;
			this.clavex = "CODECO";
			this.f8 = Integer.parseInt(this.fc.FormatoFechaHora("yyyyMMdd") );
			this.fecdia = this.f8;
			if (!this.wcta.equals("")) {
				this.wncta = this.wcta;
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutConsi(DataSet ds) {
		try {
			this.a4 = Integer.parseInt(this.wncta.substring(0, 4));
			if (this.a4 == 0) {
				this.error = true;
			}else {
				this.a1 = Integer.parseInt(this.a4.toString().substring(0,1));
				if (this.a1 == 0) {
					this.dmtyp1 = "6";
				}
				if (this.a1 == 5) {
					this.dmtyp1 = "1";
				}
				objTap002w = myDAOTap002w.getUsingLdbankAndAmtypAndAncta(ds, this.codeco, this.dmtyp1, this.wncta);
				if (objTap002w == null) {
					this.error = true;
				}
			}
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutCarga(DataSet ds) {
			try {
				SubRutBorra1(ds);    
				this.ind67[0] = "0";
				this.ind68[0] = "0";
				this.nrr = 0;
				this.wssald = objTap002w.getDmcbal();
				this.ascrha = BigDecimal.ZERO;
				if (this.a1 == 5 || this.a1 == 0) {
					this.ancta = Long.parseLong(this.wncta);
					SubRutBorra5(ds);
					SubRutDispon(ds);
					this.discbu = this.disca;
					this.disdbu = this.disda;
					this.scrhoy = this.acrhoy;
					this.sdbhoy = this.adbhoy;
					this.sdbpen = this.adbpen;
					this.sreten = this.areten;
					if (this.a1 == 5) {
						this.aaplic = this.wpsav;
					}else {
						this.aaplic = this.wpdda;
					}
					this.t = objTap002w.getDmodl();
					this.ancta1 = this.ancta;
					if (objTap002w.getDmgovt().equals("+") ) {
						if (objTap002w.getDmcmcn() != 0) {
							this.segmen = "POOL";
							objCbiptic = myDAOCbiptic.getUsingDmcmcnAndDmbrchAndSegmen(ds, objTap002w.getDmcmcn().toString(), objTap002w.getDmbrch().toString(), this.segmen);
							if (objCbiptic == null) {
								this.tipcam = new BigDecimal(1);
							}else {
								this.tipcam = objCbiptic.getTccvta();
							}
						}
						this.acmcn = this.objTap002w.getDmcmcn();
						listTap902 = myDAOTap902.getUsingListLdbankANDAcodrANDaaplicANDancta1(ds, this.codeco, this.acodr.toString(), this.aaplic.toString(), this.ancta1.toString());
						if (listTap902 != null && listTap902.size()>0) {
							this.aaplic = listTap902.get(0).getCaplp();
							this.ancta1 = listTap902.get(0).getNctap();
							this.ind67[0] = "1";
						}else {
							this.ind68[0] = "1";
						}
						listTap902 = myDAOTap902.getUsingListLdbankANDAcodrANDaaplicANDancta1(ds, this.codeco, this.acodr.toString(), this.aaplic.toString(), this.ancta1.toString());
						for(Tap902 o: listTap902) {
							if (this.ind67[0].equals("0") || this.ind68[0].equals("0") || (this.ind67[0].equals("1") && this.ind68[0].equals("1") && o.getNctav().intValue() != Integer.parseInt(this.wncta))) {
								if (this.ind68[0].equals("0")) {
									this.ind68[0] = "1";
									if (o.getCaplp() == this.wpsav) {
										this.amtyp = 1;
									}else {
										if (o.getCaplp() == this.wpdda) {
											this.amtyp = 6;
										}
									}
									this.ancta = o.getNctap();
								}else {
									if (o.getCaplv() == this.wpsav) {
										this.amtyp = 1;
									}else {
										if (o.getCaplv() == this.wpdda) {
											this.amtyp = 6;
										}
									}
									this.ancta = o.getNctav();
								}
								objTap002w = myDAOTap002w.getUsingLdbankAndAmtypAndAncta(ds, this.codeco, this.amtyp.toString(), this.ancta.toString());
								if (objTap002w != null && ( !objTap002w.getDmstat().equals("2") && !objTap002w.getDmstat().equals("3") && !objTap002w.getDmstat().equals("4") && !objTap002w.getDmstat().equals("7") && !objTap002w.getDmstat().equals("P") && !objTap002w.getDmstat().equals("N") && !objTap002w.getDmstat().equals("O")  ) && !objTap002w.getDmusr1().equals("2") ) {
									SubRutBorra5(ds);
									SubRutDispon(ds);
									this.dmcbal = objTap002w.getDmcbal().subtract( this.disda);
									this.dmcbal = objTap002w.getDmcbal().add( this.disca );
									this.asdisp = objTap002w.getDmcbal().add( new BigDecimal( objTap002w.getDmiodl() ) );
									this.ascrha = this.ascrha.add( this.acrhoy );
									this.asdisp = this.asdisp.subtract( this.adbhoy );
									this.asdisp = this.asdisp.subtract( this.adbpen );
									this.asdisp = this.asdisp.subtract( this.areten );
									if (objTap002w.getDmcmcn() != this.acmcn) {
										if (this.acmcn == 0) {
											this.segmen = "POOL";
											objCbiptic = myDAOCbiptic.getUsingDmcmcnAndDmbrchAndSegmen(ds, objTap002w.getDmcmcn().toString(), objTap002w.getDmbrch().toString(), this.segmen);
											if (objCbiptic == null) {
												this.tipcam = new BigDecimal(1);
											}else {
												this.tipcam = objCbiptic.getTccomp();
											}
											this.asdisp = this.asdisp.multiply(this.tipcam);
											this.acrhoy = this.acrhoy.multiply(this.tipcam);
										}else {
											this.asdisp = this.asdisp.divide(this.tipcam);
											this.acrhoy = this.acrhoy.divide(this.tipcam);
										}
									}
									if ( ((objTap002w.getDmstat().equals("5") || objTap002w.getDmstat().equals("B") || objTap002w.getDmstat().equals("C") || objTap002w.getDmstat().equals("D") || objTap002w.getDmstat().equals("E")) && !objTap002w.getDmusr2().equals("2")) || (!objTap002w.getDmstat().equals("5") && !objTap002w.getDmstat().equals("B") && !objTap002w.getDmstat().equals("C") && !objTap002w.getDmstat().equals("D") && !objTap002w.getDmstat().equals("E") ) ) {
										this.wsdisp = this.wsdisp.add(this.asdisp);
									}	
								}
							}
							objTap002w = myDAOTap002w.getUsingLdbankAndAmtypAndAncta(ds, this.codeco, this.amtyp.toString(), this.ancta.toString());
						}
					}
					this.dmcbal = objTap002w.getDmcbal().subtract( this.disdbu);
					this.dmcbal = this.dmcbal.add( this.discbu );
					this.asdisp = this.dmcbal.add( new BigDecimal( objTap002w.getDmiodl() ) );
					this.asdisp = this.asdisp.subtract( this.sdbpen );
					this.asdisp = this.asdisp.subtract( this.sreten );
					this.asdisp = this.asdisp.subtract( this.sdbhoy );
					if (this.a1 == 5 && objTap002w.getDmnow().equals("9")) {
						if (objTap002w.getDmusr1().equals("2")) {
							this.asdisp = this.asdisp.subtract(this.wsinmo);
							this.wsinmo = this.wsinmo.add(this.scrhoy);
						}else {
							this.asdisp = BigDecimal.ZERO;
						}
					}else {
						this.asdisp = this.asdisp.add(this.scrhoy);
					}


					if (fc.BigDecimalComparar(this.wsdisp.toString(), "0", "!=")) {
						if (objTap002w.getDmtyp() == 6) {
							this.wsdisp = this.wsdisp.add(this.ascrha);
							this.t = objTap002w.getDmodl();
							if ( fc.BigDecimalComparar(this.asdisp.toString(), "0", ">=") ) {
								this.stope = new BigDecimal( this.sr5[this.t]);
							}else {
								this.stope = new BigDecimal(this.sr5[this.t]).add(this.asdisp);
							}
						}else {
							if (fc.BigDecimalComparar(this.asdisp.toString(), "0", ">")) {
								this.stope = this.asdisp;
							}else {
								this.stope = BigDecimal.ZERO;
							}
						}
						if (fc.BigDecimalComparar(this.wsdisp.toString(), "0", "<")) {
							this.wsdisp = this.wsdisp.negate();
							if (objTap002w.getDmnow().equals("9") && this.a1 ==5) {
								this.wsdisp = this.wsdisp.subtract(this.wsinmo);
								if (fc.BigDecimalComparar(this.wsdisp.toString(), "0", "<")) {
									this.wsdisp = BigDecimal.ZERO;
								}
							}
							if (fc.BigDecimalComparar(this.wsdisp.toString(), this.stope.toString(), ">")) {
								this.sreten = this.sreten.add(this.stope);
								this.asdisp = this.asdisp.subtract(this.stope);
							}else {
								this.sreten = this.sreten.add(this.wsdisp);
								this.asdisp = this.asdisp.subtract(this.wsdisp);
							}
						}else {
							if (objTap002w.getDmtyp() == 6) {
								if (fc.BigDecimalComparar(this.wsdisp.toString(), this.stope.toString(), ">")) {
									this.dmiodl = objTap002w.getDmiodl() + new Long(this.stope.toString());
									this.asdisp = this.asdisp.add(this.stope);
								}else {
									this.dmiodl = objTap002w.getDmiodl() + new Long(this.wsdisp.toString());
									this.asdisp = this.asdisp.add(this.wsdisp);
								}
							}
						}
					}
					this.wsdisp = this.asdisp;
				}
				if (objTap002w.getDmstct().equals("A") || objTap002w.getDmstct().equals("E") || objTap002w.getDmstct().equals("T")) {
					this.abnk = this.codeco;
					if (this.a1 == 5) {
						this.aapdes = 10;
					}else {
						this.aapdes = 20;
					}
					this.acuent = Long.parseLong(this.wncta);
					this.arcd = 80;
					objAltnam = myDAOAltnam.getUsingAbnkAndAapdesAndAcuentAndArcd(ds, abnk, aapdes.toString(), acuent.toString(), arcd.toString());
					if (objAltnam != null ) {
						this.wnomb1 = objAltnam.getNamel1();
						this.wnomb2 = objAltnam.getNamel2();
						this.wnomb3 = objAltnam.getNamel3();
					}else {
						this.wnomb1 = "";
						this.wnomb2 = "";
						this.wnomb3 = "";
					}
				}else {
					if (this.a1 == 5) {
						this.aaplic = this.wpsav;
						this.acta = "01";
					}else {
						this.aaplic = this.wpdda;
						this.acta = "06";
					}
					this.acta = this.wncta.toString();
					this.wnomb1 = "";
					this.wnomb2 = "";
					this.wnomb3 = "";
					listCuxrf1 = myDAOCuxrf1.getUsigSwx2bkAndSwx2rtAndSwx2apAndSwx2acAndSwx2tyAndSwx2cs(ds, this.codeco, this.arec, this.aaplic.toString(), this.acta);
					for ( Cuxrf1 o:listCuxrf1) {
						objCumast = myDAOCumast.getUsingSwcubkAndSwcunb(ds, this.codeco, o.getCux1cs() );
						if (this.wnomb1.equals("")) {
							this.wnomb1 = objCumast.getCuna1();
						}
						if (this.wnomb2.equals("")) {
							this.wnomb2 = objCumast.getCuna1();
						}
						if (this.wnomb3.equals("")) {
							this.wnomb3 = objCumast.getCuna1();
						}
					}
				}
				
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage(), e);
				return e.getMessage();
			}
			return "";
	}
	
	private String SubRutBorra1(DataSet ds) {
		try {
			this.wsinmo = BigDecimal.ZERO;
			this.scrhoy = BigDecimal.ZERO;
			this.scrpen = BigDecimal.ZERO;
			this.sdbhoy = BigDecimal.ZERO;
			this.sdbpen = BigDecimal.ZERO;
			this.sreten = BigDecimal.ZERO;
			this.wsdisp = BigDecimal.ZERO;
			this.asdisp = BigDecimal.ZERO;
			this.ancta = new Long("0");
			this.ancta1 = new Long("0");
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutBorra5(DataSet ds) {
		try {
			this.adbpen = BigDecimal.ZERO;
			this.adbhoy = BigDecimal.ZERO;
			this.areten = BigDecimal.ZERO;
			this.acrhoy = BigDecimal.ZERO;
			this.disda = BigDecimal.ZERO;
			this.disca = BigDecimal.ZERO;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}
	
	private String SubRutDispon(DataSet ds) {
		try {
			listTranem = myDAOTranem.getUsingKeyAnctaX(ds, this.ancta.toString(), "X");
			listTransm = myDAOTransm.getUsingKeyAnctaX(ds, this.ancta.toString(), "X");
		
			for ( Transm o:listTransm) {
				this.fecval = o.getDaava() * 10000 + o.getDmmva() * 100 + o.getDddva();
				if (!o.getXerror().equals("E")) {
					this.ncbat = o.getCbat();
					this.wsdebi = BigDecimal.ZERO;
					this.wscred = BigDecimal.ZERO;
					if (o.getCref().equals("")) {
						this.cref1 = "0";
					}else {
						this.cref1 = o.getCref();
					}
					if (o.getCmonex() == 0) {
						this.aimpor = o.getSimloc();
					}else {
						this.aimpor = o.getSimpex();
					}
					if (o.getCdbcr() <= 5) {
						if (this.fecval > this.fecdia) {
							if (this.a1 == 0 || this.a1 == 5) {
								this.scrpen = this.scrpen.add(this.aimpor);
							}
							this.wscred = this.aimpor;
							this.wssald = this.wssald.add( this.wscred );
						}else {
							if (this.fecval == this.fecdia) {
								if (o.getCbat().equals("4")) {
									if (this.a1 == 0 || this.a1 == 5) {
										this.disca = this.disca.add(this.aimpor);
									}
									this.wscred = this.aimpor;
									this.wssald = this.wssald.add(this.wscred);
								}else {
									if (o.getCbat().equals("5") || o.getCbat().equals("2") && o.getCtraex() == 51) {
										this.disca = this.disca.add(this.aimpor);
										this.wscred = this.aimpor;
										this.wssald = this.wssald.add(this.wscred);
									}else {
										this.acrhoy = this.acrhoy.add(this.aimpor);
										this.wscred = this.aimpor;
										this.wssald = this.wssald.add( this.wscred );
									}
								}
							}else {
								if (this.fecing < this.fecdia) {
									this.disca = this.disca.add(this.aimpor);
									this.wscred = this.aimpor;
									this.wssald = this.wssald.add(this.aimpor);
								}else {
									this.acrhoy = this.acrhoy.add(this.aimpor);
									this.wscred = this.aimpor;
									this.wssald = this.wssald.add(this.wscred);
								}
							}
						}
					}else {
						if (this.fecval > this.fecdia) {
							this.adbpen = this.adbpen.add(this.aimpor);
							if (this.a1 == 0 || this.a1 == 5) {
								this.wsdebi = this.aimpor;
								this.wssald = this.wssald.subtract(this.wsdebi);
							}
						}else {
							if (this.fecval == this.fecdia) {
								if (o.getCbat().equals("4")) {
									this.adbpen = this.adbpen.add(this.aimpor);
									if (this.a1 == 0 || this.a1 == 5) {
										this.wsdebi = this.aimpor;
										this.wssald = this.wssald.subtract(this.wsdebi);
									}
								}else {
									this.adbhoy = this.adbhoy.add(this.aimpor);
									if (this.a1 == 0 || this.a1 == 5) {
										this.wsdebi = this.aimpor;
										this.wssald = this.wssald.subtract(this.wsdebi);
									}
								}
							}else {
								if (this.fecing < this.fecdia) {
									this.disda = this.disda.add(this.aimpor);
									this.wsdebi = this.aimpor;
									this.wssald = this.wssald.subtract(this.aimpor);
								}else {
									this.adbhoy = this.adbhoy.add(this.aimpor);
									if (this.a1 == 0 || this.a1 == 5) {
										this.wsdebi = this.aimpor;
										this.wssald = this.wssald.subtract(this.wsdebi);
									}
								}
							}
						}
					}
					if (this.a1 == 0 || this.a1 == 5 && this.nrr < 999) {
						this.adaava = Integer.parseInt(o.getDaava().toString().substring(3-1, 4));
						this.admmva = o.getDmmva();
						this.adddva = o.getDddva();
						this.afecva = this.adddva * 10000 + this.admmva * 100 + this.adaava; 
						this.nrr = this.nrr + 1;
						this.wbatch = o.getCsucur().toString() + "-" + o.getCbat() + "-" + o.getNbat().toString();  
						//Grabar registro en Pantalla2
						adapter = new FER0320V2Adapter();
						adapter.setAFECVA(this.afecva);
						adapter.setCTRAEX(o.getCtraex());
						adapter.setWBATCH(this.wbatch);
						adapter.setCREF1(this.cref1);
						adapter.setWSDEBI(this.wsdebi);
						adapter.setWSCRED(this.wscred);
						adapter.setWSSALD(this.wssald);
						listAdapter.add(adapter);
					}//fin if
				}//fin if error
			}//fin listTransm
			
			for ( Tranem o:listTranem) {
				this.fecval = o.getDaava() * 10000 + o.getDmmva() * 100 + o.getDddva();
				if (!o.getXerror().equals("E")) {
					this.ncbat = o.getCbat();
					this.wsdebi = BigDecimal.ZERO;
					this.wscred = BigDecimal.ZERO;
					if (o.getCref().equals("")) {
						this.cref1 = "0";
					}else {
						this.cref1 = o.getCref();
					}
					if (o.getCmonex() == 0) {
						this.aimpor = o.getSimloc();
					}else {
						this.aimpor = o.getSimpex();
					}
					if (o.getCdbcr() <= 5) {
						if (this.fecval > this.fecdia) {
							if (this.a1 == 0 || this.a1 == 5) {
								this.scrpen = this.scrpen.add(this.aimpor);
							}
							this.wscred = this.aimpor;
							this.wssald = this.wssald.add( this.wscred );
						}else {
							if (this.fecval == this.fecdia) {
								if (o.getCbat().equals("4")) {
									if (this.a1 == 0 || this.a1 == 5) {
										this.disca = this.disca.add(this.aimpor);
									}
									this.wscred = this.aimpor;
									this.wssald = this.wssald.add(this.wscred);
								}else {
									if (o.getCbat().equals("5") || o.getCbat().equals("2") && o.getCtraex() == 51) {
										this.disca = this.disca.add(this.aimpor);
										this.wscred = this.aimpor;
										this.wssald = this.wssald.add(this.wscred);
									}else {
										this.acrhoy = this.acrhoy.add(this.aimpor);
										this.wscred = this.aimpor;
										this.wssald = this.wssald.add( this.wscred );
									}
								}
							}else {
								if (this.fecing < this.fecdia) {
									this.disca = this.disca.add(this.aimpor);
									this.wscred = this.aimpor;
									this.wssald = this.wssald.add(this.aimpor);
								}else {
									this.acrhoy = this.acrhoy.add(this.aimpor);
									this.wscred = this.aimpor;
									this.wssald = this.wssald.add(this.wscred);
								}
							}
						}
					}else {
						if (this.fecval > this.fecdia) {
							this.adbpen = this.adbpen.add(this.aimpor);
							if (this.a1 == 0 || this.a1 == 5) {
								this.wsdebi = this.aimpor;
								this.wssald = this.wssald.subtract(this.wsdebi);
							}
						}else {
							if (this.fecval == this.fecdia) {
								if (o.getCbat().equals("4")) {
									this.adbpen = this.adbpen.add(this.aimpor);
									if (this.a1 == 0 || this.a1 == 5) {
										this.wsdebi = this.aimpor;
										this.wssald = this.wssald.subtract(this.wsdebi);
									}
								}else {
									this.adbhoy = this.adbhoy.add(this.aimpor);
									if (this.a1 == 0 || this.a1 == 5) {
										this.wsdebi = this.aimpor;
										this.wssald = this.wssald.subtract(this.wsdebi);
									}
								}
							}else {
								if (this.fecing < this.fecdia) {
									this.disda = this.disda.add(this.aimpor);
									this.wsdebi = this.aimpor;
									this.wssald = this.wssald.subtract(this.aimpor);
								}else {
									this.adbhoy = this.adbhoy.add(this.aimpor);
									if (this.a1 == 0 || this.a1 == 5) {
										this.wsdebi = this.aimpor;
										this.wssald = this.wssald.subtract(this.wsdebi);
									}
								}
							}
						}
					}
					if (this.a1 == 0 || this.a1 == 5 && this.nrr < 999) {
						this.adaava = o.getDaava();
						this.admmva = o.getDmmva();
						this.adddva = o.getDddva();
						this.nrr = this.nrr + 1;
						//Grabar registro en Pantalla2
						adapter = new FER0320V2Adapter();
						//adapter.setAFECVA(this.afecva);
						adapter.setCTRAEX(o.getCtraex());
						//adapter.setWBATCH(this.wbatch);
						adapter.setCREF1(this.cref1);
						adapter.setWSDEBI(this.wsdebi);
						adapter.setWSCRED(this.wscred);
						adapter.setWSSALD(this.wssald);
						listAdapter.add(adapter);
					}//fin if
				}//fin if error
			}//fin listTranem
			
			this.dshbk = this.codeco;
			if (this.a1 == 5) {
				this.atpcta = "1";
			}else {
				this.atpcta = "6";
			}
			this.dshdsv = this.atpcta;
			this.acuen1 = this.ancta;
			this.dsacct = this.ancta;
			this.dsser = 0;
			this.dstype = "";
			listTap003 = myDAOTap003.getUsingListDshbkAndDshdsvAndDsacctAndDstypeAndDsstat(ds, dshbk, dshdsv, dsacct.toString(), "2", "D");
			for(Tap003 o:listTap003) {
				this.areten = this.areten.add( o.getDsamt());
			}
			if ( (this.a1 == 0 || this.a1 == 5 ) && objTap002w.getDmnow().equals("9")) {
				listTap901 = myDAOTap901.getUsingListDmbkAndDmtypAndDmacct(ds, this.codeco, this.amtyp.toString(), this.ancta.toString());
				for (Tap901 o : listTap901) {
					this.ssssyy = o.getSsedyy();
					this.fecimn = Integer.parseInt( String.valueOf(o.getSsedyy() * 10000) + String.valueOf(o.getSsedmm() * 100) + o.getSseddd().toString() );
					if (this.fecimn != 0) {
						if (this.fecimn < 800000) {
							this.fecimn = this.fecimn + 20000000;
						}else {
							this.fecimn = this.fecimn + 19000000;
						}
					}
					if (this.fecimn > this.fecdia) {
						this.wsinmo = this.wsinmo.add(o.getSsamon());
					}
				}
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
}
	
	
	
	public class FER0320V2Adapter{

		Integer AFECVA = 0;
		Integer CTRAEX = 0;
		String WBATCH = "";
		String CREF1 = "";
		BigDecimal WSDEBI = new BigDecimal(0);
		BigDecimal WSCRED = new BigDecimal(0);
		BigDecimal WSSALD = new BigDecimal(0);
		
		public FER0320V2Adapter() {
			
		}

		public Integer getAFECVA() {
			return AFECVA;
		}

		public void setAFECVA(Integer aFECVA) {
			AFECVA = aFECVA;
		}

		public Integer getCTRAEX() {
			return CTRAEX;
		}

		public void setCTRAEX(Integer cTRAEX) {
			CTRAEX = cTRAEX;
		}

		public String getWBATCH() {
			return WBATCH;
		}

		public void setWBATCH(String wBATCH) {
			WBATCH = wBATCH;
		}

		public String getCREF1() {
			return CREF1;
		}

		public void setCREF1(String cREF1) {
			CREF1 = cREF1;
		}

		public BigDecimal getWSDEBI() {
			return WSDEBI;
		}

		public void setWSDEBI(BigDecimal wSDEBI) {
			WSDEBI = wSDEBI;
		}

		public BigDecimal getWSCRED() {
			return WSCRED;
		}

		public void setWSCRED(BigDecimal wSCRED) {
			WSCRED = wSCRED;
		}

		public BigDecimal getWSSALD() {
			return WSSALD;
		}

		public void setWSSALD(BigDecimal wSSALD) {
			WSSALD = wSSALD;
		}



		
		
		
	}
	

}
