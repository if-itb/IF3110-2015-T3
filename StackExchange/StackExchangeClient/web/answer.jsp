<%-- 
    Document   : answer
    Created on : Nov 18, 2015, 9:40:20 AM
    Author     : Gerry
--%>

<%@page import="java.util.List"%>
<%@page import="stackexchangews.Answer"%>
<%@page import="stackexchangews.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
  <head>
      <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
      <title>Stack Exchange</title>
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
      <link href="css/register-login.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  </head>
  
  <%
      String token = (String)request.getParameter("token");
  %>
  <body ng-controller="myCtrl" ng-init="qid=<% out.print(request.getParameter("qid"));%>; token=<% out.print("'"+token+"'");%>">
    <nav class="deep-purple darken-2" role="navigation">
      <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Stack Exchange - Answer</a>
        <ul class="right hide-on-med-and-down">
          <li><a href="index.jsp?token=<%out.print(token);%>">Home</a></li>
          <li><a href="register.jsp">Register</a></li>
          <li><a href="login.jsp">Login</a></li>
          <li><a href="index.jsp?token=null">Logout</a></li>
        </ul>

        <ul id="nav-mobile" class="side-nav">
          <li><a href="index.jsp?token=<%out.print(token);%>">Home</a></li>
          <li><a href="register.jsp">Register</a></li>
          <li><a href="login.jsp">Login</a></li>
        </ul>
        <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
      </div>
    </nav>
    <br><br><br>
    <div class="container">
      <nav class="deep-purple darken-2" role="navigation">
        <div class="nav-wrapper">
          <form>
            <div class="input-field">
              <input id="search" type="search" required>
              <label for="search"><i class="material-icons">search</i></label>
              <i class="material-icons">close</i>
            </div>
          </form>
        </div>
      </nav>
    </div>
    <br><br>
    
    <div class="section white">
        <%
            Question q = (Question)request.getAttribute("question");
            String qname = (String)request.getAttribute("qname");
            out.println("<div class='container'>");
            out.println("<div class='card deep-purple darken-2'>");
            out.println("<div class='card-content white-text'>");
            out.println("<span class='card-title'>" + q.getTopic() + "</a></span>");
            out.println("<p>" + q.getContent() + "</p>");
            out.println("</div>");
            out.println("<div class='card-action'>");
            out.println("<a class='left' href='#'>Asked by " + qname + "</a>");
        %>
        <div ng-init="qvote=<% out.print(q.getVote());%>">
            <a href="" ng-click="voteUpQ()">
                <i class="left small deep-purple darken-2 material-icons" style="padding-left:20px">thumb_up</i>
            </a>
            <a class='left' style='padding-left:5px'>{{ qvote }}</a>
            <a href="" ng-click="voteDownQ()">
                <i class="left small deep-purple darken-2 material-icons" style="padding-left:20px">thumb_down</i>
            </a>
        </div>
        <script>
            var app = angular.module('myApp', []);
            app.controller('myCtrl', ['$scope', '$http', function($scope, $http) {
                $scope.avote = [];
                $scope.comments = [];
                $scope.voteUpQ = function() {
                    $http.get('http://localhost:8081/CommentService/votequestion?qid=' + $scope.qid + '&type=1&token=' + $scope.token).then($scope.getVote);
                };
                $scope.voteDownQ = function() {
                    $http.get('http://localhost:8081/CommentService/votequestion?qid=' + $scope.qid + '&type=-1&token=' + $scope.token).then($scope.getVote);
                };
                $scope.getVote = function() {
                    $http.get('http://localhost:8081/CommentService/GetVoteServlet?qid=' + $scope.qid)
                        .then(function(response) {$scope.qvote = response.data;});
                };
                $scope.addComment = function() {
                    $http.get('http://localhost:8081/CommentService/InsertComment?qid=' + $scope.qid
                            + '&content=' + $scope.content
                            + '&token=' + $scope.token).then($scope.getComment);
                };
                $scope.voteUpA = function(aid) {
                    $http.get('http://localhost:8081/CommentService/voteanswer?aid=' + aid + '&type=1&token=' + $scope.token).then($scope.getAnswerVote(aid));
                };
                $scope.voteDownA = function(aid) {
                    $http.get('http://localhost:8081/CommentService/voteanswer?aid=' + aid + '&type=-1&token=' + $scope.token).then($scope.getAnswerVote(aid));
                };
                $scope.getAnswerVote = function(aid) {
                    $http.get('http://localhost:8081/CommentService/GetAnswerVote?aid=' + aid)
                        .then(function(response) {$scope.avote[aid] = response.data;});
                };
                $scope.getComment = function() {
                    $http.get('http://localhost:8081/CommentService/GetComment?qid=' + $scope.qid)
                        .then(function(response) {$scope.comments = response.data;});
                };
            }]);
        </script>
        <%    
            out.println("<a class='right' href='edit.jsp?qid=" + q.getId() + "&token=" + token + "'>Edit</a>");
            out.println("<a class='right' href='delete?qid=" + q.getId() + "&token=" + token + "'>Delete</a>");
            out.println("</div></div></div>");
        %>
        <div ng-init="getComment()" class="container center">
            <table>
                <tr ng-repeat="x in comments">
                    <td>{{ x.userId }}</td>
                    <td>{{ x.content }}</td>
                </tr>
            </table>
        </div>
        <br>
        <div class="container">
            <form class="container center">
              <div class="row">
                <div class="input-field">
                  <input name="content" id="content" type="text" class="validate" ng-model="content">
                  <label for="content">Comment</label>
                </div>
              </div>
                <div class="container center">
                    <button class="btn waves-effect waves-light deep-purple darken-2" ng-click="addComment()">
                        Comment
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>
        </div>
        <br>        
        <div class="divider"></div>
        <h2 align="center">Answers</h2>
        <%
            List<Answer> answers = (List<Answer>)request.getAttribute("answers");
            List<String> anames = (List<String>)request.getAttribute("anames");
            int i = 0;
            for (Answer a : answers) {
                out.println("<div class='container'>");
                out.println("<div class='card deep-purple darken-2'>");
                out.println("<div class='card-content white-text'>");
                out.println("<p>" + a.getContent() + "</p>");
                out.println("</div>");
                out.println("<div class='card-action'>");
                out.println("<a class='left' href='#'>Answered by " + anames.get(i) + "</a>");
                out.println("<a href='' ng-click='voteUpA("+ a.getId() +")'>");
                out.println("<i class='left small deep-purple darken-2 material-icons' style='padding-left:20px'>thumb_up</i>");
                out.println("</a>");
                out.println("<a ng-init='avote[" + a.getId() + "]=" + a.getVote() + "' class='left' style='padding-left:5px'>{{ avote[" + a.getId() + "] }}</a>");
                out.println("<a href='' ng-click='voteDownA(" + a.getId() + ")'>");
                out.println("<i class='left small deep-purple darken-2 material-icons'>thumb_down</i>");
                out.println("</a>");
                out.println("</div></div></div>");
                i++;
            }
        %>    
    </div>
    
    <div class="container">
      <form class="container center" action="AnswerServlet">
        <div class="row">
          <div class="input-field">
            <input name="content" id="content" type="text" class="validate">
            <label for="content">Content</label>
            <%
                out.println("<input name='qid' type='hidden' value='" + request.getParameter("qid") + "'>");
                out.println("<input name='token' type='hidden' value='" + request.getParameter("token") + "'>");
            %>
          </div>
        </div>
        <div class="container center">
            <button class="btn waves-effect waves-light deep-purple darken-2" type="submit">
                Answer
                <i class="material-icons right">send</i>
            </button>
        </div>
      </form>
    </div>
    
    
        
    <footer class="page-footer deep-purple darken-2">
      <div class="footer-copyright">
        <div class="container">
          © 2015 Created by 3xcelsi0r
        </div>
      </div>
    </footer>

    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/materialize.min.js"></script>
    <script src="js/init.js"></script>
  </body>
</html>
