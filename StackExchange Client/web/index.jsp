<%-- 
    Document   : index
    Created on : Nov 9, 2015, 5:27:23 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page = "layout/header.jsp" flush = "true"/>
<jsp:useBean id="questions" type="java.util.List<QuestionWS.Question>" scope="request" /> 
<jsp:useBean id="questions_vote_counts" type="java.util.HashMap<Integer, Integer>" scope="request" /> 
<jsp:useBean id="answer_counts" type="java.util.HashMap<Integer, Integer>" scope="request" /> 
<jsp:useBean id="question_asker" type="java.util.HashMap<Integer, String>" scope="request" /> 

    <%
        Cookie[] cookies = null;
        cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("access_token")) {
                    token = cookie.getValue();
                }
            }
        }
    %>
    <%-- start web service invocation --%>
    <%
    UserWS.User user = null;
    try {
	UserWS.UserWS_Service service = new UserWS.UserWS_Service();
	UserWS.UserWS port = service.getUserWSPort();
	
	user = port.getUserByToken(token);
	
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%>
    
    <form action="" method="GET" id="searchForm">
        <input type="text" placeholder="Search...">
        <input type="submit" value="Search">
    </form>

    <p class="ask-here">Can't find what you are looking for? <a href="${pageContext.request.contextPath}/ask">Ask here</a></p>


    <div class="row">
        <div class="secondary-title col-10 col-push-1">
            <h2>Recently Asked Questions</h2>
        </div>
    </div>
        
    <div class="question-list">
        <% for(QuestionWS.Question question: questions) { %>
        <div class="inner-container">
            <div class="question">
                <div class="row">
                    <div class="question-status col-3">
                        <div class="question-vote">
                            <div class="status-counts">
                                <span><%= questions_vote_counts.get(question.getId()) %></span>
                            </div>
                            <div class="status-title">
                                <span>vote</span>
                            </div>
                        </div>
                        <div class="question-answers">
                            <div class="status-counts">
                                <span><%= answer_counts.get(question.getId()) %></span>
                            </div>
                            <div class="status-title">
                                <span>answer</span>
                            </div>
                        </div>
                    </div>
                    <div class="question-summary col-9">
                        <h2 class="question-title">
                            <a href="question?id=<%= question.getId() %>"><%= question.getTopic() %></a>
                        </h2>
                        <p><%= question.getContent().replaceAll("(\r\n|\n\r|\r|\n)", "<br />") %></p>
                    </div>
                    <div class="question-meta">
                        <span>
                            Asked by
                            <%= question_asker.get(question.getId()) %> 
                            <% if ( ( user != null ) && (user.getName()).equals(question_asker.get(question.getId())) ) { %>
                            |
                            <a href="${pageContext.request.contextPath}/questioneditor?id=<%= question.getId() %>" class="question-edit">Edit</a> |
                            <form method="POST" action="deletequestion" id="deleteForm_question<%= question.getId() %>" class="delete-form">
                                <input type="hidden" value="<%= question.getId() %>" name="id_question">
                                <input type="hidden" value="<%= user != null ? user.getId() : 0 %>" name="id_user">
                                <input type="submit" value="Delete" class="form-delete">
                            </form>
                            <% } %>
                        </span>
                    </div>
                </div> <!-- .row -->
            </div> <!-- .question -->
        </div> <!-- .inner-container -->
        <% } %>
        
    </div> <!-- .question-list -->

<jsp:include page = "layout/footer.jsp" flush = "true"/>