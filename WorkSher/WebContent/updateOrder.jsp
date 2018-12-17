<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | Update Order | ${currentUser.username}</title>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<form action="ManageOrder" method="post">
		<h1>Update Order</h1>
		<table>
			<tr>
				<td>OrderID:</td>
				<td>${order.orderid}</td>
			</tr>
		</table>
		</br>
		<div>
			<table border=1>
				<tr>
					<td>PostingID:</td>
					<td>${posting.postingId}</td>
				</tr>
				<tr>
					<td>Posted User:</td>
					<td>${posting.username}</td>
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
					<td><textarea style="resize:none;width: 400px;height:150px;" name="description" placeholder="Enter your order description here..."></textarea></td>
				</tr>
			</table>
		</div>
		</br>
		<button type="submit" name="action" value="update">Update Order</button>
	</form>
</body>
</html>