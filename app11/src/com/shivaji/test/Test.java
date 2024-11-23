package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.shivaji.pojo.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			Configuration cfg = new AnnotationConfiguration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Employee emp = new Employee();
			emp.setEno(222);
			emp.setEname("BBB");
			emp.setEsal(3500);
			emp.setEaddr("Pune");
			int pk_Val = (Integer) session.save(emp);
			tx.commit();
			
			if(pk_Val == 555) {
				System.out.println("Employee Inserted Successfully");
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
