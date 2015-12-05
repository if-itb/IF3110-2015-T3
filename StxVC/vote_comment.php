<?php
	include('parser.php');	
	function postAnswer($q_id, $email,$content) {
		require("config.php");
		$sql = "SELECT name from user where email = '".$email."'";
		$result = $conn->query($sql);
		$name;
		if($result == true) {
			$row = $result->fetch_assoc(); 
			$name = $row["name"];
			$sql = "INSERT into answer VALUES('','".$q_id."','".$name."','".$email."','".$content."',0)";
			$conn->query($sql);
		}
		
	}
	
	function voteQuestion($id, $email, $value) {
		require("config.php");
		//cek apakah sudah vote atau belum
		$sql = "SELECT id_mail from uservote where id_mail = '".$email."' and category = q";
		$result = $conn->query($sql);
		if($result == false) {
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
		} else {
			echo "sudah ada";
		}
	}

	function voteAnswer($id, $email, $value) {
		require("config.php");
		//cek apakah sudah vote atau belum
		$sql = "SELECT id_mail from uservote where id_mail = '".$email."' and category = a";
		$result = $conn->query($sql);
		if($result == false) {
			//ambil current vote
			$sql = "SELECT vote from answer where id = '".$id."'";
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
			$sql = "INSERT INTO uservote VALUES('".$email."','a','".$id."')";
			$conn->query($sql);
		} else {
			echo "sudah ada";
		}
	}
	
	function get_Email_From_Token($token) {
		$url = 'http://localhost:8082/StxIS1/Handler?token='+$token;
		$respon_code =  getResposeCode($url);
		if($respon_code == 200) { //OK
			$xml_obj = simplexml_load_file($url);
			$email = parseTokenEmail($xml_obj);
			return $email;
		} else {
			return $respon_code;
		}
	}
?>