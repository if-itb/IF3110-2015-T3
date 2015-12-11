<%-- 
    Document   : ask
    Created on : Nov 16, 2015, 6:09:04 PM
    Author     : Luqman A. Siswanto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    String url = "login.jsp";
    response.sendRedirect(url);
  } else {
    String name = uport.getName(uid);
%>  
  
<!DOCTYPE html>
<html>
  <head>
    <title>Ask Question | Overflow48</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
    <link rel="icon" type="image/png" href="../assets/white-icon.jpg">
    
  </head>
  <body>
    <div class="container">
      <h1 class="text-center"><a href="/StackExchangeclient">OVERFLOW48</a></h1>
      <form id="search" action="search.jsp" action="GET">
        <table>
        <tr>
          <td width="200%"> <input id="q" placeholder="What are you looking for?" type="text" class="form" name="q"></td>
          <td width="20%"> <button class="button" type="submit">Search</button> </td>
        </tr>
        </table>
      </form>
      <% if(uid == 0) { %>
      <p class="text-right"><a href="login.jsp" class="link">Login</a> | <a href="register.jsp" class="link">Register</a></p>
      <% } else { %>
      <p class="text-right"><%=name%> | <a href="../controller/logout.jsp" class="link">Logout</a></p>
      <% } %>
      <h2>What's your question?</h2>
      <%
        QuestionWS.QuestionWS_Service service = new QuestionWS.QuestionWS_Service();
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        
        QuestionWS.Question q = new QuestionWS.Question();
        String str = request.getParameter("id");
        if(str != null) {
          int id = Integer.parseInt(str);
          q = port.getQuestionByQID(id).get(0);
        }
      %>
      <hr class="line">
      <form id="ask" action="../controller/ask.jsp" method="POST">
        <input name="id" type="hidden" value="<%=q.getId()%>">
        <input id="topic" placeholder="Question Topic" class="form" type="text" name="topic" value="<%=(str != null? q.getTopic() : "")%>">
        <textarea id="content" placeholder="Content" rows="4" class="box" name="content"><%=(str != null? q.getContent() : "")%></textarea>
        <div class="text-right">
            <button class="button" type="submit">Post</button>
        </div>
      </form>
    </div>
    
  </body>
  <footer> <br><br> </footer>
</html>

<%
  }
%>