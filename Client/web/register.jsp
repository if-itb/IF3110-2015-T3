<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Register</title>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
		<a class="homelink" href="index.jsp"><h1 id="title">My StackExchange</h1></a>
		<div class="content">
			<h2>Register</h2>
			<hr>
			<form action="register.jsp", method="post" onsubmit="">
			<input class="textbox" type="text", name="name", id="name" placeholder="Name">
			<br>
			<input class="textbox" type="text", name="email", id="email" placeholder="Email">
			<br>
			<input class="textbox" type="password", name="password", id="password" placeholder="Password">
			<br>
			<input type="submit" id="post"  value="Post">
			</form>
		</div>	
	</div>
	<%@page import= "java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service" %>
	<%@page import= "com.yangnormal.sstackex.WebServiceImplService" %>
	<%@page import= "com.yangnormal.sstackex.WebServiceInterface" %>
	<% 
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	if ((name!= null) && (email!=null) && (password!=null)){
		URL url = new URL ("http://localhost:8082/ws/stackexchange?wsdl");
		QName qname = new QName("http://ws.sstackex.yangnormal.com/","WebServiceImplService");
		WebServiceImplService webService = new WebServiceImplService(url,qname);
		WebServiceInterface ws = webService.getWebServiceImplPort();
		int status = ws.register(name,email,password);
		request.setAttribute("status",status);
		request.setAttribute("name","Register");
		RequestDispatcher dispatcher = request.getRequestDispatcher("status.jsp");
		dispatcher.forward(request,response);
	}
	%>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>