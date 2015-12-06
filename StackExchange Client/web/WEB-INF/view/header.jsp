<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">   
 
    <script src="${pageContext.request.contextPath}/js/angular.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
    <title>Exchange Lyz</title>
</head>
<body>    
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Exchange Lyz</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${empty user}">
                            <li><a href="register"><span class="glyphicon glyphicon-user"></span> Register</a></li>
                            <li><a href="signin"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
                        </c:when>
                        <c:otherwise>
                        <li><p class="navbar-text">Hi, <c:out value="${user.name}" /></p> </li>
                        <li><a href="signout"><span class="glyphicon glyphicon-log-out"></span> Sign out</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="div-title"><h2><a class="text-center text-title" href="${pageContext.request.contextPath}">Exchange Lyz</a></h2></div>
