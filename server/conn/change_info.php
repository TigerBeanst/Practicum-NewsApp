<?php
/*
*用户登录，服务器进行的处理
*/
include("conn.php");
mysqli_select_db($conn, 'api_jakting');
$data = json_decode(file_get_contents('php://input'), true);
$sql = mysqli_query($conn, "SELECT * FROM user WHERE userid =".$data["id"]);
if($data["password"]==""){
    //不修改密码
    $sqll = "UPDATE user SET name='".$data["name"]."' WHERE userid =".$data["id"];
    $retval = mysqli_query($conn, $sqll) or die($sqll);
    echo "Change Name OK.";
}else{
    $sqll = "UPDATE user SET name='".$data["name"]."', password='".$data["password"]."' WHERE userid = ".$data["id"];
    //echo $sqll;
    $retval = mysqli_query($conn, $sqll) or die($sqll);
    echo "Change Name And Password OK.";
}
$result = mysqli_fetch_assoc($sql);
$sqll = "UPDATE hjt_comment SET author='".$data["name"]."' WHERE author ='".$result['name']."'";
//echo $sqll;
$retval = mysqli_query($conn, $sqll) or die($sqll);
mysqli_close($conn);
?>