<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="model.JobCategory, dao.PostingDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | View Orders</title>
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

input[name='title'] {
	border: solid 1px #393E46;
	border-radius: 2px;
	font-family: Arial, sans-serif;
	font-size: 1em;
	color: #393E46;
	margin: 1%;
	width: 300px;
}

select {
	border: solid 1px #393E46;
	border-radius: 2px;
	font-family: Arial, sans-serif;
	font-size: 1em;
	color: #393E46;
	padding: 0.5%;
	margin: 1%;
}

select[name='jobCategory'], select[name='status'] {
	width: 300px;
}

td, th {
	border: 1px solid #ddd;
	padding: 8px;
	height: 35px;
}

th {
	padding-top: 10px;
	padding-bottom: 10px;
	background-color: #00ADB5;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #f5f5f5;
}

table {
	width: 100%;
}

.date-col, .id-col {
	text-align: center;
}

#numOrder {
	font-size: 25px;
	float: right;
	color: #00ADB5;
	margin-right: 8px;
}

#pageButton button {
	background-color: #00ADB5;
	font-family: Arial, sans-serif;
	font-size: 1em;
	color: white;
	height: 2em;
	border: none;
	border-radius: 2px;
}

#pageButton {
	position: absolute;
	right: 10%;
	bottom: 3%;
}
</style>
</head>
<body>
	<%
		List<JobCategory> categories = PostingDAO.getAllJobCategories();
		request.setAttribute("categories", categories);
	%>
	<jsp:include page="nav.jsp"></jsp:include>
	<c:choose>
		<c:when test="${param.initial=='true'}">
			<c:set var="load" scope="session" value="${param.load }" />
			<jsp:forward page="/LoadOrders?load=${load}" />
		</c:when>
		<c:otherwise>
			<div class="body">
				<h1>Manage Orders</h1>

				<c:if test="${load=='received'}">
					<h3>Received Orders</h3>
				</c:if>
				<c:if test="${load=='placed'}">
					<h3>Placed Orders</h3>
				</c:if>

				<c:if test="${orderMessage!=null}">
					<span>${orderMessage}</span>
				</c:if>

				<c:if test="${fn:length(orders)==0}">
					<c:if test="${load=='received'}">
						<span>You do not have any orders!!!</span>
					</c:if>
					<c:if test="${load=='placed'}">
						<span>You do not have any orders!</span>
						</br>
						<span>Check out <a
							href="${pageContext.request.contextPath}/PostingController?action=listPostings">List
								Postings</a>!!!
						</span>
					</c:if>
				</c:if>

				<div class="search">
					<form action="LoadOrders">
						<div id="searchDiv">
							<span>Search by: </span> <select name="searchBy" id="searchBy">
								<option value="search"></option>
								<option value="jobCategory">Job Category</option>
								<option value="title">Title</option>
								<option value="status">Status</option>
							</select>
							<div class="search" id="jobCategory" style="display: none">
								<select name="category">
									<c:forEach items="${categories}" var="category">
										<option id="category" value="${category.jobCategoryId}">${category.jobCategoryDesc}</option>
									</c:forEach>
								</select>
							</div>
							<div class="search" id="title" style="display: none">
								<input type="text" name="title"
									placeholder="Enter posting title here" />
							</div>
							<div class="search" id="statusDiv" style="display: none">
								<select name="status">
									<option value="Pending">Pending</option>
									<option value="Cancelled">Cancelled</option>
									<option value="Approved">Approved</option>
									<option value="Rejected">Rejected</option>
									<option value="Completed">Completed</option>
								</select>
							</div>
							<input style="display: none" type="submit" id="searchButton"
								name="searchButton" value="Go" />
						</div>
					</form>
					<script>
							var searchBy = document.getElementById("searchBy");
							var jobCategory = document
									.getElementById("jobCategory");
							var title = document.getElementById("title");
							var statusDiv = document
									.getElementById("statusDiv");
							var searchButton = document
									.getElementById("searchButton");

							searchBy
									.addEventListener(
											"change",
											function() {
												var value = searchBy.options[searchBy.selectedIndex].value;
												if (value == "jobCategory") {
													jobCategory.style.display = "inline";
													title.style.display = "none";
													statusDiv.style.display = "none";
													searchButton.style.display = "inline";
												} else if (value == "title") {
													title.style.display = "inline";
													jobCategory.style.display = "none";
													statusDiv.style.display = "none";
													searchButton.style.display = "inline";
												} else if (value == "status") {
													statusDiv.style.display = "inline";
													jobCategory.style.display = "none";
													title.style.display = "none";
													searchButton.style.display = "inline";
												} else {
													jobCategory.style.display = "none";
													title.style.display = "none";
													statusDiv.style.display = "none";
													searchButton.style.display = "none";
												}
											});
						</script>
				</div>

				<c:if test="${fn:length(orders)!=0}">
					<div>
						<table>
							<tr>
								<th>Date</th>
								<th>OrderID</th>
								<th>Category</th>
								<th>Posting Title</th>
								<c:if test="${load=='received' || currentUser.role=='admin'}">
									<th>Request User</th>
								</c:if>
								<c:if test="${load=='requested' || currentUser.role=='admin'}">
									<th>Post User</th>
								</c:if>
								<th>Status</th>
							</tr>
							<c:forEach items="${orders}" var="order"
								begin="${(param.page)*5}" end="${(param.page)*5+4}"
								varStatus="status">
								<tr>
									<td class="date-col">${order.dateRequested}</td>
									<td class="id-col"><a
										href="LoadOrder?orderid=${order.orderid}"> <c:out
												value="${order.orderid}" />
									</a></td>
									<td><c:out value="${order.posting.jobCategory}" /></td>
									<td><a
										href="PostingController?action=view&postingId=${order.posting.postingId}"><c:out
												value="${order.posting.title}" /></a></td>
									<c:if test="${load=='received' || currentUser.role=='admin'}">
										<td><c:out value="${order.requestUser.username}" /></td>
									</c:if>
									<c:if test="${load=='requested' || currentUser.role=='admin'}">
										<td><c:out value="${order.postUser.username }" /></td>

									</c:if>
									<td><c:out value="${order.status}" /></td>
								</tr>
								<c:if test="${status.last=='true' }">
									</br>
									</br>
									<span id="numOrder">${status.index+1}/${fn:length(orders)}</span>
									</br>
									</br>
								</c:if>
							</c:forEach>
						</table>
					</div>
					<div id="pageButton">
						<form action="viewOrders.jsp">
							<c:if test="${numberOfOrderPages!= 1}">
								<c:if test="${param.page > 0}">
									<button type="submit" value="${param.page-1}" name="page">Previous</button>
								</c:if>
								<c:forEach begin="0" end="${numberOfOrderPages-1}"
									varStatus="loop">
									<button type="submit" value="${loop.index}" name="page">${loop.count}</button>
								</c:forEach>
								<c:if
									test="${param.page <= (numberOfOrderPages-2) || param.page == null}">
									<button type="submit" value="${param.page+1}" name="page">Next</button>
								</c:if>
							</c:if>
						</form>
					</div>
				</c:if>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>