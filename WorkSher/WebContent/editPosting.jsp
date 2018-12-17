<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
    <%@ page import = "model.JobCategory, dao.PostingDAO" %>
    <%@ page import = "java.util.List, java.util.Calendar, java.util.ArrayList, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% List <JobCategory> categories = PostingDAO.getAllJobCategories(); 
	request.setAttribute("categories", categories);
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Date today = Calendar.getInstance().getTime();
	String ddd = df.format(today);
	request.setAttribute("dateUpdated", today);
%>
<body>

	<jsp:include page="nav.jsp"></jsp:include>
	<form action = "PostingController" method = "POST" enctype ="multipart/form-data" >
		<input hidden name = "postingId" value = "<c:out value = "${posting.postingId }"/>"/>
		Date Posted: <input readonly name = "dateCreated" value = "<c:out value = "${posting.dateCreated }"/>"/></br>
		Latest Date Updated: <input readonly value = "<c:out value = "${posting.dateUpdated }"/>"/></br>
		User: <input readonly name = "username" value = "<c:out value = "${currentUser.username }"/>"/>
		<input hidden name="dateUpdated" value = "<fmt:formatDate pattern = "dd/MM/yyyy" value = "${dateUpdated }"/>"></br>
		Category: <% System.out.println(categories.get(0).getJobCategoryDesc()); %>
		<select name = "jobCategory" required>
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
		Title: <input type = "text" required name = "title" value = "<c:out value = "${posting.title }"/>"/></br>
		Description</br> <textarea name = "description" required rows = "8" cols = "40"><c:out value = "${posting.description }"/></textarea></br>
		Compensation: <input type = "text" name = "compensation" value = "<c:out value = "${posting.compensation }"/>"/></br>
		<c:if test = "${posting.status != null }">
		Status: 
		<select name = "status">
			<c:choose>
				<c:when test = "${posting.status == 'active'}">	
					<option value = "active" selected>Active</option>
					<option value = "inactive">Inactive</option>
				</c:when>
				<c:otherwise>
					<option value = "active">Active</option>
					<option value = "inactive" selected>Inactive</option>
				</c:otherwise>
			</c:choose>
		</select>
		</c:if>
		</br>
		<fieldset>
		<legend>Upload an image!</legend>
			<input type = "file" name = "portfolio" accept = "image/*">
		</fieldset>
		</br>
		<input type = "submit" value = "Sher!">
	</form>
</body>
</html>