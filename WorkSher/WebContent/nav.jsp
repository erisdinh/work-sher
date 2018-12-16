<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/nav.css">
</head>

<!--  TODO: Add links to different application functions here -->
<body>
	<div id="nav-bar">
		<div class="drop-menu">
			<div class="menu-title" id="profile-menu">Profile</div>
			<div class="menu-items" id="profile-dropdown">
				<a href="accountsettings.jsp" class="menu-item">Settings</a> 
				<a href="LoadProfile?userId=${currentUser.userid}" class="menu-item">Public Profile</a>
				<a href="" class="menu-item">Logout</a>
			</div>
		</div>
		<div class="drop-menu">
			<div class="menu-title">Postings</div>
			<div class="menu-items">
				<a href="${pageContext.request.contextPath}/PostingController?action=listPostings" class="menu-item">View Postings</a>
			</div>
		</div>
		<div class="drop-menu">
			<div class="menu-title" id="orders-menu">View Orders</div>
			<div class="menu-items" id="orders-dropdown">
				<a href="viewOrders.jsp?initial=true&load=received" class="menu-item">Received Orders</a> 
				<a href="viewOrders.jsp?initial=true&load=requested" class="menu-item">Placed Orders</a>
			</div>
		</div>
		<div class="drop-menu">
			<div class="menu-title">Reviews</div>
			<div class="menu-items">
				<a href="ReviewController?action=load&fromUserId=${currentUser.userid}" class="menu-item">View Reviews</a>
			</div>
		</div>
		<div class="search-bar">
			Search: 		<form action = "PostingController">
			<input hidden name = "action" value = "search">
			<input type = "text" name = "searchTerm" placeholder = "Enter Search Criteria">
			<input type = "submit" value = "submit">
			<br>
			<a href="advancedSearch.jsp">Advanced Search</a>
		</form>
		</div>
	</div>
</body>
</html>