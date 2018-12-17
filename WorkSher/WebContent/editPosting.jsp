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
<title>WorkSher | 
<c:choose>
<c:when test = "${posting.status != null }">
Update Posting
</c:when>
<c:otherwise>
Create Posting
</c:otherwise>
</c:choose>
</title>
</head>

<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">


section {

	background-color: white;

	padding: 1%;
	border-radius: 15px;
}
textarea {

}
#fix {
margin:0;
}
#center-posting {
	text-align:center;
}
form {
	width: 60%;
	margin-left: auto;
	margin-right: auto;
}
h3 {
	text-align:center;
}

label {
margin:0;
	width: 30%;
}

.posting-input {
	width: 57%;
	margin:0;
	
}

#posting-submit {
	display: block;
	width: 100%;
	margin-left: auto;
	margin-right: auto;
}
#posting-submit:hover {
	background-color: #FC3C3C;
	color: white;
}

</style>
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
	<section>
	<form action = "PostingController" method = "POST" enctype ="multipart/form-data" >

		<input hidden name = "postingId" value = "<c:out value = "${posting.postingId }"/>"/>
		<c:choose>
		<c:when test = "${posting.status != null }">
		<h2>Edit Posting</h2>
		
		<label for ="dateCreated">Date Posted: </label><input class = "posting-input" readonly name = "dateCreated" value = "<c:out value = "${posting.dateCreated }"/>"/></br>
		<label for ="dateUpdated">Latest Date Updated: </label><input readonly value = "<c:out value = "${posting.dateUpdated }"/>"/></br>
		</c:when>
		<c:otherwise>
		<div id = "center-posting">
			<h2>Create a Posting</h2>
			<p>Share your talents with your fellow classmates!</p>
		</div>
		</c:otherwise>
		</c:choose>
		<label for ="username">User: </label><input readonly name = "username" value = "<c:out value = "${currentUser.username }"/>"/>
		<input hidden name="dateUpdated" value = "<fmt:formatDate pattern = "dd/MM/yyyy" value = "${dateUpdated }"/>"></br>
		<label for ="jobCategory">Category: </label><% System.out.println(categories.get(0).getJobCategoryDesc()); %>
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
		<label for ="title">Title: </label><input class = "posting-input" type = "text" required name = "title" value = "<c:out value = "${posting.title }"/>"/></br>
		<div>
		<label for ="description">Description: </label>
 		<textarea name = "description" required rows = "8" cols = "40"><c:out value = "${posting.description }"/></textarea></br>
		</div>
		<p><label for ="compensation">Compensation: </label><input class = "posting-input" id = "fix" type = "text" name = "compensation" value = "<c:out value = "${posting.compensation }"/>"/></p>
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
		<c:if test = "${posting.status == null }">
			<input name = "status" value = "active" hidden>
		</c:if>
		</br>
		<fieldset>
		<legend>Upload an image!</legend>
			<input type = "file" name = "portfolio" accept = "image/*">
		</fieldset>
		</br>
		<input type = "submit" value = "Sher!">
	</form>
	</section>
</body>
</html>