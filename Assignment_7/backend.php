<?php 

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "WT7";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}
echo "Connected successfully <br>";

$fname = $_POST["fname"];
$lname = $_POST["lname"];
$rnum = $_POST["rnum"];
$email = $_POST["email"];
$Action = $_POST["Sub_btn"];

if($Action == "Insert"){
    $sql = "INSERT INTO formInfo VALUES ('$fname', '$lname', '$rnum', '$email')";

    if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
    } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
    }
}
elseif($Action == "Update"){
    $updatedLname = "updated ".$lname;
    $sql = "UPDATE formInfo SET lname='$updatedLname' WHERE fname='$fname'";

    if ($conn->query($sql) === TRUE) {
      echo "Record updated successfully";
    } else {
      echo "Error updating record: " . $conn->error;
    }
}
elseif($Action == "Delete"){
    $sql = "DELETE FROM formInfo WHERE fname='$fname'";

    if ($conn->query($sql) === TRUE) {
      echo "Record deleted successfully";
    } else {
      echo "Error deleting record: " . $conn->error;
    }
}
elseif($Action == "Select"){
    $sql = "SELECT * FROM formInfo";
    $result = $conn->query($sql);
    
    // if ($result->num_rows > 0) {
    //   // output data of each row
    //   while($row = $result->fetch_assoc()) {
    //     echo "fname: " . $row["fname"]. " - lname: " . $row["lname"]. " - RollNumber: " . $row["rnum"]. " -email: ". $row["email"]. "<br>";
    //   }
    // } else {
    //   echo "0 results";
    // }

    if (mysqli_num_rows($result) > 0){
        while($row = $result->fetch_assoc()) {
        echo "fname: " . $row["fname"]. " - lname: " . $row["lname"]. " - RollNumber: " . $row["rollno"]. " -email: ". $row["email"]. "<br>";
      }
    }
}

$conn->close();

?>