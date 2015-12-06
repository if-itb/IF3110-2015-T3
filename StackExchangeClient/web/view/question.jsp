<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head><title>${question.topic}</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>


<body ng-app="stackExchange">
        <div class ='container'>
                <h1 class='center'><a href="Home?token=${token}" class = "title">Simple StackExchange</a></h1>

                <div class='center'>
                        <input type='text' class='searchbar'> <button>Search</button>
                </div>

                <div>&nbsp;</div>

                <h2>${question.topic}</h2>

                <div class='question'>
                    <div class='row'>
                            <div class='col-1'>
                                <a href="UpVoteQuestion?id=${question.questionId}&token=${token}"><div class='arrow-up'"></div></a>
                                <b><p class='center' id='question${question.questionId}'>${question.votes}</p></b>
                                <a href="DownVoteQuestion?id=${question.questionId}&token=${token}"><div class='arrow-down'></div></a>
                            </div>

                            <div class='col-8'>
                                    <p>${question.content}</p>
                                    <p class='right'>
                                            asked by <span class='name'>${question.askerEmail}</span> at ${question.time} | 
                                            <a href='EditQuestion?id=${question.questionId}&token=${token}' class='yellow')">edit</a> | 
                                            <a href='#' class='red')">delete</a>
                                    </p>
                            </div>
                    </div>
                    <div ng-controller="CommentController as commentCtrl">
                        <div class= 'comments' ng-repeat="comment in comments">
                            <div class = 'comment'>
                                <div class = 'row'>
                                    <div class = 'col-8'>
                                        <p>{{comment.content}} - {{comment.email}}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <p><a href = "#">add a comment</a></p>
                    </div>
                </div>
                

                <h2>${answers.size()} Answers</h2>
                <c:forEach items="${answers}" var="answer">
                        <div class='answer'>
                                <div class='row'>
                                        <div class='col-1'>
                                            <a href="UpVoteAnswer?id=${answer.answerId}&token=${token}"><div class='arrow-up'></div></a>
                                            <b><p class='center' id='answer${answer.answerId}'>${answer.votes}</p></b>
                                            <a href="DownVoteAnswer?id=${answer.answerId}&token=${token}"><div class='arrow-down'></div></a>
                                        </div>
                                        <div class='col-8'>
                                        <p>${answer.content}</p>
                                                <p class='right'>
                                                answered by <span class='name'>${answer.answererEmail}</span> at ${answer.time}
                                                </p>
                                        </div>
                                </div>
                        </div>
                </c:forEach>

                <hr>

                <h1>Your Answer</h1>
                <form name='answerForm' action="AddAnswer" method="post">
                        <input type="hidden" name="questionId" value="${question.questionId}">
                        <input type="hidden" name="token" value="${token}">
                        <textarea name='content' class='formInput' placeholder='Content' rows='10'></textarea>
                        <div class='right'><button type='submit'>Post</button></div>
                </form>
        </div>
                        
        <script type='text/javascript' src='assets/js/angular.min.js'></script>
        <script type='text/javascript' src='assets/js/app.js'></script>
</body>
</html>
