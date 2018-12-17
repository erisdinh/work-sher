<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Leave Review</title>
<link rel="stylesheet" href="css/main.css" type="text/css">
<style>
h2 {
	text-align: center;
}

#submit {
	display: block;
	width: 40%;
	margin-left: auto;
	margin-right: auto;
	padding: 1%;
}


label {
	width: 30%;
	padding: 0;
	margin: 0.5%;
}

input[type=number], textarea {
	border: solid 1px #393E46;
	border-radius: 2px;
	font-family: Arial, sans-serif;
	font-size: 1em;
	color: #393E46;
	padding: 0.5%;
	margin: 0.5%;
	float: left;
}

form {
	width: 40%;
	margin-left: auto;
	margin-right: auto;
}

p {
	padding: 0.5%;
	margin: 1%;
}
</style>
</head>
<body>
	<h2>
		Leave a review for
		<c:choose>
			<c:when test="${currentUser.userid == order.requestUser.userid}">
				<c:out value="${order.postUser.name}" />
			</c:when>
			<c:otherwise>
				<c:out value="${order.requestUser.name}" />
			</c:otherwise>
		</c:choose>
	</h2>
	
	<form method="post" action="ReviewController">
		<label id="review-posting">Posting: </label><p>${order.posting.title}</p>
		<input type="hidden" value="${currentUser.userid}" name="fromUserId" />
		<input type="hidden" value="${order.posting.postingId}" name="postingId" />
		<input type="hidden" value="${order.orderid}" name="orderId" />
		<c:choose>
			<c:when test="${currentUser.userid == order.requestUser.userid}">
				<input type="hidden" value="${order.postUser.userid}"
					name="forUserId" />
			</c:when>
			<c:otherwise>
				<input type="hidden" value="${order.requestUser.userid}"
					name="forUserId" />
			</c:otherwise>
		</c:choose>
		<label id="review-rating">Rating:</label><input type="number" min="1" max="5" step="0.5" name="reviewRating" value="1" required /> <br> 
		<label id="review-text">Review:</label>
		<textarea name="reviewText" placeholder="Enter review here!" rows="5" cols="40"></textarea>
		<br>
		<input type="submit" value="Submit Review" class="btn" id="submit" />
	</form>
</body>
</html>