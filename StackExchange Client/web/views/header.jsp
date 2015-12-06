<%-- 
    Document   : header
    Created on : Nov 15, 2015, 8:54:51 PM
    Author     : Tifani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<!DOCTYPE html>
<html lang="en">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<head>
	<meta content="text/html; charset=utf-8">
	<link REL="SHORTCUT ICON" HREF="assets/img/icon.ico"/>
	<title>Simple StackExchange</title>

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<div class="title-section">
		<header>
			<strong><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" style="text-decoration: none;"><h1 id="title">Simple StackExchange</h1></a></strong>
			<i id="tagline">A question and answer site for professionals</i>
			<br>
		</header>
	</div>
	<br>
        <div style="text-align: right; margin-right: 20px">
            <c:if test="${status == 'Authentication succeed'}">
                Welcome, <span class="author"><c:out value="${user.name}"/></span> | <a href="logout">Logout</a> 
            </c:if>
            <c:if test="${status != 'Authentication succeed'}">
                <a href="login">Login</a> | <a href="register">Register</a>
            </c:if>
        </div>