<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="nav.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Reviews</title>
</head>
<body>
	revStartIndex: ${revStartIndex}
	<br>
	revEndIndex: ${revEndIndex}
	
	<c:choose>
		<c:when test="${reviews.isEmpty()}">
	You have not left any reviews!
	</c:when>
		<c:otherwise>
			<c:set value="10" name="pageSize" scope="page" />
			<table class="reviews-table">
				<c:forEach var="review" items="${reviews.subList(revStartIndex, revEndIndex)}" varStatus="status">
					<tr>
						<td><c:out value="${review.forUsername}" /></td>
						<td><img src="images/${review.reviewImgUrl}"
							width="50px" /></td>
						<td><c:out value="${review.reviewDate}" /></td>
					</tr>
					<tr>
						<td><c:out value="${review.reviewText}" /></td>
					</tr>
				</c:forEach>
			</table>

			<form class="prev-button-form" action="LoadPrevReviewPage" method="post">
				<input type="hidden" name="pageSize" value="${pageSize}" />
				<input type="submit" class="next-button" value="Prev" />
			</form>
			<form class="next-button-form" action="LoadNextReviewPage" method="post">
				<input type="hidden" name="pageSize" value="${pageSize}" />
				<input type="submit" class="next-button" value="Next" />
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>