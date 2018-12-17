<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="model.User, model.JobCategory, dao.PostingDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkSher | View Orders | ${currentUser.username}</title>
</head>
<body>
	<%
		List<JobCategory> categories = PostingDAO.getAllJobCategories();
		request.setAttribute("categories", categories);
	%>
	<jsp:include page="nav.jsp"></jsp:include>
	<c:choose>
		<c:when test="${param.initial=='true'}">
			<jsp:forward page="/LoadOrders?load=${param.load}" />
		</c:when>
		<c:otherwise>
			<h1>Manage Orders</h1>
			<div>
				<c:if test="${load=='received'}">
					<h1>Received Orders</h1>
				</c:if>
				<c:if test="${load=='placed'}">
					<h1>Placed Orders</h1>
				</c:if>
			</div>
			<div class="search">
				<form action="LoadOrders">
					<select name="searchBy" id="searchBy">
						<option value="jobCategory">Job Category</option>
						<option value="title">Title</option>
						<option value="status">Status</option>
					</select>
					<div class="search" id="jobCategory" style="display: none">
						<select name="category">
							<c:forEach items="${categories}" var="category">
								<option id="category"
									value="${category.jobCategoryId}">${category.jobCategoryDesc}</option>
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
					<input type="submit" value="Go" />
				</form>
			</div>
			<script>
				var searchBy = document.getElementById("searchBy");
				var jobCategory = document.getElementById("jobCategory");
				var title = document.getElementById("title");
				var statusDiv = document.getElementById("statusDiv");

				searchBy.addEventListener("change", function() {
					var value = searchBy.options[searchBy.selectedIndex].value;
					if (value == "jobCategory") {
						jobCategory.style.display = "inline";
						title.style.display = "none";
						statusDiv.style.display = "none";
					} else if (value == "title") {
						title.style.display = "inline";
						jobCategory.style.display = "none";
						statusDiv.style.display = "none";
					} else if (value == "status") {
						statusDiv.style.display = "inline";
						jobCategory.style.display = "none";
						title.style.display = "none";
					}
				});
			</script>
			<c:if test="${fn:length(orders)!=0}">
				<div>
					<c:forEach items="${orders}" var="order" begin="${(param.page)*5}"
						end="${(param.page)*5+4}" varStatus="status">
						<div>
							<table border=1>
								<tr>
									<td colspan=2><a href="LoadOrder?orderid=${order.orderid}">
											<c:out value="OrderID: ${order.orderid}" />
									</a></td>
								</tr>
								<tr>
									<td><a
										href="PostingController?action=view&postingId=${order.posting.postingId}"><c:out
												value="Posting ID: ${order.posting.postingId}" /></a></td>
									<td><c:out
											value="Job Category: ${order.posting.jobCategory}" /></td>
								</tr>
								<tr>
									<td colspan=2><a
										href="PostingController?action=view&postingId=${order.posting.postingId}"><c:out
												value="Posting Title: ${order.posting.title}" /></a></td>
								</tr>
								<c:if test="${load=='received'}">
									<tr>
										<td colspan=2><c:out
												value="Request User UserName: ${order.requestUser.username}" /></td>
									</tr>
									<tr>
										<td colspan=2><c:out
												value="Request User Full Name: ${order.requestUser.name}" /></td>
									</tr>
								</c:if>
								<c:if test="${load=='requested'}">
									<tr>
										<td colspan=2><c:out
												value="Post UserName: ${order.postUser.username }" /></td>
									</tr>
									<tr>
										<td colspan=2><c:out
												value="Post User Full Name: ${order.postUser.name}" /></td>
									</tr>
								</c:if>
								<tr>
									<td><c:out value="Date: ${order.dateRequested}" /></td>
									<td><c:out value="Order status: ${order.status}" /></td>
								</tr>
								</br>
							</table>
							<c:if test="${status.last=='true' }">
								<span>${status.index+1}/${fn:length(orders)}</span>
							</c:if>
						</div>
					</c:forEach>
				</div>
				<div>
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
		</c:otherwise>
	</c:choose>
</body>
</html>