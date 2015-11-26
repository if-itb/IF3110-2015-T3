<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authenticate</title>
    <link rel="stylesheet" href="styles/main.css">
    <link href='https://fonts.googleapis.com/css?family=Josefin+Slab:400,700italic,300' rel='stylesheet' type='text/css'>
</head>
<body>
<div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>
    <div class="container clearfix">
	<p class="err"><c:out value="${errorMessage}" /></p>
        <h3 class="text-mid">You need 
            <a href="login.jsp"><span class="login">Login</span></a>
                          or 
            <a href="register.jsp"><span class="register">Register</span></a>
    </h3>

    </div>
</body>
</html>

