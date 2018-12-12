<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ page import = "model.JobCategory, dao.PostingDAO" %>
    <%@ page import = "java.util.List, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% List <JobCategory> categories = PostingDAO.getAllJobCategories(); 
	request.setAttribute("categories", categories);
%>
<body>
	<form action = "PostingController" method = "POST">
		<input hidden name = "postingId" value = "<c:out value = "${posting.postingId }"/>"/>
		Category: <% System.out.println(categories.get(0).getJobCategoryDesc()); %>
		<select name = "jobCategory">
			<c:forEach var = "category" items = "${categories }">
				<c:choose>
					<c:when test = "${category.jobCategoryId == posting.jobCategory }">
					
						<option selected value = "${category.jobCategoryId }">${category.jobCategoryDesc }</option>
					</c:when>
					<c:otherwise>
						<option value = "${category.jobCategoryId }">${category.jobCategoryDesc }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		</br>
		Title: <input type = "text" name = "title" value = "<c:out value = "${posting.title }"/>"/></br>
		Description</br> <textarea name = "description" rows = "8" cols = "40"><c:out value = "${posting.description }"/></textarea></br>
		Compensation: <input type = "text" name = "compensation" value = "<c:out value = "${posting.compensation }"/>"/></br>
		<c:if test = "${posting.status != null }">
		Status: 
		<select name = "status">
			<option value = "active">Active</option>
			<option value = "inactive">Inactive</option>
		</select>
		</c:if>
		<input type = "submit" value = "Sher!">
	</form>
</body>
</html>