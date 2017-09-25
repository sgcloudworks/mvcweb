<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<% response.setContentType("text/html"); %>
<h1>
	Hello world!  
</h1>
inside web-inf
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
