package com.shivaji.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			sessionFactory  = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			
			//Query query = session.createQuery("from Employee");
			/*
			List<Employee> list = query.list();
			System.out.println("Using list() Method");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("---------------------------");
			for(Employee emp:list) {
				System.out.print(emp.getEno()+"\t");
				System.out.print(emp.getEname()+"\t");
				System.out.print(emp.getEsal()+"\t");
				System.out.print(emp.getEaddr()+"\n");
			}
			*/
			/*
			Iterator<Employee> it = query.iterate();
			System.out.println("Using iterate() method");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("--------------------------");
			while(it.hasNext()) {
				Employee emp = it.next();
				System.out.print(emp.getEno()+"\t");
				System.out.print(emp.getEname()+"\t");
				System.out.print(emp.getEsal()+"\t");
				System.out.print(emp.getEaddr()+"\n");
			}
			*/
			
			/*
			ScrollableResults results = query.scroll();
			System.out.println("Using scroll method");
			
			System.out.println();
			System.out.println("Employee Details in Forward Direction");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("--------------------------");
			while(results.next()) {
				Object[] obj = results.get();
				for(Object o:obj) {
					Employee emp = (Employee)o;
					System.out.print(emp.getEno()+"\t");
					System.out.print(emp.getEname()+"\t");
					System.out.print(emp.getEsal()+"\t");
					System.out.print(emp.getEaddr()+"\n");
				}
			}
			
			System.out.println();
			System.out.println("Employee Details in Backward Direction");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("--------------------------");
			while(results.previous()) {
				Object[] obj = results.get();
				for(Object o:obj) {
					Employee emp = (Employee)o;
					System.out.print(emp.getEno()+"\t");
					System.out.print(emp.getEname()+"\t");
					System.out.print(emp.getEsal()+"\t");
					System.out.print(emp.getEaddr()+"\n");
				}
			}
			*/
			/*
			Employee emp = (Employee)query.uniqueResult();
			System.out.println("Using uniqueResult() method");
			System.out.println("Employee Details");
			System.out.println("--------------------------");
			System.out.println("Employee Number   : "+emp.getEno());
			System.out.println("Employee Name     : "+emp.getEname());
			System.out.println("Employee Salary   : "+emp.getEsal());
			System.out.println("Employee Address  : "+emp.getEaddr());
			*/
			/*
			
			tx = session.beginTransaction();
			Query query = session.createQuery("update Employee set esal = esal+251 where esal<6000");
			int rowCount = query.executeUpdate();
			tx.commit();
			System.out.println("Employee Updated Successfully");
			System.out.println("No of Records Updated   : "+rowCount);
			*/
			
			tx = session.beginTransaction();
			Query query = session.createQuery("delete from Employee where esal<3500");
			int rowCount = query.executeUpdate();
			tx.commit();
			System.out.println(rowCount+" Employees Deleted Successfully");
			
			
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Employee Deletion Failure");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
