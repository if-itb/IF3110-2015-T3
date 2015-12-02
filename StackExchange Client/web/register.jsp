<%-- 
    Document   : register
    Created on : 23-Nov-2015, 22:51:25
    Author     : KEVIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <link href="css/mainstyle.css" rel="stylesheet">
  </head>
  <body>
    <header id="top" class="header">
      <div class="text-vertical-center">
        <h1>Register your mail</h1>	
        <form method="POST" action="registervalidation.jsp">
          <div>
            <h2>Name<span class="error">* : <input type ="text" name="name"> </h2>
          </div>
          <div>
            <h2>E-mail address<span class="error">* : <input type ="text" name="email"> </h2> 
          </div>
          <div>
            <h2>Password<span class="error">* : <input type ="password" name="password"> </h2> 
          </div>
          <div>		
            <input type="submit" name="submit" value="Submit" class="btn btn-dark btn-lg">
          </div>
        </form>
        <%
          if (request.getParameter("res") != null) {
        %>
        <p>Email is exist, choose another one!</p>
        <%
          } 
        %>
      </div>
    </header>  
  </body>
</html>
