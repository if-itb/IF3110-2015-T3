<%-- 
    Document   : index
    Created on : Nov 14, 2015, 10:43:28 PM
    Author     : Asus
--%>


<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page language="java" import="java.sql.Timestamp"%>
<%@page language="java" import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "style2.css">
        <script type="text/javascript" src="ua-parser.pack.js"></script>
        <title>Simple StackExchange</title>
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
                //decode token
                String realtoken = URLDecoder.decode(token, "UTF-8");
                //parse token
                String[] tableRT = realtoken.split("#");
                //if user have token then validate UA and IP
                if (tableRT.length > 1){
                    //check User agent. If not equals with token then redirect to login
                    if( !tableRT[1].equals(request.getHeader("User-Agent")) ){
                        String site = "http://localhost:8000/FrontEnd/login.jsp";
                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                        response.setHeader("Location", site);
                    }
                    //check IPAddress. If not equals with token then redirect to login
                    if( !tableRT[2].equals(request.getRemoteAddr()) ){
                        String site = "http://localhost:8000/FrontEnd/login.jsp";
                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                        response.setHeader("Location", site);
                    }
                }
                
            questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
            questionmodel.QuestionWS port = service.getQuestionWSPort();
            
            try {

                Timestamp result = new Timestamp(port.getExpiredDate(token));
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                /*out.println(ts);
                out.println(result);*/
                System.out.println(result);
                if (ts.after(result)) {
                    String site = "http://localhost:8001/Identity/LoginRSServlet?token="+token;
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", site);
                }

            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        <div class="header">
            <%
                if (!token.equals("")) {
                    out.println("<a href=\"http://localhost:8001/Identity/LoginRSServlet?token=" + token + "&logout=true\" style=\"margin-left: 72%;\">Logout</a>");
                }
                else {
                    out.println("<a href=\"login.jsp\" style=\"margin-left: 72%;\">Login</a>");
                }
            %>
            <div class="container">
                <p><a href="index.jsp">Simple StackExchange</a></p> 
            </div>
        </div>

        <div class="main">
            <div class="container">

                <%
                        out.println("<form name='search' action='index.jsp' method='post' class='search'>");

                %>
                    <input type="text" maxlength="50" name="key">
                    <input type="submit" value="Search">
                </form>
                <%if (!token.equals("")) {
                        out.println("<h6>Cannot find what you are looking for? <a href='newquestion.jsp'>Ask here</a></h6>");
                    } else {
                        out.println("<h6>Cannot find what you are looking for? <a href='login.jsp'>Login to ask here</a></h6>");
                    }

                %>
            </div>
            <div class="question">
                <h5>Recently Asked Questions</h5>
                <div class="listquestion">

                    <%  String search = request.getParameter("key");
                        java.lang.String check = port.getName(token);
                        if (search == null) {
                            try {

                                // TODO process result here
                                java.util.List<questionmodel.Question> result = port.getallQuestions();
                                for (int i = 0; i < result.size(); i++) {
                                    out.println("<div class='itemquestion'>");
                                    out.println("<div class='columnsmall left'> <p>" + result.get(i).getVotes()
                                            + "</p> <p>Votes</p></div>");
                                    out.println("<div class='columnsmall left'> <p>" + result.get(i).getAnswers()
                                            + "</p> <p>Answers</p></div>");

                                    if (!token.equals("")) {
                                        if (result.get(i).getQuestion().length() > 30) {
                                            out.println("<div class='columnlarge center'><a href='answer.jsp?id=" + result.get(i).getQuestionID() + "'><h4>" + result.get(i).getTopic() + "</h4></a><p>" + result.get(i).getQuestion().substring(0, 30) + ". . .</p></div>");
                                        } else {
                                            out.println("<div class='columnlarge center'><a href='answer.jsp?id=" + result.get(i).getQuestionID() + "'><h4>" + result.get(i).getTopic() + "</h4></a><p>" + result.get(i).getQuestion() + "</p></div>");
                                        }
                                    } else {
                                        if (result.get(i).getQuestion().length() > 30) {
                                            out.println("<div class='columnlarge center'><a href='answer.jsp?id=" + result.get(i).getQuestionID() + "'><h4>" + result.get(i).getTopic() + "</h4></a><p>" + result.get(i).getQuestion().substring(0, 30) + ". . .</p></div>");
                                        } else {
                                            out.println("<div class='columnlarge center'><a href='answer.jsp?id=" + result.get(i).getQuestionID() + "'><h4>" + result.get(i).getTopic() + "</h4></a><p>" + result.get(i).getQuestion() + "</p></div>");
                                        }
                                    }

                                    if (result.get(i).getName().equals(check)) {
                                        out.println("<div class='columnlarge right'>'<p>asked by <span class='name'>"
                                                + result.get(i).getName() + "</span>|<a class='edit' href='question.jsp?id="
                                                + result.get(i).getQuestionID() + "'>edit</a> | <a class='delete' href='delete.jsp?id=" + result.get(i).getQuestionID() + "'>delete</a></p></div></div>");
                                    } else {
                                        out.println("<div class='columnlarge right'>'<p>asked by <span class='name'>"
                                                + result.get(i).getName() + "</span></p></div></div>");
                                    }
                                }
                            } catch (Exception ex) {
                                // TODO handle custom exceptions here
                            }
                        } else {

                            try {

                                
                                // TODO initialize WS operation arguments here
                                // TODO process result here
                                java.util.List<questionmodel.Question> result = port.getQuestionSearch(search);
                                for (int i = 0; i < result.size(); i++) {
                                    out.println("<div class='itemquestion'>");
                                    out.println("<div class='columnsmall left'> <p>" + result.get(i).getVotes()
                                            + "</p> <p>Votes</p></div>");
                                    out.println("<div class='columnsmall left'> <p>" + result.get(i).getAnswers()
                                            + "</p> <p>Answers</p></div>");
                                    if (!token.equals("")) {
                                        if (result.get(i).getQuestion().length() > 30) {
                                            out.println("<div class='columnlarge center'><a href='answer.jsp?id=" + result.get(i).getQuestionID() + "'><h4>" + result.get(i).getTopic() + "</h4></a><p>" + result.get(i).getQuestion().substring(0, 30) + ". . .</p></div>");
                                        } else {
                                            out.println("<div class='columnlarge center'><a href='answer.jsp?id=" + result.get(i).getQuestionID() + "'><h4>" + result.get(i).getTopic() + "</h4></a><p>" + result.get(i).getQuestion() + "</p></div>");
                                        }
                                    } else {
                                        if (result.get(i).getQuestion().length() > 30) {
                                            out.println("<div class='columnlarge center'><a href='answer.jsp?id=" + result.get(i).getQuestionID() + "'><h4>" + result.get(i).getTopic() + "</h4></a><p>" + result.get(i).getQuestion().substring(0, 30) + ". . .</p></div>");
                                        } else {
                                            out.println("<div class='columnlarge center'><a href='answer.jsp?id=" + result.get(i).getQuestionID() + "'><h4>" + result.get(i).getTopic() + "</h4></a><p>" + result.get(i).getQuestion() + "</p></div>");
                                        }
                                    }

                                    if (result.get(i).getName().equals(check)) {
                                        out.println("<div class='columnlarge right'>'<p>asked by <span class='name'>"
                                                + result.get(i).getName() + "</span>|<a class='edit' href='question.jsp?id="
                                                + result.get(i).getQuestionID() +  "'>edit</a> | <a class='delete' href='delete.jsp?id=" + result.get(i).getQuestionID() + "'>delete</a></p></div></div>");
                                    } else {
                                        out.println("<div class='columnlarge right'>'<p>asked by <span class='name'>"
                                                + result.get(i).getName() + "</span></p></div></div>");
                                    }
                                }
                            } catch (Exception ex) {
                                // TODO handle custom exceptions here
                            }
                        }
                    %>

                </div>
            </div>
        </div>
    </body>
</html>
