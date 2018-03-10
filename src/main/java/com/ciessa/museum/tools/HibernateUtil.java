package com.ciessa.museum.tools;

import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.util.StringUtils;

import com.ciessa.museum.dao.DataSetDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.model.DataSet;

public class HibernateUtil {

	public static final String SECURITY_FACTORY = "museum_security";

	private static HibernateUtil instance;

	private Map<String, SessionFactory> sessionFactories;

	public HibernateUtil() {
		super();

		sessionFactories = CollectionFactory.createMap();
	}

	public static HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
		}

		return instance;
	}

	public SessionFactory getSessionFactory() throws ASException {
		return getSessionFactory(SECURITY_FACTORY);
	}

	public SessionFactory getSessionFactory(String factoryId) throws ASException {

		if (sessionFactories.containsKey(factoryId))
			return sessionFactories.get(factoryId);
		else {
			SessionFactory factory;
			if (factoryId.equals(SECURITY_FACTORY)) {
				factory = new Configuration().configure().buildSessionFactory();
			} else {

				DataSet ds = new DataSetDAO().get(factoryId);

				Configuration config = new Configuration();
				config.configure("hibernate_legacy.cfg.xml");

				if (StringUtils.hasText(ds.getDriverClass()))
					config.setProperty("hibernate.connection.driver_class", ds.getDriverClass());
				if (StringUtils.hasText(ds.getUrl()))
					config.setProperty("hibernate.connection.url", ds.getUrl());
				if (StringUtils.hasText(ds.getUsername()))
					config.setProperty("hibernate.connection.username", ds.getUsername());
				if (StringUtils.hasText(ds.getPassword()))
					config.setProperty("hibernate.connection.password", ds.getPassword());
				if (StringUtils.hasText(ds.getDialect()))
					config.setProperty("hibernate.dialect", ds.getDialect());
				if (StringUtils.hasText(ds.getDefaultCatalog()))
					config.setProperty("hibernate.default_catalog", ds.getDefaultCatalog());
				if (StringUtils.hasText(ds.getDefaultSchema()))
					config.setProperty("hibernate.default_schema", ds.getDefaultSchema());

				factory = config.buildSessionFactory();

			}

			sessionFactories.put(factoryId, factory);
			return factory;
		}

	}

}
