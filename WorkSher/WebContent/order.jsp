<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order</title>
</head>
<body>
	<h1>Order Information:</h1>
	<table border=1>
		<tr>
			<td>PostingID:</td>
			<td>${posting.postingid}</td>
		</tr>
		<tr>
			<td>Posted UserID:</td>
			<td>${posting.user.userid}</td>
		</tr>
		<tr>
			<td>Posted Username:</td>
			<td>${posting.user.username}</td>
		</tr>
		<tr>
			<td>Posted User Full Name:</td>
			<td>${posting.user.name}</td>
		</tr>
	</table>
</body>
</html>