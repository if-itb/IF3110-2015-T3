<%-- 
    Document   : register.jsp
    Author     : moel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExcahange | Register</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        
        <a href="index.jsp"><h1>Simple StackExchange</h1></a>
        <h2 style="color:#A0A0A0" align="Center">Register Here</h2>
        <h5>Already have an account? <a href="signin.jsp"><span id="link">Sign in here</span></a></h5>
        
        <div class="garis"></div>
        
        <form action="addAccount.jsp" method="post" name="ask-form">
            Username
            <input type="text" name="Name" class="form-field" placeholder="Name" required></input>
            <br>
            Email
            <input type="text" name="Email" class="form-field" id="Email" placeholder="Email" required></input>
            <br>
            Password
            <input type="password" name="Password" class="form-field" placeholder="Password" required></input>
            <br>
            <div align="right">
                    <input type="submit" value="Register" onclick="return validateForm()" action="addAccount.jsp">
                    <h6>*When you register you accept the terms and regulation</h6>
            </div>
            
            <input type="hidden" name="id_q" />
        </form>
        
    </body>
</html>
