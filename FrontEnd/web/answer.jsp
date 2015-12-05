<%-- 
    Document   : answer
    Created on : Nov 18, 2015, 4:01:31 PM
    Author     : Satria
--%>


<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "style2.css">
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script>
            angular.module('comment', [])
                    .controller('commentCtrl', function($scope) {
                        $scope.comments = [];
                        $scope.comment = "";
                        $scope.submitComment = function(comment) {
                            $scope.comments.push(comment);
                            $scope.content = "";
                        }
            });
            
        </script>
        <title>Simple StackExchange</title>
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
                out.println("<a href=\"http://localhost:8001/Identity/LoginRSServlet?token=" + request.getParameter("token") + "&logout=true\" style=\"margin-left: 72%;\">Logout</a>");
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
                <%-- start web service invocation --%><hr/>
                <%                    token = request.getParameter("token");
                    int qidFromURL = Integer.parseInt(request.getParameter("id"));

                    //take the first question
                    try {
                        
                        java.lang.String check = port.getName(token);
                        // TODO process result here
                        java.util.List<questionmodel.Question> result = port.getQuestionbyID(qidFromURL);
                        for (int i = 0; i < result.size(); i++) {
                            out.println("<div>");
                            out.println("<h2>" + result.get(i).getTopic() + "</h2>");
                            out.println("<div class='columnsmall left'>");
                            //hide if token is not validated 
                            if (token != null) {
                                out.println("<a href='upQues.jsp?id=" + qidFromURL + "&token=" + token + "'>");
                                out.println("<img src='up.png' alt='up' height='42' width='42' >");
                                out.println("</a>");
                            }
                            //print number of votes
                            out.println("<p>" + result.get(i).getVotes() + "</p>");
                            if (token != null) {
                                out.println("<a href='downQues.jsp?id=" + qidFromURL + "&token=" + token + "'>");
                                out.println("<img src='down.png' alt='up' height='42' width='42' >");
                                out.println("</a>");
                            }
                            out.println("</div>");

                            out.println("<div class='columnlargest center'>");
                            out.println("<p class='wrap'>" + result.get(i).getQuestion() + "</p>");
                            out.println("</div>");
                            out.println("</div>");
                            out.println("<div class='footer'>");
                            if (result.get(i).getName().equals(check)) {
                                out.println("<p>asked by" + result.get(i).getName() + " at " + result.get(i).getDatetime() + " | <a class='edit' href='question.jsp?id=" + result.get(i).getQuestionID() +"&token=" + token + "'>edit</a> | <a class='delete' href='delete.jsp?id=" + result.get(i).getQuestionID() +"&token=" + token + "'>delete</a></p>");
                            } else {
                                out.println("<p>asked by" + result.get(i).getName() + " at " + result.get(i).getDatetime() + " </p>");

                            }
                            out.println("<div ng-controller='commentCtrl' ng-app='comment'>");
                            out.println("<div ng-repeat='comment in comments'>");
                            out.println("{{comment}}");
                            out.println("</div>");
                            out.println("<div>");
                            out.println("<input type='text' placeholder='Add a comment' ng-model='content' ng-keypress='$event.keyCode==13 && submitComment(content)' ><br><span style='font-size:8pt;'>Press Enter to submit</span>");
                            out.println("</form>");
                            out.println("</div>");
                            out.println("</div>");
                            out.println("</div>");
                            out.println("<div>");
                            out.println("<h2>" + result.get(i).getAnswers() + " Answers</h2>");
                        }

                    } catch (Exception ex) {
                        // TODO handle custom exceptions here
                    }

                    //take all answer
                    try {
                        answermodel.AnswerWS_Service service1 = new answermodel.AnswerWS_Service();
                        answermodel.AnswerWS port1 = service1.getAnswerWSPort();
                        // TODO process result here
                        java.util.List<answermodel.Answer> result = port1.getAnswerbyQID(qidFromURL);
                        for (int i = 0; i < result.size(); i++) {
                            out.println("<div class='answer'>");

                            out.println("<div class='columnsmall left' >");
                            //hide if token is not validated 
                            if (token != null) {
                                out.println("<a href='upAns.jsp?id=" + result.get(i).getAnswerID() + "&qid=" + qidFromURL + "&token=" + token + "'>");
                                out.println("<img src='up.png' alt='up' height='42' width='42' >");
                                out.println("</a>");
                            }
                            out.println("<p>" + result.get(i).getVotes() + "</p>");
                            //hide if token is not validated 
                            if (token != null) {
                                out.println("<a href='downAns.jsp?id=" + result.get(i).getAnswerID() + "&qid=" + qidFromURL + "&token=" + token + "'>");
                                out.println("<img src='down.png' alt='up' height='42' width='42' >");
                                out.println("</a>");
                            }
                            out.println("</div>");

                            out.println("<div class='columnlargest center'>");
                            out.println("<p class='warp'>" + result.get(i).getAnswer() + "</p>");
                            out.println("</div>");
                            
                            out.println("<div class='footer'>");
                            out.println("<p>answered by " + result.get(i).getName() + " at " + result.get(i).getDatetime() + "</p>");
                            out.println("</div>");

                            out.println("</div>");
                        }
                        out.println("</div>");
                    } catch (Exception ex) {
                        // TODO handle custom exceptions here
                        out.println("mama");
                    }

                %>
                <%-- end web service invocation --%><hr/>
                <div>
                    
                    <%                        //Check if the token is null then hide form
                        if (token != null) {
                            out.println("<h3>Your Answer</h3>");
                            out.println("<form name='answer' action='insertanswer.jsp?id=" + request.getParameter("id") + "&token=" + token + "' method='post' class='form'>");
                            out.println("<textarea name='content' placeholder='Content' maxlength='1500'></textarea>");
                            out.println("<input type='submit' value='Post'>");
                            out.println("</form>");
                        }
                    %>

                </div>
            </div>
        </div>

    </body>
</html>
