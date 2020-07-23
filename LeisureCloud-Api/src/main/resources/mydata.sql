/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : mydata

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2020-02-04 04:51:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crm_news
-- ----------------------------
DROP TABLE IF EXISTS `crm_news`;
CREATE TABLE `crm_news` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `newsTitle` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `newsContent` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `newImages` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `newsTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of crm_news
-- ----------------------------
INSERT INTO `crm_news` VALUES ('1', '缘尽世间、都是夜风惹的祸', '鞠婧炜是位四川的姑娘，90后，是SNH48的成员。什么是SNH48?SNH48是由上海丝芭文化传媒有限公司打造的中国本土化大型女子偶像团体，取“上海”的“ShangHai”的拼音缩写从而组成“SNH48”。SNH48有112名正式成员，分为SNH48 Team SII、SNH48 Team NII、SNH48 Team HII、SNH48 Team X、SNH48 Team XII五个队伍。', 'http://www.chachaba.com/news/uploads/180727/5034_180727152405_1.jpg', '2020-02-04 04:50:36');
INSERT INTO `crm_news` VALUES ('2', '知性女人', '所谓知性女人应该是举止优雅、让人一见赏心悦目的那种，待人处事落落大方，她用身体语言告诉你，她是一个时尚的、得体的、尊重别人、爱惜自己、懂得生活的女人，她的女性魅力和她的处事能力一样令人刮目相看。 自强、自立、自信的新时代女人。', 'https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=63c90ec9d000baa1be2c40b97711b9b1/5bafa40f4bfbfbed5790f55a79f0f736aec31fce.jpg', '2020-02-04 04:50:39');
INSERT INTO `crm_news` VALUES ('3', '美女', '美女是一个汉语词汇，拼音是：měi nǚ，释义：容貌姣好、仪态优雅的女子。中国古代关于美女的形容词和诗词歌赋众多，形成了丰富的美学资料。', 'https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1925573271,3224701245&fm=26&gp=0.jpg', '2020-02-04 04:50:42');

-- ----------------------------
-- Table structure for crm_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `crm_sys_menu`;
CREATE TABLE `crm_sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) NOT NULL COMMENT '上级菜单',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_icon` varchar(30) DEFAULT NULL COMMENT '图标样式',
  `menu_url` varchar(1000) DEFAULT NULL COMMENT '链接',
  `type` tinyint(1) NOT NULL COMMENT '菜单类型 1：菜单 2：按钮',
  `permission` varchar(50) DEFAULT NULL COMMENT '按钮权限',
  `sequence` int(11) NOT NULL COMMENT '排序',
  `isEnable` int(1) DEFAULT NULL COMMENT '状态 0.已停用 1.正常',
  `isDel` int(1) DEFAULT NULL COMMENT '是否删除（0.已删除 1.正常）',
  `createTime` varchar(22) DEFAULT NULL,
  `updateTime` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单表';

-- ----------------------------
-- Records of crm_sys_menu
-- ----------------------------
INSERT INTO `crm_sys_menu` VALUES ('1', '0', '系统管理', null, '-', '1', null, '1', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('2', '1', '用户管理', null, 'goHtml?tableName=crm_sys_user&pagehtml=user', '1', null, '2', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('3', '1', '角色管理', null, 'goHtml?tableName=crm_sys_role&pagehtml=role', '1', null, '3', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('4', '1', '权限管理', null, 'goHtml?tableName=crm_sys_role_menu&pagehtml=personalCalendar', '1', null, '4', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('5', '1', '菜单管理', null, 'goHtml?tableName=crm_sys_menu&pagehtml=personalCalendar', '1', null, '5', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('6', '0', '日志管理', null, '-', '1', null, '6', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('7', '6', '日志分析', null, 'goHtml?tableName=sys_log&pagehtml=personalCalendar', '1', null, '7', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('8', '0', '任务管理', null, '-', '1', null, '8', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('9', '8', '任务分析', null, 'goHtml?tableName=sys_job&pagehtml=personalCalendar', '1', null, '9', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('10', '0', '分布式Session', null, '-', '1', null, '10', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('11', '10', 'Session管理', null, 'goHtml?tableName=SPRING_SESSION&pagehtml=user', '1', null, '11', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('12', '10', 'Session数据', null, 'goHtml?tableName=SPRING_SESSION_ATTRIBUTES&pagehtml=user', '1', null, '12', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('13', '0', '分布式节点', null, '-', '1', null, '13', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('14', '13', '可用节点', null, 'goServices?type=0&pagehtml=personalCalendar', '1', null, '14', '1', '1', null, null);
INSERT INTO `crm_sys_menu` VALUES ('15', '13', '可用网关', null, 'goServices?type=1&pagehtml=personalCalendar', '1', null, '15', '1', '1', null, null);

-- ----------------------------
-- Table structure for crm_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `crm_sys_role`;
CREATE TABLE `crm_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `isEnable` tinyint(1) DEFAULT NULL COMMENT '状态 0.已停用 1.正常',
  `isDel` tinyint(1) DEFAULT NULL COMMENT '是否删除（0.已删除 1.正常）',
  `createTime` varchar(22) DEFAULT NULL,
  `updateTime` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';

