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

        <a href='index.jsp'> <h1>Simple Stack Exchange</h1> </a>
        <h2>Login</h2>
		       <div>
        <form action="log" method="POST">         
            <input class="inputform" type='text' name='email' placeholder='Email'><br><br>
            <input class="inputform" type='password' name='password' placeholder='Password'><br><br>
			New here? <a href='register.jsp'>Sign Up!</a>      
			<input class="button" type='submit' value='Login'>
        </form>
        </div>
    </body>
</html>