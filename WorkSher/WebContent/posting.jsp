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
<title>Insert title here</title>
</head>

<body>

	<jsp:include page="nav.jsp"></jsp:include>
		<c:if test = "${posting.status == 'inactive'}">
			This is an inactive post and you are unable to place orders for it</br>
		</c:if>
		User: <a href="LoadProfile?userId=<c:out value = "${posting.userId }"/>">${posting.username}</a></br>
		Title: ${posting.title }</br>
		Category: ${posting.jobCategory}</br>
		Description: ${posting.description }</br>
		Compensation: ${posting.compensation}</br>
		Portfolio</br>
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
			<form action = "editPosting.jsp"><input type = "submit" value = "Edit Your Post"></form>
		</c:if>
		<c:if test="${posting.status == 'active' }">
    	   	<c:if test="${currentUser.userid!=posting.userId}">
      	      <a href="createOrder.jsp"><button>Create New Order</button></a>
        	</c:if>
   		</c:if>
   		
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
