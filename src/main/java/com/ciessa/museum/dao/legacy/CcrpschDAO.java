package com.ciessa.museum.dao.legacy;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.ciessa.museum.dao.FactoryManager;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Ccrpsch;
import com.ciessa.museum.tools.Range;


public class CcrpschDAO {

	public List<Ccrpsch> getUsingCrnucr(DataSet ds, String crnucr, Range range) throws ASException	{
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
			Query q = session.createQuery(" from Ccrpsch Where scbanc = 1 and scnucr = :crnucr ORDER BY scncuo");
			q.setParameter("crnucr", crnucr);

			if( range != null ) {
				q.setFirstResult(range.getFrom());
				q.setMaxResults(range.getTo() - range.getFrom());
			}
			@SuppressWarnings("unchecked")
			List<Ccrpsch> list = (List<Ccrpsch>)q.list();
			for( Ccrpsch o : list ) {
				session.evict(o);
			}
			
			tx.commit();
			return list;
				
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
			}
	} // fin public
	

	public List<Ccrpsch> getUsingCrnucrAndScncuo (DataSet ds, String crnucr, int scncuo) throws ASException	{
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
			Query q = session.createQuery(" from Ccrpsch Where scbanc = 1 and  scnucr = :crnucr and scncuo >= :scncuo ORDER BY scncuo");
			q.setParameter("crnucr", crnucr);
			q.setParameter("scncuo", scncuo);
			
			/*
			Ccrpsch o = (Ccrpsch)q.uniqueResult();
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException();
			}
			session.evict(o);
			*/
			
			@SuppressWarnings("unchecked")
			List<Ccrpsch> list = (List<Ccrpsch>)q.list();
			for( Ccrpsch o : list ) {
				session.evict(o);
			}
			
			tx.commit();
			return list;
				
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
			}
	} // fin public
	
	/*
	public Ccrpsch getUsingSelect (DataSet ds) throws ASException	{
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
			Query q = session.createQuery(" from Ccrpsch");
			Ccrpsch o = (Ccrpsch)q.uniqueResult();
			
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
	*/
	
	public List<Ccrpsch> getUsingnToList(DataSet ds) throws ASException {

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
			StringBuffer sb = new StringBuffer();
			sb.append(" FROM Ccrpsch ");
			Query q = session.createQuery(sb.toString());
			
			@SuppressWarnings("unchecked")
			List<Ccrpsch> list = (List<Ccrpsch>)q.list();
			
			for( Ccrpsch o : list ) {
				session.evict(o);
			}
			tx.commit();
			
			return list;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}// fin public
	
} //fin public class