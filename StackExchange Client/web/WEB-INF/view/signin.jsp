<%-- 
    Document   : signin
    Created on : Nov 18, 2015, 12:04:59 PM
    Author     : acer
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" flush="true"/>
    <div class="container">
        <h3>Sign in</h3>
        <hr class="heading">          
        <div class="inner-container">
            <c:choose>
                <c:when test="${not empty error}"> <!-- error message -->
                    <div class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span><c:out value="${error}"/>
                    </div>
                </c:when>
                <c:when test="${not empty success}"> <!-- success message -->
                    <div class="alert alert-success" role="alert">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                        <span class="sr-only">Success:</span><c:out value="${success}"/>
                    </div>
                </c:when>
            </c:choose>
            <form class="login-wrapper form-horizontal" method="POST">
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email" required autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>
            </form>        
        </div>        
    </div>
<jsp:include page="footer.jsp" flush="true"/>