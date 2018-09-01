package com.ciessa.museum.dao.legacy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ciessa.museum.dao.FactoryManager;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Tap014;
import com.ciessa.museum.tools.Range;

public class Tap014DAO {

	public ArrayList<Tap014> getUsingWcta(DataSet ds, String wcta, String waca, String wpto, String wtod, Date fecjud, Date fecjuh, double wimp, Range range) throws ASException {
		SessionFactory factory = null;
		try {
			factory = FactoryManager.getInstance().getFactory(ds);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;
		Calendar cal;

		try {
			tx = session.beginTransaction();
			String queryStr = "FROM Tap014 WHERE dmbk = 1 AND dmtyp = 6 AND dmacct = :wcta";
			if (waca.equals("X"))
				queryStr = queryStr + " AND dmtodf = 1 ";
			if (wpto.equals("X"))
				queryStr = queryStr + " AND dmtodf = 2 ";
			if (wtod.equals("X"))
				queryStr = queryStr + " AND dmtodf = 9 ";
			if (null != fecjud && null != fecjuh) {
				queryStr = queryStr + " AND (";
				cal = Calendar.getInstance();
				cal.setTime(fecjud);
				queryStr = queryStr + "doamnx >= " + cal.get(Calendar.YEAR) + new DecimalFormat("000").format(cal.get(Calendar.DAY_OF_YEAR)) +  " AND ";
				cal.setTime(fecjuh);
				queryStr = queryStr + "doamnx <= " + cal.get(Calendar.YEAR) + new DecimalFormat("000").format(cal.get(Calendar.DAY_OF_YEAR));
				queryStr = queryStr +  " ) ";
			}
			if (wimp > 0)
				queryStr = queryStr + " AND dolima >= " + wimp;
			queryStr = queryStr + " AND (dmtodf = 1 OR dmtodf = 2 OR (DMTODF = 9 AND dotacc > 0) ) ";
			queryStr = queryStr + " ORDER BY dmbk, dmtyp, dmacct, dmtodf, dodate, dotie ";
			Query q = session.createQuery(queryStr);
			q.setParameter("wcta", wcta);
			
			if( range != null ) {
				q.setFirstResult(range.getFrom());
				q.setMaxResults(range.getTo() - range.getFrom());
			}
			
			@SuppressWarnings("unchecked")
			ArrayList<Tap014> list = (ArrayList<Tap014>)q.list();
			
			if( list.size() == 0 ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wcta);
			}
			
			session.evict(list);
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
	
	public ArrayList<Tap014> getUsingWbas(DataSet ds, String wbas, String waca, String wpto, String wtod, Date fecjud, Date fecjuh, double wimp, Range range) throws ASException {
		SessionFactory factory = null;
		try {
			factory = FactoryManager.getInstance().getFactory(ds);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;
		Calendar cal;

		try {
			tx = session.beginTransaction();
			String queryStr = "FROM Tap014 WHERE dmbk = 1 AND dmtyp = 6 ";
			queryStr = queryStr + " AND dmacct >= :wbas1 AND dmacct <= :wbas2 ";
			if (waca.equals("X"))
				queryStr = queryStr + " AND dmtodf = 1 ";
			if (wpto.equals("X"))
				queryStr = queryStr + " AND dmtodf = 2 ";
			if (wtod.equals("X"))
				queryStr = queryStr + " AND dmtodf = 9 ";
			if (null != fecjud && null != fecjuh) {
				queryStr = queryStr + " AND (";
				cal = Calendar.getInstance();
				cal.setTime(fecjud);
				queryStr = queryStr + "doamnx >= " + cal.get(Calendar.YEAR) + new DecimalFormat("000").format(cal.get(Calendar.DAY_OF_YEAR)) +  " AND ";
				cal.setTime(fecjuh);
				queryStr = queryStr + "doamnx <= " + cal.get(Calendar.YEAR) + new DecimalFormat("000").format(cal.get(Calendar.DAY_OF_YEAR));
				queryStr = queryStr +  " ) ";
			}
			if (wimp > 0)
				queryStr = queryStr + " AND dolima >= " + wimp;
			queryStr = queryStr + " AND (dmtodf = 1 OR dmtodf = 2 OR (DMTODF = 9 AND dotacc > 0) ) ";
			Query q = session.createQuery(queryStr);
			q.setParameter("wbas1", Long.valueOf(wbas + "00"));
			q.setParameter("wbas2", Long.valueOf(wbas + "99"));
			
			if( range != null ) {
				q.setFirstResult(range.getFrom());
				q.setMaxResults(range.getTo() - range.getFrom());
			}
			
			@SuppressWarnings("unchecked")
			ArrayList<Tap014> list = (ArrayList<Tap014>)q.list();
			
			if( list.size() == 0 ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wbas);
			}
			
			session.evict(list);
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
	
	public Tap014 getUsingDmtypDmacctAndDmtodf(DataSet ds, String dmtyp, String dmacct, Integer dmtodf) throws ASException {
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
			String queryStr = "FROM Tap014 WHERE dmbk = 1 AND dmtyp = :dmtyp AND dmacct = :dmacct AND dmtodf = :dmtodf";
			Query q = session.createQuery(queryStr);
			q.setParameter("dmtyp", dmtyp);
			q.setParameter("dmacct", dmacct);
			q.setParameter("dmtodf", dmtodf);
			Tap014 o = (Tap014)q.setMaxResults(1).uniqueResult();
			
			if( o != null ) {
				//tx.rollback();
				//throw ASExceptionHelper.notFoundException(dmtyp);
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
