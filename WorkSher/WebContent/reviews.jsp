<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="nav.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:if test="${forUserId != null}">
		User Reviews for ${reviews[0].forUsername}	
	</c:if> <c:if test="${fromUserId != null}">
		User Reviews from ${currentUser.username}
	</c:if></title>
</head>
<body>
<h1>Reviews You've Left</h1>
	<c:choose>
		<c:when test="${reviews.isEmpty()}">
		You have not left any reviews!
		</c:when>
		<c:otherwise>
			<table class="reviews-table">
				<c:forEach var="review" items="${reviews}" varStatus="status">
					<tr>
						<td>
						<c:if test="${forUserId != null}">
							From: <a href="LoadProfile?userId=${review.fromUserId}"><c:out value="${review.fromUsername}" /></a>
						</c:if>
						<c:if test="${fromUserId != null}">
							Review For: <a href="LoadProfile?userId=${review.forUserId}"><c:out value="${review.forUsername}" /></a>
						</c:if>
						</td>
						<td><img src="images/${review.reviewImgUrl}" width="50px" /></td>
						<td><c:out value="${review.reviewDate}" /></td>
							<c:if test="${review.fromUserId == currentUser.userid}">
							<td>
								<a href="ReviewController?action=edit&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=${referrer}&forUserId=${review.forUserId}">Edit</a>
							</td>
							</c:if>
							<c:if test="${review.fromUserId == currentUser.userid || currentUser.role.equals('admin')}">
							<td>
								<a href="ReviewController?action=delete&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=${referrer}&forUserId=${review.forUserId}">Delete</a>
							</td>
							</c:if>
					</tr>
					<tr>
						<td><c:out value="${review.reviewText}" /></td>
					</tr>
				</c:forEach>
			</table>

			<form class="prev-button-form" action="ReviewController" method="get">
				<input type="hidden" name="action" value="prev" />
				<input type="hidden" name="referrer" value="${referrer}" />
				<c:if test="${forUserId != null}">
					<input type="hidden" name="forUserId" value="${forUserId}" />
				</c:if>
				<c:if test="${fromUserId != null}">
					<input type="hidden" name="fromUserId" value="${fromUserId}" />
				</c:if>
				<c:if test="${orderId != null}">
					<input type="hidden" name="orderId" value="${orderId}" />
				</c:if>
				<c:if test="${postingId != null}">
					<input type="hidden" name="postingId" value="${postingId}" />
				</c:if>
				<input type="hidden" name="pageNum" value="${pageNum}" />
				<input type="submit" class="next-button" value="Prev" />
			</form>
			<form class="next-button-form" action="ReviewController" method="get">
				<input type="hidden" name="action" value="next" />
				<input type="hidden" name="referrer" value="${referrer}" />
				<c:if test="${forUserId != null}">
					<input type="hidden" name="forUserId" value="${forUserId}" />
				</c:if>
				<c:if test="${fromUserId != null}">
					<input type="hidden" name="fromUserId" value="${fromUserId}" />
				</c:if>
				<c:if test="${orderId != null}">
					<input type="hidden" name="orderId" value="${orderId}" />
				</c:if>
				<c:if test="${postingId != null}">
					<input type="hidden" name="postingId" value="${postingId}" />
				</c:if>
				<input type="hidden" name="pageNum" value="${pageNum}" />
				<input type="submit" class="next-button" value="Next" />
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>