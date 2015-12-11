<?php
	include 'vote_comment.php';
	session_start();
	/*	format untuk post question http://localhost/StxVC?id=
		id yang dimaksud adalah id question

		format vote http://localhost/StxVC?cat=..&id=..&val=..
		cat = kategori, a untuk answer, q untuk question
		id = id answer/question
		val = nilai vote (1 atau -1)
	*/
	$uag = $_GET['user-agent'];
	$ip = $_GET['ip'];
	if(isset($_SESSION['token'])) {
			//post answer
			$token = $_SESSION['token'];
			if(isset($_POST['content']) && isset($_GET['id'])) {
			$q_id = $_GET['id'];
			$content = $_POST['content'];
			postComment($q_id, $token,$uag, $ip,$content);
		}

		//vote
		if(isset($_GET['id']) && isset($_GET['cat']) && isset($_GET['val'])) {
			$id = $_GET['id'];
			$category = $_GET['cat'];
			$value = $_GET['val'];
			if($category == 'a') {
				voteAnswer($id,$token,$uag, $ip,$value);
			} else if ($category == 'q') {
				voteQuestion($id,$token,$uag, $ip,$value);
			}
		}
	} else {
		header("HTTP/1.1 404 tidak ada token");
	}

?>
