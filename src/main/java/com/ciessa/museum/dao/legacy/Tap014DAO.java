package com.ciessa.museum.dao.legacy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.model.legacy.Tap014;
import com.ciessa.museum.tools.HibernateUtil;

public class Tap014DAO {

	public ArrayList<Tap014> getUsingWcta(DataSet ds, String wcta, String waca, String wpto, String wtod, Date fecjud, Date fecjuh, double wimp) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		try {
			Configuration config = new Configuration();
			config.configure("hibernate_legacy.cfg.xml");
			config.setProperty("hibernate.connection.url", ds.getUrl());
			config.setProperty("hibernate.connection.username", ds.getUsername());
			config.setProperty("hibernate.connection.password", ds.getPassword());
			factory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;
		Calendar cal;

		try {
			tx = session.beginTransaction();
			String queryStr = "FROM TAP014 WHERE DMBK = 1 AND DMTYP = 6 AND DMACCT = :wcta";
			if (waca == "X")
				queryStr = queryStr + " AND DMTODF = 1 ";
			if (wpto == "X")
				queryStr = queryStr + " AND DMTODF = 2 ";
			if (wtod == "X")
				queryStr = queryStr + " AND DMTODF = 9 ";
			if (null != fecjud && null != fecjuh) {
				queryStr = queryStr + " AND (";
				cal = Calendar.getInstance();
				cal.setTime(fecjud);
				queryStr = queryStr + "DOAMNX >= " + cal.get(Calendar.YEAR) + new DecimalFormat("000").format(cal.get(Calendar.DAY_OF_YEAR)) +  " AND ";
				cal.setTime(fecjuh);
				queryStr = queryStr + "DOAMNX <= " + cal.get(Calendar.YEAR) + new DecimalFormat("000").format(cal.get(Calendar.DAY_OF_YEAR));
				queryStr = queryStr +  " ) ";
			}
			if (wimp > 0)
				queryStr = queryStr + " AND DOLIMA >= " + wimp;
			Query q = session.createQuery(queryStr);
			q.setParameter("wcta", wcta);
			ArrayList<Tap014> list = (ArrayList<Tap014>)q.list();
			
			if( list.size() == 0 ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wcta);
			}
			
			session.evict(list);
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
	
	public ArrayList<Tap014> getUsingWbas(DataSet ds, String wbas, String waca, String wpto, String wtod, Date fecjud, Date fecjuh, double wimp) throws ASException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		try {
			Configuration config = new Configuration();
			config.configure("hibernate_legacy.cfg.xml");
			config.setProperty("hibernate.connection.url", ds.getUrl());
			config.setProperty("hibernate.connection.username", ds.getUsername());
			config.setProperty("hibernate.connection.password", ds.getPassword());
			factory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;
		Calendar cal;

		try {
			tx = session.beginTransaction();
			String queryStr = "FROM TAP014 WHERE DMBK = 1 AND DMTYP = 6 AND DMACCT = :wcta";
			if (waca == "X")
				queryStr = queryStr + " AND DMTODF = 1 ";
			if (wpto == "X")
				queryStr = queryStr + " AND DMTODF = 2 ";
			if (wtod == "X")
				queryStr = queryStr + " AND DMTODF = 9 ";
			if (null != fecjud && null != fecjuh) {
				queryStr = queryStr + " AND (";
				cal = Calendar.getInstance();
				cal.setTime(fecjud);
				queryStr = queryStr + "DOAMNX >= " + cal.get(Calendar.YEAR) + new DecimalFormat("000").format(cal.get(Calendar.DAY_OF_YEAR)) +  " AND ";
				cal.setTime(fecjuh);
				queryStr = queryStr + "DOAMNX <= " + cal.get(Calendar.YEAR) + new DecimalFormat("000").format(cal.get(Calendar.DAY_OF_YEAR));
				queryStr = queryStr +  " ) ";
			}
			if (wimp > 0)
				queryStr = queryStr + " AND DOLIMA >= " + wimp;
			Query q = session.createQuery(queryStr);
			q.setParameter("wbas", wbas.substring(2-1, 7-1));
			ArrayList<Tap014> list = (ArrayList<Tap014>)q.list();
			
			if( list.size() == 0 ) {
				tx.rollback();
				throw ASExceptionHelper.notFoundException(wbas);
			}
			
			session.evict(list);
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
	
}
