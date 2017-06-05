package com.recordme.modules.usermanage.dao;

import com.recordme.modules.usermanage.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by D on 2017/2/14.
 */
@Repository
public interface UserDao extends JpaRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);

    UserInfo findByEmail(String email);

    UserInfo findByName(String name);

}