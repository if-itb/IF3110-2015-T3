<%-- 
    Document   : addQuestion.jsp
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
            String str = request.getParameter("id");
            int qid = Integer.parseInt(str);
            String topic = request.getParameter("topic");
            String content = request.getParameter("content");
            String token = "1";// not implemented yett

            QuestionModule.QuestionWS_Service qservice = new QuestionModule.QuestionWS_Service();
            QuestionModule.QuestionWS port = qservice.getQuestionWSPort();
            if(qid != 0) {     // edit question
              port.updateQuestion(token,qid, topic, content);
            } else {
              port.insertQuestion(token, topic, content);
            }
            
            //String url = "/Frontend_Webapp/displayQuestion.jsp?id=" + str;
            //response.sendRedirect(url);

        %>
    </body>
    

</html>