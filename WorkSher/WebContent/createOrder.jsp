<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Order</title>
</head>
<body>
	<form action="CreateOrder" id="newOrder" method="post">
		<h1>Create New Order</h1>
		</br>
		<div>
			<table id="postingInformation">
				<tr>
					<td>PostingID:</td>
					<td>0000000001</td>
				</tr>
				<tr>
					<td>Posted User:</td>
					<td>UserName</td>
				</tr>
				<tr>
					<td>Category:</td>
					<td>Design</td>
				</tr>
				<tr>
					<td>Posting Description:</td>
					<td>I can design posters.</td>
				</tr>
				<tr>
					<td>Compensation:</td>
					<td>2 cups of coffee.</td>
				</tr>
			</table>
		</div>
		</br>
		<div>
			<table id="newOrderInformation">
				<tr>
					<td>Requested User:</td>
					<td>UserName</td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><textarea name="description" form="newOrder">Enter your order description here...</textarea></td>
				</tr>
			</table>
		</div>
		</br>
		<input type="submit" value="Create Order"/>
	</form>
</body>
</html>