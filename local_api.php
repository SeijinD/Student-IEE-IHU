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
		 $stmt = $conn->prepare("SELECT `id`, `name_gr`, `name_en`, `email`, `personal_site`, `category_gr`, `category_en`, `link` FROM `teachers`");
		 
		 //executing the query 
		 $stmt->execute();
		 
		 //binding results to the query 
		 $stmt->bind_result($id, $name_gr, $name_en, $email, $personal_site, $category_gr, $category_en, $link);
		 
		 $results = array(); 
		 
		 //traversing through all the result 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['name_gr'] = $name_gr; 
			 $temp['name_en'] = $name_en; 
			 $temp['email'] = $email; 
			 $temp['personal_site'] = $personal_site; 
			 $temp['category_gr'] = $category_gr; 
			 $temp['category_en'] = $category_en;
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'courses':
		 $stmt = $conn->prepare("SELECT `id`, `titlee_gr`, `titlee_en`, `semester`, `teachers_gr`, `teachers_en`, `link` FROM `courses`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title_gr, $title_en, $semester, $teachers_gr, $teachers_en, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title_gr'] = $title_gr; 
			 $temp['title_en'] = $title_en; 
			 $temp['semester'] = $semester; 
			 $temp['teachers_gr'] = $teachers_gr; 
			 $temp['teachers_en'] = $teachers_en; 
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
		 $stmt = $conn->prepare("SELECT `id`, `title_gr`, `title_en`, `category`, `link` FROM `communities`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title_gr, $title_en, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title_gr'] = $title_gr; 
			 $temp['title_en'] = $title_en; 
			 $temp['category'] = $category; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'maps':
		 $stmt = $conn->prepare("SELECT `id`, `latitude`, `longitude`, `title_gr`, `title_en`, `description_gr`, `description_en` FROM `maps`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $latitude, $longitude, $title_gr, $title_en, $description_gr, $description_en);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['latitude'] = $latitude; 
			 $temp['longitude'] = $longitude; 
			 $temp['title_gr'] = $title_gr; 
			 $temp['title_en'] = $title_en;
			 $temp['description_gr'] = $description_gr; 
			 $temp['description_en'] = $description_en; 
			 array_push($results, $temp);
		 }
		break;
	case 'official_services':
		 $stmt = $conn->prepare("SELECT `id`, `title_gr`, `title_en`, `description_gr`, `description_en`, `category`, `link` FROM `official_services`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title_gr, $title_en, $description_gr, $description_en, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title_gr'] = $title_gr; 
			 $temp['title_en'] = $title_en;
			 $temp['description_gr'] = $description_gr; 
			 $temp['description_en'] = $description_en;  
			 $temp['category'] = $category; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'unofficial_services':
		 $stmt = $conn->prepare("SELECT `id`, `title_gr`, `title_en`, `description_gr`, `description_en`, `creator_gr`, `creator_en`, `category`, `link` FROM `unofficial_services`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title_gr, $title_en, $description_gr, $description_en, $creator_gr, $creator_en, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title_gr'] = $title_gr; 
			 $temp['title_en'] = $title_en;
			 $temp['description_gr'] = $description_gr; 
			 $temp['description_en'] = $description_en; 
			 $temp['creator_gr'] = $creator_gr;
			 $temp['creator_en'] = $creator_en; 
			 $temp['category'] = $category; 
			 $temp['link'] = $link; 
			 array_push($results, $temp);
		 }
		break;
	case 'useful_websites':
		 $stmt = $conn->prepare("SELECT `id`, `title_gr`, `title_en`, `description_gr`, `description_en`, `category`, `link` FROM `useful_websites`");
		 
		 $stmt->execute();
		 
		 $stmt->bind_result($id, $title_gr, $title_en, $description_gr, $description_en, $category, $link);
		 
		 $results = array(); 
		 
		 while($stmt->fetch()){
			 $temp = array();
			 $temp['id'] = $id; 
			 $temp['title_gr'] = $title_gr; 
			 $temp['title_en'] = $title_en;
			 $temp['description_gr'] = $description_gr; 
			 $temp['description_en'] = $description_en; 
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