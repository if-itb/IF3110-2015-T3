<%@page import="stackexchange.webservice.User"%>
<%@page import="stackexchange.webservice.UserWS"%>
<%@page import="stackexchange.webservice.UserWS_Service"%>
<?php header('Access-Control-Allow-Origin: *'); ?>
<html>
	<head>
		<title>StackExchange</title>

		<link href="css/style.css" rel="stylesheet">
		<!-- Link berikut hanya merupakan import library icon -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

	</head>
    <body ng-app="commentApp">
	<div class="container-brand">
		<div class="container-br-in">
			<a href="../">
				<div class="menu hvr-radial-out">
					<i class="material-icons md-18">menu</i>
				</div>
			</a>
                    
                        <%
                            String agent;
                            if(request.getHeader("User-Agent").toLowerCase().contains("chrome/")){
                                agent = "chrome";
                            }else if(request.getHeader("User-Agent").toLowerCase().contains("firefox/")){
                                agent = "firefox";
                            }else if(request.getHeader("User-Agent").toLowerCase().contains("safari/")){
                                agent = "safari";
                            }else{
                                agent = "unknown";
                            }
                            
                            Cookie[] cookies;
                            cookies = request.getCookies();
                            String token = "", email = "", browser="";
                            boolean isTokenEx = false, isEmailEx = false, isValidAgent=true;
                            if (cookies != null) {
                                for (Cookie cookie: cookies) {
                                    if (cookie.getName().equals("token")) {
                                        isTokenEx = true;
                                        token = cookie.getValue();
                                        if(!cookie.getValue().contains(agent)){
                                            Cookie tokCook = new Cookie("token", "");
                                            Cookie emaCook = new Cookie("email", "");
                                            tokCook.setMaxAge(0);
                                            emaCook.setMaxAge(0);
                                            response.addCookie(tokCook);
                                            response.addCookie(emaCook);
                                            isValidAgent = false;
                                        }else{
                                            isValidAgent = true;
                                        }
                                    }else if(cookie.getName().equals("email")){
                                        isEmailEx = true;
                                        email = cookie.getValue();
                                    }
                                }
                            }
                            
                            UserWS_Service userService = new UserWS_Service();
                            UserWS port = userService.getUserWSPort();
                            User user = port.getUser(email, token);
                            String newToken = port.getToken();
                            if(!newToken.equals("")){
                                Cookie tokCook = new Cookie("token", newToken);
                                Cookie emaCook = new Cookie("email", "test");
                                tokCook.setMaxAge(60*35);
                                emaCook.setMaxAge(60*35);
                                response.addCookie(tokCook);
                                response.addCookie(emaCook);
                            }
                            if(user.getId()>-1&&isValidAgent){
                            %>
                        <nav class="ut-nav">
                                <div class="nav-menu askhere">
					<a href="logout"><h4>Logout</h4></a>
				</div>
				<div class="nav-menu">
					<a href="#"><h4>Hi, <span><%= user.getName() %></span></h4></a>
				</div>
				<div class="nav-menu askhere">
					<a href="questionedit"><h4>Ask Here</h4></a>
				</div>
			</nav>   
                            <% }else{ %>
                        <nav class="ut-nav">
                                <div class="nav-menu">
					<a href="signIn"><h4><span>Sign In</span></h4></a>
				</div>
			</nav>    
                            <% } %>
                          
                        
                        <div>
				<a href="home"><h1 class="brand">Stack<span>Exchange</span></h1></a>
			</div>
		</div>
	</div>
	<div class="inner" ng-controller="voteCtrl">