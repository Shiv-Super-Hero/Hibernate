package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.CardPayment;
import com.shivaji.entity.ChequePayment;

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
			tx = session.beginTransaction();
			
			CardPayment card_Pay = new CardPayment();
			card_Pay.setTx_Id("t111");
			card_Pay.setPay_Date("9/11/2024");
			card_Pay.setPay_Amt(5000);
			card_Pay.setCard_No(1234);
			card_Pay.setExpr_Date("12/12/2026");
			
			ChequePayment cheque_Pay = new ChequePayment();
			cheque_Pay.setTx_Id("t222");
			cheque_Pay.setPay_Date("11/11/2024");
			cheque_Pay.setPay_Amt(10000);
			cheque_Pay.setCheque_No(256);
			cheque_Pay.setAcc_No("a222");
			
			String pk_Val1 = (String) session.save(card_Pay);
			System.out.println(pk_Val1+" Inserted Successfully");
			String pk_Val2 = (String) session.save(cheque_Pay);
			System.out.println(pk_Val2+" Inserted Successfully");
			tx.commit();
			
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
