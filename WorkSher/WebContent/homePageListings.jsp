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
<style type="text/css">
h2, section, .categories {
	width: 75%;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
}

h2.categories {
	text-transform: uppercase;
	font-weight: lighter;
	letter-spacing: 0.1em;
	margin-bottom: 0%;
}

table.categories {
	border-spacing: 0.5em;
}

.categories td {	
	height: 2.5em;
	background-color: #00ADB5;
	border-radius: 5px;	
	padding: 1%;
	margin: 2%;
}

.categories a, .categories a:hover, .categories a:visited {
	color: white;
	text-decoration: none;
}

.posting {
	width: 17%;
	text-align: left;
	vertical-align: top;
	margin: 1%;
	display: inline-block;
}

a.posting {
	font-size: 0.9em;
	font-weight: lighter;
}

h3 {
	margin-bottom: 0.5%;
}

.compensation {
	margin-top: 3%;
}
</style>
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
	<h2 class="categories">Explore</h2>
	<table class="categories">
	<c:forEach begin="0" end="3" varStatus="row">
		<tr>
		<c:forEach items="${categories}"
				   var="category"
				   begin="${row.index}"
				   step="4">
			<td><a href="PostingController?action=catSearch&jobCategory=<c:out value ="${category.jobCategoryId }"/>" class = "posting-nav-menu-items"><c:out value ="${category.jobCategoryDesc } "   /></a></td>
		</c:forEach>
		</tr>
	</c:forEach>
	</table>

	<h2 class="categories">Most recent postings</h2>
	<section>
		<c:forEach items="${postings}" var="posting">
		<div class="posting">
		<h3><a href="PostingController?action=view&postingId=${posting.postingId}">${posting.title}</a></h3>
		<p><a href="LoadProfile?userId=${posting.userId}" class="posting">${posting.username}</a></p>
		<p>${posting.description}</p>
		<p class="compensation">Compensation:<br><b>${posting.compensation}</b></p>
		</div>
		</c:forEach>
	</section>
</body>
</html>