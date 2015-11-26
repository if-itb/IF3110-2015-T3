<%-- 
    Document   : voteQuestion.jsp
    Author     : moel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExcahange | Question</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%
            int qid = Integer.parseInt(request.getParameter("id"));
            String v = request.getParameter("up");
            boolean up;
            if (v=="true") up=true;
            else up=false;

            QuestionModule.QuestionWS_Service qservice = new QuestionModule.QuestionWS_Service();
            QuestionModule.QuestionWS port = qservice.getQuestionWSPort();
            String token = "123";
            port.voteQuestion(qid,up, token);

            String url = "/Frontend_Webapp/";
            response.sendRedirect(url);
        %>
    </body>
</html>
