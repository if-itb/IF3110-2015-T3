<%-- 
    Document   : ask.jsp
    Created on : Nov 17, 2015, 7:47:33 PM
    Author     : Luqman A. Siswanto
--%>

<%
  UserWS.UserWS_Service uservice = new UserWS.UserWS_Service();
  UserWS.UserWS uport = uservice.getUserWSPort();
  
  String token = "";
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
  int uid = uport.getUID(token);
  
  if(uid == 0) {
    String url = "../view/login.jsp";
    response.sendRedirect(url);
  } else {
    String str = request.getParameter("id");
    int qid = Integer.parseInt(str);
    String topic = request.getParameter("topic");
    String content = request.getParameter("content");

    QuestionWS.QuestionWS_Service qservice = new QuestionWS.QuestionWS_Service();
    QuestionWS.QuestionWS port = qservice.getQuestionWSPort();
    if(qid != 0) {     // update question (id question ada)
      uid = port.getQuestionUID(qid);
      port.updateQuestion(qid, topic, content, uid, token);
    } else {
      port.insertQuestion(uid, topic, content);
    }

    String url = "/StackExchangeclient";
    response.sendRedirect(url);
  }
%>