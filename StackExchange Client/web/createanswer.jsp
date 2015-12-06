<%-- 
    Document   : createanswer
    Created on : Nov 26, 2015, 2:00:40 PM
    Author     : acel
--%>

<%@page import="userWebService.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%
    try {
	answerWebService.AnswerWebService_Service service = new answerWebService.AnswerWebService_Service();
	answerWebService.AnswerWebService port = service.getAnswerWebServicePort();
	userWebService.UserWebService_Service userService = new userWebService.UserWebService_Service();
        userWebService.UserWebService userPort = userService.getUserWebServicePort();
        java.lang.String token = request.getParameter("token");
        int qid = Integer.parseInt(request.getParameter("qid"));
	java.lang.String content = request.getParameter("content");
        int id = Integer.parseInt(request.getParameter("uid"));
        User user = userPort.getUser(id);
	String ip = request.getParameter("ip");
        String ua = request.getHeader("User-Agent");
        java.lang.String result = port.addAnswer(token, qid, user.getName(), user.getEmail(), content, id, ip, ua);
        if(result.equals("executed")) {
          response.sendRedirect("question.jsp?token=" + request.getParameter("token")
                                        + "&id=" + request.getParameter("uid") + "&qid=" + request.getParameter("qid"));
        } else {
          response.sendRedirect("error.jsp");
        }
    } catch (Exception ex) {
        out.println("gagal");
    }
    
    %>
