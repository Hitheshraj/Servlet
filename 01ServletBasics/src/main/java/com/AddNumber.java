package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AddNumber extends  GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String number1=request.getParameter("number1");
		String number2=request.getParameter("number2");
		PrintWriter out =response.getWriter();
		if(number1==null||number2==null||number1.isEmpty()||number2.isEmpty()||number1.isBlank()||number2.isBlank()) {
			out.println("Invalid Input");
		}
		else {
			try {
		int num1int =Integer.parseInt(number1);
		int num2int=Integer.parseInt(number2);
		int result=num1int+num2int;
		out.println("<p>Result = "+(result)+"</p>");
			}catch(Exception e) {
				out.println(e.getMessage());
			}
		}
	}

}
