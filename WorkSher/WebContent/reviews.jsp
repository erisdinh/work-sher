<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="nav.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | View Reviews</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style>
.reviews-table {
	border-collapse: collapse;
	width: 100%;
}

#reviews {
	width: 80%;
	margin: auto;
}

.next-button {
display: flex;
	margin: 5px;
}

.prev-button {
display: flex;
	margin: 5px;
}

#review-form {
	display: flex;
	flex-direction: row;
	justify-content: center;
	margin: 10px auto 0px;
	padding: 10px;
}

.rev-rating {
	margin-top: 1%;
	margin-bottom: 1%;
	font-weight: bold;
	font-size: 1.25em;
}

tr.rev-rating img {
	width: 80px;
}

a.btn {
	display: inline-block;
	height: 1.25em;
	padding: 1%;
	font-weight: lighter;
}

a.btn:visited, a.btn:hover {
	color: white;
	text-decoration: none;
}
</style>
</head>
<body>
	<div id="reviews">
		<h1>
		<c:choose>
			<c:when test="${referrer.equalsIgnoreCase('posting')}">
			Reviews for <span class="review-criterion">'${reviews[0].postingTitle}'</span>
			</c:when>
			<c:when test="${referrer.equalsIgnoreCase('profileReviews')}">
			Reviews for <span class="review-criterion">${reviews[0].forUsername}</span>
			</c:when>
			<c:otherwise>
			Reviews you've left
		</c:otherwise>
		</c:choose>
	</h1>
		<c:choose>
			<c:when test="${reviews.isEmpty()}">
		You have not left any reviews!
		</c:when>
			<c:otherwise>
				<table class="reviews-table">
					<c:forEach var="review" items="${reviews}" varStatus="status">
						<tr class="rev-rating">
							<td><img src="images/${review.reviewImgUrl}" /> ${review.reviewRating}</td>
						</tr>
						<tr class="rev-user">
							<td><c:if
									test="${forUserId != null || referrer.equalsIgnoreCase('posting')}">
							From: <a href="LoadProfile?userId=${review.fromUserId}"><c:out
											value="${review.fromUsername}" /></a>
								</c:if> <c:if test="${fromUserId != null}">
							Review For: <a href="LoadProfile?userId=${review.forUserId}"><c:out
											value="${review.forUsername}" /></a>
								</c:if> on <c:out value="${review.reviewDate}" /></td>
							<tr class="rev-posting"><c:if
									test="${!referrer.equalsIgnoreCase('posting')}">
									<td>
									Posting: <a href="PostingController?action=view&postingId=${review.postingId}">${review.postingTitle}</a></td>
								</c:if></tr>
						</tr>
						<tr class="rev-body">
							<td><c:out value="${review.reviewText}" /></td>
						</tr>
						<tr class="rev-manage">
							<td><c:if test="${review.fromUserId == currentUser.userid}">
									<a
										href="ReviewController?action=edit&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=${referrer}&forUserId=${review.forUserId}" class="btn">Edit</a>
								</c:if> <c:if
									test="${review.fromUserId == currentUser.userid || currentUser.role.equals('admin')}">
									<a
										href="ReviewController?action=delete&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=${referrer}&forUserId=${review.forUserId}" class="btn">Delete</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>

			<div id="review-form">
				<form class="prev-button-form" action="ReviewController"
					method="get">
					<input type="hidden" name="action" value="prev" /> <input
						type="hidden" name="referrer" value="${referrer}" />
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
					<input type="hidden" name="pageNum" value="${pageNum}" /> <input
						type="submit" class="next-button" value="Prev" />
				</form>

				<form class="next-button-form" action="ReviewController"
					method="get">
					<input type="hidden" name="action" value="next" /> <input
						type="hidden" name="referrer" value="${referrer}" />
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
					<input type="hidden" name="pageNum" value="${pageNum}" /> <input
						type="submit" class="next-button" value="Next" />
				</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>