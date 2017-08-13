package com.bdssite.modules.usermanage.services;

import com.bdssite.modules.common.BaseService;
import com.bdssite.modules.usermanage.dao.RoleDao;
import com.bdssite.modules.usermanage.entity.SysRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by D on 2017/2/14.
 */
@Service
public class RoleService extends BaseService<RoleDao, SysRole, Long> {
    public SysRole findById(Long id){
        return dao.findOne(id);
    }

    public Page<SysRole> queryAllRolePaging(int limit, int offset){
        return dao.findAll(new PageRequest(offset,limit));
    }
}