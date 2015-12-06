<%-- 
    Document   : error
    Created on : Nov 25, 2015, 4:41:23 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">    
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>    
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container">
                
            <% out.write("<a id='logo-container' href='index.jsp' class='brand-logo'>Home</a>");%>
            <%
                Cookie[] cookieArray = request.getCookies();
                String theCookie = null;
                for (int i = 0; i < cookieArray.length; i++){
                    if(cookieArray[i].getName().equals("access_token")){
                        theCookie = cookieArray[i].getValue();
                        break;
                    }
                }
                if (theCookie.equals("null")){
                    String border = "<ul class='right hide-on-med-and-down'>"
                                        + "<li><a href='login.jsp'>Login</a></li>"
                                        + "<li><a href='register.jsp'>Register</a></li>"
                                    + " </ul>" + 
                                    "<ul id='nav-mobile' class='side-nav'>"
                                        + "<li><a href='login.jsp'>Login</a></li>"
                                        + "<li><a href='register.jsp'>Register</a></li>"
                                    + " </ul>";
                    out.write(border);
                } else {
                    com.wbd.rgs.RegisterWS_Service service = new com.wbd.rgs.RegisterWS_Service();
                    com.wbd.rgs.RegisterWS port = service.getRegisterWSPort();
                    java.lang.String accessToken = theCookie;
                    java.lang.String result = port.getUsername(accessToken);
                    String border;
                    if (request.getParameter("id").equals("-2")){
                         border = "<ul class='right hide-on-med-and-down'>"
                                        + "<li><a href='login.jsp'>Login</a></li>"
                                        + "<li><a href='register.jsp'>Register</a></li>"
                                    + " </ul>" + 
                                    "<ul id='nav-mobile' class='side-nav'>"
                                        + "<li><a href='login.jsp'>Login</a></li>"
                                        + "<li><a href='register.jsp'>Register</a></li>"
                                    + " </ul>";
                    } else {
                    border = "<ul class='right hide-on-med-and-down'>"
                                        + "<li>" + result + "</li>"
                                        + "<li><a href='index.jsp?token=null'>Sign Out</a></li>" //Jelek, ntar diganti
                                    + " </ul>" + 
                                    "<ul id='nav-mobile' class='side-nav'>"
                                        + "<li>" + result + "</li>"
                                        + "<li><a href='index.jsp?token=null'>Sign Out</a></li>" //Jelek, ntar diganti
                                    + " </ul>";
                    }
                    out.write(border);
                }
            %>
            <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
            </div>
        </nav>   
        <script>
            $(document).ready(function(){

              // Initialize collapse button
              $(".button-collapse").sideNav();
            });
        </script>
        
                 <title>Simple StackExchange</title>
    </head>
    <body>
    <%
            if (request.getParameter("id").equals("-1")){
                out.println("<h2 class='header center orange-text'>Oops! You're not logged in yet!<br><br></h2><h4 class='center orange-text'>Please login or register</h4>");
            } else if (request.getParameter("id").equals("-2")){
                out.println("<h2 class='header center orange-text'>Oops! Your session has expired!<br><br></h2><h4 class='center orange-text'>Please login again</h4>");
            } else if (request.getParameter("id").equals("3")){
                out.println("<h2 class='header center orange-text'>Oops! You're not authorized to access that!</h4>");
            } else if (request.getParameter("id").equals("11")){
                out.println("<h2 class='header center orange-text'>Something went wrong! :( Check the Server Log</h4>");
            }
       
    %>

    </body>
</html>
