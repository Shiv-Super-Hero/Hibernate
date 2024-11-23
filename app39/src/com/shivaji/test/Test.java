package com.shivaji.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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
			sessionFactory  = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			
			//Query query = session.createQuery("select e.eno, e.ename, e.esal, e.eaddr from Employee AS e where e.esal<5000 order by e.eno asc");
			/*
			Query query = session.createQuery("select e.eno,e.ename,e.esal,e.eaddr From Employee AS e where e.esal > :min_Val and e.esal < ?");
			query.setFloat("min_Val", 2300.0f);
			query.setParameter(0, 5000.0f);
			*/
			
			Query query = session.createQuery("select e1.eno,e1.ename,e1.esal,e1.eaddr from Employee e1 where e1.esal<(select e2.esal from Employee e2 where e2.eaddr = 'Chennai')");
			
			List<Object[]> list = query.list();
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("------------------------------");
			for(Object[] obj : list) {
				for(Object ob : obj) {
					System.out.print(ob+"\t");
				}
				System.out.println();
			}
			
			//Query query = session.createQuery("select sum(e.esal) from Employee as e group by e.eaddr");
			//Query query = session.createQuery("select sum(e.esal) from Employee as e group by e.eaddr having e.eaddr = 'Pune'");
			//Query query = session.createQuery("select avg(e.esal) from Employee as e");
			/*
			Query query = session.createQuery("select e.esal from Employee as e where e.esal < 3500 AND e.esal>2500");
			
			List<Float> list = query.list();
			for(Float l: list) {
				System.out.println(l);
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
