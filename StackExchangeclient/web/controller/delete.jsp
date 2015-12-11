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
        String browserType = request.getHeader("User-Agent").substring(0, 10);
        String ipAddress  = request.getHeader("X-FORWARDED-FOR");
        if(ipAddress == null)
        {
          ipAddress = request.getRemoteAddr();
        }
        token += "#"+browserType+"#"+ ipAddress;
      }
    }
  }
  int uid = port.getQuestionUID(qid);
  port.deleteQuestion(qid,uid,token);
  
  String url = "/StackExchangeclient";
  response.sendRedirect(url);
%>