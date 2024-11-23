package com.shivaji.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("insert into Employee2(eno, ename, esal, eaddr)select e.eno, e.ename, e.esal, e.eaddr from Employee1 e");
			int rowCount = query.executeUpdate();
			tx.commit();
			System.out.println(rowCount);
			System.out.println("Employees transferred from Employee1 to Employee2");
			
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Employee Insertion Failure");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
