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
import com.ciessa.museum.model.legacy.Ccrpcpv;


public class CcrpcpvDAO {

	public Ccrpcpv getUsingnNpresAndNcuot(DataSet ds, String npres, String ncuot) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Ccrpcpv Where cpnucr = :npres and cpcuat = :ncuot ");
			q.setParameter("npres", npres);
			q.setParameter("ncuot", ncuot);
			
			Ccrpcpv o = (Ccrpcpv)q.uniqueResult();
			
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
	

	public List<Ccrpcpv> getUsingnNpresAndNcuotToList(DataSet ds, String npres, String ncuot) throws ASException {

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
			sb.append(" FROM Ccrpcpv Where cpnucr = :npres and cpcuat = :ncuot ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("npres", npres);
			q.setParameter("ncuot", ncuot);
			
			@SuppressWarnings("unchecked")
			List<Ccrpcpv> list = (List<Ccrpcpv>)q.list();
			
			for( Ccrpcpv o : list ) {
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
	}// fin public
	
	
	
	
} //fin public class