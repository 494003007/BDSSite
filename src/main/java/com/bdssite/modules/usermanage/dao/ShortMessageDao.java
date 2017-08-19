package com.bdssite.modules.usermanage.dao;

import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.SysRole;
import com.bdssite.modules.usermanage.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Ed_cc on 2017/8/16.
 */
@Repository
public interface ShortMessageDao extends JpaSpecificationExecutor<ShortMessage>
        ,PagingAndSortingRepository<ShortMessage,Long> {

    List<ShortMessage> findByToUser(UserInfo userInfo);

    List<ShortMessage> findByFromUser(UserInfo userInfo);


    void deleteByIdIn(Collection<Long> ids);

    List<ShortMessage> findByIdIn(Collection<Long> ids);
}
