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
import com.ciessa.museum.model.legacy.Zrsppma;

public class ZrsppmaDAO {
	public Zrsppma getUsingImorgAndImlogoAndImncctAndImaaf4AndImcifaAndImagig(DataSet ds, String imorg, String imlogo, String imncct, String imaaf4, String imcifa, String imagig) throws ASException {
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
			Query q = session.createQuery(" FROM Zrsppma WHERE imorg = :imorg And imlogo = :imlogo And imncct = :imncct And imaaf4 = :imaaf4 And imcifa = :imcifa And  imagig = :imagig ");
			q.setParameter("imorg", imorg);
			q.setParameter("imlogo", imlogo);
			q.setParameter("imncct", imncct);
			q.setParameter("imaaf4", imaaf4);
			q.setParameter("imcifa", imcifa);
			q.setParameter("imagig", imagig);
			
			Zrsppma o = (Zrsppma)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				//throw ASExceptionHelper.notFoundException();
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
