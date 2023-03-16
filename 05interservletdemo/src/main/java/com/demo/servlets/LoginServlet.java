package com.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	// private Statement statement;
	private PreparedStatement preparedStatement;

	public void init(ServletConfig config) {
		try {
			ServletContext context = config.getServletContext();
			String dburl = context.getInitParameter("dburl");
			String dbuser = context.getInitParameter("dbuser");
			String dbpassword = context.getInitParameter("dbpassword");
			Class.forName("com.mysql.jdbc.Driver");/* As Tomcat does not know where to go we have to specify this */
			connection = DriverManager.getConnection(dburl, dbuser, dbpassword);
			preparedStatement = connection.prepareStatement("select * from user where email = ? and password = ?;");

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (!IsValidInput(username, false) || !IsValidInput(password, false)) {
			out.println("<h1>Please enter valid Input :</h1>");
			return;
		}
		ResultSet res = null;
		try {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			boolean result = preparedStatement.execute();// execute update for int result
			if (result) {
				// to new page
				res = preparedStatement.executeQuery();// for displaying
			}
			if (res.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("HomeServleyt");
				String welcomemessage = "Welcome to servlet Communication demo " + username + " !!";
				request.setAttribute("message", welcomemessage);
				rd.include(request, response);
			} else {
				// return to same login page
				out.println("<script>alert(\"Invalid user! wrong email or password\")</script>");
				out.println("<h3 style=\"color:red;\">Error Loging in</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}

		} catch (SQLException e) {
			out.println("Error ocured could not insert :");
			e.printStackTrace();
		}

	}

	private boolean IsValidInput(String inputvalue, boolean isnumber) {
		if (inputvalue == null || inputvalue.isBlank() || inputvalue.isEmpty()) {
			return false;
		} else if (isnumber) {
			try {
				Integer.parseInt(inputvalue);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return true;
		}
	}

	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
