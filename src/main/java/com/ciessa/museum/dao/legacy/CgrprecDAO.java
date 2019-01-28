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
import com.ciessa.museum.model.legacy.Cgrprec;

public class CgrprecDAO {
	
	public List<Cgrprec> getUsingListNumcue(DataSet ds, String numcue) throws ASException {
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
			sb.append(" FROM Cgrprec where cgacct = :numcue ORDER BY cgnche, cgnrtr ");
			
			Query q = session.createQuery(sb.toString());
			
			q.setParameter("numcue", numcue);
			
			@SuppressWarnings("unchecked")
			List<Cgrprec> list = (List<Cgrprec>)q.list();
			
			for( Cgrprec o : list ) {
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
	
	public List<Cgrprec> getUsingListNumcueAndCgnche(DataSet ds, String numcue, String cgnche) throws ASException {
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
			sb.append(" FROM Cgrprec where cgacct = :numcue AND cgnche = :cgnche AND (cgtipr = 'V' OR cgtipr ='D' OR cgtipr ='B' OR cgtipr ='L' OR cgtipr ='R' OR cgtipr ='C' OR cgtipr ='E' OR cgtipr ='S') ");
			
			Query q = session.createQuery(sb.toString());
			
			q.setParameter("numcue", numcue);
			q.setParameter("cgnche", cgnche);
			
			@SuppressWarnings("unchecked")
			List<Cgrprec> list = (List<Cgrprec>)q.list();
			
			for( Cgrprec o : list ) {
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
	
	public Cgrprec getUsingAcuentAndChequeAndNrtr(DataSet ds, String acuent, String cheque, String nrtr) throws ASException {
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
			Query q = session.createQuery(" FROM Cgrprec where cgacct = :acuent AND cgnche = :cheque AND cgnrtr = :nrtr ");
			q.setParameter("acuent", acuent);
			q.setParameter("cheque", cheque);
			q.setParameter("nrtr", nrtr);
			
			Cgrprec o = (Cgrprec)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
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
	
	public Cgrprec getUsingCgacctAndCgnche(DataSet ds, String cgacct, String cgnche) throws ASException {
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
			Query q = session.createQuery(" FROM Cgrprec where cgacct = :cgacct AND cgnche = :cgnche  ");
			q.setParameter("cgacct", cgacct);
			q.setParameter("cgnche", cgnche);
			
			Cgrprec o = (Cgrprec)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
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
