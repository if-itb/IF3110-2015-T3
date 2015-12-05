<%-- 
    Document   : sign-in
    Created on : Nov 27, 2015, Nov 27, 2015 11:18:47 PM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Xml.InformationToken"%>
<%@page import="Xml.XmlResponse"%>
<!DOCTYPE html>
<%
	 // TODO initialize WS operation arguments here
        session.setAttribute("token", null);
        session.setAttribute("sessionName", null);
        session.setAttribute("sessionEmail", null);
	java.lang.String nama = "";
	java.lang.String email = request.getParameter("Email");
	java.lang.String password = request.getParameter("Password");

        userWS.UserWS_Service service = new userWS.UserWS_Service();
        userWS.UserWS port = service.getUserWSPort();
         // TODO initialize WS operation arguments here
        // TODO process result here
        nama = port.getName(email);

    

	// TODO process result here
        String url = "http://localhost:8082/StxIS1/Handler";
        String urlParameter = "email="+email+"&pass="+password;
        InformationToken it = new InformationToken();
        String token = it.getToken(url, urlParameter);

        if(it.getStatus()) {
            session.setAttribute("token", token);
            session.setAttribute("sessionName", nama);
            session.setAttribute("sessionEmail", email);
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "../index.jsp");
        } else {
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "../login.jsp");
        }
        
%>
