
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    
        <% String t = request.getParameter("token");%>
        <% String s = request.getParameter("id");%>
        <% String v = request.getParameter("value");%>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>voteQue - TuBes WBD</title>
    </head>
    
    <body>
    <%-- start web service invocation --%><hr/>
    <%
    try {
	questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
	questionmodel.QuestionWS port = service.getQuestionWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = t;
	int qId = Integer.valueOf(s);
        int val = 0;
	if (v.equals("plus"))
        {
            val = 1;
        }
        else
        {
            val = -1;
        }
	// TODO process result here
	int result = port.voteQuestion(accessToken, qId, val);
        if (result==1)
        {
            response.sendRedirect("http://localhost:8080/StackExchangeFE/answerlogin.jsp?id="+s+"&token="+t);
        }
        else
        {
            response.sendRedirect("http://localhost:8082/WBD_IS/login.jsp?msg=sessiontimeout");
        }
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>

    </body>
</html>
