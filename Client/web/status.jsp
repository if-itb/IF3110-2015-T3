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
			} else if (status==-1) {
				out.println(name+" Failed! Token Expired!");
			} else if (status==-2){
				out.println(name+" Failed! Different IP!");
			} else if (status==-3) {
				out.println(name+" Failed! Different User Agent!");
			} else {
				out.println(name+" Failed! Unknown error!");
			}
		%>
		</div>	
	</div>

	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>