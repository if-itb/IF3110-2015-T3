

<%-- 
    Document   : delete..jsp
    Created on : Nov 27, 2015, Nov 27, 2015 12:32:04 AM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    int q_id = Integer.parseInt(request.getParameter("id"));
    out.println(q_id);
	QuestionWS.QuestionWS_Service service = new QuestionWS.QuestionWS_Service();
	QuestionWS.QuestionWS port = service.getQuestionWSPort();
	 // TODO initialize WS operation arguments here
	// TODO process result here
	int result = port.delete(q_id);
	// TODO handle custom exceptions here
    String site = "../index.jsp";
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
%>
