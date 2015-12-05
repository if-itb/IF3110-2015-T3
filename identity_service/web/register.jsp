<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Register</title>

  <link rel="stylesheet" type="text/css" href="/assets/css/base.css">
  <link rel="stylesheet" type="text/css" href="/assets/css/main.css">
</head>
  <body>
    <div class="container">
      <h1 class="text-center">Simple StackExchange</h1>

      <a href="/login">Back</a>
      <h2>Register new user</h2>
      <hr>
      <c:if test="${flash != null}">
        <h4 style="color:red">Email has been taken, please input another email</h4>
      </c:if>

      <form method="POST" id="thread-form">
        <input id="username" type="text" class="form" placeholder="Name" name="name">
        <input id="email" type="text" class="form" placeholder="Email" name="email">
        <input id="password" type="password" class="form" placeholder="password" name="password">

        <div class="text-right">
          <button class="btn" type="submit">Create</button>
        </div>
      </form>
    </div>
  </body>
</html>
