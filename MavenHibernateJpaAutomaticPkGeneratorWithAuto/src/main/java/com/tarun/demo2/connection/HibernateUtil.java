package com.tarun.demo2.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		SessionFactory sessionFactory = null;
		try {
			// create configuration object
			Configuration config = new Configuration();
			
			// initialize the configuration object with the configuration file data
			config.configure("hibernate.cfg.xml");
			
			// get the SessionFactory object from configuration
			sessionFactory = config.buildSessionFactory();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return sessionFactory;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}

//for ask
//
//private static final SessionFactory sessionFactory = buildSessionFactory();
//		
//		private static SessionFactory buildSessionFactory() {
//			try {
//				return new AnnotationConfiguration().configure().buildSessionFactory();
//			}
//			catch(Throwable th){
//			   System.err.println("Enitial SessionFactory creation failed"+th);
//			   throw new ExceptionInInitializerError(th);
//			  }
//			return sessionFactory;
//		}
//		
//		 public static SessionFactory getSessionFactory() {
//		        return sessionFactory;
//		    }
