<%@page import="model.Question"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.stackexchange.webservice.service.QuestionWS;" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
  <title>StackExchange</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
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
      <li><a href="http://localhost:7000/login">Login</a></li>
      <li><a href="http://localhost:7000/register">Register</a></li>
    </ul>
  </div>
</nav>

<div id="index-banner" class="parallax-container">
  <div class="section no-pad-bot">
    <div class="container">
        <br><br>
        <h1 class="header center white-text text-lighten-2">StackExchange</h1>
        <div class="row center">
           <h5 class="header col s12 light">Cannot find what you are looking for?<a class ="orange-text" href="http://localhost:8080/stack_exchange_netbeans/CheckAddQuestionServlet?token=<c:out value="${token}"/>">Ask Here</a></h5>
        </div>
    </div>
  </div>
  <div class="parallax"></div>
</div>

<c:forEach items="${QList}" var="question">  
    <div class="row">
      <div class="col s10 offset-s1 l10">
        <div class="card blue-grey darken-1">
          <div class="card-content white-text">
              <span class="card-title"><a href = "http://localhost:8080/stack_exchange_netbeans/question?question_id=<c:out value="${question.id}"/>&token=<c:out value="${token}"/>&from=question">${question.topic}</a></span>
              <div class="right">
                <p style="font-size: 35px;margin:20px 28px 0 0">${question.vote}</p>
              </div>
            <p>${question.content}</p>
              <div class="right-align">
                <p>Asked by ${question.getName()} created at ${question.getCreatedAt()}</p>
              </div>
          </div>
          <div class="card-action">
              <div class ="row">
                <div class="col s6">
                    <div class="left">
                        <a href="http://localhost:8080/stack_exchange_netbeans/UpVoteQuestionServlet?question_id=<c:out value="${question.id}"/>&token=<c:out value="${token}"/>&from=index"><img src="assets/image/up.png" alt="Unsplashed background img 1" width="25" height="25"></a>
                        <a href="http://localhost:8080/stack_exchange_netbeans/DownVoteQuestionServlet?question_id=<c:out value="${question.id}"/>&token=<c:out value="${token}"/>&from=index"><img src="assets/image/down.png" alt="Unsplashed background img 1" width="25" height="25"></a>
                    </div>
                </div>    
                <div class="col s6">
                    <div class="right-align">
                        <c:if test="${question.userId == user_id}"><a href="http://localhost:8080/stack_exchange_netbeans/EditQuestionServlet?question_id=<c:out value="${question.id}"/>&token=<c:out value="${token}"/>">Edit</a></c:if>
                        <c:if test="${question.userId == user_id}"><a href="http://localhost:8080/stack_exchange_netbeans/DeleteQuestionServlet?question_id=<c:out value="${question.id}"/>&token=<c:out value="${token}"/>">Delete</a></c:if> 
                    </div> 
                </div> 
              </div>
          </div>
        </div>
      </div>
    </div>
</c:forEach>


<!--  Scripts-->
<script src="assets/js/jquery-2.1.1.min.js"></script>
<script src="assets/js/materialize.js"></script>
<script src="assets/js/init.js"></script>

</body>
</html>
