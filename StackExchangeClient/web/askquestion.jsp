<%-- 
    Document   : AskQuestion
    Created on : Nov 25, 2015, 1:44:34 PM
    Author     : mochamadtry
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="includes/header.jsp" %>

<div class="container">

    <div id="header">
    	<a href="/StackExchangeClient/home"><h1>StackExchange</h1></a>
  	</div>

    <div class="main">
		<div class="wrapper" id="question-form">
			<div class="content-header">
				<h2>What's your question?</h2>
			</div>
			<div class="child-content">
				<form action="/StackExchangeClient/askquestion" method="POST" id="the-form">
					
					<input type="text" name="topic" placeholder="Question Topic" id="topic" required> <br>
                    <textarea name="content" placeholder="Content" id="content" required> </textarea> <br>
					<input type="submit" value="Post" name="post" id="post">
				</form>
			</div>
		</div>
	</div>
                
</div>

</body>
</html>
