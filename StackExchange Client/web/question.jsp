<%-- 
    Document   : question
    Created on : Nov 16, 2015, 1:47:03 AM
    Author     : vanyadeasysafrina
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/views/header.jsp" flush="true"/>
<div ng-app="stackexchange">
	<div class="container">
            <h2><a href="question?q_id=<c:out value='${question.getQId()}'/>" class="question-title-big">
                <c:out value="${question.getTopic()}"/>
            </a></h2>
            <hr>
            <span ng-controller="ViewVoteController" ng-init="init(${question.getQId()}, 'question')" id="question-vote"><br>
                <div ng-click="vote(${question.getQId()},'question','${token}','up')" class="arrow-up">
                </div><br>
                <span id="questvote" class="question-number">
                    {{vote}}</span><br>
                <br>
		<div ng-click="vote(${question.getQId()},'question','${token}','down')" class="arrow-down">
                </div><br></span>
		<span id="question-content">
                    
                    <c:set var="qcontent" value="${question.getContent()}"/>
                    ${fn:replace(qcontent,'\\n', ';')}
                    
                    
                    <br><br><br>
                    <span class="question-info">asked by <span class="author">
                            <c:out value="${question.getEmail()}"/>
                    </span> at <c:out value="${question.getDateCreated()}"/>
                    <c:if test="${question.getDateEdited()!=null}">
                            <c:out value=" | edited at ${question.getDateEdited()} "/>
                    </c:if>
                    <c:if test="${user != null && user.getUId() == question.getUId()}">
                         |<a href="edit?q_id=<c:out value='${question.getQId()}'/>" class="edit-question"> edit</a> | 
                        <a href="delete?q_id=<c:out value='${question.getQId()}'/>" 
                           class="delete-question" onclick="return deleteConfirmation(<c:out value='${question.getQId()}'/>)">
                            delete
                        </a></c:if>
                        <br></span>
                </span>
                <div ng-controller="ViewCommentsController">   

                    <!-- Show comments -->                
                    <div class="comment" ng-repeat = "comment in comments">
                            {{ comment.content }}
                            <br>
                            <span class="author">{{ comment.email }}</span> at {{ comment.date_created }}
                    </div>
                </div>

		<!-- Add comment -->
		<a href="#" ng-click="showBox = ! showBox">Add Comment</a>
                <div class="comment-box" ng-show="showBox" ng-controller="AddCommentController">
                    <form class="basic-grey" ng-submit="addComment('<c:out value='${question.getQId()}'/>', content, '<c:out value='${token}'/>')">
                    <input type="hidden" name="q_id" ng-value="q_id" value="<c:out value='${question.getQId()}'/>">
                    <input type="hidden" name="token" ng-value="token" value="<c:out value='${token}'/>">
                    <textarea id="content" name="content" ng-model="content" placeholder="Content" ></textarea><br>
                    <div class="div-right-button">
                        <input type="submit" class="right-button" value="Post">
                    </div>
                    </form>
                </div>
		
		
        <br><br><br>
		<h2><c:out value="${question.getAnswer()}"/> Answer(s)</h2><hr>
		<br><br>
                <c:if test="${question.getAnswer()==0}">
			No answer.
                        <br><br><br><hr><br>
                </c:if>
                
		<c:forEach items="${answers}" var="answer">
                    <span ng-controller="ViewVoteController" ng-init="init(${answer.getAId()}, 'answer')" id="question-vote"><br>
                        <div onclick="location.href='vote?id=<c:out value="${answer.getAId()}"/>&type=a&vote=1&q_id=<c:out value="${question.getQId()}"/>';" class="arrow-up">
                        </div><br>
			<span id="ansvote-<c:out value='${answer.getAId()}'/>" class="question-number">
                            {{ vote }}</span><br>
                        <br>
                        <div onclick="location.href='vote?id=<c:out value="${answer.getAId()}"/>&type=a&vote=-1&q_id=<c:out value="${question.getQId()}"/>';" class="arrow-down"></div>
                        <br>
                    </span>
                    <span id="question-content">
                        <c:set var="acontent" value="${answer.getContent()}"/>
                        ${fn:replace(acontent,'\\n', ';')}
                        
                        
			<br><br><br>
			<span class="question-info">answered by
                            <span class="author">
                                <c:out value="${answer.getEmail()}"/>
                            </span>
                            at <c:out value="${answer.getDateCreated()}"/>
                        </span>
                    </span>
                    <br><br><hr>
                </c:forEach>
      
                
                <c:if test="${user!=null}"> 
		<div class="center">
			<form class="basic-grey" name= "answer" action="answer" onsubmit="return validateAnswerForm()" method="post">
                            <input type="hidden" name="q_id" value="<c:out value='${question.getQId()}'/>">
                            <input type="hidden" name="token" value="<c:out value='${token}'/>">
                            <textarea id="content" name="content" placeholder="Content" ></textarea><br>
                            <div class="div-right-button">
                                    <input type="submit" class="right-button" value="Post">
                            </div>
			</form>
		</div>
                </c:if>
                    <c:if test="${user == null}"><center>Please <a href="login">login</a> before answering this question</center></c:if>
            
	</div>
    </div>
<script src="assets/js/confirmation.js"></script>
<script src="assets/js/validation.js"></script>
<script src="assets/js/script.js"></script>
<jsp:include page="/views/footer.jsp" flush="true"/>