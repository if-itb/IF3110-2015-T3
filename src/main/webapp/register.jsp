<%-- 
    Document   : register
    Created on : Nov 20, 2015, 3:01:04 PM
    Author     : user
--%>

<%@page import="org.wsdl.StackExchangeImplService"%>
<%
    StackExchangeImplService stackExchangeService = new StackExchangeImplService();
    org.wsdl.StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
    if(stackExchange.register(request.getParameter("username"), request.getParameter("email"), request.getParameter("password"))){
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "login_form.jsp");
    }
%>
