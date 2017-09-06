package com.bdssite.modules.usermanage.dao;

import com.bdssite.modules.common.repository.ExtJpaRepository;
import com.bdssite.modules.usermanage.entity.ShortMessage;

import com.bdssite.modules.usermanage.entity.UserInfo;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    List<ShortMessage> findByFromUserOrToUser(UserInfo userInfo0,UserInfo userInfo1);

    List<ShortMessage> findByToUserAndIsRead(UserInfo userInfo1,int isRead1);
    @Modifying
    @Query("UPDATE ShortMessage s SET s.isRead=1 where s.fromUser = ?1 AND s.toUser = ?2 AND s.isRead = ?3")
    void updateIsReadToTrue(UserInfo fromUser,UserInfo toUser,int isRead);
    List<ShortMessage> findByToUserAndFromUserOrToUserAndFromUserOrderBySendTimeDesc(UserInfo toUser0,UserInfo fromUser0,UserInfo toUser1,UserInfo fromUser1);

    List<ShortMessage> findByFromUserAndToUserAndIsRead(UserInfo fromUser,UserInfo toUser,int isRead);
    void deleteByIdIn(Collection<Long> ids);

    ShortMessage findFirstByToUserAndFromUserOrToUserAndFromUserOrderByIdDesc(UserInfo toUser0,UserInfo fromUser0,UserInfo toUser1,UserInfo fromUser1);


    List<ShortMessage> findByIdIn(Collection<Long> ids);
}
