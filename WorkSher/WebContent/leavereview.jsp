<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leave a Review</title>
</head>
<body>
	<h1>
		Leave a review for
		<c:choose>
			<c:when test="${currentUser.userid == order.requestUser.userid}">
				<c:out value="${order.requestUser.name}" />
			</c:when>
			<c:otherwise>
				<c:out value="${order.postUser.name}" />
			</c:otherwise>
		</c:choose>
	</h1>
	<form method="post" action="ReviewController">
		<input type="hidden" value="${currentUser.userid}" name="fromUserId" />
		<input type="hidden" value="${order.posting.postingid}" name="postingId" />
		<input type="hidden" value="${order.orderId}" />
		<c:choose>
			<c:when test="${currentUser.userId == order.requestUser.userid}">
				<input type="hidden" value="${order.postingUser.userid}"
					name="forUserId" />
			</c:when>
			<c:otherwise>
				<input type="hidden" value="${order.requestUser.userid}"
					name="forUserId" />
			</c:otherwise>
		</c:choose>
		Rating: <input type="number" min="1" max="5" step="0.5" name="reviewRating" required /> <br> Review: <br>
		<textarea rows="5" cols="50" name="reviewText"></textarea>
		<input type="submit" value="Submit Review" />
	</form>
</body>
</html>