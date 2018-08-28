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
import com.ciessa.museum.model.legacy.Ccrpcar;


public class CcrpcarDAO {

	public Ccrpcar getUsingCrnucrAndScncuo(DataSet ds, String crnucr, int scncuo) throws ASException	{
		
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
			Query q = session.createQuery(" From Ccrpcar Where rvbanc=1 and rvnucr = :crnucr and rvncuo = :scncuo");
			q.setParameter("crnucr", crnucr);
			q.setParameter("scncuo", scncuo);
			Ccrpcar o = (Ccrpcar)q.uniqueResult();
			
			if( o != null ) {
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

		
	} // fin public
	
	public List<Ccrpcar> getUsingnDKynucrAndDkyncuoToList(DataSet ds, String kynucr, String kyncuo) throws ASException {

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
			sb.append(" FROM Ccrpcar Where rvbanc = 1 AND rvnucr = :kynucr and rvncuo = :kyncuo ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("kynucr", kynucr);
			q.setParameter("kyncuo", kyncuo);
			
			@SuppressWarnings("unchecked")
			List<Ccrpcar> list = (List<Ccrpcar>)q.list();
			
			for( Ccrpcar o : list ) {
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
	
} //fin public class