<%-- 
    Document   : question
    Created on : Nov 19, 2015, 4:11:41 AM
    Author     : user
--%>

<%@page import="com.mycompany.nasipadang.wsdl.StackExchange"%>
<%@page import="com.mycompany.nasipadang.wsdl.AnswerArray"%>
<%@page import="com.mycompany.nasipadang.wsdl.Answer"%>
<%@page import="com.mycompany.nasipadang.wsdl.AnswerArray"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.nasipadang.wsdl.Question"%>
<%@page import="com.mycompany.nasipadang.wsdl.QuestionArray"%>
<%@page import="com.mycompany.nasipadang.wsdl.StackExchangeImplService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExchange</title>
	<link rel="stylesheet" href="css/style.css" />
	<script src="js/validation.js"></script>
	<script src="js/delete_question.js"></script>
	<script src="js/ajax.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
        <%
            String name = (String) session.getAttribute("name");
            String token = (String) session.getAttribute("token");
            if(token == null) token = "";
            String id = request.getParameter("id");
            StackExchangeImplService stackExchangeService = new StackExchangeImplService();
            StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
            int i = Integer.parseInt(id);
            Question question = stackExchange.getQuestion(token, i);
            AnswerArray allAnswer = stackExchange.getAllAnswer(token, i);
            List<Answer> items = allAnswer.getItem();
            Cookie c = new Cookie("token", token);
            Cookie c1 = new Cookie("id", id);
            Cookie c2 = new Cookie("count", "" + question.getCount());
            String hasVote = "true";
            Cookie c4 = new Cookie("votes_question", "" + question.getVote());
            response.addCookie(c);
            response.addCookie(c1);
            response.addCookie(c2);
            response.addCookie(c4);
            String vote = "";
            if(name != null && !question.isHasVote()) vote = "enabled";
            else vote = "disabled";
        %>
    </head>
    <body ng-app="answers" ng-controller="ajaxComment">
        <a href="index.jsp"><h1>Simple StackExchange</h1></a><br>
        <%if (name != null) { 
            out.println(name); %>
            <a href="logout.jsp">log out</a>
        <%}else{%>
            <a href="login_form.jsp">log in</a>
            <a href="reg.jsp">register</a>
        <%}%>
	<div class="title"><%=question.getTopic()%></div>
	<hr></hr>
	<div class="question">
            <table>
                <tbody>
                    <tr>
                        <td>
                            <%if((name != null) && !question.isHasVote()){%><a class="vote-up" href="" ng-click="upVoteQuestion()">Up</a><%}%>
                            <div class="vote" id="votes">{{votes_question}}</div>
                            <%if((name != null) && !question.isHasVote()){%><a class="vote-down" href="" ng-click="downVoteQuestion()">Down</a><%}%>
                        </td>
                        <td>
                            <table>
                                <tbody>
                                    <tr><td><p class="content"><%=question.getContent()%></p></td></tr>
                                    <tr><td><div class="credential">asked by <%=question.getName()%> at <%=question.getTimestamp()%><%if(name != null && name.equals(question.getName())){%> | <a class="yellow" href="edit.jsp?id=<%=question.getId()%>">edit</a> | <a class="delete" href="javascript:confirmDelete(<%=question.getId()%>)">delete</a></div></td></tr><%}%>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
	<br>
        </div>
	<div class="answer">
            <div class="title" id="count">{{count}} Answer</div>
            <ul>
                <%for(Answer answer : items){%>
                    <hr></hr>
                    <li>
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <a class="vote-up" href="" ng-click="upVoteAnswer()">Up</a>
                                        <div class="vote" id="votes<%=answer.getIdAnswer()%>"><%=answer.getVote()%></div>
                                        <a class="vote-down" href="" ng-click="downVoteAnswer()">Down</a>
                                    </td>
                                    <td>
                                        <table>
                                            <tbody>
                                                <tr><td><p class="content"><%=answer.getContent()%></p></td></tr>
                                                <tr><td><div class="credential">answered by <%=answer.getName()%> at <%=answer.getTimestamp()%></div></td></tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </li><br>
                <%}%>
                <li ng-repeat="answer in answers">
                    <hr></hr>
                    <table>
                        <tbody>
                            <tr>
                                <td>
                                    <a class="vote-up" ng-href="{{vote_question.up}}">Up</a>
                                    <div class="vote" id="votes{{answer.id_answer}}">{{answer.vote}}</div>
                                    <a class="vote-down" ng-href="{{vote_question.up}}">Down</a>
                                </td>
                                <td>
                                    <table>
                                        <tbody>
                                            <tr><td><p class="content">{{answer.content}}</p></td></tr>
                                            <tr><td><div class="credential">answered by {{answer.name}} at {{answer.timestamp}}</div></td></tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </li><br>
                <li>
                    <hr></hr>
                    <div class="new-answer">
                        <div class="title">Your Answer</div>
                        <%if (name != null) {%>
                        <form name="answer" ng-submit="processForm()">
                            <textarea class="inputform" type="text" ng-model="formData.content" placeholder="Content"></textarea><br>
                            <input class="button" type="submit" value="Post">
                        </form>
                        <%}else{%>
                            <a href="login_form.jsp">log in</a>
                            <a href="reg.jsp">register</a>
                        <%}%>
                    </div>
                </li>
                <li>
                    <hr></hr>
                    <table>
                        <tbody>
                            <tr>
                                <td>
                                    <table>
                                        <tbody>
                                            <tr><td><p class="content">{{formData.content}}<br>{{error}}</p></td></tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </li><br>
            </ul>
        </div>
    </body>
    <script>
        var app1 = angular.module('answers',['ngCookies']);
        app1.controller('ajaxComment', ['$scope', '$http', '$cookies', function($scope, $http, $cookies) {
            $scope.vote = "enabled";
            $scope.count = $cookies.get("count");
            $scope.votes_question = $cookies.get("votes_question");
            $scope.answers = [];
            $scope.formData = {};
            $scope.formData.content = "";
            $scope.error = "";
            var token = $cookies.get("token");
            var id = $cookies.get("id");
            $scope.processForm = function(){
                $http.get("rest/comment?id=" + id + "&token=" + token + "&content=" + $scope.formData.content)
                    .then(function(response) {
                        if(response.data.status === "ok") {
                            $scope.answers = response.data.answers;
                            $scope.formData.content = "";
                            $scope.count = parseInt($scope.count) + 1;
                        }
                        else{$scope.error = "Your session is invalid";}
            });
            };
            
            // for vote question
            $scope.upVoteQuestion = function(){
                $http.get("rest/vote_question?id=" + id + "&token=" + token + "&vote=1")
                    .then(function(response) {
                        if(response.data.status === "ok") {
                            $scope.votes_question = response.data.votes;
                        }
                        else{$scope.error = "Your session is invalid";}
            });
            };
            $scope.downVoteQuestion = function(){
                $http.get("rest/vote_question?id=" + id + "&token=" + token + "&vote=-1")
                    .then(function(response) {
                        if(response.data.status === "ok") {
                            $scope.votes_question = response.data.votes;
                        }
                        else{$scope.error = "Your session is invalid";}
            });
            };
            
            //for vote answer
            $scope.vote_answer = {up : "upVoteAnswer({{answer.id_answer}})", down : "downVoteAnswer({{answer.id_answer}})"};
            $scope.upVoteAnswer = function(id_answer){
                $http.get("rest/vote_question?id_answer=" + id_answer + "&token=" + token + "&vote=1")
                    .then(function(response) {
                        if(response.data.status === "ok") {
                            $scope.vote_answer.up = "";
                            $scope.vote_answer.down = "";
                        }
                        else{$scope.error = "Your session is invalid";}
            });
            };
            $scope.downVoteAnswer = function(id_answer){
                $http.get("rest/vote_question?id_answer=" + id_answer + "&token=" + token + "&vote=-1")
                    .then(function(response) {
                        if(response.data.status === "ok") {
                            $scope.vote_answer.up = "";
                            $scope.vote_answer.down = "";
                        }
                        else{$scope.error = "Your session is invalid";}
            });
            };
        }]);
    </script>
</html>
