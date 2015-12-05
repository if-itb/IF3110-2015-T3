<%-- 
    Document   : register
    Created on : Nov 16, 2015, 11:35:54 PM
    Author     : chairuniaulianusapati
--%>

<%@page import="java.util.*" %>

<%@page import="java.lang.Exception" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href='css/style.css'/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <base href="http://localhost:8080/StackExchange_Client/">

        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">    
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>    
        
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container">
            <% out.write("<a id='logo-container' href='index.jsp?token="+ request.getParameter("token") +"' class='brand-logo'>Home</a>");%>
            <ul class="right hide-on-med-and-down">
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
            </ul>

            <ul id="nav-mobile" class="side-nav">
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
            </ul>
            <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
            </div>
        </nav>   
        <script>
            $(document).ready(function(){

              // Initialize collapse button
              $(".button-collapse").sideNav();
            });
        </script>   
        <title>Simple StackExchange</title>
    </head>

    <body>
        <div class="container">
            <br><br>
            <h1 class="header center orange-text">Register here !</h1>
            <br><br>
        </div>
        
        
        
        
        
        
        
        
        
        
        <div class="row">
            <%out.write("<form id='registerForm' class='col s12' name='registerForm' action='registerresult.jsp?token="+ request.getParameter("token") +"' method='POST'>");%>
              <div class="row">
                <div class="input-field col s12">
                  <input placeholder="Name" name="name" id="name" type="text" required>
                  <label for="name" data-error="wrong" data-success="right">Name</label> 
                </div>
              </div>
            <div class="row">
                <div class="input-field col s12">
                  <input placeholder="Email" name="email" id="email" type="email" class="validate" required>
                  <label for="email" data-error="wrong" data-success="right">Email</label> 
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                  <input placeholder="Password" id="password" type="password" name="password" class="validate" required>
                  <label for="password">Password</label>
                </div>
              </div>
              <button class="btn waves-effect waves-light" type="submit" name="action">register
                <i class="material-icons right">send</i>
            </button>
            </form>
          </div>   
  
    </body>
</html>
