package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.shivaji.pojo.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			Configuration cfg = new Configuration();
			cfg.setProperty("hibernate.connection.driver_Class","oracle.jdbc.OracleDriver");
			cfg.setProperty("hibernate.connection.url","jdbc:oracle:thin:@localhost:1521:xe");
			cfg.setProperty("hibernate.connection.username","system");
			cfg.setProperty("hibernate.connection.password", "durga");
			cfg.setProperty("hibernate.show_sql", "true");
			cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
			
			
			//Among both the statements use either of them to perform the database  operation 
			// the only thing that is different is Employee.hbm.xml presence or absence.
			
			//cfg.addResource("com/shivaji/resources/Employee.hbm.xml");
			cfg.addAnnotatedClass(Employee.class);
			
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			
			tx = session.beginTransaction();
			
			Employee emp = new Employee();
			emp.setEno(444);
			emp.setEname("DDD");
			emp.setEsal(4500);
			emp.setEaddr("Pune");
			int pk_Val = (Integer)session.save(emp);
			tx.commit();
			
			if(pk_Val == 333) {
				System.out.println("Employee Inserted Successfully");
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}

}
