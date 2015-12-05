<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@ include file="style.css"%>
        </style>
        <title>Stack Exchange</title>
    </head>
    <body>
        <div class="container">
            <c:choose>
                <c:when test="${username == null}">
                    <div class="login">
                        <h1>Log-in</h1>
                            <form action="/StackExchangeClient/login" method="POST">
                                <input type="text" name="user" placeholder="Username">
                                <input type="password" name="pass" placeholder="Password">
                                <input type="submit" name="login" value="Login">
                            </form>
                            <div class="login-help">
                                <a href="register.jsp">Register</a>
                            </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <p style="text-align:right">You're log in as ${username} <button onclick="window.location.href='logout';">Log out</button></p>
                </c:otherwise>
            </c:choose>

            <a href="/StackExchangeClient/index"><h1>Simple StackExchange</h1></a><br>
            
            <c:forEach items="${result}" var="question">
                <h2>${question.topic}</h2><br>  
                <table id="question">
                    <tr>
                        <td id="countvote">
                            <a href="<c:url value="/votequestion" >
                                        <c:param name="id" value="${question.idQuestion}"/>
                                        <c:param name="type" value="1"/>
                                    </c:url>">
                                <div class="arrow-up"></div>
                            </a>
                            <p id="vote" style="font-size:40px; margin:0; color:lightgrey"> ${question.vote} </p>
                            <a href="<c:url value="/votequestion" >
                                        <c:param name="id" value="${question.idQuestion}"/>
                                        <c:param name="type" value="-1"/>
                                    </c:url>">
                                <div class="arrow-down"></div>
                            </a><br>
                        </td>
                        <td id="display">
                            ${question.content}<br>
                        </td>
                    </tr>
                </table>

                <c:choose>
                    <c:when test="${question.username == username}">
                        <p style="text-align:right">
                            asked by ${question.username} | 
                            <a href="<c:url value="/editquestion" >
                                        <c:param name="id" value="${question.idQuestion}"/>
                                    </c:url>">edit
                            </a> | 
                            <a href="<c:url value="/deletequestion" >
                                1       <c:param name="id" value="${question.idQuestion}"/>
                                    </c:url>"
                                onclick="return confirm('Are you sure you want to delete this item?')">delete
                            </a>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p style="text-align:right">asked by ${question.username}</p>
                    </c:otherwise>
                </c:choose>
                <h2>${count} Answer</h2><br>
            </c:forEach>

            <table id="listAnswers">
                <c:forEach items="${answers}" var="answer">
                    <tr>
                        <td id="countvote">
                            <a href="<c:url value="/voteanswer" >
                                    <c:param name="id_answer" value="${answer.idAnswer}"/>
                                    <c:param name="id_question" value="${answer.idQuestion}"/>
                                    <c:param name="type" value="1"/>
                                </c:url>">
                                <div class="arrow-up"></div>
                            </a>
                            <p style="font-size:40px; margin:0; color:lightgrey">${answer.vote}</p>
                            <a href="<c:url value="/voteanswer" >
                                    <c:param name="id_answer" value="${answer.idAnswer}"/>
                                    <c:param name="id_question" value="${answer.idQuestion}"/>
                                    <c:param name="type" value="-1"/>
                                </c:url>">
                                <div class="arrow-down"></div>
                            </a>
                        </td>
                        <td id="display">${answer.content}<br>
                            <p style="text-align:right">answered by ${answer.username} at ${answer.date}</p>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <br><p style="font-size:30px; margin:0; color:grey"> Your Answer </p>
            <form class="AnswerForm" action="addanswer" method="POST">
		<textarea name="content" id="content" placeholder="Content"></textarea><br><br>
                <input type="hidden" name="idQuestion" value="${result.get(0).idQuestion}" />
		<input type="submit" value="Post">
            </form>
            
	</div>
    </body>
</html>
