<%-- 
    Document   : newquestion
    Created on : Nov 18, 2015, 1:56:10 PM
    Author     : yoga
--%>

<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='style2.css'/>
    </head>
    <body>
        <%
            String token = request.getParameter("token");
            questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
            questionmodel.QuestionWS port = service.getQuestionWSPort();

            try {

                Timestamp result = new Timestamp(port.getExpiredDate(token));
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                /*out.println(ts);
                out.println(result);*/

                if (ts.after(result)) {
                    String site = "http://localhost:8001/Identity/LoginRSServlet?token="+request.getParameter("token");
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", site);
                }

            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        <div class="header">
            <%
                out.println("<a href=\"http://localhost:8001/Identity/LoginRSServlet?token=" + request.getParameter("token") + "&logout=true\" style=\"margin-left: 71%;\">Logout</a>");
            %>
            <div class="container">
                <%
                    
                    if (token != null) {
                        out.println("<p><a href='index.jsp?token=" + token + "'>Simple StackExchange</a></p> ");
                    } else {
                        out.println("<p><a href='index.jsp'>Simple StackExchange</a></p> ");
                    }

                %>
            </div>
        </div>

        <div class="main">
            <div class="container">

                <%                    out.println("<form  name='question' action='insertquestion.jsp?token=" + token + "' method='post' class='form' >");
                    out.println(" <input type='text' name='topic' placeholder='Question Topic' maxlength='30'><br>");
                    out.println("<textarea name='content' placeholder='Content' maxlength='1500'></textarea>");
                    out.println(" <input type='submit' value='Post' >");
                    out.println("</form>");
                %>

            </div>
        </div>
    </body>
</html>
