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
import com.ciessa.museum.model.legacy.Zrspdmr;

public class ZrspdmrDAO {
	public List<Zrspdmr> getUsig(DataSet ds) throws ASException{
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
			sb.append(" FROM Zrspdmr Where DRMMDT = 'T' ");
			
			Query q = session.createQuery(sb.toString());
			
			@SuppressWarnings("unchecked")
			List<Zrspdmr> list = (List<Zrspdmr>)q.list();
			for ( Zrspdmr o : list ) {
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

	public Zrspdmr getUsigDrcmonAndDrcoriAndDrcmovAndDrcsmv(DataSet ds, String drcmon, String drcori, String drcmov, String drcsmv) throws ASException	{
		
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
			Query q = session.createQuery(" from Zrspdmr WHERE DRCMON = :drcmon AND DRCORI = :drcori AND DRCMOV = :drcmov AND DRCSMV = :drcsmv ");
			q.setParameter("DRCMON", drcmon);
			q.setParameter("DRCORI", drcori);
			q.setParameter("DRCMOV", drcmov);
			q.setParameter("DRCSMV", drcsmv);
			Zrspdmr o = (Zrspdmr)q.uniqueResult();
			
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
