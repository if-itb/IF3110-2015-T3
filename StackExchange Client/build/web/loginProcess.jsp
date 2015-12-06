<%-- 
    Document   : loginProcess
    Created on : Nov 25, 2015, 9:01:49 PM
    Author     : vickonovianto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container"><a id="logo-container" <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %> class="brand-logo">Stack Exchange</a>
                <ul class="right hide-on-med-and-down">
                    <li><a <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %>>Home</a></li>
                    <li><a href="#">Register</a></li>
                </ul>
            </div>
        </nav>
        <br><br>
            <%-- start login process --%>
   <%
       String token = null;
       if (request.getAttribute("success").equals("true")) {
            out.println("<div class='container center'>");
            out.println("<i class='large material-icons'>thumb_up</i><br>");
            out.println("<h4>Login Success</h4><br><br>");
            out.println("</div>");
            token = (String)request.getAttribute("token");
            out.println("<div class='row center'>");
            out.println("<a href=\"index.jsp?token=" + token + "\"id='download-button' class='btn-large waves-effect waves-light orange'>Home</a><br><br>");
            out.println("</div>");
            
       } else if (request.getAttribute("success").equals("false")) {
           //out.println("failed");
           if (request.getAttribute("error_cause").equals("email")) {
               out.println("<div class='container center'>");
               out.println("<i class='large material-icons'>thumb_down</i><br>");
               out.println("<h4>Email not found</h4><br><br>");
               out.println("</div>");
               out.println("<div class='row center'>");
               out.println("<a href='login.jsp' id='download-button' class='btn-large waves-effect waves-light orange'>Login</a><br><br>");
               out.println("</div>");
           } else if (request.getAttribute("error_cause").equals("password")) {
               out.println("<div class='container center'>");
               out.println("<i class='large material-icons'>thumb_down</i><br>");
               out.println("<h4>Wrong password</h4><br><br>");
               out.println("</div>");
               out.println("<div class='row center'>");
               out.println("<a href='login.jsp' id='download-button' class='btn-large waves-effect waves-light orange'>Login</a><br><br>");
               out.println("</div>");
           }
       }
        if (token == null) {
            out.println("<a href=\"index.jsp\">");
        } 
        else {
            out.println("<a href=\"index.jsp?token=" + token + "\">");
        }
    %>    
    
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="../../bin/materialize.js"></script>
    <script src="js/init.js"></script> 
    </body>
</html>
