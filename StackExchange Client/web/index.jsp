<%-- 
    Document   : index
    Created on : Nov 15, 2015, 8:43:46 PM
    Author     : Tifani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
   // New location to be redirected
   String site = new String("home");
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", site); 
%>