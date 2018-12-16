<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Settings</title>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<form action="ManageAccount" method="POST">
	<span class="label">Username:</span> ${currentUser.username}<br>
	<label for="name">Name: </label><input type="text" id="name" name="name" placeholder="${currentUser.name}"><br>
	<label for="email">Email: </label><input type="text" id="email" name="email" placeholder="${currentUser.email}"><br>
	<label for="newpassword">New Password: </label><input type="password" id="newpassword" name="newpassword"><br>
	<label for="newpassword2">Re-Enter New Password: </label><input type="password" id="newpassword2" name="newpassword2"><br>
	<label for="currpassword">Current Password: </label><input type="password" id="currpassword" name="currpassword" required><br>
	<input type="submit" id="submit" value="Save Changes" disabled>
	</form>
	<a href="ManageAccount?action=delete">Delete Account</a>
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