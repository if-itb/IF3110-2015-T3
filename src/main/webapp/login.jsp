<%-- 
    Document   : login
    Created on : Nov 14, 2015, 7:34:51 AM
    Author     : theaolivia
--%>

<%@page import="org.wsdl.StackExchangeImplService"%>
<%
    StackExchangeImplService stackExchangeService = new StackExchangeImplService();
    org.wsdl.StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
    String token = stackExchange.login(request.getParameter("email"), request.getParameter("password"));
    //out.println(token);
    if(!token.equals("fail") && token != null){
        request.setAttribute("token", token);
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "index.jsp");
    }
%>
