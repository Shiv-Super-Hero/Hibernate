package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.shivaji.pojo.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			System.out.println("Before buildSessionFactory");
			System.in.read();
			sessionFactory = cfg.buildSessionFactory();
			System.in.read();
			System.out.println("After buildSessionFactory");
			session = sessionFactory.openSession();
			
			Employee emp = new Employee();
			emp.setEno(444);
			emp.setEname("DDD");
			emp.setEsal(3000);
			emp.setEaddr("Pune");
			emp.setEemail("ddd@shivaji.com");
			
			tx = session.beginTransaction();
			session.save(emp);
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			try {
				session.close();
				System.out.println("Before SessionFactory.close()");
				System.in.read();
				sessionFactory.close();
				System.in.read();
				System.out.println("After SessionFactory.close()");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
