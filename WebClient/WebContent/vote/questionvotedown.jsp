<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*, java.io.*"%>
<%@ page import = "org.tusiri.ws.question.QuestionService" %>
<%@ page import = "org.tusiri.ws.question.Question" %>
<%@ page import = "org.tusiri.ws.question.QuestionItem" %>
<%@ page import = "org.tusiri.ws.question.QuestionVoteUp" %>
<%@ page import = "org.tusiri.ws.question.QuestionVoteDown" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
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
	} else {
		//Redirect to signin
	}
	
	QuestionService qservice = new QuestionService();
	Question q = qservice.getQuestionPort();
	int id = Integer.parseInt(request.getParameter("id"));
	int vote_now = q.questionVoteDown(id,access_token);
%>
	<% out.println(vote_now); %>
</body>
</html>