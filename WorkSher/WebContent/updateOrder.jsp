<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Update Order</title>
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

.btn {
	position: absolute;
	left: 45%;
	right: 45%;
	bottom: 5%;
}

table {
	width: 100%;
}

textarea {
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
			<h1>Update Order</h1>
			<table>
				<tr>
					<td>OrderID:</td>
					<td>${order.orderid}</td>
				</tr>
				<tr>
					<td>PostingID:</td>
					<td>${order.posting.postingId}</td>
				</tr>
				<tr>
					<td>Posted User:</td>
					<td>${order.posting.username}</td>
				</tr>
				<tr>
					<td>Category:</td>
					<td>${order.posting.jobCategory }</td>
				</tr>
				<tr>
					<td>Posting Description:</td>
					<td>${order.posting.description}</td>
				</tr>
				<tr>
					<td>Compensation:</td>
					<td>${order.posting.compensation}</td>
				</tr>
				<tr>
					<td>Order Description:</td>
					<td><textarea
							style="resize: none; width: 400px; height: 150px;"
							name="description" placeholder="${order.description}"></textarea></td>
				</tr>
			</table>
			</br>
			<button class="btn" type="submit" name="action" value="skip">Cancel</button>
			<button class="btn" type="submit" name="action" value="update">Update Order</button>
		</form>
	</div>
</body>
</html>