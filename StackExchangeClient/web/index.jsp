<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
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
                                <a href="/StackExchangeClient/register.jsp">Register</a>
                            </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <p style="text-align:right">You're log in as ${username} <button onclick="window.location.href='logout';">Log out</button></p>
                </c:otherwise>
            </c:choose>
            
            <a href="/StackExchangeClient/index"><h1>Simple StackExchange</h1></a><br>

            <form class="search-box" action="/StackExchangeClient/index" method="POST">
		<input type="text" name="keyword">
		<input type="submit" value="Search"><br>
            </form>
            <br><p style="text-align:center">Cannot find what you are looking for? <a href="/StackExchangeClient/askquestion.jsp" style="color:orange">Ask here</a></p>
            <h3>Recently Asked Questions</h3>

            <table id="listQuestions">
                <c:forEach items="${result}" var="question">                   
                    <tr>
                        <td id="countanswer">
                            ${question.answer}<br>Answers
                        </td>
                        <td id="countvote">
                            ${question.vote}<br>Votes
                        </td>
                        <td id="gap">
                        </td>
                        <td id="display">
                            <a href="<c:url value="/viewpost" >
                                        <c:param name="id" value="${question.idQuestion}"/>
                                    </c:url>">${question.topic}
                            </a>
                            <br><br>${question.content}<br>

                            <c:choose>
                                <c:when test="${question.username == username}">
                                    <p style="text-align:right">
                                        asked by ${question.username} | 
                                        <a href="<c:url value="/editquestion" >
                                                    <c:param name="id" value="${question.idQuestion}"/>
                                                </c:url>">edit
                                        </a> | 
                                        <a href="<c:url value="/deletequestion" >
                                                    <c:param name="id" value="${question.idQuestion}"/>
                                                </c:url>"
                                            onclick="return confirm('Are you sure you want to delete this item?')">delete
                                        </a>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p style="text-align:right">asked by ${question.username}</p>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                </c:forEach>       
            </table>
            
        </div>
        
    </body>
</html>
