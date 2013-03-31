<?php
$con = mysql_connect("<strežnik>","<up. ime>","<geslo>");
if (!$con) {
die('Could not connect: ' . mysql_error());
}
mysql_select_db("<baza>", $con);
mysql_set_charset('utf8',$con);
$author = urldecode($_POST['author']);
$message = urldecode($_POST['message']);
$date = date('Y-m-d H:i:s', time());
$longitude = $_POST['longitude'];
$latitude = $_POST['latitude'];
$query = "INSERT INTO mma_message (author, message, date, longitude, latitude) " .
"VALUES ('".$author."', '".$message."', '".$date."', '".$longitude."', '".$latitude."')";
$result = mysql_query($query);
echo $result;
mysql_close($con);
?>