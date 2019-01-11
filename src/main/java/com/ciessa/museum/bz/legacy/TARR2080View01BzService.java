package com.ciessa.museum.bz.legacy;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.DataSetDAO;

public class TARR2080View01BzService  extends RestBaseServerResource{

	public static final Logger log = Logger.getLogger(TARR2080View01BzService.class.getName());
	
	@Autowired
	DataSetDAO dsDao;
	


}
