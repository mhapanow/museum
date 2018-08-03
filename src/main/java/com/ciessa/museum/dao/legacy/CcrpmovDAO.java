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
import com.ciessa.museum.model.legacy.Ccrpmov;


public class CcrpmovDAO {
	
	public Ccrpmov getUsingCanmovAndCrnucr(DataSet ds, int canmov, String crnucr) throws ASException	{
		
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
			Query q = session.createQuery(" from Ccrpmov Where mvbanc = 1 and mvnmov = :canmov and mvnucr = :crnucr  ");
			q.setParameter("canmov", canmov);
			q.setParameter("crnucr", crnucr);
			Ccrpmov o = (Ccrpmov)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException();
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

		
		
		
	} // fin public
	
	
	
} //fin public class