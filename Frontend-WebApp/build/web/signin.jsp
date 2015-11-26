<%-- 
    Document   : signin
    Author     : moel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExcahange | SignIn</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        
        <a href="index.jsp"><h1>Simple StackExchange</h1></a>
        <h2 style="color:#A0A0A0" align="Center">Sign In</h2>
        <div class="garis"></div>
        
        <form action="addAccount.jsp" method="post" name="ask-form">
            Email
            <input type="text" name="Email" class="form-field" id="Email" placeholder="Email" required></input>
            <br>
            Password
            <input type="text" name="Password" class="form-field" placeholder="Password" required></input>
            <br>
            <div align="right">
                    <input type="submit" value="Sign In" onclick="return validateForm()" action="addAccount.jsp">
            </div>
            <input type="hidden" name="id_q" />
        </form>
        
    </body>
</html>
