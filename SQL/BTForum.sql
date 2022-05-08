-- MariaDB dump 10.19  Distrib 10.7.3-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: bt_forum
-- ------------------------------------------------------
-- Server version	10.7.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bt_billboard`
--

DROP TABLE IF EXISTS `bt_billboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_billboard` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) NOT NULL COMMENT '公告',
  `create_time` datetime DEFAULT NULL COMMENT '公告时间',
  `shows` tinyint(1) DEFAULT NULL COMMENT '1：展示中，0：过期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='全站公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_billboard`
--

LOCK TABLES `bt_billboard` WRITE;
/*!40000 ALTER TABLE `bt_billboard` DISABLE KEYS */;
INSERT INTO `bt_billboard` VALUES
(7,'我们又增加了轮播图功能，快来看看吧','2022-03-21 14:34:34',0),
(9,'test','2022-04-04 11:58:12',0),
(10,'V1.0版本来啦','2022-04-04 23:45:41',0),
(11,'新增公告','2022-04-23 11:06:20',1);
/*!40000 ALTER TABLE `bt_billboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_carousel`
--

DROP TABLE IF EXISTS `bt_carousel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_carousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '图片地址',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '信息',
  `shows` bit(1) DEFAULT b'0' COMMENT '状态，1：显示 2隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COMMENT='轮播图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_carousel`
--

LOCK TABLES `bt_carousel` WRITE;
/*!40000 ALTER TABLE `bt_carousel` DISABLE KEYS */;
INSERT INTO `bt_carousel` VALUES
(1,'http://r8uj7z8yq.hd-bkt.clouddn.com/2022/03/19/f44f071b9bba41ecafba40500e15c582.png','test','','2022-03-21 13:53:30'),
(2,'http://r8uj7z8yq.hd-bkt.clouddn.com/2022/03/20/f87dac5a8bc24b01a658a79288835987.png','test','','2022-03-21 13:53:32'),
(10,'http://r8uj7z8yq.hd-bkt.clouddn.com/2022/03/23/178c2d1732bc4dafb0160c7a3e2a4b44.png','黄河温','','2022-03-23 16:36:11'),
(12,'http://r8uj7z8yq.hd-bkt.clouddn.com/2022/04/04/482c9eef2b0c43c38506b10adb093436.png','测试','','2022-04-04 22:14:44');
/*!40000 ALTER TABLE `bt_carousel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_comment`
--

DROP TABLE IF EXISTS `bt_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_comment` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `content` varchar(1000) NOT NULL DEFAULT '' COMMENT '内容',
  `user_id` varchar(20) NOT NULL COMMENT '作者ID',
  `topic_id` varchar(20) NOT NULL COMMENT 'topic_id',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `parent_user_id` varchar(255) DEFAULT NULL COMMENT '父评论id',
  `root_id` varchar(255) DEFAULT '-1' COMMENT '评论等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_comment`
--

LOCK TABLES `bt_comment` WRITE;
/*!40000 ALTER TABLE `bt_comment` DISABLE KEYS */;
INSERT INTO `bt_comment` VALUES
('1505876366116028417','666','1504657025806737409','1505406364564914177','2022-03-21 19:57:58',NULL,NULL,'-1'),
('1508828974443175937','```python\nif True:\nprint (\"Answer\")\nprint (\"True\")\nelse:\nprint (\"Answer\")\nprint (\"False\")    # 缩进不一致，会导致运行错误\n```\n\n','1504657025806737409','1507363857419595777','2022-03-29 23:30:34',NULL,NULL,'-1'),
('1509434047888158721','普通文本\n\n','1504657025806737409','1507363857419595777','2022-03-31 15:34:55',NULL,NULL,'-1'),
('1509805683623718913','评论测试\n\n','1509802194172506114','1509805444795854849','2022-04-01 16:11:40',NULL,NULL,'-1'),
('1511002961965342721','违禁词 *\n\n','1504657025806737409','1509805444795854849','2022-04-04 23:29:13',NULL,NULL,'-1'),
('1511005239350693890','\n','1504657025806737409','1509805444795854849','2022-04-04 23:38:16',NULL,NULL,'-1'),
('1511017018034266113','普通用户评论\n\n','1511016613250375682','1507363857419595777','2022-04-05 00:25:05',NULL,NULL,'-1'),
('1511943894865031170','这说的啥','1511016613250375682','1507363857419595777','2022-04-07 13:48:09',NULL,NULL,'1508828974443175937'),
('1511944220393353217','我自己也看不懂','1504657025806737409','1507363857419595777','2022-04-07 13:49:27',NULL,NULL,'1508828974443175937'),
('1511981770826895362','*','1504657025806737409','1507363857419595777','2022-04-07 16:18:40',NULL,NULL,'1511017018034266113'),
('1511981963756490754','![httpr8uj7z8yq.hdbkt.clouddn.com202204072884dcfd4d3d407788331637b45d49f4.png](http://r8uj7z8yq.hd-bkt.clouddn.com/2022/04/07/2884dcfd4d3d407788331637b45d49f4.png)\n\n\n','1504657025806737409','1507363857419595777','2022-04-07 16:19:26',NULL,NULL,'-1'),
('1515335288111611905','你这都是啥啊\n\n','1511016613250375682','1515189211878146050','2022-04-16 22:24:20',NULL,NULL,'-1'),
('1515335803075674113','讲的好啊\n\n','1511016613250375682','1507363857419595777','2022-04-16 22:26:23',NULL,NULL,'-1'),
('1517701797785972737','评论 *\n\n','1504657025806737409','1517701629523079170','2022-04-23 11:08:00',NULL,NULL,'-1'),
('1517702247969009665','二级评论','1504657025806737409','1517701629523079170','2022-04-23 11:09:48',NULL,NULL,'1517701797785972737');
/*!40000 ALTER TABLE `bt_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_follow`
--

DROP TABLE IF EXISTS `bt_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '被关注人ID',
  `follower_id` varchar(20) DEFAULT NULL COMMENT '关注人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户关注';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_follow`
--

LOCK TABLES `bt_follow` WRITE;
/*!40000 ALTER TABLE `bt_follow` DISABLE KEYS */;
INSERT INTO `bt_follow` VALUES
(65,'1329723594994229250','1317498859501797378'),
(85,'1332912847614083073','1332636310897664002'),
(129,'1349290158897311745','1349618748226658305'),
(132,'1349290158897311745','1503778530862682114'),
(135,'1503778530862682114','4'),
(137,'1511016613250375682','1504657025806737409'),
(138,'1504657025806737409','1511016613250375682'),
(139,'1504657025806737409','1515321095140532225');
/*!40000 ALTER TABLE `bt_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_message`
--

DROP TABLE IF EXISTS `bt_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `to_user_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '接收用户id',
  `from_user_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '发送用户id',
  `content` varchar(500) CHARACTER SET utf8mb4 NOT NULL COMMENT '消息内容',
  `create_time` datetime DEFAULT NULL,
  `read_status` char(1) CHARACTER SET utf8mb4 DEFAULT '0' COMMENT '消息状态 0 未读  1 已读',
  `del_flag` int(11) DEFAULT 0 COMMENT '是否删除 0未删除 1删除',
  `remark` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `from_user_name` varchar(255) DEFAULT NULL COMMENT '发信息的用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_message`
--

LOCK TABLES `bt_message` WRITE;
/*!40000 ALTER TABLE `bt_message` DISABLE KEYS */;
INSERT INTO `bt_message` VALUES
(1,'1504657025806737409','1511016613250375682','关注了你','2022-04-15 00:25:48','0',0,NULL,'user'),
(2,'1511016613250375682','1504657025806737409','关注了你','2022-04-16 00:48:53','0',0,NULL,'admin'),
(3,'1511016613250375682','1511016613250375682','评论了你的帖子:你这都是啥啊\n\n','2022-04-16 22:24:20','0',0,NULL,'user'),
(4,'1504657025806737409','1511016613250375682','评论了你的帖子:讲的好啊\n\n','2022-04-16 22:26:23','0',0,NULL,'user'),
(5,'1515321095140532225','1504657025806737409','关注了你','2022-04-16 22:28:04','0',0,NULL,'admin'),
(6,'1504657025806737409','1504657025806737409','评论了你的帖子:*\n\n','2022-04-23 09:39:33','1',0,NULL,'admin'),
(7,'1504657025806737409','1504657025806737409','评论了你的帖子:评论 *\n\n','2022-04-23 11:08:00','1',0,NULL,'admin');
/*!40000 ALTER TABLE `bt_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_post`
--

DROP TABLE IF EXISTS `bt_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_post` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `content` longtext DEFAULT NULL COMMENT 'markdown内容',
  `user_id` varchar(20) NOT NULL COMMENT '作者ID',
  `comments` int(11) NOT NULL DEFAULT 0 COMMENT '评论统计',
  `collects` int(11) NOT NULL DEFAULT 0 COMMENT '收藏统计',
  `view` int(11) NOT NULL DEFAULT 0 COMMENT '浏览统计',
  `top` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否置顶，1-是，0-否',
  `essence` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否加精，1-是，0-否',
  `section_id` int(11) DEFAULT 0 COMMENT '专栏ID',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='话题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_post`
--

LOCK TABLES `bt_post` WRITE;
/*!40000 ALTER TABLE `bt_post` DISABLE KEYS */;
INSERT INTO `bt_post` VALUES
('1504659124443521026','admin','admin\n\n','1504657025806737409',0,0,28,'\0','\0',0,'2022-03-18 11:21:05',NULL),
('1505041610746892289','test','```java\n<dependency>\n            <groupId>com.baomidou</groupId>\n            <artifactId>mybatis-plus-boot-starter</artifactId>\n            <version>3.4.2</version>\n        </dependency>\n   \n   <dependency>\n      <groupId>com.baomidou</groupId>\n      <artifactId>mybatis-plus-generator</artifactId>\n      <version>3.5.1</version>\n    </dependency>\n\n        <!--velocity模板-->\n        <dependency>\n            <groupId>org.apache.velocity</groupId>\n            <artifactId>velocity-engine-core</artifactId>\n            <version>2.3</version>\n        </dependency>\n        <!--freemarker模板-->\n        <dependency>\n            <groupId>org.freemarker</groupId>\n            <artifactId>freemarker</artifactId>\n        </dependency>\n\n```\n\n','1504657025806737409',0,0,10,'\0','\0',0,'2022-03-19 12:40:56',NULL),
('1505406364564914177','图片测试','![httpr8uj7z8yq.hdbkt.clouddn.com20220320d13e87d397f543e796bf46d9109d7d0c.png](http://r8uj7z8yq.hd-bkt.clouddn.com/2022/03/20/d13e87d397f543e796bf46d9109d7d0c.png)\n\n\n','1504657025806737409',0,0,42,'\0','\0',0,'2022-03-20 12:50:21',NULL),
('1507362091823132674','语法高亮','```java\npublic class HelloWorld{\n    public static void main(Strin[]args){\n        System.out.println(\"Hello World\");\n    }\n}\n```\n\n\n','1504657025806737409',0,0,18,'\0','\0',0,'2022-03-25 22:21:42','2022-03-31 12:23:11'),
('1507363857419595777','评论测试','评论测试\n\n','1504657025806737409',0,0,273,'\0','\0',0,'2022-03-25 22:28:43','2022-04-07 16:23:29'),
('1514497293577953281','普通用户发帖','你看看\n\n','1511016613250375682',0,0,1,'\0','\0',0,'2022-04-14 14:54:27',NULL),
('1514497422481498114','普通用户发帖2','你看看\n\n','1511016613250375682',0,0,8,'\0','\0',0,'2022-04-14 14:54:58',NULL),
('1515184509719429121','热门帖测试','1\n\n','1504657025806737409',0,0,3,'\0','\0',0,'2022-04-16 12:25:12',NULL),
('1515189211878146050','普通用户发帖测试3','测试\n\n','1511016613250375682',0,0,3,'\0','\0',0,'2022-04-16 12:43:53',NULL),
('1516416058083295234','敏感词','*\n\n','1504657025806737409',0,0,13,'\0','\0',0,'2022-04-19 21:58:56','2022-04-19 21:59:20'),
('1517701629523079170','发布帖子1','发布帖子  *\n\n','1504657025806737409',0,0,5,'\0','\0',0,'2022-04-23 11:07:20','2022-04-23 11:07:30');
/*!40000 ALTER TABLE `bt_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_post_tag`
--

DROP TABLE IF EXISTS `bt_post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_post_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_id` varchar(20) NOT NULL COMMENT '标签ID',
  `topic_id` varchar(20) NOT NULL COMMENT '话题ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `tag_id` (`tag_id`) USING BTREE,
  KEY `topic_id` (`topic_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='话题-标签 中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_post_tag`
--

LOCK TABLES `bt_post_tag` WRITE;
/*!40000 ALTER TABLE `bt_post_tag` DISABLE KEYS */;
INSERT INTO `bt_post_tag` VALUES
(36,'1332650453377708034','1332650453142827009'),
(37,'1332681213568589825','1332681213400817665'),
(38,'1332681213631504385','1332681213400817665'),
(39,'1332682473218744321','1332682473151635458'),
(40,'1332913064463794178','1332913064396685314'),
(41,'1332913064530903041','1332913064396685314'),
(42,'1333432347107143681','1333432347031646209'),
(43,'1333432347107143682','1333432347031646209'),
(44,'1333447953697177602','1333447953558765569'),
(45,'1332913064463794178','1333668258587750401'),
(46,'1333676096320106498','1333676096156528641'),
(47,'1333695976742268930','1333695976536748034'),
(48,'1334481725519429634','1334481725322297346'),
(49,'1333447953697177602','1335149981733449729'),
(50,'1349362401597775874','1349362401438392322'),
(51,'1349631541306732545','1349631541260595202'),
(52,'1500170370181812225','1500170370144063489'),
(53,'1503956875413102594','1503956875413102593'),
(54,'1504659124443521027','1504659124443521026'),
(55,'1503956875413102594','1504684581415034881'),
(56,'1500170370181812225','1505041610746892289'),
(57,'1505406364632023041','1505406364564914177'),
(58,'1332650453377708034','1507362091823132674'),
(59,'1507363857419595778','1507363857419595777'),
(60,'1509805444862963714','1509805444795854849'),
(61,'1514497293577953282','1514497293577953281'),
(62,'1514497293577953282','1514497422481498114'),
(63,'1515184509719429122','1515184509719429121'),
(64,'1514497293577953282','1515189211878146050'),
(65,'1503956875413102594','1516416058083295234'),
(66,'1503956875413102594','1517701629523079170');
/*!40000 ALTER TABLE `bt_post_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_promotion`
--

DROP TABLE IF EXISTS `bt_promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '广告标题',
  `link` varchar(255) DEFAULT NULL COMMENT '广告链接',
  `description` varchar(255) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='广告推广表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_promotion`
--

LOCK TABLES `bt_promotion` WRITE;
/*!40000 ALTER TABLE `bt_promotion` DISABLE KEYS */;
INSERT INTO `bt_promotion` VALUES
(4,'bilibili','www.bilibili.com','B站'),
(7,'广告','http://localhost:8080/#/admin/promotion','广告');
/*!40000 ALTER TABLE `bt_promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_tag`
--

DROP TABLE IF EXISTS `bt_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_tag` (
  `id` varchar(20) NOT NULL COMMENT '标签ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '标签',
  `topic_count` int(11) NOT NULL DEFAULT 0 COMMENT '关联话题',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_tag`
--

LOCK TABLES `bt_tag` WRITE;
/*!40000 ALTER TABLE `bt_tag` DISABLE KEYS */;
INSERT INTO `bt_tag` VALUES
('1332650453377708034','java',1),
('1332681213568589825','css',0),
('1332681213631504385','mongodb',0),
('1332682473218744321','python',0),
('1332913064463794178','vue',1),
('1332913064530903041','react',1),
('1333432347107143681','node',0),
('1333432347107143682','mysql',0),
('1333447953697177602','flask',1),
('1333676096320106498','spring',0),
('1333695976742268930','django',0),
('1334481725519429634','security',0),
('1349362401597775874','tensorflow',0),
('1349631541306732545','pytorch',0),
('1500170370181812225','test',1),
('1503956875413102594','敏感词',2),
('1504659124443521027','admin',0),
('1505406364632023041','图片测试',0),
('1507363857419595778','content',0),
('1509805444862963714','发帖测试',0),
('1514497293577953282','BUTU',2),
('1515184509719429122','测试',0);
/*!40000 ALTER TABLE `bt_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_tip`
--

DROP TABLE IF EXISTS `bt_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_tip` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(1000) NOT NULL DEFAULT '' COMMENT '内容',
  `author` varchar(50) DEFAULT '' COMMENT '作者',
  `type` tinyint(4) NOT NULL COMMENT '1：使用，0：过期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24864 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='每日赠言';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_tip`
--

LOCK TABLES `bt_tip` WRITE;
/*!40000 ALTER TABLE `bt_tip` DISABLE KEYS */;
INSERT INTO `bt_tip` VALUES
(1,'多锉出快锯，多做长知识。','佚名',1),
(2,'未来总留着什么给对它抱有信心的人。','佚名',1),
(3,'一个人的智慧不够用，两个人的智慧用不完。','谚语',1),
(4,'十个指头按不住十个跳蚤','傣族',1),
(5,'言不信者，行不果。','墨子',1),
(6,'攀援而登，箕踞而遨，则几数州之土壤，皆在衽席之下。','柳宗元',1),
(7,'美德大都包含在良好的习惯之内。','帕利克',1),
(8,'人有不及，可以情恕。','《晋书》',1),
(9,'明·吴惟顺','法不传六耳',1),
(10,'真正的朋友应该说真话，不管那话多么尖锐。','奥斯特洛夫斯基',1),
(11,'时间是一切财富中最宝贵的财富。','德奥弗拉斯多',1),
(12,'看人下菜碟','民谚',1),
(13,'如果不是怕别人反感，女人决不会保持完整的严肃。','拉罗什福科',1),
(14,'爱是春暖花开时对你满满的笑意','佚名',1),
(15,'希望是坚韧的拐杖，忍耐是旅行袋，携带它们，人可以登上永恒之旅。','罗素',1),
(18,'天国般的幸福，存在于对真爱的希望。','佚名',1),
(19,'我们现在必须完全保持党的纪律，否则一切都会陷入污泥中。','马克思',1),
(20,'在科学上没有平坦的大道，只有不畏劳苦沿着陡峭山路攀登的人，才有希望达到光辉的顶点。','马克思',1),
(21,'懒惰的马嫌路远','蒙古',1),
(22,'别忘记热水是由冷水烧成的','非洲',1);
/*!40000 ALTER TABLE `bt_tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_user`
--

DROP TABLE IF EXISTS `bt_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_user` (
  `id` varchar(255) NOT NULL COMMENT '用户ID',
  `username` varchar(15) NOT NULL DEFAULT '' COMMENT '用户名',
  `alias` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `avatar` varchar(1000) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机',
  `score` int(11) NOT NULL DEFAULT 0 COMMENT '积分',
  `token` varchar(255) DEFAULT '' COMMENT 'token',
  `bio` varchar(255) DEFAULT NULL COMMENT '个人简介',
  `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否激活，1：是，0：否',
  `status` bit(1) DEFAULT b'1' COMMENT '状态，1：使用，0：停用',
  `role_id` int(11) DEFAULT NULL COMMENT '用户角色',
  `create_time` datetime NOT NULL COMMENT '加入时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `live` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_name` (`username`) USING BTREE,
  KEY `user_email` (`email`) USING BTREE,
  KEY `user_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_user`
--

LOCK TABLES `bt_user` WRITE;
/*!40000 ALTER TABLE `bt_user` DISABLE KEYS */;
INSERT INTO `bt_user` VALUES
('1504657025806737409','admin','admin','$2a$10$1ujvxe3UjshtzBdwysiZEez4q8lUqREt32aesUuX1L8BrYx/Ye9gi','http://r8uj7z8yq.hd-bkt.clouddn.com/2022/04/14/f4ec9af9c1084e2399136d756ad9999c.png','1781894948@qq.com','123456789',8,'','test','\0','',1,'2022-03-18 11:12:44',NULL,''),
('1511016613250375682','user','user','$2a$10$2naM5J4wX3c2egAgq28Cde2D/1OrBhBGv2Sm8o8bm68gsLoNe2Bwa','http://r8uj7z8yq.hd-bkt.clouddn.com/2022/04/05/bc61ad9417be49d39ab87c65dc633347.png','1781894948@qq.com',NULL,3,'',NULL,'\0','',NULL,'2022-04-05 00:23:28',NULL,'\0'),
('1515321095140532225','user1','user1','$2a$10$27mIreumklqkosooWDIN9u3hibL/A0nKby0XPd5w0Wpvvt40q2xCK',NULL,'12345@qq.com',NULL,0,'',NULL,'\0','',NULL,'2022-04-16 21:27:57',NULL,'\0'),
('1515321868725268482','user2','user2','$2a$10$VFgGIgOm7dEbT2cteEvQdOBqN.Grl0xEif9D0exXgqFtnOV4GMz.G',NULL,'1781894948@qq.com',NULL,0,'',NULL,'\0','',NULL,'2022-04-16 21:31:01',NULL,'\0'),
('3','test','test','e10adc3949ba59abbe56e057f20f883e',NULL,'123456@qq.com',NULL,1,'',NULL,'\0','',NULL,'2022-03-05 01:09:02',NULL,'\0');
/*!40000 ALTER TABLE `bt_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bt_word`
--

DROP TABLE IF EXISTS `bt_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bt_word` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `word` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '敏感词',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1 COMMENT='敏感词库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bt_word`
--

LOCK TABLES `bt_word` WRITE;
/*!40000 ALTER TABLE `bt_word` DISABLE KEYS */;
INSERT INTO `bt_word` VALUES
(16,'嗨嗨嗨'),
(17,'芍'),
(18,'黄河温'),
(19,'伞兵');
/*!40000 ALTER TABLE `bt_word` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) NOT NULL DEFAULT 'null' COMMENT '菜单名',
  `path` varchar(200) DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '路由组件',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态 0显示 1 隐藏',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态 0正常 1 停用',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT 0 COMMENT '是否删除 0未删除 1删除',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES
(1,'后台管理','admin','admin/','0','0','admin','#',NULL,0,NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role_key` char(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) CHARACTER SET utf8mb4 DEFAULT '0' COMMENT '角色状态 0 正常 1 停用',
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT 0 COMMENT '是否删除 0未删除 1删除',
  `remark` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES
(1,'管理员','admin','0',NULL,0,NULL),
(2,'普通用户','user','0',NULL,0,NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `menu_id` bigint(200) NOT NULL DEFAULT 0 COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES
(1,1),
(1,2);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(255) NOT NULL COMMENT '角色id',
  `role_id` bigint(200) NOT NULL DEFAULT 0 COMMENT '菜单id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES
('1504657025806737409',1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-08 22:04:14
