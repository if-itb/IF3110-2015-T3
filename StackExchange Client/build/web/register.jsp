<%-- 
    Document   : register
    Created on : Nov 24, 2015, 1:28:32 AM
    Author     : Raihan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <center><a href='index.jsp'> <h1>Simple Stack Exchange</h1> </a>
        <form name='login' action='reg' method="POST">
            <input type='text' name='name' placeholder='Name' size='80'><br><br>
            <input type='text' name='email' placeholder='Email' size='80'><br><br>
            <input type='text' name='pass' placeholder='Password' size='80'><br><br>
            <input type='submit' value='Register'>
            </center>
        </form>
    </body>
</html>
