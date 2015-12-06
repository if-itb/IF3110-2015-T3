<%-- 
    Document   : home
    Created on : Nov 18, 2015, 9:40:20 AM
    Author     : Gerry
--%>
<%@page import="java.util.List"%>
<%@page import="stackexchangews.Question"%>

<html lang="en">
  <head>
    <title>Stack Exchange</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  </head>

  <body>
    <nav class="deep-purple darken-2" role="navigation">
      <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Stack Exchange</a>
        <ul class="right hide-on-med-and-down">
          <li><a href="register.jsp">Register</a></li>
          <li><a href="login.jsp">Login</a></li>
          <li><a href="index.jsp?token=null">Logout</a></li>
        </ul>

        <ul id="nav-mobile" class="side-nav">
          <li><a href="register.jsp">Register</a></li>
          <li><a href="login.jsp">Login</a></li>
        </ul>
        <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
      </div>
    </nav>
    <br><br><br>
    <div class="container">
      <nav class="deep-purple darken-2" role="navigation">
        <div class="nav-wrapper">
          <form>
            <div class="input-field">
              <input id="search" type="search" required>
              <label for="search"><i class="material-icons">search</i></label>
              <i class="material-icons">close</i>
            </div>
          </form>
        </div>
      </nav>
    </div>      
    <div class="ask-question">
        <%
            String token = (String)request.getParameter("token");
        %>
      <p class="center"><a href="question.jsp?token=<% out.print(token); %>"> Ask Your Question Here</a></p>
    </div>
    <br>
    <div class="container">
      <div class="divider"></div>
    </div>
    <!--<div class="parallax-container">
    <div class="parallax"><img src="tokyo-blue-background-4547.jpg"></div>
    <center><h1 class="white-text"> Recently Asked Question </h1></center>
    </div>-->
    <center><h2 class="deep-purple-text"> Recently Asked Question </h2></center>
    <div class="section white">
        <%
            List<Question> questions = (List<Question>)request.getAttribute("questions");
            List<String> names = (List<String>)request.getAttribute("names");
            int i = 0;
            for (Question q : questions) {
                out.println("<div class='row center'>");
                out.println("<div class='container'>");
                out.println("<div class='card deep-purple darken-2 materialboxed'>"); 
                out.println("<div class='card-content white-text'>");
                out.println("<span class='card-title'><a href='questionpage.jsp?qid=" + q.getId() + "&token=" + token +  "'>" + q.getTopic() + "</a></span>");
                out.println("<p>" + q.getContent() + "</p>");
                out.println("</div>");
                out.println("<div class='card-action'>");
                String name = names.get(i);
                out.println("<a class='left' href='questionbyID?name=" + name + "&token=" + token + "'>Asked by " + names.get(i) + "</a>");
                out.println("<a class='left' style='padding-left:5px'>" + q.getVote() + "</a>");
                out.println("<a class='left'>Votes</a>");
                out.println("<a class='right' href='edit.jsp?qid=" + q.getId() + "&token=" + token + "'>Edit</a>");
                out.println("<a class='right' href='delete?qid=" + q.getId() + "&token=" + token + "'>Delete</a>");
                out.println("</div></div></div></div>");
                i++;
            }            
        %>    
    </div>
    <!--<div class="parallax-container">
    <div class="parallax"><img src="tokyo-blue-background-4547.jpg"></div>
    </div>-->

    <footer class="page-footer deep-purple darken-2">
      <div class="footer-copyright">
        <div class="container">
          © 2015 Created by 3xcelsi0r
        </div>
      </div>
    </footer>

    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/materialize.min.js"></script>
    <script src="js/init.js"></script>
  </body>
</html>