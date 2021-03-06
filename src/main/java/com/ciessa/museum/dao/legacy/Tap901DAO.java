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
import com.ciessa.museum.model.legacy.Tap901;

public class Tap901DAO {
public List<Tap901> getUsingListDmbkAndDmtypAndDmacct(DataSet ds, String dmbk,String dmtyp,String dmacct) throws ASException	{
		
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
			sb.append(" FROM Tap901 WHERE ssbk = :dmbk AND sstyp = :dmtyp AND ssacct = :dmacct ");
			
			Query q = session.createQuery(sb.toString());
			q.setParameter("dmbk", dmbk);
			q.setParameter("dmtyp", dmtyp);
			q.setParameter("dmacct", dmacct);
			
			@SuppressWarnings("unchecked")
			List<Tap901> list = (List<Tap901>)q.list();
			
			for( Tap901 o : list ) {
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

}
