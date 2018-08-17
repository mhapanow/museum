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
import com.ciessa.museum.model.legacy.Grmida;


public class GrmidaDAO {

	public Grmida getUsingRirmcn(DataSet ds, String rqrmcn) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Grmida where rirmcn = :rqrmcn ");
			q.setParameter("rqrmcn", rqrmcn);
			Grmida o = (Grmida)q.uniqueResult();
			
			if( o != null ) {
				// tx.rollback();
				// throw ASExceptionHelper.notFoundException(rqrmcn);
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
	
	
	
	
	public Grmida getUsingCrnucl(DataSet ds, String crnucl) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Grmida Where rirmcn = :crnucl and ristat ='A' ");
			q.setParameter("crnucl", crnucl);
			Grmida o = (Grmida)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(crnucl);
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