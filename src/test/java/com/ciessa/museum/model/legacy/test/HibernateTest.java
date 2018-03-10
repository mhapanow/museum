package com.ciessa.museum.model.legacy.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.ciessa.museum.model.legacy.Cfp00101;

import junit.framework.TestCase;

public class HibernateTest extends TestCase {

	private static SessionFactory factory;
	
	@Test
	public void testHibernate() {

		try {
			Configuration config = new Configuration();
			config.configure("hibernate_legacy.cfg.xml");
			config.setProperty("hibernate.connection.username", "museum" );
			config.setProperty("hibernate.connection.password", "rootman01" );
			factory = config.buildSessionFactory();
			
//			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Cfp00101> list = (List<Cfp00101>)session.createQuery("FROM Cfp00101").list();
			for (Iterator<Cfp00101> iterator = list.iterator(); iterator.hasNext();) {
				Cfp00101 record = (Cfp00101) iterator.next();
				System.out.print("Banco: " + record.getCfbco());
				System.out.print("  Reg: " + record.getCfreg());
				System.out.print("  CodTran: " + record.getCfctr());
				System.out.println("  Desc: " + record.getCfdco());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
