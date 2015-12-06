<%-- 
    Document   : insertAnswer
    Created on : Nov 27, 2015, 4:18:12 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>insertAnswer - TuBes WBD</title>
    </head>
    
        <% String t = "";
            Cookie [] cookieArray = request.getCookies();
            if(cookieArray != null){
                    for (int j=0; j<cookieArray.length;j++){
                        if(cookieArray[j].getName().equals("token")){
                            t = cookieArray[j].getValue();
                        }
                    }
                   }%>
        <% String id = request.getParameter("id");%>
        
        <%String ans = request.getParameter("answer");%>
    
    <body>
        
            <%-- start web service invocation --%><hr/>
    <%
    try {
	answermodel.AnswerWS_Service service = new answermodel.AnswerWS_Service();
	answermodel.AnswerWS port = service.getAnswerWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = t;
	int qid = Integer.valueOf(id);
	java.lang.String content = ans;
        int uid = port.getUserID(t);
        java.lang.String name = port.getUserName(t);
        java.lang.String email = port.getUserEmail(t);
        
	// TODO process result here
	int result = port.insertAnswer(accessToken, qid, uid, content, name, email);        
        if (result==1)
        {
            response.sendRedirect("http://localhost:8080/StackExchangeFE/answerlogin.jsp?id="+id);
        }
        else
        {
            response.sendRedirect("http://localhost:8082/WBD_IS/login.jsp?msg=sessiontimeout");
        }
    } catch (Exception ex) {
	out.println(ex);
    }
    %>
    <%-- end web service invocation --%><hr/>

        
        
    </body>
</html>
