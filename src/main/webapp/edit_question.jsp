<%-- 
    Document   : edit_question
    Created on : Nov 20, 2015, 10:37:51 AM
    Author     : user
--%>
<%@page import="com.mycompany.nasipadang.wsdl.StackExchange"%>
<%@page import="com.mycompany.nasipadang.wsdl.StackExchangeImplService"%>
<%
    StackExchangeImplService stackExchangeService = new StackExchangeImplService();
    StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
    int id = Integer.parseInt(request.getParameter("id"));
    id = stackExchange.editQuestion(id,(String) session.getAttribute("token"), request.getParameter("topic"), request.getParameter("content"));
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "question.jsp?id=" + id);
%>
