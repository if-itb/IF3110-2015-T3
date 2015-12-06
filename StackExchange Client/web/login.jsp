<%-- 
    Document   : login
    Created on : Nov 17, 2015, 1:51:17 PM
    Author     : jessica
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/views/header.jsp" flush="true"/>
    <div class="container">
        <center><h2>Log In</h2></center>
        <hr>
        <br>
        <div class="center">
            <c:choose>
                <c:when test="${param.st == '1'}">
                    <div class="success">Registration success.</div>
                    <br>
                </c:when>
                <c:when test="${param.st == '0'}">
                    <div class="error">Access denied. Please log in.</div>
                    <br>
                </c:when>
                <c:otherwise>
                   <!-- No comment -->
                </c:otherwise>
            </c:choose>
            <div class="center">
                <form name="login" class="register" action="login" 
                    onsubmit="javascript: return validateLoginForm();" method="post">
                    <input type="text" id="email" name="email" placeholder="Email"><br>
                    <input type="password" id="pass" name="pass" placeholder="Password"><br>
                    <div class="div-right-button">
                        <input type="submit" class="right-button" value="Log In">
                    </div>
                </form>
            </div>
        </div>
    </div>
<script src="assets/js/validation.js"></script>
<jsp:include page="/views/footer.jsp" flush="true"/>