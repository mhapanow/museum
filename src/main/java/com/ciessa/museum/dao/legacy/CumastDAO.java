package com.ciessa.museum.dao.legacy;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ciessa.museum.dao.FactoryManager;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Cumast;

public class CumastDAO {

	public Cumast getUsingCunbr(DataSet ds, String cunbr) throws ASException {
		SessionFactory factory = null;
		try {
			factory = FactoryManager.getInstance().getFactory(ds);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Cumast WHERE cubk = 1 AND cunbr = :cunbr");
			q.setParameter("cunbr", cunbr);
			Cumast o = (Cumast)q.uniqueResult();
			
			if( o != null ) {
				session.evict(o);
				tx.commit();
			}
						
			return o;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}
	
	public Cumast getUsingSwcubkAndSwcunb(DataSet ds, String swcubk, String swcunb) throws ASException {
		
		SessionFactory factory = null;
		try {
			factory = FactoryManager.getInstance().getFactory(ds);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Cumast WHERE cubk = :swcubk AND cunbr = :swcunb");
			q.setParameter("swcubk", swcubk);
			q.setParameter("swcunb", swcunb);
			
			Cumast o = (Cumast)q.uniqueResult();
			
			if( o != null ) {
				session.evict(o);
				tx.commit();
			}
			
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
