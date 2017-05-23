INSERT INTO recordme.sys_permission (available, create_time, name, parent_id, parent_ids, permission, resource_type, url) VALUES (true, '2017-04-09', '管理', null, null, '', 'menu', '');
INSERT INTO recordme.sys_permission (available, create_time, name, parent_id, parent_ids, permission, resource_type, url) VALUES (true, '2017-04-09', '角色管理', 1, '1', '', 'menu', '/AuthorizationManage/roleList');
INSERT INTO recordme.sys_permission (available, create_time, name, parent_id, parent_ids, permission, resource_type, url) VALUES (false, '2017-04-09', '角色查询', 2, '1,2', 'role:view', 'button', null);
INSERT INTO recordme.sys_permission (available, create_time, name, parent_id, parent_ids, permission, resource_type, url) VALUES (false, '2017-04-09', '角色编辑', 2, '1,2', 'role:edit', 'button', null);
INSERT INTO recordme.sys_permission (available, create_time, name, parent_id, parent_ids, permission, resource_type, url) VALUES (true, '2017-04-09', '权限管理', 1, '1', '', 'menu', '/AuthorizationManage/permissionList');
INSERT INTO recordme.sys_permission (available, create_time, name, parent_id, parent_ids, permission, resource_type, url) VALUES (false, '2017-04-09', '权限查询', 5, '1,5', 'permission:view', 'button', null);
INSERT INTO recordme.sys_permission (available, create_time, name, parent_id, parent_ids, permission, resource_type, url) VALUES (false, '2017-04-09', '权限编辑', 5, '1,5', 'permission:edit', 'button', null);

INSERT INTO recordme.sys_role (available, create_time, create_user, description, parent_role, role, role_describe) VALUES (true, null, null, '11', null, 'admin', null);

INSERT INTO recordme.sys_role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO recordme.sys_role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO recordme.sys_role_permission (role_id, permission_id) VALUES (1, 3);
INSERT INTO recordme.sys_role_permission (role_id, permission_id) VALUES (1, 4);