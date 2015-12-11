<!DOCTYPE html>
<html>
	<?php 
		session_start();
		$_SESSION['token'] = 'lsohfpmv438lq13k2qlm63gufe$Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0$0:0:0:0:0:0:0:1$';
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