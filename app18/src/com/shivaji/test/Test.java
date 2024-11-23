package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Employee;

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
			
			Employee emp = new Employee();
			emp.setEno(111);
			emp.setEname("AAA");
			emp.setEsal(2000);
			emp.setEaddr("Hyd");
			int pk_Val = (Integer)session.save(emp);
			
			if(pk_Val == 111) {
				tx.commit();
				System.out.println("Employee 111 inserted Successfully !!!");
			}else {
				tx.rollback();
				System.out.println("Employee 111 insertion failure !!!");
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
