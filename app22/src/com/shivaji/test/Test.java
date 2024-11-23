package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.EmployeeAccount;
import com.shivaji.entity.StudentAccount;

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
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			StudentAccount sa = new StudentAccount();
			sa.setAccNo("a111");
			sa.setAccName("AAA");
			sa.setAccType("Savings");
			sa.setSid("S-111");
			sa.setSbranch("CS");
			sa.setSmarks(89);
			
			EmployeeAccount ea = new EmployeeAccount();
			ea.setAccNo("a222");
			ea.setAccName("BBB");
			ea.setAccType("Savings");
			ea.setEid("E-111");
			ea.setEsal(5000);
			ea.setEaddr("Hyd");
			
			tx = session.beginTransaction();
			String pk_Val1 = (String)session.save(sa);
			String pk_Val2 = (String)session.save(ea);
			
			if(pk_Val1.equals("a111") && pk_Val2.equals("a222")) {
				tx.commit();
				System.out.println("Employee "+pk_Val1+" and "+pk_Val2+ " are Inserted Successfully");
			}else {
				tx.rollback();
				System.out.println("Employee "+pk_Val1+" and "+pk_Val2+ " are Not Inserted Successfully");
			}
			
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Employee Insertion Failure");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
