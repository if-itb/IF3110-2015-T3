<%-- 
    Document   : newaccount.jsp
    Author     : moel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExcahange | Register</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            

            QuestionModule.QuestionWS_Service qservice = new QuestionModule.QuestionWS_Service();
            QuestionModule.QuestionWS port = qservice.getQuestionWSPort();
            if(qid != 0) {     // edit question
              port.updateQuestion(token,qid, topic, content);
            } else {
              port.insertQuestion(token, topic, content);
            }
            
            String url = "/Frontend_Webapp/displayQuestion.jsp?id=" + str;
            response.sendRedirect(url);

        %>
    </body>
</html>
