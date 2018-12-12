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
import com.ciessa.museum.model.legacy.Tap902;

public class Tap902DAO {

	public Tap902 getUsingCuentaAndCaplp(DataSet ds, String cuenta, Integer caplp) throws ASException {
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
			Query q = session.createQuery("FROM Tap902 where cbnk = 1 AND crel = 1 AND caplp = :caplp and nctap = :cuenta");
			q.setParameter("cuenta", cuenta);
			q.setParameter("caplp", caplp);
			Tap902 o = (Tap902)q.uniqueResult();
			
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

	public Tap902 getUsingLdbankANDAcodrANDaaplicANDancta1(DataSet ds, String ldbank, String acodr, String aaplic, String ancta1) throws ASException {
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
			Query q = session.createQuery("FROM Tap902 where cbnk = :ldbank AND crel = :acodr AND caplv = :aaplic AND nctav = :ancta1 ");
			q.setParameter("ldbank", ldbank);
			q.setParameter("acodr", acodr);
			q.setParameter("aaplic", aaplic);
			q.setParameter("ancta1", ancta1);
			Tap902 o = (Tap902)q.uniqueResult();
			
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
	
	public List<Tap902> getUsingListLdbankANDAcodrANDaaplicANDancta1(DataSet ds, String ldbank, String acodr, String aaplic, String ancta1) throws ASException {
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
			sb.append("FROM Tap902 where cbnk = :ldbank AND crel = :acodr AND caplv = :aaplic AND nctav = :ancta1 ");
			
			Query q = session.createQuery(sb.toString());
			
			q.setParameter("ldbank", ldbank);
			q.setParameter("acodr", acodr);
			q.setParameter("aaplic", aaplic);
			q.setParameter("ancta1", ancta1);
			
			@SuppressWarnings("unchecked")
			List<Tap902> list = (List<Tap902>)q.list();
			
			for( Tap902 o : list ) {
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
