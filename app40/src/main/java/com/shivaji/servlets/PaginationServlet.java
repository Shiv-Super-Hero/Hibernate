package com.shivaji.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shivaji.entity.Employee;
import com.shivaji.service.EmployeeService;

@WebServlet("/pagination")
public class PaginationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmployeeService empService = null;
	@Override
	public void init() throws ServletException {
		empService = new EmployeeService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String label = request.getParameter("button");
		List<Employee> empList = empService.getEmployees(label);
		
		System.out.println(empList);
		for(Employee emp: empList) {
			System.out.println(emp.getEno()+","+emp.getEname()+","+emp.getEsal()+","+emp.getEaddr());
		}
		
		out.println("<html><body>");
		out.println("<h2 style='color:red;' align='center'>Employee Details</h2>");
		out.println("<center>");
		out.println("<table border='1'>");
		out.println("<tr><th>ENO</th><th>ENAME</th><th>ESAL</th><th>EADDR</th></tr>");
		for(Employee emp: empList) {
			out.println("<tr>");
			out.println("<td>"+emp.getEno()+"</td>");
			out.println("<td>"+emp.getEname()+"</td>");
			out.println("<td>"+emp.getEsal()+"</td>");
			out.println("<td>"+emp.getEaddr()+"</td>");
			out.println("</tr>");
		}
		out.println("</table></center></body></html>");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("form.html");
		requestDispatcher.include(request, response);
	}

}
