package com.bdssite.modules.usermanage.services;

import com.bdssite.modules.common.BaseService;
import com.bdssite.modules.usermanage.dao.RoleDao;
import com.bdssite.modules.usermanage.dao.ShortMessageDao;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by Ed_cc on 2017/8/16.
 */
public class ShortMessageService  extends BaseService<ShortMessageDao, ShortMessage, Long> {

    public List<ShortMessage> findByToUser(Long id){
        return dao.findByToUser(id);
    }

    public List<ShortMessage> findByFromUser(Long id){
        return dao.findByFromUser(id);
    }
}
