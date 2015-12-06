<%-- 
    Document   : addAnswerProcess
    Created on : Nov 24, 2015, 8:37:45 PM
    Author     : Marco Orlando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="4, <% out.println("url=questionAnswerPage.jsp?token=" + request.getParameter("token") + "&q_id=" + request.getParameter("questionId") + "\""); %>">
        <title>Add Answer to Particular Question </title>
    </head>
    <body>

    
    
    
        <%-- start web service invocation --%><hr/>
    <%
    try {
	wsmodel.WS_Service service = new wsmodel.WS_Service();
	wsmodel.WS port = service.getWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = request.getParameter("token");
	int questionId = Integer.parseInt(request.getParameter("questionId"));
	java.lang.String answerContent = request.getParameter("answerContent");
	// TODO process result here
	java.lang.String result = port.addAnswer(accessToken, questionId, answerContent);
	
        if (result.equals("valid")){
            out.println("success");
        } else if (result.equals("expired")) {
            out.println("failed<br/>token expired, please login again");
        } else if (result.equals("invalid")) {
            out.println("failed<br/>token invalid, please login again");     
        } else {
            out.println("failed<br/>");
            out.println(result);
        }
       
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>


        
        
    
    
    
        
        
        
        
        
        
        
    </body>
</html>
