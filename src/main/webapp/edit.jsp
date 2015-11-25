<%-- 
    Document   : edit
    Created on : Nov 19, 2015, 4:08:09 AM
    Author     : user
--%>

<%@page import="org.wsdl.Question"%>
<%@page import="org.wsdl.StackExchangeImplService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<title>Simple StackExchange</title>
	<link rel="stylesheet" href="css/style.css" />
	<script src="js/validation.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            String id = request.getParameter("id");
            StackExchangeImplService stackExchangeService = new StackExchangeImplService();
            org.wsdl.StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
            int i = Integer.parseInt(id);
            String token = (String) session.getAttribute("token");
            String name = (String) session.getAttribute("name");
            Question question = stackExchange.getQuestion(token,i);
        %>
    </head>
    <body>
	<a href="index.jsp"><h1>Simple StackExchange</h1></a><br>
        <%if (name != null) { 
            out.println(name); %>
            <a href="logout.jsp">log out</a>
        <%}else{%>
            <a href="login_form.jsp">log in</a>
            <a href="reg.jsp">register</a>
        <%}%>
	<div class="list">
	<div class="title">Edit your question</div>
	<hr></hr>
	<form name="edit" method="post" action="edit_question.jsp">
            <input type="hidden" name="token" value="<%=token%>"><br>
            <input type="hidden" name="id" value="<%=request.getParameter("id")%>"><br>
            <input class="inputform" type="text" name="topic" placeholder="Question Topic" value="<%=question.getTopic()%>"><br>
            <textarea class="inputform" name="content" placeholder="Content" value="<%=question.getContent()%>"></textarea><br>
            <input type="submit" class="button" value="Update" onclick="return validateFormEdit()">
	</form>
	</div>
</body>
</html>
