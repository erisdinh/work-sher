<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List, dao.PostingDAO, model.JobCategory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
</script>
<title>Insert title here</title>
</head>
<% 

	List <JobCategory> categories = PostingDAO.getAllJobCategories(); 
	request.setAttribute("categories", categories);

%>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
		<div id = "advsearch">
		<h3>Search</h3>
		<form action = "PostingController">
			<input hidden name = "action" value = "advsearch">
				<table>
					<tr>
						<td>
							<select name = "jobCategory">
							<option selected value = "">No category selected</option>
								<c:forEach var = "category" items = "${categories }">
										<option value = "${category.jobCategoryId }">${category.jobCategoryDesc }</option>
								</c:forEach>
							</select>
						</td>
						<td>Title: </td>
						<td><input type = "text" name = "title">
						<td>User: </td>
						<td><input type = "text" name = "user">
						<td>Description: </td>
						<td><input type = "text" name = "description"></td>
					</tr>
					<tr>
						<td>Start Date:</td>
						<td><input type = "date" name = "startDate" min = "2018-12-01 "id = "startDate"></td>
						<td>End Date:</td>
						<td><input type = "date" name = "startDate"max = "2018-12-01" id = "endDate"></td>
						
						<td><input type = "submit" value = "search"></td>
					</tr>
					
				</table>
		</form>
	</div>
</body>
</html>