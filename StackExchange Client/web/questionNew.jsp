<%-- 
    Document   : question
    Created on : 23-Nov-2015, 13:20:51
    Author     : KEVIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head> 
	<meta charset="UTF-8">
	<title>Simple StackExchange</title>
	<link rel="stylesheet" type="text/css" href="css/mainstyle.css">        
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="js/function.js"></script>

</head>

<body>
<div ng-app="App">
<%
        String token = "";
        Cookie[] cookies = request.getCookies();
        for(Cookie temp : cookies){
            if(temp.getName().equals("token")){
                token = temp.getValue();
            }
        }
    %>
    <div id="wrapper">
        <jsp:include page="header.jsp" />
        <div id= "question_page">
        <div class="section">
            <h2 class="underlined">  </h2>
            <div class="question" id="question-">
                <h2>
                <%
                try {
                    questionWebService.QuestionWebService_Service service = new questionWebService.QuestionWebService_Service();
                    questionWebService.QuestionWebService port = service.getQuestionWebServicePort();
                    int id = Integer.parseInt(request.getParameter("qid"));
                    questionWebService.Question result = port.getQuestionById(id);
                    out.println(result.getTopic());
                %>
                </h2>
                <hr>
                <div class="row">
                    <div class= "vote col"  ng-controller="questionVoteController as questionVote"
                         ng-init="questionVote.getVote()">
                         <div class="vote-up"
                            ng-init="questionVote.init(<%= question.getId() %>, <%= user != null ? user.getId() : -1 %>)">
                            <a class="vote-link" 
                               ng-click="questionVote.addVote(1)">▲</a>
                        </div>
                        <div class="vote-counts">
                            <span>{{ questionVote.vote }}</span>
                        </div>
                         <div class="vote-down"
                            ng-init="questionVote.init(<%= question.getId() %>, <%= user != null ? user.getId() : -1 %>)">
                            <a class="vote-link" 
                               ng-click="questionVote.addVote(-1)">▼</a>
                        </div>
                    </div>
                    <div class = "col-content">
                        <%
                        out.println(result.getContent());
                        %>
                    </div>
                </div>
                <div class="row info">
                    asked by <span class="name"><%= result.getAskerName()%></span>
                </div>

                <hr>
                <br>
                    <%
                    } catch (Exception ex) {
                    }
                    %>
                <h2>Answers</h2>
                <div class="answer">
                    <%
                    try {
                        answerWebService.AnswerWebService_Service service = new answerWebService.AnswerWebService_Service();
                        answerWebService.AnswerWebService port = service.getAnswerWebServicePort();
                        int qid = Integer.parseInt(request.getParameter("qid"));
                        java.util.List<answerWebService.Answer> result = port.getAnswerByQid(qid);
                        for(int i=0; i<result.size(); i++ ){
                    %>
                            <hr>
                            <div class="section" id="answers">
                                <div class="answer underline" id="answer-">
                                    <div class="row">
                                         <div class="vote"
                                            ng-controller="answerVoteController as answerVote"
                                                ng-init="answerVote.getVote(<%= answer.getId() %>)">
                    
                                        <div class="vote-up"
                                     ng-init="answerVote.init(<%= answer.getId() %>, <%= user != null ? user.getId() : -1 %>)">
                                        <a class="vote-link" 
                                            ng-click="answerVote.addVote(1)">▲</a>
                                       </div>

                                        <div class="vote-counts">
                                             <span>{{ answerVote.vote }}</span>
                                        </div>

                        <div class="vote-down"
                            ng-init="answerVote.init(<%= answer.getId() %>, <%= user != null ? user.getId() : -1 %>)">
                            <a class="vote-link" 
                               ng-click="answerVote.addVote(-1)">▼</a>
                        </div>
                    </div>
                                    </div>
                                    <div class="row info">
                                        answered by <span class="name"><%= result.get(i).getAnswererName() %></span>
                                        <span class="email">&lt;<%=result.get(i).getEmail()%>&gt;</span>
                                    </div>
                                </div>
                            </div>
        
                            <div id="abc" ng-app="myForm" ng-controller="formCtrl">
                                <div id="popupContact">
                                <form action='#' id="form" method="post" name="form" novalidate>
                                    Comment this<br>
                                    <textarea ng-model="user.lastName" placeholder="comment here"></textarea>
                                    <br><br>
                                    <button ng-click="reset()" onclick ="div_hide()">COMMENT</button>
                                </form>
                                </div>
                            </div>
                                    <button id="popup" onclick="div_show()">Popup</button>
                            <% 
                            } //end for
                            %>
                            <hr>
                        <%
                        } catch (Exception ex) {
                        }
                        %>		
                        <div class="section" id="form-answer">
                            <h2>Your Answer</h2>
                            <form class="block" action="createanswer.jsp" method="POST">
                                <textarea name="content" placeholder="Content"></textarea>
                                <input type="submit" value="Post">
                                <input type="hidden" name="qid" value="<%= request.getParameter("qid") %>">
                                <input type="hidden" name="token" value="<%= token%>" />
                                <input type="hidden" name="ip" value="<%= request.getRemoteHost()%>"/>
                                <input type="hidden" name="uid" value="<%= request.getParameter("id")%>" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
  
var appName = angular.module("App", []);

appName.controller("voteController", function($scope, $http) {
      $scope.voteClick = function(target,action,id,userId) {
          var responsePromise = $http.get("http://localhost:8080/VoteAndComment Service/Vote?target=" + target + "&action=" + action + "&id="+ id +"&userId=" + userId);
          if(target == "answer") {
            responsePromise.success(function(data, status, headers, config) {
                $scope.(answerVoteNumber) = data.answer_vote;
            });
          } else if(target == "question") {
            responsePromise.success(function(data, status, headers, config) {
                $scope.questionVoteNumber = data.question_vote;
            });
          }
          responsePromise.error(function(data, status, headers, config) {
              alert("AJAX failed!");
          });
      }
  } );

  appName.controller("commentController", function($scope, $http) {
      $scope.commentClick = function(id,target,content,userId) {
          var responsePromise = $http.get("http://localhost:8080/VoteAndComment Service/Comment?id=" + id + "&target=" + target + "&content="+ content +"&userId=" + userId);
          var responseGetComment = $http.get("http://localhost:8080/VoteAndComment Service/GetComment?id=" + id + "&target=" + target);

          responseGetComment.success(function(data,status,headers,config) {
            $scope.comment = data.comment;
          });
          responseGetComment.error(function(data,status,headers,config) {
            alert("Ajax failed");
          });
      }
  } );

</script>
 </body>
 </html>
