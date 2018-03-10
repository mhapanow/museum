package com.ciessa.museum.tools;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@SuppressWarnings("serial")
public class InitAppServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(InitAppServlet.class.getName());

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			// Warm up request;
			SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
			Session session = factory.openSession();
			session.close();
		} catch( Throwable t ) {
		} finally {
			log.log(Level.INFO, "Basic data initialized!");
		}
	}

}
