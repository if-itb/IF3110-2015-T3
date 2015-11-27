<%-- 
    Document   : updateQuestion
    Created on : Nov 24, 2015, 9:12:42 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
        <% String t = request.getParameter("token");%>
        <% String s = request.getParameter("id");%>
        
        <%String tit = request.getParameter("topic");%>
        <%String con = request.getParameter("content");%>
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>udpateQuestion - TuBes WBD</title>
    </head>
    
    <body>
        
            <%-- start web service invocation --%><hr/>
            <%
            try {
                questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
                questionmodel.QuestionWS port = service.getQuestionWSPort();
                 // TODO initialize WS operation arguments here
                java.lang.String accessToken = t;
                int id = Integer.valueOf(s);
                java.lang.String topic = tit;
                java.lang.String content = con;
                int uid = port.getUserID(t);
                out.println(accessToken);
                out.println(id);
                out.println(uid);
                out.println(topic);
                out.println(content);
                // TODO process result here
                int result = port.updateQuestion(accessToken, id, uid, topic, content);
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
