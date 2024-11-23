package com.durgasoft.service;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.durgasoft.pojo.User;
import com.durgasoft.util.HibernateUtil;

public class UserServiceImpl implements UserService {

	@Override
	public String checkLogin(String uname, String upwd) {
		String status = "";
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			User user = (User) session.get(User.class, uname);
			if(user == null) {
				status = "User not Exist";
			}else {
				if(user.getUpwd().equals(upwd)) {
					status = "Success";
				}else {
					status = "Failure";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
