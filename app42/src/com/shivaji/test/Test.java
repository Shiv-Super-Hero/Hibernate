package com.shivaji.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Employee;

public class Test {

	public static void main(String[] args) {
		StandardServiceRegistryBuilder builder = null;
		StandardServiceRegistry registry = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			
//			SQLQuery sqlQuery = session.createSQLQuery("select eno,ename,esal,eaddr from emp1 where esal > :min and esal < :max");
//			sqlQuery.setParameter("min", 1500);
//			sqlQuery.setParameter("max", 5500);
			
			Query sqlQuery = session.getNamedQuery("sql_Query");
			sqlQuery.setFloat(0, 1500);
			sqlQuery.setFloat("max", 5500);
			
			List<Object[]> list = sqlQuery.list();
			System.out.println("ENO\tENAME\tESAL\tESAL");
			for(Object[] obj:list) {
				for(Object ob:obj) {
					System.out.print(ob+"\t");
				}
				System.out.println();
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

}