-- ----------------------------
-- Records of crm_sys_role
-- ----------------------------
INSERT INTO `crm_sys_role` VALUES ('1', '管理员', null, '1', '1', null, null);
INSERT INTO `crm_sys_role` VALUES ('2', '操作员', null, '1', '1', null, null);

-- ----------------------------
-- Table structure for crm_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `crm_sys_role_menu`;
CREATE TABLE `crm_sys_role_menu` (
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色与菜单关联表';

-- ----------------------------
-- Records of crm_sys_role_menu
-- ----------------------------
INSERT INTO `crm_sys_role_menu` VALUES ('1', '1');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '2');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '3');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '4');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '5');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '6');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '7');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '8');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '9');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '10');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '11');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '12');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '13');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '14');
INSERT INTO `crm_sys_role_menu` VALUES ('1', '15');

-- ----------------------------
-- Table structure for crm_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `crm_sys_user`;
CREATE TABLE `crm_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '系统用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `headImgUrl` varchar(255) DEFAULT NULL COMMENT '头像',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `telephone` varchar(30) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `birthday` varchar(10) DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `isEnable` int(1) DEFAULT NULL COMMENT '状态 0.已停用 1.正常',
  `isDel` int(1) DEFAULT NULL COMMENT '是否删除（0.已删除 1.正常）',
  `createTime` varchar(22) DEFAULT NULL,
  `updateTime` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44445 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- ----------------------------
-- Records of crm_sys_user
-- ----------------------------
INSERT INTO `crm_sys_user` VALUES ('1', 'admin', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('2', '1', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('3', 'admin2', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('5', 'admin3', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('6', 'admin4', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('7', 'admin5', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('8', 'admin6', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('9', 'admin7', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('10', 'admin8', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('11', 'admin9', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('12', 'admin10', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('13', 'admin11', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('14', 'admin12', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('44', '1admin9', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('55', '1admin11', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('77', '77', '888', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `crm_sys_user` VALUES ('111', 'admin21', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('121', '12313', '323', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `crm_sys_user` VALUES ('122', 'admin71', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('222', 'admin61', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('234', '234', '234', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `crm_sys_user` VALUES ('322', 'admin51', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('333', '1admin8', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('444', '1admin10', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('666', '55', '55', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `crm_sys_user` VALUES ('1111', 'admin31', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('2211', '111', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('11111', 'admin41', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('11112', '1admin111', 'admin', null, null, null, null, null, '1', null, null, '1', null, null);
INSERT INTO `crm_sys_user` VALUES ('13123', '23', '323', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `crm_sys_user` VALUES ('44444', '4444', '44', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for crm_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `crm_sys_user_role`;
CREATE TABLE `crm_sys_user_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户与角色关联表';

-- ----------------------------
-- Records of crm_sys_user_role
-- ----------------------------
INSERT INTO `crm_sys_user_role` VALUES ('1', '1');
INSERT INTO `crm_sys_user_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for spring_session
-- ----------------------------
DROP TABLE IF EXISTS `spring_session`;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of spring_session
-- ----------------------------
INSERT INTO `spring_session` VALUES ('257d7adc-5cdc-4b0d-9de0-0fd58d99c201', 'a28a164b-32db-4af8-b01b-c5edadee0959', '1576882132539', '1576882153221', '300', '1576882453221', null);

-- ----------------------------
-- Table structure for spring_session_attributes
-- ----------------------------
DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of spring_session_attributes
-- ----------------------------
INSERT INTO `spring_session_attributes` VALUES ('257d7adc-5cdc-4b0d-9de0-0fd58d99c201', 'user_info_in_the_session', 0xACED00057400247B226964223A312C22726F6C65223A312C22757365726E616D65223A2261646D696E227D);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `jobId` int(11) NOT NULL AUTO_INCREMENT,
  `beanName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `methodName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `methodParams` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cronExpression` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `jobStatus` tinyint(3) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('10', 'sysServiceImpl', 'job', '111', '0/2 * * * * ?', null, '1', null, null);
