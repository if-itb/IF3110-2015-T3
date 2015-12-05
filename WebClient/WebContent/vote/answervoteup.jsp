<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*, java.io.*"%>
<%@ page import = "org.tusiri.ws.answer.AnswerService" %>
<%@ page import = "org.tusiri.ws.answer.Answer" %>
<%@ page import = "org.tusiri.ws.answer.AnswerItem" %>
<%@ page import = "org.tusiri.ws.answer.AnswerVoteUp" %>
<%@ page import = "org.tusiri.ws.answer.AnswerVoteDown" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>VoteUp answer</title>
<script>
	function VoteUp(){
		var voteUp = "http://localhost:8081/Comment_Vote-WS/rest/voteanswer/voteup";
		var tokenData = {access_token:'<%= request.getParameter("cookie")%>',
							id_answer:'<%=request.getParameter("id")%>'}
		$.ajax({
	        url: regenerateTokenUrl,
	        data: tokenData,
	        dataType: "json",
	        type: "POST",
	        success: function(data) {
	        	var vote_value = data;
	            document.getElementById("nilai_vote").innerHTML = data;
	        }
	    });
	}
	$(document).ready(function(){
		VoteUp();
	});
</script>
</head>
<body>
	<span id = nilai_vote>-9999</span>
</body>
</html>