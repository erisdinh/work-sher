<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorSher | View Orders | ${currentUser.username}</title>
</head>
<body>
	<h1>Manage Orders</h1>
	<div>
		<table>
			<tr>
				<td><a href="../LoadOrders?mode=user&load=received">Received
						Orders</a></td>
			</tr>
			<tr>
				<td><a href="../LoadOrders?mode=user&load=requested">Requested
						Orders</a></td>
			</tr>
		</table>
	</div>
	<div>
		<c:forEach items="${orders}" var="order"
			begin="${(param.pageOrders)*5}" end="${(param.pageOrders)*5+4}">
			<div>
				<table border=1>
					<tr>
						<td colspan=2>
							<a href="../LoadOrder?mode=user&orderid=${order.orderid}">
								<em><c:out value="OrderID: ${order.orderid}" /></em>
							</a>
						</td>
					</tr>
					<tr>
						<td colspan=2><c:out value="Posting ID: ${order.posting.postingid }" /></td>
					</tr>
					<tr>
						<td colspan=2><c:out value="Job Category: ${order.posting.jobCategory }" /></td>
					</tr>
					<c:if test="${load=='received'}">
						<tr>
							<td colspan=2><c:out value="Request User UserName: ${order.requestUser.username }" /></td>
						</tr>
						<tr>
							<td colspan=2><c:out value="Request User Full Name: ${order.requestUser.name}" /></td>
						</tr>
					</c:if>
					<c:if test="${load=='requested'}">
						<tr>
							<td colspan=2><c:out value="Post UserName: ${order.postUser.username }" /></td>
						</tr>
						<tr>
							<td colspan=2><c:out value="Post User Full Name: ${order.postUser.name}" /></td>
						</tr>
					</c:if>
					<tr>
						<td><c:out value="Date: ${order.dateRequested}" /></td>
						<td><c:out value="Order status: ${order.status}" /></td>
					</tr>

					</br>
				</table>
			</div>
		</c:forEach>

		<div>
			<form action="viewOrders.jsp">
				<c:forEach begin="0" end="${numberOfOrderPages}" varStatus="loop">
					<button type="submit" value="${loop.index}" name="pageOrders">${loop.count}</button>
				</c:forEach>
			</form>
		</div>
</body>
</html>