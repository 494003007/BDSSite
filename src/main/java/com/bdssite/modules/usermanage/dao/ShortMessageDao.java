package com.bdssite.modules.usermanage.dao;

import com.bdssite.modules.common.repository.ExtJpaRepository;
import com.bdssite.modules.usermanage.entity.ShortMessage;

import com.bdssite.modules.usermanage.entity.UserInfo;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Ed_cc on 2017/8/16.
 */
@Repository
public interface ShortMessageDao extends ExtJpaRepository<ShortMessage,Long> {

    List<ShortMessage> findByToUser(UserInfo userInfo);

    List<ShortMessage> findByFromUser(UserInfo userInfo);


    void deleteByIdIn(Collection<Long> ids);

    List<ShortMessage> findByIdIn(Collection<Long> ids);
}
