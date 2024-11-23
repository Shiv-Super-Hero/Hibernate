package com.shivaji.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Department;
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
			
			Employee e1 = new Employee();
			e1.setEno(111);
			e1.setEname("AAA");
			e1.setEsal(2000);
			e1.setEaddr("Hyd");
			
			Employee e2 = new Employee();
			e2.setEno(222);
			e2.setEname("BBB");
			e2.setEsal(3000);
			e2.setEaddr("Goa");
			
			Employee e3  = new Employee();
			e3.setEno(333);
			e3.setEname("CCC");
			e3.setEsal(5000);
			e3.setEaddr("Pune");
			
			Set<Employee> emps = new HashSet<>();
			emps.add(e1);
			emps.add(e2);
			emps.add(e3);
			
			Department dept = new Department();
			dept.setDid("D-111");
			dept.setDname("Admin");
			dept.setEmps(emps);
			
			String pk_Val = (String)session.save(dept);
			if(pk_Val.equals("D-111")) {
				tx.commit();
				System.out.println("Department "+pk_Val+" Inserted Successfully");
			}else {
				tx.rollback();
				System.out.println("Department "+pk_Val+" Insertion Failure");
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
