<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="header.jsp" %>
<jsp:useBean id="question" type="stackexchange.webservice.Question" scope="request" />
<jsp:useBean id="answers" type="java.util.List<stackexchange.webservice.Answer>" scope="request" />

		<h1 class="tag"><%= question.getTopic() %></h1>
		<div class="innerContent fQuestion">
			<div class="col votesCount">
				<div class="up vote">
                                    <a class="voteDes" href="" ng-click="voteUpQ()"><i class="material-icons md-48">arrow_drop_up</i></a>
				</div>
                                <div id="questionVote<%= question.getId()%>" questionVote>
                                    {{value}}
				</div>
				<div class="down vote">
                                    <a class="voteDes" href="" ng-click="voteDownQ()"><i class="material-icons md-48">arrow_drop_down</i></a>
				</div>
			</div>
			<div class="col content">
				<p>
					<%= question.getContent()%>
				</p>
                                <div class="navPost2">
                                    <% if(user.getEmail().equals(question.getEmail())){ %>
                                        <p>
                                                asked by You <span><%= question.getEmail()%></span> | <a class="link edit" href="questionedit?withanswer=true&id=<%= question.getId()%>"> edit</a> | <a class="link delete" href="questiondelete?id=<%= question.getId()%>" onclick="return validateDelete();"> delete </a>
                                        </p>
                                    <% }else{ %>
                                        <p>
                                                asked by <%= question.getName()%> <span><%= question.getEmail()%></span> 
                                        </p>
                                    <% } %>
                                </div>
                                <div class="comment-container">
                                    <p class="comment">
                                        Give is more source code, it's not enough - <span class="userCom"> fauzan </span>
                                    </p>
                                    <div class="addCom">
                                        <span><a class="submitButton" href="#"> add comment </a></span>
                                    </div>
                                </div>
			</div>
		</div>
		<div class="containerAnswer">
                    <h1 class="tag"><%= answers.size() %> Answer</h1>
                    <% for(stackexchange.webservice.Answer answer : answers){ 
                    %>
                    <div id="answer<%= answer.getId() %>" class="innerContent fAnswer">
                            <div class="col votesCount">
                                    <div class="up vote">
                                            <a class="voteDes" href="answerVote?questionid=<%= question.getId()%>&id=<%= answer.getId() %>&dir=up"><i class="material-icons md-48">arrow_drop_up</i></a>
                                    </div>
                                    <div id="answerVote<%= answer.getId() %>">
                                            <%= answer.getVote() %>
                                    </div>
                                    <div class="down vote">
                                        <a class="voteDes" href="answerVote?questionid=<%= question.getId()%>&id=<%= answer.getId() %>&dir=down"><i class="material-icons md-48">arrow_drop_down</i></a>
                                    </div>
                            </div>
                            <div class="col content">
                                    <p>
                                            <%= answer.getContent() %>
                                    </p>
                            </div>
                            <div class="navPost2">
                                <% if(user.getEmail().equals(answer.getEmail())){ %>
                                    <p>
                                        answered by You <span><%= answer.getEmail() %></span>
                                    </p>
                                <% }else{ %>
                                    <p>
                                        answered by <%= answer.getName() %> <span><%= answer.getEmail() %></span>
                                    </p>
                                <% } %>
                            </div>
                    </div>
                    <% } %>
		</div>
                
                <% 
                    String disableF = "disabled-form";
                    String disableFtext = "Sign In to answer.";
                    String disableB = "disabled-button";
                    String disableTag = "disabled";
                    if(user.getId() > -1){
                        disableF = "";
                        disableB = "";
                        disableTag = "";
                        disableFtext = "Input your answer here..";
                    } %>
                
		<div id="answerForm">
			<h1 class="tag">Your Answer</h1>
                        <form onsubmit="return validasi(this);" class="form makeQuestion" method="POST" action="addAnswer?id=<%= question.getId() %>">
				<div class="innerForm">
					<textarea class="textArea <%= disableF %>" name="content" placeholder="<%= disableFtext %>" <%= disableTag %>></textarea>
				</div>
				<div class="innerForm">
					<input class="submitButton <%= disableB %>" type="submit" value="Send Answer" <%= disableTag %>>
				</div>
			</form>
		</div>

<%@ include file="footer.jsp" %>