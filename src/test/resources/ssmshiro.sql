/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : ssmshiro

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-04-20 12:44:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sign` varchar(20) NOT NULL,
  `description` varchar(30) NOT NULL,
  `is_valid` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('16b6fc63b5ca4f1299bfc9a19473815c', '角色修改', 'role:update', '角色修改', '1');
INSERT INTO `t_permission` VALUES ('2d34a726eb1140aa8f07498a45fc866b', '角色新增', 'role:create', '角色新增', '1');
INSERT INTO `t_permission` VALUES ('32153c06d7fc4ab6857390d8222ecb98', '查看用户', 'user:read', '查看用户', '1');
INSERT INTO `t_permission` VALUES ('3e8d9bb646744d50800e0a36cbe8bb49', '用户新增', 'user:create', '用户新增', '1');
INSERT INTO `t_permission` VALUES ('5005b876b5034a56ab339f9e9148c8bc', '查看角色', 'role:read', '查看角色', '1');
INSERT INTO `t_permission` VALUES ('7c7ad048673a4ff4824cb1a14f5ad8af', '用户修改', 'user:update', '用户修改', '1');
INSERT INTO `t_permission` VALUES ('bfacd98c705c41f48b5a64147bd42a99', '用户删除', 'user:delete', '用户删除', '1');
INSERT INTO `t_permission` VALUES ('ce44478b87824c949661ab1faecda2b1', '查看权限', 'permission:readALL', '查看权限', '1');
INSERT INTO `t_permission` VALUES ('e489fd81e89d409eb0e6873d9739735f', '角色删除', 'role:delete', '角色删除', '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` varchar(30) DEFAULT NULL,
  `sign` varchar(20) NOT NULL,
  `is_valid` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('7e44c622e0394238a563eb6406951ce2', '角色2', '测试角色2', 'simple_user', '1');
INSERT INTO `t_role` VALUES ('c0c76fc6ff794004aa2fd4e8ee88cb55', '角色1', '测试角色111', 'admin', '1');
INSERT INTO `t_role` VALUES ('e4189799976548388f4dee7a5683f941', '超级管理员', '拥有所有权限', 'sys_admin', '1');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `permission_id` varchar(32) NOT NULL,
  `is_valid` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('05facc3c054b4fd49a8be2da247cddd7', 'e4189799976548388f4dee7a5683f941', 'ce44478b87824c949661ab1faecda2b1', '1');
INSERT INTO `t_role_permission` VALUES ('3bd7e85c8644423cb0708199f4b5a73c', 'e4189799976548388f4dee7a5683f941', '32153c06d7fc4ab6857390d8222ecb98', '1');
INSERT INTO `t_role_permission` VALUES ('51eb46fb18fc4281b24babef2c2644dd', 'c0c76fc6ff794004aa2fd4e8ee88cb55', '2d34a726eb1140aa8f07498a45fc866b', '1');
INSERT INTO `t_role_permission` VALUES ('5d16d42154e843bf84c184e9023038af', '7e44c622e0394238a563eb6406951ce2', '7c7ad048673a4ff4824cb1a14f5ad8af', '1');
INSERT INTO `t_role_permission` VALUES ('6cd9d6cf4fe5431a97b22db5f972748a', 'e4189799976548388f4dee7a5683f941', '3e8d9bb646744d50800e0a36cbe8bb49', '1');
INSERT INTO `t_role_permission` VALUES ('6e9fe3ad8c4f4bda8bf70bcd90d629b4', '7e44c622e0394238a563eb6406951ce2', '32153c06d7fc4ab6857390d8222ecb98', '1');
INSERT INTO `t_role_permission` VALUES ('946758609fa748a798a8f51d7639bdef', 'e4189799976548388f4dee7a5683f941', '2d34a726eb1140aa8f07498a45fc866b', '1');
INSERT INTO `t_role_permission` VALUES ('9f757b5777f742edbb8d061f991dc8b9', 'c0c76fc6ff794004aa2fd4e8ee88cb55', '16b6fc63b5ca4f1299bfc9a19473815c', '1');
INSERT INTO `t_role_permission` VALUES ('a96fa406d0ee45eb9dc2f74ae082b2c1', 'e4189799976548388f4dee7a5683f941', '16b6fc63b5ca4f1299bfc9a19473815c', '1');
INSERT INTO `t_role_permission` VALUES ('c5a05dc748a14b5b8522bbb8f70c8dc4', 'e4189799976548388f4dee7a5683f941', 'e489fd81e89d409eb0e6873d9739735f', '1');
INSERT INTO `t_role_permission` VALUES ('c9567f5136da4592adb6688bc2f8838e', 'e4189799976548388f4dee7a5683f941', '7c7ad048673a4ff4824cb1a14f5ad8af', '1');
INSERT INTO `t_role_permission` VALUES ('d7f0bf65fd064aab8e0ddf689ee90cd4', 'e4189799976548388f4dee7a5683f941', 'bfacd98c705c41f48b5a64147bd42a99', '1');
INSERT INTO `t_role_permission` VALUES ('e640dfe36a724751b539ea5f15a961f8', 'e4189799976548388f4dee7a5683f941', '5005b876b5034a56ab339f9e9148c8bc', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(30) NOT NULL,
  `state` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_valid` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('f25604b5d23f461c80fc5b10b8c28417', 'haohua', '123456', '951087952@qq.com', '1', '2017-04-18 20:26:16', '1');
INSERT INTO `t_user` VALUES ('f9ebc8ffde144609b46a7d485ffb4be5', 'haohua2', '123457', '951087953@qq.com', '1', '2017-04-20 09:18:44', '1');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `is_valid` tinyint(4) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('f25604b5d23f461c80fc5b10b8c28417', 'e4189799976548388f4dee7a5683f941', '1');
INSERT INTO `t_user_role` VALUES ('f9ebc8ffde144609b46a7d485ffb4be5', '7e44c622e0394238a563eb6406951ce2', '1');
INSERT INTO `t_user_role` VALUES ('f9ebc8ffde144609b46a7d485ffb4be5', 'c0c76fc6ff794004aa2fd4e8ee88cb55', '1');
