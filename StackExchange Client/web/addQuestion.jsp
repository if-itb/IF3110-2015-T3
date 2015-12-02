<%-- 
    Document   : addQuestion
    Created on : Nov 25, 2015, 8:48:07 PM
    Author     : William Sentosa
--%>

<%@page import="userWebService.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  try {
      questionWebService.QuestionWebService_Service service = new questionWebService.QuestionWebService_Service();
      questionWebService.QuestionWebService port = service.getQuestionWebServicePort();
      String token = request.getParameter("token");
      String topic = request.getParameter("topic");
      String content = request.getParameter("content");
      String identification = request.getParameter("uid");
      int id = Integer.parseInt(identification);
      userWebService.UserWebService_Service userService = new userWebService.UserWebService_Service();
      userWebService.UserWebService userPort = userService.getUserWebServicePort();
      User user = userPort.getUser(id);
      String result = port.addQuestion(token, user.getName(), user.getEmail(), topic, content, id);
      if(result.equals("executed")) {
        response.sendRedirect("index.jsp?token=" + token + "&id=" + id);
      } else {
        response.sendRedirect("error.jsp");
      }
  } catch (Exception ex) {
      out.println("Gagal");
  }
%>
