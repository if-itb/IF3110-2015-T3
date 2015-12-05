<%-- 
    Document   : question
    Created on : Nov 19, 2015, 4:11:41 AM
    Author     : user
--%>

<%@page import="com.mycompany.nasipadang.wsdl.StackExchange"%>
<%@page import="com.mycompany.nasipadang.wsdl.StackExchangeImplService"%>
<%@page import="com.mycompany.nasipadang.wsdl.Question"%>
<%@page import="com.mycompany.nasipadang.wsdl.AnswerArray"%>
<%@page import="com.mycompany.nasipadang.wsdl.Answer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExchange</title>
	<link rel="stylesheet" href="css/style.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
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
                                    <tr><td><div class="credential">asked by <%=question.getName()%> at <%=question.getTimestamp()%><%if(name != null && name.equals(question.getName())){%> | <a class="yellow" href="edit.jsp?id=<%=question.getId()%>">edit</a> | <a class="delete" href="javascript:confirmDelete(<%=question.getId()%>)">delete</a></div></td></tr><%}%>
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
                <hr></hr>
                    <li>
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <%if(name != null && !question.isHasVote()){%><a class="vote-up" href="update_vote_answer.jsp?id={{answer.id}}&id_answer={{answer.id}}&vote=1">Up</a><%}%>
                                        <div class="vote" id="votes{{answer.id}}}">{{answer.vote}}</div>
                                        <%if(name != null && !question.isHasVote()){%><a class="vote-down" href="update_vote_answer.jsp?id=<%=question.getId()%>&id_answer={{answer.id}}&vote=-1">Down</a><%}%>
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
            <div id="ajax_answer"></div>
	</div>

	<hr></hr>
	<div class="new-answer">
		<div class="title">Your Answer</div>
                <%if (name != null) {%>
                <div ng-app="comment" ng-controller="comment">
                    <form name="answer" method="post" action="ajaxComment">
                        <input class="inputform" type="hidden" name="token" value="<%=token%>"><br>
                        <input type="hidden" name="id" value="<%=question.getId()%>">
                        <textarea class="inputform" type="text" ng-model="content" placeholder="Content"></textarea><br>
                        <input class="button" type="submit" value="Post" onclick="">
                    </form>
                </div>
                <p class="content">{{content}}</p></td></tr>
                <%}else{%>
                    <a href="login_form.jsp">log in</a>
                    <a href="reg.jsp">register</a>
                <%}%>
                
	</div>
    </body>
    <script>
        var app = angular.module('comment', []);
        app.controller('comment', function($scope) {
            $scope.content = "";
        });
        app.controller('ajaxComment', function($scope, $http) {
            $http.post("rest/comment", $scope)
            .then(function(response) {$scope.answer = response.data.records;});
        });
    </script>
</html>
