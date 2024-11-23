package com.shivaji.test;

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
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			
			tx = session.beginTransaction();
			Employee emp = new Employee();
			//emp.setEno(111);
			emp.setEname("BBB");
			emp.setEsal(3000);
			emp.setEaddr("Hyd");
			int pk_Val = (int)session.save(emp);
			
			tx.commit();
			System.out.println(pk_Val+" Employee Inserted Successfully");
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}


//In order to create Sequence we need to write following command in sqlplus : create sequence my_sequence increment by 5;
//Here my_sequence is sequence name and 5 is increment magnitude by default sequence name is hibernate_sequence.

//These are the commands for HiLo primary key generation algo

/*SQL> create table key_generator(key_Value int primary key);

Table created.

SQL> insert into key_generator values(100);

1 row created.

SQL> commit;

Commit complete.

SQL> select * from emp1;*/

//In order to accomodate the primary key in the eno column we need to create the table with ENO number(>=4)


//This is the code for hibernate-mapping file
/*
<generator class="org.hibernate.id.TableHiLoGenerator">
<param name="table">key_generator</param>
<param name="column">key_Value</param>
<param name="max_lo">10</param>
</generator>
*/