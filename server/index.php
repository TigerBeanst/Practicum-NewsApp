<?php
include("conn/conn.php");
mysqli_select_db($conn, 'api_jakting');
$get = $_GET["num"];
$type = $_GET["type"];
$content = $_GET["content"];
if ((!isset($_GET["num"]) || !isset($_GET["type"])) && !isset($_GET["content"])) {
    echo "无参数";
} else {
    if (!isset($_GET["content"])) {
        switch ($type) {
            case "digital":
                $kind = "BAI6JOD9wangning";
                break;
            case "finance":
                $kind = "BA8EE5GMwangning";
                break;
            case "phone":
                $kind = "BAI6I0O5wangning";
                break;
            case "science":
                $kind = "BA8D4A3Rwangning";
                break;
        }
        $url = "https://3g.163.com/touch/reconstruct/article/list/" . $kind . "/" . $get*10 . "-10.html";

        $html = file_get_contents($url);
        //echo $html;
        $re = '/{"liveInfo":.*?,"docid":".*?","source":"(.*?)","title":"(.*?)","priority":.*?,"hasImg":.*?,"url":"(.*?)","commentCount":.*?,"imgsrc3gtype":".*?","stitle":".*?","digest":"(.*?)","imgsrc":"(.*?)","ptime":"(.*?)"}/m';
        preg_match_all($re, $html, $matches, PREG_SET_ORDER, 0);
        for ($i = 0; $i < 10; $i++) {
            for ($j = 1; $j <= 6; $j++) {
                $array[$i][$j - 1] = $matches[$i][$j];
            }
            $array[$i][2] = str_replace("http://3g.163.com/", "", $array[$i][2]);
            $array[$i][2] = str_replace(".html", "", $array[$i][2]);

        }
        echo "{\"list\":[";
        for ($i = 0; $i < 10; $i++) {

            echo "{\"source\": \"" . $array[$i][0] . "\",";
            echo "\"title\": \"" . $array[$i][1] . "\",";
            echo "\"url\": \"" . $array[$i][2] . "\",";
            echo "\"digest\": \"" . $array[$i][3] . "\",";
            echo "\"imgsrc\": \"" . $array[$i][4] . "\",";
            echo "\"ptime\": \"" . $array[$i][5] . "\"}";
            if ($i != 9) {
                echo ",";
            }
        }
        echo "]}";
    } else {
        $url = "https://3g.163.com/" . $content . ".html";
        $html = file_get_contents($url);
        $re = '/<h1 class="title">(.*?)<\/h1>.*?<span class="time js-time">(.*?)<\/span>.*?<span class="source js-source">(.*?)<\/span>.*?<div class="page js-page on">\n(.*?)<p class="editor">(.*?)<\/p>/ms';
        preg_match_all($re, $html, $matches, PREG_SET_ORDER, 0);
        if ($matches[0][1] == "") {
            echo "参数错误";
        } else {
            function escapeJsonString($value)
            {
                $escapers = array("\\", "/", "\"", "\n", "\r", "\t", "\x08", "\x0c");
                $replacements = array("\\\\", "\\/", "\\\"", "\\n", "\\r", "\\t", "\\f", "\\b");
                $result = str_replace($escapers, $replacements, $value);
                return $result;
            }

            $sqll = "SELECT * FROM like_count WHERE url ='".$content."'";
            $retval = mysqli_query($conn, $sqll) or die($sqll);
            $retval = mysqli_fetch_array($retval, MYSQLI_NUM);
            if($retval[2]=="") $retval[2] = 0;
            $sqll = "SELECT count(*) FROM hjt_comment WHERE url ='".$content."'";
            $retvall = mysqli_query($conn, $sqll) or die($sqll);
            $retvall = mysqli_fetch_array($retvall, MYSQLI_NUM);
            echo "{\"title\": \"" . $matches[0][1] . "\",";
            echo "\"source\": \"" . $matches[0][3] . "\",";
            echo "\"ptime\": \"" . $matches[0][2] . "\",";
            echo "\"likecount\": \"" . $retval[2] . "\",";
            echo "\"commentcount\": \"" . $retvall[0] . "\",";
            echo "\"content\": \"" . escapeJsonString($matches[0][4]) . "\"}";
        }

    }
}
