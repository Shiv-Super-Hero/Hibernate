package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Account;
import com.shivaji.entity.EmployeeAccount;
import com.shivaji.entity.StudentAccount;

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
			
			StudentAccount sa = (StudentAccount) session.get(Account.class, "a111");
			System.out.println("Student Details");
			System.out.println("--------------------");
			System.out.println("Account Number  : "+sa.getAccNo());
			System.out.println("Account Name    : "+sa.getAccName());
			System.out.println("Account Type    : "+sa.getAccType());
			System.out.println("Student Id      : "+sa.getSid());
			System.out.println("Student Branch  : "+sa.getSbranch());
			System.out.println("Student Marks   : "+sa.getSmarks());
			
			EmployeeAccount ea = (EmployeeAccount) session.get(Account.class, "a222");
			System.out.println("Employee Account Details");
			System.out.println("-----------------------------");
			System.out.println("Account Number  : "+ea.getAccNo());
			System.out.println("Account Name    : "+ea.getAccName());
			System.out.println("Account Type    : "+ea.getAccType());
			System.out.println("Employee Id     : "+ea.getEid());
			System.out.println("Emloyee Salary  : "+ea.getEsal());
			System.out.println("Employee Address: "+ea.getEaddr());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
