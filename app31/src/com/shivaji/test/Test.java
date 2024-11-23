package com.shivaji.test;

import java.util.HashSet; 
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Department;
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
			
			Department dept = (Department)session.get(Department.class, "D-111");
			System.out.println("Department Details");
			System.out.println("-------------------------");
			System.out.println("Departmnet Id    : "+dept.getDid());
			System.out.println("Department Name  : "+dept.getDname());
			Set<Employee> emps = dept.getEmps();
			
			System.out.println();
			
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("----------------------------");
			for(Employee e : emps) {
				System.out.print(e.getEno()+"\t");
				System.out.print(e.getEname()+"\t");
				System.out.print(e.getEsal()+"\t");
				System.out.print(e.getEaddr()+"\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
