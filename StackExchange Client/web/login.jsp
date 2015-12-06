<%--
  Created by IntelliJ IDEA.
  User: Marco Orlando
  Date: 11/17/2015
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link rel="stylesheet" type="text/css" href="style.css">
  <title>Simple StackExchange</title>
  <script type = "text/javascript"></script>
</head>

<body>
    <!--div id="header_addQuestions">
      <span id="Judul"><a <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %>>StackExchange</a></span>
    <span id="what">Login</span>
  </div-->
    <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %> class="brand-logo">Stack Exchange</a>
      <ul class="right hide-on-med-and-down">
        <li><a <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %>>Home</a></li>
        <li><a href="registration.jsp">Register</a></li>
      </ul>
    </div>
    </nav>
    <br><br>
    <div class="container" id="container">
    <div id="body">
        <div class="container">
            <form name="myForm" action="ClientLogin" method="post" onsubmit="return validateFormQuestion()">
              <input type="email" name="email" placeholder="Email" required><br>
              <input type="password" name="password" placeholder="Password" required><br><br>
              <div class="row center">
                  <input id="download-button" class="btn-large waves-effect waves-light orange" name="submitButton" type="submit" value="Login"/>                  
              </div>
            </form>
        </div>
    </div>
    </div>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="../../bin/materialize.js"></script>
    <script src="js/init.js"></script>
</body>
</html>
