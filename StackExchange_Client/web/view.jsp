<%-- 
    Document   : view
    Created on : Nov 22, 2015, 6:18:46 AM
    Author     : Venny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="question" type="model.question.Question" scope="request"/>
<jsp:useBean id="answers" type="java.util.List<model.answer.Answer>" scope="request"/>
<jsp:useBean id="q_user" type="model.user.User" scope="request"/>
<jsp:useBean id="a_user" type="java.util.Map<Integer, model.user.User>" scope="request"/>
<jsp:useBean id="uid" type="Integer" scope="request"/>
<jsp:include page="header.jsp" flush="true"/>
            <div class="content">
                <h2><%= question.getTopic() %></h2>
                <div class="voting">
                    <div class="arrow-up" onclick="return vote(<%= question.getQuestionId() %>,1,'question')"></div>
                    <div class="votenumber" id="vote-q"><%= question.getVote() %></div>
                    <div class="arrow-down" onclick="return vote(<%= question.getQuestionId() %>,-1,'question')"></div>
		</div>
		<div class="question-content">
                    <p><%= question.getContent() %></p>
		</div>
		<div class="question-sign">
                    <p>asked by <font color="#008080"><%= q_user.getName() %> (<%= q_user.getEmail() %>)</font> at <%= question.getCreateTime() %><br> 
                    <% if (question.getUserId() == uid) { %><a class="edit" href="edit?id=<%= question.getQuestionId() %>">edit</a><a class="delete" href="delete?id=<%= question.getQuestionId() %>" onclick="return confirm('Are you sure you want delete this question?')">delete</a></span></div><% } %>
            </div>
            <div class="content" ng-controller="commentController">
                <div ng-repeat="comment in comments">
                <div class="comment">
                    <p>{{comment.content}} — <font color="#008080">{{comment.name}}</font> at {{comment.create_time}} </p>
                </div>
                </div>
                <div class="comment" style="width:95%">
                    <p align="right"><a href = "#" onclick="showCommentForm()"><font color="#008080">add comment</font></a></p>
                </div>
                <form class="inputform" name="savecomment" ng-submit="submitComment()" style="display:none;" id="savecomment">
                    <textarea placeholder="Content" rows="2" name="content" ng-model="newcontent"></textarea>
                    <div class="button-bottom"><input type="submit" value="Submit" /></div>
                </form>
            </div>
            <div class="content">
                <h2>Answers</h2>
                <% if (!answers.isEmpty()) { %>
                    <% for (model.answer.Answer answer : answers) { %>
                    <div class="answer-list">
                        <div class="voting">
                            <div class="arrow-up" onclick="return vote(<%= answer.getAnswerId() %>,1,'answer')"></div>
                            <div class="votenumber" id="vote-a<%= answer.getAnswerId() %>"><%= answer.getVote() %></div>
                            <div class="arrow-down" onclick="return vote(<%= answer.getAnswerId() %>,-1,'answer')"></div>
                        </div>
                        <div class="answer-content">
                            <p><%= answer.getContent() %></p>
                        </div>
                        <div class="question-sign">
                            <p>answered by <font color="#008080"><%= a_user.get(answer.getAnswerId()).getName() %> (<%= a_user.get(answer.getAnswerId()).getEmail() %>)</font> at <%= answer.getCreateTime() %></p>
                        </div>
                    </div>
                    <% } %>
                <% }%>
            </div>

            <div class="content" style="margin-top:30px;">
		<h1>Your Answer</h1>
                <form class="inputform" method="post" name="saveanswer" action="addAnswer">
                    <input type="hidden" name="question_id" value="<%= question.getQuestionId() %>">
                    <textarea placeholder="Content" rows="5" name="content"></textarea>
                    <div class="button-bottom">
                        <button type="submit" name="saveanswer" value="Submit">Post</button>
                    </div>
                </form>
            </div>
	</div>

    <script src="js/functions.js"></script>
    <script src="js/app.js"></script>
    <script src="js/controllers/commentController.js"></script>
    </body>
</html>
