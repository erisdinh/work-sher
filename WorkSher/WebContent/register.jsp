<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
<form action="RegisterUser" method="POST">
	<label for="username">Username: </label><input type="text" id="username" name="username"><br>
	<label for="password">Password: </label><input type="password" id="password" name="password"><br>
	<label for="password2">Re-enter Password: </label><input type="password" id="password2" name="password2"><br>
	<label for="name">Full name: </label><input type="text" id="name" name="name"><br>
	<label for="email">Email: </label><input type="text" id="email" name="email"><br>
	<input type="submit" value="Register">
</form>
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