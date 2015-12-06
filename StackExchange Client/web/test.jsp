<%-- 
    Document   : test
    Created on : Dec 6, 2015, 10:17:17 AM
    Author     : William Sentosa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h1>Hello World!</h1>
    <%= request.getParameter("result") %>
  </body>
</html>
