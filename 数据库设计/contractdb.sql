/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : contractdb

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2017-06-18 10:47:32
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('11', '3', 'qqtr', '2017061412485818200', 'mmm', '2016-06-06', '2010-09-10', 'tuoiuoio', '0');
INSERT INTO `contract` VALUES ('12', '3', 'guiom.', '2017061412592694758', 'yuoiiuo', '2016-06-06', '2017-08-09', 'hjljkjlk', '0');
INSERT INTO `contract` VALUES ('13', '3', 'uyoiuo', '2017061401045821274', 'mllm', '1996-10-29', '2010-09-10', 'ijoijo', '0');
INSERT INTO `contract` VALUES ('14', '3', 'rerytiu', '2017061401160524375', 'guhhihiu', '2016-06-06', '2017-08-09', 'guhoij', '0');
INSERT INTO `contract` VALUES ('15', '3', 'guiuo', '2017061401193934979', '111', '2004-04-07', '2017-08-09', 'ygujoijo', '0');
INSERT INTO `contract` VALUES ('16', '0', '2ff', '2017061403475858561', '1111', '2223-10-22', '2223-10-22', '2222-22-22', '0');
INSERT INTO `contract` VALUES ('17', '9', '恶趣味；', '2017061405032884097', '围棋界', '1996-10-29', '2010-09-10', 'fdsfasda', '0');
INSERT INTO `contract` VALUES ('18', '10', 'sdasd', '2017061406315324469', 'sdadas', '2223-10-22', '2223-10-22', 'sfasfsaf', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract_process
-- ----------------------------
INSERT INTO `contract_process` VALUES ('55', '14', '3', '1', '1', 'jijpio', '2017-06-14 13:16:37', '0');
INSERT INTO `contract_process` VALUES ('56', '14', '3', '2', '1', 'huioijoij', '2017-06-14 13:16:45', '0');
INSERT INTO `contract_process` VALUES ('57', '14', '3', '3', '1', '', '2017-06-14 13:16:24', '0');
INSERT INTO `contract_process` VALUES ('58', '15', '3', '1', '1', 'huhjoij', '2017-06-14 13:20:30', '0');
INSERT INTO `contract_process` VALUES ('59', '15', '3', '2', '1', 'etuygi', '2017-06-14 13:20:39', '0');
INSERT INTO `contract_process` VALUES ('60', '15', '3', '3', '1', '', '2017-06-14 13:20:06', '0');
INSERT INTO `contract_process` VALUES ('61', '16', '3', '1', '1', 'å¾å°ç', '2017-06-14 16:00:01', '0');
INSERT INTO `contract_process` VALUES ('62', '16', '3', '2', '0', '', '2017-06-14 15:59:42', '0');
INSERT INTO `contract_process` VALUES ('63', '16', '3', '3', '0', '', '2017-06-14 15:59:42', '0');
INSERT INTO `contract_process` VALUES ('64', '17', '9', '1', '1', 'cjskdls ', '2017-06-14 17:04:25', '0');
INSERT INTO `contract_process` VALUES ('65', '17', '9', '2', '1', 'sadasdasd', '2017-06-14 17:10:20', '0');
INSERT INTO `contract_process` VALUES ('66', '17', '9', '3', '1', 'fsdfasdas', '2017-06-14 17:04:01', '0');
INSERT INTO `contract_process` VALUES ('67', '18', '10', '1', '1', 'sfdasda', '2017-06-14 18:32:35', '0');
INSERT INTO `contract_process` VALUES ('68', '18', '10', '2', '1', 'vdfas', '2017-06-14 18:32:59', '0');
INSERT INTO `contract_process` VALUES ('69', '18', '10', '3', '1', 'fsfasd', '2017-06-14 18:32:18', '0');
INSERT INTO `contract_process` VALUES ('70', '14', '4', '1', '0', '', '2017-06-14 23:07:13', '0');
INSERT INTO `contract_process` VALUES ('71', '14', '4', '2', '0', '', '2017-06-14 23:07:13', '0');
INSERT INTO `contract_process` VALUES ('72', '14', '4', '3', '0', '', '2017-06-14 23:07:13', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract_state
-- ----------------------------
INSERT INTO `contract_state` VALUES ('38', '14', '1', '2017-06-14 13:16:05', '0');
INSERT INTO `contract_state` VALUES ('39', '14', '2', '2017-06-14 13:16:37', '0');
INSERT INTO `contract_state` VALUES ('40', '14', '4', '2017-06-14 13:16:45', '0');
INSERT INTO `contract_state` VALUES ('41', '14', '5', '2017-06-14 13:16:52', '0');
INSERT INTO `contract_state` VALUES ('42', '15', '1', '2017-06-14 13:19:39', '0');
INSERT INTO `contract_state` VALUES ('43', '15', '2', '2017-06-14 13:20:30', '0');
INSERT INTO `contract_state` VALUES ('44', '15', '4', '2017-06-14 13:20:38', '0');
INSERT INTO `contract_state` VALUES ('45', '15', '5', '2017-06-14 13:20:45', '0');
INSERT INTO `contract_state` VALUES ('46', '16', '1', '2017-06-14 15:47:58', '0');
INSERT INTO `contract_state` VALUES ('47', '16', '2', '2017-06-14 16:00:00', '0');
INSERT INTO `contract_state` VALUES ('48', '14', '3', '2017-06-14 16:06:10', '0');
INSERT INTO `contract_state` VALUES ('49', '17', '1', '2017-06-14 17:03:28', '0');
INSERT INTO `contract_state` VALUES ('50', '17', '2', '2017-06-14 17:04:24', '0');
INSERT INTO `contract_state` VALUES ('51', '17', '3', '2017-06-14 17:10:06', '0');
INSERT INTO `contract_state` VALUES ('52', '17', '4', '2017-06-14 17:10:19', '0');
INSERT INTO `contract_state` VALUES ('53', '17', '5', '2017-06-14 17:10:41', '0');
INSERT INTO `contract_state` VALUES ('54', '18', '1', '2017-06-14 18:31:53', '0');
INSERT INTO `contract_state` VALUES ('55', '18', '2', '2017-06-14 18:32:34', '0');
INSERT INTO `contract_state` VALUES ('56', '18', '3', '2017-06-14 18:32:48', '0');
INSERT INTO `contract_state` VALUES ('57', '18', '4', '2017-06-14 18:32:59', '0');
INSERT INTO `contract_state` VALUES ('58', '18', '5', '2017-06-14 18:33:08', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'Cus20131211182300001', 'HB company', 'hubei wuhan', '11111111111', '12121212', '430000', 'Bank Of China', '621661***', '0');
INSERT INTO `customer` VALUES ('2', 'Cus20131211182300002', 'BJ company', 'beijing', '22222222', '34213467', '100000', 'Agricultural Bank of China', '622848***', '0');
INSERT INTO `customer` VALUES ('3', 'Cus20131211182300003', 'Jack Wang', 'Shanghai', '14231116', '45678234', '200000', 'Industrial and Commercial Bank of China Limited', '530990***', '0');
INSERT INTO `customer` VALUES ('4', null, 'qqqq', '四季度开始的', '1325353', '1224353', '24353', '31224353', '243243535', '0');
INSERT INTO `customer` VALUES ('5', null, '额度完全', '委屈委屈', '23123', '234', '565645', '4324234', '64565435', '0');
INSERT INTO `customer` VALUES ('6', null, '时代的撒', '委屈委屈', '23123', '234', '565645', '4324234', '64565435', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;

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
INSERT INTO `t_right` VALUES ('2', '2', '1', 'admin', '0');
INSERT INTO `t_right` VALUES ('3', '3', '2', 'operator', '0');
INSERT INTO `t_right` VALUES ('4', '4', '2', 'operator', '0');
INSERT INTO `t_right` VALUES ('5', '5', '2', 'operator', '0');
INSERT INTO `t_right` VALUES ('6', '8', '3', 'nothing', '0');
INSERT INTO `t_right` VALUES ('7', '9', '2', 'operator', '0');
INSERT INTO `t_right` VALUES ('8', '10', '2', 'operator', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', 'To implement the system management and contract management', '003,004,008,009,010,011,012,013,014,015,016,017,018,019,020,021,022,023,024,025,026,027,028,029,030', '0');
INSERT INTO `t_role` VALUES ('2', 'operator', 'operate contract', '001,002,003,005,006,007,011,027', '0');
INSERT INTO `t_role` VALUES ('3', 'none', 'Not assign any permission yet', null, '0');

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
INSERT INTO `user` VALUES ('9', 'qy', '123', '0');
INSERT INTO `user` VALUES ('10', 'qqq', '123', '0');
