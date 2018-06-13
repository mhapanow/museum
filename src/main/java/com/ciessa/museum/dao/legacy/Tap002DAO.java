package com.ciessa.museum.dao.legacy;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Tap002;
import com.ciessa.museum.tools.HibernateUtil;

public class Tap002DAO {

	public Tap002 getUsingWcta(DataSet ds, String wcta) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		try {
			Configuration config = new Configuration();
			config.configure("hibernate_legacy.cfg.xml");
			config.setProperty("hibernate.connection.url", ds.getUrl());
			config.setProperty("hibernate.connection.username", ds.getUsername());
			config.setProperty("hibernate.connection.password", ds.getPassword());
			factory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Tap002 where dmbk = 1 and dmtyp = 6 AND dmacct = :wcta");
			q.setParameter("wcta", wcta);
			Tap002 o = (Tap002)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wcta);
			}
			
			session.evict(o);
			tx.commit();
			
			return o;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}
	
	public Tap002 getUsingWbas(DataSet ds, String wbas) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		try {
			Configuration config = new Configuration();
			config.configure("hibernate_legacy.cfg.xml");
			config.setProperty("hibernate.connection.url", ds.getUrl());
			config.setProperty("hibernate.connection.username", ds.getUsername());
			config.setProperty("hibernate.connection.password", ds.getPassword());
			factory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Tap002 WHERE dmbk = 1 AND dmtyp = 6 AND dmacct = :wbas");
			q.setParameter("wbas", wbas.substring(2-1, 7-1));
			Tap002 o = (Tap002)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wbas);
			}
			
			session.evict(o);
			tx.commit();
			
			return o;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}
	
}
