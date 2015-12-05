<%-- 
    Document   : register
    Created on : Nov 18, 2015, 12:40:34 AM
    Author     : Luqman A. Siswanto
--%>

<%
  UserWS.UserWS_Service uservice = new UserWS.UserWS_Service();
  UserWS.UserWS uport = uservice.getUserWSPort();
  
  String name = request.getParameter("name");
  String email = request.getParameter("email");
  String pass = request.getParameter("pass");
  boolean done = uport.emailDone(email);
  if(done) {
    String url = "../view/done.jsp";
    response.sendRedirect(url);
  } else {
    uport.createUser(name, email, pass);
    int uid = uport.getUIDByEmail(email);
    String token = uport.createToken(uid);
    Cookie cookie = new Cookie("auth", token);
    cookie.setMaxAge(3600);
    cookie.setPath("/");
    response.addCookie(cookie);
    String url = "../";
    response.sendRedirect(url);
  }
%>