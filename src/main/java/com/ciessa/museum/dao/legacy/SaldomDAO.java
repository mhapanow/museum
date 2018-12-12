package com.ciessa.museum.dao.legacy;

import java.util.Calendar;
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
import com.ciessa.museum.model.legacy.Saldom;

public class SaldomDAO {

	public Saldom getUsingWcta(DataSet ds, String wcta) throws ASException {
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
			Query q = session.createQuery("FROM Saldom WHERE cbank = 1 AND ccta = 6 AND ncta = :wcta AND daasal = :daasal AND dmmsal = :dmmsal");
			q.setParameter("wcta", wcta);
			q.setParameter("daasal", Calendar.getInstance().get(Calendar.YEAR));
			q.setParameter("dmmsal", Calendar.getInstance().get(Calendar.MONTH));
			Saldom o = (Saldom)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wcta);
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
	
	
	
	
	public Saldom getUsingTipoAndCuenta(DataSet ds, String tipo, String cuenta) throws ASException {
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
			Query q = session.createQuery("FROM Saldom WHERE ncta = :cuenta ");
			q.setParameter("cuenta", cuenta);
			List<Saldom> list = (List<Saldom>)q.list();
			Saldom o = null;
			for( Saldom item : list ) {
				if (item.getMember().equals("SALDOACT")) {
						o = item;
				}
				session.evict(item);
			}
			if( o == null ) {
				tx.rollback();
				//throw ASExceptionHelper.notFoundException(cuenta);
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
	
	
	public Saldom getUsingDmsf4AndDmbrchAndDmtypAndDmacct(DataSet ds, Integer dmsf4, Integer dmbrch, Integer dmtyp, Long dmacct) throws ASException {
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
			Query q = session.createQuery("FROM Saldom where cdivi = :dmsf4 AND cbranc = :dmbrch AND ccta = :dmtyp  AND ncta = :dmacct");
			q.setParameter("dmsf4", dmsf4);
			q.setParameter("dmbrch", dmbrch);
			q.setParameter("dmtyp", dmtyp);
			q.setParameter("dmacct", dmacct);
			
			Saldom o = (Saldom)q.uniqueResult();
			
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
