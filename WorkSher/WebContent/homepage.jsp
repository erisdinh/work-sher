<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Dashboard</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">
h2.welcome {
	width: 100%;
	font-size: 2.5em;
	text-align: left;
	margin-bottom: 0%;
}

p {
	margin: 0%;
}

div.welcome {
	width: 75%;
	text-align: left;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<c:choose>
		<c:when test="${currentUser == null}">
			<a href="login.jsp">Login</a>
			<br>
			<a href="register.jsp">Register</a>
			<br>
		</c:when>
		<c:otherwise>
			<div class="welcome">
			<h2 class="welcome">Hello, ${currentUser.name}</h2>
			<p class="welcome">Welcome to your WorkSher dashboard. <a href="PostingController?action=insert">Post a job!</a></p>
			</div>
			<jsp:include page="homePageListings.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>
</body>
</html>