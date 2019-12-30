<?php
/*
*用户登录，服务器进行的处理
*/
include("conn.php");
mysqli_select_db($conn, 'api_jakting');
$data = json_decode(file_get_contents('php://input'), true);
$sql = mysqli_query($conn, "SELECT * FROM user WHERE username ='" . $data["username"] . "'");
$result = mysqli_fetch_assoc($sql);
//存在该用户
//用户名密码匹配正确
$sqll = "SELECT * FROM user WHERE username ='" . $data["username"] . "'";
$retval = mysqli_query($conn, $sqll) or die($sqll);
$retval = mysqli_fetch_array($retval, MYSQLI_NUM);
//print_r($retval);
$back['status'] = "1";
$back['info'] = "login success";
$back['name'] = $retval[3];
$back['like_count'] = $retval[4];
$back['star_count'] = $retval[5];
$back['star'] = $retval[6];
echo(json_encode($back));

mysqli_close($conn);
?>