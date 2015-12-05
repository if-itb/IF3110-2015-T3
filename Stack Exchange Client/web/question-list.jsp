
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : question-list
    Created on : Nov 16, 2015, 12:04:39 AM
    Author     : zulvafachrina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="StyleSheet" href="style.css" type="text/css">
        <script src="script.js"></script>
        <title>Simple Stack Exchage</title>
    </head>

    
<body>
        <div id="edit-delete" style="text-align:right">
            <c:choose>
                <c:when test="${token.length()==0}">
                    <p>
                        <c:out value="Logged in as"/> 
                        <b><c:out value="guest"/></b> | 
                        <a href="login.jsp" style="color:#FFA500"> Log in </a> | 
                        <a href="register.jsp" style="color:#FF0000"> Register </a> 
                    </p>
                </c:when>
                <c:otherwise>
                    <p>
                        <c:out value="Logged in as"/> 
                        <b><c:out value="${token}"/></b> | 
                        <a href="http://localhost:8082/Stack_Exchange_IS/Logout" style="color:#FFA500"> Log out </a> | 
                        <a href="register.jsp" style="color:#FF0000"> Register </a> 
                    </p>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="header">
	<h1> <a href ="/Stack_Exchange_Client/QuestionServlet" style="color:#000"> Simple Stack Exchange </a> </h1>
        </div>

<div class = "container">

	<form method="GET" action="question-list.jsp">
		<div>
			<input type="submit" id="search_button" id="search_button" value="Search">
			<input type="text" name="searchkey" id="searchkey">
                        <p style="text-align:center"> Cannot find what you are looking for?
                            <c:choose>
                                <c:when test="${token.length() != 0}">
                                <a href="ask-question.jsp" style="color:#FFA500"> Ask Here </a>
                                </c:when>
                                <c:otherwise>
                                <a href="login.jsp" style="color:#FFA500"> Log In First! </a>
                                </c:otherwise>
                            </c:choose>
                           </p>      
		</div>
	</form>
        <h2> Recently Asked Question <hr> </h2>
        
        <c:forEach var="question" items="${questions}">
            <div class="boxarea">
		<div class="vote">
                    <h3><c:out value="${question.vote}"/></h3>
                    <c:out value="Votes"/>
		</div>

                <div class="vote" style="margin-left:5%">
		<h3><c:out value="${question.countAnswer}"/></h3>
                <c:out value="Answers"/>
		</div>

		<div class="question-content">
                    <h4><a href="/Stack_Exchange_Client/QuestionPage?id=${question.id}"> <c:out value="${question.topic}"/></a></h4>
                    <p> <c:out value="${question.content}"/> </p>
		</div>
					
		<div class = "edit-delete">
                    <p> asked by <b><c:out value="${question.username}"/></b> 
                        <c:if test="${token.length()!=0}">
                            |<a href="/Stack_Exchange_Client/GetQuestion?qid=${question.id}" style="color:#FFA500"> edit </a> | 
                            <a href="#" onclick="validateDelete(${question.id})" style="color:#FF0000"> delete </a>
                        </c:if>
                    </p>         
		</div>

            </div>
            <hr>
        </c:forEach>
    </body>
</html>