INSERT INTO `sys_job` VALUES ('11', 'sysServiceImpl', 'job', '111', '0/2 * * * * ?', null, '1', null, null);
INSERT INTO `sys_job` VALUES ('12', 'sysServiceImpl', 'job', '111', '0/2 * * * * ?', null, '1', null, null);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `operationLog` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=652 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('328', 'com.drops.service.impl.GenericServiceImpl.selectByParem');
INSERT INTO `sys_log` VALUES ('329', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('330', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('331', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('332', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('333', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('334', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('335', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('336', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('337', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('338', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('339', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('340', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('341', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('342', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('343', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('344', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('345', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('346', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('347', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('348', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('349', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('350', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('351', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('352', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('353', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('354', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('355', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('356', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('357', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('358', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('359', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('360', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('361', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('362', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('363', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('364', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('365', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('366', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('367', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('368', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('369', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('370', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('371', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('372', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('373', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('374', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('375', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('376', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('377', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('378', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('379', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('380', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('381', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('382', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('383', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('384', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('385', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('386', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('387', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('388', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('389', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('390', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('391', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('392', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('393', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('394', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('395', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('396', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('397', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('398', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('399', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('400', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('401', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('402', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('403', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('404', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('405', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('406', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('407', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('408', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('409', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('410', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('411', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('412', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('413', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('414', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('415', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('416', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('417', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('418', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('419', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('420', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('421', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('422', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('423', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('424', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('425', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('426', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('427', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('428', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('429', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('430', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('431', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('432', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('433', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('434', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('435', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('436', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('437', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('438', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('439', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('440', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('441', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('442', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('443', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('444', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('445', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('446', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('447', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('448', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('449', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('450', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('451', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('452', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('453', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('454', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('455', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('456', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('457', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('458', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('459', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('460', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('461', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('462', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('463', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('464', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('465', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('466', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('467', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('468', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('469', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('470', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('471', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('472', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('473', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('474', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('475', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('476', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('477', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('478', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('479', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('480', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('481', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('482', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('483', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('484', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('485', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('486', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('487', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('488', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('489', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('490', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('491', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('492', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('493', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('494', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('495', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('496', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('497', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('498', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('499', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('500', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('501', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('502', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('503', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('504', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('505', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('506', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('507', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('508', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('509', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('510', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('511', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('512', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('513', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('514', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('515', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('516', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('517', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('518', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('519', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('520', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('521', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('522', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('523', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('524', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('525', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('526', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('527', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('528', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('529', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('530', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('531', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('532', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('533', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('534', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('535', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('536', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('537', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('538', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('539', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('540', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('541', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('542', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('543', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('544', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('545', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('546', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('547', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('548', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('549', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('550', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('551', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('552', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('553', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('554', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('555', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('556', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('557', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('558', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('559', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('560', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('561', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('562', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('563', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('564', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('565', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('566', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('567', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('568', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('569', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('570', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('571', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('572', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('573', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('574', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('575', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('576', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('577', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('578', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('579', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('580', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('581', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('582', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('583', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('584', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('585', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('586', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('587', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('588', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('589', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('590', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('591', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('592', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('593', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('594', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('595', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('596', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('597', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('598', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('599', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('600', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('601', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('602', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('603', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('604', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('605', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('606', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('607', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('608', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('609', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('610', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('611', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('612', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('613', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('614', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('615', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('616', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('617', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('618', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('619', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('620', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('621', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('622', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('623', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('624', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('625', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('626', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('627', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('628', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('629', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('630', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('631', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('632', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('633', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('634', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('635', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('636', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('637', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('638', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('639', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('640', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('641', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('642', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('643', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('644', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('645', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('646', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('647', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('648', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('649', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('650', 'com.drops.service.impl.GenericServiceImpl.insert');
INSERT INTO `sys_log` VALUES ('651', 'com.drops.service.impl.GenericServiceImpl.insert');

-- ----------------------------
-- Table structure for sys_table_mapper
-- ----------------------------
DROP TABLE IF EXISTS `sys_table_mapper`;
CREATE TABLE `sys_table_mapper` (
  `id` bigint(11) NOT NULL,
  `tablename` varchar(110) DEFAULT NULL,
  `filed` varchar(110) DEFAULT NULL,
  `remark` varchar(110) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_table_mapper
-- ----------------------------
INSERT INTO `sys_table_mapper` VALUES ('1', 'crm_contact', 'id,c_name', '主键,名称');
INSERT INTO `sys_table_mapper` VALUES ('2', 'crm_sys_role', 'id,name', '主键,名称');
INSERT INTO `sys_table_mapper` VALUES ('3', 'crm_sys_menu', 'menu_id,menu_url', '菜单标识,链接');
