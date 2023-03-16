package com.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomeServleyt")
public class HomeServleyt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	           doGet(request,response);
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		if(username==null) {
			out.println("User name not ound in the session : ");
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		else {
			String favcolor=null;
			out.println("User name found in seeeion "+username);
			for(Cookie cooki:request.getCookies()) {
				favcolor=cooki.getValue();
				out.println("<br><p>User's Favorate color is : "+favcolor.toString());
			}
			
		}
	}

}
