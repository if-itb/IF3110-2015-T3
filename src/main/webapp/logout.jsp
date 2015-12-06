<%-- 
    Document   : logout
    Created on : Nov 25, 2015, 4:26:16 PM
    Author     : user
--%>

<%
    session.invalidate();
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "index.jsp");
%>
