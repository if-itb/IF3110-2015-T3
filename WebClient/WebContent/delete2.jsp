<%@ page import="java.util.*, java.io.*"%>
<%@ page import = "org.tusiri.ws.question.QuestionService" %>
<%@ page import = "org.tusiri.ws.question.Question" %>
<%@ page import = "org.tusiri.ws.question.QuestionItem" %>
<%
	QuestionService qservice = new QuestionService();
	Question q = qservice.getQuestionPort();
	String access_token = request.getParameter("access_token");
	int id_question = Integer.parseInt(request.getParameter("id_question"));
	
	int result = q.deleteQuestion(access_token, id_question);
	if(result>0){//success
		out.println(1);
	} else {
		out.println(0);
	}
%>