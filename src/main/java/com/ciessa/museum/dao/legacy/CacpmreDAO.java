package com.ciessa.museum.dao.legacy;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.util.StringUtils;

import com.ciessa.museum.dao.FactoryManager;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Cacpmre;
import com.ciessa.museum.tools.Range;

public class CacpmreDAO {

	public Cacpmre getUsingMrmrec(DataSet ds, String wsmrec) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Cacpmre where mrmrec = :wsmrec ");
			q.setParameter("wsmrec", wsmrec);
			Cacpmre o = (Cacpmre)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				//throw ASExceptionHelper.notFoundException(wsmrec.toString());
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
	
	public List<Cacpmre> getUsing(DataSet ds, String order,
			Map<String, String> attributes, Range range) throws ASException {

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
			sb.append("FROM Cacpmre");
			if( StringUtils.hasText(order)) {
				sb.append(" ORDER BY " + order);
			} else {
				sb.append(" ORDER BY MRDESC");
			}
			Query q = session.createQuery(sb.toString());
			
			if( range != null ) {
				q.setFirstResult(range.getFrom());
				q.setMaxResults(range.getTo() - range.getFrom());
			}
			
			@SuppressWarnings("unchecked")
			List<Cacpmre> list = (List<Cacpmre>)q.list();
			
			for( Cacpmre o : list ) {
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