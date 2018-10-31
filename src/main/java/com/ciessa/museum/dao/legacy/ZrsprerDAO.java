package com.ciessa.museum.dao.legacy;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ciessa.museum.bz.legacy.FUNCIONESBzService;
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
			Query q = session.createQuery(" FROM Zrsprer WHERE meorg = :meorg and melogo = :melogo and mencct = :mencct and meyfac = :meyfac and meaafc = :meaafc and mecifa = :mecifa and meagig = :meagig ");
			q.setParameter("meorg", meorg);
			q.setParameter("melogo", melogo);
			q.setParameter("mencct", mencct);
			q.setParameter("meyfac", meyfac); //--meyfac)  20;
			q.setParameter("meaafc", meaafc); //--meaafc)  11;
			q.setParameter("mecifa", mecifa);
			q.setParameter("meagig", meagig);
			Zrsprer o = (Zrsprer)q.uniqueResult();
			
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
	} // fin public
	
	
	public List<Zrsprer> getUsingW1afacW1cifaW1agigW1orgnW1logoW1acnsW1cansW1baddW1bbahW1ebadW1ebahW1cyduW1obolW1estcW1caclW1cposW1retrW1funcANDw1crbaToList(DataSet ds, int w1afac, int w1cifa, int w1agig, int w1orgn, int w1logo, String w1acns, String w1cans, BigDecimal w1bbad, BigDecimal w1bbah, BigDecimal w1ebad, BigDecimal w1ebah, String w1cydu, String w1obol, String w1estc, String w1cacl, String w1cpos, String w1retr, String w1func, String w1crba) throws ASException {
		SessionFactory factory = null;
		FUNCIONESBzService fc = new FUNCIONESBzService();
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
			String queryStr = "";
			String queryFilter = "";
		
			if ((w1afac) != 0)
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " meyfac = " + Integer.toString(w1afac).substring(0,2) + " And meaafc = " + Integer.toString(w1afac).substring(2,4);		
			if ((w1cifa) != 0)
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " mecifa = " + w1cifa;		
			if((w1agig) != 0)
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " meagig = "+ w1agig;		
			if((w1orgn) != 0)
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " meorg = "+ w1orgn;
			if ((w1logo) != 0)
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " melogo = "+ w1logo;
			if (!(w1acns).equals(""))
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " mencct = '"+ w1acns +"'";
			if(!(w1cans).equals(""))
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " mencrd = '"+ w1cans+"'";

			if (fc.BigDecimalComparar(w1bbad.toString(), "0", "!=") || fc.BigDecimalComparar(w1bbah.toString(), "0", "!=")) {
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) +  " mesafl + (mesafi * meocrt) BETWEEN "+ w1bbad +" AND " + w1bbah;
			}
			if (fc.BigDecimalComparar(w1ebad.toString(), "0", "!=") || fc.BigDecimalComparar(w1ebah.toString(), "0", "!=")) {
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) +  " meenba + (meenbu * meocrt) BETWEEN "+ w1ebad +" AND " + w1ebah;
			}
			
			if (!w1cydu.equals(""))
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) +  " mecydu = "+ w1cydu;
			if (!w1obol.equals(""))
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " meobol = "+ w1obol;
			if (!w1estc.equals(""))
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " meestc = "+ w1estc;
			if (!w1cacl.equals(""))
				queryFilter = (queryFilter.equals("") == false ? " AND " : "" ) + " mecacl = '"+ w1cacl+"'";
			//TODO: Este campo "mecpos" no existe en la tabla del sqlserver
			/*
			if (!w1cpos.equals(""))
				queryFilter = (queryFilter.equals("") == false ? " AND " : "" ) + " mecpos = "+ w1cpos;
				 */
			if (!w1retr.equals(""))
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " meretr = '"+ w1retr+"'";			
			if (!w1func.equals(""))
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " mefunc = '"+ w1func+"'";
			if (!w1crba.equals(""))
				queryFilter += (queryFilter.equals("") == false ? " AND " : "" ) + " mecrba = "+ w1crba;
			
			queryStr = " from Zrsprer " +
						" where " + queryFilter + " " + 
						" Order by meyfac, meaafc, mecifa, meagig, meorg, melogo, mencct";
			
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