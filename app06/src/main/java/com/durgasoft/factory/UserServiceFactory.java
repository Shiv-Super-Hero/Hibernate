package com.durgasoft.factory;

import com.durgasoft.service.UserService;
import com.durgasoft.service.UserServiceImpl;

public class UserServiceFactory {
	private static UserService userService;
	static {
		userService = new UserServiceImpl();
	}
	public static UserService getUserService() {
		return userService;
	}
}
