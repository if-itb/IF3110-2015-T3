<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            Simple StackExchange
        </title>
        <link rel="stylesheet" href="main.css">
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    </head>
    <body>
        <div class="navbar-up">
            <a href="index.jsp">
                <h1 class="white">Simple StackExchange</h1>
            </a>
        </div>
        <c:set var="qid" value="${param.qid}" scope="request" />
        <jsp:include page="/QuestionByQIDServlet" />
    </body>
</html>
