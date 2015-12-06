<%-- 
    Document   : ask
    Created on : 09-Nov-2015, 17:17:40
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" flush="true"/>
    <form action="" method="GET" id="searchForm">
        <input type="text" placeholder="Search...">
        <input type="submit" value="Search">
    </form>

    <p class="ask-here">Can't find what you are looking for? <a href="${pageContext.request.contextPath}/ask">Ask here</a></p>

    <div class="inner-container">

        <div class="question-header">
            <h1>What's your question?</h1>
        </div>

        <div class="row">
            <div class="col-12">

                <div class="form-wrapper">
                    <form id="askForm" action="newquestion" method="POST">
                        
                        <div class="form-field">
                            <label for="title">Question Topic</label>
                            <input id="title" name="title" type="text" placeholder="Question Topic">
                        </div>

                        <div class="form-field">
                            <label for="content">Content</label>
                            <textarea name="content" placeholder="Your question content goes here"></textarea>
                        </div>
                        
                        <input type="submit" value="Ask"/>
                    </form>
                </div>

            </div> <!-- .col -->
        </div> <!-- .row -->
        
    </div> <!-- .inner-container -->
<jsp:include page="layout/footer.jsp" flush="true"/>
