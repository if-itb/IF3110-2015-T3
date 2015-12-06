<%-- 
    Document   : sessionpanel
    Created on : Dec 6, 2015, 12:51:04 AM
    Author     : tama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Panel</title>        
        <link rel="stylesheet" type="text/css" href="style/style.css">
    </head>
    <body>
        <% long times = (long) (System.currentTimeMillis() / 1000);
        times = times+3600;
       String exp= String.valueOf(times);     %>
        <% 
         String to="",us="",ex="";
          int idu=0; 
          HttpSession ss = request.getSession();
          to =String.valueOf(ss.getAttribute("token"));
          us = String.valueOf(ss.getAttribute("username"));
          ex = String.valueOf(ss.getAttribute("expire"));
          if (!to.equals("null")) idu = Integer.valueOf(String.valueOf(ss.getAttribute("id")));
        %>
      
         <a href="home.jsp"><div id="h">Stack <at>Exchange</at></div></a>
        
        
        
        <div class="raq">Session Page</div>
        <div class="separator"></div>
        <br><div class="raq">
         Current token = <% out.println(to) ;%><br>
         Current username = <% out.println(us) ;%><br>
         Current ex = <% out.println(ex) ;%><br>
         <br>Change token :
        </div>
         <form action="home.jsp" method="POST">
             <input id="ntoken" type="text" name="ntoken" placeholder="New token : "/><br>             
           <input type="hidden" name="nexp" value="<% out.println(exp);%>">
             <input id="lsubmit" type="submit" value="Confirm"><br>
         </form>
    </body>
</html>
