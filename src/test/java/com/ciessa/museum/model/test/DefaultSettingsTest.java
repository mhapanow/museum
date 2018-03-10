package com.ciessa.museum.model.test;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.ciessa.museum.auth.AuthHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.User;

import junit.framework.TestCase;

public class DefaultSettingsTest extends TestCase {

	private static SessionFactory factory;

	@Test
	public void testHibernate() {

		try {
			Configuration config = new Configuration();
			config.configure("hibernate.cfg.xml");
			factory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		Session session = factory.openSession();
		Transaction tx = null;

		try {
			{
				tx = session.beginTransaction();
				Query q = session.createQuery("from User where key = :key");
				q.setParameter("key", User.DEFAULT_ADMIN_KEY);
				User o = (User) q.uniqueResult();
				if (o == null) {
					o = new User();
					o.setKey(User.DEFAULT_ADMIN_KEY);
					o.setEmail("info@ciessa.com");
					o.setFirstname("Admin");
					o.setLastname("Admin");
					o.setLogin("admin");
					o.setPassword(new AuthHelper().encodePassword("admin01"));
					o.setRole(User.ROLE_ADMIN);
					o.setDefaultDataSet(DataSet.DEFAULT_DS_KEY);
					session.save(o);
					System.out.println("User Admin Created");
				}
				System.out.println("User: " + o.toString());
				tx.commit();
			}

			{
				tx = session.beginTransaction();
				Query q = session.createQuery("from DataSet where key = :key");
				q.setParameter("key", DataSet.DEFAULT_DS_KEY);
				DataSet o = (DataSet) q.uniqueResult();
				if (o == null) {
					Configuration config = new Configuration();
					config.configure("hibernate_legacy.cfg.xml");
					
					o = new DataSet();
					o.setKey(User.DEFAULT_ADMIN_KEY);
					
					o.setName("Default");
					o.setDescription("Default DataSet");
					
					o.setDriverClass(config.getProperty("hibernate.connection.driver_class"));
					o.setUsername(config.getProperty("hibernate.connection.username"));
					o.setPassword(config.getProperty("hibernate.connection.password"));
					o.setUrl(config.getProperty("hibernate.connection.url"));
					o.setDialect(config.getProperty("hibernate.dialect"));
					o.setDefaultCatalog(config.getProperty("hibernate.default_catalog"));
					o.setDefaultSchema(config.getProperty("hibernate.default_schema"));
					
					session.save(o);
					System.out.println("DataSet Default Created");
				}
				System.out.println("DataSet: " + o.toString());
				tx.commit();
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
