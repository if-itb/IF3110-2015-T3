<%-- 
    Document   : login
    Created on : Nov 26, 2015, 6:06:56 AM
    Author     : zulvafachrina
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="StyleSheet" href="style.css" type="text/css">
        <title>Stack Exchange</title>
    </head>
    <body>
        <div id="header">
            <h1> <a href ="/Stack_Exchange_Client/QuestionServlet" style="color:#000"> Simple Stack Exchange </a> </h1>
        </div>
    
        <div class = "container">
            <div class="loginbox">
                <h3> Login <hr> </h3>
                    <form method="POST" name="Form" action="http://localhost:8082/Stack_Exchange_IS/Login" onsubmit="return validateFormQuestion()">
                            <input type="text" name="email" id="username" placeholder="Email"/>
                            <br>
                            <input type="password" name="password" id="password" placeholder="Password"/>
                            <br> 
                            <input type="submit" id="submit_login" name="submit_login" value="Login"/>
                            <br><br>
                            <p style="float:right"> Don't have an account? <a href="register.jsp" style="color:#FFA500"> Register </a> </p>
                    </form>
                    <c:if test="${success==0}">
                    <br>
                    <br>
                    <p style="color:#FF0000; text-align:center"><c:out value="Login Error! Wrong username/password!"/><p>
                    </c:if>
            </div>
            </div>
        </div>
    </body>
</html>
