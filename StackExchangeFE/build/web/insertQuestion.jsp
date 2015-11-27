<%-- 
    Document   : insertQuestion.jsp
    Created on : Nov 24, 2015, 11:50:11 AM
    Author     : Ben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <% String t = request.getParameter("token");%>
        
        <%String tit = request.getParameter("topic");%>
        <%String con = request.getParameter("content");%>
        

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>insertQuestion - TuBes WBD</title>
    </head>
        
    <body>
        
          <%-- start web service invocation --%><hr/>
    <%
    try {
                questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
                questionmodel.QuestionWS port = service.getQuestionWSPort();
                 // TODO initialize WS operation arguments here
                java.lang.String accessToken = t;
                java.lang.String title = tit;
                java.lang.String content = con;
                java.util.List<questionmodel.Question> results = port.getQuestion();
                int id = port.getUserID(t);
                String n = port.getUserName(t);
                String e = port.getUserEmail(t);
                // TODO process result here
                int result = port.insertQuestion(accessToken, title, content, id, n, e);
                if (result==1)
                {
                    response.sendRedirect("http://localhost:8080/StackExchangeFE/homepagelogin.jsp?token="+t);
                }
                else
                {
                    response.sendRedirect("http://localhost:8082/WBD_IS/login.jsp?msg=sessiontimeout");
                }
    } catch (Exception ex) {
	// TODO handle custom exceptions here
        out.println(ex);
    }
    %>
    <%-- end web service invocation --%><hr/>

    
        
        
    </body>
</html>
