<?php
	include 'parser.php';

	function postComment($q_id, $token, $uag, $ip,$content) {
		require'config.php';
		$res = get_Email_From_Token($token,$uag, $ip);
		if($res != 404 && $res != 402) {
			$email = $res;
			$sql = "SELECT name from user where email = '".$email."'";
			$result = $conn->query($sql);
			$name;
			if($result == true) {
				$row = $result->fetch_assoc();
				$name = $row["name"];
				$sql = "INSERT into comment VALUES('','".$q_id."','".$name."','".$email."','".$content."',0)";
				$conn->query($sql);
				$conn->close();
			}
		}
	}

	function voteQuestion($id, $token, $uag, $ip, $value) {
		require'config.php';
		$res = get_Email_From_Token($token,$uag, $ip);
		if($res != 404 && $res != 402) {
			$email = $res;
			//cek apakah sudah vote atau belum
			$sql = "SELECT id_mail from uservote where id_mail = '".$email."' and category = 'q' and id = '".$id."'";
			$result = $conn->query($sql);

			if($result->num_rows == 0) {
				//ambil current vote
				$sql = "SELECT vote from question where id = '".$id."'";
				$result = $result = $conn->query($sql);
				$current_vote = 0;
				if ($row = $result->fetch_assoc()) {
					$current_vote = $row["vote"];
				}
				$newVote = $current_vote + $value;
				//update vote
				$sql = "UPDATE question SET vote = '".$newVote."' where id = '".$id."'";
				$conn->query($sql);
				//insert to uservote
				$sql = "INSERT INTO uservote VALUES('".$email."','q','".$id."')";
				$conn->query($sql);
				header("Content-type: text/xml; charset=utf-8");
				$response = '<?xml version="1.0" encoding="utf-8"?>';
				$response .= '<root><vote>'.$newVote.'</vote></root>';
				echo $response;
				$conn->close();
			} else {
				$sql = "SELECT vote from question where id = '".$id."'";
				$result = $result = $conn->query($sql);
				$current_vote = 0;
				if ($row = $result->fetch_assoc()) {
					$current_vote = $row["vote"];
				}
				header("Content-type: text/xml; charset=utf-8");
				$response = '<?xml version="1.0" encoding="utf-8"?>';
				$response .= '<root><vote>'.$current_vote.'</vote></root>';
				echo $response;
				$conn->close();
			}

		}
	}

	function voteAnswer($id, $token, $uag, $ip, $value) {
		require'config.php';
		$res = get_Email_From_Token($token,$uag,$ip);
		if($res != 404 && $res != 402) {
			$email = $res;
			//cek apakah sudah vote atau belum
			$sql = "SELECT * FROM `uservote` WHERE id_mail = '".$email."' and category = 'a' and id = '".$id."'";
			$result = $conn->query($sql);
			if($result->num_rows == 0) {
				//ambil current vote
				$sql = "SELECT vote from answer where id = '".$id."'";
				$result = $result = $conn->query($sql);
				$current_vote = 0;
				if ($row = $result->fetch_assoc()) {
					$current_vote = $row["vote"];
				}
				$newVote = $current_vote + $value;
				//update vote
				$sql = "UPDATE answer SET vote = '".$newVote."' where id = '".$id."'";
				$conn->query($sql);
				//insert to uservote
				$sql = "INSERT INTO uservote VALUES('".$email."','a','".$id."')";
				$conn->query($sql);
				header("Content-type: text/xml; charset=utf-8");
				$response = '<?xml version="1.0" encoding="utf-8"?>';
				$response .= '<root><vote>'.$newVote.'</vote></root>';
				echo $response;
				$conn->close();
			} else {
				$sql = "SELECT vote from answer where id = '".$id."'";
				$result = $result = $conn->query($sql);
				$current_vote = 0;
				if ($row = $result->fetch_assoc()) {
					$current_vote = $row["vote"];
				}
				header("Content-type: text/xml; charset=utf-8");
				$response = '<?xml version="1.0" encoding="utf-8"?>';
				$response .= '<root><vote>'.$current_vote.'</vote></root>';
				echo $response;
				$conn->close();
			}
		} else {
			header("HTTP/1.1 201 Sudah vote");
		}
	}

	function get_Email_From_Token($token, $uag, $ip) {
		//$_SERVER['HTTP_USER_AGENT'];
		//header("user-agent:hahahahah");
		$url = 'http://localhost:8080/StxIS/Handler?user-agent='.urlencode($uag).'&ip='.$ip.'&token='.$token;
		
		$respon_code = getResponseCode($url);

		if($respon_code == 200) { //OK
			$xml_obj = simplexml_load_file($url);
			$email = parseTokenEmail($xml_obj);
			return $email;
		} else {
			header("HTTP/1.1 ".$respon_code." ".getResponsePhrase($url));
			echo "<h1>HTTP/1.1 ".$respon_code." ".getResponsePhrase($url)."</h1>";
			return $respon_code;
		}
	}
?>
