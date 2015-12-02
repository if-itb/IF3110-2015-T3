<%-- 
    Document   : login
    Created on : 23-Nov-2015, 22:51:05
    Author     : KEVIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Simple StackExchange - Login</title>	
    <link href="css/mainstyle.css" rel="stylesheet">
  </head>
  <body> 
    <header id="top" class="header">
      <div class="text-vertical-center">
        <h1>Login</h1>			
	<form method="POST" action="http://localhost:8082/IdentityServices/Token">    
          <div>
              <h2>Email<span class="error">* : <input type ="text" name="uname" value=""> </span></h2>
          </div>
          <div>
              <h2>Password<span class="error">* : <input type ="password" name="pword" value=""> </span></h2> 
          </div>
          <div>		
            <input type="submit" name="submit" value="Submit" class="btn btn-dark btn-lg">
          </div>
        </form>
        <%
        if(request.getParameter("ef") != null){
        %>
        <div class="error center">
            Username does not exist or password does not match!
        </div>
        <%
        }
        %>
      </div>
    </header>
  </body>
</html>
