package com.ciessa.museum.dao;

import java.util.HashMap;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.model.DataSet;
import com.ciessa.museum.tools.HibernateUtil;

public class FactoryManager {

	private static FactoryManager instance;
	private HashMap<String, SessionFactory> factories;
	
	public FactoryManager() {
		factories = new HashMap<String, SessionFactory>();
	}
	
	public static FactoryManager getInstance() {
		if( null == instance)
			instance = new FactoryManager();
		return instance;
	}


	public SessionFactory getFactory(DataSet ds ) throws ASException {

		SessionFactory factory = factories.get(ds.getIdentifier());
		if( null == factory ) {
			factory = HibernateUtil.getInstance().getSessionFactory();
			Configuration config = new Configuration();
			config.configure("hibernate_legacy.cfg.xml");
			config.setProperty("hibernate.connection.url", ds.getUrl());
			config.setProperty("hibernate.connection.username", ds.getUsername());
			config.setProperty("hibernate.connection.password", ds.getPassword());
			factory = config.buildSessionFactory();
			factories.put(ds.getIdentifier(), factory);
		}
		return factory;
	}

}
