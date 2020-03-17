/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : bank

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-04-20 11:40:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_info
-- ----------------------------
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardNo` int(32) NOT NULL,
  `pwd` int(6) NOT NULL,
  `name` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `balance` double(20,2) NOT NULL,
  `surplusInputNum` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of account_info
-- ----------------------------
INSERT INTO `account_info` VALUES ('1', '123456', '123456', '张三', '900.00', '5');

-- ----------------------------
-- Table structure for voucher_info
-- ----------------------------
DROP TABLE IF EXISTS `voucher_info`;
CREATE TABLE `voucher_info` (
  `watercourseNum` int(64) NOT NULL AUTO_INCREMENT,
  `atmId` int(16) NOT NULL,
  `money` int(6) NOT NULL,
  `time` datetime NOT NULL,
  `cardNo` int(32) NOT NULL,
  `option` int(3) NOT NULL,
  PRIMARY KEY (`watercourseNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of voucher_info
-- ----------------------------
