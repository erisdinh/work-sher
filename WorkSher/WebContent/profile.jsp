<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${user.name}'s Profile</title>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<section class="userinfo">
	<h1>${user.name}'s Profile</h1>
	${user.username}<br>
	<a href="mailto:${user.email}">Contact ${user.name}</a><br>
	Member since: ${user.dateJoined}
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
	<c:choose>
		<c:when test="${reviews.isEmpty()}">
			${currentUser.username} does not have any reviews yet. :(
		</c:when>
		<c:otherwise>
			<c:set value="10" var="pageSize" scope="request" />
			<c:set value="${reviews}" var="reviews" scope="request" />
			<c:if test="${revStartIndex == null}">
				<c:set value="0" var="revStartIndex" scope="request" />
			</c:if>
			<c:if test="${revEndIndex == null}">
				<c:set value="5" var="revEndIndex" scope="request" />
			</c:if>
			
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
				<input type="submit" class="next-button" value="Prev" />
			</form>
			<form class="next-button-form" action="LoadNextReviewPage" method="post">
				<input type="submit" class="next-button" value="Next" />
			</form>
		</c:otherwise>
	</c:choose>
	</section>
</body>
</html>