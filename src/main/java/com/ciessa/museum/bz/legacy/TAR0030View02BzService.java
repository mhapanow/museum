package com.ciessa.museum.bz.legacy;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;
import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;

import com.ciessa.museum.model.legacy.Rncptvp;
import com.ciessa.museum.dao.legacy.RncptvpDAO;
import com.ciessa.museum.model.legacy.Rncppro;
import com.ciessa.museum.dao.legacy.RncpproDAO;
import com.ciessa.museum.model.legacy.Rsctam; 
import com.ciessa.museum.dao.legacy.RsctamDAO;



public class TAR0030View02BzService extends RestBaseServerResource{
	
	public static final Logger log = Logger.getLogger(TAR0030View02BzService.class.getName());

	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	RncptvpDAO myDao; 

	@Autowired
	RncpproDAO myDaoRncppro;

	@Autowired
	RsctamDAO myDaoRsctam;

	Rncptvp objRncptvp = new Rncptvp();
	Rncppro objRncppro = new Rncppro();
	Rsctam 	objRsctam = new Rsctam();
	
	
	String tipo	  = null;
	String cuenta = null;
	String wwmcap = null;
	String wwcvta = null;
	String wwcpro = null;
	String wwprom = null; 
	Integer segmen; 
	Integer bcracd; 
	Integer canext; 
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue = null;
		
		
		try {
			
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();

			String ctacta = obtainStringValue("ctacta", null);
			
			if (ctacta.equals(""))
			{
				log.log(Level.SEVERE, ctacta, new Exception());
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException("Parámetros incorrectos", new Exception())).toString();
			}
			
			if (!ctacta.equals(""))
			{
				cuenta = ctacta;
			}
			
			if (cuenta.charAt(0)!='0' && cuenta.charAt(0)!='5') {
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException("Parámetros incorrectos", new Exception())).toString();
			} 
			else {
				if (cuenta.charAt(0)=='5')
				{
					tipo="1";
				} 
				else
				{
					tipo="6";
				}
			}
			
			
			objRsctam = myDaoRsctam.getUsingCuenta(ds,cuenta);
			if (objRsctam !=null )
			{
				
				 segmen = objRsctam.getCsegme();
				 bcracd = objRsctam.getCactiv();
				 canext = objRsctam.getQresum();
				 
				 if (tipo =="6")
					{
						String rpta = SubRutDattrk(ds);
					
						if (!rpta.equals(""))
						{
							log.log(Level.SEVERE, rpta, new Exception());
							return getJSONRepresentationFromException(ASExceptionHelper.defaultException(rpta, new Exception())).toString();
						}
					}
			}
			
			long diff = new Date().getTime() - millisPre;
			// Logs the result
			log.info("Element found in " + diff + " millis");
			
			
			String[] fields = new String[] {
					"CUENTA",
					"WWPROM",
					"WWCVTA",
					"WWCPRO",
					"WWMCAP",
			};

			// Obtains the user JSON representation
			TAR0030Adapter adapted = new TAR0030Adapter();
			adapted.setCUENTA(this.cuenta);
			adapted.setWWPROM(this.wwprom);
			adapted.setWWCVTA(this.wwcvta);
			adapted.setWWCPRO(this.wwcpro);
			adapted.setWWMCAP(this.wwmcap);
			returnValue = getJSONRepresentationFromObject(adapted, fields);

		
		} //fin try
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
		
				
} // fin @Get

	
	
	private String SubRutDattrk(DataSet ds)	{
	try 
	{
		objRncptvp = myDao.getUsingNrmcap(ds, objRsctam.getNrmcap());
		if (objRncptvp !=null )
		{		
			wwmcap = objRncptvp.getVpdsda();
		}
		objRncptvp = myDao.getUsingNrslch(ds, objRsctam.getNrslch());
		if (objRncptvp !=null )
		{	
			wwcvta = objRncptvp.getVpdsda();
		}
		objRncptvp = null;
		/* COMMENT: Se quita esta funcionalidad porque el filtro es "cadena" y el sql espera un entero
		objRncptvp = myDao.getUsingNrcofn(ds, objRsctam.getNrcofn().substring(0,2));
		if (objRncptvp !=null )
		{	
			wwcpro = objRncptvp.getVpdsda();
		}
		*/
		objRncppro = myDaoRncppro.getUsingNrcppr(ds, objRsctam.getNrcppr());
		if (objRncppro !=null )
		{
			wwprom = objRncppro.getPrappm();
		}
	} catch (ASException e) {
		return e.getMessage();
	}
		return "";
	}
	

	public class TAR0030Adapter {
		
		private String CUENTA;
		private String WWPROM;
		private String WWCVTA;
		private String WWCPRO;
		private String WWMCAP;
		
		
		public TAR0030Adapter() {
			this.CUENTA = cuenta;
			this.WWPROM = wwmcap;
			this.WWCVTA = wwcvta;
			this.WWCPRO = wwcpro;
			this.WWMCAP = wwprom;
			
		}


		public String getCUENTA() {
			return CUENTA;
		}


		public void setCUENTA(String cUENTA) {
			CUENTA = cUENTA;
		}


		public String getWWPROM() {
			return WWPROM;
		}


		public void setWWPROM(String wWPROM) {
			WWPROM = wWPROM;
		}


		public String getWWCVTA() {
			return WWCVTA;
		}


		public void setWWCVTA(String wWCVTA) {
			WWCVTA = wWCVTA;
		}


		public String getWWCPRO() {
			return WWCPRO;
		}


		public void setWWCPRO(String wWCPRO) {
			WWCPRO = wWCPRO;
		}


		public String getWWMCAP() {
			return WWMCAP;
		}


		public void setWWMCAP(String wWMCAP) {
			WWMCAP = wWMCAP;
		}
		
		
	}
	
	
}

