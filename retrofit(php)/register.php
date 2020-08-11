<?php


require_once 'connection.php';


$username = @$_GET['username'];
$email   = @$_GET['email'];
$phone   = @$_GET['phone'];
$password = @$_GET['password'];


$query_check = "SELECT * FROM users WHERE email = '$email' ";

$result = @mysqli_query($connection , $query_check);

if(@mysqli_num_rows($result)>0){
	
	$key = "USER_REGISTER";
	
	
}else{
	
	$query_register = "INSERT INTO users (username , email , phone , password) VALUES ('$username' , '$email' , '$phone' , '$password') ";
	
	
	if(mysqli_query($connection,$query_register)){
		
	
		$key = "SUCCESS";
		
	}else{
		
		
		$key = "WRONG";
		
	}	
}
		
		echo json_encode(array("response"=>$key));
		
		mysqli_close($connection);
		


?>