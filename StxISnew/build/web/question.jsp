<%-- 
    Document   : question
    Created on : Nov 17, 2015, Nov 17, 2015 8:06:01 AM
    Author     : Fikri-PC
--%>
<%@page import="QuestionWS.Question"%>
<%@page import="QuestionWS.QuestionWS"%>
<%@page import="QuestionWS.QuestionWS_Service"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="include/header.jsp" %>
<% if (session.getAttribute("sessionName") != null) { %>
        <h2>Apa pertanyaan anda ?</h2> <hr>
        <%
            String sid = request.getParameter("id");
            if(sid != null){
        %>
           
        <%
                QuestionWS_Service service = new QuestionWS_Service();
                QuestionWS port = service.getQuestionWSPort();
                // TODO process result here
                int id = Integer.parseInt(sid);
                Question Q = port.getQuestionById(id);
       
        %>
        
        <form class="block" action = 'actions/post.jsp?id=<% out.print(id); %>' name = "myForm" method = 'POST' onsubmit = "return(validateQuestion());">
                <ul>
                        <input type = 'text' name = 'Topik' placeholder="Topik" value = "<% out.print(Q.getTopic()); %>" maxlength = '140'></input>
                        <textarea rows = '100' cols = '100' placeholder="Konten" name = 'Konten' ><% out.print(Q.getContent()); %></textarea>
                        <input type = 'submit' value = "Update"></input>
                </ul>
        </form>
        <% } else {%>
        <form class="block" action = 'actions/post.jsp' name = "myForm" method = 'POST' onsubmit = "return(validateQuestion());">
                <ul>
                        <input type = 'text' name = 'Topik' placeholder="Topik" maxlength = '140'></input>
                        <textarea rows = '100' cols = '100' placeholder="Konten" name = 'Konten' ></textarea>
                        <input type = 'submit' value ="Kirim"></input>
                </ul>
        </form>
        <% } %>
    <% } else {
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "login.jsp");
}%>
    
<%@include file="include/footer.jsp" %>