<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="dao.ReviewDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Order Information</title>
</head>
<body>
	<%
		boolean exist = ReviewDAO.checkIfReviewExist();
		request.setAttribute("reviewExist", exist);
	%>
	
	<jsp:include page="nav.jsp"></jsp:include>
	<h1>Order Information:</h1>
	<table border=1>
		<c:if test="${type=='old'}">
			<tr>
				<td>OrderID:</td>
				<td>${order.orderid}</td>
			</tr>
		</c:if>
		<c:if
			test="${currentUser.userid==order.requestUser.userid || currentUser.role=='admin'}">
			<tr>
				<td>Posted User:</td>
				<td>${order.postUser.username}</td>
			</tr>
		</c:if>
		<c:if
			test="${currentUser.userid==order.postUser.userid || currentUser.role=='admin'}">
			<tr>
				<td>Requested User:</td>
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
			<td>Order Status:</td>
			<td>${order.status}</td>
		</tr>
		<c:if test="${type=='old'}">
			<tr>
				<td>Order Requested Date:</td>
				<td>${order.dateRequested}</td>
			</tr>
		</c:if>
		<c:if test="${order.status!='pending' && type=='old'}">
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
				<c:if test="${order.status=='Completed'}">
					<button type="submit" value="review" name="action">Review</button>
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
		</c:if>
	</form>
	<c:if test="${order.status=='Completed'}">
		<form action="ReviewController">
		<button type="submit" name="action" value="leaveReview">Review</button>
			</form>
	</c:if>
		<c:if test="${currentUser.role=='admin' && param.action!='delete'}">
			<form action="ManageOrder">
				<c:if test="${order.status=='Pending'}">
					<button type="submit" value="cancel" name="action">Cancel</button>
					<button type="submit" value="update" name="action">Update</button>
					<button type="submit" value="reject" name="action">Reject</button>
					<button type="submit" value="approve" name="action">Approve</button>
				</c:if>
				<c:if test="${order.status=='Approved'}">
					<button type="submit" value="complete" name="action">Complete</button>
				</c:if>
				<c:if test="${order.status=='Completed'}">
					<button type="submit" value="review" name="action">Review</button>
				</c:if>
			</form>
			<form action="order.jsp" method="post">
				<button type="submit" value="delete" name="action">Delete</button>
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
</body>
</html>