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
	
	AnswerService qservice = new AnswerService();
	Answer a = qservice.getAnswerPort();
	int id = Integer.parseInt(request.getParameter("id"));
	int vote_now = a.answerVoteUp(id,access_token);
%>
	<% out.println(vote_now); %>
</body>
</html>