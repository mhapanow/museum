package com.ciessa.museum.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.User;
import com.ciessa.museum.tools.HibernateUtil;

public class UserDAO extends GenericDAOImpl<User> implements GenericDAO<User>{

	public UserDAO() {
		super(User.class);
	}
	
	public User getByAuthToken(String authToken) throws ASException {
		
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User WHERE authToken = :authToken");
			q.setParameter("authToken", authToken);
			User o = (User)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(authToken);
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

	public User getByEmail(String email) throws ASException {
		
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User WHERE email = :email");
			q.setParameter("email", email);
			User o = (User)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(email);
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

	public User getByLogin(String login) throws ASException {
		
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User WHERE login = :login");
			q.setParameter("login", login);
			User o = (User)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(login);
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
