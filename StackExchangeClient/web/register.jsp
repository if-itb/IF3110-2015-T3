<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@ include file="style.css"%>
        </style>
        <title>Stack Exchange</title>
    </head>
    <body>
        <div class="container">
            <a href="/StackExchangeClient/index"><h1>Simple StackExchange</h1></a><br>
            <h2>Register </h2><br>
            <form name="RegisterForm" class="RegisterForm" action="register" method="POST">
		<input type="text" name="name" placeholder="Name"><br>
		<input type="text" name="username" placeholder="Username"><br>
		<input type="text" name="email" placeholder="Email"><br>
		<input type="password" name="password" placeholder="Password"><br>
		<input type="password" name="confirmpassword" placeholder="Confirm Password"><br><br>
		<input type="submit" value="Sign Up">
            </form>
        </div>
    </body>
</html>
