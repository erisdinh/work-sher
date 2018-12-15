<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order</title>
</head>
<body>
	<h1>Order Information:</h1>
	<table border=1>
		<tr>
			<td>PostingID:</td>
			<td>${order.posting.postingId}</td>
		</tr>
		<tr>
			<td>Posted Username:</td>
			<td>${order.postUser.username}</td>
		</tr>
		<tr>
			<td>Posted User Full Name:</td>
			<td>${order.postUser.name}</td>
		</tr>
		<tr>
			<td>Category:</td>
			<td>${order.posting.jobCategory}</td>
		</tr>
		<tr>
			<td>Posting Description:</td>
			<td>${order.posting.description}</td>
		</tr>
		<tr>
			<td>Compensation:</td>
			<td>${order.posting.compensation}</td>
		</tr>
		<tr>
			<td>Requested UserName:</td>
			<td>${order.requestUser.username}</td>
		</tr>
		<tr>
			<td>Requested User Full Name:</td>
			<td>${order.requestUser.name}</td>
		</tr>
		<tr>
			<td>Order Description:</td>
			<td>${order.description}</td>
		</tr>
		<tr>
			<td>Order Status:</td>
			<td>${order.status}</td>
		</tr>
		<tr>
			<td>Order Requested Date:</td>
			<td>${order.dateRequested}</td>
		</tr>
		<c:if test="${order.status!='pending' }">
			<tr>
				<td>Order Response Date:</td>
				<td>${order.dateResponsed}</td>
			</tr>
			<c:if test="${order.status=='completed'}">
				<tr>
					<td>Order Completed Date:</td>
					<td>${order.dateCompleted}</td>
				</tr>
			</c:if>
		</c:if>
	</table>

	<form action="ManageOrder">
		<c:if test="${currentUser.userid==order.requestUser.userid}">
			<button>Cancel Order</button>
			<button>Edit Order</button>
			<c:if test="${order.status=='complete'}">
				<button>Leave Review</button>
			</c:if>
		</c:if>
		<c:if test="${currentUser.userid==order.postUser.userid}">
			<c:if test="${order.status=='pending'}">
				<button>Reject Order</button>
				<button>Approve Order</button>
			</c:if>
			<button></button>
		</c:if>
	</form>
</body>
</html>