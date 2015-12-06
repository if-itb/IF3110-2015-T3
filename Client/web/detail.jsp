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

<body ng-app = 'App'>
    <div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>
    <div class='container clearfix'>
        <div class='containerDetail'>
        <div class="auth">
        
        <c:choose>
            <c:when test="${isAuthenticated}">
            <h2><a class="logout" href="logout">Logout</a><span class="username"><c:out value="(  ${username}  )"/></span><h2>
            </c:when>    
            <c:otherwise>
            <h2><a class="lgn" href="login.jsp">Login</a><a class="rgs" href="register.jsp">Register</a>
            </c:otherwise>
        </c:choose>
        </div>
            <h2><c:out value="${question.getQtopic()}" /></h2>
            <div class='row rowQuestion clearfix' id='<c:out value="${question.getQid()}" />'>
                <div class='colVote' ng-controller='voteCtrl' >
                    <div class='qVote arrow-up' id='<c:out value="${question.getQid()}" />' ng-click='voteUp()'></div>
                    <span class='qVoteVal'>{{vote}}</span>
                    <div class='qVote arrow-down' id='<c:out value="${question.getQid()}" />' ng-click='voteDown()'></div>
                </div>
                <div class='elemQDetail'>
                    <p><c:out value="${question.getQcontent()}" /></p>
                    <div class='elemAuthor'>
                        <span class='askedBy'>Asked By : </span>
                        <div class='author'>
                            <span class='name'> <c:out value="${question.getName()}" /> at <c:out value="${question.getCreatedAt()}"/>
                <c:set var="name" scope="page" value="${question.getName()}"/>
                <c:set var="username" scope="page" value="${username}"/>
                <c:if test="${ name == username}">
                    <a href='edit?idEdited=<c:out value="${question.getQid()}" />&fromDetail=1'><span class='edit' >Edit</span></a>
                    <a href='delete?idDeleted=<c:out value="${question.getQid()}" />'><span class='delete'>Delete</span></a>
                </c:if>
                            </span>
                        </div>
                    </div>
                </div>
            </div>


            <div ng-controller = 'commentCtrl'>
                <form>
                    <input type="text" id = "content" name="content" placeholder="zzz" ng-model="content">
                    <button type="submit"  ng-click='comment()'> Submit </button>
                </form>
		
                <div ng-repeat = "comment in comments" class="comment">
                    <div class="line-separator"></div>
		              <p>{{comment.content}} by <strong>{{comment.name}}</strong> at {{comment.createdAt}}</p>
                </div>
            </div>

            <div class='answer'>
                <h2><c:out value="${answers.size()}" />  Answer</h2>
                <div class='row clearfix'>

                    <c:forEach items="${answers}" var="answer">
                    <div class='colVote'>
                        <div class='aVote arrow-up' id='<c:out value="${answer.getQid()}" />' ng-click='voteUp()'></div>
                        <span class='voteVal'>{{vote}}</span>
                        <div class='aVote arrow-down' id='<c:out value="${answer.getQid()}" />' ng-click='voteDown()'></div>
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
                <textarea  name='content' id='content'  placeholder='Content'></textarea>
                <button  id='submitBtn' class='submitBtn' >Answer</button>
                <input type='hidden'  name='qid' value='<c:out value="${question.getQid()}" />'/>
            </form>
        </div>
    </div>
</div>
</div>

    <script type="text/javascript" src="angular.min.js"></script>
    <script type="text/javascript" src="angular-cookies.min.js"></script>
    <script type="text/javascript" src="x2js.js"></script>
    <script type="text/javascript" src="xml2json.js"></script>
    <script type="text/javascript" src="controller.js"></script>
    <script type="text/javascript" src="vote.js"></script>
</body>
</html>
