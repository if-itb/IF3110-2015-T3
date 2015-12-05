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
	<div class="container">
            <h2><a href="question?q_id=<c:out value='${question.getQId()}'/>" class="question-title-big">
                <c:out value="${question.getTopic()}"/>
            </a></h2>
            <hr>
            <span id="question-vote"><br>
                <div onclick="location.href='vote?id=<c:out value="${question.getQId()}"/>&type=q&vote=1';" class="arrow-up">
                </div><br>
                <span id="questvote" class="question-number"><c:out value="${question.getVote()}"/></span><br>
                <br>
		<div onclick="location.href='vote?id=<c:out value="${question.getQId()}"/>&type=q&vote=-1';" class="arrow-down">
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
                <br><br><br>
		<h2><c:out value="${question.getAnswer()}"/> Answer(s)</h2><hr>
		<br><br>
                <c:if test="${question.getAnswer()==0}">
			No answer.
                        <br><br><br><hr><br>
                </c:if>
                
		<c:forEach items="${answers}" var="answer">
                    <span id="question-vote"><br>
                        <div onclick="location.href='vote?id=<c:out value="${answer.getAId()}"/>&type=a&vote=1&q_id=<c:out value="${question.getQId()}"/>';" class="arrow-up">
                        </div><br>
			<span id="ansvote-<c:out value='${answer.getAId()}'/>" class="question-number">
                            <c:out value="${answer.getVote()}"/></span><br>
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
				<textarea id="content" name="content" placeholder="Content" ></textarea><br>
				<div class="div-right-button">
					<input type="submit" class="right-button" value="Post">
				</div>
			</form>
		</div>
                </c:if>
                    <c:if test="${user == null}"><center>Please <a href="login">login</a> before answering this question</center></c:if>
            
	</div>

<script src="assets/js/confirmation.js"></script>
<script src="assets/js/validation.js"></script>
<script src="assets/js/script.js"></script>
<jsp:include page="/views/footer.jsp" flush="true"/>