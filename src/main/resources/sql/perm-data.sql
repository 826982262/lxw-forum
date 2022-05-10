SET FOREIGN_KEY_CHECKS=0;

INSERT INTO `t_term_config` VALUES ('1484814343199862785', 'SYSTEM_NOTICE', '系统公告', '系统公告---', null, '2022-01-22 12:00:00', '2022-01-22 12:00:00', '2022-01-22 12:00:00');

DELETE FROM sys_role WHERE id='876708082437197827';
DELETE FROM sys_role WHERE id='876708082437197828';
UPDATE sys_role SET role_code='server' WHERE id='876708082437197833';

-- INSERT INTO sys_role(id,org_id,sort_order,pid,name,tips,version,role_code,made_by,user_type,delete_flag) 
--        VALUES(876708082437197827,1,2,NULL,'运维','平台运维人员',NULL,'server','SYSTEM',NULL,0);


-- DELETE FROM sys_perm_group WHERE id='1432625188005834753';
-- DELETE FROM sys_perm WHERE id='100000000000005001';
-- DELETE FROM sys_perm WHERE id='100000000000005002';
-- DELETE FROM sys_perm WHERE id='100000000000005003';
-- DELETE FROM sys_perm WHERE id='100000000000005004';
-- DELETE FROM sys_perm WHERE id='100000000000005005';
-- DELETE FROM sys_perm WHERE id='100000000000005006';
-- DELETE FROM sys_perm WHERE id='100000000000005007';
-- DELETE FROM sys_perm WHERE id='100000000000005008';

-- INSERT INTO sys_perm_group(`id`, `org_id`, `pid`, `identifier`, `name`) VALUES ('1432625188005834753', '100000000000000001', '100000000000000001', 'Player.management', '盟友模块');

-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005001',  '1432625188005834753', 'Player.edit',  '编辑盟友', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005002' , '1432625188005834753', 'Player.delete','删除盟友', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005003' , '1432625188005834753', 'Player.view',  '查看盟友', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005004' , '1432625188005834753', 'Player.new',   '新增盟友', '0');

-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005005' , '1432625188005834753', 'Player.edit.state',  '修改盟友支付状态', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005006' , '1432625188005834753', 'Player.edit.state.up',  '升级盟友', '0');

-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005007' , '1432625188005834753', 'Bonus.view',  '产品销量', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005008' , '1432625188005834753', 'Bonus.Dividend',  '盟友分红信息', '0');


-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005007' , '1432625188005834753', 'PlayerBonus.edit',    '编辑盟友奖金', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005008' , '1432625188005834753', 'PlayerBonus.delete',  '删除盟友奖金', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005009' , '1432625188005834753', 'PlayerBonus.view',    '查看盟友奖金', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000005010' , '1432625188005834753', 'PlayerBonus.new',     '新增盟友奖金', '0');

-- INSERT INTO sys_perm_group(`id`, `org_id`, `pid`, `identifier`, `name`)
--     VALUES('100000000000000006','100000000000000001','100000000000000001', 'wallet_management','钱包管理');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000006001', '100000000000000006', 'Wallet.edit',  '编辑钱包', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000006002', '100000000000000006', 'Wallet.delete','删除钱包', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000006003', '100000000000000006', 'Wallet.view',  '查看钱包 ', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000006004', '100000000000000006', 'Wallet.new',   '新增钱包 ', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000006005', '100000000000000006', 'WalletCharge.edit',  '编辑钱包零钱', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000006006', '100000000000000006', 'WalletCharge.delete','删除钱包零钱', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000006007', '100000000000000006', 'WalletCharge.view',  '查看钱包零钱', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000006008', '100000000000000006', 'WalletCharge.new',   '新增钱包零钱', '0');
-- INSERT INTO sys_perm(`id`, `group_id`, `identifier`, `name`,`tag`) VALUES('100000000000006009', '100000000000000006', 'WalletHistory.view', '查看钱包使用记录', '0');

