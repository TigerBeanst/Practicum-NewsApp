<?php
/*
*用户注册，服务器进行的处理
*/
include("conn.php");
mysqli_select_db($conn, 'api_jakting');
$data = json_decode(file_get_contents('php://input'), true);
$sql = mysqli_query($conn, "SELECT * FROM user WHERE username ='" . $data["username"] . "'");
$result = mysqli_fetch_assoc($sql);
if (!empty($result)) {
    //存在该用户
    $back['status'] = "-1";
    $back['info'] = "user already exists";
    echo(json_encode($back));

} else {
    //不存在该用户，可以注册
    $sqll = "INSERT INTO user (username, password,name)VALUES ('" . $data["username"] . "','" . $data["password"] . "','" . $data["name"] . "');";
    $retval = mysqli_query($conn, $sqll) or die($sqll);
    $sql = mysqli_query($conn, "SELECT * FROM user WHERE username ='" . $data["username"] . "'");
    $result = mysqli_fetch_assoc($sql);
    $back['status'] = "1";
    $back['info'] = "user not exist,reg finish";
    $back['id'] = intval($result['userid']);
    echo(json_encode($back));
}

mysqli_close($conn);
?>