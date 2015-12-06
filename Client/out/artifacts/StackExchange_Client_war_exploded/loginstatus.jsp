<!DOCTYPE HTML>
<html>
<head>
<title>Login Status</title>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
		<a class="homelink" href="http://mystackexchange.dev"><h1 id="title">My StackExchange</h1></a>
		<div class="content">
			<h1>
			<%
				int status = request.getAttribute("status");
				if (status==0) {
					out.println("Login Success");
					String token=request.getAttribute("token")

				} else {
					out.println("Login Failed, check your email or password");
				}
				try {
				    Thread.sleep(1000);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				response.sendRedirect("index.jsp");
			%>
			</h1>
			<h2>Login 
		</div>	
	</div>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>