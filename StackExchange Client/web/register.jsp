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
        <title>Login</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <h1>Simple Stack Exchange</h1>
        <form name='login' action='ask.jsp'>
            <input class="inputform" type='text' name='name' placeholder='Name'><br><br>
            <input class="inputform" type='text' name='mail' placeholder='Email'><br><br>
            <input class="inputform" type='password' name='pass' placeholder='Password'><br><br>
            <input class="button" type='submit' value='Register'>
            
        </form>
    </body>
</html>
