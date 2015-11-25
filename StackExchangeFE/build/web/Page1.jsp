<!DOCTYPE html>


<html>

	<head>
		<link rel="stylesheet" type="text/css" href="wbd.css">
		<title>
			Page 1 - TuBes WBD
		</title>
	</head>

	<body>
	
		<div class="font30 color-blue">
			<h1>
				StackExchange
			</h1>
		</div>
		
		<form action="Page2.jsp" method="GET">
			<input class="form-textbox2" type="text" name="search">
			<input class="form-submit" type="submit" name="submit" value="Search">
		</form>
		
		<p class="font20">
			Cannot find what you are looking for ?
			<a class="no-text-decoration color-red" href="Page2.jsp">Ask here</a><br><br>
		</p>
			
		<h2 class="font20 text-left">
			Recently Asked Question <br>
		</h2>
                
                
                <%-- start web service invocation --%><hr/>

                    <%
                        try 
                        {
                            questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
                            questionmodel.QuestionWS port = service.getQuestionWSPort();
                            // TODO process result here
                            java.util.List<questionmodel.Question> result = port.getQuestion();
                            out.println("<table class='table1'>");
                            for (int i=0;i<result.size();i++) 
                            {
                                out.println("<tr>");
                                out.println("<td class='tdnumber text-center'>"+3);
                                out.println("<br><br>votes");
                                out.println("</td>");
                                out.println("<td class='tdnumber text-center'>"+1);
                                out.println("<br><br>answers");
                                out.println("</td>");
                                out.println("<td class='tdtopic no-text-decoration text-left'>");
                                out.println("<a href='Page3.jsp?id="+result.get(i).getQId()+"'>"+result.get(i).getQTopic()+"<br><br></a>");
                                out.println("<br>"+result.get(i).getQContent()+"</br>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<td colspan='3' class='text-right'>");
                                out.println("<p>");
                                out.println("Asked by <span class='color-blue'>"+result.get(i).getQId()+"</span>");
                                out.println(" | ");
                                out.println("<a class'color-yellow' href='Page2.jsp?id="+result.get(i).getQId()+"'> edit </a>");
                                out.println(" | ");
                                out.println("<a class='color-red' href='deleteQuestion.jsp?id="+result.get(i).getQId()+"'> delete </a>");
                                out.println("</p>");
                                out.println("<br><hr>");
                                out.println("</tr>");
                            }
                            out.println("</table>");
                        } 
                        catch (Exception ex) 
                        {
                            // TODO handle custom exceptions here
                        }

                    %>
                <%-- end web service invocation --%><hr/>




                
	</body>

</html>