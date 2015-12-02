<html lang="en">
  <head> 
    <meta charset="UTF-8">
    <title>Simple StackExchange </title>
    <link rel="stylesheet" type="text/css" href="css/mainstyle.css">
  </head>
  <body>
    <div id="wrapper">
      <h1 class = "center"> <a href="index.jsp">Simple StackExchange</a></h1>
      <h2 class ="underline"> What's your question? </h2>
      <form action="addQuestion.jsp" method="POST" class="block">
        <input type="text" placeholder="Question Topic" name="topic" value="" />
        <textarea name="content" placeholder="Content"></textarea>
        <input type="submit" value="Post" />
        <input type="hidden" name="token" value="<%= request.getParameter("token")%>" />
        <input type="hidden" name="uid" value="<%= request.getParameter("id")%>" />
      </form>
    </div>
  </body>
</html>
