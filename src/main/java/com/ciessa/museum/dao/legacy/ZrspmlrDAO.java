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
import com.ciessa.museum.model.legacy.Zrspmlr;

public class ZrspmlrDAO {
	public Zrspmlr getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(DataSet ds, String meyfac, String meaafc, String mecifa, String meagig, String aaorgn, String melogo, String mencct) throws ASException	{
			
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
			Query q = session.createQuery(" from Zrspmlr WHERE MLYFAC = :meyfac AND MLAAFC = :meaafc AND MLCIFA = :mecifa AND MLAGIG = :meagig AND MLORG = :aaorgn AND MLLOGO = :melogo AND MLNCCT = :mencct ");
			q.setParameter("meyfac", meyfac);
			q.setParameter("meaafc", meaafc);
			q.setParameter("mecifa", mecifa);
			q.setParameter("meagig", meagig);
			q.setParameter("aaorgn", aaorgn);
			q.setParameter("melogo", melogo);
			q.setParameter("mencct", mencct);
			Zrspmlr o = (Zrspmlr)q.uniqueResult();
			
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
	
	public List<Zrspmlr> getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedict(DataSet ds, String meyfac, String meaafc, String mecifa, String meagig, String aaorgn, String melogo, String mencct, String meract, String medict, String mlmini) throws ASException{
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
			sb.append(" FROM Zrspmlr WHERE MLYFAC = :meyfac AND MLAAFC = :meaafc AND MLCIFA = :mecifa AND MLAGIG = :meagig AND MLORG = :aaorgn AND MLLOGO = :melogo AND MLNCCT = :mencct AND SUBSTRING(MLNCCT, 6, 15) = :meract AND SUBSTRING(MLNCCT, 16, 19) = :medict AND MLMINI = :mlmini ORDER BY MLYFAC, MLAAFC, MLCIFA, MLAGIG, MLORG, MLLOGO, MLNCCT, MLMINI, MLNCRD, MLUBIR, MLXTR1, MLCMOV, MLYMOV, MLAMOV, MLMMOV, MLDMOV, MLNSEM  ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("meyfac", meyfac);
			q.setParameter("meaafc", meaafc);
			q.setParameter("mecifa", mecifa);
			q.setParameter("meagig", meagig);
			q.setParameter("aaorgn", aaorgn);
			q.setParameter("melogo", melogo);
			q.setParameter("mencct", mencct);
			q.setParameter("meract", meract);
			q.setParameter("medict", medict);
			q.setParameter("mlmini", mlmini);
			
			@SuppressWarnings("unchecked")
			List<Zrspmlr> list = (List<Zrspmlr>)q.list();
			for ( Zrspmlr o : list ) {
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
