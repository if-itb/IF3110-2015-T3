<%-- 
    Document   : about.jsp
    Created on : Nov 17, 2015, 4:19:10 PM
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
  <title>StackExchange</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
  <nav class="white" role="navigation">
    <div class="nav-wrapper container">
      <a id="logo-container" href="index?token=<%= request.getParameter("token") %>" class="brand-logo">RestingSOAP</a>
      <ul class="right hide-on-med-and-down">
        <li class="active"><a href="about.jsp?token=<%= request.getParameter("token") %>">About Us</a></li>
        <li><a href="login.jsp?token=<%= request.getParameter("token") %>">Log In</a></li>
        <li><a href="register.jsp?token=<%= request.getParameter("token") %>">Sign Up</a></li>
      </ul>
    </div>
  </nav>

  <div id="index-banner" class="parallax-container">
    <div class="section no-pad-bot">
      <div class="container">
        <br><br>
        <h1 class="header center white-text">StackExchange</h1>
        <div class="row center">
          <h2 class="header col s12 light">About Us</h2>
        </div>
        <br><br>
      </div>
    </div>
    <div class="parallax"><img src="images/background1.jpg" alt="Unsplashed background img 1"></div>
  </div>
    
  <div class="container">
    <div class="section">

      <!--   Icon Section   -->
      <div class="row">
        <div class="col s12 m4">
          <div class="icon-block">
            <h2 class="center brown-text"><img src="images/vincent.jpg" class="circle foto"></h2>
            <h5 class="center">Vincent Theophilus C.</h5>
            <p class="light center">Teknik Informatika</p>
            <p class="light center">13513005</p>
          </div>
        </div>

        <div class="col s12 m4">
          <div class="icon-block">
            <h2 class="center brown-text"><img src="images/juan.jpg" class="circle foto"></h2>
            <h5 class="center">Juan Anton</h5>
            <p class="light center">Teknik Informatika</p>
            <p class="light center">13513013</p> </div>
        </div>

        <div class="col s12 m4">
          <div class="icon-block">
            <h2 class="center brown-text"><img src="images/hans.jpg" class="circle foto"></h2>
            <h5 class="center">Hans Christian G.</h5>
            <p class="light center">Teknik Informatika</p>
            <p class="light center">13513047</p></div>
        </div>
      </div>
    </div>
  </div>

  <footer class="page-footer black">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">About Us</h5>
          <p class="grey-text text-lighten-4">We are a team of college students working on this project for fulfilling Web-Based Development task.</p>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
      Made by RestingSOAP Group</a>
      </div>
    </div>
  </footer>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>

  </body>
</html>
