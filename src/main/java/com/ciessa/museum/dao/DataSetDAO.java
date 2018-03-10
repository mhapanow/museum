package com.ciessa.museum.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.tools.HibernateUtil;
import com.ciessa.museum.tools.Range;

public class DataSetDAO implements GenericDAO<DataSet> {

	public DataSet get(String identifier) throws ASException {

		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		if( factory == null )
			throw ASExceptionHelper.defaultException("Couldn't initialize Security Session Factory", new Exception());
		
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM DataSet WHERE key = :key");
			q.setParameter("key", identifier);
			DataSet o = (DataSet)q.uniqueResult();
			
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

	public List<DataSet> getAll() throws ASException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DataSet> getUsingRange(Range range) throws ASException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DataSet> getAllAndOrder(String order) throws ASException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DataSet> getUsingStatusAndRange(List<Integer> status, Range range, String order,
			Map<String, String> aattributes) throws ASException {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(DataSet obj) throws ASException {
		// TODO Auto-generated method stub
		
	}

	public void update(DataSet obj) throws ASException {
		// TODO Auto-generated method stub
		
	}

	public void delete(DataSet obj) throws ASException {
		// TODO Auto-generated method stub
		
	}

	public void delete(String identifier) throws ASException {
		// TODO Auto-generated method stub
		
	}
	
}
