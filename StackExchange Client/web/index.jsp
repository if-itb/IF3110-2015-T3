<%-- 
    Document   : index
    Created on : Nov 20, 2015, 10:57:36 PM
    Author     : William Sentosa
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="questionWebService.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Simple StackExchange</title>
	<link rel="stylesheet" type="text/css" href="css/mainstyle.css">
    </head>
    <body>
        <%
            String token = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie temp : cookies){
                if(temp.getName().equals("token")){
                    token = temp.getValue();
                }
            }
        %>
	<div id="wrapper">
            <jsp:include page="header.jsp" />
            <div class="row">
                <%
                if(!token.isEmpty()){
                %>
                  <%! String name = ""; %>
                  Welcome, 
                  <%
                  try {
                      userWebService.UserWebService_Service service0 = new userWebService.UserWebService_Service();
                      userWebService.UserWebService port0 = service0.getUserWebServicePort();
                      int uid = Integer.parseInt(request.getParameter("id"));
                      userWebService.User result0 = port0.getUser(uid);
                      name = result0.getName();
                      out.print(name);
                  } catch (Exception ex) {
                  }
                  %>!
                  <div class="right">
                      <a href="http://localhost:8082/IdentityServices/IdentityChecker?action=logout&token=<%= token %>">Logout</a>
                  </div>
                <%
                } //bracket for if
                %>
                
            </div>
            <div id="main-search" class="center">
                    <%
                    if(request.getParameter("id") != null && !token.isEmpty()){
                    %>
                        <form action="index.jsp?id=<%=request.getParameter("id")%>" method="POST">
                    <%
                    } else {
                    %>
                        <form action="index.jsp" method="POST">
                    <%
                    }
                    %>
                            <input autofocus="autofocus" type="text" name="search" id="search-bar" placeholder="Search question topic or content here..." value="">
                            <input type="submit" value="Search">
                    </form>
            </div>
            <%
            if(request.getParameter("id") != null && !token.isEmpty()){
            %>
                <div class="center">
                    <h3>
                        <a href="ask.jsp?id=<%=request.getParameter("id")%>">Ask a question!</a>
                    </h3>    
                </div>
            <%
            } else {
            %>
                <div class="center">
                    <h3>
                      <a href="login.jsp">Login</a>
                    </h3>    
                </div>
            <%
            }
            %>
            
            <div id="main-page" onload="function() {document.getElementById('autofocus').focus();}">
                <div class="questions">
                    <h2>Recently Asked Questions</h2>
                    <hr class="border">
                    <% 
                    try {
                        questionWebService.QuestionWebService_Service service = new questionWebService.QuestionWebService_Service();
                        questionWebService.QuestionWebService port = service.getQuestionWebServicePort();
                        List<Question> result = new ArrayList<Question>();
                        if(request.getParameter("search") == null) {
                            result = port.getAllQuestion();
                        } else {
                            result = port.searchQuestion(request.getParameter("search"));
                            if(result.size() == 0) {
                              out.println("No question found");
                            }
                        }
                        for (int i=0 ; i < result.size(); i++){
                            int id = result.get(i).getQuestionId();
                            String s = String.valueOf(id);
                    %>
                            <div class="col title">
                                <a href="question.jsp?id=<%=request.getParameter("id")%>&qid=<%= s %>"><% out.print(result.get(i).getTopic());%></a>
                            </div>
                            <div class="content">
                                <br>
                                <% 
                                String res = result.get(i).getContent();
                                if(res.length() >= 75){
                                  res = res.substring(0, 74);
                                  res += res + "...";
                                }
                                out.println(res);
                                %>
                            </div>
                            <br>
                            <div class="question">
                                <div class="row">
                                    <div class="col vote in-numbers">
                                        <div class="number"><%= result.get(i).getVote() %></div>
                                        <div class="flag">Votes</div>
                                    </div>
                                    <div class="col answers in-numbers">
                                        <div class="number">
                                        <%
                                        try {
                                            answerWebService.AnswerWebService_Service service2 = new answerWebService.AnswerWebService_Service();
                                            answerWebService.AnswerWebService port2 = service2.getAnswerWebServicePort();
                                            int qid = result.get(i).getQuestionId();
                                            int result2 = port2.getCountAnswerByQid(qid);
                                            out.println(result2);
                                        } catch (Exception ex) {
                                        }
                                        %>
                                        </div>
                                        <div class="flag">Answers</div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="controls" align="right">
                                        asked by 
                                        <span class="name"> <% out.print(result.get(i).getAskerName()); %></span>
                                        <% 
                                        if(name.equals(result.get(i).getAskerName())) { 
                                        %>
                                            |
                                            <a href="editForm.jsp?id=<%=request.getParameter("id")%>&qid=<%= result.get(i).getQuestionId()%>"><span class="link edit"> edit </span></a> |
                                            <a onclick="confirm('Are you sure to delete this question ?');" href="deleteQuestion.jsp?id=<%=request.getParameter("id")%>&qid=<%= result.get(i).getQuestionId()%>"><span class="link delete"> delete </span></a>
                                        <%
                                        } 
                                        %>
                                    </div>
                                </div>
                            </div>
                        <%
                        } //end for
                        %>
                    <% 
                    } catch (Exception ex) {
 
                    }
                    %>    
                </div>
            </div>
        </div>
    </body>   
</html>
