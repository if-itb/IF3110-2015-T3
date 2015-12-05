<%@ include file="header.jsp" %>
<jsp:useBean id="questions" type="java.util.List<stackexchange.webservice.Question>" scope="request" /> 
		<div id="search">
                    <form method="get" action="home">
                        <input class="textForm" type="text" name="query" />
                        <input class="submitButton" type="submit" value="Search" />
                    </form>
		</div>
                <% if(user.getId() <= -1){ %>
		<p class="midText">Want to asking a question? Please <span><a class="submitButton" href="register?reg=true" >Sign Up</a></span></p>
		<% } %>
                <div id="recentAsk">
			<h1 class="tag">Recently Asked Question</h1>
			<% for(stackexchange.webservice.Question question: questions) {%>
			<div id="question<%= question.getId() %>" class="innerContent">
				<div class="col countVotes">
					<div>
						<span><%= question.getVote() %></span>
					</div>
					<div>
						Votes
					</div>
				</div>
				<div class="col countAnswers">
					<div>
						<span><%= question.getAnswer() %></span>
					</div>
					<div>
						Answers
					</div>
				</div>
				<div class="col topic">
					<div>
                                            <a class="link" href="singleQuestion?id=<%= question.getId() %>"><h3><%= question.getTopic() %></h3></a>
					</div>
					<div>
                                            <% if (question.getContent().length()>200) {%>
                                                <p><%= question.getContent().substring(0, 200)+"..." %></p>
                                            <% }else{ %>
                                                <p><%= question.getContent() %></p>
                                            <% } %>
					</div>
				</div>
				<div class="col navPost">
                                    <% if(user.getEmail().equals(question.getEmail())){ %>
					<p>
                                            asked by You <br> 
                                            <a class="link edit" href="questionedit?id=<%= question.getId() %>"> edit</a> <br> 
                                            <a class="link delete" href="questiondelete?id=<%= question.getId() %>" onclick="return validateDelete();"> delete </a>
					</p>
                                    <% }else{ %>
                                        <p>
                                            asked by <%= question.getName() %> <br> 
                                        </p>
                                    <% } %>
				</div>
			</div>
                        <% } %>
		</div>

<%@ include file="footer.jsp" %>