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
import com.ciessa.museum.model.legacy.Tap003;

public class Tap003DAO {
	public List<Tap003> getUsingDmacct(DataSet ds, String dmacct) throws ASException {
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
			Query q = session.createQuery("FROM Tap003 WHERE dsacct  = :dmacct and dsstat  != 'D' AND dstype  = '1' ");
			q.setParameter("dmacct", dmacct);
			
			@SuppressWarnings("unchecked")
			List<Tap003> list = (List<Tap003>)q.list();
			
			for( Tap003 o : list ) {
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
	
public List<Tap003> getUsingListAtpctaAndAcuen1(DataSet ds, String atpcta, String acuen1) throws ASException	{
		
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
			sb.append(" FROM Tap003 WHERE dshdsv = :atpcta AND dsacct = :acuen1 AND dstype = '2' AND dsstat != 'D' ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("atpcta", atpcta);
			q.setParameter("acuen1", acuen1);
			
			@SuppressWarnings("unchecked")
			List<Tap003> list = (List<Tap003>)q.list();
			
			for( Tap003 o : list ) {
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
	} // fin public

	public List<Tap003> getUsingListDshbkAndDshdsvAndDsacct(DataSet ds, String dshbk, String dshdsv, String dsacct) throws ASException	{
		
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
			sb.append(" FROM Tap003 WHERE dshbk = :dshbk AND dshdsv = :dshdsv AND dsacct = :dsacct ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("dshbk", dshbk);
			q.setParameter("dshdsv", dshdsv);
			q.setParameter("dsacct", dsacct);
			
			@SuppressWarnings("unchecked")
			List<Tap003> list = (List<Tap003>)q.list();
			
			for( Tap003 o : list ) {
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
	} // fin public
	
	
	public List<Tap003> getUsingListDshbkAndDshdsvAndDsacctAndDstypeAndDsstat(DataSet ds, String dshbk, String dshdsv, String dsacct, String dstype, String dsstat) throws ASException	{
		
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
			sb.append(" FROM Tap003 WHERE dshbk = :dshbk AND dshdsv = :dshdsv AND dsacct = :dsacct AND dstype = :dstype AND dsstat != :dsstat ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("dshbk", dshbk);
			q.setParameter("dshdsv", dshdsv);
			q.setParameter("dsacct", dsacct);
			q.setParameter("dstype", dstype);
			q.setParameter("dsstat", dsstat);
			
			@SuppressWarnings("unchecked")
			List<Tap003> list = (List<Tap003>)q.list();
			
			for( Tap003 o : list ) {
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
	} // fin public



}
