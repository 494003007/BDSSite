package com.bdssite.modules.usermanage.dao;

import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.SysRole;
import com.bdssite.modules.usermanage.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Ed_cc on 2017/8/16.
 */
public interface ShortMessageDao extends JpaSpecificationExecutor<ShortMessage>
        ,PagingAndSortingRepository<ShortMessage,Long> {

    List<ShortMessage> findByToUser(UserInfo userInfo);

    List<ShortMessage> findByFromUser(UserInfo userInfo);
}
