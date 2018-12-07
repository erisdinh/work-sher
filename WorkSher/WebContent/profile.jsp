<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<!--  These are temp test forms for the review. They are not representative of how reviews will actually be formatted -->
	<form method="post" action="LoadReviews">
		<input type="submit" value="Test LoadReviews" />
	</form>
	<br>
	<form method="post" action="CreateReview">
		# of stars: <input type="number" min="1" max="5" name="reviewRating" required />
		<br>
		Review:
		<br>
		<textarea rows="5" cols="10" name="reviewText"></textarea>
		<input type="submit" value="Test CreateReview" />
	</form>
	<!--  End of temp test forms -->
</body>
</html>