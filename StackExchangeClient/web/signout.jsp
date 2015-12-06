<%-- 
    Document   : signout
    Created on : Nov 26, 2015, 5:28:10 PM
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
    <%
    try {
        Cookie[] cookieArray = request.getCookies();
                String theCookie = null;
                for (int i = 0; i < cookieArray.length; i++){
                    if(cookieArray[i].getName().equals("access_token")){
                        theCookie = cookieArray[i].getValue();
                        break;
                    }
                }
	com.wbd.rgs.RegisterWS_Service service = new com.wbd.rgs.RegisterWS_Service();
	com.wbd.rgs.RegisterWS port = service.getRegisterWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = theCookie;
	// TODO process result here
        out.println("Hello WOrld");
	int result = port.signout(accessToken);
	out.println("Result = "+result);
        if (result == 1){
            String site = "index.jsp?token=null";
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        }
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>

    </body>
</html>
