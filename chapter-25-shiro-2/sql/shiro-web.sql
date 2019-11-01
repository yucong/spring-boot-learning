

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`),
  KEY `idx_sys_organization_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


INSERT INTO `sys_organization` VALUES ('1', '总公司', '0', '0/', '1');
INSERT INTO `sys_organization` VALUES ('2', '分公司1', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('3', '分公司2', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('4', '分公司11', '2', '0/1/2/', '1');


DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;


INSERT INTO `sys_permission` VALUES ('1', '资源', 'menu', '', '0', '0/', '', '1');
INSERT INTO `sys_permission` VALUES ('11', '组织机构管理', 'menu', '/organization', '1', '0/1/', '', '1');
INSERT INTO `sys_permission` VALUES ('12', '组织机构新增', 'button', '', '11', '0/1/11/', 'organization:create', '1');
INSERT INTO `sys_permission` VALUES ('13', '组织机构修改', 'button', '', '11', '0/1/11/', 'organization:update', '1');
INSERT INTO `sys_permission` VALUES ('14', '组织机构删除', 'button', '', '11', '0/1/11/', 'organization:delete', '1');
INSERT INTO `sys_permission` VALUES ('15', '组织机构查看', 'button', '', '11', '0/1/11/', 'organization:view', '1');
INSERT INTO `sys_permission` VALUES ('21', '用户管理', 'menu', '/user', '1', '0/1/', '', '1');
INSERT INTO `sys_permission` VALUES ('22', '用户新增', 'button', '', '21', '0/1/21/', '/user/add', '1');
INSERT INTO `sys_permission` VALUES ('23', '用户修改', 'button', '', '21', '0/1/21/', '/user/update', '1');
INSERT INTO `sys_permission` VALUES ('24', '用户删除', 'button', '', '21', '0/1/21/', '/user/delete', '1');
INSERT INTO `sys_permission` VALUES ('25', '用户查看', 'button', '', '21', '0/1/21/', '/user/list', '1');
INSERT INTO `sys_permission` VALUES ('26', '更改密码', 'button', '', '21', '0/1/21/', '/user/changePassword', '1');
INSERT INTO `sys_permission` VALUES ('31', '资源管理', 'menu', '/resource', '1', '0/1/', '', '1');
INSERT INTO `sys_permission` VALUES ('32', '资源新增', 'button', '', '31', '0/1/31/', 'resource:create', '1');
INSERT INTO `sys_permission` VALUES ('33', '资源修改', 'button', '', '31', '0/1/31/', 'resource:update', '1');
INSERT INTO `sys_permission` VALUES ('34', '资源删除', 'button', '', '31', '0/1/31/', 'resource:delete', '1');
INSERT INTO `sys_permission` VALUES ('35', '资源查看', 'button', '', '31', '0/1/31/', 'resource:view', '1');
INSERT INTO `sys_permission` VALUES ('41', '角色管理', 'menu', '/role', '1', '0/1/', '', '1');
INSERT INTO `sys_permission` VALUES ('42', '角色新增', 'button', '', '41', '0/1/41/', 'role:create', '1');
INSERT INTO `sys_permission` VALUES ('43', '角色修改', 'button', '', '41', '0/1/41/', 'role:update', '1');
INSERT INTO `sys_permission` VALUES ('44', '角色删除', 'button', '', '41', '0/1/41/', 'role:delete', '1');
INSERT INTO `sys_permission` VALUES ('45', '角色查看', 'button', '', '41', '0/1/41/', 'role:view', '1');
INSERT INTO `sys_permission` VALUES ('46', 'URL管理', 'menu', '/urlFilter', '1', '0/1/', '', '1');
INSERT INTO `sys_permission` VALUES ('47', 'URL新增', 'button', '', '46', '0/1/46/', 'urlFilter:create', '1');
INSERT INTO `sys_permission` VALUES ('48', 'URL修改', 'button', '', '46', '0/1/46/', 'urlFilter:update', '1');
INSERT INTO `sys_permission` VALUES ('49', 'URL删除', 'button', '', '46', '0/1/46/', 'urlFilter:delete', '1');
INSERT INTO `sys_permission` VALUES ('50', 'URL查看', 'button', '', '46', '0/1/46/', 'urlFilter:view', '1');


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', '1');

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;


INSERT INTO `sys_role_permission` VALUES ('1', '1', '11');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '12');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '13');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '14');
INSERT INTO `sys_role_permission` VALUES ('5', '1', '15');
INSERT INTO `sys_role_permission` VALUES ('6', '1', '21');
INSERT INTO `sys_role_permission` VALUES ('7', '1', '22');
INSERT INTO `sys_role_permission` VALUES ('8', '1', '23');
INSERT INTO `sys_role_permission` VALUES ('9', '1', '24');
INSERT INTO `sys_role_permission` VALUES ('10', '1', '25');
INSERT INTO `sys_role_permission` VALUES ('11', '1', '31');
INSERT INTO `sys_role_permission` VALUES ('12', '1', '32');
INSERT INTO `sys_role_permission` VALUES ('13', '1', '33');
INSERT INTO `sys_role_permission` VALUES ('14', '1', '34');
INSERT INTO `sys_role_permission` VALUES ('15', '1', '35');
INSERT INTO `sys_role_permission` VALUES ('16', '1', '41');
INSERT INTO `sys_role_permission` VALUES ('17', '1', '42');
INSERT INTO `sys_role_permission` VALUES ('18', '1', '43');
INSERT INTO `sys_role_permission` VALUES ('19', '1', '44');
INSERT INTO `sys_role_permission` VALUES ('20', '1', '45');
INSERT INTO `sys_role_permission` VALUES ('21', '1', '26');


DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organization_id` bigint(20) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `role_ids` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`),
  KEY `idx_sys_user_organization_id` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;



INSERT INTO `sys_user` VALUES ('1', null, 'admin', 'ca29326401e4806cf427174f099e7698', 'e19216a39060f78024f22b9a047ff2c2', '1', '0');
INSERT INTO `sys_user` VALUES ('2', '1', 'test', 'dd3736d1c3c4702e5170cde08bffd411', '8d78869f470951332959580424d4bf4f', '1', '0');
INSERT INTO `sys_user` VALUES ('3', null, 'zhang', '76ea8805abdb15e71c0f7ff7a653de5b', '645b20bba7d349982c267c8b4ebd24a1', null, '0');
INSERT INTO `sys_user` VALUES ('5', null, 'lisi', 'b11799eee6f6ece1f7533702e1ee634e', 'a1e935b74101e260645d7183cdf88b5e', null, '0');



DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '1');


