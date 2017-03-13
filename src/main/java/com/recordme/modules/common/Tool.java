package com.recordme.modules.common;

import com.recordme.modules.usermanage.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by D on 2017/3/13.
 */
public class Tool {
    public static UserInfo getUser(){
        Subject currentUser = SecurityUtils.getSubject();
        return (UserInfo)currentUser.getPrincipal();
    }
}
