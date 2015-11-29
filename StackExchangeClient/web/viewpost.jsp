<%-- 
    Document   : viewpost
    Created on : Nov 25, 2015, 3:03:06 PM
    Author     : Bimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="includes/header.jsp" %>

<div class="container">
    <div id="header">
        <a href="index.php"><h1>Simple StackExchange</h1></a>
    </div>

    <div class="main">

        <div class="wrapper" id="the-question">
            <div class="content-header">
                <h2>${result.getQuestionTopic()}</h2>
            </div>
            <div class="child-content">
                <div class="sidebar">
                    <a href="<c:url value="/votequestion?qid=${result.getQuestionId()}&jlhvote=1"></c:url>"><div class="voteup"></div>
                    </a>
                    <div>${questionvote}</div>
                    <a href="<c:url value="/votequestion?qid=${result.getQuestionId()}&jlhvote=-1"></c:url>"><div class="votedown"></div>
                    </a>
                </div>

                <div class="list-content">
                    <div class="thread-content">
                        ${result.getQuestionContent()}
                    </div>
                    <div class="content-footer">
                        <c:choose>
                            <c:when test="${name == asker}">
                            asked by <span class="user-question">${asker}</span> at ${result.getQuestionTimestamp()} | <a href="<c:url value="/editquestion" >
                            <c:param name="qid" value="${result.getQuestionId()}"/></c:url>">Edit</a> | <a href="deletequestion?qid=${result.getQuestionId()}">Delete</a>
                            </c:when>
                            <c:otherwise>
                            asked by <span class="user-question">${asker}</span> at ${result.getQuestionTimestamp()}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>  
        </div>
        
        <div class="wrapper" id="the-answers">
            <div class="content-header">
                <h2>${answers.size()} Answers</h2>
            </div>

            <c:forEach items="${answers}" var="answer">

            <div class="child-content">
                <div class="sidebar">
                    <a href="<c:url value="/voteanswer?aid=${answer.getAnswerId()}&qid=${result.getQuestionId()}&jlhvote=1"></c:url>"><div class="voteup"></div>
                    </a> 
                    <div>
                        ${ansvotemap.get(answer.getAnswerId())}
                    </div>
                    <a href="<c:url value="/voteanswer?aid=${answer.getAnswerId()}&qid=${result.getQuestionId()}&jlhvote=-1"></c:url>"><div class="votedown"></div>
                    </a>
                    
                </div>
                <div class="list-content">
                    <div class="thread-content">
                        ${answer.getAnswerContent()}
                    </div>
                    <div class="content-footer">
                        answered by <span class="user-question">${hmap.get(answer.getAnswerId())} at ${answer.getAnswerTimestamp()}</span>
                    </div>
                </div>
            </div>

            </c:forEach>
        </div>

        <div class="wrapper" id="answer-form">
            <div class="child-content">
                <div class="content-header">
                    <h2>Your Answer</h2>
                </div>
                <c:choose>
                    <c:when test="${name != null}">
                        <form role="form" onsubmit="return validateAnswerForm()" action="/StackExchangeClient/addanswer" method="post" id="the-form">
                            <input type="hidden" name="qid" value="${result.getQuestionId()}">
                            <textarea name="content" form="the-form" placeholder="Your Answer Content" id="content"></textarea><br>
                            <input type="submit" value="Post" name="post" id="post">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <br>
                        <p>Please login to add answer to this question!</p>
                    </c:otherwise>
                </c:choose>
                
            </div>
        </div>

    </div>
    
</div>

</body>
</html>
