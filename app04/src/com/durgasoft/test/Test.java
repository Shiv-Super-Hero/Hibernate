package com.durgasoft.test;

import com.durgasoft.pojo.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class Test {

	public static void main(String[] args) {
		Configuration config = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		
		try {
			config = new Configuration();
			config.configure("/com//durgasoft/resources/myconfig.xml");
			sessionFactory = config.buildSessionFactory();
			session = sessionFactory.openSession();
			
			Employee emp = (Employee)session.get(Employee.class,555);
			if (emp==null) {
				System.out.println("Employee doesn't exist");
			}else {
				System.out.println("Employee Details");
				System.out.println("----------------------------------");
				System.out.println("Employee Number  : "+emp.getEno());
				System.out.println("Employee Name  : "+emp.getEname());
				System.out.println("Employee Salary  : "+emp.getEsal());
				System.out.println("Employee Address  : "+emp.getEaddr());
			}
			
//			System.out.println("Before Calling load() method");
//			Employee emp = (Employee)session.load(Employee.class, 777);
//			System.out.println(emp.getEno());
//			System.out.println("After calling load() method");
//			System.out.println(emp.getEname());
//			System.out.println("Employee Details");
//			System.out.println("----------------------------------");
//			System.out.println("Employee Number  : "+emp.getEno());
//			System.out.println("Employee Name  : "+emp.getEname());
//			System.out.println("Employee Salary  : "+emp.getEsal());
//			System.out.println("Employee Address  : "+emp.getEaddr());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
