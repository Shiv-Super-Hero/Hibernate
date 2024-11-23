package com.shivaji.test;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Customer;
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
			
			Employee emp = (Employee)session.get(Employee.class, "AAA");
			System.out.println("Employee Details");
			System.out.println("-------------------------");
			System.out.println("Person Name     : "+emp.getPname());
			System.out.println("Person Address  : "+emp.getPaddr());
			System.out.println("Employee Id     : "+emp.getEid());
			System.out.println("Employee Salary : "+emp.getEsal());
			
			System.out.println();
			
			Customer cust = (Customer)session.get(Customer.class, "BBB");
			System.out.println("Customer Details");
			System.out.println("-----------------------");
			System.out.println("Person Name     : "+cust.getPname());
			System.out.println("Person Address  : "+cust.getPaddr());
			System.out.println("Customer Id     : "+cust.getCid());
			System.out.println("Customer Mobile : "+cust.getCmobile());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
