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
import com.ciessa.museum.model.legacy.Ccrpcre;
import com.ciessa.museum.tools.Range;


public class CcrpcreDAO {

	public Ccrpcre getUsingNpres(DataSet ds, String npres) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Ccrpcre Where crbanc = 1 and crnucr = :npres ");
			q.setParameter("npres", npres);
			Ccrpcre o = (Ccrpcre)q.uniqueResult();
			
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
	} // fin public
	
	
	public Ccrpcre getUsingNumcre(DataSet ds, String numcre) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Ccrpcre Where crbanc = 1 and crnucr = :numcre ");
			q.setParameter("numcre", numcre);
			Ccrpcre o = (Ccrpcre)q.uniqueResult();
			
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
	} // fin public

	public List<Ccrpcre> getUsingCrbancCrcsucCrcdivAndCrstco(DataSet ds,Integer crbanc, String crcsuc, String crcdiv, String crstco, Range range) throws ASException {
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
			
			Query q = session.createQuery("FROM Ccrpcre WHERE crbanc = :crbanc and crcsuc = :crcsuc and crcdiv = :crcdiv and crstco = :crstco ");
			q.setParameter("crbanc", crbanc);
			q.setParameter("crcsuc", crcsuc);
			q.setParameter("crcdiv", crcdiv);
			q.setParameter("crstco", crstco);
			
			if( range != null ) {
				q.setFirstResult(range.getFrom());
				q.setMaxResults(range.getTo() - range.getFrom());
			}
			
			@SuppressWarnings("unchecked")
			List<Ccrpcre> list = (List<Ccrpcre>)q.list();
			
			for( Ccrpcre o : list ) {
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
	}
	
	public List<Ccrpcre> getUsingCrntar(DataSet ds,String crntar, Range range) throws ASException {
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
			
			Query q = session.createQuery(" FROM Ccrpcre WHERE crntar = :crntar ");
			q.setParameter("crntar", crntar);
			
			if( range != null ) {
				q.setFirstResult(range.getFrom());
				q.setMaxResults(range.getTo() - range.getFrom());
			}
			
			@SuppressWarnings("unchecked")
			List<Ccrpcre> list = (List<Ccrpcre>)q.list();
			
			for( Ccrpcre o : list ) {
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
	}

} //fin public class