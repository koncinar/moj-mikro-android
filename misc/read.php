<?php
$con = mysql_connect("<strežnik>","<up. ime>","<geslo>");
if (!$con) {
die('Could not connect: ' . mysql_error());
}
mysql_select_db("<baza>", $con);
mysql_set_charset('utf8',$con);
$result = mysql_query("SELECT * FROM mma_message ORDER BY date DESC LIMIT 0,
100");
$rows = array();
while($row = mysql_fetch_assoc($result)) {
$clear_row = array('author'=>$row['author'],
'message'=>$row['message'],
'date'=>$row['date'],
'longitude'=>$row['longitude'],
'latitude'=>$row['latitude']);
array_push($rows, $clear_row);
}
echo json_encode($rows);
mysql_close($con);
?>