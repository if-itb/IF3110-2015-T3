<%-- 
    Document   : question
    Created on : Nov 18, 2015, 1:27:57 PM
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
            String token = new String();
            Cookie cookies[] = request.getCookies();
                if (cookies != null) {
                    
                    for (int i=0;i<cookies.length;i++) {
                        if (cookies[i].getName().toString().equals("access_token_frontend")) {
                            token = cookies[i].getValue();
                            break;
                        }
                    }
                }
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
                out.println("<a href=\"http://localhost:8001/Identity/LoginRSServlet?token=" + request.getParameter("token") + "&logout=true\" style=\"margin-left: 72%;\">Logout</a>");
            %>
            <div class="container">
               <%
                   
                    if (!token.equals("")) {
                        out.println("<p><a href='index.jsp'>Simple StackExchange</a></p> ");
                    }
                %>
            </div>
        </div>

        <div class="main">
            <div class="container">
                
                <%-- start web service invocation --%><hr/>
                <%
                    try {
                        int qid=Integer.parseInt(request.getParameter("id"));
                        
                        
                        // TODO initialize WS operation arguments here
                        int id = Integer.parseInt(request.getParameter("id"));
                        // TODO process result here
                        java.util.List<questionmodel.Question> result = port.getQuestionbyID(id);
                        for (int i = 0; i < result.size(); i++) {
                            out.println("<form name='question' action='updatequestion.jsp' method='post' class='form'>");
                            out.println("<input type='text' maxlength='30' name='topic' placeholder='Question Topic' value='"+result.get(i).getTopic()+"'><br>");
                            out.println("<textarea name='content' placeholder='Content' maxlength='1500'>"+result.get(i).getQuestion()+"</textarea>");
                            out.println("<input type='submit' value='Post'>");
                            out.println("</form>'");
                        }
                    } catch (Exception ex) {
                        // TODO handle custom exceptions here
                    }
                %>
                <%-- end web service invocation --%><hr/>

            </div>
        </div>
    </body>
</html>
