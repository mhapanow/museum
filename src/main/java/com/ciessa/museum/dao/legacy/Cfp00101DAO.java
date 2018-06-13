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
import com.ciessa.museum.model.legacy.Cfp00101;
import com.ciessa.museum.tools.Range;

public class Cfp00101DAO {

	public List<Cfp00101> getUsingKeyAndRange(DataSet ds, Range range, String order,
			Map<String, String> attributes) throws ASException {

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
			sb.append("FROM Cfp00101 WHERE cfbco = '001' AND cfreg = '220'");
			if( StringUtils.hasText(order)) {
				sb.append(" ORDER BY " + order);
			} else {
				sb.append(" ORDER BY cfctr");
			}
			Query q = session.createQuery(sb.toString());
			
			if( range != null ) {
				q.setFirstResult(range.getFrom());
				q.setMaxResults(range.getTo() - range.getFrom());
			}
			@SuppressWarnings("unchecked")
			List<Cfp00101> list = (List<Cfp00101>)q.list();
			
			for( Cfp00101 o : list ) {
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

	//TODO: nomenclatuda de los metodos 'getUsingWscodiAndWsacct'
	public Cfp00101 getUsingWscodi(DataSet ds, String wscodi) throws ASException {
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
			Query q = session.createQuery("FROM Cfp00101 WHERE cfbco = '001' AND cfreg = '220' AND cfctr = :wscodi");
			q.setParameter("wscodi", wscodi);
			Cfp00101 o = (Cfp00101)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wscodi);
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
