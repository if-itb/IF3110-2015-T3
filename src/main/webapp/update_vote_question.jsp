<%-- 
    Document   : update_vote_question
    Created on : Nov 20, 2015, 10:53:19 AM
    Author     : user
--%>

<%@page import="com.mycompany.nasipadang.wsdl.StackExchange"%>
<%@page import="com.mycompany.nasipadang.wsdl.StackExchangeImplService"%>
<%
    StackExchangeImplService stackExchangeService = new StackExchangeImplService();
    StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
    int id = Integer.parseInt(request.getParameter("id"));
    int vote = Integer.parseInt(request.getParameter("vote"));
    int votes = stackExchange.updateVoteQuestion((String)session.getAttribute("token"),id, vote);
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "question.jsp?id=" + id);
%>