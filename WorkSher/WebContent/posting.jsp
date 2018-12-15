<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import = "java.util.Date, java.util.List, java.util.ArrayList" %>
    <%@ page import = "model.JobCategory, model.Posting, dao.UserDAO, dao.PostingDAO, model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%User postingUser = UserDAO.getUserById(  ((Posting)request.getAttribute("posting")).getUserId()    ); 
request.setAttribute("postingUser", postingUser);
%>
<body>
	<script>
		$('input[name=dob]').datepicker();
	</script>
		User: ${postingUser.username}</br>
		Title: ${posting.title }</br>
		Category: ${posting.jobCategory}</br>
		Description: ${posting.description }</br>
		Compensation: ${posting.compensation}</br>
		<!-- <img src= "${pageContext.request.contextPath}/ImageServlet?ps=${ posting.postingId}">-->
<!-- 	<
		<select name = "jobCategory">
			<c:forEach var = "category" items = "${categories }">
				<% System.out.println("test"); %>
				<option value = "${category.jobCategoryId }">${category.jobCategoryDesc }</option>"
			</c:forEach>
		</select>
		</br>
		Title: <input type = "text" name = "title" value = "<c:out value = ${posting.title }"></br>
		Description</br> <textarea name = "description" rows = "8" cols = "40"></textarea></br>
		Compensation: <input type = "text" name = "compensation"></br>
		<input hidden name = "status" value = "active">
		
<!--		User ID: <input type = "text" readonly = "readonly" name = "userid" value = "<c:out value = "${user.userid}"/>"/></br>
		First Name: <input type = "text" name = "firstName" value = "<c:out value = "${user.firstName }"/>"/></br>
		Last Name: <input type = "text" name = "lastName" value = "<c:out value = "${user.lastName }"/>"/></br>
		DOB: <input type = "text" name = "dob" value = "<fmt:formatDate pattern = "dd/MM/yyyy" value = "${user.dob }"/>"/>(dd/MM/yyyy)</br>
		Email: <input type = "text" name = "email" value = "<c:out value = "${user.email }"/>"/></br>
		<input type = "submit" value = "Submit"/>
	</form>-->
	<!-- a button to create order for the current posting -->
	<a href="User/createOrder.jsp" ><button>Create New Order</button></a>
</body>
</html>