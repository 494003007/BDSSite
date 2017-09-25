package com.bdssite.modules.usermanage.dao;

import com.bdssite.modules.common.repository.ExtJpaRepository;
import com.bdssite.modules.usermanage.entity.UserInfo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by D on 2017/2/14.
 */
@Repository
public interface UserDao extends ExtJpaRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);

    UserInfo findByEmail(String email);

    UserInfo findByName(String name);


    @Query(nativeQuery = true,value =
                    "SELECT u.* FROM user_info u " +
                    "LEFT OUTER JOIN following_users f ON u.uid=f.followed_uid " +
                            "WHERE f.following_uid = 3 ORDER BY u.uid \n#pageable\n")
    List<UserInfo> findUserFollowing(Pageable pageable);

//    Page<UserInfo> findByFollowingUsers(Pageable p);
}