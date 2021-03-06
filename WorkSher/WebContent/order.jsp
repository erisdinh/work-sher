<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="model.User, model.Order, dao.ReviewDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Order Information</title>
<link rel="stylesheet" href="css/main.css" type="text/css">
<style type="text/css">
.body {
	height: 100%;
	width: 80%;
	background-color: white;
	margin-left: auto;
	margin-right: auto;
	padding: 1%;
	border-radius: 15px;
	font-size: 25px;
}

button {
	background-color: #00ADB5;
	font-family: Arial, sans-serif;
	font-size: 1em;
	color: white;
	height: 2em;
	border: none;
	border-radius: 2px;
}

#message{
	color: red;
	font-style: italic;
}

.nobr {
	display: inline-block;
}
</style>
</head>
<body>
	<%
		User user = (User) session.getAttribute("currentUser");
		Order order = (Order) session.getAttribute("order");
		boolean exist = ReviewDAO.checkIfReviewExists(user.getUserid(), order.getOrderid());
		request.setAttribute("reviewExist", exist);
	%>

	<jsp:include page="nav.jsp"></jsp:include>

	<div class="body">
		<c:if test="${orderMessage!=null}">
			<span id="message">${orderMessage}</span>
		</c:if>

		<h1>Order Information</h1>
		<table>
			<c:if test="${type=='old'}">
				<tr>
					<td>OrderID:</td>
					<td>${order.orderid}</td>
				</tr>
			</c:if>
			<c:if
				test="${currentUser.userid==order.requestUser.userid || currentUser.role=='admin'}">
				<tr>
					<td>Post User:</td>
					<td>${order.postUser.username}</td>
				</tr>
			</c:if>
			<c:if
				test="${currentUser.userid==order.postUser.userid || currentUser.role=='admin'}">
				<tr>
					<td>Request User:</td>
					<td>${order.requestUser.username}</td>
				</tr>
			</c:if>
			<tr>
				<td>PostingID:</td>
				<td>${order.posting.postingId}</td>
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
				<td>Order Description:</td>
				<td>${order.description}</td>
			</tr>
			<tr>
				<td>Status:</td>
				<td>${order.status}</td>
			</tr>
			<c:if test="${type=='old'}">
				<tr>
					<td>Requested Date:</td>
					<td>${order.dateRequested}</td>
				</tr>
			</c:if>
			<c:if test="${order.status!='Pending' && type=='old'}">
				<tr>
					<td>Responsed Date:</td>
					<td>${order.dateResponsed}</td>
				</tr>
				<c:if test="${order.status=='Completed'}">
					<tr>
						<td>Completed Date:</td>
						<td>${order.dateCompleted}</td>
					</tr>
				</c:if>
			</c:if>
		</table>

		</br>

		<c:if test="${type=='old'}">
			<form action="ManageOrder">
				<c:if
					test="${currentUser.userid==order.requestUser.userid && currentUser.role=='user'}">
					<c:if test="${order.status=='Pending'}">
						<button type="submit" value="cancel" name="action">Cancel</button>
						<button type="submit" value="update" name="action">Update</button>
					</c:if>
					<c:if test="${order.status=='Approved'}">
						<button type="submit" value="cancel" name="action">Cancel</button>
					</c:if>
				</c:if>
				<c:if
					test="${currentUser.userid==order.postUser.userid && currentUser.role=='user'}">
					<c:if test="${order.status=='Pending'}">
						<button type="submit" value="reject" name="action">Reject</button>
						<button type="submit" value="approve" name="action">Approve</button>
					</c:if>
					<c:if test="${order.status=='Approved'}">
						<button type="submit" value="complete" name="action">Complete</button>
					</c:if>
				</c:if>
			</form>
			<c:if
				test="${order.status=='Completed' && reviewExist=='false'&& (currentUser.userid==order.requestUser.userid || currentUser.userid==order.postUser.userid)}">
				<form action="ReviewController">
					<button type="submit" name="action" value="leaveReview">Review</button>
				</form>
			</c:if>
			<c:if test="${currentUser.role=='admin' && param.action!='delete'}">
				<form action="ManageOrder" class="nobr">
					<c:if test="${order.status=='Pending'}">
						<button type="submit" value="cancel" name="action">Cancel</button>
						<button type="submit" value="update" name="action">Update</button>
						<button type="submit" value="reject" name="action">Reject</button>
						<button type="submit" value="approve" name="action">Approve</button>
					</c:if>
					<c:if test="${order.status=='Approved'}">
						<button type="submit" value="complete" name="action">Complete</button>
					</c:if>
				</form>
				<form action="order.jsp" method="post" class="nobr">
					<button id="delete "type="submit" value="delete" name="action">Delete</button>
				</form>
			</c:if>
			<c:if test="${param.action=='delete'}">
				<form action="ManageOrder"">
					<div>
						<span>Do you want to delete this order?</span>
						<button type="submit" value="skip" name="action">Cancel</button>
						<button type="submit" value="confirm" name="action">Confirm</button>
					</div>
				</form>
			</c:if>
		</c:if>
	</div>
</body>
</html>