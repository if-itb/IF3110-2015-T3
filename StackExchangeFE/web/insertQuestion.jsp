<%-- 
    Document   : insertQuestion.jsp
    Created on : Nov 24, 2015, 11:50:11 AM
    Author     : Ben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        
    <body>
        
        <%-- start web service invocation --%><hr/>
        
        <%
            try {
                questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
                questionmodel.QuestionWS port = service.getQuestionWSPort();
                 // TODO initialize WS operation arguments here
                java.lang.String accessToken = "";
                java.lang.String title = "";
                java.lang.String content = "";
                // TODO process result here
                int result = port.insertQuestion(accessToken, title, content);
                out.println("Result = "+result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        
        <%-- end web service invocation --%><hr/>
    
        
        
    </body>
</html>
