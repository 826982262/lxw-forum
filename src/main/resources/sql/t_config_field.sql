SET FOREIGN_KEY_CHECKS=0;

DELETE FROM t_config_field WHERE id in (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26);

INSERT INTO `t_config_field` VALUES ('1', 'WISP_MATCH_SUCCESS_RATE', '2', 'zh', '精灵匹配成功率', '20', 'INTEGER', '精灵匹配成功率', '1');
INSERT INTO `t_config_field` VALUES ('2', 'UNLOCK_ACCOUNT_CONSUME_COINS', '3', 'zh', '解封账号所需精灵币', '100', 'INTEGER', '解封账号所需精灵币', '1');
INSERT INTO `t_config_field` VALUES ('3', 'LOCK_MINUTE_BEFORE_MATCH', '2', 'zh', '匹配前的锁定时长(分钟)', '30', 'INTEGER', '匹配前的锁定时长(分钟)', '1');
INSERT INTO `t_config_field` VALUES ('4', 'RECOMMEND_PROFIT_WITH_LEVEL_ONE', '3', 'zh', '一级推荐收益', '30', 'INTEGER', '一级推荐收益', '1');
INSERT INTO `t_config_field` VALUES ('5', 'RECOMMEND_PROFIT_WITH_LEVEL_TWO', '3', 'zh', '二级推荐收益', '30', 'INTEGER', '二级推荐收益', '1');
INSERT INTO `t_config_field` VALUES ('6', 'BURN_RATE', '3', 'zh', '烧伤比例', '30', 'INTEGER', '烧伤比例', '1');
INSERT INTO `t_config_field` VALUES ('7', 'PROFIT_OF_REWARD', '3', 'zh', '奖励收益', '30', 'INTEGER', '奖励收益', '1');
INSERT INTO `t_config_field` VALUES ('8', 'MATCH_WILL_SUCCESS_LAST_TIMES', '2', 'zh', '第n次必定匹配成功', '5', 'INTEGER', '第n次必定匹配成功', '1');
INSERT INTO `t_config_field` VALUES ('9', 'MATCH_WILL_SUCCESS_WITH_LAST_TIMES_ENABLE', '2', 'zh', '是否启用保底匹配成功', 'false', 'BOOLEAN', '是否启用保底匹配成功', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('10', 'VALID_VIP_COIN', '3', 'zh', '有效会员', '100', 'INTEGER', '精灵令需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('11', 'LEVEL1_VIP_COIN', '3', 'zh', 'v1级会员/精灵令', '200 ', 'INTEGER', '精灵令需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('12', 'LEVEL1_VIP_INVITE', '3', 'zh', 'v1级会员/直推人数', '5', 'INTEGER', '直推人数需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('13', 'LEVEL1_VIP_TEAM', '3', 'zh', 'v1级会员/团队人数', '10', 'INTEGER', '团队人数需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('14', 'LEVEL1_VIP_EARNINGS', '3', 'zh', 'v1级会员/团队收益', '10', 'INTEGER', '会员团队收益百分比', '1');

INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('15', 'LEVEL2_VIP_COIN', '3', 'zh', 'v2级会员/精灵令', '300 ', 'INTEGER', '精灵令需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('16', 'LEVEL2_VIP_INVITE', '3', 'zh', 'v2级会员/直推人数', '10', 'INTEGER', '直推人数需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('17', 'LEVEL2_VIP_TEAM', '3', 'zh', 'v2级会员/团队人数', '20', 'INTEGER', '团队人数需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('18', 'LEVEL2_VIP_EARNINGS', '3', 'zh', 'v2级会员/团队收益', '20', 'INTEGER', '会员团队收益百分比', '1');

INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('19', 'LEVEL3_VIP_COIN', '3', 'zh', 'v3级会员/精灵令', '400 ', 'INTEGER', '精灵令需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('20', 'LEVEL3_VIP_INVITE', '3', 'zh', 'v3级会员/直推人数', '20', 'INTEGER', '直推人数需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('21', 'LEVEL3_VIP_TEAM', '3', 'zh', 'v3级会员/团队人数', '30', 'INTEGER', '团队人数需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('22', 'LEVEL3_VIP_EARNINGS', '3', 'zh', 'v3级会员/团队收益', '30', 'INTEGER', '会员团队收益百分比', '1');

INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('23', 'LEVEL4_VIP_COIN', '3', 'zh', 'v4级会员/精灵令', '500 ', 'INTEGER', '精灵令需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('24', 'LEVEL4_VIP_INVITE', '3', 'zh', 'v4级会员/直推人数', '30', 'INTEGER', '直推人数需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('25', 'LEVEL4_VIP_TEAM', '3', 'zh', 'v4级会员/团队人数', '40', 'INTEGER', '团队人数需满足要求数量', '1');
INSERT INTO `t_config_field` (`id`, `field`, `group_id`, `lang`, `name`, `value`, `data_type`, `description`, `org_id`) VALUES ('26', 'LEVEL4_VIP_EARNINGS', '3', 'zh', 'v4级会员/团队收益', '40', 'INTEGER', '会员团队收益百分比', '1');