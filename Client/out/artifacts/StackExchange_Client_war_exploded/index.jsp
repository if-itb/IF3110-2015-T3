<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
					<html>
					<head>
					<title>My StackExchange</title>
					<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
					<link rel="stylesheet" type="text/css" href="css/style.css">
					</head>
					<body>
						<div class="container">
							<a class="homelink" href="http://mystackexchange.dev"><h1 id="title">My StackExchange</h1></a>
							<div class="search">
								<form action="search.php" method="post">
									<input type="text" id="searchbar" name="searchbar">
									<input type="submit" id="searchsubmit" value="Search">
								</form>
								<br>
								Can\'t find what you are looking for? <a href="ask.html">Ask here!</a>
							</div>
							<div class="content">
								<h2>Recently Asked Questions</h2>
								<hr>;
								<%
								//REQUEST QUESTION DARI WEB SERVICE
								//MENERIMA RESPONSE LIST QUESTIONS DARI WEB SERVICE
								//if RESPONSE tidak kosong {
								//foreach (RESPONSE) { %>
									<div class="stack">
									<div class="votes"><div>${param.votes}</div>Votes</div>
									<div class="answers"><div>${param.answers}</div>Answers</div>
									<div class="questiontitle"><a href="">${param.questiontitle}</a></div>
									<div class="detail">asked by <a class="linkname">${param.email}</a> | <a class="linkedit" href="">edit</a> | <a class="linkdelete" onclick="return validatedelete()" href="">delete</a></div>
									<hr>
								</div>}
								}
							</div>	
						</div>
						<script src="js/script.js"></script>
					</body>
					</html>;
