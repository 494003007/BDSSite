package com.bdssite.modules.usermanage.dao;

import com.bdssite.modules.common.repository.ExtJpaRepository;
import com.bdssite.modules.usermanage.entity.UserInfo;

import org.springframework.stereotype.Repository;

/**
 * Created by D on 2017/2/14.
 */
@Repository
public interface UserDao extends ExtJpaRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);

    UserInfo findByEmail(String email);

    UserInfo findByName(String name);

}