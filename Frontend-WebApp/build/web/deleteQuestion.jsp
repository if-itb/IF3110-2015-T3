<%-- 
    Document   : deleteQuestion.jsp
    Author     : moel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Simple StackExcahange | Question</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%
            int qid = Integer.parseInt(request.getParameter("id"));

            QuestionModule.QuestionWS_Service qservice = new QuestionModule.QuestionWS_Service();
            QuestionModule.QuestionWS port = qservice.getQuestionWSPort();
            String token = "123";
            port.deleteQuestion(token,qid);

            String url = "/Frontend_Webapp/";
            response.sendRedirect(url);
          %>
    </body>
</html>
