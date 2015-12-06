<%-- 
    Document   : answer
    Created on : Nov 18, 2015, 4:01:31 PM
    Author     : Satria
--%>


<%@page import="java.net.URLDecoder"%>
<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String token = new String();
            int qidFromURL = Integer.parseInt(request.getParameter("id"));
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "style2.css">
        <script src="angular.js" type="text/javascript"></script>
        <script>
            var app = angular.module('votecomment', []);
            app.config(function($httpProvider) {
                $httpProvider.defaults.useXDomain = true;
            });
            app.controller('commentCtrl', function($scope, $http) {
                        $scope.comments = [];
                        $http.get("http://localhost:8001/VoteComment/CommentRSServlet?qid=<%=qidFromURL%>").then(function(response) {
                            $scope.comments = response.data.comments;
                        });
                        $scope.comment = "";
                        $scope.submitComment = function(token, comment, qid) {
                            $http.post("http://localhost:8001/VoteComment/CommentRSServlet", JSON.stringify({comment:comment, qid:qid, access_token:token })).then(function(response) {
                                $scope.comments.push(response.data);
                            });
                            //$scope.comments.push(comment);
                            $scope.content = "";
                        };
            });
            app.controller('VoteCtrl', function($scope, $http){
                $scope.vote2 = [];
                $scope.voteupQuestion = function(id,str){
                        console.log(id+" asdf "+str);
                        $http({
                                method:'GET',
                                url: 'http://localhost:8001/VoteComment/VoteUpQuestionRSServlet?token='+ str + '&qid=' + id
                        }).
                        then(function(response){
                            console.log(JSON.stringify(response));
                             $scope.vote = response.data.Vote;
                            
                        });
                };
                
                $scope.votedownQuestion = function(id,str){
                        console.log(id+" asdf "+str);
                        $http({
                                method:'GET',
                                url: 'http://localhost:8001/VoteComment/VoteDownQuestionRSServlet?token='+ str + '&qid=' + id
                        }).
                        then(function(response){
                            console.log(JSON.stringify(response));
                             $scope.vote = response.data.Vote;
                         
                        });
                };
                
                $scope.voteupAnswer = function(id,index,str){
                     
                        $http({
                                method:'GET',
                                url: 'http://localhost:8001/VoteComment/VoteUpAnswerRSServlet?token='+ str + '&id=' + id
                        }).
                        then(function(response){
                            console.log(JSON.stringify(response));
                             $scope.vote2[index] = response.data.Vote;
                          
                        });
                };
                
                $scope.votedownAnswer = function(id,index,str){
                    
                        $http({
                                method:'GET',
                                url: 'http://localhost:8001/VoteComment/VoteDownAnswerRSServlet?token='+ str + '&id=' + id
                        }).
                        then(function(response){
                            console.log(JSON.stringify(response));
                             $scope.vote2[index] = response.data.Vote;
                           
                        });
                };
            });
        </script>
        <title>Simple StackExchange</title>
    </head>
    <body ng-app="votecomment">
        <%
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
            <div class="container" ng-controller="VoteCtrl">
                <%-- start web service invocation --%><hr/>
                <%                    
                    

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
                            if (!token.equals("")) {
                               out.println("<img src='up.png' alt='up' height='42' width='42' data-ng-click='voteupQuestion("+result.get(i).getQuestionID()+",\""+token+"\")'>");
                            }
                            //print number of votes
                            out.println("<p ng-bind='vote' ng-init='vote="+result.get(i).getVotes()+"'>" + result.get(i).getVotes() + "</p>");
                            if (!token.equals("")) {
                                out.println("<img src='down.png' alt='up' height='42' width='42' data-ng-click='votedownQuestion("+result.get(i).getQuestionID()+",\""+token+"\")'>");
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
                            out.println("<div class='comment' ng-controller='commentCtrl'>");
                            out.println("<div class='comments' ng-repeat='comment in comments'>");
                            out.println("{{comment.name + ' - ' + comment.comment + ' at ' + comment.time}}");
                            out.println("</div>");
                            if (!token.equals("")) {
                                out.println("<div><br>");
                                out.println("<input type='text' style='width:100%' placeholder='Add a comment' ng-model='content' ng-keypress='$event.keyCode==13 && submitComment(\""+token+"\",content, "+qidFromURL+")' ><br><span style='font-size:8pt;'>Press Enter to submit</span>");
                                out.println("</div>");
                            }
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
                            if (!token.equals("")) {
                                 out.println("<img src='up.png' alt='up' height='42' width='42' data-ng-click='voteupAnswer("+result.get(i).getAnswerID()+","+i+",\""+token+"\")'>");
                            }
                            out.println("<p ng-bind='vote2["+i+"]' ng-init='vote2["+i+"]="+result.get(i).getVotes()+"'>" + result.get(i).getVotes() + "</p>");
                            //hide if token is not validated 
                            if (!token.equals("")) {
                                out.println("<img src='down.png' alt='up' height='42' width='42' data-ng-click='votedownAnswer("+result.get(i).getAnswerID()+","+i+",\""+token+"\")'>");
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
                        if (!token.equals("")) {
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
