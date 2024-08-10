<?php
$password="";
$database="kct";
$username="root";
$server="localhost";

// Create connection.
$conn = new mysqli($server, $username, $password, $database);

// Check connection.
if ($conn->connect_error) {
    die("Connection failed:" . $conn -> connect_error);
}

$sql = "SELECT * FROM student";
$result = $conn->query($sql);

// Encoding data in json format.
$json = array();

if($result->num_rows > 0){
    while ($row = $result->fetch_array()){
        $json["data"][] = array(
         "id" => $row["id"],
         "name" => $row["name"],
         "address" => $row["address"],
        );
    }
}

echo json_encode($json);
$conn->close();
?>
