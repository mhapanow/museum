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
import com.ciessa.museum.model.legacy.Cuxrf1;

public class Cuxrf1DAO {

	public List<Cuxrf1> getUsingCux1ac(DataSet ds, String tipo, String cuenta) throws ASException {

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
			sb.append("FROM Cuxrf1 WHERE cuxbk = 1 AND cux1ac = :cux1ac");
			Query q = session.createQuery(sb.toString());
			q.setParameter("cux1ac", "0" + tipo + cuenta);
			@SuppressWarnings("unchecked")
			List<Cuxrf1> list = (List<Cuxrf1>)q.list();
			for( Cuxrf1 o : list ) {
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
