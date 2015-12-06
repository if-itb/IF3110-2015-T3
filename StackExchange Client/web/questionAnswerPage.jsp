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
                            $scope.sendPost = function(){
                                
                                $.ajax({
                                    type: "POST",
                                    url: "http://localhost:8083/CommentService/CommentServlet",
                                    data: {
                                        q_id  : 27,
                                        user_id  : 4,
                                        content : $scope.commentContent
                                    
                                     },
                                    dataType: "json"
                                  });
//                                $http({
//                                    method: 'POST',
//                                    url : "http://localhost:8083/CommentService/CommentServlet",
//                                    data : {
//                                        questionID  : 1,
//                                        token  : "sdfsd",
//                                        content : $scope.commentContent
//                                    
//                                     }
//                                }).then(function (data){
//                                    console.log(data);
//                                });
                            }
                        }).controller('customersCtrl', function($scope, $http) {
                 console.log("sdfsdf");
                $http.get("http://localhost:8083/CommentService/CommentServlet?q_id="+q_id)
                    .then(function(response) {
                        $scope.comments = response.data;
                        console.log(response);
                    });
             })
             .controller('questionCtrl',function($scope,$http){
                     console.log("voteupquestion");
                      $scope.voteUpQuestion = function(){
                        $.ajax({
                            type: "POST",
                            url: "http://localhost:8083/CommentService/VoteUpQuestionServlet",
                            data: {
                                q_id  :12,
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
                                url: "http://localhost:8083/CommentService/VoteDownQuestionServlet",
                                data: {
                                    q_id  :12,
                                    user_id  : 14,
                                 },
                                dataType: "json"
                              }).then(function (data) {
                                  $scope.getVoteNumber();
                              });
                        }
                        $scope.getVoteNumber = function(){
                           $http.get("http://localhost:8083/CommentService/VoteDownQuestionServlet?q_id="+q_id)
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
                     $http.get("http://localhost:8083/CommentService/AnswerServlet?q_id="+q_id)
                    .then(function(response2) {
                        $scope.Answers = response2.data;
                        console.log(response2);
                    });      
             
             
             
             
                      $scope.voteUpAnswer = function(){
                        $.ajax({
                            type: "POST",
                            url: "http://localhost:8083/CommentService/VoteUpAnswerServlet",
                            data: {
                                q_id  :12,
                                user_id  : 14,
                                ans_id :4,
                             },
                            dataType: "json"
                          }).then(function (data) {
                                  $scope.getVoteNumber();
                              });
                          
                    }
                        $scope.voteDownAnswer = function(){
                            $.ajax({
                                type: "POST",
                                url: "http://localhost:8083/CommentService/VoteDownAnswerServlet",
                                data: {
                                    q_id  :12,
                                    user_id  : 14,
                                    ans_id :4,
                                 },
                                dataType: "json"
                              }).then(function (data) {
                                  $scope.getVoteNumber();
                              });
                        }
                        $scope.getVoteNumber = function(){
                           $http.get("http://localhost:8083/CommentService/VoteDownAnswerServlet?q_id="+q_id+"&ans_id="+ans_id)
                           .then(function(response) {
                               console.log("success");
                               $scope.votenumber = response.data.vote;
                               console.log(response);
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
           
           
            <div ng-controller="customersCtrl"> 

             <ul>
               <li ng-repeat="x in comments">
                 {{ x.content }}
               </li>
             </ul>

             </div>

             <script>
             
             </script>

           
             
             <div ng-controller="FormController">
                <form novalidate class="simple-form">
                    Comment: <input type="textarea" ng-model="commentContent" /><br />
                    
                    <button type ="button" ng-click="sendPost()">Add Comment</button>
                </form>
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
