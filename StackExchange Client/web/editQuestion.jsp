<%-- 
    Document   : editQuestion
    Created on : Nov 26, 2015, 11:37:20 AM
    Author     : William Sentosa
--%>

<%@page import="userWebService.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  try {
      questionWebService.QuestionWebService_Service service = new questionWebService.QuestionWebService_Service();
      questionWebService.QuestionWebService port = service.getQuestionWebServicePort();
      String token = "";
      Cookie[] cookies = request.getCookies();
      for(Cookie temp : cookies){
          if(temp.getName().equals("token")){
              token = temp.getValue();
          } 
      }
      String topic = request.getParameter("topic");
      String content = request.getParameter("content");
      int id = Integer.parseInt(request.getParameter("id"));
      int qid= Integer.parseInt(request.getParameter("qid"));
      userWebService.UserWebService_Service userService = new userWebService.UserWebService_Service();
      userWebService.UserWebService userPort = userService.getUserWebServicePort();
      String ip = request.getParameter("ip");
      String ua = request.getHeader("User-Agent");
      String result = port.editQuestion(token, qid, topic, content, id, ip, ua);
      if(result.equals("executed")) {
          response.sendRedirect("index.jsp?token=" + token + "&id=" + id);
      } else {
          response.sendRedirect("error.jsp");
      }
  } catch (Exception ex) {
      out.println("Gagal");
  }
%>

