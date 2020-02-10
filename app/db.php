<?php
$servername = "localhost";
$username = "id12537668_dinner";
$password = "dinner";
$dbname = "id12537668_dinner";
//Create connection
$conn = new mysqli ($servername, $username, $password, $dbname);
//Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if (isset($_POST['insert'])) {
    $type = $_POST['type'];
    $delivery = $_POST['delivery'];
    $price = $_POST['price'];
    $payment = $_POST['payment'];

    $sql = "INSERT INTO dinners(`type`, `delivery`, `price`, `payment`} VALUES(`$type`, `$delivery`, `$price`, `$payment`)";

    if ($conn->query($sql) === TRUE) {
        echo "New record created successfuly";
    } else {
        echo "Error: " . $sql . "<or>" . $conn->error;
    }
}

$conn->close();