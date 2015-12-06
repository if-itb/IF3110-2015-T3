<%-- 
    Document   : editQuestion
    Created on : Nov 24, 2015, 4:40:05 AM
    Author     : Marco Orlando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <!--link rel="stylesheet" type="text/css" href="style.css"-->
  <title>Simple StackExchange</title>
  <script type = "text/javascript" src="validatorInputQuestionAnswer.js"></script>
  <script type="text/javascript" src="checklogin.js"></script>
</head>

<body onload="checkLogin(<% out.println(request.getParameter("token")); %>)">
<div id="container">
    <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %> class="brand-logo">Stack Exchange</a>
    </div>
    </nav>
    <br><br>
    <div class="container" id="container">
     <div id="body">
       <div class="cotainer center">
            <h5 id="what">Edit Question ?</h5><br>
            <div class="container divider" style="border: solid 1.5px; width:500px;"></div><br><br>
    
        <%-- start web service invocation --%>
    <%
	wsmodel.WS_Service service = new wsmodel.WS_Service();
	wsmodel.WS port = service.getWSPort();
	 // TODO initialize WS operation arguments here
	 int questionId = Integer.parseInt(request.getParameter("id"));
	// TODO process result here
	wsmodel.QuestionClass result = port.getQuestionByID(questionId);
	
        
        String qTopic = result.getQuestionTitle();
        String qContent = result.getQuestionContent();
    %>
    <%-- end web service invocation --%>
        <form name="myForm" action="editQuestionProcess.jsp" method="post">
          <input type="text" name="questionTopic" placeholder="Question Topic" value='<%= qTopic %>' required><br>
          <% out.println("<input type='text' name='questionContent' placeholder='Content' value='" + qContent + "' required>"); %>
          <input type="hidden" name="questionId" value='<%= questionId %>' required>
          <input type="hidden" name="token" value='<%= request.getParameter("token") %>' required>
          <div id="submitter">
                <input id="download-button" class="btn-large waves-effect waves-light orange" name="submitButton" type="submit" value="Update Question"/>                  
          </div>
        </form>
    </div>
  </div>    
 </div>    
</div>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="../../bin/materialize.js"></script>
    <script src="js/init.js"></script> 
</body>
</html>
