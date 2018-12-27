package com.ciessa.museum.dao.legacy;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ciessa.museum.dao.FactoryManager;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Tap002w;

public class Tap002wDAO {

	public Tap002w getUsingWcta(DataSet ds, String wcta) throws ASException {
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
			Query q = session.createQuery("FROM Tap002w where dmbk = 1 and dmtyp = 6 AND dmacct = :wcta");
			q.setParameter("wcta", wcta);
			Tap002w o = (Tap002w)q.uniqueResult();
			
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
	
    public Tap002w getUsingLdbankAndAmtypAndAncta(DataSet ds, String ldbank, String amtyp, String ancta) throws ASException {
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
            Query q = session.createQuery("FROM Tap002w where dmbk = :ldbank and dmtyp = :amtyp AND dmacct = :ancta");
            q.setParameter("ldbank", ldbank);
            q.setParameter("amtyp", amtyp);
            q.setParameter("ancta", ancta);
            Tap002w o = (Tap002w)q.uniqueResult();
            
            if( o == null ) {
                tx.rollback();
                //throw ASExceptionHelper.notFoundException();
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
	
	public Tap002w getUsingWbas(DataSet ds, String wbas) throws ASException {
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
			Query q = session.createQuery("FROM Tap002w WHERE dmbk = 1 AND dmtyp = 6 AND dmacct >= :wbas1 AND dmacct <= :wbas2");
			q.setParameter("wbas1", Long.valueOf(wbas + "00"));
			q.setParameter("wbas2", Long.valueOf(wbas + "99"));
			Tap002w o = (Tap002w)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wbas);
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
	
	public Tap002w getUsingTipoAndCuenta(DataSet ds, String tipo, String cuenta) throws ASException {
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
			Query q = session.createQuery("FROM Tap002w where dmbk = 1 and dmtyp = :tipo AND dmacct = :cuenta");
			q.setParameter("cuenta", cuenta);
			q.setParameter("tipo", tipo);
			Tap002w o = (Tap002w)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(cuenta);
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
	
	public Tap002w getUsingAcctan(DataSet ds, String acctan) throws ASException {
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
			Query q = session.createQuery(" FROM Tap002w WHERE Dmacct = :acctan");
			q.setParameter("acctan", acctan);
			Tap002w o = (Tap002w)q.uniqueResult();
			
			if( o == null ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(acctan);
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
