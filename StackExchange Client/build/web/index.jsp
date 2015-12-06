<%--
  Created by IntelliJ IDEA.
  User: Marco Orlando
  Date: 11/17/2015
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link rel="stylesheet" type="text/css" href="style.css">
  <title>Simple StackExchange</title>
</head>

<body>
  <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" <% out.println("href=\"index.jsp?token=" + request.getParameter("token") + "\""); %> class="brand-logo">Stack Exchange</a>
      <ul class="right hide-on-med-and-down">
        <% if (request.getParameter("token") == null) { out.println("<li><a href=\"login.jsp\">Login</a></li>"); } else { out.println("<li><a href=\"ClientLogout?token=" + request.getParameter("token") + "\"" + ">Logout</a></li>"); } %>
        
        <li><a href="registration.jsp">Registration</a></li>
      </ul>
    </div>
  </nav>
  <br><br>
  <div id = "container">
    <div id = "header">
     <div class="container">
      <nav class="orange" role="navigation">
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
    </div>
      <p align="center">Cannot find what you are looking for? <a style="color:red" <% if (request.getParameter("token") == null) { out.println("href=\"addQuestion.jsp\""); } else { out.println("href=\"addQuestion.jsp" + "?token=" + request.getParameter("token") + "\""); } %>>Ask here</a>
    <div id ="body">
      <h3>Recently Asked Questions</h3>
    <%-- start web service invocation --%>
            <%
                wsmodel.WS_Service service = new wsmodel.WS_Service();
                wsmodel.WS port = service.getWSPort();
                // TODO process result here
                java.util.List<wsmodel.QuestionClass> allQuestionList = port.getAllQuestion();	
                int allQuestionNumber = allQuestionList.size();
            
            %>
    <%-- end web service invocation --%>
    
    <% for (int i = 0; i< allQuestionNumber;i++){ %>
    		<div class = 'questionItem'>
                        <div class = questionLeft>
                            <span class = 'votes_number'> <% out.println(allQuestionList.get(i).getQuestionVote()); %> <br>Votes</span>
                                <%-- start web service invocation --%>
                                <%
                                    wsmodel.WS_Service service2 = new wsmodel.WS_Service();
                                    wsmodel.WS port2 = service2.getWSPort();
                                     // TODO initialize WS operation arguments here
                                    int questionId = allQuestionList.get(i).getQuestionId();
                                    // TODO process result here
                                    int numOfAnswer = port2.getSumAnswer(questionId);
                                %>
                                <%-- end web service invocation --%>

                            
                            <span class = 'Answers_number'> <% out.println(numOfAnswer); %> <br>Answers</span>
                        </div>

                        <div class= questionMid>
                            <a class ='questionTitle' <% out.println("href=\"questionAnswerPage.jsp"  + "?q_id=" + allQuestionList.get(i).getQuestionId() + "&token=" + request.getParameter("token") + "\""); %>> <%= allQuestionList.get(i).getQuestionTitle() %><br></a>
                            <div class =questionContent>
                                <%
                                        String str = allQuestionList.get(i).getQuestionContent();
		  			if(str.length() < 180) {
		  				out.println (allQuestionList.get(i).getQuestionContent());
		  			} else {
		  				out.println (str.substring(0, 180)+"...");
		  			}
                                %>
                             </div>
                        </div>
                             
                            <%-- start web service invocation --%>
                            <%
                                wsmodel.WS_Service service3 = new wsmodel.WS_Service();
                                wsmodel.WS port3 = service3.getWSPort();
                                 // TODO initialize WS operation arguments here
                                int userID = allQuestionList.get(i).getQuestionUserId();
                                // TODO process result here
                                java.lang.String q_UserName = port3.getUserName(userID);
                            %>
                            <%-- end web service invocation --%>
     
                             
                             

                        <div class= questionRight>
                            asked by <font color='blue'> <% out.println(q_UserName);%> </font> |
                            <a class='editQuestion' <% if (request.getParameter("token") == null) { out.println("href=\"editQuestion.jsp" + "?id=" + allQuestionList.get(i).getQuestionId() + "\""); } else { out.println("href=\"editQuestion.jsp"  + "?id=" + allQuestionList.get(i).getQuestionId() + "&token=" + request.getParameter("token") + "\""); } %>>
                              <font color='green'>edit</font>
                            </a>|
                            <a class='deleteQuestion' <% if (request.getParameter("token") == null) { out.println("href=\"deleteQuestionProcess.jsp" + "?id=" + allQuestionList.get(i).getQuestionId() + "\""); } else { out.println("href=\"deleteQuestionProcess.jsp"  + "?id=" + allQuestionList.get(i).getQuestionId() + "&token=" + request.getParameter("token") + "\""); } %> onclick= "return confirm('are you sure?')">
                                <font color='red'>delete</font> 
                            </a>
                        </div>

                </div>
    <% } %>                   
    </div>

  </div>



</body>
</html>
