<%-- 
    Document   : register
    Created on : Nov 17, 2015, 12:47:14 PM
    Author     : jessica
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

function validateForm() {
    var name = document.forms["register"]["name"].value;
    var email = document.forms["register"]["email"].value;
    var pass = document.forms["register"]["pass"].value;
    var passcheck = document.forms["register"]["passcheck"].value;
  
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    
    
    
    if (name === null || name === "" || email === null || email === "" || pass === null || pass === "" || passcheck === null || passcheck === "") {
        alert("All fields are required. Please complete the form.");
        return false;
    }
    else if (!filter.test(email)) {
        alert('Please provide a valid email address');
        return false;
    }
        
    else if (pass !== passcheck){
        alert('Password does not match');
        return false;
    }
    
    else {
        return true;
    }
}

</script>
<jsp:include page="/views/header.jsp" flush="true"/>
    <div class="container">
        <center><h2>Register</h2></center>
        <hr>
        <br>
        <c:choose>
            <c:when test="${message == 'Failed'}">
                <div class="error">
                    Registration failed
                </div>
                <br>
            </c:when>
            <c:when test="${message == 'Duplicate'}">
               <div class="error">
                    Email has been registered
                </div>
                <br>
            </c:when>
            <c:otherwise>
               <!-- No comment -->
            </c:otherwise>
        </c:choose>
        <div class="center">
            <form name="register" class="register" action="register" method="post" onsubmit="javascript:return validateForm();">
                <input type="text" id="name" name="name" placeholder="Name"><br>
                <input type="text" id="email" name="email" placeholder="Email"><br>
                <input type="password" id="pass" name="pass" placeholder="Password"><br>
                <input type="password" id="passcheck" name="passcheck" placeholder="Type your password again"><br>
                <div class="div-right-button">
                    <input type="submit" class="right-button" value="Register">
                </div>
                </form>
        </div>
    </div>
<!--<script src="assets/js/validation.js"></script>-->

<jsp:include page="/views/footer.jsp" flush="true"/>
