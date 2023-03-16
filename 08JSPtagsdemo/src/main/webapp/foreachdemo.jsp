<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>For each Demo</title>
</head>
<body>
	<%
	String[] places = { "Mumbai", "Bangalore", "Mysore" };
	pageContext.setAttribute("places", places);
	%>
	<%
	for (String place : places) {
		out.println(place);
	}
	%>
	<p>Using tags</p>
	<c:forEach var="place" items="${places}">
    -->${place}<br/>
	</c:forEach>
</body>
</html>