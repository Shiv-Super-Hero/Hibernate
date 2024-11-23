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
			config.configure();
			sessionFactory = config.buildSessionFactory();
			session = sessionFactory.openSession();
			tx = session.getTransaction();
			tx.begin();
			Employee emp = new Employee();
			emp.setEno(777);
			emp.setEname("GGG");
			emp.setEsal(12000);
			emp.setEaddr("Narmada");
			//session.update(emp);
			session.saveOrUpdate(emp);
			tx.commit();
			System.out.println("Employee Inserted/Updated Successsfully");
	
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Employee Insertion/Updation Failure");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
