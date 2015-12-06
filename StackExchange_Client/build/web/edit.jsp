<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="question" type="model.question.Question" scope="request"/>
<jsp:include page="header.jsp" flush="true"/>
            <div class="subq black">Edit your question</div>
            <form class="inputform" method="post" onsubmit="return validateQuestionForm()" action="updateQuestion">
                    <input type="hidden" name="question_id" value="<%= question.getQuestionId() %>">
                <input type="text" id="createtopic" name="topic" value="<%= question.getTopic() %>" />
                <textarea rows="5" name="content"><%= question.getContent() %></textarea>
                    <button type="submit" name="submit">Post</button>
            </form>
        </div>
        </div>
    </body>
</html>
