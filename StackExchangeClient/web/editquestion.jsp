<%-- 
    Document   : editquestion
    Created on : Nov 25, 2015, 5:30:40 PM
    Author     : mochamadtry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="includes/header.jsp" %>

<div class="container">
    <div id="header">
        <a href="index.php"><h1>Simple StackExchange</h1></a>
    </div>
                
    <div class="main">
        <div class="wrapper" id="question-form">
            <div class="content-header">
                <h2>Edit your question!</h2>
            </div>

            <div class="child-content">
                <form action="/StackExchangeClient/savequestion" method="POST" id="the-form">

                    <input type="text" name="topic" placeholder="Question Topic" id="topic" value="${question.getQuestionTopic()} "> <br>
                    <textarea name="content" id="content" > ${question.getQuestionContent()} </textarea> <br>
                    <input type="hidden" name="qid" value="${question.getQuestionId()}" />
                    <input type="submit" value="Post" name="post" id="post">
                </form>
            </div>
        </div>
	</div>
                
</div>
</body>
</html>
