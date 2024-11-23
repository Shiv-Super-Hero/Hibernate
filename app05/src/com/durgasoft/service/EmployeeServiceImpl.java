package com.durgasoft.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.durgasoft.pojo.Employee;
import com.durgasoft.util.HibernateUtil;

public class EmployeeServiceImpl implements EmployeeService {
		Employee emp;
	
//	public EmployeeServiceImpl() {
//		try {
//			config = new Configuration();
//			config.configure("/com/durgasoft/resources/hibernate.cfg.xml");
//			sessionFactory = config.buildSessionFactory();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public Employee search(int eno) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			emp = (Employee) session.get(Employee.class, eno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
}
