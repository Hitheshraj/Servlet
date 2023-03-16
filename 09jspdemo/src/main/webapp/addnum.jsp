<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	int num1 = Integer.parseInt(request.getParameter("number1"));
	int num2 = Integer.parseInt(request.getParameter("number1"));
	%>
	Sum of Number =<%=num1 + num2%>
</body>
</html>