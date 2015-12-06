<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>Ask Your Question</title>
    <link href='https://fonts.googleapis.com/css?family=Josefin+Slab:400,700italic,300' rel='stylesheet' type='text/css'>
    <link rel='stylesheet' href='styles/main.css'>
</head>
<body>
<div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>
<div class='container'>
    <h2>Edit your question!</h2>
    <form name='questionForm'  action='editQuestion' method='POST'>
        <input type='text' id='qtopic' name='qtopic' placeholder='Question Topic' value='<c:out value="${question.getQtopic()}"/>'/>
        <textarea  id='qcontent' name='qcontent' placeholder='Content' ><c:out value="${question.getQcontent()}"/></textarea>
        <input type='hidden' name='idEdited' value='<c:out value="${question.getQid()}"/>'/>
        <input type='hidden' name='fromDetail' value='<c:out value="${fromDetail}"/>'/>
        <button  id='submitBtn' class='submitBtn' >Edit</button>
    </form>
</div>