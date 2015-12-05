<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*, java.io.*"%>
<%@ page import = "org.tusiri.ws.user.UserService" %>
<%@ page import = "org.tusiri.ws.user.User" %>
<html>
	<head>
		<title>Registration Post</title>
		<link rel="stylesheet" type="text/css" href="assets/css/main.css">
	</head>
	<body class="contact notFoundBody">
	<%
		UserService uservice = new UserService();
		User u = uservice.getUserPort();
		String message = "";

		int result = u.createUser(request.getParameter("username"), request.getParameter("password"),request.getParameter("email"),request.getParameter("fullname"));
		if(result>0){//success
			String site = new String("User.jsp?id="+result+"&s=1");
			response.setStatus(response.SC_MOVED_TEMPORARILY);			
			response.setHeader("Location", site); 
		}
		else if(result==-1){//exist redirect into sorry page
			message  = "Email has been registered. Please choose another email";
			
		}
		else{
			message = "Ooops.. Something went wrong. Please retry in a few moment";
		}
	%>
	
	<div class="outerNotFound">
		<div class="middleNotFound">
			<center>
				<strong><font size="5pt"><%=message %></font><br></strong>
				<font size="5pt">Back to <a href="register.jsp">Register</a></font>
			</center>
		</div>
	</div>
</body>
</html>