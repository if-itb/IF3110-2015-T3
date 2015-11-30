<%-- 
    Document   : ask
    Created on : Nov 17, 2015, 11:57:34 PM
    Author     : visat
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="header.jsp" flush="true"/>
    <div class="container">
        <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span><c:out value="${error}"/>
        </div>
        </c:if>
        <h3>What's your question?</h3>
        <hr class="heading">        
        <form method="post">
            <input type="text" class="form-control" name="topic" placeholder="Question Topic" value="<c:out value="${empty question? '' : question.topic}"/>" required autofocus></input>
            <textarea class="form-control" name="content" placeholder="Content" rows="10" required><c:out value="${empty question? '' : question.content}"/></textarea>
            <input type="submit" class="btn-default btn-right" value="Post"></input>
            <c:if test="${not empty edit_question}">
            <input type="hidden" name="id" value="${question.id}"></input>
            </c:if>
        </form>
    </div>
<jsp:include page="footer.jsp" flush="true"/>
