package com.ciessa.museum.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.util.StringUtils;

import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.ModelKey;
import com.ciessa.museum.tools.HibernateUtil;
import com.ciessa.museum.tools.Range;

public class GenericDAOImpl <T extends ModelKey> implements GenericDAO<T> {

	Class<T> clazz;
	Logger log;

	/**
	 * Generic Constructor
	 * 
	 * @param clazz
	 */
	public GenericDAOImpl(Class<T> clazz) {
		super();
		this.clazz = clazz;
		this.log = Logger.getLogger(clazz.getName());
	}

	/**
	 * Obtains the class affected by this DAO
	 */
	public Class<T> getAffectedClass() {
		return clazz;
	}

	public T get(String identifier) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM " + clazz.getName() + " WHERE key = :key");
			q.setParameter("key", identifier);
			@SuppressWarnings("unchecked")
			T o = (T)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(identifier);
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

	public List<T> getAll() throws ASException {
		return getUsingStatusAndRange(null, null, null, null);
	}

	public List<T> getUsingRange(Range range) throws ASException {
		return getUsingStatusAndRange(null, range, null, null);
	}

	public List<T> getAllAndOrder(String order) throws ASException {
		return getUsingStatusAndRange(null, null, order, null);
	}

	public List<T> getUsingStatusAndRange(List<Integer> status, Range range, String order,
			Map<String, String> aattributes) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			StringBuffer sb = new StringBuffer();
			sb.append("FROM " + clazz.getName());
			if( status != null && status.size() > 0 ) {
				sb.append(" WHERE status = :status");
			}
			if( StringUtils.hasText(order)) {
				sb.append(" ORDER BY " + order);
			}
			Query q = session.createQuery(sb.toString());
			if( status != null && status.size() > 0 ) {
				q.setParameter("status", status.get(0));
			}
			
			if( range != null ) {
				q.setFirstResult(range.getFrom());
				q.setMaxResults(range.getTo() - range.getFrom());
			}
			@SuppressWarnings("unchecked")
			List<T> list = (List<T>)q.list();
			
			for( T o : list ) {
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

	public void create(T obj) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			obj.setCreationDateTime(new Date());
			obj.setLastUpdate(new Date());
			obj.setKey(UUID.randomUUID().toString());
			session.save(obj);
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	public void update(T obj) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	public void delete(T obj) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	public void delete(String identifier) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("DELETE FROM " + clazz.getName() + " WHERE key = :key");
			q.setParameter("key", identifier);
			q.executeUpdate();
			tx.commit();
			
			return;
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}


}
