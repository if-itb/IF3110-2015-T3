<%-- 
    Document   : index.jsp
    Author     : moel
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExchange</title>
	<link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        
        <%
        
            //getting token
            String token = (String)session.getAttribute("access_token");
            String user="";
            if (token == null) {user="Register";}
            else{
                Calendar limcal = ((Calendar)session.getAttribute("start"));
                Integer lifetime = (Integer)session.getAttribute("lifetime");
                limcal.add(Calendar.SECOND, lifetime);
                if (limcal.before(Calendar.getInstance())){
                    response.sendRedirect("signin.jsp");
                }else{
                    user="Sign Out";
                    
                }
            }
        
        %>
        
        <a href="index.jsp"><h1>Simple StackExchange</h1></a>
        
        
        <a href="register.jsp"><h4 align="Right"><%=user%></h4></a>
        <div class="garis"></div>
        
        <form action="search.jsp" method="GET" name="search">
                <p align="center">
                        <input type="text" name="search" class="search" placeholder="Search here..."> </input>
                        <input class="button" type="submit" value="Search" onclick="search.jsp"> </input>
                </p>
        </form>
        
        <h5>Cannot find what you are  looking for? <a href="ask.jsp"><span id="link">Ask here</span></a></h5>
        <h4>Recently Asked Questions</h4>
        
        <%
            QuestionModule.QuestionWS_Service service = new QuestionModule.QuestionWS_Service();
            QuestionModule.QuestionWS port = service.getQuestionWSPort();
            java.util.List<QuestionModule.Question> questions = port.getAllQuestion();
            
        %>
        
        <div class="garis"></div>
        
        <% for(QuestionModule.Question q : questions) { %>
        
        <table class="question">
            <tr>
                    <td class="number">
                            <%= q.getQvote() %>
                    </td>
                    <td class="number">
                            <%
                                Answer.AnswerWS_Service as = new Answer.AnswerWS_Service();
                                Answer.AnswerWS ap = as.getAnswerWSPort();
                                java.util.List<Answer.Answer> ans = ap.getAllAnswer(q.getQid());

                                int na = ans.size();
                            %>
                            <%= na %>
                    </td>
                    <td>
                    </td>
                    <td class="Topic">
                        <a href="displayQuestion.jsp?id=<%=q.getQid()%>">
                            <%= q.getQtopic() %>
                        </a>
                    </td>
                    <td class="Date">
                            <%= q.getQtimestamp() %>
                    </td>
            </tr>
            <tr>
                    <td class="Text">
                            Votes
                    </td>
                    <td class="Text">
                            Answers
                    </td>
                    <td></td>
                    <td class="Content">
                            <%= q.getQcontent() %>
                    </td>
                    <td class="Detail">
                            Asked by
                            <span class="name">
                                    <%= q.getUemail() %>
                            </span>
                            | 
                            <a href="ask.jsp?id=<%=q.getQid()%>"><span class="edit">
                            edit
                            </span></a>
                            |
                            <a href="deleteQuestion.jsp?id=<%=q.getQid()%>"><Span class="delete">
                            delete
                            </span></a>
                    </td>
            </tr>
    </table>
    <div class="garis"></div>
    <% } %>
    </body>
</html>
