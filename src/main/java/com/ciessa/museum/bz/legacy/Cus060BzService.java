package com.ciessa.museum.bz.legacy;

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
import com.ciessa.museum.dao.legacy.CumastDAO;
import com.ciessa.museum.dao.legacy.Cuxrf1DAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Altnam;
import com.ciessa.museum.model.legacy.Cumast;
import com.ciessa.museum.model.legacy.Cuxrf1;
import com.ciessa.museum.tools.CollectionFactory;

public class Cus060BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(Cus060BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	Cuxrf1DAO myDAOCuxrf1;
	
	@Autowired
	CumastDAO myDAOCumast;
	
	@Autowired
	AltnamDAO myDAOAltnam;
	
	
	Cuxrf1 objCuxrf1 = new Cuxrf1();
	Cumast objCumast = new Cumast();
	Altnam objAltnam = new Altnam();
	
	
	String paramwkbank = "";
	String paramwkacct = "";
	String paramwkalt = "";
	String paramwktitl = "";
	String paramwkappl = "";
	String paramna1 = "";
	String paramna2 = "";
	String paramna3 = "";
	String paramna4 = "";
	String paramna5 = "";
	String paramna6 = "";
	Integer paramwkssno = 0;
	Integer paramwkbuph = 0;
	Integer paramwkhmph = 0;
	
	String wkaccx = "";
	String wkappx = "";
	String swx2ky = "";
	String swx2bk = "";
	String swx2rt = "";
	String swx2ap = "";
	String swx2ac = "";
	Integer swx2ty = 0;
	String swx2cs = "";
	String fld30 = "";
	String na0 = "";
	Integer z = 0;
	Integer xnbr = 0;
	String[] cu1 = new String[10];
	String[] cu2 = new String[3];
	Integer y = 0;
	String swcubk = "";
	String swcunb = "";
	Integer wkzip = 0;
	String wcnta = "";
	String wkfld1 = "";
	Integer zip30 = 0;
	String cur = "";
	
	
	//
	FUNCIONESBzService func = new FUNCIONESBzService();
	
	//
	CUS060Adapter adapter = null;
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			this.paramwkbank = obtainStringValue("wkbank", null);
			this.paramwkacct = obtainStringValue("wkacct", null);
			this.paramwkalt = obtainStringValue("wkalt", null);
			this.paramwktitl = obtainStringValue("wktitl", null);
			this.paramwkappl = obtainStringValue("wkappl", null);
			this.paramna1 = obtainStringValue("na1", null);
			this.paramna2 = obtainStringValue("na2", null);
			this.paramna3 = obtainStringValue("na3", null);
			this.paramna4 = obtainStringValue("na4", null);
			this.paramna5 = obtainStringValue("na5", null);
			this.paramna6 = obtainStringValue("na6", null);
			this.paramwkssno = obtainIntegerValue("wkssno", 0);
			this.paramwkbuph = obtainIntegerValue("wkbuph", 0);
			this.paramwkhmph = obtainIntegerValue("wkhmph", 0);
			
			// inicializar array de string
			this.fld30 = String.format("%1$-30s",this.fld30);
			this.na0 = String.format("%1$-30s",this.na0);
			this.cur = String.format("%1$-17s",this.cur);
			
			//Proces
			this.cu1[1] = this.fld30;
			this.cu1[2] = this.fld30;
			this.cu1[3] = this.fld30;
			this.cu1[4] = this.fld30;
			this.cu2[1] = " ";
			this.cu2[2] = " ";
			this.cu2[3] = " ";
			this.cu2[4] = " ";
			paramna1 = this.fld30;
			paramna2 = this.fld30;
			paramna3 = this.fld30;
			paramna4 = this.fld30;
			paramna5 = this.fld30;
			paramna6 = this.fld30;
			this.na0 = this.fld30;
			
			this.wkaccx = this.paramwkacct;
			this.wkappx = this.paramwkappl;
			this.swx2ky = this.paramwkbank + "1" + this.wkappx + this.paramwkacct;
			this.swx2bk = "001";
			this.swx2rt = "1";
			this.swx2ap = this.paramwkappl;
			this.swx2ac = this.paramwkacct;
			this.swx2ty = 0;
			this.swx2cs = "";
			
			objCuxrf1 = myDAOCuxrf1.getUsigSwx2bkAndSwx2rtAndSwx2apAndSwx2acAndSwx2tyAndSwx2cs(ds, swx2bk, swx2rt, swx2ap, swx2ac, swx2ty.toString(), swx2cs);
			
			//this.fld30 = "";
			this.z = 2;
			this.xnbr = 0;
			
			if (true) {//existe registro
				this.xnbr = this.xnbr + 1;
				if (objCuxrf1.getCuxrel().equals("SOW")) {
					this.cu1[1] = objCuxrf1.getCux1cs();
					this.cu2[1] = objCuxrf1.getCuxrel();
				}
				if (objCuxrf1.getCuxrel().equals("JOF") || objCuxrf1.getCuxrel().equals("JAF")) {
					this.cu1[1] = objCuxrf1.getCux1cs();
					this.cu2[1] = objCuxrf1.getCuxrel();
				}else {
					if (!objCuxrf1.getCuxrel().equals("JOO") || !objCuxrf1.getCuxrel().equals("JAO")) {
						if (objCuxrf1.getCux1ty() != 2 && this.z <= 4) {
							this.cu1[z] = objCuxrf1.getCux1cs();
							this.cu2[z] = objCuxrf1.getCuxrel();
							this.z = this.z + 1;
						}
					}
				}
				
			}//end if existe registro
			
			if (!this.paramwkalt.equals("A") && !this.paramwkalt.equals("T") && !this.paramwkalt.equals("E") ) {
				if (this.xnbr < 1) {
					this.paramna1 = "NO NAME";
				}else {
					this.z = 0;
					this.y = 0;
					while (this.z > 4) {
						this.z = this.z + 1;
						if (!this.cu1[z].equals("")) {
							swcubk = paramwkbank;
							swcunb = this.cu1[z];
							objCumast = myDAOCumast.getUsingSwcubkAndSwcunb(ds, this.swcubk, this.swcunb);
							if (objCumast != null) {
								this.y = this.y + 1;
								if (this.y == 1) {
									paramna1 = objCumast.getCuna1();
									paramna2 = objCumast.getCuna2();
									paramna3 = objCumast.getCuna3();
									paramna4 = objCumast.getCuna4();
									paramna5 = objCumast.getCuna5();
									paramna6 = objCumast.getCuna6();
									this.wkzip = objCumast.getCuzip();
								}else {
									this.na0 = this.fld30;
									paramna6 = paramna5;
									paramna5 = paramna4;
									paramna4 = paramna3;
									paramna3 = paramna2;
									if (this.cu2[z].equals("JAO")) {
										this.na0 = func.StringToArrayString(this.na0, 0, "AND");
										this.na0 = func.StringToArrayString(this.na0, 5, objCumast.getCuna1());
										paramna2 = this.na0;
									}else {
										this.na0 = func.StringToArrayString(this.na0, 0, "OR");
										this.na0 = func.StringToArrayString(this.na0, 4, objCumast.getCuna1());
										paramna3 = paramna2;
									}
								}
							}
						}
					}
					if (!this.paramwktitl.equals("")) {
						if (this.y == 1) {
							paramna6 = paramna5;
							paramna5 = paramna4;
							paramna4 = paramna3;
							paramna3 = paramna2;
							paramna2 = fld30;
							paramna2 = paramwktitl;
						}else {
							if (this.y == 2) {
								paramna6 = paramna5;
								paramna5 = paramna4;
								paramna4 = paramna3;
								paramna3 = fld30;
								paramna3 = paramwktitl;
							}else {
								if (this.y == 3) {
									paramna6 = paramna5;
									paramna5 = paramna4;
									paramna4 = fld30;
									paramna4 = paramwktitl;
								}else {
									paramna6 = paramna5;
									paramna5 = fld30;
									paramna5 = paramwktitl;
								}
							}
						}
					}
				}
			}else {
				this.wcnta = paramwkacct;
				this.wkfld1 = paramwkacct;
				
				objAltnam = myDAOAltnam.getUsingCuenta(ds, wcnta);
				
				if (objAltnam != null) {
					this.paramna1 = "NO NAME";
				}else {
					paramna1 = objAltnam.getNamel1();
					paramna2 = objAltnam.getNamel2();
					paramna3 = objAltnam.getNamel3();
					this.wkzip = objAltnam.getCposta();
					paramna4 = objAltnam.getAdres1();
					paramna5 = objAltnam.getAdres2();
					paramna6 = objAltnam.getAdres3();
					
				}
				
			}
			
			if (this.wkzip != 0) {
				Boolean flag = false;
				if (!paramna6.equals(this.fld30)) {
					this.z = 6;
					this.na0 = paramna6;
					flag = true;
				}
				if (!paramna5.equals(this.fld30)) {
					this.z = 5;
					this.na0 = paramna5;
					flag = true;
				}
				if (!paramna4.equals(this.fld30)) {							
					this.na0 = paramna4;
					this.z = 4;
					flag = true;
				}
				if (!paramna3.equals(this.fld30)) {
					this.na0 = paramna3;
					this.z = 3;
					flag = true;
				}
				if (flag == false) {//otro valor
					this.na0 = paramna2;
					this.z = 2;
				}
				this.y = 30;
				while (this.y >= 1 && this.na0.substring(this.y, this.y+1).equals("")) {
					this.y = this.y - 1;
				}
				if (this.y < 1) {
					this.y = 1;
				}
				if (this.y > 26) {
					this.y = 26;
					if (this.z != 6) {
						this.na0 = this.fld30;
						this.z = this.z + 1;
					}
				}
				this.zip30 = this.wkzip;
				this.na0 = func.StringToArrayString(this.na0, this.y, this.zip30.toString());
				flag = false;
				if (this.z == 6) {
					this.na0 = paramna6;
					flag = true;
				}
				if (this.z == 5) {
					this.na0 = paramna5;
					flag = true;
				}
				if (this.z == 4) {
					this.na0 = paramna4;
					flag = true;
				}
				if (this.z == 3) {
					this.na0 = paramna3;
					flag = true;
				}
				if (flag == false) {//otro valor
					this.na0 = paramna2;
				}
			}
			paramwkalt = " ";
			paramwktitl = "";
			paramwkhmph = (int) (long)objCumast.getCuhmph();
			paramwkbuph = (int) (long)objCumast.getCubuph();
			paramwkssno = (int) (long)objCumast.getCussnr();
			this.cur = func.StringToArrayString(this.cur, 0, paramwkbank);
			paramwkacct = this.wkaccx;
						
			// retrieve all elements
			Map<String,String> attributes = CollectionFactory.createMap();
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found in " + diff + " millis");
			
			String[] fields = new String[] {
					"WKBANK",
					"WKACCT",
					"WKALT",
					"WKTITL",
					"WKAPPL",
					"NA1",
					"NA2",
					"NA3",
					"NA4",
					"NA5",
					"NA6",
					"WKSSNO",
					"WKBUPH",
					"WKHMPH",
			};
			adapter = new CUS060Adapter();
			adapter.setWKBANK(paramwkbank);
			adapter.setWKACCT(paramwkacct);
			adapter.setWKALT(paramwkalt);
			adapter.setWKTITL(paramwktitl);
			adapter.setWKAPPL(paramwkappl);
			adapter.setNA1(paramna1);
			adapter.setNA2(paramna2);
			adapter.setNA3(paramna3);
			adapter.setNA4(paramna4);
			adapter.setNA5(paramna5);
			adapter.setNA6(paramna6);
			adapter.setWKSSNO(paramwkssno);
			adapter.setWKBUPH(paramwkbuph);
			adapter.setWKHMPH(paramwkhmph);
			
			// Obtains the user JSON representation
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
	
	public class CUS060Adapter {
		String WKBANK = null;
		String WKACCT = null;
		String WKALT = null;
		String WKTITL = null;
		String WKAPPL = null;
		String NA1 = null;
		String NA2 = null;
		String NA3 = null;
		String NA4 = null;
		String NA5 = null;
		String NA6 = null;
		Integer WKSSNO = 0;
		Integer WKBUPH = 0;
		Integer WKHMPH = 0;
		
		public CUS060Adapter() {
			
		}

		public String getWKBANK() {
			return WKBANK;
		}

		public void setWKBANK(String wKBANK) {
			WKBANK = wKBANK;
		}

		public String getWKACCT() {
			return WKACCT;
		}

		public void setWKACCT(String wKACCT) {
			WKACCT = wKACCT;
		}

		public String getWKALT() {
			return WKALT;
		}

		public void setWKALT(String wKALT) {
			WKALT = wKALT;
		}

		public String getWKTITL() {
			return WKTITL;
		}

		public void setWKTITL(String wKTITL) {
			WKTITL = wKTITL;
		}

		public String getWKAPPL() {
			return WKAPPL;
		}

		public void setWKAPPL(String wKAPPL) {
			WKAPPL = wKAPPL;
		}

		public String getNA1() {
			return NA1;
		}

		public void setNA1(String nA1) {
			NA1 = nA1;
		}

		public String getNA2() {
			return NA2;
		}

		public void setNA2(String nA2) {
			NA2 = nA2;
		}

		public String getNA3() {
			return NA3;
		}

		public void setNA3(String nA3) {
			NA3 = nA3;
		}

		public String getNA4() {
			return NA4;
		}

		public void setNA4(String nA4) {
			NA4 = nA4;
		}

		public String getNA5() {
			return NA5;
		}

		public void setNA5(String nA5) {
			NA5 = nA5;
		}

		public String getNA6() {
			return NA6;
		}

		public void setNA6(String nA6) {
			NA6 = nA6;
		}

		public Integer getWKSSNO() {
			return WKSSNO;
		}

		public void setWKSSNO(Integer wKSSNO) {
			WKSSNO = wKSSNO;
		}

		public Integer getWKBUPH() {
			return WKBUPH;
		}

		public void setWKBUPH(Integer wKBUPH) {
			WKBUPH = wKBUPH;
		}

		public Integer getWKHMPH() {
			return WKHMPH;
		}

		public void setWKHMPH(Integer wKHMPH) {
			WKHMPH = wKHMPH;
		}
		
		
		
	}

}
