<%-- 
    Document   : registerresult
    Created on : Nov 17, 2015, 4:50:12 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%-- start web service invocation --%><hr/>
    <%
    try {
	com.wbd.rgs.RegisterWS_Service service = new com.wbd.rgs.RegisterWS_Service();
	com.wbd.rgs.RegisterWS port = service.getRegisterWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String nama = request.getParameter("name");
	java.lang.String email = request.getParameter("email");
	java.lang.String password = request.getParameter("password");
	// TODO process result here
	int result = port.register(nama, email, password);
        if (result == 1){
            out.println("Registration SUCCESS!");
        } else {
            out.println("Registration FAILED!");
        }
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    
    String site = "index.jsp?token=" + request.getParameter("token");
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
    %>   
    </body>
</html>
