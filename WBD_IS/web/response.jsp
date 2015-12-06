<%-- 
    Document   : response
    Created on : Nov 10, 2015, 4:23:21 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="mybean" scope="session" class="org.mypackage.hello.namehandler"/>
        <jsp:setProperty name="mybean" property="nama"/>
        <h1>Hello,<jsp:getProperty name="mybean" property="nama"/> !</h1>
    </body>
</html>
