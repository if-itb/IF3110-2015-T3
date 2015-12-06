<%-- 
    Document   : addQuestionProcess
    Created on : Nov 23, 2015, 10:20:17 PM
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
        <meta http-equiv="refresh" content="4, <% out.println("url=index.jsp?token=" + request.getParameter("token") + "\""); %>">
        <title>add Question Processing</title>
    </head>
    
    <body>
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container"><a id="logo-container" <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %> class="brand-logo">Stack Exchange</a>
            </div>
        </nav>
        <br><br>
         <%-- start web service invocation --%>
    <%
    try {
	wsmodel.WS_Service service = new wsmodel.WS_Service();
	wsmodel.WS port = service.getWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String questionTitle = request.getParameter("questionTopic");
	java.lang.String questionContent = request.getParameter("questionContent");
	java.lang.String access_token = request.getParameter("token");
	// TODO process result here
	java.lang.String result = port.addQuestion(access_token, questionTitle, questionContent);
	
        if (result.equals("valid")){
            out.println("<div class='container center'>");
            out.println("<i class='large material-icons'>thumb_up</i><br>");
            out.println("<h4>Add Question Success</h4><br><br>");
            out.println("</div>");
            out.println("<div class='row center'>");
            out.println("<a href=\"index.jsp?token=" + access_token + "\"id='download-button' class='btn-large waves-effect waves-light orange'>Home</a><br><br>");
            out.println("</div>");
        } else if (result.equals("expired")) {
            out.println("<div class='container center'>");
            out.println("<i class='large material-icons'>thumb_down</i><br>");
            out.println("<h4>Failed, token expired. Please login again</h4><br><br>");
            out.println("</div>");
            out.println("<div class='row center'>");
            out.println("<a href='login.jsp' id='download-button' class='btn-large waves-effect waves-light orange'>Login</a><br><br>");
            out.println("</div>");
        } else if (result.equals("invalid")) {
            out.println("<div class='container center'>");
            out.println("<i class='large material-icons'>thumb_down</i><br>");
            out.println("<h4>Failed, token invalid. Please login again</h4><br><br>");
            out.println("</div>");
            out.println("<div class='row center'>");
            out.println("<a href='login.jsp' id='download-button' class='btn-large waves-effect waves-light orange'>Login</a><br><br>");
            out.println("</div>");
        } else {
            out.println("<div class='container center'>");
            out.println("<i class='large material-icons'>thumb_down</i><br>");
            out.println("<h4>Failed add question.</h4><br><br>");
            out.println("</div>");
            out.println("<div class='row center'>");
            out.println("<a href=\"index.jsp?token=" + access_token + "\"id='download-button' class='btn-large waves-effect waves-light orange'>Home</a><br><br>");
            out.println("</div>");
            //out.println(result);
        }
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    
    
    %>
    <%-- end web service invocation --%>


    <%--String site = new String("index.jsp"); 
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);--%>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="../../bin/materialize.js"></script>
    <script src="js/init.js"></script>     
    </body>
</html>
