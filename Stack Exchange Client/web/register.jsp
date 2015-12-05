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
                <h3> Register <hr> </h3>
                    <form method="POST" name="Form" action="/Stack_Exchange_Client/Register" onsubmit="return validateFormQuestion()">
                            <input type="text" name="username" id="username" placeholder="Name"/>
                            <br>
                            <input type="text" name="email" id="username" placeholder="Email"/>
                            <br>
                            <input type="password" name="password" id="password" placeholder="Password"/>
                            <br> 
                            <input type="submit" id="submit_login" name="submit_login" value="Register"/>
                            
                            
                    </form>
                    <c:if test="${registrasi==0}">
                    <br>
                    <br>
                    <p style="color:#FF0000; text-align:center"><c:out value="Register Error! Email has been used!"/><p>
                    </c:if>
            </div>
            </div>
        </div>
    </body>
</html>
