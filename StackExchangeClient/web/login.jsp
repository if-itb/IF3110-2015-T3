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
                <h2>Log In Now!</h2> 
            </div>
		
            <div class="child-content">
                <c:choose>
                    
                <c:when test="${error != null}">
                    <br>
                    <p class="bg-warning">${error}</p>
                </c:when>
                </c:choose>
                <form name="LoginForm" action="/StackExchangeClient/Login" method="POST"> 
                    <input type="text" name="email" placeholder="Email"> <br>
                    <input type="password" name="password" placeholder="Password"> <br>
                    <input type="submit" value="Log In" style="height:50px">
                </form>
                
            </div>

	   </div>
    </div>
</div>
</body>
</html>