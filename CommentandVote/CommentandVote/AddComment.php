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
    $token = $_GET["token"];
    $content = $_GET["content"];
    $username = $_GET["username"];
    $q_id = $_GET["q_id"];
    $id_c = $_GET["id_c"];
    $ipa  =$_GET["ipa"];
    $browser = $_GET["browser"];
    
    //Pertama cek token valid / expire
    
    
    //cek browser dan ip
    
    
    
    // add ke database
    echo($outp);

?>
