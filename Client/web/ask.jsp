<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Your Question</title>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
		<a class="homelink" href="index.jsp"><h1 id="title">My StackExchange</h1></a>
		<div class="content">
			<h2>What's your question?</h2>
			<hr>
			<form action="ask.jsp", method="post" onsubmit="">
			<input class="textbox" type="text", name="topic", id="topic" placeholder="Question Topic">
			<br>
			<textarea class="textarea", name="content", id="content" placeholder="Content" ></textarea>
			<br>
			<input type="submit" id="post"  value="Post">
			</form>
		</div>	
	</div>
	<script type="text/javascript" src="js/script.js"></script>
	<%@page import= "java.net.URL,javax.xml.namespace.QName,java.lang.String" %>
	<%@page import= "com.yangnormal.sstackex.WebServiceInterface" %>
	<%@page import= "com.yangnormal.sstackex.WebServiceImplService" %>
	<%

		String topic = request.getParameter("topic");
		String content = request.getParameter("content");
		String token = "";
		Cookie[] cookies = request.getCookies();
		for (int i=0;i<cookies.length;i++){
			if (cookies[i].getName().equals("token")){
				token = cookies[i].getValue();
			}
		}
		if ((topic!=null)&&(content!=null)&&!(token.equals(""))){
			URL url = new URL ("http://localhost:8082/ws/stackexchange?wsdl");
			QName qname = new QName("http://ws.sstackex.yangnormal.com/","WebServiceImplService");
			WebServiceImplService webService = new WebServiceImplService(url,qname);
			WebServiceInterface ws = webService.getWebServiceImplPort();
			int status = ws.postQuestion(token,topic,content);
			request.setAttribute("status",status);
			request.setAttribute("name","Ask Question");
			RequestDispatcher dispatcher = request.getRequestDispatcher("status.jsp");
			dispatcher.forward(request,response);
		} else if (token.equals("")){
			response.sendRedirect("login.jsp");
		}
	%>
</body>
</html>