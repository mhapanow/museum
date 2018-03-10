package com.ciessa.museum.bz;

import org.restlet.resource.Get;

public class IPAddressBzService extends RestBaseServerResource {

	@Get
	public String retrieve() {
		return getRequestIP();
	}

}
