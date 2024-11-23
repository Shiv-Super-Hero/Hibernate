package com.shivaji.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.shivaji.formbeans.RegistrationActionForm;
import com.shivaji.service.StudentService;

public class RegistartionAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RegistrationActionForm raf = (RegistrationActionForm)form;
		String sid = raf.getSid();
		String sname = raf.getSname();
		String saddr = raf.getSaddr();
		
		StudentService studentService = new StudentService();
		String status = studentService.register(sid, sname, saddr);
		
		return mapping.findForward(status);
	}
}
