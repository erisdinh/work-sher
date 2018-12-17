<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Log in</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">
html, body {
	background-color: #393E46;
}

section {
	width: 75%;
	background-color: white;
	margin-left: auto;
	margin-right: auto;
	padding: 1%;
	border-radius: 15px;
}

h1 {
	text-align: center;
	color: white;
	font-size: 3.5em;
	margin-bottom: 1%;
	text-shadow: 2px 2px #00ADB5;
}

form {
	width: 30%;
	margin-left: auto;
	margin-right: auto;
}

label {
	width: 30%;
}

input[type=text], input[type=password] {
	width: 60%;
}

input[type=submit] {
	display: block;
	width: 30%;
	margin-left: auto;
	margin-right: auto;
}

.other {
	text-align: center; 
}
</style>
</head>
<body>
<h1>WorkSher</h1>
<section>
	<p>Sheridan College is proud to present WorkSher, a web-based service for students by students. WorkSher allows you to apply
	practical skills learned in the classroom and begin building your client base before you even graduate. By using the WorkSher
	skills exchange service, you agree to abide by the Policies and Procedures as outlined by Sheridan College. Please enter your
	credentials to log in.</p>
	<form action="Login" method="POST">	
		<label for="username">Username: </label><input type="text" id="username" name="username"><br>
		<label for="password">Password: </label><input type="password" id="password" name="password"><br>
		<input type="submit" value="Login">
	</form>
	<p class="other">
	<span class = "feedback">${feedback}</span>
	<a href="register.jsp">Register New Account</a>
	</p>
</section>
</body>
</html>