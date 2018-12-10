<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorSher | Manager Order | ${currentUser.username}</title>
</head>
<body>
	<h1>Manage Orders</h1>
	<div>
		<table>
			<tr>
				<td><a href="../LoadOrders?mode=user&action=received">Received Orders</a></td>
			</tr>
			<tr>
				<td><a href="../LoadOrders?mode=user&action=requested">Requested Orders</a></td>
			</tr>
		</table>
	</div>
	
	<div border=1>
		<c:if test="${action=='received'}">
			<c:forEach items="${receivedOrders}" var="order">
				<div border=1>
					<a href="../LoadOrder?mode=user&orderid=${order.orderid}"><h3><c:out value="OrderID: ${order.orderid }"/></h3></a></br>
					<c:out value="PostingID: ${order.posting.postingid }"/></br>
					<c:out value="Post User: ${order.postUser.username }"/></br>
					<c:out value="Requested Date: ${order.dateRequested}"/></br>
					<c:out value="Responsed Date: ${order.dateResponsed}"/></br>
					<c:out value="Completed Date: ${order.dateCompleted}"/></br>
					<c:out value="Order status: ${order.status}"/></br>
				</div>
			</c:forEach>
		</c:if>
				<c:if test="${action=='requested'}">
			<c:forEach items="${requestedOrders}" var="order">
				<div border=1>
					<a href="../LoadOrder?mode=user&orderid=${order.orderid}"><h3><c:out value="OrderID: ${order.orderid }"/></h3></a></br>
					<c:out value="PostingID: ${order.posting.postingid }"/></br>
					<c:out value="Post User: ${order.postUser.username }"/></br>
					<c:out value="Requested Date: ${order.dateRequested}"/></br>
					<c:out value="Responsed Date: ${order.dateResponsed}"/></br>
					<c:out value="Completed Date: ${order.dateCompleted}"/></br>
					<c:out value="Order status: ${order.status}"/></br>
				</div>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>