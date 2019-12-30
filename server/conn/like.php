<?php
/*
*用户登录，服务器进行的处理
*/
include("conn.php");
mysqli_select_db($conn, 'api_jakting');
$data = json_decode(file_get_contents('php://input'), true);
$sql = mysqli_query($conn, "SELECT * FROM like_count WHERE url ='" . $data["url"] . "'");
$result = mysqli_fetch_assoc($sql);
if (!empty($result)) {
    //存在该用户
    $sqll = "SELECT * FROM like_count WHERE url ='" . $data["url"] . "'";
    $retval = mysqli_query($conn, $sqll) or die($sqll);
    $retval = mysqli_fetch_array($retval, MYSQLI_NUM);
    if ($data["method"] == "post") {
        $c = $retval[2] + 1;
        $sqll = "UPDATE like_count SET like_count.like_count=$c WHERE like_count.url ='" . $data["url"] . "'";
        $retval = mysqli_query($conn, $sqll) or die($sqll);
        $sqll = "UPDATE user SET user.like_count=(user.like_count+1) WHERE user.userid=".$data["id"];
        $retval = mysqli_query($conn, $sqll) or die($sqll);
        echo $c;
    } else if ($data["method"] == "get") {
        echo $retval[2];
    }

} else {
    //不存在
    $sqll = "INSERT INTO like_count (url,like_count)VALUES ('" . $data["url"] . "',1);";
    $retval = mysqli_query($conn, $sqll) or die($sqll);
    $sqll = "UPDATE user SET user.like_count=(user.like_count+1)  WHERE user.userid=".$data["id"];
    $retval = mysqli_query($conn, $sqll) or die($sqll);
    echo "1";
}

mysqli_close($conn);
?>