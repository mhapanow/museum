package com.ciessa.museum.dao.legacy;

import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ciessa.museum.dao.FactoryManager;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Saldom;

public class SaldomDAO {

	public Saldom getUsingWcta(DataSet ds, String wcta) throws ASException {
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
			Query q = session.createQuery("FROM Saldom WHERE cbank = 1 AND ccta = 6 AND ncta = :wcta AND daasal = :daasal AND dmmsal = :dmmsal");
			q.setParameter("wcta", wcta);
			q.setParameter("daasal", Calendar.getInstance().get(Calendar.YEAR));
			q.setParameter("dmmsal", Calendar.getInstance().get(Calendar.MONTH));
			Saldom o = (Saldom)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wcta);
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
