<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Review</title>
<link rel="stylesheet" href="css/main.css" type="text/css">
<style>
	h2 {
		text-align: center;
	}
	
	#submit {
	display: block;
	width: 20%;
	margin-left: auto;
	margin-right: auto;
}

form {
	width: 70%;
	margin-left: auto;
	margin-right: auto;
}

label {
	width: 30%;
	margin: 0.5%;
}

input[type=number], input[type=textarea] {
	width: 50%;
	margin: 0.5%;
}

</style>
</head>
<body>
	<h2>Edit Review</h2>
	
	<label id="review-username">Editing review for:</label> ${review.forUsername}
	<br>
	<label id="review-posting">Posting:</label> ${posting.title}
	<br>
	<label id="review-order">Order #:</label> ${review.orderId}
	<br>
	<form action="ReviewController" method="post">
		<input type="hidden" name="reviewId" value="${review.reviewId}" />
		<input type="hidden" name="action" value="edit" />
		<input type="hidden" name="referrer" value="${referrer}" />
		<label id="rating-label">Rating:</label>
		<br>
		<input type="number" min="1" max="5" name="reviewRating" step="0.5" value="${review.reviewRating}" required />
		<br>
		<label id="review-rating">Review:</label>
		<textarea rows="5" cols="50" name="reviewText" placeholder="Enter review text here!">${review.reviewText}</textarea>
		<br>
		<input type="submit" value="Edit Review" class="btn" />
	</form>
</body>
</html>