package com.shivaji.test;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.CardPayment;
import com.shivaji.entity.ChequePayment;
import com.shivaji.entity.Payment;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			
			CardPayment card_Pay = (CardPayment) session.get(Payment.class, "t111");
			System.out.println("Card Payment Details");
			System.out.println("------------------------");
			System.out.println("Transaction Id    : "+card_Pay.getTx_Id());
			System.out.println("Payment Date      : "+card_Pay.getPay_Date());
			System.out.println("Payment Amount    : "+card_Pay.getPay_Amt());
			System.out.println("Card Number       : "+card_Pay.getCard_No());
			System.out.println("Expiry Date       : "+card_Pay.getExpr_Date());
			System.out.println();
			
			ChequePayment cheque_Pay = (ChequePayment)session.get(Payment.class, "t222");
			System.out.println("Cheque Payment Details");
			System.out.println("--------------------------");
			System.out.println("Transaction Id    : "+cheque_Pay.getTx_Id());
			System.out.println("Payment Date      : "+cheque_Pay.getPay_Date());
			System.out.println("Payment Amount    : "+cheque_Pay.getPay_Amt());
			System.out.println("Cheque Number     : "+cheque_Pay.getCheque_No());
			System.out.println("Account Number    : "+cheque_Pay.getAcc_No());
			System.out.println();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
