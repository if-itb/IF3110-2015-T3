<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*, java.io.*"%>
<%@ page import = "org.tusiri.ws.answer.AnswerService" %>
<%@ page import = "org.tusiri.ws.answer.Answer" %>
<%@ page import = "org.tusiri.ws.answer.AnswerItem" %>
<html>
<head>
<title>Insert Title Here</title>
<link rel="stylesheet" type="text/css" href="assets/css/main.css">
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
int q_id = Integer.parseInt(request.getParameter("q_id"));
int result = a.createAnswer(access_token, q_id, request.getParameter("content"));
if(result>0){//success
	String site = new String("question.jsp?q_id="+q_id);
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", site); 
}


%>
	<div class="outerNotFound">
		<div class="middleNotFound">
			<center>
				<strong><font size="5pt">Please Sign in First</font><br></strong>
				<font size="5pt">Go to <a href="signin.jsp">Sign In</a></font>
			</center>
		</div>
	</div>
</body>
</html>