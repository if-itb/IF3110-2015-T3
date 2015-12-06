<%-- 
    Document   : search
    Created on : Nov 16, 2015, 11:26:12 AM
    Author     : vanyadeasysafrina
--%>

<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/views/header.jsp" flush="true"/>
<jsp:useBean id="questions" type="java.util.List<QuestionWS.Question>" scope="request"/>
	<div class="container">
            <br>
            <h2>Search: <c:out value="${param.keyword}"/></h2>
            <c:forEach items="${questions}" var="q">
            <hr>
            <span id="vote"><br>
                <span class="number"><c:out value="${q.getVote()}"/></span><br>
                Votes
            </span>
            <span id="answer"><br>
                <span class="number"><c:out value="${q.getAnswer()}"/></span><br>
                Answers
            </span>
            <span id="question">
                <a href="question?q_id=<c:out value='${q.getQId()}'/>" class="question-title">
                    <c:out value="${q.getTopic()}"/>
                </a><br>
                <c:choose>
                    <c:when test="${q.getContent().length()>700}">
                        <c:out value='${q.getContent().substring(700)} ...'/>                            
                    </c:when>
                    <c:otherwise>
                        <c:out value="${q.getContent()}"/>
                    </c:otherwise>
                </c:choose>
                <br><br>
                <span class="question-info">
                    asked by <span class="author"><c:out value="${q.getName()}"/></span>
                    <c:if test="${user != null && user.getUId() == q.getUId()}">
                    | <a href="edit?q_id=<c:out value='${q.getQId()}'/>" class="edit-question">edit</a> | 
                    <a href="delete?q_id=<c:out value='${q.getQId()}'/>" class="delete-question"
                    onclick="return deleteConfirmation(<c:out value='${q.getQId()}'/>)">delete</a>
                    </c:if>
                   <br>
                </span>
            </span>
            </c:forEach>
	</div>
<script src="assets/js/confirmation.js"></script>
<jsp:include page="/views/footer.jsp" flush="true"/>