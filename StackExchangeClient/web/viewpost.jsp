<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <style>
            <%@ include file="style.css"%>
        </style>
        <title>Stack Exchange</title>
    </head>
    <body ng-app="app">
        <div class="container">
            <c:choose>
                <c:when test="${username == null}">
                    <div class="login">
                        <h1>Log-in</h1>
                            <form action="/StackExchangeClient/login" method="POST">
                                <input type="text" name="user" placeholder="Username">
                                <input type="password" name="pass" placeholder="Password">
                                <input type="submit" name="login" value="Login">
                            </form>
                            <div class="login-help">
                                <a href="register.jsp">Register</a>
                            </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <p style="text-align:right">You're log in as ${username} <button onclick="window.location.href='logout';">Log out</button></p>
                </c:otherwise>
            </c:choose>

            <a href="/StackExchangeClient/index"><h1>Simple StackExchange</h1></a><br>
            
            <c:forEach items="${result}" var="question">
                <h2>${question.topic}</h2><br>  
                <table id="question">
                    <tr>
                        <td id="countvote">
                            <div ng-controller="voteQCtrl" ng-init="init(${question.vote})">
                                <div class="arrow-up" ng-click="voteup('${token}',${question.idQuestion})"></div>
                                <p id="vote" style="font-size:40px; margin:0; color:lightgrey" ng-bind="countvoteQ"></p>
                                <div class="arrow-down" ng-click="votedown('${token}',${question.idQuestion})"></div>                           
                            </div>    
                            <br>
                        </td>
                        <td id="display">
                            ${question.content}<br><br>
                            
                            <c:choose>
                                <c:when test="${question.username == username}">
                                    <p style="text-align:right">
                                        asked by ${question.username} | 
                                        <a href="<c:url value="/editquestion" >
                                                    <c:param name="id" value="${question.idQuestion}"/>
                                                </c:url>">edit
                                        </a> | 
                                        <a href="<c:url value="/deletequestion" >
                                                   <c:param name="id" value="${question.idQuestion}"/>
                                                </c:url>"
                                            onclick="return confirm('Are you sure you want to delete this item?')">delete
                                        </a>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p style="text-align:right">asked by ${question.username}</p>
                                </c:otherwise>
                            </c:choose>
                            
                            <div id="app2"  ng-model="list" ng-init="init(${question.idQuestion}) ;" ng-controller="commentCtrl">        
                                <div id="comment" ng-repeat="comments in list">
                                    {{comments.content}} - {{comments.username}}
                                </div>
                                <form class="addcomment">
                                    <input type="text" ng-model="content" placeholder="Enter your comment" />
                                    <button ng-click="add(${question.idQuestion},'${token}','${username}')">Add Comment</button>
                                </form>
                            </div>
                             
                        </td>
                    </tr>
                </table>

                        
                <h2>${count} Answer</h2><br>
            </c:forEach>

            <table id="listAnswers">
                <c:forEach items="${answers}" var="answer">
                    <tr>
                        <td id="countvote">                      
                            <div id="app3" ng-controller="voteACtrl" ng-init="init(${answer.vote})">
                                <div class="arrow-up" ng-click="voteup('${token}',${answer.idAnswer})"></div>
                                <p style="font-size:40px; margin:0; color:lightgrey" ng-bind="countvoteA"></p>
                                <div class="arrow-down" ng-click="votedown('${token}',${answer.idAnswer})"></div>                           
                            </div>                      
                        </td>
                        <td id="display">${answer.content}<br>
                            <p style="text-align:right">answered by ${answer.username} at ${answer.date}</p>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <br><p style="font-size:30px; margin:0; color:grey"> Your Answer </p>
            <form class="AnswerForm" action="addanswer" method="POST">
		<textarea name="content" id="content" placeholder="Content"></textarea><br><br>
                <input type="hidden" name="idQuestion" value="${result.get(0).idQuestion}" />
		<input type="submit" value="Post">
            </form>
            
	</div>
                
        <script>
            var app = angular.module('app', []);
            app.controller('voteQCtrl', ['$scope', '$http', function($scope, $http) {             
                $scope.init = function(countvoteQ) {
                    $scope.countvoteQ = countvoteQ;
                };           
                $scope.voteup = function(token,qid) {
                    status = "";
                    $http({
                        url: "http://localhost:10940/StackExchangeClient/voteupquestion",
                        method: "POST",
                        data: {token: token, qid: qid}
                    }).success(function(data) {
                        status = data.status.toString();
                    });
                    /*if (status == "0")
                        alert("you've already vote up this question");
                    else if (status == "1")*/
                        $scope.countvoteQ += 1;
                    /*else if (status == "2")
                        $scope.countvoteQ += 2;
                    else if (status == "-1")
                        alert("your token is invalid");
                    else if (status == "-2")
                        alert("you're already login in other browser");
                    else // if (status == '-3')
                        alert("you're already login in other computer");*/
                };
                $scope.votedown = function(token,qid) {
                    status = "";
                    $http({
                        url: "http://localhost:10940/StackExchangeClient/votedownquestion",
                        method: "POST",
                        data: {token: token, qid: qid}
                    }).success(function(data) {
                        status = data.status.toString();
                    });
                    /*if (status == "0")
                        alert("you've already vote up this question");
                    else if (status == "1")*/
                        $scope.countvoteQ -= 1;
                    /*else if (status == "2")
                        $scope.countvoteQ -= 2;
                    else if (status == "-1")
                        alert("your token is invalid");
                    else if (status == "-2")
                        alert("you're already login in other browser");
                    else // if (status == '-3')
                        alert("you're already login in other computer");*/
                };
            }]);
            
            //var myApp = angular.module('commentApp', []);          
            app.controller('commentCtrl', ['$scope', '$http', function($scope, $http) {
                $scope.init = function(qid) {
                    $http({
                        url: "http://localhost:10940/StackExchangeClient/getcomments",
                        method: "GET",
                        params: {qid: qid}
                    }).success(function(data) {
                        $scope.list = data.comments ;
                    });
                };
                $scope.add = function(qid,token,username) {
                    $http({
                        url: "http://localhost:10940/StackExchangeClient/addcomment",
                        method: "POST",
                        data: {qid: qid, token: token, content: $scope.content}
                    }).success(function(data) {
   
                    });
                    $scope.list.push({ username: username, content: $scope.content, qid: qid });
                    $scope.content = '';
                };
            }]);
            
            //var app = angular.module('voteAnswer', []);
            app.controller('voteACtrl', ['$scope', '$http', function($scope, $http) {             
                $scope.init = function(countvoteA) {
                    $scope.countvoteA = countvoteA;
                };
                $scope.voteup = function(token,aid) {
                    status = "";
                    $http({
                        url: "http://localhost:10940/StackExchangeClient/voteupanswer",
                        method: "POST",
                        data: {token: token, aid: aid}
                    }).success(function(data) {
                        status = data.status.toString();
                    });
                    /*if (status == "0")
                        alert("you've already vote up this question");
                    else if (status == "1")*/
                        $scope.countvoteA += 1;
                    /*else if (status == "2")
                        $scope.countvoteA += 2;
                    else if (status == "-1")
                        alert("your token is invalid");
                    else if (status == "-2")
                        alert("you're already login in other browser");
                    else if (status == '-3')
                        alert("you're already login in other computer");
                    else
                        alert(status);*/
                };
                $scope.votedown = function(token,aid) {
                    $status = "";
                    $http({
                        url: "http://localhost:10940/StackExchangeClient/votedownanswer",
                        method: "POST",
                        data: {token: token, aid: aid}
                    }).success(function(data) {
                        status = data.status.toString();
                    });
                    /*if (status == "0")
                        alert("you've already vote up this question");
                    else if (status == "1")*/
                        $scope.countvoteA -= 1;
                    /*else if (status == "2")
                        $scope.countvoteA -= 2;
                    else if (status == "-1")
                        alert("your token is invalid");
                    else if (status == "-2")
                        alert("you're already login in other browser");
                    else // if (status == '-3')
                        alert("you're already login in other computer");*/
                };
            }]);

        </script>
        
    </body>
</html>
