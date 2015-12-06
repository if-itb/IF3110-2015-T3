<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Functions.js"></script>
        <style>
            <%@ include file="style.css"%>
        </style>
        <title>Stack Exchange</title>
    </head>
    <body>
        <div class="container">
            <c:forEach items="${result}" var="question">
                <p style="text-align:right">You're log in as ${question.username} <button onclick="window.location.href='index';">Log out</button></p>

                <a href="/StackExchangeClient/index"><h1>Simple StackExchange</h1></a><br>
                <h2>What's your question? </h2><br>
                <form class="QuestionForm" action="saveeditedquestion" method="POST">
                    <input type="text" name="topic" id="inputtext1" value="${question.topic}"><br>
                    <textarea name="content">${question.content}</textarea><br><br>
                    <input type="hidden" name="id" value="${question.idQuestion}" />
                    <input type="submit" value="Post">
                </form>
            </c:forEach>
	</div>
    </body>
</html>
