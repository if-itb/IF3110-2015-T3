<%-- 
    Document   : checkregister
    Created on : Nov 26, 2015, 12:40:39 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            checkregister - TuBes WBD
        </title>
    </head>
    
    
    <body>
    
        <%String u = request.getParameter("username");%>
        <%String e = request.getParameter("email");%>
        <%String p = request.getParameter("password");%>
        
            <%-- start web service invocation --%><hr/>
            <%
                try {
                    
                    out.println(u);
                    out.println(e);
                    out.println(p);
                    usermodel.UserWS_Service service = new usermodel.UserWS_Service();
                    usermodel.UserWS port = service.getUserWSPort();
                     // TODO initialize WS operation arguments here
                    java.lang.String name = u;
                    java.lang.String email = e;
                    java.lang.String password = p;
                    // TODO process result here
                    int result = port.register(u,e,p);
                    out.println(result);
                    if (result==1)
                    {
                        response.sendRedirect("http://localhost:8082/WBD_IS/login.jsp?msg=registerberhasil");
                    }
                    else
                    {
                        response.sendRedirect("http://localhost:8080/StackExchangeFE/register.jsp?msg=registergagal");
                    }
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
            %>
            <%-- end web service invocation --%><hr/>

        
    </body>
</html>
