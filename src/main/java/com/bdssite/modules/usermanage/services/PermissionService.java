package com.bdssite.modules.usermanage.services;

import com.bdssite.modules.common.BaseService;
import com.bdssite.modules.usermanage.dao.PermissionDao;
import com.bdssite.modules.usermanage.entity.SysPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * Created by D on 2017/2/14.
 */
@Service
public class PermissionService extends BaseService<PermissionDao, SysPermission, Long> {
    public SysPermission findById(Long id){
        return dao.findOne(id);
    }

    public Page<SysPermission> queryAllPermissionPaging(int limit,int offset){
        return dao.findAll(new PageRequest(offset,limit));
    }
}
