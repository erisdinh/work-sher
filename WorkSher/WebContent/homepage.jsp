<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher</title>
</head>
<body>
	<h1>Welcome to WorkSher!</h1>
	<c:choose>
		<c:when test="${currentUser == null}">
			<a href="login.jsp">Login</a>
			<br>
			<a href="register.jsp">Register</a>
			<br>
		</c:when>
		<c:otherwise>
			<a href="LoadReviews">View Reviews</a>
		</c:otherwise>
	</c:choose>
</body>
</html>