<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit question</title>
    </head>
    <body>
	 <div id="big">Simple StackExchange</div>
	 <div class="mediumbaru">
	 <div id="m1">What's your question?</div>
	 <form name="makequestion" method="post" action="EditQuestion.jsp" >
		<input type="text" name="question" value="${question.title}" placeholder="Question Topic" class="medium">
		<textarea name="content" placeholder="Content" class="medium" id="content">${question.content}</textarea> 
		<input type="hidden" name="question_id" value="${question.id}">
                <input type="submit" value="Post" id="button">
	 </form></div>
         <%
            int qid = Integer.parseInt(request.getParameter("question_id"));
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
                    int result = port.editQuestion(title, content,qid, token);
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
