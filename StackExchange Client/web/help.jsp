<%-- 
    Document   : help
    Created on : Dec 6, 2015, 10:06:57 AM
    Author     : acel
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        String url = "http://localhost:8082/IdentityServices/IdentityChecker";
        String token = "";
        Cookie[] cookies = request.getCookies();
        for(Cookie temp : cookies){
            if(temp.getName().equals("token")){
                token = temp.getValue();
            }
        }
        String id = request.getParameter("id");
        
        String link = url + "?token=" + token + "&id=" + id + "&ip=" + request.getRemoteHost();
        out.println(link + "<br>");
        StringBuffer response1 = new StringBuffer();
        String USER_AGENT = request.getHeader("User-Agent");
        URL obj = new URL(link);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                    response1.append(inputLine);
            }
            in.close();

        } catch (Exception e){

        }
        out.println(response1.toString());
        %>
    </body>
</html>
