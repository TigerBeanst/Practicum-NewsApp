<?php
/*
*用户登录，服务器进行的处理
*/
include("conn.php");
mysqli_select_db($conn, 'api_jakting');
$data = json_decode(file_get_contents('php://input'), true);
$sql = mysqli_query($conn, "SELECT * FROM hjt_comment WHERE url ='".$data["url"]."'");
$result = mysqli_fetch_assoc($sql);
if (!empty($result)) {
    //存在该用户
    //用户名密码匹配正确
    $back = array();
    $sqll = "SELECT * FROM hjt_comment WHERE url ='".$data["url"]."'";
    $retval = mysqli_query($conn, $sqll) or die($sqll);
    //$retval = mysqli_fetch_array($retval, MYSQLI_NUM);
    if($data["method"]=="post"){
//        $c = $retval[2]+1;
////        $sqll = "UPDATE like_count SET like_count.like_count=$c WHERE url ='".$data["url"]."'";
////        $retval = mysqli_query($conn, $sqll) or die($sqll);
////        echo $c;
    }else if($data["method"]=="get"){
        //echo $retval[2];
        //var_dump($retval);
        while($row = mysqli_fetch_assoc($retval)){
            unset($row['url']);
            array_push($back,$row);//往array数组中加入查询得到的数据
        }
        $backo = json_encode($back,JSON_UNESCAPED_UNICODE);
        echo $backo;
    }

} else {
    //不存在
    echo "-1";
}

mysqli_close($conn);
?>