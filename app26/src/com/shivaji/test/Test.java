package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Customer;
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
			emp.setPname("AAA");
			emp.setPaddr("Pune");
			emp.setEid("E-111");
			emp.setEsal(5000);
			
			Customer cust = new Customer();
			cust.setPname("BBB");
			cust.setPaddr("Hyd");
			cust.setCid("C-111");
			cust.setCmobile("+91-9988776655");
			
			String pk_Val1 = (String)session.save(emp);
			String pk_Val2 = (String)session.save(cust);
			
			if(pk_Val1.equals("AAA") && pk_Val2.equals("BBB")) {
				tx.commit();
				System.out.println("Employee "+pk_Val1+" Inserted Successfully");
				System.out.println("Customer "+pk_Val2+" Inserted Successfully...");
			}else {
				tx.rollback();
				System.out.println("Employee Insertion Failure");
				System.out.println("Customer Insertion Failure");
			}
			
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Employee Insertion Failure");
			System.out.println("Customer Insertion Failure");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
