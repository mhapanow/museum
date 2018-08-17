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
import com.ciessa.museum.model.legacy.Ccrpcap;


public class CcrpcapDAO {

	public List<Ccrpcap> getUsingCrnucrAndScncuoToList(DataSet ds, String crnucr, int scncuo ) throws ASException	{
		
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
			sb.append(" from Ccrpcap Where cabanc = 1 and canucr = :crnucr and cancuo = :scncuo");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("crnucr", crnucr);
			q.setParameter("scncuo", scncuo);
			
			@SuppressWarnings("unchecked")
			List<Ccrpcap> list = (List<Ccrpcap>)q.list();
			
			for( Ccrpcap o : list ) {
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
	
	
	
} //fin public class