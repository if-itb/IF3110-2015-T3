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
            /*$(document).ready(function(){
                $("#tablogin").click(function(){
                    $("#tablogin").css("background-color", "white");
                    $("#tabreg").css("background-color", "silver");
                });
                $("#tabreg").click(function(){
                    $("#tablogin").css("background-color", "silver");
                    $("#tabreg").css("background-color", "white");
                });
            });*/
            /*$(document).ready(function(){
                $("#loginform").submit(function(e) {
                    e.preventDefault();
                    $.support.cors = true;
                    var credential = { Email: $("#emailtext").val(), Password: $("#password").val() };
                    $.ajax({
                        url: 'http://localhost:8001/Identity/LoginRSServlet',
                        type: 'POST',
                        data: JSON.stringify(credential),
                        ontentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        async: false,
                        crossDomain: true,
                        success: function(msg) {
                            alert("msg");
                        },
                        error: function(xhr, status, errMsg) {
                            //window.location.href = "http://localhost:8001/Identity/LoginRSServlet";
                            console.log(errMsg);
                            alert("sesuatu");
                        }
                    });
                    alert(JSON.stringify(credential));
                    
                });
            });*/
            /*function login(email, password) {
                var req = new XMLHttpRequest();
                var body = JSON.stringify({ Email: email, Password: password });

                if ('withCredentials' in req) {
                    req.open('GET', 'http://updates.html5rocks.com', true);
                    req.setRequestHeader('Content-Type', 'application/json');
                    req.onreadystatechange = function() {
                        if (req.readyState === 4) {
                            console.log(req.responseText);
                        }
                    };
                    req.send();
                }/*
                var createCORSRequest = function(method, url) {
                    var xhr = new XMLHttpRequest();
                    if ("withCredentials" in xhr) {
                        // Most browsers.
                        xhr.open(method, url, true);
                    
                    } else {
                        // CORS not supported.
                        xhr = null;
                    }
                    return xhr;
                };

              var url = 'http://server.cors-api.appspot.com/server?id=8710499&enable=true&status=200&credentials=false';
              var method = 'GET';
              var xhr = createCORSRequest(method, url);

              xhr.onload = function() {
                // Success code goes here.
                console.log(xhr.responseText);
              };

              xhr.onerror = function() {
                // Error code goes here.
                console.log(xhr.responseText);
              };

              xhr.send();
            }
            */
            function changetab(type) {
                /*var xhttp;
                if (window.XMLHttpRequest) {
                    xhttp = new XMLHttpRequest();
                    } else {
                    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xhttp.onreadystatechange = function() {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("formaccount").innerHTML = xhttp.responseText;
                    }
                }
                if (type == "login")
                    xhttp.open("GET", "login.html", true);
                else
                    xhttp.open("GET", "reg.html", true);
                xhttp.send();*/
                window.open(type+".jsp", "_self");
            }
        </script>
        <title>Simple StackExchange</title>
    </head>
    <body>
        <div style = "width : 40%; margin : auto; text-align: center;">
            <h1><a href="index.jsp" id = "title">Simple StackExchange</a></h1>
            <div class = "tabbutton" style="background-color : white;" onclick = "changetab('login')">Login</div><div class = "tabbutton" style="background-color : silver;" onclick = "changetab('register')">Create New Account</div>
            <div id ="formaccount">
                <%
                    try {
                        int valid = Integer.valueOf(request.getParameter("valid"));
                        if (valid == 0) {
                            out.println("<br>Login unsuccessful. Username and password combination is incorrect.");
                        }
                        else {
                            response.sendRedirect("http://localhost:8000/FrontEnd/index.jsp");
                        }
                        
                    } catch (Exception ex) {
                        
                    }
                    if (request.getParameter("relog")!= null){
                        out.println("<br>Session expired. You need to relog again.");
                    }
                %>
                <form id = "loginform" method = "post" action = "http://localhost:8001/Identity/LoginRSServlet">
                    <br>
                    <table class = "borderless">
                        <tr><td class = "borderless">Email</td><td class = "borderless">:</td><td class = "borderless"><input type ="text" name = "email" id = "emailtext"></td></tr>
                        <tr><td class = "borderless">Password</td><td class = "borderless">:</td><td class = "borderless"><input type ="password" name = "password" id = "password"></td></tr>
                    </table>
                    <br>
                    <input type ="hidden" name ="submitted" value ="yes">
                    <input type ="submit" value = "Login">
                </form>
            </div>
        </div>
    </body>
</html>
