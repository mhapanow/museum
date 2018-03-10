package com.ciessa.museum.tools;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.ext.servlet.ServletAdapter;
import org.restlet.ext.spring.RestletFrameworkServlet;
import org.springframework.beans.BeansException;

public class MuseumRestletFrameworkServlet extends RestletFrameworkServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7501493955463894202L;

	@Override
	protected Context createContext() {
		// TODO Auto-generated method stub
		return super.createContext();
	}

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doService(request, response);
	}

	@Override
	protected ServletAdapter getAdapter() {
		// TODO Auto-generated method stub
		return super.getAdapter();
	}

	@Override
	protected Restlet getTargetRestlet() {
		// TODO Auto-generated method stub
		return super.getTargetRestlet();
	}

	@Override
	public String getTargetRestletBeanName() {
		// TODO Auto-generated method stub
		return super.getTargetRestletBeanName();
	}

	@Override
	protected void initFrameworkServlet() throws ServletException, BeansException {
		// TODO Auto-generated method stub
		super.initFrameworkServlet();
	}

	@Override
	public void setTargetRestletBeanName(String targetRestletBeanName) {
		// TODO Auto-generated method stub
		super.setTargetRestletBeanName(targetRestletBeanName);
	}

}
