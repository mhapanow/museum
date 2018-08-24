package com.ciessa.museum.bz.legacy;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.dao.legacy.DtgpdesDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;
import com.ciessa.museum.model.legacy.Dtgpdes;

public class ZRSTEMMVView01BzService extends RestBaseServerResource {
	public static final Logger log = Logger.getLogger(ZRSTEMMVView01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	
	@Autowired
	DtgpdesDAO myDAODtgpdes;
	
	List<Dtgpdes> ListDtgpdes = null;
	String syapaso  = null;
	String scabecera = null;
	Integer Sind = null;
	
	String wtctal = null;
	String wtctai = null;
	String sadhoc = null;
	String wapr16 = null;
	String fqdivr = null;
	String wapr15 = null;
	String fqdivg = null;
	String wapd16 = null;
	String wapd15 = null;
	String dsfeho = null;
	String dscoca = null;
	String dscoac = null;
	Integer y = null;
	
	String dwcoac = null;
	String wwcoac = null;
	String[] mon = null;
	String[] moe = null;
	String[] mta = null;
	String[] daj = null;
	
	String wconfi = null;
	String mecacl = null;
	String mone = null;
	String sposic = null;
	String sslmcm = null;
	String memlco = null;
	String metcon = null;
	String aaorgn = null;
	String meorg = null;
	
	//Funtions
	
	FUNCIONESBzService fc = new FUNCIONESBzService();
	
	public String SubProcGetstmdet (String SstmHdr, String Spos, String SmaxElem, String Sfmt, Integer Sind  ) {
		long start = markStart();
		try {
			// validate authToken
			User user = this.getUserFromToken();
			DataSet ds = dsDao.get(user.getDefaultDataSet());
						
			this.SubRutAinit(ds);
			this.syapaso = "1";
			this.scabecera = SstmHdr;
			this.Sind = 0;
			this.SubRutApprde(ds, Spos);
			
			
		} catch (ASException e) {
			if (e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENEXPIRED_CODE
					|| e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENMISSING_CODE) {
				log.log(Level.INFO, e.getMessage());
			} else {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
			//returnValue = getJSONRepresentationFromException(e);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			//returnValue = getJSONRepresentationFromException(ASExceptionHelper.defaultException(e.getMessage(), e));
		} finally {
			markEnd(start);
		}
		
		return "";
	}
	
	private String SubRutAinit(DataSet ds) {
		try {
			this.wtctal = null;
			this.wtctai = null;
			this.sadhoc = null;
			this.wapr16 = this.fqdivr; // TODO.:NO EXISTE
			this.wapr15 = this.fqdivg; // TODO.:NO EXISTE
			this.wapd16 = this.fqdivr; // TODO.:NO EXISTE
			this.wapd15 = this.fqdivg; // TODO.:NO EXISTE
			this.dsfeho = this.fc.FormatoFechaHora("HH:mm:ss yy.MM.dd");
			this.dscoca = "810";
			this.dscoac = "";
			this.y = 0;
			
			ListDtgpdes = myDAODtgpdes.getUsingDscoca(ds, this.dscoca);
			
			for(Dtgpdes o : ListDtgpdes) {
				this.y = this.y +1;
				this.dwcoac = o.getDscoac();
				this.wwcoac = this.dwcoac;
				if (o.getDsvl01() == 0) {
					mon[y] = this.wwcoac;
				}else {
					mon[y] = o.getDsvl01().toString();
				}
				moe[y] = o.getDsds02();
			}
			this.dscoca = "3";
			this.dscoac = "400";
			this.y = 0;
			ListDtgpdes = myDAODtgpdes.getUsingDscoca(ds, this.dscoca);
			for(Dtgpdes o : ListDtgpdes) {
				if (o.getDstreg() == "6") {
					if (o.getDscoac() == "413" | o.getDscoac() == "414") {
						y = y + 1;
						this.mta[y] = o.getDscoac();
						this.daj[y] = o.getDsds02();
					}
				}
			}
			y = 1;
			
			
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		
		
		return "";
	}
	
	
	private String SubRutApprde (DataSet ds, String Spos) {
		try {
			this.wconfi = this.mecacl; // TODO.: No existe mecacl
			this.mone = "pesos";
			this.mone = "";
			this.sposic = Spos;
			if (this.mecacl == "c") {
				this.sslmcm = this.memlco; // TODO.: No existe sslmcm y memlco
			}
			if (this.metcon == "1" | this.metcon == "3") { //TODO.: no existe metcon
				this.aaorgn = this.meorg; // TODO.: no existe aargn y meorg
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return e.getMessage();
		}
		return "";
	}

}
