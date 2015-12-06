<%-- 
    Document   : editQuestion
    Created on : Nov 18, 2015, 7:12:33 PM
    Author     : Tifani
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/views/header.jsp" flush="true"/>
<jsp:useBean id="question" type="QuestionWS.Question" scope="request"/>
    <div class="container">
            <h2>What's your question?</h2>
            <hr>
            <br>
            <div class="center">
                <!-- TODO: Edit Controller -->
                <form name="ask" class="basic-grey" action="edit" 
                        onsubmit="return validateAskForm()" method="post">
                    <input type="hidden" name="q_id" value="<c:out value='${question.getQId()}'/>">
                    <input type="hidden" name="token" value="<c:out value='${token}'/>">
                    <input type="text" id="topic" name="topic" placeholder="Question Topic" value="<c:out value='${question.getTopic()}'/>"><br>
                    <textarea id="content" name="content" placeholder="Content"><c:out value="${question.getContent()}"/></textarea><br>
                    <div class="div-right-button">
                        <input type="submit" class="right-button" value="Post">
                    </div>
                </form>
            </div>
    </div>
<script src="assets/js/validation.js"></script>
<jsp:include page="/views/footer.jsp" flush="true"/>
