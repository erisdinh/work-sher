<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>WorkSher | ${user.name}</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">
.userinfo {
	text-align: center;
}

.userinfo h2 {
	font-size: 2.5em;
	margin: 1%;
	margin-bottom: 0%;
}

p {
	margin: 0%;
}

.postings, .reviews {
	width: 45%;
	margin: 1.5%;
	display: inline-block;
	float: left;
	padding: 1%;	
}

.posting {
	border-left: 5px solid #393E46;
	padding-left: 2%;
}

.postings h2, .reviews h2 {
	text-transform: uppercase;
	font-weight: lighter;
	letter-spacing: 0.1em;
	margin-top: 0%;
}

h3 {
	margin-bottom: 0.5%;
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

.reviews-table {
	border-collapse: collapse;
	width: 100%;
}

tr.rev-rating img {
	width: 80px;
}

.rev-rating td {
	margin-top: 1%;
	margin-bottom: 1%;
	font-weight: bold;
	font-size: 1.25em;
}

.rev-rating p {
	vertical-align: middle;
}

.rev-manage td {
	padding-bottom: 2%;
}

</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<section class="userinfo">
	<h2>${user.name}</h2>
	<p>${user.username} &bull; Member since: ${user.dateJoined}</p>
	<p><a href="mailto:${user.email}">Contact ${user.name}</a>
	<c:if test = "${currentUser.role.equalsIgnoreCase('admin')}">
	 &bull; <a href="ManageAccount?action=admdelete&userId=${user.userid}">Delete User</a>
	</c:if></p>
	</section>
	
	<section class="postings">
	<h2>Postings</h2>
	<c:forEach items="${postings}" var="posting">
		<div class="posting">
		<h3><a href="PostingController?action=view&postingId=${posting.postingId}">${posting.title}</a></h3>
		<p>${posting.description}</p>
		<p class = "compensation">Compensation: <b>${posting.compensation}</b></p>
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
						<tr class = "rev-rating">
							<td><p><img src="images/${review.reviewImgUrl}"/> ${review.reviewRating}</p></td>
						</tr>
						<tr class = "rev-from">
							<td><a href="LoadProfile?userId=${review.fromUserId}"><c:out value="${review.fromUsername}" /></a> on <c:out value="${review.reviewDate}" /></td>							
						</tr>
						<tr class = "rev-body">
							<td><c:out value="${review.reviewText}" /></td>
						</tr>
						<tr class = "rev-manage">
						<td>
							<c:if test="${review.fromUserId == currentUser.userid}">
							<a href="ReviewController?action=edit&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=profile&forUserId=${review.forUserId}" class="btn">Edit</a>			
							</c:if>
							<c:if test="${review.fromUserId == currentUser.userid || currentUser.role.equals('admin')}">
							<a href="ReviewController?action=delete&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=profile&forUserId=${review.forUserId}" class="btn">Delete</a>
							</c:if>
						</td>
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