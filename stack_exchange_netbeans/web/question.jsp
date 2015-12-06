<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML>
<html ng-app="question">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
  <title>StackExchange</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <script>
    window.token = "<c:out value="${token}"/>";
    window.question_id = <c:out value="${question.id}" />;
  </script>
</head>
<body class="cyan lighten-2" ng-controller="QuestionController">
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
        <h1 class="header center white-text text-lighten-2">Question</h1>
    </div>
  </div>
  <div class="parallax"></div>
</div>

     
<div class="row">
    <div class="col s10 offset-s1 l10">
      <div class="card blue-grey darken-1">
        <div class="card-content white-text">
            <span class="card-title">${question.topic}</a></span>
            <div class="right">
                 <p id="question-vote" style="font-size: 35px;margin:20px 28px 0 0">${question.vote}</p>
            </div>
          <p>${question.content}</p>
            <div class="right-align">
                <p>Asked by ${question_name} < <c:out value="${question_email}"/> > at ${question.createdAt}</p>
            </div>
        </div>
        <div class="card-action">
            <div class ="row" style="margin-bottom:0px">
              <div class="col s6" >
          <div class="left">
              <a ng-click="upvoteQuestion(<c:out value="${question.id}"/>)" ><img src="assets/image/up.png" alt="Unsplashed background img 1" width="25" height="25"></a>
              <a ng-click="downvoteQuestion(<c:out value="${question.id}"/>)" ><img src="assets/image/down.png" alt="Unsplashed background img 1" width="25" height="25"></a>
          </div>
              </div>
          <div class="right-align">
              <c:if test="${question.userId == user_id}"><a href="http://localhost:8080/stack_exchange_netbeans/EditQuestionServlet?question_id=<c:out value="${question.id}"/>&token=<c:out value="${token}"/>">Edit</a></c:if>
              <c:if test="${question.userId == user_id}"><a href="http://localhost:8080/stack_exchange_netbeans/DeleteQuestionServlet?question_id=<c:out value="${question.id}"/>&token=<c:out value="${token}"/>">Delete</a></c:if>
          </div> 
            </div>
        </div>
      </div>
    </div>
</div>
            
            <div class="row">
                <div class="col s8 offset-s1 l10">
                    <div class="card blue-grey darken-1">
                        <div class="card-content white-text">
                            <span class="card-title">Comment</span>
                            <div ng-repeat="comment in data.comments">
                                <hr>
                                <p>{{ comment.content }}</p>
                                <small>{{ comment.name }} - {{comment.createdAt}}</small>
                            </div>
                            <hr>
                            <div class="input-field col s12">
                                <input id="comment" type="text" ng-model="form.comment">
                                <label for="comment">Comment</label>
                            </div>
                            <button type="submit" class="btn waves-effect waves-light" type="button" name="action" ng-click="submitComment()">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
            
       
    <c:set var="count" value="0" scope="page" />
    <c:forEach items="${answer}" var="answers">
        <c:set var="count" value="${count + 1}" scope="page"/>
    </c:forEach>
    
    <c:set var="counts" value="0" scope="page" />
    <div class="answers">
        <h1 class="header center white-text text-lighten-2"><c:out value="${count}"/> Answer<c:if test="${count > 1}">s</c:if></h1>
        <c:forEach items="${answer}" var="answers">
            <div class="row">
                <div class="col s10 offset-s1 l10">
                  <div class="card blue-grey darken-1">
                    <div class="card-content white-text">
                        <div class="right">
                            <p id="answer-<c:out value="${answers.id}"/>" style="font-size: 35px;margin:20px 28px 0 0">${answers.vote}</p>
                        </div>
                      <p>${answers.content}</p>
                        <div class="right-align">
                          <p style="margin:47px 0 0 0">Asked by ${answer_name[counts]} < <c:out value="${answer_email[counts]}"/> > at ${answers.createdAt}</p>
                          <c:set var="count" value="${counts + 1}" scope="page"/>
                        </div>
                    </div>
                    <div class="card-action">
                        <div class="row" style="margin-bottom:0px">
                            <div class ="col s6">
                                <div class="left">
                                    <a ng-click="upvoteAnswer(<c:out value="${answers.id}"/>)" ><img src="assets/image/up.png" alt="Unsplashed background img 1" width="25" height="25"></a>
                                    <a ng-click="downvoteAnswer(<c:out value="${answers.id}"/>)" ><img src="assets/image/down.png" alt="Unsplashed background img 1" width="25" height="25"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                  </div>
                </div>
            </div>
	</c:forEach>
    </div>
        
    <h1 class="header center white-text text-lighten-2">Your Answer</h1>



    <div class="col m6">
            <div class="row">
                <form class="col s12" method="POST" id="form">
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="first_name" type="text" class="validate" name="content">
                            <label for="first_name">Content</label>
                        </div>
                    </div>
             
                    <div class="row">
                        
                        <div class="col m12">
                            <p class="center-align">
                                <input id="form-button" type="submit" class="btn btn-large waves-effect waves-light" type="button" name="action" style="align-items: center" value="Post">
                            </p>
                        </div>
                       
                    </div>
                </form>
            </div>
    </div>

<!--  Scripts-->
<script src="assets/js/jquery-2.1.1.min.js"></script>
<script src="assets/js/angular.js" type="text/javascript"></script>
<script src="assets/js/question.js" type="text/javascript"></script>
<script src="assets/js/materialize.js"></script>
<script src="assets/js/init.js"></script>
<script type="text/javascript" src="/assets/js/home.js"></script>

</body>
</html>

