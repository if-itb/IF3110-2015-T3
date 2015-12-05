<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<jsp:include page="Header.jsp" flush="true">
		<jsp:param name="pageTitle" value="Simple StackExchange" />
		<jsp:param name="needDeleteQuestion" value="true" />
		<jsp:param name="check" value='1' />
	</jsp:include>
</head>

<body class="index">
	
	<div id="page-wrapper">
	
	<!-- Header -->
	<header id="header" class="alt">
		<h1 id="logo"><a href="index.jsp">Stack Exchange <span>| by Tusiri</span></a></h1>
		<jsp:include page="navigationbar.jsp" flush ="true"/>
	</header>
	
   <!-- Banner -->
	<section id="banner">
		<div class="inner">
			<header>
				<h2>STACK EXCHANGE</h2>
			</header>
			<p>This is <strong>STACK EXCHANGE</strong>, a place
			<br />
			where Tusiri is
			<br />
			better than Google</a>.</p>
			<footer>
				<ul class="buttons vertical">
					<li><a href="question_create.jsp" class="button fit scrolly">Ask a Question</a></li>
				</ul>
			</footer>
		</div>
	</section>
    
    <!-- Main -->
	<article id="main">
	<header class="special container">
		<span class="icon fa-bar-chart-o"></span>
		<h2>Hello, We are <strong>Tusiri</strong>. 
		<br />
		Feel Free to Use Our Web</h2>
		<p>Because it is <strong>Free</strong>
		<br />
		<h3>- Tusiri -</h3>
		<div class="search">
			<form action = "search.jsp" method="GET">
				<div class="row">
					<div class="8u 12u(mobile)">
						<input id = 'searchbar' type="text" name="search" placeholder="Search" />
					</div>
					<div class="4u 12u(mobile)">
						<input id = 'searchsubmit' type="submit"/>
					</div>
				</div>
			</form>
	        <p>Cannot find what you are looking for? <a href = "question_create.jsp">Ask here</a></p>
		</div>
	</header>
	
	<section class="wrapper style1 container">
	<!-- Content -->
		<div class="content">
			<section>
				<jsp:include page="question_list.jsp" flush ="true"/>
			</section>
		</div>
	</section>
	
    </article>
    <%@include file="footer.jsp" %>
	
    
</body>
</html>