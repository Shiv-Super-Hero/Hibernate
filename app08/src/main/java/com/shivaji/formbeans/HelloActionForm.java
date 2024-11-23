package com.shivaji.formbeans;

import org.apache.struts.action.ActionForm;

public class HelloActionForm extends ActionForm {
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
}
