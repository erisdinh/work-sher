<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Account Settings</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style>
h2 {
	text-align: center;
}

form {
	width: 70%;
	margin-left: auto;
	margin-right: auto;
}

label, .label {
	width: 30%;
	margin: 0.5%;
}

.username {
	display: inline-block;
	text-align: left;
	padding: 0.5%;
	margin: 0.5%;
}

input[type=text], input[type=password] {
	width: 50%;
	margin: 0.5%;
}

#submit {
	display: block;
	width: 20%;
	margin-left: auto;
	margin-right: auto;
}

#submit:disabled {
	color: #00ADB5;
	background-color: white;
	border: solid 1px #00ADB5;
}

.feedback {
	color: #FC3C3C;
	font-size: 0.75em;
	font-style: italic;
	display: block;
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<h2>Account Settings for ${currentUser.name}</h2>
	<p class = "feedback">${feedback}</p>
	<form action="ManageAccount" method="POST">
	<span class="label">Username:</span> <p class ="username">${currentUser.username}</p><br>
	<label for="name">Name: </label><input type="text" id="name" name="name" placeholder="${currentUser.name}"><br>
	<label for="email">Email: </label><input type="text" id="email" name="email" placeholder="${currentUser.email}"><br>
	<label for="newpassword">New Password: </label><input type="password" id="newpassword" name="newpassword"><br>
	<label for="newpassword2">Re-Enter New Password: </label><input type="password" id="newpassword2" name="newpassword2"><br>
	<label for="currpassword">Current Password: </label><input type="password" id="currpassword" name="currpassword" required><br>
	<input type="submit" id="submit" value="Save Changes" disabled>
	<center><a href="ManageAccount?action=delete">Delete Account</a></center>
	</form>	
</body>
<script type="text/javascript">
document.getElementById("newpassword").addEventListener("keyup", checkPass); 
document.getElementById("newpassword2").addEventListener("keyup", checkPass);

function checkPass() {
	var newpass = document.getElementById("newpassword").value;
	var newpass2 = document.getElementById("newpassword2").value;
	var savebtn = document.getElementById("submit");

	if (newpass === newpass2 && newpass.length != 0 && newpass2.length != 0){
		savebtn.disabled = false;
	} else {
		savebtn.disabled = true;
	}
}
</script>
</html>