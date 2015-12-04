<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import= "java.net.URL,javax.xml.namespace.QName,java.lang.String" %>
<%@page import= "com.yangnormal.sstackex.WebServiceInterface" %>
<%@page import= "com.yangnormal.sstackex.WebServiceImplService" %>
<%@page import= "com.yangnormal.sstackex.Question" %>
<%
	if (request.getParameter("id")!=null) {
		int id = Integer.parseInt(request.getParameter("id"));
		URL url = new URL ("http://localhost:8082/ws/stackexchange?wsdl");
		QName qname = new QName("http://ws.sstackex.yangnormal.com/","WebServiceImplService");
		WebServiceImplService webService = new WebServiceImplService(url,qname);
		WebServiceInterface ws = webService.getWebServiceImplPort();
		Question question = ws.getQuestion(id);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Edit Post</title>
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
		<a class="homelink" href="index.jsp"><h1 id="title">My StackExchange</h1></a>
		<div class="content">
			<h2>Edit Post</h2>
			<hr>
			<form action="updatepost.jsp", method="post">
			<input class="textbox" type="text", name="topic", id="topic" value="<%out.println(question.getTopic());%>">
			<br>
			<textarea class="textarea", name="content", id="content"><%out.println(question.getContent());%></textarea>
			<br>
			<input type="hidden" name="id" id="id" value=<%out.println(id);%>>
			<input type="submit" id="post" value="Post">
			</form>
		</div>
	</div>
</body>
</html>
<%
	} else {
		response.sendRedirect("index.jsp");
	}
%>