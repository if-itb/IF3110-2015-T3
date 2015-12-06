<%-- 
    Document   : view-question
    Created on : Nov 17, 2015, Nov 17, 2015 8:06:32 AM
    Author     : Fikri-PC
--%>

<%@page import="AnswerWS.Answer"%>
<%@page import="AnswerWS.AnswerWS"%>
<%@page import="AnswerWS.AnswerWS_Service"%>
<%@page import="QuestionWS.QuestionWS"%>
<%@page import="QuestionWS.QuestionWS_Service"%>
<%@page import="QuestionWS.Question"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="include/header.jsp" %>
<%
    String sid = request.getParameter("id");
    int id = Integer.parseInt(sid);
%>
<div id="view-qeustion-page">
<!-- pertanyaan-->
    <%
	QuestionWS_Service service = new QuestionWS_Service();
	QuestionWS port = service.getQuestionWSPort();
	 // TODO initialize WS operation arguments here
	
	// TODO process result here
	Question Q = port.getQuestionById(id);
    %>
	<div class="section">	
		<h2><% out.print(Q.getTopic());%></h2>
		<div class="row">
                    <div class="col vote" ng-controller="xvote">
				<div class = "vote-btn">                                   
				<button type="button" onclick="vote(q, id, email, 1)"><i class="fa fa-chevron-up"></i></button>
                                <p class = "number-vote" > {{vote_num}}</p>
				<button type="button" onclick="vote(q, id, email, -1))"><i class="fa fa-chevron-down"></i></button>
				</div>
			</div>
			<div class="col content">
				<p><% out.print(Q.getContent());%></p>
				<br>
			</div>
		</div>
		<div class = "row info" align = "right">
			Ditanyakan oleh <span class="name"><% out.print(Q.getName());%></span> 
                        <% if ((session.getAttribute("sessionName") != null) && (session.getAttribute("sessionName").toString().equals(Q.getName()))) { %>
                            | <span class="link edit"> <a href= "question.jsp?id=<%out.print(id);%>">Edit</a> </span> | 
                            <span class="link delete"> <a href= "javascript:delete_question(<% out.print(id);%>)" >Delete</a></span>
                        <% } %>
		</div>
		<br>
	</div>
<!-- Jawaban --> 
	<div class="section" id="answers">
		<%
                try {
                    QuestionWS_Service service1 = new QuestionWS_Service();
                    QuestionWS port1 = service1.getQuestionWSPort();
                     // TODO initialize WS operation arguments here
                    
                    // TODO process result here
                    int result1 = port1.getNumAnswer(id);
                    out.println("<h2>"+result1+ " Jawaban </h2>");
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                %>
                
                    <%-- start web service invocation --%><hr/>
                <%
                    AnswerWS_Service service2 = new AnswerWS_Service();
                    AnswerWS port2 = service2.getAnswerWSPort();
                     // TODO initialize WS operation arguments here
                    int qId = id;
                    // TODO process result here
                    java.util.List<Answer> result2 = port2.getAnswers(qId);
                   
                %>
                <%-- end web service invocation --%><hr/>
                <%
                    for(Answer Ans : result2) {
                %>
                    <div class = "row">
				<div class = "col vote">
                                    <button type="button" onclick="vote(a, id, email, 1)"><i class="fa fa-chevron-up"></i></button>
                                    <p class = "number-vote" > {{vote_num}}</p>
                                    <button type="button" onclick="vote(a, id, email, -11)"><i class="fa fa-chevron-down"></i></button>
				</div>
				<div class = "col content">
					<p> <% out.print(Ans.getAnsContent());%></p>
					<br>
				</div>
				<div class = "row info" align = "right">

					<p>Dijawab oleh <span class = "name"><% out.print(Ans.getAnsName());%></span> </p>

				</div>
				<hr>
			</div>
		<% } %>
	</div>

<!-- Form untuk menjawab-->
	<div class="section" id="form-answer">
		<h2>Beri jawaban :</h2>
		<form class = "block" action = 'actions/post-answer.jsp?id=<% out.print(id);%>' name = "myForm" method = 'POST' onsubmit = "return(validateAnswer());">
			<ul>
				<textarea rows = '100' cols = '100' placeholder="Jawaban" name = 'Jawaban'></textarea>
				<input type = 'submit' value = "Kirim"></input>
			</ul>
		</form>
	</div>
                        <%
	//<?php mysql_close($link); ?>
                %>
	</div>
</div>

<%@include file="include/footer.jsp" %>