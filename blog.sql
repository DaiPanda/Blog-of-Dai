/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-03-17 23:26:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comment_article`
-- ----------------------------
DROP TABLE IF EXISTS `comment_article`;
CREATE TABLE `comment_article` (
  `comment_id` int(10) NOT NULL AUTO_INCREMENT,
  `comment_name` varchar(20) NOT NULL,
  `parent_id` int(10) unsigned NOT NULL,
  `comment_content` text NOT NULL,
  `comment_likenum` int(8) unsigned NOT NULL,
  `comment_createtime` bigint(20) NOT NULL,
  `article_id` int(10) NOT NULL,
  `comment_email` varchar(25) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `article_key_id` (`article_id`),
  CONSTRAINT `article_key_id` FOREIGN KEY (`article_id`) REFERENCES `dai_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of comment_article
-- ----------------------------

-- ----------------------------
-- Table structure for `dai_adm`
-- ----------------------------
DROP TABLE IF EXISTS `dai_adm`;
CREATE TABLE `dai_adm` (
  `adm_id` tinyint(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `adm_name` varchar(20) NOT NULL,
  `adm_password` varchar(20) NOT NULL,
  PRIMARY KEY (`adm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of dai_adm
-- ----------------------------
INSERT INTO `dai_adm` VALUES ('0005', '胖达2', '23123');
INSERT INTO `dai_adm` VALUES ('0007', 'pp3', '22');
INSERT INTO `dai_adm` VALUES ('0008', '胖达3', '123456');
INSERT INTO `dai_adm` VALUES ('0009', '胖达22', '123456');
INSERT INTO `dai_adm` VALUES ('0011', '胖达34s', '123456');
INSERT INTO `dai_adm` VALUES ('0017', 'p2', '123456');
INSERT INTO `dai_adm` VALUES ('0018', 'p3', '123456');
INSERT INTO `dai_adm` VALUES ('0019', 'p4', '123456');
INSERT INTO `dai_adm` VALUES ('0020', 'p5', '123456');
INSERT INTO `dai_adm` VALUES ('0022', 'p7', '123456');
INSERT INTO `dai_adm` VALUES ('0023', 'p8', '123456');
INSERT INTO `dai_adm` VALUES ('0024', 'p9', '123456');
INSERT INTO `dai_adm` VALUES ('0026', '胖达', '23123');
INSERT INTO `dai_adm` VALUES ('0027', 'panda', '123456');
INSERT INTO `dai_adm` VALUES ('0028', 'wewe', '1111');
INSERT INTO `dai_adm` VALUES ('0029', 'shl46546', '123456');

-- ----------------------------
-- Table structure for `dai_article`
-- ----------------------------
DROP TABLE IF EXISTS `dai_article`;
CREATE TABLE `dai_article` (
  `article_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '博文id',
  `article_content` text NOT NULL COMMENT '博文内容',
  `article_likenum` int(8) unsigned zerofill NOT NULL COMMENT '博文点赞量',
  `article_views` int(12) unsigned zerofill NOT NULL COMMENT '博文浏览量',
  `article_image` varchar(150) NOT NULL COMMENT '博文图片',
  `article_title` varchar(100) NOT NULL COMMENT '博文标题',
  `article_createtime` bigint(20) NOT NULL COMMENT '博文创建时间',
  `article_isTop` tinyint(1) unsigned zerofill NOT NULL,
  `category_id` int(10) NOT NULL,
  `article_desc` varchar(255) NOT NULL,
  `count_comment` int(10) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  KEY `category_id_fk` (`category_id`),
  CONSTRAINT `category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `dai_category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of dai_article
-- ----------------------------

-- ----------------------------
-- Table structure for `dai_category`
-- ----------------------------
DROP TABLE IF EXISTS `dai_category`;
CREATE TABLE `dai_category` (
  `category_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `parent_id` int(10) NOT NULL COMMENT '父类id',
  `category_name` varchar(20) NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of dai_category
-- ----------------------------
INSERT INTO `dai_category` VALUES ('3', '0', '杂谈');
INSERT INTO `dai_category` VALUES ('4', '0', '技术笔记');
INSERT INTO `dai_category` VALUES ('5', '3', '生活');
INSERT INTO `dai_category` VALUES ('6', '3', '学习');
INSERT INTO `dai_category` VALUES ('7', '3', '情感');
INSERT INTO `dai_category` VALUES ('8', '3', '美妆');
INSERT INTO `dai_category` VALUES ('9', '3', '美食');
INSERT INTO `dai_category` VALUES ('10', '4', 'HTML5');
INSERT INTO `dai_category` VALUES ('11', '4', 'CSS3');
INSERT INTO `dai_category` VALUES ('12', '4', 'JavaScript');
INSERT INTO `dai_category` VALUES ('13', '4', 'Python');
INSERT INTO `dai_category` VALUES ('14', '4', 'Java');
INSERT INTO `dai_category` VALUES ('15', '4', 'Php');
INSERT INTO `dai_category` VALUES ('16', '4', 'Android');
INSERT INTO `dai_category` VALUES ('25', '3', '小说');
INSERT INTO `dai_category` VALUES ('26', '4', 'MYSQL');
INSERT INTO `dai_category` VALUES ('27', '4', '测试');

-- ----------------------------
-- Table structure for `dai_comment`
-- ----------------------------
DROP TABLE IF EXISTS `dai_comment`;
CREATE TABLE `dai_comment` (
  `comment_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `comment_name` varchar(20) NOT NULL COMMENT '评论名',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '评论父id',
  `comment_content` text NOT NULL COMMENT '评论内容',
  `comment_likenum` int(8) NOT NULL DEFAULT '0' COMMENT '评论点赞数',
  `comment_createtime` bigint(20) NOT NULL,
  `comment_type` tinyint(3) unsigned zerofill NOT NULL,
  `comment_email` varchar(25) NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of dai_comment
-- ----------------------------
INSERT INTO `dai_comment` VALUES ('19', '33', '0', '33', '0', '1576793387554', '001', '33');
INSERT INTO `dai_comment` VALUES ('20', '33', '0', '33<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f6/2018new_nu_thumb.png\" width=\"22\" height=\"22\">', '0', '1576793427498', '001', '22');
INSERT INTO `dai_comment` VALUES ('21', '2', '0', '<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/1e/2018new_taikaixin_org.png\" width=\"22\" height=\"22\">你真棒', '0', '1576793718002', '001', '33');
INSERT INTO `dai_comment` VALUES ('22', '2', '0', '<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/2018new_guzhang_thumb.png\" width=\"22\" height=\"22\">33333', '0', '1576793750590', '001', '33');
INSERT INTO `dai_comment` VALUES ('23', '18', '0', '你好<br><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/33/2018new_xixi_thumb.png\" width=\"22\" height=\"22\">', '0', '1576794081826', '001', '33');
INSERT INTO `dai_comment` VALUES ('24', '18', '0', '这篇文章太令人感动了！！！<br><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/1e/2018new_taikaixin_org.png\" width=\"22\" height=\"22\">', '0', '1576794122134', '001', '33');
INSERT INTO `dai_comment` VALUES ('25', '22', '0', '22', '0', '1576805994710', '001', '33');
INSERT INTO `dai_comment` VALUES ('26', '22', '0', '<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/4a/2018new_xiaoku_thumb.png\" width=\"22\" height=\"22\">ww', '0', '1576809952185', '001', '33');
INSERT INTO `dai_comment` VALUES ('27', '胖达', '0', '<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/1e/2018new_taikaixin_org.png\" height=\"22\" width=\"22\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/33/2018new_xixi_thumb.png\" height=\"22\" width=\"22\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/43/2018new_jiyan_org.png\" height=\"22\" width=\"22\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/33/2018new_xixi_thumb.png\" height=\"22\" width=\"22\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/b8/2018new_ningwen_org.png\" height=\"22\" width=\"22\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a1/2018new_kulou_thumb.png\" height=\"22\" width=\"22\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/39/starwar_chongfengduiyuan_thumb.png\" height=\"22\" width=\"22\">', '0', '1578731717104', '001', '33');

-- ----------------------------
-- Table structure for `dai_friend`
-- ----------------------------
DROP TABLE IF EXISTS `dai_friend`;
CREATE TABLE `dai_friend` (
  `friend_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `friend_name` varchar(50) NOT NULL,
  `friend_url` varchar(100) NOT NULL,
  PRIMARY KEY (`friend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of dai_friend
-- ----------------------------
INSERT INTO `dai_friend` VALUES ('0000000001', '百度', 'https://www.baidu.com');
INSERT INTO `dai_friend` VALUES ('0000000002', '网易云音乐', 'https://music.163.com');
INSERT INTO `dai_friend` VALUES ('0000000003', 'github', 'https://github.com');
INSERT INTO `dai_friend` VALUES ('0000000004', '慕课', 'https://www.imooc.com');
INSERT INTO `dai_friend` VALUES ('0000000005', '网易云课堂', 'https://study.163.com');
INSERT INTO `dai_friend` VALUES ('0000000008', '学堂在线', 'https://next.xuetangx.com');
INSERT INTO `dai_friend` VALUES ('0000000009', '中国知网', 'https://www.cnki.net/');
INSERT INTO `dai_friend` VALUES ('0000000010', '简书', 'https://www.jianshu.com/');
INSERT INTO `dai_friend` VALUES ('0000000011', '贝壳网', 'https://www.bakclass.com/');
INSERT INTO `dai_friend` VALUES ('0000000012', '司法考试', 'https://www.51test.net/sifa/');
INSERT INTO `dai_friend` VALUES ('0000000013', '道客巴巴', 'http://www.doc88.com/');
INSERT INTO `dai_friend` VALUES ('0000000014', '研究生考试', 'http://yankao.neea.edu.cn/');
INSERT INTO `dai_friend` VALUES ('0000000015', '注会CPA考试', 'http://www.exam8.com/zige/CPA/');
INSERT INTO `dai_friend` VALUES ('0000000016', '喜马拉雅网站', 'https://www.ximalaya.com/');
INSERT INTO `dai_friend` VALUES ('0000000017', '中国人事考试网', 'http://www.cpta.com.cn/');
INSERT INTO `dai_friend` VALUES ('0000000018', '全国大学生英语四六级', 'http://cet.neea.edu.cn/');
INSERT INTO `dai_friend` VALUES ('0000000019', '驾驶员考试', 'https://www.51test.net/jsyks/');
INSERT INTO `dai_friend` VALUES ('0000000020', '编程猫', 'https://www.codemao.cn/');
INSERT INTO `dai_friend` VALUES ('0000000021', '公务员考试', 'https://www.51test.net/gwy/gwycx/');
INSERT INTO `dai_friend` VALUES ('0000000022', '考虫', 'https://www.kaochong.com/');
INSERT INTO `dai_friend` VALUES ('0000000023', '外交部', 'https://www.fmprc.gov.cn/web/');

-- ----------------------------
-- Table structure for `dai_tag`
-- ----------------------------
DROP TABLE IF EXISTS `dai_tag`;
CREATE TABLE `dai_tag` (
  `tag_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of dai_tag
-- ----------------------------
INSERT INTO `dai_tag` VALUES ('1', 'MySql');
INSERT INTO `dai_tag` VALUES ('3', 'PHP');
INSERT INTO `dai_tag` VALUES ('4', 'Python');
INSERT INTO `dai_tag` VALUES ('5', 'JAVA');
INSERT INTO `dai_tag` VALUES ('6', '大数据');
INSERT INTO `dai_tag` VALUES ('7', 'Go');
INSERT INTO `dai_tag` VALUES ('8', 'Android');
INSERT INTO `dai_tag` VALUES ('9', 'IOS');
INSERT INTO `dai_tag` VALUES ('10', '开心');

-- ----------------------------
-- Table structure for `dai_work`
-- ----------------------------
DROP TABLE IF EXISTS `dai_work`;
CREATE TABLE `dai_work` (
  `work_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `work_name` varchar(200) NOT NULL,
  `work_desc` text NOT NULL,
  `category_id` int(10) NOT NULL,
  PRIMARY KEY (`work_id`),
  KEY `category_id2_fk` (`category_id`),
  CONSTRAINT `category_id2_fk` FOREIGN KEY (`category_id`) REFERENCES `dai_category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of dai_work
-- ----------------------------

-- ----------------------------
-- Table structure for `tag_article`
-- ----------------------------
DROP TABLE IF EXISTS `tag_article`;
CREATE TABLE `tag_article` (
  `tag_id` int(11) unsigned NOT NULL,
  `article_id` int(11) NOT NULL,
  PRIMARY KEY (`tag_id`,`article_id`),
  KEY `article_id_fk` (`article_id`),
  CONSTRAINT `article_id_fk` FOREIGN KEY (`article_id`) REFERENCES `dai_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tag_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `dai_tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tag_article
-- ----------------------------

-- ----------------------------
-- View structure for `count_comment`
-- ----------------------------
DROP VIEW IF EXISTS `count_comment`;
CREATE ALGORITHM=UNDEFINED DEFINER=`skip-grants user`@`skip-grants host` SQL SECURITY DEFINER VIEW `count_comment` AS select `dai_article`.`article_id` AS `article_id`,count(`comment_article`.`comment_id`) AS `comment_count` from (`dai_article` join `comment_article`) where (`dai_article`.`article_id` = `comment_article`.`article_id`) ;
DROP TRIGGER IF EXISTS `trigger_count1`;
DELIMITER ;;
CREATE TRIGGER `trigger_count1` AFTER INSERT ON `comment_article` FOR EACH ROW begin update dai_article set count_comment = count_comment+1 where dai_article.article_id = new.article_id;end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_count2`;
DELIMITER ;;
CREATE TRIGGER `trigger_count2` AFTER DELETE ON `comment_article` FOR EACH ROW begin update dai_article set count_comment = count_comment-1 where dai_article.article_id = old.article_id;end
;;
DELIMITER ;
