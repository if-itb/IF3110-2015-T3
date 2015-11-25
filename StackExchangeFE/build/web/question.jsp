<!DOCTYPE html>

<html>

        <% String t = request.getParameter("token");%>
        <% String s = request.getParameter("id");%>
        
	<head>
		<link rel="stylesheet" type="text/css" href="css/wbd.css">
		<title>
			question - TuBes WBD
		</title>
	</head>

        <body>
        
                <div class ="text-right">
                    <a href="homepage.jsp"><h3>Logout</h3></a>
                </div>        
	
		<div class="font30 color-blue">
			<h1>
                                <a class="no-text-decoration" href="homepagelogin.jsp?token=<%=t%>">
                                        StackExchange
                                </a>
			</h1>
		</div>		
		
                <br>
			
		<div class="judulform text-left">
				What's your question ?
		</div>

		<br>

                
		<% if (s==null || s.isEmpty()) 
                { %>
                    <form name="questionForm" action="insertQuestion.jsp" method="post" onsubmit="return validateQue()">
                             <div class="text-left">
                                <input class="form-textbox" type="text" name="topic" placeholder="Question Topic"><br><br>
                                <textarea name="question" placeholder="Content"></textarea><br><br>
                             </div>

                             <div class="text-right">
                                <input class="form-submit" type="submit" name="post" value="Post">
                             </div>
                     </form>

                <% } 
                else 
                { %>
                
                
                
                <%-- start web service invocation --%>
   
                <%
                    try 
                    {
                        questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
                        questionmodel.QuestionWS port = service.getQuestionWSPort();
                        // TODO process result here
                        java.util.List<questionmodel.Question> result = port.getQuestion();
                        for (int i=0; i<result.size();i++)
                        {
                            if ( result.get(i).getQId() == Integer.valueOf(s))
                            {
                                out.println("<form name='questionForm' action='updateQuestion.jsp?id="+result.get(i).getQId()+"' method='post' onsubmit='return validateQue()'>");
                                out.println("<div class='text-left'>");
                                   out.println("<input class='form-textbox' type='text' name='topic' value="+result.get(i).getQTopic()+"'><br><br>");
                                   out.println("<textarea name='question'>"+result.get(i).getQContent()+"</textarea><br><br>");
                                out.println("</div>");
                                out.println("<div class='text-right'>");
                                out.println("<input class='form-submit' type='submit' name='post' value='Post'>");
                                out.println("</div>");
                                out.println("</form>");
                            }
                        }
                        
                    }
                    catch (Exception ex) 
                    {
                        // TODO handle custom exceptions here
                    }
                %>
            
                <%-- end web service invocation --%>
            
                <%}%>
                
        </body>
</html>		