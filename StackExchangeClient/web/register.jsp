<%-- 
    Document   : register
    Created on : Nov 24, 2015, 12:02:31 PM
    Author     : mochamadtry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="includes/header.jsp" %>

<div class="container">
    <div id="header">
        <a href="/StackExchangeClient/home"><h1>StackExchange</h1></a>
    </div>

    <div class="main">
        <div class="wrapper" id="question-form">
            <div class="content-header">
                <h2>Register Form</h2> 
            </div>
		
            <div class="child-content">
                <form name="RegisterForm" action="/StackExchangeClient/registers" method="POST"> 
                    <input type="text" name="name" placeholder="Name"> <br>
                    <input type="text" name="email" placeholder="Email"> <br>
                    <input type="password" name="password" placeholder="Password"> <br>
                    <input type="submit" value="Sign Up" style="height:50px">

                </form>
            </div>

	   </div>
    </div>
</div>
</body>
</html>