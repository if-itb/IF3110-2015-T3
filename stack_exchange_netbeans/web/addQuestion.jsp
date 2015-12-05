<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>StackExchange</title>
    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="assets/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link rel="stylesheet" type="text/css" href="assets/css/question.css">
</head>
<body class="cyan lighten-2">
    <nav class="white" role="navigation">
      <div class="nav-wrapper container">
         <c:choose>
        <c:when test="${token==null}">
            <a href="http://localhost:8080/stack_exchange_netbeans/index"><img src="assets/image/Bicep.jpg" alt="Unsplashed background img 1" width="48" height="48"> </a>
            <a id="logo-container" href="http://localhost:8080/stack_exchange_netbeans/index" class="brand-logo">LEXCLE</a>
        </c:when>    
        <c:otherwise>
            <a href="http://localhost:8080/stack_exchange_netbeans/index?token=<c:out value="${token}"/>"><img src="assets/image/Bicep.jpg" alt="Unsplashed background img 1" width="48" height="48"> </a>
            <a id="logo-container" href="http://localhost:8080/stack_exchange_netbeans/index?token=<c:out value="${token}"/>" class="brand-logo">LEXCLE</a>
        </c:otherwise>
       </c:choose>
        <ul class="right hide-on-med-and-down">
          <li><a href="#">Login</a></li>
          <li><a href="#">Register</a></li>
        </ul>
        <a href="WEB-INF/web.xml"></a>
        <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
      </div>
    </nav>
    <div id="index-banner" class="parallax-container">
        <div class="section no-pad-bot">
          <div class="container">
              <br><br>
              <h1 class="header center white-text text-lighten-2">StackExchange</h1>
              <div class="row center">
                 <h5 class="header col s12 light">What's Your Question?</h5>
              </div>
          </div>
        </div>
        <div class="parallax"></div>
    </div>
    <div class="container">
      <form name="addQuestion" action="http://localhost:8080/stack_exchange_netbeans/AddNewQuestionServlet?token=<c:out value="${token}"/>" method = "Post">
        <input type="text" class="form white-text" placeholder="Question Topic" name="Topic">
        <textarea class="form white-text" placeholder="Content" rows="5" name="Content"></textarea>
        <div class="align-right">
          <button class="button">Post</Button>
        </div>
      </form>
    </div>
</body>
</html>
