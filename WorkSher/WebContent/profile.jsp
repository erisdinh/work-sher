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
	<h2>Reviews for ${user.name}</h2>	
	<c:forEach items="${reviews}" var="review">
		<div class="review">
		<p>Rating: ${review.reviewRating}<br>
		<p>${review.reviewText}</p>
		<p>${review.reviewDate}</p>
		</div>
	</c:forEach>
	</section>
</body>
</html>