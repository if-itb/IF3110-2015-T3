<%-- 
    Document   : header
    Created on : 09-Nov-2015, 17:12:15
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" ng-app="stackexchange">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Simple StackExchange</title>

    <!-- Main style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <!-- Google fonts -->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:800italic,300,400,400italic,600,700' rel='stylesheet' type='text/css'>
</head>
<body>
    <%
        Cookie[] cookies = null;
        cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("access_token")) {
                    token = cookie.getValue();
                }
            }
        }
    %>
    <%-- start web service invocation --%>
    <%
    UserWS.User user = null;
    try {
	UserWS.UserWS_Service service = new UserWS.UserWS_Service();
	UserWS.UserWS port = service.getUserWSPort();
	
	user = port.getUserByToken(token);
	
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%>




<div class="outer-container">
    <div class="inner-container">
        <ul class="account-menu">
            <% if(user != null) { %>
                <li>Welcome <%= user.getName() %>!</li>
                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
            <% } else { %>
                <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
                <li><a href="${pageContext.request.contextPath}/login">Log in</a></li>
            <% } %>
        </ul>

    </div>
    <header class="main-title">
        <h1><a href="${pageContext.request.contextPath}">Stack<span>Exchange</span></a></h1>
    </header>