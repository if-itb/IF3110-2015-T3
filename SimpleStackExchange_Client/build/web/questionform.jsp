<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="tool.Util" %>
<% if(!Util.isLogin(request)) {%>
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
       
      </div>
    </nav>
        
      <div class="container">

          <form class="form-signin" action="${pageContext.request.contextPath}/QuestionCreate" method="POST">
          <h2 class="form-signin-heading">Ask Your Question!<br/>
              <small></small></h2>
          <hr/>
          <div class="form-group">
                <label for="inputTopic" class="sr-only">Topic</label>
                <input name="topic" type="text" id="inputTopic" class="form-control" placeholder="Topic Question" required="" autofocus="">
          </div>
          <div class="form-group">
            <textarea placeholder="Content" id="inputContent" class="form-control" rows="5" name="content"></textarea>
          </div>
          
          <div class="form-group">
          <button class="btn btn-lg btn-primary btn-block" type="submit">Ask</button>
          </div>
        </form>

      </div> <!-- /container -->
  </body>
</html>