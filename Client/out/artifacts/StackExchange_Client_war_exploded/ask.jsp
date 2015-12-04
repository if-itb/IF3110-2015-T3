<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Your Answer</title>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
		<a class="homelink" href="http://mystackexchange.dev"><h1 id="title">My StackExchange</h1></a>
		<div class="content">
			<h2>What's your question?</h2>
			<hr>
			<form action="ask.jsp", method="post" onsubmit="return validatequestion()">
			<input class="textbox" type="text", name="name", id="name" placeholder="Name">
			<br>
			<input class="textbox" type="text", name="email", id="email" placeholder="Email">
			<br>
			<input class="textbox" type="text", name="topic", id="topic" placeholder="Question Topic">
			<br>
			<textarea class="textarea", name="content", id="content" placeholder="Content" ></textarea>
			<br>
			<input type="submit" id="post"  value="Post">
			</form>
		</div>	
	</div>
	<script type="text/javascript" src="js/script.js"></script>
	<%@page import= "java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service" %>
	<%@page import= "com.yangnormal.sstackex.ws.WebServiceInterface" %>
	<%@page import= "com.yangnormal.sstackex.ws.WebServiceImplService" %>
	<%
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String topic = request.getParameter("topic");
		String content = request.getParameter("content");
		if ((name!= null) && (email!=null) && (topic!=null)&&(content!=null)){
			URL url = new URL ("http://localhost:8082/ws/stackexchange?wsdl");
			QName qname = new QName("http://ws.sstackex.yangnormal.com/","WebServiceImplService");
			WebServiceImplService webService = new WebServiceImplService(url,qname);
			WebServiceInterface ws = webService.getWebServiceImplPort();
			out.println(ws.printMessage());
			/*int status = ws.insertQuestion(name,email,topic,content);
			if (status==0){
				response.sendRedirect("registerSuccess.jsp");
			} else {
				response.sendRedirect("registerFail.jsp");
			}*/
		}
	%>
</body>
</html>