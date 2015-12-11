<%-- 
    Document   : search
    Created on : Nov 16, 2015, 6:09:31 PM
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
  String name = uport.getName(uid);
%>  

<!DOCTYPE html>
<html>
  <head>
    <title>Search | Overflow48</title>
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

      <p class="text-center">Cannot find what you are looking for? <a href="ask.jsp" class="link">Ask here.</a></p>
      
      <%
        QuestionWS.QuestionWS_Service service = new QuestionWS.QuestionWS_Service();
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        String query = request.getParameter("q");
        java.util.List<QuestionWS.Question> result = port.getQuestionByQuery(query);
        int cnt = result.size();
      %>

      <h3><%=cnt%> results about '<%=query%>'</h3>

      <% for(QuestionWS.Question q : result) { %>
        <div class="question">
          <hr class="line">
          <div class="item">
            <div class="vote">
              <p class="text-center">
                <a href="question.jsp?id=<%=q.getId()%>">
                  <%=q.getVote() %>
                </a>
              </p>
              <p class="text-center">
                <a href="question.jsp?id=<%=q.getId()%>">
                  Votes
                </a>
              </p>
            </div>
            <div class="answer">
              <p class="text-center">
                <a href="question.jsp?id=<%=q.getId()%>">
                  <%=q.getSumAns()%>
                </a>
              </p>
              <p class="text-center">
                <a href="question.jsp?id=<%=q.getId()%>">
                  Answers
                </a>
              </p>
            </div>
            <div class="text">
              <p><i><a href="question.jsp?id=<%=q.getId()%>"><%=q.getTopic()%></a></i></p>
            </div>
            <div class="text">
              <p><a href="question.jsp?id=<%=q.getId()%>"><%=q.getContent()%></a></p>
            </div>
            <div class="text-right">
              <p>asked by <%=q.getName()%>
                <% if(uid == q.getUid()) { %>
                | <a href="ask.jsp?id=<%=q.getId()%>" class="link">edit</a> | <a href="../controller/delete.jsp?id=<%=q.getId()%>" class="link">delete</a></p>
                <% } %>
            </div>
          </div>
        </div>
      <% } %>
    </div>

  </body>
  <footer> <br><br> </footer>
</html>