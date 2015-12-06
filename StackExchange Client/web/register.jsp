<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExchange</title>
        <link rel="stylesheet" href="main.css">
    </head>
    <body>
        <div class="navbar-up">
            <a href="index.jsp">
                <h1 class="white">Simple StackExchange</h1>
            </a>
        </div>
        <div class="navbar-down">
            <b class="navbar-down">
                <a class="white" href="register.jsp">Register</a> |
                <a class="white" href="login.jsp">Login</a>
            </b>
        </div>
        <div class="main">
            <br>
            <h2>Register your account</h2>
            <hr>
            <form action="RegisterServlet" method="post">
                <input class="text" name="nama" type="text" placeholder="Nama"><br>
                <input class="text" name="email" type="text" placeholder="Email"><br>
                <input class="text" name="password" type="password" placeholder="Password"><br>
                <input type="submit" value="Register">
            </form>
        </div>
    </body>
</html>
