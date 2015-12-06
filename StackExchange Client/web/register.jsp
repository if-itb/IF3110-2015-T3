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
        <link rel="stylesheet" type="text/css" href="style.css" />
        <title>Register</title>
    </head>
    <body>
        <a href='index.jsp'><h1>Simple Stack Exchange</h1></a>
        <form name='login' action='reg' method="POST">
            <input class="inputform" type='text' name='name' placeholder='Name'><br><br>
            <input class="inputform" type='text' name='email' placeholder='Email'><br><br>
            <input class="inputform" type='text' name='pass' placeholder='Password'><br><br>
            <input class="button" type='submit' value='Register'>
        </form>
    </body>
</html>