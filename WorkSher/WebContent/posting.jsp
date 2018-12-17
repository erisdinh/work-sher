<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date, java.util.List, java.util.ArrayList"%>
<%@ page
	import="model.JobCategory, model.Posting, dao.UserDAO, dao.PostingDAO, model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | ${posting.title}</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">
.label {
	font-weight: bold;
	width: 25%;
	margin: 0%;
}

div.posting-details {
	display: inline-block;
	float: left;
	width: 45%;
	margin-left: 5%;
}

.posting-details p {
	margin: 0%;
	padding: 0.5%;
}

.posting-details h2, .reviews h2 {
	text-align: center;
	margin-bottom: 0%;
}

.posting-details img {
	display: block;
	width: 75%;
	margin-left: auto;
	margin-right: auto;
}

#portfolio {
	font-size: 1.5em;
	text-transform: uppercase;
	width: 100%;
	text-align: center;
	letter-spacing: 0.1em;
	margin-bottom: 0%;
	padding-top: 0.5%;
	padding-bottom: 0.5%;
}

.reviews-table {
	border-collapse: collapse;
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
	margin-bottom: 0.5%;
}

.rev-manage td {
	padding-bottom: 2%;
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
	<jsp:include page="nav.jsp"></jsp:include>
		<c:if test = "${posting.status == 'inactive'}">
		This is an inactive post and you are unable to place orders for it<br>
		</c:if>
		<div class="posting-details">
		<h2>${posting.title}</h2>
		<span class="label">User:</span> <p><a href="LoadProfile?userId=<c:out value = "${posting.userId }"/>">${posting.username}</a></p>
		<span class="label">Category:</span> <p>${posting.jobCategory}</p>
		<span class="label">Description:</span> <p>${posting.description }</p>
		<span class="label">Compensation:</span> <p>${posting.compensation}</p>
		<p id="portfolio">Portfolio</p>
		<c:choose>
			<c:when test = "${posting.portfolioType != null}">
				<img src= "${pageContext.request.contextPath}/ImageServlet?ps=${ posting.postingId}" style = "width:400px">				
			</c:when>
			<c:otherwise>
				<img src = "images/portofolioPlaceholder.jpg">
			</c:otherwise>
		</c:choose>
		</br>
		<c:if test = "${currentUser.userid == posting.userId}">
			<form action = "editPosting.jsp"><center><input type = "submit" value = "Edit Your Post"></center></form>
		</c:if>
		<c:if test="${posting.status == 'active' }">
    	   	<c:if test="${currentUser.userid!=posting.userId}">
      	      <center><a href="createOrder.jsp"><button class="btn">Create New Order</button></a></center>
        	</c:if>
   		</c:if>
   		</div>
   		
   		<section class="reviews">
		<h2>Reviews</h2>
		<c:choose>
		<c:when test="${reviews.isEmpty()}">
		This posting does not have any reviews yet! :(
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
							<a href="ReviewController?action=edit&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=posting&forUserId=${review.forUserId}" class="btn">Edit</a>			
							</c:if>
							<c:if test="${review.fromUserId == currentUser.userid || currentUser.role.equals('admin')}">
							<a href="ReviewController?action=delete&reviewId=${review.reviewId}&fromUserId=${review.fromUserId}&referrer=posting&forUserId=${review.forUserId}" class="btn">Delete</a>
							</c:if>
						</td>
						</tr>
					</c:forEach>
				</table>
				<c:if test="${numReviews > 5}">
					<a href="ReviewController?action=load&referrer=posting&postingId=${posting.postingId}">See More Reviews</a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>
