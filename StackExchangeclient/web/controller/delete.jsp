<%-- 
    Document   : delete.jsp
    Created on : Nov 17, 2015, 7:47:45 PM
    Author     : Luqman A. Siswanto
--%>

<%
  int qid = Integer.parseInt(request.getParameter("id"));
  
  QuestionWS.QuestionWS_Service qservice = new QuestionWS.QuestionWS_Service();
  QuestionWS.QuestionWS port = qservice.getQuestionWSPort();
  
  port.deleteQuestion(qid);
  
  String url = "/StackExchangeclient";
  response.sendRedirect(url);
%>