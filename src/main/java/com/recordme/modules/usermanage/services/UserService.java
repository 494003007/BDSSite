package com.recordme.modules.usermanage.services;

import com.recordme.modules.common.BaseService;
import com.recordme.modules.usermanage.dao.UserDao;
import com.recordme.modules.usermanage.entity.UserInfo;
import org.springframework.stereotype.Service;

/**
 * Created by D on 2017/2/14.
 */

@Service
public class UserService extends BaseService<UserDao, UserInfo, Long> {

    public UserInfo findByUsername(String username) {
        return dao.findByUsername(username);
    }
    public UserInfo findByName(String name){
        return dao.findByName(name);
    }
    public UserInfo findByEmail(String email){
        return dao.findByEmail(email);
    }

    public UserInfo findByUid(Long uid){
        return dao.getOne(uid);
    }
}
