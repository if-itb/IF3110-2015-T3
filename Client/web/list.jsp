<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
    
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Stack Exchange</title>
    <link rel="stylesheet" href="style.css"/>
</head>

<body>
  <c:forEach items="${questions}" var="question">
      
      <div class='container card'>
	  <div class='statistic'>
	      <span class='count'><c:out value="${question.getVotes()}"/></span>
	      <span class='unit'>votes</span>
	  </div>
	  <div class='statistic'>
	      <span class='count'><c:out value="${question.getAnswerCount()}"/></span>
	      <span class='unit'>answers</span>
	  </div>
	  <div class='question-detail'>
	      <div class='topic'><a href='/questions?id=<?= $question->id ?>'><c:out value="${question.getQtopic()}"/></a></div>
	      <div class='detail'>
		  <p><c:out value="${question.getQcontent()}"/></p>
	      </div>
	  </div>
	  <span class='control'>
	      <span>
		  asked by 
		  <span class='username'><c:out value="${question.getName()}"/></span>
		  <span class='email'><c:out value="${question.getEmail()}"/></span>
		  at <c:out value="${question.getCreatedAt()}"/> |
	      </span>
	      <span><a href='/questions/edit?id=<?= $question->id ?>'>edit </a></span>|
	      <span><a href='/questions/delete?id=<?= $question->id ?>' onclick="return deleteConfirm()">delete </a></span>
	  </span>
      </div>


  </c:forEach>
</body>
</html>






<!DOCTYPE html>
<!--<html lang='en'>
<head>
    <meta charset='UTF - 8' >
    <title>Stack Exchange</title
    <link href='https://fonts.googleapis.com/css?family=Josefin+Slab:400,700italic,300' rel='stylesheet' type='text/css'>
    <link rel='stylesheet' href='style.css'>
</head>

<body>
<div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>
<div class='container clearfix'>
<form class='searchForm clearfix' action='php/Search.php' method='POST'>
        <div class='searchInput'>
        <input  name='keyword' type='text' placeholder='Keyword Pencarian'/>
        <p class='askHere'>Cannot find what you are looking for ? <a href='Create.html'>Ask here</a></p>
        </div>
        <button class='searchBtn' type='submit'>Search</button>
    </form>
<h4>Recently Answered Questions</h4>-->
<%--<%=(String)request.getAttribute("questions")%>--%>

<!--<div class='table'>
    <div class='row clearfix'>
for(Question question : questions) {
    <div class='elemValue'>
        <span>" + question.getVotes() + "</span>
        <span class='vote'>Votes</span>
    </div>

    <div class='elemAnswer'>
        <span>" + question.getAnswerCount() + "</span>
        <span class='ans'>Answers</span>
    </div>

    <div class='elemQ'>
        <div class='elemQuestion'>
            <a href='detail?idDetail="+question.getQid()+"'><span class='topic'>" + question.getQtopic() + "</span></
    );
    uestion.getQcontent(
        </div>

        <div class='elemAuthor'>
            <span class='askedBy'>Asked By:</span>
            <div class='author'>
                <span class='name'>" + question.getName() + "</span>
                <a href='edit?idEdited="+ question.getQid() +"&fromDetail=0'> <span class='edit'>Edit</span></a>
                <a href='delete?idDeleted="+ question.getQid() +"'> <span class='delete'>Delete</span></a>
            </div>
        </div>
    </div>
}
<!--</div>
</div>
</body>
</html>-->