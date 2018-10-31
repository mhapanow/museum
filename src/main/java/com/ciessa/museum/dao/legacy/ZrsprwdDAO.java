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
import com.ciessa.museum.model.legacy.Zrsprwd;


public class ZrsprwdDAO {

	public Zrsprwd getUsingRwaafcAndRwcifaAndRwagigAndRwlogoAndRwncct(DataSet ds, int rwaafc, String rwcifa, String rwagig, int rwlogo, String rwncct) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Zrsprwd WHERE rwaafc = :rwaafc And rwcifa = :rwcifa And rwagig = :rwagig And rwlogo = :rwlogo And rwncct = :rwncct ");
			q.setParameter("rwaafc", rwaafc);
			q.setParameter("rwcifa", rwcifa);
			q.setParameter("rwagig", rwagig);
			q.setParameter("rwlogo", rwlogo);
			q.setParameter("rwncct", rwncct);
			Zrsprwd o = (Zrsprwd)q.uniqueResult();
			
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
	
	//List New
	public List<Zrsprwd> getUsingListRwaafcAndRwcifaAndRwagigAndRwlogoAndRwncct(DataSet ds, int rwaafc, String rwcifa, String rwagig, int rwlogo, String rwncct) throws ASException	{
		
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
			sb.append(" FROM Zrsprwd WHERE rwaafc = :rwaafc And rwcifa = :rwcifa And rwagig = :rwagig And rwlogo = :rwlogo And rwncct = :rwncct ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("rwaafc", rwaafc);
			q.setParameter("rwcifa", rwcifa);
			q.setParameter("rwagig", rwagig);
			q.setParameter("rwlogo", rwlogo);
			q.setParameter("rwncct", rwncct);
			
			@SuppressWarnings("unchecked")
			List<Zrsprwd> list = (List<Zrsprwd>)q.list();
			
			for( Zrsprwd o : list ) {
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
	
	//Obj New
	public Zrsprwd getUsingRwaafcAndRwcifaAndRwagigAndRwlogoAndRwncctAndW4rrreAndW4orgnAndW4rearAndW4rpre(DataSet ds, int rwaafc, String rwcifa, String rwagig, int rwlogo, String rwncct, String w4rrre, String w4orgn, String w4rear, String w4rpre) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Zrsprwd WHERE rwaafc = :rwaafc And rwcifa = :rwcifa And rwagig = :rwagig And rwlogo = :rwlogo And rwncct = :rwncct And rwrrre = :w4rrre And rworg = :w4orgn And rwrear = :w4rear And rwrpre = :w4rpre ");
			q.setParameter("rwaafc", rwaafc);
			q.setParameter("rwcifa", rwcifa);
			q.setParameter("rwagig", rwagig);
			q.setParameter("rwlogo", rwlogo);
			q.setParameter("rwncct", rwncct);
			q.setParameter("w4rrre", w4rrre);
			q.setParameter("w4orgn", w4orgn);
			q.setParameter("w4rear", w4rear);
			q.setParameter("w4rpre", w4rpre);
			Zrsprwd o = (Zrsprwd)q.uniqueResult();
			
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
	
} //fin public class
