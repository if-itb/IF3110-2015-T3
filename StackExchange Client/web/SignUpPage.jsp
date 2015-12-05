<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
    <head>
        <title>StackExchange Sign Up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div id="big">StackExchange Register</div>
	 <div class="mediumbaru">
        <form action="SignUpPage.jsp" name="newuser" method="post">
		<input type="text" name="name" placeholder="Name" class="medium">
		<input type="email" name="email" placeholder="Email" class="medium">
		<input type="password" name="password" placeholder="Password" class="medium">
		<input type="submit" value="Post" id="button">
        </form> 
        </div>
    </body>
    <%
        // TODO initialize WS operation arguments here
        java.lang.String name = request.getParameter("name");
        java.lang.String email = request.getParameter("email");
        java.lang.String password = request.getParameter("password");
        if(email!="" && email!=null) {
            try {
                registration.RegistrationWS_Service service = new registration.RegistrationWS_Service();
                registration.RegistrationWS port = service.getRegistrationWSPort();
                boolean result = port.register(name, email, password);
                if(result) 
                    response.sendRedirect(request.getContextPath() + "/LogInPage.jsp");
                else 
                    response.sendRedirect(request.getContextPath() + "/SignUpPage.jsp");
            } catch (Exception ex) {}
        }
    %>
</html>