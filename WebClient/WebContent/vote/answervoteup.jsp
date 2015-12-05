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
	<script src="assets/js/jquery-1.11.3.min.js"></script>
    <script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="assets/js/angular.min.js"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>VoteUp answer</title>
	<script>
		function VoteUp(cookie,id){
			alert(cookie)
			var voteUpUrl = "http://localhost:8081/Comment_Vote-WS/rest/voteanswer/voteup";
			var tokenData = {access_token:cookie, id_answer:id}
			$.ajax({
		        url: voteUpUrl,
		        data: tokenData,
		        dataType: "json",
		        type: "POST",
		        success: function(data) {
		        	var vote_value = data;
		            document.getElementById("nilai_vote").innerHTML = data;
		        }
		    });
			
		}
			
	</script>
</head>
<body>
	<script>
		VoteUp("<%=request.getParameter("cookie")%>", <%=request.getParameter("id")%>);
		document.write("")
	</script>
</body>
</html>