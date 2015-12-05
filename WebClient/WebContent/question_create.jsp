<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    	pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" flush="true">
		<jsp:param name="pageTitle" value="Ask A Question" />
		<jsp:param name="check" value="3" />
	</jsp:include>
</head>

<body class="contact noscript">
	<div id="page-wrapper">
	<!-- Header -->
	<header id="header">
		<h1 id="logo"><a href="index">Stack Exchange <span>| by Tusiri</span></a></h1>
		<jsp:include page="navigationbar.jsp" flush ="true"/>
	</header>
	
	<article id="main">
		<header class="special container">
			<span class="icon fa-question-circle"></span>
			<strong><h2>Create Question</h2></strong>
			<p>Fill the form below to ask your question.</p>
		</header>
		<!-- One -->
		<section class="wrapper style4 special container 75%">
		<!-- Content -->
			<div class="content">
				<form name="sentMessage" id="contactForm" action="question_create_post.jsp" METHOD="POST">
					<div class="control-group form-group">
						<div class="controls">
							<strong><label class="questionmenu">Topic:</label></strong>
                            <input type="text" class="form-control" name="topic" required data-validation-required-message="Please enter your topic">
                            <p class="help-block"></p>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<strong><label class="questionmenu">Question:</label></strong>
                           	<textarea rows="10" cols="100" class="form-control" name="content" required maxlength="999" style="resize:none"></textarea>
						</div>
					</div>
					
					<div class="row">
						<div class="12u">
							<ul class="buttons">
								<li><input type="submit" class="special" value="Ask Question" /></li>
							</ul>
						</div>
					</div>
				</form>
			</div>
		</section>
	</article>
	<%@include file="footer.jsp" %>
    </div>
</body>
<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>
</html>