package com.bdssite.modules.usermanage.services;

import com.bdssite.modules.common.BaseService;
import com.bdssite.modules.usermanage.dao.RoleDao;
import com.bdssite.modules.usermanage.dao.ShortMessageDao;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.SysRole;
import com.bdssite.modules.usermanage.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ed_cc on 2017/8/16.
 */
@Service
public class ShortMessageService  extends BaseService<ShortMessageDao, ShortMessage, Long> {

    public List<ShortMessage> findByToUser(UserInfo userInfo){
        return dao.findByToUser(userInfo);
    }

    public List<ShortMessage> findByFromUser(UserInfo userInfo){
        return dao.findByFromUser(userInfo);
    }
}
