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
import com.ciessa.museum.model.legacy.Zrsprer;

public class ZrsprerDAO {
	public Zrsprer getUsingMeorgAndMelogoAndMencctAndMeyfacAndMeaafcAndMecifaAndMeagig(DataSet ds, int meorg, int melogo, String mencct, int meyfac, int meaafc, String mecifa, String meagig) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Zrsprer Where Meorg =:meorg And Melogo =:melogo And Mencct =:mencct And Meyfac =:meyfac And Meaafc =:meaafc And Mecifa =:mecifa And Meagig =:meagig ");
			q.setParameter("meorg", meorg);
			q.setParameter("melogo", melogo);
			q.setParameter("mencct", mencct);
			q.setParameter("meyfac", meyfac);
			q.setParameter("meaafc", meaafc);
			q.setParameter("mecifa", mecifa);
			q.setParameter("meagig", meagig);
			Zrsprer o = (Zrsprer)q.uniqueResult();
			
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
	} // fin public
	
	
	public List<Zrsprer> getUsingW1afacW1cifaW1agigW1orgnW1logoW1acnsW1cansW1baddW1bbahW1ebadW1ebahW1cyduW1obolW1estcW1caclW1cposW1retrW1funcANDw1crbaToList(DataSet ds, int w1afac, int w1cifa, int w1agig, int w1orgn, int w1logo, long w1acns, int w1cans, String w1badd, String w1bbah, String w1ebad, String w1ebah, String w1cydu, String w1obol, String w1estc, String w1cacl, String w1cpos, String w1retr, String w1func, String w1crba) throws ASException {
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
			String queryStr = " from Zrsprer ";
		
			if (Integer.toString(w1afac) != null)
				queryStr = queryStr + " Where meyfac = " + Integer.toString(w1afac).substring(0,1) + " And meaafc = " + Integer.toString(w1afac).substring(2,3);
			
			if (Integer.toString(w1cifa) != null)
				queryStr = queryStr + " And mecifa = " + w1cifa;
			
			if(Integer.toString(w1agig) != null)
				queryStr = queryStr + " And meagig = "+ w1agig;
			
			if(Integer.toString(w1orgn) != null)
				queryStr = queryStr + " And meorg = "+ w1orgn;
			
			if (Integer.toString(w1logo) != null)
			queryStr = queryStr + " And melogo = "+ w1logo;
			
			if (Long.toString(w1acns) != null)
			queryStr = queryStr + " And mencct = "+ w1acns;

			if(Integer.toString(w1cans) != null)
				queryStr = queryStr + " And meorg = "+ w1cans;
			
			if (w1badd != null ||  w1bbah != null)
			queryStr = queryStr + " And melogo = "+ w1badd;
			
			//TODO: Revisar estas condiciones
			/*
			 * si w1badd <> 0 o w1bbah <> 0
			 * 		W1BBAD <= MESAFL + (MESAFI “, null); MEOCRT) <= W1BBAH
			 * Fin si
			 * 
			 * si w1ebad <> 0 o w1ebah <> 0
			 * 		W1EBAD <= MEENBA + (MEENBU “, null); MEOCRT) <= W1EBAH
			 * Fin si
			 * */
			
			if (w1cydu != null)
				queryStr = queryStr + " And mecydu = "+ w1cydu;

			if (w1obol != null)
				queryStr = queryStr + " And meobol = "+ w1obol;
	
			if (w1estc != null)
				queryStr = queryStr + " And meestc = "+ w1estc;

			if (w1cacl != null)
				queryStr = queryStr + " And mecacl = "+ w1cacl;
			
			if (w1cpos != null)
				queryStr = queryStr + " And mecpos = "+ w1cpos;

			if (w1retr != null)
				queryStr = queryStr + " And meretr = "+ w1retr;
			
			if (w1func != null)
				queryStr = queryStr + " And mefunc = "+ w1func;
			
			if (w1crba != null)
				queryStr = queryStr + " And meccrba = "+ w1crba;
			
			queryStr =  queryStr + " Order by meyfac, meaafc, mecifa, meagig, meorg, melogo, mencct, mename";
			
			Query q = session.createQuery(queryStr);
			
			@SuppressWarnings("unchecked")
			List<Zrsprer> list = (List<Zrsprer>)q.list();
			
			for( Zrsprer o : list ) {
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
	}// Fin Public
	
	
} //fin public class