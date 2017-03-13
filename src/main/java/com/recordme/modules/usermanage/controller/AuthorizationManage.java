package com.recordme.modules.usermanage.controller;

import com.recordme.modules.usermanage.entity.SysPermission;
import com.recordme.modules.usermanage.entity.SysRole;
import com.recordme.modules.usermanage.entity.UserInfo;
import com.recordme.modules.usermanage.services.PermissionService;
import com.recordme.modules.usermanage.services.RoleService;
import com.recordme.modules.usermanage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Role Model Created by Ed_cc on 2017/2/18.
 * Permission Model Created by D on 2017/2/17.
 */
@Controller
@RequestMapping(value = "/AuthorizationManage")
public class AuthorizationManage {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "permissionList", method = RequestMethod.GET)
    public String permissionList(Map<String, Object> map){
        map.put("permissions",permissionService.findAll());
        return "/usermanage/permissionList";
    }

    @RequestMapping(value = "permissionDelete", method = RequestMethod.GET)
    public String deletePermission(Long id){
        permissionService.delete(id);
        return  "redirect:/AuthorizationManage/permissionList";
    }

    @RequestMapping(value = "permissionView", method = RequestMethod.GET)
    public String permissionView(Map<String, Object> map,Long id){

        if(id != null){
            map.put("permission",permissionService.findById(id));
        }else{
            map.put("permission",new SysPermission());
        }

        return "/usermanage/permissionView";
    }


    @RequestMapping(value = "permissionUpdate", method = RequestMethod.POST)
    public String permissionUpdate(Map<String, Object> map,SysPermission permission){
        permissionService.save(permission);
        return "redirect:/AuthorizationManage/permissionView?id=" + permission.getId();
    }

    ///////////////////////////////////////////////////

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
    public String userRoleRelationEdit(@PathVariable("username")String username, UserInfo userInfo, SysRole sysRole, Map<String,Object> map){
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
