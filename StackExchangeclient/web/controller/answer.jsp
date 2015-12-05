<%-- 
    Document   : answer
    Created on : Nov 17, 2015, 8:21:10 PM
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
      }
    }
  }
  int uid = uport.getUID(token);
  if(uid == 0) {
    String url = "../view/login.jsp";
    response.sendRedirect(url);
  } else {
    int qid = Integer.parseInt(request.getParameter("id"));
    String content = request.getParameter("content");

    AnswerWS.AnswerWS_Service aservice = new AnswerWS.AnswerWS_Service();
    AnswerWS.AnswerWS port = aservice.getAnswerWSPort();
    port.insertAnswer(uid, qid, content);

    String url = "../view/question.jsp?id=" + qid;
    response.sendRedirect(url);
  }
%>