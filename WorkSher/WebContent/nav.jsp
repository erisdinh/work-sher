<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/nav.css">
</head>

<!--  TODO: Add links to different application functions here -->
<body>
	<h1 id="nav-header"><a href="homepage.jsp">Work<span class="highlight">Sher</span></a></h1>
	<div id="nav-bar">
		<div class="drop-menu">
			<div class="menu-title" id="profile-menu">Profile</div>
			<div class="menu-items" id="profile-dropdown">
				<a href="accountsettings.jsp" class="menu-item">Settings</a> 
				<a href="LoadProfile?userId=${currentUser.userid}" class="menu-item">Public Profile</a>
				<a href="Login?action=logout" class="menu-item">Logout</a>
			</div>
		</div>
		<div class="drop-menu">
			<div class="menu-title">Postings</div>
			<div class="menu-items">
				<a href="${pageContext.request.contextPath}/PostingController?action=listPostings" class="menu-item">View Postings</a>
			</div>
		</div>
		<div class="drop-menu">
			<div class="menu-title" id="orders-menu">Orders</div>
				<div class="menu-items" id="orders-dropdown">
					<c:if test = "${currentUser.role.equalsIgnoreCase('admin')}">
					<a href="viewOrders.jsp?initial=true&load=all" class="menu-item">View All Orders</a>
					</c:if>
					<a href="viewOrders.jsp?initial=true&load=received" class="menu-item">Received Orders</a> 
					<a href="viewOrders.jsp?initial=true&load=requested" class="menu-item">Placed Orders</a>
				</div> 
		</div>
		<div class="drop-menu">
			<div class="menu-title">Reviews</div>
			<div class="menu-items">
				<a href="ReviewController?action=load&fromUserId=${currentUser.userid}&referrer=reviews" class="menu-item">View Reviews</a>
			</div>
		</div>
		<div id="search-bar">
			<form action = "PostingController">
			<input name = "action" value = "search" hidden>
			<input type = "text" name = "searchTerm" placeholder = "Enter Search Criteria">
			<input type = "submit" value = "Search">
			<br>
			<a href="advancedSearch.jsp">Advanced Search</a>
		</form>
		</div>
	</div>
</body>
</html>