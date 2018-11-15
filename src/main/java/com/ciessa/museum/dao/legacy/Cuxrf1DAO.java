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
import com.ciessa.museum.model.legacy.Cuxrf1;

public class Cuxrf1DAO {

	public List<Cuxrf1> getUsingCux1ac(DataSet ds, String tipo, String cuenta) throws ASException {

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
			sb.append("FROM Cuxrf1 WHERE cuxbk = 1 AND cux1ac = :cux1ac ORDER BY cux1cs");
			Query q = session.createQuery(sb.toString());
			q.setParameter("cux1ac", "0" + tipo + cuenta);
			@SuppressWarnings("unchecked")
			List<Cuxrf1> list = (List<Cuxrf1>)q.list();
			for( Cuxrf1 o : list ) {
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
	
	public Cuxrf1 getUsigSwx2bkAndSwx2rtAndSwx2apAndSwx2acAndSwx2tyAndSwx2cs(DataSet ds, String swx2bk, String swx2rt, String swx2ap, String swx2ac, String swx2ty, String swx2cs ) throws ASException {
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
					Query q = session.createQuery(" FROM Cuxrf1 WHERE cuxbk = :swx2bk And cuxrec = :swx2rt And cux1ap = :swx2ap And cux1ac = :swx2ac And cux1ty = :swx2ty And cux1cs = :swx2cs ");
					q.setParameter("swx2bk", swx2bk);
					q.setParameter("swx2rt", swx2rt);
					q.setParameter("swx2ap", swx2ap);
					q.setParameter("swx2ac", swx2ac);
					q.setParameter("swx2ty", swx2ty);
					q.setParameter("swx2cs", swx2cs);
					
					Cuxrf1 o = (Cuxrf1)q.uniqueResult();
					
					if( o != null ) {
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
			}
	
}
