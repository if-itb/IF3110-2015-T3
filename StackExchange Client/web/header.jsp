<%-- 
    Document   : header
    Created on : Dec 5, 2015, 4:16:31 PM
    Author     : acel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
        if(request.getParameter("id") != null){
        %>
        <h1 class="center">
            <a href="index.jsp?id=<%=request.getParameter("id")%>">Simple StackExchange</a>
        </h1>
        <%
        } else {
        %>
        <h1 class="center">
            <a href="index.jsp">Simple StackExchange</a>
        </h1>
        <%
        }
        %>
    </body>
</html>
