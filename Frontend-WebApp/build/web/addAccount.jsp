<%-- 
    Document   : addAccount
    Created on : Nov 26, 2015, 4:52:27 PM
    Author     : nim_13512501
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
            
            String redir_url = null;
            String redir_label = null;
            String status = null;
            
            String Name = request.getParameter("Name");
            String Email = request.getParameter("Email");
            String Password = request.getParameter("Password");
            
            User.UserWS_Service serv = new User.UserWS_Service();
            User.UserWS port = serv.getUserWSPort();
            
            
            if (Name!=null && Email !=null && Password !=null ){
                status = port.insertUser(Email, Name, Password);
                
                //TODO display status if success
                

                redir_url="index.jsp";
                redir_label = "home";
            }
        %>
        
        Registration completed with status : <%=status%>
        
        <a href ="<%=redir_url%>">Go back to <%=redir_label%></a>
        
    </body>
</html>
