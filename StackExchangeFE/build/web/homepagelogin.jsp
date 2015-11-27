<%-- 
    Document   : homepagelogin
    Created on : Nov 25, 2015, 5:26:22 PM
    Author     : Ben Lemuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>

        <% String t = request.getParameter("token");%> 
    

	<head>
		<link rel="stylesheet" type="text/css" href="css/wbd.css">
		<title>
			homepage - TuBes WBD
		</title>
	</head>

	<body>
            
                <div class ="text-right">
                    <a href="homepage.jsp"><h3>Logout</h3></a>
                </div>
            
		<div class="font30 color-blue">
			<h1>
				StackExchange
			</h1>
		</div>
		
		<form action="homepagelogin.jsp?token=<%=t%>" method="GET">
			<input class="form-textbox2" type="text" name="search">
			<input class="form-submit" type="submit" name="submit" value="Search">
		</form>
		
		<p class="font20">
			Cannot find what you are looking for ?
			<a class="no-text-decoration color-red" href="question.jsp?token=<%=t%>">Ask here</a><br><br>
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
                            int id = port.getUserID(t);
                            for (int i=0;i<result.size();i++) 
                            {
                                int count = port.getAnswerCount(result.get(i).getQId());
                                out.println("<tr>");
                                out.println("<td class='tdnumber text-center'>"+result.get(i).getQVote());
                                out.println("<br><br>votes");
                                out.println("</td>");
                                out.println("<td class='tdnumber text-center'>"+count);
                                out.println("<br><br>answers");
                                out.println("</td>");
                                out.println("<td class='tdtopic no-text-decoration text-left'>");
                                out.println("<a href='answerlogin.jsp?id="+result.get(i).getQId()+"&token="+t+"'>"+result.get(i).getQTopic()+"<br><br></a>");
                                out.println("<br>"+result.get(i).getQContent()+"</br>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<td colspan='3' class='text-right'>");
                                out.println("<p>");
                                if (id==result.get(i).getUId())
                                {
                                    out.println("Asked by <span class='color-blue'>you</span>");
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
                            }
                            out.println("</table>");
                        } 
                        catch (Exception ex) 
                        {
                            // TODO handle custom exceptions here
                        }

                    %>
                
                <%-- end web service invocation --%>




                
	</body>

</html>
