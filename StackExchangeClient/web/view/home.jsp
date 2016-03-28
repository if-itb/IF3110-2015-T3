<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head><title>Simple StackExchange</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>

<body>
	<div class ='container'>
            <h1 class='center'><a href="Home?token=${token}" class="title">Simple StackExchange</a></h1>
                <a href="Logout?token=${token}">logout</a>
		<div class='center'>
			<form action='/tools/question/search.php' method='GET'>
				<input type='text' name="search" class='searchbar'><button type="submit">Search</button>
			</form>
		</div>

		<p class='center'>
			Cannot find what you are looking for? <a href='AskQuestionPage?token=${token}' class='yellow'>Ask here</a>
		</p>

		<h2>Recently Asked Questions</h2>

		<c:forEach items="${questions}" var="question">
			<div class='question'>
			<div class='row'>
				<div class='col-1'>
					<p class='center'><b>${question.votes}</b></p>
					<p class='center'><b>Votes</b></p>
				</div>
				<div class='col-2'>
					<p class='center'><b>${question.countAnswers}</b></p>
					<p class='center'><b>Answers</b></p>
				</div>
				<div class='col-8'>
					<p>	
						<a href='ViewQuestion?id=${question.questionId}&token=${token}'> 
							${question.topic}
						</a>
					</p>
					<p>
                                            ${question.content}
					</p>
					<p class='right'>
					asked by <span class="name">${question.askerEmail}</span> | 
					<a href='EditQuestion?id=${question.questionId}&token=${token}' class='yellow' onclick = "editQuestion(${question.questionId})">edit</a> | 
					<a href='Delete?id=${question.questionId}&token=${token}' class='red'>delete</a>
					</p>
				</div>
			</div>
			</div>
		</c:forEach>
        </div>
</body>
</html>