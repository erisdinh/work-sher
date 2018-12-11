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
	$(document).ready(function() {
		
		var index = 2;
		
		var indexString = "LoadReviews?index=" + index;
		console.log(indexString);

		$("#prev-button").on("click", function() {
			console.log("Prev button clicked.");
			if (index > 0) {
				$.ajax({
					type: "get",
					dataType: "json",
					url: indexString,
					success: function(data) {
						console.log(data);
						
						for (var i = 0; i < data.features.length; i++) {
							$("#reviews-table").append("<tr><td>" + data[i].username + "</td><td>" + data[i].date + "</td><td>" + data[i].rating + "</td></tr>");
							$("#reviews-table").append("<tr><td>" + data[i].text + "</td></tr>");
						}
					},
					error: function(x, status, error) {
						console.log(error);
					}
				})
			}
		})
		
		$("next-button").on("click", function() {
			console.log("Next button clicked.");
			if (index < ${reviews.size()}) {
				$.ajax({
					type: "get",
					dataType: "json",
					url: indexString,
					success: function(data) {
						console.log(data);
					},
					error: function(x, status, error) {
						console.log(error);
					}
				})
			}
		})
	})
	
	
</script>
</head>
<body>
	<!--  These are temp test forms for the review. They are not representative of how reviews will actually be formatted -->
	<form method="post" action="LoadReviews">
		<input type="submit" value="Test LoadReviews" />
	</form>
	<br>
	<form method="post" action="CreateReview">
		Rating: <input type="number" min="1" max="5" step="0.5"
			name="reviewRating" required /> <br> Review: <br>
		<textarea rows="5" cols="50" name="reviewText"></textarea>
		<input type="submit" value="Test CreateReview" />
	</form>
	<!--  End of temp test forms -->
	<!--  Load Profile Reviews  -->
	<table id="reviews-table">
		<tr id="reviews-table-head"></tr>
		<tr id="reviews-table-text"></tr>
	</table>

	<button id="prev-button">Prev</button>
	<button id="next-button">Next</button>
	<!--  End of Load Profile Reviews  -->

	</br>
	</br>
	<!-- Link to manage all user's order in user mode -->
	<a href="viewOrders.jsp">View Orders</a>
</body>
</html>