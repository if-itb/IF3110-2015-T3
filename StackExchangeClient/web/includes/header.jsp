<%-- 
    Document   : header
    Created on : Nov 27, 2015, 5:05:51 PM
    Author     : Ahmad Naufal Farhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>StackExchange!</title>
        <style>
            <%@include file="style.css"%>
            <%@include file="style2.css"%>
        </style>
    </head>
    <body>
        <script src="app.js" type="text/javascript"></script>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
              <div class="navbar-header">
                <a class="navbar-brand" href="/StackExchangeClient/home">StackExchange!</a>
              </div>
                
              <div id="navbar" class="navbar-collapse collapse pull-right">
                <ul class="nav navbar-nav">
                  <c:choose>
                    <c:when test="${name == null}">
                        <li><a href="/StackExchangeClient/loginform">Login</a></li>
                        <li><a href="/StackExchangeClient/register.jsp">Register</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>Welcome, ${name}</li>
                        <li><a href="/StackExchangeClient/Logout">Logout</a></li>
                    </c:otherwise>
                  </c:choose>
                </ul>
              </div><!--/.nav-collapse -->
            </div>
        </nav>
