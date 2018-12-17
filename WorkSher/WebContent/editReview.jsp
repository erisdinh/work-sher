<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="nav.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Edit Review</title>
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
	clear: left;
	margin: 1% auto auto auto;
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
	<h2>Edit Review</h2>

	<form action="ReviewController" method="post">
		<label id="review-username">Editing review for:</label><p>${review.forUsername}</p>
		<label id="review-posting">Posting:</label><p>${posting.title}</p>
			<label id="review-order">Order #:</label><p>${review.orderId}</p>
		<input type="hidden" name="reviewId" value="${review.reviewId}" /> <input
			type="hidden" name="action" value="edit" /> <input type="hidden"
			name="referrer" value="${referrer}" /> <label id="rating-label">Rating:</label>
		<input type="number" min="1" max="5" name="reviewRating" step="0.5"
			value="${review.reviewRating}" required /><br> <label id="review-rating">Review:</label>
		<textarea rows="3" name="reviewText"
			placeholder="Enter review text here!">${review.reviewText}</textarea>
		<input type="submit" value="Edit Review" class="btn" id="submit" />
	</form>
</body>
</html>