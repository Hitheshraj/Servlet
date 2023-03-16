<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border=2>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Password</th>
		</tr>
		<c:forEach var="user" items="${User_list}">
			<c:if test="${user.bln}">
				<tr>
					<td>${user.firstname}</td>
					<td>${user.lastname}</td>
					<td>${user.email}</td>
					<td>${user.password}</td>
				</tr>
			</c:if>

		</c:forEach>
	</table>
</body>
</html>