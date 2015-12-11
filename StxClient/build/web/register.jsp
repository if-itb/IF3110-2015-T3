<%-- 
    Document   : register
    Created on : Nov 26, 2015, Nov 26, 2015 1:46:03 AM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="include/header.jsp"  %>
<h1>Sign up</h1>
<form class="block" action="actions/sign-up.jsp" name="LoginForm" method="POST">
    <ul>
        <input type='text' name = 'Name' placeholder='nama'></input>
        <input type='text' name = 'Email' placeholder='email'></input>
        <input type='password' name = 'Password' placeholder='password'></input><br><br>
        <input type='submit' value='register'></input>
    </ul>
</form>
<%@include file="include/footer.jsp" %>
