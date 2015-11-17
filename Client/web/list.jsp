<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Stack Exchange</title>
	<link rel="stylesheet" href="styles/main.css"/>
	<link href='https://fonts.googleapis.com/css?family=Josefin+Slab:400,700italic,300' rel='stylesheet' type='text/css'>
</head>

<body>
	
	<div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>
	<div class='container clearfix'>
		
		<form class="searchForm clearfix" method="POST">
			<div class="searchInput">
				<input  name="keyword" type="text" placeholder="Keyword Pencarian"/>
				<p class="askHere">Cannot find what you are looking for ? <a ui-sref='ask'>Ask here</a></p>
			</div>
			<button class="searchBtn" type="submit">Search</button>
		</form>
		<h4>Recently Answered Questions</h4>

		<div class="table">
			<div class="row clearfix">
				<c:forEach items="${questions}" var="question">
				
				
				<div class="elemValue">
					<span><c:out value="${question.getVotes()}"/></span>
					<span class="vote">Votes</span>
				</div>

				<div class="elemAnswer">
					<span><c:out value="${question.getAnswerCount()}"/></span>
					<span class="ans">Answers</span>
				</div>

				<div class="elemQ">
					<div class="elemQuestion">
						<a href='detail?idDetail=<c:out value="${question.getQid()}"/>'><span class="topic" ><c:out value="${question.getQtopic()}"/></span></a>
						<c:out value="${question.getQcontent()}"/>
					</div>

					<div class='elemAuthor'>
						<span class='askedBy'>Asked By:</span>
						<div class='author'>
							<span class='name'><c:out value="${question.getName()}"/></span>
							<a href='edit?idEdited="+ <c:out value="${question.getQid()}"/> +"&fromDetail=0'> <span class='edit'>Edit</span></a>
							<a href='delete?idDeleted="+ <c:out value="${question.getQid()}"/> +"'> <span class='delete'>Delete</span></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>