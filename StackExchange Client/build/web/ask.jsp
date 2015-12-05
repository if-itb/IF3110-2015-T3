<%-- 
    Document   : ask
    Created on : Nov 15, 2015, 9:19:23 PM
    Author     : Tifani
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/views/header.jsp" flush="true"/>
    <div class="container">
            <h2>What's your question?</h2>
            <hr>
            <br>
            <div class="center">
                <form name="ask" class="basic-grey" action="ask" 
                          onsubmit="return validateAskForm()" method="post">
                            <input type="text" id="topic" name="topic" placeholder="Question Topic"><br>
                            <textarea id="content" name="content" placeholder="Content"></textarea><br>
                            <div class="div-right-button">
                                    <input type="submit" class="right-button" value="Post">
                            </div>
                    </form>
            </div>
    </div>
<script src="assets/js/validation.js"></script>
<jsp:include page="/views/footer.jsp" flush="true"/>