package com.recordme.modules.usermanage.controller;

import com.recordme.modules.usermanage.entity.SysRole;
import com.recordme.modules.usermanage.entity.UserInfo;
import com.recordme.modules.usermanage.services.RoleService;
import com.recordme.modules.usermanage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ed_cc on 2017/2/18.
 */
@Controller
@RequestMapping("/")
public class userRoleRelationController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "userRoleRelationEdit/{username}",method = RequestMethod.GET)
    public String userRoleRelationEditPage(@PathVariable("username")String username, Map<String,Object> map){
        UserInfo userInfo = userService.findByUsername(username);
        map.put("userInfo",userInfo);
        return "/usermanage/userRoleRelationEdit";
    }

    @RequestMapping(value = "userRoleRelationList",method = RequestMethod.GET)
    public String roleDistributionPage(Map<String,Object> map){
        List<UserInfo> list = (List<UserInfo>) userService.findAll();
        map.put("userInfoList",list);
        return "/usermanage/userRoleRelationList";
    }

    @RequestMapping(value = "userRoleRelationEdit/{username}",method = RequestMethod.POST)
    public String userRoleRelationEdit(@PathVariable("username")String username,UserInfo userInfo, SysRole sysRole,Map<String,Object> map){
        userInfo = userService.findByUsername(userInfo.getUsername());
        userInfo.getRoleList().add(roleService.findByRole(sysRole));
        userInfo.setRoleList(userInfo.getRoleList());

        map.put("userInfo",userInfo);


        userService.save(userInfo);
        return "/usermanage/userRoleRelationEdit";
    }


    @RequestMapping(value = "userRoleRelationList",method = RequestMethod.POST)
    public String roleDistribution(UserInfo userInfo, SysRole sysRole,Map<String,Object> map){
        return "/usermanage/userRoleRelationList";
    }



}
