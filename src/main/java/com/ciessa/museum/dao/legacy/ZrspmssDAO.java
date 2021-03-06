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
import com.ciessa.museum.model.legacy.Zrspmss;

public class ZrspmssDAO {
	public Zrspmss getUsingMsyfacAndMsaafcAndMscifaAndMsccycAndMsorgAndMslogoAndMsmgcdAndMsvrsn(DataSet ds, String msyfac, String msaafc, String mscifa, String msccyc, String msorg, String mslogo, String msmgcd, String msvrsn) throws ASException	{
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
			Query q = session.createQuery(" FROM Zrspmss WHERE msyfac = :msyfac And msaafc = :msaafc And mscifa = :mscifa And msccyc = :msccyc And msorg = :msorg And mslogo = :mslogo And msmgcd = :msmgcd And msvrsn = :msvrsn ");
			q.setParameter("msyfac", msyfac);
			q.setParameter("msaafc", msaafc);
			q.setParameter("mscifa", mscifa);
			q.setParameter("msccyc", msccyc);
			q.setParameter("msorg", msorg);
			q.setParameter("mslogo", mslogo);
			q.setParameter("msmgcd", msmgcd);
			q.setParameter("msvrsn", msvrsn);
			
			Zrspmss o = (Zrspmss)q.uniqueResult();
			
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

}
