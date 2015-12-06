<%-- 
    Document   : error
    Created on : Dec 6, 2015, 9:25:25 PM
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
            String message = request.getParameter("message");
            
            String url_ = request.getParameter("url");
            %>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>        
        <link rel="stylesheet" type="text/css" href="style/style.css">
    </head>
    <body>
       
         <a href="home.jsp"><div id="h">Stack <at>Exchange</at></div></a>
        
        
        <div class="raq"> </div>
        <div class="separator"></div>
        <% response.setHeader("Refresh", "2;url="+url_); %>
        <div class="rsuccess"><% out.print(message) ; %></div>
    </body>
</html>
