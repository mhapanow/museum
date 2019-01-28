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
import com.ciessa.museum.model.legacy.Tap03w;

public class Tap03wDAO {
	public List<Tap03w> getUsingListWshbkAndWshdsvAndWsacct(DataSet ds, String wshbk, String wshdsv, String wsacct) throws ASException	{
		
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
			sb.append(" FROM Tap03w WHERE wsstat = 'D' AND wshbk = :wshbk AND wshdsv = :wshdsv AND wsacct = :wsacct ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("wshbk", wshbk);
			q.setParameter("wshdsv", wshdsv);
			q.setParameter("wsacct", wsacct);
			
			@SuppressWarnings("unchecked")
			List<Tap03w> list = (List<Tap03w>)q.list();
			
			for( Tap03w o : list ) {
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
	
	public Tap03w getUsing(DataSet ds) throws ASException {
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
			Query q = session.createQuery("FROM Tap03w WHERE WHERE wsstat = 'D' ");
			//q.setParameter("", );
			Tap03w o = (Tap03w)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
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
}
