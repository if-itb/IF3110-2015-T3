<%-- 
    Document   : ask
    Created on : Nov 16, 2015, 11:23:02 PM
    Author     : chairuniaulianusapati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href='css/style.css'/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">    
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>    
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container">
                
            <% 
                //out.write("<a id='logo-container' href='index.jsp?token="+ request.getParameter("token") +"' class='brand-logo'>Home</a>");
                out.write("<a id='logo-container' href='index.jsp' class='brand-logo'>Home</a>");
            %>
            <%
                Cookie[] cookieArray = request.getCookies();
                String theCookie = "null";
                if (cookieArray != null){
                    for (int i = 0; i < cookieArray.length; i++){
                        if(cookieArray[i].getName().equals("access_token")){
                            theCookie = cookieArray[i].getValue();
                            break;
                        }
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
                    String border = "<ul class='right hide-on-med-and-down'>"
                                        + "<li>"+ result + "</li>"
                                        + "<li><a href='signout.jsp'>Sign Out</a></li>"
                                    + " </ul>" + 
                                    "<ul id='nav-mobile' class='side-nav'>"
                                        + "<li>" + result + "</li>"
                                        + "<li><a href='signout.jsp'>Sign Out</a></li>"
                                    + " </ul>";
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
        <div class="container">
            <br><br>
            <h1 class="header center orange-text">Ask here!</h1>
            <br><br>
        </div>

        <div class="row">
            <%out.write("<form class='col s12' name='loginForm' action='createQuestion.jsp' onsubmit='' method='POST'>");%>
              <input type="hidden" name="question_id">
              <div class="row">
                <div class="input-field col s12">
                  <input placeholder="The question topic goes here..." name="topic" id="topic" type="text" required>
                  <label for="topic" data-error="wrong" data-success="right">Question Topic</label> 
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                   <textarea id="textarea1" class="materialize-textarea" placeholder="The content goes here..." name="content" type="text" required></textarea>
                   <label for="textarea1" data-error="wrong" data-success="right">Content</label>
                </div>
              </div>
              <button class="btn waves-effect waves-light" type="submit" name="action">ask
                <i class="material-icons right">send</i>
            </button>
            </form>
         </div>
        
    
    </body>
</html>



