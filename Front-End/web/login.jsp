<%-- 
    Document   : login.jsp
    Created on : Nov 17, 2015, 2:19:38 PM
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
      <a id="logo-container" href="index" class="brand-logo">RestingSOAP</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="about.jsp">About Us</a></li>
        <li class="active"><a href="login.jsp">Log In</a></li>
        <li><a href="register.jsp">Sign Up</a></li>
      </ul>
    </div>
  </nav>

  <div id="index-banner" class="parallax-container">
    <div class="section no-pad-bot">
      <div class="container">
        <br><br>
        <h1 class="header center white-text">StackExchange</h1>
        <div class="row center">
          <h5 class="header col s12 light">Log In to your account</h5>
        </div>
      </div>
    </div>
    <div class="parallax"><img src="images/background1.jpg" alt="Unsplashed background img 1"></div>
  </div>


  <div class="container">
    <div class="section">
        <h2 class="header center blue-text text-darken-4">Log In</h2>
          <div class="row">
            <form class="col s12">
              <div class="row">
                <div class="input-field col s12">
                    <input id="email" name="email" type="email" class="validate">
                  <label for="email">Email</label>
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                  <input id="password" name="password" type="password" class="validate">
                  <label for="password">Password</label>
                </div>
              </div>
              
              <div class="container">
                <div class="section">
                    <div class="row right">
                        <button class="btn waves-effect waves-light blue darken-4" type="submit" name="action" formaction="http://localhost:8080/IdentityService/LoginServlet" formmethod="POST">Submit
                        <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
              </div>
                
            </form>
          </div>
        <br>
    </div>
  </div>
    
  <br><br><br><br>

  <footer class="page-footer black">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">About Us</h5>
          <p class="grey-text text-lighten-4">We are a team of college students working on this project for fulfilling Web-Based Development task.</p>
          <p> <c:out value="${report}"></c:out> </p>
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