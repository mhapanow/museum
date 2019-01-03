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
import com.ciessa.museum.model.legacy.Tap005b;

public class Tap005bDAO {
	public List<Tap005b> getUsingListDmbkAndDmtypAndDmacctAndDmfsttAndRegist(DataSet ds, String dmbk, String dmtyp, String dmacct, String dmfstt, String regist) throws ASException {
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
			Query q = session.createQuery("FROM Tap005b WHERE dhbank = :dmbk and dhtyp = :dmtyp and dhacct = :dmacct and dhstnr = :dmfstt and dhrec = :regist  ORDER BY dheff ");
			q.setParameter("dmbk", dmbk);
			q.setParameter("dmtyp", dmtyp);
			q.setParameter("dmacct", dmacct);
			q.setParameter("dmfstt", dmfstt);
			q.setParameter("regist", regist);
			
			@SuppressWarnings("unchecked")
			List<Tap005b> list = (List<Tap005b>)q.list();
			
			for( Tap005b o : list ) {
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
	
	public List<Tap005b> getUsingListCodecoAndDmtypAndWnctaAndDhrec(DataSet ds, String codeco, String dmtyp, String wncta, String dhrec) throws ASException {
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
			Query q = session.createQuery(" FROM Tap005b where dhbank = :codeco AND dhtyp = :dmtyp AND dhacct = :wncta AND dhrec = :dhrec ");
			q.setParameter("codeco", codeco);
			q.setParameter("dmtyp", dmtyp);
			q.setParameter("wncta", wncta);
			q.setParameter("dhrec", dhrec);
			
			@SuppressWarnings("unchecked")
			List<Tap005b> list = (List<Tap005b>)q.list();
			
			for( Tap005b o : list ) {
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
	
	public List<Tap005b> getUsingKeyCodecoAndDmtypAndWnctaAndDhrecAndWfech(DataSet ds, String codeco, String dmtyp, String wncta, String dhrec, String wfech) throws ASException {

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
			Query q = session.createQuery(" FROM Tap005b where dhbank = :codeco AND dhtyp = :dmtyp AND dhacct = :wncta AND dhrec = :dhrec AND dheff  >= :wfech ");
			q.setParameter("codeco", codeco);
			q.setParameter("dmtyp", dmtyp);
			q.setParameter("wncta", wncta);
			q.setParameter("dhrec", dhrec);
			q.setParameter("wfech", wfech);
			
			@SuppressWarnings("unchecked")
			List<Tap005b> list = (List<Tap005b>)q.list();
			
			for( Tap005b o : list ) {
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
	
	public Tap005b getUsingKey(DataSet ds) throws ASException {

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
			Query q = session.createQuery("FROM Tap005b ");

			Tap005b o = (Tap005b)q.uniqueResult();
			
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
	}
	
	public List<Tap005b> getUsingListCodecoAndDmtypAndWncta(DataSet ds, String codeco, String dmtyp, String wncta) throws ASException {
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
			Query q = session.createQuery("FROM Tap005b WHERE dhbank = :codeco and dhtyp = :dmtyp and dhacct = :wncta ORDER BY dhstnr, dhrec, dheff ");
			q.setParameter("codeco", codeco);
			q.setParameter("dmtyp", dmtyp);
			q.setParameter("wncta", wncta);
			
			@SuppressWarnings("unchecked")
			List<Tap005b> list = (List<Tap005b>)q.list();
			
			for( Tap005b o : list ) {
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
	
	

}
