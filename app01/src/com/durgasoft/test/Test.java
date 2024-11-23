package com.durgasoft.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import com.durgasoft.pojo.Employee;

public class Test {

	public static void main(String[] args)throws Exception {
		Configuration config = new Configuration();
		config.configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Employee emp = new Employee();
		emp.setEno(666);
		emp.setEname("FFF");
		emp.setEsal(17000);
		emp.setEaddr("Leh");
		//int eno = (Integer)session.save(emp);
		session.persist(emp);
		tx.commit();
		System.out.println("Employee Inserted Successfully");
//		if(eno==444) {
//			System.out.println("Employee Inserted Successfully");
//		}else {
//			System.out.println("Employee Insertion Failure");
//		}
		session.close();
		sessionFactory.close();
	}

}
