<?php
/*
*用户登录，服务器进行的处理
*/
include("conn.php");
mysqli_select_db($conn, 'api_jakting');
$data = json_decode(file_get_contents('php://input'), true);
$sqll = "INSERT INTO hjt_comment (url,author,comment_content)VALUES ('".$data["url"]."','".$data["author"]."','".$data["comment_content"]."');";
$retval = mysqli_query($conn, $sqll) or die($sqll);
echo "1";

mysqli_close($conn);
?>