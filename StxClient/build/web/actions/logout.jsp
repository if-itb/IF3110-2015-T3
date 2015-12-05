<%-- 
    Document   : logout
    Created on : Nov 27, 2015, Nov 27, 2015 8:15:40 PM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
        session.setAttribute("sessionName", null);
        session.setAttribute("sessionEmail", null);
        session.setAttribute("token", null);
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "../login.jsp");
%>
