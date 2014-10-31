<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User list</title>
</head>
<body>
	<a href="add">ADD</a><br/>---->${loginUser.nickname }<br/>
	<c:forEach items="${users }" var="u">
	${u.value.username }
	----${u.value.password }
	<a href="${u.value.username }">${u.value.nickname }</a>
	----${u.value.email }
	<a href="${u.value.username }/update">Update</a><a href="${u.value.username }/delete">Delete</a><br/>
	</c:forEach>
	
</body>
</html>