<% if((request.getParameter("onlyBody") == null) || (request.getParameter("onlyBody").equals("true"))){ %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<link rel="stylesheet" type="text/css" href="assets/css/main.css">
</head>
<% } %>
<body class="contact notFoundBody">
	<div class="outerNotFound">
		<div class="middleNotFound">
			<center>
				<font size="10pt">404 Not Found</font><br>
				<font size="5pt">Go to <a href="index.jsp">Home</a></font>

			</center>
		</div>
	</div>
</body>
<% if((request.getParameter("onlyBody") == null) || (request.getParameter("onlyBody").equals("true"))){ %>
</html>
<% } %>