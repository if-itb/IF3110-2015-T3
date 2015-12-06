<%-- 
    Document   : regprocess
    Created on : Nov 11, 2015, 11:39:44 PM
    Author     : tama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> <% String ipa = request.getHeader("X-FORWARDED-FOR");  
            if (ipa == null) {  
                ipa = request.getRemoteAddr();  
            }
            String browser = request.getHeader("user-agent");
            %>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Success</title>        
        <link rel="stylesheet" type="text/css" href="style/style.css">
    </head>
    <body>
       <div id="head1">            
            <a href="login.jsp"><div id="login" >Login</div></a>
            <a href="register.jsp"><div id ="reg">Register</div></a>
        </div>
         <a href="home.jsp"><div id="h">Stack <at>Exchange</at></div></a>
        
        
        <div class="raq"> </div>
        <div class="separator"></div>
        <% response.setHeader("Refresh", "2;url=home.jsp"); %>
        <div class="rsuccess">Registration success !!  . . <br>You'll be redirected to home page. If not click <a href="home.jsp"><rr>here</rr></a></div>
    </body>
</html>
