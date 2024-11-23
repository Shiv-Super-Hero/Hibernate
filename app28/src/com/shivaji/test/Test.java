package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Account;
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
			builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Account acc = new Account();
			acc.setAccNo("abc123");
			acc.setAccName("AAA");
			acc.setAccType("Savings");
			acc.setBalance(10000);
			
			Employee emp = new Employee();
			emp.setEno(111);
			emp.setEname("AAA");
			emp.setEsal(5000);
			emp.setEaddr("Hyd");
			emp.setAcc(acc);
			
			int pk_Val = (Integer)session.save(emp);
			if(pk_Val == 111) {
				System.out.println(pk_Val+" Employee inserted Successfully");
				tx.commit();
			}else {
				tx.rollback();
				System.out.println(pk_Val+" Employee insertion Failure");
			}
			
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
