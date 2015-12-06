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
    <body>
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
                            <div ng-app="voteQuestion" ng-controller="voteQCtrl" ng-init="init(${question.vote})">
                                <div class="arrow-up" ng-click="voteup('${token}',${question.idQuestion})"></div>
                                <p id="vote" style="font-size:40px; margin:0; color:lightgrey" ng-bind="countvoteQ"></p>
                                <div class="arrow-down" ng-click="votedown('${token}',${question.idQuestion})"></div>                           
                            </div>        
                            <br>
                        </td>
                        <td id="display">
                            ${question.content}<br>
                        </td>
                    </tr>
                </table>

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
                        
                <h2>${count} Answer</h2><br>
            </c:forEach>

            <table id="listAnswers">
                <c:forEach items="${answers}" var="answer">
                    <tr>
                        <td id="countvote">                      
                            <div id="app2" ng-app="voteAnswer" ng-controller="voteACtrl" ng-init="init(${answer.vote})">
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
            var app = angular.module('voteQuestion', []);
            app.controller('voteQCtrl', function($scope) {             
                $scope.init = function(countvoteQ) {
                    $scope.countvoteQ = countvoteQ;
                };
                $scope.voteup = function(token,qid) {
                    
                    $http.get("http://www.w3schools.com/angular/customers.php").then(function(response){
                        $scope.names = response.data.records;
                    });
                    
                    $scope.countvoteQ += 1;
                    alert("your token: " + token + "\nyour qid: " + qid);
                };
                $scope.votedown = function(token,qid) {
                    $scope.countvoteQ -= 1;
                    alert("your token: " + token + "\nyour qid: " + qid);
                };
            });
            var app = angular.module('voteAnswer', []);
            app.controller('voteACtrl', function($scope) {             
                $scope.init = function(countvoteA) {
                    $scope.countvoteA = countvoteA;
                };
                $scope.voteup = function(token,aid) {
                    $scope.countvoteA += 1;
                    alert("your token: " + token + "\nyour aid: " + aid);
                };
                $scope.votedown = function(token,aid) {
                    $scope.countvoteA -= 1;
                    alert("your token: " + token + "\nyour aid: " + aid);
                };
            });
            angular.bootstrap(document.getElementById("app2"),['voteAnswer']);
        </script>
        
    </body>
</html>
