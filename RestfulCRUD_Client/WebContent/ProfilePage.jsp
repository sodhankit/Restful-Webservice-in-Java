<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4 align="center">${error}</h4>
<br>
<table border=1 cellpadding="5" align="center">
<tr>
<th>First Name</th>
<th>Last Name</th>
<th>Email ID</th>
<th>Edit</th>
<th>Delete</th>
</tr>
<c:forEach var="stud" items="${students}">
<tr>
<td>${stud.fname}</td>
<td>${stud.lname}</td>
<td>${stud.emailid}</td>
<td><a href="updatePage.jsp?sid=${stud.id}">Edit</a></td>
<td><a href="MainServlet?sid=${stud.id}&action=delete">Delete</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>