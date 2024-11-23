package com.shivaji.service;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.shivaji.pojo.Student;
import com.shivaji.util.HibernateUtil;

public class StudentService {
	public String register(String sid, String sname, String saddr) {
		String status="";
		Transaction tx = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Student std = (Student)session.get(Student.class, sid);
			if(std == null) {
				std  = new Student();
				std.setSid(sid);
				std.setSname(sname);
				std.setSaddr(saddr);
				tx = session.beginTransaction();
				String pk_Val = (String)session.save(std);
				tx.commit();
				if(pk_Val.equals(sid)) {
					status = "success";
				}else {
					status = "failure";
				}
			}else {
				status = "existed";
			}
		} catch (Exception e) {
			if (tx != null) {
                tx.rollback();
            }
			status = "failure";
			e.printStackTrace();
		}
		return status;
	}
}
