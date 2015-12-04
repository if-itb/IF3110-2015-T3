<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Login Page</title>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
		<a class="homelink" href="http://mystackexchange.dev"><h1 id="title">My StackExchange</h1></a>
		<div class="content">
			<h2>Login</h2>
			<hr>
			<form action="/login", method="post" onsubmit="">
			<input class="textbox" type="text", name="email", id="email" placeholder="Email">
			<br>
			<input class="textbox" type="password", name="password", id="password" placeholder="Password">
			<br>
			<input type="submit" id="post"  value="Post">
			</form>
		</div>	
	</div>
	<%@page import= "java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service" %>
	<%/*@page import= "com.yangnormal.is.login"*/ %>
		
	<% 
	%>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>