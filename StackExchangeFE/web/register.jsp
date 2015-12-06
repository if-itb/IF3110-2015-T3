<%-- 
    Document   : register
    Created on : Nov 25, 2015, 5:39:49 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/modernizr.custom.86080.js"></script>
    </head>
    <body>
    <ul class="cb-slideshow">
        <li><span>Image 01</span></li>
        <li><span>Image 02</span></li>
        <li><span>Image 03</span></li>
        <li><span>Image 04</span></li>
        <li><span>Image 05</span></li>
        <li><span>Image 06</span></li>
        
    </ul>
       
            <div class="grad"></div>
		<div class="header">
			<div>Stack<span>Exchange</span></div>
                        <br>
                        <div>Register<span>!</span></div>
		</div>
                <div class="login">
                    <form method="POST" action="http://localhost:8080/StackExchangeFE/checkregister.jsp">
                        <input type="text" placeholder="username" name="username"><br>
                        <input type="password" placeholder="password" name="password"><br><br>
                        <input type="text" placeholder="email" name="email"><br>
                        <input type="submit" value="register"><br>
                    </form>
                </div>
            
    </body>
    
</html>