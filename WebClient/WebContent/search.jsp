<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*, java.io.*"%>
<%@ page import = "org.tusiri.ws.question.QuestionService" %>
<%@ page import = "org.tusiri.ws.question.Question" %>
<%@ page import = "org.tusiri.ws.question.QuestionItem" %>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    	pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" flush="true">
		<jsp:param name="pageTitle" value="Search Question" />
	</jsp:include>
</head>
<body class="contact noscript">
	<div id="page-wrapper">
	<!-- Header -->
	<header id="header">
		<h1 id="logo"><a href="index">Stack Exchange <span>| by Tusiri</span></a></h1>
		<jsp:include page="navigationbar.jsp" flush ="true"/>
	</header>
	
	<%
	QuestionService qservice = new QuestionService();
	Question q = qservice.getQuestionPort();
	String keyword = request.getParameter("search");
	List<QuestionItem> questionList = q.searchQuestion(keyword);
	int n = questionList.size();
	%>

	<article id="main">
		<header class="special container">
			<span class="icon fa-search"></span>
			<strong><h2>Search Question</h2></strong>
			<p>Total : <%= n %> question(s).</p>
		
			<div class="special container wrapper style1">
				<div class="search">
					<form action = "search.jsp" method="GET">
						<div class="row">
							<div class="9u 12u(mobile)">
								<input id = 'searchbar' type="text" name="search" placeholder="Search" />
							</div>
							<div class="3u 12u(mobile)">
								<input id = 'searchsubmit' type="submit"/>
							</div>
						</div>
					</form>
			        <p>Cannot find what you are looking for? <a href = "question_create.jsp">Ask here</a></p>
				</div>
			</div>
		</header>
		
		<!-- One -->
		<section class="wrapper style4 container">
		<!-- Content -->
			<div class="content">
			<% 
				for (int i = 0; i < n; i++) { 
				int id = questionList.get(i).getIdUser();
			%>
	
				<div id='question_item_<%= questionList.get(i).getIdQuestion() %>'>
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
			</div>
		</section>
	</article>
<%@include file="footer.jsp" %>
</div>
</body>
</html>