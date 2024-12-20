package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shivaji.entity.Account;

public class Test {

	public static void main(String[] args) {
		Configuration oracle_Cfg = null;
		Configuration mysql_Cfg = null;
		
		SessionFactory oracle_SessionFactory = null;
		SessionFactory mysql_SessionFactory = null;
		
		Session oracle_Session = null;
		Session mysql_Session = null;
		
		Transaction oracle_Tx = null;
		Transaction mysql_Tx = null;
		
		StandardServiceRegistry oracle_Registry = null;
		StandardServiceRegistry mysql_Registry = null;
		
		try {
			oracle_Cfg = new Configuration();
			oracle_Cfg.configure("/com/shivaji/resources/oracle_cfg.xml");
			
			mysql_Cfg = new Configuration();
			mysql_Cfg.configure("/com/shivaji/resources/mysql_cfg.xml");
			
			StandardServiceRegistryBuilder oracle_Builder = new StandardServiceRegistryBuilder();
			oracle_Builder = oracle_Builder.applySettings(oracle_Cfg.getProperties());
			oracle_Registry = oracle_Builder.build();
			
			StandardServiceRegistryBuilder mysql_Builder = new StandardServiceRegistryBuilder();
			mysql_Builder = mysql_Builder.applySettings(mysql_Cfg.getProperties());
			mysql_Registry = mysql_Builder.build();
			
			oracle_SessionFactory = oracle_Cfg.buildSessionFactory(oracle_Registry);
			mysql_SessionFactory = mysql_Cfg.buildSessionFactory(mysql_Registry);
			
			oracle_Session = oracle_SessionFactory.openSession();
			mysql_Session = mysql_SessionFactory.openSession();
			
			Account source_Account = (Account)oracle_Session.get(Account.class, "a111");
			source_Account.setBalance(source_Account.getBalance()-3000); //Debit Operation
			
			Account target_Account = (Account)mysql_Session.get(Account.class, "b111");
			target_Account.setBalance(target_Account.getBalance()+3000); //Credit Operation
			
			oracle_Tx = oracle_Session.beginTransaction();
			mysql_Tx = mysql_Session.beginTransaction();
			
			oracle_Session.update(source_Account);
			mysql_Session.update(target_Account);
			
			oracle_Tx.commit();
			mysql_Tx.commit();
			System.out.println("Transaction Success");
			
			
		} catch (Exception e) {
			oracle_Tx.rollback();
			mysql_Tx.rollback();
			System.out.println("Transaction Failure");
			e.printStackTrace();
		}finally {
			oracle_Session.close();
			oracle_SessionFactory.close();
			
			mysql_Session.close();
			mysql_SessionFactory.close();
			
			StandardServiceRegistryBuilder.destroy(oracle_Registry);
			StandardServiceRegistryBuilder.destroy(mysql_Registry);
			
		}

	}

}
