package com.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	//private Statement statement;
	private PreparedStatement preparedStatement;

	public void init(ServletConfig config) {
		try {
			ServletContext context = config.getServletContext();
			String dburl = context.getInitParameter("dburl");
			String dbuser = context.getInitParameter("dbuser");
			String dbpassword = context.getInitParameter("dbpassword");
			Class.forName("com.mysql.jdbc.Driver");/* As Tomcat does not know where to go we have to specify this */
			connection = DriverManager.getConnection(dburl, dbuser, dbpassword);
			preparedStatement = connection.prepareStatement("insert into product values(?,?,?,?);");

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String discription = request.getParameter("discription");
		String price = request.getParameter("price");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (!IsValidInput(id, true) ||! IsValidInput(name, false) || !IsValidInput(discription, false)
				||! IsValidInput(price, true)) {
			out.println("<h1>Please enter valid Input :</h1>");
			return;
		}
		try {
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, discription);
			preparedStatement.setInt(4,Integer.parseInt(price));
			int result=preparedStatement.executeUpdate();
			out.println("Product Created result :"+result);

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
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
