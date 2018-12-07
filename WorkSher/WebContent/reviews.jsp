<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Reviews</title>
</head>
<body>
	<table id="reviews-table">
		<c:forEach var="review" items="${reviews}">
			<tr>
				<td><c:out value="${review.userId}" /></td>
				<td><c:out value="${review.reviewDate}" /></td>
				<td><c:out value="${review.reviewRating}" /></td>
			</tr>
			<tr>
				<td><c:out value="${review.reviewText}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>