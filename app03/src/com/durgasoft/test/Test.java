package com.durgasoft.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.durgasoft.pojo.Employee;

public class Test {

	public static void main(String[] args) {
		Configuration config = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			config = new Configuration();
			config.configure("myconfig.xml");
			sessionFactory = config.buildSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Employee emp = new Employee();
			emp.setEno(777);
			session.delete(emp);
			tx.commit();
			System.out.println("Employee Deleted Successfully");
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Employee Deletion Failure");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
