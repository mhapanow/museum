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
import com.ciessa.museum.model.legacy.Tablam;


public class TablamDAO {
	
	
	public Tablam getUsingCvalor(DataSet ds, String cvalor) throws ASException {

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
		Query q = session.createQuery(" from Tablam where CTABLA='21' and CVALOR= :cvalor  and CAUTOR='A' ");
		q.setParameter("cvalor", cvalor);
		Tablam o = (Tablam)q.uniqueResult();
		
		if( o == null ) {
			tx.rollback();
			throw ASExceptionHelper.notFoundException(cvalor);
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
	
	public Tablam getUsingCrcdiv(DataSet ds, String crcdiv) throws ASException {

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
			Query q = session.createQuery(" from Tablam where CTABLA='12' and CVALOR= :crcdiv ");
			q.setParameter("crcdiv", crcdiv);
			Tablam o = (Tablam)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(crcdiv);
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
