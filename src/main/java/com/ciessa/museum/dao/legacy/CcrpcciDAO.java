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
import com.ciessa.museum.model.legacy.Ccrpcci;


public class CcrpcciDAO {



public List<Ccrpcci> getUsingnScbancAndScnucrAndScncuoAndScticuToList(DataSet ds, String scbanc , String scnucr, String scncuo, String scticu) throws ASException	{
	
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

		sb.append(" from Ccrpcci Where cibanc = :scbanc and cinucr = :scnucr and cincuo = :scncuo and citicu = :scticu and cistco = A ");
		
		Query q = session.createQuery(sb.toString());
		q.setParameter("scbanc", scbanc);
		q.setParameter("scnucr", scnucr);
		q.setParameter("scncuo", scncuo);
		q.setParameter("scticu", scticu);
	
		
		@SuppressWarnings("unchecked")
		List<Ccrpcci> list = (List<Ccrpcci>)q.list();
		
		for( Ccrpcci o : list ) {
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


}// Fin Class