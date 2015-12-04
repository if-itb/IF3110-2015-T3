<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Register Status</title>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container status">
		<a class="homelink" href="index.jsp"><h1 id="title">My StackExchange</h1></a>
		<div class="content">
		<%
			int status = (Integer)request.getAttribute("status");
			String name = (String) request.getAttribute("name");
			if (status==1){
				out.println(name+" Success!");
			} else {
				out.println(name+" Failed!");
			}
		%>
		</div>	
	</div>

	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>