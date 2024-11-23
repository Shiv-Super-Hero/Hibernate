package com.shivaji.test;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Employee;

public class Test {

	public static void main(String[] args) {
		StandardServiceRegistry registry = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder.applySettings(cfg.getProperties());
			registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			
			Query query = session.createQuery("from Employee");
			Filter filter = session.enableFilter("empFilter");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Employee Types : ");
			System.out.print("1. Perm    2. Perm");
			System.out.println("    3. Both ");
			System.out.print("Your Option [1/2/3]  : ");
			int option = scanner.nextInt();
			
			if(option == 1) {
				filter.setParameter("type", "Perm");
			}
			else if(option == 2) {
				filter.setParameter("type", "Temp");
			}
			else {
				session.disableFilter("empFilter");
			}
			List<Employee> empList = query.list();
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("-----------------------------");
			for(Employee emp: empList) {
				System.out.print(emp.getEno()+"\t");
				System.out.print(emp.getEname()+"\t");
				System.out.print(emp.getEsal()+"\t");
				System.out.print(emp.getEaddr()+"\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}

	}

}
