<%-- 
    Document   : delete.jsp
    Created on : Nov 17, 2015, 7:47:45 PM
    Author     : Luqman A. Siswanto
--%>

<%
  String token = "";
  
  QuestionWS.QuestionWS_Service qservice = new QuestionWS.QuestionWS_Service();
  QuestionWS.QuestionWS port = qservice.getQuestionWSPort();
  int qid = Integer.parseInt(request.getParameter("id"));
  Cookie[] cookies = null;
  cookies = request.getCookies();
  if(cookies != null) {
    for(Cookie cookie : cookies) {
      if(cookie.getName().equals("auth")) {
        token = cookie.getValue();
      }
    }
  }
  int uid = port.getQuestionUID(qid);
  port.deleteQuestion(qid,uid,token);
  
  String url = "/StackExchangeclient";
  response.sendRedirect(url);
%>