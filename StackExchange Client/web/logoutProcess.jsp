<%-- 
    Document   : logoutFailed
    Created on : Nov 30, 2015, 1:13:37 PM
    Author     : Jessica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="4, url=index.jsp">
        <title>Logout Process</title>
    </head>
    <body>
        <br><br><br>
            <%-- start login process --%>
   <%
       if (request.getAttribute("success").equals("true")) {
           String site = "index.jsp";
           response.setStatus(response.SC_MOVED_TEMPORARILY);
           response.setHeader("Location", site); 
       }
       else if (request.getAttribute("success").equals("false")) {
            if (request.getAttribute("description").equals("different browser")) {
                out.println("<div class='container center'>");
                out.println("<i class='large material-icons'>thumb_down</i><br>");
                out.println("<h4>Failed, you use different browser. Please login again</h4><br><br>");
                out.println("</div>");
                out.println("<div class='row center'>");
                out.println("<a href='login.jsp' id='download-button' class='btn-large waves-effect waves-light orange'>Login</a><br><br>");
                out.println("</div>");
            }  else if (request.getAttribute("description").equals("different ip address")) {
                out.println("<div class='container center'>");
                out.println("<i class='large material-icons'>thumb_down</i><br>");
                out.println("<h4>Failed, you use different ip address. Please login again</h4><br><br>");
                out.println("</div>");
                out.println("<div class='row center'>");
                out.println("<a href='login.jsp' id='download-button' class='btn-large waves-effect waves-light orange'>Login</a><br><br>");
                out.println("</div>");
            }  
            else {
                out.println("<div class='container center'>");
                out.println("<i class='large material-icons'>thumb_down</i><br>");
                out.println("<h4>Token invalid</h4><br><br>");
                out.println("</div>");
            }
       }
        
    %>    
    
    </body>
</html>
