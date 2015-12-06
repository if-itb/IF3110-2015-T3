<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Timestamp"%>
<!DOCTYPE html>

<html>

        <%  
            String t = "";
            Cookie [] cookieArray = request.getCookies();
            if(cookieArray != null){
                    for (int j=0; j<cookieArray.length;j++){
                        if(cookieArray[j].getName().equals("token")){
                            t = cookieArray[j].getValue();
                        }
                    }
                   }
        %>
        <% String s = request.getParameter("id");%> 
        
	<head>
                
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/wbd.css">
                <script src="angular.js" type="text/javascript"></script>
                    <script>
                        
                        var app = angular.module('comment', []);
                        app.config(function($httpProvider){
                            $httpProvider.defaults.useXDomain = true;
                        });
                        
                        app.controller('commentCtrl', function($scope,$http) {
                            $scope.commentList=[];
                            $scope.comment="";
                            var sendTo = "http://localhost:8082/StackExchangeREST/Comment";
                            $http.get(sendTo+"?qid=<%=s%>").then(function(response) {
                                $scope.commentList = response.data.commentList;
                            });
                            $scope.add = function(token,qid,uid,content,uname) {  
                                var send = JSON.stringify({qid:qid,uid:uid,content:content,token:token, uname:uname});
                                $http.post(sendTo,send).then(function(response){
                                  $scope.commentList.push(response.data);
                                  console.log(JSON.stringify(response));
                                });
                                $scope.content = "";
                            };
                        });
                        
                    
                        app.controller('voteCtrl', function($scope, $http){
                        $scope.voteQue = function(token,qid,aid,uid,vcount,qvote){
                                var sendTo = "http://localhost:8082/StackExchangeREST/voteQuestion";
                                var send = JSON.stringify({token:token,qid:qid,aid:aid,uid:uid,vcount:vcount,qvote:qvote});
                                $http.post(sendTo,send).then(function(response){
                                    $scope.vote = response.data.vote;
                                    console.log(JSON.stringify(response));
                                });
                        };
                        $scope.voteAns = function(token,qid,aid,uid,vcount,avote){
                                var sendTo = "http://localhost:8082/StackExchangeREST/voteAnswer";
                                var send = JSON.stringify({token:token,qid:qid,aid:aid,uid:uid,vcount:vcount,avote:avote});
                                $http.post(sendTo,send).then(function(response){
                                    $scope.vote = response.data.vote;
                                    console.log(JSON.stringify(response));
                                });
                        };
                    });
                        
                    </script>
		<title>
			answer - TuBes WBD
		</title>
	</head>

	<body ng-app='comment' >

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
                                    <a class="no-text-decoration" href="homepagelogin.jsp">
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
                        String uname = port.getUserName(t);
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
                                out.println("<div ng-controller='voteCtrl'>");
                                out.println("<a ng-model='vote' ng-init='vote= "+ result.get(i).getQVote() + ";'><img class='image1' src='jpg/arrowup.jpg' ng-click='voteQue(\""+t+"\", "+qid+", 0, "+id+", 1, "+result.get(i).getQVote()+");'></a>");
                                out.println("<br><br>{{vote}}<br><br>");
                                out.println("<a ng-model='vote'><img class='image1' src='jpg/arrowdown.jpg' ng-click='voteQue(\""+t+"\", "+qid+", 0, "+id+", -1, "+result.get(i).getQVote()+");'><br>");
                                out.println("</div>");
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
                                    out.println("<a class'color-yellow' href='question.jsp?id="+result.get(i).getQId()+"'> edit </a>");
                                    out.println(" | ");
                                    out.println("<a class='color-red' href='deleteQuestion.jsp?id="+result.get(i).getQId()+"'> delete </a>");
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
                                out.println("<div ng-controller='commentCtrl'>");
                                out.println("<a class='underline font30'>Add Comment</a><br><br>");
                                out.println("<div class='text-center'>");
                                out.println("<input class='form-textbox2' type='text' Placeholder='Comment' ng-model='content'>");
                                out.println("<input ng-click='add(\""+t+"\", "+qid+", "+id+", content, \""+uname+"\");' class='form-submit' type='submit' name='submit' placeholder='Add'><br><br>");
                                out.println("</div>");
                                out.println("<div ng-repeat='comment in commentList'>");
                                out.println("{{comment.c_content}}");
                                out.println("<br>");
                                out.println("{{' commented by '}}");
                                out.println("<a class='color-blue'>");
                                out.println("{{comment.u_name}}");
                                out.println("</a>");
                                out.println("{{' at ' + comment.c_time}}");
                                out.println("<hr>");
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
                                out.println("<div ng-controller='voteCtrl'>");
                                out.println("<a ng-model='vote' ng-init='vote= "+ result.get(i).getAVote() + ";'ng-click='voteAns(\""+t+"\", 0, "+result.get(i).getAId()+", "+id+", 1, "+result.get(i).getAVote()+");'><img class='image1' src='jpg/arrowup.jpg'></a>");
                                out.println("<br><br>{{vote}}<br><br>");
                                out.println("<a ng-model='vote' ng-click='voteAns(\""+t+"\", 0, "+result.get(i).getAId()+", "+id+", -1, "+result.get(i).getAVote()+");'><img class='image1' src='jpg/arrowdown.jpg'></a>");
                                out.println("</div>");
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
                                    out.println("<a class'color-yellow' href='answerlogin.jsp?id="+result.get(i).getQId()+"'> edit </a>");
                                    out.println(" | ");
                                    out.println("<a class='color-red' href='answerlogin.jsp?id="+result.get(i).getQId()+"'> delete </a>");
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
        
            <form name="questionForm" action="insertAnswer.jsp?id=<%=s%>" method="post" onsubmit="return validateQue()">
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
