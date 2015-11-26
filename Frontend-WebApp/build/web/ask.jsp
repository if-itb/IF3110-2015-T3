<%-- 
    Document   : ask.jsp
    Author     : moel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Simple StackExcahange | Ask</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="js/validate.js"></script>
    </head>
    <body>
        
        <a href="index.jsp"><h1>Simple StackExchange</h1></a>
        
        <h2>What's your question?</h2>
        
        <%
            
        
        QuestionModule.QuestionWS_Service service = new QuestionModule.QuestionWS_Service();
        QuestionModule.QuestionWS port = service.getQuestionWSPort();
        
        QuestionModule.Question q = new QuestionModule.Question();
        String str = request.getParameter("id");
        if(str != null) {
          int id = Integer.parseInt(str);
          q = port.getQuestionByID(id);
        }
        %>
        
        <form action="addQuestion.jsp" method="post" name="ask-form">
            <input type="text" name="Topic" class="form-field" placeholder="Question Topic" value="<%= str != null?q.getQtopic() :""%>"></input>
            <br>
            <textarea name="Content" placeholder="Content" class="form-textarea" ><%= str != null?q.getQcontent():"" %></textarea>
            <br>
            <div align="right">
                    <input type="submit" value="Post" onclick="return validateForm()" action="addQuestion.jsp">
            </div>
            <input name="id" type="hidden" value=<%=q.getQid()%>>
        </form>

    </body>
</html>
