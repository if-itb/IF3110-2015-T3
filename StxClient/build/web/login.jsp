<%-- 
    Document   : login
    Created on : Nov 26, 2015, Nov 26, 2015 12:45:49 AM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="include/header.jsp"  %>
<h2>Sign in</h2>
<form class="block" action="actions/sign-in.jsp" name="LoginForm" method="POST">
    <ul>
       
        <input type='text' name = 'Email' placeholder='email'></input>
        <input type='password' name = 'Password' placeholder='password'></input><br><br>
        <input type='submit' value='sign in'></input>
    </ul>
</form>
<br> <br> <br>
<h4>
    belum memiliki akun, <a href="register.jsp"> sign up</a>
</h4>
<%@include file="include/footer.jsp" %>
