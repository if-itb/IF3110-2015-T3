<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Stack Exchange</title>
  <link rel="stylesheet" type="text/css" href="/assets/css/question.css">
<script type = "text/javascript" src="/assets/js/validatequestion.js"> </script>
</head>
<body>
<div class="container">
  <h1 class="align-center margin-bot"><a class="text-link" href="index.jsp"><black>Simple StackExchange</black></a></h1>
  <h2>What's Your Question?</h2>
  <hr>
  <form name="addQuestion" action="data-question.jsp" onsubmit="return validateForm()" method = "Post">
    <input type="text" class="form" placeholder="Question Topic" name="Topic">
    <textarea class="form" placeholder="Content" rows="5" name="Content"></textarea>
    <div class="align-right">
      <button class="button">Post</Button>
    </div>
  </form>
</div>
</body>
</html>
