
		<?php		
                   class Comment {
                        public $UID = "";
                        public $CON  = "";
                        
                     }
		$servername = "localhost";
		$username = "root";
		$password = "";
		$dbname = "stackexchange";
                $qid = $_GET['id'];
		// Create connection
		$conn = mysqli_connect($servername, $username, $password, $dbname);

		// Check connection
		if (!$conn) {
		    die("Post failed, please resubmit your question.");
		}
		
			//SQL Query		
		$sql ="select * from comment where id_question=".$qid;
		$result = mysqli_query($conn, $sql);		                
                $a = array();
                $b = array();
		if (mysqli_num_rows($result) > 0) {
		    // output data of each row                    
		    while($row = mysqli_fetch_assoc($result)) {			
                        $e = new Comment();                        
                        $e->CON = $row['content'];
                        $sql = "select username from user where id=".$row['id_user'];
                        $aresult = mysqli_query($conn, $sql);	
                        $arow = mysqli_fetch_assoc($aresult);
                        $e->UID = $arow['username'];
                        array_push($a,$e);
		    }
		} else {		    
		}
                header('Access-Control-Allow-Origin: *');
                $b = array("comments" => $a);
                echo json_encode($b);
                
		mysqli_close($conn);
		?>	

