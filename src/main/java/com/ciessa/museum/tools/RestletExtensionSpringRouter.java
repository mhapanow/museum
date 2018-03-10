package com.ciessa.museum.tools;

import java.util.Map;

import org.restlet.ext.spring.SpringRouter;

public class RestletExtensionSpringRouter extends SpringRouter {

	public void setRoutesMap(Map<String, Object> routes) {
		super.setAttachments(routes);
	}
}
