package com.recordme.modules.usermanage.dao;

import com.recordme.modules.usermanage.entity.SysPermission;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by D on 2017/2/14.
 */

@Repository
public interface PermissionDao extends CrudRepository<SysPermission, Long> {
    SysPermission findById(Long id);

    @Query("SELECT permission_id FROM sys_role_permission where role_id = ?1")
    List<Long> findRolePermissionList(Long roleId);
}
