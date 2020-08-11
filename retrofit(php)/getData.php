<?php

require_once 'connection.php';

$query = " SELECT * FROM books ";

$result = mysqli_query($connection,$query);

if($result){
	
	$response = array();
	
	while($row = mysqli_fetch_array($result)){
		
		$books = array();
		
		$books['id'] = $row['id'];  
		$books['name'] = $row['name'];  
		$books['author'] = $row['author'];  
		$books['genre'] = $row['genre'];  
		$books['published'] = $row['published'];  
		$books['description'] = $row['description'];  
		$books['price'] = $row['price'];  
		$books['link_img'] = $row['link_img'];  
		
		array_push($response,$books);
		
	}
	
}else {
	
	echo "Failed";
	
}

echo (json_encode($response));



?>