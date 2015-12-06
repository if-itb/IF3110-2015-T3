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
	if(isset($_SESSION['token'])) {
			//post answer
			$token = $_SESSION['token'];
			if(isset($_POST['content']) && isset($_GET['id'])) {
			$q_id = $_GET['id'];
			$content = $_POST['content'];
			$email = get_Email_From_Token($token);
			if($email != 404 && $email != 402) {
				echo $email;
				if($email != null) {
					postAnswer($q_id, $email,$content);
				}
			}
		}
		
		//vote
		if(isset($_GET['id']) && isset($_GET['cat']) && isset($_GET['val'])) {
			$id = $_GET['id'];
			$category = $_GET['cat'];
			$value = $_GET['val'];
			$email = get_Email_From_Token($token);
			if($email != 404 && $email != 402) {
				if($category == 'a') {
					voteAnswer($id,$email,$value);
				} else if ($category == 'q') {
					voteQuestion($id,$email,$value);
				}
			}
		}
	}
	
?>