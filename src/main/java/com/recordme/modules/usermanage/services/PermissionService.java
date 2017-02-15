package com.recordme.modules.usermanage.services;

import com.recordme.modules.common.BaseService;
import com.recordme.modules.usermanage.dao.PermissionDao;
import com.recordme.modules.usermanage.entity.SysPermission;
import org.springframework.stereotype.Service;


/**
 * Created by D on 2017/2/14.
 */
@Service
public class PermissionService extends BaseService<PermissionDao, SysPermission, Long> {
}
