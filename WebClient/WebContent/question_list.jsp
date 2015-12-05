<%@ page import="java.util.*, java.io.*"%>
<%@ page import = "org.tusiri.ws.question.QuestionService" %>
<%@ page import = "org.tusiri.ws.question.Question" %>
<%@ page import = "org.tusiri.ws.question.QuestionItem" %>

<h2 style="color:darkblue">Recently Asked Questions</h2>
<hr>

<%
	QuestionService qservice = new QuestionService();
	Question q = qservice.getQuestionPort();
	List<QuestionItem> questionList = q.getQuestionList();
	int n = questionList.size();
%>

<% for (int i = 0; i < n; i++) { 
	int id = questionList.get(i).getIdUser(); 
%>
	
	<div class = 'content' id='question_item_<%= questionList.get(i).getIdQuestion() %>'>
		<div class = 'row q_or_a'>
			<div class = 'left'>
				<span class = 'vote'>
					<% int tmp = questionList.get(i).getNumVote(); 
						out.println(tmp);	
					%>
					<br>Votes
				</span>
				<span class = 'answer'>
					<% tmp = questionList.get(i).getNumAnswer(); 
						out.println(tmp);
					%>
					<br>Answers
				</span>
			</div>
			<div class = 'mid'>
				<a class = 'topic' href='question.jsp?q_id=<%= questionList.get(i).getIdQuestion() %>'><%= questionList.get(i).getTopic() %><br></a>
				<div class = 'q_content'>
				<%
  					String str = questionList.get(i).getContent();
		  			if(str.length() < 180) {
		  				out.println (questionList.get(i).getContent());
		  			} else {
		  				out.println (str.substring(0, 180)+"...");
		  			}
   				%>
   				</div>
			</div>
		</div>
		<div class = 'details'>Asked by 
			<span class = 'b_link'><%= questionList.get(i).getUsername() %> </span>
			<span hidden class = 'modify_<%=id%>'>
				|
              		<a href = 'question_edit.jsp?id=<%= questionList.get(i).getIdQuestion() %>' class = 'y_link'> edit </a>|
              		<a onclick='delQuestion(<%= questionList.get(i).getIdQuestion() %>,true)' class = 'r_link'>delete</a><br>
              	</span>
              </div>
	</div>
       <hr>
	<% } %>