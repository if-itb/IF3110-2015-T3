<%-- 
    Document   : update_vote_answer
    Created on : Nov 20, 2015, 10:57:48 AM
    Author     : user
--%>

<%@page import="com.mycompany.nasipadang.wsdl.StackExchange"%>
<%@page import="com.mycompany.nasipadang.wsdl.StackExchangeImplService"%>
<%
    StackExchangeImplService stackExchangeService = new StackExchangeImplService();
    StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
    int id = Integer.parseInt(request.getParameter("id"));
    int id_answer = Integer.parseInt(request.getParameter("id_answer"));
    int vote = Integer.parseInt(request.getParameter("vote"));
    int votes = stackExchange.updateVoteAnswer((String)session.getAttribute("token"), id_answer, vote);
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "question.jsp?id=" + id);
%>
