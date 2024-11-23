package com.shivaji.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.shivaji.pojo.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory oracle_Session_Factory = null;
		SessionFactory mysql_Session_Factory = null;
		
		Session oracle_Session = null;
		Session mysql_Session = null;
		
		Transaction mysql_Transaction = null;
		
		try {
			
			Configuration oracle_Cfg = new Configuration();
			oracle_Cfg.configure("/com/shivaji/resources/hibernate_oracle_cfg.xml");
			
			Configuration mysql_Cfg = new Configuration();
			mysql_Cfg.configure("/com/shivaji/resources/hibernate_mysql_cfg.xml");
			
			oracle_Session_Factory = oracle_Cfg.buildSessionFactory();
			mysql_Session_Factory = mysql_Cfg.buildSessionFactory();
			
			oracle_Session = oracle_Session_Factory.openSession();
			mysql_Session = mysql_Session_Factory.openSession();
			
			Employee emp = (Employee) oracle_Session.get(Employee.class, 444);
			if(emp != null) {
				System.out.println("Employee Details");
				System.out.println("------------------");
				System.out.println("Employee Id  : "+emp.getEno());
				System.out.println("Employee Name  : "+emp.getEname());
				System.out.println("Employee Salary  : "+emp.getEsal());
				System.out.println("Employee Address  : "+emp.getEaddr());
			}
			
			if(emp == null) {
				System.out.println("Employee 444 does not exist in Oracle Database");
			}
			else {
				mysql_Transaction = mysql_Session.beginTransaction();
				int pk_Val = (Integer)mysql_Session.save(emp);
				if(pk_Val == emp.getEno()) {
					mysql_Transaction.commit();
					System.out.println("Employee Transferred from Oracle DB to MySQL DB Successfully");
				}else {
					mysql_Transaction.rollback();
					System.out.println("Employee is not Transferred from Oracle DB to MySQL DB");
				}
			}
			
		} catch (Exception e) {
			mysql_Transaction.rollback();
			e.printStackTrace();
		}finally{
			oracle_Session.close();
			mysql_Session.close();
			oracle_Session_Factory.close();
			mysql_Session_Factory.close();
		}

	}

}
