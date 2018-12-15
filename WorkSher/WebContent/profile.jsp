<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="javascript/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<!--  These are temp test forms for the review. They are not representative of how reviews will actually be formatted -->
	<form method="post" action="LoadReviews">
		<input type="submit" value="Test LoadReviews" />
	</form>
	<br>
	<form method="post" action="CreateReview">
		Rating: <input type="number" min="1" max="5" step="0.5" name="reviewRating" required />
		<br>
		Review:
		<br>
		<textarea rows="5" cols="50" name="reviewText"></textarea>
		<input type="submit" value="Test CreateReview" />
	</form>
	<!--  End of temp test forms -->
	<!--  Load Profile Reviews  -->
		<table id="reviews-table">
		<c:forEach var="review" items="${reviews}" varStatus="status">
			<tr>
				<td><c:out value="${review.username}" /></td>
				<td><img src="images/${reviewImages[status.index]}" width="50px"/></td>
				<td><c:out value="${review.reviewDate}" /></td>
			</tr>
			<tr>
				<td><c:out value="${review.reviewText}" /></td>
			</tr>
		</c:forEach>
	</table>
	
	<button id="prev-button" onclick="previous()">Prev</button> <button id="next-button" onclick="next()">Next</button>
	<!--  End of Load Profile Reviews  -->
	
	</br></br>
	<!-- Link to manage all user's order in user mode -->
	<a href="viewOrders.jsp?load=received">View Orders</a>
</body>
</html>