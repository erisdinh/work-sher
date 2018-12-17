<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Create Order</title>
<link rel="stylesheet" href="css/main.css" type="text/css">
<style type="text/css">
.body {
	height: 100%;
	width: 80%;
	background-color: white;
	margin-left: auto;
	margin-right: auto;
	padding: 1%;
	border-radius: 15px;
}

td {
	font-family: Arial, sans-serif;
	font-size: 1em;
	color: #393E46;
	margin: 1%;
	padding: 10px;
}

table {
	width: 100%;
}

textarea{
	resize: none; 
	width: 600px; 
	height: 150px;
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="body">
		<form action="ManageOrder" method="post">
			<h1>Create New Order</h1>
			<div>
				<table>
					<tr>
						<td>PostingID:</td>
						<td>${posting.postingId}</td>
					</tr>
					<tr>
						<td>Posted User:</td>
						<td>${posting.username}</td>
					</tr>
					<tr>
						<td>Posting Title:</td>
						<td>${posting.title}</td>
					</tr>
					<tr>
						<td>Category:</td>
						<td>${posting.jobCategory }</td>
					</tr>
					<tr>
						<td>Posting Description:</td>
						<td>${posting.description}</td>
					</tr>
					<tr>
						<td>Compensation:</td>
						<td>${posting.compensation}</td>
					</tr>
					<tr>
						<td>Order Description:</td>
						<td>
							<textarea name="description" placeholder="Enter your order description here..."></textarea>
						</td>
					</tr>
				</table>
			</div>
			</br>
			<center><button class="btn" type="submit" name="action" value="create">Create Order</button></center>
		</form>
	</div>
</body>
</html>