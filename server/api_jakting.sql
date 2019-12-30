-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2019-12-30 11:00:12
-- 服务器版本： 5.6.41
-- PHP 版本： 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `api_jakting`
--

-- --------------------------------------------------------

--
-- 表的结构 `hjt_comment`
--

CREATE TABLE `hjt_comment` (
  `id` int(11) NOT NULL,
  `url` text NOT NULL,
  `author` text NOT NULL,
  `comment_content` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `hjt_comment`
--

INSERT INTO `hjt_comment` (`id`, `url`, `author`, `comment_content`) VALUES
(57, 'digi/19/1227/08/F1CUV3L7001697V8', 'Jartip888', 'test for comment'),
(58, 'digi/19/1227/08/F1CUV3L7001697V8', 'Jartip888', 'test for comment2'),
(59, 'digi/19/1227/08/F1CUV3L7001697V8', 'Jartip888', 'test for comment3'),
(60, 'digi/19/1227/08/F1CUV3L7001697V8', 'Jartip888', 'test for comment4'),
(61, 'digi/19/1227/08/F1CUV3L7001697V8', 'Jartip888', 'test for comment5'),
(62, 'digi/19/1227/08/F1CUV3L7001697V8', 'Jartip888', 'test for comment6'),
(63, 'digi/19/1227/10/F1D876G3001697V8', 'Jartip888', 'Hao!'),
(64, 'digi/19/1227/10/F1D876G3001697V8', 'Jartip888', 'haohaoha'),
(65, 'digi/19/1227/10/F1D876G3001697V8', 'Jartip888', 'oooooo'),
(66, 'digi/19/1227/10/F1D8JD4F001697V8', 'Jartip888', 'hao!'),
(67, 'digi/19/1227/10/F1D8JD4F001697V8', 'Jartip888', 'eieie'),
(68, 'digi/19/1227/08/F1CUV3L7001697V8', 'Jartip888', 'stttttsts'),
(69, 'digi/19/1227/08/F1CUS4CF001697V8', 'Jartip888', 'First!'),
(70, 'digi/19/1227/08/F1CUS4CF001697V8', 'Jartip888', 'OneOne'),
(71, 'digi/19/1227/10/F1D876G3001697V8', 'Jartip888', '55555'),
(72, 'digi/19/1227/10/F1D876G3001697V8', 'Jartip888', '55555666'),
(73, 'digi/19/1227/10/F1D876G3001697V8', '工具人8号', '好评'),
(74, 'digi/19/1227/10/F1D8JD4F001697V8', '工具人8号', '好评嗷'),
(75, 'digi/19/1227/08/F1CV44E1001697V8', 'GgjRRRR', '工具人评论1'),
(76, 'digi/19/1227/08/F1CV44E1001697V8', 'GgjRRRR', '工具人评论2'),
(79, 'digi/19/1227/07/F1CUPL0P001697V8', 'GgjRRRR', '可还行嗷'),
(80, 'digi/19/1227/07/F1CUPL0P001697V8', 'GgjRRRR', '可还行嗷'),
(81, 'digi/19/1227/10/F1D8D3RV001697V8', 'GjR', '雷暴雨'),
(82, 'digi/19/1227/08/F1CUV3L7001697V8', 'Jartip888', 'Ping Lun'),
(83, 'digi/19/1227/08/F1CUV3L7001697V8', 'Jartip888', 'Pingg'),
(84, 'digi/19/1227/10/F1D876G3001697V8', 'Jartip888', 'CeshiPingLun'),
(85, 'digi/19/1227/08/F1CV44E1001697V8', 'Jartip888', 'CeshiPingLun'),
(86, 'mobile/19/1226/15/F1B7R9C20011819H', 'Jartip888', 'Hao!'),
(87, 'mobile/19/1226/11/F1APMEHN00119821', '123', '发发发'),
(88, 'mobile/19/1226/11/F1APMEHN00119821', '123', '发发发'),
(89, 'mobile/19/1226/11/F1APMEHN00119821', '123', '发发发'),
(90, 'mobile/19/1226/11/F1APMEHN00119821', '123', '发发发'),
(91, 'mobile/19/1226/11/F1APMEHN00119821', '123', '发发发'),
(92, 'digi/19/1230/08/F1KM1BVB001697V8', 'Test', 'ce shi'),
(93, 'digi/19/1230/08/F1KMKH0S001697V8', 'Test', 'hao!'),
(94, 'money/19/1230/09/F1KR27VR00258105', 'Test2', 'hh'),
(95, 'money/19/1230/09/F1KR27VR00258105', 'Test2', 'opoo');

-- --------------------------------------------------------

--
-- 表的结构 `like_count`
--

CREATE TABLE `like_count` (
  `id` int(11) NOT NULL,
  `url` text NOT NULL,
  `like_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `like_count`
--

INSERT INTO `like_count` (`id`, `url`, `like_count`) VALUES
(1, 'digi/19/1224/07/F155KRBH001697V8', 64),
(4, 'digi/19/1224/10/F15HNPT8001697V8', 7),
(5, 'digi/19/1224/07/F156I9UK001697V8', 8),
(6, 'digi/19/1223/08/F12NF39M001697V8', 1),
(7, 'digi/19/1224/08/F15AU9QT001697V8', 1),
(8, 'digi/19/1224/07/F157HORL001697V8', 1),
(9, 'digi/19/1224/08/F15AFT65001697V8', 1),
(10, 'digi/19/1224/07/F155HTKM001697V8', 1),
(11, 'tech/19/1224/07/F156QDAN00097U7T', 5),
(12, 'digi/19/1224/07/F155FO2M001697V8', 1),
(13, 'digi/19/1224/07/F1569OCA001697V8', 67),
(14, 'digi/19/1224/07/F1567ET8001697V8', 1),
(15, 'digi/19/1223/08/F12MR5NR001697V8', 1),
(16, 'digi/19/1224/07/F156Q5T8001697V8', 1),
(17, 'digi/19/1224/16/F164TA6J001697V8', 1),
(18, 'digi/19/1225/08/F17Q4UMO001697V8', 2),
(19, 'tech/19/1225/07/F17NLBOJ00097U7R', 5),
(20, 'digi/19/1225/08/F17QFIRB001697V8', 25),
(21, 'digi/19/1226/07/F1AAJ8R5001697V8', 1),
(22, 'digi/19/1227/08/F1CUV3L7001697V8', 31),
(23, 'digi/19/1227/10/F1D876G3001697V8', 5),
(24, 'digi/19/1227/08/F1CUS4CF001697V8', 1),
(25, 'digi/19/1227/10/F1D8JD4F001697V8', 2),
(26, 'digi/19/1227/08/F1CV44E1001697V8', 3),
(27, 'money/19/1227/09/F1D28LV500258J1R', 1),
(28, 'digi/19/1227/07/F1CUPL0P001697V8', 1),
(29, 'digi/19/1227/10/F1D8D3RV001697V8', 3),
(30, 'mobile/19/1226/15/F1B7R9C20011819H', 2),
(31, 'mobile/19/1226/11/F1APG2I500119821', 58),
(32, 'digi/19/1230/08/F1KM1BVB001697V8', 2),
(33, 'digi/19/1230/07/F1KKMJQF001697V8', 1),
(34, 'digi/19/1230/08/F1KMDJKN001697V8', 1),
(35, 'digi/19/1230/08/F1KMKH0S001697V8', 1),
(36, 'money/19/1230/09/F1KR27VR00258105', 2);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `like_count` int(11) NOT NULL DEFAULT '0',
  `star_count` int(11) NOT NULL DEFAULT '0',
  `star` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`userid`, `username`, `password`, `name`, `like_count`, `star_count`, `star`) VALUES
(1, 'hjthjthjt', 'E10ADC3949BA59ABBE56E057F20F883E', 'Jartip888', 20, 2, '{\"starlist\":[{\"title\":\"苹果要推Mac游戏机或MBP游戏本 售价5000美元起\",\"url\": \"digi/19/1225/08/F17Q4UMO001697V8\"},{\"title\":\"15999元限量1000块！华硕白色版2080 Ti上架\",\"url\": \"digi/19/1225/08/F17QFIRB001697V8\"}]}'),
(3, 'gjr1', 'E10ADC3949BA59ABBE56E057F20F883E', '工具人1号', 0, 0, NULL),
(6, 'gjr2', '1A100D2C0DAB19C4430E7D73762B3423', 'gongjuren222222', 0, 0, NULL),
(7, 'hjt1', 'E10ADC3949BA59ABBE56E057F20F883E', 'hjt1', 0, 0, NULL),
(8, 'gjr3', 'E10ADC3949BA59ABBE56E057F20F883E', '工具人3号', 0, 0, NULL),
(9, 'gjr4', 'E10ADC3949BA59ABBE56E057F20F883E', '工具人4号', 0, 0, NULL),
(10, 'gjr5', 'E10ADC3949BA59ABBE56E057F20F883E', '工具人5号', 0, 0, NULL),
(11, 'gjr33', 'E10ADC3949BA59ABBE56E057F20F883E', '工具人33号', 0, 0, NULL),
(12, 'gjr6', 'E10ADC3949BA59ABBE56E057F20F883E', '工具人6号', 0, 0, NULL),
(13, 'gjr7', 'E10ADC3949BA59ABBE56E057F20F883E', '工具人8号', 2, 0, NULL),
(14, 'ggjr1', 'E10ADC3949BA59ABBE56E057F20F883E', 'GgjRRRR', 3, 0, NULL),
(15, 'gjr9', 'E10ADC3949BA59ABBE56E057F20F883E', 'GjR', 3, 0, NULL),
(16, '123', '202CB962AC59075B964B07152D234B70', '123', 58, 0, NULL),
(17, 'gjr11', 'E10ADC3949BA59ABBE56E057F20F883E', 'gongjuren11', 0, 0, NULL),
(18, 'test', 'E10ADC3949BA59ABBE56E057F20F883E', 'Test', 5, 0, NULL),
(19, 'test2', 'E10ADC3949BA59ABBE56E057F20F883E', 'Test2', 2, 0, NULL);

--
-- 转储表的索引
--

--
-- 表的索引 `hjt_comment`
--
ALTER TABLE `hjt_comment`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `like_count`
--
ALTER TABLE `like_count`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `hjt_comment`
--
ALTER TABLE `hjt_comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- 使用表AUTO_INCREMENT `like_count`
--
ALTER TABLE `like_count`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
