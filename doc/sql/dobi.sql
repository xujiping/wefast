/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : dobi

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 18/12/2018 15:14:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alipay_token
-- ----------------------------
DROP TABLE IF EXISTS `alipay_token`;
CREATE TABLE `alipay_token` (
  `client_listen_id` varchar(32) NOT NULL COMMENT '客户端用户ID',
  `authCode` varchar(32) NOT NULL COMMENT '授权码',
  `user_id` varchar(16) NOT NULL COMMENT '支付宝用户的唯一userId',
  `access_token` varchar(40) NOT NULL COMMENT '访问令牌',
  `expires_in` varchar(16) NOT NULL COMMENT '访问令牌的有效时间，单位：秒',
  `refresh_token` varchar(40) NOT NULL COMMENT '刷新令牌',
  `re_expires_in` varchar(16) NOT NULL COMMENT '刷新令牌的有效时间，单位：秒',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '超时日期',
  PRIMARY KEY (`client_listen_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='支付宝授权码表';

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '级别',
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `subject` varchar(20) NOT NULL DEFAULT 'all' COMMENT '主体  "atlas","all"',
  `seq` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图标',
  `code` varchar(16) DEFAULT NULL COMMENT '编码值',
  `stat` varchar(16) NOT NULL DEFAULT 'normal' COMMENT '状态（normal：正常、audit：待审核、pass：通过、failed：未通过、block：冻结）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for cms_client
-- ----------------------------
DROP TABLE IF EXISTS `cms_client`;
CREATE TABLE `cms_client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '客户端名称',
  `client_id` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '客户端ID',
  `secret` varchar(64) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '客户端密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cms_client
-- ----------------------------
BEGIN;
INSERT INTO `cms_client` VALUES (1, 'cms', 'cms', '123456');
COMMIT;

-- ----------------------------
-- Table structure for cms_role
-- ----------------------------
DROP TABLE IF EXISTS `cms_role`;
CREATE TABLE `cms_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT ' 角色标题',
  `description` varchar(100) NOT NULL DEFAULT '' COMMENT '描述',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '最后更新者',
  `orders` int(2) DEFAULT NULL COMMENT '排序 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of cms_role
-- ----------------------------
BEGIN;
INSERT INTO `cms_role` VALUES (1, 'admin', '系统管理员', '系统管理员，拥有所有权限', '2018-11-09 15:59:37', '2018-11-09 17:02:58', NULL, 1);
INSERT INTO `cms_role` VALUES (2, 'assessor', '审核员', '审核人员', '2018-11-20 14:48:53', '2018-11-20 14:48:55', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for cms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `cms_role_permission`;
CREATE TABLE `cms_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for cms_user
-- ----------------------------
DROP TABLE IF EXISTS `cms_user`;
CREATE TABLE `cms_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT '123456' COMMENT '密码',
  `salt` varchar(10) NOT NULL DEFAULT '123456' COMMENT '随机盐',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态：0不可用 1正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='后台管理-用户表';

-- ----------------------------
-- Records of cms_user
-- ----------------------------
BEGIN;
INSERT INTO `cms_user` VALUES (1, 'admin', '0DB74D5AB06C2141E35EFB7632A50AB0', '123456', '', '17620897422', '1@1.com', '2018-11-20 14:59:09', '2018-11-20 14:59:10', 1);
INSERT INTO `cms_user` VALUES (3, 'test', 'EA48576F30BE1669971699C09AD05C94', '123456', '', '12345678912', '1@1.com', '2018-11-20 14:59:45', '2018-11-20 14:59:45', 1);
COMMIT;

-- ----------------------------
-- Table structure for cms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_role`;
CREATE TABLE `cms_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='后台管理-用户角色表';

-- ----------------------------
-- Table structure for logging_event
-- ----------------------------
DROP TABLE IF EXISTS `logging_event`;
CREATE TABLE `logging_event` (
  `timestmp` bigint(20) NOT NULL,
  `formatted_message` text NOT NULL,
  `logger_name` varchar(254) NOT NULL,
  `level_string` varchar(254) NOT NULL,
  `thread_name` varchar(254) DEFAULT NULL,
  `reference_flag` smallint(6) DEFAULT NULL,
  `arg0` varchar(254) DEFAULT NULL,
  `arg1` varchar(254) DEFAULT NULL,
  `arg2` varchar(254) DEFAULT NULL,
  `arg3` varchar(254) DEFAULT NULL,
  `caller_filename` varchar(254) NOT NULL,
  `caller_class` varchar(254) NOT NULL,
  `caller_method` varchar(254) NOT NULL,
  `caller_line` char(4) NOT NULL,
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24540 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_exception`;
CREATE TABLE `logging_event_exception` (
  `event_id` bigint(20) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254) NOT NULL,
  PRIMARY KEY (`event_id`,`i`) USING BTREE,
  CONSTRAINT `logging_event_exception_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_property`;
CREATE TABLE `logging_event_property` (
  `event_id` bigint(20) NOT NULL,
  `mapped_key` varchar(254) NOT NULL,
  `mapped_value` text,
  PRIMARY KEY (`event_id`,`mapped_key`) USING BTREE,
  CONSTRAINT `logging_event_property_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
