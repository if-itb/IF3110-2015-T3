<!DOCTYPE html>
<html>

<head><title>Ask question</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>


<body>
	<div class ='container'>
		<h1 class='center'>Simple StackExchange</h1>
		<div>&nbsp;</div>
		<h1>What's your question?</h1>
		<hr>

		<form name='askForm' action='AskQuestion' method = 'post'>
                        <input type="hidden" name="token" value="${token}">
			<input type='text' name='topic' class='formInput' placeholder='Question Topic'>
			<textarea name='content' class='formInput' placeholder='Content' rows='10'></textarea>
			<div class='right'><button type='submit'>Post</button></div>
		</form>
	</div>
	<script type='text/javascript' src='assets/js/validate.js'></script>
</body>
</html>