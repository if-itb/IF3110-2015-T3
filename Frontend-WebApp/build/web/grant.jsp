<%-- 
    Document   : grant
    Created on : Nov 26, 2015, 5:06:57 PM
    Author     : nim_13512501
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.StringReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.net.URL"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="javax.xml.bind.JAXBContext"%>
<%@page import="javax.xml.bind.JAXBException"%>
<%@page import="javax.xml.bind.annotation.XmlRootElement"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="javax.xml.parsers.ParserConfigurationException"%>
<%@page import="javax.xml.xpath.XPath"%>
<%@page import="javax.xml.xpath.XPathExpressionException"%>
<%@page import="javax.xml.xpath.XPathFactory"%>
<%@page import="java.io.DataOutputStream"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>grant</title>
    </head>
    <body>
        <%
            String uri ="http://localhost:8082/Identity_Service/Login";
            URL url = new URL(uri);
            HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/xml");
            
            connection.setDoOutput(true);
            
            String Email = request.getParameter("Email");
            String Password = request.getParameter("Password");
            
            String urlParameters = "Email="+Email+"&AccountPassword="+Password;
            
            
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            String result = null;
            String access_token = null;
            int lifetime = 0;

            switch(connection.getResponseCode()){
                case HttpURLConnection.HTTP_CREATED:
                    InputStream xmlstream = connection.getInputStream();
                    StringBuilder inputStringBuilder = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(xmlstream, "UTF-8"));
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse(xmlstream);
                    result =  connection.getResponseMessage();
                    access_token = document.getElementsByTagName("access_token").item(0).getTextContent();
                    lifetime = Integer.parseInt(document.getElementsByTagName("lifetime").item(0).getTextContent());
            
                    session.setAttribute("access_token", access_token);
                    session.setAttribute("lifetime", lifetime);
                    session.setAttribute("start", Calendar.getInstance());
        
                default:
                    result = connection.getResponseMessage();
            }
            
        %>
        access_token : <%=access_token%><br>
        lifetime : <%=lifetime%><br>
        result : <%=result%><br>
        <a href="index.jsp"> Home</a>
    </body>
</html>
