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
            response.addCookie(c);
            response.addCookie(c1);
            String vote = "";
            if(name != null && !question.isHasVote()) vote = "enabled";
            else vote = "disabled";
        %>
    </head>
    <body>
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
                            <a class="vote-up-<%=vote%>" href="update_vote_question.jsp?id=<%=question.getId()%>&vote=1">Up</a>
                            <div class="vote" id="votes"><%=question.getVote()%></div>
                            <a class="vote-down-<%=vote%>" href="update_vote_question.jsp?id=<%=question.getId()%>&vote=-1">Down</a>
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
	<div class="answer" ng-app="answers" ng-controller="ajaxComment">
            <div class="title" id="count"><%=question.getCount()%> Answer</div>
            <ul>
                <%for(Answer answer : items){%>
                    <hr></hr>
                    <li>
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <%if(name != null && !answer.isHasVote()){%><a class="vote-up" href="update_vote_answer.jsp?id=<%=question.getId()%>&id_answer=<%=answer.getIdAnswer()%>&vote=1">Up</a><%}%>
                                        <div class="vote" id="votes<%=answer.getIdAnswer()%>"><%=answer.getVote()%></div>
                                        <%if(name != null && !answer.isHasVote()){%><a class="vote-down" href="update_vote_answer.jsp?id=<%=question.getId()%>&id_answer=<%=answer.getIdAnswer()%>&vote=-1">Down</a><%}%>
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
                                    <a class="vote-up" href="update_vote_answer.jsp?id=<%=question.getId()%>&id_answer={{answer.id_answer}}&vote=1">Up</a>
                                    <div class="vote" id="votes{{answer.id_answer}}">{{answer.vote}}</div>
                                    <a class="vote-down" href="update_vote_answer.jsp?id=<%=question.getId()%>&id_answer={{answer.id_answer}}&vote=-1">Down</a>
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
            $scope.answers = [];
            $scope.formData = {};
            $scope.formData.content = "";
            $scope.error = "";
            $scope.processForm = function(){
                var token = $cookies.get("token");
                var id = $cookies.get("id");
                $http.get("rest/comment?id=" + id + "&token=" + token + "&content=" + $scope.formData.content)
                    .then(function(response) {
                        if(response.data.status === "ok") {$scope.answers = response.data.answers; $scope.formData.content = "";}
                        else{$scope.error = "Your session is invalid";}
            });
            };
        }]);
    </script>
</html>
