package com.shivaji.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Course;
import com.shivaji.entity.Student;

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
			
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Student Insertion Failure");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
