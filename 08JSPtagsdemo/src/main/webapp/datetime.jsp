<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Date time</title>
</head>
<body>
	Time on the server is <%= new Date() %>
	<br></br>
	<%java.util.Date mydate=new java.util.Date(); %>
	Mydate = <%= mydate %>	
	
</body>
</html>