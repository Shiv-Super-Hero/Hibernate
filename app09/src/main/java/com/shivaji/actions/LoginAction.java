package com.shivaji.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.shivaji.formbeans.LoginActionForm;

public class LoginAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String status="";
		
		LoginActionForm loginActionForm = (LoginActionForm)form;
		
		String uname = loginActionForm.getUname();
		String upwd = loginActionForm.getUpwd();
		
		if(uname.equals("shiva") && upwd.equals("shiva")) {
			status = "success";
		}else {
			status = "failure";
		}
		return mapping.findForward(status);
	}
}
