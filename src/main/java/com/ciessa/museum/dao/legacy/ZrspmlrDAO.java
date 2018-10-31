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
	public List<Zrspmlr> getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(DataSet ds, String meyfac, String meaafc, String mecifa, String meagig, String aaorgn, String melogo, String mencct) throws ASException	{
			
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
			Query q = session.createQuery(" from Zrspmlr WHERE mlyfac = :meyfac AND mlaafc = :meaafc AND mlcifa = :mecifa AND mlagig = :meagig AND mlorg = :aaorgn AND mllogo = :melogo AND mlncct = :mencct ");
			q.setParameter("meyfac", meyfac);
			q.setParameter("meaafc", meaafc);
			q.setParameter("mecifa", mecifa);
			q.setParameter("meagig", meagig);
			q.setParameter("aaorgn", aaorgn);
			q.setParameter("melogo", melogo);
			q.setParameter("mencct", mencct);
			
			
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
			sb.append(" FROM Zrspmlr WHERE mlyfac = :meyfac AND mlaafc = :meaafc AND mlcifa = :mecifa AND mlagig = :meagig AND mlorg = :aaorgn AND mllogo = :melogo AND mlncct = :mencct AND SUBSTRING(mlncct, 6, 15-6+1) = :meract AND SUBSTRING(mlncct, 16, 19-16+1) = :medict AND mlmini = :mlmini ORDER BY mlyfac, mlaafc, mlcifa, mlagig, mlorg, mllogo, mlncct, mlmini, mlncrd, mlubir, mlxtr1, mlcmov, mlymov, mlamov, mlmmov, mldmov, mlnsem  ");
			
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
