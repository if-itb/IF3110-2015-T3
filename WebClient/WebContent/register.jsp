<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
   <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>StackExchange Register Form</title>

       <jsp:include page="signin_header.jsp" flush ="true"/>
		<script src="assets/js/validator.js"></script>
	
	</head>
    <body>
    <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><a href="index.jsp"><strong>StackExchange</strong></a> Register Form</h1>
                            <div class="description">
                            	<p>
	                            	Welcome to StackExchange!
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Sign up to our site</h3>
                            		<p>It's free and anyone can join</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form id="registerForm" onsubmit='validate_Register()' action = "register_post"  METHOD = "POST">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">Fullname</label>
			                        	<input type="text" name="fullname" placeholder="Fullname..." class="form-username form-control" id="form-username" required ="true">
			                        </div>
			                        <div class="form-group">
			                    		<label class="sr-only" for="form-username">Username</label>
			                        	<input type="text" name="username" placeholder="Username..." class="form-username form-control" id="form-username" required ="true">
			                        </div>
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">Email</label>
			                        	<input type="email" name="email" placeholder="Email..." class="form-username form-control" id="form-username" required ="true">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">Password</label>
			                        	<input type="password" name="password" placeholder="Password..." class="form-password form-control" id="form-password" required ="true">
			                        </div>
			                        <button type="submit" class="btn">Sign up!</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>