<%-- 
    Document   : index
    Created on : Nov 12, 2015, 2:18:23 AM
    Author     : Asus
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "style.css">
        <script src="jquery.min.js"></script>
        <script>
        
            function changetab(type) {
                window.open(type+".jsp", "_self");
            }
        </script>
        <title>Simple StackExchange</title>
    </head>
    <body>
        <div style = "width : 40%; margin : auto; text-align: center;">
            <h1><a href="index.jsp" id = "title">Simple StackExchange</a></h1>
            <div class = "tabbutton" style="background-color : silver;" onclick = "changetab('login')">Login</div><div class = "tabbutton" style="background-color : white;" onclick = "changetab('register')">Create New Account</div>
            <div id ="formaccount">
                <form method = "post" action = "register.jsp">
                    <br>
                    <%-- start web service invocation --%>
                    <%
                    try {
                        String Name = request.getParameter("name");
                        String Email = request.getParameter("email");
                        String Password = request.getParameter("password");
                        String Submitted = request.getParameter("submitted");
                        ws.register.RegisterWS_Service service = new ws.register.RegisterWS_Service();
                        ws.register.RegisterWS port = service.getRegisterWSPort();
                         // TODO initialize WS operation arguments here
                        java.lang.String name = Name;
                        java.lang.String email = Email;
                        java.lang.String password = Password;
                        java.lang.String registered = Submitted;
                        // TODO process result here
                        if (Submitted.equalsIgnoreCase("yes")) {
                            int result = port.register(name, email, password);
                            if (result == 1)
                                out.println("Registration successful!");
                            else
                                out.println("Registration unsuccessful. Please try again.");
                        }
                    } catch (Exception ex) {
                        // TODO handle custom exceptions here
                    }
                    %>
                    <%-- end web service invocation --%>
                    <table class = "borderless">
                        <tr><td class = "borderless">Name</td><td class = "borderless">:</td><td class = "borderless"><input type ="text" name = "name"></td></tr>
                        <tr><td class = "borderless">Email</td><td class = "borderless">:</td><td class = "borderless"><input type ="text" name = "email"></td></tr>
                        <tr><td class = "borderless">Password</td><td class = "borderless">:</td><td class = "borderless"><input type ="password" name = "password"></td></tr>
                    </table>
                    <br>
                    <input type ="hidden" name ="submitted" value ="yes">
                    <input type ="submit" value = "Register">
                </form>
            </div>
        </div>


    </body>
</html>
