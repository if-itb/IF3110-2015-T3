<%-- 
    Document   : avotedown
    Created on : Nov 26, 2015, 2:48:34 PM
    Author     : acel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%
    try {
	answerWebService.AnswerWebService_Service service = new answerWebService.AnswerWebService_Service();
	answerWebService.AnswerWebService port = service.getAnswerWebServicePort();
	java.lang.String token = request.getParameter("token");
	int aid = Integer.parseInt(request.getParameter("aid"));
	int qid = Integer.parseInt(request.getParameter("qid"));
        int uid = Integer.parseInt(request.getParameter("id"));
	java.lang.String result = port.decrVote(token, aid, qid, uid);
	out.println(token);
        out.println(aid);
        out.println(uid);
        if(result.equals("executed")) {
          response.sendRedirect("question.jsp?token=" + request.getParameter("token") + "&id=" + request.getParameter("id") + "&qid=" + request.getParameter("qid"));
        } else {
          response.sendRedirect("error.jsp");
        }
    } catch (Exception ex) {
        out.println("gagal");
    }
    %>
