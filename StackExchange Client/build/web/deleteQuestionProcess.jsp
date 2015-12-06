<%-- 
    Document   : deleteQuestionProcess
    Created on : Nov 24, 2015, 5:56:48 AM
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
        <title>Delete Question Process</title>
         <script type="text/javascript" src="checklogin.js"></script>
    </head>
    <body>
        <br><br><br>
        <%-- start web service invocation --%>
    <%
    try {
	wsmodel.WS_Service service = new wsmodel.WS_Service();
	wsmodel.WS port = service.getWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = request.getParameter("token");
	int questionId = Integer.parseInt(request.getParameter("id"));
	// TODO process result here
	java.lang.String result = port.deleteQuestion(accessToken, questionId);
        
	if (result.equals("valid")){
            out.println("<div class='container center'>");
            out.println("<i class='large material-icons'>thumb_up</i><br>");
            out.println("<h4>Delete Success</h4><br><br>");
            out.println("</div>");
            out.println("<div class='row center'>");
            out.println("<a href=\"index.jsp?token=" + accessToken + "\"id='download-button' class='btn-large waves-effect waves-light orange'>Home</a><br><br>");
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
            out.println("<h4>Failed delete question.</h4><br><br>");
            out.println("</div>");
            out.println("<div class='row center'>");
            out.println("<a href=\"index.jsp?token=" + accessToken + "\"id='download-button' class='btn-large waves-effect waves-light orange'>Home</a><br><br>");
            out.println("</div>");
            out.println(result);
        }
        
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="../../bin/materialize.js"></script>
    <script src="js/init.js"></script>         
    </body>
</html>
