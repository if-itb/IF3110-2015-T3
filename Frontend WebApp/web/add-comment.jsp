<!-- Tugas 3 IF3110 Pengembangan Aplikasi Berbasis Web
Membuat website tanya jawab seperti Stack Exchange dengan REST dan SOAP dan arsitektur berorientasi servis.
Author: 
- Irene Wiliudarsan (13513002)
- Angela Lynn       (13513002)
- Devina Ekawati    (13513002) -->
<!-- File: add-comment.jsp  -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Comment</title>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
  </head>
  <body>
    <% if ((request.getParameter("token") != null) || (request.getParameter("token") != "not-valid")) { %>
      <!--User Name-->
      <div class="user-identity">
        Hi <span class="user-name blue"></span>
      </div>
      <ul class="nav-bar">
        <li><a href="IndexController">Logout</a></li>
      </ul>
    <% } %>
    <!-- Title -->
    <div class="title">
      <a href="IndexController?token=<%= request.getParameter("token") %>">
        StackExchange
      </a>
    </div>

    <!-- Comment Form -->
    <div class="content">
      <div class="subtitle">
        What's your comment?
      </div>
      <form class="right" id="comment-form" action="" method="post" onsubmit="return answerFormValidation()">
        <input class="full-length" id="comment-name" name="comment-name" type="text" placeholder="Name">
        <input class="full-length" id="comment-email" name="comment-email" type="email" placeholder="Email">
        <textarea class="full-length" id="comment-content" name="comment-content" placeholder="Comment" rows="10" cols="50"></textarea>
        <input class="button" name="comment-submit" type="submit" value="Post">
      </form>
    </div>

    <!-- JavaScript -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
    <script src="js/script.js"></script>
    <script src="js/script-user-identity.js"></script>
  </body>
</html>
