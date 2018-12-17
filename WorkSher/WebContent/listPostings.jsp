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
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">
#postings-parent {
	width:100%;
}
#posting-nav-menu {
	width:20%;
}
#postings-list {

	float:right;
	width:80%;
}

p {
	margin:0;
}
#post-a-job {

	text-decoration: none;
	font-weight: lighter;
	width:100%;
	line-height: 1.5em;
	text-align: center;
	padding: 1%;
	background-color: #393E46;
	
}
.date-col {
	text-align:center;
}
a.posting-nav-menu-items {
	text-decoration: none;
	font-weight: lighter;
	display: block;
	line-height: 1.5em;
	text-align: center;
	padding: 1%;
	color: #393E46;
	background-color: #ddd;
}
.posting-nav-menu-items:hover {

	cursor: pointer;
	background-color: #00ADB5;
}
table {
	width:100%;
}

</style>
</head>
<% 
	List <JobCategory> categories = PostingDAO.getAllJobCategories(); 
	request.setAttribute("categories", categories);
%>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<div id = "postings-parent">
	<div id = "posting-nav-menu">
		<p><a href="${pageContext.request.contextPath}/PostingController?action=listPostings" class = "posting-nav-menu-items">All Categories</a></p>
		<c:forEach var = "category" items = "${categories }">
			<p><a href="PostingController?action=catSearch&jobCategory=<c:out value ="${category.jobCategoryId }"/>" class = "posting-nav-menu-items"><c:out value ="${category.jobCategoryDesc } "   /></a></p>
			<input hidden name = "action" value = "advsearch">
		</c:forEach>
	</div>
	
	<div id = "postings-list">
	<p id = "post-a-job"><a href="PostingController?action=insert">Post a job!</a>
		<table>
			<thead>
				<tr>
					<th>Date Created</th>
					<th>Date Updated</th>
					<th>Job Category</th>
					<th>Title</th>
					<c:choose>
					<c:when test = "${currentUser.role == 'admin'}">
					<th colspan = 3>Management</th>
					</c:when>
					<c:otherwise>
					<th colspan = 2>Management</th>
					</c:otherwise>
					</c:choose>
				</tr>
			</thead>
			<tbody>
				<c:forEach var = "posting" items = "${postings }">
				<tr>
					<td class = "date-col"><c:out value = "${posting.dateCreated }"/></td>
					<td class = "date-col"><c:out value = "${posting.dateUpdated }"/></td>
					<td><c:out value = "${posting.jobCategory }"/></td>
					<td><a href="PostingController?action=view&postingId=<c:out value = "${posting.postingId}"/>"><c:out value = "${posting.title}"/></a></td>
					<c:if test = "${(currentUser.role) == 'admin' || currentUser.userid == posting.userId}">
						<td><a href="PostingController?action=edit&postingId=<c:out value = "${posting.postingId }"/>">Update</a></td>
						<c:choose>
							<c:when test = "${posting.status == 'inactive' }">
								<td><a href="PostingController?action=activate&postingId=<c:out value = "${posting.postingId }"/>">Activate</a></td>
							</c:when>
							<c:otherwise>
								<td><a href="PostingController?action=deactivate&postingId=<c:out value = "${posting.postingId }"/>">Deactivate</a></td>
							</c:otherwise>
						</c:choose>
						<c:if test = "${(currentUser.role) == 'admin' }">
							<td><a href="PostingController?action=delete&postingId=<c:out value = "${posting.postingId }"/>">Delete</a></td>
						</c:if>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>	
</body>
</html>