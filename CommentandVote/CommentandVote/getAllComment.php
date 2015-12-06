<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

DEFINE('HOST','localhost');
DEFINE('NAME','stack');
DEFINE('USERNAME','root');
DEFINE('PASSWORD','');
  
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
$conn = mysqli_connect(HOST, USERNAME,PASSWORD,NAME);


  
    global $conn;
    $id = $_GET["id"];
    $sql = "SELECT * FROM comment WHERE q_id=$id";
    $result = mysqli_query($conn, $sql);
    $outp = "";
    while($rs = $result->fetch_array(MYSQLI_ASSOC)) {
        if ($outp != "") {$outp .= ",";}
        $outp .= '{"id_c":"'  . $rs["id_c"] . '",';
        $outp .= '"q_id":"'   . $rs["q_id"]        . '",';
         $outp .= '"content":"'   . $rs["content"]        . '",';
          $outp .= '"date":"'   . $rs["date"]        . '",';
        $outp .= '"username":"'. $rs["username"]     . '"}';
    }
    $outp ='{"records":['.$outp.']}';
    $conn->close();

    echo($outp);

?>
