<!DOCTYPE html>
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

          <form class="form-signin" action="${pageContext.request.contextPath}/UserLogin" method="POST">
          <h2 class="form-signin-heading">Login now!<br/>
              <small></small></h2>
          <hr/>
          <div class="form-group">
          <label for="inputEmail" class="sr-only">Email address</label>
          <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
          </div>
          <div class="form-group">
          <label for="inputPassword" class="sr-only">Password</label>
          <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
        </form>

      </div> <!-- /container -->
  </body>
</html>