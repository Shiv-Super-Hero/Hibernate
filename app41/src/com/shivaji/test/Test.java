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
			
//			This is the Programmatic Approach
			
//			SQLQuery sqlQuery = session.createSQLQuery("select * from emp1 where ESAL > ? and ESAL < :max");
//			sqlQuery.setFloat(0, 1500);
//			sqlQuery.setFloat("max", 4000);
			
			// This is named approach through which we perform the sql operations via hibernate-mapping file
			
			Query sqlQuery = session.getNamedQuery("sql_Query");
			
			//sqlQuery.addEntity(Employee.class);
			List<Employee> empList = sqlQuery.list();
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("------------------------------");
			for(Employee emp : empList) {
				System.out.print(emp.getEno()+"\t");
				System.out.print(emp.getEname()+"\t");
				System.out.print(emp.getEsal()+"\t");
				System.out.print(emp.getEaddr()+"\n");
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
