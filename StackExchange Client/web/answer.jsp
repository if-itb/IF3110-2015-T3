<%-- 
    Document   : answer
    Created on : Nov 24, 2015, 1:20:01 AM
    Author     : Raihan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Answer a Question!</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
	<center>
    <%! 
               Cookie[] cookies = null;
               String token = null;
            %>

            <%                            
                cookies = request.getCookies();                
                if (cookies != null) {
                    for (Cookie cookie : cookies) {                        
                        if (cookie.getName().equals("user_token")) {
                            token = cookie.getValue();                             
                        }
                    }
                }
                
            %>    
        <a href='index.jsp'> <h1>Simple Stack Exchange</h1> </a>
            <%-- start web service invocation --%>
    <%
    try {
	stackexchange.Question_Service service = new stackexchange.Question_Service();
	stackexchange.QuestionWS port = service.getQuestionWSPort();
	stackexchangews.Operation_Service service2 = new stackexchangews.Operation_Service();
	stackexchangews.Operation port2 = service2.getOperationPort();        
	 // TODO initialize WS operation arguments here
	int qid = Integer.parseInt(request.getParameter("id"));
	// TODO process result here
	stackexchange.Question question = port.getQuestionByID(qid);
        
        out.println("<h3>"+question.getTopic()+"</h3>");
        out.println("<hr width='80%'>");
        out.println(question.getContent()+"<br>");
        
        int nanswer = port2.countAnswer(Integer.toString(question.getId()));
        int nvote = port2.countVote(true, Integer.toString(question.getId()));
        String uname = port2.getUserbyID(question.getIdUser());     
        if (port2.canVote(Integer.toString(port2.getUIDbyToken(token)), true, Integer.toString(question.getId()))) {
            out.println("<a href='state?mode=true&id="+ question.getId() +"&val=1" + "'>Vote Up</a>");
            out.println(" | ");
             out.println("<a href='state?mode=true&id="+ question.getId() +"&val=-1" + "'>Vote Down</a>");
            out.println("<BR>");
        }        
        out.println("Asked by : " + uname + "<br><br>");
        out.println(nanswer + " Answers<br>");
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%>
        <hr width="80%">       
        
            <%-- start web service invocation --%>
    <%
    try {
	stackexchangews.Operation_Service service2 = new stackexchangews.Operation_Service();
	stackexchangews.Operation port2 = service2.getOperationPort();
                
	Answer.AnswerWS_Service service = new Answer.AnswerWS_Service();
	Answer.AnswerWS port = service.getAnswerWSPort();
	 // TODO initialize WS operation arguments here
	int qid = Integer.parseInt(request.getParameter("id"));
	// TODO process result here
	java.util.List<Answer.Answer> anslist = port.getAnswerByQID(qid);
	
        if (anslist.isEmpty()) {
            out.println("No answer yet.<br>");            
        } else {
            for (Answer.Answer ans : anslist) {
                int nvote = port2.countVote(false, Integer.toString(ans.getId()));
                java.lang.String uname = port2.getUserbyID(ans.getIdUser());  
                out.println(nvote + " Votes <br>");

                if (port2.canVote(Integer.toString(port2.getUIDbyToken(token)), !true, Integer.toString(ans.getId()))) {
                    out.println("<a href='state?mode=false&id="+ ans.getId() +"&val=1" + "'>Vote Up</a>");
                    out.println(" | ");
                    out.println("<a href='state?mode=false&id="+ ans.getId() +"&val=-1" + "'>Vote Down</a>");
                    out.println("<BR>");
                }                       
                
                out.println("Answered by : " + uname + "<br>");
                out.println(ans.getContent()+"<br>");
                out.println("<hr width='80%'>");
            }
        }
		  } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%>
	 <h4>Your Answer</h4>
        <form name='question' action='answer' method='POST'>            
            <textarea class="inputform" name='acontent' placeholder="Answer" rows="5"></textarea><br><br>
            <input type='hidden' name='idq' value="<%out.println(request.getParameter("id"));%>">
            <input class="button" type="submit" value="Submit">
			</form>        
        
        
    </center>
	</body>
</html>