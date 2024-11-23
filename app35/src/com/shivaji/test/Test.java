package com.shivaji.test;

import java.util.HashSet; 
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Course;
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
			
			/*
			tx = session.beginTransaction();
			
			Course c1 = new Course();
			c1.setCid("C-111");
			c1.setCname("C");
			c1.setCcost(1000);
			
			Course c2 = new Course();
			c2.setCid("C-222");
			c2.setCname("C++");
			c2.setCcost(1100);
			
			Course c3 = new Course();
			c3.setCid("C-333");
			c3.setCname("JAVA");
			c3.setCcost(1200);
			
			Set<Course> courses = new HashSet<>();
			courses.add(c1);
			courses.add(c2);
			courses.add(c3);
			
			Student std1 = new Student();
			std1.setSid("S-111");
			std1.setSname("AAA");
			std1.setSaddr("Hyd");
			std1.setCourses(courses);
			
			Student std2 = new Student();
			std2.setSid("S-222");
			std2.setSname("BBB");
			std2.setSaddr("Pune");
			std2.setCourses(courses);
			
			Student std3 = new Student();
			std3.setSid("S-333");
			std3.setSname("CCC");
			std3.setSaddr("Goa");
			std3.setCourses(courses);
			
			session.save(std1);
			session.save(std2);
			session.save(std3);
			tx.commit();
			System.out.println("Student Inserted Successfully");
			
			*/
			
			Student std1 = (Student) session.get(Student.class, "S-111");
			System.out.println("Student Details");
			System.out.println("---------------------");
			System.out.println("Student Id      : "+std1.getSid());
			System.out.println("Student Name    : "+std1.getSname());
			System.out.println("Student Address : "+std1.getSaddr());
			
			System.out.println();
			
			Set<Course> courses1 = std1.getCourses();
			System.out.println("CID\tCNAME\tCCOST");
			System.out.println("--------------------");
			for(Course c:courses1) {
				System.out.print(c.getCid()+"\t");
				System.out.print(c.getCname()+"\t");
				System.out.print(c.getCcost()+"\n");
			}
			
			System.out.println();
			
			Student std2 = (Student) session.get(Student.class, "S-222");
			System.out.println("Student Details");
			System.out.println("---------------------");
			System.out.println("Student Id      : "+std2.getSid());
			System.out.println("Student Name    : "+std2.getSname());
			System.out.println("Student Address : "+std2.getSaddr());
			
			System.out.println();
			
			Set<Course> courses2 = std2.getCourses();
			System.out.println("CID\tCNAME\tCCOST");
			System.out.println("--------------------");
			for(Course c:courses2) {
				System.out.print(c.getCid()+"\t");
				System.out.print(c.getCname()+"\t");
				System.out.print(c.getCcost()+"\n");
			}
			System.out.println();
			
			Student std3 = (Student) session.get(Student.class, "S-333");
			System.out.println("Student Details");
			System.out.println("---------------------");
			System.out.println("Student Id      : "+std3.getSid());
			System.out.println("Student Name    : "+std3.getSname());
			System.out.println("Student Address : "+std3.getSaddr());
			
			System.out.println();
			
			Set<Course> courses3 = std3.getCourses();
			System.out.println("CID\tCNAME\tCCOST");
			System.out.println("--------------------");
			for(Course c:courses3) {
				System.out.print(c.getCid()+"\t");
				System.out.print(c.getCname()+"\t");
				System.out.print(c.getCcost()+"\n");
			}
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
