<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Register</title>
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
	width: 45%;
	margin-left: auto;
	margin-right: auto;
}

label {
	display: block;
	width: 35%;
	float: left;
	clear: left;
}

input[type=text], input[type=password] {
	width: 57%;
}

input[type=submit] {
	display: block;
	width: 30%;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
<h1>WorkSher</h1>
<section>
<p>Please enter your information below to register for the WorkSher service. By joining the WorkSher skills exchange service, you agree to abide by the Policies and Procedures as outlined by Sheridan College.</p>
<form action="RegisterUser" method="POST">
	<label for="username">Username: </label><input type="text" id="username" name="username"><br>
	<label for="password">Password: </label><input type="password" id="password" name="password"><br>
	<label for="password2">Re-enter Password: </label><input type="password" id="password2" name="password2"><br>
	<label for="name">Full name: </label><input type="text" id="name" name="name"><br>
	<label for="email">Email: </label><input type="text" id="email" name="email"><br>
	<input type="submit" value="Register">
</form>
</section>
</body>
<script type="text/javascript">
document.getElementById("password").addEventListener("keyup", checkPass); 
document.getElementById("password2").addEventListener("keyup", checkPass);

function checkPass() {
	var pass = document.getElementById("newpassword").value;
	var pass2 = document.getElementById("newpassword2").value;
	var savebtn = document.getElementById("submit");
	
	if (pass === pass2 && pass.length != 0 && pass2.length != 0){
		savebtn.disabled = false;
	} else {
		savebtn.disabled = true;
	}
}
</script>
</html>