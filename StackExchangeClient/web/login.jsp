<%@page import="java.util.*" %>
<%@page import="java.lang.Exception" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href='css/style.css'/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <base href="http://localhost:8080/StackExchange_Client/">

        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">    
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>    
        
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container">
            <% out.write("<a id='logo-container' href='index.jsp?token="+ request.getParameter("token") +"' class='brand-logo'>Home</a>");%>
            <ul class="right hide-on-med-and-down">
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
            </ul>

            <ul id="nav-mobile" class="side-nav">
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
            </ul>
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

        <div class="container">
            <br><br>
            <h1 class="header center orange-text">Login Here !</h1>
            <br><br>
        </div>
    
        <%
            /*Cookie[] cookArray = request.getCookies();
            out.println("Ada cookie sebanyak: "+ cookArray.length);
            for (int i = 0; i < cookArray.length; i++){
                out.println("COOKIE JSP: " + cookArray[i].getName());
            }*/
        %>
        <div class="row">
            <form id="loginForm" class="col s12" name="loginForm" action="" onsubmit="" method="POST">
              <div class="row">
                <div class="input-field col s12">
                  <input placeholder="Email" name="email" id="email" type="email" class="validate" required>
                  <label for="email" data-error="wrong" data-success="right">Email</label> 
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                  <input placeholder="Password" id="password" type="password" name="password" class="validate" required>
                  <label for="password">Password</label>
                </div>
              </div>
                  <input hidden name="user_agent"> 
                  <input hidden name="user_ipaddress">
              <button id = "button-post" class="btn waves-effect waves-light" type="submit" name="action">login
                <i class="material-icons right">send</i>
            </button>
            </form>
          </div>   
       
        <% 
            /*Cookie cookie = null;
            Cookie[] cookies = null;
            String access_token = null;
            
            
            
            if (cookies != null){
                for (int i=0; i< cookies.length; i++){
                   if (cookies[i].getName().equals("access_token")){
                        access_token = cookie.getValue();
                        break;
                    }
                }

                if (access_token != null && access_token.length() > 0){
                    //check access_token validity to server    */
        %>
        
        
        

        <script>
            //Fungsi java script untuk pengecekan Validitas Token 

            //Java script, JQuery, and AJAX Initialization
            /*function checkTokenValidity(){
                var token = {access_token: "<%//= access_token %>" };
                var url = "http://localhost:8082/StackExchange_IS/rest/tokenValidate";

                $.ajax({
                    url: url,
                    data: token,
                    dataType: "jsonp",
                    crossDomain: true,
                    type: "POST",
                    success: function(data){
                        var valid = data.valid;
                        if (valid == 1){
                            window.location.href = "index.jsp";
                        }
                        else if (valid == 0){
                            //regenerate Token and move to index.jsp

                        }
                    },
                    error: function(jqxhr,status,errorMsg){
                        alert(status + " : " + errorMsg);
                    }
                });
            }

            //Document ready function, java script function
            $(document).ready(function(){
                checkTokenValidity();
            });*/
        </script>
            
        <%
        /*    }
        }
        else{
            //out.println("Tidak ada cookies");
        }*/
        %>
        
        <script>
            function setCookie(cname, cvalue, exsecond) {
                var d = new Date();
                d.setTime(d.getTime() + (exsecond*1000));
                var expires = "expires="+d.toUTCString();
                document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";//coba gapake plus terakhir kalo gabisa
}           
            function getCookie(cname) {
                var name = cname + "=";
                var ca = document.cookie.split(';');
                for(var i=0; i<ca.length; i++) {
                    var c = ca[i];
                    while (c.charAt(0)==' ') c = c.substring(1);
                    if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
                }
                return "";
            }
            
            $(document).ready(function(){
                var url = "http://localhost:8082/StackExchange_IS/rest/token";
                $('#button-post').click(function(e){
                    e.preventDefault();
                    
                    var data = $('#loginForm').serialize();
                    console.log(data);
                    $.ajax({
                        url: url,
                        data: data,
                        crossDomain: true,
                        dataType: "json",
                        type: "POST",
                        success: function(data){
                            var token = data.access_token;
                            
                            //expired = null;
                            if (token == null){
                                //Username password gak sama
                                //window.location.href = "IVanWeteng.jsp"
                                alert("Invalid Username and Password");
                            }
                            else{
                                //Berhasil login
                                //window.location.href = "index.jsp";
                                //window.location.href = "http://localhost:8080/StackExchange_Client/index.jsp?token=" + token;
                                
                                setCookie("access_token", token, 2*60);
                                console.log("COOKIE: "+ getCookie("access_token"));
                                window.location.href = "http://localhost:8080/StackExchange_Client/index.jsp";
                            }

                        },
                        error: function(jqxhr,status,errorMsg){
                            out.println("AJAX Error");
                        }
                    });
                });
            });
        </script>
        
        <%
            //String token = "INITOKENPALSU";
            //if (token != "tokengagal"){
                //Cookie tokenCookie = new Cookie("tokenCookie", token);
                //tokenCookie.setMaxAge(2*60);//detik expirednya, = 120 detik = 2 menit
                //tokenCookie.setPath("/");
                //response.addCookie(tokenCookie);
            //}
        %>


    </body>
</html>
