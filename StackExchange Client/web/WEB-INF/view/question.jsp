<%-- 
    Document   : question
    Created on : Nov 17, 2015, 6:25:38 PM
    Author     : visat
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="header.jsp" flush="true"/>    
    <h3 class="topic"><a class="topic" href="question?id=${question.id}"><c:out value="${question.topic}"/></a></h3>
    <hr class="heading">
    <div ng-app="stackexchange">
        <div class="question-item">
            <div class="vote-panel" ng-controller="voteController" ng-init="init(${question.id}, 'question')">
                <c:choose>
                    <c:when test="${empty user}"><c:set var="question_class" value="-guest"></c:set></c:when>
                    <c:when test="${question_state > 0}"><c:set var="question_class" value="-yes"></c:set></c:when>
                    <c:when test="${question_state < 0}"><c:set var="question_class" value="-no"></c:set></c:when>
                    <c:otherwise><c:set var="question_class" value=""></c:set></c:otherwise>
                </c:choose>
                <span id="question-${question.id}-up" class="vote-button${question_class} glyphicon glyphicon-chevron-up" ng-click="voteQuestionUp(${question.id})" data-id="${question.id}" data-type="question" data-action="up"></span>
                <div class="vote-count">
                    <span id="question-${question.id}" ng-model="votes">{{votes["question" + ${question.id}]}}</span>
                </div>
                <c:choose>
                    <c:when test="${empty user}"><c:set var="question_class" value="-guest"></c:set></c:when>
                    <c:when test="${question_state < 0}"><c:set var="question_class" value="-yes"></c:set></c:when>
                    <c:when test="${question_state > 0}"><c:set var="question_class" value="-no"></c:set></c:when>
                    <c:otherwise><c:set var="question_class" value=""></c:set></c:otherwise>
                </c:choose>
                    <span id="question-${question.id}-down" class="vote-button${question_class} glyphicon glyphicon-chevron-down" data-id="${question.id}" data-type="question" data-action="down" ng-click="voteQuestionDown(${question.id})"></span>
            </div>
            
            <div class="question-content">
                <p><c:out value="${question.content}"/></p>
                <div class="timestamp">
                    asked by <c:out value="${asker.name}"/></a> at ${question.timestamp}
                    <c:if test="${not empty user && user.id==question.idUser}">
                        <a class="edit" href="edit?id=${question.id}"><span class="glyphicon glyphicon-edit"></span> edit</a>
                        <a class="delete" href="delete?id=${question.id}" onclick="return confirm('Are you sure you want to delete this question?');"><span class="glyphicon glyphicon-remove"></span> delete</a>
                    </c:if>
                </div>    
            </div>
            <div class="question-content">        
                <div class="comment" ng-controller="commentController" ng-init="init(${question.id})">
                    <div class="comment-item" ng-repeat="comment in comments">
                        <p>{{comment.content}} - <span class="author">{{ comment.user }}</span></p>
                    </div>

                    <form name="commentForm" ng-submit="addComment(${question.id})">
                        <input name="content" ng-model="content" type="text" placeholder="Your Comment" required>
                        <input type="submit" class="btn btn-default" value="Add comment">
                    </form>
                </div>
             </div>
        </div>
        <br>
        <c:if test="${fn:length(answers) > 0}">
            <h3>${fn:length(answers)} Answer${fn:length(answers) > 1 ? "s" : ""}</h3>
            <hr class="heading">
            <div class="answer-list">
            <c:forEach items="${answers}" var="answer">                
                <div class="answer-item">
                    <div class="vote-panel" ng-controller="voteController" ng-init="init(${answer.id}, 'answer')">
                        <c:choose>
                            <c:when test="${empty user}"><c:set var="answer_class" value="-guest"></c:set></c:when>
                            <c:when test="${answer_states[answer.id] > 0}"><c:set var="answer_class" value="-yes"></c:set></c:when>
                            <c:when test="${answer_states[answer.id] < 0}"><c:set var="answer_class" value="-no"></c:set></c:when>
                            <c:otherwise><c:set var="answer_class" value=""></c:set></c:otherwise>
                        </c:choose>
                        <span id="answer-${answer.id}-up" class="vote-button${answer_class} glyphicon glyphicon-chevron-up" data-id="${answer.id}" data-type="answer" data-action="up" ng-click="voteAnswerUp(${answer.id})"></span>
                        <div class="vote-count">
                            <span id="answer-${answer.id}" ng-model="votes">{{votes["answer" + ${answer.id}]}}</span>
                        </div>
                        <c:choose>
                            <c:when test="${empty user}"><c:set var="answer_class" value="-guest"></c:set></c:when>
                            <c:when test="${answer_states[answer.id] < 0}"><c:set var="answer_class" value="-yes"></c:set></c:when>
                            <c:when test="${answer_states[answer.id] > 0}"><c:set var="answer_class" value="-no"></c:set></c:when>
                            <c:otherwise><c:set var="answer_class" value=""></c:set></c:otherwise>
                        </c:choose>
                        <span id="answer-${answer.id}-down" class="vote-button${answer_class} glyphicon glyphicon-chevron-down" data-id="${answer.id}" data-type="answer" data-action="down" ng-click="voteAnswerDown(${answer.id})"></span>
                    </div>
                    <div class="answer-content">
                        <p><c:out value="${answer.content}"/></p>
                        <div class="timestamp">
                            answered by <c:out value="${answerers[answer.id].name}"/> at ${answer.timestamp}
                        </div>
                    </div>
                        
                </div>
                <hr>
            </c:forEach>
            </div>
        </c:if>
        </div>
                    
        <br>        
        <h3>Your Answer</h3>
        <hr class="heading">
        <form method="post">
            <textarea name="content" class="form-control" placeholder="Content" rows="7"></textarea>
            <input type="submit" class="btn-default btn-right" value="Post"></input>
            <input type="hidden" name="id" value="${question.id}"></input>
        </form>        
    </div>    
<jsp:include page="footer.jsp" flush="true"/>