<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Review</title>
</head>
<body>
	<h1>Edit Review</h1>
	
	Editing review for: ${review.forUsername}
	<br>
	Posting: <a href="PostingController?action=view&postingId=${posting.postingId}">${posting.title}</a>
	<br>
	Order #: <a href="LoadOrder?orderid=${review.orderId}">${review.orderId}</a>
	<form action="ReviewController" method="post">
		<input type="hidden" name="reviewId" value="${review.reviewId}" />
		<input type="hidden" name="action" value="edit" />
		<input type="hidden" name="referrer" value="${referrer}" />
		Rating:
		<input type="number" min="1" max="5" name="reviewRating" step="0.5" value="${review.reviewRating}" required />
		<br>
		Review:
		<br>
		<textarea rows="5" cols="50" name="reviewText" placeholder="Enter review text here!">${review.reviewText}</textarea>
		<br>
		<input type="submit" value="Edit Review" />
	</form>
</body>
</html>