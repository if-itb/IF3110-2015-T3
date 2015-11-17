<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF - 8' >
    <title>Stack Exchange</title>
    <link href='https://fonts.googleapis.com/css?family=Josefin+Slab:400,700italic,300' rel='stylesheet' type='text/css'>
    <link rel='stylesheet' href='styles/main.css'>
</head>

<body>
    <div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>
    <div class='container clearfix'>
        <div class='containerDetail'>
            <h2><c:out value="${question.getQtopic()}" /></h2>
            <div class='row rowQuestion clearfix'>
                <div class='colVote'>
                    <div class='qVote arrow-up' id='<c:out value="${question.getQid()}" />'></div>
                    <span class='qVoteVal'><c:out value="${question.getVotes()}" /></span>
                    <div class='qVote arrow-down' id='<c:out value="${question.getQid()}" />'></div>
                </div>
                <div class='elemQDetail'>
                    <p><c:out value="${question.getQcontent()}" /></p>
                    <div class='elemAuthor'>
                        <span class='askedBy'>Asked By : </span>
                        <div class='author'>
                            <span class='name'> <c:out value="${question.getName()}" /> at <c:out value="${question.getCreatedAt()}"/>
                                <a href='edit?idEdited=<c:out value="${question.getQid()}" />&fromDetail=1'><span class='edit' >Edit</span></a>
                                <a href='delete?idDeleted=<c:out value="${question.getQid()}" />'><span class='delete'>Delete</span></a>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <div class='answer'>
                <h2><c:out value="${answers.size()}" />  Answer</h2>
                <div class='row clearfix'>

                    <c:forEach items="${answers}" var="answer">
                    <div class='colVote'>
                        <div class='aVote arrow-up' id='<c:out value="${answer.getAid()}" />'></div>
                        <span class='voteVal'><c:out value="${answer.getVotes()}" /></span>
                        <div class='aVote arrow-down' id='<c:out value="${answer.getAid()}" />'></div>
                    </div>
                    <div class='elemQDetail'>
                        <div class='elemQuestion elemA'><c:out value="${answer.getContent()}" /></div>
                        <div class='elemAuthor'>
                            <span class='answeredBy'>Answered By :</span>
                            <div class='author'>
                                <span class='name'><c:out value="${answer.getName()}" />  at  <c:out value="${answer.getCreatedAt()}" /> </span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class='yourAnswer'>
            <h2 class='yourAnswerTitle'>Your Answer Here</h2>
            <form name='questionForm' action='answer' method='POST'>
                <input type='text'  name='name'  placeholder='Name'/>
                <input type='text'  name='email'  placeholder='Email'/>
                <textarea  name='content' id='content'  placeholder='Content'></textarea>
                <button  id='submitBtn' class='submitBtn' >Answer</button>
                <input type='hidden'  name='qid' value=' + question.getQid() + '/>
            </form>
        </div>
    </div>
</div>
</div>

<script src='scripts/detail.js'></script>
</body>
</html>
