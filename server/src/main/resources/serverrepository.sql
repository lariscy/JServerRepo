/*
MySQL Data Transfer
Target Database: serverrepository
Date: 2/2/2017 5:07:17 PM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for audit_log
-- ----------------------------
CREATE TABLE `audit_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(4) NOT NULL,
  `ip_address` varchar(15) NOT NULL,
  `timestamp` datetime NOT NULL,
  `log` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for node
-- ----------------------------
CREATE TABLE `node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `node_type_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `label` varchar(75) DEFAULT NULL,
  `os_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parent_id_id` (`parent_id`),
  KEY `fk_os_type_id_id` (`os_type_id`),
  KEY `fk_node_type_id_id` (`node_type_id`),
  CONSTRAINT `fk_node_type_id_id` FOREIGN KEY (`node_type_id`) REFERENCES `node_type` (`id`),
  CONSTRAINT `fk_os_type_id_id` FOREIGN KEY (`os_type_id`) REFERENCES `os_type` (`id`),
  CONSTRAINT `fk_parent_id_id` FOREIGN KEY (`parent_id`) REFERENCES `node` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for node_type
-- ----------------------------
CREATE TABLE `node_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for os_type
-- ----------------------------
CREATE TABLE `os_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_label` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverrepo_config
-- ----------------------------
CREATE TABLE `serverrepo_config` (
  `key` varchar(50) NOT NULL,
  `value` varchar(150) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `id` varchar(4) NOT NULL,
  `level` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `node_type` VALUES ('1', 'Folder');
INSERT INTO `node_type` VALUES ('2', 'Server');
INSERT INTO `node_type` VALUES ('3', 'URL');

INSERT INTO `os_type` VALUES ('4', 'AIX');
INSERT INTO `os_type` VALUES ('3', 'RedHat');
INSERT INTO `os_type` VALUES ('1', 'Windows');
INSERT INTO `os_type` VALUES ('2', 'zLinux');

INSERT INTO `serverrepo_config` VALUES ('audit.adds.enabled', 'true');
INSERT INTO `serverrepo_config` VALUES ('audit.changes.enabled', 'true');
INSERT INTO `serverrepo_config` VALUES ('audit.connects.enabled', 'true');
INSERT INTO `serverrepo_config` VALUES ('audit.deletes.enabled', 'true');
INSERT INTO `serverrepo_config` VALUES ('audit.logins.enabled', 'true');
