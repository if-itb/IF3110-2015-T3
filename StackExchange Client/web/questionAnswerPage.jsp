<%--
  Created by IntelliJ IDEA.
  User: Marco Orlando
  Date: 11/17/2015
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="style.css">
  <script type="text/javascript" src="checklogin.js"></script>
  <title> Simple StackExchange</title>
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>
  <script>
      var q_id = <% out.println(request.getParameter("q_id")); %>;
      var ans_id = <% out.println(request.getParameter("ans_id")); %>;
                angular.module('myForm',[])
                        .controller('FormController',function($scope,$http){
                            console.log("masuk");
                    
                                var loadComment =  function() {
                                   $http.get("http://localhost:8083/CommentVoteService/CommentServlet?q_id="+q_id)
                                  .then(function(response) {
                                       $scope.comments = response.data;
                                       console.log($scope.comments);
                                  });      
                               }
                               loadComment();
                            $scope.sendPost = function(){
                                $.ajax({
                                    type: "POST",
                                    url: "http://localhost:8083/CommentVoteService/CommentServlet",
                                    data: {
                                        q_id  : q_id,
                                        user_id  : 4,
                                        content : $scope.commentContent
                                    
                                     },
                                    dataType: "json"
                                  }).then(function (data) {
                                    loadComment();
                                });

                            }
                        })
             .controller('questionCtrl',function($scope,$http){
                     console.log("voteupquestion");
                      $scope.voteUpQuestion = function(){
                        $.ajax({
                            type: "POST",
                            url: "http://localhost:8083/CommentVoteService/VoteUpQuestionServlet",
                            data: {
                                q_id  :q_id,
                                user_id  : 14,
                             },
                            dataType: "json"
                          }).then(function (data) {
                                  $scope.getVoteNumber();
                              });
                          
                    }
                        $scope.voteDownQuestion = function(){
                            $.ajax({
                                type: "POST",
                                url: "http://localhost:8083/CommentVoteService/VoteDownQuestionServlet",
                                data: {
                                    q_id  :q_id,
                                    user_id  : 14,
                                 },
                                dataType: "json"
                              }).then(function (data) {
                                  $scope.getVoteNumber();
                              });
                        }
                        $scope.getVoteNumber = function(){
                           $http.get("http://localhost:8083/CommentvoteService/VoteDownQuestionServlet?q_id="+q_id)
                           .then(function(response) {
                               console.log("success");
                               $scope.votenumber = response.data.vote;
                               console.log(response);
                           });
                        }   
                        $scope.getVoteNumber();      
             })
                          .controller('answerCtrl',function($scope,$http){
                     console.log("voteupquestion");
                    var loadAnswer = function() {
                        $http.get("http://localhost:8083/CommentVoteService/AnswerServlet?q_id="+q_id)
                       .then(function(response2) {
                           console.log("init");
                           $scope.comment = response2.data;
                           console.log($scope.Answers);
                           console.log(response2);
                       });      
                    }
                    loadAnswer();
             
                        $scope.voteUpAnswer = function(answerId){
                          $.ajax({
                              type: "POST",
                              url: "http://localhost:8083/CommentVoteService/VoteUpAnswerServlet",
                              data: {
                                  q_id  :q_id,
                                  user_id  : 14,
                                  ans_id :answerId,
                               },
                              dataType: "json"
                            }).then(function (data) {
                                    loadAnswer();
                                });
                          
                    }
                        $scope.voteDownAnswer = function(answerId){
                            $.ajax({
                                type: "POST",
                                url: "http://localhost:8083/CommentVoteService/VoteDownAnswerServlet",
                                data: {
                                    q_id  :q_id,
                                    user_id  : 14,
                                    ans_id :answerId,
                                 },
                                dataType: "json"
                              }).then(function (data) {
                                  loadAnswer();
                              });
                        }
                        //$scope.getVoteNumber();      
             })             

    ;
            </script>
</head>

<body ng-app="myForm">
<div id="container">
  <div id="header">
      <span id="Judul"><a <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %>>StackExchange</a></span>
  </div>

  <div id="body">
    <div id="questionAndAnswer">
           <jsp:include page="questionAnswerPage_Q.jsp" flush ="true"/>
           
           <div ng-controller="FormController">
            <div> 

             <ul>
               <li ng-repeat="x in comments">
                 {{ x.content }}
               </li>
             </ul>

             </div>

             <script>
             
             </script>

           
             
             <div>
                <form novalidate class="simple-form">
                    Comment: <input type="textarea" ng-model="commentContent" /><br />
                    
                    <button type ="button" ng-click="sendPost()">Add Comment</button>
                </form>
              </div>
             
           </div>
            

             
           
           
           
           
           <jsp:include page="questionAnswerPage_A.jsp" flush ="true"/>  
    </div>


    <div id="yourAnswer">   

    <% int questionId = Integer.parseInt(request.getParameter("q_id")); %>
      <span id="yourAnswerWord">Your Answer</span>
      <div id="answerForm">
        <form name="myForm" action="addAnswerProcess.jsp" method="post">
          <textarea name="answerContent" placeholder="Your answer" onfocus="checkLogin(<% out.println(request.getParameter("token")); %>)"></textarea><br>
          <input type="hidden" name="questionId" value='<%= questionId %>'>
          <input type="hidden" name="token" value='<%= request.getParameter("token") %>'>
          <div id="submitter">
            <input type="submit" value="Post">
          </div>
        </form>
      </div>
    </div>
  </div>

</div>
</body>



</html>
