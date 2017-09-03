package com.bdssite.modules.usermanage.services;

import com.bdssite.modules.common.BaseService;
import com.bdssite.modules.usermanage.dao.ShortMessageDao;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ed_cc on 2017/8/16.
 */
@Service
@Transactional
public class ShortMessageService extends BaseService<ShortMessageDao, ShortMessage, Long> {

    public List<ShortMessage> findByToUser(UserInfo userInfo) {
        return dao.findByToUser(userInfo);
    }

    public List<ShortMessage> findByFromUser(UserInfo userInfo) {
        return dao.findByFromUser(userInfo);
    }

    public List<ShortMessage> findByToUserAndIsRead(UserInfo userInfo1, int is_read1) {
        return dao.findByToUserAndIsRead(userInfo1, is_read1);
    }

    public void updateIsReadToTrue(UserInfo fromUser, UserInfo toUser, int isRead) {
        dao.updateIsReadToTrue(fromUser, toUser, isRead);
    }



    public void deleteByIdIn(Collection<Long> ids) {
        dao.deleteByIdIn(ids);
    }

    public List<ShortMessage> showMessageRecord(UserInfo user1, UserInfo user2) {
        return dao.findByToUserAndFromUserOrToUserAndFromUserOrderBySendTimeDesc(user1, user2, user2, user1);
    }

    public ShortMessage findLastNewMessage(UserInfo user1, UserInfo user2) {
        return dao.findFirstByToUserAndFromUserOrToUserAndFromUserOrderByIdDesc(user1, user2, user2, user1);
    }

    public List<ShortMessage> findByIdIn(Collection<Long> ids) {
        return dao.findByIdIn(ids);
    }

    public List<ShortMessage> findByFromUserOrToUser(UserInfo userInfo1,UserInfo userInfo2) {
        return dao.findByFromUserOrToUser(userInfo1,userInfo2);
    }
}
