package com.ciessa.museum.dao.legacy;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ciessa.museum.dao.FactoryManager;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Cfp001210;

public class Cfp001210DAO {

	public Cfp001210 getUsingKey(DataSet ds, String Tipo, String Dmtype) throws ASException {
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
			Query q = session.createQuery("FROM Cfp001210 WHERE cfbk = '001' AND cfrec = '210' AND cfadsg = :tipo AND cftnbr = :dmtype");
			q.setParameter("tipo", Tipo);
			q.setParameter("dmtype", Dmtype);
			Cfp001210 o = (Cfp001210)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(Tipo);
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
