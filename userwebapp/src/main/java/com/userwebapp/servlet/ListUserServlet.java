package com.userwebapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
//Prepared statement compiles ones executes many times{TO improve perfomance}
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listuser")
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connetion;
	
	public void init(ServletConfig config) {
		try {
			ServletContext context= config.getServletContext();
			String dburl=context.getInitParameter("dburl");
			String dbuser=context.getInitParameter("dbuser");
			String dbpassword=context.getInitParameter("dbpassword");
			Class.forName("com.mysql.jdbc.Driver");/* As Tomcat does not know where to go we have to specify this */
			connetion = DriverManager.getConnection(dburl,dbuser,dbpassword);

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (Statement statement = connetion.createStatement();
				ResultSet result = statement.executeQuery("select * from user;\r\n");) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");

			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<th>First Name</th>");
			out.println("<th>Last Name</th>");
			out.println("<th>Email</th>");
			out.println("</tr>");
			while (result.next()) {
				String firstname = result.getString(1);
				String lastname = result.getString(2);
				String email = result.getString(3);
				out.println("<tr>");
				out.println("<td>" + firstname + "</td>");
				out.println("<td>" + lastname + "</td>");
				out.println("<td>" + email + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<a href=\"index.html\">Home</a>");
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void destroy() {
		try {
			if (connetion != null) {
				connetion.close();
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
