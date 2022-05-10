SET FOREIGN_KEY_CHECKS=0;

DELETE FROM t_config_field_group WHERE id in (1,2,3);

INSERT INTO `t_config_field_group` VALUES ('1', null, 'zh', '数据字典', null, '1', 'OPTION', '1');
INSERT INTO `t_config_field_group` VALUES ('2', null, 'zh', '精灵配置', null, '1', 'CONFIG', '1');
INSERT INTO `t_config_field_group` VALUES ('3', null, 'zh', '会员配置', null, '2', 'CONFIG', '1');
