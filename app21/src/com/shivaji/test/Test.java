package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Account;
import com.shivaji.entity.Address;
import com.shivaji.entity.Employee;

public class Test {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = null;
		Session session = null;
		
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			
			Employee emp = (Employee)session.get(Employee.class, 111);
			System.out.println("Employee Details");
			System.out.println("----------------------");
			System.out.println("Employee Number  : "+emp.getEno());
			System.out.println("Employee Name    : "+emp.getEname());
			System.out.println("Employee Salary  : "+emp.getEsal());
			
			System.out.println();
			
			Account acc = emp.getAcc();
			System.out.println("Account Details");
			System.out.println("----------------------");
			System.out.println("Account Number  : "+acc.getAccNo());
			System.out.println("Account Name    : "+acc.getAccName());
			System.out.println("Account Type    : "+acc.getAccType());
			
			System.out.println();
			
			Address addr = emp.getAddr();
			System.out.println("Address Details");
			System.out.println("----------------------");
			System.out.println("Plot Number  : "+addr.getPno());
			System.out.println("Street Name  : "+addr.getStreet());
			System.out.println("City Name    : "+addr.getCity());
			
			System.out.println();
			
		} catch (Exception e) {
			System.out.println("Employee Insertion Failure exception");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
