package com.recordme.modules.usermanage.services;

import com.recordme.modules.common.BaseService;
import com.recordme.modules.usermanage.dao.RoleDao;
import com.recordme.modules.usermanage.entity.SysRole;

import org.springframework.stereotype.Service;

/**
 * Created by D on 2017/2/14.
 */
@Service
public class RoleService extends BaseService<RoleDao, SysRole, Long> {
    public SysRole findByRole(SysRole sysRole){return dao.findByRole(sysRole.getRole());}
}
