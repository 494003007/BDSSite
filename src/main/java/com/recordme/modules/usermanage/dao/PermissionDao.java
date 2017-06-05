package com.recordme.modules.usermanage.dao;

import com.recordme.modules.usermanage.entity.SysPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by D on 2017/2/14.
 */

@Repository
public interface PermissionDao extends JpaRepository<SysPermission,Long> {
}