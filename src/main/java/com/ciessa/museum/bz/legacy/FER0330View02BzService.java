package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.ciessa.museum.dao.legacy.Cfp001005DAO;
import com.ciessa.museum.dao.legacy.CumastDAO;
import com.ciessa.museum.dao.legacy.Cuxrf1DAO;
import com.ciessa.museum.dao.legacy.Tap002wDAO;
import com.ciessa.museum.dao.legacy.Tap005bDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Altnam;
import com.ciessa.museum.model.legacy.Cfp001005;
import com.ciessa.museum.model.legacy.Cumast;
import com.ciessa.museum.model.legacy.Cuxrf1;
import com.ciessa.museum.model.legacy.Tap002w;
import com.ciessa.museum.model.legacy.Tap005b;
import com.ciessa.museum.tools.CollectionFactory;

public class FER0330View02BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(FER0330View02BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	Cfp001005DAO myDAOCfp001005;
	
	@Autowired
	Tap002wDAO myDAOTap002w;
	
	@Autowired
	Tap005bDAO myDAOTap005b;
	
	@Autowired
	AltnamDAO myDAOAltnam;
	
	@Autowired
	Cuxrf1DAO myDAOCuxrf1;
	
	@Autowired
	CumastDAO myDAOCumast;
	
	
	Cfp001005 objCfp001005 = new Cfp001005();
	Tap002w objTap002w = new Tap002w();
	Tap005b objTap005b = new Tap005b();
	Altnam objAltnam = new Altnam();
	Cumast objCumast = new Cumast();
	
	
	List<Cuxrf1> listCuxrf1 = null;
	List<Tap005b> listTap005b = null;
	
	
	
	
	
	String wcta = "";
	String amar = "";
	String wdevpr = "";
	
	String adevpr = "";
	String fecha = "";
	String wfech = "";
	Integer wfec2 = 0;
	String qname = "";
	String qlib = "";
	Integer qlen = 0;
	String abanco = "";
	String codeco = "1";
	String march = "";
	Integer wbatch = 0;
	Integer wfec1 = 0;
	String banco = "";
	Integer nroreg = 0;
	String cfalfa = "";
	Integer wpsav = 0;
	Integer wpdda = 0;
	String wncta = "";
	Boolean error = false;
	Long a4 = new Long("0");
	Long a1 = new Long("0");
	String dmtyp = "";
	Integer auxmon = 0;
	Integer nrr = 0;
	Integer samd7 = 0;
	Integer hoja = 0;
	Integer csdd8 = 0;
	Integer csmm8 = 0;
	String adia = "";
	String ames = "";
	String aano = "";
	Integer csaa8 = 0;
	
	
	Integer amm1 = 0;
	Integer add1 = 0;
	Integer aaa1 = 0;
	Integer csdma6 = 0;
	Integer wfec3 = 0;
	Integer aaa2 = 0;
	Integer aaa3 = 0;
	String abnk = "";
	Integer aapdes = 0;
	String acuent = "";
	Integer arcd = 0;
	String wnomb1 = "";
	String wnomb2 = "";
	String wnomb3 = "";
	Integer aaplic = 0;
	String acta = "";
	String ind61 = "";
	String abco = "";
	Integer atip = 0;
	String acuen = "";
	Integer fecw = 0;
	//Integer aaw = 0;
	BigDecimal dhamt = new BigDecimal(0);
	Integer desde = 0;
	BigDecimal wssald = new BigDecimal(0);
	Integer w = 0;
	Integer guardo = 0;
	Integer fechai = 0;
	String fecval = "";
	BigDecimal wscred = new BigDecimal(0);
	BigDecimal wsdebi = new BigDecimal(0);
	Long cref1 = new Long("0");
	Integer ctraex = 0;
	
	
	FER0330V02Adapter adapter = null;
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
	List<FER0330V02Adapter> listAdapter = null;
	
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
			this.wdevpr = obtainStringValue("wdevpr", null);
			
			this.adevpr = this.wdevpr;
			this.fecha = fc.FormatoFechaHora("dd/MM/yyyy");
			this.wfech = "0";
			this.wfec2 = 0;
			this.qname = "FEU0003";
			this.qlib = "*LIBL";
			this.qlen = 29;
			this.abanco = this.codeco;
			this.march = "";
			this.wbatch = 0;
			this.wfec1 = 0;
			
			listAdapter = new ArrayList<FER0330V02Adapter>();
			
			SubRutUnica(ds);
//			this.adia = "0";// this.wfech.substring(0, 2);
			this.ames = "0";//this.wfech.substring(2, 4);
			this.aano = "0";//this.wfech.substring(4, 6);
			SubRutConsi(ds);
			if (!this.error) {
				SubRutCarga(ds);
				SubRutCarga1(ds);
				//Mostrar pantalla
			}
			
			// retrieve all elements
			Map<String,String> attributes = CollectionFactory.createMap();
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found [" + listAdapter.size() + "] in " + diff + " millis");
			
			
			String[] fields = new String[] {
					"WNCTA", 
					"WFECH", 
					"CODECO",
					"DMBRCH",
					"DMCMCN",
					"WNOMB1",
					"WNOMB2",
					"WNOMB3",
			};
			String[] arrayFields = new String[] {
					"FECVAL",
					"CTRAEX",
					"WBATCH",
					"CREF1",
					"WSDEBI",
					"WSCRED",
					"WSSALD",
			};
			returnValue = getJSONRepresentationFromObject(adapter, fields);
			returnValue.put("data", getJSONRepresentationFromArrayOfObjects(listAdapter, arrayFields).get("data"));
			returnValue.put("WNCTA" , this.wncta);
			returnValue.put("WFECH" , this.wfech);
			returnValue.put("CODECO" , this.codeco);
			returnValue.put("DMBRCH" , objTap002w.getDmbrch());
			returnValue.put("DMCMCN" , objTap002w.getDmcmcn());
			returnValue.put("WNOMB1" , this.wnomb1);
			returnValue.put("WNOMB2" , this.wnomb2);
			returnValue.put("WNOMB3" , this.wnomb3);
			
			if( attributes.containsKey("recordCount"))
				returnValue.put("recordCount", Long.valueOf(attributes.get("recordCount")));
			else 
				returnValue.put("recordCount", listAdapter.size());
			
			this.wncta = "0";
			this.wfech = "";
			this.amar = "0";
			
			
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
			this.banco = this.codeco;
			this.nroreg = 5;
			this.cfalfa = "";
			
			objCfp001005 = myDAOCfp001005.getUsingKey(ds);
			if (objCfp001005 != null) {
				this.wpsav = objCfp001005.getCfpsav();
				this.wpdda = objCfp001005.getCfpdda();
			}
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
			this.a4 = Long.parseLong(this.wncta);
			if (this.a4 < 1000000) {
				this.error = true;
			}else {
				this.a1 = this.a4;
				if (new Long(this.a1) >= new Long("5000000000") ) {
					this.dmtyp = "1";
				}else {
					this.dmtyp = "6";
				}
				objTap002w = myDAOTap002w.getUsingLdbankAndAmtypAndAncta(ds, this.codeco, this.dmtyp, this.wncta);
				if (objTap002w == null) {
					this.error = true;
				}else {
					this.auxmon = objTap002w.getDmcmcn();
					if (this.wcta.equals("")) {
						if (objTap002w.getDmemp().equals("O")) {
							this.error = true;
						}
					}
				}
			}
			if (!this.error) {
				listTap005b = myDAOTap005b.getUsingListCodecoAndDmtypAndWnctaAndDhrec(ds, this.codeco, this.dmtyp, this.wncta, "0");
				if ( (listTap005b == null || listTap005b.size()== 0) || ( (listTap005b != null || listTap005b.size() >  0) && listTap005b.get(0).getDhstnr() >= objTap002w.getDmfstt()  ) ) {//exite
					this.wfec2 = 0;
				}else {
					this.wfec2 = objTap005b.getDheff();
				}
			}
			
			if (Integer.parseInt(this.wfech) > 0) {
				this.csdd8 = Integer.parseInt(this.adia);
				this.csmm8 = Integer.parseInt(this.ames);
				this.csaa8 = Integer.parseInt(this.aano);
				if (this.csaa8 < 80) {
					this.csaa8 = this.csaa8 + 2000;
				}else {
					this.csaa8 = this.csaa8 + 1900;
				}
				
				this.aaa1 = Integer.parseInt(this.aano);
				this.amm1 = Integer.parseInt(this.ames);
				this.add1 = Integer.parseInt(this.adia);
				if (Integer.parseInt(this.wfech) != 0) {
					if (this.aaa1 < 80) {
						this.aaa1 = this.aaa1 + 2000;
					}else {
						this.aaa1 = this.aaa1 + 1900;
					}
				}
				this.wfec1 = Integer.parseInt( this.aaa1.toString() + String.format("%02d", this.amm1) + String.format("%02d",this.add1) );
				this.csdma6 =Integer.parseInt(objTap002w.getDmdlst().toString().substring(1, 7));
				this.wfec3 = this.csdma6;
				if (this.csdma6 != 0) {
					if (this.wfec3 < 800000) {
						this.wfec3 = this.wfec3 + 20000000;
					}else {
						this.wfec3 = this.wfec3 + 19000000;
					}
				}
				if (this.wfec1 < this.wfec2 || this.wfec1 > this.wfec3) {
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
			if (objTap002w.getDmstct().equals( "A") || objTap002w.getDmstct().equals("E") || objTap002w.getDmstct().equals("T")) {
				this.abnk = this.codeco;
				if (this.a1 == 5) {
					this.aapdes = 10;
				}else {
					this.aapdes = 20;
				}
				this.acuent = this.wncta;
				this.arcd = 80;
				objAltnam = myDAOAltnam.getUsingAbnkAndAapdesAndAcuentAndArcd(ds, this.abnk, this.aapdes.toString(), this.acuent, this.arcd.toString());
				if (objAltnam != null) {
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
				listCuxrf1 = myDAOCuxrf1.getUsigListCodecoAndAaplicAndActa(ds, codeco, aaplic.toString(), acta);
				for ( Cuxrf1 o:listCuxrf1) {
					objCumast = myDAOCumast.getUsingSwcubkAndSwcunb(ds, this.codeco, o.getCux1cs() );
					if (objCumast != null) {
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
			}
			
			
			this.ind61 = "0";
			this.abco = this.codeco;
			if (this.a1 == 5) {
				this.atip = 1;
			}else {
				this.atip = 6;
			}
			this.acuen = this.wncta;
			this.nrr = 0;
			//Grabar registro en Pantalla2
			adapter = new FER0330V02Adapter();
			adapter.setFECVAL(this.fecval);
			adapter.setCTRAEX(this.ctraex);
			adapter.setWBATCH(this.wbatch);
			adapter.setCREF1(this.cref1);
			adapter.setWSDEBI(this.wsdebi);
			adapter.setWSCRED(this.wscred);
			adapter.setWSSALD(this.wssald);
			listAdapter.add(adapter);
			
			List<Tap005b> lstTap005b = myDAOTap005b.getUsingKeyCodecoAndDmtypAndWnctaAndDhrecAndWfech(ds, this.codeco, this.dmtyp, this.wncta, "0", this.wfech);
			//--objTap005b = myDAOTap005b.getUsingKeyCodecoAndDmtypAndWnctaAndDhrecAndWfech(ds, this.codeco, this.dmtyp, this.wncta, "0", this.wfech);
			
			if (lstTap005b.size() > 0) {
				objTap005b = lstTap005b.get(0);
				this.fecw = objTap005b.getDheff();
				if ( objTap005b.getDheff() <= 19910000  && this.auxmon == 0) {
					this.dhamt = objTap005b.getDhamt().subtract(new BigDecimal(0.01));
					this.dhamt = this.dhamt.divide( new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP);;
				}
				this.wfec2 = objTap005b.getDheff();
				this.desde = objTap005b.getDhstnr();
				this.wssald = objTap005b.getDhamt();
			}else {
				this.wfec2 = objTap002w.getDmdlst();
				if (this.aaa2 < 50) {
					this.aaa2 = 20;
				}else {
					this.aaa2 = 1900;
				}
				this.wssald = BigDecimal.ZERO;
				this.desde = 0;
			}
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
}
	
	private String SubRutCarga1(DataSet ds) {
		try {
			if (this.ind61.equals("1")) {
				this.w = 9;
			}else {
				this.w = 8;
			}
			listTap005b = myDAOTap005b.getUsingListCodecoAndDmtypAndWncta(ds, this.codeco, this.dmtyp, this.wncta);
			
			for(Tap005b o: listTap005b) {
				if (o.getDhstnr() < objTap002w.getDmfstt() && o.getDhrec() == 1) {
					this.guardo = this.wbatch;
					this.fecw = o.getDheff();
					this.fechai = o.getDheff();
					this.fecval = (new SimpleDateFormat("ddMMyyyy").parse(this.fechai.toString()) ).toString();
					SubRutCarga3(ds); 
					if (o.getDhdrcr() <= 5) {
						this.wscred = o.getDhamt();
						this.wsdebi = BigDecimal.ZERO;
						this.wssald = this.wssald.add(o.getDhamt());
					}else {
						this.wsdebi = o.getDhamt();
						this.wscred = BigDecimal.ZERO;
						this.wssald = this.wssald.subtract(o.getDhamt());
					}
					if (o.getDheff() >= this.wfec1) {
						this.wbatch = this.guardo;
						this.cref1 = o.getDhref();
						this.ctraex = o.getDhitc();
						this.nrr = this.nrr + 1;
						//Grabar registro en Pantalla2
						adapter = new FER0330V02Adapter();
						adapter.setFECVAL(this.fecval);
						adapter.setCTRAEX(this.ctraex);
						adapter.setWBATCH(this.wbatch);
						adapter.setCREF1(this.cref1);
						adapter.setWSDEBI(this.wsdebi);
						adapter.setWSCRED(this.wscred);
						adapter.setWSSALD(this.wssald);
						listAdapter.add(adapter);
						
						this.w = this.w - 1;
						if (this.w == 0) {
							continue;
						}
					}
				}
			}//fin for
			this.hoja = this.nrr;
			
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
}
	
	private String SubRutCarga3(DataSet ds) {
		try {
			if (this.ind61.equals("0")) {
				this.nrr = this.nrr + 1;
				this.ind61 = "1";
				this.ctraex = 0;
				this.wbatch = 0;
				this.cref1 = new Long(0);
				this.wsdebi = BigDecimal.ZERO;
				this.wscred = BigDecimal.ZERO;
				
				//Grabar registro en Pantalla2
				adapter = new FER0330V02Adapter();
				adapter.setFECVAL(this.fecval);
				adapter.setCTRAEX(this.ctraex);
				adapter.setWBATCH(this.wbatch);
				adapter.setCREF1(this.cref1);
				adapter.setWSDEBI(this.wsdebi);
				adapter.setWSCRED(this.wscred);
				adapter.setWSSALD(this.wssald);
				listAdapter.add(adapter);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
}
	
	public class FER0330V02Adapter {
		
		String FECVAL = "";
		Integer CTRAEX = 0;
		Integer WBATCH = 0;
		Long CREF1 = new Long("0");
		BigDecimal WSDEBI = new BigDecimal(0);
		BigDecimal WSCRED = new BigDecimal(0);
		BigDecimal WSSALD = new BigDecimal(0);
		
		public FER0330V02Adapter() {
			
		}

		public String getFECVAL() {
			return FECVAL;
		}

		public void setFECVAL(String fECVAL) {
			FECVAL = fECVAL;
		}

		public Integer getCTRAEX() {
			return CTRAEX;
		}

		public void setCTRAEX(Integer cTRAEX) {
			CTRAEX = cTRAEX;
		}

		public Integer getWBATCH() {
			return WBATCH;
		}

		public void setWBATCH(Integer wBATCH) {
			WBATCH = wBATCH;
		}

		public Long getCREF1() {
			return CREF1;
		}

		public void setCREF1(Long cREF1) {
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
