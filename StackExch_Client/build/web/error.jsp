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
            String status=request.getParameter("status");
            int ss = Integer.valueOf(status);
            
            
            String browser ="";
            String message = "";
            
            String url_ = "";
            if (ss==-1) {
                message = "Your token not valid";
                url_ = "logout";
            } else if (ss==-2) {
                 message = "Your token expired";
                url_ = "logout";
            } else if (ss==-3) {
                message = "You detected connected from different ip";
                url_ = "logout";
            } else if (ss==-4) {
                message = "You detected connected from different browser";
                url_ = "logout";
            } else {
                message = "You only allowed vote one time";
                url_ = "home.jsp";
            }
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
