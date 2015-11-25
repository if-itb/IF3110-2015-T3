<%-- 
    Document   : add_question
    Created on : Nov 20, 2015, 10:10:38 AM
    Author     : user
--%>

<%@page import="org.wsdl.StackExchangeImplService"%>
<%
    StackExchangeImplService stackExchangeService = new StackExchangeImplService();
    org.wsdl.StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
    int id = stackExchange.addQuestion((String) session.getAttribute("token"), request.getParameter("topic"), request.getParameter("content"));
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "question.jsp?id=" + id);
%>
