<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import = "java.util.Date, java.util.List, java.util.ArrayList" %>
    <%@ page import = "model.JobCategory, model.Posting, dao.UserDAO, dao.PostingDAO, model.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	
	<jsp:include page="nav.jsp"></jsp:include>
	<script>
		$('input[name=dob]').datepicker();
	</script>
		User: ${posting.username}</br>
		Title: ${posting.title }</br>
		Category: ${posting.jobCategory}</br>
		Description: ${posting.description }</br>
		Compensation: ${posting.compensation}</br>
		<!-- <img src= "${pageContext.request.contextPath}/ImageServlet?ps=${ posting.postingId}">-->
		Portfolio</br>
		<c:choose>
			<c:when test = "${posting.portfolioType != null}">
				<img src= "${pageContext.request.contextPath}/ImageServlet?ps=${ posting.postingId}" style = "width:400px">
				
			</c:when>
			<c:otherwise>
			hi	
				<img src = "images/portofolioPlaceholder.jpg">
			</c:otherwise>
		</c:choose>
		</br>
	<a href="User/createOrder.jsp" ><button>Create New Order</button></a>
</body>
</html>