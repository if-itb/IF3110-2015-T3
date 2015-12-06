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
			<h2>Register</h2>
			<hr>
			<form action="register.jsp", method="post" onsubmit="return validateRegistration()">
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
	<%@page import= "com.yangnormal.sstackex.ws.WebServiceImpl;" %>
		
	<% 
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	if ((name!= null) && (email!=null) && (password!=null)){
		URL url = new URL ("http://localhost:8080/ws/stackexchange?wsdl");
		QName qname = new QName("http://ws.yangnormal.com/","RegistrationImplService");
		Service service = Service.create(url,qname);
		WebServiceImpl ws = service.getPort(WebService.class);
		int status = ws.register(name,email,password); //method register di webservicenya return status ngecek kalau emailnya udah ada di DB atau belum
		if (status==0){
			response.redirect("registerSuccess.jsp");
		} else {
			response.redirect("registerFail.jsp");
		}
	}
	%>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>