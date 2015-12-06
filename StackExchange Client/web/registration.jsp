<%--
  Created by IntelliJ IDEA.
  User: Marco Orlando
  Date: 11/17/2015
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <!--link rel="stylesheet" type="text/css" href="style.css"-->
  <title>Simple StackExchange</title>
  <script type = "text/javascript"></script>
</head>

<body>
    <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %> class="brand-logo">Stack Exchange</a>
      <ul class="right hide-on-med-and-down">
        <li><a <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %>>Home</a></li>
        <li><a href="login.jsp">Login</a></li>
      </ul>
    </div>
    </nav>
    <br><br>
    <div class="container" id="container">
      <div id="body">
          <div class="cotainer center">
              <h5 id="what">Registration Form</h5><br>
              <div class="container divider" style="border: solid 1.5px; width:500px;"></div><br><br>
              <form name="registForm" action="registrationProcess.jsp" method="post">
                    <input type="text" name="name" placeholder="Name" required><br>
                    <input type="email" name="email" placeholder="Email" required><br>
                    <input type="password" name="password" placeholder="Password" required><br><br>
                    <div class="row center">
                        <input id="download-button" class="btn-large waves-effect waves-light orange" name="submitButton" type="submit" value="Register"/>                  
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
