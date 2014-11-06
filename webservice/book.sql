/*
Navicat MySQL Data Transfer

Source Server         : apartment
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2014-11-06 18:55:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bookactivity`
-- ----------------------------
DROP TABLE IF EXISTS `bookactivity`;
CREATE TABLE `bookactivity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `act_period` varchar(255) NOT NULL,
  `act_sum` int(11) DEFAULT NULL,
  `act_status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookactivity
-- ----------------------------
INSERT INTO `bookactivity` VALUES ('1', '2014.9.1-2014.10.1', '1000', '0');

-- ----------------------------
-- Table structure for `bookbasic`
-- ----------------------------
DROP TABLE IF EXISTS `bookbasic`;
CREATE TABLE `bookbasic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'book_id',
  `book_name` varchar(255) NOT NULL,
  `book_author` varchar(255) NOT NULL,
  `book_type` varchar(255) NOT NULL,
  `act_id` int(11) NOT NULL,
  `book_info` varchar(255) DEFAULT NULL,
  `book_price` float DEFAULT NULL,
  `book_status` varchar(255) NOT NULL DEFAULT '未被借',
  `favour` int(11) NOT NULL DEFAULT '0',
  `dislike` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookbasic
-- ----------------------------
INSERT INTO `bookbasic` VALUES ('1', 'Android应用UI设计模式', 'Greg Nudelman', '移动端', '1', '人民邮电', null, '已被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('2', 'Android和PHP开发最佳实践', '黄隽实', '移动端', '1', '机械工业', null, '未入库', '0', '0');
INSERT INTO `bookbasic` VALUES ('3', '深入浅出PhoneGap', '饶侠', '移动端', '1', '人民邮电', null, '未购买', '0', '0');
INSERT INTO `bookbasic` VALUES ('4', '第一行代码Android', '郭霖', '移动端', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('5', 'Java程序设计经典300例', '李源', 'JAVA', '1', '电子工业', null, '已超期', '0', '0');
INSERT INTO `bookbasic` VALUES ('6', 'Java常用算法手册', '徐明远', 'JAVA', '1', '中国铁道', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('7', 'Web3.0与Semantic Web编程', 'John Hebeler', 'JAVA', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('8', 'Java Web整合开发王者归来', '刘京华', 'JAVA', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('9', 'Java编程思想', 'Bruce Eckel', 'JAVA', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('10', 'Java网络编程', 'Elliotte Rusty', 'JAVA', '1', '中国电力', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('11', 'HTML5WebSocket权威指南', 'Vanessa Wang', 'Web小技术', '1', '机械工程', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('12', '图解HTTP', '上野宣', 'Web小技术', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('13', '网站性能检测与优化', 'Alistair Croll', 'Web小技术', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('14', 'Web技术——HTTP到服务器端', '小泉修', 'Web小技术', '1', '科学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('15', 'RESTfulWeb APIs', 'Leonard Richardson', 'Web小技术', '1', '电子工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('16', 'Thinking in UML', '谭云杰', 'UML', '1', '中国水利水电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('17', 'UML面向对象建模基础', '徐峰', 'UML', '1', '中国水利水电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('18', '深入浅出JS编程', 'Eric Freeman', 'JS', '1', '东南大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('19', 'JQuery技术内幕', '高云', 'JS', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('20', '精通Jquery', 'Adam Freeman', 'JS', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('21', 'JS权威指南', 'David Flanagan', 'JS', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('22', '单页Web应用', 'Michael S.', 'JS', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('23', '扩展及应用开发Chrome', '李喆', 'JS', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('24', 'JavaScript DOM编程艺术', 'Jeremy Keith', 'JS', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('25', 'Ruby基础教程', '高桥征义', '小众后端', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('26', '面向对象设计实践指南Ruby语言描述', 'Sandi Metz', '小众后端', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('27', 'Go语言程序设计', 'Mark Summerfield', '小众后端', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('28', '深入浅出nodjs', '朴灵', '小众后端', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('29', 'The Ruby Way', 'Hal Fulton', '小众后端', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('30', 'Ruby Cookbook', 'Lucas Carlson', '小众后端', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('31', 'Programming Ruby', 'Dave Thomas', '小众后端', '1', '电子工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('32', '只是为了好玩', 'Linus Torvalds', '操作系统', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('33', '理解Unix进程', 'Jesse Storimer', '操作系统', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('34', '深入Linux内核架构', 'Wolfgang Mauerer', '操作系统', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('35', 'Unix网络编程卷一', 'W.Richard Stevens', '操作系统', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('36', 'Unix网络编程卷二', 'W.Richard Stevens', '操作系统', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('37', '精通Unix shell脚本编程', 'Randal K.', '操作系统', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('38', '操作系统之哲学原理', '邹恒明', '操作系统', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('39', 'PHP编程实战', 'Peter Maclntyre', 'PHP', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('40', '细说PHP', 'LAMP兄弟连', 'PHP', '1', '电子工业', null, '已被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('41', 'PHP核心技术与最佳实践', '陈文', 'PHP', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('42', 'PHP和MySQL Web开发', 'Luke Welling', 'PHP', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('43', 'PHP与MySQL程序设计', 'W.Jason Gilmore', 'PHP', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('44', '利用Python进行数据分析', 'Wes McKinney', '数据挖掘', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('45', 'Hadoop权威指南', 'Tom White', '数据挖掘', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('46', '数据挖掘', 'Jiawei Han', '数据挖掘', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('47', 'HTML5实战', '陶国荣', '前端', '1', '机械工业', null, '已被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('48', 'Web前端黑客技术揭秘', '钟晨鸣', '前端', '1', '电子工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('49', 'HTML5与CSS3权威指南', '陆凌牛', '前端', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('50', 'CSS高效开发实战', '谢郁', '前端', '1', '电子工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('51', 'C++PrimerPlus', 'Stephen Prata', 'C++', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('52', '高效程序员的45个习惯——敏捷开发修炼之道', 'Venkat Subramaniam', '思维锻炼', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('53', '统计思维 程序员数学之概率统计', 'Allen B.Downey', '思维锻炼', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('54', '研磨设计模式', '陈臣', '思维锻炼', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('55', '大设计', '霍金', '思维锻炼', '1', '湖南科学技术', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('56', 'Scrum要素', 'Chris Slims', '思维锻炼', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('57', 'Scrum实战', 'Mitch Lacey', '思维锻炼', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('58', 'Javascript编程精解', 'Marijn Haverbeke', 'JS', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('59', '数据库系统概念', 'Abraham Silberschatz', '数据库', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('60', '数据库查询优化器', '李海翔', '数据库', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('61', 'Linux教程', 'Syed Mansoor Sarwar', '操作系统', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('62', '编写可维护的javaScript', 'Nicholas C.Zakas', 'JS', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('63', '基于MVC的javaScript Web 富应用开发', 'Alex MacCaw', 'JS', '1', '电子工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('64', '给大家看的Web设计书', 'Robin Williams', 'Web小技术', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('65', 'Sass与Compass实战', 'Wynn Netherland', 'Web小技术', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('66', '黑客与画家', 'Paul Graham', '杂项', '1', '人民邮电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('67', '霍金传', 'Michael White', '杂项', '1', '湖南科学技术', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('68', '你的灯亮着吗？', '唐纳德·高斯', '杂项', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('69', '逻辑的力量', 'C.Stephen Layman', '杂项', '1', '中国人民大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('70', '数字文明物理学和计算机', '郝柏林', '杂项', '1', '科学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('71', '不插电的计算机科学', 'Tim Bell', '杂项', '1', '华中科技大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('72', '白', '原研哉', '杂项', '1', '广西师范大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('73', '摇摆难以抗拒的非理性诱惑', '奥瑞·布莱福曼', '杂项', '1', '中信', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('74', '程序开发心理学', 'Gerald M.Weinberg', '杂项', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('75', '通灵芯片', 'W.Daniel Hillis', '杂项', '1', '上海世纪', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('76', '和谐社会之数学表达', '赵克', '杂项', '1', '广东人民', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('77', '互联网：碎片化生存', '段永朝', '杂项', '1', '中信', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('78', 'SEO实战密码', 'Zac', '杂项', '1', '电子工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('79', '蚁群优化', 'Marco Dorigo', '杂项', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('80', '复杂网络理论及其应用', '汪小帆', '杂项', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('81', '链接网络新科学', '艾伯特-拉斯洛·巴拉巴西', '杂项', '1', '湖南科学技术', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('82', '人机工程与工业设计', '张宇红', '杂项', '1', '中国水利水电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('83', '链接分析：信息科学的研究方法', 'Mike Thelwall', '杂项', '1', '东南大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('84', '嵌入式系统高能效软件技术及应用', '赵霞', '杂项', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('85', '如何求解问题', 'Zbigniew Michalewicz', '杂项', '1', '中国水利水电', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('86', '数据库原理与实现', '高屹', '数据库', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('87', '可穿戴设备', '陈根', '交互', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('88', 'Kinect应用开发实战', '余涛', '交互', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('89', '虚拟现实技术', '陈怀友', '交互', '1', '清华大学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('90', '色彩设计的原理', '伊达千代', '前端', '1', '中信', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('91', '文字的设计原理', '伊达千代', '前端', '1', '中信', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('92', '版面设计的原理', '伊达千代', '前端', '1', '中信', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('93', '笔式用户界面', '戴国忠', '交互', '1', '中国科学技术', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('94', '信息可视化交互设计', 'Robert Spence', '交互', '1', '机械工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('95', '关键设计报告', '比尔·莫格里奇', '前端', '1', '中信', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('96', '物联网导论', '刘云浩', '杂项', '1', '科学', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('97', '用AngularJS开发下一代Web应用', 'Brad Green', '前端', '1', '电子工业', null, '未被借', '0', '0');
INSERT INTO `bookbasic` VALUES ('98', 'CSS设计指南', 'Charles Wyke-Smith', '前端', '1', '人民邮电', null, '未被借', '0', '0');

-- ----------------------------
-- Table structure for `bookcirculate`
-- ----------------------------
DROP TABLE IF EXISTS `bookcirculate`;
CREATE TABLE `bookcirculate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookcirculate
-- ----------------------------
INSERT INTO `bookcirculate` VALUES ('1', '47', '12108413', '2014-10-13', '0000-00-00');
INSERT INTO `bookcirculate` VALUES ('2', '40', '12108206', '2014-10-22', '0000-00-00');
INSERT INTO `bookcirculate` VALUES ('3', '1', '12108413', '2014-11-05', '0000-00-00');

-- ----------------------------
-- Table structure for `bookdetail`
-- ----------------------------
DROP TABLE IF EXISTS `bookdetail`;
CREATE TABLE `bookdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `book_pic` text,
  `book_link` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookdetail
-- ----------------------------
INSERT INTO `bookdetail` VALUES ('1', '1', 'https://avatars0.githubusercontent.com/u/8351862?v=2&s=460', 'http://www.uisdc.com/android-ui-mode');
INSERT INTO `bookdetail` VALUES ('2', '2', null, null);
INSERT INTO `bookdetail` VALUES ('3', '3', null, null);
INSERT INTO `bookdetail` VALUES ('4', '4', null, null);
INSERT INTO `bookdetail` VALUES ('5', '5', null, null);
INSERT INTO `bookdetail` VALUES ('6', '6', null, null);
INSERT INTO `bookdetail` VALUES ('7', '7', null, null);
INSERT INTO `bookdetail` VALUES ('8', '8', null, null);
INSERT INTO `bookdetail` VALUES ('9', '9', null, null);
INSERT INTO `bookdetail` VALUES ('10', '10', null, null);
INSERT INTO `bookdetail` VALUES ('11', '11', null, null);
INSERT INTO `bookdetail` VALUES ('12', '12', null, null);
INSERT INTO `bookdetail` VALUES ('13', '13', null, null);
INSERT INTO `bookdetail` VALUES ('14', '14', null, null);
INSERT INTO `bookdetail` VALUES ('15', '15', null, null);
INSERT INTO `bookdetail` VALUES ('16', '16', null, null);
INSERT INTO `bookdetail` VALUES ('17', '17', null, null);
INSERT INTO `bookdetail` VALUES ('18', '18', null, null);
INSERT INTO `bookdetail` VALUES ('19', '19', null, null);
INSERT INTO `bookdetail` VALUES ('20', '20', null, null);
INSERT INTO `bookdetail` VALUES ('21', '21', null, null);
INSERT INTO `bookdetail` VALUES ('22', '22', null, null);
INSERT INTO `bookdetail` VALUES ('23', '23', null, null);
INSERT INTO `bookdetail` VALUES ('24', '24', null, null);
INSERT INTO `bookdetail` VALUES ('25', '25', null, null);
INSERT INTO `bookdetail` VALUES ('26', '26', null, null);
INSERT INTO `bookdetail` VALUES ('27', '27', null, null);
INSERT INTO `bookdetail` VALUES ('28', '28', null, null);
INSERT INTO `bookdetail` VALUES ('29', '29', null, null);
INSERT INTO `bookdetail` VALUES ('30', '30', null, null);
INSERT INTO `bookdetail` VALUES ('31', '31', null, null);
INSERT INTO `bookdetail` VALUES ('32', '32', null, null);
INSERT INTO `bookdetail` VALUES ('33', '33', null, null);
INSERT INTO `bookdetail` VALUES ('34', '34', null, null);
INSERT INTO `bookdetail` VALUES ('35', '35', null, null);
INSERT INTO `bookdetail` VALUES ('36', '36', null, null);
INSERT INTO `bookdetail` VALUES ('37', '37', null, null);
INSERT INTO `bookdetail` VALUES ('38', '38', null, null);
INSERT INTO `bookdetail` VALUES ('39', '39', null, null);
INSERT INTO `bookdetail` VALUES ('40', '40', null, null);
INSERT INTO `bookdetail` VALUES ('41', '41', null, null);
INSERT INTO `bookdetail` VALUES ('42', '42', null, null);
INSERT INTO `bookdetail` VALUES ('43', '43', null, null);
INSERT INTO `bookdetail` VALUES ('44', '44', null, null);
INSERT INTO `bookdetail` VALUES ('45', '45', null, null);
INSERT INTO `bookdetail` VALUES ('46', '46', null, null);
INSERT INTO `bookdetail` VALUES ('47', '47', null, null);
INSERT INTO `bookdetail` VALUES ('48', '48', null, null);
INSERT INTO `bookdetail` VALUES ('49', '49', null, null);
INSERT INTO `bookdetail` VALUES ('50', '50', null, null);
INSERT INTO `bookdetail` VALUES ('51', '51', null, null);
INSERT INTO `bookdetail` VALUES ('52', '52', null, null);
INSERT INTO `bookdetail` VALUES ('53', '53', null, null);
INSERT INTO `bookdetail` VALUES ('54', '54', null, null);
INSERT INTO `bookdetail` VALUES ('55', '55', null, null);
INSERT INTO `bookdetail` VALUES ('56', '56', null, null);
INSERT INTO `bookdetail` VALUES ('57', '57', null, null);
INSERT INTO `bookdetail` VALUES ('58', '58', null, null);
INSERT INTO `bookdetail` VALUES ('59', '59', null, null);
INSERT INTO `bookdetail` VALUES ('60', '60', null, null);
INSERT INTO `bookdetail` VALUES ('61', '61', null, null);
INSERT INTO `bookdetail` VALUES ('62', '62', null, null);
INSERT INTO `bookdetail` VALUES ('63', '63', null, null);
INSERT INTO `bookdetail` VALUES ('64', '64', null, null);
INSERT INTO `bookdetail` VALUES ('65', '65', null, null);
INSERT INTO `bookdetail` VALUES ('66', '66', null, null);
INSERT INTO `bookdetail` VALUES ('67', '67', null, null);
INSERT INTO `bookdetail` VALUES ('68', '68', null, null);
INSERT INTO `bookdetail` VALUES ('69', '69', null, null);
INSERT INTO `bookdetail` VALUES ('70', '70', null, null);
INSERT INTO `bookdetail` VALUES ('71', '71', null, null);
INSERT INTO `bookdetail` VALUES ('72', '72', null, null);
INSERT INTO `bookdetail` VALUES ('73', '73', null, null);
INSERT INTO `bookdetail` VALUES ('74', '74', null, null);
INSERT INTO `bookdetail` VALUES ('75', '75', null, null);
INSERT INTO `bookdetail` VALUES ('76', '76', null, null);
INSERT INTO `bookdetail` VALUES ('77', '77', null, null);
INSERT INTO `bookdetail` VALUES ('78', '78', null, null);
INSERT INTO `bookdetail` VALUES ('79', '79', null, null);
INSERT INTO `bookdetail` VALUES ('80', '80', null, null);
INSERT INTO `bookdetail` VALUES ('81', '81', null, null);
INSERT INTO `bookdetail` VALUES ('82', '82', null, null);
INSERT INTO `bookdetail` VALUES ('83', '83', null, null);
INSERT INTO `bookdetail` VALUES ('84', '84', null, null);
INSERT INTO `bookdetail` VALUES ('85', '85', null, null);
INSERT INTO `bookdetail` VALUES ('86', '86', null, null);
INSERT INTO `bookdetail` VALUES ('87', '87', null, null);
INSERT INTO `bookdetail` VALUES ('88', '88', null, null);
INSERT INTO `bookdetail` VALUES ('89', '89', null, null);
INSERT INTO `bookdetail` VALUES ('90', '90', null, null);
INSERT INTO `bookdetail` VALUES ('91', '91', null, null);
INSERT INTO `bookdetail` VALUES ('92', '92', null, null);
INSERT INTO `bookdetail` VALUES ('93', '93', null, null);
INSERT INTO `bookdetail` VALUES ('94', '94', null, null);
INSERT INTO `bookdetail` VALUES ('95', '95', null, null);
INSERT INTO `bookdetail` VALUES ('96', '96', null, null);
INSERT INTO `bookdetail` VALUES ('97', '97', null, null);
INSERT INTO `bookdetail` VALUES ('98', '98', null, null);

-- ----------------------------
-- Table structure for `bookmessage`
-- ----------------------------
DROP TABLE IF EXISTS `bookmessage`;
CREATE TABLE `bookmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_message` varchar(255) NOT NULL,
  `created_at` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookmessage
-- ----------------------------
INSERT INTO `bookmessage` VALUES ('1', '1', '1', '我看过这本', '2014-10-27');

-- ----------------------------
-- Table structure for `recommend`
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `rec_reason` varchar(255) NOT NULL,
  `created_at` date NOT NULL,
  `rec_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recommend
-- ----------------------------
INSERT INTO `recommend` VALUES ('1', '1', '1', '这书不错', '2014-10-26', '自选入库');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_rank` varchar(255) NOT NULL DEFAULT '普通用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '11101018', '李智', '', '普通用户');
INSERT INTO `user` VALUES ('2', '12108129', '吴伟', '', '普通用户');
INSERT INTO `user` VALUES ('3', '12108135', '张坚革', '', '普通用户');
INSERT INTO `user` VALUES ('4', '12108139', '郑齐阳', '', '普通用户');
INSERT INTO `user` VALUES ('5', '12108201', '白林林', '', '普通用户');
INSERT INTO `user` VALUES ('6', '12108203', '彭雅丽', '', '普通用户');
INSERT INTO `user` VALUES ('7', '12108206', '边涛', '', '普通用户');
INSERT INTO `user` VALUES ('8', '12108208', '陈浩', '', '普通用户');
INSERT INTO `user` VALUES ('9', '12108209', '陈露耿', '', '普通用户');
INSERT INTO `user` VALUES ('10', '12108210', '陈轲', '', '普通用户');
INSERT INTO `user` VALUES ('11', '12108211', '段鹏飞', '', '普通用户');
INSERT INTO `user` VALUES ('12', '12108216', '李金祥', '', '普通用户');
INSERT INTO `user` VALUES ('13', '12108222', '沈之川', '', '普通用户');
INSERT INTO `user` VALUES ('14', '12108227', '熊君睿', '', '普通用户');
INSERT INTO `user` VALUES ('15', '12108230', '章晨昱', '', '普通用户');
INSERT INTO `user` VALUES ('16', '12108234', '张旭', '', '普通用户');
INSERT INTO `user` VALUES ('17', '12108238', '钟云昶', '123', '图书管理');
INSERT INTO `user` VALUES ('18', '12108305', '王雪莹', '', '普通用户');
INSERT INTO `user` VALUES ('19', '12108306', '曹平涛', '', '普通用户');
INSERT INTO `user` VALUES ('20', '12108309', '程彦', '', '普通用户');
INSERT INTO `user` VALUES ('21', '12108315', '刘宏志', '', '普通用户');
INSERT INTO `user` VALUES ('22', '12108316', '刘铁', '', '普通用户');
INSERT INTO `user` VALUES ('23', '12108318', '罗鹏展', '', '普通用户');
INSERT INTO `user` VALUES ('24', '12108327', '谢俊杰', '', '购书管理');
INSERT INTO `user` VALUES ('25', '12108331', '许鹏飞', '', '普通用户');
INSERT INTO `user` VALUES ('26', '12108413', '黄可庆', '12345', '普通用户');
