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
                            <%if(name != null && !question.isHasVote()){%><a class="vote-up" href="update_vote_question.jsp?id=<%=question.getId()%>&vote=1">Up</a><%}%>
                            <div class="vote" id="votes"><%=question.getVote()%></div>
                            <%if(name != null && !question.isHasVote()){%><a class="vote-down" href="update_vote_question.jsp?id=<%=question.getId()%>&vote=-1">Down</a><%}%>
                        </td>
                        <td>
                            <table>
                                <tbody>
                                    <tr><td><p class="content"><%=question.getContent()%></p></td></tr>
                                    <tr><td><div class="credential">asked by <%=question.getName()%> at <%=question.getTimestamp()%> | <a class="yellow" href="edit.jsp?id=<%=question.getId()%>">edit</a> | <a class="delete" href="javascript:confirmDelete(<%=question.getId()%>)">delete</a></div></td></tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
	<br>
	<div class="answer">
            <div class="title"><div id="count"><%=question.getCount()%> Answer</div>
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
            </ul>
            <ul>     
                <li repeat="answer in answers">
                    <hr></hr>
                    <table>
                        <tbody>
                            <tr>
                                <td>
                                    <a class="vote-up" href="update_vote_answer.jsp?id=<%=question.getId()%>&id_answer={{answer.id}}&vote=1">Up</a>
                                    <div class="vote" id="votes{{answer.id}}">{{answer.vote}}</div>
                                    <a class="vote-down" href="update_vote_answer.jsp?id=<%=question.getId()%>&id_answer={{answer.id}}&vote=-1">Down</a>
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
            </ul>
	</div>

	<hr></hr>
        <div class="new-answer" ng-app="comment" ng-controller="commentController">
		<div class="title">Your Answer</div>
                <%if (name != null) {%>
                <form name="answer" method="post" ng-submit="processForm()">
                    <input type="hidden" name="token" value="<%=token%>"><br>
                    <input type="hidden" name="id" value="<%=question.getId()%>">
                    <textarea class="inputform" type="text" ng-model="content" placeholder="Content"></textarea><br>
                    <input class="button" type="submit" value="Post">
		</form>
                <%}else{%>
                    <a href="login_form.jsp">log in</a>
                    <a href="reg.jsp">register</a>
                <%}%>
	</div>
        <hr></hr>
            <li>
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <table>
                                    <tbody>
                                        <tr><td><p class="content">{{content}}</p></td></tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </li><br>
    </body>
    <script>
        var app = angular.module('comment', []);
        app.controller('commentController', function($scope) {
            $scope.content = "";
        });
        function ajaxComment($scope, $http) {
            $scope.formData = {};
            $scope.processForm = function() {
                $http({
                    method  : 'POST',
                    url     : 'rest/comment',
                    data    : $.param($scope.formData),  // pass in data as strings
                    headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
                   })
                    .success(function(data) {
                      console.log(data);

                      if (!data.success) {
                        // if not successful, bind errors to error variables
//                        $scope.errorName = data.errors.name;
//                        $scope.errorSuperhero = data.errors.superheroAlias;
                      } else {
                        // if successful, bind success message to message
                        $scope.answers = data.message;
                      }
                    });
            };
        }
    </script>
</html>
