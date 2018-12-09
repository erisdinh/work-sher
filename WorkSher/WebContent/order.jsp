<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order</title>
</head>
<body>
	<c:if test="${newOrder.status == 'pending'}">
		<c:out value="Order has been placed successfully!"/>
	</c:if>
	<h1>Order Information:</h1>
	<table border=1>
		<tr>
			<td>PostingID:</td>
			<td>${posting.postingid}</td>
		</tr>
		<tr>
			<td>Posted Username:</td>
			<td>${posting.user.username}</td>
		</tr>
		<tr>
			<td>Posted User Full Name:</td>
			<td>${posting.user.name}</td>
		</tr>
		<tr>
			<td>Category:</td>
			<td>${posting.jobCategory}</td>
		</tr>
		<tr>
			<td>Posting Description:</td>
			<td>${posting.description}</td>
		</tr>
		<tr>
			<td>Compensation:</td>
			<td>${posting.compensation}</td>
		</tr>
		<tr>
			<td>Requested UserName:</td>
			<td>${currentUser.username}</td>
		</tr>
		<tr>
			<td>Requested User Full Name:</td>
			<td>${currentUser.name}</td>
		</tr>
		<tr>
			<td>Order Description:</td>
			<td>${newOrder.description}</td>
		</tr>
	</table>
</body>
</html>