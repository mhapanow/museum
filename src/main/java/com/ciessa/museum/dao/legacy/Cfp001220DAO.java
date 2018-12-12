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
import com.ciessa.museum.model.legacy.Cfp001220;
import com.ciessa.museum.tools.Range;

public class Cfp001220DAO {

	public List<Cfp001220> getUsingKeyAndRange(DataSet ds, Range range, String order,
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
			sb.append("FROM Cfp001220 WHERE cfbco = '001' AND cfreg = '220'");
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
			List<Cfp001220> list = (List<Cfp001220>)q.list();
			
			for( Cfp001220 o : list ) {
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
	public Cfp001220 getUsingWscodi(DataSet ds, String wscodi) throws ASException {
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
			Query q = session.createQuery("FROM Cfp001220 WHERE cfbco = '001' AND cfreg = '220' AND cfctr = :wscodi");
			q.setParameter("wscodi", wscodi);
			Cfp001220 o = (Cfp001220)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				//throw ASExceptionHelper.notFoundException(wscodi);
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
	
	public Cfp001220 getUsingTipoAndDmtype(DataSet ds, String tipo, Integer dmtype) throws ASException {
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
			
			Query q = session.createQuery("FROM Cfp001220 where PKID ='0576d2af-7356-4a92-bb4c-76a98075e68f'");
			//TODO: Se nos pide implementar:  Acceder al archivo CFP001 con clave KEY = ‘001210’ + TIPO + DMTYPE + Blancos
			//TODO: El Valor Key no se ha definido en la doc.
			q.setParameter("tipo", tipo);
			q.setParameter("dmtype", dmtype);
			Cfp001220 o = (Cfp001220)q.uniqueResult();
			
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
	}
	
}
