<%-- 
    Document   : signout
    Created on : Nov 30, 2015, 10:04:09 PM
    Author     : nim_13512501
--%>

<%@page import="java.io.DataOutputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signing Out...</title>
    </head>
    <body>
        <h1>Signing Out...</h1>
        <%
            String uri ="http://localhost:8082/Identity_Service/Login";
            URL url = new URL(uri);
            HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);
            
            String urlParameters = "access_token="+(String)session.getAttribute("access_token");
            
            
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            
            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
            session.removeAttribute("access_token");
            session.removeAttribute("start");
            session.removeAttribute("lifetime");
            
            response.sendRedirect("index.jsp");
            
        %>
    </body>
</html>
