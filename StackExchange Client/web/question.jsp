<%-- 
    Document   : question
    Created on : 23-Nov-2015, 13:20:51
    Author     : KEVIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head> 
	<meta charset="UTF-8">
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
        <div id= "question_page">
        <div class="section">
            <h2 class="underlined">  </h2>
            <div class="question" id="question-">
                <h2>
                <%
                try {
                    questionWebService.QuestionWebService_Service service = new questionWebService.QuestionWebService_Service();
                    questionWebService.QuestionWebService port = service.getQuestionWebServicePort();
                    int id = Integer.parseInt(request.getParameter("qid"));
                    questionWebService.Question result = port.getQuestionById(id);
                    out.println(result.getTopic());
                %>
                </h2>
                <hr>
                <div class="row">
                    <div class= "vote col">
                        <a href=" <% out.print("qvoteup.jsp?token=" + token + "&id=" + request.getParameter("id") + "&qid=" + request.getParameter("qid")); %>" >
                        <img src="img/upvote.png" width ="35" height="35"><br>
                        </a>
                        <span id="question-vote-count<%= result.getUserId()%>"><%= result.getVote() %></span><br>
                        <a href=" <% out.print("qvoteup.jsp?token=" + token + "&id=" + request.getParameter("id") + "&qid=" + request.getParameter("qid")); %>">  
                        <img src="img/downvote.png" width="35" height="35">
                        </a>
                    </div>
                    <div class = "col-content">
                        <%
                        out.println(result.getContent());
                        %>
                    </div>
                </div>
                <div class="row info">
                    asked by <span class="name"><%= result.getAskerName()%></span>
                </div>

                <hr>
                <br>
                    <%
                    } catch (Exception ex) {
                    }
                    %>
                <h2>Answers</h2>
                <div class="answer">
                    <%
                    try {
                        answerWebService.AnswerWebService_Service service = new answerWebService.AnswerWebService_Service();
                        answerWebService.AnswerWebService port = service.getAnswerWebServicePort();
                        int qid = Integer.parseInt(request.getParameter("qid"));
                        java.util.List<answerWebService.Answer> result = port.getAnswerByQid(qid);
                        for(int i=0; i<result.size(); i++ ){
                    %>
                            <hr>
                            <div class="section" id="answers">
                                <div class="answer underline" id="answer-">
                                    <div class="row">
                                        <div class="vote col">
                                            <a href=" <% out.print("avoteup.jsp?token=" + token + "&id=" + request.getParameter("id") + "&qid=" + request.getParameter("qid") + "&aid=" +result.get(i).getAnswerId()); %>">
                                            <img src="img/upvote.png" width="35" height="35">
                                            </a><br>

                                            <a href=" <% out.print("avotedown.jsp?token=" + token + "&id=" + request.getParameter("id") + "&qid=" + request.getParameter("qid") + "&aid=" +result.get(i).getAnswerId()); %>">
                                            <img src="img/downvote.png" width="35" height="35">
                                            </a>
                                        </div>
                                            <div class = "col content">
                                                <span id="answer-vote-count-<%= result.get(i).getAnswerId() %>">
                                                 <%= result.get(i).getVote() %>
                                                </span>
                                            </div>
                                        <div class="col-content">
                                            <p>
                                                <%= result.get(i).getContent() %>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="row info">
                                        answered by <span class="name"><%= result.get(i).getAnswererName() %></span>
                                        <span class="email">&lt;<%=result.get(i).getEmail()%>&gt;</span>
                                    </div>
                                </div>
                            </div>
                            <% 
                            } //end for
                            %>
                            <hr>
                        <%
                        } catch (Exception ex) {
                        }
                        %>		
                        <div class="section" id="form-answer">
                            <h2>Your Answer</h2>
                            <form class="block" action="createanswer.jsp" method="POST">
                                <textarea name="content" placeholder="Content"></textarea>
                                <input type="submit" value="Post">
                                <input type="hidden" name="qid" value="<%= request.getParameter("qid") %>">
                                <input type="hidden" name="token" value="<%= token%>" />
                                <input type="hidden" name="uid" value="<%= request.getParameter("id")%>" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 </body>
 </html>
