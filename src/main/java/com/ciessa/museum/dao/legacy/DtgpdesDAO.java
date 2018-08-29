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
import com.ciessa.museum.model.legacy.Dtgpdes;

public class DtgpdesDAO {
	
	
	public Dtgpdes getUsingCtstco(DataSet ds, String ctstco) throws ASException {

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
			Query q = session.createQuery(" from Dtgpdes where DSCOCA ='038' and DSCOAC = :ctstco ");
			q.setParameter("ctstco", ctstco);
			Dtgpdes o = (Dtgpdes)q.uniqueResult();
			
			if( o == null ) {
				//tx.rollback();
				//throw ASExceptionHelper.notFoundException(ctstco);
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
	
	
	public Dtgpdes getUsingTxmhab(DataSet ds, String txmhab) throws ASException {

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
			Query q = session.createQuery(" from Dtgpdes Where dscoca=810 And dscoac = :txmhab ");
			q.setParameter("txmhab", txmhab);
			Dtgpdes o = (Dtgpdes)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(txmhab);
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

	public List<Dtgpdes> getUsingDscoca(DataSet ds, String dscoca) throws ASException {

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
			Query q = session.createQuery(" from Dtgpdes where DSCOCA = :dscoca ");
			q.setParameter("dscoca", dscoca);
			
			@SuppressWarnings("unchecked")
			List<Dtgpdes> list = (List<Dtgpdes>)q.list();
			
			for(Dtgpdes o : list) {
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
