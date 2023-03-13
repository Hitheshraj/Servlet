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

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;

	public void init(ServletConfig config) {
		try {
			/* As Tomcat does not know where to go we have to specify this */
			ServletContext context= config.getServletContext();
			String dburl=context.getInitParameter("dburl");
			String dbuser=context.getInitParameter("dbuser");
			String dbpassword=context.getInitParameter("dbpassword");
			Class.forName("com.mysql.jdbc.Driver");/* As Tomcat does not know where to go we have to specify this */
			connection = DriverManager.getConnection(dburl,dbuser,dbpassword);
			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		try {
			int result = statement
					.executeUpdate("delete from user where firstname='"+firstname+"'and lastname='"+lastname+"';");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<h1>User Deleted</h1>");
			} else {
				out.println("<h1>Error Deleting User</h1>");
			}
			out.println("<a href=\"index.html\">Home</a>");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
