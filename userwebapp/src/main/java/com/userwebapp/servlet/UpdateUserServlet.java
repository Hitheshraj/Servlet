package com.userwebapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connetion;
	private Statement statement;

	public void init(ServletConfig config) {
		try {
			ServletContext context= config.getServletContext();
			String dburl=context.getInitParameter("dburl");
			String dbuser=context.getInitParameter("dbuser");
			String dbpassword=context.getInitParameter("dbpassword");
			Class.forName("com.mysql.jdbc.Driver");/* As Tomcat does not know where to go we have to specify this */
			connetion = DriverManager.getConnection(dburl,dbuser,dbpassword);
			statement = connetion.createStatement();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			int result = statement
					.executeUpdate("update user set password='" + password + "' where email='" + email + "';");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<h1>Password Changed</h1>");
			} else {
				out.println("<h1>Error Changing Password</h1>");
			}
			out.println("<a href=\"index.html\">Home</a>");
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connetion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
