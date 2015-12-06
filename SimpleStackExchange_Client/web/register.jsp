<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="tool.Util" %>
<% if(Util.isLogin(request)) {%>
    <c:redirect url="/"/>
<%}%>
<html lang="en">
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="assets/img/favicon.ico">

    <title>Signup to Simple Stack Exchange</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
  </head>

    <body>
      <nav class="navbar">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Simple <strong>StackExchange</strong></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
            <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/UserLogin" method="POST">
            <div class="form-group">
              <input type="text" name="email" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" name="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
        
      <div class="container">

          <form class="form-signin" action="register" method="POST">
          <h2 class="form-signin-heading">Sign up now!<br/>
              <small>So, you can access full feature from Simple Stack Exchange</small></h2>
          <hr/>
          <div class="form-group">
          <label for="inputName" class="sr-only">Name</label>
          <input name="name" type="text" id="inputName" class="form-control" placeholder="Name" required="" autofocus="">
          </div>
          <div class="form-group">
          <label for="inputEmail" class="sr-only">Email address</label>
          <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
          </div>
          <div class="form-group">
          <label for="inputPassword" class="sr-only">Password</label>
          <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
          </div>
          <div class="form-group">
          <label for="confirmPassword" class="sr-only">Password</label>
          <input name="cpassword" type="password" id="confirmPassword" class="form-control" placeholder="Password" required="">
          </div>
          <div class="checkbox form-group">
            <label>
              <input name="check" type="checkbox" value="ok"> I accept the terms provided 
            </label>
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
        </form>

      </div> <!-- /container -->
  </body>
</html>