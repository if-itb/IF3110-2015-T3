<%-- 
    Document   : upvoteQuestion
    Created on : Nov 17, 2015, 8:07:56 PM
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
      }
    }
  }
  int uid = uport.getUID(token);
  if(uid == 0) {
    String url = "../view/login.jsp";
    response.sendRedirect(url);
  } else {
    int qid = Integer.parseInt(request.getParameter("id"));
    int type = +1;

    QuestionWS.QuestionWS_Service service = new QuestionWS.QuestionWS_Service();
    QuestionWS.QuestionWS port = service.getQuestionWSPort();

    port.voteQuestion(qid, uid, type);

    String url = "../view/question.jsp?id=" + qid;
    response.sendRedirect(url);
  }
%>