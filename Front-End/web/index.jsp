<%-- 
    Document   : index.jsp
    Created on : Nov 17, 2015, 12:51:04 PM
    Author     : Vincent
--%>
<%@ page import="QuestionWS.Question" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% 
    Cookie[] cookies = null;
    Cookie cookie = null;
    String token = "";
    
    cookies = request.getCookies();
    if (cookies != null) {
        for (int i=0; i < cookies.length; i++) {
            cookie = cookies[i];
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
    }
%>

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
        <li><a href="login.jsp">Log In</a></li>
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
          <h5 class="header col s12 light">A Website for ask and answer</h5>
        </div>
        <div class="row center">
          <a href="addQuestion.jsp" id="download-button" class="btn-large waves-effect waves-light blue darken-4">Ask Question</a>
        </div>
        <br><br>
      </div>
    </div>
    <div class="parallax"><img src="images/background1.jpg" alt="Unsplashed background img 1"></div>
  </div>

  <form action="searchServlet" method="POST">
  <div class="container">
    <div class="section">
        <h2 class="header center blue-text text-darken-4">Search Question</h2>
        <nav>
          <div class="nav-wrapper">
              
              <div class="input-field indigo darken-4">
                <input id="search" type="search" name="keyword">
                <label for="search"><i class="material-icons">search</i></label>
                <i class="material-icons">close</i>
              </div>
            
          </div>
        </nav>
        <br>
        <div class="center">
            <button class="btn center waves-effect waves-light blue darken-4" type="submit" name="action">Search
              <i class="material-icons right">search</i>
            </button>
        </div>
    </div>
  </div>
  </form>
  <br><br>

  <div class="parallax-container valign-wrapper">
    <div class="section no-pad-bot">
      <div class="container">
        <div class="row center">
          <h1 class="header col s12 light">Recently Asked Question</h1>
        </div>
      </div>
    </div>
    <div class="parallax"><img src="images/background1.jpg" alt="Unsplashed background img 1"></div>
  </div>
  <br><br>
  
  <% List<Question> questions = (List<Question>)request.getAttribute("questions");%>
  <% for (Question question : questions) { %>
    <div class="container">
        <div class="section">
            <div class="row">
                <div class="col s12">
                <div class="card cardhome blue-grey darken-1">
                    <div class="card-content white-text">
                        <span class="card-title"><a href="answer?qid=<%= question.getQuestionid() %>" class="white-text"><%= question.getTopic() %></a></span>
                        <p class="right vote"><%= question.getVote()%> vote</p>
                        <a href="voteDownQuestionServlet?qid=<%= question.getQuestionid() %>" class="btn-floating btn-large waves-effect waves-light red right"><i class="material-icons">thumb_down</i></a>
                        <a href="voteUpQuestionServlet?qid=<%= question.getQuestionid() %>" class="btn-floating btn-large waves-effect waves-light green right"><i class="material-icons">thumb_up</i></a>
                        <p><%= question.getContent() %></p>
                    </div>
                    <div class="card-action">
                        <p class="blue-text text-lighten-1 right">Asked by <%= question.getUsername() %> at <%= question.getTimestamp() %></p>
                        <a href="editQuestion?qid=<%= question.getQuestionid() %>">Edit</a>
                        <a class="red-text" href="delete?qid=<%= question.getQuestionid() %>">Delete</a>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
  <% } %>  
              
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
