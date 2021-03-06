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
import com.ciessa.museum.model.legacy.Ccrpsce;

public class CcrpsceDAO {
public List<Ccrpsce> getUsingnAbancAnucrAncuoAndAsbncToList(DataSet ds, String abanc , String anucr, String ancuo, String asbnc) throws ASException	{
		
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

			sb.append(" FROM Ccrpsce Where scbanc = :abanc and scnucr = :anucr and scncuo = :ancuo and scsbnc = :asbnc ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("abanc", abanc);
			q.setParameter("anucr", anucr);
			q.setParameter("ancuo", ancuo);
			q.setParameter("asbnc", asbnc);
			
			@SuppressWarnings("unchecked")
			List<Ccrpsce> list = (List<Ccrpsce>)q.list();
			
			for( Ccrpsce o : list ) {
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


	public List<Ccrpsce> getUsingnAbancAnucrAncuoAsbncAndAcconToList(DataSet ds, String abanc , String anucr, String ancuo, String asbnc, String accon) throws ASException	{
		
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
	
			sb.append(" FROM Ccrpsce Where scbanc = :abanc and scnucr = :anucr and scncuo = :ancuo and scsbnc = :asbnc and scccon = :accon");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("abanc", abanc);
			q.setParameter("anucr", anucr);
			q.setParameter("ancuo", ancuo);
			q.setParameter("asbnc", asbnc);
			q.setParameter("accon", accon);
			
			@SuppressWarnings("unchecked")
			List<Ccrpsce> list = (List<Ccrpsce>)q.list();
			
			for( Ccrpsce o : list ) {
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
