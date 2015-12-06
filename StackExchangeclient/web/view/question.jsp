<%-- 
    Document   : question
    Created on : Nov 16, 2015, 6:09:23 PM
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
  String name = uport.getName(uid);
%>  
  
<!DOCTYPE html>
<html>
  <script src="../assets/angular/angular.min.js"></script>
  <script src="../assets/js/comment.js"></script>
  <script src="../assets/js/vote.js"></script>
  <head>
    <title>Question | Overflow48</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
    <link rel="icon" type="image/png" href="../assets/white-icon.jpg">
  </head>
  <body>
    <div class="container" ng-app="app">
      <h1 class="text-center"><a href="/StackExchangeclient">OVERFLOW48</a></h1>
      <form id="search" action="search.jsp" method="GET">
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
      
      <%
        QuestionWS.QuestionWS_Service qservice = new QuestionWS.QuestionWS_Service();
        QuestionWS.QuestionWS qport = qservice.getQuestionWSPort();
        AnswerWS.AnswerWS_Service aservice = new AnswerWS.AnswerWS_Service();
        AnswerWS.AnswerWS aport = aservice.getAnswerWSPort();
        
        int id = Integer.parseInt(request.getParameter("id"));
        QuestionWS.Question q = qport.getQuestionByQID(id).get(0);
        java.util.List<AnswerWS.Answer> answers = aport.getAnswerByQID(id);
      %>      
      
      <h2><a href="question.jsp?id=<%=id%>"><%=q.getTopic()%></a></h2>
      <div class="question">
        <hr class="line">
        <div class="item">
          <div class="vote">
            <table width="100%" class="text-center">
              <% int qv = qport.numVoteQuestion(uid, id); %>
              <% if(qv == 1) { %>
                <tr><td><div class="upvoted">&#9650;</div></td></tr>
              <% } else if(qv == 0 && uid != 0) { %>
                <tr><td><div class="upvote"><a href="../controller/upvoteQuestion.jsp?id=<%=id%>">&#9650;</a></div></td></tr>
              <% } else { %>
                <tr><td><div class="voted">&#9650;</div></td></tr>
              <% } %>
              <tr><td><div class="votes" id="voteQuestion"><%=q.getVote()%></div></td></tr>
              <% if(qv == 1 || uid == 0) { %>
                <tr><td><div class="voted">&#9660;</div></td></tr>
              <% } else if(qv == 0 && uid != 0) { %>
                <tr><td><div class="downvote"><a href="../controller/downvoteQuestion.jsp?id=<%=id%>">&#9660;</a></div></td></tr>
              <% } else { %>
                <tr><td><div class="downvoted">&#9660;</div></td></tr>
              <% } %>
            </table>
          </div>
          <div class="text-long">
            <p><%=q.getContent()%></p>
          </div>
          <div class="text-right">
            <p>asked by <%=q.getName()%> at <%=q.getDate()%>
            <% if(uid == q.getUid()) { %>
            | <a href="ask.jsp?id=<%=id%>" class="link">edit</a> | <a href="../controller/delete.jsp?id=<%=id%>" class="link">delete</a></p>
            <% } %>
          </div>
        </div>
      </div>
      
      <input type="hidden" id="qid" value="<%=id%>">
      <div ng-controller="comment">
        <div ng-repeat="c in comments">
          <div class="question">
            <hr class="line">
            <div class="item">
              <div class="vote">
                <table width="100%">
                  <tr><td></td></tr>
                </table>
              </div>
              <div class="text-long">
                <p class="comment">{{ c.content }}</p>
              </div>
              <div class="text-right">
                <p class="comment">commented by {{ c.name }} at {{ c.date }}</p>
              </div>
            </div>
          </div>
        </div>
        <p ng-click="show()" class="link" ng-bind="caption"></p>
        <div ng-show="showed">
          <% if(uid == 0) { %>
            <p>You must be logged in to comment this question.</p>
          <% } else { %>
              <input ng-model="qid" type="hidden" value="<%=id%>">
              <textarea ng-model="content" placeholder="Content" class="box" rows="5"></textarea>
              <div class="text-right">
                  <button ng-click="submit(<%=uid%>)" class="button" class="text-right" type="submit">Post</button>
              </div>
          <% } %>
        </div>
      </div>
      
      <br/> <h2><%=q.getSumAns()%> Answers</h2>

      <% for(AnswerWS.Answer a : answers) { %>
      <div class="question">
        <hr class="line">
        <div class="item">
          <div class="vote">
            <table width="100%" class="text-center">
              <% int av = aport.numVoteAnswer(uid, a.getId()); %>
              <% if(av == 1) { %>
                <tr><td><div class="upvoted">&#9650;</div></td></tr>
              <% } else if(av == 0 && uid != 0) { %>
                <tr><td><div class="upvote"><a href="../controller/upvoteAnswer.jsp?id=<%=a.getId()%>">&#9650;</a></div></td></tr>
              <% } else { %>
                <tr><td><div class="voted">&#9650;</div></td></tr>
              <% } %>
              <tr><td><div class="votes" id="voteQuestion"><%=a.getVote()%></div></td></tr>
              <% if(av == 1 || uid == 0) { %>
                <tr><td><div class="voted">&#9660;</div></td></tr>
              <% } else if(av == 0 && uid != 0) { %>
                <tr><td><div class="downvote"><a href="../controller/downvoteAnswer.jsp?id=<%=a.getId()%>">&#9660;</a></div></td></tr>
              <% } else { %>
                <tr><td><div class="downvoted">&#9660;</div></td></tr>
              <% } %>
            </table>
          </div>
          <div class="text-long">
            <p><%=a.getContent()%></p>
          </div>
          <div class="text-right">
            <p>answered by <%=a.getName()%> at <%=a.getDate()%></p>
          </div>
        </div>
      </div>
      <% } %>

      <br/><h3>Your Answer</h3>
      <% if(uid == 0) { %>
        <p>You must be logged in to answer this question.</p>
      <% } else { %>
        <form id="answer" action="../controller/answer.jsp" method="POST">
          <input name="id" type="hidden" value=<%=id%>>
          <textarea id="content" placeholder="Content" class="box" name="content" rows="5"></textarea>
          <div class="text-right">
              <button class="button" class="text-right" type="submit">Post</button>
          </div>
        </form>
      <% } %>
    </div>
        
  </body>
  <footer> <br><br> </footer>
</html>
