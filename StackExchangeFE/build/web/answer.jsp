<!DOCTYPE html>

<html>

	<head>
		<link rel="stylesheet" type="text/css" href="css/wbd.css">
		<title>
			answer - TuBes WBD
		</title>
	</head>

	<body>
		<div class="font30 color-blue">
			<h1>
				<a class="no-text-decoration" href=homepage.jsp>
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
                                int count = port.getAnswerCount(result.get(i).getQId());
                                out.println("<h1 class='text-left font30'>"+result.get(i).getQTopic()+"</h1>");
                                out.println("<hr>");
                                out.println("<div>");
                                out.println("<table class='table1'>");
                                out.println("<tr>");
                                out.println("<td class='tdnumber2 text-center'>");
                                out.println("<br><br>"+result.get(i).getQVote()+"<br><br>Votes");
                                out.println("</td>");
                                out.println("<td class='tdtopic text-left'>");
                                out.println(result.get(i).getQContent());
                                out.println("</td>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<td colspan='2' class='text-right'>");
                                out.println("<p>");
                                out.println("Asked by <span class='color-blue'>"+result.get(i).getUName()+"</span>");
                                out.println(" at <span>"+result.get(i).getQDate()+"</span>");
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
                    int count = 0;
                    for (int i=0;i<result.size();i++) 
                    {
                        if ( result.get(i).getQId() == Integer.valueOf(s))
                        {
                            count++;
                        }
                    }
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
                                out.println("<br><br>"+result.get(i).getAVote()+"<br><br>Votes");
                                out.println("</td>");
                                out.println("<td class='tdtopic text-left'>");
                                out.println(result.get(i).getAContent());
                                out.println("</td>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<td colspan='2' class='text-right'>");
                                out.println("<p>");
                                out.println("Answered by <span class='color-blue'>"+result.get(i).getUName()+"</span>");
                                out.println(" at <span>"+result.get(i).getADate()+"</span>");
                                out.println("</p>");
                                out.println("<br><hr>");
                                out.println("</tr>");
                            }
                        }
                        out.println("<br><br><td colspan='3' class='tdtopic'><h2>Login <a href='http://localhost:8020/WBD_IS/login.jsp'>here</a> to answer the question</h2></td>");
                        out.println("</table>");
                    }
                    else
                    {
                        out.println("<table class='table1'>");
                        out.println("<tr><br><br><br><br>");
                        out.println("<h1 class='text-left'>No Answer</h1><hr>");
                        out.println("</tr>"); 
                        out.println("<td class='tdtopic'><h2>Login <a href='http://localhost:8020/WBD_IS/login.jsp'>here</a> to answer the question</h2></td>");
                        out.println("</table>");
                    }
                } 
                catch (Exception ex) 
                {
                    // TODO handle custom exceptions here
                }
                %>
            <%-- end web service invocation --%>


        
</html>