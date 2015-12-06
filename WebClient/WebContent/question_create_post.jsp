<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*, java.io.*"%>
<%@ page import = "org.tusiri.ws.question.QuestionService" %>
<%@ page import = "org.tusiri.ws.question.Question" %>
<%@ page import = "org.tusiri.ws.question.QuestionItem" %>
<html>
<head>
<title>Question Create Post</title>
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
			String user_agent = request.getHeader("User-Agent");
			String ip_adr = request.getRemoteAddr();
			access_token += "#" + user_agent + "#" + ip_adr;
			break;
		}
	}
} else {
	//Redirect to signin
}

QuestionService qservice = new QuestionService();
Question q = qservice.getQuestionPort();
int result = q.createQuestion(access_token, request.getParameter("topic"), request.getParameter("content"));
if(result>0){//success
	//String site = new String("question?id="+result);
	String site;
	if(request.getRequestURL().lastIndexOf(".") == request.getRequestURL().length()-4){
		site = new String("index.jsp");
	} else {
		site = new String("../index.jsp");
	}
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", site); 
}


%>
<%= result %>
<center>
<ul>
<li><p><b>Topic:</b>
   <%= request.getParameter("topic")%>
</p></li>
<li><p><b>Content:</b>
   <%= request.getParameter("content")%>
</p></li>
</ul>
</body>
</html>