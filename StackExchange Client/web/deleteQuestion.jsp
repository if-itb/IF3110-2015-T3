<%-- 
    Document   : deleteQuestion
    Created on : Nov 26, 2015, 12:00:33 PM
    Author     : William Sentosa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  try {
      questionWebService.QuestionWebService_Service service = new questionWebService.QuestionWebService_Service();
      questionWebService.QuestionWebService port = service.getQuestionWebServicePort();
      String token = request.getParameter("token");
      int id = Integer.parseInt(request.getParameter("id"));
      int qid= Integer.parseInt(request.getParameter("qid"));
      String ip = request.getParameter("ip");
      String ua = request.getHeader("User-Agent");
      String result = port.deleteQuestion(token, qid, id, ip, ua);
      if(result.equals("executed")) {
          response.sendRedirect("index.jsp?token=" + token + "&id=" + id);
      } else {
          response.sendRedirect("error.jsp");
      }
      
  } catch (Exception ex) {
      out.println("Gagal");
  }
%>
