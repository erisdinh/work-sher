<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | View Orders | ${currentUser.username}</title>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<c:choose>
		<c:when test="${param.initial=='true'}">
			<jsp:forward page="/LoadOrders?load=${param.load}" />
		</c:when>
		<c:otherwise>

			<h1>Manage Orders</h1>
			<div>
				<c:if test="${load=='received'}">
					<h1>Received Orders</h1>
				</c:if>
				<c:if test="${load=='requested'}">
					<h1>Placed Orders</h1>
				</c:if>
			</div>
			<div>
				<c:forEach items="${orders}" var="order" begin="${(param.page)*5}"
					end="${(param.page)*5+4}" varStatus="">
					<div>
						<table border=1>
							<tr>
								<td colspan=2><a href="LoadOrder?orderid=${order.orderid}">
										<em><c:out value="OrderID: ${order.orderid}" /></em>
								</a></td>
							</tr>
							<tr>
								<td colspan=2><c:out
										value="Posting ID: ${order.posting.postingId}" /></td>
							</tr>
							<tr>
								<td colspan=2><c:out
										value="Job Category: ${order.posting.jobCategory}" /></td>
							</tr>
							<c:if test="${load=='received'}">
								<tr>
									<td colspan=2><c:out
											value="Request User UserName: ${order.requestUser.username}" /></td>
								</tr>
								<tr>
									<td colspan=2><c:out
											value="Request User Full Name: ${order.requestUser.name}" /></td>
								</tr>
							</c:if>
							<c:if test="${load=='requested'}">
								<tr>
									<td colspan=2><c:out
											value="Post UserName: ${order.postUser.username }" /></td>
								</tr>
								<tr>
									<td colspan=2><c:out
											value="Post User Full Name: ${order.postUser.name}" /></td>
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
			</div>
			<c:if
				test="${param.page < (numberOfOrderPages-1) || param.page == null}">
				<span>${(param.page)*5+4+1}/${fn:length(orders)}</span>
			</c:if>
			<c:if test="${param.page == (numberOfOrderPages-1)}">
				<span>${fn:length(orders)}/${fn:length(orders)}</span>
			</c:if>
			<div>
				<form action="viewOrders.jsp">
					<c:if test="${numberOfOrderPages!= 1}">
						<c:if test="${param.page > 0}">
							<button type="submit" value="${param.page-1}" name="page">Previous</button>
						</c:if>
						<c:forEach begin="0" end="${numberOfOrderPages-1}"
							varStatus="loop">
							<button type="submit" value="${loop.index}" name="page">${loop.count}</button>
						</c:forEach>
						<c:if
							test="${param.page <= (numberOfOrderPages-2) || param.page == null}">
							<button type="submit" value="${param.page+1}" name="page">Next</button>
						</c:if>
					</c:if>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>