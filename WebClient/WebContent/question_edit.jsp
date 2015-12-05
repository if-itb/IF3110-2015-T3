<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*, java.io.*"%>
	<%@ page import = "org.tusiri.ws.question.QuestionService" %>
	<%@ page import = "org.tusiri.ws.question.Question" %>
	<%@ page import = "org.tusiri.ws.question.QuestionItem" %>
	
    <jsp:include page="Header.jsp" flush="true">
		<jsp:param name="pageTitle" value="Edit Question" />
		<jsp:param name="isNeedCookieCheck" value="true" />
		<jsp:param name="check" value="2" />
		<jsp:param name="q_id" value='<%= request.getParameter("id") %>' />
	</jsp:include>
	
<%
	int id_question = Integer.parseInt(request.getParameter("id"));
	Cookie cookie = null;
	Cookie[] cookies = null;
	String access_token = null;
	// Get an array of Cookies associated with this domain
	cookies = request.getCookies();
	if( cookies != null ){
		for (int i = 0; i < cookies.length; i++){
			cookie = cookies[i];
			if(cookie.getName().equals("access_token")){
				access_token = cookie.getValue();
				break;
			}
		}
	
		if((access_token != null) && (access_token.length()>0)){
			//check access_token validity to server
%>
	
</head>

<body class="contact">
	
<%

	QuestionService qservice = new QuestionService();
    Question q = qservice.getQuestionPort();
    QuestionItem qi = q.getQuestionInfo(access_token,id_question);
    
    String judul = qi.getTopic();
    String content = qi.getContent();
%>
    
    <div id="page-wrapper">
		<!-- Header -->
		<header id="header">
			<h1 id="logo"><a href="index.jsp">Stack Exchange <span>| by Tusiri</span></a></h1>
			<jsp:include page="navigationbar.jsp" flush ="true"/>
		</header>
	<article id="main">
		<header class="special container">
			<span class="icon fa-edit"></span>
			<strong><h2>Edit Question</h2></strong>
			<p>Fill the form below to edit your question.</p>
		</header>
		<!-- One -->
		<section class="wrapper style4 special container 75%">
		<!-- Content -->
			<div class="content">
				<form name="sentMessage" id="contactForm" action="question_edit_post.jsp" METHOD="POST">
					<div class="control-group form-group">
						<div class="controls">
							<strong><label class="questionmenu">Topic:</label></strong>
                            <input type="text" class="form-control" name="topic" required="true" required data-validation-required-message="Please enter your topic" value='<%= judul %>'>
                            <p class="help-block"></p>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<strong><label class="questionmenu">Question:</label></strong>
                           	<textarea rows="10" cols="100" class="form-control" name="content" required data-validation-required-message="Please enter your message" maxlength="999" style="resize:none" ><%= content %></textarea>
						</div>
					</div>
					<input type='hidden' name='id_question' id='id_question' value=<%= id_question %> />
					<div class="row">
						<div class="12u">
							<ul class="buttons">
								<li><input type="submit" class="special" value="Edit Question" /></li>
							</ul>
						</div>
					</div>
				</form>
			</div>
		</section>
	</article>
	
	<%@include file="footer.jsp" %>
	</div>
</body>
</html>

<% 		}
	}%>