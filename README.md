# 新闻客户端
## 一、目的与要求
1. 掌握Android 中的菜单及导航框架。
2. 掌握自定义布局。
3. 掌握Android 中的数据存储。
## 二、功能要求
1. 要求实现体育、财经、娱乐、科技等多个新闻版块（或者自定义），并可以自由通过导航菜单切换。 
2. 对每条新闻可以打开，进行阅读详细信息，包含丰富的图片和文字。
3. 对每条新闻可以进行点赞和评论。
4. 新闻数据： 如果动手能力较强，可以尝试自己动手用 PHP、JSP 等搭后台，利用 Android 网络编程，或者通过网络爬虫，爬取相关的新闻素材。（特别提示：通过安卓模拟器访问 PC 搭建的网站，IP 地址请使用 10.0.2.2）
# 服务端
不知道，反正本地是 Apache + MySQL + PHP
新闻均为从网易新闻获取，新闻列表为对网易新闻API的二次转换，新闻内容为爬虫
数据库 `server/api_jakting.sql`
php文件 `server/`下的文件
## 获取新闻列表
```
GET .......index.php?num=XXX&type=YYY
```
### XXX
即请求从XXX号开始往后十条新闻
eg：XXX=20时，API 返回从网易处获取新闻的第20~30条
### YYY
类型，只做了四个栏目
数码 digital
财经 finance
手机 phone
科技 science
### 返回示例
```
{
    "list": [
        {
            "source": "快科技",
            "title": "索尼明年3月开始预售PS5：售价或499美元",
            "url": "digi/19/1230/07/F1KKMJQF001697V8",
            "digest": "虽说索尼已经公布了PS5的不少信息，但是这款新主机何时上市依",
            "imgsrc": "http://cms-bucket.ws.126.net/2019/1230/790ce9d6p00q3as8t00goc000s600e3c.png",
            "ptime": "2019-12-30 07:36:58"
        },
        {
            "source": "快科技",
            "title": "任天堂Switch Lite宣告破解：游戏随便玩",
            "url": "digi/19/1230/08/F1KMKH0S001697V8",
            "digest": "对于0元饭和游戏宅来说，Switch破解取得重大突破。本周末",
            "imgsrc": "http://cms-bucket.ws.126.net/2019/1230/17cad412p00q3attt003kc0009c005uc.png",
            "ptime": "2019-12-30 08:10:47"
    }
}
```
## 获取新闻内容
```
GET .......index.php?content=ZZZ
```
### ZZZ
从新闻列表处可以得到 url 字段，如 `digi/19/1230/07/F1KKMJQF001697V8`
### 返回示例
```
{
    "title": "索尼明年3月开始预售PS5：售价或499美元",
    "source": "快科技",
    "ptime": "2019-12-30 07:36:58",
    "likecount": "1",
    "commentcount": "0",
    "content": "              <p>虽说索尼已经公布了PS5的不少信息，但是这款新主机何时上市依然不明朗。<\/p><p>据外媒最新报道称，曾爆料过《最后的生还者2》发售日和PS5消息的推特爆料人@PSErebus放出的猛料显示，PS5配套的DualShock5手柄将会在2020 Q1公布，而整机PS5将会在3月份于全球范围开启预购。<\/p><div class=\"photo\">\n                  <a href=\"http:\/\/cms-bucket.ws.126.net\/2019\/1230\/de7e7a9fp00q3as7u0063c000go009ec.png\">\n                      <img alt=\"\" data-src=\"http:\/\/cms-bucket.ws.126.net\/2019\/1230\/de7e7a9fp00q3as7u0063c000go009ec.png\">\n                      <span><\/span>\n                  <\/a>\n              <\/div><p>@PSErebus在爆料中还提到，PS5目标发售日期为2020年11月20日，将标配超大容量SSD，定价499美刀，约合人民币3500元，如果真是这样的话，那么3月份之前索尼就会公布更多有关PS5的详情。<\/p><p>之前曾有业界分析师表示，如果索尼和微软依然坚持400美元的定价，那么Xbox Series X和PS5是必然亏本的，当然它们也不会高的离谱，所以具体的售价应该是高于400美元（约合人民币2800元），低于699美元（约合人民币4900元），这是一个合理的价位。<\/p><div class=\"photo\">\n                  <a href=\"http:\/\/cms-bucket.ws.126.net\/2019\/1230\/822b41d1j00q3as5u001xc000go009dc.jpg\">\n                      <img alt=\"\" data-src=\"http:\/\/cms-bucket.ws.126.net\/2019\/1230\/822b41d1j00q3as5u001xc000go009dc.jpg\">\n                      <span><\/span>\n                  <\/a>\n              <\/div>\n              <div class=\"otitle_editor\">\n \n                "
}
```
请自行净化 HTML 内容
# 其他
懒得写了，`server/conn` 目录下的文件都是 POST 或者 GET，POST 的数据