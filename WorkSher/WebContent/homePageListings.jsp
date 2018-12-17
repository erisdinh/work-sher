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
	<div id = "posting-nav-menu">
		<c:forEach var = "category" items = "${categories }">
			<p><a href="PostingController?action=catSearch&jobCategory=<c:out value ="${category.jobCategoryId }"/>" class = "posting-nav-menu-items"><c:out value ="${category.jobCategoryDesc } "   /></a></p>
			<input hidden name = "action" value = "advsearch">
		</c:forEach>
	</div>
	
		<c:forEach items="${postings}" var="posting">
		<div class="posting">
		<h3><a href="PostingController?action=view&postingId=${posting.postingId}">${posting.title}</a></h3>
		<p>${posting.username }</p>
		<p>${posting.description}</p>
		<p class = "compensation">Compensation: <b>${posting.compensation}</b></p>
		</div>
	</c:forEach>
</body>
</html>