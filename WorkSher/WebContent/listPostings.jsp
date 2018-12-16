<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
    <%@ page import = "java.util.List" %>
    <%@ page import = "model.User, model.JobCategory, dao.PostingDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% User user = new User();
	user.setRole("admin");
	request.setAttribute("currentUser", user);

	List <JobCategory> categories = PostingDAO.getAllJobCategories(); 
	request.setAttribute("categories", categories);

%>
<body>

	<jsp:include page="nav.jsp"></jsp:include>
	<div id = "search">
		<form action = "PostingController">
			<input hidden name = "action" value = "search">
			<input type = "text" name = "searchTerm">
			<input type = "submit" value = "submit">
		</form>
	</div>

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
						<td><input type = "submit" value = "search"></td>
					</tr>
				</table>
		</form>
	</div>
	<div id = "results">
		<table border = 1>
			<thead>
				<tr>
					<th>Date Created</th>
					<th>Date Updated</th>
					<th>Job Category</th>
					<th>Title</th>
					<c:if test = "${currentUser.role == 'admin' }">
					<th colspan = 2>Admin</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach var = "posting" items = "${postings }">
				<tr>
					<td><c:out value = "${posting.dateCreated }"/></td>
					<td><c:out value = "${posting.dateUpdated }"/></td>
	
					<td><c:out value = "${posting.jobCategory }"/></td>
					<td><a href="PostingController?action=view&postingId=<c:out value = "${posting.postingId}"/>"><c:out value = "${posting.title}"/></a></td>
					<c:if test = "${(currentUser.role) == 'admin' }">
					<td><a href="PostingController?action=edit&postingId=<c:out value = "${posting.postingId }"/>">Update</a></td>
					<td><a href="PostingController?action=delete&postingId=<c:out value = "${posting.postingId }"/>">Delete</a></td>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
			
		</table>
	</div>
	<p><a href="PostingController?action=insert">Post a job!</a>
</body>
</html>