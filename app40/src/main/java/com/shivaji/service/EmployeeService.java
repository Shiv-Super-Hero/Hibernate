package com.shivaji.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shivaji.entity.Employee;
import com.shivaji.util.HibernateUtil;

public class EmployeeService {
	Session session = null;
	Query query = null;
	public EmployeeService() {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			query = session.createQuery("from Employee");
			query.setMaxResults(3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public List<Employee> getEmployees(String label){
		List<Employee> empsList = null;
		int b = Integer.parseInt(label);
		try {
			if(b == 1) {
				query.setFirstResult(0);
			}
			if(b == 2) {
				query.setFirstResult(3);
			}
			if(b == 3) {
				query.setFirstResult(6);
			}
			empsList = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empsList;
	}
}
