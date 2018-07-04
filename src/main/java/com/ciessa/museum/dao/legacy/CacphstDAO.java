package com.ciessa.museum.dao.legacy;

import java.util.ArrayList;
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
import com.ciessa.museum.model.legacy.Cacphst;



public class CacphstDAO {

	public List<Cacphst> getUsingHiacct(DataSet ds, String wsacct) throws ASException {

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
			sb.append(" FROM Cacphst WHERE hiacct = :wsacct ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("wsacct", wsacct);
			
			@SuppressWarnings("unchecked")
			List<Cacphst> list = (List<Cacphst>)q.list();
			
			for( Cacphst o : list ) {
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
	
	public List<Cacphst> getUsingHiacctAndClave(DataSet ds, String wsacct, String clave) throws ASException {

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
			sb.append(" FROM Cacphst WHERE hiacct = :wsacct ");
			sb.append(" AND concat(hiacct,hitodf,hidate,hitie,hifsel) = " + clave );
			Query q = session.createQuery(sb.toString());
			q.setParameter("wsacct", wsacct);
			
			@SuppressWarnings("unchecked")
			List<Cacphst> list = (List<Cacphst>)q.list();
			
			for( Cacphst o : list ) {
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
	
	public List<Cacphst> getUsingOrderHifsel(DataSet ds) throws ASException {

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
			sb.append("select hifsel, hitreg, hifunc from Cacphst group by hifsel, hitreg, hifunc order by hifsel desc ");
			Query q = session.createQuery(sb.toString());
			
			List<Cacphst> list = null;
			Cacphst item = null;
			List<?> lstCustom = q.list();
			if (lstCustom.size() > 0) list = new ArrayList<Cacphst>();  
			for(int i=0; i<lstCustom.size(); i++) {
				Object[] row = (Object[]) lstCustom.get(i);
				item = new Cacphst();
				item.setHifsel(Integer.parseInt(row[0].toString()));
				item.setHitreg(row[1].toString());
				item.setHifunc(row[2].toString());
				list.add(item);
			}
			
			/*
			@SuppressWarnings("unchecked")
			List<Cacphst> list = (List<Cacphst>)q.list();
			
			for( Cacphst o : list ) {
				session.evict(o);
			}
			*/
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
	
	public Cacphst getUsingMax(DataSet ds) throws ASException {
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
			Query q = session.createQuery("FROM Cacphst WHERE hifsel IN (SELECT MAX(hifsel) FROM Cacphst)");
			List<Cacphst> list = (List<Cacphst>)q.list();
			Cacphst o = null;
			if (list.size() > 0) {
				o = new Cacphst();
				o.setHifsel(list.get(0).getHifsel());
			}
			
			if( o == null ) {
				tx.rollback();
				// throw ASExceptionHelper.notFoundException();
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
	
	public List<Cacphst> getUsingKey(DataSet ds, Integer Ssfsel, Integer Ssbrch, 
			Integer Ssmrec, String Ssresu) throws ASException {

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
			String queryStr = "FROM Cacphst WHERE hifsel = :ssfsel AND hiresu = :ssresu";
			if (Ssbrch != 999) {
				queryStr = queryStr + " AND hibrch = " + Ssbrch ;
			}
			if (Ssmrec.toString() != "") {
				queryStr = queryStr + " AND himrec = "+ Ssmrec;
			}
			Query q = session.createQuery(queryStr);
			q.setParameter("ssresu", Ssresu);
			q.setParameter("ssfsel", Ssfsel);
			@SuppressWarnings("unchecked")
			List<Cacphst> list = (List<Cacphst>)q.list();
			
			for( Cacphst o : list ) {
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
