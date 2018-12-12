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
import com.ciessa.museum.model.legacy.Grmria;


public class GrmriaDAO {

	public Grmria getUsingRqactn(DataSet ds, String wsacct) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Grmria where rqprcd = 'CA' and rqactn = :wsacct and rqacrt = 'T' ");
			q.setParameter("wsacct", wsacct);
			Grmria o = (Grmria)q.uniqueResult();
			
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
	
public List<Grmria> getUsingRqprcdAndRqactn(DataSet ds, String rqprcd, String rqactn) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Grmria where rqprcd = :rqprcd and rqactn = :rqactn ");
			q.setParameter("rqprcd", rqprcd);
			q.setParameter("rqactn", rqactn);
			
			@SuppressWarnings("unchecked")
			List<Grmria> list = (List<Grmria>)q.list();
			
			for( Grmria o : list) {
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

public Grmria getUsingRaprcdAndRaactnAndRqacrt(DataSet ds, String raprcd, String raactn, String rqacrt) throws ASException	{
	
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
		Query q = session.createQuery(" FROM Grmria where rqprcd = :raprcd  AND rqactn = :raactn AND rqacrt = :rqacrt  ");
		q.setParameter("raprcd", raprcd);
		q.setParameter("raactn", raactn);
		q.setParameter("rqacrt", rqacrt);
		Grmria o = (Grmria)q.uniqueResult();
		
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