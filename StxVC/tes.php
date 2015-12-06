<!DOCTYPE html>
<html>
	<?php 
		session_start();
		$_SESSION['token'] = '6b874c66bb08d0f3f3e7ed37c9dc8b5f17739af0';
	?>
	<h5>Test Post Answr</h5>
	<form action = "index.php?id=5" method = 'POST'>
		<input type = 'text' name = 'content' placeholder="Konten" maxlength = '140'></input></br>
		<input type = 'submit' value = "Submit"></input>
	</form>
	<br></br>
	<h5>Test vote </h5>
	<form action = "index.php" method = 'GET'>
		<input type = 'text' name = 'id' placeholder="id" maxlength = '10'></input></br>
		<input type = 'text' name = 'cat' placeholder="kategori" maxlength = '1'></input></br>
		<input type = 'text' name = 'val' placeholder="nilai" maxlength = '1'></input>
		<input type = 'submit' value = "Vote"></input>
	</form>
	
</html>