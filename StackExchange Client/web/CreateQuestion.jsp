<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="com.mysql.jdbc.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<link rel="stylesheet" type="text/css" href="style.css"/>
	<title>Questions</title>
    </head>
    <body>
	 <div id="big">Simple StackExchange</div>
	 <div class="mediumbaru">
	 <div id="m1">What's your question?</div>
	 <form name="makequestion" method="post" action="CreateQuestion.jsp" >
		<input type="text" name="question" placeholder="Question Topic" class="medium">
		<textarea name="content" placeholder="Content" class="medium" id="content"></textarea> 
		<input type="submit" value="Post" id="button">
	 </form></div>
         <%
            String title = request.getParameter("question");
            String content = request.getParameter("content");            
            if(title!=null && title!="") {
                String token = "";
                Cookie[] cookies = request.getCookies();
                if(cookies==null) {      
                    System.out.println("COOKIES NULL");
                }
                else {                
                    for(Cookie cookie : cookies) {
                        if("token".equals(cookie.getName())) { 
                            token = cookie.getValue();
                            System.out.println(token);
                            break;
                        }   
                    }
                }
                try {
                    questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
                    questionmodel.QuestionWS port = service.getQuestionWSPort();
                    int result = port.createQuestion(token, title, content);
                    if(result==1) 
                        response.sendRedirect(request.getContextPath() + "/ShowQuestionServlet");
                    else if(result==0)
                        response.sendRedirect(request.getContextPath() + "/LogInPage.jsp");
                    else
                        response.sendRedirect(request.getContextPath() + "/SignUpPage.jsp");                         
                } catch (Exception ex) {}                
            }
         %>
    </body>
</html>