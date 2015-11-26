<%-- 
    Document   : displayQuestion.jsp
    Author     : moel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Simple StackExcahange | Question</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        
        <a href="index.jsp"><h1>Simple StackExchange</h1></a>
        <h2>Topic</h2>
        <div class="garis"></div>
        
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
        
        <table class="question">
            <tr>
                    <td class="number">
                            <%= q.getQvote() %>
                    </td>
                    <td class="number">
                            nAns
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
                            <a href="delete.jsp?id=<%=q.getQid()%>"><Span class="delete">
                            delete
                            </span></a>
                    </td>
            </tr>
    </table>
        
        <h2>nAns Answer</h2>
        <div class="garis"></div>
        
        <table >
            <tr>
                <td class="vote">
                    <img src="image/Up.png" width="30" hight="30">

                    <h3>
                        nVote
                    </h3>
                    <img src="image/down.png"  width="30" hight="30">
                </td>
                <td class="dContent">
                    Content
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td class="Detail">
                    Asked by
                    <span class="name">
                        Username
                    </span>
                    at
                    date
                </td>
            </tr>
        </table>
        <div class="garis"></div>
        
        <h2 style="color:#A0A0A0">Your Answer</h2>
        <form action="addAnswer.php" method="post" name="ask-ans">
            <input type="text" name="Name" class="form-field" placeholder="Name"></input>
            <br>
            <input type="text" name="Email" class="form-field" id="Email" placeholder="Email"></input>
            <br>
            <textarea name="Content" placeholder="Content" class="form-textarea" ></textarea>
            <br>
            <div align="right">
                    <input type="submit" value="Post" onclick="return validateAns()" action="addAnswer.jsp">
            </div>
            <input name="id" type="hidden" value=<%=q.getQid()%>>
        </form>

    </body>
</html>
