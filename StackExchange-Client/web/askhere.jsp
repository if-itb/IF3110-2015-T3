<%@page import="stackexchange.webservice.Question"%>
<%@ include file = "header.jsp" %>
<jsp:useBean id="question" type="Question" scope="request"/>
<jsp:useBean id="state" type="String" scope="request"/>
<jsp:useBean id="withanswer" type="String" scope="request"/>
<%
    String action="",topic="",content="";
    
    if(state.equals("edit")){
        action = "editquestion?withanswer="+withanswer+"&id="+question.getId();
        topic = question.getTopic();
        content = question.getContent();
    } else if(state.equals("ask")){
        action = "addquestion";
        topic = "";
        content = "";
    }

%>
		<h1 class="tag">Question</h1>

                <form onsubmit="return validasi(this);" class="form makeQuestion" method="post" action=<%= action %>>
			<div class="innerForm">
                            <input class="textForm" type="text" placeholder="Topic" name="topic" value="<%= topic %>">
			</div>
			<div class="innerForm">
				<textarea rows="10" class="textArea" name="content" placeholder="Insert your question here.." ><%= content %></textarea>
			</div>
			<div class="innerForm">
				<input class="submitButton" type="submit" placeholder="Send Ask" value="Send Question">
			</div>
		</form>

<%@ include file = "footer.jsp" %>