package com.bardouski.hibernate.initializer;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/** SessionFactory configurer. */
public class HibernateUtil {

	private static HibernateUtil instance = null;
	private SessionFactory sessionFactory;

	private void configure() {

		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private HibernateUtil() {
		configure();
	}

	public static HibernateUtil getInstance() {
		return (instance == null) ? instance = new HibernateUtil() : instance;
	}

	public void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
