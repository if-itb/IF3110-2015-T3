<%-- 
    Document   : login
    Created on : Nov 18, 2015, 9:40:20 AM
    Author     : Gerry
--%>
<%@page import="java.util.*"%>
<html lang="en">
  <head>
    <title>Stack Exchange</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/register-login.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  </head>
  
  <body>
    <nav class="deep-purple darken-2" role="navigation">
      <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Stack Exchange - Login</a>
        <ul class="right hide-on-med-and-down">
          <li><a href="index.jsp">Home</a></li>
          <li><a href="register.jsp">Register</a></li>
          <li><a href="index.jsp?token=null">Logout</a></li>
        </ul>

        <ul id="nav-mobile" class="side-nav">
          <li><a href="index.jsp">Home</a></li>
          <li><a href="register.jsp">Register</a></li>
        </ul>
        <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
      </div>
    </nav>
    <br><br><br>
    <div class="container">
      <nav class="deep-purple darken-2" role="navigation">
        <div class="nav-wrapper">
          <form>
            <div class="input-field">
              <input id="search" type="search" required>
              <label for="search"><i class="material-icons">search</i></label>
              <i class="material-icons">close</i>
            </div>
          </form>
        </div>
      </nav>
    </div>
    <br><br>
    <%
        try{
            int valid = Integer.parseInt(request.getParameter("valid"));
            if(valid == 0)
                out.println("<div class='center'><br> Login unsuccessful, Incorrect Email or Password ! </div>");
            else{
                String token = request.getParameter("token");
                response.sendRedirect("http://localhost:8080/StackExchangeClient/index.jsp?token="+token);
            }
        }catch(Exception ex){
            
        }
    %>
    <div class="container">
      <form class="container center">
        <div class="row">
          <div class="input-field">
            <input id="email" type="email" name="email" class="validate" required>
            <label for="email">Email</label>
          </div>
          <div class="input-field">
            <input id="password" type="password" name="password" class="validate" required>
            <label for="password">Password</label>
          </div>
        </div>
        <div class="container center">
            <button class="btn waves-effect waves-light deep-purple darken-2" type="submit" formmethod="POST" formaction="http://localhost:8082/IdentityService/LoginServlet">Login
              <i class="material-icons right">send</i>
            </button>
        </div>
      </form>
    </div>
    
    <br><br><br><br><br><br>
    
    <footer class="page-footer deep-purple darken-2">
      <div class="footer-copyright">
        <div class="container">
          © 2015 Created by 3xcelsi0r
        </div>
      </div>
    </footer>

    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/materialize.min.js"></script>
    <script src="js/init.js"></script>
  </body>
</html>