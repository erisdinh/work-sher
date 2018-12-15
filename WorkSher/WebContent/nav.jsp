<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function() {
		var profileMenu = document.getElementById("profile-menu");
		var profileDropdown = document.getElementById("profile-dropdown");
		var ordersMenu = document.getElementById("orders-menu");
		var ordersDropdown = document.getElementById("orders-dropdown");
		
		// Display profile dropdown menu on click
		profileMenu.addEventListener("click", function() {
			if (profileDropdown.style.display == "none") {
				profileDropdown.style.display = "flex";
				ordersDropdown.style.display = "none";
			} else {
				profileDropdown.style.display = "none";
			}
			
		})
		
		// Display orders dropdown menu on click
		ordersMenu.addEventListener("click", function() {
			
			if (ordersDropdown.style.display == "none") {
				ordersDropdown.style.display = "flex";
				profileDropdown.style.display = "none";
			} else {
				ordersDropdown.style.display = "none";
			}
		})
		
		// Close menus if user clicks outside
		window.addEventListener("click", function(e) {
			if (e.target != profileMenu && e.target != ordersMenu) {
				profileDropdown.style.display = "none";
				ordersDropdown.style.display = "none";	
			}
		})
	})
</script>
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
		<a href="" class="click-menu">View Postings</a>
		<div class="drop-menu">
			<div class="menu-title" id="orders-menu">View Orders</div>
			<div class="menu-items" id="orders-dropdown">
				<a href="viewOrders.jsp?initial=true&load=received" class="menu-item">Received Orders</a> 
				<a href="viewOrders.jsp?initial=true&load=requested" class="menu-item">Placed Orders</a>
			</div>
		</div>
		<a href="reviews.jsp" class="click-menu">View Reviews</a>
		<div class="search-bar">
			Search: <input type="text" id="nav-search-field" placeholder="Enter search criteria" />
		</div>
	</div>
</body>
</html>