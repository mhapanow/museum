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
import com.ciessa.museum.model.legacy.Zrspple;

public class ZrsppleDAO {
	
	public Zrspple getUsigRlorgAndRllogoAndRlncctAndRlaaf4AndRlcifaAndRlagigAndRltenv (DataSet ds, String rlorg, String rllogo, String rlncct, String rlaaf4, String rlcifa, String rlagig, String rltenv ) throws ASException {
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
			Query q = session.createQuery(" FROM Zrspple Where Rlorg = :rlorg And Rllogo = :rllogo And Rlncct = :rlncct And Rlaaf4 = :rlaaf4 And Rlcifa = :rlcifa And Rlagig = :rlagig And Rltenv = :rltenv ");
			q.setParameter("rlorg", rlorg);
			q.setParameter("rllogo", rllogo);
			q.setParameter("rlncct", rlncct);
			q.setParameter("rlaaf4", rlaaf4);
			q.setParameter("rlcifa", rlcifa);
			q.setParameter("rlagig", rlagig);
			q.setParameter("rltenv", rltenv);
			Zrspple o = (Zrspple)q.uniqueResult();
			
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
