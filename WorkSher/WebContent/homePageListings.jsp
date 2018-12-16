<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
    <%@ page import = "java.util.List" %>
    <%@ page import = "model.User, model.Posting, model.JobCategory, dao.PostingDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<% 
	List <JobCategory> categories = PostingDAO.getAllJobCategories(); 
	request.setAttribute("categories", categories);
	List<Posting> postings = PostingDAO.getHomePageResults();
	for (int i = 0; i < postings.size(); i++) {
		System.out.println(postings.get(i).getTitle());
	}
	request.setAttribute("postings", postings);
%>
<body>

	<div id = "postingsTable">
		<table border = 1>
			<thead>
				<tr>
					<th>Job Category</th>
					<th>Title</th>
					<th>Classmate</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var = "posting" items = "${postings }">
				<tr>
					<td><c:out value = "${posting.jobCategory }"/></td>
					<td><a href="PostingController?action=view&postingId=<c:out value = "${posting.postingId}"/>"><c:out value = "${posting.title}"/></a></td>
					<td><c:out value = "${posting.username }"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id = "postingsDivs">
	<c:forEach var = "posting" items = "${postings }">
		<div><a href="PostingController?action=view&postingId=<c:out value = "${posting.postingId}"/>">"<c:out value = "${posting.title}"/>" from user: <c:out value = "${posting.username }"/></div></a><br>
		</c:forEach>
	</div>
</body>
</html>