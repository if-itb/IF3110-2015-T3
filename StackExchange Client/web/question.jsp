<%-- 
    Document   : question
    Created on : 09-Nov-2015, 17:16:57
    Author     : Asus
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" flush="true"/>
<jsp:useBean id="question" type="QuestionWS.Question" scope="request" /> 
<jsp:useBean id="question_vote_count" type="Integer" scope="request" /> 
<jsp:useBean id="answers" type="java.util.List<AnswerWS.Answer>" scope="request" /> 
<jsp:useBean id="answer_count" type="Integer" scope="request" /> 
<jsp:useBean id="answers_vote_counts" type="HashMap<Integer,Integer>" scope="request" />
<jsp:useBean id="answers_answerer" type="HashMap<Integer,UserWS.User>" scope="request" />
<jsp:useBean id="question_asker" type="String" scope="request" /> 

    <%
        Cookie[] cookies = null;
        cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("access_token")) {
                    token = cookie.getValue();
                }
            }
        }
        
        String userIP = request.getHeader("X-FORWARDED-FOR");  
        if (userIP == null) {  
            userIP = request.getRemoteAddr();  
        }
        String userAgent = request.getHeader("User-Agent");
    %>
    <%-- start web service invocation --%>
    <%
    UserWS.User user = null;
    try {
	UserWS.UserWS_Service service = new UserWS.UserWS_Service();
	UserWS.UserWS port = service.getUserWSPort();
	
	user = port.getUserByToken(token,userIP,userAgent);
	
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%>

    <form action="" method="GET" id="searchForm">
        <input type="text" placeholder="Search...">
        <input type="submit" value="Search">
    </form>

    <p class="ask-here">Can't find what you are looking for? <a href="${pageContext.request.contextPath}/ask">Ask here</a></p>

    <div class="inner-container">

        <div class="question-header">
            <h1><%= question.getTopic() %></h1>
        </div>

        <div class="question-item">
            <div class="row">

                <div class="question-status col-2">
                    <div class="vote"
                         ng-controller="questionVoteController as questionVote"
                         ng-init="questionVote.getVote()">

                        <div class="vote-up"
                            ng-init="questionVote.init(<%= question.getId() %>, <%= user != null ? user.getId() : -1 %>)">
                            <a class="vote-link" 
                               ng-click="questionVote.addVote(1)">▲</a>
                        </div>

                        <div class="vote-counts">
                            <span>{{ questionVote.vote }}</span>
                        </div>

                        <div class="vote-down":
                            ng-init="questionVote.init(<%= question.getId() %>, <%= user != null ? user.getId() : -1 %>)">
                            <a class="vote-link" 
                               ng-click="questionVote.addVote(-1)">▼</a>
                        </div>

                    </div>
                </div> <!-- .question-status -->

                <div class="question-content col-10">
                    <p><%= question.getContent().replaceAll("(\r\n|\n\r|\r|\n)", "<br />") %></p> 
                </div>

                <div class="question-meta">
                    <span>
                        Asked by
                        <%= question_asker %>                        
                        <% if ( ( user != null ) && (user.getName()).equals(question_asker) ) { %>
                            |
                            <a href="${pageContext.request.contextPath}/questioneditor?id=<%= question.getId() %>" class="question-edit">Edit</a> |
                            <form method="POST" action="deletequestion" id="deleteForm_question<%= question.getId() %>" class="delete-form">
                                <input type="hidden" value="<%= question.getId() %>" name="id_question">
                                <input type="hidden" value="<%= user != null ? user.getId() : 0 %>" name="id_user">
                                <input type="submit" value="Delete" class="form-delete">
                            </form>
                        <% } %>
                    </span>
                </div>

            </div> <!-- .row -->
            
            <div class="row">
                <div class="comment col-10 col-push-2" ng-controller="questionController as question">
                    <div class="comment-item" ng-repeat="comment in question.comments">
                        <p>{{ comment.content }} - <span class="author">{{ comment.user }}</span></p>
                    </div>
                    
                    <% if (user != null) { %>
                    <div name="commentForm" ng-controller="commentController as commentCtrl">
                        <input ng-model="commentCtrl.comment.q_id" ng-init="commentCtrl.data.q_id=<%= question.getId() %>" type="hidden">
                        <input ng-model="commentCtrl.comment.u_id" ng-init="commentCtrl.data.u_id=<%= user.getId() %>" type="hidden">
                        <input ng-model="commentCtrl.comment.user" ng-init="commentCtrl.data.user='<%= user.getName() %>'" type="hidden">
                        <input ng-model="commentCtrl.comment.content" type="text" placeholder="Your comment" required>
                        <input type="submit" value="Add comment" ng-click="commentCtrl.addComment(question.comments)" >
                    </div>
                    <% } %>
                </div>
            </div>
        </div> <!-- .question-item -->

    </div> <!-- .inner-container -->

    <div class="row">
        <div class="answer-header col-10 col-push-1">
            <h2><%= answer_count %> Answers</h2>
        </div>
    </div>

    <% for( AnswerWS.Answer answer: answers ) { %>
    <div class="inner-container">
        <div class="answer">
            <div class="row">
                
                <div class="answer-status col-2">
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
                </div> <!-- .answer-status -->

                <div class="answer-content col-10">
                    <p><%= answer.getContent() %></p>
                </div>

                <div class="answer-meta">
                    <span>
                        Answered by <%= answers_answerer.get(answer.getId()).getName() %>
                    </span>
                </div>

            </div> <!-- .row -->
        </div> <!-- .answes -->
    </div> <!-- .inner-container -->

   <% } %>
    
    <div class="inner-container">
        <div class="row">
            <div class="answer-form col-10 col-push-2">
                
                <h3 class="answer-form-header">Your Answer</h3>

                <form id="answerForm" action="newanswer" method="POST">
                    <input type="hidden" name="qid" value="<%= question.getId() %>">
                    <div class="form-field">
                        <label for="content">Answer</label>
                        <textarea name="content" placeholder="Your answer goes here"></textarea>
                    </div>
                    <input type="submit" value="Submit"/>
                </form>

            </div> <!-- .answer-form -->
        </div> <!-- .row -->
    </div> <!-- .inner-container -->
<jsp:include page="layout/footer.jsp" flush="true"/>
