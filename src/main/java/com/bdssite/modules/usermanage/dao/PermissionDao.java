package com.bdssite.modules.usermanage.dao;

import com.bdssite.modules.common.repository.ExtJpaRepository;
import com.bdssite.modules.usermanage.entity.SysPermission;



import org.springframework.stereotype.Repository;


/**
 * Created by D on 2017/2/14.
 */

@Repository
public interface PermissionDao extends ExtJpaRepository<SysPermission,Long> {
}