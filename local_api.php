<?php 

 //database constants
 define('DB_HOST', 'localhost');
 define('DB_USER', 'root');
 define('DB_PASS', '');
 define('DB_NAME', 'student_iee_ihu');
 
 //connecting to database and getting the connection object
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
 
 //Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
	 echo "Failed to connect to MySQL: " . mysqli_connect_error();
	 die();
 }
 
 mysqli_set_charset($conn, "utf8");
 
 if(isset($_GET['table'])) {
	$table = $_GET['table'];
 }
 
switch($table) {
	case 'teachers':
		 //creating a query
		 $stmt = $conn->prepare("SELECT `id`, `name`, `email`, `personal_site`, `category`, `link` FROM `teachers`");
		 
		 //executing the query 
		 $stmt->execute();
		 
		 //binding results to the query 
		 $stmt->bind_result($id, $name, $email, $personal_site, $category, $link);
		 
		 $results = array(); 
		 
		 //traversing through all the result 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['name'] = $name; 
			 $temp['email'] = $email; 
			 $temp['personal_site'] = $personal_site; 
			 $temp['category'] = $category; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'courses':
		 $stmt = $conn->prepare("SELECT `id`, `title`, `semester`, `teachers`, `link` FROM `courses`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title, $semester, $teachers, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title'] = $title; 
			 $temp['semester'] = $semester; 
			 $temp['teachers'] = $teachers; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'offers':
		 $stmt = $conn->prepare("SELECT `id`, `title`, `description`, `category`, `link` FROM `offers`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title, $description, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title'] = $title; 
			 $temp['description'] = $description; 
			 $temp['category'] = $category; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'communities':
		 $stmt = $conn->prepare("SELECT `id`, `title`, `category`, `link` FROM `communities`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title, $description, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title'] = $title; 
			 $temp['category'] = $category; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'maps':
		 $stmt = $conn->prepare("SELECT `id`, `latitude`, `longitude`, `name`, `description` FROM `maps`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title, $description, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['latitude'] = $latitude; 
			 $temp['longitude'] = $longitude; 
			 $temp['name'] = $name; 
			 $temp['description'] = $description; 
			 array_push($results, $temp);
		 }
		break;
	case 'official_services':
		 $stmt = $conn->prepare("SELECT `id`, `title`, `description`, `category`, `link` FROM `official_services`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title, $description, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title'] = $title; 
			 $temp['description'] = $description; 
			 $temp['category'] = $category; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'unofficial_services':
		 $stmt = $conn->prepare("SELECT `id`, `title`, `description`, `creator`, `category`, `link` FROM `unofficial_services`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title, $description, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title'] = $title; 
			 $temp['description'] = $description; 
			 $temp['creator'] = $creator; 
			 $temp['category'] = $category; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'useful_websites':
		 $stmt = $conn->prepare("SELECT `id`, `title`, `description`, `category`, `link` FROM `useful_websites`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title, $description, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title'] = $title; 
			 $temp['description'] = $description; 
			 $temp['category'] = $category; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	default:
	// Default
}
 
 //displaying the result in json format 
 echo json_encode($results);