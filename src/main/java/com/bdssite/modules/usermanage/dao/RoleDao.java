package com.bdssite.modules.usermanage.dao;


import com.bdssite.modules.usermanage.entity.SysRole;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by D on 2017/2/14.
 */
@Repository
public interface RoleDao extends JpaSpecificationExecutor<SysRole>
        ,PagingAndSortingRepository<SysRole,Long> {
    SysRole findByRole(String role);

}
