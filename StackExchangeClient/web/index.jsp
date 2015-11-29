<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="includes/header.jsp" %>

<div class="container" style="margin-top: 20px;">
  <div id="header">
    <a href="/StackExchangeClient/home"><h1>StackExchange</h1></a>
  </div>

	<div class="main">
    <div class="wrapper" id="searchbox">
			<form role="form" action="/StackExchangeClient/home" method="GET">
        <input type="text" id="searchbox" name="keyword" required>
        <input class="btn" type="submit" id="searchbutton" value="Search" name="search">
			</form>

      <p>Cannot find what you are looking for? <a href="/StackExchangeClient/askquestion.jsp" style="color:pink">Ask here</a></p>
		</div>
    
    <div class="wrapper">
      <div class="content-header">
        <h2>Recently Asked Questions</h2>
      </div>

      <c:forEach items="${result}" var="question">
        <div class="child-content">
          <div class="sidebar">
            <div id="votes"><span id="numvotes">${hmap.get(question.getQuestionId())}</span><br>Votes</div>
            <div id="answers"><span id="numanswer">${answermap.get(question.getQuestionId())}</span><br>Answers</div>
          </div>

          <div class="list-content">
            <div class="question-title">
              <a href="<c:url value="/viewpost?qid=${question.getQuestionId()}"></c:url>">${question.getQuestionTopic()}</a>
            </div>
            <div class="question-preview">
              <c:set var="questioncontent" value="${question.getQuestionContent()}" />
              <c:set var="questionoverview" value="${fn:substring(questioncontent, 0, 150)}" /><br>
              ${questionoverview}
            </div>
            <div class="content-footer">
            	<c:choose>
                    <c:when test="${name == askmap.get(question.getQuestionId())}">
                        Asked by: ${askmap.get(question.getQuestionId())} | <a href="<c:url value="/editquestion" >
                         <c:param name="qid" value="${question.getQuestionId()}"/></c:url>">Edit</a> | <a href="deletequestion?qid=${question.getQuestionId()}">Delete</a>
                    </c:when>
                    <c:otherwise>
                        Asked by: ${askmap.get(question.getQuestionId())}
                    </c:otherwise>
                </c:choose>
            </div>
          </div>
        </div>
      </c:forEach> 

    </div> 
  </div>     
</div>
</body>
</html>