DELETE FROM sys_perm WHERE id='100000000000010001';
DELETE FROM sys_perm WHERE id='100000000000010002';
DELETE FROM sys_perm WHERE id='100000000000010003';
DELETE FROM sys_perm WHERE id='100000000000011001';
DELETE FROM sys_perm WHERE id='100000000000011002';
DELETE FROM sys_perm WHERE id='100000000000011003';
DELETE FROM sys_perm WHERE id='100000000000020001';
DELETE FROM sys_perm WHERE id='100000000000020002';
DELETE FROM sys_perm WHERE id='100000000000020003';
DELETE FROM sys_perm WHERE id='100000000000030001';
DELETE FROM sys_perm WHERE id='100000000000030002';
DELETE FROM sys_perm WHERE id='100000000000030003';
DELETE FROM sys_perm WHERE id='876708082437197920';
DELETE FROM sys_perm WHERE id='876708082437197921';
DELETE FROM sys_perm WHERE id='876708082437197922';
DELETE FROM sys_perm WHERE id='876708082437197923';
DELETE FROM sys_perm WHERE id='876708082437197924';
DELETE FROM sys_perm WHERE id='876708082437197925';
DELETE FROM sys_perm WHERE id='1012573379300782088';
DELETE FROM sys_perm WHERE id='1012573379300782089';
DELETE FROM sys_perm WHERE id='1012573379300782090';


INSERT INTO `sys_perm` VALUES ('100000000000010001', '100000000000000105', 'sysUser.view', '查看用户', '0');
INSERT INTO `sys_perm` VALUES ('100000000000010002', '100000000000000105', 'sysUser.edit', '编辑用户', '0');
INSERT INTO `sys_perm` VALUES ('100000000000010003', '100000000000000105', 'sysUser.del', '删除用户', '0');
INSERT INTO `sys_perm` VALUES ('100000000000011001', '100000000000000005', 'OperationLog.view', '查看日志', '0');
INSERT INTO `sys_perm` VALUES ('100000000000011002', '100000000000000006', 'Config.view', '查看配置', '0');
INSERT INTO `sys_perm` VALUES ('100000000000011003', '100000000000000006', 'Config.edit', '修改配置', '0');
INSERT INTO `sys_perm` VALUES ('100000000000020001', '100000000000000106', 'sysRole.view', '查看角色', '0');
INSERT INTO `sys_perm` VALUES ('100000000000020002', '100000000000000106', 'sysRole.edit', '更新角色', '0');
INSERT INTO `sys_perm` VALUES ('100000000000020003', '100000000000000106', 'sysRole.del', '删除角色', '0');
INSERT INTO `sys_perm` VALUES ('100000000000030001', '100000000000000103', 'Org.view', '查看组织', '0');
INSERT INTO `sys_perm` VALUES ('100000000000030002', '100000000000000103', 'Org.edit', '编辑组织', '0');
INSERT INTO `sys_perm` VALUES ('100000000000030003', '100000000000000103', 'Org.del', '删除组织', '0');
INSERT INTO `sys_perm` VALUES ('876708082437197920', '876708082437197910', 'sysUser.view', '查看用户', '0');
INSERT INTO `sys_perm` VALUES ('876708082437197921', '876708082437197910', 'sysUser.edit', '编辑用户', '0');
INSERT INTO `sys_perm` VALUES ('876708082437197922', '876708082437197910', 'sysUser.del', '删除用户', '0');
INSERT INTO `sys_perm` VALUES ('876708082437197923', '876708082437197911', 'sysRole.view', '查看角色', '0');
INSERT INTO `sys_perm` VALUES ('876708082437197924', '876708082437197911', 'sysRole.edit', '更新角色', '0');
INSERT INTO `sys_perm` VALUES ('876708082437197925', '876708082437197911', 'sysRole.del', '删除角色', '0');
INSERT INTO `sys_perm` VALUES ('1012573379300782088', '112573379300782088', 'eav.view', '新建自定义表单', '0');
INSERT INTO `sys_perm` VALUES ('1012573379300782089', '112573379300782088', 'eav.del', '删除自定义表单', '0');
INSERT INTO `sys_perm` VALUES ('1012573379300782090', '112573379300782088', 'eav.edit', '编辑自定义表单', '0');
