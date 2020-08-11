<?php

require_once 'connection.php';


$username = @$_GET['username'];
$email   = @$_GET['email'];
$phone   = @$_GET['phone'];
$password = @$_GET['password'];


$query_check = "SELECT email FROM users WHERE email = '$email' AND password = '$password' ";

$result = @mysqli_query($connection , $query_check);

if(@mysqli_num_rows($result)>0){
	
	$key = "USER_LOGIN";
	
	
}else{
	
		$key = "NO_ACCOUNT";
		
	}	
		
		echo json_encode(array("response"=>$key));
		
		mysqli_close($connection);

?>

