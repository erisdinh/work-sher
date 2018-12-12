<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
    <%@ page import = "model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% User user = new User();
	user.setRole("admin");
	request.setAttribute("currentUser", user);
%>
<body>
	<table border = 1>
		<thead>
			<tr>
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
	<p><a href="PostingController?action=insert">Post a job!</a>
</body>
</html>