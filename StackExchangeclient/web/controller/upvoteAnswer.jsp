<%-- 
    Document   : upvoteAnswer
    Created on : Nov 17, 2015, 8:08:16 PM
    Author     : Luqman A. Siswanto
--%>

<%
  UserWS.UserWS_Service uservice = new UserWS.UserWS_Service();
  UserWS.UserWS uport = uservice.getUserWSPort();
  
  String token = "";
  Cookie[] cookies = null;
  cookies = request.getCookies();
  if(cookies != null) {
    for(int i = 0; i < cookies.length; i++) {
      Cookie cookie = cookies[i];
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
    int aid = Integer.parseInt(request.getParameter("id"));
    int type = +1;

    AnswerWS.AnswerWS_Service aservice = new AnswerWS.AnswerWS_Service();
    AnswerWS.AnswerWS port = aservice.getAnswerWSPort();

    int qid = port.getAnswerQID(aid);
    port.voteAnswer(aid, uid, type);

    String url = "../view/question.jsp?id=" + qid;
    response.sendRedirect(url);
  }
%>