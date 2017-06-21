/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : contractdb

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2017-06-21 20:06:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `customer` varchar(40) DEFAULT NULL,
  `num` varchar(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `beginTime` date NOT NULL,
  `endTime` date NOT NULL,
  `content` text NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('21', '3', 'HB', '2017062112342974037', '合同1', '2016-06-06', '2017-08-09', '呵呵\r\n', '0');
INSERT INTO `contract` VALUES ('22', '3', 'Jack Wang', '2017062106294139612', '合同2', '2000-01-01', '2009-03-03', '分手大师', '0');

-- ----------------------------
-- Table structure for contract_attachment
-- ----------------------------
DROP TABLE IF EXISTS `contract_attachment`;
CREATE TABLE `contract_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `con_id` int(11) NOT NULL,
  `fileName` varchar(40) NOT NULL,
  `path` varchar(100) NOT NULL,
  `type` varchar(10) NOT NULL,
  `uploadTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for contract_process
-- ----------------------------
DROP TABLE IF EXISTS `contract_process`;
CREATE TABLE `contract_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `con_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  `content` text,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_contract_process` (`con_id`),
  KEY `FK_process_user` (`user_id`),
  CONSTRAINT `FK_contract_process` FOREIGN KEY (`con_id`) REFERENCES `contract` (`id`),
  CONSTRAINT `FK_process_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract_process
-- ----------------------------
INSERT INTO `contract_process` VALUES ('79', '21', '3', '1', '1', '好', '2017-06-21 12:35:13', '0');
INSERT INTO `contract_process` VALUES ('80', '21', '3', '2', '1', '好', '2017-06-21 12:36:30', '0');
INSERT INTO `contract_process` VALUES ('81', '21', '3', '3', '1', '好的', '2017-06-21 12:34:58', '0');
INSERT INTO `contract_process` VALUES ('82', '22', '3', '1', '1', 'ok', '2017-06-21 18:30:21', '0');
INSERT INTO `contract_process` VALUES ('83', '22', '3', '2', '1', '给对方发生', '2017-06-21 18:31:34', '0');
INSERT INTO `contract_process` VALUES ('84', '22', '3', '3', '1', '666', '2017-06-21 18:30:07', '0');

-- ----------------------------
-- Table structure for contract_state
-- ----------------------------
DROP TABLE IF EXISTS `contract_state`;
CREATE TABLE `contract_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `con_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_belong` (`con_id`),
  CONSTRAINT `FK_belong` FOREIGN KEY (`con_id`) REFERENCES `contract` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract_state
-- ----------------------------
INSERT INTO `contract_state` VALUES ('62', '21', '1', '2017-06-21 12:34:32', '0');
INSERT INTO `contract_state` VALUES ('63', '21', '2', '2017-06-21 12:35:15', '0');
INSERT INTO `contract_state` VALUES ('64', '21', '3', '2017-06-21 12:36:21', '0');
INSERT INTO `contract_state` VALUES ('65', '21', '4', '2017-06-21 12:36:32', '0');
INSERT INTO `contract_state` VALUES ('66', '21', '5', '2017-06-21 12:36:40', '0');
INSERT INTO `contract_state` VALUES ('67', '22', '1', '2017-06-21 18:29:44', '0');
INSERT INTO `contract_state` VALUES ('68', '22', '2', '2017-06-21 18:30:23', '0');
INSERT INTO `contract_state` VALUES ('69', '22', '3', '2017-06-21 18:30:37', '0');
INSERT INTO `contract_state` VALUES ('70', '22', '4', '2017-06-21 18:31:37', '0');
INSERT INTO `contract_state` VALUES ('71', '22', '5', '2017-06-21 18:31:48', '0');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(20) DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `address` varchar(200) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `bank` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('2', 'Cus20131211182300002', 'BJ company', 'beijing', '22222222', '34213467', '100000', 'Agricultural Bank of China', '622848***', '0');
INSERT INTO `customer` VALUES ('3', 'Cus20131211182300003', 'Jack Wang', 'Shanghai', '14231116', '45678234', '200000', 'Industrial and Commercial Bank of China Limited', '530990***', '0');
INSERT INTO `customer` VALUES ('4', null, 'qqqq', '四季度开始的', '1325353', '1224353', '24353', '北京银行', '243243535', '0');
INSERT INTO `customer` VALUES ('7', null, '钱洋', '北京交大', '15088721518', '34234234', '100044', '中国银行', '66039034920', '0');
INSERT INTO `customer` VALUES ('13', null, '向富宁', '北交', '34234234', '543534', '454353465', '华夏银行', '34982394234', '0');

-- ----------------------------
-- Table structure for function
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of function
-- ----------------------------
INSERT INTO `function` VALUES ('1', '001', 'QCHT', null, null, '0');
INSERT INTO `function` VALUES ('2', '002', 'DGHT', null, null, '0');
INSERT INTO `function` VALUES ('3', '003', 'CXHT', null, null, '0');
INSERT INTO `function` VALUES ('4', '004', 'SCHT', null, null, '0');
INSERT INTO `function` VALUES ('5', '005', 'HQHT', null, null, '0');
INSERT INTO `function` VALUES ('6', '006', 'SPHT', null, null, '0');
INSERT INTO `function` VALUES ('7', '007', 'QDHT', null, null, '0');
INSERT INTO `function` VALUES ('8', '008', 'FPHQ', null, null, '0');
INSERT INTO `function` VALUES ('9', '009', 'FPSP', null, null, '0');
INSERT INTO `function` VALUES ('10', '010', 'FPQD', null, null, '0');
INSERT INTO `function` VALUES ('11', '011', 'LCCX', null, null, '0');
INSERT INTO `function` VALUES ('12', '012', 'XZYH', null, null, '0');
INSERT INTO `function` VALUES ('13', '013', 'BJYH', null, null, '0');
INSERT INTO `function` VALUES ('14', '014', 'CXYH', null, null, '0');
INSERT INTO `function` VALUES ('15', '015', 'SCYH', null, null, '0');
INSERT INTO `function` VALUES ('16', '016', 'XZJS', null, null, '0');
INSERT INTO `function` VALUES ('17', '017', 'BJJS', null, null, '0');
INSERT INTO `function` VALUES ('18', '018', 'CXJS', null, null, '0');
INSERT INTO `function` VALUES ('19', '019', 'SCJS', null, null, '0');
INSERT INTO `function` VALUES ('20', '020', 'XZGN', null, null, '0');
INSERT INTO `function` VALUES ('21', '021', 'BJGN', null, null, '0');
INSERT INTO `function` VALUES ('22', '022', 'CXGN', null, null, '0');
INSERT INTO `function` VALUES ('23', '023', 'SCGN', null, null, '0');
INSERT INTO `function` VALUES ('24', '024', 'PZQX', null, null, '0');
INSERT INTO `function` VALUES ('25', '025', 'XZKH', null, null, '0');
INSERT INTO `function` VALUES ('26', '026', 'BJKH', null, null, '0');
INSERT INTO `function` VALUES ('27', '027', 'CXKH', null, null, '0');
INSERT INTO `function` VALUES ('28', '028', 'SCKH', null, null, '0');
INSERT INTO `function` VALUES ('29', '029', 'CXRZ', null, null, '0');
INSERT INTO `function` VALUES ('30', '030', 'SCRZ', null, null, '0');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1', '2', '2017-06-14 15:44:23', '列表', '0');
INSERT INTO `t_log` VALUES ('2', '3', '2017-06-14 15:47:12', '列表', '0');
INSERT INTO `t_log` VALUES ('3', '0', '2017-06-14 15:47:59', '起草合同', '0');
INSERT INTO `t_log` VALUES ('4', '0', '2017-06-14 15:48:23', '得到合同', '0');
INSERT INTO `t_log` VALUES ('5', '0', '2017-06-14 15:59:30', '得到合同', '0');
INSERT INTO `t_log` VALUES ('6', '3', '2017-06-14 15:59:43', '分配合同', '0');
INSERT INTO `t_log` VALUES ('7', '3', '2017-06-14 15:59:43', '分配合同', '0');
INSERT INTO `t_log` VALUES ('8', '3', '2017-06-14 15:59:43', '分配合同', '0');
INSERT INTO `t_log` VALUES ('9', '0', '2017-06-14 15:59:59', '得到合同', '0');
INSERT INTO `t_log` VALUES ('10', '3', '2017-06-14 16:00:01', '会签', '0');
INSERT INTO `t_log` VALUES ('11', '3', '2017-06-14 16:00:02', '得到列表', '0');
INSERT INTO `t_log` VALUES ('12', '3', '2017-06-14 16:00:04', '意见查看', '0');
INSERT INTO `t_log` VALUES ('13', '3', '2017-06-14 16:00:20', '意见查看', '0');
INSERT INTO `t_log` VALUES ('14', '3', '2017-06-14 16:00:21', '意见查看', '0');
INSERT INTO `t_log` VALUES ('15', '3', '2017-06-14 16:00:22', '意见查看', '0');
INSERT INTO `t_log` VALUES ('16', '3', '2017-06-14 16:00:22', '意见查看', '0');
INSERT INTO `t_log` VALUES ('17', '3', '2017-06-14 16:00:22', '意见查看', '0');
INSERT INTO `t_log` VALUES ('18', '3', '2017-06-14 16:00:23', '意见查看', '0');
INSERT INTO `t_log` VALUES ('19', '3', '2017-06-14 16:00:23', '意见查看', '0');
INSERT INTO `t_log` VALUES ('20', '3', '2017-06-14 16:00:59', '列表', '0');
INSERT INTO `t_log` VALUES ('21', '3', '2017-06-14 16:05:40', '列表', '0');
INSERT INTO `t_log` VALUES ('22', '3', '2017-06-14 16:05:50', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('23', '3', '2017-06-14 16:05:53', '得到合同', '0');
INSERT INTO `t_log` VALUES ('24', '3', '2017-06-14 16:06:11', '得到合同', '0');
INSERT INTO `t_log` VALUES ('25', '3', '2017-06-14 16:06:11', '定稿', '0');
INSERT INTO `t_log` VALUES ('26', '3', '2017-06-14 16:06:11', '列表', '0');
INSERT INTO `t_log` VALUES ('27', '3', '2017-06-14 16:13:49', '列表', '0');
INSERT INTO `t_log` VALUES ('28', '3', '2017-06-14 16:14:26', '得到列表', '0');
INSERT INTO `t_log` VALUES ('29', '3', '2017-06-14 16:14:50', '意见查看', '0');
INSERT INTO `t_log` VALUES ('30', '3', '2017-06-14 16:15:28', '意见查看', '0');
INSERT INTO `t_log` VALUES ('31', '3', '2017-06-14 16:16:08', '意见查看', '0');
INSERT INTO `t_log` VALUES ('32', '3', '2017-06-14 16:16:09', '意见查看', '0');
INSERT INTO `t_log` VALUES ('33', '3', '2017-06-14 16:16:16', '意见查看', '0');
INSERT INTO `t_log` VALUES ('34', '3', '2017-06-14 16:16:17', '意见查看', '0');
INSERT INTO `t_log` VALUES ('35', '3', '2017-06-14 16:16:40', '得到列表', '0');
INSERT INTO `t_log` VALUES ('36', '3', '2017-06-14 16:16:43', '意见查看', '0');
INSERT INTO `t_log` VALUES ('37', '3', '2017-06-14 16:16:45', '列表', '0');
INSERT INTO `t_log` VALUES ('38', '3', '2017-06-14 16:20:05', '列表', '0');
INSERT INTO `t_log` VALUES ('39', '3', '2017-06-14 16:20:13', '意见查看', '0');
INSERT INTO `t_log` VALUES ('40', '3', '2017-06-14 16:20:14', '列表', '0');
INSERT INTO `t_log` VALUES ('41', '3', '2017-06-14 16:20:20', '意见查看', '0');
INSERT INTO `t_log` VALUES ('42', '3', '2017-06-14 16:21:34', '得到列表', '0');
INSERT INTO `t_log` VALUES ('43', '3', '2017-06-14 16:21:35', '意见查看', '0');
INSERT INTO `t_log` VALUES ('44', '3', '2017-06-14 16:23:06', '意见查看', '0');
INSERT INTO `t_log` VALUES ('45', '3', '2017-06-14 16:23:59', '意见查看', '0');
INSERT INTO `t_log` VALUES ('46', '3', '2017-06-14 16:28:10', '意见查看', '0');
INSERT INTO `t_log` VALUES ('47', '3', '2017-06-14 16:28:11', '得到列表', '0');
INSERT INTO `t_log` VALUES ('48', '3', '2017-06-14 16:52:15', '意见查看', '0');
INSERT INTO `t_log` VALUES ('49', '9', '2017-06-14 17:03:28', '起草合同', '0');
INSERT INTO `t_log` VALUES ('50', '9', '2017-06-14 17:03:46', '得到合同', '0');
INSERT INTO `t_log` VALUES ('51', '9', '2017-06-14 17:04:02', '分配合同', '0');
INSERT INTO `t_log` VALUES ('52', '9', '2017-06-14 17:04:02', '分配合同', '0');
INSERT INTO `t_log` VALUES ('53', '9', '2017-06-14 17:04:02', '分配合同', '0');
INSERT INTO `t_log` VALUES ('54', '9', '2017-06-14 17:04:12', '列表', '0');
INSERT INTO `t_log` VALUES ('55', '9', '2017-06-14 17:04:22', '得到合同', '0');
INSERT INTO `t_log` VALUES ('56', '9', '2017-06-14 17:04:25', '会签', '0');
INSERT INTO `t_log` VALUES ('57', '9', '2017-06-14 17:04:26', '得到列表', '0');
INSERT INTO `t_log` VALUES ('58', '9', '2017-06-14 17:04:28', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('59', '9', '2017-06-14 17:04:29', '得到合同', '0');
INSERT INTO `t_log` VALUES ('60', '9', '2017-06-14 17:04:34', '得到列表', '0');
INSERT INTO `t_log` VALUES ('61', '9', '2017-06-14 17:04:35', '意见查看', '0');
INSERT INTO `t_log` VALUES ('62', '9', '2017-06-14 17:04:36', '得到列表', '0');
INSERT INTO `t_log` VALUES ('63', '9', '2017-06-14 17:04:39', '意见查看', '0');
INSERT INTO `t_log` VALUES ('64', '9', '2017-06-14 17:04:40', '得到列表', '0');
INSERT INTO `t_log` VALUES ('65', '9', '2017-06-14 17:04:41', '意见查看', '0');
INSERT INTO `t_log` VALUES ('66', '9', '2017-06-14 17:04:42', '得到列表', '0');
INSERT INTO `t_log` VALUES ('67', '9', '2017-06-14 17:04:53', '列表', '0');
INSERT INTO `t_log` VALUES ('68', '9', '2017-06-14 17:05:02', '得到合同', '0');
INSERT INTO `t_log` VALUES ('69', '9', '2017-06-14 17:05:05', '得到合同', '0');
INSERT INTO `t_log` VALUES ('70', '9', '2017-06-14 17:05:05', '修改合同', '0');
INSERT INTO `t_log` VALUES ('71', '9', '2017-06-14 17:07:05', '得到合同', '0');
INSERT INTO `t_log` VALUES ('72', '9', '2017-06-14 17:07:08', '得到合同', '0');
INSERT INTO `t_log` VALUES ('73', '9', '2017-06-14 17:07:08', '修改合同', '0');
INSERT INTO `t_log` VALUES ('74', '9', '2017-06-14 17:07:35', '列表', '0');
INSERT INTO `t_log` VALUES ('75', '9', '2017-06-14 17:07:39', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('76', '9', '2017-06-14 17:07:41', '得到合同', '0');
INSERT INTO `t_log` VALUES ('77', '9', '2017-06-14 17:07:44', '得到合同', '0');
INSERT INTO `t_log` VALUES ('78', '9', '2017-06-14 17:07:44', '修改合同', '0');
INSERT INTO `t_log` VALUES ('79', '9', '2017-06-14 17:09:55', '列表', '0');
INSERT INTO `t_log` VALUES ('80', '9', '2017-06-14 17:09:55', '列表', '0');
INSERT INTO `t_log` VALUES ('81', '9', '2017-06-14 17:09:56', '列表', '0');
INSERT INTO `t_log` VALUES ('82', '9', '2017-06-14 17:09:59', '得到合同', '0');
INSERT INTO `t_log` VALUES ('83', '9', '2017-06-14 17:10:01', '得到合同', '0');
INSERT INTO `t_log` VALUES ('84', '9', '2017-06-14 17:10:01', '修改合同', '0');
INSERT INTO `t_log` VALUES ('85', '9', '2017-06-14 17:10:01', '列表', '0');
INSERT INTO `t_log` VALUES ('86', '9', '2017-06-14 17:10:07', '得到合同', '0');
INSERT INTO `t_log` VALUES ('87', '9', '2017-06-14 17:10:07', '定稿', '0');
INSERT INTO `t_log` VALUES ('88', '9', '2017-06-14 17:10:07', '列表', '0');
INSERT INTO `t_log` VALUES ('89', '9', '2017-06-14 17:10:14', '得到列表', '0');
INSERT INTO `t_log` VALUES ('90', '9', '2017-06-14 17:10:15', '意见查看', '0');
INSERT INTO `t_log` VALUES ('91', '9', '2017-06-14 17:10:16', '得到合同', '0');
INSERT INTO `t_log` VALUES ('92', '9', '2017-06-14 17:10:20', '同意', '0');
INSERT INTO `t_log` VALUES ('93', '9', '2017-06-14 17:10:20', '意见查看', '0');
INSERT INTO `t_log` VALUES ('94', '9', '2017-06-14 17:10:29', '得到合同', '0');
INSERT INTO `t_log` VALUES ('95', '9', '2017-06-14 17:12:32', '得到合同', '0');
INSERT INTO `t_log` VALUES ('96', '1', '2017-06-14 17:12:46', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('97', '1', '2017-06-14 17:12:49', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('98', '3', '2017-06-14 17:12:52', '得到合同', '0');
INSERT INTO `t_log` VALUES ('99', '1', '2017-06-14 17:13:11', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('100', '1', '2017-06-14 17:16:25', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('101', '3', '2017-06-14 17:16:50', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('102', '3', '2017-06-14 17:16:53', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('103', '9', '2017-06-14 17:17:05', '列表', '0');
INSERT INTO `t_log` VALUES ('104', '9', '2017-06-14 17:19:20', '列表', '0');
INSERT INTO `t_log` VALUES ('105', '9', '2017-06-14 17:19:21', '列表', '0');
INSERT INTO `t_log` VALUES ('106', '9', '2017-06-14 17:21:33', '得到列表', '0');
INSERT INTO `t_log` VALUES ('107', '9', '2017-06-14 17:21:45', '得到列表', '0');
INSERT INTO `t_log` VALUES ('108', '9', '2017-06-14 17:21:47', '意见查看', '0');
INSERT INTO `t_log` VALUES ('109', '9', '2017-06-14 17:22:04', '意见查看', '0');
INSERT INTO `t_log` VALUES ('110', '9', '2017-06-14 17:22:07', '意见查看', '0');
INSERT INTO `t_log` VALUES ('111', '9', '2017-06-14 17:22:12', '意见查看', '0');
INSERT INTO `t_log` VALUES ('112', '9', '2017-06-14 17:22:51', '意见查看', '0');
INSERT INTO `t_log` VALUES ('113', '9', '2017-06-14 17:23:50', '得到列表', '0');
INSERT INTO `t_log` VALUES ('114', '9', '2017-06-14 17:23:50', '意见查看', '0');
INSERT INTO `t_log` VALUES ('115', '9', '2017-06-14 17:24:42', '列表', '0');
INSERT INTO `t_log` VALUES ('116', '9', '2017-06-14 17:24:59', '列表', '0');
INSERT INTO `t_log` VALUES ('117', '9', '2017-06-14 17:25:13', '列表', '0');
INSERT INTO `t_log` VALUES ('118', '9', '2017-06-14 17:25:13', '列表', '0');
INSERT INTO `t_log` VALUES ('119', '9', '2017-06-14 17:38:46', '意见查看', '0');
INSERT INTO `t_log` VALUES ('120', '9', '2017-06-14 17:38:48', '意见查看', '0');
INSERT INTO `t_log` VALUES ('121', '9', '2017-06-14 17:38:50', '列表', '0');
INSERT INTO `t_log` VALUES ('122', '0', '2017-06-14 18:30:01', '得到合同', '0');
INSERT INTO `t_log` VALUES ('123', '3', '2017-06-14 18:30:13', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('124', '10', '2017-06-14 18:31:35', '意见查看', '0');
INSERT INTO `t_log` VALUES ('125', '10', '2017-06-14 18:31:41', '得到列表', '0');
INSERT INTO `t_log` VALUES ('126', '10', '2017-06-14 18:31:53', '起草合同', '0');
INSERT INTO `t_log` VALUES ('127', '10', '2017-06-14 18:32:07', '得到合同', '0');
INSERT INTO `t_log` VALUES ('128', '10', '2017-06-14 18:32:11', '得到合同', '0');
INSERT INTO `t_log` VALUES ('129', '10', '2017-06-14 18:32:18', '分配合同', '0');
INSERT INTO `t_log` VALUES ('130', '10', '2017-06-14 18:32:18', '分配合同', '0');
INSERT INTO `t_log` VALUES ('131', '10', '2017-06-14 18:32:18', '分配合同', '0');
INSERT INTO `t_log` VALUES ('132', '10', '2017-06-14 18:32:29', '列表', '0');
INSERT INTO `t_log` VALUES ('133', '10', '2017-06-14 18:32:33', '得到合同', '0');
INSERT INTO `t_log` VALUES ('134', '10', '2017-06-14 18:32:35', '会签', '0');
INSERT INTO `t_log` VALUES ('135', '10', '2017-06-14 18:32:39', '列表', '0');
INSERT INTO `t_log` VALUES ('136', '10', '2017-06-14 18:32:43', '得到合同', '0');
INSERT INTO `t_log` VALUES ('137', '10', '2017-06-14 18:32:45', '得到合同', '0');
INSERT INTO `t_log` VALUES ('138', '10', '2017-06-14 18:32:45', '修改合同', '0');
INSERT INTO `t_log` VALUES ('139', '10', '2017-06-14 18:32:45', '列表', '0');
INSERT INTO `t_log` VALUES ('140', '10', '2017-06-14 18:32:48', '得到合同', '0');
INSERT INTO `t_log` VALUES ('141', '10', '2017-06-14 18:32:48', '定稿', '0');
INSERT INTO `t_log` VALUES ('142', '10', '2017-06-14 18:32:48', '列表', '0');
INSERT INTO `t_log` VALUES ('143', '10', '2017-06-14 18:32:53', '得到列表', '0');
INSERT INTO `t_log` VALUES ('144', '10', '2017-06-14 18:32:55', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('145', '10', '2017-06-14 18:32:57', '意见查看', '0');
INSERT INTO `t_log` VALUES ('146', '10', '2017-06-14 18:32:58', '得到合同', '0');
INSERT INTO `t_log` VALUES ('147', '10', '2017-06-14 18:32:59', '同意', '0');
INSERT INTO `t_log` VALUES ('148', '10', '2017-06-14 18:32:59', '意见查看', '0');
INSERT INTO `t_log` VALUES ('149', '3', '2017-06-14 23:06:28', '列表', '0');
INSERT INTO `t_log` VALUES ('150', '3', '2017-06-14 23:06:31', '得到列表', '0');
INSERT INTO `t_log` VALUES ('151', '3', '2017-06-14 23:06:32', '意见查看', '0');
INSERT INTO `t_log` VALUES ('152', '3', '2017-06-14 23:06:35', '意见查看', '0');
INSERT INTO `t_log` VALUES ('153', '3', '2017-06-14 23:06:36', '得到列表', '0');
INSERT INTO `t_log` VALUES ('154', '3', '2017-06-14 23:07:07', '得到合同', '0');
INSERT INTO `t_log` VALUES ('155', '4', '2017-06-14 23:07:14', '分配合同', '0');
INSERT INTO `t_log` VALUES ('156', '4', '2017-06-14 23:07:14', '分配合同', '0');
INSERT INTO `t_log` VALUES ('157', '4', '2017-06-14 23:07:14', '分配合同', '0');
INSERT INTO `t_log` VALUES ('158', '3', '2017-06-14 23:12:03', '列表', '0');
INSERT INTO `t_log` VALUES ('159', '3', '2017-06-14 23:12:06', '得到列表', '0');
INSERT INTO `t_log` VALUES ('160', '3', '2017-06-14 23:12:06', '意见查看', '0');
INSERT INTO `t_log` VALUES ('161', '3', '2017-06-21 08:12:19', '列表', '0');
INSERT INTO `t_log` VALUES ('162', '3', '2017-06-21 08:12:22', '得到列表', '0');
INSERT INTO `t_log` VALUES ('163', '3', '2017-06-21 08:12:23', '意见查看', '0');
INSERT INTO `t_log` VALUES ('164', '3', '2017-06-21 11:02:28', '起草合同', '0');
INSERT INTO `t_log` VALUES ('165', '3', '2017-06-21 11:03:51', '列表', '0');
INSERT INTO `t_log` VALUES ('166', '3', '2017-06-21 11:05:01', '列表', '0');
INSERT INTO `t_log` VALUES ('167', '3', '2017-06-21 11:05:02', '列表', '0');
INSERT INTO `t_log` VALUES ('168', '3', '2017-06-21 11:05:11', '列表', '0');
INSERT INTO `t_log` VALUES ('169', '3', '2017-06-21 11:05:46', '得到列表', '0');
INSERT INTO `t_log` VALUES ('170', '3', '2017-06-21 11:07:11', '得到列表', '0');
INSERT INTO `t_log` VALUES ('171', '3', '2017-06-21 11:07:12', '得到列表', '0');
INSERT INTO `t_log` VALUES ('172', '3', '2017-06-21 11:07:16', '得到列表', '0');
INSERT INTO `t_log` VALUES ('173', '3', '2017-06-21 11:07:17', '意见查看', '0');
INSERT INTO `t_log` VALUES ('174', '3', '2017-06-21 11:09:23', '得到列表', '0');
INSERT INTO `t_log` VALUES ('175', '3', '2017-06-21 11:09:25', '意见查看', '0');
INSERT INTO `t_log` VALUES ('176', '3', '2017-06-21 11:09:29', '得到列表', '0');
INSERT INTO `t_log` VALUES ('177', '3', '2017-06-21 11:09:29', '意见查看', '0');
INSERT INTO `t_log` VALUES ('178', '3', '2017-06-21 11:10:15', '得到列表', '0');
INSERT INTO `t_log` VALUES ('179', '3', '2017-06-21 11:10:39', '意见查看', '0');
INSERT INTO `t_log` VALUES ('180', '3', '2017-06-21 11:11:27', '意见查看', '0');
INSERT INTO `t_log` VALUES ('181', '3', '2017-06-21 11:11:28', '意见查看', '0');
INSERT INTO `t_log` VALUES ('182', '3', '2017-06-21 11:11:35', '意见查看', '0');
INSERT INTO `t_log` VALUES ('183', '3', '2017-06-21 11:11:36', '意见查看', '0');
INSERT INTO `t_log` VALUES ('184', '3', '2017-06-21 11:13:04', '列表', '0');
INSERT INTO `t_log` VALUES ('185', '3', '2017-06-21 11:13:07', '列表', '0');
INSERT INTO `t_log` VALUES ('186', '3', '2017-06-21 11:13:14', '得到列表', '0');
INSERT INTO `t_log` VALUES ('187', '3', '2017-06-21 11:13:18', '意见查看', '0');
INSERT INTO `t_log` VALUES ('188', '3', '2017-06-21 11:13:22', '意见查看', '0');
INSERT INTO `t_log` VALUES ('189', '3', '2017-06-21 11:14:42', '得到列表', '0');
INSERT INTO `t_log` VALUES ('190', '3', '2017-06-21 11:14:43', '意见查看', '0');
INSERT INTO `t_log` VALUES ('191', '3', '2017-06-21 11:14:45', '列表', '0');
INSERT INTO `t_log` VALUES ('192', '3', '2017-06-21 11:15:09', '列表', '0');
INSERT INTO `t_log` VALUES ('193', '3', '2017-06-21 11:17:22', '得到列表', '0');
INSERT INTO `t_log` VALUES ('194', '3', '2017-06-21 11:17:23', '意见查看', '0');
INSERT INTO `t_log` VALUES ('195', '3', '2017-06-21 11:17:34', '意见查看', '0');
INSERT INTO `t_log` VALUES ('196', '3', '2017-06-21 11:17:34', '得到列表', '0');
INSERT INTO `t_log` VALUES ('197', '3', '2017-06-21 11:17:37', '得到合同', '0');
INSERT INTO `t_log` VALUES ('198', '3', '2017-06-21 11:18:03', '列表', '0');
INSERT INTO `t_log` VALUES ('199', '3', '2017-06-21 11:18:04', '得到列表', '0');
INSERT INTO `t_log` VALUES ('200', '3', '2017-06-21 11:18:05', '意见查看', '0');
INSERT INTO `t_log` VALUES ('201', '3', '2017-06-21 11:51:20', '列表', '0');
INSERT INTO `t_log` VALUES ('202', '3', '2017-06-21 12:22:45', '列表', '0');
INSERT INTO `t_log` VALUES ('203', '3', '2017-06-21 12:22:50', '意见查看', '0');
INSERT INTO `t_log` VALUES ('204', '3', '2017-06-21 12:22:50', '得到列表', '0');
INSERT INTO `t_log` VALUES ('205', '1', '2017-06-21 12:28:30', '列表', '0');
INSERT INTO `t_log` VALUES ('206', '3', '2017-06-21 12:30:39', '起草合同', '0');
INSERT INTO `t_log` VALUES ('207', '3', '2017-06-21 12:30:44', '列表', '0');
INSERT INTO `t_log` VALUES ('208', '3', '2017-06-21 12:30:58', '得到合同', '0');
INSERT INTO `t_log` VALUES ('209', '3', '2017-06-21 12:31:02', '得到合同', '0');
INSERT INTO `t_log` VALUES ('210', '3', '2017-06-21 12:31:18', '分配合同', '0');
INSERT INTO `t_log` VALUES ('211', '3', '2017-06-21 12:31:18', '分配合同', '0');
INSERT INTO `t_log` VALUES ('212', '3', '2017-06-21 12:31:18', '分配合同', '0');
INSERT INTO `t_log` VALUES ('213', '3', '2017-06-21 12:31:28', '列表', '0');
INSERT INTO `t_log` VALUES ('214', '3', '2017-06-21 12:31:45', '得到合同', '0');
INSERT INTO `t_log` VALUES ('215', '3', '2017-06-21 12:32:17', '列表', '0');
INSERT INTO `t_log` VALUES ('216', '3', '2017-06-21 12:32:21', '列表', '0');
INSERT INTO `t_log` VALUES ('217', '3', '2017-06-21 12:32:22', '列表', '0');
INSERT INTO `t_log` VALUES ('218', '3', '2017-06-21 12:32:22', '列表', '0');
INSERT INTO `t_log` VALUES ('219', '3', '2017-06-21 12:32:23', '列表', '0');
INSERT INTO `t_log` VALUES ('220', '3', '2017-06-21 12:32:23', '列表', '0');
INSERT INTO `t_log` VALUES ('221', '3', '2017-06-21 12:32:37', '得到合同', '0');
INSERT INTO `t_log` VALUES ('222', '3', '2017-06-21 12:32:44', '分配合同', '0');
INSERT INTO `t_log` VALUES ('223', '3', '2017-06-21 12:32:44', '分配合同', '0');
INSERT INTO `t_log` VALUES ('224', '3', '2017-06-21 12:32:44', '分配合同', '0');
INSERT INTO `t_log` VALUES ('225', '3', '2017-06-21 12:33:20', '列表', '0');
INSERT INTO `t_log` VALUES ('226', '3', '2017-06-21 12:33:22', '列表', '0');
INSERT INTO `t_log` VALUES ('227', '3', '2017-06-21 12:33:22', '列表', '0');
INSERT INTO `t_log` VALUES ('228', '3', '2017-06-21 12:33:23', '列表', '0');
INSERT INTO `t_log` VALUES ('229', '3', '2017-06-21 12:33:23', '列表', '0');
INSERT INTO `t_log` VALUES ('230', '3', '2017-06-21 12:33:26', '列表', '0');
INSERT INTO `t_log` VALUES ('231', '3', '2017-06-21 12:33:27', '列表', '0');
INSERT INTO `t_log` VALUES ('232', '3', '2017-06-21 12:33:27', '列表', '0');
INSERT INTO `t_log` VALUES ('233', '3', '2017-06-21 12:33:28', '列表', '0');
INSERT INTO `t_log` VALUES ('234', '3', '2017-06-21 12:33:29', '列表', '0');
INSERT INTO `t_log` VALUES ('235', '3', '2017-06-21 12:33:31', '列表', '0');
INSERT INTO `t_log` VALUES ('236', '3', '2017-06-21 12:33:31', '列表', '0');
INSERT INTO `t_log` VALUES ('237', '3', '2017-06-21 12:33:31', '列表', '0');
INSERT INTO `t_log` VALUES ('238', '3', '2017-06-21 12:33:35', '列表', '0');
INSERT INTO `t_log` VALUES ('239', '3', '2017-06-21 12:33:35', '列表', '0');
INSERT INTO `t_log` VALUES ('240', '3', '2017-06-21 12:34:30', '起草合同', '0');
INSERT INTO `t_log` VALUES ('241', '3', '2017-06-21 12:34:35', '列表', '0');
INSERT INTO `t_log` VALUES ('242', '3', '2017-06-21 12:34:37', '列表', '0');
INSERT INTO `t_log` VALUES ('243', '3', '2017-06-21 12:34:50', '得到合同', '0');
INSERT INTO `t_log` VALUES ('244', '3', '2017-06-21 12:34:56', '分配合同', '0');
INSERT INTO `t_log` VALUES ('245', '3', '2017-06-21 12:34:56', '分配合同', '0');
INSERT INTO `t_log` VALUES ('246', '3', '2017-06-21 12:34:56', '分配合同', '0');
INSERT INTO `t_log` VALUES ('247', '3', '2017-06-21 12:35:04', '列表', '0');
INSERT INTO `t_log` VALUES ('248', '3', '2017-06-21 12:35:06', '得到合同', '0');
INSERT INTO `t_log` VALUES ('249', '3', '2017-06-21 12:35:13', '会签', '0');
INSERT INTO `t_log` VALUES ('250', '3', '2017-06-21 12:35:16', '得到列表', '0');
INSERT INTO `t_log` VALUES ('251', '3', '2017-06-21 12:35:18', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('252', '3', '2017-06-21 12:35:27', '得到列表', '0');
INSERT INTO `t_log` VALUES ('253', '3', '2017-06-21 12:35:29', '列表', '0');
INSERT INTO `t_log` VALUES ('254', '3', '2017-06-21 12:35:36', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('255', '3', '2017-06-21 12:35:39', '得到合同', '0');
INSERT INTO `t_log` VALUES ('256', '3', '2017-06-21 12:35:50', '得到合同', '0');
INSERT INTO `t_log` VALUES ('257', '3', '2017-06-21 12:36:07', '得到合同', '0');
INSERT INTO `t_log` VALUES ('258', '3', '2017-06-21 12:36:14', '得到合同', '0');
INSERT INTO `t_log` VALUES ('259', '3', '2017-06-21 12:36:14', '修改合同', '0');
INSERT INTO `t_log` VALUES ('260', '3', '2017-06-21 12:36:14', '列表', '0');
INSERT INTO `t_log` VALUES ('261', '3', '2017-06-21 12:36:19', '得到合同', '0');
INSERT INTO `t_log` VALUES ('262', '3', '2017-06-21 12:36:19', '定稿', '0');
INSERT INTO `t_log` VALUES ('263', '3', '2017-06-21 12:36:19', '列表', '0');
INSERT INTO `t_log` VALUES ('264', '3', '2017-06-21 12:36:23', '得到列表', '0');
INSERT INTO `t_log` VALUES ('265', '3', '2017-06-21 12:36:25', '意见查看', '0');
INSERT INTO `t_log` VALUES ('266', '3', '2017-06-21 12:36:26', '得到合同', '0');
INSERT INTO `t_log` VALUES ('267', '3', '2017-06-21 12:36:30', '同意', '0');
INSERT INTO `t_log` VALUES ('268', '3', '2017-06-21 12:36:30', '意见查看', '0');
INSERT INTO `t_log` VALUES ('269', '3', '2017-06-21 12:37:06', '得到列表', '0');
INSERT INTO `t_log` VALUES ('270', '3', '2017-06-21 12:37:07', '意见查看', '0');
INSERT INTO `t_log` VALUES ('271', '3', '2017-06-21 12:37:14', '得到列表', '0');
INSERT INTO `t_log` VALUES ('272', '3', '2017-06-21 12:37:15', '意见查看', '0');
INSERT INTO `t_log` VALUES ('273', '3', '2017-06-21 12:37:25', '列表', '0');
INSERT INTO `t_log` VALUES ('274', '3', '2017-06-21 16:27:55', '列表', '0');
INSERT INTO `t_log` VALUES ('275', '3', '2017-06-21 16:28:30', '列表', '0');
INSERT INTO `t_log` VALUES ('276', '3', '2017-06-21 16:28:48', '列表', '0');
INSERT INTO `t_log` VALUES ('277', '3', '2017-06-21 16:29:15', '得到列表', '0');
INSERT INTO `t_log` VALUES ('278', '3', '2017-06-21 16:29:25', '得到列表', '0');
INSERT INTO `t_log` VALUES ('279', '3', '2017-06-21 16:29:27', '得到列表', '0');
INSERT INTO `t_log` VALUES ('280', '3', '2017-06-21 16:29:33', '得到列表', '0');
INSERT INTO `t_log` VALUES ('281', '3', '2017-06-21 16:29:35', '得到列表', '0');
INSERT INTO `t_log` VALUES ('282', '3', '2017-06-21 16:29:36', '意见查看', '0');
INSERT INTO `t_log` VALUES ('283', '3', '2017-06-21 16:29:51', '意见查看', '0');
INSERT INTO `t_log` VALUES ('284', '3', '2017-06-21 16:29:55', '意见查看', '0');
INSERT INTO `t_log` VALUES ('285', '3', '2017-06-21 16:29:57', '意见查看', '0');
INSERT INTO `t_log` VALUES ('286', '3', '2017-06-21 17:55:17', '得到列表', '0');
INSERT INTO `t_log` VALUES ('287', '3', '2017-06-21 17:55:18', '得到合同', '0');
INSERT INTO `t_log` VALUES ('288', '3', '2017-06-21 17:55:22', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('289', '3', '2017-06-21 17:55:24', '意见查看', '0');
INSERT INTO `t_log` VALUES ('290', '3', '2017-06-21 17:55:29', '得到合同', '0');
INSERT INTO `t_log` VALUES ('291', '3', '2017-06-21 17:55:41', '得到列表', '0');
INSERT INTO `t_log` VALUES ('292', '3', '2017-06-21 17:55:43', '列表', '0');
INSERT INTO `t_log` VALUES ('293', '3', '2017-06-21 17:59:47', '意见查看', '0');
INSERT INTO `t_log` VALUES ('294', '3', '2017-06-21 18:00:01', '得到列表', '0');
INSERT INTO `t_log` VALUES ('295', '3', '2017-06-21 18:00:02', '意见查看', '0');
INSERT INTO `t_log` VALUES ('296', '3', '2017-06-21 18:00:03', '得到列表', '0');
INSERT INTO `t_log` VALUES ('297', '3', '2017-06-21 18:29:41', '起草合同', '0');
INSERT INTO `t_log` VALUES ('298', '3', '2017-06-21 18:29:56', '得到合同', '0');
INSERT INTO `t_log` VALUES ('299', '3', '2017-06-21 18:30:05', '分配合同', '0');
INSERT INTO `t_log` VALUES ('300', '3', '2017-06-21 18:30:05', '分配合同', '0');
INSERT INTO `t_log` VALUES ('301', '3', '2017-06-21 18:30:05', '分配合同', '0');
INSERT INTO `t_log` VALUES ('302', '3', '2017-06-21 18:30:15', '列表', '0');
INSERT INTO `t_log` VALUES ('303', '3', '2017-06-21 18:30:16', '得到列表', '0');
INSERT INTO `t_log` VALUES ('304', '3', '2017-06-21 18:30:18', '得到合同', '0');
INSERT INTO `t_log` VALUES ('305', '3', '2017-06-21 18:30:21', '会签', '0');
INSERT INTO `t_log` VALUES ('306', '3', '2017-06-21 18:30:22', '得到列表', '0');
INSERT INTO `t_log` VALUES ('307', '3', '2017-06-21 18:30:26', '得到合同意见', '0');
INSERT INTO `t_log` VALUES ('308', '3', '2017-06-21 18:30:29', '列表', '0');
INSERT INTO `t_log` VALUES ('309', '3', '2017-06-21 18:30:34', '得到合同', '0');
INSERT INTO `t_log` VALUES ('310', '3', '2017-06-21 18:30:34', '定稿', '0');
INSERT INTO `t_log` VALUES ('311', '3', '2017-06-21 18:30:34', '列表', '0');
INSERT INTO `t_log` VALUES ('312', '3', '2017-06-21 18:30:36', '意见查看', '0');
INSERT INTO `t_log` VALUES ('313', '3', '2017-06-21 18:31:31', '得到合同', '0');
INSERT INTO `t_log` VALUES ('314', '3', '2017-06-21 18:31:34', '同意', '0');
INSERT INTO `t_log` VALUES ('315', '3', '2017-06-21 18:31:34', '意见查看', '0');
INSERT INTO `t_log` VALUES ('316', '3', '2017-06-21 18:32:24', '意见查看', '0');
INSERT INTO `t_log` VALUES ('317', '3', '2017-06-21 18:32:25', '得到列表', '0');
INSERT INTO `t_log` VALUES ('318', '3', '2017-06-21 18:32:30', '列表', '0');

-- ----------------------------
-- Table structure for t_right
-- ----------------------------
DROP TABLE IF EXISTS `t_right`;
CREATE TABLE `t_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_right_u` (`user_id`),
  KEY `FK_right_r` (`role_id`),
  CONSTRAINT `FK_right_r` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_right_u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_right
-- ----------------------------
INSERT INTO `t_right` VALUES ('1', '1', '1', 'admin', '0');
INSERT INTO `t_right` VALUES ('2', '2', '2', 'operator', '0');
INSERT INTO `t_right` VALUES ('3', '3', '2', 'operator', '0');
INSERT INTO `t_right` VALUES ('4', '4', '2', 'operator', '0');
INSERT INTO `t_right` VALUES ('5', '5', '2', 'operator', '0');
INSERT INTO `t_right` VALUES ('6', '8', '2', 'operator', '0');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `function_ids` varchar(500) DEFAULT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', 'To implement the system management and contract management', '003,004,008,009,010,011,012,013,014,015,016,017,018,019,020,021,022,023,024,025,026,027,028,029,030', '0');
INSERT INTO `t_role` VALUES ('2', 'operator', 'operate contract', '001,002,003,005,006,007,011,027', '0');
INSERT INTO `t_role` VALUES ('3', 'none', 'Not assign any permission yet', null, '0');
INSERT INTO `t_role` VALUES ('4', 'test', 'test', '001,002,003,004,005,006,007,025,026,027,028', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `password` varchar(20) NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '0');
INSERT INTO `user` VALUES ('2', 'thomas', '123456', '0');
INSERT INTO `user` VALUES ('3', 'lucy', '123456', '0');
INSERT INTO `user` VALUES ('4', 'lily', '123456', '0');
INSERT INTO `user` VALUES ('5', 'jack', '123456', '0');
INSERT INTO `user` VALUES ('8', 'qxy', '123456', '0');
