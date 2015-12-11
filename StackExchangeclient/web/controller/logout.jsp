<%-- 
    Document   : logout
    Created on : Nov 18, 2015, 8:59:20 PM
    Author     : Luqman A. Siswanto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  Cookie cookie = new Cookie("auth", "overflow48");
  cookie.setMaxAge(0);
  cookie.setPath("/");
  response.addCookie(cookie);
  String url = "../";
  response.sendRedirect(url);
%>