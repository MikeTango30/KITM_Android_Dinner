<?php
	$username="epiz_25248079";
	$password="oBRlyGZB0bS3Gg";
	$servername="sql304.epizy.com";
	$database="epiz_25248079_dinner";

    	//Create connection
	$conn = new mysqli ($servername, $username, $password, $database);

	//Check connection
	if ($conn->connect_error){
		die("Connection failed: " . $conn->connect_error);
	}

	$veiksmas = $_POST['action'];
	if (!strcmp("insert", $veiksmas)) {
		$dinner_type = $_POST['dinner_type'];
		$delivery = $_POST['delivery'];
		$price = $_POST['price'];
		$payment = $_POST['payment'];

		$sql = "INSERT INTO dinner (dinner_type, delivery, price, payment) VALUES ('$dinner_type', '$delivery', '$price', '$payment')";
		if($conn->query($sql) === TRUE){
			echo "New entry created successfully";
		}else{
			echo "Error creating new entry " . $sql . "<br>" . $conn ->error;
		}
	}

    if (isset($_POST['searchQuery'])) {

            $search = $_POST['searchQuery'];

            $sql = "SELECT * FROM dinner WHERE dinner_type LIKE '%$search%'";

            $res = $conn->query($sql);

            $result = array();
                while($row = $res->fetch_assoc()) {
                    $result[] = $row;
                }
                echo json_encode($result);


            /*if($res === TRUE){
                //41-45 eilutes
            }else{
                echo "no row ".$sql;
            }*/
        }

	$conn->close();
?>