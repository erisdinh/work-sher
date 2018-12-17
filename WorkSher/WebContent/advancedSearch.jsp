<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List, dao.PostingDAO, model.JobCategory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
	var startDate = document.getElementById("startDate");
	var endDate = document.getElementById("endDate");
	var today = new Date();
	var initialEndDate = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate();
	endDate.value = initialEndDate;
	startDate.max = endDate.value;

	startDate.addEventListener("change", function() {
		endDate.min = startDate.value;
	})
	endDate.addEventListener("change", function() {
		startDate.max = endDate.value;
	})
})
</script>
<title>WorkSher | Advanced Search</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">


section {
	width: 75%;
	background-color: white;
	margin-left: auto;
	margin-right: auto;
	padding: 1%;
	border-radius: 15px;
}


form {
	width: 30%;
	margin-left: auto;
	margin-right: auto;
}

label {
	width: 30%;
}
input[type=text] {
	width: 60%;
}

input[type=submit] {
	display: block;
	width: 30%;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<% 
	List <JobCategory> categories = PostingDAO.getAllJobCategories(); 
	request.setAttribute("categories", categories);
%>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
		<div id = "advsearch">
		<h3>Advanced Search</h3>
		<form action = "PostingController">
			<input hidden name = "action" value = "advsearch">
				<select name = "jobCategory">
					<option selected value = "">No category selected</option>
					<c:forEach var = "category" items = "${categories }">
						<option value = "${category.jobCategoryId }">${category.jobCategoryDesc }</option>
					</c:forEach>
				</select>
				</br>
			<label for ="title">Title: </label><input type = "text" name = "title"></br>
			<label for ="User">User: </label><input type = "text" name = "user"></br>
			<label for ="description">Description: </label><input type = "text" name = "description"></br>
			<label for ="title">Start Date: </label><input type = "date" name = "startDate" min = "2018-12-01" id = "startDate" value = "2018-12-01"></br>
			<label for ="title">End Date: </label><input type = "date" name = "endDate" min = "2018-12-01" id = "endDate" value = "2018-12-02"></br>
			<input type = "submit" value = "search">
		</form>
	</div>
</body>
</html>