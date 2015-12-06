<%-- 
    Document   : edit
    Created on : Nov 30, 2015, 12:47:42 PM
    Author     : Raihan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ask a Question!</title>
    </head>
    <body>       <%-- start web service invocation --%><hr/>
        <%!
        stackexchange.Question Q = null;
        %>
    <%
    try {
	stackexchange.Question_Service service = new stackexchange.Question_Service();
	stackexchange.QuestionWS port = service.getQuestionWSPort();
	 // TODO initialize WS operation arguments here
	int qid = Integer.parseInt(request.getParameter("id"));
	// TODO process result here
	Q = port.getQuestionByID(qid);	
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
     
        <center><a href='index.jsp'> <h1>Simple Stack Exchange</h1> </a>
            <form name='question' action='edit' method='POST'>
                <input type='text' name='topic' placeholder='Question Topic' size='80' value="<%out.println(Q.getTopic());%>"><br><br>
                <textarea name='qcontent' placeholder="Question"  style="resize:none;width:500px;"><%out.println(Q.getContent());%></textarea><br><br>
                <input type ="hidden" name="id" value="<%out.println(Q.getId());%>">;
                <input type="submit" value="Submit">
            </form>
            
            
        </center>
    </body>
</html>