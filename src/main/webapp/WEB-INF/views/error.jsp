<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Error</title>
</head>
<body>
<h1>
	${ex.message }  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
