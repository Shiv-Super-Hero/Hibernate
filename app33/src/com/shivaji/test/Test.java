package com.shivaji.test;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Branch;
import com.shivaji.entity.Student;

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
			
			Student std1 = (Student)session.get(Student.class, "S-111");
			System.out.println("Student Details");
			System.out.println("----------------------");
			System.out.println("Student Id      : "+std1.getSid());
			System.out.println("Student Name    : "+std1.getSname());
			System.out.println("Student Address : "+std1.getSaddr());
			System.out.println("Branch Id       : "+std1.getBranch().getBid());
			System.out.println("Branch Name     : "+std1.getBranch().getBname());
			
			System.out.println();
			
			Student std2 = (Student)session.get(Student.class, "S-222");
			System.out.println("Student Details");
			System.out.println("----------------------");
			System.out.println("Student Id      : "+std2.getSid());
			System.out.println("Student Name    : "+std2.getSname());
			System.out.println("Student Address : "+std2.getSaddr());
			System.out.println("Branch Id       : "+std2.getBranch().getBid());
			System.out.println("Branch Name     : "+std2.getBranch().getBname());
			
			System.out.println();
			
			Student std3 = (Student)session.get(Student.class, "S-333");
			System.out.println("Student Details");
			System.out.println("----------------------");
			System.out.println("Student Id      : "+std3.getSid());
			System.out.println("Student Name    : "+std3.getSname());
			System.out.println("Student Address : "+std3.getSaddr());
			System.out.println("Branch Id       : "+std3.getBranch().getBid());
			System.out.println("Branch Name     : "+std3.getBranch().getBname());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
