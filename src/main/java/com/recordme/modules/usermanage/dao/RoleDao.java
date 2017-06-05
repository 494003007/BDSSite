package com.recordme.modules.usermanage.dao;

import com.recordme.modules.usermanage.entity.SysPermission;
import com.recordme.modules.usermanage.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

/**
 * Created by D on 2017/2/14.
 */
@Repository
public interface RoleDao extends JpaRepository<SysRole, Long> {
    SysRole findByRole(String role);

}
