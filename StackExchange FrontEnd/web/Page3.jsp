<!DOCTYPE html>

<html>

	<head>
		<link rel="stylesheet" type="text/css" href="wbd.css">
		<script type="text/javascript" src="wbd.js"></script>
		<title>
			Page 3 - TuBes WBD
		</title>
	</head>

	<body>
		<div class="font30 color-blue">
			<h1>
				<a class="no-text-decoration" href=Page1.jsp>
					StackExchange
				</a>
			</h1>
		</div>
		<br>
	</body>
        
        <% String s = request.getParameter("id"); %>

            <%-- start web service invocation --%>
   
                <%
                    try 
                    {
                        questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
                        questionmodel.QuestionWS port = service.getQuestionWSPort();
                        // TODO process result here
                        java.util.List<questionmodel.Question> result = port.getQuestion();
                        for (int i=0;i<result.size();i++) 
                        {
                            if ( result.get(i).getQId() == Integer.valueOf(s))
                            {                            
                                out.println("<h1 class='text-left font30'>"+result.get(i).getQTopic()+"</h1>");
                                out.println("<hr>");
                                out.println("<div>");
                                out.println("<table class='table1'>");
                                out.println("<tr>");
                                out.println("<td class='tdnumber2 text-center'>");
                                out.println("<a href='voteUpQue.jsp?id="+result.get(i).getQId()+"'><img class='image1' src='arrowup.jpg' alt='VoteUp'></a>");
                                out.println("<br><br>3<br><br>");
                                out.println("<a href='voteDownQue.jsp?id="+result.get(i).getQId()+"'><img class='image1' src='arrowdown.jpg' alt='VoteDown'><br>");
                                out.println("</td>");
                                out.println("<td class='tdtopic text-left'>");
                                out.println(result.get(i).getQContent());
                                out.println("</td>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<td colspan='2' class='text-right'>");
                                out.println("<p>");
                                out.println("Asked by <span class='color-blue'>"+result.get(i).getUId()+"</span>");
                                out.println(" at <span>"+result.get(i).getQDate()+"</span>");
                                out.println(" | ");
                                out.println("<a class'color-yellow' href='Page2.jsp?id="+result.get(i).getQId()+"'> edit </a>");
                                out.println(" | ");
                                out.println("<a class='color-red' href='deleteQuestion.jsp?id="+result.get(i).getQId()+"'> delete </a>");
                                out.println("</p>");
                                out.println("<br><hr>");
                                out.println("</tr>");
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
                    for (int i=0;i<result.size();i++) 
                    {
                        if ( result.get(i).getQId() == Integer.valueOf(s))
                        {
                            out.println("<table class='table1'>");
                            out.println("<tr>");
                            out.println("<td class='tdnumber2 text-center'>");
                            out.println("<a href='voteUpAns.jsp?id="+result.get(i).getAId()+"'><img class='image1' src='arrowup.jpg' alt='VoteUp'></a>");
                            out.println("<br><br>3<br><br>");
                            out.println("<a href='voteDownAns.jsp?id="+result.get(i).getAId()+"'><img class='image1' src='arrowdown.jpg' alt='VoteUp'></a>");
                            out.println("</td>");
                            out.println("<td class='tdtopic text-left'");
                            out.println(result.get(i).getAContent());
                            out.println("</td>");
                            out.println("</tr>");
                            out.println("<tr>");
                            out.println("<td colspan='2' class='text-right'>");
                            out.println("<p>");
                            out.println("Asked by <span class='color-blue'>"+result.get(i).getUId()+"</span>");
                            out.println(" at <span>"+result.get(i).getADate()+"</span>");
                            out.println(" | ");
                            out.println("<a class'color-yellow' href='updateAnswer.jsp?id="+result.get(i).getQId()+"'> edit </a>");
                            out.println(" | ");
                            out.println("<a class='color-red' href='deleteAnswer.jsp?id="+result.get(i).getQId()+"'> delete </a>");
                            out.println("</p>");
                            out.println("<br><hr>");
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

        
</html>