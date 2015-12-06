<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Simple Stack Exchange</title>
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
                token = null;
                if (cookies != null) {
                    for (Cookie cookie : cookies) { 
                        //out.println(cookie.getName());
                        if (cookie.getName().equals("user_token")) {
                            token = cookie.getValue();                             
                        }
                    }
                }
                
            %>


            <a href='index.jsp'> <h1>Simple Stack Exchange</h1> </a>
			        
        <form name="Search" action='state'>
            <input class="searchform" type="text" name="keyword">
            <input class="button" type="submit" value="Search"><br>
            Can't find what you're looking for? 
            <%
                if (token==null)
                    out.println("<a href='login.jsp'>");
                else {
                    out.println("<a href='ask.jsp'>");
                }
              %>  
                Ask here</a><br>
            <%            
            stackexchangews.Operation_Service service1 = new stackexchangews.Operation_Service();
            stackexchangews.Operation port1 = service1.getOperationPort();
            java.lang.String usname = port1.getUserbyToken(token);          
            if (token==null) {
                out.println("<a href='login.jsp'> Login </a>| <a href='register.jsp'> Sign Up</a>");
            } else {
                out.println("Welcome, you are logged in as " + usname  + "<br>");
                out.println("<a href='logout'> Logout </a>");
            }
                %>
             
        <h2>Recently Asked Question</h2> 
        

    <%
    try {
	stackexchangews.Operation_Service service2 = new stackexchangews.Operation_Service();
	stackexchangews.Operation port2 = service2.getOperationPort();	        
        
	stackexchange.Question_Service service = new stackexchange.Question_Service();
	stackexchange.QuestionWS port = service.getQuestionWSPort();
	// TODO process result here
	java.util.List<stackexchange.Question> qlist = port.getListQuestion();
	if (!qlist.isEmpty()) {
            for (stackexchange.Question q : qlist) {
                out.println("<hr width='80%'>");
                int nanswer = port2.countAnswer(Integer.toString(q.getId()));
                int nvote = port2.countVote(true, Integer.toString(q.getId()));
                String uname = port2.getUserbyID(q.getIdUser());
                out.println(nanswer + " Answers | " + nvote + " Votes<br>" + uname + " | <a href='answer.jsp?id=" + q.getId() + "'>" +q.getTopic() + "</a> | " + q.getContent());         
                if (port2.getUIDbyToken(token)==q.getIdUser()) {
                   out.println(" | <a href='edit.jsp?id="+ q.getId() +"'> Edit </a> | <a href='delete?mode=true&id="+ q.getId() +"'> Delete </a>");
                }
                out.println("<br>");
            }
        } else {
            out.println("<hr width='80%'>");
            out.println("No recent question asked.<br>");
        }
        
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
        </center> 
    </body>
    
</html>