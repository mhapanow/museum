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
import com.ciessa.museum.model.legacy.Zrspplr;

public class ZrspplrDAO {
	public List<Zrspplr> getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(DataSet ds, String meyfac, String meaafc, String mecifa, String meagig, String aaorgn, String melogo, String mencct) throws ASException{
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
			sb.append(" FROM Zrspplr Where tlyfac = :meyfac And tlaafc = :meaafc And tlcifa = :mecifa And tlagig = :meagig And tlorg = :aaorgn And tllogo = :melogo And tlncct = :mencct ORDER BY tlyfac, tlaafc, tlcifa, tlagig, tlorg, tllogo, tlncct, tlubir, tlcrcc, tlcraa, tlcrmm, tlcrdd, tlnsem ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("meyfac", meyfac);
			q.setParameter("meaafc", meaafc);
			q.setParameter("mecifa", mecifa);
			q.setParameter("meagig", meagig);
			q.setParameter("aaorgn", aaorgn);
			q.setParameter("melogo", melogo);
			q.setParameter("mencct", mencct);
			
			@SuppressWarnings("unchecked")
			List<Zrspplr> list = (List<Zrspplr>)q.list();
			for ( Zrspplr o : list ) {
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
