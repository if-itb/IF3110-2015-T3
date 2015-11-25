<%-- 
    Document   : reg
    Created on : Nov 14, 2015, 7:35:38 AM
    Author     : theaolivia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
            <center>
                <form name="new" method="post" action="register.jsp">
                    <input class="inputform" type="text" name="username" placeholder="User Name"><br>
                    <input class="inputform" type="text" name="email" placeholder="Email"><br>
                    <input class="inputform" type="password" name="password" placeholder="Password"><br>
                    <input type="submit" class="button" value="Register">
                </form>
            </center>
    </body>
</html>
