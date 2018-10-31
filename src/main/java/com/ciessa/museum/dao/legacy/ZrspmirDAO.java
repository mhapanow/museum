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
import com.ciessa.museum.model.legacy.Zrspmir;

public class ZrspmirDAO {
	public List<Zrspmir> getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencctAndMeractAndMedictAndMimini(DataSet ds, String meyfac, String meaafc, String mecifa, String meagig, String aaorgn, String melogo, String mencct, String meract, String medict, String mimini) throws ASException{
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
			sb.append(" FROM Zrspmir WHERE miyfac = :meyfac AND miaafc = :meaafc AND micifa = :mecifa AND miagig = :meagig AND miorg = :aaorgn AND milogo = :melogo AND mincct = :mencct AND SUBSTRING(mincct, 6, 15-6+1) = :meract AND SUBSTRING(mincct, 16, 19-16+1) = :medict AND mimini = :mimini ORDER BY miyfac, miaafc, micifa, miagig, miorg , milogo, mincct, mimini, mincrd, miubir, mixtr1, micfar, micmov, miccup, miacup, mimcup, midcup, minsem ");
			
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
			q.setParameter("mimini", mimini);
			
			@SuppressWarnings("unchecked")
			List<Zrspmir> list = (List<Zrspmir>)q.list();
			for ( Zrspmir o : list ) {
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

public List<Zrspmir> getUsigMeyfacAndMeaafcAndMecifaAndMeagigAndAaorgnAndMelogoAndMencct(DataSet ds, String meyfac, String meaafc, String mecifa, String meagig, String aaorgn, String melogo, String mencct) throws ASException	{
		
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
			Query q = session.createQuery(" from Zrspmir WHERE miyfac = :meyfac AND miaafc = :meaafc AND micifa = :mecifa AND miagig = :meagig AND miorg = :aaorgn AND milogo = :melogo AND mincct = :mencct ");
			q.setParameter("meyfac", meyfac);
			q.setParameter("meaafc", meaafc);
			q.setParameter("mecifa", mecifa);
			q.setParameter("meagig", meagig);
			q.setParameter("aaorgn", aaorgn);
			q.setParameter("melogo", melogo);
			q.setParameter("mencct", mencct);
			
			@SuppressWarnings("unchecked")
			List<Zrspmir> list = (List<Zrspmir>)q.list();
			for ( Zrspmir o : list ) {
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
