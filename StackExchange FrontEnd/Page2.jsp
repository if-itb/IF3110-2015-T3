<!DOCTYPE html>

<html>

	<head>
		<link rel="stylesheet" type="text/css" href="wbd.css">
		<script type="text/javascript" src="wbd.js"></script>
		<title>
			Page 2 - TuBes WBD
		</title>
	</head>

	<body>
	
		<div class="font30">
			<h1>
				<a class="no-text-decoration" href="Page1.jsp">
					Simple StackExchange
				</a>
			</h1>
		</div>		
			<br>
			
		<div class="judulform text-left">
				What's your question ?
		</div>

		<br>
		
		<form name="questionForm" action="insertQuestion.php" method="post" onsubmit="return validateQue()">
			 <div class="text-left">
					 <input class="form-textbox" type="text" name="name" placeholder="Name"><br><br>
					 <input class="form-textbox" type="text" name="email" placeholder="Email"><br><br>
					 <input class="form-textbox" type="text" name="topic" placeholder="Question Topic"><br><br>
					 <textarea name="question" placeholder="Content"></textarea><br><br>
			 </div>
		
			 <div class="text-right">
				 <input class="form-submit" type="submit" name="post" value="Post">
			 </div>
		 </form>
	</body>
	
</html>		