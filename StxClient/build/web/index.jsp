<%-- 
    Document   : index
    Created on : Nov 17, 2015, Nov 17, 2015 8:05:49 AM
    Author     : Fikri-PC
--%>

<%@page import="QuestionWS.Question"%>
<%@page import="QuestionWS.QuestionWS"%>
<%@page import="QuestionWS.QuestionWS_Service"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<%@include file = "include/header.jsp" %>
<% if (session.getAttribute("sessionName") == null){ %>
    <a href= "login.jsp">sign in</a> | <a href= "register.jsp">sign up</a>
<% } else { %>    
    hello, <%= session.getAttribute("sessionName") %> | <a href="actions/logout.jsp"> logout </a>
<% } %>
        <div id="main-page" onload="function() {document.getElementById('autofocus').focus();}">
                <div id="main-search" class="center">
                <form action = 'search.jsp' id = "main-search" method = 'GET'>
                        <ul>
                                <input type = 'text' id = "search-bar" name = 'key' maxlength = '160' placeholder = "Cari disini" required>
                                <input type = 'submit' value = 'Cari'>
                        </ul>
                </form>
                <p> 
                        Tidak dapat menemukan yang anda cari ? Tanyakan <a href = "question.jsp">disini!</a>
                </p>
                </div>
                <div class = "question">
                <span class = "page-title"><h3>Pertanyaan baru-baru ini</h3></span> <hr>
                    <%-- start web service invocation --%>
                <%
               
                QuestionWS_Service service = new QuestionWS_Service();
                QuestionWS port = service.getQuestionWSPort();
                // TODO process result here
                java.util.List<Question> result = port.getAllQuestion();
                for (Question Q : result) { %>

                     <div class="row">
                    <h2> <a href = view-question.jsp?id=<% out.print(Q.getId()); %>><% out.print(Q.getTopic()); %></a></h2>
                    <p> <% out.print(Q.getContent()); %></p>
                    <br>
                    <div class="col vote in-numbers">
                        <div class = "flag">Vote</div>
                        <div class = "number"> <% out.print(Q.getVote()); %></div>
                    </div>
                    <div class = "col answers in-numbers">
                        <div class = "flag">Jawaban</div> <div class = "number"> <%    
                        try {
                            QuestionWS_Service service3 = new QuestionWS_Service();
                            QuestionWS port3 = service3.getQuestionWSPort();
                             // TODO initialize WS operation arguments here
                           
                            // TODO process result here
                            int result3 = port3.getNumAnswer(Q.getId());
                            out.println(result3);
                        } catch (Exception ex) {
                            // TODO handle custom exceptions here
                        }
                     %></div>
                    </div>
                    <div class = "controls" align = "right">
                        Ditanyakan oleh <span class="name"><% out.print(Q.getName()); %></span> 
                        <% if ((session.getAttribute("sessionName") != null) && (session.getAttribute("sessionName").toString().equals(Q.getName()))) { %>
                            <span class="link edit"> <a href= "question.jsp?id=<% out.print(Q.getId()); %>">Edit</a> </span> | 
                            <span class="link delete"> <a href= "javascript:delete_question(<% out.print(Q.getId()); %>)" >Delete</a></span>
                        <% } %>
                    </div>
                </div>
                <br><hr>
               
                <% } %>
                <%-- end web service invocation --%><hr/>

                
                </div>
        </div>
<%@include file="include/footer.jsp" %>