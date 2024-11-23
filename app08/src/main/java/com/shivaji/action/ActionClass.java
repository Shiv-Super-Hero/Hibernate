package com.shivaji.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.shivaji.formbeans.HelloActionForm;

public class ActionClass extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HelloActionForm haf = (HelloActionForm) form;
		String uanme = haf.getUname();
		request.setAttribute("uname", uanme);
		return mapping.findForward("wish");
	}
}
