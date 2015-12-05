<!DOCTYPE html>

<html>

        <% String t = request.getParameter("token");%>
        <% String s = request.getParameter("id");%>
        
	<head>
                
		<link rel="stylesheet" type="text/css" href="css/wbd.css">
                <script src="angular.js" type="text/javascript"></script>
                    <script>
                        var app = angular.module('comment', []);
                        app.config(function($httpProvider){
                            $httpProvider.defaults.useXDomain = true;
                        })
                        app.controller('commentCtrl', function($scope,$http) {
                            $scope.commentList=[];
                            $scope.newComment="";
                            $scope.myVar = true;
                            $scope.toggle = function() {
                                $scope.myVar = !$scope.myVar;
                            };
                            $scope.add = function(qid,uid,content,token) {
                                var sendTo = "http://localhost:8082/StackExchangeREST/Comment"+"<%= request.getParameter("token") %>";
                                var data = JSON.strigify({qid:qid,uid:uid,content:content,token:token});
                                $http.post(sendTo,data).then(function(response){
                                    $scope.commentList.push(response.data.error);
                                });
                                $scope.newComment="";
                            };
                        });
                    </script>
		<title>
			answer - TuBes WBD
		</title>
	</head>

	<body>

                <div class ="text-right">
                    <a href="homepage.jsp"><h3>Logout</h3></a>
                </div>
            
		<div class="font30 color-blue">
			<h1>
                                <%if (t.equals(null))
                                {%>
                                    <a class="no-text-decoration" href="homepage.jsp">
                                            StackExchange
                                    </a>
                                <%}
                                else 
                                {%>
                                    <a class="no-text-decoration" href="homepagelogin.jsp?token=<%=t%>">
                                            StackExchange
                                    </a>
                                <%}%>
			</h1>
		</div>
		<br>

            <%-- start web service invocation --%>
   
                <%
                    try 
                    {
                        questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
                        questionmodel.QuestionWS port = service.getQuestionWSPort();
                        // TODO process result here
                        java.util.List<questionmodel.Question> result = port.getQuestion();
                        int id = port.getUserID(t);
                        int qid = Integer.valueOf(s);
                        for (int i=0;i<result.size();i++) 
                        {
                            if ( result.get(i).getQId() == qid)
                            {      
                                int count = port.getAnswerCount(result.get(i).getQId());
                                out.println("<h1 class='text-left font30'>"+result.get(i).getQTopic()+"</h1>");
                                out.println("<hr>");
                                out.println("<div>");
                                out.println("<table class='table1'>");
                                out.println("<tr>");
                                out.println("<td class='tdnumber2 text-center'>");
                                out.println("<a href='voteQue.jsp?id="+result.get(i).getQId()+"&token="+t+"&value=plus'><img class='image1' src='jpg/arrowup.jpg' alt='VoteUp'></a>");
                                out.println("<br><br>"+result.get(i).getQVote()+"<br><br>");
                                out.println("<a href='voteQue.jsp?id="+result.get(i).getQId()+"&token="+t+"&value=minus'><img class='image1' src='jpg/arrowdown.jpg' alt='VoteDown'><br>");
                                out.println("</td>");
                                out.println("<td class='tdtopic text-left'>");
                                out.println(result.get(i).getQContent());
                                out.println("</td>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<td colspan='2' class='text-right'>");
                                out.println("<p>");
                                if (id==result.get(i).getUId())
                                {
                                    out.println("Asked by <span class='color-blue'>you</span>");
                                    out.println(" at <span>"+result.get(i).getQDate()+"</span>");
                                    out.println(" | ");
                                    out.println("<a class'color-yellow' href='question.jsp?id="+result.get(i).getQId()+"&token="+t+"'> edit </a>");
                                    out.println(" | ");
                                    out.println("<a class='color-red' href='deleteQuestion.jsp?id="+result.get(i).getQId()+"&token="+t+"'> delete </a>");
                                }
                                else
                                {
                                    out.println("Asked by <span class='color-blue'>"+result.get(i).getUName()+"</span>");
                                }
                                out.println("</p>");
                                out.println("<br><hr>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<td colspan='2'class='text-left'>");
                                out.println("<div ng-app='comment' ng-controller='commentCtrl'>");
                                out.println("<button class='button1' ng-click='toggle()'>Add Comment</button>");
                                out.println("<p class='text-center' ng-hide='myVar'>");
                                out.println("<input class='form-textbox2' type='text' Placeholder='Comment' ng-model='addComment'>");
                                //out.println(id + " " + qid + " " + t);
                                out.println("<input ng-model='content' ng-click='add("+qid+", "+id+", content, "+t+")' class='form-submit' type='submit' name='submit' value='Add'><br><br>");
                                out.println("</p>");
                                out.println("<div ng-repeat='newComment in commentList'>");
                                out.println("{{newComment}}");
                                out.println("</div>");
                                out.println("</div>");
                                out.println("</tr>");
                                out.println("</table>");
                            }
                        }
                    }
                    catch (Exception ex) 
                    {
                        // TODO handle custom exceptions here
                    }
                %> 
                
                
                
            <%-- end web service invocation --%>
                
            <%-- start web service invocation --%>
            
                <%
                try {
                    answermodel.AnswerWS_Service service = new answermodel.AnswerWS_Service();
                    answermodel.AnswerWS port = service.getAnswerWSPort();
                     // TODO initialize WS operation arguments here
                    int qid = Integer.valueOf(s);
                    // TODO process result here
                    java.util.List<answermodel.Answer> result = port.getAnswerByQID(qid);
                    int count = 0;
                    for (int i=0;i<result.size();i++) 
                    {
                        if ( result.get(i).getQId() == Integer.valueOf(s))
                        {
                            count++;
                        }
                    }
                    int id = port.getUserID(t);
                    if (count>0)
                    {
                        out.println("<table class='table1'>");
                        out.println("<tr><br><br><br><br>");
                        out.println("<h1 class='text-left'>"+count+" Answers</h1><hr>");
                        out.println("</tr>");
                        for (int i=0;i<result.size();i++) 
                        {
                            if ( result.get(i).getQId() == Integer.valueOf(s))
                            {
                                out.println("<tr>");
                                out.println("<td class='tdnumber2 text-center'>");
                                out.println("<a href='voteAns.jsp?aid="+result.get(i).getAId()+"&qid="+s+"&token="+t+"&value=plus'><img class='image1' src='jpg/arrowup.jpg' alt='VoteUp'></a>");
                                out.println("<br><br>"+result.get(i).getAVote()+"<br><br>");
                                out.println("<a href='voteAns.jsp?aid="+result.get(i).getAId()+"&qid="+s+"&token="+t+"&value=minus'><img class='image1' src='jpg/arrowdown.jpg' alt='VoteUp'></a>");
                                out.println("</td>");
                                out.println("<td class='tdtopic text-left'>");
                                out.println(result.get(i).getAContent());
                                out.println("</td>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<td colspan='2' class='text-right'>");
                                out.println("<p>");
                                if (id==result.get(i).getUId())
                                {
                                    out.println("Answered by <span class='color-blue'>you</span>");
                                    out.println(" at <span>"+result.get(i).getADate()+"</span>");
                                    out.println(" | ");
                                    out.println("<a class'color-yellow' href='answerlogin.jsp?id="+result.get(i).getQId()+"&token="+t+"'> edit </a>");
                                    out.println(" | ");
                                    out.println("<a class='color-red' href='answerlogin.jsp?id="+result.get(i).getQId()+"&token="+t+"'> delete </a>");
                                }
                                else
                                {
                                    out.println("Answered by <span class='color-blue'>"+result.get(i).getUName()+"</span>");
                                }
                                out.println("</p>");
                                out.println("<br><hr>");
                                out.println("</tr>");
                            }
                        }
                        out.println("</table>");
                    }
                    else
                    {
                        out.println("<table class='table1'>");
                        out.println("<tr><br><br><br><br>");
                        out.println("<h1 class='text-left'>No Answer</h1><hr>");
                        out.println("</tr>");                       
                    }
                } 
                catch (Exception ex) 
                {
                    // TODO handle custom exceptions here
                }
                %>
            <%-- end web service invocation --%>
        
            <form name="questionForm" action="insertAnswer.jsp?id=<%=s%>&token=<%=t%>" method="post" onsubmit="return validateQue()">
                    <div class="text-left">
                        <br><br><br><br>
                        <h1>Do you know the answer of the question ?</h1>
                       <textarea name="answer" placeholder="Answer"></textarea><br><br>
                    </div>

                    <div class="text-right">
                       <input class="form-submit" type="submit" name="post" value="Post">
                    </div>
            </form>
        </body>
        
</html>

