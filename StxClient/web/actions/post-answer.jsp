<%-- 
    Document   : post-answer.jsp
    Created on : Nov 27, 2015, Nov 27, 2015 8:12:32 AM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%-- start web service invocation --%><hr/>
    <%

/*
          */
    %>
    <%-- end web service invocation --%><hr/>
    <%-- start web service invocation --%><hr/>
    <%
	AnswerWS.AnswerWS_Service service = new AnswerWS.AnswerWS_Service();
	AnswerWS.AnswerWS port = service.getAnswerWSPort();
	 // TODO initialize WS operation arguments here
	int qid = Integer.parseInt(request.getParameter("id"));
	java.lang.String name = session.getAttribute("sessionName").toString();
	java.lang.String email = session.getAttribute("sessionEmail").toString();
	java.lang.String content = request.getParameter("Jawaban");
	// TODO process result here
	int result = port.postAns(qid, session.getAttribute("token").toString(), content);
        if(result < 0) {
            String site = "../login.jsp";
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        } else {
            String site = "../view-question.jsp?id="+qid;
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        }
    %>
    <%-- end web service invocation --%><hr/>

