<?php
error_reporting(E_ALL & ~E_NOTICE & ~E_DEPRECATED);
$mysql_url = "localhost";
$mysql_user = "api_jakting";
$mysql_pw =  "123456";
$mysql_db = "api_jakting";
$conn = mysqli_connect($mysql_url, $mysql_user, $mysql_pw) or die("数据库服务器连接错误" . mysqli_error());
mysqli_query($conn, "SET NAMES 'utf8'");
?>