<%-- 
    Document   : main
    Created on : Nov 6, 2015, 5:53:06 PM
    Author     : tama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% String ipa = request.getHeader("X-FORWARDED-FOR");  
            if (ipa == null) {  
                ipa = request.getRemoteAddr();  
            }
            String browser = request.getHeader("user-agent");
            %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>        
        <link rel="stylesheet" type="text/css" href="style/style.css">
    </head>
    <body>
        <div id="head1">            
            <a href="login.jsp"><div id="login" >Login</div></a>
            <a href="register.jsp"><div id ="reg">Register</div></a>
        </div>
         <a href="home.jsp"><div id="h">Stack <at>Exchange</at></div></a>
        
        
        <div class="raq">Login Page </div>
        <div class="separator"></div>
        
         <form action="http://localhost:21215/StackExch_Client/loginprocess" method="POST">
             <input id="lname" type="text" name="lusername" placeholder="Name / Email : "/><br>             
            <input id="lpass" type="password"  name="lpass" placeholder="Password : " /><br>
            <input type="hidden" name="lipa" value="<%out.println(ipa);%>">
             <input type="hidden" name="lbrowser" value="<%out.println(browser);%>"> 
             <input id="lsubmit" type="submit" value="Login"><br>
         </form>
    </body>
</html>
