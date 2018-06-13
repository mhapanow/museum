package com.ciessa.museum.bz.legacy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.bz.legacy.TAR0077ViewBzService.TAR0077Adapter;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.AltnamDAO;
import com.ciessa.museum.dao.legacy.SaldomDAO;
import com.ciessa.museum.dao.legacy.Tap002DAO;
import com.ciessa.museum.dao.legacy.Tap014DAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Altnam;
import com.ciessa.museum.model.legacy.Saldom;
import com.ciessa.museum.model.legacy.Tap002;
import com.ciessa.museum.model.legacy.Tap014;

public class Fer1020View2BzService extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(TAR0077ListBzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;

	@Autowired
	Tap002DAO myDaoTap002;
	
	@Autowired
	AltnamDAO myDaoAltnam;
	
	@Autowired
	SaldomDAO myDaoSaldom;
	
	@Autowired
	Tap014DAO myDaoTap014;
	
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	@Get
	public String view() {
		long start = markStart();
		JSONObject returnValue;
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
			long millisPre = new Date().getTime();
			
			Date fechaDia = new Date();
			
			String wbas = obtainStringValue("wbas", null);
			String wcta = obtainStringValue("wcta", null);
			String waca = obtainStringValue("waca", null);
			String wpto = obtainStringValue("wpto", null);
			String wtod = obtainStringValue("wtod", null);
			String wvigd = obtainStringValue("wvigd", null);
			String wvigh = obtainStringValue("wvigh", null);
			double wimp = obtainDoubleValue("wimp",0d);
			
			Date fecjud = null;
			Date fecjuh = null;
			
			boolean rpta = SubRut001(ds, wcta, wbas, wvigd, wvigh, wtod, fecjud, fecjuh);
			if (rpta == false)
			{
				log.log(Level.SEVERE, "Prueba Error", new Exception());
				//TODO: Personalizar los mensajes en SubRut001
				return getJSONRepresentationFromException(ASExceptionHelper.defaultException("Prueba Error", new Exception())).toString();
			}
			else
			{
				ArrayList<Tap014> lstTap014 = SubRut007(ds, wcta, wbas, waca, wpto, wtod, fecjud, fecjuh, wimp);

				// Get the output fields
				String[] fields = new String[] {
						"pkid",
						"member",
						"DMBK",
						"DMTYP",
						"DMACCT",
						"DMTODF",
						"DODATE",
						"DOTIE"
				};
				
				// Obtains the user JSON representation
				returnValue = getJSONRepresentationFromObject(lstTap014, fields);
			}
			
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
	
	private boolean SubRut001(DataSet ds, String wcta, String wbas, String wvigd, String wvigh, String wtod, Date fecjud, Date fecjuh) throws ParseException {
		String pgreco;
		try {
			if (Integer.parseInt(wcta) > 0) {
				Tap002 obj = myDaoTap002.getUsingWcta(ds, wcta);
				if (obj == null)
					return false;
				else
					return true;
			}
			if (Integer.parseInt(wbas) > 0) {
				Tap002 obj = myDaoTap002.getUsingWbas(ds, wbas);
				if (obj == null)
					return false;
				else
					return true;
			}
			if ((Integer.parseInt(wbas) == 0 && Integer.parseInt(wcta) == 0) || (Integer.parseInt(wbas) > 0 && Integer.parseInt(wcta) > 0)){
				return false;
			}
			if (Integer.parseInt(wvigd) > 0) {
				if (wtod == "X")
					return false;
				else {
					fecjud = format.parse(wvigd);
				}
			}
			if (Integer.parseInt(wvigh) > 0) {
				if (wtod == "X")
					return false;
				else
				{
					fecjuh = format.parse(wvigh);
				}
			}
		
		} catch (ASException e) {
			return false;
		}
		return true;
	}
	
	private ArrayList<Tap014> SubRut007(DataSet ds, String wcta, String wbas, String waca, String wpto, String wtod, Date fecjud, Date fecjuh, double wimp) throws ParseException, ASException {
		Long wdias;
		Date dexaca;
		Tap002 objTap002 = myDaoTap002.getUsingWcta(ds, wcta);
		Altnam objAltNam = myDaoAltnam.getUsingWcta(ds, wcta);
		if (objTap002.getDmcbal().intValue() < 0) {
			Saldom objSaldom = myDaoSaldom.getUsingWcta(ds, wcta);
			if (objSaldom != null) {
				if (objSaldom.getDexaca() > 0) {
					//TODO: Resolver
					dexaca = format.parse(objSaldom.getDexaca().toString());
					long currentTime = Calendar.getInstance().getTime().getTime(); 
					wdias = Calendar.getInstance().getTime().getTime() - dexaca.getTime();
				}
				else
				{
					wdias = (long)0;
				}
			}
			else
				wdias = (long)0;
		}
		else
			wdias = (long)0;
		//
		ArrayList<Tap014> lstTap014 = SubRut006(ds, wcta, wbas, waca, wpto, wtod, fecjud, fecjuh, wimp);
		return lstTap014;
	}
	
	private ArrayList<Tap014> SubRut006(DataSet ds, String wcta, String wbas, String waca, String wpto, String wtod, Date fecjud, Date fecjuh, double wimp) {
		ArrayList<Tap014> lstTap014 = null;
		try {
			if (wcta != "")
				lstTap014 = myDaoTap014.getUsingWcta(ds, wcta, waca, wpto, wtod, fecjud, fecjuh, wimp);
			if (wbas != "")
				lstTap014 = myDaoTap014.getUsingWbas(ds, wbas, waca, wpto, wtod, fecjud, fecjuh, wimp);
			return lstTap014;
		}catch (ASException e) {
			return null;
		}
	}
}
