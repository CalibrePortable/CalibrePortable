/*
Navicat MySQL Data Transfer

Source Server         : apartment
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2014-11-27 10:41:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bookactivity`
-- ----------------------------
DROP TABLE IF EXISTS `bookactivity`;
CREATE TABLE `bookactivity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `act_period` varchar(255) NOT NULL COMMENT '活动时间段',
  `act_budget` int(11) NOT NULL COMMENT '活动预算',
  `act_status` varchar(255) NOT NULL COMMENT '活动状态',
  `created_at` date NOT NULL COMMENT '创建日期',
  `updated_at` date NOT NULL COMMENT '更新时间',
  `act_cost` float DEFAULT NULL COMMENT '活动花费',
  `act_message` varchar(255) DEFAULT NULL COMMENT '活动公示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookactivity
-- ----------------------------
INSERT INTO `bookactivity` VALUES ('1', '2014.9.1-2014.10.1', '0', '0', '0000-00-00', '0000-00-00', null, '');

-- ----------------------------
-- Table structure for `bookbasic`
-- ----------------------------
DROP TABLE IF EXISTS `bookbasic`;
CREATE TABLE `bookbasic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书类编号',
  `book_isbn` varchar(255) NOT NULL COMMENT '唯一isbn',
  `book_name` varchar(255) NOT NULL COMMENT '书本名称',
  `book_author` varchar(255) NOT NULL DEFAULT '未知' COMMENT '书本作者',
  `book_pub` varchar(255) DEFAULT NULL COMMENT '出版版次',
  `book_type` varchar(255) NOT NULL COMMENT '书本类型',
  `book_edit` varchar(255) DEFAULT NULL COMMENT '出版社团',
  `book_price` float(11,1) NOT NULL DEFAULT '0.0' COMMENT '书本价格',
  `book_pic` text NOT NULL COMMENT '书图片url',
  `book_link` text COMMENT '书相关url',
  `book_info` text COMMENT '书本简介',
  `favour` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookbasic
-- ----------------------------
INSERT INTO `bookbasic` VALUES ('1', '', 'Android应用UI设计模式', 'Greg Nudelman', null, '移动端', '人民邮电出版社', '0.0', '', null, null, '2');
INSERT INTO `bookbasic` VALUES ('2', '', 'Android和PHP开发最佳实践', '黄隽实', null, '移动端', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('3', '', '深入浅出PhoneGap', '饶侠', null, '移动端', '人民邮电出版社', '0.0', '', null, null, '1');
INSERT INTO `bookbasic` VALUES ('4', '', '第一行代码Android', '郭霖', null, '移动端', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('5', '', 'Java程序设计经典300例', '李源', null, 'JAVA', '电子工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('6', '', 'Java常用算法手册', '徐明远', null, 'JAVA', '中国铁道出版社', '0.0', '', null, null, '1');
INSERT INTO `bookbasic` VALUES ('7', '', 'Web3.0与Semantic Web编程', 'John Hebeler', null, 'JAVA', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('8', '', 'Java Web整合开发王者归来', '刘京华', null, 'JAVA', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('9', '', 'Java编程思想', 'Bruce Eckel', null, 'JAVA', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('10', '', 'Java网络编程', 'Elliotte Rusty', null, 'JAVA', '中国电力出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('11', '', 'HTML5WebSocket权威指南', 'Vanessa Wang', null, 'Web', '机械工程出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('12', '', '图解HTTP', '上野宣', null, 'Web', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('13', '', '网站性能检测与优化', 'Alistair Croll', null, 'Web', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('14', '', 'Web技术——HTTP到服务器端', '小泉修', null, 'Web', '科学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('15', '', 'RESTfulWeb APIs', 'Leonard Richardson', null, 'Web', '电子工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('16', '', 'Thinking in UML', '谭云杰', null, 'UML', '中国水利水电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('17', '', 'UML面向对象建模基础', '徐峰', null, 'UML', '中国水利水电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('18', '', '深入浅出JS编程', 'Eric Freeman', null, 'JS', '东南大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('19', '', 'JQuery技术内幕', '高云', null, 'JS', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('20', '', '精通Jquery', 'Adam Freeman', null, 'JS', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('21', '', 'JS权威指南', 'David Flanagan', null, 'JS', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('22', '', '单页Web应用', 'Michael S.', null, 'JS', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('23', '', '扩展及应用开发Chrome', '李喆', null, 'JS', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('24', '', 'JavaScript DOM编程艺术', 'Jeremy Keith', null, 'JS', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('25', '', 'Ruby基础教程', '高桥征义', null, '小众后端', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('26', '', '面向对象设计实践指南Ruby语言描述', 'Sandi Metz', null, '小众后端', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('27', '', 'Go语言程序设计', 'Mark Summerfield', null, '小众后端', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('28', '', '深入浅出nodjs', '朴灵', null, '小众后端', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('29', '', 'The Ruby Way', 'Hal Fulton', null, '小众后端', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('30', '', 'Ruby Cookbook', 'Lucas Carlson', null, '小众后端', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('31', '', 'Programming Ruby', 'Dave Thomas', null, '小众后端', '电子工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('32', '', '只是为了好玩', 'Linus Torvalds', null, '操作系统', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('33', '', '理解Unix进程', 'Jesse Storimer', null, '操作系统', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('34', '', '深入Linux内核架构', 'Wolfgang Mauerer', null, '操作系统', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('35', '', 'Unix网络编程卷一', 'W.Richard Stevens', null, '操作系统', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('36', '', 'Unix网络编程卷二', 'W.Richard Stevens', null, '操作系统', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('37', '', '精通Unix shell脚本编程', 'Randal K.', null, '操作系统', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('38', '', '操作系统之哲学原理', '邹恒明', null, '操作系统', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('39', '', 'PHP编程实战', 'Peter Maclntyre', null, 'PHP', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('40', '', '细说PHP', 'LAMP兄弟连', null, 'PHP', '电子工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('41', '', 'PHP核心技术与最佳实践', '陈文', null, 'PHP', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('42', '', 'PHP和MySQL Web开发', 'Luke Welling', null, 'PHP', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('43', '', 'PHP与MySQL程序设计', 'W.Jason Gilmore', null, 'PHP', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('44', '', '利用Python进行数据分析', 'Wes McKinney', null, '数据挖掘', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('45', '', 'Hadoop权威指南', 'Tom White', null, '数据挖掘', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('46', '', '数据挖掘', 'Jiawei Han', null, '数据挖掘', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('47', '9787111358732', 'HTML 5实战', '陶国荣', '2011-11', '前端', '机械工业出版社', '59.0', 'http://img3.douban.com/mpic/s24573620.jpg', 'http://book.douban.com/subject/6958339/', '陶国荣编著的《HTML5实战》是一本系统而全面的HTML 5教程，根据HTML 5标准的最新草案，系统地对HTML 5的所有重要知识点进行了全面的讲解。在写作方式上，本书以一种开创性的方式使理论与实践达到极好的平衡，不仅对理论知识进行了清晰而透彻的阐述，而且根据读者理解这些知识的需要，精心设计了106个完整（每个案例分为功能描述、实现代码、效果展示和代码分析4个部分）的实战案例，旨在帮助读者通过实践的方式迅速掌握这些知识。\n《HTML5实战》共11章，内容涵盖了HTML 5的各个方面。第1章通过实现一个简单的HTML 5页面讲解了如何搭建支持HTML 5的浏览器环境、HTML 5页面所具备的特征，以及如何检测浏览器对HTML 5的各种特性的支持情况；第2章介绍了HTML 5中常用的交互元素，包括内容交互元素、菜单交互元素和状态交互元素等几大类；第3章介绍了HTML根元素、文档元素，以及与脚本、节点、分组内容、文本层次语义、嵌入内容、公共属性相关的重要元素；第4章和第5章讲解了HTML 5中的表单和文件的功能特性以及常见的各种操作；第6章和第7章讲解了HTML 5中的音频、视频和绘图相关的知识，重点讲解了各种常见的操作和使用方法；第8章和第9章讲解了HTML 5中的数据存储和离线应用；第10章对Web Sockets、Geolocation、Web Workers、元素的拖放等重要内容进行了全面的讲解。\n本书适合所有想系统学习HTML 5的读者阅读。如果按照本书的顺序逐章阅读，同时亲自动手实现本书中的案例，相信一定能达到事半功倍的效果。\n\n海报：', '3');
INSERT INTO `bookbasic` VALUES ('48', '', 'Web前端黑客技术揭秘', '钟晨鸣', null, '前端', '电子工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('49', '', 'HTML5与CSS3权威指南', '陆凌牛', null, '前端', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('50', '', 'CSS高效开发实战', '谢郁', null, '前端', '电子工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('51', '', 'C++PrimerPlus', 'Stephen Prata', null, 'C++', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('52', '', '高效程序员的45个习惯——敏捷开发修炼之道', 'Venkat Subramaniam', null, '思维锻炼', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('53', '', '统计思维 程序员数学之概率统计', 'Allen B.Downey', null, '思维锻炼', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('54', '', '研磨设计模式', '陈臣', null, '思维锻炼', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('55', '', '大设计', '霍金', null, '思维锻炼', '湖南科学技术出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('56', '', 'Scrum要素', 'Chris Slims', null, '思维锻炼', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('57', '', 'Scrum实战', 'Mitch Lacey', null, '思维锻炼', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('58', '', 'Javascript编程精解', 'Marijn Haverbeke', null, 'JS', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('59', '', '数据库系统概念', 'Abraham Silberschatz', null, '数据库', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('60', '', '数据库查询优化器', '李海翔', null, '数据库', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('61', '', 'Linux教程', 'Syed Mansoor Sarwar', null, '操作系统', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('62', '', '编写可维护的javaScript', 'Nicholas C.Zakas', null, 'JS', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('63', '', '基于MVC的javaScript Web 富应用开发', 'Alex MacCaw', null, 'JS', '电子工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('64', '', '给大家看的Web设计书', 'Robin Williams', null, 'Web', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('65', '', 'Sass与Compass实战', 'Wynn Netherland', null, 'Web', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('66', '', '黑客与画家', 'Paul Graham', null, '其他', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('67', '', '霍金传', 'Michael White', null, '其他', '湖南科学技术出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('68', '', '你的灯亮着吗？', '唐纳德·高斯', null, '其他', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('69', '', '逻辑的力量', 'C.Stephen Layman', null, '其他', '中国人民大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('70', '', '数字文明物理学和计算机', '郝柏林', null, '其他', '科学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('71', '', '不插电的计算机科学', 'Tim Bell', null, '其他', '华中科技大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('72', '', '白', '原研哉', null, '其他', '广西师范大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('73', '', '摇摆难以抗拒的非理性诱惑', '奥瑞·布莱福曼', null, '其他', '中信出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('74', '', '程序开发心理学', 'Gerald M.Weinberg', null, '其他', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('75', '', '通灵芯片', 'W.Daniel Hillis', null, '其他', '上海世纪出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('76', '', '和谐社会之数学表达', '赵克', null, '其他', '广东人民出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('77', '', '互联网：碎片化生存', '段永朝', null, '其他', '中信出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('78', '', 'SEO实战密码', 'Zac', null, '其他', '电子工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('79', '', '蚁群优化', 'Marco Dorigo', null, '其他', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('80', '', '复杂网络理论及其应用', '汪小帆', null, '其他', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('81', '', '链接网络新科学', '艾伯特-拉斯洛·巴拉巴西', null, '其他', '湖南科学技术出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('82', '', '人机工程与工业设计', '张宇红', null, '其他', '中国水利水电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('83', '', '链接分析：信息科学的研究方法', 'Mike Thelwall', null, '其他', '东南大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('84', '', '嵌入式系统高能效软件技术及应用', '赵霞', null, '其他', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('85', '', '如何求解问题', 'Zbigniew Michalewicz', null, '其他', '中国水利水电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('86', '', '数据库原理与实现', '高屹', null, '数据库', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('87', '', '可穿戴设备', '陈根', null, '交互', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('88', '', 'Kinect应用开发实战', '余涛', null, '交互', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('89', '', '虚拟现实技术', '陈怀友', null, '交互', '清华大学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('90', '', '色彩设计的原理', '伊达千代', null, '前端', '中信出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('91', '', '文字的设计原理', '伊达千代', null, '前端', '中信出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('92', '', '版面设计的原理', '伊达千代', null, '前端', '中信出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('93', '', '笔式用户界面', '戴国忠', null, '交互', '中国科学技术出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('94', '', '信息可视化交互设计', 'Robert Spence', null, '交互', '机械工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('95', '', '关键设计报告', '比尔·莫格里奇', null, '前端', '中信出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('96', '', '物联网导论', '刘云浩', null, '其他', '科学出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('97', '', '用AngularJS开发下一代Web应用', 'Brad Green', null, '前端', '电子工业出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('98', '', 'CSS设计指南', 'Charles Wyke-Smith', null, '前端', '人民邮电出版社', '0.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('99', '', 'new book', '123', null, '46', '90出版社', '12.0', '', null, null, '0');
INSERT INTO `bookbasic` VALUES ('100', '', '2', '3', '4', '5', '6', '7.0', '8', '9', '0', '0');

-- ----------------------------
-- Table structure for `bookcirculate`
-- ----------------------------
DROP TABLE IF EXISTS `bookcirculate`;
CREATE TABLE `bookcirculate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL COMMENT '与书关联',
  `user_id` int(11) NOT NULL COMMENT '用户关联',
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookcirculate
-- ----------------------------
INSERT INTO `bookcirculate` VALUES ('1', '47', '12108413', '2014-10-13', '2014-11-12');
INSERT INTO `bookcirculate` VALUES ('2', '40', '12108206', '2014-10-22', '0000-00-00');
INSERT INTO `bookcirculate` VALUES ('3', '1', '12108413', '2014-11-05', '0000-00-00');
INSERT INTO `bookcirculate` VALUES ('4', '47', '12108238', '2014-11-25', '0000-00-00');
INSERT INTO `bookcirculate` VALUES ('5', '2', '12108413', '2014-11-10', '2014-11-26');
INSERT INTO `bookcirculate` VALUES ('6', '113', '12108206', '2014-11-12', '0000-00-00');
INSERT INTO `bookcirculate` VALUES ('8', '5', '12108413', '2014-11-25', '0000-00-00');

-- ----------------------------
-- Table structure for `booklike`
-- ----------------------------
DROP TABLE IF EXISTS `booklike`;
CREATE TABLE `booklike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_kind` int(11) NOT NULL COMMENT '与类关联',
  `user_id` int(11) NOT NULL COMMENT '与人关联',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booklike
-- ----------------------------
INSERT INTO `booklike` VALUES ('1', '1', '12108413');
INSERT INTO `booklike` VALUES ('2', '3', '12108413');
INSERT INTO `booklike` VALUES ('3', '1', '12108238');
INSERT INTO `booklike` VALUES ('4', '6', '12108238');
INSERT INTO `booklike` VALUES ('5', '47', '12108238');

-- ----------------------------
-- Table structure for `booklist`
-- ----------------------------
DROP TABLE IF EXISTS `booklist`;
CREATE TABLE `booklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '清单编号',
  `book_kind` int(11) NOT NULL COMMENT '与类关联',
  `book_time` date NOT NULL DEFAULT '0000-00-00' COMMENT '录入时间',
  `book_status` varchar(255) NOT NULL DEFAULT '未被借' COMMENT '书本状态',
  `act_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动相关',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booklist
-- ----------------------------
INSERT INTO `booklist` VALUES ('1', '1', '2014-09-01', '已被借', '0');
INSERT INTO `booklist` VALUES ('2', '2', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('3', '3', '2014-09-01', '未入库', '0');
INSERT INTO `booklist` VALUES ('4', '4', '2014-09-01', '已超期', '0');
INSERT INTO `booklist` VALUES ('5', '5', '2014-09-01', '已被借', '0');
INSERT INTO `booklist` VALUES ('6', '6', '2014-09-01', '未购买', '0');
INSERT INTO `booklist` VALUES ('7', '7', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('8', '8', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('9', '9', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('10', '10', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('11', '11', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('12', '12', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('13', '13', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('14', '14', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('15', '15', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('16', '16', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('17', '17', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('18', '18', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('19', '19', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('20', '20', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('21', '21', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('22', '22', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('23', '23', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('24', '24', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('25', '25', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('26', '26', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('27', '27', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('28', '28', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('29', '29', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('30', '30', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('31', '31', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('32', '32', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('33', '33', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('34', '34', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('35', '35', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('36', '36', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('37', '37', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('38', '38', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('39', '39', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('40', '40', '2014-09-01', '已被借', '0');
INSERT INTO `booklist` VALUES ('41', '41', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('42', '42', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('43', '43', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('44', '44', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('45', '45', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('46', '46', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('47', '47', '2014-09-01', '已被借', '0');
INSERT INTO `booklist` VALUES ('48', '48', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('49', '49', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('50', '50', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('51', '51', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('52', '52', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('53', '53', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('54', '54', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('55', '55', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('56', '56', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('57', '57', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('58', '58', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('59', '59', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('60', '60', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('61', '61', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('62', '62', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('63', '63', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('64', '64', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('65', '65', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('66', '66', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('67', '67', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('68', '68', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('69', '69', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('70', '70', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('71', '71', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('72', '72', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('73', '73', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('74', '74', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('75', '75', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('76', '76', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('77', '77', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('78', '78', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('79', '79', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('80', '80', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('81', '81', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('82', '82', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('83', '83', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('84', '84', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('85', '85', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('86', '86', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('87', '87', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('88', '88', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('89', '89', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('90', '90', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('91', '91', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('92', '92', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('93', '93', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('94', '94', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('95', '95', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('96', '96', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('97', '97', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('98', '98', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('99', '99', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('100', '100', '2014-09-01', '未被借', '0');
INSERT INTO `booklist` VALUES ('101', '47', '2014-11-26', '未被借', '0');
INSERT INTO `booklist` VALUES ('113', '47', '2014-11-25', '已被借', '0');
INSERT INTO `booklist` VALUES ('122', '47', '2014-11-26', '未被借', '0');

-- ----------------------------
-- Table structure for `bookmessage`
-- ----------------------------
DROP TABLE IF EXISTS `bookmessage`;
CREATE TABLE `bookmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_kind` int(11) NOT NULL COMMENT '与类关联',
  `user_id` int(11) NOT NULL COMMENT '与人关联',
  `user_message` varchar(255) NOT NULL COMMENT '留言信息',
  `created_at` date NOT NULL COMMENT '创建日期',
  `updated_at` date NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookmessage
-- ----------------------------
INSERT INTO `bookmessage` VALUES ('1', '1', '1', '我看过这本', '2014-10-27', '0000-00-00');

-- ----------------------------
-- Table structure for `recommend`
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '推荐编号',
  `user_id` int(11) NOT NULL COMMENT '与人关联',
  `book_kind` int(11) NOT NULL COMMENT '与类关联',
  `rec_reason` varchar(255) NOT NULL COMMENT '推荐理由',
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL,
  `buy_link` text NOT NULL COMMENT '购买链接',
  `rec_type` varchar(255) NOT NULL COMMENT '推荐类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recommend
-- ----------------------------
INSERT INTO `recommend` VALUES ('1', '1', '1', '这书不错', '2014-10-26', '0000-00-00', '', '自选入库');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_id` int(11) NOT NULL COMMENT '用户学号',
  `user_name` varchar(255) NOT NULL COMMENT '用户姓名',
  `user_password` varchar(255) NOT NULL COMMENT '用户密码',
  `user_rank` varchar(255) NOT NULL DEFAULT '普通用户' COMMENT '用户等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '11101018', '李智', '1a48d1844d6ca6b456dd931d227f9843', '普通用户');
INSERT INTO `user` VALUES ('2', '12108129', '吴伟', '25946b36d3d2b1473b13492f4f3761b7', '普通用户');
INSERT INTO `user` VALUES ('3', '12108135', '张坚革', 'c52ab205539e00d8ac9e5117235fb6d3', '普通用户');
INSERT INTO `user` VALUES ('4', '12108139', '郑齐阳', '3bb9f156e763d3313a1527b1046cd99e', '普通用户');
INSERT INTO `user` VALUES ('5', '12108201', '白林林', '06267320e3540d239e2b14140b6cc67b', '普通用户');
INSERT INTO `user` VALUES ('6', '12108203', '彭雅丽', 'ca91f19d6d4f72aba7e085faf9a4caca', '普通用户');
INSERT INTO `user` VALUES ('7', '12108206', '边涛', 'c9b866aad15452c1b9901c2911ff5afb', '普通用户');
INSERT INTO `user` VALUES ('8', '12108208', '陈浩', 'e5d723350c7164a530769048b7547c96', '普通用户');
INSERT INTO `user` VALUES ('9', '12108209', '陈露耿', '41ca4c23cd320607c3fdfba9cc9ffdb5', '普通用户');
INSERT INTO `user` VALUES ('10', '12108210', '陈轲', 'eaa449211dd0cf977a026ebcdf2f3fe9', '普通用户');
INSERT INTO `user` VALUES ('11', '12108211', '段鹏飞', '2880067adbe0aca9e828e647b1569ddc', '普通用户');
INSERT INTO `user` VALUES ('12', '12108216', '李金祥', 'd6962e4c46547fc647cb2d9189e8b74c', '普通用户');
INSERT INTO `user` VALUES ('13', '12108222', '沈之川', 'ac677272ad2b6480bc834aa15a681e3c', '普通用户');
INSERT INTO `user` VALUES ('14', '12108227', '熊君睿', '0e44b4c6c34dc8ce18c01770219ca1e1', '普通用户');
INSERT INTO `user` VALUES ('15', '12108230', '章晨昱', '84b1df4354bdce1324009d97073e3856', '普通用户');
INSERT INTO `user` VALUES ('16', '12108234', '张旭', 'fec6b9b77656406c09ef0c06017f252a', '普通用户');
INSERT INTO `user` VALUES ('17', '12108238', '钟云昶', '123', '图书管理');
INSERT INTO `user` VALUES ('18', '12108305', '王雪莹', '12bb5766cc55ef0e09aa196e20959729', '普通用户');
INSERT INTO `user` VALUES ('19', '12108306', '曹平涛', 'c304fb8318c7a552a4e241e1b3c5c2dc', '普通用户');
INSERT INTO `user` VALUES ('20', '12108309', '程彦', '2f7a10e0faa742d8f5f4665d6e4f3e05', '普通用户');
INSERT INTO `user` VALUES ('21', '12108315', '刘宏志', '416418370ccae48a1a8861a78860902f', '普通用户');
INSERT INTO `user` VALUES ('22', '12108316', '刘铁', '770653b67a74cf253287e0536970c779', '普通用户');
INSERT INTO `user` VALUES ('23', '12108318', '罗鹏展', '2733b73426c8d43db20f607f886f51f7', '普通用户');
INSERT INTO `user` VALUES ('24', '12108327', '谢俊杰', 'b6c63bc373200d84c0020d7f5b7c6ef2', '购书管理');
INSERT INTO `user` VALUES ('25', '12108331', '许鹏飞', '5370b00a2b3f1a75512ee6facf57127d', '普通用户');
INSERT INTO `user` VALUES ('26', '12108413', '黄可庆', '12345', '普通用户');
INSERT INTO `user` VALUES ('27', '99', 'adfa', '59cf4aae5d59e8ac95ae7e2590919b58', '普通用户');
INSERT INTO `user` VALUES ('28', '98', 'qwe', '59cf4aae5d59e8ac95ae7e2590919b58', '普通用户');
