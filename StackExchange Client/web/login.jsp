<%-- 
    Document   : login
    Created on : Nov 24, 2015, 1:19:29 AM
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
        <div>
        <form name='login' action='ask.jsp'>            
            <input class="inputform" type='text' name='mail' placeholder='Email'><br><br>
            <input class="inputform" type='password' name='pass' placeholder='Password'><br><br>
            New here? <a href='register.jsp'>Sign Up!</a>          
            <input class="button" type='submit' value='Login'>
            
           
        </form>
        </div>
    </body>
</html>
