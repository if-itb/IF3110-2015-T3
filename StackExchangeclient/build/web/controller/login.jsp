<%-- 
    Document   : login
    Created on : Nov 18, 2015, 12:27:18 AM
    Author     : Luqman A. Siswanto
--%>

<%
  UserWS.UserWS_Service uservice = new UserWS.UserWS_Service();
  UserWS.UserWS uport = uservice.getUserWSPort();
  
  String email = request.getParameter("email");
  String pass = request.getParameter("pass");
  boolean match = uport.match(email, pass);
  if(match) {
    int uid = uport.getUIDByEmail(email);
    String token = uport.createToken(uid);
    Cookie cookie = new Cookie("auth", token);
    cookie.setMaxAge(3600);
    cookie.setPath("/");
    response.addCookie(cookie);
    String url = "../";
    response.sendRedirect(url);
  } else {
    String url = "../view/mismatch.jsp";
    response.sendRedirect(url);
  }
%>