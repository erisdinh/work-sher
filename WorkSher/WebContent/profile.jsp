<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${user.name}'s Profile</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<section class="userinfo">
	<h1>${user.name}'s Profile</h1>
	${user.username}<br>
	<a href="mailto:${user.email}">Contact ${user.name}</a><br>
	Member since: ${user.dateJoined}<br>
	<c:if test = "${currentUser.role.equalsIgnoreCase('admin')}">
	<a href="ManageAccount?action=admdelete&userId=${user.userid}">Delete User</a>
	</c:if>
	</section>
	
	<section class="postings">
	<h2>Postings</h2>
	<c:forEach items="${postings}" var="posting">
		<div class="posting">
		<h3><a href="PostingController?action=view&postingId=${posting.postingId}">${posting.title}</a></h3>
		<p>${posting.description}</p>
		<p>Compensation: ${posting.compensation}</p>
		</div>
	</c:forEach>
	</section>
	
	<section class="reviews">
		<h2>Reviews</h2>
		<c:choose>
			<c:when test="${reviews.isEmpty()}">
		${user.name} does not have any reviews yet! :(
		</c:when>
			<c:otherwise>
				<table class="reviews-table">
					<c:forEach var="review" items="${reviews}" varStatus="status">
						<tr>
							<td>
								From: <a href="LoadProfile?userId=${review.fromUserId}"><c:out value="${review.fromUsername}" /></a>
								</td>
							<td><img src="images/${review.reviewImgUrl}" width="50px" /></td>
							<td><c:out value="${review.reviewDate}" /></td>
							<c:if test="${review.fromUserId == currentUser.userid}">
							<td>
								<a href="ReviewController?action=edit&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=profile&forUserId=${review.forUserId}">Edit</a>
							</td>
							</c:if>
							<c:if test="${review.fromUserId == currentUser.userid || currentUser.role.equals('admin')}">
							<td>
								<a href="ReviewController?action=delete&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=profile&forUserId=${review.forUserId}">Delete</a>
							</td>
							</c:if>
						</tr>
						<tr>
							<td><c:out value="${review.reviewText}" /></td>
						</tr>
					</c:forEach>
				</table>
				<c:if test="${numReviews > 5}">
					<a href="ReviewController?action=load&forUserId=${user.userid}&referrer=profileReviews">See More Reviews</a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>