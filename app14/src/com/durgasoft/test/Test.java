package com.durgasoft.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.shivaji.pojo.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			
			Employee emp = (Employee) session.get(Employee.class, 111);
			if(emp == null) {
				System.out.println("Employee 111 does not exist !!!");
			}
			else {
				System.out.println("Employee Details");
				System.out.println("---------------------------");
				System.out.println("Employee Number  : "+emp.getEno());
				System.out.println("Employee Name  : "+emp.getEname());
				System.out.println("Employee Salary  : "+emp.getEsal());
				System.out.println("Employee Address  : "+emp.getEaddr());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
