<%-- 
    Document   : header
    Created on : Nov 27, 2015, 6:37:12 PM
    Author     : ASUS X202E
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExchange</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href='https://fonts.googleapis.com/css?family=Dosis:500' rel='stylesheet' type='text/css'>
    </head>
    <script src="js/angular.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
    <body data-ng-app="stackExchange">
        <div class="container">
        	<div class="userinfo">
                    <c:choose>
				<c:when test = "${uid==-1||uid==null}">
				    <a href="login">Login</a> | <a href="register.jsp">Register</a>
				</c:when>
                                    <c:otherwise>
				    Hello, ${uname} | <a href="logout">Logout</a>
                                    </c:otherwise>
                    </c:choose>
			</div>
            <a href="index"><h1>Simple StackExchange</h1></a>