<%-- 
    Document   : create_question
    Created on : Nov 18, 2015, 11:11:58 AM
    Author     : Fitra Rahmamuliani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" flush="true"/>
            <div class="subq black">What's your question?</div>
            <form class="inputform" name="createquestion" method="post" onsubmit="return validateQuestionForm()" action="addQuestion">
                <input type="text" id="createtopic" name="topic" placeholder="Question Topic" />
                <textarea placeholder="Content" rows="5" name="content"></textarea>
                    <button type="submit" name="submit">Post</button>
            </form>
        </div>
        </div>
    </body>
</html>
