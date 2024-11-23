package com.shivaji.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;

import com.shivaji.entity.Employee;

public class Test {

	public static void main(String[] args) {
		StandardServiceRegistryBuilder builder = null;
		StandardServiceRegistry registry = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/com/shivaji/resources/hibernate.cfg.xml");
			builder = new StandardServiceRegistryBuilder();
			builder.applySettings(cfg.getProperties());
			registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			
			Criteria c = session.createCriteria(Employee.class);
			Criterion c1 = Restrictions.gt("esal", 2500.0f);
			Criterion c2 = Restrictions.lt("esal", 5500.0f);
			c.add(c1);
			c.add(c2);
			
			Projection p1 = Projections.property("eno");
			Projection p2 = Projections.property("ename");
			Projection p3 = Projections.property("esal");
			Projection p4 = Projections.property("eaddr");
			ProjectionList pl = Projections.projectionList();
			
			pl.add(p1);
			pl.add(p2);
			pl.add(p3);
			pl.add(p4);
			c.setProjection(pl);
			
			Order o = Order.desc("ename");
			c.addOrder(o);
			
			List<Object[]> list = c.list();
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("-------------------------------");
			for(Object[] obj : list) {
				for(Object ob : obj) {
					System.out.print(ob+"\t");
				}
				System.out.println();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				session.close();
				sessionFactory.close();
				StandardServiceRegistryBuilder.destroy(registry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
