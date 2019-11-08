
DROP TABLE IF EXISTS `sys_organization`;
DROP TABLE IF EXISTS `sys_user`;
DROP TABLE IF EXISTS `sys_role`;
DROP TABLE IF EXISTS `sys_permission`;
DROP TABLE IF EXISTS `sys_user_role`;
DROP TABLE IF EXISTS `sys_role_permission`;


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

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=526 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;










# 组织
INSERT INTO `sys_organization` VALUES ('1', '总公司', '0', '0/', '1');
INSERT INTO `sys_organization` VALUES ('2', '分公司1', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('3', '分公司2', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('4', '分公司11', '2', '0/1/2/', '1');

# 用户
INSERT INTO `sys_user` VALUES ('1', null, 'admin', 'ca29326401e4806cf427174f099e7698', 'e19216a39060f78024f22b9a047ff2c2', '1', '0');
INSERT INTO `sys_user` VALUES ('2', '1', 'test', '5987964431e387f0470ef677a1231595', '44c90e3838bb087bb7d9dbe17a388168', '1', '0');
INSERT INTO `sys_user` VALUES ('3', null, 'zhang', '76ea8805abdb15e71c0f7ff7a653de5b', '645b20bba7d349982c267c8b4ebd24a1', null, '0');
INSERT INTO `sys_user` VALUES ('5', null, 'lisi2', '', 'a1e935b74101e260645d7183cdf88b5e', null, '0');
INSERT INTO `sys_user` VALUES ('7', null, 'yucong', 'c69a0bc5755dcfbdb4778e9376b909e6', '1e7a35cca1ea1a3e0a7dd63b3b10b0ea', null, '0');
INSERT INTO `sys_user` VALUES ('8', null, 'aaa', '', 'f2743a761e86c32a37be77fa19432238', null, '1');

# 权限
INSERT INTO `sys_permission` VALUES ('1', '系统管理', 'menu', '0', '0/', '', '1', '1');
INSERT INTO `sys_permission` VALUES ('11', '组织机构管理', 'menu', '1', '0/1/', '/organization', '1', '1');
INSERT INTO `sys_permission` VALUES ('12', '组织机构新增', 'button', '11', '0/1/11/', 'organization:create', '1', '1');
INSERT INTO `sys_permission` VALUES ('13', '组织机构修改', 'button', '11', '0/1/11/', 'organization:update', '1', '2');
INSERT INTO `sys_permission` VALUES ('14', '组织机构删除', 'button', '11', '0/1/11/', 'organization:delete', '1', '3');
INSERT INTO `sys_permission` VALUES ('15', '组织机构查看', 'button', '11', '0/1/11/', 'organization:view', '1', '4');
INSERT INTO `sys_permission` VALUES ('21', '用户管理', 'menu', '1', '0/1/', 'module/system/sys_user.html', '1', '2');
INSERT INTO `sys_permission` VALUES ('22', '用户新增', 'button', '21', '0/1/21/', '/user/add', '1', '1');
INSERT INTO `sys_permission` VALUES ('23', '用户修改', 'button', '21', '0/1/21/', '/user/update', '1', '2');
INSERT INTO `sys_permission` VALUES ('24', '用户冻结', 'button', '21', '0/1/21/', '/user/locked', '1', '3');
INSERT INTO `sys_permission` VALUES ('25', '用户查看', 'button', '21', '0/1/21/', '/user/list', '1', '4');
INSERT INTO `sys_permission` VALUES ('26', '更改密码', 'button', '21', '0/1/21/', '/user/changePassword', '1', '5');
INSERT INTO `sys_permission` VALUES ('27', '更新用户角色', 'button', '21', null, '/user/updateRole', '1', '6');
INSERT INTO `sys_permission` VALUES ('31', '权限管理', 'menu', '1', '0/1/', 'module/system/sys_menu.html', '1', '4');
INSERT INTO `sys_permission` VALUES ('32', '权限新增', 'button', '31', '0/1/31/', '/permission/add', '1', '1');
INSERT INTO `sys_permission` VALUES ('33', '权限修改', 'button', '31', '0/1/31/', '/permission/update', '1', '2');
INSERT INTO `sys_permission` VALUES ('34', '权限冻结', 'button', '31', '0/1/31/', '/permission/locked', '1', '3');
INSERT INTO `sys_permission` VALUES ('35', '权限查看', 'button', '31', '0/1/31/', '/permission/listAll', '1', '4');
INSERT INTO `sys_permission` VALUES ('36', '我的权限', 'button', '31', '0/1/31/', '/permission/listMyMenu', '1', '5');
INSERT INTO `sys_permission` VALUES ('37', '权限详情', 'button', '31', '0/1/31/', '/permission/detail', '1', '6');
INSERT INTO `sys_permission` VALUES ('38', '角色权限集合', 'button', '31', null, '/permission/listPermissionByRoleId', '1', '7');
INSERT INTO `sys_permission` VALUES ('39', '用户权限集合', 'button', '31', null, '/permission/listByUserId', '1', '8');
INSERT INTO `sys_permission` VALUES ('41', '角色管理', 'menu', '1', '0/1/', 'module/system/sys_role.html', '1', '3');
INSERT INTO `sys_permission` VALUES ('42', '角色新增', 'button', '41', '0/1/41/', '/role/addRolePermission', '1', '1');
INSERT INTO `sys_permission` VALUES ('43', '角色修改', 'button', '41', '0/1/41/', '/role/updateRolePermission', '1', '2');
INSERT INTO `sys_permission` VALUES ('44', '角色冻结', 'button', '41', '0/1/41/', '/role/locked', '1', '3');
INSERT INTO `sys_permission` VALUES ('45', '角色查看', 'button', '41', '0/1/41/', '/role/list', '1', '4');
INSERT INTO `sys_permission` VALUES ('46', '用户拥有角色', 'button', '41', null, '/role/listByUserId', '1', '5');
INSERT INTO `sys_permission` VALUES ('51', '测试0', 'menu', '1', null, '	module/system/sys_user.html', '1', '10');
INSERT INTO `sys_permission` VALUES ('52', '测试1', 'menu', '1', null, '	module/system/sys_user.html', '1', '20');
INSERT INTO `sys_permission` VALUES ('53', '测试2', 'menu', '1', null, '	module/system/sys_user.html', '0', '40');
INSERT INTO `sys_permission` VALUES ('54', '测试3', 'menu', '1', null, '	module/system/sys_user.html', '0', '30');

# 角色
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', '1');
INSERT INTO `sys_role` VALUES ('2', 'role', '拥有角色控制的所有权限', '1');
INSERT INTO `sys_role` VALUES ('3', 'user', '拥有用户操作的所有权限', '1');
INSERT INTO `sys_role` VALUES ('4', 'permission', '拥有权限相关的所有权限', '1');
INSERT INTO `sys_role` VALUES ('5', 'organization', '拥有组织相关的所有权限', '0');
INSERT INTO `sys_role` VALUES ('6', 'menu', '拥有测试菜单的权限', '1');
INSERT INTO `sys_role` VALUES ('7', 'test', '测试角色', '1');

# 用户-角色
INSERT INTO `sys_user_role` VALUES ('9', '8', '2');
INSERT INTO `sys_user_role` VALUES ('33', '3', '1');
INSERT INTO `sys_user_role` VALUES ('34', '3', '2');
INSERT INTO `sys_user_role` VALUES ('35', '3', '3');
INSERT INTO `sys_user_role` VALUES ('54', '1', '1');
INSERT INTO `sys_user_role` VALUES ('126', '2', '6');
INSERT INTO `sys_user_role` VALUES ('127', '2', '7');
INSERT INTO `sys_user_role` VALUES ('132', '7', '2');
INSERT INTO `sys_user_role` VALUES ('133', '7', '3');
INSERT INTO `sys_user_role` VALUES ('134', '7', '4');
INSERT INTO `sys_user_role` VALUES ('135', '7', '6');


# 角色-权限
INSERT INTO `sys_role_permission` VALUES ('363', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('364', '1', '11');
INSERT INTO `sys_role_permission` VALUES ('365', '1', '12');
INSERT INTO `sys_role_permission` VALUES ('366', '1', '13');
INSERT INTO `sys_role_permission` VALUES ('367', '1', '14');
INSERT INTO `sys_role_permission` VALUES ('368', '1', '15');
INSERT INTO `sys_role_permission` VALUES ('369', '1', '21');
INSERT INTO `sys_role_permission` VALUES ('370', '1', '22');
INSERT INTO `sys_role_permission` VALUES ('371', '1', '23');
INSERT INTO `sys_role_permission` VALUES ('372', '1', '24');
INSERT INTO `sys_role_permission` VALUES ('373', '1', '25');
INSERT INTO `sys_role_permission` VALUES ('374', '1', '26');
INSERT INTO `sys_role_permission` VALUES ('375', '1', '27');
INSERT INTO `sys_role_permission` VALUES ('376', '1', '41');
INSERT INTO `sys_role_permission` VALUES ('377', '1', '42');
INSERT INTO `sys_role_permission` VALUES ('378', '1', '43');
INSERT INTO `sys_role_permission` VALUES ('379', '1', '44');
INSERT INTO `sys_role_permission` VALUES ('380', '1', '45');
INSERT INTO `sys_role_permission` VALUES ('381', '1', '46');
INSERT INTO `sys_role_permission` VALUES ('382', '1', '31');
INSERT INTO `sys_role_permission` VALUES ('383', '1', '32');
INSERT INTO `sys_role_permission` VALUES ('384', '1', '33');
INSERT INTO `sys_role_permission` VALUES ('385', '1', '34');
INSERT INTO `sys_role_permission` VALUES ('386', '1', '35');
INSERT INTO `sys_role_permission` VALUES ('387', '1', '36');
INSERT INTO `sys_role_permission` VALUES ('388', '1', '37');
INSERT INTO `sys_role_permission` VALUES ('389', '1', '38');
INSERT INTO `sys_role_permission` VALUES ('390', '1', '39');
INSERT INTO `sys_role_permission` VALUES ('391', '1', '51');
INSERT INTO `sys_role_permission` VALUES ('392', '1', '52');
INSERT INTO `sys_role_permission` VALUES ('393', '1', '54');
INSERT INTO `sys_role_permission` VALUES ('394', '1', '53');
INSERT INTO `sys_role_permission` VALUES ('462', '5', '1');
INSERT INTO `sys_role_permission` VALUES ('463', '5', '11');
INSERT INTO `sys_role_permission` VALUES ('464', '5', '12');
INSERT INTO `sys_role_permission` VALUES ('465', '5', '13');
INSERT INTO `sys_role_permission` VALUES ('466', '5', '14');
INSERT INTO `sys_role_permission` VALUES ('467', '5', '15');
INSERT INTO `sys_role_permission` VALUES ('468', '4', '1');
INSERT INTO `sys_role_permission` VALUES ('469', '4', '31');
INSERT INTO `sys_role_permission` VALUES ('470', '4', '32');
INSERT INTO `sys_role_permission` VALUES ('471', '4', '33');
INSERT INTO `sys_role_permission` VALUES ('472', '4', '34');
INSERT INTO `sys_role_permission` VALUES ('473', '4', '35');
INSERT INTO `sys_role_permission` VALUES ('474', '4', '36');
INSERT INTO `sys_role_permission` VALUES ('475', '4', '37');
INSERT INTO `sys_role_permission` VALUES ('476', '4', '38');
INSERT INTO `sys_role_permission` VALUES ('477', '4', '39');
INSERT INTO `sys_role_permission` VALUES ('478', '3', '1');
INSERT INTO `sys_role_permission` VALUES ('479', '3', '21');
INSERT INTO `sys_role_permission` VALUES ('480', '3', '22');
INSERT INTO `sys_role_permission` VALUES ('481', '3', '23');
INSERT INTO `sys_role_permission` VALUES ('482', '3', '24');
INSERT INTO `sys_role_permission` VALUES ('483', '3', '25');
INSERT INTO `sys_role_permission` VALUES ('484', '3', '26');
INSERT INTO `sys_role_permission` VALUES ('485', '3', '27');
INSERT INTO `sys_role_permission` VALUES ('486', '2', '1');
INSERT INTO `sys_role_permission` VALUES ('487', '2', '41');
INSERT INTO `sys_role_permission` VALUES ('488', '2', '42');
INSERT INTO `sys_role_permission` VALUES ('489', '2', '43');
INSERT INTO `sys_role_permission` VALUES ('490', '2', '44');
INSERT INTO `sys_role_permission` VALUES ('491', '2', '45');
INSERT INTO `sys_role_permission` VALUES ('492', '2', '46');
INSERT INTO `sys_role_permission` VALUES ('498', '6', '1');
INSERT INTO `sys_role_permission` VALUES ('499', '6', '51');
INSERT INTO `sys_role_permission` VALUES ('500', '6', '52');
INSERT INTO `sys_role_permission` VALUES ('501', '6', '54');
INSERT INTO `sys_role_permission` VALUES ('502', '6', '53');
INSERT INTO `sys_role_permission` VALUES ('503', '7', '1');
INSERT INTO `sys_role_permission` VALUES ('504', '7', '21');
INSERT INTO `sys_role_permission` VALUES ('505', '7', '22');
INSERT INTO `sys_role_permission` VALUES ('506', '7', '23');
INSERT INTO `sys_role_permission` VALUES ('507', '7', '24');
INSERT INTO `sys_role_permission` VALUES ('508', '7', '25');
INSERT INTO `sys_role_permission` VALUES ('509', '7', '26');
INSERT INTO `sys_role_permission` VALUES ('510', '7', '27');
INSERT INTO `sys_role_permission` VALUES ('511', '7', '41');
INSERT INTO `sys_role_permission` VALUES ('512', '7', '42');
INSERT INTO `sys_role_permission` VALUES ('513', '7', '43');
INSERT INTO `sys_role_permission` VALUES ('514', '7', '44');
INSERT INTO `sys_role_permission` VALUES ('515', '7', '45');
INSERT INTO `sys_role_permission` VALUES ('516', '7', '46');
INSERT INTO `sys_role_permission` VALUES ('517', '7', '31');
INSERT INTO `sys_role_permission` VALUES ('518', '7', '32');
INSERT INTO `sys_role_permission` VALUES ('519', '7', '33');
INSERT INTO `sys_role_permission` VALUES ('520', '7', '34');
INSERT INTO `sys_role_permission` VALUES ('521', '7', '35');
INSERT INTO `sys_role_permission` VALUES ('522', '7', '36');
INSERT INTO `sys_role_permission` VALUES ('523', '7', '37');
INSERT INTO `sys_role_permission` VALUES ('524', '7', '38');
INSERT INTO `sys_role_permission` VALUES ('525', '7', '39');




