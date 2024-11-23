package com.shivaji.Test;

import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shivaji.pojo.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		FileInputStream fis = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("xyz.xml");
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			
			Employee emp = (Employee)session.get(Employee.class, 444);
			if(emp == null) {
				System.out.println("Employee Not Existed");
			}else {
				System.out.println("Employee Details");
				System.out.println("------------------------");
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
