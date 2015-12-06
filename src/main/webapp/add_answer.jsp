<%-- 
    Document   : add_answer
    Created on : Nov 20, 2015, 10:44:35 AM
    Author     : user
--%>

<%@page import="org.wsdl.StackExchangeImplService"%>
<%
    StackExchangeImplService stackExchangeService = new StackExchangeImplService();
    org.wsdl.StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
    int id = Integer.parseInt(request.getParameter("id"));
    if(stackExchange.addAnswer(id, (String) session.getAttribute("token"), request.getParameter("content"))){
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "question.jsp?id=" + id);
    }
%>
