<%@page import="questionWebService.Question"%>
<html lang="en">
  <head> 
    <meta charset="UTF-8">
    <title>Simple StackExchange </title>
    <link rel="stylesheet" type="text/css" href="css/mainstyle.css">
  </head>
  <body>
    <%
      String token = request.getParameter("token");
      int qid = Integer.parseInt(request.getParameter("qid"));
      int id = Integer.parseInt(request.getParameter("id"));
      Question question = new Question();
      try {
        questionWebService.QuestionWebService_Service service = new questionWebService.QuestionWebService_Service();
        questionWebService.QuestionWebService port = service.getQuestionWebServicePort();
        question = port.getQuestionById(qid);
      } catch (Exception ex) {
        out.println("Failed");
      }
    %>

    <div id="wrapper">
      <h1 class = "center"> <a href="index.jsp">Simple StackExchange</a></h1>
      <h2 class ="underline"> What's your question? </h2>
      <form action="editQuestion.jsp" method="POST" class="block">
        <input type="text" placeholder="Question Topic" name="topic" value="<%=question.getTopic()%>" />
        <textarea name="content" placeholder="Content"><%=question.getContent()%></textarea>
        <input type="submit" value="Post" />
        <input type="hidden" name="token" value="<%=token%>" />
        <input type="hidden" name="id" value="<%=id%>" />
        <input type="hidden" name="qid" value="<%=qid%>" />
      </form>
    </div>
  </body>
</html>
