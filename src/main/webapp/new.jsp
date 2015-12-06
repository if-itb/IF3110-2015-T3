<%-- 
    Document   : new
    Created on : Nov 19, 2015, 4:10:35 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExchange</title>
	<link rel="stylesheet" href="css/style.css" />
	<script src="js/validation.js"></script>
    </head>
    <body>
        <a href="index.jsp"><h1>Simple StackExchange</h1></a><br>
        <%if (session.getAttribute("name") != null) { 
            out.println(session.getAttribute("name")); %>
            <a href="logout.jsp">log out</a>
        <%}else{%>
            <a href="login_form.jsp">log in</a>
            <a href="reg.jsp">register</a>
        <%}%>
	<div class="list">
	<div class="title">What's your question?</div>
	<hr></hr>
        <%if (session.getAttribute("name") != null) {%>
        <form name="new" method="post" action="add_question.jsp">
            <input class="inputform" type="hidden" name="token" value="<%=session.getAttribute("token")%>"><br>
		<input class="inputform" type="text" name="topic" placeholder="Question Topic"><br>
		<textarea class="inputform" name="content" placeholder="Content" rows="5"></textarea><br>
		<input type="submit" class="button" value="Post" onclick="return validateFormNew()">
	</form>
        <%}else{%>
            <a href="login_form.jsp">log in</a>
            <a href="reg.jsp">register</a>
        <%}%>
	</div>
    </body>
</html>
