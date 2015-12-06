<%-- 
    Document   : registrationProcess
    Created on : Nov 25, 2015, 1:25:44 PM
    Author     : Marco Orlando
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
                
            </div>
        </nav>
        <br><br>
    <%
    try {
	wsmodel.WS_Service service = new wsmodel.WS_Service();
	wsmodel.WS port = service.getWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String userName = request.getParameter("name");
	java.lang.String password = request.getParameter("password");
	java.lang.String email = request.getParameter("email");
	// TODO process result here
	java.lang.Boolean result = port.addUser(userName, password, email);
	
        if (result) {
            out.println("<div class='container center'>");
            out.println("<i class='large material-icons'>thumb_up</i><br>");
            out.println("<h4>Registration Success</h4><br><br>");
            out.println("</div>");
            out.println("<div class='row center'>");
            out.println("<a href='login.jsp' id='download-button' class='btn-large waves-effect waves-light orange'>Login</a><br><br>");
            out.println("</div>");
        } else {
            out.println("<div class='container center'>");
            out.println("<i class='large material-icons'>thumb_down</i><br>");
            out.println("<h4>Registration Failed. Email already exists</h4><br><br>");
            out.println("</div>");
            out.println("<div class='row center'>");
            out.println("<a href='login.jsp' id='download-button' class='btn-large waves-effect waves-light orange'>Login</a><br><br>");
            out.println("</div>");
        }
        
        
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%>
    </body>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="../../bin/materialize.js"></script>
    <script src="js/init.js"></script> 
</html>
