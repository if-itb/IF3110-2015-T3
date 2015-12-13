<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>StackExchange</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <h1>Simple StackExchange</h1>
        <form action="search.jsp" name="search" method="post">
		<input type="text" name="search" placeholder="Type here" class="medium">
		<input type="submit" value="search" id="button">
        </form> 
        <p> Cannot find what you are looking for? <a href="CreateQuestion.jsp">Ask here</a> <p>
        <h2> Recently Asked Questions </h2>
            <c:forEach items="${questions}" var="question">
            <hr>
		<div class=vote>
                    <div class=number>
			<span>${question.votes}</span>
                    </div>	
                    <div>
                        Votes
                    </div>
                </div>
				
		<div class=answer>
                    <div class=number>
                        <span>${answers[question.id]}</span>
                    </div>
                    <div>
                        Answers
                    </div>		
		</div>
					
		<div class=topic>
                    <a href="AnswerServlet?id=${question.id}"><c:out value="${question.title}"/></a>
                    <c:out value="${question.content}"/>
                    <br>
		</div>
                <c:if test="${userid == question.idUser}">			
		<div class=asked>
                    asked by <span class=name>${askers[question.id]}</span> | <a href="EditQuestionServlet?qid=${question.id}&id=${userid}">edit</a> | <a href="DeleteQuestionServlet?qid=${question.id}&id=${userid}">delete</a>
		</div>
                </c:if>
                <c:if test="${userid != question.idUser}">			
		<div class=asked>
                    asked by <span class=name>${askers[question.id]}</span>
		</div>
                </c:if>
            </c:forEach>
            <a href="LogOut">logout</a>
    </body>
</html>