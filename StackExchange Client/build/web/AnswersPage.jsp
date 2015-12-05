<%-- 
    Document   : answers
    Created on : Nov 26, 2015, 1:29:14 PM
    Author     : Scemo
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="controller">
    <head>
        <title>StackExchange</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css"><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Simple StackExchange</h1>
            <h2>
                <div>
                    <span>${question.title}</span>
                </div>
                <div>
                    <hr>
                </div>
            </h2>
            <div class="answer_question">
                <div class="vote">
                    <div class="number">
                        <span><a href="VoteServlet?userid=${userid}&qid=${question.id}&type=q&stat=up">&#x25B2</a></span>
                    </div>
                    <br>
                    <div class="number">
                        <span>${question.votes}</span>
                    </div>
                    <br>
                    <div class="number">
                        <span><a href="VoteServlet?userid=${userid}&qid=${question.id}&type=q&stat=down">&#x25BC</a></span>
                    </div>		
		</div>
                <div class="answer_topic">
                    <c:out value="${question.content}"/>
		</div>
			
		<div class="asked_email">
                    asked by <span>${asker}</span>|<!-- <a href="Edit.php?id=".$row["Q_id"]."\" class=\"color_yellow\">edit<a> | <a href=\"Delete.php?id=".$row["Q_id"]."\" class=\"delete\">delete<a> -->
		</div>
                <div>
                    <div ng-controller="CommentController">
                        <div ng-repeat="comment in comments">
                            <p>{{ comment.comment }} - {{ comment.user }}</p>
                        </div>
                        <form name="commentForm" ng-submit="addcommentasync(comments)">
                            <input ng-model="comment.qid" ng-init="comment.q_id=${question.id}" type="hidden">
                            <input ng-model="comment.user"  type="hidden"> <!--ng-init="commentCtrl.comment.user= user.getName() %>"-->
                            <input ng-model="comment.comment" type="text" placeholder="Your comment">
                            <input type="submit" value="Add comment">
                        </form>
                    </div>
                </div>
            </div>
        <h2 class="align">
            <div>   
                <span>${count_answer[question.id]} Answers </span>
            </div>
            <div>
		<hr>
            </div>
	</h2>
        <c:forEach items="${answers}" var="answers">
            <div class="answers">
		<div class="vote">
                    <div class="number">
                        <span><a href="VoteServlet?userid=${userid}&aid=${answers.id}&type=a&stat=up">&#x25B2</a></span>
                    </div>
                    <br>
                    <div class="number">
			<span>${answers.vote}</span>
                    </div>
                    <br>
                    <div class="number">
                        <span><a href="VoteServlet?userid=${userid}&aid=${answers.id}&type=a&stat=down">&#x25BC</a></span>
                    </div>	
		</div>
		<div class="answer_topic">
                    <c:out value="${answers.content}"/>
		</div>
				
		<div class="asked_email">
                    answered by ${answerers[answers.id]}
                </div>			
            </div>
            <hr class="Margin">
        </c:forEach>
        <h3 class="align"> Your Answer </h3>
            <form class="align" name="answer" method="post" action="AnswersPage.jsp">
		<div class="kotakform">
                    <textarea name="content" class="form_content" placeholder="Content" required></textarea>
		</div>
                <input type="hidden" name="question_id" value="${question.id}">
		<div class="form_post">
                    <input type="submit" name="answer" value="Post">
		</div>
            </form>  
        <%
            String question_id = request.getParameter("question_id");
            String content = request.getParameter("content");            
            if(content!=null && content!="") {
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
                int result=0;
                try {
                    answermodel.AnswerWS_Service service = new answermodel.AnswerWS_Service();
                    answermodel.AnswerWS port = service.getAnswerWSPort();
                    result = port.createAnswer(token, Integer.parseInt(question_id), content);
                    out.println("Result = "+result);
                } catch (Exception ex) {}
                if(result==1) 
                    response.sendRedirect(request.getContextPath() + "/AnswerServlet?id="+question_id);
                else if(result==0)
                    response.sendRedirect(request.getContextPath() + "/LogInPage.jsp");
                else
                    response.sendRedirect(request.getContextPath() + "/SignUpPage.jsp");                         
            }
         %>
         <script src="controller.js"></script>
    </body>
</html>