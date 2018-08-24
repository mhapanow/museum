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
import com.ciessa.museum.model.legacy.Ccrppro;


public class CcrpproDAO {

	public Ccrppro getUsingCrcapiAndCrlineAndCrtrtaAndCrtrinAndCrcoajAndCrctaiAndCrcina(DataSet ds, String crcapi, String crline, String crtrta, String crtrin, String crcoaj, String crctai, String crcina) throws ASException	{
		
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
			Query q = session.createQuery(" FROM Ccrppro Where orbanc=1 AND orcapi = :crcapi AND orline = :crline AND ortrta = :crtrta AND ortrin = :crtrin AND orcoaj = :crcoaj AND orctai = :crctai AND orcina = :crcina ");
			q.setParameter("crcapi", crcapi);
			q.setParameter("crline", crline);
			q.setParameter("crtrta", crtrta);
			q.setParameter("crtrin", crtrin);
			q.setParameter("crcoaj", crcoaj);
			q.setParameter("crctai", crctai);
			q.setParameter("crcina", crcina);
			
			Ccrppro o = (Ccrppro)q.uniqueResult();
			
			if( o != null ) {
				//tx.rollback();
				//throw ASExceptionHelper.notFoundException();
				session.evict(o);
				tx.commit();
			}

			return o;
			
				
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw ASExceptionHelper.defaultException(e.getMessage(), e);
		} finally {
			session.close();
			}
		} // fin public
	
	
	
} //fin public class