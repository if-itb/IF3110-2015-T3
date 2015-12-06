<%--
  Created by IntelliJ IDEA.
  User: elvan_owen
  Date: 11/11/15
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>Simple StackExchange</title>
    <style>
        <%@ include file="/assets/css/ask.css"%>
    </style>
</head>
<body>
<header>
    Simple StackExchange
</header>
<section id='ask'>
    <div class='title'>
        What's Your Question ?
    </div>
    <hr>
    <form id='question-form'>
        <div>
            <input id='name' type='text' placeholder='Name' required>
        </div>
        <div>
            <input id='email' type='email' placeholder='Email' required>
        </div>
        <div>
            <input id='topic' type='text' placeholder='Question Topic' required>
        </div>
        <div>
            <textarea id='content' placeholder='Content' rows='10' required></textarea>
        </div>
        <div>
            <div>
                <a href='#' class='btn post-btn'>Post</a>
            </div>
        </div>
    </form>
</section>
<script>
    <%@ include file="/assets/js/ask.js"%>
</script>
</body>
</html>